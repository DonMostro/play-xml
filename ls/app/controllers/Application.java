package controllers;

import play.*;
import play.mvc.*;
import zwei.admin.ClassInvoker;
import zwei.admin.Xml;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import models.*;



public class Application extends Controller 
{
    public static void index() 
    {
    	new AclModules();
    	List tasks = Task.findAll();
        render(tasks);
    }
    
    public static void changeStatus(Long id, Boolean done) 
    {
    	Task task = Task.findById(id);
    	task.done = done;
    	task.save();
    	renderJSON(task);
    }
    
	public static void createTask(String title) 
	{
		Task task = new Task(title).save();
		renderJSON(task);
	}
	
	public static void components(String p) throws ParserConfigurationException, SAXException, IOException, SecurityException, ClassNotFoundException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, InstantiationException
	{
		String pathComponents = (String) Play.configuration.get("path.components");
		
		File file = new File(pathComponents + "/" + p + ".xml");
		Xml xml = new Xml(file);
		xml.parse();
		Element component = xml.getComponent();
		
		String componentType = "zwei.admin.components." + zwei.utils.String.toClassName(component.getAttribute("type"));
		Class[] componentParams = new Class[] { String.class, String[].class };
		ClassInvoker componentClass = new ClassInvoker(componentType, componentParams);
		
		Object[] arguments = new Object[] { p, null };
		componentClass.initMethod("display", (Class[]) arguments);
		
		String content = (String) componentClass.getResult();
		render(content);
	}
	
	public static void modules()
	{
		
	}
}