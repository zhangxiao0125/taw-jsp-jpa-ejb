/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g21.dao;

import g21.entity.Estudios;
import g21.entity.Usuario;
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
public class EstudiosFacade extends AbstractFacade<Estudios> {

    @PersistenceContext(unitName = "ProyectoGrupo21PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstudiosFacade() {
        super(Estudios.class);
    }
    
    
    public List<Estudios> FindByUser(Usuario usuario) {
        Query q = this.getEntityManager().createQuery("SELECT e FROM Estudios e WHERE e.analistaId = :analista");
        q.setParameter("analista", usuario);
        
        return q.getResultList();
    }

    public String CheckQuery(String strq) {
        String respuesta;
        String keyword = strq.split(" ")[0].toUpperCase();
        
        if(!"SELECT".equals(keyword)) {
            respuesta = "ERROR: Solo puedes usar sentencias del tipo SELECT";
        } else {
            respuesta = "Query valida";
        }
        
        try {
            Query q = this.getEntityManager().createQuery(strq);
        } catch (Exception e) {
            respuesta = e.getMessage();
        }
        
        return respuesta;
    }
    
    public List QueryOnDemand(String strq) {
        Query q = this.getEntityManager().createQuery(strq);
        List list = q.getResultList();
        
        //mirar si la lista de datos esta hecha de arrays, o de cualquier cosa que no es un array
        //si no esta hecha de arrays, pues lo que pasamos es un clon de esa lista con todo metido en un array
        
        if(!list.isEmpty()) {
            if(!list.get(0).getClass().isArray()) {
                //convertir esto en un array
                for (int i=0;i<list.size();i++) {
                    Object[] arrayo = {list.get(i)};
                    list.set(i, arrayo);
                }
            }
        }
        
        return list;
    }
}
