package models;

import play.*;
import play.db.jpa.*;
import play.data.validation.*;
import play.exceptions.JavaExecutionException;
import zwei.utils.HqlToSqlTranslator;

import javax.persistence.*;

import java.io.Serializable;
import java.util.*;

@javax.persistence.Entity
@Table(name="acl_modules")
public class AclModules extends JPA implements Serializable
{

    @ManyToOne
    @JoinColumn(name="parent_id")
	private AclModules parentId;

    @Column(name="title")
    private String title;

    @JoinTable(name="acl_modules")
    @Transient
    private String parentTitle;    
    
    @Column(name="module")
    private String module;

    @Column(name="tree")
    private String tree;

    @Column(name="linkable")
    private String linkable;

    @Column(name="approved")
    private String approved;

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
    
	public AclModules()
	{
	}

    
    
	/**
	 * Method 'AclModules'
	 * 
	 */
	public AclModules(int id, String title)
	{
		this.id=id;
		this.title=title;
	}

	/**
	 * Method 'AclModules'
	 * 
	 */
	public AclModules(int id, String title, String module, String linkable, String approved, String tree)
	{
		//System.out.println(parentId.id);
		//this.parentTitle=parentId.title;
		this.id = id;
		this.title = title;
		this.module = module;
		this.linkable = linkable;
		this.approved = approved;
		this.tree = tree;
	}
	
	/**
	 * Method 'AclModules'
	 * 
	 */
	public AclModules(int id, String title, String module, String linkable, String approved, String tree, AclModules parentId)
	{
		//System.out.println(parentId.id);
		this.parentTitle=parentId.title;
		this.id = id;
		this.title = title;
		this.module = module;
		this.linkable = linkable;
		this.approved = approved;
		this.tree = tree;
		//this.parentId = parentId;
		//this.parentTitle = parentTitle;
	}

	
	/**
	 * Method 'getId'
	 * 
	 * @return int
	 */
	@Column(name="id")
	public int getId()
	{
		return id;
	}
	
	
	/**
	 * Method 'setId'
	 * 
	 * @param parentId
	 */
	public void setId(int id)
	{
		this.id = id;
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
		List<AclModules> modules = null;
		Query query = null;
		
		//[TODO] por falta de conocimientos en HQL se usa createNativeQuery(String SQL, class JPA) por ahora, 
		// lo ideal sería usar createQuery(String HQL).
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
				, AclModules.class
		);

		if (query.getResultList().size() > 0) {
			modules = query.getResultList();	
		}
		return modules;
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
		HashMap subnodes2 = new HashMap();
		ArrayList<HashMap> childrens = new ArrayList<HashMap>();
		
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
					childrens = new ArrayList<HashMap>();
					List<AclModules> root2 = getChildrens(key);
					for (AclModules branch2 : root2) {
						subnodes2 = new HashMap<String, String>();
						
						if (branch2.getTree().equals("1")) {
							subnodes2.put("id", branch2.getId());
							subnodes2.put("label", branch2.getTitle());
							
							if (branch2.getLinkable().equals("1")) {
								subnodes2.put("url", "/application/components?p=" + branch2.getModule());
							}
							childrens.add(subnodes2);
						}
					}
					subnodes.put("children", childrens);
				}
			}
			nodes.put(key, subnodes);
		}
	    
		return nodes;
	}
	
	public List<AclModules> findAll() {
		List<AclModules> modules = null;
		Query query = null;
		
		query = this.em().createQuery(
				"select new AclModules(mod.id, mod.title, mod.module, mod.linkable, mod.approved, mod.tree, mod.parentId) " +
				"from AclModules mod " 
		);
	
		
		if (query.getResultList().size() > 0) {
			modules = query.getResultList();
		}

		return modules;
	}
	
	/**
	 * Retorna findAll() como ArrayList<HashMap<String, String>>  en lugar de List<AclModules>
	 * @param returnMap
	 * @return
	 */
	public ArrayList<HashMap<String, String>> findAll(boolean returnArray) 
	{
		HashMap<String, String> modules; 
		ArrayList<HashMap<String, String>> rows = new ArrayList<HashMap<String, String>>();
		
		List<AclModules> modulesList = this.findAll();
		for (AclModules module : modulesList) {
			modules = new HashMap<String, String>();
			modules.put("title", module.getTitle());
			modules.put("id", Integer.toString(module.getId()));
			rows.add(modules);
		}
		return rows;
	}
	
	
	
	
	/**
 	 * Selecciona diferentes módulos, para combobox de módulo padre 
	 * evitando colisiones de nombre dada la relación recursiva 
	 * en módulo "módulos"
	 * @return
	 */

	public List<AclModules> selectDistinct()
	{
		Query query = null;
		List<AclModules> modules = null;
		
		query = this.em().createQuery(
				"select distinct new AclModules(mod.id, mod.title as parentTitle) " +
				"from AclModules mod " 
		);

		if (query.getResultList().size() > 0) {
			modules = query.getResultList();
		}

		return modules;
	}

	
	/**
	 * Retorna selectDistinct() como HashMap<String, String> en lugar de List<AclModules>
	 * @param returnMap
	 * @return
	 */
	public ArrayList<HashMap<String, String>>  selectDistinct(boolean returnArray) 
	{
		HashMap<String, String> modules; 
		ArrayList<HashMap<String, String>> rows = new ArrayList<HashMap<String, String>>();
		
		List<AclModules> modulesList = this.selectDistinct();
		for (AclModules module : modulesList) {
			modules = new HashMap<String, String>();
			modules.put("parent_title", module.getTitle());
			modules.put("id", Integer.toString(module.getId()));
			rows.add(modules);
		}
		return rows;
	}
	
	
	/**
	 * Selecciona diferentes módulos para uso general
     * [TODO] redunda la funcionalidad de selectDistinct() pero cambia 'parent_title' por 'module_title'
	 */

	public List<AclModules> getModules()
	{
		Query query = null;
		List<AclModules> modules = null;
		
		query = this.em().createQuery(
				"select distinct new AclModules(mod.id, mod.title as moduleTitle) " +
				"from AclModules mod " 
		);

		if (query.getResultList().size() > 0) {
			modules = query.getResultList();
		}

		return modules;
	}
	
	/**
	 * Retorna getModules() como ArrayList<HashMap<String, String>>  en lugar de List<AclModules>
	 * @param returnMap
	 * @return
	 */
	public ArrayList<HashMap<String, String>> getModules(boolean returnArray) 
	{
		HashMap<String, String> modules; 
		ArrayList<HashMap<String, String>> rows = new ArrayList<HashMap<String, String>>();
		
		List<AclModules> modulesList = this.getModules();
		for (AclModules module : modulesList) {
			modules = new HashMap<String, String>();
			modules.put("title", module.getTitle());
			modules.put("id", Integer.toString(module.getId()));
			rows.add(modules);
		}
		return rows;
	}
}
