package controllers;

import play.*;
import play.mvc.*;
import zwei.admin.ClassInvoker;
import zwei.admin.Xml;

import java.io.File;
import java.io.FileNotFoundException;
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
		String pathComponents = (String) Play.configuration.get("application.pathComponents");
		System.out.println(pathComponents);
		String content;
		
		File file = new File(pathComponents + "/" + p + ".xml");

		Xml xml = new Xml(file);
		xml.parse();
		Element component = xml.getComponent();
		
		String componentClassName = "zwei.admin.components." + zwei.utils.String.toClassName(component.getAttribute("type"));
		Class[] componentParamsTypes = new Class[] { String.class, String[].class };
		Object[] componentParams = new Object [] {p, null };
		ClassInvoker componentClass = new ClassInvoker(componentClassName, componentParamsTypes);
		
		componentClass.initMethod("display");
		
		Map<String, String[]> pars = params.all();
		System.out.println(pars.get("p").toString());
		
		content = (String) componentClass.getResult(componentParams);

		render(content);
	}
	
	public static void modules()
	{
		
	}
}