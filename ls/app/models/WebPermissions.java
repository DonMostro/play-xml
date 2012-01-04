package models;

import java.util.*;
import java.io.*;
import javax.persistence.*;

import play.db.jpa.Model;

@Entity
@Table(name="web_permissions")
public class WebPermissions 
{
	@Id 
    public String id; 
	
	public String title;

	public void setId(String id)
	{
		this.id = id;
	}
	
	public String getId()
	{
		return this.id;
	}
	
	
	
	/**
	 * Method 'WebPermissions'
	 * 
	 */
	public WebPermissions()
	{
	}

	/**
	 * Method 'getId'
	 * 
	 * @return String
	 */
	
	/**
	 * Method 'getTitle'
	 * 
	 * @return String
	 */
	@Column(name="title")
	public String getTitle()
	{
		return title;
	}

	/**
	 * Method 'setTitle'
	 * 
	 * @param title
	 */
	public void setTitle(String title)
	{
		this.title = title;
	}

}
