/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.miniprojectreg;

/**
 *
 * @author Amitabh
 */
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

@Path("/userauth")
public class userauth {
    static String username="null";
    static String pass="null";
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{myresource1}")
    public static String getit(@PathParam("myresource1") String param)
    {
        return check();
    }
    public static String check()
    {
        System.out.println("in check");
        System.out.println(username+"\t"+pass);
        if(username.equalsIgnoreCase("null") || pass.equalsIgnoreCase("null"))
        {
            return "False";
        }
        else
        {
            return "True";
        }
    }
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{myresource1}/{myresource2}")
    public static String getit(@PathParam("myresource1") String user, @PathParam("myresource2") String password)
    {
        username=user;
        pass=password;
        return "Complete";
       
    }
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{myresource1}/{myresource2}/{myresource3}")
    public static String getit(@PathParam("myresource1") String user, @PathParam("myresource2") String password,@PathParam("myresource3") String dummy)
    {
        username="null";
        pass="null";
        System.out.println(username+"\t"+pass);
        return "Logout"; 
    }
}

