/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tienda;

import entities.Item;
import entities.Persona;
import java.sql.SQLException;
import persistencia.ItemService;
import persistencia.PersonaService;

/**
 *
 * @author fs20165
 */
public class TiendaMain {
    
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, SQLException{
        ItemService it = new ItemService();
        PersonaService ps = new PersonaService();
        Persona p1 = new Persona("Persona1","persona1@fusepong.org","pers1","persona1");
        ps.savePersona(p1);
        
    }
    
}
