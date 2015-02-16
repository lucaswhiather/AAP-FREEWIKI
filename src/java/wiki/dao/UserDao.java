/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wiki.dao;

import org.hibernate.criterion.Restrictions;
import wiki.models.User;

/**
 *
 * @author Lucas Whiather
 */
public class UserDao extends GenericDao<User> {

    public User logar(String email, String senha) throws Exception {
        if (email == null || email.trim().equals("")) {
            return null;
        }
        if (senha == null || senha.trim().equals("")) {
            return null;
        }
        return (User) this.getSession().createCriteria(User.class)
                .add(Restrictions.eq("email", email)).add(Restrictions.eq("password", senha)).uniqueResult();
    }
}
