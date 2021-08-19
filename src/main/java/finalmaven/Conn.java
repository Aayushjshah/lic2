package finalmaven;
import java.sql.*;

public class Conn {
    Connection c;
    Statement s;
    Conn(){
        try{
            c=DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/lic","root","root");

            s=c.createStatement();

        }catch(Exception e){
            System.out.println("Error in db connection");
            e.printStackTrace();
        }
            
    }
}
