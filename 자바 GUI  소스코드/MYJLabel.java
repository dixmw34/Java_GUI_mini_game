import java.awt.Font;

import javax.swing.JLabel;

public class MYJLabel extends JLabel {
	public MYJLabel(String st) {
		setText(st);
		setFont(new Font("메이플스토리", Font.PLAIN, 15));
		setHorizontalAlignment(JLabel.CENTER);
	}
	
}
