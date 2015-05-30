
package highscore;

import highscore.data.HighScoreRequestType;

import javax.xml.namespace.QName;
import javax.xml.ws.*;

import java.net.MalformedURLException;
import java.net.URL;


@WebServiceClient(name = "PublishHighScoreService", targetNamespace = "http://big.tuwien.ac.at/we/highscore", wsdlLocation = "http://playground.big.tuwien.ac.at:8080/highscoreservice/PublishHighScoreService?wsdl")
public class PublishHighScoreService extends Service implements PublishHighScoreEndpoint{

    private final static URL PUBLISHHIGHSCORESERVICE_WSDL_LOCATION;
    private final static WebServiceException PUBLISHHIGHSCORESERVICE_EXCEPTION;
    private final static QName PUBLISHHIGHSCORESERVICE_QNAME = new QName("http://big.tuwien.ac.at/we/highscore", "PublishHighScoreService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://playground.big.tuwien.ac.at:8080/highscoreservice/PublishHighScoreService?wsdl");
            play.Logger.info("Schritt 1");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        PUBLISHHIGHSCORESERVICE_WSDL_LOCATION = url;
        PUBLISHHIGHSCORESERVICE_EXCEPTION = e;
    }

    public PublishHighScoreService() {
        super(__getWsdlLocation(), PUBLISHHIGHSCORESERVICE_QNAME);
        play.Logger.info("Schritt 2");
    }

    public PublishHighScoreService(WebServiceFeature... features) {
        super(__getWsdlLocation(), PUBLISHHIGHSCORESERVICE_QNAME, features);
        play.Logger.info("Schritt 3");
    }

    public PublishHighScoreService(URL wsdlLocation) {
        super(wsdlLocation, PUBLISHHIGHSCORESERVICE_QNAME);
        play.Logger.info("Schritt 4");
    }

    public PublishHighScoreService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, PUBLISHHIGHSCORESERVICE_QNAME, features);
        play.Logger.info("Schritt 5");
    }

    public PublishHighScoreService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
        play.Logger.info("Schritt 6");
    }

    public PublishHighScoreService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
        play.Logger.info("Schritt 7");
    }

    /**
     * 
     * @return
     *     returns PublishHighScoreEndpoint
     */
    @WebEndpoint(name = "PublishHighScorePort")
    public PublishHighScoreEndpoint getPublishHighScorePort() {
    	play.Logger.info("Schritt 8");
        return super.getPort(new QName("http://big.tuwien.ac.at/we/highscore", "PublishHighScorePort"), PublishHighScoreEndpoint.class);
        
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns PublishHighScoreEndpoint
     */
    @WebEndpoint(name = "PublishHighScorePort")
    public PublishHighScoreEndpoint getPublishHighScorePort(WebServiceFeature... features) {
    	play.Logger.info("Schritt 9");
        return super.getPort(new QName("http://big.tuwien.ac.at/we/highscore", "PublishHighScorePort"), PublishHighScoreEndpoint.class, features);
    }

    private static URL __getWsdlLocation() {
        if (PUBLISHHIGHSCORESERVICE_EXCEPTION!= null) {
            throw PUBLISHHIGHSCORESERVICE_EXCEPTION;
        }
        return PUBLISHHIGHSCORESERVICE_WSDL_LOCATION;
    }

	@Override
	public String publishHighScore(HighScoreRequestType in) throws Failure {
		Endpoint endpoint = Endpoint.publish("http://playground.big.tuwien.ac.at:8080/highscoreservice/", this);
		return endpoint.toString();
	}

}
