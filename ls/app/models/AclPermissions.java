package models;

import java.util.*;
import java.io.*;
import javax.persistence.*;

import play.db.jpa.Model;

@Entity
@Table(name="acl_permissions")
public class AclPermissions extends Model
{

	@Column(name="acl_roles_id")
	protected int aclRolesId;

	@Column(name="acl_modules_id")
	protected int aclModulesId;

	protected String permission;

	/**
	 * Method 'AclPermissions'
	 * 
	 */
	public AclPermissions()
	{
	}

	/**
	 * Method 'getAclRolesId'
	 * 
	 * @return int
	 */
	@Column(name="acl_roles_id")
	public int getAclRolesId()
	{
		return aclRolesId;
	}

	/**
	 * Method 'setAclRolesId'
	 * 
	 * @param aclRolesId
	 */
	public void setAclRolesId(int aclRolesId)
	{
		this.aclRolesId = aclRolesId;
	}

	/**
	 * Method 'getAclModulesId'
	 * 
	 * @return int
	 */
	@Column(name="acl_modules_id")
	public int getAclModulesId()
	{
		return aclModulesId;
	}

	/**
	 * Method 'setAclModulesId'
	 * 
	 * @param aclModulesId
	 */
	public void setAclModulesId(int aclModulesId)
	{
		this.aclModulesId = aclModulesId;
	}

	/**
	 * Method 'getPermission'
	 * 
	 * @return String
	 */
	@Column(name="permission")
	public String getPermission()
	{
		return permission;
	}

	/**
	 * Method 'setPermission'
	 * 
	 * @param permission
	 */
	public void setPermission(String permission)
	{
		this.permission = permission;
	}

}
