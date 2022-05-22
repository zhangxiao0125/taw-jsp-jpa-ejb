/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g21.dao;

import g21.entity.Categoria;
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
public class CategoriaFacade extends AbstractFacade<Categoria> {

    @PersistenceContext(unitName = "ProyectoGrupo21PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CategoriaFacade() {
        super(Categoria.class);
    }
    
    
    public Categoria findbynombre(String nombre)
    {
        Query q;
        q = this.getEntityManager().createNamedQuery("Categoria.findByNombre");
        q.setParameter("nombre", nombre);
        List<Categoria> result = q.getResultList();
        if (result.isEmpty()) {
            return null;
        } else {
            return result.get(0);
        }     
        
    }
    
}
