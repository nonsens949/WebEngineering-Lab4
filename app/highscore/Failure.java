package highscore;

import javax.xml.ws.WebFault;

@WebFault(name = "Failure", targetNamespace = "http://big.tuwien.ac.at/we/highscore")
public class Failure extends Exception{
	
	/**
	 * contains detailed info about the failure
	 */
	private FailureType failure;
	
	/**
	 * This method creates a new instance
	 * @param message
	 * @param failure
	 */
	public Failure(String message, FailureType failure){
		super(message);
		this.failure = failure;
		play.Logger.info("Schritt 10");
	}
	
	/**
	 * This method creates a new instance
	 * @param message
	 * @param failure
	 * @param cause  another throwable that caused this throwable to be constructed
	 */
	public Failure(String message, FailureType failure, Throwable cause){
		super(message,cause);
		this.failure = failure;
		play.Logger.info("Schritt 11");
	}
	
	/**
	 * this method returns the fault bean highscore.FailureType
	 * @return
	 */
	public FailureType getInfo(){
		play.Logger.info("Schritt 12");
		return this.failure;
		
	}
	
}
