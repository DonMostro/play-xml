package zwei.db;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import play.db.jpa.JPASupport;
import play.db.jpa.Model;
import zwei.admin.ClassInvoker;
import zwei.utils.StringU;

public class Object 
{
	private Map<String, String> form;
	ClassInvoker modelClass = null;

	public Object(Map<String, String> form) throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException, SecurityException, ClassNotFoundException, NoSuchMethodException 
	{
		this.form = form;
		String modelName = "models." + StringU.toClassName(form.get("model"));
		//ClassInvoker modelClass = null;
		this.modelClass = new ClassInvoker(modelName);
	}
	
	public List<JPASupport> findAll() throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, InstantiationException
	{
		this.modelClass.initMethod("findAll");
		return (List<JPASupport>) this.modelClass.getResult();
		//return this.model.findAll();
	}
	
}
