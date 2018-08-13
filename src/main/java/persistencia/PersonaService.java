/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import entities.Persona;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author germa_000
 */
public class PersonaService {
    
    ConnectionPostgres cn;
    Connection conex;
    
    public PersonaService() throws ClassNotFoundException, IllegalAccessException{
        cn = new ConnectionPostgres();
        conex = cn.connect();
    }
    
    public void savePersona(Persona persona){
        String sql = "insert into usuarios (nombre,correo,usuario,pw) values(?,?,?,?)";
        PreparedStatement ps;
        try {
            ps = conex.prepareStatement(sql);
            ps.setString(1, persona.getNombre());
            ps.setString(2, persona.getCorreo());
            ps.setString(3, persona.getUsuario());
            ps.setString(4, persona.getPw());
            ps.executeUpdate();
            System.out.println("Registró al usuario");
        } catch (SQLException ex) {
            Logger.getLogger(PersonaService.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    
    
    
}
