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

@Path("/getdata")

public class Getdata {

    static String driver;
    static String url;
    static Connection con;
    static Statement stm;
    static ResultSet RS;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{a}/{b}")
    public String getIt2(@PathParam("a") String div, @PathParam("b") String year) throws SQLException, JSONException {
        System.out.println(div + year);
        return getJSON1(div, year);
    }

    public static String getJSON1(String div, String year) throws JSONException {
        JSONArray array = new JSONArray();
        try {

            url = "jdbc:mysql://localhost:3306/miniproject";
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, "abc", "Project123");
            String str = "Database connection established";
            System.out.println(str);
            if (year.equalsIgnoreCase("SECOND YEAR")) {
                stm = con.createStatement();
                System.out.println(div + year);
                String sql = "SELECT * FROM guide where sdiv1 ='" + div + "' or sdiv2='" + div + "' or sdiv3='" + div + "' or sdiv4='" + div + "'";
                RS = stm.executeQuery(sql);

                while (RS.next()) {
                    String guide = RS.getString("gname");
                    JSONObject j = new JSONObject();
                    j.put("guide", guide);
                    array.put(j);
                }
            } else {
                stm = con.createStatement();
                System.out.println(div + year);
                String sql = "SELECT * FROM guide where tdiv1 ='" + div + "' or tdiv2='" + div + "' or tdiv3='" + div + "'";
                RS = stm.executeQuery(sql);

                while (RS.next()) {
                    String guide = RS.getString("gname");
                    JSONObject j = new JSONObject();
                    j.put("guide", guide);
                    array.put(j);
                }
            }
            System.out.println(array);
            return array.toString();
        } catch (Exception err) {
            System.out.println("ERROR: " + err);

        }
        return null;
    }

}

