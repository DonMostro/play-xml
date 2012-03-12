package zwei.admin.elements;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.Query;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import models.AclModules;

import play.db.jpa.JPA;
import play.db.jpa.JPASupport;

import zwei.admin.ClassInvoker;
import zwei.utils.StringU;

public class DojoFilteringSelect extends Element
{
	public DojoFilteringSelect() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DojoFilteringSelect(String name,	String target, String value, Node params, Map<String, String> form) {
		super(name, target, value, params, form);
		// TODO Auto-generated constructor stub
	}

	public DojoFilteringSelect(String name,
			String target, String value, Map<String, String> params) {
		super(name, target, value, params);
		// TODO Auto-generated constructor stub
	}

	public DojoFilteringSelect(String name,
			String target, String value) {
		super(name, target, value);
		// TODO Auto-generated constructor stub
	}

	public DojoFilteringSelect(String name,
			String target) {
		super(name, target);
		// TODO Auto-generated constructor stub
	}

	public DojoFilteringSelect(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}



	public String edit(String i, String j)
	{
		NamedNodeMap attributes = this.params.getAttributes();
		String readonly = attributes.getNamedItem("readonly") != null && attributes.getNamedItem("readonly").equals("true") ?  "readonly" : "";
		String disabled = attributes.getNamedItem("disabled") != null && attributes.getNamedItem("disabled").equals("true") ? "disabled" : "";
		String required = attributes.getNamedItem("required") != null && attributes.getNamedItem("required").equals("true") ? "required=\"true\"" : "";
		String onchange = attributes.getNamedItem("onchange") != null ? "onchange=\""+attributes.getNamedItem("onchange")+"\"":"";	
		String invalid_message = attributes.getNamedItem("invalid_message") != null ? "invalidMessage=\"" + attributes.getNamedItem("invalid_message") + "\"" : "";
		String prompt_message = attributes.getNamedItem("prompt_message") != null ? "promptMessage=\"" + attributes.getNamedItem("prompt_message") + "\"" : "";		
		String value = attributes.getNamedItem("default_value") != null && attributes.getNamedItem("default_text") == null ? "value=\""+attributes.getNamedItem("default_value")+"\"" : "";
		String options = this.options();

		return "<select dojoType=\"dijit.form.FilteringSelect\" id=\"edit"+i+"_"+j+"\" name=\""+this.target+"["+i+"]\" onload=\"dijit.byId('edit"+i+"_"+j+"').set('value', dijit.byId('edit"+i+"_"+j+"').get('value'))\" " 
				+ readonly + disabled + value + onchange + required + invalid_message + prompt_message + " style=\"display:block\" >\r\n"+options+"\r\n</select>\r\n";		
	}
	
	public String display(String i, String j)
	{
		NamedNodeMap attributes = this.params.getAttributes(); 
        if (this.value == null) {
        	this.value = form.get(this.target) != null ? form.get(this.target) : null;
        } /*else {
            value = this.value;
        }
		*/

        
        //$id = !empty($this->params['TABLE_PK']) ? $this->params['TABLE_PK'] : $model->getName().'.id';
        
        String field = attributes.getNamedItem("table_field") != null ? attributes.getNamedItem("table_field").getNodeValue() : attributes.getNamedItem("field").getNodeValue();
		String method = "findAll"; 
        
        if (attributes.getNamedItem("default_value") != null && attributes.getNamedItem("default_value").getNodeValue() == value) {
        	return attributes.getNamedItem("default_text") != null ? attributes.getNamedItem("default_text").getNodeValue() : "Ninguno";
        } else if (attributes.getNamedItem("table_method") != null) {
            method = StringU.toClassName(attributes.getNamedItem("table_method").getNodeValue());
            //$select = $model->$method();
        } else { 
            //$select = $model->select(array($field, $id));
        }
        
        
		String modelName = "models." + zwei.utils.StringU.toClassName(form.get("model"));
		ClassInvoker modelClass = null;
		
		try {
			modelClass = new ClassInvoker(modelName);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		try {
			modelClass.initMethod(method);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<JPASupport> rows = null;
		
		try {
			rows = (List) modelClass.getResult();
		} catch (IllegalArgumentException e) {
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
		
		return (String) (rows.get(0) != null  ? rows.get(0).findById(attributes.getNamedItem("field")) : ""); 
	}
	
	public String options()
	{
		String options = "";
        //Map<String, String> selected;
		NamedNodeMap attributes = this.params.getAttributes();
		
        try {
	        if (attributes.getNamedItem("table") != null) {
	            String id = attributes.getNamedItem("table_pk") != null ? attributes.getNamedItem("table_pk").getNodeValue() : "id";
	    		String className = "models." + StringU.toClassName(attributes.getNamedItem("table").getNodeValue());
	    		//System.out.println(modelName);
	    		Class<?> classBase = Class.forName(className);
	    		
	    		ClassInvoker modelClass = null;
	    		
	    		try {
	    			modelClass = new ClassInvoker(className);
	    		} catch (SecurityException e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	    		} catch (ClassNotFoundException e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	    		} catch (NoSuchMethodException e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	    		}
	            
	    		String field = null;
	    		ArrayList<HashMap<String, String>> rows = null;
	            
	            if (attributes.getNamedItem("table_method") != null) {
	                String methodName = StringU.toFunctionName(attributes.getNamedItem("table_method").getNodeValue());
	                Class[] methodArgTypes = {boolean.class};
	                Object[] arguments = {true}; 
	                modelClass = new ClassInvoker(className);
	                
	                modelClass.initMethod(methodName, methodArgTypes);
	                rows = (ArrayList<HashMap<String, String>>) modelClass.getResult(null, arguments);
	                
	                
	               // $select = $model->$method();
	            } else {
	                if (attributes.getNamedItem("table_field") != null){
	                    //$select = $model->select(array($this->params['TABLE_FIELD'], $id));
	                	field = attributes.getNamedItem("table_field").getNodeValue();
	                } else {
	                	field = attributes.getNamedItem("field").getNodeValue();
	                    //$select = $model->select(array($this->params['FIELD'], $id));
	                }    
	            }
	            
	            //Zwei_Utils_Debug::writeBySettings($select->__toString(), 'query_log');
	            
	            Query query = JPA.em().createQuery("SELECT new " + className + "(mod." + id +", mod."+ "title" + ") FROM " + className + " mod");
	
	            if (query.getResultList().size() > 0) {
	            	
	            	List rowsx = query.getResultList();

	            }	
	            
	            
	            
	            if (this.value == null) {
	                value = this.form.get(this.target) != null ? this.form.get(this.target) : null;
	            } /* else {
	                value = this.value;
	            }
				*/
	            if (attributes.getNamedItem("default_value") != null && attributes.getNamedItem("default_text") != null) {
	        		String text = attributes.getNamedItem("default_text").getNodeValue();
	            	options += "<option value=\""+attributes.getNamedItem("default_value")+"\">"+attributes.getNamedItem("default_text")+"</option>\r\n";
	        	}


	            for (int i=0; i<rows.size(); i++) {
	            	String selected = rows.get(i).get(id).equals(this.value) ? "selected" : "";
	            	String optionText = "";
					if (attributes.getNamedItem("table_field") != null) {
						optionText = rows.get(i).get(attributes.getNamedItem("table_field").getNodeValue());
						System.out.println("table_field:"+attributes.getNamedItem("table_field").getNodeValue());
					} else {
						optionText = rows.get(i).get(attributes.getNamedItem("field").getNodeValue());
						System.out.println("field:"+attributes.getNamedItem("field").getNodeValue());
					}
					
					
					options += "<option value=\""+rows.get(i).get(id)+"\" "+ "selected" +" >"+optionText+"</option>\r\n";
	            	
	            }
	            
	            //Iterator it = rows.iterator();
	            /*
	            while (it.hasNext()) {
	            	Object element = it.next(); 
		             
		            	
	            	//String selected = it. .findKey(id).toString() == this.value ? "selected" : "";
					if (attributes.getNamedItem("table_field") != null) {
					    options += "<option value=\""+element +"\" "+ "selected" +" >"+attributes.getNamedItem("table_field").getNodeValue()+"</option>\r\n";
					} else {
					    options += "<option value=\""+element+"\" "+ "selected" +" >"+attributes.getNamedItem("field").getNodeValue()+"</option>\r\n";
					} 
	            }
				*/
	            
			    
			    
	
				
	        } else {

	        	
	            if (this.value == null) {
	                value = this.form.get(this.target) != null ? this.form.get(this.target) : null;
	            }/* else {
	                value = this.value;
	            }*/
	        	//System.out.println("value="+this.value);
	            
	            
	            options = "";
	            String[] rows = attributes.getNamedItem("list") !=null ? attributes.getNamedItem("list").getNodeValue().split(",") : null;
	            if (rows != null) {
		            for (String row : rows) {
		                String selected = row == value ? "selected" : "";
		                options += "<option value=\""+row+"\" "+selected+" >"+row+"</option>\r\n";
		            }
	            }
	        }
        } catch (Exception e) {
        	e.printStackTrace();
        }
		return options;
	}
}
