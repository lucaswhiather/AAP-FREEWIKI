/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wiki.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import wiki.dao.CategoryDao;
import wiki.models.Category;

/**
 *
 * @author Lucas Whiather
 */
@FacesConverter(forClass = Category.class)
public class CategoryConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        CategoryDao dao = new CategoryDao();
        try {
            return dao.buscarPorId(Integer.parseInt(string));
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        return ((Category) o).getId().toString();
    }

}
