package zwei.admin.elements;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
		System.out.println("llamado DojoFilteringSelect.options()");
		String options = "";
        //Map<String, String> selected;
		NamedNodeMap attributes = this.params.getAttributes();
		
        try {
	        if (attributes.getNamedItem("table") != null) {
	            String id = attributes.getNamedItem("table_pk") != null ? attributes.getNamedItem("table_pk").getNodeValue() : "id";
	    		String modelName = "models." + StringU.toClassName(attributes.getNamedItem("table").getNodeValue());
	    		System.out.println(modelName);
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
	            
	    		String field = null;
	            
	            if (attributes.getNamedItem("table_method") != null) {
	                String method = StringU.toFunctionName(attributes.getNamedItem("table_method").getNodeValue());
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
	            List<JPASupport> rows = null;
	            //Zwei_Utils_Debug::writeBySettings($select->__toString(), 'query_log');
	            
	            rows = (List<JPASupport>) JPA.em().createQuery(field+","+id);
	
	        
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
	
			    
				for (JPASupport row : rows) {
	                String selected = row.findKey(id).toString() == this.value ? "selected" : "";
	                if (attributes.getNamedItem("table_field") != null) {
	                    options += "<option value=\""+row.findKey(id).toString()+"\" "+selected+" >"+attributes.getNamedItem("table_field")+"</option>\r\n";
	                } else {
	                    options += "<option value=\""+row.findKey(id).toString()+"\" "+selected+" >"+attributes.getNamedItem("field")+"</option>\r\n";
	                }    
	            }
	        } else {
	        	
	        	System.out.println("attributes.getNamedItem(\"table\") == null");
	
	        	
	            if (this.value == null) {
	                value = this.form.get(this.target) != null ? this.form.get(this.target) : null;
	            }/* else {
	                value = this.value;
	            }*/
	        	System.out.println("value="+this.value);
	            
	            
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
