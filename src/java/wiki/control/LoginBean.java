/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wiki.control;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import wiki.dao.UserDao;
import wiki.models.User;

/**
 *
 * @author Lucas Whiather
 */
@ManagedBean
@SessionScoped
public class LoginBean extends GenericBean {

    private User user;
    private String email;
    private String senha;

    public LoginBean() {
    }

    public String logar() {
        try {
            UserDao userDao = new UserDao();
            user = userDao.logar(email, senha);
            if (user != null) {
                email = "";
                senha = "";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuário logado com sucesso!", ""));
                return "index.xhtml?faces-redirect=true";
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário ou senha invalido!", ""));

                return "";
            }
        } catch (Exception ex) {
            System.out.println("Erro - " + ex.getMessage());
            return "";
        }
    }

    public String sair() {
        user = null;
        email = "";
        senha = "";
        return "index.html?redirect=true";
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    

}
