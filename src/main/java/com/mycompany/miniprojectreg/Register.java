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
import java.util.Calendar;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

@Path("/register")

public class Register {

    ResultSet RS1;
    Statement stm;
    int ctr = 0;

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String reg(String group) throws Exception {
        System.out.println("IN REGISTER");
        String result = "false";
        System.out.println(group);
        JSONObject inputJsonObj = new JSONObject(group);
        String div = (String) inputJsonObj.get("div");
        String year = (String) inputJsonObj.get("year");
        String grno1 = (String) inputJsonObj.get("grno1");
        String grno2 = (String) inputJsonObj.get("grno2");
        String grno3 = (String) inputJsonObj.get("grno3");
        String grno4 = (String) inputJsonObj.get("grno4");
        String grno5 = (String) inputJsonObj.get("grno5");
        String roll1 = (String) inputJsonObj.get("roll1");
        String roll2 = (String) inputJsonObj.get("roll2");
        String roll3 = (String) inputJsonObj.get("roll3");
        String roll4 = (String) inputJsonObj.get("roll4");
        String roll5 = (String) inputJsonObj.get("roll5");
        String name1 = (String) inputJsonObj.get("name1");
        String name2 = (String) inputJsonObj.get("name2");
        String name3 = (String) inputJsonObj.get("name3");
        String name4 = (String) inputJsonObj.get("name4");
        String name5 = (String) inputJsonObj.get("name5");
        String domain1 = (String) inputJsonObj.get("domain1");
        String domain2 = (String) inputJsonObj.get("domain2");
        String domain3 = (String) inputJsonObj.get("domain3");
        try {

            String myDriver = "org.gjt.mm.mysql.Driver";
            String myUrl = "jdbc:mysql://localhost:3306/miniproject";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, "abc", "Project123");
//            String sql="Select *from register where divname='"+div+"' and year='"+year+"'";
//            stm = conn.createStatement();
//            RS1=stm.executeQuery(sql);
//            while(RS1.next())
//            {
//                ctr++;
//            }
//            System.out.println("Ctr="+ctr);
            // the mysql insert statement
            String query = " insert into register (year, divname, grno1,grno2,grno3,grno4,grno5,rollno1,rollno2,rollno3,rollno4,rollno5, name1,name2,name3,name4,name5, domain1,domain2,domain3,domain_allot,guide,title,flag)"
                    + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            //ctr=ctr+1;
            PreparedStatement preparedStmt1 = conn.prepareStatement(query);
            //preparedStmt1.setInt(1, ctr);
            preparedStmt1.setString(1, year);
            preparedStmt1.setString(2, div);
            preparedStmt1.setString(3, grno1);
            preparedStmt1.setString(4, grno2);
            preparedStmt1.setString(5, grno3);
            preparedStmt1.setString(6, grno4);
            if (grno5.equalsIgnoreCase("-")) {
                preparedStmt1.setString(7, "0");
            } else {
                preparedStmt1.setString(7, grno5);
            }
            preparedStmt1.setString(8, roll1);
            preparedStmt1.setString(9, roll2);
            preparedStmt1.setString(10, roll3);
            preparedStmt1.setString(11, roll4);
            if (roll5.equalsIgnoreCase("-")) {
                preparedStmt1.setString(12, "0");
            } else {
                preparedStmt1.setString(12, roll5);
            }
            preparedStmt1.setString(13, name1);
            preparedStmt1.setString(14, name2);
            preparedStmt1.setString(15, name3);
            preparedStmt1.setString(16, name4);
            if (name5.equalsIgnoreCase("-")) {
                preparedStmt1.setString(17, "-");
            } else {
                preparedStmt1.setString(17, name5);
            }
            preparedStmt1.setString(18, domain1);
            preparedStmt1.setString(19, domain2);
            preparedStmt1.setString(20, domain3);
            preparedStmt1.setString(21, "-");
            preparedStmt1.setString(22, "-");
            preparedStmt1.setString(23, "-");
            preparedStmt1.setString(24, "0");
            //execute the preparedstatement
            preparedStmt1.execute();
            System.out.println("In Student table");
            String query1 = "update students2015_16 set Flag=? where GRNo=?";
            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt2 = conn.prepareStatement(query1);
            System.out.println(grno1);
            preparedStmt2.setString(1, "1");
            preparedStmt2.setString(2, grno1);
            preparedStmt2.executeUpdate();
            preparedStmt2.setString(1, "1");
            preparedStmt2.setString(2, grno2);
            preparedStmt2.executeUpdate();
            preparedStmt2.setString(1, "1");
            preparedStmt2.setString(2, grno3);
            preparedStmt2.executeUpdate();
            preparedStmt2.setString(1, "1");
            preparedStmt2.setString(2, grno4);
            preparedStmt2.executeUpdate();
            if (grno5.equalsIgnoreCase("-")) {
            } else {
                preparedStmt2.setString(1, "1");
                preparedStmt2.setString(2, grno5);
            }
            preparedStmt2.executeUpdate();
            String sql3 = "SELECT * FROM students2015_16 where GRNo='" + grno1 + "'";
            stm=conn.createStatement();
            RS1 = stm.executeQuery(sql3);
            if (RS1.next()) {
                result = RS1.getString("DivisionName") + '_' + RS1.getString("RollNo") + '_' + RS1.getString("GRNo") + '_' + RS1.getString("FirstName") + '_' + RS1.getString("MiddleName") + '_' + RS1.getString("SurName") + '_' + RS1.getString("Course") + '_' + RS1.getString("Branch") + '_' + RS1.getString("Class") + '_' + RS1.getString("ModuleName") + '_' + RS1.getString("Gender") + '_' + RS1.getInt("Flag");
                System.out.println(result);
            }
            conn.close();
            return result;
        } catch (Exception err) {
            System.out.println("ERROR: " + err);
        }
        return null;

    }
}
