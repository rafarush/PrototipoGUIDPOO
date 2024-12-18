package gui.dialogs;

import gui.mainFrame.MainFrame;
import gui.utils.JTextFieldLimitado;
import gui.utils.Validaciones;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import runner.Runner;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPasswordField;
import javax.swing.SwingWorker;

import logica.Clases.*;

import javax.swing.JProgressBar;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class JDialogLoginUser extends JDialog {

	private static final long serialVersionUID = 1L;

	private final JPanel upperBarPanel = new JPanel();
	private JTextFieldLimitado nombreTextField;
	private JPasswordField passwordField;
	private int limite = 35;
	private final Border bordeRojo = BorderFactory.createLineBorder(Color.RED,1);
	private final Border bordeNegro = BorderFactory.createLineBorder(Color.GRAY, 1);

	/**
	 * Crear JDialog Login
	 */
	public JDialogLoginUser() {
		setUndecorated(true);
		setModal(true);
		setBounds(100, 100, 538, 360);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);

		final JLabel closeBotton = new JLabel("");
		closeBotton.setIcon(new ImageIcon(JDialogLoginUser.class
				.getResource("/gui/utils/closeBotton.png")));
		closeBotton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				closeBotton.setIcon(new ImageIcon(JDialogLoginUser.class
						.getResource("/gui/utils/closeBottonSelected1.png")));
				closeBotton.setToolTipText("Cerrar");
			}

			@Override
			public void mouseExited(MouseEvent e) {
				closeBotton.setIcon(new ImageIcon(JDialogLoginUser.class
						.getResource("/gui/utils/closeBotton.png")));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (JOptionPane.showConfirmDialog(null,
						"�Seguro que desea cerrar la aplicaci�n",
						"Confirmaci�n", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					dispose();
				}
			}
		});
		closeBotton.setBounds(500, 0, 27, 34);
		upperBarPanel.add(closeBotton);

		upperBarPanel.setBackground(new Color(255, 255, 255));
		upperBarPanel.setBounds(0, 0, 537, 34);
		upperBarPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(upperBarPanel);
		upperBarPanel.setLayout(null);

		JLabel nameFrame = new JLabel(
				"Bienvenido al Gestor de la Filial de Ciencias T\u00E9cnicas");
		nameFrame.setBounds(10, 0, 335, 34);
		upperBarPanel.add(nameFrame);
		{
			final JPanel mainPanel = new JPanel();
			mainPanel.setBackground(Color.WHITE);
			mainPanel.setBounds(0, 34, 537, 326);
			getContentPane().add(mainPanel);
			mainPanel.setLayout(null);

			JPanel panel = new JPanel();
			panel.setBackground(new Color(0, 204, 255));
			panel.setBounds(387, 0, 150, 326);
			mainPanel.add(panel);

			final JProgressBar progressBar = new JProgressBar();
			progressBar.setBorderPainted(false);
			progressBar.setForeground(new Color(0, 191, 255));
			progressBar.setBounds(0, 312, 387, 14);
			progressBar.setMaximum(100);
			progressBar.setMinimum(0);
			mainPanel.add(progressBar);

			nombreTextField = new JTextFieldLimitado();
			nombreTextField.setToolTipText("Nombre de usuario");
			nombreTextField.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					nombreTextField.setBorder(bordeNegro);
				}
				@Override
				public void keyTyped(KeyEvent e){
					JTextField text = (JTextField) e.getSource();
					if(text.getText().length()== limite)
						e.consume();
				}
			});
			nombreTextField.setColumns(10);
			nombreTextField.setBounds(81, 182, 223, 20);
			mainPanel.add(nombreTextField);

			JLabel lblNombre = new JLabel("Usuario:");
			lblNombre.setForeground(new Color(51, 51, 51));
			lblNombre.setBounds(81, 160, 166, 20);
			mainPanel.add(lblNombre);

			JLabel lblContrasenna = new JLabel("Contrase\u00F1a:");
			lblContrasenna.setBounds(81, 213, 166, 20);
			mainPanel.add(lblContrasenna);

			JLabel lblUserIcon = new JLabel("\\");
			lblUserIcon.setIcon(new ImageIcon(JDialogLoginUser.class
					.getResource("/gui/utils/userPhoto.png")));
			lblUserIcon.setBounds(93, 11, 192, 146);
			mainPanel.add(lblUserIcon);

			passwordField = new JPasswordField();
			passwordField.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					passwordField.setBorder(bordeNegro);
				}
				@Override
				public void keyTyped(KeyEvent e){
					JTextField text = (JTextField) e.getSource();
					if(text.getText().length()== limite)
						e.consume();
				}
			});
			passwordField.setBounds(81, 234, 223, 20);
			mainPanel.add(passwordField);

			final JLabel loginBotton = new JLabel("");
			loginBotton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					loginBotton.setIcon(new ImageIcon(
							JDialogLoginUser.class
									.getResource("/gui/utils/LogInBottonSelected_60x25_via_10015_io.png")));
				}

				@Override
				public void mouseExited(MouseEvent e) {
					loginBotton.setIcon(new ImageIcon(
							JDialogLoginUser.class
									.getResource("/gui/utils/LogInBotton_60x25_via_10015_io.png")));
				}

				@Override
				public void mouseClicked(MouseEvent e) {
					if (nombreTextField.getText().isEmpty() || passwordField.getPassword().toString().isEmpty()) {
						nombreTextField.setBorder(bordeRojo);
						passwordField.setBorder(bordeRojo);
						JOptionPane.showMessageDialog(null,"Existen campos vac�os");
					} else {
						if (!login(Fct.getInstance().getUsuario())) {
							JOptionPane.showMessageDialog(null,"Credenciales Incorrectas");
							nombreTextField.setBorder(bordeRojo);
							passwordField.setBorder(bordeRojo);
						} else {
							JOptionPane.showMessageDialog(null,"Credenciales Correctas");
							dispose();
							// mainframe...
							/**
							 * Abrir la ventana principal
							 */
							EventQueue.invokeLater(new Runnable() {
								public void run() {
									try {
										MainFrame frame = new MainFrame();
										ImageIcon appIcono = new ImageIcon(
												MainFrame.class
														.getResource("/gui/utils/appicon.png"));
										frame.setIconImage(appIcono.getImage());
										frame.setVisible(true);
									} catch (Exception e1) {
										e1.printStackTrace();
									}
								}
							});
						}
					}
				}
			});
			loginBotton
					.setIcon(new ImageIcon(
							JDialogLoginUser.class
									.getResource("/gui/utils/LogInBotton_60x25_via_10015_io.png")));
			loginBotton.setBounds(158, 265, 60, 25);
			mainPanel.add(loginBotton);

		}
	}

	public boolean login(Usuario usuario) {
		boolean autorizado = false;
		String name = usuario.getNombre();
		String password = usuario.getPassword();
		String nameIn = nombreTextField.getText();
		String passwordIn = new String(passwordField.getPassword());
		if (nameIn.equals(name) && passwordIn.equals(password)) {
			autorizado = true;
		}
		return autorizado;
	}
}
