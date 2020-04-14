package server.utils;

import java.awt.Toolkit;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * SERVER SIDED
 * 
 * @author Karimshan
 *
 */
public class JFrameUtils {
	
	public static Object inputDialog(String title, Object message) {
		return JOptionPane.showInputDialog(new JFrame(), message, title, JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static boolean confirmDialog(String title, Object message) {
		int result = JOptionPane.showConfirmDialog(new JFrame(), message, title, JOptionPane.WARNING_MESSAGE);
		return result == 0 ? true : false;
	}
	
	public static void showMessage(String title, Object message) {
		JOptionPane.showMessageDialog(new JFrame(), message, title, JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static File selectFile(boolean directory) {
		JFileChooser chooser = new JFileChooser();
		File file = null;
		chooser.setFileSelectionMode(directory ? JFileChooser.DIRECTORIES_ONLY : JFileChooser.FILES_ONLY);
		if(chooser.showOpenDialog(new JFrame()) == JFileChooser.APPROVE_OPTION) {
			try {
				file = chooser.getSelectedFile();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(new JFrame(), "Error occured while selecting path, make sure it's valid and exists.");
			}
		}
		return file;
	}

	public static void setFrameIcon(JFrame frame, String path) {
		try {
			frame.setIconImage(Toolkit.getDefaultToolkit().getImage(path));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
