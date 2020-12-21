package ca.unmined.api;

import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.*;

@RestController
public class UserController {
    @PostMapping("/test")
    public String Test(@RequestBody String o) {
        System.out.println(o);
        return o;
    }

//    @GetMapping("/test")
    public String test() throws URISyntaxException, IOException {
        System.out.println("/test was called");
        BufferedReader reader = new BufferedReader(new FileReader(
                "C:\\Users\\chris\\Documents\\Misc programs\\Test Programs\\JavaServer\\src\\main\\java\\ca\\unmined\\api\\data.json"));
        StringBuilder out = new StringBuilder();
        String l;

        while ((l = reader.readLine()) != null) {
            out.append(l);
        }
        return out.toString();
    }

    @GetMapping("/users")
    public String getUsers() {
        System.out.println("/users was called");
        try {
            ResultSet rs = getRSFromQuery("SELECT * FROM users");
            assert rs != null;

            StringBuilder out = new StringBuilder();

            while (rs.next()) {
                if (rs.first()) {
                    out.append("[");
                }

                int id = rs.getInt("id");
                String name = rs.getString("name");
                String info = rs.getString("info");

                if (rs.last()) {
                    out.append(userToJson(id, name, info, "}]"));
                }
                else {
                    out.append(userToJson(id, name, info, ",\n"));
                }
            }

            System.out.println(out.toString());
            return out.toString();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "nope";
    }

    @GetMapping("/users/{id_}")
    public String getUsers(@PathVariable String id_) {
        System.out.println("/test/{id} was called");

        try {
            ResultSet rs = getRSFromQuery("SELECT id, name, info FROM users WHERE id = " + id_);
            assert rs != null;

            int id = 0;
            String name = "";
            String info = "";

            while (rs.next()) {
                id = rs.getInt("id");
                name = rs.getString("name");
                info = rs.getString("info");
            }

            return userToJson(id, name, info, "");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private ResultSet getRSFromQuery(String sql) {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            String url = "jdbc:mysql://10.0.0.139:3306/site_database?useSSL=false";
            Connection conn = DriverManager.getConnection(url, "root", "Rcal20050914%");


            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            return rs;
        } catch (IllegalAccessException | InstantiationException | SQLException | ClassNotFoundException e) {

            return null;
        }
    }

    private String userToJson(int id, String name, String info, String after) {

        return "{" +
                "\"id\": \"" + id + "\"," +
                "\"name\": \"" + name + "\"," +
                "\"info\": \"" + info + "\"" +
                "}" + after;
    }
}
