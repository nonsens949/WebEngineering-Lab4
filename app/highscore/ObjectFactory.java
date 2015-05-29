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
		
	}
	
	/**
	 * This method creates a new instance of FailureType
	 * @return
	 */
	public FailureType createFailureType(){
		return new FailureType();
	}
	
	@XmlElementDecl(namespace = "http://big.tuwien.ac.at/we/highscore", name = "Failure")
    public JAXBElement<FailureType> createFailure(FailureType value) {
        return new JAXBElement<FailureType>(_Failure_QName, FailureType.class, null, value);
    }
	
}
