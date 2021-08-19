package finalmaven;
import java.sql.*;

public class monthPremiumReportFire {
    String[][] toPdfGenerator;
    String pDate="N/A";
    DateGetter dg = new DateGetter();
    monthPremiumReportFire(){}
    public String[][] arrayGiver(int num){
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
                    String AgentNameQuery = "select name from agent where agentId="+rs1.getInt(6);
                    Conn c2 = new Conn();
                    ResultSet rs2 = c2.s.executeQuery(AgentNameQuery);
                    rs2.next();
                    pDate = dg.modToCurrentYear(rs1.getString(4));
                    // System.out.println(rs.getInt(2));
                    toPdfGenerator[i][0] = rs1.getString(1);
                    toPdfGenerator[i][1] = rs1.getString(2);
                    toPdfGenerator[i][2] = rs1.getString(3);
                    toPdfGenerator[i][3] = pDate;
                    toPdfGenerator[i][4] = rs2.getString(1);
                    toPdfGenerator[i][5] = rs1.getString(5);
                }catch(Exception e){
                    e.printStackTrace();
                }
                i++;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        // for(int i=0;i<toPdfGenerator.length;i++){
        //     for(int j=0;j<6;j++){
        //         System.out.println(toPdfGenerator[i][j]);
        //     }
        // }
//db ENDStatic void main(String[] args) {
    //     monthPremiumReportFire mprf= new monthPremiumReportFire();
    //     mprf.arrayGiver(8);
    // }
            return toPdfGenerator;
    }
    // public s
}

