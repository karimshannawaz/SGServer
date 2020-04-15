package server;

/**
 * Handles creating reports which include total revenue for that day, the most
 * popular menu item, hours worked by each employee, and total tips earned in
 * that one day.
 * 
 * @author Karimshan Nawaz
 *
 */
public class Reports {

	public static double totalTips;
	public static double totalRevenue;
	
	public static int newRewardMembers;
	public static int employeesClockedIn;
	public static int activeTables;
	
	public static String getMostPopularMenuItem() {
		return "Nothing yet!";
	}
	
	public static int getTotalHoursWorked() {
		return 0;
	}

}
