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
	}
	
	/**
	 * This method creates a new instance
	 * @param message
	 * @param failure
	 * @param cause  another throwable that caused this throwable to be constructed
	 */
	public Failure(String message, FailureType failure, Throwable cause){
		super(message);
		this.failure = failure;
	}
	
	/**
	 * this method returns the fault bean highscore.FailureType
	 * @return
	 */
	public FailureType getInfo(){
		return this.failure;
	}
	
}
