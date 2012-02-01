package zwei.admin.components;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import play.Play;

import zwei.admin.Controller;

public class TableDojo extends Controller
{
	public TableDojo(String page, String[] id) {
		super(page, id);
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
		
		
		return out;
	}
}
