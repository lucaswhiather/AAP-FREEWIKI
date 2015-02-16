/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wiki.dao;

import java.util.List;
import org.hibernate.criterion.Order;
import wiki.models.Category;

/**
 *
 * @author Lucas Whiather
 */
public class CategoryDao extends GenericDao<Category> {

    public Category buscarPorId(Integer id) throws Exception {
        return (Category) getSession().get(Category.class, id);
    }

    public List<Category> buscarTodos() throws Exception {
        return (List<Category>) getSession()
                .createCriteria(Category.class)
                .addOrder(Order.asc("name"))
                .list();
    }
}
