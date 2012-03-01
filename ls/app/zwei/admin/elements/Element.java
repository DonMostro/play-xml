package zwei.admin.elements;

import java.util.Map;

/**
 * 
 * Elementos para los formularios del CMS
 *
 * @version $Id:$
 * @since 0.1
 *
 */
public class Element {

	protected boolean visible;
	protected boolean edit;
	protected String name;
	protected String target;
	protected String value;
	protected Map<String, String> params;
	protected Map<String, String> form;
	
	/**
	 * 
	 * @param visible
	 * @param edit
	 * @param name
	 * @param target
	 * @param value
	 * @param params
	 */
	public Element(String name, String target, String value, Map<String, String> params) 
	{
		this.name = name;
		this.target = target;
		this.value = value;
		this.params = params;
	}
	
	
	public Element(String name, String target, String value, 
			Map<String, String> params, Map<String, String> form) 
	{
		this.name = name;
		this.target = target;
		this.value = value;
		this.params = params;
		this.form = form;		
	}
	
	/**
	 * 
	 * @param visible
	 * @param edit
	 * @param name
	 * @param target
	 * @param value
	 */
	public Element(String name, String target, String value) 
	{
		this.name = name;
		this.target = target;
		this.value = value;
	}

	/**
	 * 
	 * @param visible
	 * @param edit
	 * @param name
	 * @param target
	 */
	public Element(String name, String target) 
	{
		this.name = name;
		this.target = target;
		this.value = "";
	}
	
	/**
	 * 
	 * @param visible
	 * @param edit
	 * @param name
	 */
	public Element(String name) 
	{
		this.name = name;
		this.target = "";
		this.value = "";
	}
	
	/**
	 * 
	 */
	public Element() 
	{
		this.name = "";
		this.target = "";
		this.value = "";
	}
	
	/**
	 * Despliegue del elemento en formulario editable
	 * @param i
	 * @param j
	 * @return
	 */
	public String display(int i, int j) {
		String href;
		if (this.params.get("link") != null) {
			if (this.params.get("image") != null) {
				value = "<img src=\"" + this.params.get("image") + " alt=\"" + this.params.get("value") + "\"/>";
			} else {
				value = this.params.get("default");
			}
			href = this.params.get("link").replace("{id}", this.params.get("id"));
			href = href.replace("{value}", this.value);
			return "<a id=\"field" + i + "_" + j + "\" title=\"" + this.value + "\" name=\"" + this.target + "[]\" href=\"" + href + "\">" + value + "</a>";
		}
		return "<span id=\"field" + i + "_" + j + "\" name=\"" + this.target + "[]\">" + this.value + "</span>";
	}
	
	
	/**
	 * Despliegue del elemento en formulario editable
	 * @param i
	 * @param j
	 * @return input html
	 */

	public String edit(int i, int j) {
		String readonly = this.params.get("readonly") != null && this.params.get("readonly").equals("true") ? "readonly" : "";
		String disabled = this.params.get("disabled") != null && this.params.get("disabled").equals("true") ? "disabled" : "";
		//return "<input type=\"text\" style=\"display:$display\" id=\"edit{$i}_{$j}\" name=\"$this->target[$i]\" value=\"".str_replace('"','&quot;',$this->value)."\" $readonly $disabled/>";
		return "<input type=\"text\" style=\"display:block\" id=\"edit" + i + "_" + j + "\" name=\""+this.target+"["+i+"]\" value=\"" + this.value.replace("\"", "&quot;") +"\" "+readonly+disabled+"/>";
	}

	/**
	 * obtener valor del formulario
	 * @param $value
	 * @param $i
	 * @return unknown_type
	 */


	public String get()
	{
		return this.value;
	}
	/**
     * Sobrescribir para personalizar el elemento json al desplegar en EditTableDojo 
	 * @param i
	 * @param j
	 * @return javascript 
	 */
	public String editCustomDisplay(int i, int j)
	{
	   return "";	
	}

}
