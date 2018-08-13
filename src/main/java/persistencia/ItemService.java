/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import entities.Item;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author germa_000
 */
public class ItemService {
    
    ConnectionPostgres cn;
    Connection conex;
    
    public ItemService() throws ClassNotFoundException, IllegalAccessException, SQLException{
        cn = new ConnectionPostgres();
        conex = cn.connect();
    }
    
    public void saveItem(Item item){
        String sql = "insert into items (nombre,descripcion,valor,iva,envio,foto) values(?,?,?,?,?,?)";
        try {
            PreparedStatement ps = conex.prepareStatement(sql);
            ps.setString(1, item.getNombre());
            ps.setString(2, item.getDescripcion());
            ps.setInt(3, item.getValor());
            ps.setFloat(4, item.getIva());
            ps.setString(5, item.getEnvio());
            ps.setBlob(6, item.getFoto());
            ps.executeUpdate();
            System.out.println("Guardo el item");
        } catch (SQLException ex) {
            Logger.getLogger(ItemService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Item getItemByName(String itemName){
        String sql = "select * from items where nombre = ? ";
        Item it = new Item();
        try {
            PreparedStatement ps = conex.prepareStatement(sql);
            ps.setString(1, itemName);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                it.setNombre(rs.getString(1));
                it.setDescripcion(rs.getString(2));
                it.setValor(rs.getInt(3));
                it.setIva(rs.getFloat(4));
                it.setEnvio(rs.getString(5));
                it.setFoto(rs.getBlob(6));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ItemService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return it;
    }
    
    public List<Item> getAllItems(){
        List<Item> items = new ArrayList<>();
        String sql = "select * from items";
        try {
            PreparedStatement ps = conex.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Item it = new Item();
                it.setNombre(rs.getString(1));
                it.setDescripcion(rs.getString(2));
                it.setValor(rs.getInt(3));
                it.setIva(rs.getFloat(4));
                it.setEnvio(rs.getString(5));
                it.setFoto(rs.getBlob(6));
                items.add(it);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ItemService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return items;
    }
    
    public void deleteItemByName(String itemName){
        String sql = "delete from items where nombre = ?";
        try {
            PreparedStatement ps = conex.prepareStatement(sql);
            ps.setString(1, itemName);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ItemService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
