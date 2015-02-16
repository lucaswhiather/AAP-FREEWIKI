/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wiki.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import wiki.models.Article;
import wiki.models.Category;
import wiki.models.User;

/**
 *
 * @author Lucas Whiather
 */
public class ArticleDao extends GenericDao<Article> {

    public Article buscarPorId(Integer id) throws Exception {
        return (Article) getSession().get(Article.class, id);
    }

    public List<Article> buscarTodos() throws Exception {
        return (List<Article>) getSession().createCriteria(Article.class)
                .addOrder(Order.asc("title"))
                .list();
    }

    public List<Article> buscarTodos(User user) throws Exception {
        return (List<Article>) getSession().createCriteria(Article.class)
                .add(Restrictions.eq("user", user))
                .addOrder(Order.asc("title"))
                .list();
    }

    public List<Article> buscarTodos(Category categoria, String titulo) throws Exception {

        Criteria criteria = getSession()
                .createCriteria(Article.class)
                .add(Restrictions.eq("userApproved", true))
                .add(Restrictions.eq("admApproved", true));
        

        criteria.addOrder(Order.asc("title"));
        
        if(categoria!=null){
            criteria.add(Restrictions.eq("category", categoria));
        }
          
        if(titulo!=null && !titulo.equals("")){
            criteria.add(Restrictions.ilike("title", titulo,MatchMode.ANYWHERE));
        }
        System.out.println("chegando - "+titulo);
        return (List<Article>) criteria.list();

    }

    public List<Article> buscarTodos(boolean aprovado) throws Exception {
        return (List<Article>) getSession().createCriteria(Article.class)
                .add(Restrictions.eq("admApproved", aprovado))
                .addOrder(Order.asc("title"))
                .list();
    }

    public List<Article> buscarTop() throws Exception {
        return (List<Article>) getSession().createCriteria(Article.class)
                .add(Restrictions.eq("admApproved", true))
                .add(Restrictions.eq("userApproved", true))
                .addOrder(Order.desc("useless"))
                .setMaxResults(5)
                .list();
    }

    public List<Article> buscarTodos(Category category) throws Exception {
        return (List<Article>) getSession().createCriteria(Article.class)
                .add(Restrictions.eq("category", category))
                .addOrder(Order.asc("title"))
                .list();
    }

}
