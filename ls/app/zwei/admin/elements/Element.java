package zwei.admin.elements;

import java.util.Map;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

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
	protected Node params;
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
	public Element(String name, String target, String value, Map<String, String> form) 
	{
		this.name = name;
		this.target = target;
		this.value = value;
		this.form = form;
	}
	
	public Element(String name, String target, String value, Node params) 
	{
		this.name = name;
		this.target = target;
		this.value = value;
		this.params = params;
	}
	
	
	
	public Element(String name, String target, String value, 
			Node params, Map<String, String> form) 
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
	public String display(String i, String j) {
		String href;
		NamedNodeMap attributes = this.params.getAttributes(); 
		if (attributes.getNamedItem("link") != null) {
			if (attributes.getNamedItem("image") != null) {
				value = "<img src=\"" + attributes.getNamedItem("image").getNodeValue() + " alt=\"" + attributes.getNamedItem("value").getNodeValue() + "\"/>";
			} else {
				value = attributes.getNamedItem("default").getNodeValue();
			}
			href = attributes.getNamedItem("link").getNodeValue().replace("{id}", attributes.getNamedItem("id").getNodeValue());
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

	public String edit(String i, String j) {
		NamedNodeMap attributes = this.params.getAttributes(); 
		String readonly = attributes.getNamedItem("readonly") != null && attributes.getNamedItem("readonly").getNodeValue().equals("true") ? "readonly" : "";
		String disabled = attributes.getNamedItem("disabled") != null && attributes.getNamedItem("disabled").getNodeValue().equals("true") ? "disabled" : "";
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
