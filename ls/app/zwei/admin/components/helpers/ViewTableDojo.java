package zwei.admin.components.helpers;

import java.util.Map;

import org.w3c.dom.Element;

import zwei.admin.Controller;
import zwei.admin.Xml;

public class ViewTableDojo extends Controller
{
	int count;
	
	public ViewTableDojo(String page, String[] id, Map<String, String> form) {
		super(page, id, form);
		// TODO Auto-generated constructor stub
	}

	public String display(Xml xml) 
	{
		this.xml = xml;
		String out = "";
		if (this.component.getAttribute("search") != null && this.component.getAttribute("search").equals("true")/* && $this->_acl->isUserAllowed($this->page, 'LIST')*/)
		{
			if (this.component.getAttribute("search_type").equals("multiple")){
				//out += this.searcherMultiple(); [TODO] habilitar
			}else{
				//out += this.searcher(); [TODO] habilitar
			}
		}
		String params = this.getRequestedParams();
		String store = "";
		
		if (this.component.getAttribute("list") != null && this.component.getAttribute("list").equals("true")/* && $this->_acl->isUserAllowed($this->page, 'LIST')*/) {
			out += "\r\n <span dojoType=\"dojo.data.ItemFileReadStore\" id=\"store_grid\" jsId=\"store_grid\" url=\""+this.baseUrl+"/objects?model="+this.component.getAttribute("target")+"&format=json"+params+"\"></span>";
			store = "store=\"store_grid\"";
		}
		
		count = this.elements.getLength();
		int	widthCol = 120;
		int widthTable = 0;

		Element attributes;
		for (int i=0; i<count; i++) {
			attributes = (Element) this.elements.item(i).getAttributes(); 
			if (attributes.getAttribute("visible").equals("true")) {
				widthTable += attributes.getAttribute("width") != null ? Integer.parseInt(attributes.getAttribute("width")) : widthCol;
			}
		}
		widthTable += 40;
		
		String dojoType = this.component.getAttribute("table_dojo_type") != null ? "dojoType=\""+this.component.getAttribute("table_dojo_type")+"\"" : "dojoType=\"dojox.grid.DataGrid\"";
		String plugins = this.component.getAttribute("plugins") != null ? "plugins=\""+this.component.getAttribute("plugins")+"\"" : "plugin=\"dojox.grid.DataGrid\"";

		out += "\r\n<table "+ dojoType + plugins + " id=\"main_grid\" jsId=\"main_grid\" " + store + " clientSort=\"true\" style=\"width:" + widthTable + "px; height: 320px;\" rowSelector=\"20px\" rowsPerPage=\"10\" noDataMessage=\"Sin datos.\">\r\n<thead><tr>\r\n";

		for (int i=0; i<count; i++) {
			attributes = (Element) this.elements.item(i).getAttributes(); 
			String target = attributes.getAttribute("field") != null ? attributes.getAttribute("field") : attributes.getAttribute("target");
			String formatter = attributes.getAttribute("type") != null  && attributes.getAttribute("type").equals("dojo_yes_no") ? "formatter=\"formatYesNo\"" : "";
			if (attributes.getAttribute("formatter") != null) formatter = "formatter=\"" + attributes.getAttribute("formatter") + "\"";
			String width = attributes.getAttribute("width") != null ? attributes.getAttribute("width") : Integer.toString(widthCol);

			if (attributes.getAttribute("visible").equals(true)){
				out += "\t\t<th field=\""+target+"\" editable=\"false\" width=\""+width+"px\" "+formatter+">"+attributes.getAttribute("name")+"</th>\r\n";
			}
		}
		
		out += "\t</tr>\r\n</thead></table>\r\n";

		if (this.form.get("id") != null) {
			out += "<input type=\"hidden\" name=\"id\" id=\"id\" value=\"" + this.form.get("id") + "\">\r\n";
		}
		
		return out;
	}
}
