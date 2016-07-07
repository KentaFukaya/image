package Jframe;

import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ImageFrame extends JPanel{
	
	String filename= null;
	
	public ImageFrame(String name){
	Path filepath  = null;
	 filepath= Paths.get("src", "histgram", "images",name).toAbsolutePath();// Œ©‚½‚¢•”•ª‚Ìpath
	
	ImageIcon icon1 = new ImageIcon(filepath.toString());
	JLabel label = new JLabel(icon1);
	this.add(new JLabel());
	this.add(label);
	}
	
	public void setFileName(String name){
		this.filename = name;
	}
}
