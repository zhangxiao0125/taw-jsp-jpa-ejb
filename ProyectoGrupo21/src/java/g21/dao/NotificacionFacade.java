/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g21.dao;

import g21.entity.Lista;
import g21.entity.Notificacion;
import g21.entity.Usuario;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author ferna
 */
@Stateless
public class NotificacionFacade extends AbstractFacade<Notificacion> {

    @EJB ListaFacade listaFacade;
    @EJB UsuarioFacade usuarioFacade;
    
    @PersistenceContext(unitName = "ProyectoGrupo21PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NotificacionFacade() {
        super(Notificacion.class);
    }
    
    
    public List<Notificacion> getTodasNotificaciones(int id) {
        Query q;
        q = this.getEntityManager().createQuery("SELECT n FROM Notificacion n WHERE n.receptor.userId = :id");
        q.setParameter("id", id);
        return q.getResultList();
    }
    
    public void enviarNotificacionParaLista(int idLista, String notificacion) {
        Lista l = this.listaFacade.find(idLista);
        Usuario marketingUsuario = this.usuarioFacade.find(4);
        for (Usuario usuario : l.getUsuarioList()) {
            Notificacion n = new Notificacion();
            n.setFecha(new Date());
            n.setMensaje(notificacion);
            n.setReceptor(usuario);
            n.setMensajero(marketingUsuario);
            //usuario.getNotificacionList().add(n);
            this.em.persist(n);
        }
        
    }
    
}
