package zwei.admin.elements;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import models.AclModules;

import play.db.jpa.JPASupport;

import zwei.admin.ClassInvoker;
import zwei.utils.StringU;

public class DojoFilteringSelect extends Element
{
	public DojoFilteringSelect() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DojoFilteringSelect(String name,
			String target, String value, Map<String, String> params,
			Map<String, String> form) {
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



	public String edit(int i, int j)
	{
		String readonly = this.params.get("readonly") != null && this.params.get("readonly").equals("true") ?  "readonly" : "";
		String disabled = this.params.get("disabled") != null && this.params.get("disabled").equals("true") ? "disabled" : "";
		String required = this.params.get("required") != null && this.params.get("required").equals("true") ? "required=\"true\"" : "";
		String onchange = this.params.get("onchange") != null ? "onchange=\""+this.params.get("onchange")+"\"":"";	
		String invalid_message = this.params.get("invalid_message") != null ? "invalidMessage=\"" + this.params.get("invalid_message") + "\"" : "";
		String prompt_message = this.params.get("prompt_message") != null ? "promptMessage=\"" + this.params.get("prompt_message") + "\"" : "";		
		String value = this.params.get("default_value") != null && this.params.get("default_text") == null ? "value=\""+this.params.get("default_value")+"\"" : "";
		String options = this.options();
		return "<select dojoType=\"dijit.form.FilteringSelect\" id=\"edit"+i+"_"+j+"\" name=\""+this.target+"["+i+"]\" onload=\"dijit.byId('edit"+i+"_"+j+"').set('value', dijit.byId('edit"+i+"_"+j+"').get('value'))\" " 
				+ readonly + disabled + value + onchange + required + invalid_message + prompt_message + " style=\"display:block\" >\r\n"+options+"\r\n</select>\r\n";		
	}
	
	public String display(int i, int j)
	{
        if (this.value == null) {
        	this.value = form.get(this.target) != null ? form.get(this.target) : null;
        } /*else {
            value = this.value;
        }
		*/

        
        //$id = !empty($this->params['TABLE_PK']) ? $this->params['TABLE_PK'] : $model->getName().'.id';
        
        String field = this.params.get("table_field") != null ? this.params.get("table_field") : this.params.get("field");
		String method = "findAll"; 
        
        if (this.params.get("default_value") != null && this.params.get("default_value") == value) {
        	return this.params.get("default_text") != null ? this.params.get("default_text") : "Ninguno";
        } else if (this.params.get("table_method") != null) {
            method = StringU.toClassName(this.params.get("table_method"));
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
		
		return (String) (rows.get(0) != null  ? rows.get(0).findById(this.params.get("field")) : ""); 
	}
	
	public String options()
	{
		String options = "";
        //Map<String, String> selected;
        
        if (this.params.get("table") != null) {
            String id = this.params.get("table_pk") != null ? this.params.get("table_pk") : "id";
    		String modelName = "models." + StringU.toClassName(form.get("model"));
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
            
    		AclModules modules = new AclModules();
    		String field = null;
            
            if (this.params.get("table_method") != null) {
                String method = StringU.toFunctionName(this.params.get("table_method"));
               // $select = $model->$method();
            } else {
                if (this.params.get("table_field") != null){
                    //$select = $model->select(array($this->params['TABLE_FIELD'], $id));
                	field = this.params.get("table_field");
                } else {
                	field = this.params.get("field");
                    //$select = $model->select(array($this->params['FIELD'], $id));
                }    
            }
            List<JPASupport> rows = null;
            //Zwei_Utils_Debug::writeBySettings($select->__toString(), 'query_log');
            
            rows = (List<JPASupport>) modules.em().createQuery(field+","+id);

        
            if (this.value == null) {
                value = this.form.get(this.target) != null ? this.form.get(this.target) : null;
            } else {
                value = this.value;
            }

            if (this.params.get("default_value") != null && this.params.get("default_text") != null) {
        		String text = this.params.get("default_text");
            	options += "<option value=\""+this.params.get("default_value")+"\">"+this.params.get("default_text")+"</option>\r\n";
        	}

		    
			for (JPASupport row : rows) {
                String selected = row.findKey(id).toString() == this.value ? "selected" : "";
                if (this.params.get("table_field") != null) {
                    options += "<option value=\""+row.findKey(id).toString()+"\" "+selected+" >"+this.params.get("table_field")+"</option>\r\n";
                } else {
                    options += "<option value=\""+row.findKey(id).toString()+"\" "+selected+" >"+this.params.get("field")+"</option>\r\n";
                }    
            }
        } else {
        	
            if (this.value == null) {
                value = this.form.get(this.target) != null ? this.form.get(this.target) : null;
            } else {
                value = this.value;
            }
            
            options = "";
            String[] rows = this.params.get("list").split(",");
            for (String row : rows) {
                String selected = row == value ? "selected" : "";
                options += "<option value=\""+row+"\" "+selected+" >"+row+"</option>\r\n";
            }
        }
		return options;
	}
}
