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

	public List<AclModules> listGrantedResourcesByParentId(int parentId)
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
			    "AND parent_id = " + parentId + " "
			    //"AND acl_users.user_name = " + this.user + " " +			    
			    
			    //"select mod from aclModules " +
				//"where parentId = " + parentId +				
				, AclModules.class
		);
		
		if (query.getResultList().size() > 0) {
			returns = query.getResultList();	
		}
		return returns;
		/* 
		$select=$this->_db->select()
		->from($this->_tb_modules, array('id','parent_id','module','title','linkable','tree'))
		->from($this->_tb_permissions, array())
		->from($this->_tb_roles, array())
		->from($this->_tb_users, array())
		->where($this->_tb_modules."_id = $this->_tb_modules.id")
		->where($this->_tb_permissions.".{$this->_tb_roles}_id = $this->_tb_roles.id")
		->where($this->_tb_users.".acl_roles_id = $this->_tb_permissions.{$this->_tb_roles}_id")
		->where('parent_id ='.(int)$parent_id)
		->where($this->_tb_users.'.user_name = "' . $this->_user . '"')
		->where($this->_tb_modules.'.tree = ?', '1') //[TODO] externalizar la condicion tree segun el caso

		->group($this->_tb_modules.'.id')
		;

		//Zwei_Utils_Debug::write($select->__toString());
		return($this->_db->fetchAll($select));
		*/
	}
	
	
	
	public HashMap getChildrens(int parentId)
	{
		List<AclModules> childrens = listGrantedResourcesByParentId(parentId);
		HashMap returns = null;
		
		if (parentId != 0) {
			for (AclModules child : childrens) {
				returns.put("label", child.getTitle());
				returns.put("module", child.getModule());
				returns.put("tree", child.getTree());
				returns.put("id", child.getId());
				returns.put("linkable", child.getLinkable());
			}
		}
		
		return returns;
	}


	public HashMap getTreeStruct(int parentId)
	{
		HashMap<String, String> root = getChildrens(parentId);
		HashMap<Integer, HashMap> nodes = new HashMap(); 
		HashMap subnodes = new HashMap();
		
		//List<List<String>> nodes = new ArrayList<List<String>>();
		
		int i = 0;
		int key = 0;
		
		Iterator it = root.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry e = (Map.Entry) it.next();
			subnodes.put(e.getKey(), e.getValue());
		}
		nodes.put(key, subnodes);
		/*
		for (AclModules branch : root.values()) {
			
			if (branch.getTree().equals("1")) {
				key = (int) ((branch.parentId.id == 0) ? branch.getId() : i);
			}
			
			subnodes = new HashMap();
			subnodes.put("id", branch.getId());
			subnodes.put("label", branch.getTitle());
			
			if (branch.getLinkable().equals("1")) {
				subnodes.put("url", "index/components?p=" + branch.getModule());
			}
			
			if (getChildrens(key) != null) {
				subnodes.put("children", getChildrens(key));
				i++;
			}
			nodes.put(key, subnodes);
		}
	    */
		return nodes;
	}	
}
