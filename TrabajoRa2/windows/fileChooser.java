package windows;


import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class fileChooser {
	
	public static String extension;
	public static Path sourcer;
	public static Path destination;

	public static void start() {
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos de Imágenes", "bmp", "gif", "ico", "jpg",
				"png");
		fileChooser.setFileFilter(filtro);
		fileChooser.setLocation(250, 300);

		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		FileNameExtensionFilter soloImg = new FileNameExtensionFilter("JPG & PNG Images", "jpg", "png", "jpeg");
		fileChooser.setFileFilter(soloImg);
		fileChooser.showSaveDialog(null);

		
		//Create a temp img to show the pic in the register student
		if (fileChooser.getSelectedFile() != null) {
			extension = fileChooser.getSelectedFile().toString()
					.substring(fileChooser.getSelectedFile().toString().lastIndexOf('.'));
			File imagenes = new File("files/tempSelfies/imgTemp" + extension);
			sourcer = fileChooser.getSelectedFile().getAbsoluteFile().toPath();
			destination = imagenes.toPath();
			try {
				Files.copy(sourcer, destination);
				ImageIcon imageIcon = new ImageIcon(imagenes.getPath()); // load the image to a imageIcon
				Image image = imageIcon.getImage(); // transform it 
				Image newimg = image.getScaledInstance(140, 156,  java.awt.Image.SCALE_SMOOTH); 
				ImageIcon imageIcon2 = new ImageIcon(newimg);	
				RegisterStudent.lblFoto.setIcon(imageIcon2);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}