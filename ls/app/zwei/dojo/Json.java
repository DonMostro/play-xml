package zwei.dojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Json 
{
	public static HashMap toDataStore(HashMap hash) 
	{
		HashMap wrapper = toDataStore(hash, "id", "label");
		return wrapper;
	}
	
	public static HashMap toDataStore(HashMap hash, String identifier, String label) 
	{
		HashMap wrapper = new HashMap();
		wrapper.put("items", hash.values());
		wrapper.put("label", label);		
		wrapper.put("identifier", identifier);	
		return wrapper;
	}
	
	public static HashMap toDataStore(List list) 
	{
		HashMap wrapper = toDataStore(list, "id", "label");
		return wrapper;
	}
	
	
	public static HashMap toDataStore(List list, String identifier, String label) 
	{
		HashMap wrapper = new HashMap();
		wrapper.put("items", list);
		wrapper.put("label", label);		
		wrapper.put("identifier", identifier);	
		return wrapper;
	}
}
