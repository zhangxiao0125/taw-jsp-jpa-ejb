package g21.service.marketing;

import g21.dao.ListaFacade;
import g21.dto.marketing.ListaDTO;
import g21.entity.Lista;
import g21.entity.Usuario;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author Xiao
 */
@Stateless
public class ListaService {

    @EJB
    ListaFacade listaFacade;

    public void guardarOEditar(ListaDTO listaDTO) {

        // Asignamos los valores del DTO a un objeto de Lista.
        Lista lista = new Lista();
        lista.setListaId(listaDTO.getId());
        lista.setNombre(listaDTO.getNombre());

        // si el id es invalido, creamos uno nuevo.
        if (lista.getListaId() == null) {
            this.listaFacade.create(lista);
        } else {
            // si el id es valido, editamos la antigua
            this.listaFacade.edit(lista);
        }
    }

    public Lista find(String id) {
        return this.listaFacade.find(Integer.parseInt(id));
    }

    public List<Lista> findAll() {

        return this.listaFacade.findAll();
    }

    public void borrarLista(int parseInt) {
        Lista l = this.listaFacade.find(parseInt);
        this.listaFacade.remove(l);
    }

    public void andirCompradorALista(int compradorId, int listaId) {
        this.listaFacade.andirCompradorALista(compradorId, listaId);
    }

    public void borrarCompradorDesdeLaLista(String compradorId, String listaId) {
        this.listaFacade.borrarCompradorDesdeLaLista(
                Integer.parseInt(compradorId), Integer.parseInt(listaId));
    }

    public List<Lista> filtrarPorNombre(String clave) {
        return this.listaFacade.filtrarPorNombre(clave);
    }
}
