package zwei.admin.components;

import java.io.IOException;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import play.Play;

import zwei.admin.Controller;
import zwei.admin.components.helpers.ViewTableDojo;

public class TableDojo extends Controller
{
	public TableDojo(String page, String[] id, Map<String, String> form)
	{
		super(page, id, form);
		try {
			this.initLayout();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String display()
	{
		String out = "<h2>"+this.name+"</h2>\n";
		if (this.component.hasAttribute("js")) {
			out +="<script type=\"text/javascript\" src=\"" + this.baseUrl + "js/" + this.component.getAttribute("js") +"?version="+ Play.configuration.getProperty("application.versionNum") +"\"></script>\n";
		}
		out += "<div id=\"content_dojo\" style=\"width:100%\">\n";
		ViewTableDojo viewTable = new ViewTableDojo(this.page, this.id, this.form);
		out += viewTable.display(this.xml);
		out += "\t<table align=\"center\">\n\t\t<tr>\n";
		
		Element firstElement = (Element) this.elements.item(0);
		
		if (firstElement.getNodeName().equals("tab")) {
			if(this.component.getAttribute("add").equals("true") /*[TODO]  && $this->_acl->isUserAllowed($this->page, 'ADD') */){
				out += "\t\t\t<td><button type=\"button\" dojoType=\"dijit.form.Button\" iconClass=\"dijitIconNewTask\" id=\"btnNuevoUsr\" onClick=\"cargarTabsPanelCentral('"+this.page+"','add');\">";
				out += "Agregar " + this.component.getAttribute("name");
				out += "</button></td>\n";
			}
			
			if (this.component.getAttribute("edit").equals("true") /*[TODO] && $this->_acl->isUserAllowed($this->page, 'EDIT') */){
				out += "\t\t\t<td><button type=\"button\" dojoType=\"dijit.form.Button\" iconClass=\"dijitIconEdit\" id=\"btnEditarUsr\" onClick=\"cargarTabsPanelCentral('$this->page','edit');\">";
				out += "Editar " + this.component.getAttribute("name");
				out += "</button></td>\n";
			}


		} else {
			if(this.component.getAttribute("add").equals("true") /*[TODO]  && $this->_acl->isUserAllowed($this->page, 'ADD') */){
				out += "\t\t\t<td><button type=\"button\" dojoType=\"dijit.form.Button\" iconClass=\"dijitIconNewTask\" id=\"btnNuevoUsr\" onClick=\"showDialog('add');\">";
				out += "Agregar "  + this.component.getAttribute("name");
				out += "</button></td>\n";
			}

			if (this.component.getAttribute("edit").equals("true") /*[TODO] && $this->_acl->isUserAllowed($this->page, 'EDIT') */){
				out += "\t\t\t<td><button type=\"button\" dojoType=\"dijit.form.Button\" iconClass=\"dijitIconEdit\" id=\"btnEditarUsr\" onClick=\"showDialog('edit');\">";
				out += "Editar " + this.component.getAttribute("name");
				out += "</button></td>\n";
			}
		}

		
		
		out += "\t\t</tr>\n\t</table>\n";
		out += "</div>\n";
		return out;
	}
}
