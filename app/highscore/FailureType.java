package highscore;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <xsd:complexType name="FailureType">
 * 	<xsd:complexContent>
 * 		<xsd:restriction base="http://www.w3.org/2001/XMLSchema}anyType">
 * 			<xsd:sequence>
 * 				<xsd:element name="Code" type="{http://www.w3.org/2001/XMLSchema}string"/>
 * 				<xsd:element name="Reason" type="{http://www.w3.org/2001/XMLSchema}string"/>
 * 				<xsd:element name="Detail" type="{http://www.w3.org/2001/XMLSchema}string"/>
 * 			</xsd:sequence>
 * 		</xsd:restriction>
 * 	</xsd:complexContent>
 * </xsd:complexType>
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FailureType", propOrder = {"Code","Reason","Detail"})
public class FailureType {
	
	@XmlElement(name = "Code", required = true)
	protected String code;
	
	@XmlElement(name = "Reason", required = true)
	protected String reason;
	
	@XmlElement(name = "Detail", required = true)
	protected String detail;

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}

	/**
	 * @param reason the reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}

	/**
	 * @return the detail
	 */
	public String getDetail() {
		return detail;
	}

	/**
	 * @param detail the detail to set
	 */
	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	
}
