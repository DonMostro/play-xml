package models;

import play.*;
import play.db.jpa.*;
import play.data.validation.*;
import javax.persistence.*;
import controllers.CRUD.*;

import org.hibernate.annotations.SQLUpdate;
import org.hsqldb.lib.MD5;

import java.util.*;
import java.security.*;

@Entity
@Table(name="acl_users")
public class AclUsers extends JPA
{
    @Required
    @ManyToOne
    @JoinColumn(name="acl_roles_id")
	public AclRoles aclRolesId;
    
    
	@Column(name="id")
	@Id
	public String id;
	
	@Column(name="user_name")
	public String userName;

	@Column(name="password")
	@Password
	public String password;

	@Column(name="first_names")
	public String firstNames;

	@Column(name="last_names")
	public String lastNames;

	@Email
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
	 * Method 'getUserName'
	 * 
	 * @return String
	 */
	@Column(name="user_name")
	public String getUserName()
	{
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
	 * @throws NoSuchAlgorithmException 
	 */
	public void setPassword(String password) throws NoSuchAlgorithmException
	{
		MessageDigest md = MessageDigest.getInstance("MD5");
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

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString() 
	{
		return userName;
	}
	
	public List<AclUsers> findAll()
	{
		Query query = this.em().createNativeQuery(
			    "SELECT * FROM acl_users "
			
				, AclUsers.class
		);
		return query.getResultList();
		
	}
	
}
