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

@Path("/getdiv")

public class AllotDiv {

    static String driver;
    static String url;
    static Connection con;
    static Statement stm;
    static ResultSet RS;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{a}")
    public String getIt2(@PathParam("a") String year) throws SQLException, JSONException {
        System.out.println(year);
        return getJSON1(year);
    }

    public static String getJSON1(String year) throws JSONException {
        JSONArray array = new JSONArray();
        try {

            url = "jdbc:mysql://localhost:3306/miniproject";
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, "abc", "Project123");
            String str = "Database connection established";
            System.out.println(str);

            stm = con.createStatement();
            System.out.println(year);
            String sql = "SELECT * FROM division where year ='" + year + "'";      
            RS = stm.executeQuery(sql);

            while (RS.next()) {
                String div = RS.getString("div");
                String mod=RS.getString("mod");
                JSONObject j = new JSONObject();
                j.put("div",div);
                j.put("mod",mod);
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
