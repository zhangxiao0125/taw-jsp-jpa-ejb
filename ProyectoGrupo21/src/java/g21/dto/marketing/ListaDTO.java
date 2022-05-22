/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g21.dto.marketing;

/**
 *
 * @author Xiao
 */
public class ListaDTO {

    private Integer id = null;
    private String nombre = null;

    public ListaDTO(String id, String nombre) {
        if (id != null && id.length() > 0) {
            this.id = Integer.parseInt(id);
        }
        this.nombre = nombre;
    }

    public ListaDTO(String nombre) {
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setId(String id) {
        this.id = Integer.parseInt(id);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
