/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.edri.servicio;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import mx.com.edri.data.PersonaDao;
import mx.com.edri.domain.Persona;

/**
 *
 * @author MI05332
 */
@Stateless
@Path("/personas")
public class PersonaServiceRS 
{
    @Inject
    private PersonaDao personadao;
    
    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public List<Persona> listarPersonas()
    {
        List<Persona> personas = personadao.encontrarTodasPersonas();
        System.out.println("personas necopntradas : " + personas);
        
        return personas;
    }
    
    
    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    @Path("{id}") //hace referencia al path sifuiente: /personas/{id}
    public Persona encontrarPersona(@PathParam("id")int id)
    {
        Persona persona = personadao.encontrarPersona(new Persona(id));
        System.out.println("persona encontrada: " + persona);
        
        return persona;
    }
    
    @POST
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON)
    public Persona agregarPersona(Persona persona)
    {
        personadao.insertarPersona(persona);
        System.out.println("persona agregada: " + persona);
        
        return persona;
    }
    
    @PUT 
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response modificarPersona(@PathParam("id")int id, Persona personaModificada)
    {
        Persona persona = personadao.encontrarPersona(new Persona(id));
        
        
        if(persona != null)
        {
            personadao.actualizarPersona(personaModificada);
            System.out.println("persona modificada: " + personaModificada);
            
            return Response.ok().entity(personaModificada).build();
        }
        else
        {
            return Response.status(Response.Status.NOT_FOUND).build();
        }       
    }
    
    @DELETE
    @Produces(value = MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response eliminarPersona(@PathParam("id")int id)
    {
        personadao.eliminarPersona(new Persona(id));
        System.out.println("persona eliminada: " + id);
        
        return Response.ok().build();
    }
    
}
