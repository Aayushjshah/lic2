package finalmaven;
import javax.swing.*;
import javax.swing.JOptionPane;
import java.io.File;
import java.awt.Desktop;
public class PdfViewer extends JFrame{
    // JScrollPane sPane;
   PdfViewer(){}
    String s="db/Manraj/Jignesh/ee.pdf";
    public void openFile(String filePath){
        if (Desktop.isDesktopSupported()) {
            try {
                File myFile = new File(filePath);
                Desktop.getDesktop().open(myFile);
            
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"error in opening file");
                
            }
        }
    }


    // JTextArea textArea = new JTextArea(20, 20);  
    
    //  sPane= new JScrollPane(textArea);

    
    // sPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    // sPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  
    // sPane.setBounds(20, 20, 1150, 720);
    // getContentPane().add(sPane);

    //mainPane
        // setBounds(100,20,1200,800);
        // setLayout(null);
        // getContentPane().setBackground(Color.WHITE);
        // setDefaultCloseOperation(EXIT_ON_CLOSE);
        // setVisible(true);


  /* void openpdf(String file){
  
    try {
           SwingController control=new SwingController();
            SwingViewBuilder factry=new SwingViewBuilder(control);
            JPanel veiwerCompntpnl=factry.buildViewerPanel();
            ComponentKeyBinding.install(control, veiwerCompntpnl);
            control.getDocumentViewController().setAnnotationCallback(
                    new org.icepdf.ri.common.MyAnnotationCallback(
                    control.getDocumentViewController()));
                   control.openDocument(file);
                   sPane.setViewportView(veiwerCompntpnl); 
        } catch (Exception ex) {
            // ex.printStackTrace();
            ex.getMessage();
            JOptionPane.showMessageDialog(this,"Cannot Load Pdf");
        }
}*/
   
    public static void main(String[] args){
        PdfViewer s = new PdfViewer();
        // s.openpdf("C:\\cygwin64\\home\\aayus\\aayushjshah\\lic\\src\\ss.pdf");
        // s.openpdf("src/sample.pdf");


        
        
    }
}
