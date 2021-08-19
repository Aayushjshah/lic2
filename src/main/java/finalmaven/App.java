package finalmaven;
import javax.swing.*;
import javax.swing.border.Border;
import java.sql.*;
import java.awt.Color;
import java.awt.Image;
import java.awt.Font;
import java.awt.event.*;
public class App extends JFrame implements ActionListener{
    
    JButton b1 , b2 , bl1, signUp ;
    JLabel l1,l2 ,newUser ;
    JTextField t1;
    JPasswordField t2;
    App(){

        ImageIcon i1 = new ImageIcon(this.getClass().getResource("/icons/ocean.jpg"));
        Image i2 = i1.getImage().getScaledInstance(380,440,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel li = new JLabel(i3);
        li.setBounds(10,10,380,440);
        add(li);

        
        //main Heading
        JLabel head = new JLabel("<HTML><U>Pashyati</U></HTML>");
        head.setBounds(500,40,250,46);
        // head.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        head.setForeground(Color.BLACK);
        head.setFont(new Font("Serif" , Font.ITALIC , 36));
        add(head);
        Font forLabel = new Font("Tahoma" , Font.ITALIC , 22 );
        // Login
        b1 = new JButton("<html><u>Log In</u></html>");
        b1.setBounds(420,250,120,30);
        b1.setFont(forLabel);
        b1.addActionListener(this);
        b1.setForeground(Color.WHITE);
        b1.setBackground(Color.BLACK);
        add(b1);
        
        b2 = new JButton("<html><u>Register</u></html>");
        b2.setBounds(570,250,120,30);
        b2.setFont(forLabel);
        b2.addActionListener(this);
        b2.setForeground(Color.WHITE);
        b2.setBackground(Color.BLACK);
        add(b2);


        //textFields 
        t1 = new JTextField();
        t1.setFont(forLabel);
        t1.setBounds(550 ,170 ,150,30);
        t1.setVisible(false);
        add(t1);

        
        t2 = new JPasswordField();
        t2.setFont(forLabel);
        t2.setBounds(550 ,220 ,150,30);
        t2.setVisible(false);
        add(t2);

        //login page fields
        l1 = new JLabel("ID:");
        l1.setBounds(420 , 170 , 120, 30);
        l1.setFont(forLabel);
        l1.setVisible(false);
        add(l1);

        l2 = new JLabel("Password:");
        l2.setBounds(420 , 220 , 120, 30);
        l2.setFont(forLabel);
        l2.setVisible(false);
        add(l2);

        bl1 = new JButton("<html><u>SignIn</u></html>");
        bl1.setBounds(490,300,120,30);
        bl1.setFont(forLabel);
        bl1.addActionListener(this);
        bl1.setForeground(Color.WHITE);
        bl1.setBackground(Color.BLACK);
        bl1.setVisible(false);
        add(bl1);


        newUser = new JLabel("New User?");
        newUser.setBounds(480,400,100,20);
        newUser.setFont(new Font("Serif", Font.PLAIN , 17));
        newUser.setVisible(false);
        add(newUser);

        signUp = new JButton("<html><u>SignUp</u></html>");
        signUp.setBounds(555,400,60,25);
        signUp.setFont(new Font("Serif" , Font.ITALIC , 19));
        Border emptyBorder = BorderFactory.createEmptyBorder();
        signUp.setBorder(emptyBorder);
        signUp.setForeground(Color.BLUE);
        signUp.setBackground(Color.WHITE);
        signUp.setVisible(false);
        signUp.addActionListener(this);
        add(signUp);



        //mainPane
        setBounds(400,170,780,500);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        JButton temp = (JButton) ae.getSource();
        if( temp == b1){
            
            b1.setVisible(false);
            b2.setVisible(false);
            l1.setVisible(true);
            l2.setVisible(true);
            t1.setVisible(true);
            t2.setVisible(true);
            bl1.setVisible(true);
            newUser.setVisible(true);
            signUp.setVisible(true);

        }else if(temp == b2){
            //b2
            this.setVisible(false);
            new Register().setVisible(true);

        }else if(temp == bl1){
            //logining in button
            String tId = t1.getText();
            String tPassword = new String(t2.getPassword());

            Conn c = new Conn();
            Hash h = new Hash();
            String hashedPassword = h.doHashing(tPassword);
            // System.out.println(hashedPassword);
            // String query = "insert into login values('"+ tId+"' , '"+hashedPassword+"')";
            String query = "select * from login where id='"+tId+"' and password='"+hashedPassword+"'";
            try{
                
                
                ResultSet rs = c.s.executeQuery(query);
                String bu ="Welcome ";
                while(rs.next()){
                    bu+=rs.getString(1);
                    JOptionPane.showMessageDialog(null , bu);
                }
                
            }catch(Exception e){
                e.printStackTrace();
            }


             
        }else if(temp == signUp){
            //same as b2
            this.setVisible(false);
            new Register().setVisible(true);
        }

    }

    public static void main(String[] args) throws Exception {
        new App().setVisible(true);
    }
}
