package server.user;

import java.util.ArrayList;
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
		
		return null;
	}


	public String getWorkedTime(int slot) {
		String elapsed = "";
		String in = punchIns.get(slot);
		String out = punchOuts.get(slot);
		return elapsed;
	}
	
	public String getTotalWorkedHours() {
		String total = "";
		return total;
	}

}
