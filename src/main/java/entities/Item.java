/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Blob;

/**
 *
 * @author fs20165
 */
public class Item {
    
    private String nombre;
    private String descripcion;
    private int valor;
    private float iva;
    private String envio;
    private Blob foto;
    
    public Item(){}
    
    public Item(String nombre,String descripcion,int valor,float iva,String envio,Blob foto){
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.valor = valor;
        this.iva = iva;
        this.envio = envio;
        this.foto = foto;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the valor
     */
    public int getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(int valor) {
        this.valor = valor;
    }

    /**
     * @return the iva
     */
    public float getIva() {
        return iva;
    }

    /**
     * @param iva the iva to set
     */
    public void setIva(float iva) {
        this.iva = iva;
    }

    /**
     * @return the envio
     */
    public String getEnvio() {
        return envio;
    }

    /**
     * @param envio the envio to set
     */
    public void setEnvio(String envio) {
        this.envio = envio;
    }

    /**
     * @return the foto
     */
    public Blob getFoto() {
        return foto;
    }

    /**
     * @param foto the foto to set
     */
    public void setFoto(Blob foto) {
        this.foto = foto;
    }
    
    
    
}