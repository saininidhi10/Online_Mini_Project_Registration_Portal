package com.mycompany.miniprojectreg;

import static com.mycompany.miniprojectreg.MyResource.con;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Example resource class hosted at the URI path "/myresource"
 */
@Path("/getguide")

public class Getguide {

    static String driver;
    static String url;
    static Connection con;
    static Statement stm;
    static ResultSet RS, RS1;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{myresource1}")

    public String getIt1(@PathParam("myresource1") String id) throws SQLException {
        System.out.println(id);
        return connect(id);
    }

    public static String connect(String input1) throws SQLException {
        try {
            String login = "false";
            String str;
            String url = "jdbc:mysql://localhost:3306/miniproject";
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, "abc", "Project123");
            str = "Database connection established";
            System.out.println(str);
            int i = 0;
            stm = con.createStatement();
            String sql1 = "SELECT * FROM guide where id='" + input1 + "'";
            RS1 = stm.executeQuery(sql1);
            if (RS1.next()) {
                System.out.println(RS1.getString("gname"));
                login = RS1.getString("gname");
            }

            return login;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Getguide.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
