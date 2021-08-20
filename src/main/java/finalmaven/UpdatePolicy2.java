package finalmaven;
import javax.swing.*;

import com.itextpdf.kernel.pdf.PageFlushingHelper;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.*;
import java.io.File;
import java.sql.ResultSet;
//changes to be made
// connect insuranceType to DB and fetch details from there
//rm main method
//name the ecopy with policy name aswell as id
//next button be disabled until all the details are filled
public class UpdatePolicy2 extends JFrame implements ActionListener{
    JLabel[] arr = new JLabel[8];
    String[] labels = {"First premium","1st premium date","Premium Cycle", "Installment Premium"  , "Last Premium Date",
                        "From Bank A/C","Maturity Amount" , "Date of Maturity"};
    PlaceholderTextField[] tarr = new PlaceholderTextField[8];
    JComboBox<String>carr,nCarr;//last for nominee
    // String[] c0,c1,c2,c3;
    int j;

    JLabel[] nArr = new JLabel[3];  //nominee relation
    String[] nLabel = {"Name", "Contact NO." };
    JTextField[] nTarr = new JTextField[3];

    DateGetter dg = new DateGetter();
    String[] pag2Fetch = new String[10];
    String[] agentName;
    JButton back,next,Cancel,Submit;
    JLabel image;
    FontPicker fp = new FontPicker();
    int i;
    public String eCopyFlag="n";
    String userName;

//tempSaveVariables

UpdatePolicy ap;
String[] policyDetails,nomineeDetails;
    public UpdatePolicy2(String username,String[] policyDet , String[] nomineeDet,UpdatePolicy app,String polId){
        policyDetails=policyDet;
        nomineeDetails=nomineeDet;
        ap=app;
        //inital db connect to fill up dropdowns
        userName=username;
            Conn c = new Conn();
            String getAgentNames = "select name from agent";
            
        try{
            ResultSet rs = c.s.executeQuery("select count(*) from agent");
            rs.next();
            agentName = new String[rs.getInt(1)+2];
            rs= c.s.executeQuery(getAgentNames);
            i=0;
            while(rs.next()){
                agentName[i]=rs.getString(1);
                i++;
            }
            agentName[agentName.length-2]="None";
            agentName[agentName.length-1]="Add New";
        }catch(Exception e){
            System.out.println("Initial connect AddPolicy2");
        }
        String fetch3 = "select firstPremium,firstPDate,premiumCycle,installmentPremium,lastPDate,FromBankAcc,maturityAmt,dateOfMaturity,agent from policies"+
                        " where policyId='"+polId+"'";
        try{
            System.out.println(fetch3);
            ResultSet rs = c.s.executeQuery(fetch3);
            rs.next();
            for(int j=0;j<8;j++){
                pag2Fetch[j]=rs.getString(j+1);
            }
            String agentFetch = "select name,contactNO from agent where agentId="+rs.getInt(9);
            ResultSet rs2 = c.s.executeQuery(agentFetch);
            rs2.next();
            pag2Fetch[8] = rs2.getString(1);
            pag2Fetch[9] = rs2.getString(2);
        }catch(Exception e){
            System.out.println("ERROR IN PART3 FETCH");
            e.printStackTrace();
        }
        

        JLabel head = new JLabel("<html><u>Add Policy</u></html>");
        head.setBounds(340,5,200,40);
        head.setFont(fp.headFont);
        head.setForeground(Color.BLACK);
        add(head);
        int x=40;

        JLabel premiumTitle = new JLabel("<html><u><b>Premium Details</b></u></html>");
        premiumTitle.setFont(fp.subHeadFont);
        premiumTitle.setForeground(Color.BLACK);
        premiumTitle.setBounds(50,x,200,30);
        x+=50;
        add(premiumTitle);
        
        for(i=0;i<8;i++){
            arr[i]=new JLabel(labels[i]);
            arr[i].setFont(fp.forLabel);
            arr[i].setForeground(Color.BLACK);
            arr[i].setBounds(20,x,180,30);
            arr[i].setVisible(true);
            add(arr[i]);
            if(i==2){
                String[] c1 = {"ONE TIME" , "YEARLY" , "HALF YEARLY" , "QUARTERLY" , "MONTHLY"};// will come from db
                carr=new JComboBox<String>(c1);
                carr.setSelectedItem(pag2Fetch[i]);
                carr.setFont(fp.forLabel);
                carr.addActionListener(this);
                carr.setBounds(205,x,150,30);
                x+=50;
                carr.setBackground(Color.WHITE);
                add(carr);
                continue;
            }
            
            tarr[i]= new PlaceholderTextField();
            tarr[i].setText(pag2Fetch[i]);
            tarr[i].setFont(fp.forLabel);
            tarr[i].setForeground(Color.BLACK);
            tarr[i].setBounds(205,x,150,30);
            x+=50;
            add(tarr[i]);
        }
        tarr[1].setPlaceholder("dd-MM-yy");
        tarr[4].setPlaceholder("dd-MM-yy");
        tarr[7].setPlaceholder("dd-MM-yy");
        
        JLabel nHead = new JLabel("<html><u><b>Agent Details</b></u></html>");
        nHead.setFont(fp.subHeadFont);
        nHead.setForeground(Color.BLACK);
        x-=10;
        nHead.setBounds(50,x,200,30);
        x+=40;
        add(nHead);
        int m=x;
//Agent subform
        for(i=0;i<2;i++){
            nArr[i]= new JLabel(nLabel[i]);
            nArr[i].setFont(fp.forLabel);
            nArr[i].setForeground(Color.BLACK);
            nArr[i].setBounds(20,x,150,30);
            nArr[i].setVisible(true);
            add(nArr[i]);
            
            nTarr[i]= new JTextField();
            nTarr[i].setText(pag2Fetch[8+i]);
            nTarr[i].setFont(fp.forLabel);
            nTarr[i].setForeground(Color.BLACK);
            nTarr[i].setBounds(205,x,150,30);
            x+=50;
            add(nTarr[i]);

            
        }
        nTarr[0].setVisible(false);
        nTarr[0].setEnabled(false);
        // String[] agentName={"Divyesh Shah" ,"Kalpana Shah","Add New","None"};
        nCarr = new JComboBox<String>(agentName);
        nCarr.setSelectedItem(pag2Fetch[8]);
        nCarr.setBounds(205,m,150,30);
        nCarr.setBackground(Color.WHITE);
        nCarr.setForeground(Color.BLACK);
        nCarr.addActionListener(this);
        nCarr.setFont(fp.forLabel);
        add(nCarr);

        

        //Image
        int ht=450,wdt=440;
        ImageIcon i1 = new ImageIcon(this.getClass().getResource("/finalmaven/icons/ocean.jpg"));
        Image i2 = i1.getImage().getScaledInstance(wdt,ht,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        image = new JLabel(i3);
        image.setBounds(370,80,wdt,ht);
        add(image);
        image.setVisible(true);
        // image.setVisible(false);


        back = new JButton("<html><u>Back</u></html>");
        back.setFont(fp.forLabel);
        back.setForeground(Color.WHITE);
        back.setBackground(Color.BLACK);
        back.addActionListener(this);
        back.setBounds(545,570,120,30);
        add(back);


        next = new JButton("<html><u>Next</u></html>");
        next.setFont(fp.forLabel);
        next.setForeground(Color.WHITE);
        next.setBackground(Color.BLACK);
        next.addActionListener(this);
        next.setBounds(685,570,120,30);
        add(next);

        Cancel = new JButton("<html><u>Cancel</u></html>");
        Cancel.setFont(fp.forLabel);
        Cancel.setForeground(Color.WHITE);
        Cancel.setBackground(Color.BLACK);
        Cancel.addActionListener(this);
        Cancel.setBounds(405,570,120,30);
        Cancel.setVisible(false);
        add(Cancel);

        Submit = new JButton("<html><u>Submit</u></html>");
        Submit.setFont(fp.forLabel);
        Submit.setForeground(Color.WHITE);
        Submit.setBackground(Color.BLACK);
        Submit.addActionListener(this);
        Submit.setBounds(685,570,120,30);
        Submit.setVisible(false);
        add(Submit);



        //mainPane
        setBounds(350,120,830,650);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
//private methods    

    

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == back){
            ap.setVisible(true);
            this.setVisible(false);
            
        }else if(ae.getSource() == next){
            // new AddeCopy(userName,policyDetails[0],policyDetails[4],this).setVisible(true);
            System.out.println("Change E copy?");
            next.setVisible(false);
            Submit.setVisible(true);
            Cancel.setVisible(true);

        }else if(ae.getSource() == Cancel){
            String destPath = "db/"+userName +"/" +policyDetails[0] + "/"+policyDetails[4];
            File fDel = new File(destPath);
            if(fDel.delete()){
                System.out.println("File Deleted Sucessfully!");
            }else{
                System.out.println("!");
            }
        }else if(ae.getSource() == Submit){
            //save to db pg1 and pg2
            
            
            String policyQuery="insert into policies values("+"'"+userName+"','";
            for(i=0;i<policyDetails.length;i++){
                policyQuery+=policyDetails[i]+"','";
            }
            System.out.println(policyQuery);

            for(i=0;i<tarr.length;i++){
                if(i==2){
                    policyQuery+=(String)carr.getSelectedItem()+"','";
                    continue;
                }if(i==1 || i==4 || i==7){
                    policyQuery+=dg.adjustDate(tarr[i].getText())+"','";
                    continue;
                }
                policyQuery+=tarr[i].getText()+"','";
            }
            for(i=0;i<3;i++){
                policyQuery+=nomineeDetails[i]+"','";
            }
            policyQuery+=eCopyFlag+"',";
            //agentDetails left!
            if(nTarr[0].isEnabled()){
                String agentQuery = "insert into agent(name,ContactNO) values('";
                if(nTarr[0].isEnabled()){
                    agentQuery+=nTarr[0].getText()+"','";
                }else{
                    agentQuery+=nCarr.getSelectedItem()+"','";
                }
                agentQuery+=nTarr[1].getText()+"')";
                System.out.println(agentQuery);
                Conn c = new Conn();
                try{
                    // c.s.executeUpdate(agentQuery);
                    ResultSet rs = c.s.executeQuery("select LAST_INSERT_ID()");
                    rs.next();
                    policyQuery+=rs.getInt(1)+")";
                    System.out.println("Agent Sucess");
                }catch(Exception e){
                    System.out.println("Agent Fail");
                    e.printStackTrace();
                }
            }else {
                String selItem=(String)nCarr.getSelectedItem();
                if(selItem.equals("None")){
                    policyQuery+=Integer.toString(-1)+")";
                }else{
                   int id=nCarr.getSelectedIndex()+1; 
                   policyQuery+=id+")";
                }
            }
            //policy
            try{
                Conn c = new Conn();
                // c.s.executeUpdate(policyQuery);
                System.out.println("Added to db!");
            }catch(Exception e){
                System.out.println("error in policy");
            }
            System.out.println("++++++++++++++");
            System.out.println(policyQuery);
        }else if(ae.getSource() == nCarr){
            String selItem = (String)nCarr.getSelectedItem();
            if(selItem.equals("Add New")){
                nCarr.setVisible(false);
                nTarr[0].setVisible(true);
                nTarr[0].setEnabled(true);
                nTarr[0].requestFocus();
            }else if(selItem.equals("None")){
                nTarr[1].setText("            -");
                
            }else{
                Conn c = new Conn();
                String fetchContactNO = "select ContactNO from agent where agentID='"+(nCarr.getSelectedIndex()+1)+"'";
                try{
                    ResultSet rs = c.s.executeQuery(fetchContactNO);
                    rs.next();
                    nTarr[1].setText(Integer.toString(rs.getInt(1)));
                    nTarr[1].setEnabled(false);
                }catch(Exception e){
                    System.out.println("Error in displaying contactno of agent");
                }
            }
        }    
    }






    

}
