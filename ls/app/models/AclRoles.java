package models;

import play.*;
import play.db.jpa.*;
import play.data.validation.*;
import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="acl_roles")
public class AclRoles extends Model
{

	@Required
	@MaxSize(20)
	@MinSize(3)
	@Column(name="role_name")
	public String roleName;

	/**
	 * Method 'AclRoles'
	 * 
	 */
	public AclRoles(String roleName)
	{
		this.roleName = roleName;
	}

	/**
	 * Method 'getRoleName'
	 * 
	 * @return String
	 */
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
	
	public String toString() {
		return roleName;
	}

}
