/**
 * 
 */
package zwei.utils;


public class String {

	/**
	 * Transforma una variable a un string que sigue las convenciones Java
	 * de nombres de clases
	 */
	public static java.lang.String toClassName(java.lang.String string)
	{
		return toClassName(string, "_");
	}
	
	
	public static java.lang.String toClassName(java.lang.String string, java.lang.String limiter)
	{
		java.lang.String returns = "";
		java.lang.String[] aString = string.split(limiter);
		

		for (java.lang.String s : aString ) {
			returns += toProperCase(s);
			System.out.println(returns);
		}

		return returns;		
	}
	
	/**
	 * Transforma una variable a un string que sigue las convenciones Zend
	 * de nombres de métodos
	 * @param $string
	 * @param $limiter
	 * @return string
	 */
	public static java.lang.String toFunctionName(java.lang.String string)
	{
		return toFunctionName(string, "_");
	}
	
	
	public static java.lang.String toFunctionName(java.lang.String string, java.lang.String limiter)
	{
		java.lang.String returns = "";
		java.lang.String[] aString = string.split(limiter);
		
		int i = 0;
		for(java.lang.String s: aString){
			returns += (i==0) ? s : toProperCase(s);
			i++; 
		}

		return returns;		
	}
	
	public static java.lang.String toProperCase(java.lang.String string) 
	{
	    return string.substring(0, 1).toUpperCase() + string.substring(1).toLowerCase();
	}
}
