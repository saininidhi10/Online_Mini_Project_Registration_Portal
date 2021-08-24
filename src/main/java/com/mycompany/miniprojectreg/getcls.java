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

@Path("/getcls")

public class getcls {

    static String driver;
    static String url;
    static Connection con;
    static Statement stm;
    static ResultSet RS;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{a}/{b}")
    public String getIt2(@PathParam("a") String year, @PathParam("b") String class1) throws SQLException, JSONException {
        System.out.println(year + class1);
        return getJSON1(year,class1);
    }

    public static String getJSON1(String year, String class1) throws JSONException {
        JSONArray array = new JSONArray();
        try {

            url = "jdbc:mysql://localhost:3306/miniproject";
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, "abc", "Project123");
            String str = "Database connection established";
            System.out.println(str);
            stm = con.createStatement();
            System.out.println(year + class1);
            String sql = "SELECT * FROM title2015 where year ='" + year + "' and class='" + class1 + "'";
            RS = stm.executeQuery(sql);
            int i=0;
            while (RS.next()) {
                i++;
                String title = RS.getString("projectTitle");
                JSONObject j=new JSONObject();
                j.put("srno",i);
                j.put("title",title);
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
