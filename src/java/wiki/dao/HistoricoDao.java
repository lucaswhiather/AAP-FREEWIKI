/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wiki.dao;

import java.util.List;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import wiki.models.Article;
import wiki.models.Historico;
import wiki.models.User;

/**
 *
 * @author Lucas Whiather
 */
public class HistoricoDao extends GenericDao<Historico>{
     
    public List<Historico> buscarTodos(Article artigo) throws Exception {
        return (List<Historico>) getSession().createCriteria(Historico.class)
                .add(Restrictions.eq("artigo", artigo))
                .addOrder(Order.asc("id"))
                .list();
    }
    
    
    public void excluirHistorico(Article artigo) throws Exception{
        List<Historico> lista = buscarTodos(artigo);
           for (Historico h : lista) {
            excluir(h);
        }
    }
           
}
