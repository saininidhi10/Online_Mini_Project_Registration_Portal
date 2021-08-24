package com.mycompany.miniprojectreg;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import java.sql.*;
import java.util.Calendar;
import java.util.Date;

/**
 * Example resource class hosted at the URI path "/myresource"
 */
@Path("/titlecheck")

public class titlecheck {

    static String driver;
    static String url;
    static Connection con;
    static Statement stm;
    static ResultSet RS, RS1;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{myresource1}/{myresource2}")

    public String getIt2(@PathParam("myresource1") String title, @PathParam("myresource2") String grno) throws SQLException {
        System.out.println(title + grno);
        return conn(title, grno);
    }

    public static String conn(String input1, String input2) throws SQLException {
        String reg = "false";
        String str;
        try {

            String url = "jdbc:mysql://localhost:3306/miniproject";
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, "abc", "Project123");
            str = "Database connection established";
            System.out.println(str);
            stm = con.createStatement();
            System.out.println(input1);
            String sql = "SELECT * FROM title2015 where projectTitle ='" + input1 + "'";
            RS = stm.executeQuery(sql);
            String title = "null";
            while (RS.next()) {
                title = RS.getString("projectTitle");
            }
            if (title == "null") {
                System.out.println("Title Registered Successfully.");
                
                String query1 = "update register set title='" + input1 + "' where grno1='" + input2 + "' or grno2='" + input2 + "' or grno3='" + input2 + "' or grno4='" + input2 + "' or grno5='" + input2 + "'";
                stm.executeUpdate(query1);
                String query2 = "SELECT * FROM register where grno1='" + input2 + "' or grno2='" + input2 + "' or grno3='" + input2 + "' or grno4='" + input2 + "' or grno5='" + input2 + "'";
                RS1 = stm.executeQuery(query2);
                String grno1 = "null", grno2 = "null", grno3 = "null", grno4 = "null", grno5 = "null";
                if (RS1.next()) {
                    grno1 = RS1.getString("grno1");
                    grno2 = RS1.getString("grno2");
                    grno3 = RS1.getString("grno3");
                    grno4 = RS1.getString("grno4");
                    grno5 = RS1.getString("grno5");
                } else {
                    return "Updation failed";
                }
                String query3 = "update students2015_16 set Flag='3' where GRNo=?";
                PreparedStatement preparedStmt = con.prepareStatement(query3);
                preparedStmt.setString(1, grno1);
                preparedStmt.executeUpdate();
                preparedStmt.setString(1, grno2);
                preparedStmt.executeUpdate();
                preparedStmt.setString(1, grno3);
                preparedStmt.executeUpdate();
                preparedStmt.setString(1, grno4);
                preparedStmt.executeUpdate();
                preparedStmt.setString(1, grno5);
                preparedStmt.executeUpdate();

                reg = "Title Registeration Complete";
                String sql3 = "SELECT * FROM students2015_16 where GRNo='" + input2 + "'";
                String classname=null;
                stm = con.createStatement();
                RS1 = stm.executeQuery(sql3);
                if (RS1.next()) {
                    classname=RS1.getString("Class");
                    reg = RS1.getString("DivisionName") + '_' + RS1.getString("RollNo") + '_' + RS1.getString("GRNo") + '_' + RS1.getString("FirstName") + '_' + RS1.getString("MiddleName") + '_' + RS1.getString("SurName") + '_' + RS1.getString("Course") + '_' + RS1.getString("Branch") + '_' + RS1.getString("Class") + '_' + RS1.getString("ModuleName") + '_' + RS1.getString("Gender") + '_' + RS1.getInt("Flag");
                    System.out.println(reg);
                }
                Date date = null; // your date
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                int year = cal.get(Calendar.YEAR);
                String query = "insert into title2015(projectTitle,year,class)" + "values('" + input1 + "','"+year+"','"+classname+"')";   //"//"update register set title='"+input1+"' where grno1='"+input2+"' or grno2='"+input2+"' or grno3='"+input2+"' or grno4='"+input2+"' or grno5='"+input2+"'";
                stm.execute(query);
                con.close();
                return reg;

            } else {
                System.out.println("Same title is already registered!");
                reg = "Same title is already registered!";
                con.close();
                return reg;
            }

        } catch (Exception err) {
            System.out.println("ERROR: " + err);
        }
        return reg;
    }

}
