package zwei.admin.elements;

import java.util.Map;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class DojoValidationTextbox extends Element {
	
	public DojoValidationTextbox() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DojoValidationTextbox(String name,
			String target, String value, Node params,
			Map<String, String> form) {
		super(name, target, value, params, form);
		// TODO Auto-generated constructor stub
	}

	public DojoValidationTextbox(String name,
			String target, String value, Map<String, String> params) {
		super(name, target, value, params);
		// TODO Auto-generated constructor stub
	}

	public DojoValidationTextbox(String name,
			String target, String value) {
		super(name, target, value);
		// TODO Auto-generated constructor stub
	}

	public DojoValidationTextbox(String name,
			String target) {
		super(name, target);
		// TODO Auto-generated constructor stub
	}

	public DojoValidationTextbox(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}


	/**
	 * Despliegue del elemento en formulario editable 
	 * @param i
	 * @param j
	 */
	public String edit(String i, String j) 
	{
		NamedNodeMap attributes = this.params.getAttributes();
		String readonly = attributes.getNamedItem("readonly") != null && attributes.getNamedItem("readonly").getNodeValue().equals("true") ?  "readonly" : "";
		String disabled = attributes.getNamedItem("disabled") != null && attributes.getNamedItem("disabled").getNodeValue().equals("true") ? "disabled" : "";
		String required = attributes.getNamedItem("required") != null && attributes.getNamedItem("required").getNodeValue().equals("true") ? "required=\"true\"" : "";
		String trim = attributes.getNamedItem("trim") != null && attributes.getNamedItem("trim").getNodeValue().equals("true") ? "trim=\"true\"" : "";
		String onblur = attributes.getNamedItem("onblur") != null ? "onblur=\"" + attributes.getNamedItem("onblur").getNodeValue() + "\"" : "";
		String regexp = attributes.getNamedItem("reg_exp") != null ? "RegExp=\"" + attributes.getNamedItem("reg_exp").getNodeValue() + "\"" : "";
		String invalid_message = attributes.getNamedItem("invalid_message") != null ? "invalidMessage=\"" + attributes.getNamedItem("invalid_message").getNodeValue() + "\"" : "";
		String prompt_message = attributes.getNamedItem("prompt_message") != null ? "promptMessage=\"" + attributes.getNamedItem("prompt_message").getNodeValue() + "\"" : "";
		String maxlength = attributes.getNamedItem("maxlength") != null ? "maxlength=\"" + attributes.getNamedItem("maxlength").getNodeValue() + "" : "";
		//return "<input dojoType=\"dijit.form.ValidationTextBox\" type=\"text\" style=\"display:$display\" id=\"edit{$i}_{$j}\" name=\"$this->target[$i]\" value=\"".str_replace('"','&quot;',$this->value)."\" $readonly $disabled $required $regexp $trim/>";
		return "<input dojoType=\"dijit.form.ValidationTextBox\" type=\"text\" style=\"display:block\" id=\"edit" + i + "_" + j + "\" name=\"" 
		+ this.target + "["+i+"]\" value=\"" + this.value.replace("\"", "&quot;") + "\" "+readonly + disabled + required + regexp + trim + invalid_message + prompt_message + maxlength + onblur + " />";
	}

	/**
	 * obtener valor del formulario
	 * @param value
	 * @return
	 */
	public String get(String value)
	{
		return value;
	}
}
