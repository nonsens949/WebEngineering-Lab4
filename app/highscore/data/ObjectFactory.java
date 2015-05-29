package highscore.data;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {
	
	private final static QName _HighScoreRequest_QName = new QName("http://big.tuwien.ac.at/we/highscore/data", "HighScoreRequest");
	private final static QName _HighScoreResponse_QName = new QName("http://big.tuwien.ac.at/we/highscore/data", "HighScoreResponse");
	private final static QName _UserKey_QName = new QName("http://big.tuwien.ac.at/we/highscore/data", "UserKey");
	
	public ObjectFactory(){
		
	}
	
	/**
	 * This method creates a new instance of HighscoreRequestType
	 * @return
	 */
	public HighScoreRequestType createHighScoreRequestType(){
		return new HighScoreRequestType();
	}
	
	@XmlElementDecl(namespace = "http://big.tuwien.ac.at/we/highscore/data", name = "HighScoreRequest")
    public JAXBElement<HighScoreRequestType> createHighScoreRequest(HighScoreRequestType value) {
        return new JAXBElement<HighScoreRequestType>(_HighScoreRequest_QName, HighScoreRequestType.class, null, value);
    }
	
	@XmlElementDecl(namespace = "http://big.tuwien.ac.at/we/highscore/data", name = "HighScoreResponse")
	public JAXBElement<String> createHighScoreResponse(String value){
		return new JAXBElement<String>(_HighScoreResponse_QName, String.class, null, value);
	}
	
	@XmlElementDecl(namespace = "http://big.tuwien.ac.at/we/highscore/data", name = "UserKey")
    public JAXBElement<String> createUserKey(String value) {
        return new JAXBElement<String>(_UserKey_QName, String.class, null, value);
    }

}
