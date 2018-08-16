/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tienda.controladores;

import com.mycompany.tienda.entities.Item;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.mycompany.tienda.persistencia.ItemService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.SysexMessage;
import javax.sql.rowset.serial.SerialBlob;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;




/**
 *
 * @author fs20165
 */
@RestController
@RequestMapping("/api")
public class ItemController {
    
    ItemService is = new ItemService();
    
    @RequestMapping(value = "/item/{itemName}", method = RequestMethod.GET)
    @ResponseBody
    public Item getItemByName(@PathVariable String itemName){
        //Servicio obtener item por su nombre
        Item it = is.getItemByName(itemName);
        return it;
    }
    
    @RequestMapping(value = "/items", method = RequestMethod.GET)
    @ResponseBody
    public List<Item> getAllItems(){
        //Servicio obtener todos los item
        List<Item> items = is.getAllItems();
        return items;
    }
    
    @RequestMapping(value = "/sold/items", method = RequestMethod.GET)
    @ResponseBody
    public List<Item> getSoldItems(){
        return is.soldItems();
    }
    
    @RequestMapping(value = "/item", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void saveItem(@RequestPart(value ="item")Item item,@RequestPart(value ="file", required = false) MultipartFile request){
        try {
            File imagenP = new File(request.getOriginalFilename());
            imagenP.createNewFile();
            FileOutputStream fos = new FileOutputStream(imagenP);
            fos.write(request.getBytes());
            fos.close();
            is.saveItem(item,imagenP);
            } catch (IOException ex) {
                Logger.getLogger(ItemController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }
    
    @RequestMapping(value = "/item/{itemId}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteItemById(@PathVariable int itemId){
        //Servicio obtener todos los item
        is.deleteItemById(itemId);
    }
    
    @RequestMapping(value = "/item", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void updateItem(@RequestPart(value ="item")Item itemUpdate,@RequestPart(value ="file", required = false) MultipartFile request){
        if(request != null){
            try {
                File imagenUpdate = new File(request.getOriginalFilename());
                imagenUpdate.createNewFile();
                FileOutputStream fos = new FileOutputStream(imagenUpdate);
                fos.write(request.getBytes());
                fos.close();
                byte[] foto = Files.readAllBytes(imagenUpdate.toPath());
                itemUpdate.setFoto(foto);
                is.updateItem(itemUpdate);
            } catch (IOException ex) {
                Logger.getLogger(ItemController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            itemUpdate.setFoto(is.getItemById(itemUpdate.getId()).getFoto());
            is.updateItem(itemUpdate);
        }
    }
    
    
    @RequestMapping(value = "/compra/item", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void buyItem(@RequestBody Item item){
        item.setCantidad(item.getCantidad()-1);
        is.updateItem(item);
        //Servicio agregar en tabla de items comprados
        is.saveCompra(item);
    }
    
    
}
