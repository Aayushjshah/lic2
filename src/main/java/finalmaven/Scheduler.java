package finalmaven;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.sql.*;
import javax.swing.JScrollPane;
import javax.swing.event.MouseInputListener;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.border.Border;
public class Scheduler implements MouseInputListener{
    JPanel myPanel = new JPanel();
    JPanel[] months = new JPanel[12];
    String[] mNames = {"January","February","March","April","May","June","July","August","September","October",
                      "November","December"};
    FontPicker fp = new FontPicker();
    //modernScrollPane to get a stylish one.//public sicnce jspp will be added to the card Layout manager;
    public JScrollPane jspp = new ModernScrollPane(myPanel);
    JLabel[] ll1=new JLabel[12];
    JLabel[] l1 = new JLabel[12];
    int[] countKeeper = new int[12];
    int x=30,i=0;    //the variable to manage placemunet vertically in the panel.
    Border b1 = new RoundedBorder(Color.RED,50);
    Border b2 = new RoundedBorder(fp.panelColor,50);
    Border b3 = new RoundedBorder(fp.shadowforPanel,50);
    CardLayoutMgr clm;
    JLabel li;
    DateGetter dg = new DateGetter();
    String usrnm;
    Scheduler(CardLayoutMgr cll,String username){
        clm=cll;
        usrnm=username;
//db connect
        Conn c = new Conn();
        String reminderTableQuery = "select * from reminders";
        try{
            ResultSet rs = c.s.executeQuery(reminderTableQuery);
            while(rs.next()){
                // System.out.println(rs.getInt(2));
                int mValue =rs.getInt(2);
                countKeeper[mValue-1] +=1;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
//db end        
ImageIcon iii1 = new ImageIcon(ClassLoader.getSystemResource("icons/genPdf.jpg"));
        Image iii2 = iii1.getImage().getScaledInstance(150,48,Image.SCALE_DEFAULT);
        ImageIcon iii3 = new ImageIcon(iii2);
        li = new JLabel(iii3);
        li.setBackground(Color.WHITE);
        li.setBounds(420,15,150,48);
        li.addMouseListener(this);
        myPanel.add(li);
        JLabel headMonth = new JLabel("<html><u>"+"Yearly Schedule"+"</u></html>");
        headMonth.setFont(fp.headFont);
        headMonth.setBounds(15,20,300,40);
        myPanel.add(headMonth);
        x=100;
//setting up 12 months panels        
       for(;i<12;i++,x+=130){
        //panel Lft
        months[i] = new JPanel();
        months[i].setBackground(Color.WHITE);
        months[i].setLayout(null);
        months[i].setBorder(b2);
        months[i].setBounds(5,x,300,100);
        myPanel.add(months[i]);

        if(countKeeper[i] !=0){
            JLabel s1 = new JLabel("You have "+countKeeper[i]+" policies due.");
            s1.setForeground(Color.BLACK);
            s1.setBounds(10,50,150,30);
            months[i++].add(s1);
        }else{
            JLabel s1 = new JLabel("You have No Dues.");
            s1.setForeground(Color.BLACK);
            s1.setBounds(10,50,150,30);
            months[i++].add(s1);
        }

        //panel Rt
        months[i] = new JPanel();
        months[i].setBackground(Color.WHITE);
        months[i].setLayout(null);
        months[i].setBorder(b2);
        months[i].setBounds(330,x,300,100);
        myPanel.add(months[i]);

//display number of policies;
        if(countKeeper[i] !=0){
            JLabel s1;
            if(countKeeper[i]==1){
                s1 = new JLabel("You have "+countKeeper[i]+" policy due.");    
            }else{
             s1= new JLabel("You have "+countKeeper[i]+" policies due.");
            }
            s1.setForeground(Color.BLACK);
            s1.setBounds(10,50,150,30);
            months[i].setBorder(b1);
            months[i].add(s1);
        }else{
            JLabel s1 = new JLabel("You have No Dues.");
            s1.setForeground(Color.BLACK);
            s1.setBounds(10,50,150,30);
            months[i].add(s1);
        }
       }
//adding names and click and color change policy
       for(i=0;i<12;i++){
           JLabel l1 = new JLabel(mNames[i]);
           l1.setBounds(15,5,200,30);
           l1.setFont(fp.subHeadFont);
           l1.setForeground(Color.WHITE);
           months[i].add(l1);

           if(countKeeper[i] != 0){
               months[i].addMouseListener(this);
           }
       }
//adding pdf
       int w=40,h=40;
       for(i=0;i<12;i++){
           if(countKeeper[i]!=0){
            ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/pdf3.png"));
            Image i2 = i1.getImage().getScaledInstance(w,h,Image.SCALE_DEFAULT);
            ImageIcon i3 = new ImageIcon(i2);
            l1[i] = new JLabel(i3);
            l1[i].setBounds(260,3,w,h);
            l1[i].addMouseListener(this);
            months[i].add(l1[i]);

            ImageIcon ii1 = new ImageIcon(ClassLoader.getSystemResource("icons/pdf2.png"));
            Image ii2 = ii1.getImage().getScaledInstance(w,h,Image.SCALE_DEFAULT);
            ImageIcon ii3 = new ImageIcon(ii2);
            ll1[i] = new JLabel(ii3);
            ll1[i].setBounds(260,3,w,h);
            ll1[i].setVisible(false);;
            ll1[i].addMouseListener(this);
            months[i].add(ll1[i]);
           }
       }
//myPanel settings        
        myPanel.setBackground(Color.WHITE);
        myPanel.setLayout(null);
        // x=640;
//settings suitable to scrollpane;        
        myPanel.setBounds(0,0,640,x);
        myPanel.setPreferredSize(new Dimension(640,x));   
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
        for(i=0;i<12;i++){
            if(e.getSource()==months[i]){
                System.out.println("Show months data");
                clm.adderdscl(new DetailedScheduler(clm,usrnm, i+1,countKeeper[i]));
                clm.setr("dscl");
            }
        }
        if(e.getSource()== li){
            yearPremiumReportFire yprf = new yearPremiumReportFire();
            YearlyReportGenerator yrg= new YearlyReportGenerator();
            yrg.pdfMaker(usrnm,Integer.toString(dg.currYear),yprf.arrayGiver());
        }

        for(i=0;i<12;i++){
            if(e.getSource()==ll1[i]){
                monthPremiumReportFire yprf = new monthPremiumReportFire();
            MonthlyReportGenerator mrg= new MonthlyReportGenerator();
            mrg.pdfMaker(usrnm,dg.getMonthName(i+1),yprf.arrayGiver(i+1));
                break;
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        for(i=0;i<12;i++){
            if(e.getSource()==l1[i]){
                ll1[i].setVisible(true);
                l1[i].setVisible(false);
                break;
            }
        }

        for(i=0;i<12;i++){
            if(e.getSource()==months[i]){
                months[i].setBorder(b3);
                break;
            }
        }
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        for(i=0;i<12;i++){
            if(e.getSource()==ll1[i]){
                ll1[i].setVisible(false);
                l1[i].setVisible(true);
                break;
            }
        }
        
        for(i=0;i<12;i++){
            if(e.getSource()==months[i]){
                months[i].setBorder(b1);
                break;
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
