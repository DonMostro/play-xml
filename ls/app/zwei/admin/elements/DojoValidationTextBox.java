package zwei.admin.elements;

public class DojoValidationTextBox extends Element {
	
	/**
	 * Despliegue del elemento en formulario editable 
	 * @param i
	 * @param j
	 */
	public String edit(int i, int j) 
	{
		String readonly = this.params.get("readonly") != null && this.params.get("readonly").equals("true") ?  "readonly" : "";
		String disabled = this.params.get("disabled") != null && this.params.get("disabled").equals("true") ? "disabled" : "";
		String required = this.params.get("required") != null && this.params.get("required").equals("true") ? "required=\"true\"" : "";
		String trim = this.params.get("trim") != null && this.params.get("trim").equals("true") ? "trim=\"true\"" : "";
		String onblur = this.params.get("onblur") != null ? "onblur=\"" + this.params.get("onblur") + "\"" : "";
		String regexp = this.params.get("reg_exp") != null ? "RegExp=\"" + this.params.get("reg_exp") + "\"" : "";
		String invalid_message = this.params.get("invalid_message") != null ? "invalidMessage=\"" + this.params.get("invalid_message") + "\"" : "";
		String prompt_message = this.params.get("prompt_message") != null ? "promptMessage=\"" + this.params.get("prompt_message") + "\"" : "";
		String maxlength = this.params.get("maxlength") != null ? "maxlength=\"" + this.params.get("maxlength") + "" : "";
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
