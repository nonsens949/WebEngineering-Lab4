package highscore.jeopardyGame;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

//Every non static, non transient field in a class will be automatically bound to XML
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder={"Password","FirstName","LastName","Points"})
public class Loser extends PlayerType{

}
