/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g21.service.marketing;

import g21.dao.ListaFacade;
import g21.dao.NotificacionFacade;
import g21.dao.UsuarioFacade;
import g21.dto.marketing.UsuarioDTO;
import g21.entity.Lista;
import g21.entity.Notificacion;
import g21.entity.Usuario;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Xiao
 */
@Stateless
public class NotificacionService {

    @EJB
    ListaFacade listaFacade;
    @EJB
    UsuarioFacade usuarioFacade;
    @EJB
    NotificacionFacade notificacionFacade;

    public void enviarNotificacionParaLista(String idLista, UsuarioDTO usuario, String notificacion) {
        Lista l = this.listaFacade.find(Integer.parseInt(idLista));
        Usuario marketingUsuario = this.usuarioFacade.find(usuario.getId());

        this.notificacionFacade.enviarNotificacionParaLista(l, marketingUsuario, notificacion);
    }

    public List<Notificacion> getTodasNotificaciones(String id) {
        return this.notificacionFacade.getTodasNotificaciones(Integer.parseInt(id));
    }
}
