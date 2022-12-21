package lmp.members.menu.readingroom.seatlist.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import lmp.login.MemberLoginFrame;
import lmp.members.dao.MemberLogHistoryDao;
import lmp.members.dao.SeatUseDetailDao;
import lmp.members.menu.readingroom.seatlist.label.SeatLabel;
import lmp.members.vo.MemberLogHistoryVO;
import lmp.members.vo.SeatUseDetailVO;

public class SeatMouseListener extends MouseAdapter{
	
	SeatUseDetailDao sudDao = new SeatUseDetailDao();
	SeatUseDetailVO  sudVO;
	MemberLogHistoryDao memLogDao = new MemberLogHistoryDao();
	MemberLogHistoryVO memLogVO;
	MemberLoginFrame memLogFrame = new MemberLoginFrame();
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
		SeatLabel seatLabel = (SeatLabel) e.getSource();
		int seat_num = Integer.parseInt(seatLabel.getText());
		try {
			
			sudVO = sudDao.searchSeat(seat_num);
			
			// 이용중인 자리 확인
			if (sudVO != null) {
				JOptionPane.showMessageDialog(null, "이미 사용중인 자리입니다.", "Message", 1);
			} else {
				memLogVO = memLogDao.getLog();
				System.out.println(memLogVO);
				
				// 로그인 여부 확인
				if (memLogVO == null) {
					JOptionPane.showMessageDialog(null, "로그인 후 이용하세요.", "Message", 1);
					memLogFrame.initialize();
					memLogFrame.setVisible(true);
				} else {
					
				}
				
				
			}
			
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

}
