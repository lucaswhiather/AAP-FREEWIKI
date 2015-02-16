/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wiki.control;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Lucas Whiather
 */
public class GenericBean implements Serializable {

    private static final long serialVersionUID = 1L;

    public void exibirMensagem(String msg, FacesMessage.Severity tipo) {
        FacesContext fc = FacesContext.getCurrentInstance();
        FacesMessage fm;

        fm = new FacesMessage(tipo, msg, msg);
        fc.addMessage(null, fm);
    }
}
