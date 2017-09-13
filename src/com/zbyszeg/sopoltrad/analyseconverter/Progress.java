package com.zbyszeg.sopoltrad.analyseconverter;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class Progress extends JFrame {
	private JLabel text;
	
	public Progress() {
		setTitle("Sopoltrad Analyse Converter by Zbyszek Góra");
		setSize(450, 90);
		setLayout(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setIconImage(new ImageIcon(getClass().getResource("/img/analyse.png")).getImage());
		
		text = new JLabel("Przetwarzanie...");
		text.setBounds(30, 10, 200, 30);
		add(text);
	}
}
