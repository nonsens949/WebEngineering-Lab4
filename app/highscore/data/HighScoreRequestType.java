package highscore.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HighScoreRequestType", propOrder={"userKey" , "userData"})

@XmlRootElement(namespace = "http://big.tuwien.ac.at/we/highscore/data", name = "HighScoreRequest")
public class HighScoreRequestType {
	
	@XmlElement(namespace = "http://big.tuwien.ac.at/we/highscore/data", name = "UserKey", required = true)
	protected String userKey;
	
	@XmlElement(namespace = "http://big.tuwien.ac.at/we/highscore/data", name = "UserData", required = true)
	protected UserData userData;

	/**
	 * @return the userKey
	 */
	public String getUserKey() {
		return userKey;
	}

	/**
	 * @param userKey the userKey to set
	 */
	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}

	/**
	 * @return the userData
	 */
	public UserData getUserData() {
		return userData;
	}

	/**
	 * @param userData the userData to set
	 */
	public void setUserData(UserData userData) {
		this.userData = userData;
	}

}
