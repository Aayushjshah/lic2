package finalmaven;
import javax.swing.*;


import java.awt.Color;
import java.awt.Image;
import java.awt.event.*;
import java.sql.ResultSet;

public class AddPolicy extends JFrame implements ActionListener{
    JLabel[] arr = new JLabel[7];
    String[] labels = {"Policy Holder" , "Insurance Type" , "Policy NO." ,"Policy ID", "Policy Name" , "Company" , "Sum Assured"};
    JTextField[] tarr = new JTextField[4];
    JComboBox[] carr = new JComboBox[4];//last for nominee
    String[] c0,c1,c2,c3;
    int j;

    JLabel[] nArr = new JLabel[3];  //nominee relation
    String[] nLabel = {"Nominee", "Relation" , "Contact NO." };
    JTextField[] nTarr = new JTextField[3];

//new insurancetypr
    JTextField nInsuranceType,nCompanyTf;
    JLabel subHead,subHead2;

    JButton back,next,subnInsuranceType,nCompanyBut;
    JLabel image;
    FontPicker fp = new FontPicker();
    int i;
    String userName;
// JPanel
//tempSaveVariables
String[] policyPg1 = new String[7];
String[] nomineeMemory = new String[3];
AddPolicy ap2;
    public AddPolicy(String username,AddPolicy app2){
        //inital db connect to fill up dropdowns
        userName=username;
        ap2=app2;
        Conn c = new Conn();
        try{
            //company c2
            String query = "select count(*) from company";
            ResultSet rs = c.s.executeQuery(query);
            rs.next();
            c2=new String[rs.getInt(1)+1];

            String query1 = "select * from company";
            rs=c.s.executeQuery(query1);
            i=0;
            while(rs.next()){
                c2[i++]=rs.getString(1);
            }
            c2[c2.length-1]="add new Entry";
        //Members c0 and c3
            String query3 = "select NOofMembers from headUsers where username='"+ username+"'";
            rs=c.s.executeQuery(query3);
            rs.next();
            c0=new String[rs.getInt(1)];
            c3=new String[rs.getInt(1)+2];
            String memberQuery = "select name from members where username='"+username+"'";
            rs= c.s.executeQuery(memberQuery);
            i=0;
            while(rs.next()){
                c3[i]=rs.getString(1);
                c0[i++]=rs.getString(1);
            }
            c3[c3.length-2]="None";
            c3[c3.length-1]="Add new";
        //Insurance Type c1
            String query5 = "select count(*) from InsuranceType";
            rs=c.s.executeQuery(query5);
            rs.next();
            c1 = new String[rs.getInt(1)+1];
            c1[c1.length-1]="Add New Type";

        }catch(Exception e){
            System.out.println("Initial connect AddPolicy");
        }
        
        

        JLabel head = new JLabel("<html><u>Add Policy</u></html>");
        head.setBounds(340,5,200,40);
        head.setFont(fp.headFont);
        head.setForeground(Color.BLACK);
        add(head);

        int x=60;
        for(i=0;i<7;i++){
            arr[i]=new JLabel(labels[i]);
            arr[i].setFont(fp.forLabel);
            arr[i].setForeground(Color.BLACK);
            arr[i].setBounds(20,x,150,30);
            arr[i].setVisible(true);
            add(arr[i]);

            x+=50;
        }
        x=60;
        //0
        // String[] c0 = {"Jignesh", "Aayush","Mannan","Rina"};//policyHolder will come from db
        carr[0]=new JComboBox<String>(c0);
        carr[0].setFont(fp.forLabel);
        carr[0].setBounds(175,x,150,30);
        x+=50;
        carr[0].setBackground(Color.WHITE);
        add(carr[0]);
        
        
        // String[] c1 = {"ASSS","DDDD","Add new Type"};// will come from db
        carr[1]=new JComboBox<String>(c1);
        carr[1].setFont(fp.forLabel);
        carr[1].addActionListener(this);
        carr[1].setBounds(175,x,150,30);
        x+=50;
        carr[1].setBackground(Color.WHITE);
        add(carr[1]);

        tarr[0]= new JTextField();
        tarr[0].setFont(fp.forLabel);
        tarr[0].setForeground(Color.BLACK);
        tarr[0].setBounds(175,x,150,30);
        x+=50;
        add(tarr[0]);

        tarr[1]= new JTextField();
        tarr[1].setFont(fp.forLabel);
        tarr[1].setForeground(Color.BLACK);
        tarr[1].setBounds(175,x,150,30);
        x+=50;
        add(tarr[1]);

        tarr[2]= new JTextField();
        tarr[2].setFont(fp.forLabel);
        tarr[2].setForeground(Color.BLACK);
        tarr[2].setBounds(175,x,150,30);
        x+=50;
        add(tarr[2]);

        // String[] c2 = {" LIC" , "KOTAK" , "NEW INDIA" , "STAR HEALTH" , "SBI"  , "add new Entry"};// will come from db//Company
        carr[2]=new JComboBox<String>(c2);
        carr[2].setFont(fp.forLabel);
        carr[2].setBounds(175,x,150,30);
        x+=50;
        carr[2].setBackground(Color.WHITE);
        carr[2].addActionListener(this);
        add(carr[2]);



        tarr[3]= new JTextField();
        tarr[3].setFont(fp.forLabel);
        tarr[3].setForeground(Color.BLACK);
        tarr[3].setBounds(175,x,150,30);
        x+=50;
        add(tarr[3]);




        

        JLabel nHead = new JLabel("<html><u><b>Nominee Details</b></u></html>");
        nHead.setFont(fp.subHeadFont);
        
        nHead.setForeground(Color.BLACK);
        x-=10;
        nHead.setBounds(50,x,200,30);
        x+=50;
        add(nHead);
        int m=x;
//nominee subform
        for(i=0;i<3;i++){
            nArr[i]= new JLabel(nLabel[i]);
            nArr[i].setFont(fp.forLabel);
            nArr[i].setForeground(Color.BLACK);
            nArr[i].setBounds(20,x,150,30);
            nArr[i].setVisible(true);
            add(nArr[i]);
            
            nTarr[i]= new JTextField();
            nTarr[i].setFont(fp.forLabel);
            nTarr[i].setForeground(Color.BLACK);
            nTarr[i].setBounds(175,x,150,30);
            x+=50;
            add(nTarr[i]);

            
        }
        nTarr[0].setVisible(false);
        // c3 = {"Jignesh", "Aayush","Mannan","Rina","Add new"};//policyHolder will come from db
        //if Add new then then from dropdown to textfield
        //if not then auto fill rest 2 fields
        //add contact to register page
        carr[3]=new JComboBox<String>(c3);
        carr[3].setFont(fp.forLabel);
        carr[3].setBounds(175,m,150,30);
        // x+=50;
        carr[3].addActionListener(this);
        carr[3].setBackground(Color.WHITE);
        add(carr[3]);                
        
//Add new Insurance TYPe
            //Render a form

        subHead = new JLabel("<html><u>Add New Insurance Type</u></html>");
        subHead.setFont(fp.sHeadFont);
        subHead.setBounds(450,100,300,30);
        subHead.setVisible(false);
        add(subHead);
        
        nInsuranceType = new JTextField();
        nInsuranceType.setForeground(Color.BLACK);
        nInsuranceType.setFont(fp.forLabel);
        nInsuranceType.setBounds(485,150,200,30);
        nInsuranceType.setVisible(false);
        add(nInsuranceType);

        subnInsuranceType = new JButton("<html><u>Submit</u><html>");
        subnInsuranceType.setBounds(510,200,150,30);
        subnInsuranceType.setFont(fp.forLabel);
        subnInsuranceType.setForeground(Color.WHITE);
        subnInsuranceType.setBackground(Color.BLACK);
        subnInsuranceType.setVisible(false);
        subnInsuranceType.addActionListener(this);
        add(subnInsuranceType);
//New Company providing Insurance

subHead2 = new JLabel("<html><u>Add New Company</u></html>");
subHead2.setFont(fp.sHeadFont);
subHead2.setBounds(450,100,300,30);
subHead2.setVisible(false);
add(subHead2);

nCompanyTf = new JTextField();
nCompanyTf.setForeground(Color.BLACK);
nCompanyTf.setFont(fp.forLabel);
nCompanyTf.setBounds(485,150,200,30);
nCompanyTf.setVisible(false);
add(nCompanyTf);

nCompanyBut = new JButton("<html><u>Submit</u><html>");
nCompanyBut.setBounds(510,200,150,30);
nCompanyBut.setFont(fp.forLabel);
nCompanyBut.setForeground(Color.WHITE);
nCompanyBut.setBackground(Color.BLACK);
nCompanyBut.setVisible(false);
nCompanyBut.addActionListener(this);
add(nCompanyBut);

        //Image
        int ht=450,wdt=470;
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/ocean.jpg"));
        Image i2 = i1.getImage().getScaledInstance(wdt,ht,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        image = new JLabel(i3);
        image.setBounds(340,80,wdt,ht);
        add(image);
        image.setVisible(true);
        // image.setVisible(false);


        back = new JButton("<html><u>Back</u></html>");
        back.setFont(fp.forLabel);
        back.setForeground(Color.WHITE);
        back.setBackground(Color.BLACK);
        back.addActionListener(this);
        back.setBounds(485,570,150,30);
        add(back);


        next = new JButton("<html><u>Next</u></html>");
        next.setFont(fp.forLabel);
        next.setForeground(Color.WHITE);
        next.setBackground(Color.BLACK);
        next.addActionListener(this);
        next.setBounds(655,570,150,30);
        add(next);

        //mainPane
        setBounds(350,120,830,650);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
//private methods    
    private void dispInsuranceTypeForm(boolean a){
        image.setVisible(!a);
        subnInsuranceType.setVisible(a);
        nInsuranceType.setVisible(a);
        subHead.setVisible(a);
        if(a){
            nInsuranceType.requestFocus();
        }

        //disable everything else function call
    }

    private void dispCompanyNameForm(boolean a){
        image.setVisible(!a);
        subHead2.setVisible(a);
        nCompanyTf.setVisible(a);
        nCompanyBut.setVisible(a);
        if(a){
            nCompanyTf.requestFocus();
        }
        //disable everything else function call
    }

    private void DisableElse(boolean a){
        // i=0;
        for(i=0;i<tarr.length;i++){
            tarr[i].setEnabled(!a);
        }
        for(i=0;i<nTarr.length;i++){
            nTarr[i].setEnabled(!a);
        }
        for(i=0;i<carr.length;i++){
            carr[i].setEnabled(!a);
        }

        
    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == back){
            System.out.println("BACK PRESSED");
            
        }else if(ae.getSource() == next){
            
            //one array for policyDetails
           
            policyPg1[0]=(String)carr[0].getSelectedItem();
            if(policyPg1[1]==null){
                policyPg1[1]=(String)carr[1].getSelectedItem();
            }
            policyPg1[2]=tarr[0].getText();
            policyPg1[3]=tarr[1].getText();
            policyPg1[4]=tarr[2].getText();
            if(policyPg1[5]==null){
                policyPg1[5]=(String)carr[2].getSelectedItem();
            }
            
            policyPg1[6]=tarr[3].getText();

            //one Array for NomineeDetails
            if(nTarr[0].isEnabled()){
                nomineeMemory[0]=nTarr[0].getText();
            }else{
                nomineeMemory[0]=(String)carr[3].getSelectedItem();
            }
            nomineeMemory[1]=nTarr[1].getText();
            nomineeMemory[2]=nTarr[2].getText();
            
            this.setVisible(false);
            if(ap2==null){
                new AddPolicy2(userName,policyPg1,nomineeMemory,this).setVisible(true);    
            }else{
                ap2.setVisible(true);
                this.setVisible(false);
            }
            


            for(i=0;i<policyPg1.length;i++){
                System.out.println(policyPg1[i]);
            }
            for(i=0;i<nomineeMemory.length;i++){
                System.out.println(nomineeMemory[i]);
            }

        }else if(ae.getSource() == subnInsuranceType){  //button for insurance Type subForm
            dispInsuranceTypeForm(false);
            DisableElse(false);
            //saving in arrMemory
            policyPg1[1]=nInsuranceType.getText();
        }else  if(ae.getSource() == carr[1]){
            String selItem = (String)carr[1].getSelectedItem();
            if(selItem.equals("Add New Type")){
                dispInsuranceTypeForm(true);
                DisableElse(true);
            }
        }else if(ae.getSource() == carr[2]){
            String selItem = (String)carr[2].getSelectedItem();
            if(selItem.equals("add new Entry")){
                dispCompanyNameForm(true);
                DisableElse(true);
            }
        }else if(ae.getSource() == nCompanyBut){    //new Company Button
            dispCompanyNameForm(false);
            DisableElse(false);
            //saving in arrMemory
            policyPg1[5]=nCompanyTf.getText();

        }else if(ae.getSource() == carr[3]){
            String selItem = (String)carr[3].getSelectedItem();
            if(selItem.equals("Add new")){
                carr[3].setVisible(false);
                nTarr[0].setVisible(true);
                nTarr[0].requestFocus();
                //will save name at next click itself
            }else if(selItem.equals("None")){
                nTarr[1].setText("            -");
                nTarr[2].setText("            -");
            }
            else{
                //fille the 2 blocks below and disable them
                //extra make an algo to determine the correct relation also add contact no.
                // try{
                //     Conn c = new Conn();
                //     String relationQuery ="select relation from members where name='"+selItem+"' and username='"+
                //     userName+"'";
                //     ResultSet rs = c.s.executeQuery(relationQuery);
                //     rs.next();
                //     nTarr[1].setText(rs.getString(1));
                // }catch(Exception e){
                //     System.out.println("In carr[3] connect");
                // }
            }
        }
    }






    public static void main(String[] args){
        new AddPolicy("Manraj",null);
    }
}
    