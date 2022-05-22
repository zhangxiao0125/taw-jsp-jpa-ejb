package g21.service.marketing;

import g21.dao.ListaFacade;
import g21.dto.marketing.ListaDTO;
import g21.entity.Lista;
import javax.ejb.EJB;
import javax.ejb.Stateless;

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
}
