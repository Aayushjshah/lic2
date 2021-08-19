package finalmaven;
import javax.swing.JPanel;
import javax.swing.JLabel;

import javax.swing.JButton;

import javax.swing.border.Border;
import javax.swing.event.MouseInputListener;
import java.sql.*;
import java.awt.Color;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import javax.swing.border.EmptyBorder;
public class PolicyDetailsDisplay2 extends JPanel implements MouseInputListener {
    //Note : actionListener doesnt work for container
    
    public JPanel myPanel = new JPanel();
    // public JScrollPane jsp = new JScrollPane(myPanel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    public JScrollPane jsp = new ModernScrollPane(myPanel);
    String[] policyNames,policyIds,insuranceTypes,Company,nextPremiumDates;
    CardLayoutMgr clm;String username,member;
    JButton[] lArr = new JButton[5];
    // CardLayout cl;
    JPanel[] p2= new JPanel[10] ;
    int i=0,cnt;
    FontPicker fp = new FontPicker();
    public PolicyDetailsDisplay2(CardLayoutMgr cll,String usernm,String memberName){
        username=usernm;member=memberName;
        clm=cll;
//dbconnect
//getting the count
        Conn c = new Conn();
        String countQuery = "select count(*) from policies where username='"+username+"' and holder='"+member+
        "'";
        String data = "select policyName,policyId,insuranceType,Company from policies where"+
        " username='"+username+"' and holder='"+member+"'";
        try{
            ResultSet rs = c.s.executeQuery(countQuery);
            rs.next();
            cnt = Integer.parseInt(rs.getString(1));
            policyNames = new String[cnt];
            policyIds = new String[cnt];
            insuranceTypes= new String[cnt];
            Company=new String[cnt];
            nextPremiumDates= new String[cnt];
            try{
            rs=c.s.executeQuery(data);
            int k=0;
            while(rs.next()){
                policyNames[k]=rs.getString(1);
                policyIds[k]=rs.getString(2);
                insuranceTypes[k]=rs.getString(3);
                Company[k]=rs.getString(4);
                // nextPremiumDates[k]=rs.getString(6);
                k++;
            }
            }catch(Exception e){
                System.out.println("error pdd2 populate");    
                e.printStackTrace();
            }

        }catch(Exception e){
            System.out.println("error pdd2 countDb");
            
        }
//=========================

        myPanel.setLayout(null);
        myPanel.setBackground(Color.WHITE);
    
        jsp.setBorder(new EmptyBorder(0,0,0,0));
        //pilot
        int x=50;
        for(i=0;i<cnt;i++,x+=130){
        p2[i]= new JPanel();
        // JPanel p2[i] = new RoundedBorderTest()
        p2[i].setBackground(Color.WHITE);
        p2[i].setBounds(10,x,600,100);
        p2[i].setLayout(null);
        Border b2 = new RoundedBorder(fp.panelColor,50);
        p2[i].setBorder(b2);
        p2[i].addMouseListener(this);
        myPanel.add(p2[i]);
        

        //inside
        JLabel name = new JLabel("Name:");
        name.setFont(fp.forLabel);
        name.setBounds(20,5,60,30);
        p2[i].add(name);
        String nameStr ="<html><b><u>"+policyNames[i]+"</u></b></html>";
        JLabel nameVal = new JLabel(nameStr);
        nameVal.setFont(fp.forLabel);
        nameVal.setBounds(85,5,160,30);
        p2[i].add(nameVal);

        JLabel relation = new JLabel("PolicyID:");
        relation.setFont(fp.forLabel);
        relation.setBounds(400,5,70,30);
        p2[i].add(relation);
        String idStr ="<html><b><u>"+policyIds[i]+"</u></b></html>";
        JLabel relationVal = new JLabel(idStr);
        relationVal.setFont(fp.forLabel);
        relationVal.setBounds(480,5,120,30);
        p2[i].add(relationVal);
            int y=10;
        JLabel pol1 = new JLabel("=>"+insuranceTypes[i]);
        pol1.setFont(fp.forLabel);
        pol1.setBounds(y,50,180,30);
        pol1.setForeground(Color.WHITE);
        p2[i].add(pol1);
        y+=210;
        JLabel pol2 = new JLabel("=>"+Company[i]);
        pol2.setFont(fp.forLabel);
        pol2.setBounds(y,50,220,30);
        pol2.setForeground(Color.WHITE);
        p2[i].add(pol2);        
        y+=240;    
        nextPremiumDates[i]="12-12-12";
        JLabel pol4 = new JLabel("=>"+nextPremiumDates[i]);
        pol4.setFont(fp.forLabel);
        pol4.setBounds(y,50,120,30);
        pol4.setForeground(Color.WHITE);
        p2[i].add(pol4);
        }
        myPanel.setBounds(0,0,640,x);
        myPanel.setPreferredSize(new Dimension(640,x));
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        for(int i =0 ; i<4;i++){
            if(e.getSource() ==p2[i]){
                clm.adderiPdd1(new IndividualPolicyDetailsDisplay1(clm, username, policyIds[i]));
                clm.setr("ipdd1");
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        for(int i =0 ; i<4;i++){
            if(e.getSource() ==p2[i]){
                //change the layout
                p2[i].setBorder(new RoundedBorder(Color.RED,50));
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        for(int i =0 ; i<4;i++){
            if(e.getSource() ==p2[i]){
                //change the layout
                p2[i].setBorder(new RoundedBorder(fp.panelColor,50));
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
}
