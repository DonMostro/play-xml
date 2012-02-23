package zwei.admin;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Xml 
{
	private File file;
	private Element component;
	private NodeList elements;
	private NodeList tabs;

	public Xml(File file) 
	{
		this.file = file;
	}
	
	public void parse() throws ParserConfigurationException, SAXException, IOException
	{
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		db = dbf.newDocumentBuilder();

		Document doc;
		doc = db.parse(file);

		doc.getDocumentElement().normalize();
		
		this.component = doc.getDocumentElement();
		this.elements = doc.getElementsByTagName("element");
		this.tabs = doc.getElementsByTagName("tab");
	}
	
	public NodeList getElements()
	{
		return this.elements;
	}
	
	public NodeList getTabs()
	{
		return this.tabs;
	}
	
	public Element getComponent()
	{
		return this.component;
	}	
}
