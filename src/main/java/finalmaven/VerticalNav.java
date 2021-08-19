package finalmaven;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Image;

// import javax.smartcardio.Card;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JButton;
// import javax.swing
public class VerticalNav extends JPanel implements ActionListener{
    public JPanel p1 = new JPanel();
    JButton[] lArr = new JButton[5];
    String[] labels = {"PolicySchedule","Add policy","View Policy","Update Policy","Manage Reminders"};
    FontPicker fp = new FontPicker();
    CardLayoutMgr cl;
        VerticalNav(CardLayoutMgr cll){
            cl=cll;
        p1.setBackground(new Color(230, 255, 230));
        p1.setPreferredSize(new Dimension(220,150));
        p1.setVisible(true);
        p1.setBounds(0,0,220,650);
        p1.setLayout(null);
        // add(p1,BorderLayout.WEST);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/back-button.png"));
        Image i2 = i1.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel back = new JLabel(i3);
        back.setBounds(0,0,50,50);
        p1.add(back);
        int j=100;
        int i=0;
        for(;i<5;i++){
            lArr[i]=new JButton(labels[i]);
            lArr[i].setFont(fp.forLabel);
            lArr[i].setForeground(Color.WHITE);
            lArr[i].setBackground(Color.BLACK);
            lArr[i].addActionListener(this);
            lArr[i].setBounds(10, j, 200, 30);
            p1.add(lArr[i]);
            j+=50;
        }

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        cl.setr("pdd1");
        System.out.println("SSS");
    }
}
