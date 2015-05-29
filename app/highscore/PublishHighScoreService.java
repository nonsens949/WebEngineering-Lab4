package highscore;

import javax.xml.ws.*;
import javax.xml.namespace.QName;
import java.net.MalformedURLException;
import java.net.URL;

/** 		
* 
* This class was generated by the JAX-WS RI.
* JAX-WS RI 2.2.4-b01
* Generated source version: 2.2
* 
*/

public class PublishHighScoreService extends Service{
	
	 private final static URL PUBLISHHIGHSCORESERVICE_WSDL_LOCATION;
	    private final static WebServiceException PUBLISHHIGHSCORESERVICE_EXCEPTION;
	    private final static QName PUBLISHHIGHSCORESERVICE_QNAME = new QName("http://big.tuwien.ac.at/we/highscore", "PublishHighScoreService");

	    static {
	        URL url = null;
	        WebServiceException e = null;
	        try {
	            url = new URL("http://playground.big.tuwien.ac.at:8080/highscore/PublishHighScoreService?wsdl");
	        } catch (MalformedURLException ex) {
	            e = new WebServiceException(ex);
	        }
	        PUBLISHHIGHSCORESERVICE_WSDL_LOCATION = url;
	        PUBLISHHIGHSCORESERVICE_EXCEPTION = e;
	    }

	    public PublishHighScoreService() {
	        super(__getWsdlLocation(), PUBLISHHIGHSCORESERVICE_QNAME);
	    }

	    public PublishHighScoreService(WebServiceFeature... features) {
	        super(__getWsdlLocation(), PUBLISHHIGHSCORESERVICE_QNAME, features);
	    }

	    public PublishHighScoreService(URL wsdlLocation) {
	        super(wsdlLocation, PUBLISHHIGHSCORESERVICE_QNAME);
	    }

	    public PublishHighScoreService(URL wsdlLocation, WebServiceFeature... features) {
	        super(wsdlLocation, PUBLISHHIGHSCORESERVICE_QNAME, features);
	    }

	    public PublishHighScoreService(URL wsdlLocation, QName serviceName) {
	        super(wsdlLocation, serviceName);
	    }

	    public PublishHighScoreService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
	        super(wsdlLocation, serviceName, features);
	    }

	    /**
	     * 
	     * @return
	     *     returns PublishHighScoreEndpoint
	     */
	    @WebEndpoint(name = "PublishHighScorePort")
	    public PublishHighScoreEndpoint getPublishHighScorePort() {
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
	        return super.getPort(new QName("http://big.tuwien.ac.at/we/highscore", "PublishHighScorePort"), PublishHighScoreEndpoint.class, features);
	    }

	    private static URL __getWsdlLocation() {
	        if (PUBLISHHIGHSCORESERVICE_EXCEPTION!= null) {
	            throw PUBLISHHIGHSCORESERVICE_EXCEPTION;
	        }
	        return PUBLISHHIGHSCORESERVICE_WSDL_LOCATION;
	    }

}