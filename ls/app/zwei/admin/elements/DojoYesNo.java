/**
 * 
 */
package zwei.admin.elements;

import java.util.HashMap;
import java.util.Map;

/**
 * Filtering Select Yes No
 *
 */
public class DojoYesNo extends Element {

	/**
	 * @param visible
	 * @param edit
	 * @param name
	 * @param target
	 * @param value
	 * @param params
	 */
	public DojoYesNo(String name, String target,
			String value, Map<String, String> params) {
		super(name, target, value, params);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param visible
	 * @param edit
	 * @param name
	 * @param target
	 * @param value
	 * @param params
	 * @param form
	 */
	public DojoYesNo(String name, String target,
			String value, Map<String, String> params, Map<String, String> form) {
		super(name, target, value, params, form);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param visible
	 * @param edit
	 * @param name
	 * @param target
	 * @param value
	 */
	public DojoYesNo(String name, String target, String value) {
		super(name, target, value);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param visible
	 * @param edit
	 * @param name
	 * @param target
	 */
	public DojoYesNo(String name, String target) {
		super(name, target);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param visible
	 * @param edit
	 * @param name
	 */
	public DojoYesNo(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}


	/**
	 * 
	 */
	public DojoYesNo() {
		// TODO Auto-generated constructor stub
	}

	public String edit(int i, int j) {
		Map<String, String> selected = new HashMap();
		selected.put("", "");
		if (this.value.equals("1")) {
			selected.put("1", "selected=\"selected\"");
		} else {
			selected.put("0", "selected=\"selected\"");
		}
		String returns = "<select dojoType=\"dijit.form.FilteringSelect\"  value=\"1\" style=\"width:50px;display:block\"  id=\"edit"+i+"_"+j+"\" name=\""+this.target+"["+i+"]\" >\n"+
				"<option value=\"1\" "+selected.get("1")+">S&iacute;</option>\n"+
				"<option value=\"0\" "+selected.get("2")+">No</option>\n"+
				"</select>\n";
		
		returns += "<script type=\"dojo/method\">\n"+
        "\tfunction switchYesNo"+i+"_"+j+"() {\n"+
		"\t\tif (dijit.byId('edit"+i+"_"+j+"')).get('value') == '0') dijit.byId('edit"+i+"_"+j+"').set('value', '0')\n"+
		"\t\telse if (dijit.byId('edit"+i+"_"+j+"')).get('value') == '1') dijit.byId('edit"+i+"_"+j+"').set('value', '1')\n"+
		"\t}\n"+
    	"</script>";
		
		return returns;
	}
}
