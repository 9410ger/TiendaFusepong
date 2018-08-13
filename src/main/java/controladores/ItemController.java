/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import entities.Item;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;




/**
 *
 * @author fs20165
 */
@RestController
@RequestMapping("/api")
public class ItemController {
    
    @RequestMapping(value = "/item/{itemName}", method = RequestMethod.GET)
    @ResponseBody
    public Item getItemByName(@PathVariable String itemName){
        //Servicio obtener item
        return null;
    }
    
    @RequestMapping(value = "/items", method = RequestMethod.GET)
    @ResponseBody
    public List<Item> getAllItems(){
        //Servicio obtener todos los item
        return null;
    }
    
    @RequestMapping(value = "/item", method = RequestMethod.POST)
    @ResponseBody
    public void saveItem(Item item){
        //Servicio obtener todos los item
    }
    
    @RequestMapping(value = "/item/{itemName}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteItemByName(String itemName){
        //Servicio obtener todos los item
    }
    
    
}
