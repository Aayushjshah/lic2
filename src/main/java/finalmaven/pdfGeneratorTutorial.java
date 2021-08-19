package finalmaven;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class pdfGeneratorTutorial {
    pdfGeneratorTutorial(){
        System.out.println("Aayush");
//
        Document document = new Document();
        try{
            PdfWriter writer = PdfWriter.getInstance(document,new FileOutputStream("designing/Aayush.pdf"));
            document.open();
            document.add(new Paragraph("Sincerity. Respect Time."));
            document.close();
            writer.close();

        }catch(DocumentException de){
            de.printStackTrace();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }

    }
    public static void main(String[] args){
        new pdfGeneratorTutorial();
    }
}
