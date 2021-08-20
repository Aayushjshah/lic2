package finalmaven;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import java.text.NumberFormat;
public class MonthlyReportGenerator{
    String[] headers = {"PolicyId" , "Holder" , "Premium Amount" , "Due Date" , "Agent" , "Bank Acc"};
	Cell[] cells ;
    MonthlyReportGenerator(){}
    public void pdfMaker(String usrnm,String currMnth,String[][] values){
        try{
            NumberFormat f1=NumberFormat.getCurrencyInstance();
            int total=0;
            int lengthh=values.length;
            int len=values[0].length;
            cells = new Cell[len];
            PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);       
            System.out.println("Pdf generated");
            String dest = "reports/monthlySample.pdf";
            PdfWriter writer = new PdfWriter(dest);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);
            pdfDoc.setDefaultPageSize(PageSize.A4);
//table
            float col=280f;
            float[] columnWidth = {100f,col+50f,col-50f};
            Table table = new Table(columnWidth);
            Text t3 = new Text("Manraj Technologies\n Mumbai\n8879090901");
            Text t = new Text("Monthly Premium Report");
           //image
           String imFile = "src/ocean.jpg";       
	      ImageData data = ImageDataFactory.create(imFile);              
	      
	      // Creating an Image object        
	      Image image = new Image(data);                
	      
	      // Setting the position of the image to the center of the page       
	    //   image.setFixedPosition(20,760);
	      image.scaleToFit(80F, 80F); 
            Cell c1 = new Cell();
            c1.add(image);
            c1.setBorder(Border.NO_BORDER);
            c1.setTextAlignment(TextAlignment.LEFT);
            c1.setVerticalAlignment(VerticalAlignment.MIDDLE);
            c1.setPaddingLeft(5f);
            table.addCell(c1);
            table.addCell(new Cell().add(new Paragraph(t))
                // .setTextAlignment(TextAlignment.CENTER)
                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                .setFontSize(24f)
                .setMarginTop(30f)
                .setMarginBottom(30f)
                .setMarginLeft(10f)
                .setBorder(Border.NO_BORDER)
            );
            table.addCell(new Cell().add(new Paragraph(t3))
                .setTextAlignment(TextAlignment.RIGHT)
                .setVerticalAlignment(VerticalAlignment.BOTTOM)
                .setMarginTop(20f)
                .setMarginBottom(20f)
                .setBorder(Border.NO_BORDER)
            );
            table.setBackgroundColor(new DeviceRgb(63,169,219))
                .setFontColor(ColorConstants.WHITE);
            document.add(table);
//
//Pass Monthly Data
//Month and UserID:
String mnUi= "Month: "+ currMnth;
Text t2 = new Text(mnUi);       
   t2.setFont(font);  
   t2.setUnderline(); 
//  t2.setFontColor(ColorConstants.CYAN);
 t2.setFontSize(12);
//			t2.setTextAlignment(TextAlignment.RIGHT);
 Paragraph p2 = new Paragraph();
 p2.setMarginTop(10);
 p2.add(t2);
//			p2.setTextAlignment(TextAlignment.CENTER);
//  document.add(p2);
 
 String mnUi2= "UserId: "+usrnm;
   Text tt3 = new Text(mnUi2);       
       tt3.setFont(font);   
        tt3.setUnderline();
       //  tt3.setFontColor(ColorConstants.CYAN);
     tt3.setFontSize(12);
//				tt3.set
     Paragraph p3 = new Paragraph();
     p3.add(tt3);
     float[] cVal ={300f,300f};
     Table table3 = new Table(cVal);
     table3.addCell(new Cell()
            .add(p2)
            .setTextAlignment(TextAlignment.LEFT)
            .setBorder(Border.NO_BORDER)
            .setVerticalAlignment(VerticalAlignment.MIDDLE)
     );
     table3.addCell(new Cell()
            .add(p3)
            .setTextAlignment(TextAlignment.RIGHT)
            .setBorder(Border.NO_BORDER)
            .setVerticalAlignment(VerticalAlignment.MIDDLE)
     );
     

    table3.setMarginTop(10f);
     document.add(table3);
    float[] widthVal = {100F,150F,150F,150F,150F,100F};
            Table table2 = new Table(widthVal);
            table2.setMarginTop(20);
        //headers	
            for(int i=0;i<6;i++) {
                Cell c2 = new Cell();
                Paragraph ppc2 = new Paragraph(headers[i]);;
                c2.add(ppc2);
                c2.setTextAlignment(TextAlignment.CENTER);
                c2.setVerticalAlignment(VerticalAlignment.MIDDLE);
                c2.setBorder(Border.NO_BORDER);
                c2.setFontSize(13f);
                c2.setBackgroundColor(new DeviceRgb(63,169,219));
                table2.addHeaderCell(c2);
            }
            for(int j=0;j<lengthh;j++){
                Color cr;
                if(j%2==0){
                    cr= new DeviceRgb(255,255,255);
                }else{
                    cr= new DeviceRgb(232,242,230);
                }

            for(int i=0;i<len;i++) {
                cells[i] = new Cell();
                Paragraph ppc1 = new Paragraph(values[j][i]);;
                cells[i].add(ppc1);
                cells[i].setTextAlignment(TextAlignment.CENTER);
                cells[i].setBorder(Border.NO_BORDER);
                cells[i].setBackgroundColor(cr);
                table2.addCell(cells[i]);
            }
            for(int i=0;i<len;i+=6) {
                cells[i].setUnderline();
                cells[i].setFontColor(ColorConstants.BLUE);
            }
            for(int i=2;i<len;i+=6) {
                cells[i].setFontColor(ColorConstants.RED);
                cells[i+1].setFontColor(ColorConstants.RED);
            }
            total += Integer.parseInt(values[j][2]);
        }
            table2.addCell(new Cell()
                .setBorder(Border.NO_BORDER)
            );
            System.out.println(total);
            table2.addCell(new Cell()
                .add(new Paragraph("TOTAL :"))
                .setTextAlignment(TextAlignment.CENTER)
                .setFontColor(ColorConstants.WHITE)
                .setBorder(Border.NO_BORDER)
                .setUnderline()
                .setBold()
                .setBackgroundColor(new DeviceRgb(63,169,219))
            );
            table2.addCell(new Cell()
                .add(new Paragraph(f1.format(total)))
                .setTextAlignment(TextAlignment.CENTER)
                .setBorder(Border.NO_BORDER)
                .setFontColor(ColorConstants.WHITE)
                .setUnderline()
                .setBold()
                .setBackgroundColor(new DeviceRgb(63,169,219))
            );
            document.add(table2);
            document.close();
        }catch(Exception e){
            System.out.println("Error in yearly Report");
            e.printStackTrace();
        }
    }
    // public static void main(String[] args){
    //     String[] values = {"Manraj","September","january","1991","Rina","4000","08-08-2021","Divyesh","Boi@Rina","january","1991","Rina","4000","08-08-2021","Divyesh","Boi@Rina"};
    //     String[][]val = {{"1991","Rina","4000","08-08-2021","Divyesh","Boi@Rina"},
    //                      {"1991","Rina","4000","08-08-2021","Divyesh","Boi@Rina"},
    //                      {"1991","Rina","4000","08-08-2021","Divyesh","Boi@Rina"},
    //                      {"1991","Rina","4000","08-08-2021","Divyesh","Boi@Rina"},
    //                      {"1991","Rina","4000","08-08-2021","Divyesh","Boi@Rina"} };
    //     new MonthlyReportGenerator("Manraj","August",val);
    // }
}
