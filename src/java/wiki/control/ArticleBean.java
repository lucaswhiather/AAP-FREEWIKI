/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wiki.control;

import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.chart.PieChartModel;
import wiki.dao.AlteracaoDao;
import wiki.dao.ArticleDao;
import wiki.dao.CategoryDao;
import wiki.dao.HistoricoDao;
import wiki.models.Article;
import wiki.models.Category;
import wiki.models.User;

/**
 *
 * @author Lucas Whiather
 */
@ManagedBean
@ViewScoped
public class ArticleBean extends GenericBean {

    private Article article;
    @ManagedProperty(value = "#{loginBean.user}")
    private User user;

    private Integer id;
    private Integer cat;
    private String busca;

    private Category categoria;

    public ArticleBean() {
        categoria = null;
        busca = null;
    }

    public void carregar() {
        
        if (id != null) {
            try {
                ArticleDao articleDao = new ArticleDao();
                article = articleDao.buscarPorId(id);
            } catch (Exception ex) {
                System.out.println("Erro - " + ex.getMessage());
            }
        }

        if (cat != null) {
            try {
                CategoryDao categoriaDao = new CategoryDao();
                categoria = categoriaDao.buscarPorId(cat);
            } catch (Exception ex) {
                System.out.println("Erro - " + ex.getMessage());
            }
        }
    }

    public void novoCadastro() {
        article = new Article();
        article.setIntusefull(0);
        article.setUseless(0);
        article.setAdmApproved(false);
        article.setUserApproved(false);
        article.setUser(user);
    }

    public void salvar() {
        try {
            ArticleDao articleDao = new ArticleDao();
            if (article.getId() == null) {
                articleDao.salvar(article);
                new HistoricoBean().salvar(user, article, "Artigo criado");
                novoCadastro();
                exibirMensagem("Artigo cadastrado com sucesso.", FacesMessage.SEVERITY_INFO);
            } else {
                article.setAdmApproved(false);
                article.setUserApproved(false);

                articleDao.editar(article);

                new HistoricoBean().salvar(user, article, "Artigo alterado");
                exibirMensagem("Artigo alterado com sucesso.", FacesMessage.SEVERITY_INFO);
            }
        } catch (Exception ex) {
            exibirMensagem("Não foi possivel realizar a operação.", FacesMessage.SEVERITY_ERROR);
            System.out.println("Erro - " + ex.getMessage());
        }
    }

    public void alterar(Article article) {
        this.article = article;
    }

    public void excluir(Article article) {
        try {
            new HistoricoDao().excluirHistorico(article);
            new AlteracaoDao().excluirAlteracoes(article);
            ArticleDao articleDao = new ArticleDao();
            articleDao.excluir(article);
            exibirMensagem("Artigo excluido com sucesso.", FacesMessage.SEVERITY_INFO);
        } catch (Exception ex) {
            System.out.println(ex);
            exibirMensagem(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void aprovar(Article article) {
        try {
            ArticleDao articleDao = new ArticleDao();
            article.setUserApproved(!article.isUserApproved());
            articleDao.editar(article);
            new HistoricoBean().salvar(user, article, "Artigo " + (article.isUserApproved() ? "aprovado" : "desaprovado") + " pelo dono");
            exibirMensagem("Artigo " + (article.isUserApproved() ? "aprovado" : "desaprovado") + " com sucesso.", FacesMessage.SEVERITY_INFO);
        } catch (Exception ex) {
            System.out.println(ex);
            exibirMensagem(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void aprovarAdm(Article article) {
        try {
            ArticleDao articleDao = new ArticleDao();
            article.setAdmApproved(!article.isAdmApproved());
            articleDao.editar(article);
            new HistoricoBean().salvar(user, article, "Artigo " + (article.isAdmApproved() ? "aprovado" : "desaprovado") + " pelo administrador");
            exibirMensagem("Artigo " + (article.isAdmApproved() ? "aprovado" : "desaprovado") + " com sucesso.", FacesMessage.SEVERITY_INFO);
        } catch (Exception ex) {
            System.out.println(ex);
            exibirMensagem(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public List<Article> listarTodosUsuario() {
        ArticleDao articleDAo = new ArticleDao();
        try {
            return articleDAo.buscarTodos(user);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return null;
    }

    public List<Article> listarTop() {
        try {
            return new ArticleDao().buscarTop();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return null;
    }

    public List<Article> listarTodos() {
        try {
            return new ArticleDao().buscarTodos(categoria,busca);
        } catch (Exception ex) {
            System.out.println("Erro ao listar - "+ex);
        }
        return null;
    }

    public List<Article> listarTodosAprovadoADM(boolean aprovado) {
        try {
            return new ArticleDao().buscarTodos(aprovado);
        } catch (Exception ex) {
            System.out.println("Erro - " + ex);
        }
        return null;
    }

    public PieChartModel util() {
        PieChartModel pieModel = new PieChartModel();

        pieModel.set("Util - " + article.getIntusefull(), article.getIntusefull());
        pieModel.set("Inutil - " + article.getUseless(), article.getUseless());

        //pieModel.setTitle("Utilidade");
        pieModel.setLegendPosition("e");
        pieModel.setFill(false);
        pieModel.setShowDataLabels(true);
        pieModel.setDiameter(150);
        return pieModel;
    }

    public void foiUtil(boolean util) {
        ArticleDao articleDAo = new ArticleDao();
        try {
            if (util) {
                article.setIntusefull(article.getIntusefull() + 1);
            } else {
                article.setUseless(article.getUseless() + 1);
            }
            articleDAo.editar(article);
            exibirMensagem("Origado por avaliar esse artigo.", FacesMessage.SEVERITY_INFO);

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCat() {
        return cat;
    }

    public void setCat(Integer cat) {
        this.cat = cat;
    }

    public String getBusca() {
        return busca;
    }

    public void setBusca(String busca) {
        this.busca = busca;
    }

}
