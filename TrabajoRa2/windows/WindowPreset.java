package windows;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class WindowPreset {

	public static void preset(JFrame jf) {
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setBackground(Color.GRAY);
		jf.setLocationRelativeTo(null);
		jf.setResizable(false);
		Image icon1 = Toolkit.getDefaultToolkit().getImage("files/icon.png");
		jf.setIconImage(icon1);
	}

}
