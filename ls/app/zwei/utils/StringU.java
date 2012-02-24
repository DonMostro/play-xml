/**
 * 
 */
package zwei.utils;


public class StringU {

	/**
	 * Transforma una variable a un string que sigue las convenciones Java
	 * de nombres de clases
	 */
	public static String toClassName(java.lang.String string)
	{
		return toClassName(string, "_");
	}
	
	
	public static String toClassName(java.lang.String string, String limiter)
	{
		String returns = "";
		String[] aString = string.split(limiter);
		

		for (String s : aString ) {
			returns += toProperCase(s);
		}

		return returns;		
	}
	
	/**
	 * Transforma una variable a un string que sigue las convenciones Zend
	 * de nombres de métodos
	 * @param string
	 * @return string
	 */
	public static String toFunctionName(java.lang.String string)
	{
		return toFunctionName(string, "_");
	}
	
	/**
	 * Transforma una variable a un string que sigue las convenciones Zend
	 * de nombres de métodos
	 * @param string
	 * @param limiter
	 * @return
	 */
	public static String toFunctionName(java.lang.String string, java.lang.String limiter)
	{
		String returns = "";
		String[] aString = string.split(limiter);
		
		int i = 0;
		for(java.lang.String s: aString){
			returns += (i==0) ? s : toProperCase(s);
			i++; 
		}

		return returns;		
	}
	
	public static String toProperCase(String string) 
	{
	    return string.substring(0, 1).toUpperCase() + string.substring(1).toLowerCase();
	}
	
	/**
	 * Transforma una variable a un string que sigue las convenciones Zend
	 * de nombres de variables simples
	 * @param string
	 * @return
	 */
	
	public static String toVarName(String string)
	{
		return string.replaceAll("/(?<=[a-zA-Z])(?=[A-Z])/", "_");
	}
}
