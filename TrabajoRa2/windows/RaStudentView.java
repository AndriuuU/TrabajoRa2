package windows;

import java.awt.FlowLayout;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class RaStudentView extends JFrame {

	public RaStudentView() {

		super("RaStudentView");
		WindowPreset.preset(this);
		setLayout(new FlowLayout());
	}

}
