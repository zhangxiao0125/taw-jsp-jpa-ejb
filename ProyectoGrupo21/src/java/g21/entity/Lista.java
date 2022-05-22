/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g21.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ferna
 */
@Entity
@Table(name = "LISTA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Lista.findAll", query = "SELECT l FROM Lista l")
    , @NamedQuery(name = "Lista.findByListaId", query = "SELECT l FROM Lista l WHERE l.listaId = :listaId")
    , @NamedQuery(name = "Lista.findByNombre", query = "SELECT l FROM Lista l WHERE l.nombre = :nombre")})
public class Lista implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "LISTA_ID")
    private Integer listaId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NOMBRE")
    private String nombre;
    @ManyToMany(mappedBy = "listaList")
    private List<Usuario> usuarioList;

    public Lista() {
    }

    public Lista(Integer listaId) {
        this.listaId = listaId;
    }

    public Lista(Integer listaId, String nombre) {
        this.listaId = listaId;
        this.nombre = nombre;
    }

    public Integer getListaId() {
        return listaId;
    }

    public void setListaId(Integer listaId) {
        this.listaId = listaId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (listaId != null ? listaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lista)) {
            return false;
        }
        Lista other = (Lista) object;
        if ((this.listaId == null && other.listaId != null) || (this.listaId != null && !this.listaId.equals(other.listaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "g21.entity.Lista[ listaId=" + listaId + " ]";
    }
    
}
