package zwei.admin;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
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
	protected Map<String, String> form;
	
	
	public Controller (String page, String[] id, Map<String, String> form) 
	{
		this.page = page;
		this.id = id;
		this.form = form;
		this.baseUrl = (String) Play.configuration.get("application.baseUrl");
	}
	
    protected void initLayout() 
    {
    	String pathComponents = (String) Play.configuration.get("application.pathComponents");
        Xml xml = new Xml(new File(pathComponents + "/" + this.page + ".xml"));
        try {
			xml.parse();
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
        this.xml = xml;
        this.component = this.xml.getComponent(); 
        this.name = component.getAttribute("name");
        System.out.println(name);
        this.target = component.getAttribute("target");
        this.elements = this.xml.getElements();
        this.tabs = this.xml.getTabs();
    }
    
    /**
    * Captura los parámetros $_REQUEST si existen en el componente XML
    * @return string $_GET
    */
    public String getRequestedParams()
    { 
    	int count = this.elements.getLength();
    	String params = "";
    
        for (int i=1; i<count; i++) {
        	Element attributes = (Element) this.elements.item(i).getAttributes();   // @$this->layout[$i]["TARGET"];
            String field = attributes.getAttribute("target");
            if (this.form.get(field) != null) {
            	params += "&"+field+"=" + URLEncoder.encode(form.get(field));
            }
        }
        return params; 
    }
    
}