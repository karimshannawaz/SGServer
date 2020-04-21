package server;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import server.menu.Inventory;
import server.user.TimeLog;
import server.utils.JFrameUtils;

/**
 * Handles creating reports which include total revenue for that day, the most
 * popular menu item, hours worked by each employee, and total tips earned in
 * that one day
 * 
 * @author Karimshan Nawaz
 *
 */
public class Reports {
	
	/**
	 * Temporary information
	 */
	public static int newRewardMembers;
	public static int employeesClockedIn;
	public static int activeTables;
	
	/**
	 * Saved information
	 */
	public static double totalTips;
	public static double totalRevenue;
	public static int totalNewRewardMembers;
	public static int totalEmployeesClockedIn;
	public static int totalActiveTables;
	public static String totalHoursWorked = "00:00:00";
	public static Map<String, Integer> mostPopularMI = new HashMap<String, Integer>();
	public static String mostPopularItemName = "Nothing yet!";
	
	
	/**
	 * Updates the information panel with the total time worked
	 * across every employee.
	 */
	public static void generateTotalTimeWorked() {
		int hrs = 0;
		int mins = 0;
		int secs = 0;
		for(TimeLog log : TimeLog.logs) {
			// Loops through all of the employee's time logs.
			for(int i = 0; i < log.getPunchOuts().size(); i++) {
				// Converts the time worked to its hours,
				// minutes and seconds.
				String in = log.getPunchIns().get(i);
				String out = log.getPunchOuts().get(i);
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
		}
		// Returns the time worked in HH mm ss format.
		Reports.totalHoursWorked = ((hrs < 10 ? "0" : "") + hrs) + ":" + 
			((mins < 10 ? "0" : "") + mins) + ":" + 
			((secs < 10 ? "0" : "") + secs);
		Server.ui.infoPanel.updateLabels();
	}
	
	/**
	 * Updates the most popular menu item on the server information panel.
	 */
	public static void updateMostPopularMI() {
		int mostQty = 0;
		String mostPopular = "";
		for(Map.Entry<String, Integer> entry : mostPopularMI.entrySet()) {
			String name = entry.getKey();
			int qty = entry.getValue();
			if(qty > mostQty) {
				mostQty = qty;
				mostPopular = ""+name;
			}
		}
		mostPopularItemName = ""+mostPopular;
		Server.ui.infoPanel.updateLabels();
	}

	/**
	 * Generates a report to save to a file. Either during the day
	 * or at the end of the day
	 * @param endOfDay
	 */
	public static void generateReport(boolean endOfDay) {
		if(!endOfDay) {
			boolean choice = JFrameUtils.confirmDialog("Reports", 
				"Are you sure you would like to generate a mid-day report?");
			if(!choice) {
				return;
			}
		}
		try {
			DateFormat d = new SimpleDateFormat("MMMM dd, yyyy");
			File f = new File("data/reports/"+d.format(new Date())+".txt");
			BufferedWriter writer = new BufferedWriter(new FileWriter(f, true));
			if(endOfDay) {
				writer.write("[------------------- End of Day Report for "+(new Date())+" -------------------]");
			}
			else {
				writer.write("[---------------------- Mid Day Report for "+(new Date())+" -------------------]");
			}
			writer.write("\n\n\t\t\t\t\t\t\tTotal Active Tables: "+Reports.totalActiveTables);
			writer.write("\n\t\t\t\t\t\t\tTotal Revenue: "+Reports.totalRevenue);
			writer.write("\n\t\t\t\t\t\t\tTotal Tips: "+Reports.totalTips);
			writer.write("\n\t\t\t\t\t\t\tMost Popular Menu Item: "+Reports.mostPopularItemName);
			writer.write("\n\t\t\t\t\t\t\tNew Reward Members: "+Reports.newRewardMembers);
			writer.write("\n\t\t\t\t\t\t\tTotal Employees Clocked In: "+Reports.totalEmployeesClockedIn);
			writer.write("\n\t\t\t\t\t\t\tTotal Hours Worked Today: "+Reports.totalHoursWorked);
			writer.write("\n\n[------------------------------------------------------------------------------------------]");
			writer.write("\n\n\n");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
