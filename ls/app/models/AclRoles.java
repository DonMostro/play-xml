package models;

import java.util.*;
import java.io.*;
import javax.persistence.*;

import play.db.jpa.Model;

@Entity
@Table(name="acl_roles")
public class AclRoles extends Model
{
	@Column(name="role_name")
	protected String roleName;

	protected String root;

	/**
	 * Method 'AclRoles'
	 * 
	 */
	public AclRoles()
	{
	}

	/**
	 * Method 'getRoleName'
	 * 
	 * @return String
	 */
	@Column(name="role_name")
	public String getRoleName()
	{
		return roleName;
	}

	/**
	 * Method 'setRoleName'
	 * 
	 * @param roleName
	 */
	public void setRoleName(String roleName)
	{
		this.roleName = roleName;
	}

	/**
	 * Method 'getRoot'
	 * 
	 * @return String
	 */
	@Column(name="root")
	public String getRoot()
	{
		return root;
	}

	/**
	 * Method 'setRoot'
	 * 
	 * @param root
	 */
	public void setRoot(String root)
	{
		this.root = root;
	}

}
