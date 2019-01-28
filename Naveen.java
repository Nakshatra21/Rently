import java.util.*;

class Entry {
    public int hr;
    public int min;
    public int secs;
    public int phoneNo;
    public int totalSecs;
    public int billAmt;
    
    public Entry(int hr, int min, int secs, int phoneNo, int totalSecs, int billAmt) {
        this.hr = hr;
        this.min = min;
        this.secs = secs;
        this.phoneNo = phoneNo;
        this.totalSecs = totalSecs;
        this.billAmt = billAmt;
    }
    public int getPhoneNo(){
        return this.phoneNo;
    }
    public int getTotalSecs(){
        return this.totalSecs;
    }
    public int getBillAmt(){
        return this.billAmt;
    }
    public void setBillAmt(int billAmt){
        this.billAmt = billAmt;
    }
    public void setTotalSecs(int value){
        this.totalSecs= value;
    }
}
public class Solution {
    
    public static HashMap<Integer, Entry> hm = new HashMap<Integer, Entry>();
    
    public static void main(String[] args){
        
        Solution s1 = new Solution();
        int res = s1.solution("00:05:17,959-720-018 00:03:00,959-720-018 00:04:59,959-720-018");
        System.out.println(res);
    }
    
    public int solution(String S) {
        int hr, min, secs, phoneNo, totalSecs, billAmt;
        String[] logs = S.split(" ");
        for(int i=0; i<logs.length; i++){
            hr = Integer.parseInt(logs[i].substring(0,2));
            min = Integer.parseInt(logs[i].substring(3,5));
            secs = Integer.parseInt(logs[i].substring(6,8));
            phoneNo = Integer.parseInt(logs[i].substring(9).replaceAll("[-]",""));
            totalSecs = (hr * 3600) + (min * 60) + secs;
            billAmt = Solution.calculateBillAmt(totalSecs,hr,min,secs);
            
            if(!hm.containsKey(phoneNo))
                hm.put(phoneNo, new Entry(hr, min, secs, phoneNo, totalSecs, billAmt));
            else{
                int v1 = hm.get(phoneNo).getTotalSecs() + totalSecs;
                int v2 = hm.get(phoneNo).getBillAmt() + billAmt;
                hm.get(phoneNo).setTotalSecs(v1);
                hm.get(phoneNo).setBillAmt(v2);
            } 
        }
        Iterator<Map.Entry<Integer, Entry>> itr = hm.entrySet().iterator();
        int max = -1;
        int max_phoneNo = 0;
        while(itr.hasNext()){
            Map.Entry<Integer, Entry> m = itr.next();
            if(m.getValue().getTotalSecs() > max){
                max = m.getValue().getTotalSecs();
                max_phoneNo = m.getValue().getPhoneNo();
            }else if(m.getValue().getTotalSecs() == max){
                max_phoneNo = Math.min(max_phoneNo, m.getValue().getPhoneNo());
            }
        }
        hm.get(max_phoneNo).setBillAmt(0);
        Iterator<Map.Entry<Integer, Entry>> itr1 = hm.entrySet().iterator();
        int sum = 0;
        while(itr1.hasNext()){
            Map.Entry<Integer, Entry> m = itr1.next();
            sum += m.getValue().getBillAmt();
        }
        return sum;   
    }
    public static int calculateBillAmt(int totalSecs, int hr, int min, int secs){
        int bill = 0;
        if(totalSecs < 300)
            bill = (totalSecs * 3);
        else{
            bill = (hr * 60 * 150) + (min * 150);
            if(secs!=0)
                bill += 150;
        }
        System.out.println("Bill  Amt : "+ bill);
        return bill;   
    }
}
