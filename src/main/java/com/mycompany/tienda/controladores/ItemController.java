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
    
    @RequestMapping(value = "/item", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void saveItem(@RequestBody Item item){
        //Servicio obtener todos los item
        is.saveItem(item);
    }
    
    @RequestMapping(value = "/item/{itemName}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteItemByName(@PathVariable String itemName){
        //Servicio obtener todos los item
        is.deleteItemByName(itemName);
    }
    
    
}
