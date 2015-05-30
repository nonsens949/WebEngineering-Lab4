package highscore;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {
	
	private final static QName _Failure_QName = new QName("http://big.tuwien.ac.at/we/highscore", "Failure");

	/**
	 * This method creates a new instance of ObjectFactory
	 */
	public ObjectFactory(){
		play.Logger.info("Schritt 13");
	}
	
	/**
	 * This method creates a new instance of FailureType
	 * @return
	 */
	public FailureType createFailureType(){
		play.Logger.info("Schritt 14");
		return new FailureType();
	}
	
	@XmlElementDecl(namespace = "http://big.tuwien.ac.at/we/highscore", name = "Failure")
    public JAXBElement<FailureType> createFailure(FailureType value) {
		play.Logger.info("Schritt 15");
        return new JAXBElement<FailureType>(_Failure_QName, FailureType.class, null, value);
    }
	
}
