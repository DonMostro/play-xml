package models;

import java.util.*;
import java.io.*;
import javax.persistence.*;

import org.hibernate.annotations.Type;

import play.db.jpa.Model;

@Entity
@Table(name="acl_permissions")
public class AclPermissions extends Model implements Serializable
{
	@JoinColumn(name="acl_roles_id")
	@JoinTable(name="AclRoles")
	public AclRoles aclRolesId;

	@JoinColumn(name="acl_modules_id")
	@JoinTable(name="AclModules")
	public AclModules aclModulesId;

	@JoinColumn(name="permission")
	@JoinTable(name="WebPermissions")
	public String permission;

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
	public AclRoles getAclRolesId()
	{
		return aclRolesId;
	}

	/**
	 * Method 'setAclRolesId'
	 * 
	 * @param aclRolesId
	 */
	public void setAclRolesId(AclRoles aclRolesId)
	{
		this.aclRolesId = aclRolesId;
	}

	/**
	 * Method 'getAclModulesId'
	 * 
	 * @return int
	 */
	@Column(name="acl_modules_id")
	public AclModules getAclModulesId()
	{
		return aclModulesId;
	}

	/**
	 * Method 'setAclModulesId'
	 * 
	 * @param aclModulesId
	 */
	public void setAclModulesId(AclModules aclModulesId)
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
	
	public String toString()
	{
		return this.permission;
	}
	
}
