/**
 * 
 */
package zwei.admin.components.helpers;

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
			"\t\t\t} else if(opc == 'edit') {\n" +
			"\t\t\t\tvar items = main_grid.selection.getSelected();\n" +
			"\t\t\t\tif (items[0]==undefined) {\n" +
			"\t\t\t\t\talert('Debes seleccionar una fila');\n" +
			"\t\t\t\treturn;\n" +
			"\t\t\t}" +
			"\t\t\tconsole.debug(items[0]);\r\n"; 
		i = 0;
		for (int j = 1;  j < count-1; j++) {
			attributes = this.elements.item(i).getAttributes(); 
			params = null;
			if (attributes.getNamedItem("edit").getNodeValue().equals("true")) {
				pfx = "";
				this.out += "\t\t\ttry{\n";
				if (attributes.getNamedItem("type").getNodeValue().equals("dojo_filtering_select")) {
					this.out += "\t\t\t\tdijit.byId('edit"+Integer.toString(i)+"_"+pfx+Integer.toString(j)+"').set('value',items[0]."+StringU.toFunctionName(attributes.getNamedItem("target").getNodeValue())+");\n";
				} else if(attributes.getNamedItem("type").getNodeValue().equals("select")) {
					this.out += "\t\t\t\tselectValueSet('edit"+Integer.toString(i)+"_"+pfx+Integer.toString(j)+"', items[0].{$node['TARGET']});\n";
                } else if(attributes.getNamedItem("edit_custom_display").getNodeValue().equals("true")) {
                	/**
                	 * Si es un input que require lógica especial para el despliegue 
                	 * agregar atributo <code>edit_custom_display="true"</code> en elemento XML
                	 * y sobrescribir método editCustomDisplay($i, $pfx.$j) en Element personalizado 
                	 */
					//$sElement = "Zwei_Admin_Elements_".Zwei_Utils_String::toClassWord($node['TYPE']);
					//$oElement = new $sElement($node['VISIBLE'], '', '', $node['TARGET'], '', $params);
					//this.out += $oElement->editCustomDisplay($i, $pfx.$j);    
				} else {
					this.out += "\t\t\t\tdocument.getElementById('edit{$i}_{$pfx}{$j}').value = items[0].{$node['TARGET']};\n";
				}
				this.out += "\t\t\t}catch(e){console.log(e)}\n";
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
			    "\t\t\twindow.location.href = base_url+'index/login';" +
			    "\t\t\treturn err;" +
			    "\t\t\t}" +
			    "\t\t\t});return res;" +
			    "\t\t\t}" +
			    "\t\t\tfunction actualizar(model, items, id) {" +
			    "\t\t\tvar res = '';";
		
	}

	public String display() {
		return this.display("edit");
	}
	
	public String display(String mode) {
		String out = this.out;
		String pfx;
		String elementEdit;
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
		Object[] editArguments;
		ClassInvoker elementClass = null;
		Class[] classParamsTypes = new Class[] { String.class, String[].class, Map.class };//Clases de parámetros del constructor element
		Class[] methodArgsTypes = new Class[] { Map.class };//Clases de argumentos de método element.edit

		
		
		
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
				className = "zwei.admin.elements_"+StringU.toClassName(attributes.getNamedItem("type").getNodeValue());
				
				elementClass = new ClassInvoker(className, classParamsTypes);
				elementClass.initMethod(methodName, methodArgsTypes);
				
				elementParams = new Object [] { 
						attributes.getNamedItem("visible").getNodeValue(), 
						attributes.getNamedItem("edit").getNodeValue(), 
						attributes.getNamedItem("name").getNodeValue(), 
						attributes.getNamedItem("target").getNodeValue(),
						value,
						null
				};

				
				elementEdit = (String) elementClass.getResult(elementParams, editArguments);
				
				if (mode.equals("add")) pfx="_add";
				else pfx="";

				if($node['TYPE'] != 'hidden'){
					out +="<tr><td><label for=\"{$node['TARGET']}\">{$node['NAME']}</label></td><td>"+$element->edit($i, $pfx.$j)+"</td></tr>";
				}else{
					//out +=$element->edit($i,$pfx.$j);
				}
			}
		}		
		
		return out;
	}
}
