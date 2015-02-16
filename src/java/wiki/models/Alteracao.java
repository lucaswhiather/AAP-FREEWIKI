/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wiki.models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

/**
 *
 * @author Lucas Whiather
 */
@Entity
public class Alteracao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Lob
    private String conteudo;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User usuario;

    @JoinColumn(name = "id_artigo", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Article artigo;

    @Basic(optional = false)
    @Column
    private boolean aprovacaoAdm;

    @Basic(optional = false)
    @Column
    private boolean aprovacaoUsuario;

    public Alteracao() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }    
    
    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

    public Article getArtigo() {
        return artigo;
    }

    public void setArtigo(Article artigo) {
        this.artigo = artigo;
    }

    public boolean isAprovacaoAdm() {
        return aprovacaoAdm;
    }

    public void setAprovacaoAdm(boolean aprovacaoAdm) {
        this.aprovacaoAdm = aprovacaoAdm;
    }

    public boolean isAprovacaoUsuario() {
        return aprovacaoUsuario;
    }

    public void setAprovacaoUsuario(boolean aprovacaoUsuario) {
        this.aprovacaoUsuario = aprovacaoUsuario;
    }

}
