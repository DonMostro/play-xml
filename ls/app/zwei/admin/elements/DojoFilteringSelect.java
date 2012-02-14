package zwei.admin.elements;

import zwei.admin.ClassInvoker;

public class DojoFilteringSelect extends Element
{
	public String edit(int i, int j)
	{
		String readonly = this.params.get("readonly") != null && this.params.get("readonly").equals("true") ?  "readonly" : "";
		String disabled = this.params.get("disabled") != null && this.params.get("disabled").equals("true") ? "disabled" : "";
		String required = this.params.get("required") != null && this.params.get("required").equals("true") ? "required=\"true\"" : "";
		String onchange = this.params.get("onchange") != null ? "onchange=\""+this.params.get("onchange")+"\"":"";	
		String invalid_message = this.params.get("invalid_message") != null ? "invalidMessage=\"" + this.params.get("invalid_message") + "\"" : "";
		String prompt_message = this.params.get("prompt_message") != null ? "promptMessage=\"" + this.params.get("prompt_message") + "\"" : "";		
		String value = this.params.get("default_value") != null && this.params.get("default_text") == null ? "value=\""+this.params.get("default_value")+"\"" : "";
		String options = this.options();
		return "<select dojoType=\"dijit.form.FilteringSelect\" id=\"edit"+i+"_"+j+"\" name=\""+this.target+"["+i+"]\" onload=\"dijit.byId('edit"+i+"_"+j+"').set('value', dijit.byId('edit"+i+"_"+j+"').get('value'))\" " 
				+ readonly + disabled + value + onchange + required + invalid_message + prompt_message + " style=\"display:block\" >\r\n"+options+"\r\n</select>\r\n";		
	}
	
	public String display(int i, int j)
	{
        if (this.value == null) {
        	this.value = form.get(this.target) != null ? form.get(this.target) : null;
        } else {
            value = this.value;
        }
		
		String modelName = "models." + zwei.utils.StringU.toClassName(form.get("model"));
		
		try {
			ClassInvoker modelClass = new ClassInvoker(modelName);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        //$id = !empty($this->params['TABLE_PK']) ? $this->params['TABLE_PK'] : $model->getName().'.id';
        
        String field = this.params.get("table_field") != null ? this.params.get("table_field") : this.params.get("field");
		
		
		return null;
	}
	
	public String options()
	{
		String options = "";
		return options;
	}
}
