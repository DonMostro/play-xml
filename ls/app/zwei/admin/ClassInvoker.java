package zwei.admin;
import java.lang.reflect.*;

/**
 * Invocador de clases a partir de Strings
 *
 */
public class ClassInvoker {
	/**
	 * Clase a ser invocada
	 */
	private Class className;
	/**
	 * Constructor
	 */
	private Constructor constructor;
	/**
	 * Parametros del constructor
	 */
	private Object[] params;
	/**
	 * Método a ser invocado 
	 */
	private Method method;
	/**
	 * Argumentos del metodo a ser invocado
	 */
	private Class[] arguments;

	
	public ClassInvoker(String className) throws ClassNotFoundException, SecurityException, NoSuchMethodException
	{
		this.className = Class.forName(className);
		this.constructor = this.className.getConstructor();
	}
	
	public ClassInvoker(String className, Class[] classParams) throws ClassNotFoundException, SecurityException, NoSuchMethodException
	{
		this.className = Class.forName(className);
		this.constructor = this.className.getConstructor(classParams);
	}
	
	public Object newInstance() throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException{
		return this.constructor.newInstance();
	}
	
	public Object newInstance(Object[] params) throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException{
		this.params = params;
		return this.constructor.newInstance(this.params);
	}
	
	public void initMethod(String methodName) throws SecurityException, NoSuchMethodException
	{
		this.method = this.className.getMethod(methodName);
	}
	
	public void initMethod(String methodName, Class[] arguments) throws SecurityException, NoSuchMethodException
	{
		this.arguments = arguments;
		this.method = this.className.getMethod(methodName, arguments);
	}
	
	public Object getResult() throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, InstantiationException
	{
		return this.method.invoke(newInstance(), (Object[]) this.arguments);
	}
	
	public Object getResult(Object[] params) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, InstantiationException
	{
		return this.method.invoke(newInstance(params), (Object[]) this.arguments);
	}

}
