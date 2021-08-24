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

@Path("/myresource")

public class MyResource {

    static String driver;
    static String url;
    static Connection con;
    static Statement stm;
    static ResultSet RS,RS1,RS2;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{myresource1}/{myresource2}")

    public String getIt1(@PathParam("myresource1") String username, @PathParam("myresource2") String password) throws SQLException {
        System.out.println(username + "\t" + password);
        return connect(username, password);
    }

    public static String connect(String input1, String input2) throws SQLException {
        String login = "false";
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
            String sql = "SELECT * FROM login where username ='" + input1 + "' and Password ='" + input2 + "'";     // where Username = '"+str1+"' and Password = '"+str2+"' "; 
          String sql2 = "SELECT * FROM guide where guideusername ='" + input1 + "' and guidepassword ='" + input2 + "'";
            RS = stm.executeQuery(sql);

            while (RS.next()) {
                String Username = RS.getString("Username");
                String Password = RS.getString("Password");
                 if (Username.equals("12345") & Password.equals(input2)){
                        System.out.println("ADMIN");
                        login = "admin";
                        return login;
                
                }
                else if (Username.equals(input1) & Password.equals(input2)) {

                    System.out.println("Logged In Successfully !");
                   String sql1 = "SELECT * FROM students2015_16 where GRNo='" + input1 + "'";
                    RS1= stm.executeQuery(sql1);
                    if(RS1.next())
                    {
                        System.out.println(RS1.getString("DivisionName") + RS1.getString("RollNo") + RS1.getString("GRNo") + RS1.getString("FirstName") + RS1.getString("MiddleName") + RS1.getString("SurName") + RS1.getString("Course") + RS1.getString("Branch") + RS1.getString("Class") + RS1.getString("ModuleName") + RS1.getString("Gender")+RS1.getInt("Flag"));
                        login= RS1.getString("DivisionName") +'_'+ RS1.getString("RollNo")+'_'+ RS1.getString("GRNo") +'_'+ RS1.getString("FirstName") + '_'+RS1.getString("MiddleName") + '_'+RS1.getString("SurName") +'_'+ RS1.getString("Course") +'_'+ RS1.getString("Branch") +'_'+ RS1.getString("Class") +'_'+ RS1.getString("ModuleName") +'_'+ RS1.getString("Gender")+'_'+RS1.getInt("Flag");
                    }
                    return login;
                }else {
                    System.out.println("Invalid Username Or Password!");
                    login = "false";
                }
            }
            
           RS2= stm.executeQuery(sql2);         
           while (RS2.next()) 
           {
               String Username = RS2.getString("guideusername");
               String Password = RS2.getString("guidepassword");
               System.out.println("username" +Username +"   " + Password);
               System.out.println("inputs " + input1 + "   " + input2);
              if (Username.equals(input1) & Password.equals(input2)) {

                    System.out.println("Logged In Successfully as guide !");
                    login="guide";
                    /*String sql3 = "SELECT * FROM guide where guideusername='" + input1 + "'";
                    RS2= stm.executeQuery(sql3);
                   if(RS2.next())
                    {
//                        System.out.println(RS2.getString("gname") + RS2.getString("sdiv1") + RS2.getString("sdiv2") + RS2.getString("sdiv3") + RS2.getString("sdiv4") + RS2.getString("tdiv1") + RS2.getString("tdiv2") + RS2.getString("tdiv3"));
//                        login= RS2.getString("gname") +'_'+ RS2.getString("sdiv1")+'_'+ RS2.getString("sdiv2") +'_'+ RS2.getString("sdiv3") + '_'+RS2.getString("sdiv4") + '_'+RS2.getString("tdiv1") +'_'+ RS2.getString("tdiv2") +'_'+ RS2.getString("tdiv3");
                          login="guide";
                    }
                    */
                    return login;
                }else {
                    System.out.println("Invalid Username Or Password!");
                    login = "false";
                } 
           }
        } catch (Exception err) {
            System.out.println("ERROR: " + err);
        }
        return login;
    }
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
            String sql = "SELECT * FROM login where username ='" + input3 + "'";
            RS = stm.executeQuery(sql);
            if (RS.next()) {
                String username = RS.getString("username");
                String password = RS.getString("password");
                if (password.equals(input1)) {

                    String sql1 = "UPDATE login "
                            + "SET password = ? WHERE username = ?";
                    PreparedStatement ps = con.prepareStatement(sql1);
                    ps.setString(1, input2);
                    ps.setString(2, input3);
                    ps.executeUpdate();
                    if(input1.equalsIgnoreCase("12345")){
                        login="true";
                        return login;
                    }
                    else
                    {
                    String sql3 = "SELECT * FROM students2015_16 where GRNo='" + input1 + "'";
                    RS1 = stm.executeQuery(sql3);
                    if (RS1.next()) {
                        login = RS1.getString("DivisionName") + '_' + RS1.getString("RollNo") + '_' + RS1.getString("GRNo") + '_' + RS1.getString("FirstName") + '_' + RS1.getString("MiddleName") + '_' + RS1.getString("SurName") + '_' + RS1.getString("Course") + '_' + RS1.getString("Branch") + '_' + RS1.getString("Class") + '_' + RS1.getString("ModuleName") + '_' + RS1.getString("Gender") + '_' + RS1.getInt("Flag");
                        System.out.println(login);
                    }
                    return login;
                    }
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
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{a}")
        public String print()
        {
            return "Insufficient Data";
        }

}
