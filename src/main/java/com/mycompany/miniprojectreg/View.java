package com.mycompany.miniprojectreg;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import java.sql.*;

/**
 * Example resource class hosted at the URI path "/myresource"
 */
@Path("/View")

public class View {

    static String driver;
    static String url;
    static Connection con;
    static Statement stm;
    static ResultSet RS;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{myresource1}")

    public String getIt1(@PathParam("myresource1") String grno) throws SQLException {
        System.out.println(grno);
        return connect(grno);
    }

    public static String connect(String input1) throws SQLException {
        String view = "false";
        String str;
        try {

            String url = "jdbc:mysql://localhost:3306/miniproject";
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, "abc", "Project123");
            str = "Database connection established";
            System.out.println(str);
            stm = con.createStatement();
            System.out.println(input1);
            String sql = "SELECT * FROM register where grno1='" + input1 + "' or grno2='" + input1 + "' or grno3='" + input1 + "' or grno4='" + input1 + "' or grno5='" + input1 + "'";
            RS = stm.executeQuery(sql);
            while (RS.next()) {
                view = RS.getString("grpid") + '_' + RS.getString("year") + '_' + RS.getString("divname") + '_' + RS.getString("grno1") + '_' + RS.getString("grno2") + '_' + RS.getString("grno3") + '_' + RS.getString("grno4") + '_' + RS.getString("grno5") + '_' + RS.getInt("rollno1") + '_' + RS.getInt("rollno2") + '_' + RS.getInt("rollno3") + '_' + RS.getInt("rollno4") + '_' + RS.getInt("rollno5") + '_' + RS.getString("name1") + '_' + RS.getString("name2") + '_' + RS.getString("name3") + '_' + RS.getString("name4") + '_' + RS.getString("name5") + '_' + RS.getString("domain_allot") + '_' + RS.getString("guide") + '_' + RS.getString("title");
                System.out.println(view);
                return view;

            }
        } catch (Exception err) {
            System.out.println("ERROR: " + err);
        }
        return view;
    }

}