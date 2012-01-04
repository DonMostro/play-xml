package models;

import play.*;
import play.db.jpa.*;
import play.data.validation.*;
import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="acl_users")
public class AclUsers extends Model
{
	@Column(name="acl_roles_id")
	public int aclRolesId;
	
	@Column(name="user_name")
	public String userName;

	@Column(name="password")
	public String password;

	@Column(name="first_names")
	public String firstNames;

	@Column(name="last_names")
	public String lastNames;

	public String email;

	public Boolean approved;


	/**
	 * Method 'AclUsers'
	 * 
	 */
	public AclUsers(String userName)
	{
		this.userName = userName;
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
	 * Method 'getUserName'
	 * 
	 * @return String
	 */
	@Column(name="user_name")
	public String getUserName()
	{
		return userName;
	}
	
	public String toString() {
		return userName;
	}

	/**
	 * Method 'setUserName'
	 * 
	 * @param userName
	 */
	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	/**
	 * Method 'getPassword'
	 * 
	 * @return String
	 */
	@Column(name="password")
	public String getPassword()
	{
		return password;
	}

	/**
	 * Method 'setPassword'
	 * 
	 * @param password
	 */
	public void setPassword(String password)
	{
		this.password = password;
	}

	/**
	 * Method 'getFirstNames'
	 * 
	 * @return String
	 */
	@Column(name="first_names")
	public String getFirstNames()
	{
		return firstNames;
	}

	/**
	 * Method 'setFirstNames'
	 * 
	 * @param firstNames
	 */
	public void setFirstNames(String firstNames)
	{
		this.firstNames = firstNames;
	}

	/**
	 * Method 'getLastNames'
	 * 
	 * @return String
	 */
	@Column(name="last_names")
	public String getLastNames()
	{
		return lastNames;
	}

	/**
	 * Method 'setLastNames'
	 * 
	 * @param lastNames
	 */
	public void setLastNames(String lastNames)
	{
		this.lastNames = lastNames;
	}

	/**
	 * Method 'getEmail'
	 * 
	 * @return String
	 */
	@Column(name="email")
	public String getEmail()
	{
		return email;
	}

	/**
	 * Method 'setEmail'
	 * 
	 * @param email
	 */
	public void setEmail(String email)
	{
		this.email = email;
	}

	/**
	 * Method 'getApproved'
	 * 
	 * @return String
	 */
	@Column(name="approved")
	public Boolean getApproved()
	{
		return approved;
	}

	/**
	 * Method 'setApproved'
	 * 
	 * @param approved
	 */
	public void setApproved(Boolean approved)
	{
		this.approved = approved;
	}

}
