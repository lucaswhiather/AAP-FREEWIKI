/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wiki.control;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import wiki.dao.HistoricoDao;
import wiki.models.Article;
import wiki.models.Historico;
import wiki.models.User;

/**
 *
 * @author Lucas Whiather
 */
@ManagedBean
@ViewScoped
public class HistoricoBean extends GenericBean{

    public void salvar(User usuario, Article artigo, String acao) throws Exception {
        HistoricoDao historicoDao = new HistoricoDao();
        Historico historico = new Historico();
        
        historico.setData(new Date());
        historico.setAcao(acao);
        historico.setArtigo(artigo);
        historico.setUsuario(usuario);
        historicoDao.salvar(historico);

    }
    
     public List<Historico> listarTodos(Article artigo){
        try {
            return new HistoricoDao().buscarTodos(artigo);
        } catch (Exception ex) {            
            System.out.println(ex);
        }
        return null;
    }

}
