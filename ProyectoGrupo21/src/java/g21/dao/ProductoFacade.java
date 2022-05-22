/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g21.dao;

import g21.entity.Producto;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author ferna
 */
@Stateless
public class ProductoFacade extends AbstractFacade<Producto> {

    @PersistenceContext(unitName = "ProyectoGrupo21PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductoFacade() {
        super(Producto.class);
    }
    
    
    public Producto findbyid(int id)
    {
        Query q;
        q = this.getEntityManager().createNamedQuery("Producto.findByProductId");
        q.setParameter("productId", id);
        
        List<Producto> result = q.getResultList();
        if(result.isEmpty())
        {
            return null;
        }
        else
        {
            return result.get(0);
        }
    }
    
    public List<Producto> findsearch(String search)
    {
        Query q;
        q = this.getEntityManager().createQuery("SELECT p FROM Producto p WHERE p.titulo LIKE :search");
        q.setParameter("search", '%' + search + '%');
        List<Producto> result = q.getResultList();
        return result;
        
    }
    
}
