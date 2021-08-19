package finalmaven;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.sql.*;
import javax.swing.border.Border;
import java.awt.Image;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.event.MouseInputListener;
// import org.icepdf.core.exceptions.PDFException;

import java.awt.Dimension;
import java.awt.Color;
public class DetailedScheduler implements MouseInputListener{
    JPanel myPanel = new JPanel();
    public JScrollPane jsp = new ModernScrollPane(myPanel);
    String[] heads = {"PolicyNo.","Holder","Premium Amt.","DueDate","Agent" , "BankA/c"};
    FontPicker fp = new FontPicker();
    Border b2 =new RoundedBorder(fp.panelColor,50);
    int x=640;
    int y=95;
    String pDate="N/A";
    CardLayoutMgr clm;
    int totalPremiums;
    JLabel li;
    JLabel[] polIdLabels;
    String[] polIds;
    DateGetter dg = new DateGetter();
    String username;
    String[][] toPdfGenerator;
    DetailedScheduler(CardLayoutMgr cll,String usrnm,int num,int val){
        clm=cll;
        username=usrnm;
        totalPremiums=val;
        polIdLabels = new JLabel[totalPremiums];
        polIds = new String[totalPremiums];
        // clm.varSize(100,0);
    //db connect    
        Conn c = new Conn();
        String countQuery = "select count(*) from reminders where month="+num;
        String basicQuery = "select * from reminders where month="+num;
        try{
            try{
            ResultSet cs = c.s.executeQuery(countQuery);
            cs.next();
            toPdfGenerator = new String[cs.getInt(1)][6];
            }catch(Exception e){
                System.out.println("In countQuery");
                e.printStackTrace();
            }
            ResultSet rs = c.s.executeQuery(basicQuery);
            int i=0;
            while(rs.next()){

                //calling to set initial headings
                Conn c1 = new Conn();
                String policiesQuery = "select policyId,holder,installmentPremium,firstPDate,FromBankAcc,agent,premiumCycle " +
                                        "from policies where policyId='"+rs.getString(1)+"'";
                try{
                    ResultSet rs1 = c1.s.executeQuery(policiesQuery);
                    rs1.next();
                    JPanel tr = headAdder(rs.getInt(3),rs1.getString(7));
                    String AgentNameQuery = "select name from agent where agentId="+rs1.getInt(6);
                    Conn c2 = new Conn();
                    ResultSet rs2 = c2.s.executeQuery(AgentNameQuery);
                    rs2.next();
                    pDate = dg.modToCurrentYear(rs1.getString(4));
                    System.out.println("line 70"+pDate);
                    polIds[i]=rs.getString(1);
                    toPdfGenerator[i][0] = rs1.getString(1);
                    toPdfGenerator[i][1] = rs1.getString(2);
                    toPdfGenerator[i][2] = rs1.getString(3);
                    toPdfGenerator[i][3] = pDate;
                    toPdfGenerator[i][4] = rs2.getString(1);
                    toPdfGenerator[i][5] = rs1.getString(5);
                    tr=insertValues(tr,i,rs1.getString(1),rs1.getString(2),rs1.getString(3),pDate,rs2.getString(1),rs1.getString(5));
                    myPanel.add(tr);
                }catch(Exception e){
                    e.printStackTrace();
                }
                i++;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
//db ENDS

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/genPdf.jpg"));
        Image i2 = i1.getImage().getScaledInstance(150,48,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        li = new JLabel(i3);
        li.setBackground(Color.WHITE);
        li.setBounds(570,15,150,48);
        li.addMouseListener(this);
        myPanel.add(li);
        System.out.println("pDate at 98:"+pDate);
        JLabel headMonth = new JLabel("<html><u>"+dg.getMonthName(pDate)+"</u></html>");
        headMonth.setFont(fp.headFont);
        headMonth.setBounds(15,20,200,35);
        myPanel.add(headMonth);

        myPanel.setLayout(null);
        myPanel.setBackground(Color.WHITE);
        myPanel.setBounds(0,0,640,y);
        myPanel.setPreferredSize(new Dimension(640, y));
    }
    public JPanel insertValues(JPanel temp,int i,String polId,String holder,String PAmt,String pDate , String Agent ,String BankAcc){
        int u=5;
        // for(int i=0;i<5;i++,u+=130){
            polIdLabels[i] = new JLabel("<html><u>"+polId+"</u></html>");
            polIdLabels[i].setFont(fp.forLabel);
            polIdLabels[i].setForeground(new Color(0,20,150));
            polIdLabels[i].setBounds(u,75,130,30);
            polIdLabels[i].addMouseListener(this);
            temp.add(polIdLabels[i]);
            u+=130;
            JLabel l1 = new JLabel(holder);
            l1.setFont(fp.forLabel);
            l1.setForeground(Color.WHITE);
            l1.setBounds(u,75,110,30);
            temp.add(l1);
            u+=110;
             l1 = new JLabel(PAmt);
            l1.setFont(fp.forLabel);
            l1.setForeground(Color.RED);
            l1.setBounds(u,75,130,30);
            temp.add(l1);
            u+=145;
             l1 = new JLabel(pDate);
            l1.setFont(fp.forLabel);
            l1.setForeground(Color.RED);
            l1.setBounds(u,75,120,30);
            temp.add(l1);
            u+=120;
             l1 = new JLabel(Agent);
            l1.setFont(fp.forLabel);
            l1.setForeground(Color.WHITE);
            l1.setBounds(u,75,130,30);
            temp.add(l1);
            u+=120;
             l1 = new JLabel(BankAcc);
            l1.setFont(fp.forLabel);
            l1.setForeground(Color.WHITE);
            l1.setBounds(u,75,120,30);
            temp.add(l1);
            return temp;
    }
    public JPanel headAdder(int kind,String pCyc){
        JPanel temp = new JPanel();
        temp.setBorder(b2);
        temp.setBackground(Color.WHITE);
        temp.setBounds(10,y,730,120);
        temp.setLayout(null);
        
        String kindL="Installment Premium";
        if(kind!=1){
            kindL="Maturity";
        }
        JLabel kindLabel = new JLabel("<html><b><u>"+kindL+"</u></b></html>");
        kindLabel.setFont(fp.forLabel);
        kindLabel.setBounds(15,5,200,30);
        temp.add(kindLabel);
        
        JLabel pCycle = new JLabel("<html><u>"+pCyc+"</u></html>");
        pCycle.setFont(fp.forLabel);
        pCycle.setBounds(615,5,150,30);
        temp.add(pCycle);
        
        int u=5;
        // for(int i=0;i<5;i++,u+=130){
            JLabel l1 = new JLabel(heads[0]);
            l1.setFont(fp.forLabel);
            l1.setForeground(Color.WHITE);
            l1.setBounds(u,38,130,30);
            temp.add(l1);
            u+=130;
             l1 = new JLabel(heads[1]);
            l1.setFont(fp.forLabel);
            l1.setForeground(Color.WHITE);
            l1.setBounds(u,38,110,30);
            temp.add(l1);
            u+=110;
             l1 = new JLabel(heads[2]);
            l1.setFont(fp.forLabel);
            l1.setForeground(Color.WHITE);
            l1.setBounds(u,38,130,30);
            temp.add(l1);
            u+=145;
             l1 = new JLabel(heads[3]);
            l1.setFont(fp.forLabel);
            l1.setForeground(Color.WHITE);
            l1.setBounds(u,38,120,30);
            temp.add(l1);
            u+=120;
             l1 = new JLabel(heads[4]);
            l1.setFont(fp.forLabel);
            l1.setForeground(Color.WHITE);
            l1.setBounds(u,38,130,30);
            temp.add(l1);
            u+=120;
             l1 = new JLabel(heads[5]);
            l1.setFont(fp.forLabel);
            l1.setForeground(Color.WHITE);
            l1.setBounds(u,38,120,30);
            temp.add(l1);

        y+=135;
        return temp;
}
    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        for(int j=0;j<totalPremiums;j++){
            if(e.getSource()==polIdLabels[j]){
                clm.adderiPdd1(new IndividualPolicyDetailsDisplay1(clm, username, polIds[j]));
                clm.setr("ipdd1");
            }
            if(e.getSource() == li){
                //fire monhtlyReport
                MonthlyReportGenerator mrg = new MonthlyReportGenerator();
                mrg.pdfMaker(username, dg.getMonthName(pDate), toPdfGenerator);
            }
        }
        
    }
    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        for(int j=0;j<totalPremiums;j++){
            if(e.getSource()==polIdLabels[j]){
                polIdLabels[j].setText("<html><i><u>"+"More Details"+"</u></i></html>");
            }
        }
        
    }
    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        for(int j=0;j<totalPremiums;j++){
            if(e.getSource()==polIdLabels[j]){
                polIdLabels[j].setText("<html><i><u>"+polIds[j]+"</u></i></html>");
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
