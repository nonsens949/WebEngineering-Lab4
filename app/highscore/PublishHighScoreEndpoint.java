
package highscore;

import highscore.data.HighScoreRequestType;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebService(name = "PublishHighScoreEndpoint", targetNamespace = "http://big.tuwien.ac.at/we/highscore")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    highscore.ObjectFactory.class,
    highscore.data.ObjectFactory.class,
    highscore.jeopardyGame.ObjectFactory.class
})
public interface PublishHighScoreEndpoint {


    /**
     * 
     *
     * @param in
     * @return
     *     returns java.lang.String
     * @throws Failure
     */
    @WebMethod(operationName = "PublishHighScore", action = "/PublishHighScore")
    @WebResult(name = "HighScoreResponse", targetNamespace = "http://big.tuwien.ac.at/we/highscore/data", partName = "out")
    public String publishHighScore(
            @WebParam(name = "HighScoreRequest", targetNamespace = "http://big.tuwien.ac.at/we/highscore/data", partName = "in")
            HighScoreRequestType in)
            throws Failure
    ;

}
