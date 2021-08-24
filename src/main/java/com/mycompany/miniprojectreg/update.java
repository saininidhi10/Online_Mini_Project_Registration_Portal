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
@Path("/update")

public class update {

    static String driver;
    static String url;
    static Connection con;
    static Statement stm;
    static PreparedStatement preparedStmt;
    static ResultSet RS;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{myresource1}/{myresource2}/{myresource3}")

    public String getIt1(@PathParam("myresource1") String domain, @PathParam("myresource2") String guide, @PathParam("myresource3") String grpid) throws SQLException {
        System.out.println(domain + "\t" + guide + "\t" + grpid);
        return insert(domain, guide, grpid);
    }

    public static String insert(String input1, String input2, String input3) throws SQLException {
        String result = "Failed";
        String str;
        String grpid1 = null, grpid2 = null, grpid3 = null;
        try {

            String url = "jdbc:mysql://localhost:3306/miniproject";
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, "abc", "Project123");
            str = "Database connection established";
            System.out.println(str);
            stm = con.createStatement();
            System.out.println(input1 + "\t" + input2 + "\t" + input3);
            String query1 = "Select *from guide where gname='" + input2 + "'";
            RS = stm.executeQuery(query1);
            if (RS.next()) {
                grpid1 = RS.getString("grpid1");
                grpid2 = RS.getString("grpid2");
                grpid3 = RS.getString("grpid3");
            }
            if (grpid1.equals("0") || grpid2.equals("0") || grpid3.equals("0")) {
                String query = "update register set domain_allot = ? , guide=? ,flag='1' where grpid = ?";
                PreparedStatement preparedStmt = con.prepareStatement(query);
                preparedStmt.setString(1, input1);
                preparedStmt.setString(2, input2);
                preparedStmt.setString(3, input3);
              
                // execute the java preparedstatement
                preparedStmt.executeUpdate();
                String query3="select *from register where grpid='"+input3+"'";
                RS=stm.executeQuery(query3);
                int grno1=0,grno2=0,grno3=0,grno4=0,grno5=0;
                if(RS.next())
                {
                    grno1=RS.getInt("grno1");
                    grno2=RS.getInt("grno2");
                    grno3=RS.getInt("grno3");
                    grno4=RS.getInt("grno4");
                    grno5=RS.getInt("grno5");
                }
                String query4="update students2015_16 set flag='2' where GRNo=?";
                preparedStmt = con.prepareStatement(query4);
                preparedStmt.setInt(1, grno1);
                preparedStmt.executeUpdate();
                preparedStmt.setInt(1, grno2);
                preparedStmt.executeUpdate();
                preparedStmt.setInt(1, grno3);
                preparedStmt.executeUpdate();
                preparedStmt.setInt(1, grno4);
                preparedStmt.executeUpdate();
                if(grno5!=0)
                {
                preparedStmt.setInt(1, grno5);
                preparedStmt.executeUpdate();
                }
                if(grpid1.equals("0"))
                {
                   String query2="update guide set grpid1='"+input3+"' where gname='"+input2+"'";
                   stm.executeUpdate(query2);
                }
                else if(grpid2.equals("0"))
                {
                   String query2="update guide set grpid2='"+input3+"' where gname='"+input2+"'";
                   stm.executeUpdate(query2);
                }
                else
                {
                   String query2="update guide set grpid3='"+input3+"' where gname='"+input2+"'";
                   stm.executeUpdate(query2);
                }
                result = "Success";
                return result;

            }
            else{
                return "No. of projects of Guide exeeded!";
            }

        } catch (Exception err) {
            result = "Failed";
            System.out.println("ERROR: " + err);
            return result;
        }
    }
}
