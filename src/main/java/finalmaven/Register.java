package finalmaven;
import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.*;
import java.sql.ResultSet;
// import java.sql.*;

public class Register extends JFrame implements ActionListener,FocusListener,KeyListener{
    
    JLabel li;
    JLabel[] notify = new JLabel[3];
    JLabel[] arr = new JLabel[7];
    String[] labels = {"Name of the Head" , "Relation" , "Date Of Birth" , "Total Members" ,"SetUp LoginId",
                        "Set Password" , "Confirm Password"};
    JButton back,signUp,addMember;
    // JTextField[] tarr = new JTextField[5];
    PlaceholderTextField[] tarr = new PlaceholderTextField[5];
    JPasswordField[] parr = new JPasswordField[2];
    // Store temp member values
    String[][] tempStorage;
    String[] tempMainStorage = new String[4];
    
  //members  
    JLabel[] memJLab = new JLabel[4];
    String[] memLab = {"Name" , "Relation" , "D.O.B."};
    PlaceholderTextField[] tarrMem = new PlaceholderTextField[3];
    JLabel head2 ;
    int memCounter=2;
    int numMem;
    int t=0;
    String headStr;
//end
    JComboBox<Integer> c1;
    Font forLabel = new Font("Tahoma" , Font.ITALIC , 17 );
    Font headFont = new Font("Serif" , Font.ITALIC , 32);
    int i;
    
    
    Register(){

        
        JLabel head = new JLabel("<html><u>Register</u></html>");
        // head.setFont(new Font("Serif" , Font.ITALIC , 32));
        head.setFont(headFont);
        head.setBounds(360,5,200,40);
        add(head);

         
        //connect db
        

        
        int x=60;
        for(i=0;i<7;i++){
            arr[i]= new JLabel(labels[i]);
            arr[i].setFont(forLabel);
            if(i==3){
                Integer[] iArr = {1,2,3,4};
                c1 = new JComboBox<Integer>(iArr);
                c1.setBounds(175,x,150,30);
                c1.setBackground(Color.WHITE);
                c1.addActionListener(this);
                c1.setFont(forLabel);
                add(c1);

                arr[i].setBounds(20,x,150,30);
                add(arr[i]);        
                x+=50;
            }else{
                arr[i].setBounds(20,x,150,30);
                add(arr[i]);
                if(i<5){
                // tarr[i] = new JTextField();
                tarr[i] = new PlaceholderTextField();
                tarr[i].setBounds(175,x,150,30);
                tarr[i].setFont(forLabel);
                add(tarr[i]);
                x+=50;
                }else{
                    
                    x-=20;
                    notify[i-5] = new JLabel("username available :)");
                    notify[i-5].setBounds(10,x,300,12);
                    notify[i-5].setForeground(new Color(0, 153, 51));

                    add(notify[i-5]);
                    notify[i-5].setVisible(false);
                    x+=20;
                    
                    parr[i-5] = new JPasswordField();
                parr[i-5].setBounds(175,x,150,30);
                parr[i-5].addFocusListener(this);
                parr[i-5].setFont(forLabel);
                add(parr[i-5]);
                x+=50;
                }
            }
        }
            x-=20;
            notify[2] = new JLabel("username available :)");
            notify[2].setBounds(10,x,300,12);
            notify[2].setForeground(new Color(0, 153, 51));

            add(notify[2]);
            notify[2].setVisible(false);
            x+=30;

        tarr[1].setText("HEAD");
        tarr[1].setEnabled(false);
        tarr[2].setPlaceholder("yyyy-mm-dd");
        tarr[4].addFocusListener(this);
        parr[1].addKeyListener(this);
            


        //SignUp button
        back = new JButton("<html><u>Back</u></html>");
        back.setFont(new Font("Serif" , Font.ITALIC , 21));
        back.setForeground(Color.WHITE);
        back.setBackground(Color.BLACK);
        back.setBounds(40,x,100,30);
        back.addActionListener(this);
        add(back);

        signUp = new JButton("<html><u>signUp</u></html>");
        signUp.setFont(new Font("Serif" , Font.ITALIC , 21));
        signUp.setForeground(Color.WHITE);
        signUp.setBackground(Color.BLACK);
        signUp.setBounds(170,x,120,30);
        signUp.addActionListener(this);
        add(signUp);

//form for new member
  
//sub heading 
        
        head2 = new JLabel("AA");
        // head2.setText("AAY");
        head2.setBounds(500,100,200,28);
        head2.setFont(new Font("Serif" , Font.ITALIC , 26));
        add(head2);   
        

        int y=140;
        for(i=0;i<3;i++){
            memJLab[i] = new JLabel(memLab[i]);
            memJLab[i].setFont(forLabel);
            memJLab[i].setBounds(400,y,100,30);
            tarrMem[i] = new PlaceholderTextField();
            tarrMem[i].setBounds(505,y,150,30);
            tarrMem[i].setFont(forLabel);
            y+=50;
            add(tarrMem[i]);
            add(memJLab[i]);
        }
        tarrMem[2].setPlaceholder("yyyy-mm-dd");
        //add member button

        addMember = new JButton("<html><u>Add Member</u></html>");
        addMember.setFont(forLabel);
        addMember.setBackground(Color.BLACK);
        addMember.setForeground(Color.WHITE);
        addMember.setBounds(430,y,150,30);
        addMember.addActionListener(this);
        add(addMember);
// member end




        //Image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/ocean.jpg"));
        Image i2 = i1.getImage().getScaledInstance(400,380,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        li = new JLabel(i3);
        li.setBounds(350,60,400,380);
        add(li);
        
        toggleDisplay(false);
        //mainPane
        setBounds(400,170,780,500);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);


        // String query = "insert into login values('"+ tId+"' , '"+hashedPassword+"')";
    }


//addMember function
    private void addMemberFunction(){
        // System.out.println(num);
        headStr = "Add Member " + Integer.toString(memCounter);
        System.out.println(headStr);
        head2.setText(headStr);
        memCounter+=1;
       
    }
    private void toggleDisplay(Boolean a){
        li.setVisible(!a);
                for(int i=0;i<3;i++){
                    memJLab[i].setVisible(a);
                    tarrMem[i].setVisible(a);
                }
                head2.setVisible(a);
                addMember.setVisible(a);
    }
    public void actionPerformed (ActionEvent ae){
        if(c1 == ae.getSource()){
            
            numMem = (Integer)c1.getSelectedItem();
            tempStorage = new String[numMem-1][3];
            addMemberFunction();
            c1.setEnabled(false);
            toggleDisplay(true);     // display form for adding new members using for loop 
            enabler(false);


        }else if( signUp == ae.getSource()){
            Conn c = new Conn();
        //headUsers    
            int c1ans=(Integer)c1.getSelectedItem();
            String query1 = "insert into headUsers values('"+tarr[0].getText() +"','"+tarr[2].getText()+"',"+c1ans +",'"+tarr[4].getText()+"')";
        //login table    
            Hash h = new Hash();
            String tPassword = new String(parr[0].getPassword());
            String hashedPassword = h.doHashing(tPassword);            
            String query2 = "insert into login values('"+tarr[4].getText() +"','"+hashedPassword+"')";
        //members
            // String query3 = "insert into members values(1,'"+tarr[4].getText()+"','"+tempStorage[0][1]+"','"+tempStorage[0][2]+"','"+tempStorage[0][0]+"'";
            System.out.println(tempStorage.length);
            
            String query3 = "insert into members values(1,'"+tarr[4].getText()+"','HEAD','"+tarr[2].getText()+"','"+tarr[0].getText()+"')";
            System.out.println(query3);
            try{
                c.s.executeUpdate(query1);//headUsers
                c.s.executeUpdate(query2);//login
                c.s.executeUpdate(query3);//members
                for(int i=0 ; i<tempStorage.length;i++){
                    String query4 = "insert into members values("+(i+2)+",'"+tarr[4].getText()+"','"+tempStorage[i][1]+"','"+tempStorage[i][2]+"','"+tempStorage[i][0]+"')";
                    c.s.executeUpdate(query4);//members
                }    
                JOptionPane.showMessageDialog(null , "Head added sucessfully");
            }catch(Exception e){
                // System.out.println(query2);    
                e.printStackTrace();
            }

            CreateDir cd = new CreateDir();
            cd.Dirmaker(tarr[4].getText());





        }else if( back == ae.getSource()){
            // System.out.println("Signed IN");
            this.setVisible(false);
            new App().setVisible(true);
        }else if(ae.getSource()== addMember ){
            //take info and store it
                for(int i=0 ; i<3;i++){
                    tempStorage[t][i]= tarrMem[i].getText();   
                    tarrMem[i].setText("");
                }
                t++;
                System.out.println(tempStorage[t-1][0]);
                System.out.println(tempStorage[t-1][1]);
                System.out.println(tempStorage[t-1][2]);
            if(memCounter != numMem+1){
                addMemberFunction();
            }else{
                toggleDisplay(false);
                enabler(true);
                tarr[4].requestFocus(); 
            }
        }
    }


    public void focusGained(FocusEvent fe){
        
    }
    public void focusLost(FocusEvent fe){
        if(fe.getSource() == tarr[4]){
            //check with db if this is available i.e. unique
            Conn c = new Conn();
            String usrnm = tarr[4].getText();
            String query = "select COUNT(*) from login where id='"+usrnm+"'";
            try{
                ResultSet rs = c.s.executeQuery(query);
                rs.next();
                
                
                if(rs.getInt(1) != 0){
                    notify[0].setText("username already exists :(");
                    notify[0].setForeground(new Color(153,0,0));
                    // notify[0].requestFocus();
                }else{
                    notify[0].setText("username available :)");
                    notify[0].setForeground(new Color(0, 153, 51));
                }
                notify[0].setVisible(true);
            }catch(Exception e){
                System.out.println("Error in login check db conn");
                // e.printStackTrace();
            }
        }else if(parr[0] == fe.getSource()){
            String pass = new String(parr[0].getPassword());
            PasswordValidator pv = new PasswordValidator();
            try{
                pv.isValid(pass);
                notify[1].setText("Password valid");
                notify[1].setForeground(new Color(0, 153, 51));
            }catch(InvalidPasswordException e){
                notify[1].setText(e.printMessage());
                notify[1].setForeground(new Color(153,0,0));
                // notify[0].requestFocus();
            }
            notify[1].setVisible(true);
        }
    }


    public void keyPressed(KeyEvent e) {}  
    public void keyReleased(KeyEvent e) {  
        String pas1 = new String(parr[0].getPassword());
        String pas2 = new String(parr[1].getPassword());
        
        if(pas1.equals(pas2)){
            notify[2].setText("Password matches");
            notify[2].setForeground(new Color(0, 153, 51));
        }else{
            notify[2].setText("Password doesn't match");
            notify[2].setForeground(new Color(153,0,0));
        }
        notify[2].setVisible(true);
    }  
    public void keyTyped(KeyEvent e) {}  



    private void enabler(boolean t){
            tarr[0].setEnabled(t);
            tarr[2].setEnabled(t);
            tarr[4].setEnabled(t);
        for(i=0;i<2;i++){
            parr[i].setEnabled(t);
        }
    }
    public static void main(String[] args) {
        new Register().setVisible(true);
    }
}
