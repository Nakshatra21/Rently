package problem;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

class Entry {

	private int hr,mins,secs,phoneNo;
	private double billAmt;
	private int totalSecs;
	
	public Entry(int hr,int mins, int secs, int phoneNo, double billAmt,int totalSecs) {
		this.hr = hr;
		this.mins = mins;
		this.secs = secs;
		this.phoneNo = phoneNo;
		this.billAmt = billAmt;
		this.totalSecs = totalSecs;
	}

	public int getPhoneNo() {
		return phoneNo;
	}

	public double getBillAmt() {
		return billAmt;
	}
	public void setBillAmt(double amount) {
		 this.billAmt = amount;
	}

	public int getTotalSecs() {
		return totalSecs;
	}
	public void setTotalSecs(int value) {
		this.totalSecs = value;
	}
	
}

public class Main {

	public static HashMap<Integer, Entry> hm = new HashMap<Integer, Entry>();
	public static double calculateBillAmt(int totalSecs) {
		double bill;
		if(totalSecs <300) {
			bill = (totalSecs * 3);
			return bill;
		}
		 else {
             int hours  = (totalSecs/3600);
             int mins = (totalSecs%3600)/60;
             int secs = (totalSecs%3600)%60;
             bill = (hours * 60 * 150) + (mins * 150);
             if(secs!= 0){
                bill += 150;
             }
                return bill;
            }
	}
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		String callLog = scan.nextLine().trim();
		scan.close();
		int hr,mins,secs,phoneNo, totalSecs;
		double billAmt;
		String logs[] = callLog.split(" ");
		for(int i =0; i<logs.length; i++) {
			
			hr = Integer.parseInt(logs[i].substring(0,2));
			mins = Integer.parseInt(logs[i].substring(3,5));
			secs = Integer.parseInt(logs[i].substring(6,8));
			phoneNo = Integer.parseInt(logs[i].substring(9).replaceAll("[-]", ""));
			totalSecs = (hr*60) + (60 * mins) + secs;
			billAmt = calculateBillAmt(totalSecs);
			if(!hm.containsKey(phoneNo))
				hm.put(phoneNo,new Entry(hr,mins,secs,phoneNo,billAmt,totalSecs));
			else {
				hm.get(phoneNo).setBillAmt(hm.get(phoneNo).getBillAmt() + billAmt);
				int value = hm.get(phoneNo).getTotalSecs() + totalSecs; 
				hm.get(phoneNo).setTotalSecs(value);
			}
		}
		Iterator<Map.Entry<Integer,Entry>> itr = hm.entrySet().iterator();
		
		int max = -1;
		int max_phoneNo = 0;
		while(itr.hasNext()) {
			Map.Entry<Integer, Entry> 	value = itr.next();
			if(value.getValue().getTotalSecs() > max) {
				max = value.getValue().getTotalSecs();
				max_phoneNo = value.getValue().getPhoneNo();
				
			}else if(value.getValue().getTotalSecs() == max)	
				max_phoneNo = Math.min(value.getValue().getPhoneNo(),max_phoneNo);
			
		}
		hm.get(max_phoneNo).setBillAmt(0);
		
		Iterator<Map.Entry<Integer,Entry>> itr1 = hm.entrySet().iterator();
		double totalBillAmount = 0;
		while(itr1.hasNext()) {
			Map.Entry<Integer, Entry> 	value = itr1.next();
			totalBillAmount  += value.getValue().getBillAmt();
		}
		System.out.println(totalBillAmount);
	}
}
