package models;

import play.*;
import play.db.jpa.*;
import play.data.validation.*;
import play.exceptions.JavaExecutionException;
import zwei.utils.HqlToSqlTranslator;

import javax.persistence.*;

import java.util.*;

@javax.persistence.Entity
@Table(name="acl_modules")
public class AclModules extends Model
{

    @ManyToOne
    @JoinColumn(name="parent_id")
	public AclModules parentId;

    @Column(name="title")
    public String title;

    @Column(name="module")
    public String module;

    @Column(name="tree")
    public String tree;

    @Column(name="linkable")
    public String linkable;

    @Column(name="approved")
    public String approved;
    

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
	public AclModules getParentId()
	{
		return parentId;
	}

	/**
	 * Method 'setParentId'
	 * 
	 * @param parentId
	 */
	public void setParentId(AclModules parentId)
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
	
	public String toString() 
	{
		return title;
	}

	public List<AclModules> listGrantedResourcesByParentId(long parentId)
	{	
		List<AclModules> returns = null;
		Query query = null;
		
		query = this.em().createNativeQuery(
			    "SELECT acl_modules.id, acl_modules.parent_id, acl_modules.title, " +
			    "acl_modules.module, acl_modules.tree, acl_modules.approved, acl_modules.linkable " +
			    "FROM acl_modules " +
			    "JOIN acl_permissions " +
			    "ON acl_modules.id = acl_permissions.acl_modules_id " +
			    "JOIN acl_roles " +
			    "ON acl_roles.id = acl_permissions.acl_roles_id " +
			    "JOIN acl_users " +
			    "ON acl_roles.id = acl_users.acl_roles_id " +
			    "WHERE acl_modules.tree = '1' " +
			    "AND parent_id = " + parentId + " " +
			    "GROUP BY acl_modules.id "
			    //"AND acl_users.user_name = " + this.user + " " +			    
			    
			    //"select mod from aclModules " +
				//"where parentId = " + parentId + //Query JPA				
				, AclModules.class
		);

		if (query.getResultList().size() > 0) {
			returns = query.getResultList();	
		}
		return returns;
	}
	
	
	
	public List<AclModules> getChildrens(long parentId)
	{
		List<AclModules> childrens = listGrantedResourcesByParentId(parentId);
		return childrens;
	}


	public HashMap getTreeStruct(int parentId)
	{
		List<AclModules>  root = getChildrens(parentId);
		HashMap<Long, HashMap> nodes = new HashMap(); 
		HashMap subnodes = new HashMap();
		HashMap subnodes2;
		
		int i = 0;
		long key = 0;
		
		for (AclModules branch : root) {
			
			if (branch.getTree().equals("1")) {
				key = branch.parentId.id == 0 ?  branch.getId() : i;

				subnodes = new HashMap();
				subnodes.put("id", branch.getId());
				subnodes.put("label", branch.getTitle());

				if (branch.getLinkable().equals("1")) {
					subnodes.put("url", "index/components?p=" + branch.getModule());
				}
				
				if (getChildrens(key) != null) {
					List<AclModules> root2 = getChildrens(key);
					subnodes2 = new HashMap();
					for (AclModules branch2 : root2) {
						
						if (branch2.getTree().equals("1")) {
							subnodes2.put("id", branch2.getId());
							subnodes2.put("label", branch2.getTitle());
							
							System.out.println("linkable: "+branch2.getLinkable());
							System.out.println("module: "+branch2.getModule());
							
							if (branch2.getLinkable().equals("1")) {
								subnodes2.put("url", "/application/components?p=" + branch2.getModule());
							}
							subnodes.put("children", subnodes2);
						}
						
					}
				}
			}
			nodes.put(key, subnodes);
		}
	    
		return nodes;
	}	
}
