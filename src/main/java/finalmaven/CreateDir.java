package finalmaven;
import java.io.File;
public class CreateDir {
   public CreateDir(){}
   public void Dirmaker(String usrNm,String memName) {
      try {
         String fName = "db/"+usrNm +"/" +memName;
         File file = new File(fName);
         // file.createNewFile();
         boolean flag = file.mkdir();
         System.out.print("Directory created? " + flag);
      } catch(Exception e) {
         e.printStackTrace();
      }
   }
   public void Dirmaker(String memName) {
      try {
         String fName = "db/"  +memName;
         File file = new File(fName);
         // file.createNewFile();
         boolean flag = file.mkdir();
         System.out.print("Directory created? " + flag);
      } catch(Exception e) {
         e.printStackTrace();
      }
   }
   public static void main(String[] args){
      CreateDir cd  = new CreateDir();
      cd.Dirmaker("Manraj","manan");
   }
}