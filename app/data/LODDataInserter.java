import java.util.List;
import java.util.ArrayList;
import java.util.Locale;

import play.db.jpa.Transactional;
import models.Answer;
import models.Category;
import models.JeopardyDAO;
import models.Question;

import static at.ac.tuwien.big.we.dbpedia.api.DBPediaService.*;
import at.ac.tuwien.big.we.dbpedia.api.*;
import at.ac.tuwien.big.we.dbpedia.vocabulary.*;
import at.ac.tuwien.big.we.dbpedia.example.*;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.sparql.vocabulary.FOAF;
import com.hp.hpl.jena.vocabulary.RDF;
import com.hp.hpl.jena.vocabulary.RDFS;

public class LODDataInserter {
    
    public static List<Category> loadCategoryData() {
		
		List<Category> categories = new ArrayList<>();
		
		if(!DBPediaService.isAvailable()) {
            return categories;
		}
		
		Category category = new Category();
        category.setName("Movies", "en");
        category.setName("Filme", "de");
        
        category.addQuestion(getQuestion1());
        category.addQuestion(getQuestion2());
        category.addQuestion(getQuestion3());
        category.addQuestion(getQuestion4());
        category.addQuestion(getQuestion5());
        
        categories.add(category);
		return categories;
	}
	
	@Transactional
	public static List<Category> insertData() {
		List<Category> lodCategories = loadCategoryData();
		for(Category category : lodCategories)
			JeopardyDAO.INSTANCE.persist(category);
		return lodCategories;
	}
	
	private static Question createQuestion(String textEN, String textDE, int value, Model correctAnswers, Model wrongAnswers) {
	    
	    Question question = new Question();
	    question.setTextEN(textEN);
	    question.setTextDE(textDE);
	    question.setValue(value);
	    
	    Answer answer = new Answer();
	    
	    List<String> correctAnswersEN = DBPediaService.getResourceNames(correctAnswers, Locale.ENGLISH);
        List<String> correctAnswersDE = DBPediaService.getResourceNames(correctAnswers, Locale.GERMAN);
	    
	    int correct = correctAnswersEN.size();
	    
	    for (int i = 0; i<correct; i++) {
	        
	        answer = new Answer();
	        answer.setTextEN(correctAnswersEN.get(i));
	        answer.setTextDE(correctAnswersDE.get(i));
	        
	        question.addRightAnswer(answer);
	    }
	    
	    List<String> wrongAnswersEN = DBPediaService.getResourceNames(wrongAnswers, Locale.ENGLISH);
        List<String> wrongAnswersDE = DBPediaService.getResourceNames(wrongAnswers, Locale.GERMAN);
	    
	    int wrong = wrongAnswersEN.size();
	    
	    for (int i = 0; i<wrong; i++) {
	        
	        answer = new Answer();
	        answer.setTextEN(wrongAnswersEN.get(i));
	        answer.setTextDE(wrongAnswersDE.get(i));
	        
	        question.addWrongAnswer(answer);
	    }
	    
	    return question;
	    
	}
	
	private static Question getQuestion1() {
	    
	    Resource composer = loadStatements(DBPedia.createResource("Hans_Zimmer"));
		
		SelectQueryBuilder movieQuery = DBPediaService.createQueryBuilder()
			.setLimit(3)
			.addWhereClause(RDF.type, DBPediaOWL.Film)
			.addPredicateExistsClause(FOAF.name)
			.addPredicateExistsClause(RDFS.label)
			.addWhereClause(DBPediaOWL.musicComposer, composer)
			.addFilterClause(RDFS.label, Locale.ENGLISH)
			.addFilterClause(RDFS.label, Locale.GERMAN);
            
        Model hansZimmerMovies = DBPediaService.loadStatements(movieQuery.toQueryString());
        
        movieQuery.removeWhereClause(DBPediaOWL.musicComposer, composer);
        movieQuery.addMinusClause(DBPediaOWL.musicComposer, composer);
        
        Model noHansZimmerMovies = DBPediaService.loadStatements(movieQuery.toQueryString());
        
		String englishQuestionText = "Which are movies with soundtrack composed by Hans Zimmer?";
        String germanQuestionText = "Für welche Filme schrieb Hans Zimmer den Soundtrack?";
        int value = 50;
	    
	    return createQuestion(englishQuestionText, germanQuestionText, value, hansZimmerMovies, noHansZimmerMovies);
	}
	
	private static Question getQuestion2() { 
	    
	    Resource director = loadStatements(DBPedia.createResource("Steven_Spielberg"));
		
		SelectQueryBuilder movieQuery = DBPediaService.createQueryBuilder()
			.setLimit(2)
			.addWhereClause(RDF.type, DBPediaOWL.Film)
			.addPredicateExistsClause(FOAF.name)
			.addPredicateExistsClause(RDFS.label)
			.addWhereClause(DBPediaOWL.director, director)
			.addWhereClause(DBPediaOWL.producer, director)
			.addFilterClause(RDFS.label, Locale.ENGLISH)
			.addFilterClause(RDFS.label, Locale.GERMAN);
	    
	    Model producer = DBPediaService.loadStatements(movieQuery.toQueryString());
	    
	    movieQuery.removeWhereClause(DBPediaOWL.producer, director);
        movieQuery.addMinusClause(DBPediaOWL.producer, director);
        
        Model notProducer = DBPediaService.loadStatements(movieQuery.toQueryString());
        
        String englishQuestionText = "In which of his films Stephen Spielberg was director and producer at the same time?";
        String germanQuestionText = "In welchen seiner Filme war Stephen Spielberg gleichzeitig Regisseur und Produzent?";
        int value = 30;
	    
	    return createQuestion(englishQuestionText, germanQuestionText, value, producer, notProducer);
	}
	
	private static Question getQuestion3() {
	    
	    Resource distributor = loadStatements(DBPedia.createResource("Walt_Disney_Studios_Motion_Pictures"));
		
		SelectQueryBuilder movieQuery = DBPediaService.createQueryBuilder()
			.setLimit(3)
			.addWhereClause(RDF.type, DBPediaOWL.Film)
			.addPredicateExistsClause(FOAF.name)
			.addPredicateExistsClause(RDFS.label)
			.addWhereClause(DBPediaOWL.distributor, distributor)
			.addFilterClause(RDFS.label, Locale.ENGLISH)
			.addFilterClause(RDFS.label, Locale.GERMAN);
	    
	    Model disneyMovies = DBPediaService.loadStatements(movieQuery.toQueryString());
	    
	    movieQuery.removeWhereClause(DBPediaOWL.distributor, distributor);
        movieQuery.addMinusClause(DBPediaOWL.distributor, distributor);
        
        Model noDisneyMovies = DBPediaService.loadStatements(movieQuery.toQueryString());
        
        String englishQuestionText = "Which of the following are Disney movies?";
        String germanQuestionText = "Welche der folgenden Filme sind von Disney?";
        int value = 20;
	    
	    return createQuestion(englishQuestionText, germanQuestionText, value, disneyMovies, noDisneyMovies);
	    
	}
	
	private static Question getQuestion4() { 
	    
	    Resource brad = loadStatements(DBPedia.createResource("Brad_Pitt"));
	    Resource angelina = loadStatements(DBPedia.createResource("Angelina_Jolie"));
		
		SelectQueryBuilder movieQuery = DBPediaService.createQueryBuilder()
			.setLimit(3)
			.addWhereClause(RDF.type, DBPediaOWL.Film)
			.addPredicateExistsClause(FOAF.name)
			.addPredicateExistsClause(RDFS.label)
			.addWhereClause(DBPediaOWL.starring, brad)
			.addWhereClause(DBPediaOWL.starring, angelina)
			.addFilterClause(RDFS.label, Locale.ENGLISH)
			.addFilterClause(RDFS.label, Locale.GERMAN);
	    
	    Model brangelinaMovies = DBPediaService.loadStatements(movieQuery.toQueryString());
	    
	    movieQuery.setLimit(5);
	    movieQuery.removeWhereClause(DBPediaOWL.starring, angelina);
        movieQuery.addMinusClause(DBPediaOWL.starring, angelina);
        
        Model noBrangelinaMovies = DBPediaService.loadStatements(movieQuery.toQueryString());
        
        String englishQuestionText = "In which of the following movies Brad Pitt and Angelina Jolie performed together?";
        String germanQuestionText = "In welchen der folgenden Filme spielten Brad Pitt und Angelina Jolie zusammen mit?";
        int value = 40;
	    
	    return createQuestion(englishQuestionText, germanQuestionText, value, brangelinaMovies, noBrangelinaMovies);
	    
	}
	private static Question getQuestion5() { 
	    
	    Resource actor = loadStatements(DBPedia.createResource("Leonardo_DiCaprio"));
		
		SelectQueryBuilder movieQuery = DBPediaService.createQueryBuilder()
			.setLimit(4)
			.addWhereClause(RDF.type, DBPediaOWL.Film)
			.addPredicateExistsClause(FOAF.name)
			.addPredicateExistsClause(RDFS.label)
			.addWhereClause(DBPediaOWL.starring, actor)
			.addFilterClause(RDFS.label, Locale.ENGLISH)
			.addFilterClause(RDFS.label, Locale.GERMAN);
            
        Model leoMovies = DBPediaService.loadStatements(movieQuery.toQueryString());
        
        movieQuery.setLimit(2);
        movieQuery.removeWhereClause(DBPediaOWL.starring, actor);
        movieQuery.addMinusClause(DBPediaOWL.starring, actor);
        
        Model noLeoMovies = DBPediaService.loadStatements(movieQuery.toQueryString());
        
		String englishQuestionText = "Leonardo DiCaprio performed in this movie.";
        String germanQuestionText = "Leonardo DiCaprio spielte in diesem Film mit.";
        int value = 10;
	    
	    return createQuestion(englishQuestionText, germanQuestionText, value, leoMovies, noLeoMovies);
	}
	
}