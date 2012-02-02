package zwei.admin;
import java.lang.reflect.*;

/**
 * Invocador de clases y métodos a partir de Strings
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

	/**
	 * Inicializa clase por su nombre, sin parámetros
	 * @param className
	 * @throws ClassNotFoundException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 */
	public ClassInvoker(String className) throws ClassNotFoundException, SecurityException, NoSuchMethodException
	{
		this.className = Class.forName(className);
		this.constructor = this.className.getConstructor();
	}
	
	/**
	 * Inicia clase por su nombre, indicando clase(s) de parametros(s) 
	 * @param className
	 * @param classParams
	 * @throws ClassNotFoundException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 */
	public ClassInvoker(String className, Class[] classParams) throws ClassNotFoundException, SecurityException, NoSuchMethodException
	{
		this.className = Class.forName(className);
		this.constructor = this.className.getConstructor(classParams);
	}
	
	/**
	 * Instancia constructor de clase sin parámetros
	 * @return
	 * @throws IllegalArgumentException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public Object newInstance() throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException{
		return this.constructor.newInstance();
	}
	
	/**
	 * Instancia constructor de clase con parámetros
	 * @param params
	 * @return
	 * @throws IllegalArgumentException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public Object newInstance(Object[] params) throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException{
		this.params = params;
		return this.constructor.newInstance(this.params);
	}
	/**
	 * Inicializa el método sin argumentos
	 * @param Nombre del método
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 */
	public void initMethod(String methodName) throws SecurityException, NoSuchMethodException
	{
		this.method = this.className.getMethod(methodName);
	}
	
	/**
	 * Inicializa el método con argumentos, se debe indicar tipo de datos de los argumentos
	 * @param methodName - Nombre del método
	 * @param arguments - Tipo de datos los argumentos 
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 */
	public void initMethod(String methodName, Class[] arguments) throws SecurityException, NoSuchMethodException
	{
		this.arguments = arguments;
		this.method = this.className.getMethod(methodName, arguments);
	}
	/**
	 * Devuelve el resultado del método despues de inicializar con initMethod()
	 * @return Method
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws InstantiationException
	 */
	public Object getResult() throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, InstantiationException
	{
		return this.method.invoke(newInstance(), (Object[]) this.arguments);
	}
	/**
	 * Devuelve el resultado del método despues de inicializar con initMethod(),
	 * indicar parámetros del objeto
	 * @param params Parámetros del objeto
	 * @return Method
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws InstantiationException
	 */
	public Object getResult(Object[] params) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, InstantiationException
	{
		return this.method.invoke(newInstance(params), (Object[]) this.arguments);
	}
	
	/**
	 * Devuelve el resultado del método despues de inicializar con initMethod(),
	 * indicar parámetros del objeto y parámetros del método
	 * @param params    -  Parámetros del objeto
	 * @param arguments -  Argumentos del método
	 * @return Method
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws InstantiationException
	 */
	public Object getResult(Object[] params, Object[] arguments) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, InstantiationException
	{
		return this.method.invoke(newInstance(params), arguments);
	}
	

}
