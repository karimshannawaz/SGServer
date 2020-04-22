package server.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.DecimalFormat;
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
		return ((int) (fraction + minimum));
	}
	
	@SuppressWarnings("resource")
	public static void copyFile(File sourceFile, File destFile)
			throws IOException {
		if (!destFile.exists()) {
			destFile.createNewFile();
		}
		FileChannel source = null;
		FileChannel destination = null;
		try {
			source = new FileInputStream(sourceFile).getChannel();
			destination = new FileOutputStream(destFile).getChannel();
			destination.transferFrom(source, 0, source.size());
		} finally {
			if (source != null) {
				source.close();
			}
			if (destination != null) {
				destination.close();
			}
		}
	}
	
	/**
	 * Formats a decimal to be displayed as currency.
	 * @param num
	 * @return
	 */
	public static String decimalF(double num) {
		return DecimalFormat.getCurrencyInstance().format(num);
	}

}
