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


public class MassageD extends JFrame implements ActionListener{

	private JButton btn;
	public MassageD() {
		setTitle("ȯ���մϴ�!");
		setSize(600, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		
		
		Container c = getContentPane();
		c.setLayout(new GridLayout(8, 1));
		JPanel jpn = new JPanel();
		jpn.setBackground(new Color(204, 204, 255));
		c.setBackground(new Color(204, 204, 255));
		
		MYJLabel lb0 = new MYJLabel("���� �׸��� ã���� ���׶�� ǥ�ð� ������");
		MYJLabel lb1 = new MYJLabel("�� ������ ó�� �Ͻô±���! ȯ���մϴ�!\r\n");
		MYJLabel lb2 = new MYJLabel("�� ������ ���� �׸��� ã�� �����Դϴ�.\r\n");
		MYJLabel lb3 = new MYJLabel("�ٽ� �����ϰ� �����ø� R��ư�� �����ּ���!.\r\n");
		MYJLabel lb4 = new MYJLabel("������ �ϴ� ���߿� �Ǽ��� R��ư�� ������ �ʵ��� ������ �ּ���!\r\n");
		MYJLabel lb5 = new MYJLabel("�̸޼����� �ٽ� ��������ø� Q��ư�� �����ø� �˴ϴ�.");
		MYJLabel lb6 = new MYJLabel("��ܿ��� ã������ �׸��� ������� �˴ϴ�.");
		
		c.add(lb1);
		c.add(lb2);
		c.add(lb0);
		c.add(lb6);
		c.add(lb3);
		c.add(lb4);
		c.add(lb5);
		
		btn = new JButton("Ȯ��");
		btn.setFont(new Font("�����ý��丮", Font.PLAIN, 15));
		btn.addActionListener(this);
		
		jpn.add(btn);
		
		c.add(jpn);
		

		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new MassageD();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == btn) {
			this.dispose();
		}
	}

}
