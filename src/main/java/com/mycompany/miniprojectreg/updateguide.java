/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.miniprojectreg;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Amitabh
 */
@Path("/updateguide")
public class updateguide {
    static String driver;
    static String url;
    static Connection con;
    static Statement stm;
    static ResultSet RS,RS1,RS2;
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{myresource1}/{myresource2}/{myresource3}")

    public String getIt1(@PathParam("myresource1") String pass, @PathParam("myresource2") String newpass, @PathParam("myresource3") String grno) throws SQLException {
        System.out.println(pass + "\t" + newpass + "\t" + grno);
        return connect(pass, newpass, grno);
    }

    public static String connect(String input1, String input2, String input3) throws SQLException {
        String login = "false";
        String str;
        try {

            String url = "jdbc:mysql://localhost:3306/miniproject";
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, "abc", "Project123");
            str = "Database connection established";
            System.out.println(str);
            int i = 0;
            stm = con.createStatement();
            System.out.println(input1 + "\t" + input2);
            String sql = "SELECT * FROM guide where guideusername ='" + input3 + "'";
            RS = stm.executeQuery(sql);
            if (RS.next()) {
                String username = RS.getString("guideusername");
                String password = RS.getString("guidepassword");
                if (password.equals(input1)) {

                    String sql1 = "UPDATE guide "
                            + "SET guidepassword = ? WHERE guideusername = ?";
                    PreparedStatement ps = con.prepareStatement(sql1);
                    ps.setString(1, input2);
                    ps.setString(2, input3);
                    ps.executeUpdate();
                    
                    login=input3;
                    return login;
                    
                } else {
                    System.out.println("Invalid Password!");
                    login = "false";
                    return login;
                }
            }
        } catch (Exception err) {
            System.out.println("ERROR: " + err);
        }
        return login;
    }
    
  
}
