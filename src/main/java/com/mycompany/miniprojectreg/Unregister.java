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

@Path("/unreg")

public class Unregister {

    static String driver;
    static String url;
    static Connection con;
    static Statement stm;
    static ResultSet RS;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{a}/{b}")
    public String getIt2(@PathParam("a") String div, @PathParam("b") String year) throws SQLException, JSONException {
        System.out.println(div);
        System.out.println(year);
        return getJSON1(year,div);
    }

    public static String getJSON1(String year,String div) throws JSONException {
        JSONArray array = new JSONArray();
        try {

            url = "jdbc:mysql://localhost:3306/miniproject";
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, "abc", "Project123");
            String str = "Database connection established";
            System.out.println(str);
           
            stm = con.createStatement();
            System.out.println( year+" "+ div);
            
            String sql = "SELECT * FROM students2015_16 where Class ='" + year +  "' and DivisionName='" + div + "' and Flag = '0'";      
            RS = stm.executeQuery(sql);

            while (RS.next()) {
                
                String Name = RS.getString("FirstName") + " " + RS.getString("MiddleName") + " " + RS.getString("SurName");
                String GrNo = RS.getString("GrNo");
                String Rollno = RS.getString("RollNo");
                String Mail = RS.getString("Mail");
                JSONObject j = new JSONObject();
                j.put("name", Name);
                j.put("roll", Rollno);
                j.put("grno", GrNo);
                j.put("mail", Mail);
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
