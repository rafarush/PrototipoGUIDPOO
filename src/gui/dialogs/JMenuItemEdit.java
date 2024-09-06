package gui.dialogs;

import javax.swing.JMenuItem;
import java.awt.Color;
import java.awt.Font;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class JMenuItemEdit extends JMenuItem{

	private static final long serialVersionUID = 1L;

	public JMenuItemEdit() {
		setIcon(new ImageIcon(JMenuItemEdit.class.getResource("/gui/utils/closeBotton-removebg-preview.png")));
		setText("Cerrar Sesi\u00F3n");
		setFont(new Font("Tahoma", Font.PLAIN, 13));
		setForeground(new Color(0, 0, 0));
	}

	
}
