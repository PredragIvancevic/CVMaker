/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import data.Company;
import exception.MyException;
import java.util.List;
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
import service.CompanyService;

/**
 *
 * @author predr
 */

@Path("experience")
public class CompanyRest {
    private final CompanyService companyService=CompanyService.getInstance();
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addCompany(Company company)throws MyException{
        companyService.addNewCompany(company);
        return Response.ok().build();
        
    }
    
    @GET
    @Path("/{experienceId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Company findCompanyById(@PathParam("experienceId") int companyId) throws MyException{
        return companyService.findCompany(companyId);
    }
    
     @GET
    @Path("/users/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Company>findCompaniesByUsername(@PathParam("username")String username)throws MyException{
        return companyService.findAllCompanies(username);
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCompany(Company company)throws MyException{
        companyService.updateCompany(company);
        return Response.ok().build();
    }
    
    @DELETE
    @Path("/{experienceId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(@PathParam("experienceId")int experienceId) throws MyException {
            companyService.deleteCompany(experienceId);
            return Response.ok().build();
    }
    
  
    
    
    
}
