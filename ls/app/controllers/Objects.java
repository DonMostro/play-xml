package controllers;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




//import flexjson.*;

import play.Play;
import play.db.jpa.JPASupport;
import play.db.jpa.Model;
import play.mvc.Controller;
import play.mvc.results.RenderTemplate;
import play.templates.Template;
import zwei.admin.ClassInvoker;
import zwei.dojo.Json;

public class Objects extends Controller 
{
	private static zwei.db.Object dbObject;
	private static Model model;
	
	public static void index(String model, String action)
	{
		Map<String, String> httpParams = params.allSimple();//Request params
		List<JPASupport> data = null;
		String pathComponents = (String) Play.configuration.get("application.pathComponents");
		String content = "";
		String className = "";
		String methodName = "";
		
	
		if (action != null) {
			if (action.equals("add")) {
				
			} else if (action.equals("edit")) {
				
			} else if (action.equals("delete")) {
				
			}
		}
		
		try {
			dbObject = new zwei.db.Object(httpParams);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			content = "<p>No se encuentra modelo <i>models." + zwei.utils.StringU.toClassName(model)  +"</i></p>";
			render(content);
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}

	
		try {
			data = dbObject.findAll();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//JSONSerializer modelSerializer = new JSONSerializer().include("company","secondmodel.id","secondmodel.test").exclude("*"); 
		HashMap hash = Json.toDataStore(data);
		renderJSON(hash);

		
		//Class[] componentParamsTypes = new Class[] { String.class, String[].class };//Clases de parámetros del constructor
		//Object[] componentParams = new Object [] { p, null };//Valores parámetros del constructor
		//Class[] methodArgsTypes = new Class[] { Map.class };//Clases de argumentos de método 
		//Object[] methodArgs = new Object [] { httpParams };//Valores argumentos de método
	}
}
