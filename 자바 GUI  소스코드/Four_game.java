
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;




public class Four_game extends JFrame implements MouseListener, KeyListener{
	
	private int score;
	private JPanel jpn1, jpn2, jpn3;
	private JLabel jlb, jlb2, jlb3, jlb4;
	private JLabel[] lb;
	private Container c;
	private String[] st;
	private String simple;
	public Four_game() {
		setTitle("숨은그림 찾기2");
		setSize(800, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		score=0;
		lb = new JLabel[6];
		st = new String[6];
		
		URL url1 = getClass().getClassLoader().getResource("circle_B.png");
		ImageIcon icon1 = new ImageIcon(url1);
		
		for (int i = 0; i < 6; i++) {
			lb[i] = new JLabel(icon1);
		}
		simple = "찾을것 : 슬리퍼  은행잎  반바지  연필  야구방망이  양말";
		
		
		c= getContentPane();
		c.setLayout(new BorderLayout());
		
		jpn1 = new JPanel();
		
		jpn2 = new JPanel();
		jpn2.setBackground(Color.GRAY);
		jlb = new JLabel("처음하신다면 Q를 눌러보세요!!!");
		jlb.setFont(new Font("메이플스토리", Font.BOLD, 20));
		jlb2 = new JLabel("점수: "+String.valueOf(score));
		jlb2.setFont(new Font("메이플스토리", Font.BOLD, 20));
		jlb4 = new JLabel(simple);
		jlb4.setFont(new Font("메이플스토리", Font.PLAIN, 20));


		st[0] = "슬리퍼";
		st[1] ="은행잎" ;
		st[2] =" 반바지" ;
		st[3] = "연필";
		st[4] = "야구방망이";
		st[5] = "양말";

		
		jpn2.add(jlb);
		jpn3 = new JPanel();
		jpn3.setBackground(Color.PINK);
		jpn3.setLayout(new GridLayout(2, 1));
		jlb2.setHorizontalAlignment(JLabel.CENTER);
		jlb4.setHorizontalAlignment(JLabel.CENTER);
		jpn3.add(jlb2);
		jpn3.add(jlb4);
		URL url = getClass().getClassLoader().getResource("two.png");
		ImageIcon icon = new ImageIcon(url);
		jlb3 = new JLabel(icon);
		for (int i = 0; i < lb.length; i++) {
			jpn1.add(lb[i]);
			lb[i].setVisible(false);
			
		}
		jpn1.add(jlb3);
		jpn1.setLayout(null);
		jpn1.setBackground(Color.WHITE);
		jlb3.setBounds(15, -50, 750, 580 );
		
		lb[0].setBounds(410, 95, 80, 80);
		lb[1].setBounds(330, 410, 80, 80);
		lb[2].setBounds(565, 375, 80, 80);
		lb[3].setBounds(80, 40, 80, 80);
		lb[4].setBounds(220, 25, 80, 80);
		lb[5].setBounds(210, 315, 80, 80);
		
		

		c.add(jpn1, BorderLayout.CENTER);
		c.add(jpn2, BorderLayout.SOUTH);
		c.add(jpn3, BorderLayout.NORTH);
		
		jpn1.addMouseListener(this);
		

		c.addKeyListener(this);

		c.setFocusable(true);
		c.requestFocus();
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Four_game();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		score+=100;
		jlb2.setText("점수: "+String.valueOf(score));
		int x = e.getX();
		int y = e.getY();
		if(x>414 && x<491 &&y>117&&  y<142 ) {
			lb[0].setVisible(true);
			simple = simple.replace(st[0]," ");
			jlb4.setText(simple);
		}
		else if(x>339 && x<385 && y>432 && y<462) {
			lb[1].setVisible(true);
			simple = simple.replace(st[1]," ");
			jlb4.setText(simple);
		}
		else if(x>555 && x<682 && y>377 && y<449) {
			lb[2].setVisible(true);
			simple = simple.replace(st[2]," ");
			jlb4.setText(simple);
		}
		else if(x>105 && x<135 && y>36 && y<112) {
			lb[3].setVisible(true);
			simple = simple.replace(st[3]," ");
			jlb4.setText(simple);
		}
		else if(x>250 && x<270 && y>4 && y<126) {
			lb[4].setVisible(true);
			simple = simple.replace(st[4]," ");
			jlb4.setText(simple);
		}
		else if(x>226 && x<268 && y>316 && y<379) {
			lb[5].setVisible(true);
			simple = simple.replace(st[5]," ");
			jlb4.setText(simple);
		}
		if(simple.equals("찾을것 :                ")) {
			new End_page(2,score);
			this.dispose();
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {

		char ch= e.getKeyChar();
		if(ch == 'r'||ch == 'R' ) {
			this.dispose();
			new Four_game();
		}
		else if(ch == 'Q'||ch == 'q') {
			new MassageD();

		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}