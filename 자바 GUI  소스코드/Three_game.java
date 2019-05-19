
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




public class Three_game extends JFrame implements MouseListener, KeyListener{
	
	private int score;
	private JPanel jpn1, jpn2, jpn3;
	private JLabel jlb, jlb2, jlb3, jlb4;
	private JLabel[] lb;
	private Container c;
	private String[] st;
	private String simple;
	public Three_game() {
		setTitle("숨은그림 찾기1");
		setSize(800, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		score=0;
		lb = new JLabel[5];
		st = new String[5];
		
		URL url1 = getClass().getClassLoader().getResource("circle_B.png");
		ImageIcon icon1 = new ImageIcon(url1);
		
		for (int i = 0; i < 5; i++) {
			lb[i] = new JLabel(icon1);
		}
		simple = "찾을것 : 마침표  느낌표  큰따음표  물음표  작은따음표";
		
		
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
		
		
		st[0] = "마침표";
		st[1] ="느낌표" ;
		st[2] ="큰따음표" ;
		st[3] = "물음표";
		st[4] = "작은따음표";

		
		jpn2.add(jlb);
		jpn3 = new JPanel();
		jpn3.setBackground(Color.PINK);
		jpn3.setLayout(new GridLayout(2, 1));
		jlb2.setHorizontalAlignment(JLabel.CENTER);
		jlb4.setHorizontalAlignment(JLabel.CENTER);
		jpn3.add(jlb2);
		jpn3.add(jlb4);
		URL url = getClass().getClassLoader().getResource("one.png");
		ImageIcon icon = new ImageIcon(url);
		jlb3 = new JLabel(icon);
		for (int i = 0; i < lb.length; i++) {
			jpn1.add(lb[i]);
			lb[i].setVisible(false);
			
		}
		jpn1.add(jlb3);
		jpn1.setLayout(null);
		jpn1.setBackground(Color.WHITE);
		jlb3.setBounds(10, -30, 750, 580 );
		
		lb[0].setBounds(375, 205, 80, 80);
		lb[1].setBounds(300, 135, 80, 80);
		lb[2].setBounds(566, -10, 80, 80);
		lb[3].setBounds(125, 195, 80, 80);
		lb[4].setBounds(660, 245, 80, 80);
		
		

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
		new Three_game();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		score+=100;
		jlb2.setText("점수: "+String.valueOf(score));
		int x = e.getX();
		int y = e.getY();
		if(x>403 && x<421 &&y>239&&  y<249 ) {
			lb[0].setVisible(true);
			simple = simple.replace(st[0]," ");
			jlb4.setText(simple);
		}
		else if(x>326 && x<355 && y>149 && y<201) {
			lb[1].setVisible(true);
			simple = simple.replace(st[1]," ");
			jlb4.setText(simple);
		}
		else if(x>566 && x<628 && y>1 && y<51) {
			lb[2].setVisible(true);
			simple = simple.replace(st[2]," ");
			jlb4.setText(simple);
		}
		else if(x>112 && x<201 && y>217 && y<248) {
			lb[3].setVisible(true);
			simple = simple.replace(st[3]," ");
			jlb4.setText(simple);
		}
		else if(x>686 && x<693 && y>256 && y<299) {
			lb[4].setVisible(true);
			simple = simple.replace(st[4]," ");
			jlb4.setText(simple);
		}
		if(simple.equals("찾을것 :              ")) {
			new End_page(1,score);
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
			new Three_game();
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