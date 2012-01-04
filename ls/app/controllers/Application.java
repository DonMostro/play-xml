package controllers;

import play.*;
import play.mvc.*;

import java.util.*;
import models.*;



public class Application extends Controller {

    public static void index() {
    	List tasks = AclUsers.findAll();
        render(tasks);
    }
    
    public static void changeStatus(Long id, Boolean done) {
    	AclUsers task = AclUsers.findById(id);
    	task.approved = done;
    	task.save();
    	renderJSON(task);
    }
    
	public static void createTask(String userName) {
		AclUsers task = new AclUsers(userName).save();
		renderJSON(task);
		
	}
}