package ca.unmined.api;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;

@RestController
public class UserController {
    @GetMapping("/users")
    public String getUsers() {
        System.out.println("/users was called");

        ArrayList<User> users = getUserListFromQuery("SELECT * FROM users");

        StringBuilder out = new StringBuilder();

        assert users != null;
        for (int i = 0; i < users.size(); i++) {
            if (i == 0) out.append("[");

            int id = users.get(i).getId();
            String name = users.get(i).getName();
            String info = users.get(i).getInfo();

            if (i == users.size() - 1) out.append(userToJson(id, name, info, "]"));
            else out.append(userToJson(id, name, info, ",\n"));
        }

        System.out.println(out.toString());
        return out.toString();
    }

    @GetMapping("/users/{id_}")
    public String getUser(@PathVariable String id_) {
        System.out.println("/test/{id} was called");

        User r = Objects.requireNonNull(getUserListFromQuery("SELECT id, name, info FROM users WHERE id = " + id_)).get(0);
        return userToJson(r.getId(), r.getName(), r.getInfo(), "");
    }

    @PostMapping("/users")
    public String addUser(@RequestBody String data) throws ParseException {
        if (!data.contains("id")) return "Invalid adding of user: does not contain \"id\"";
        if (!data.contains("name")) return "Invalid adding of user: does not contain \"name\"";
        if (!data.contains("info")) return "Invalid adding of user: does not contain \"info\"";

        User u = UserFromString(data);

        String sql = "INSERT INTO site_database.users (name, info) VALUES ('"
                + u.getName() + "', '" + u.getInfo() + "')";

        sendQuery(sql);

        return "";
    }

    @DeleteMapping("/users/{id}")
    public String removeUser(@PathVariable("id") String id_) throws SQLException {
        sendQuery("DELETE from site_database.users where id = " + id_);
        fixIndices();
        return "";
    }

    private User UserFromString(String in) throws ParseException {
        JSONObject obj = JSONObjectFromString(in);

        return new User(Integer.parseInt((String) obj.get("id")),
                (String) obj.get("name"),
                (String) obj.get("info")
        );
    }
    private JSONObject JSONObjectFromString(String in) throws ParseException {
        JSONParser parser = new JSONParser();
        return (JSONObject) parser.parse(in);
    }

    private ArrayList<User> getUserListFromQuery(String sql) {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            String url = "jdbc:mysql://10.0.0.139:3306/site_database?useSSL=false";
            Connection conn = DriverManager.getConnection(url, "root", "Rcal20050914%");


            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            ArrayList<User> out = new ArrayList<>();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String info = rs.getString("info");

                out.add(new User(id, name, info));
            }

            return out;
        } catch (IllegalAccessException | InstantiationException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
    private void sendQuery(String sql) {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            String url = "jdbc:mysql://10.0.0.139:3306/site_database?useSSL=false";
            Connection conn = DriverManager.getConnection(url, "root", "Rcal20050914%");

            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);

        } catch (IllegalAccessException | InstantiationException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void sendQuery(String sql, boolean exec) {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            String url = "jdbc:mysql://10.0.0.139:3306/site_database?useSSL=false";
            Connection conn = DriverManager.getConnection(url, "root", "Rcal20050914%");

            Statement stmt = conn.createStatement();
            stmt.execute(sql);

        } catch (IllegalAccessException | InstantiationException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private int getQuery(String sql) {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            String url = "jdbc:mysql://10.0.0.139:3306/site_database?useSSL=false";
            Connection conn = DriverManager.getConnection(url, "root", "Rcal20050914%");


            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            rs.next();
            return rs.getInt(1);
        } catch (IllegalAccessException | InstantiationException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return 1;
        }
    }

    private String userToJson(int id, String name, String info, String after) {
        return "{" +
                "\"id\": \"" + id + "\"," +
                "\"name\": \"" + name + "\"," +
                "\"info\": \"" + info + "\"" +
                "}" + after;
    }

    private void fixIndices() {
        int len = getQuery("SELECT COUNT(*) FROM site_database.users");

        System.out.println(len);
        sendQuery("ALTER TABLE site_database.users AUTO_INCREMENT = " + (len + 1), false);
    }
}
