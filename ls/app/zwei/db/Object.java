package zwei.db;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import play.db.jpa.JPASupport;
import play.db.jpa.Model;
import zwei.admin.ClassInvoker;

public class Object 
{
	private Map<String, String> httpParams;
	ClassInvoker modelClass = null;

	public Object(Map<String, String> httpParams) throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException, SecurityException, ClassNotFoundException, NoSuchMethodException 
	{
		this.httpParams = httpParams;
		String modelName = "models." + zwei.utils.StringU.toClassName(httpParams.get("model"));
		ClassInvoker modelClass = null;
		this.modelClass = new ClassInvoker(modelName);
	}
	
	public List<JPASupport> findAll() throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, InstantiationException
	{
		this.modelClass.initMethod("findAll");
		return (List<JPASupport>) this.modelClass.getResult();
		//return this.model.findAll();
	}
	
}
