package zwei.admin;
import java.lang.reflect.*;


public class ClassInvoker {
	private Class className;
	private Constructor constructor;
	private Method method;
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

}
