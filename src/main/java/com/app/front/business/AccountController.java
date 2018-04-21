package com.app.front.business;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.app.front.model.User;


@ManagedBean(name="accountController")
@SessionScoped
public class AccountController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String EMAIL = "email";
	private static final String FULL_NAME = "full_name";
	
	private User user;	
	private int count_error;		
	
	private boolean loggedIn;
	
	@PostConstruct
    public void init() {
        user = new User();
        count_error = 0;    
    }
	
//	public void setUser(User user) {
//		this.user = user;
//	}	

	public User getUser() {
		return user;
	}

	public String doLogin() {		
				
		String email = user.getEmail();
		String password = user.getPassword();
		String fullName = user.getFullName();
		
		//Session JSF
        FacesContext context = FacesContext.getCurrentInstance();
		
		if(email.equals("darimile@gmail.com") && password.equals("4321") ) {			
			//context.getExternalContext().getSessionMap().put(EMAIL,email);
			//context.getExternalContext().getSessionMap().put(FULL_NAME,fullName);			
			
			loggedIn = true;			
			HttpSession session = SessionUtils.getSession();
			session.setAttribute(EMAIL,email);
			session.setAttribute(FULL_NAME,fullName);
			return "pages/home?faces-redirect=true";
		}else {	
			context.addMessage(null, new FacesMessage("Unknown login, try again"));
			count_error++;
			user.setMessage("Account invalid - veces: " + count_error);
			return "/login";
		}	
	}
	
	public String doLogout() {
		user.setMessage("Exit Session, Bye!");
		loggedIn = false;
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return "/login";		
	}	
		
	
	public String getUserName() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);		
		return session.getAttribute(EMAIL).toString();
	}

	public String getFullName() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
		return session.getAttribute(FULL_NAME).toString();
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
	
}
