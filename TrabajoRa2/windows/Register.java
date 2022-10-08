package windows;

import java.awt.FlowLayout;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Register extends JFrame {

	public Register() {

		super("Register");
		WindowPreset.preset(this);
		setLayout(new FlowLayout());
	}

}
