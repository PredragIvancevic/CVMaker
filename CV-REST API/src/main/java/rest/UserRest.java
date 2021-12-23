/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import data.Education;
import data.User;
import exception.MyException;
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
import service.UserService;

/**
 *
 * @author predr
 */
@Path("user")
public class UserRest{
     private final UserService userService = UserService.getInstance();
     
    @GET
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUserByUsername(@PathParam("username") String username) throws MyException {
        return userService.findUser(username);
    }
  @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addUser(User user) throws MyException{
            userService.addNewUser(user);
            return Response.ok().build();
    }
    
    @POST 
    @Path("/{education}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addUserEducation(Education education) throws MyException{
            userService.addNewEducation(education);
            return Response.ok().build();
    }
     @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(User user) throws MyException {
            userService.updateUser(user);
            return Response.ok().build();
    }
    
   /* @PUT
    @Path("/{contact}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateContact(User user)throws MyException{
        userService.updateContact(user);
        return Response.ok().build();
    }
    
    
    @PUT
    @Path("/{education}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateEducation(User user) throws MyException {
            userService.updateEducation(user);
            return Response.ok().build();
    }
*/

    
    
     @DELETE
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(@PathParam("username")String username) throws MyException {
            userService.deleteUser(username);
            return Response.ok().build();
    }
}
