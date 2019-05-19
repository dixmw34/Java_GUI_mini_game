
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
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class Two_game extends JFrame implements ActionListener, KeyListener{
	private int endgame;
	private int score;
	static public String st;
	private JPanel jpn1, jpn2, jpn3;
	private JPanel[] imgjpn;
	private JLabel jlb, jlb2;
	private JLabel[] imglb;
	private JButton[] btn;
	private Container c;
	public Two_game() {
		int[] arr = new int[32];
		for(int i = 0; i<arr.length; i++) {
			arr[i] = (int)(Math.random()*32);
			for (int j = 0; j < i; j++) {
				if(arr[i] == arr[j]) {
					i--;
					break;
				}
			}
		}
		
		
		score = 0;
		endgame = 0;
		st = "";
		
		setTitle("32 카드맞추기");
		setSize(800, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		c = getContentPane();
		c.setLayout(new BorderLayout());
		
		jpn1 = new JPanel();
		jpn1.setLayout(new GridLayout(4, 8));
		
		imgjpn = new JPanel[32];
		imglb = new JLabel[32];
		btn= new JButton[32];
		
		URL fileStream0 = getClass().getClassLoader().getResource("1.png");
		ImageIcon image0 = new ImageIcon(fileStream0);
		URL fileStream1 = getClass().getClassLoader().getResource("2.png");
		ImageIcon image1 = new ImageIcon(fileStream1);
		URL fileStream2 = getClass().getClassLoader().getResource("3.png");
		ImageIcon image2 = new ImageIcon(fileStream2);
		URL fileStream3 = getClass().getClassLoader().getResource("4.png");
		ImageIcon image3 = new ImageIcon(fileStream3);
		URL fileStream4 = getClass().getClassLoader().getResource("5.png");
		ImageIcon image4 = new ImageIcon(fileStream4);
		URL fileStream5 = getClass().getClassLoader().getResource("6.png");
		ImageIcon image5 = new ImageIcon(fileStream5);
		URL fileStream6 = getClass().getClassLoader().getResource("7.png");
		ImageIcon image6 = new ImageIcon(fileStream6);
		URL fileStream7 = getClass().getClassLoader().getResource("8.png");
		ImageIcon image7 = new ImageIcon(fileStream7);
		URL fileStream8 = getClass().getClassLoader().getResource("9.png");
		ImageIcon image8 = new ImageIcon(fileStream8);
		URL fileStream9 = getClass().getClassLoader().getResource("10.png");
		ImageIcon image9 = new ImageIcon(fileStream9);
		URL fileStream10 = getClass().getClassLoader().getResource("11.png");
		ImageIcon image10 = new ImageIcon(fileStream10);
		URL fileStream11 = getClass().getClassLoader().getResource("12.png");
		ImageIcon image11 = new ImageIcon(fileStream11);
		URL fileStream12 = getClass().getClassLoader().getResource("13.png");
		ImageIcon image12 = new ImageIcon(fileStream12);
		URL fileStream13 = getClass().getClassLoader().getResource("14.png");
		ImageIcon image13 = new ImageIcon(fileStream13);
		URL fileStream14 = getClass().getClassLoader().getResource("15.png");
		ImageIcon image14 = new ImageIcon(fileStream14);
		URL fileStream15 = getClass().getClassLoader().getResource("16.png");
		ImageIcon image15 = new ImageIcon(fileStream15);
		
		for (int i = 0; i < imglb.length; i++) {
			if(i%16 == 0) imglb[i] = new JLabel(image0);
			else if(i%16 == 1) imglb[i] = new JLabel(image1);
			else if(i%16 == 2) imglb[i] = new JLabel(image2);
			else if(i%16 == 3) imglb[i] = new JLabel(image3);
			else if(i%16 == 4) imglb[i] = new JLabel(image4);
			else if(i%16 == 5) imglb[i] = new JLabel(image5);
			else if(i%16 == 6) imglb[i] = new JLabel(image6);
			else if(i%16 == 7) imglb[i] = new JLabel(image7);
			else if(i%16 == 8) imglb[i] = new JLabel(image8);
			else if(i%16 == 9) imglb[i] = new JLabel(image9);
			else if(i%16 == 10) imglb[i] = new JLabel(image10);
			else if(i%16 == 11) imglb[i] = new JLabel(image11);
			else if(i%16 == 12) imglb[i] = new JLabel(image12);
			else if(i%16 == 13) imglb[i] = new JLabel(image13);
			else if(i%16 == 14) imglb[i] = new JLabel(image14);
			else if(i%16 == 15) imglb[i] = new JLabel(image15);
		}
		
		
		for (int i = 0; i < btn.length; i++) {
			imgjpn[i] = new JPanel();
			imgjpn[i].setBackground(Color.WHITE);
			imgjpn[i].setLayout(new BorderLayout());
			btn[i] = new JButton();
			btn[i].addActionListener(this);
			imgjpn[i].add(imglb[i]);
			imgjpn[i].add(btn[i]);
		}
		for (int i = 0; i < btn.length; i++) {
			int num = arr[i];
			jpn1.add(imgjpn[num]);
		}
		
		jpn2 = new JPanel();
		jpn2.setBackground(Color.GRAY);
		jlb = new JLabel("처음하신다면 Q를 눌러보세요!!!");
		jlb.setFont(new Font("메이플스토리", Font.BOLD, 20));
		jlb2 = new JLabel("점수: "+String.valueOf(score));
		jlb2.setFont(new Font("메이플스토리", Font.BOLD, 20));
		jpn2.add(jlb);
		jpn3 = new JPanel();
		jpn3.setBackground(Color.PINK);
		jpn3.add(jlb2);
		

		c.add(jpn1, BorderLayout.CENTER);
		c.add(jpn2, BorderLayout.SOUTH);
		c.add(jpn3, BorderLayout.NORTH);

		c.addKeyListener(this);

		c.setFocusable(true);
		c.requestFocus();
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Two_game();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		score+=10;
		jlb2.setText("점수: "+String.valueOf(score));
		for (int i = 0; i < btn.length; i++) {
			if(obj == btn[i]) {
				if(st.equals("")){ 
					st = ""+i;
					no_btn(i);
				}
				else {
					int num = Integer.parseInt(st);
					st = "";
					if(num%16 == i%16) {
						no_btn(i);
						endgame++;
						if(endgame == 16) {
							new End_page(32,score);	//이렇게하면 두번째 페이지로 넘어감
							this.dispose();
						}
					}
					else if(num%16 != i%16) {
						yes_btn(num);
						
					}
				}
			}
			c.requestFocus();
		}
	}

	private void yes_btn(int num) {
		imgjpn[num].setLayout(new BorderLayout());
		btn[num].setVisible(true);
		btn[num].setEnabled(true);
		imglb[num].setVisible(false);
	}

	private void no_btn(int i) {
		imgjpn[i].setLayout(new FlowLayout());
		btn[i].setVisible(false);
		btn[i].setEnabled(false);
		imglb[i].setVisible(true);
	}

	@Override
	public void keyPressed(KeyEvent e) {

		char ch= e.getKeyChar();
		if(ch == 'r'||ch == 'R' ) {
			this.dispose();
			new Two_game();
		}
		else if(ch == 'Q'||ch == 'q') {
			new MassageC();

		}
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	

}
