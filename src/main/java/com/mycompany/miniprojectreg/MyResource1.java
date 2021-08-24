package com.mycompany.miniprojectreg;

import java.sql.*;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

@Path("/mydata")

public class MyResource1 {

    static String driver;
    static String url;
    static Connection con;
    static Statement stm;
    static ResultSet RS;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{myresource1}/{myresource2}")

    public String getIt1(@PathParam("myresource1") String username, @PathParam("myresource2") String password) throws SQLException, JSONException {
        System.out.println(username + "\t" + password);
        return getJSON(username, password);
    }

    public static String getJSON(String in1, String in2) throws JSONException {
        JSONArray array = new JSONArray();
        try {

            url = "jdbc:mysql://localhost:3306/miniproject";
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, "abc", "Project123");
            String str = "Database connection established";
            System.out.println(str);

            stm = con.createStatement();
            System.out.println(in1 + "\t" + in2);
            String sql = "SELECT * FROM students2015_16 where DivisionName ='" + in1 + "' and Class ='" + in2 + "' and Flag='0'";     // where Username = '"+str1+"' and Password = '"+str2+"' "; 
            RS = stm.executeQuery(sql);

            while (RS.next()) {
                String Name = RS.getString("FirstName") + " " + RS.getString("MiddleName") + " " + RS.getString("SurName");
                String GrNo = RS.getString("GrNo");
                String Rollno = RS.getString("RollNo");
                JSONObject j = new JSONObject();
                j.put("name", Name);
                j.put("roll", Rollno);
                j.put("grno", GrNo);
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
