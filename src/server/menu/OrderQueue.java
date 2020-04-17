package server.menu;

import java.util.ArrayList;
import java.util.List;

/**
 * Holds information for what table currently has an order in
 * the queue.
 * 
 * @author Karimshan
 *
 */
public class OrderQueue {

	public static List<Order> unfulfilledOrders = new ArrayList<Order>();
	public static List<Order> unpaidOrders = new ArrayList<Order>();
	public static List<Order> paidOrders = new ArrayList<Order>();
	
}
