/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g21.dao;

import g21.entity.Lista;
import g21.entity.Usuario;
import java.util.ArrayList;
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
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @EJB
    ListaFacade listaFacade;

    @PersistenceContext(unitName = "ProyectoGrupo21PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    public Usuario findbyuserid(int userid) {
        Query q;
        q = this.getEntityManager().createNamedQuery("Usuario.findByUserId");
        q.setParameter("userId", userid);

        List<Usuario> result = q.getResultList();
        if (result.isEmpty()) {
            return null;
        } else {
            return result.get(0);
        }
    }

    public Usuario findbyemail(String email) {
        Query q;
        q = this.getEntityManager().createNamedQuery("Usuario.findByEmail");
        q.setParameter("email", email);

        List<Usuario> result = q.getResultList();
        if (result.isEmpty()) {
            return null;
        } else {
            return result.get(0);
        }
    }

    public List<Usuario> filtroComprador(Integer idLista) {

        Query q;
        q = this.getEntityManager().createQuery("select u from Usuario u "
                + "left join u.listaList lista "
                + "where (u.rolId.rolId = 1) and :id = lista.listaId");
        q.setParameter("id", idLista);
        return q.getResultList();
    }

    public List<Usuario> getCompradoresDisponibles(Lista l) {
        List<Integer> idsL = new ArrayList<>();

        l.getUsuarioList().forEach((u) -> {
            idsL.add(u.getUserId());
        });
        if (idsL.size() > 0) {
            Query q;
            q = this.getEntityManager().createQuery("SELECT u from Usuario u where u.rolId.rolId = 1 and u.userId NOT IN :compradoresLista ");
            q.setParameter("compradoresLista", idsL);

            return q.getResultList();
        } else {
            return this.getCompradores();
        }

    }

    public List<Usuario> getCompradores() {
        Query q;
        q = this.getEntityManager().createQuery("SELECT u from Usuario u where u.rolId.rolId = 1");
        return q.getResultList();
    }

}
