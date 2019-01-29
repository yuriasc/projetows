/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pb;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 * @author yuria
 */
@Entity
@Table(name = "filmes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Filme.findAll", query = "SELECT l FROM Filme l"),
    @NamedQuery(name = "Filme.findByTitulo", query = "SELECT l FROM Filme l WHERE l.titulo = :titulo"),
    @NamedQuery(name = "Filme.findByDiretor", query = "SELECT l FROM Filme l WHERE l.diretor = :diretor"),
    @NamedQuery(name = "Filme.findByEstudio", query = "SELECT l FROM Filme l WHERE l.estudio = :estudio"),
    @NamedQuery(name = "Filme.findByGenero", query = "SELECT l FROM Filme l WHERE l.genero = :genero"),
    @NamedQuery(name = "Filme.findByLacamento", query = "SELECT l FROM Filme l WHERE l.lancamento = :lancamento")})
public class Filme implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")    
    private Integer codigo;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "titulo")    
    private String titulo;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "diretor")    
    private String diretor;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "estudio")     
    private String estudio;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "genero")    
    private String genero;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "lancamento")
    private String lancamento;

    public Filme() {
    }

    public Filme(Integer codigo) {
        this.codigo = codigo;
    }

    public Filme(String titulo, String diretor, String estudio, String genero, String lancamento) {        
        this.titulo = titulo;
        this.diretor = diretor;
        this.estudio = estudio;
        this.genero = genero;
        this.lancamento = lancamento;
    }

    public Integer getCodigo() { 
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    public String getEstudio() {
        return estudio;
    }

    public void setEstudio(String estudio) {
        this.estudio = estudio;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getLancamento() {
        return lancamento;
    }

    public void setLancamento(String lancamento) {
        this.lancamento = lancamento;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Filme)) {
            return false;
        }
        Filme other = (Filme) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Filme {" + "codigo=" + codigo + ", titulo=" + titulo + ", diretor=" + diretor + ", estudio=" + estudio + ", genero=" + genero + ", lancamento=" + lancamento + '}';
    }
}
