/**
 * 
 */
package zwei.admin.components.helpers;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.w3c.dom.NamedNodeMap;

import zwei.admin.ClassInvoker;
import zwei.admin.Controller;
import zwei.utils.StringU;

/**
 *
 */
public class EditTableDojo extends Controller{

	private String out = "";
	
	public EditTableDojo(String page, String[] id, Map<String, String> form) {
		super(page, id, form);
		int count = this.elements.getLength();

		this.out += "<script type=\"text/javascript\">\n" +
				"\tfunction showDialog(opc) {\n" +
				"\t\tconsole.debug(opc);\n" +
				"\t\tvar formDlg = (opc=='edit') ? dijit.byId('formDialogoEditar') : dijit.byId('formDialogo');\n" +
				"\t\tconsole.log('opcion usr:'+ opc);\n" +
				"\t\tglobal_opc=opc;\n" +
				"\t\tif (opc == 'add') {\n";
	    int i = 0;
	    String cleanFilteringSelects = "";
	    String pfx;
	    NamedNodeMap attributes; 
	    
	    Map<String, String> params;
		for (int j = 0; j<count-1; j++) {
			attributes = this.elements.item(i).getAttributes(); 
			params = null;
			if (attributes.getNamedItem("add").getNodeValue().equals("true")) {
				pfx = "_add";
				this.out += "\t\t\t try{";
				if (attributes.getNamedItem("type").getNodeValue().equals("dojo_filtering_select") || attributes.getNamedItem("type").getNodeValue().equals("dojo_yes_no")) {
					this.out += "\t\t\t\t dijit.byId('edit"+Integer.toString(i)+"_"+pfx+Integer.toString(j)+"').set('value',0);\n";
					// workaround bug dojo para limpiar selects despues de insertar
					// de otra forma notifica como opción NO valida una opción SI válida y confunde a usuario
					cleanFilteringSelects += "\t\t\t\t dijit.byId('edit"+Integer.toString(i)+"_"+pfx+Integer.toString(j)+"').set('value', '');\n";
				} else if (attributes.getNamedItem("type").getNodeValue().equals("select")) {
					this.out += "\t\t\t\t selectValueSet('edit"+Integer.toString(i)+"_"+pfx+Integer.toString(j)+"', '0');\n";
				} else if (attributes.getNamedItem("search_table_target") != null && (attributes.getNamedItem("search_table_target").getNodeValue().equals(attributes.getNamedItem("target").getNodeValue()))){
					//esto debiera tener un type="hidden"
					this.out += "\t\t\tdocument.getElementById('edit"+Integer.toString(i)+"_"+pfx+Integer.toString(j)+"').value = dijit.byId('search').get('value');\n";
				} else {
					this.out += "\t\t\t\tdocument.getElementById('edit"+Integer.toString(i)+"_"+pfx+Integer.toString(j)+"').value = '';\n";
				}
				this.out += "\t\t\t}catch(e){console.log(e)}\n";
			}
		}
		
		this.out += "\t\t\t\tformDlg.set('title','Agregar "+this.component.getAttribute("name")+"');\n" +
			"\t\t} else if(opc == 'edit') {\n" +
			"\t\t\tvar items = main_grid.selection.getSelected();\n" +
			"\t\t\tif (items[0]==undefined) {\n" +
			"\t\t\t\talert('Debes seleccionar una fila');\n" +
			"\t\t\t\treturn;\n" +
			"\t\t\t}" +
			"\t\t\tconsole.debug(items[0]);\r\n"; 
		i = 0;
		for (int j = 1;  j < count-1; j++) {
			attributes = this.elements.item(j).getAttributes(); 
			params = null;
			if (attributes.getNamedItem("edit").getNodeValue().equals("true")) {
				pfx = "";
				this.out += "\t\t\ttry{\n";
				if (attributes.getNamedItem("type").getNodeValue().equals("dojo_filtering_select")) {
					this.out += "\t\t\t\tdijit.byId('edit"+Integer.toString(i)+"_"+pfx+Integer.toString(j)+"').set('value',items[0]."+StringU.toFunctionName(attributes.getNamedItem("target").getNodeValue())+");\n";
				} else if(attributes.getNamedItem("type").getNodeValue().equals("select")) {
					this.out += "\t\t\t\tselectValueSet('edit"+Integer.toString(i)+"_"+pfx+Integer.toString(j)+"', items[0]."+StringU.toFunctionName(attributes.getNamedItem("target").getNodeValue())+");\n";
                } else if(attributes.getNamedItem("edit_custom_display") != null && attributes.getNamedItem("edit_custom_display").getNodeValue().equals("true")) {
                	/**
                	 * Si es un input que require lógica especial para el despliegue 
                	 * agregar atributo <code>edit_custom_display="true"</code> en elemento XML
                	 * y sobrescribir método editCustomDisplay($i, $pfx.$j) en Element personalizado 
                	 */
					//$sElement = "Zwei_Admin_Elements_".Zwei_Utils_String::toClassWord($node['TYPE']);
					//$oElement = new $sElement($node['VISIBLE'], '', '', $node['TARGET'], '', $params);
					//this.out += $oElement->editCustomDisplay($i, $pfx.$j);    
				} else {
					this.out += "\t\t\t\tdocument.getElementById('edit"+i+"_"+pfx+j+"').value = items[0].{"+StringU.toFunctionName(attributes.getNamedItem("target").getNodeValue())+"};\n";
				}
				this.out += "\t\t\t}catch(e){console.log(e)}\n";
			}
		}
		
		this.out +="\t\t\ttry{formDlg.set('title','Editar "+this.component.getAttribute("name")+"');}catch(e){console.debug(e);}\n" +
				"\t\t}\n"+
				"\t\tformDlg.show();\n"+
				"\t}\n";
		
		String additionalValidation = "";

		if (component.getAttribute("edit_additional_validation") != null && component.getAttribute("edit_additional_validation").equals("true")) {
			/*
			$modelclass=Zwei_Utils_String::toClassWord($this->layout[0]['TARGET'])."Model";
			$Model=new $modelclass();
			$additional_validation=$Model->getEditValidation();//usar en js var global_opc para discriminar entre 'edit' y add'
			*/
		} else {
			additionalValidation = "";
		}
		
		

		this.out += "" +
				"function modify(model, items) {" +
				"var resp = '';" +
	            additionalValidation +
	            "if(global_opc == 'add') {" +
	            "	resp = insertar(model,items);" +
	            "} else if(global_opc == 'edit') {" +
	            "var items = main_grid.selection.getSelected();" +
	            "var id = items[0].id;" +
	            "resp = actualizar(model, items, id);" +
	            "}" +
	            "if(resp.message != '' && resp.message != null){" +
	            "alert(resp.message);" +
	            "}else if(resp.state == 'UPDATE_OK'){" +
	            "	alert('Datos Actualizados');" +
	            "}else if(resp.state == 'ADD_OK'){" +
	            "	alert('Datos Ingresados');" +
	            "}else if(resp.state == 'UPDATE_FAIL'){" +
	            "	alert('Ha ocurrido un error, o no ha modificado datos');" +
	            "}else if(resp.state == 'ADD_FAIL'){" +
	            "alert('Ha ocurrido un error, verifique datos o intente más tarde');" +
	            "}" +
	            "cargarDatos(model);" +
	            "}";
	    
		this.out += "" +
	        "function insertar(model, items) {" +
	        "var res = '';" +
	        "dojo.xhrPost( {" +
	        "url: base_url+'objects'," +
	        "content: {";
            i=0;

    		for (int j=1; j<count-1; j++) {
    			attributes = this.elements.item(j).getAttributes(); 
    			params = null;
    			if(attributes.getNamedItem("add").getNodeValue().equals("true")){
    				pfx = "_add";
    				if (attributes.getNamedItem("type").getNodeValue().equals("dojo_filtering_select")) {
    					//$this->_out.="\t\t\t\t'data[{$node['TARGET']}]' : dijit.byId('{$node['TARGET']}[0]').get('value'), \r\n";
    					this.out +="\t\t\t\t'data["+StringU.toFunctionName(attributes.getNamedItem("target").getNodeValue())+"]' : dijit.byId('edit"+i+"_"+pfx+j+"').get('value'), \r\n";
    				}else{
    					//$this->_out.="\t\t\t\t'data[{$node['TARGET']}]' : document.getElementById('{$node['TARGET']}[0]').value, \r\n";
    					this.out +="\t\t\t\t'data["+StringU.toFunctionName(attributes.getNamedItem("target").getNodeValue())+"]' : document.getElementById('edit"+i+"_"+pfx+j+"').value, \r\n";
    				}
    			}
    		}

		
		
		
		this.out += "'\t\t\taction'      :'add',\n" +
				"\t\t\t'model'     : model,\n" +
				"\t\t\t'format'    : 'json'\n" +
				"\t\t\t},handleAs: 'json',\n" +
				"\t\t\tsync: true,\n" +
				"\t\t\tpreventCache: true,\n" +
				"\t\t\ttimeout: 5000," +
				"\t\t\tload: function(respuesta){" +
				"\t\t\tconsole.debug(dojo.toJson(respuesta));" +
				"\t\t\t"+cleanFilteringSelects +
			    "\t\t\tres = respuesta;" +
			    "\t\t\treturn respuesta;" +
			    "\t\t\t}," +
			    "\t\t\terror:function(err){" +
			    "\t\t\t//alert('Error en comunicacion de datos. error: '+err); alert('Ha ocurrido un error, por favor reinicie sesión');" +
			    "\t\t\twindow.location.href = base_url + 'index/login';" +
			    "\t\t\treturn err;" +
			    "\t\t\t}" +
			    "\t\t\t});return res;" +
			    "\t\t\t}" +
			    "\t\t\tfunction actualizar(model, items, id) {" +
			    "\t\t\tvar res = '';\n";
		
        this.out +="</script>\n";
		
	}

	public String display() {
		return this.display("edit");
	}
	
	public String display(String mode) {
		String out = this.out;
		String pfx;
		String elementEdit = null;
		this.out = "";
		int count = this.elements.getLength();
		int i = 0;
		NamedNodeMap attributes; 
		
		/**
		 * Parámetros clase element
		 */
		String className;
		String methodName = "edit";
		Object[] elementParams;
		Object[] editArguments = null;
		ClassInvoker elementClass = null;
		Class[] classParamsTypes = new Class[] { String.class, String.class, String.class, Map.class };//Clases de parámetros del constructor element
		Class[] methodArgsTypes = new Class[] { int.class, int.class };//Clases de argumentos de método element.edit


		for (int j=1; j<count-1; j++) {
			attributes = this.elements.item(j).getAttributes();
			//foreach ($node as $k=>$v) if($k!='VALUE') $params[$k] = $v;
			/*
			if (attributes.getNamedItem("value") == null) {
				attributes.setNamedItem("value");
				attributes.getNamedItem("value").setNodeValue("");
			}
			*/
    
			/*
			if (attributes.getNamedItem("value") != null || isset($form->{$node['TARGET']}) && is_array($form->{$node['TARGET']})){
				$value = $node['VALUE'][$i];
			} else {*/
				String value = form.get(attributes.getNamedItem("target").getNodeValue()) != null ? form.get(attributes.getNamedItem("target").getNodeValue()) : "";
			//}
    
			if (attributes.getNamedItem(mode).getNodeValue().equals("true")) {
				className = "zwei.admin.elements."+StringU.toClassName(attributes.getNamedItem("type").getNodeValue());
				
				try {
					elementClass = new ClassInvoker(className, classParamsTypes);
				} catch (SecurityException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NoSuchMethodException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					elementClass.initMethod(methodName, methodArgsTypes);
				} catch (SecurityException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NoSuchMethodException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				elementParams = new Object [] { 
					attributes.getNamedItem("name").getNodeValue(), 
					attributes.getNamedItem("target").getNodeValue(),
					value,
					this.form
				};

				editArguments = new Object [] { 
					0,
					j
				};
				
				
				
				try {
					elementEdit = (String) elementClass.getResult(elementParams, editArguments);
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
				
				if (mode.equals("add")) pfx = "_add";
				else pfx = "";

				if (attributes.getNamedItem("type").getNodeValue().equals("hidden")) {
					out += "\t"+elementEdit;
				} else {
					out +="\t<tr><td><label for=\""+StringU.toFunctionName(attributes.getNamedItem("target").getNodeValue())+"\">"+attributes.getNamedItem("name").getNodeValue()+"</label></td><td>"+elementEdit+"</td></tr>";
				}
			}
		}		
		
		return out;
	}
}
