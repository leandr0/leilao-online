/**
 * 
 */
package com.fiap.leilao.web.application;

import java.io.Serializable;
import java.security.Principal;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Leandro
 *
 */
public abstract class UIAbstractBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2478948320073700578L;

	protected HttpSession session;

	protected String user;

	public UIAbstractBean() {
		createNewSession();
	}

	private void createNewSession(){
		session = (HttpSession) 
		getExternalContext().getSession(true);
	}

	public String getUser(){

		HttpServletRequest request =  (HttpServletRequest) getExternalContext().getRequest();
		Principal principal = request.getUserPrincipal();

		return principal.getName();
	}

	protected void setAttributeInSession(String attributeName, Object value){
		session.setAttribute(attributeName, value);
	}

	protected Object getAttributeInSession(String attributeName){
		return session.getAttribute(attributeName);
	}

	protected ExternalContext getExternalContext() {
		return FacesContext.getCurrentInstance()
		.getExternalContext();
	}

	protected Object getAttributeInContext(String attributeName){
		return getExternalContext().getRequestMap().get(attributeName);
	}

	public HttpSession getSession() {

		if(session == null)
			createNewSession();

		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}
	
	protected void showMessageWar(String message){
		FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_WARN,message,message));
	}

	protected void showMessageInfo(String message){
		FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_INFO,message,message));
	}

	protected void showMessageError(String message){
		FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_ERROR,message,message));
	}
	
	protected void showMessageFatal(String message){
		FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_FATAL,message,message));
	}
}
