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

@Path("/getgroups1")

public class Getgroups1 {

    static String driver;
    static String url;
    static Connection con;
    static Statement stm;
    static ResultSet RS;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{a}")
    public String getIt1(@PathParam("a") String guide){
        System.out.println(guide);
        return getJSON2(guide);
    }
    
    public String getJSON2(String input1){
        JSONArray array = new JSONArray();
        try {

            url = "jdbc:mysql://localhost:3306/miniproject";
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, "abc", "Project123");
            String str = "Database connection established";
            System.out.println(str);
            stm = con.createStatement();
            System.out.println(input1);
            String sql = "SELECT * FROM register where guide ='" + input1 + "'";
            RS = stm.executeQuery(sql);

            while (RS.next()) {
                String grpid = RS.getString("grpid");
                String grno1 = RS.getString("grno1");
                String grno2 = RS.getString("grno2");
                String grno3 = RS.getString("grno3");
                String grno4 = RS.getString("grno4");
                String grno5 = RS.getString("grno5");
                String rollno1 = RS.getString("rollno1");
                String rollno2 = RS.getString("rollno2");
                String rollno3 = RS.getString("rollno3");
                String rollno4 = RS.getString("rollno4");
                String rollno5 = RS.getString("rollno5");
                String name1 = RS.getString("name1");
                String name2 = RS.getString("name2");
                String name3 = RS.getString("name3");
                String name4 = RS.getString("name4");
                String name5 = RS.getString("name5");
                String domain1 = RS.getString("domain1");
                String domain2 = RS.getString("domain2");
                String domain3 = RS.getString("domain3");
                String domain_allot=RS.getString("domain_allot");
                String guide=RS.getString("guide");
                String title=RS.getString("title");
                JSONObject j = new JSONObject();
                j.put("grpid", grpid);
                j.put("grno1", grno1);
                j.put("grno2", grno2);
                j.put("grno3", grno3);
                j.put("grno4", grno4);
                j.put("grno5", grno5);
                j.put("rollno1", rollno1);
                j.put("rollno2", rollno2);
                j.put("rollno3", rollno3);
                j.put("rollno4", rollno4);
                j.put("rollno5", rollno5);
                j.put("name1", name1);
                j.put("name2", name2);
                j.put("name3", name3);
                j.put("name4", name4);
                j.put("name5", name5);
                j.put("domain1", domain1);
                j.put("domain2", domain2);
                j.put("domain3", domain3);
                j.put("domain_allot",domain_allot);
                j.put("guide",guide);
                j.put("title",title);
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
    
