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

@Path("/mod")

public class mod {

    static String driver;
    static String url;
    static Connection con;
    static Statement stm;
    static ResultSet RS,RS1;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{myresource1}/{myresource2}")

    public String getIt1(@PathParam("myresource1") String grpid, @PathParam("myresource2") String guide) throws SQLException {
        System.out.println(grpid + "\t" + guide);
        return connect(grpid, guide);
    }

    public static String connect(String input1, String input2) throws SQLException {
        String result= "failed";
        String str;
        try {
            
            String url = "jdbc:mysql://localhost:3306/miniproject";
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, "abc", "Project123");
            str = "Database connection established";
            System.out.println(str);
            int i=0;
            stm = con.createStatement();
            System.out.println(input1 + "\t" + input2);
            String sql1="Update register set guide='-',domain_allot='-',flag='0' where grpid='"+input1+"'";
            stm.executeUpdate(sql1);
            System.out.println("Register table altered!");
            String sql = "Select * from guide where (grpid1='"+input1+"' or grpid2='"+input1+"' or grpid3='"+input1+"')and gname='"+input2+"'";     // where Username = '"+str1+"' and Password = '"+str2+"' "; 
            RS = stm.executeQuery(sql);
            String grpid1="null",grpid2="null",grpid3="null";
            if (RS.next()) {
                grpid1 = RS.getString("grpid1");
                grpid2 = RS.getString("grpid2");
                grpid3 = RS.getString("grpid3");
            }
            if(grpid1.equalsIgnoreCase(input1)){
                String query="update guide set grpid1='0' where gname='"+input2+"'";
                stm.executeUpdate(query);
                System.out.println(result);
                result="Success";
                return result;
            }
            else if(grpid2.equalsIgnoreCase(input1)){
                String query="update guide set grpid2='0' where gname='"+input2+"'";
                stm.executeUpdate(query);
                System.out.println(result);
                result="Success";
                return result;
            }
            else if(grpid3.equalsIgnoreCase(input1)){
                String query="update guide set grpid3='0' where gname='"+input2+"'";
                stm.executeUpdate(query);
                result="Success";
                System.out.println(result);
                return result;
            }
            else{
                return result;
            }
        } catch (Exception err) {
            System.out.println("ERROR: " + err);
            return result;
        }
        
    }
}