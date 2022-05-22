/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g21.dao;

import g21.entity.Lista;
import g21.entity.Usuario;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ferna
 */
@Stateless
public class ListaFacade extends AbstractFacade<Lista> {

    @EJB UsuarioFacade usuarioFacade;
    
    @PersistenceContext(unitName = "ProyectoGrupo21PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ListaFacade() {
        super(Lista.class);
    }
    
    
        public void borrarLista(int parseInt) {
        Lista l = this.find(parseInt);
        this.em.remove(l);
    }

    private boolean exists(int compradorId, int listaId) {

        boolean result = false;
        Lista l = this.find(listaId);

        for (Usuario usuario : l.getUsuarioList()) {
            if (usuario.getUserId() == compradorId) {
                result = true;
            }
        }

        return result;
    }

    public void andirCompradorALista(int compradorId, int listaId) {
        if (this.exists(compradorId, listaId) == false) {
            Usuario u = this.usuarioFacade.find(compradorId);
            Lista l = this.find(listaId);

            l.getUsuarioList().add(u);
            this.edit(l);

            u.getListaList().add(l);
            this.usuarioFacade.edit(u);
            
            this.em.persist(l);

        }
    }

    public void borrarCompradorDesdeLaLista(int compradorId, int listaId) {
        Usuario u = this.usuarioFacade.find(compradorId);
        Lista l = this.find(listaId);

        l.getUsuarioList().remove(u);
        this.edit(l);

        u.getListaList().remove(l);
        this.usuarioFacade.edit(u);
        this.em.persist(l);
    }
    
    
}
