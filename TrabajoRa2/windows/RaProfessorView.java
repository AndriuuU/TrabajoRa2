package windows;

import java.awt.FlowLayout;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class RaProfessorView extends JFrame {

	public RaProfessorView() {

		super("RaProfessorView");
		WindowPreset.preset(this);
		setLayout(new FlowLayout());
	}

}
