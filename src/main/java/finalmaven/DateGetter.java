package finalmaven;
import java.time.LocalDate;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.sql.*;
public class DateGetter {
    Conn c = new Conn();
    final String[] monthsNames = {"January","February","March","April","May","June","July","August","September",
                                    "October","November","December"};
    LocalDate curr = LocalDate.now();
    public int currYear =curr.getYear();
    DateGetter(){}
    public String adjustDate(String s1){
        String s2;
        s2 = s1.substring(0, 2);
        s2+="-"+s1.substring(3,5)+"-"+s1.substring(6);
        System.out.println(s2);
        return s2;
    }
    ArrayList<String> al = new ArrayList<String>();
        public void isDateinCurrentYear(String polId,String fpd,String lpd,int w) throws Exception{
        Date d1 = new SimpleDateFormat("dd-MM-yy").parse(fpd);
        LocalDate ld1 = Instant.ofEpochMilli(d1.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        Date d2 = new SimpleDateFormat("dd-MM-yy").parse(lpd);
        LocalDate ld2 = Instant.ofEpochMilli(d2.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        if(ld2.getYear()>=currYear){
            LocalDate dtemp;
            if(ld1.getYear()!=currYear){
                dtemp= ld1.withYear(currYear-1);
            }else{
                // the first pay isitself to be added!
                dtemp=ld1;
                String query = "insert into reminders values('"+polId+"',"+dtemp.getMonthValue()+
                                    ",1)";//1 stands for premium kind
                al.add(query);
            }
            while(dtemp.getYear()<currYear+1){
                dtemp=dtemp.plusMonths(w);
                if(dtemp.getYear()==currYear){
                    String query = "insert into reminders values('"+polId+"',"+dtemp.getMonthValue()+
                                    ",1)";//1 stands for premium kind
                    al.add(query);
                }
            }    
        }
    }
        public int  queryMaturityDate(String matDate) throws Exception{
            // String matDate = "04-03-32";
            Date d3 = new SimpleDateFormat("dd-MM-yy").parse(matDate);
            LocalDate ld3 = Instant.ofEpochMilli(d3.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
            if(ld3.getYear() == currYear){
                return ld3.getMonthValue();
            }
            return -1;
        }
        // "ONE TIME" , "YEARLY" , "HALF YEARLY" , "QUARTERLY" , "MONTHLY"
        private int setIncrement(String pc){
            if(pc.equals("YEARLY")){
                return 12;
            }else if(pc.equals("HALF YEARLY")){
                return 6;
            }else if(pc.equals("QUARTERLY")){
                return 4;
            }else if(pc.equals("MONTHLY")){
                return 1;
            }
            return 0;
        }
        public void generateYearReminder(){
            String getPolicyDetails = "select policyId,firstPDate,lastPDate,premiumCycle,dateOfMaturity from policies;";
            try{
                ResultSet rs =c.s.executeQuery(getPolicyDetails);
                while(rs.next()){
                    System.out.println(rs.getString(4));
                    //driver code here
                    int inc = setIncrement(rs.getString(4));
                    
                    
                    if(inc!=0){
                        //check for premiums
                        isDateinCurrentYear(rs.getString(1), rs.getString(2), rs.getString(3),inc);
                    }else{
                        //add it to table;
                        int p=queryMaturityDate(rs.getString(2));
                        String query= "insert into reminders values('"+rs.getString(1)+"',"+p+",1)";
                        al.add(query);
                    }
                    // add Maturity 
                    int mQ =queryMaturityDate(rs.getString(5));
                    if(mQ!=-1){
                        String query= "insert into reminders values('"+rs.getString(1)+"',"+mQ+",0)";
                        al.add(query);
                    }
                    System.out.println(al);
                }
                for(String s:al){
                    try{
                    c.s.executeUpdate(s);
                    }catch(Exception e){
                        System.out.println("Error in updating reminder db");
                    }
                }
            }catch(Exception e){
                System.out.println("Error in yearReminder");
                e.printStackTrace();
            }
        }
        private LocalDate LocalDateGetter(String init) throws Exception{
            Date d3 = new SimpleDateFormat("dd-MM-yy").parse(init);
            LocalDate ld3 = Instant.ofEpochMilli(d3.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
            return ld3;
        }
        private LocalDate LocalDateGetter2(String init) throws Exception{
            Date d3 = new SimpleDateFormat("yyyy-MM-dd").parse(init);
            LocalDate ld3 = Instant.ofEpochMilli(d3.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
            return ld3;
        }
        public String modToCurrentYear(String init) throws Exception{
            try{
                LocalDate ld3 = LocalDateGetter(init);
                LocalDate ld4 =ld3.withYear(currYear);
                return ld4.toString();
            }catch(Exception e){
                return "N/A";
            }
            
        }
        public String getMonthName(String init){
            try{
            LocalDate ld3 =LocalDateGetter2(init);
            System.out.println("From dg :"+ld3);
            return ld3.getMonth().toString();
            }catch(Exception e){
                e.printStackTrace();
                return "N/A";
            }
        }
        public String getMonthName(int num){
            return monthsNames[num-1];
        }
        public String getYear(String init){
            try{
            LocalDate ld3 =LocalDateGetter(init);
            return Integer.toString(ld3.getYear());
            }catch(Exception e){
                e.printStackTrace();
                return "N/A";
            }
        }

    public static void main(String[] args){
        DateGetter dg = new DateGetter();
        // try{dg.isDateinCurrentYear();dg.queryMaturityDate();}catch(Exception e){}
        try{dg.getMonthName("08-08-2008");}catch(Exception e){};
        dg.generateYearReminder();
    }
}
