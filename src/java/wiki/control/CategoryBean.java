/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wiki.control;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import wiki.dao.CategoryDao;
import wiki.models.Category;

/**
 *
 * @author Lucas Whiather
 */
@ManagedBean
@ViewScoped
public class CategoryBean extends GenericBean {

    private Category category;

    public CategoryBean() {
    }

    public void novoCadastro() {
        category = new Category();
    }

    public void salvar() {
        try {
            CategoryDao categoryDao = new CategoryDao();
            if (category.getId() == null) {
                categoryDao.salvar(category);
                novoCadastro();
                exibirMensagem("Categoria cadastrada com sucesso.", FacesMessage.SEVERITY_INFO);
            } else {
                categoryDao.editar(category);
                exibirMensagem("Categoria alterada com sucesso.", FacesMessage.SEVERITY_INFO);
            }
        } catch (Exception ex) {
            exibirMensagem("Não foi possivel realizar a operação.", FacesMessage.SEVERITY_ERROR);
            System.out.println("Erro - " + ex.getMessage());
        }
    }

    public void alterar(Category category) {
        this.category = category;
    }

    public void excluir(Category category) {
        try {
            CategoryDao categoryDao = new CategoryDao();
            categoryDao.excluir(category);
            exibirMensagem("Categoria excluida com sucesso.", FacesMessage.SEVERITY_INFO);
        } catch (Exception ex) {
            System.out.println(ex);
            exibirMensagem("Não foi possivel realizar a operação.", FacesMessage.SEVERITY_ERROR);
        }
    }
    
    public List<Category> listarTodos(){
        CategoryDao categoryDao = new CategoryDao();
        try {
            return categoryDao.buscarTodos();
        } catch (Exception ex) {            
            System.out.println(ex);
        }
        return null;
    }
    
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

}
