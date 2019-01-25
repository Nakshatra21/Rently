
package basics;
import java.util.Map;
import java.util.Iterator;
import java.util.HashMap;
class Entry{
    int hr,mins,secs,totalSecs, billAmt,phoneNo;
    public Entry(int hr, int mins, int secs, int totalSecs, int billAmt, int phoneNo) {
    this.hr = hr;
    this.mins = mins;
    this.secs = secs;
    this.totalSecs = totalSecs;
    this.billAmt = billAmt;
    this.phoneNo = phoneNo;
    }

    public void setTotalSecs(int secs){
        this.totalSecs = secs;
    }
    public void setBillAmt(int billAmt){
        this.billAmt = billAmt;
    }
    public int getTotalSecs(){
        return this.totalSecs;
    }
    
    public int getBillAmt(){
        return this.billAmt;
    }
    public int getPhoneNo(){
        return this.phoneNo;
    }
}
public class Solution{
    
    public static HashMap<Integer, Entry> hm  = new HashMap<Integer, Entry>();

    public static int solution(String S){
        int result = 0;
        String logs[] = S.split(" ");
        int hr,mins,secs,phoneNo,totalSecs,billAmt;
       
        for(int i=0; i< logs.length; i++){  
                                    
        hr =    Integer.parseInt(logs[i].substring(0,2));
        mins =    Integer.parseInt(logs[i].substring(3,5));
        secs =     Integer.parseInt(logs[i].substring(6,8));
        phoneNo= Integer.parseInt(logs[i].substring(9).replaceAll("[-]",""));
        totalSecs = (hr * 3600) + (mins * 60) + secs;
        billAmt = Solution.calculateBillAmt(totalSecs);
        //System.out.println(billAmt);
        
        
        if(!hm.containsKey(phoneNo))
            hm.put(phoneNo, new Entry(hr,mins,secs,totalSecs,billAmt,phoneNo));
        else{
            int v1 = hm.get(phoneNo).getBillAmt() + billAmt;
            int v2 = hm.get(phoneNo).getTotalSecs() + totalSecs;
            hm.get(phoneNo).setBillAmt(v1);
            hm.get(phoneNo).setTotalSecs(v1);
        }
        }
            // Identifying max totalsecs and its phoneNo
       System.out.println(hm.size());
        
        Iterator<Map.Entry<Integer,Entry>> itr = hm.entrySet().iterator();
        int max = -1;
        int max_phoneNo = 0;
        while(itr.hasNext()){

            Map.Entry<Integer, Entry> m = itr.next();
            if(m.getValue().getTotalSecs() > max){
                 max = m.getValue().getTotalSecs();
                 max_phoneNo = m.getValue().getPhoneNo();
            }else if(m.getValue().getTotalSecs() == max){
                 max_phoneNo = Math.min(m.getValue().getPhoneNo(),max_phoneNo);    
            }            
                
        }
        
        
        
        // Reseting billAmt for longest call duration
        System.out.println(hm.get(max_phoneNo).getBillAmt());    
        hm.get(max_phoneNo).setBillAmt(0);
           

        // calculating overall monthly bill
        Iterator<Map.Entry<Integer,Entry>> itr1 = hm.entrySet().iterator();
        
        while(itr1.hasNext()){

            Map.Entry<Integer, Entry> m = itr1.next();
               System.out.println(result);
                result += m.getValue().getBillAmt();        
                
        }
        
        
        
    //    System.out.println(result);
        return result;
    }

    public static int calculateBillAmt(int totalSecs){

        int bill =0, hr,mins,secs;
            if(totalSecs < 300)
                    bill = (totalSecs * 3);
            else{
                hr = (totalSecs/3600);
                mins = (totalSecs % 3600) /60;
                secs = (totalSecs%3600)%60;
                
                bill = (hr * 60 * 150) + (mins * 150);
                if(secs != 0) {
                    bill += 150;
                	}
                }
            return bill;
    }
    public static void main(String[] args){
        
        int s1 = Solution.solution("00:05:17,959-720-018 00:19:38,750-222-197 00:14:21,959-720-018 00:17:01,892-545-277");
    
        System.out.println(s1);
    }

}