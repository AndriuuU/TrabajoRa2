package windows;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class WindowPreset {

	public static void preset(JFrame jf) {
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.getContentPane().setBackground(Color.LIGHT_GRAY);
		jf.setLocationRelativeTo(null);
		jf.setResizable(false);
		Image icon1 = Toolkit.getDefaultToolkit().getImage("files/icon.png");
		jf.setIconImage(icon1);
	}

	public static void buttonPreset(JButton B, String T, String U) {

		Icon ic = new ImageIcon(U);
		B.setBackground(Color.LIGHT_GRAY);
		B.setBorderPainted(false);
		B.setToolTipText(T);
		B.setIcon(ic);

	}

}
