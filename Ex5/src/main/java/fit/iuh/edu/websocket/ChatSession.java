package fit.iuh.edu.websocket;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import jakarta.annotation.PostConstruct;

public class ChatSession implements Serializable {
	 private static final long serialVersionUID = 1L;
	private Map<String, String> users =  new HashMap<>();
	    
	    public ChatSession(){}
	    
	    @PostConstruct
	    public void init(){
	         users = new HashMap<>();
	    }

	    /**
	     * @return the users
	     */
	    public Map<String, String> getUsers() {
	        return users;
	    }

	    /**
	     * @param for the users
	     */
	    public void setUsers(Map<String, String> users) {
	        this.users = users;
	    }
}
