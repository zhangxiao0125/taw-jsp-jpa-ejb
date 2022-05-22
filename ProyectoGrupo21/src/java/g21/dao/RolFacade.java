/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g21.dao;

import g21.entity.Rol;
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
public class RolFacade extends AbstractFacade<Rol> {

    @PersistenceContext(unitName = "ProyectoGrupo21PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RolFacade() {
        super(Rol.class);
    }
    
    
    public Rol findbynombre(String rol) {
        Query q;
        q = this.getEntityManager().createQuery("SELECT r FROM Rol r WHERE r.nombre like :nombre");
        q.setParameter("nombre", '%' + rol + '%');
        List<Rol> result = q.getResultList();
        
        if (result == null || result.isEmpty()) {
            return null;
        } else {
            return result.get(0);
        }        
    }
    
}
