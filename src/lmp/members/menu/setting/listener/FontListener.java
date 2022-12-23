package lmp.members.menu.setting.listener;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JButton;

import lmp.members.dao.FontDao;

public class FontListener extends MouseAdapter{
	
	
	FontDao fontDao = new FontDao();
	JButton[] btns;
	
	public FontListener(JButton[] btns) {
		this.btns = btns;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		JButton btn = (JButton) e.getSource();
		
		for (int i =0; i < btns.length;  i++) {
			if (btns[i].getText() == btn.getText()) {
				btns[i].setBackground(Color.BLUE);
				try {
					fontDao.setFont(btns[i].getText());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			} else {
				btns[i].setBackground(Color.LIGHT_GRAY);
			}
		}
		
	}
	
}