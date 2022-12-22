package lmp.members.menu.member;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import lmp.db.dao.CheckOutDao;
import lmp.db.dao.MemberDao;
import lmp.db.dao.MemberLogHistoryDao;
import lmp.db.dao.MenuDao;
import lmp.db.vo.CheckOutVO;
import lmp.db.vo.MemberLogHistoryVO;
import lmp.db.vo.MemberVO;
import lmp.members.MemberFrame;
import lmp.members.MemberLoginFrame;
import lmp.util.ShaPasswordEncoder;
import lmp.util.Validator;


public class MemberMenu extends JPanel {

	MemberLogHistoryDao mdao = new MemberLogHistoryDao();
	MemberLogHistoryVO memLogVO;
	
	ShaPasswordEncoder pwEncoder = new ShaPasswordEncoder();
	MemberDao memberDao = new MemberDao();
	MemberVO mvo;
	Validator vd = new Validator();
	

	JLabel memberInfo;
	JLabel membernum;
	JLabel membername;
	JLabel memberId;
	JLabel memberbirth;
	JLabel membersex;
	JLabel memberphone;
	JLabel memberemail;
	JLabel memberaddress;

	JLabel membernum2;
	JLabel membername2;
	JLabel memberId2;
	JLabel memberbirth2;
	JLabel membersex2;
	JLabel memberphone2;
	JLabel memberemail2;
	JLabel memberaddress2;


	JButton changeBtn;
	JButton deleteBtn;
	JButton logoutBtn;



	public MemberMenu() {
		try {
			initialize();
		} catch (SQLException e) {
			return;
		}
	}

	public void refresh() throws SQLException {

		mvo = mdao.getLog().getMemberVO();

		membernum2.setText("" + mvo.getNum());
		membername2.setText("" + mvo.getName());
		memberId2.setText("" + mvo.getId());
		memberbirth2.setText("" + mvo.getBirthDay());
		membersex2.setText("" + (mvo.getSex().equals("0") ? "남" : "여"));
		memberphone2.setText("" + mvo.getPhone());
		memberemail2.setText("" + mvo.getEmail());
		memberaddress2.setText("" + mvo.getAddress());


	}


	public void initialize() throws SQLException {		

		memberInfo = new JLabel("회원 정보");
		membernum = new JLabel("회원번호");
		membername = new JLabel("이름");
		memberId = new JLabel("아이디");
		memberbirth = new JLabel("생년월일");
		membersex = new JLabel("성별");
		memberphone = new JLabel("전화번호");
		memberemail = new JLabel("이메일");
		memberaddress = new JLabel("주소");

		//		MemberLogHistoryVO logvo = null;
		//		try {
		//			logvo = new MemberLogHistoryVO(mdao.getLog());
		//		} catch (SQLException e3) {
		//			e3.printStackTrace();
		//		}
		//		

		// 받아온내용 get.mem_name;
		membernum2 = new JLabel("" );
		membername2 = new JLabel("");
		memberId2 = new JLabel("");
		memberbirth2 = new JLabel("");
		membersex2 = new JLabel("");
		memberphone2 = new JLabel("");
		memberemail2 = new JLabel("");
		memberaddress2 = new JLabel("");


		changeBtn = new JButton("수정");
		deleteBtn = new JButton("탈퇴");
		logoutBtn = new JButton("로그아웃");


		setlabel(memberInfo, 25, 30, 30);

		setlabel(membernum, 20, 100 ,100);
		setlabel(membernum2, 20, 300 ,100);

		setlabel(membername, 20, 130, 180);
		setlabel(membername2, 20, 300, 180);

		setlabel(memberId, 20, 120, 260);
		setlabel(memberId2, 20, 300, 260);

		setlabel(memberbirth, 20, 100, 340);
		setlabel(memberbirth2, 20, 300, 340);

		setlabel(membersex, 20, 130, 420);
		setlabel(membersex2, 20, 300, 420);

		setlabel(memberphone, 20, 600, 100);
		setlabel(memberphone2, 20, 800, 100);

		setlabel(memberemail, 20, 620, 180);
		setlabel(memberemail2, 20, 800, 180);

		setlabel(memberaddress, 20, 630, 260);
		setlabel(memberaddress2, 20, 800, 260);

		setBtn(changeBtn, 20, 950, 530);

		setBtn(deleteBtn, 20, 1050, 530);
		
		setBtn(logoutBtn, 10, 1050, 30);
		logoutBtn.setFont(new Font("한컴 말랑말랑 Bold", Font.BOLD, 15));
		logoutBtn.setBackground(Color.WHITE);
		logoutBtn.setForeground(Color.RED);
		logoutBtn.setFocusable(false);
		logoutBtn.setBounds(1030, 30, 100, 50);
		add(logoutBtn);

		logoutBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int var = JOptionPane.showConfirmDialog
						(null, "로그아웃하시겠습니까?", "로그아웃",
								JOptionPane.YES_NO_OPTION,
								JOptionPane.INFORMATION_MESSAGE, null);
				if (var == JOptionPane.YES_OPTION) {
					try {
						if(mdao.getLog() != null) {
							mdao.update(mdao.getLog());
							System.out.println("로그아웃");
							MemberFrame.card.show(MemberFrame.menuCardPanel, "1");
						}
					} catch (SQLException e1) {
						return;
					}
				}


			}
		});
		
		// 수정버튼 구현
		changeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame j = new JFrame();

				JLabel join = new JLabel("회원수정");
				JLabel id = new JLabel("아이디");
				JLabel pw = new JLabel("비밀번호");
				JLabel chkpw = new JLabel("비밀번호확인");
				JLabel name = new JLabel("이름");
				JLabel birth = new JLabel("생년월일");
				JLabel sex = new JLabel("성별");
				JLabel phone = new JLabel("전화번호");
				JLabel email = new JLabel("이메일");
				JLabel address = new JLabel("주소");

				JLabel pwinfo = new JLabel("특수문자포함 8자리 이상");
				// get
				JTextField idField = new JTextField("" + mvo.getId());
				JPasswordField pwField = new JPasswordField();
				JPasswordField chkpwField = new JPasswordField();
				JTextField nameField = new JTextField("" + mvo.getName());
				JTextField birthField = new JTextField("" + mvo.getBirthDay());
				JTextField sexField = new JTextField("" + (mvo.getSex().equals("0") ? "남" : "여"));
				JTextField phoneField = new JTextField("" + mvo.getPhone());
				JTextField emailField = new JTextField("" + mvo.getEmail());
				JTextField addressField = new JTextField("" + mvo.getAddress());

				JButton phonecheckBtn = new JButton("중복확인");
				JButton emailcheckBtn = new JButton("중복확인");
				JButton joinBtn = new JButton("가입하기");
				JButton changeBtn2 = new JButton("수정");
				JButton cancelBtn = new JButton("취소");



				setlabel2(join, 40, 55, 13);
				j.add(join);

				setlabel2(id, 18, 40, 90);
				setField(idField, 113);
				idField.setEditable(false);
				j.add(id);
				j.add(idField);

				setlabel2(pw, 18, 40, 140);
				setField(pwField, 163);
				j.add(pw);
				j.add(pwField);

				setlabel2(chkpw, 18, 40, 190);
				setField(chkpwField, 213);
				j.add(chkpw);
				j.add(chkpwField);

				setlabel2(name, 18, 40, 240);
				setField(nameField, 263);
				j.add(name);
				j.add(nameField);

				setlabel2(birth, 18, 40, 290);
				setField(birthField, 313);
				birthField.setEditable(false);
				j.add(birth);
				j.add(birthField);

				setlabel2(sex, 18, 40, 340);
				setField(sexField, 363);
				sexField.setEditable(false);
				j.add(sex);
				j.add(sexField);

				setlabel2(phone, 18, 40, 390);
				setField(phoneField, 413);
				setBtn2(phonecheckBtn, 13, 400, 413, 80, 30);
				j.add(phone);
				j.add(phoneField);
				j.add(phonecheckBtn);

				setlabel2(email, 18, 40, 440);
				setField(emailField, 463);
				setBtn2(emailcheckBtn, 13, 400, 463, 80, 30);
				j.add(email);
				j.add(emailField);
				j.add(emailcheckBtn);

				setlabel2(address, 18, 40, 490);
				setField(addressField, 513);
				j.add(address);
				j.add(addressField);

				setBtn2(changeBtn2, 18, 280, 580, 80, 40 );
				j.add(changeBtn2);

				MemberDao memberDao = new MemberDao();
				// 전화번호 중복체크 / 유효성검사 / 사용불가능하면 수정버튼 비활성화
				phonecheckBtn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if (vd.isValidatePhone(phoneField.getText()))  {
							MemberVO memberVO = null;
							try {
								memberVO = memberDao.get2(2, phoneField.getText()).get(0);
							} catch (SQLException e1) {
								JOptionPane.showMessageDialog(null, "사용가능합니다");

							} catch (IndexOutOfBoundsException e2) {
								JOptionPane.showMessageDialog(null, "사용가능합니다");
								changeBtn2.setEnabled(true);
							}

							if (memberVO != null) {
								JOptionPane.showMessageDialog(null, "중복되는 전화번호 입니다.",
										"경고", JOptionPane.ERROR_MESSAGE);
								changeBtn2.setEnabled(false);
							}
						} else {
							JOptionPane.showMessageDialog(null, "사용 불가한 전화번호입니다",
									"경고", JOptionPane.ERROR_MESSAGE);
							changeBtn2.setEnabled(false);
						}

					}
				});

				// 이메일 중복체크 / 유효성검사 / 사용불가능하면 수정버튼 비활성화
				emailcheckBtn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if (vd.isValidateEmail(emailField.getText()))  {
							MemberVO memberVO = null;
							try {
								memberVO = memberDao.get2(3, emailField.getText()).get(0);
							} catch (SQLException e1) {
								JOptionPane.showMessageDialog(null, "사용가능합니다");

							} catch (IndexOutOfBoundsException e2) {
								JOptionPane.showMessageDialog(null, "사용가능합니다");
								changeBtn2.setEnabled(true);
							}

							if (memberVO != null) {
								JOptionPane.showMessageDialog(null, "중복되는 이메일 입니다.",
										"경고", JOptionPane.ERROR_MESSAGE);
								changeBtn2.setEnabled(false);
							}
						} else {
							JOptionPane.showMessageDialog(null, "사용 불가한 이메일입니다",
									"경고", JOptionPane.ERROR_MESSAGE);
							changeBtn2.setEnabled(false);
						}

					}
				});


				changeBtn2.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {

						if (vd.isValidatePW(new String(pwField.getPassword())) &&
								vd.isValidateName(nameField.getText()) &&
								vd.isValidatePhone(phoneField.getText()) &&
								vd.isValidateEmail(emailField.getText()) &&
								!(addressField.getText().equals("")) 
								) {
							int var = JOptionPane.showConfirmDialog
									(null, "수정하시겠습니까?", "수정 확인",
											JOptionPane.YES_NO_OPTION,
											JOptionPane.INFORMATION_MESSAGE, null);
							if (var == JOptionPane.YES_OPTION) {
								MenuDao mdao = new MemberDao();
								MemberVO vo = null;
								try {
									vo = new MemberVO(pwEncoder.encrypt(new String(pwField.getPassword())),
											mvo.getNum(),
											nameField.getText(),
											phoneField.getText(),
											emailField.getText(),
											addressField.getText()
											);
								} catch (Exception e2) {
									e2.printStackTrace();
								}
								try {
									mdao.update(vo);
								} catch (SQLException e1) {
									e1.printStackTrace();
								}



							} else if (vd.isValidateName(nameField.getText()) == false) {
								JOptionPane.showMessageDialog(null, "적절하지 않은 이름입니다", "경고",
										JOptionPane.ERROR_MESSAGE);
							} else {
								JOptionPane.showMessageDialog(null, "전부입력해주세요", "경고",
										JOptionPane.ERROR_MESSAGE);
							}
						}

						try {
							refresh();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						JOptionPane.showMessageDialog(null, "수정되었습니다");
						j.dispose();
					}
				});

				j.setLayout(null);
				j.setBounds(330, 130, 550, 700);
				j.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				j.setVisible(true);
			}
		});

		// 탈퇴버튼
		deleteBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CheckOutDao checkOutDao = new CheckOutDao();
				ArrayList<CheckOutVO> checkOutVO = new ArrayList();

				try {
					checkOutVO.addAll(checkOutDao.get(mvo.getNum().toString()));
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
				// 미반납 도서가 존재하면 안내문구 출력 후 삭제 버튼 무효
				if (checkOutVO.size() != 0) {
					JOptionPane.showMessageDialog(null, "미반납 도서가 존재합니다.");
					deleteBtn.setEnabled(false);
					return;
				} 
				
				System.out.println(mvo.getNum());
				int var = JOptionPane.showConfirmDialog
						(null, "탈퇴하시겠습니까?", "탈퇴 안내",
								JOptionPane.YES_NO_OPTION,
								JOptionPane.INFORMATION_MESSAGE, null);
				if (var == JOptionPane.YES_OPTION) {
					try {
						memberDao.delete(mvo.getNum());
						MemberFrame.card.show(MemberFrame.menuCardPanel, "1");
						JOptionPane.showMessageDialog(null, "탈퇴되었습니다");
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});



		// 판넬 설정
		setBackground(new Color(87, 119, 119));
		setLayout(null);
		//setBorder(new LineBorder(Color.WHITE, 2, false)); // 테두리
		setBounds(15, 180, 1150, 550);

		this.validate();

	}

	// 라벨 생성 및 설정함수
	public void setlabel(JLabel label , int size, int x, int y) {
		Font font = new Font("한컴 말랑말랑 Bold", Font.BOLD, size);
		label.setFont(font);
		label.setForeground(Color.WHITE);
		label.setBounds(x, y, 300, 30);
		add(label);
	}

	public void setlabel2(JLabel label , int size, int x, int y) {
		Font font = new Font("한컴 말랑말랑 Bold", Font.BOLD, size);
		label.setFont(font);
		label.setForeground(new Color(49, 82, 91));
		label.setBounds(x, y, 200, 70);
		add(label);
	}

	// 버튼 생성 및 설정함수
	public void setBtn(JButton button , int size, int x, int y) {
		Font font = new Font("한컴 말랑말랑 Bold", Font.BOLD, size);

		button.setFont(font);
		button.setBackground(Color.WHITE);
		button.setForeground(new Color(49, 82, 91));
		button.setFocusable(false);
		button.setBounds(x, y, 70, 50);
		add(button);
	}

	public void setBtn2(JButton button , int size, int x, int y, int a, int b) {
		Font font = new Font("한컴 말랑말랑 Bold", Font.BOLD, size);

		button.setFont(font);
		button.setBackground(new Color(87, 119, 119));
		button.setForeground(Color.WHITE);
		button.setFocusable(false);
		button.setBounds(x, y, a, b);
		add(button);
	}

	// 텍스트필드 생성 및 설정함수
	public void setField(JTextField field, int y) {
		Font font = new Font("한컴 말랑말랑 Bold", Font.BOLD, 13);
		field.setFont(font);
		field.setBounds(180, y, 200, 30);
		field.setBorder(new LineBorder(new Color(49, 82, 91), 2, false));
		add(field);
	}



}


