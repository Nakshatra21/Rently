// you can also use imports, for example:
// import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");
import java.util.*; 
class Entry{
    public int hr,mins,secs,totalSecs,billAmt,phoneNo;
    
    public Entry(int hr, int mins, int secs, int totalSecs,int billAmt, int phoneNo){
        
        this.hr = hr;
        this.mins = mins;
        this.secs = secs;
        this.totalSecs = totalSecs;
        this.billAmt = billAmt;
        this.phoneNo = phoneNo;
    }
    
    public int getTotalSecs(){
        return this.totalSecs;
    }
    public int getPhoneNo(){
        return this.phoneNo;
    }
    public int getBillAmt(){
        return this.billAmt;
    }
    public void setTotalSecs(int secs){
        this.totalSecs = secs;
    }
    public void setBillAmt(int bill){
        this.billAmt = bill;
    }
}
public class Solution {
   public static  HashMap<Integer, Entry> hm = new HashMap<Integer,Entry>();

    public static void main(String[] args){
        
        Solution s = new Solution();
       int result =  s.solution("00:05:17,959-720-018 00:03:00,959-720-018 00:04:59,959-720-018");
        System.out.println(result);    
    }

    public int solution(String S) {
    int hr, mins, secs, totalSecs, billAmt, phoneNo;
    String[] logs = S.split(" ");
    
    for(int i=0; i< logs.length; i++){
    
        hr = Integer.parseInt(logs[i].substring(0,2));
        mins = Integer.parseInt(logs[i].substring(3,5));
        secs = Integer.parseInt(logs[i].substring(6,8));
        phoneNo = Integer.parseInt(logs[i].substring(9).replaceAll("[-]",""));
        totalSecs = (hr * 3600) + (mins * 60) + secs;
        billAmt = Solution.calculateBillAmount(totalSecs);
        System.out.println(billAmt);
        if(!hm.containsKey(phoneNo))   
            hm.put(phoneNo, new Entry(hr, mins, secs, totalSecs, billAmt, phoneNo));  
         else{
             int v1 = hm.get(phoneNo).getTotalSecs() + totalSecs;
             int v2 = hm.get(phoneNo).getBillAmt() + billAmt;
             hm.get(phoneNo).setTotalSecs(v1);
             hm.get(phoneNo).setBillAmt(v2);
         }
         
    }
    
    Iterator<Map.Entry<Integer,Entry>> itr = hm.entrySet().iterator();
    int max = -1;
    int max_phoneNo = 0;
    while(itr.hasNext()){
        
        Map.Entry<Integer, Entry> m = itr.next();
        if(m.getValue().getTotalSecs() > max){
            max = m.getValue().getTotalSecs();
            max_phoneNo = m.getValue().getPhoneNo();
            }
        else if (m.getValue().getTotalSecs() == max){
             max_phoneNo = Math.min(max_phoneNo,m.getValue().getPhoneNo());
        }
    }
    hm.get(max_phoneNo).setBillAmt(0);
    
    Iterator<Map.Entry<Integer,Entry>> itr1 = hm.entrySet().iterator();
    int result = 0;
    while(itr1.hasNext()){
        
        Map.Entry<Integer, Entry> m = itr1.next();
        result += m.getValue().getBillAmt();
        
    }
    
    return result;
    }
    
    public static int calculateBillAmount(int totalSecs){
        int bill =0,hr, mins, secs;
        if(totalSecs < 300)
            return (totalSecs * 3);
        else{
            hr = (totalSecs/3600);
            mins = (totalSecs%3600)/60;
            secs = (totalSecs%3600)%60;
            bill = (hr * 60 * 150) + (mins * 150);
            if(secs != 0)
                bill += 150;
        }
        return bill;
    }
    
}
