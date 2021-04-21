package bean;

import java.sql.*;
import java.util.SortedMap;
import java.util.TreeMap;

import dao.LoginDao;

public class LoginBean {

    public LoginBean(){
    	//nothing
    }

    private String username;
    private String password;
    
    private LoginDao loginDao = new LoginDao();
    SortedMap<String, String> user = new TreeMap<>();
	
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

