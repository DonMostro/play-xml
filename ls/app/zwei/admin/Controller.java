package zwei.admin;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import play.Play;

public class Controller 
{
	protected String[] id;
	protected Element component;	
	protected String page;
	protected String name;
	protected String target;
	protected NodeList elements;
	protected NodeList tabs;
	protected String baseUrl;
	protected Xml xml;
	
	public Controller (String page, String[] id) 
	{
		this.page = page;
		this.id = id;
		this.baseUrl = (String) Play.configuration.get("application.baseUrl");
	}
	
    protected void initLayout() throws ParserConfigurationException, SAXException, IOException
    {
    	String pathComponents = (String) Play.configuration.get("application.pathComponents");
        Xml xml = new Xml(new File(pathComponents + "/" + this.page + ".xml"));
        xml.parse();
        this.xml = xml;
        this.component = this.xml.getComponent(); 
        this.name = component.getAttribute("name");
        this.target = component.getAttribute("target");
        this.elements = this.xml.getElements();
        this.tabs = this.xml.getTabs();
    }	
}