import java.sql.*;
import java.util.ArrayList;

public class DBConnection {

    private ResultSet resultSet;

    public DBConnection(){

        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.1.11/ThirdPlace", "root", "root");
            Statement stmt = connection.createStatement();
            resultSet = stmt.executeQuery("SELECT `Company name`,`Social media profiles` FROM `TABLE 1`WHERE `Social media types` LIKE '%Twitter%';");

        } catch(Exception e) {

            System.out.println(e);

        }
    }

    public ArrayList<Company> getUsers(){
        ArrayList<Company> users = new ArrayList<>();
        try {
            while(resultSet.next()){
                String end = resultSet.getString(1).substring(resultSet.getString(1).indexOf("twitter.com/")+12);
                users.add(new Company(resultSet.getString(0), end.substring(0, (!end.contains(",")) ? end.length() : end.indexOf(","))));
            }
        } catch (SQLException e){
            System.out.println(e);
        }
        return users;
    }
}