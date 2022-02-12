package logic.bean;


import logic.dao.LoginDao;

import java.sql.SQLException;
import java.util.SortedMap;

public class LoginBean {

    public LoginBean(){
    	//nothing
    }

    private String username;
    private String password;
    
    private final LoginDao loginDao = new LoginDao();
    SortedMap<String, String> user;
	
    public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public int userExist() throws SQLException {
	user = loginDao.authenticate(username, password);
	
	if (user.isEmpty()) {
		return 1;		
		}
	else
		return 0;
	}
	public SortedMap<String, String> getUser() {
		return user;
	}
}

