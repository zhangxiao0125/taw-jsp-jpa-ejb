/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g21.entity;

import g21.dto.EstudiosDTO;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ferna
 */
@Entity
@Table(name = "ESTUDIOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estudios.findAll", query = "SELECT e FROM Estudios e")
    , @NamedQuery(name = "Estudios.findByEstudioId", query = "SELECT e FROM Estudios e WHERE e.estudioId = :estudioId")
    , @NamedQuery(name = "Estudios.findByNombre", query = "SELECT e FROM Estudios e WHERE e.nombre = :nombre")
    , @NamedQuery(name = "Estudios.findByQuery", query = "SELECT e FROM Estudios e WHERE e.query = :query")
    , @NamedQuery(name = "Estudios.findByTitulos", query = "SELECT e FROM Estudios e WHERE e.titulos = :titulos")})
public class Estudios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ESTUDIO_ID")
    private Integer estudioId;
    @Size(max = 200)
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 500)
    @Column(name = "QUERY")
    private String query;
    @Size(max = 200)
    @Column(name = "TITULOS")
    private String titulos;
    @JoinColumn(name = "ANALISTA_ID", referencedColumnName = "USER_ID")
    @ManyToOne
    private Usuario analistaId;

    public Estudios() {
    }

    public Estudios(Integer estudioId) {
        this.estudioId = estudioId;
    }

    public Integer getEstudioId() {
        return estudioId;
    }

    public void setEstudioId(Integer estudioId) {
        this.estudioId = estudioId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getTitulos() {
        return titulos;
    }

    public void setTitulos(String titulos) {
        this.titulos = titulos;
    }

    public Usuario getAnalistaId() {
        return analistaId;
    }

    public void setAnalistaId(Usuario analistaId) {
        this.analistaId = analistaId;
    }
    
    
    public EstudiosDTO toDTO () {
        EstudiosDTO dto = new EstudiosDTO();
        dto.setAnalistaId(analistaId);
        dto.setEstudioId(estudioId);
        dto.setNombre(nombre);
        dto.setQuery(query);
        dto.setTitulos(titulos);
        
        return dto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (estudioId != null ? estudioId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estudios)) {
            return false;
        }
        Estudios other = (Estudios) object;
        if ((this.estudioId == null && other.estudioId != null) || (this.estudioId != null && !this.estudioId.equals(other.estudioId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "g21.entity.Estudios[ estudioId=" + estudioId + " ]";
    }
    
}
