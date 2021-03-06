package controllers;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;

import at.ac.tuwien.big.we.highscore.Failure;
import at.ac.tuwien.big.we.highscore.FailureType;
import at.ac.tuwien.big.we.highscore.PublishHighScoreEndpoint;
import at.ac.tuwien.big.we.highscore.PublishHighScoreService;
import at.ac.tuwien.big.we.highscore.data.GenderType;
import at.ac.tuwien.big.we.highscore.data.HighScoreRequestType;
import at.ac.tuwien.big.we.highscore.data.UserDataType;
import at.ac.tuwien.big.we.highscore.data.UserType;
import ch.qos.logback.core.rolling.helper.DateTokenConverter;
import models.Category;
import models.JeopardyDAO;
import models.JeopardyGame;
import models.JeopardyUser;
import play.Logger;
import play.cache.Cache;
import play.data.DynamicForm;
import play.data.DynamicForm.Dynamic;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;
import play.mvc.Security;
import twitter.ITwitterClient;
import twitter.TwitterClient;
import twitter.TwitterStatusMessage;
import views.html.jeopardy;
import views.html.question;
import views.html.winner;

@Security.Authenticated(Secured.class)
public class GameController extends Controller {
	
	protected static final int CATEGORY_LIMIT = 5;
	
	@Transactional
	public static Result index() {
		return redirect(routes.GameController.playGame());
	}
	
	@play.db.jpa.Transactional(readOnly = true)
	private static JeopardyGame createNewGame(String userName) {
		return createNewGame(JeopardyDAO.INSTANCE.findByUserName(userName));
	}
	
	@play.db.jpa.Transactional(readOnly = true)
	private static JeopardyGame createNewGame(JeopardyUser user) {
		if(user == null) // name still stored in session, but database dropped
			return null;

		Logger.info("[" + user + "] Creating a new game.");
		List<Category> allCategories = JeopardyDAO.INSTANCE.findEntities(Category.class);
		
		if(allCategories.size() > CATEGORY_LIMIT) {
			// select 5 categories randomly (simple)
			Collections.shuffle(allCategories);
			allCategories = allCategories.subList(0, CATEGORY_LIMIT);
		}
		Logger.info("Start game with " + allCategories.size() + " categories.");
		JeopardyGame game = new JeopardyGame(user, allCategories);
		cacheGame(game);
		return game;
	}
	
	private static void cacheGame(JeopardyGame game) {
		Cache.set(gameId(), game, 3600);
	}
	
	private static JeopardyGame cachedGame(String userName) {
		Object game = Cache.get(gameId());
		if(game instanceof JeopardyGame)
			return (JeopardyGame) game;
		return createNewGame(userName);
	}
	
	private static String gameId() {
		return "game." + uuid();
	}

	private static String uuid() {
		String uuid = session("uuid");
		if (uuid == null) {
			uuid = UUID.randomUUID().toString();
			session("uuid", uuid);
		}
		return uuid;
	}
	
	@Transactional
	public static Result newGame() {
		Logger.info("[" + request().username() + "] Start new game.");
		JeopardyGame game = createNewGame(request().username());
		return ok(jeopardy.render(game));
	}
	
	@Transactional
	public static Result playGame() {
		Logger.info("[" + request().username() + "] Play the game.");
		JeopardyGame game = cachedGame(request().username());
		if(game == null) // e.g., username still in session, but db dropped
			return redirect(routes.Authentication.login());
		if(game.isAnswerPending()) {
			Logger.info("[" + request().username() + "] Answer pending... redirect");
			return ok(question.render(game));
		} else if(game.isGameOver()) {
			Logger.info("[" + request().username() + "] Game over... redirect");
			
			//TODO replace string with actual uuid
			String uuid = publishHighScore(game);
			boolean success = publishUuid(uuid);
			
			return ok(winner.render(game, success, uuid));
			//return ok(winner.render(game));
		}			
		return ok(jeopardy.render(game));
	}
	
	@play.db.jpa.Transactional(readOnly = true)
	public static Result questionSelected() {
		JeopardyGame game = cachedGame(request().username());
		if(game == null || !game.isRoundStart())
			return redirect(routes.GameController.playGame());
		
		Logger.info("[" + request().username() + "] Questions selected.");		
		DynamicForm form = Form.form().bindFromRequest();
		
		String questionSelection = form.get("question_selection");
		
		if(questionSelection == null || questionSelection.equals("") || !game.isRoundStart()) {
			return badRequest(jeopardy.render(game));
		}
		
		game.chooseHumanQuestion(Long.parseLong(questionSelection));
		
		return ok(question.render(game));
	}
	
	@play.db.jpa.Transactional(readOnly = true)
	public static Result submitAnswers() {
		JeopardyGame game = cachedGame(request().username());
		if(game == null || !game.isAnswerPending())
			return redirect(routes.GameController.playGame());
		
		Logger.info("[" + request().username() + "] Answers submitted.");
		Dynamic form = Form.form().bindFromRequest().get();
		
		@SuppressWarnings("unchecked")
		Map<String,String> data = form.getData();
		List<Long> answerIds = new ArrayList<>();
		
		for(String key : data.keySet()) {
			if(key.startsWith("answers[")) {
				answerIds.add(Long.parseLong(data.get(key)));
			}
		}
		game.answerHumanQuestion(answerIds);
		if(game.isGameOver()) {
			return redirect(routes.GameController.gameOver());
		} else {
			return ok(jeopardy.render(game));
		}
	}
	
	@play.db.jpa.Transactional(readOnly = true)
	public static Result gameOver() {
		JeopardyGame game = cachedGame(request().username());
		if(game == null || !game.isGameOver())
			return redirect(routes.GameController.playGame());
		
		Logger.info("[" + request().username() + "] Game over.");	
		
		//TODO replace string with actual uuid
		String uuid = publishHighScore(game);
		boolean success = publishUuid(uuid);
		
		return ok(winner.render(game, success, uuid));
		//return ok(winner.render(game));
	}
	
	public static boolean publishUuid(String uuid){
		
		TwitterStatusMessage twitterMessage = new TwitterStatusMessage(request().username(), uuid, new Date());
		
		ITwitterClient twitterClient = new TwitterClient();
		
		boolean success = true;
		try{
			twitterClient.publishUuid(twitterMessage);
		} catch (Exception e){
			success = false;
			play.Logger.error("Exception caught while publishing UUID");
		}	
		if(success){
			play.Logger.info("Publishing the UUID was successful");
		} else {
			play.Logger.info("Publishing the UUID failed");
		}
		
		return success;
	}
	
	///HighScore-Service///
	private static String publishHighScore(JeopardyGame game){
		
		PublishHighScoreService highScoreService = new PublishHighScoreService();
		play.Logger.info("Schritt 1 GameController");
		PublishHighScoreEndpoint highScoreEndpoint = highScoreService.getPublishHighScorePort();
		play.Logger.info("Schritt 2 GameController");
		
		//HighScoreRequestType
		HighScoreRequestType highScoreRequestType = new HighScoreRequestType();
		highScoreRequestType.setUserKey("3ke93-gue34-dkeu9");
		play.Logger.info("Schritt 3 GameController");
		
		
		//Loser//
		UserType loser = new UserType();
		//Winner//
		UserType winner = new UserType();
		
		//Password
		loser.setPassword("");
		winner.setPassword("");
		
		//Firstname
		String loserFirstname = game.getLoser().getUser().getFirstName();
		play.Logger.info("LoserFirstname " + loserFirstname);
		if(loserFirstname == ""){
			loser.setFirstName("Anonymus");
		}else{
			loser.setFirstName(loserFirstname);
		}
			
		String winnerFirstname = game.getWinner().getUser().getFirstName();
		if(winnerFirstname == ""){
			winner.setFirstName("Anonymus");
		}else{
			winner.setFirstName(winnerFirstname);
		}
		
		//Lastname
		String loserLastname = game.getLoser().getUser().getLastName();
		if(loserLastname == ""){
			loser.setLastName("Anonym");
		}else{
			loser.setLastName(loserLastname);
		}
		
		String winnerLastname = game.getWinner().getUser().getLastName();
		if(winnerLastname == ""){
			winner.setLastName("Anonym");
		}else{
			winner.setLastName(winnerLastname);
		}
		
		//Birthdate
		XMLGregorianCalendar loserBirthday = dateToXMLGregorianCalendar(game.getLoser().getUser().getBirthDate());
		if(loserBirthday == null){
			loser.setBirthDate(dateToXMLGregorianCalendar(new Date(1900,1,1)));
		}else{
			loser.setBirthDate(loserBirthday);
		}
		
		XMLGregorianCalendar winnerBirthday = dateToXMLGregorianCalendar(game.getWinner().getUser().getBirthDate());
		if(winnerBirthday == null){
			winner.setBirthDate(dateToXMLGregorianCalendar(new Date(1900,1,1)));
		}else{
			winner.setBirthDate(winnerBirthday);
		}
		
		//Gender
		GenderType genderLoser = GenderType.fromValue(game.getLoser().getUser().getGender().toString());
		if(genderLoser == null){
			loser.setGender(GenderType.MALE);
		}else{
			loser.setGender(genderLoser);
		}
		
		GenderType genderWinner = GenderType.fromValue(game.getWinner().getUser().getGender().toString());
		if(genderWinner == null){
			winner.setGender(GenderType.MALE);
		}else{
			winner.setGender(genderWinner);
		}
		
		//Points
		loser.setPoints(game.getLoser().getProfit());
		winner.setPoints(game.getWinner().getProfit());
		
		//UserDataType
		UserDataType userData = new UserDataType();
		userData.setLoser(loser);
		userData.setWinner(winner);
		
		highScoreRequestType.setUserData(userData);
		
		
		try{
			play.Logger.info("Schritt 4 GameController");
			String highScoreUUID = highScoreEndpoint.publishHighScore(highScoreRequestType);
			play.Logger.info("HighScore UUID published" + highScoreUUID);
			return highScoreUUID;
		}catch(Failure e){
			play.Logger.error("Publish Highscore Service Error", e);
		}
		
		return null;
	}
	
	/**
	 * This method converts Date to XMLGregorianCalendar
	 * @param date
	 * @return
	 */
	private static XMLGregorianCalendar dateToXMLGregorianCalendar(Date date){
		if(date == null){
			return null;
		}
		XMLGregorianCalendar xmlCalendar;
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		
		try{
			xmlCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar();
			xmlCalendar.setYear(calendar.get(Calendar.YEAR));
			xmlCalendar.setMonth(calendar.get(Calendar.MONTH)+1);
			xmlCalendar.setDay(calendar.get(Calendar.DAY_OF_MONTH));
			return xmlCalendar;
			
		}catch(DatatypeConfigurationException e){
			play.Logger.error("DatatypeConfigurationException", e);
		}
		
		return null;
	}
}
