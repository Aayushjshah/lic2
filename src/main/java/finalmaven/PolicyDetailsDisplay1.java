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
public class PolicyDetailsDisplay1 extends JPanel implements MouseInputListener {
    //Note : actionListener doesnt work for container
    
    public JPanel myPanel = new JPanel();
    // public JScrollPane jsp = new JScrollPane(myPanel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    public JScrollPane jsp = new ModernScrollPane(myPanel);
    CardLayoutMgr clm;
    JButton[] lArr = new JButton[5];
    // CardLayout cl;
    JPanel[] p2;
    String[] nameArr;
    String[] relationArr;
    int i=0;
    public String username,memberName;
    // public String memberName;
    FontPicker fp = new FontPicker();
    public PolicyDetailsDisplay1(CardLayoutMgr cll,String usernm){
        username=usernm;
        
        clm=cll;
//dbConnect
        Conn c = new Conn();
        //initialing array p2's size
        String query1 = "select count(*) from members where username='"+username+"'";
        String query2 = "select name,relation from members where username='"+username+"'";
        try{
            ResultSet rs = c.s.executeQuery(query1);
            rs.next();
            int num=Integer.parseInt(rs.getString(1));
            p2 = new JPanel[num];
            nameArr = new String[num];
            relationArr = new String[num];
            rs=c.s.executeQuery(query2);
            int k=0;
            while(rs.next()){
                nameArr[k]=rs.getString(1);
                relationArr[k]=rs.getString(2);
                //fetch policies
                k++;
            }
        }catch(Exception e){
            System.out.println("Exception in pdd1 dbConnect");
        }


//=======================dbup        
        // clm.varSize(0,-100);
        myPanel.setLayout(null);
        myPanel.setBackground(Color.WHITE);
        
        // jsp.setPreferredSize(new Dimension(640,50));
        jsp.setBorder(new EmptyBorder(0,0,0,0));
        //pilot
        int x=50;
        int p2Len = p2.length;
        for(i=0;i<p2Len;i++,x+=130){
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
        String nameString = "<html><b><u>"+nameArr[i]+"</u></b></html>";
        JLabel nameVal = new JLabel(nameString);
        nameVal.setFont(fp.forLabel);
        nameVal.setBounds(85,5,160,30);
        p2[i].add(nameVal);

        JLabel relation = new JLabel("Relation:");
        relation.setFont(fp.forLabel);
        relation.setBounds(400,5,70,30);
        p2[i].add(relation);
        String relationString = "<html><b><u>"+relationArr[i]+"</u></b></html>";
        JLabel relationVal = new JLabel(relationString);
        relationVal.setFont(fp.forLabel);
        relationVal.setBounds(480,5,120,30);
        p2[i].add(relationVal);
//4 policies
        String policyQuery = "select policyName from policies where username='"+username+"' and holder='"+
        nameArr[i]+"' limit 3";
        try{
            ResultSet rs1 = c.s.executeQuery(policyQuery);
            int y=10;
            while(rs1.next()){
                JLabel pol1 = new JLabel("=>"+rs1.getString(1));
                pol1.setFont(fp.forLabel);
                pol1.setBounds(y,50,180,30);
                pol1.setForeground(Color.WHITE);
                p2[i].add(pol1);
                y+=200;
                
            }
        }catch(Exception e){System.out.println("policyName db error!");}
//====================
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
                memberName=nameArr[i];
                clm.adderPdd2(new PolicyDetailsDisplay2(clm, username, nameArr[i]));
                clm.setr("pdd2");
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
