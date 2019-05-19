
import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class One_game extends JFrame implements ActionListener, KeyListener{
	
	private int endgame;
	private int score;
	static public String st;
	private JPanel jpn1, jpn2, jpn3;
	private JPanel[] imgjpn;
	private JLabel jlb, jlb2;
	private JLabel[] imglb;
	private JButton[] btn;
	private Container c;
	public One_game() {
		int[] arr = new int[18];
		for(int i = 0; i<arr.length; i++) {
			arr[i] = (int)(Math.random()*18);
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
		
		setTitle("18 카드맞추기");
		setSize(800, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		c= getContentPane();
		c.setLayout(new BorderLayout());
		
		jpn1 = new JPanel();
		jpn1.setLayout(new GridLayout(3, 6));
		
		imgjpn = new JPanel[18];
		imglb = new JLabel[18];
		btn= new JButton[18];
		
		URL fileStream0 = getClass().getClassLoader().getResource("001.png");
		ImageIcon image0 = new ImageIcon(fileStream0);
		URL fileStream1 = getClass().getClassLoader().getResource("002.png");
		ImageIcon image1 = new ImageIcon(fileStream1);
		URL fileStream2 = getClass().getClassLoader().getResource("003.png");
		ImageIcon image2 = new ImageIcon(fileStream2);
		URL fileStream3 = getClass().getClassLoader().getResource("004.png");
		ImageIcon image3 = new ImageIcon(fileStream3);
		URL fileStream4 = getClass().getClassLoader().getResource("005.png");
		ImageIcon image4 = new ImageIcon(fileStream4);
		URL fileStream5 = getClass().getClassLoader().getResource("006.png");
		ImageIcon image5 = new ImageIcon(fileStream5);
		URL fileStream6 = getClass().getClassLoader().getResource("007.png");
		ImageIcon image6 = new ImageIcon(fileStream6);
		URL fileStream7 = getClass().getClassLoader().getResource("008.png");
		ImageIcon image7 = new ImageIcon(fileStream7);
		URL fileStream8 = getClass().getClassLoader().getResource("009.png");
		ImageIcon image8 = new ImageIcon(fileStream8);
		
		
		for (int i = 0; i < imglb.length; i++) {
			if(i%9 == 0) imglb[i] = new JLabel(image0);
			else if(i%9 == 1) imglb[i] = new JLabel(image1);
			else if(i%9 == 2) imglb[i] = new JLabel(image2);
			else if(i%9 == 3) imglb[i] = new JLabel(image3);
			else if(i%9 == 4) imglb[i] = new JLabel(image4);
			else if(i%9 == 5) imglb[i] = new JLabel(image5);
			else if(i%9 == 6) imglb[i] = new JLabel(image6);
			else if(i%9 == 7) imglb[i] = new JLabel(image7);
			else if(i%9 == 8) imglb[i] = new JLabel(image8);
			
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
		new One_game();
		
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
					if(num%9 == i%9) {
						no_btn(i);
						endgame++;
						if(endgame == 9) {
							new End_page(18,score);	//이렇게하면 두번째 페이지로 넘어감
							this.dispose();
						}
					}
					else if(num%9 != i%9) {
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
			new One_game();
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
