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
/**
 * Parseo de componentes XML Zwei Admin
 * @author rodrigo
 *
 */
public class Xml 
{
	private File file;
	private Element component;
	private NodeList elements;
	private NodeList tabs;

	/**
	 * Constructor, carga el archivo XML
	 * @param file
	 */
	public Xml(File file) 
	{
		this.file = file;
	}
	
	/**
	 * Parsea el componente XML, inicializa componente, elementos y pestañas
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
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
	
	/**
	 * Retorna Nodos element (elementos de formulario)
	 * @return
	 */
	public NodeList getElements()
	{
		return this.elements;
	}
	
	/**
	 * Retorna Nodos tab (pestañas de formulario)
	 * @return
	 */
	public NodeList getTabs()
	{
		return this.tabs;
	}
	
	/**
	 * Retorna nodo principal (nodo component)
	 * @return
	 */
	public Element getComponent()
	{
		return this.component;
	}	
}
