package models;

import java.util.*;
import java.io.*;
import javax.persistence.*;

import play.db.jpa.JPA;
import play.db.jpa.Model;

@Entity
@Table(name="web_settings")
public class WebSettings extends JPA
{
	@Id
	protected String id;
	
	@Column(name="enum")
	protected String enm;

	protected String value;

	protected String type;

	protected String description;

	protected int ord;

	protected String group;

	protected String function;

	protected String approved;

	/**
	 * Method 'AppSettings'
	 * 
	 */
	public WebSettings()
	{
	}

	/**
	 * Method 'getId'
	 * 
	 * @return String
	 */
	@Id
	@Column(name="id")
	public String getId()
	{

		return id;
	}

	/**
	 * Method 'setId'
	 * 
	 * @param id
	 */
	public void setId(String id)
	{
		this.id = id;
	}

	/**
	 * Method 'getEnum'
	 * 
	 * @return String
	 */
	@Column(name="enm")
	public String getEnum()
	{
		return enm;
	}

	/**
	 * Method 'setenm'
	 * 
	 * @param enum
	 */
	public void setEnum(String enm)
	{
		this.enm = enm;
	}

	/**
	 * Method 'getValue'
	 * 
	 * @return String
	 */
	@Column(name="value")
	public String getValue()
	{
		return value;
	}

	/**
	 * Method 'setValue'
	 * 
	 * @param value
	 */
	public void setValue(String value)
	{
		this.value = value;
	}

	/**
	 * Method 'getType'
	 * 
	 * @return String
	 */
	@Column(name="type")
	public String getType()
	{
		return type;
	}

	/**
	 * Method 'setType'
	 * 
	 * @param type
	 */
	public void setType(String type)
	{
		this.type = type;
	}

	/**
	 * Method 'getDescription'
	 * 
	 * @return String
	 */
	@Column(name="description")
	public String getDescription()
	{
		return description;
	}

	/**
	 * Method 'setDescription'
	 * 
	 * @param description
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}

	/**
	 * Method 'getOrd'
	 * 
	 * @return int
	 */
	@Column(name="ord")
	public int getOrd()
	{
		return ord;
	}

	/**
	 * Method 'setOrd'
	 * 
	 * @param ord
	 */
	public void setOrd(int ord)
	{
		this.ord = ord;
	}

	/**
	 * Method 'getGroup'
	 * 
	 * @return String
	 */
	@Column(name="group")
	public String getGroup()
	{
		return group;
	}

	/**
	 * Method 'setGroup'
	 * 
	 * @param group
	 */
	public void setGroup(String group)
	{
		this.group = group;
	}

	/**
	 * Method 'getFunction'
	 * 
	 * @return String
	 */
	@Column(name="function")
	public String getFunction()
	{
		return function;
	}

	/**
	 * Method 'setFunction'
	 * 
	 * @param function
	 */
	public void setFunction(String function)
	{
		this.function = function;
	}

	/**
	 * Method 'getApproved'
	 * 
	 * @return String
	 */
	@Column(name="approved")
	public String getApproved()
	{
		return approved;
	}

	/**
	 * Method 'setApproved'
	 * 
	 * @param approved
	 */
	public void setApproved(String approved)
	{
		this.approved = approved;
	}
	
	public List<WebSettings> findAll()
	{
		Query query = this.em().createQuery("SELECT wset FROM WebSettings wset");
		List<WebSettings> returns = query.getResultList();
		
		return returns;
	}
}
