package finalmaven;

import java.sql.*;

public class driver {
    public static void main(String[] args) {
        System.out.println("DID IT!");
        createMonthReport cmr = new createMonthReport();
        String[] values = {"Usernamee","September","1991","Rina","4000","08-08-2021","Divyesh","Boi@Rina","1991","Rina","4000","08-08-2021","Divyesh","Boi@Rina"};
        cmr.setLayout(values);
        Conn c = new Conn();
        try{
            ResultSet rs = c.s.executeQuery("select * from reminders");
            while(rs.next()){
                System.out.println(rs.getString(2));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
