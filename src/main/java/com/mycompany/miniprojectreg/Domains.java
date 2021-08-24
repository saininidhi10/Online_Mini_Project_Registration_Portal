/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.miniprojectreg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

@Path("/mydata1")

public class Domains {

    static String driver;
    static String url;
    static Connection con;
    static Statement stm;
    static ResultSet RS;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{a}")
    public String getIt2(@PathParam("a") String mod) throws SQLException, JSONException {
        System.out.println(mod);
        return getJSON1(mod);
    }

    public static String getJSON1(String mod) throws JSONException {
        JSONArray array = new JSONArray();
        try {

            url = "jdbc:mysql://localhost:3306/miniproject";
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, "abc", "Project123");
            String str = "Database connection established";
            System.out.println(str);

            stm = con.createStatement();
            System.out.println(mod);
            String sql = "SELECT * FROM modules where modname ='" + mod + "'";      
            RS = stm.executeQuery(sql);

            while (RS.next()) {
                String sub = RS.getString("sub");
                JSONObject j = new JSONObject();
                j.put("sub", sub);
                array.put(j);
            }
            System.out.println(array);
            return array.toString();
        } catch (Exception err) {
            System.out.println("ERROR: " + err);
        }
        return null;

    }
}
