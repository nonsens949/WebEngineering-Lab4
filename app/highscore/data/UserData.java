package highscore.data;

import highscore.jeopardyGame.Loser;
import highscore.jeopardyGame.Winner;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UserData" , propOrder = {"Loser","Winner"})
@XmlRootElement(namespace = "http://big.tuwien.ac.at/we/highscore/data", name = "HighScoreRequet")
public class UserData {
	
	@XmlElement(namespace = "", required = true)
	protected Loser loser;
	
	@XmlElement(namespace = "", required = true)
	protected Winner winner;

	/**
	 * @return the loser
	 */
	public Loser getLoser() {
		return loser;
	}

	/**
	 * @param loser the loser to set
	 */
	public void setLoser(Loser loser) {
		this.loser = loser;
	}

	/**
	 * @return the winner
	 */
	public Winner getWinner() {
		return winner;
	}

	/**
	 * @param winner the winner to set
	 */
	public void setWinner(Winner winner) {
		this.winner = winner;
	}
}
