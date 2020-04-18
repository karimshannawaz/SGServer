package server.user;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Represents a timelog for an employee.
 * We are formatting it with strings because we are not
 * paying actual employees for their time,
 * and it only needs to display how much they worked.
 * 
 * @author Karimshan
 *
 */
public class TimeLog {
	
	private List<String> punchIns;
	private List<String> punchOuts;
	
	public TimeLog() {
		punchIns = new ArrayList<String>();
		punchOuts = new ArrayList<String>();
	}
	
	
	public void punchIn() {
		punchIns.add(getCurrentTime());
	}
	
	public void punchOut(int slot) {
		punchOuts.add(getCurrentTime());
	}
	
	private String getCurrentTime() {
		return new SimpleDateFormat("HH:mm:ss").format(new Date());
	}


	public String getWorkedTime(int slot) {
		String in = punchIns.get(slot);
		String out = punchOuts.get(slot);
		String[] inTok = in.split(":");
		String[] outTok = out.split(":");
		int hrsIn = Integer.parseInt(inTok[0]);
		int minsIn = Integer.parseInt(inTok[1]);
		int secsIn = Integer.parseInt(inTok[2]);
		int hrsOut = Integer.parseInt(outTok[0]);
		int minsOut = Integer.parseInt(outTok[1]);
		int secsOut = Integer.parseInt(outTok[2]);
		int hrsDiff = (hrsOut - hrsIn);
		int minsDiff = (minsOut - minsIn);
		int secsDiff = (secsOut - secsIn);
		return ((hrsDiff < 10 ? "0" : "") + hrsDiff) + ":" + 
			   ((minsDiff < 10 ? "0" : "") + minsDiff) + ":" + 
			   ((secsDiff < 10 ? "0" : "") + secsDiff);
	}
	
	public String getTotalWorkedHours() {
		int hrs = 0;
		int mins = 0;
		int secs = 0;
		for(int i = 0; i < punchIns.size(); i++) {
			String in = punchIns.get(i);
			String out = punchOuts.get(i);
			String[] inTok = in.split(":");
			String[] outTok = out.split(":");
			int hrsIn = Integer.parseInt(inTok[0]);
			int minsIn = Integer.parseInt(inTok[1]);
			int secsIn = Integer.parseInt(inTok[2]);
			int hrsOut = Integer.parseInt(outTok[0]);
			int minsOut = Integer.parseInt(outTok[1]);
			int secsOut = Integer.parseInt(outTok[2]);
			int hrsDiff = (hrsOut - hrsIn);
			int minsDiff = (minsOut - minsIn);
			int secsDiff = (secsOut - secsIn);
			hrs += hrsDiff;
			minsDiff += minsDiff;
			secsDiff += secsDiff;
		}
		return ((hrs < 10 ? "0" : "") + hrs) + ":" + 
		   ((mins < 10 ? "0" : "") + mins) + ":" + 
		   ((secs < 10 ? "0" : "") + secs);
	}

}
