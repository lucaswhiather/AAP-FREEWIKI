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
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import wiki.dao.AlteracaoDao;
import wiki.models.Alteracao;
import wiki.models.Article;
import wiki.models.User;

/**
 *
 * @author Lucas Whiather
 */
@ManagedBean
@ViewScoped
public class AlteracaoBean extends GenericBean {

    private Alteracao alteracao;
    @ManagedProperty(value = "#{loginBean.user}")
    private User usuario;

    public AlteracaoBean() {
    }

    public void novaAlteracao(Article artigo) {
        alteracao = new Alteracao();
        alteracao.setArtigo(artigo);
        alteracao.setUsuario(usuario);
        alteracao.setAprovacaoAdm(false);
        alteracao.setAprovacaoUsuario(false);
    }

    public void salvar() {
        try {
            AlteracaoDao alteracaoDao = new AlteracaoDao();

            alteracaoDao.salvar(alteracao);
            new HistoricoBean().salvar(usuario, alteracao.getArtigo(), "Proposta de alteração");
            novaAlteracao(alteracao.getArtigo());
            exibirMensagem("Proposta de alteração salva com sucesso.", FacesMessage.SEVERITY_INFO);
        } catch (Exception ex) {
            exibirMensagem("Não foi possivel realizar a operação.", FacesMessage.SEVERITY_ERROR);
            System.out.println("Erro - " + ex.getMessage());
        }
    }

    public List<Alteracao> listarAlteracaoDono() {
        try {
            return new AlteracaoDao().buscarTodos(usuario);
        } catch (Exception ex) {
            System.out.println("erro - " + ex.getMessage());
        }
        return null;
    }

    public List<Alteracao> listarTodasAlteracao() {
        try {
            return new AlteracaoDao().buscarNaoAprovadoADM();
        } catch (Exception ex) {
            System.out.println("erro - " + ex.getMessage());
        }
        return null;
    }

    public List<Alteracao> listarTodas(Article artigo) {
        try {
            System.out.println("Artigo - "+artigo.getTitle());
            return new AlteracaoDao().buscarTodos(artigo);
        } catch (Exception ex) {
            System.out.println("erro - " + ex.getMessage());
        }
        return null;
    }

    public void aprovarDono(Alteracao alteracao) {
        try {
            AlteracaoDao alteracaoDao = new AlteracaoDao();
            alteracao.setAprovacaoUsuario(true);
            alteracaoDao.editar(alteracao);
            new HistoricoBean().salvar(usuario, alteracao.getArtigo(), "Alteraçãp aprovada pelo dono.");
            exibirMensagem("Alteração aprovada com sucesso.", FacesMessage.SEVERITY_INFO);
        } catch (Exception ex) {
            System.out.println(ex);
            exibirMensagem(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void aprovarAdm(Alteracao alteracao) {
        try {
            AlteracaoDao alteracaoDao = new AlteracaoDao();
            alteracao.setAprovacaoAdm(true);
            alteracaoDao.editar(alteracao);
            new HistoricoBean().salvar(usuario, alteracao.getArtigo(), "Alteraçãp aprovada pelo Administrador.");
            exibirMensagem("Alteração aprovada com sucesso.", FacesMessage.SEVERITY_INFO);
        } catch (Exception ex) {
            System.out.println(ex);
            exibirMensagem(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void visualizar(Alteracao alteracao){
        this.alteracao = alteracao;
    }
    
    public Alteracao getAlteracao() {
        return alteracao;
    }

    public void setAlteracao(Alteracao alteracao) {
        this.alteracao = alteracao;
    }

    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

}
