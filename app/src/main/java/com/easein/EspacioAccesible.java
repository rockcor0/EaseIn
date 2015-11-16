package com.easein;

/**
 * Created by Ricardo on 19/11/2015.
 */
public class EspacioAccesible {

    private String tipoEspacio;
    private String descripcion;
    private int posicion;
    private String imagen;

    public EspacioAccesible(int posicion, String tipoEspacio, String descripcion, String imagen) {
        this.tipoEspacio = tipoEspacio;
        this.descripcion = descripcion;
        this.posicion = posicion;
        this.imagen = imagen;
    }

    public String getTipoEspacio() {
        return tipoEspacio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getPosicion() {
        return posicion;
    }

    public String getImagen() {
        return imagen;
    }


}
