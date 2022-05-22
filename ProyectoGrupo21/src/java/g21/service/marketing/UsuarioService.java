/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g21.service.marketing;

import g21.dao.ListaFacade;
import g21.dao.UsuarioFacade;
import g21.entity.Lista;
import g21.entity.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author Xiao
 */
@Stateless
public class UsuarioService {

    @EJB
    ListaFacade listaFacade;
    @EJB
    UsuarioFacade usuarioFacade;

    public List<Usuario> getCompradoresDisponibles(Integer idLista) {
        Lista l = this.listaFacade.find(idLista);
        return this.usuarioFacade.getCompradoresDisponibles(l);
    }

    public List<Usuario> getCompradores() {
        return this.usuarioFacade.getCompradores();
    }
    public Usuario find(String id){
        return this.usuarioFacade.find(Integer.parseInt(id));
    }
}
