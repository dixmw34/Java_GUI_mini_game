import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class MassageC extends JFrame implements ActionListener{

	private JButton btn;
	public MassageC() {
		setTitle("환영합니다!");
		setSize(600, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		
		Container c = getContentPane();
		c.setLayout(new GridLayout(8, 1));
		JPanel jpn = new JPanel();
		jpn.setBackground(new Color(204, 204, 255));
		c.setBackground(new Color(204, 204, 255));
		
		MYJLabel lb0 = new MYJLabel("처음 그림을 뒤집으면 그림이 보이고 두번째로 뒤집은 것과 \r\n");
		MYJLabel lb1 = new MYJLabel("이 게임을 처음 하시는군요! 환영합니다!\r\n");
		MYJLabel lb2 = new MYJLabel("이 게임은 같은 그림을 맞추는 게임입니다.\r\n");
		MYJLabel lb3 = new MYJLabel("다시 시작하고 싶으시면 R버튼을 눌러주세요!.\r\n");
		MYJLabel lb4 = new MYJLabel("게임을 하는 도중에 실수로 R버튼을 누르지 않도록 주의해 주세요!\r\n");
		MYJLabel lb5 = new MYJLabel("이메세지를 다시 보고싶으시면 Q버튼을 누르시면 됩니다.");
		MYJLabel lb6 = new MYJLabel("그림이 같으면 둘다 그림이 보이지만 그림이 다르면 둘다 그림이 보이지 않습니다.");
		
		c.add(lb1);
		c.add(lb2);
		c.add(lb0);
		c.add(lb6);
		c.add(lb3);
		c.add(lb4);
		c.add(lb5);
		
		btn = new JButton("확인");
		btn.setFont(new Font("메이플스토리", Font.PLAIN, 15));
		btn.addActionListener(this);
		
		jpn.add(btn);
		
		c.add(jpn);
		

		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new MassageC();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == btn) {
			this.dispose();
		}
	}

}
