
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;




public class End_page extends JFrame implements ActionListener{
	
	private JLabel lb1, lb2, lb3;
	private JButton btn;
	private String fname;
	private int fscore, score, key;
	private JPanel[] jpn;
	public End_page(int key, int score) {
		setTitle("������");
		setSize(800, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.score = score;
		this.key = key;
		
		
		 
		


		
		lb1 = new JLabel("����� ����: "+score);
		lb1.setFont(new Font("�����ý��丮", Font.PLAIN, 60));
		btn = new JButton("1� �����ϱ�!!!");
		btn.setFont(new Font("�����ý��丮", Font.PLAIN, 30));
		lb2 = new JLabel("�̰� �����");
		lb2.setFont(new Font("�����ý��丮", Font.PLAIN, 30));
		lb2.setVisible(false);
		lb2.setEnabled(false);
		lb3 = new JLabel();
		lb3.setFont(new Font("�����ý��丮", Font.PLAIN, 30));
		btn.addActionListener(this);
		Container c = getContentPane();

		c.setLayout(new GridLayout(4, 1));
		//c.setBackground(Color.DARK_GRAY);
		
		jpn = new JPanel[4];
		
		for (int i = 0; i < jpn.length; i++) {
			jpn[i] = new JPanel();
			jpn[i].setBackground(Color.PINK);
		}
		
		jpn[1].add(lb1);
		jpn[2].add(btn);
		jpn[2].add(lb2);
		jpn[3].add(lb3);
		
		
		for (int i = 0; i < jpn.length; i++) {
			c.add(jpn[i]);
		}
		
		setVisible(true);
		
		
		
	}
	public static void main(String[] args) {
		new End_page(32, 12344);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == btn) {
			btn.setEnabled(false);
			btn.setVisible(false);
			lb2.setEnabled(true);
			lb2.setVisible(true);
			Connection con= null; 
			Statement stmt = null; 
			ResultSet rs = null;
			 try {
				Class.forName("com.mysql.jdbc.Driver");
				String DB_URL = "jdbc:mysql://localhost:3306/game?&useSSL=false"; 
				String DB_USER = "admin";
				String DB_PASSWORD= "1234"; 
				con = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
				stmt = con.createStatement();
				String query = "SELECT * FROM mini WHERE num = "+key; 
				rs = stmt.executeQuery(query);
				while (rs.next()) {
	               fname= rs.getString("name");
	               fscore = rs.getInt("score");
	           }
				lb2.setText("1���� �̸��� : "+fname);
				lb3.setText("1���� ������ : "+fscore+"�Դϴ�.");
				if(fscore>score) {
					
					String name = JOptionPane.showInputDialog("�����մϴ�. 1���Դϴ�. �̸��� �Է����ּ���: ");
					query = "UPDATE mini SET name= '"+name+"', score= "+score+" WHERE num= "+key;
					stmt.executeUpdate(query);
					lb2.setText("1���� �̸��� : "+name);
					lb3.setText("1���� ������ : "+score+"�Դϴ�.");
				}
				else{
					JOptionPane.showMessageDialog(null, "�ƽ�����. �ٽ� �����غ�����!");
					
				}

	           rs.close();
	           stmt.close();
	           con.close();


			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				System.out.println(e1);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				System.out.println(e1);
			}
		}
		
	}
	
	
}

