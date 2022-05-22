/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g21.dto;

import g21.entity.Usuario;

/**
 *
 * @author Fernando Jesús Ruano Linares
 */
public class EstudiosDTO {
    private Integer estudioId;
    private String nombre;
    private String query;
    private String titulos;
    private Usuario analistaId;

    public void setEstudioId(Integer estudioId) {
        this.estudioId = estudioId;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public void setTitulos(String titulos) {
        this.titulos = titulos;
    }

    public void setAnalistaId(Usuario analistaId) {
        this.analistaId = analistaId;
    }

    public Integer getEstudioId() {
        return estudioId;
    }

    public String getNombre() {
        return nombre;
    }

    public String getQuery() {
        return query;
    }

    public String getTitulos() {
        return titulos;
    }

    public Usuario getAnalistaId() {
        return analistaId;
    }
    
    public EstudiosDTO() {
        
    }
    
}
