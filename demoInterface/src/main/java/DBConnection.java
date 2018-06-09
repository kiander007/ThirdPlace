import java.sql.*;
import java.util.ArrayList;

public class DBConnection {

    private ResultSet resultSet;

    public DBConnection(){

        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.1.11/ThirdPlace", "root", "root");
            Statement stmt = connection.createStatement();
            resultSet = stmt.executeQuery("SELECT * FROM `TABLE 1`");

        } catch(Exception e) {

            System.out.println(e);

        }
    }

    public ArrayList<String> getUsers(){
        ArrayList<String> users = new ArrayList<>();
        try {
            while(resultSet.next()){
                if(resultSet.getString(15).contains("Twitter")){
                    String end = resultSet.getString(16).substring(resultSet.getString(16).indexOf("twitter.com/")+12);
                    users.add(end.substring(0, (!end.contains(",")) ? end.length() : end.indexOf(",")));
                }
            }
        } catch (SQLException e){
            System.out.println(e);
        }
        return users;
    }
}