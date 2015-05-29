package highscore.jeopardyGame;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
	
	/**
	 * This method creates a new ObjectFactory
	 */
	public ObjectFactory(){
		
	}
	
	/**
	 * This method creates a new instance of Winners
	 * @return
	 */
	public Winner createWinner(){
		return new Winner();
	}
	
	/**
	 * This method creates a new instance of Looser
	 * @return
	 */
	public Loser createLooser(){
		return new Loser();
	}

}
