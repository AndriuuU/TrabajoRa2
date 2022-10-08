package windows;

import java.awt.FlowLayout;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class AdminWindow extends JFrame {

	public AdminWindow() {

		super("AdminWindow");
		WindowPreset.preset(this);
		setLayout(new FlowLayout());
	}

}
