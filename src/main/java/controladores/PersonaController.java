/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import entities.Persona;
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
public class PersonaController {
    
    @RequestMapping(value = "/persona", method = RequestMethod.POST)
    @ResponseBody
    public void savePersona(Persona persona){
        //Servicio obtener todos los item
    }
    
}
