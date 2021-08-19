package finalmaven;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.TextAlignment;

public class createMonthReport {
	
	String[] headers = {"PolicyId" , "Holder" , "Premium Amount" , "Due Date" , "Agent" , "Bank Acc"};
	Cell[] cells ;
	createMonthReport(){}
		public void setLayout(String[] values){
			int len=values.length;
			cells = new Cell[len-2];
		String dest = "sample.pdf";
		try {
		PdfWriter writer = new PdfWriter(dest);
		PdfDocument pdfdocument = new PdfDocument(writer);
		Document document = new Document(pdfdocument);
//add image icon type 
		String imFile = "src/ocean.jpg";       
	      ImageData data = ImageDataFactory.create(imFile);              
	      
	      // Creating an Image object        
	      Image image = new Image(data);                
	      
	      // Setting the position of the image to the center of the page       
	      image.setFixedPosition(20,760);
	      image.scaleToFit(80F, 80F);
	      // Adding image to the document       
	      document.add(image);    
//add Label by side
		Text t1 = new Text("Manraj Technologies");
		PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);       
	      t1.setFont(font);   
		t1.setFontColor(ColorConstants.MAGENTA);
		t1.setFontSize(30f);
//		t1.setTextAlignment(TextAlignment.RIGHT);
		Paragraph p1 = new Paragraph();
		p1.add(t1);
		p1.setTextAlignment(TextAlignment.CENTER);
		document.add(p1);
//line
		PdfPage pdfPage = pdfdocument.getFirstPage();  
		PdfCanvas canvas = new PdfCanvas(pdfPage);              
	      
	      // Initial point of the line       
	      canvas.moveTo(40, 750);              
	      
	      // Drawing the line       
	      canvas.lineTo(540, 750);           
	      
	      // Closing the path stroke       
	      canvas.closePathStroke();
//subHead
	      String subHead= "Monthly Report";
	       Text t2 = new Text(subHead);       
		      t2.setFont(font);   
			t2.setFontColor(ColorConstants.CYAN);
			t2.setFontSize(15);
			t2.setUnderline();
//			t2.setTextAlignment(TextAlignment.RIGHT);
			Paragraph p4 = new Paragraph();
			p4.setMarginTop(15);
			p4.add(t2);
			p4.setTextAlignment(TextAlignment.CENTER);
			document.add(p4);
	      //Month and UserID:
	      String mnUi= "Month: "+ values[1];
	      	t2 = new Text(mnUi);       
		      t2.setFont(font);   
			t2.setFontColor(ColorConstants.CYAN);
			t2.setFontSize(12);
//			t2.setTextAlignment(TextAlignment.RIGHT);
			Paragraph p2 = new Paragraph();
			p2.setMarginTop(10);
			p2.add(t2);
//			p2.setTextAlignment(TextAlignment.CENTER);
			document.add(p2);
			
			String mnUi2= "UserId: "+values[0];
		      Text t3 = new Text(mnUi2);       
			      t3.setFont(font);   
				t3.setFontColor(ColorConstants.CYAN);
				t3.setFontSize(12);
//				t3.set
				Paragraph p3 = new Paragraph();
				p3.add(t3);
//				p2.setTextAlignment(TextAlignment.CENTER);
				document.add(p3);
	      
  //table begins		
		float[] widthVal = {100F,150F,150F,150F,150F,100F};
		Table table = new Table(widthVal);
		table.setMarginTop(20);
	//headers	
		for(int i=0;i<6;i++) {
			Cell c1 = new Cell();
			Paragraph ppc1 = new Paragraph(headers[i]);;
            c1.add(ppc1);
			c1.setTextAlignment(TextAlignment.CENTER);
			c1.setBorder(Border.NO_BORDER);
			table.addHeaderCell(c1);
		}
		for(int i=2;i<len;i++) {
			cells[i-2] = new Cell();
            Paragraph ppc1 = new Paragraph(values[i]);;
			cells[i-2].add(ppc1);
			cells[i-2].setTextAlignment(TextAlignment.CENTER);
			cells[i-2].setBorder(Border.NO_BORDER);
			table.addCell(cells[i-2]);
		}
		for(int i=2;i<len;i+=6) {
			cells[i-2].setUnderline();
			cells[i-2].setFontColor(ColorConstants.BLUE);
		}
		for(int i=4;i<len;i+=6) {
			cells[i-2].setFontColor(ColorConstants.RED);
			cells[i-1].setFontColor(ColorConstants.RED);
		}
		
		document.add(table);
		document.close();
		System.out.println("PDF CREATED!");
		}catch(Exception e) {
			System.out.println("ERROR");
			e.printStackTrace();
		}
	}
//	public static void main(String[] args){
//		createMonthReport cmr=new createMonthReport();
//		String[] values = {"Manraj","September","1991","Rina","4000","08-08-2021","Divyesh","Boi@Rina","1991","Rina","4000","08-08-2021","Divyesh","Boi@Rina"};
//		cmr.setLayout(values);
//	}
}
