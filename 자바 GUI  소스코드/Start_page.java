
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;




public class Start_page extends JFrame implements ActionListener{
	
	private JLabel lb1, lb2, lb3;
	private JButton btn1, btn2, btn3 ,btn4,  btn_ex;
	private JPanel[] jpn;
	private JPanel[] pnsub;
	public Start_page() {
		setTitle("프레임");
		setSize(800, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		Container c = getContentPane();
		c.setLayout(new GridLayout(7, 1));
		
		jpn = new JPanel[7];
		pnsub = new JPanel[6];
		
		for (int i = 0; i < jpn.length; i++) {
			jpn[i] = new JPanel();
			jpn[i].setBackground(Color.PINK);
		}
		for (int i = 0; i < pnsub.length; i++) {
			pnsub[i] = new JPanel();
			pnsub[i].setBackground(Color.PINK);
			
		}
		
		lb1 = new JLabel("미니 게임");
		lb1.setFont(new Font("메이플스토리", Font.BOLD, 75));
		lb1.setForeground(Color.DARK_GRAY);
		
		lb2 = new JLabel("같은 카드 맞추기");
		lb2.setFont(new Font("메이플스토리", Font.PLAIN, 50));
		lb2.setForeground(Color.DARK_GRAY);
		lb3 = new JLabel("숨은 그림 찾기");
		lb3.setFont(new Font("메이플스토리", Font.PLAIN, 50));
		lb3.setForeground(Color.DARK_GRAY);
		
		btn1 = new JButton("18");
		btn1.setFont(new Font("메이플스토리", Font.PLAIN, 30));
		btn1.addActionListener(this);
		btn2 = new JButton("32");
		btn2.setFont(new Font("메이플스토리", Font.PLAIN, 30));
		btn2.addActionListener(this);
		btn3 = new JButton("1");
		btn3.setFont(new Font("메이플스토리", Font.PLAIN, 30));
		btn3.addActionListener(this);
		btn4 = new JButton("2");
		btn4.setFont(new Font("메이플스토리", Font.PLAIN, 30));
		btn4.addActionListener(this);
		btn_ex = new JButton("게임종료");
		btn_ex.setFont(new Font("메이플스토리", Font.PLAIN, 30));
		btn_ex.addActionListener(this);
		
		pnsub[0].add(lb2);
		pnsub[1].add(lb3);
		pnsub[2].add(btn1);
		pnsub[3].add(btn3);
		pnsub[4].add(btn2);
		pnsub[5].add(btn4);
		
		
		
		jpn[1].add(lb1);

		jpn[3].setLayout(new GridLayout(1, 2));
		jpn[3].add(pnsub[0]);
		jpn[3].add(pnsub[1]);
		jpn[4].setLayout(new GridLayout(1, 2));
		jpn[4].add(pnsub[2]);
		jpn[4].add(pnsub[3]);
		jpn[5].setLayout(new GridLayout(1, 2));
		jpn[5].add(pnsub[4]);
		jpn[5].add(pnsub[5]);

		
		jpn[6].add(btn_ex);
		
		for (int i = 0; i < jpn.length; i++) {
			c.add(jpn[i]);
		}

		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Start_page();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == btn1) {
			new One_game();
			this.dispose();
		}else if(obj == btn2) {
			new Two_game();	
			this.dispose();
		}else if(obj == btn_ex) System.exit(0);
		else if(obj == btn3) {
			new Three_game();	
			this.dispose();
		}
		else if(obj == btn4) {
			new Four_game();	
			this.dispose();
		}
		
	}

}
