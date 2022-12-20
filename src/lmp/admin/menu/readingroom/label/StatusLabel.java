package lmp.admin.menu.readingroom.label;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class StatusLabel extends JLabel{
	
	public StatusLabel() {
		
		this.setOpaque(true);
		this.setFont(new Font("한컴 말랑말랑 Regular", Font.BOLD, 18));
		this.setForeground(Color.WHITE);
		this.setBackground(new Color(126, 151, 148));
		this.setHorizontalAlignment(JLabel.CENTER);
	}
}