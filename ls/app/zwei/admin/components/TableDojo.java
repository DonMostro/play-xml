package zwei.admin.components;

import zwei.admin.Controller;

public class TableDojo extends Controller
{
	public TableDojo(String page, String[] id) {
		super(page, id);
	}

	public String display()
	{
		return "Hello World";
	}
}
