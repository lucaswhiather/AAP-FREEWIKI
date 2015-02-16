/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wiki.dao;

import java.io.Serializable;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import wiki.util.HibernateUtil;

/**
 *
 * @author Lucas Whiather
 */
public class GenericDao<T> implements Serializable{

    private SessionFactory sf;
    private Session session;

    public GenericDao() {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
    }

    public GenericDao(Session session) {
        this.session = session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Session getSession() {
        return this.session;
    }

    public void salvar(T obj) throws Exception {
        this.session.save(obj);
    }

    public void editar(T obj) throws Exception {
        this.session.merge(obj);
    }

    public void excluir(T obj) throws Exception {
        this.session.delete(obj);
    }
}
