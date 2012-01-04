package models;

import play.*;
import play.db.jpa.*;
import play.data.validation.*;
import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="acl_modules")
public class AclModules extends Model
{

	@Column(name="parent_id")
	protected int parentId;

	protected String title;

	protected String module;

	protected String tree;

	protected String linkable;

	protected String approved;

	/**
	 * Method 'AclModules'
	 * 
	 */
	public AclModules()
	{
	}

	/**
	 * Method 'getParentId'
	 * 
	 * @return int
	 */
	@Column(name="parent_id")
	public int getParentId()
	{
		return parentId;
	}

	/**
	 * Method 'setParentId'
	 * 
	 * @param parentId
	 */
	public void setParentId(int parentId)
	{
		this.parentId = parentId;
	}

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

	/**
	 * Method 'getModule'
	 * 
	 * @return String
	 */
	@Column(name="module")
	public String getModule()
	{
		return module;
	}

	/**
	 * Method 'setModule'
	 * 
	 * @param module
	 */
	public void setModule(String module)
	{
		this.module = module;
	}

	/**
	 * Method 'getTree'
	 * 
	 * @return String
	 */
	@Column(name="tree")
	public String getTree()
	{
		return tree;
	}

	/**
	 * Method 'setTree'
	 * 
	 * @param tree
	 */
	public void setTree(String tree)
	{
		this.tree = tree;
	}

	/**
	 * Method 'getLinkable'
	 * 
	 * @return String
	 */
	@Column(name="linkable")
	public String getLinkable()
	{
		return linkable;
	}

	/**
	 * Method 'setLinkable'
	 * 
	 * @param linkable
	 */
	public void setLinkable(String linkable)
	{
		this.linkable = linkable;
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

}
