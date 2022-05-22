/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g21.service;

import g21.dao.EstudiosFacade;
import g21.dto.EstudiosDTO;
import g21.entity.Estudios;
import g21.entity.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Fernando Jesús Ruano Linares
 */
@Stateless
public class EstudiosService {
    @EJB EstudiosFacade ef;
    
    public String CheckQuery(String strq) {
        return ef.CheckQuery(strq);
    }
    
    public List QueryOnDemand(String strq) {
        return ef.QueryOnDemand(strq);
    }
    
    public EstudiosDTO buscarEstudio (Integer id) {
        Estudios estudio = this.ef.find(id);
        return estudio.toDTO();
    }
    
    public void borrarEstudio (Integer id) {
        Estudios estudio = this.ef.find(id);

        this.ef.remove(estudio);        
    }
    
    private List<EstudiosDTO> listaEntityADTO (List<Estudios> lista) {
        List<EstudiosDTO> listaDTO = null;
        if (lista != null) {
            listaDTO = new ArrayList<>();
            for (Estudios estudio:lista) {
                listaDTO.add(estudio.toDTO());
            }
        }
        return listaDTO;
    }
    
    public List<EstudiosDTO> FindByUser(Usuario usuario) {
        return this.listaEntityADTO(this.ef.FindByUser(usuario));
    }
    
    private void rellenarEstudio (Estudios estudio, Usuario analistaId,
                                    String nombre, String query, String titulos) {
        
        estudio.setAnalistaId(analistaId);
        estudio.setNombre(nombre);
        estudio.setQuery(query);
        estudio.setTitulos(titulos);             
    }
    
    public Estudios crearEstudio (Usuario analistaId,
                                    String nombre, String query, String titulos) {
        Estudios nuevoestudio = new Estudios();

        this.rellenarEstudio(nuevoestudio, analistaId, nombre, query, titulos);

        this.ef.create(nuevoestudio);
        
        return nuevoestudio;
    }

    public void modificarEstudio (Integer id, Usuario analistaId,
                                    String nombre, String query, String titulos) {
        
        Estudios estudio = this.ef.find(id);

        this.rellenarEstudio(estudio, analistaId, nombre, query, titulos);

        this.ef.edit(estudio);
    }
    
}
