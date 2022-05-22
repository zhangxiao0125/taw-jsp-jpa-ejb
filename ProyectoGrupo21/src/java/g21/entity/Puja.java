/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g21.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ferna
 */
@Entity
@Table(name = "PUJA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Puja.findAll", query = "SELECT p FROM Puja p")
    , @NamedQuery(name = "Puja.findByPujaId", query = "SELECT p FROM Puja p WHERE p.pujaId = :pujaId")
    , @NamedQuery(name = "Puja.findByCantidad", query = "SELECT p FROM Puja p WHERE p.cantidad = :cantidad")
    , @NamedQuery(name = "Puja.findByFecha", query = "SELECT p FROM Puja p WHERE p.fecha = :fecha")})
public class Puja implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PUJA_ID")
    private Integer pujaId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "CANTIDAD")
    private BigDecimal cantidad;
    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "PRODUCT_ID")
    @ManyToOne
    private Producto productId;
    @JoinColumn(name = "COMPRADOR_ID", referencedColumnName = "USER_ID")
    @ManyToOne
    private Usuario compradorId;

    public Puja() {
    }

    public Puja(Integer pujaId) {
        this.pujaId = pujaId;
    }

    public Integer getPujaId() {
        return pujaId;
    }

    public void setPujaId(Integer pujaId) {
        this.pujaId = pujaId;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Producto getProductId() {
        return productId;
    }

    public void setProductId(Producto productId) {
        this.productId = productId;
    }

    public Usuario getCompradorId() {
        return compradorId;
    }

    public void setCompradorId(Usuario compradorId) {
        this.compradorId = compradorId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pujaId != null ? pujaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Puja)) {
            return false;
        }
        Puja other = (Puja) object;
        if ((this.pujaId == null && other.pujaId != null) || (this.pujaId != null && !this.pujaId.equals(other.pujaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "g21.entity.Puja[ pujaId=" + pujaId + " ]";
    }
    
}
