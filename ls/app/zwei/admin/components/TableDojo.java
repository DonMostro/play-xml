package zwei.admin.components;

import java.io.IOException;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import play.Play;

import zwei.admin.Controller;
import zwei.admin.components.helpers.EditTableDojo;
import zwei.admin.components.helpers.ViewTableDojo;

public class TableDojo extends Controller
{
	public TableDojo(String page, String[] id, Map<String, String> form)
	{
		super(page, id, form);
	}
	public String display()
	{
		Element firstElement = (Element) this.elements.item(0);
		String out = "<h2>"+this.name+"</h2>\n";
		if (this.component.hasAttribute("js")) {
			out +="<script type=\"text/javascript\" src=\"" + this.baseUrl + "js/" + this.component.getAttribute("js") +"?version="+ Play.configuration.getProperty("application.versionNum") +"\"></script>\n";
		}
		out += "<div id=\"content_dojo\" style=\"width:100%\">\n";
		
		EditTableDojo editTable = null;
		if (firstElement.getNodeName().equals("tab")) {
			//edittable = new Zwei_Admin_Components_Helpers_EditTabs($this->page);
		/*} else if (this.tabs_dojo != null){
			$edittable = new Zwei_Admin_Components_Helpers_EditTabsDojo($this->page);*/
		} else {
			editTable = new EditTableDojo(page, id, form);
		}
		
		/*
		 * 		$id = $edittable->layout[1]['TARGET'];

		if(isset($request[$id])) $edittable->setId($request[$id]);

		$params = $this->getRequested_params();
		 * 
		 */
		
		
		
		ViewTableDojo viewTable = new ViewTableDojo(this.page, this.id, this.form);
		out += viewTable.display(this.xml);
		out += "\t<table align=\"center\">\n\t\t<tr>\n";
		
		
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
			if (this.component.getAttribute("add").equals("true") /*[TODO]  && $this->_acl->isUserAllowed($this->page, 'ADD') */){
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
		
		if (this.component.getAttribute("add").equals("true") && /*&& ($this->_acl->isUserAllowed($this->page, 'ADD') || $this->_acl->isUserAllowed($this->page, 'EDIT'))*/ !firstElement.getNodeName().equals("tab"))	{
			 
			out += "<div dojoType=\"dijit.Dialog\" id=\"formDialogo\" title=\"Agregar " + this.component.getAttribute("name") + ";\" execute=\"modify('" + this.component.getAttribute("target") + "',arguments[0]);\">\r\n";
			out += "\t"+editTable.display("add");
			out += "\n</div>\r\n";

		}

		if (this.component.getAttribute("edit").equals("true") && /*($this->_acl->isUserAllowed($this->page, 'EDIT')) && */ !firstElement.getNodeName().equals("tab"))
		{
			out += "<div dojoType=\"dijit.Dialog\" id=\"formDialogoEditar\" title=\"Agregar " + this.component.getAttribute("name") + ";\" execute=\"modify('" + this.component.getAttribute("target") + "',arguments[0]);\">\r\n";
			out += "\t"+editTable.display("edit");
			out += "\n</div>\r\n";
		}


		
		
		
		out += "</div>\n";
		out +="<div id=\"output_grid\"></div>";
		return out;
	}
}
