/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wiki.control;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import wiki.dao.UserDao;
import wiki.models.User;

/**
 *
 * @author Lucas Whiather
 */
@ManagedBean
@ViewScoped
public class UserBean extends GenericBean{
    
    private User user;
    private String confirmar;

    public UserBean() {
    }
    
    public void novoCadastro(){
        user = new User();
        user.setType(1);
    }
    
    public void salvar(){
        
        if(!user.getPassword().equals(confirmar)){
            exibirMensagem("Senha diferente de confirmar.", FacesMessage.SEVERITY_ERROR);
            return;
        }
        
        UserDao userDao = new UserDao();
        try {
            userDao.salvar(user);
            exibirMensagem("Cadastro efetuado com sucesso.", FacesMessage.SEVERITY_INFO);
        } catch (Exception ex) {
            System.out.println("Erro - "+ex.getMessage());
            exibirMensagem("Erro ao cadastrar.", FacesMessage.SEVERITY_ERROR);
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getConfirmar() {
        return confirmar;
    }

    public void setConfirmar(String confirmar) {
        this.confirmar = confirmar;
    }
    
    
    
}
