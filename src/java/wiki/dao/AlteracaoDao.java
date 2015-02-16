/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wiki.dao;

import java.util.List;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import wiki.models.Alteracao;
import wiki.models.Article;
import wiki.models.User;

/**
 *
 * @author Lucas Whiather
 */
public class AlteracaoDao extends GenericDao<Alteracao> {

    public List<Alteracao> buscarNaoAprovadoADM() throws Exception {
        return (List<Alteracao>) getSession().createCriteria(Alteracao.class)
                .add(Restrictions.eq("aprovacaoAdm", false))
                .list();
    }

    public List<Alteracao> buscarTodos(User usuario) throws Exception {
        return (List<Alteracao>) getSession().createCriteria(Alteracao.class)
                .add(Restrictions.eq("aprovacaoUsuario", false))
                .createAlias("artigo", "a")
                .add(Restrictions.eq("a.user", usuario))
                .list();
    }

    public List<Alteracao> buscarTodos(Article artigo) throws Exception {
        return (List<Alteracao>) getSession().createCriteria(Alteracao.class)
                .add(Restrictions.eq("aprovacaoUsuario", true))
                .add(Restrictions.eq("aprovacaoAdm", true))
                .add(Restrictions.eq("artigo", artigo))
                .list();
    }

    public void excluirAlteracoes(Article artigo) throws Exception {
        List<Alteracao> lista = (List<Alteracao>) getSession().createCriteria(Alteracao.class)
                .add(Restrictions.eq("artigo", artigo))
                .list();

        for (Alteracao a : lista) {
            excluir(a);
        }
    }

}
