package finalmaven;
import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
// import javax.swing.JButton;
import java.awt.Color;
// import java.awt.event.ActionListener;


public class CardLayoutMgr extends JFrame{
    CardLayout cl = new CardLayout();
    
        
    int i=0;
    final int height=650;
    final int fwidth = 900;
    final int pclwidth= 660;
    final int pclheight=605;
    FontPicker fp = new FontPicker();
    JPanel pcl = new JPanel(cl);
    VerticalNav vn = new VerticalNav(this);
    JPanel p1 = vn.p1;
    CardLayoutMgr(){
        
        setBounds(350,120,fwidth,height);
        pcl.setBounds(220,5,pclwidth,pclheight);   //the outer right panel
        pcl.setBackground(Color.WHITE);
        // pcl.setLayout(cl);
        add(p1);
        
        PolicyDetailsDisplay1 pdd1 = new PolicyDetailsDisplay1(this,"Manraj");   //panel added to pcl
        // PolicyDetailsDisplay2 pdd2 = new PolicyDetailsDisplay2(this,pdd1.username,pdd1.memberName);   //panel added to pcl
        // IndividualPolicyDetailsDisplay1 ipdd1 = new IndividualPolicyDetailsDisplay1(this,"Manraj","19991");  //panel added to pcl
        Scheduler scl = new Scheduler(this,"Manraj");
        pcl.add(pdd1.jsp,"pdd1");
        pcl.add(scl.jspp,"scl");
        // pcl.add(dscl.jsp,"dscl");
        // pcl.add(pdd2.jsp,"pdd2");
        // pcl.add(ipdd1,"ipdd1");
        add(pcl);
        // cl.show(pcl,"pdd1");//cl is cardLayout
        // cl.show(pcl,"scl");
        cl.show(pcl,"scl");
        

//end
        setLayout(null);
        // this.setUndecorated(true);
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void setr(String i){
        pcl.setSize(pclwidth,pclheight);
            this.setSize(fwidth,height);
        if(i.equals("ipdd1")){
            this.varSize(0,0);
        }else if(i.equals("dscl")){
            this.varSize(100,0);
        }
        else{
        }
        cl.show(pcl,i);     
    }
    public void adderPdd2(PolicyDetailsDisplay2 c){
        pcl.add(c.jsp,"pdd2");
    }
    public void adderiPdd1(IndividualPolicyDetailsDisplay1 c){
        pcl.add(c,"ipdd1");
        // return pcl;
    }
    public void adderdscl(DetailedScheduler c){
        pcl.add(c.jsp,"dscl");
        // return pcl;
    }
    public void varSize(int widthInc,int heightInc){
        pcl.setSize(pcl.getWidth()+ widthInc, pcl.getHeight()+heightInc);
        this.setSize(this.getWidth()+widthInc,this.getHeight()+heightInc);
    }

    public static void main(String[] args){
        new CardLayoutMgr();
    }
    
}
