package server.utils;

import java.util.Random;

public class Constants {
	
	public static final String NAME = "Seven Guys";
	public static final int PORT = 43594;
	
	public static int generateNumber(int minimum, int maximum) {
		if (minimum > maximum) {
			return 0;
		}
		Random r = new Random();
		long range = (long) maximum - (long) minimum + 1;
		long fraction = (long) (range * r.nextDouble());
		return  ((int) (fraction + minimum));
	}
	
}
