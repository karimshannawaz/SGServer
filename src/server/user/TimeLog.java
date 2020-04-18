package server.user;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.table.DefaultTableModel;

import server.Server;
import server.utils.JFrameUtils;

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

	/**
	 * Holds a global list of time logs for every employee 
	 * on a given day.
	 */
	public static List<TimeLog> logs = new ArrayList<TimeLog>();
	
	// Holds the employee's ID.
	private String id;

	// Holds a list of punch ins and outs.
	private List<String> punchIns;
	private List<String> punchOuts;

	/**
	 * Creates a constructor of this log.
	 */
	public TimeLog() {
		punchIns = new ArrayList<String>();
		punchOuts = new ArrayList<String>();
	}
	
	/**
	 * Represents the employee's ID.
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the employee ID
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Returns the list of punch ins.
	 * @return
	 */
	public List<String> getPunchIns() {
		return punchIns;
	}

	/**
	 * Returns the list of punch outs.
	 * @return
	 */
	public List<String> getPunchOuts() {
		return punchOuts;
	}

	/**
	 * Adds a punch in to the employee's list.
	 */
	public void punchIn() {
		punchIns.add(getCurrentTime());
	}

	/**
	 * Adds a punch out to the employee's list.
	 */
	public void punchOut() {
		punchOuts.add(getCurrentTime());
	}

	/**
	 * Returns the current time in HH mm ss.
	 * @return
	 */
	private String getCurrentTime() {
		return new SimpleDateFormat("HH:mm:ss").format(new Date());
	}
	
	/**
	 * Returns the time worked based on time now - clock in time.
	 * @return
	 */
	public String getWorkedTime() {
		return getWorkedTime(-1, true);
	}

	/**
	 * Returns the time worked based on when they clocked in/out.
	 * @return
	 */
	public String getWorkedTime(int slot, boolean now) {
		// Converts the time worked to its hours,
		// minutes and seconds.
		String in = punchIns.get(now ? punchIns.size() -1 : slot);
		String out = now ? getCurrentTime() : punchOuts.get(slot);
		String[] inTok = in.split(":");
		String[] outTok = out.split(":");
		// Calculates the time worked.
		int hrsIn = Integer.parseInt(inTok[0]);
		int minsIn = Integer.parseInt(inTok[1]);
		int secsIn = Integer.parseInt(inTok[2]);
		int hrsOut = Integer.parseInt(outTok[0]);
		int minsOut = Integer.parseInt(outTok[1]);
		int secsOut = Integer.parseInt(outTok[2]);
		int hrsDiff = (hrsOut - hrsIn);
		int minsDiff = (minsOut - minsIn);
		// Calculates the difference in the mins/seconds and adjusts
		// hours/mins based on if the difference is less than 0.
		if(minsDiff < 0) {
			minsDiff = minsDiff + 60;
			hrsDiff = hrsDiff - 1;
		}
		int secsDiff = (secsOut - secsIn);
		if(secsDiff < 0) {
			secsDiff = secsDiff + 60;
			minsDiff = minsDiff - 1;
		}
		// Returns the time worked in HH mm ss format.
		return ((hrsDiff < 10 ? "0" : "") + hrsDiff) + ":" + 
		((minsDiff < 10 ? "0" : "") + minsDiff) + ":" + 
		((secsDiff < 10 ? "0" : "") + secsDiff);
	}

	/**
	 * Returns the total number of hours worked from the employee's 
	 * punch in and out lists.
	 * @return
	 */
	public String getTotalWorkedHours() {
		int hrs = 0;
		int mins = 0;
		int secs = 0;
		// Loops through all of the employee's time logs.
		for(int i = 0; i < punchIns.size(); i++) {
			// Converts the time worked to its hours,
			// minutes and seconds.
			String in = punchIns.get(i);
			String out = punchOuts.get(i);
			String[] inTok = in.split(":");
			String[] outTok = out.split(":");
			// Calculates the time worked.
			int hrsIn = Integer.parseInt(inTok[0]);
			int minsIn = Integer.parseInt(inTok[1]);
			int secsIn = Integer.parseInt(inTok[2]);
			int hrsOut = Integer.parseInt(outTok[0]);
			int minsOut = Integer.parseInt(outTok[1]);
			int secsOut = Integer.parseInt(outTok[2]);
			int hrsDiff = (hrsOut - hrsIn);
			int minsDiff = (minsOut - minsIn);
			// Calculates the difference in the mins/seconds and adjusts
			// hours/mins based on if the difference is less than 0.
			if(minsDiff < 0) {
				minsDiff = minsDiff + 60;
				hrsDiff = hrsDiff - 1;
			}
			int secsDiff = (secsOut - secsIn);
			if(secsDiff < 0) {
				secsDiff = secsDiff + 60;
				minsDiff = minsDiff - 1;
			}
			hrs += hrsDiff;
			mins += minsDiff;
			secs += secsDiff;
		}
		// Adjusts the hours and mins if secs or mins
		// were greater than 60. This adjusts it to be
		// normal time format
		if(secs >= 60) {
			int newSecs = (secs % 60);
			int remainder = (secs / 60);
			mins += remainder;
			secs = newSecs;
		}
		if(mins >= 60) {
			int newMins = (mins % 60);
			int remainder = (mins / 60);
			hrs += remainder;
			mins = newMins;
		}
		// Returns the time worked in HH mm ss format.
		return ((hrs < 10 ? "0" : "") + hrs) + ":" + 
		((mins < 10 ? "0" : "") + mins) + ":" + 
		((secs < 10 ? "0" : "") + secs);
	}
	
	/**
	 * Returns true if the time logs contains the specified
	 * employee ID.
	 * @param id
	 * @return
	 */
	public static boolean containsEmployee(String id) {
		for(TimeLog log : TimeLog.logs) {
			if(log.getId().equals(id)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns true if the time logs contains the specified
	 * employee ID.
	 * @param id
	 * @return
	 */
	public static TimeLog getLog(String id) {
		for(int i = 0; i < TimeLog.logs.size(); i++) {
			if(TimeLog.logs.get(i).getId().equals(id)) {
				return TimeLog.logs.get(i);
			}
		}
		return null;
	}

	/**
	 * The employee clocks in with the specified employee ID
	 * and employee's name.
	 * @param id
	 * @param name
	 * @return
	 */
	public static boolean clockIn(String id, String name) {
		boolean exists = TimeLog.containsEmployee(id);
		if(exists) {
			TimeLog log = TimeLog.getLog(id);
			boolean doubleClock = log.getPunchIns().size() > log.getPunchOuts().size();
			if(doubleClock) {
				JFrameUtils.showMessage("Time Logs", 
					"You are currently already clocked in, please clock out first before trying again.\n"
					+ "You have currently worked for: "+(log.getWorkedTime())+" so far.");
				return false;
			}
			log.punchIn();
		}
		else {
			TimeLog log = new TimeLog();
			log.setId(id);
			log.punchIn();
			logs.add(log);
			((DefaultTableModel) Server.ui.timelogPanel.table.getModel()).addRow(
				new Object[] {
					new String(id),
					new String(name),
					new String(log.getPunchIns().get(0)),
					new String(""),
					new String("")
				}
			);
			return true;
		}
		Server.ui.timelogPanel.updateTable(id, true);
		return true;
	}

	/**
	 * The employee clocks out with the specified employee ID
	 * and employee's name.
	 * @param id
	 * @param name
	 * @return
	 */
	public static boolean clockOut(String id, String name) {
		boolean exists = TimeLog.containsEmployee(id);
		if(exists) {
			TimeLog log = TimeLog.getLog(id);
			boolean doubleClockOut = log.getPunchIns().size() == log.getPunchOuts().size();
			if(doubleClockOut) {
				JFrameUtils.showMessage("Time Logs", 
					"You are not currently clocked in. Please clock in again to be able to perform this action.\n"
					+ "Your last total amount of time worked was: "+log.getTotalWorkedHours());
				return false;
			}
			log.punchOut();
		}
		else {
			JFrameUtils.showMessage("Time Logs", "This should not be happening, please contact Karimshan and let him know.");
			return false;
		}
		Server.ui.timelogPanel.updateTable(id, false);
		return true;
	}

}
