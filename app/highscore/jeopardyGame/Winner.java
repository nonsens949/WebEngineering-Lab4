package highscore.jeopardyGame;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

//Every non static, non transient field in a class will be automatically bound to XML
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder={"password","firstname","lastname","points"})
public class Winner{

	@XmlElement(required = true)
	protected String password;
	
	@XmlElement(required = true)
	protected String firstname;
	
	@XmlElement(required = true)
	protected String lastname;

	@XmlElement(required = true)
	protected int points;
	
	@XmlAttribute(name="BirthDate")
	protected XMLGregorianCalendar birthdate;
	
	@XmlAttribute(name = "gender", required=true)
	protected String gender;

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * @param firstname the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * @param lastname the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	 * @return the birthdate
	 */
	public XMLGregorianCalendar getBirthdate() {
		return birthdate;
	}

	/**
	 * @param birthdate the birthdate to set
	 */
	public void setBirthdate(XMLGregorianCalendar birthdate) {
		this.birthdate = birthdate;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	/**
	 * @return the points
	 */
	public int getPoints() {
		return points;
	}

	/**
	 * @param points the points to set
	 */
	public void setPoints(int points) {
		this.points = points;
	}
	
}
