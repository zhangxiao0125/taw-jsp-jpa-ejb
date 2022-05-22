/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g21.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ferna
 */
@Entity
@Table(name = "PRODUCTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto p")
    , @NamedQuery(name = "Producto.findByProductId", query = "SELECT p FROM Producto p WHERE p.productId = :productId")
    , @NamedQuery(name = "Producto.findByTitulo", query = "SELECT p FROM Producto p WHERE p.titulo = :titulo")
    , @NamedQuery(name = "Producto.findByDescripcion", query = "SELECT p FROM Producto p WHERE p.descripcion = :descripcion")
    , @NamedQuery(name = "Producto.findByUrlFoto", query = "SELECT p FROM Producto p WHERE p.urlFoto = :urlFoto")
    , @NamedQuery(name = "Producto.findByPrecio", query = "SELECT p FROM Producto p WHERE p.precio = :precio")
    , @NamedQuery(name = "Producto.findByComienzoPuja", query = "SELECT p FROM Producto p WHERE p.comienzoPuja = :comienzoPuja")
    , @NamedQuery(name = "Producto.findByFinalPuja", query = "SELECT p FROM Producto p WHERE p.finalPuja = :finalPuja")})
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PRODUCT_ID")
    private Integer productId;
    @Size(max = 50)
    @Column(name = "TITULO")
    private String titulo;
    @Size(max = 200)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Size(max = 200)
    @Column(name = "URL_FOTO")
    private String urlFoto;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PRECIO")
    private BigDecimal precio;
    @Column(name = "COMIENZO_PUJA")
    @Temporal(TemporalType.DATE)
    private Date comienzoPuja;
    @Column(name = "FINAL_PUJA")
    @Temporal(TemporalType.DATE)
    private Date finalPuja;
    @ManyToMany(mappedBy = "productoList")
    private List<Usuario> usuarioList;
    @JoinColumn(name = "CAT_ID", referencedColumnName = "CAT_ID")
    @ManyToOne
    private Categoria catId;
    @JoinColumn(name = "VENDEDOR_ID", referencedColumnName = "USER_ID")
    @ManyToOne
    private Usuario vendedorId;
    @OneToMany(mappedBy = "productId")
    private List<Puja> pujaList;

    public Producto() {
    }

    public Producto(Integer productId) {
        this.productId = productId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Date getComienzoPuja() {
        return comienzoPuja;
    }

    public void setComienzoPuja(Date comienzoPuja) {
        this.comienzoPuja = comienzoPuja;
    }

    public Date getFinalPuja() {
        return finalPuja;
    }

    public void setFinalPuja(Date finalPuja) {
        this.finalPuja = finalPuja;
    }

    @XmlTransient
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    public Categoria getCatId() {
        return catId;
    }

    public void setCatId(Categoria catId) {
        this.catId = catId;
    }

    public Usuario getVendedorId() {
        return vendedorId;
    }

    public void setVendedorId(Usuario vendedorId) {
        this.vendedorId = vendedorId;
    }

    @XmlTransient
    public List<Puja> getPujaList() {
        return pujaList;
    }

    public void setPujaList(List<Puja> pujaList) {
        this.pujaList = pujaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productId != null ? productId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Producto)) {
            return false;
        }
        Producto other = (Producto) object;
        if ((this.productId == null && other.productId != null) || (this.productId != null && !this.productId.equals(other.productId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "g21.entity.Producto[ productId=" + productId + " ]";
    }
    
}
