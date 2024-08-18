package gui.dialogs;


import gui.mainFrame.MainFrame;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import runner.Runner;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.Semaphore;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JPasswordField;
import javax.swing.SwingWorker;

import logica.User;

import javax.swing.JProgressBar;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;



public class LoginUser extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private final JPanel upperBarPanel = new JPanel();
	private JTextField nombreTextField;
	private JPasswordField passwordField;

	/**
	 * Crear Input para agregar
	 */
	public LoginUser() {
		setUndecorated(true);
		setModal(true);
		setBounds(100, 100, 538, 360);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		
		
		final JLabel closeBotton = new JLabel("");
		closeBotton.setIcon(new ImageIcon(LoginUser.class.getResource("/gui/utils/closeBotton.png")));
		closeBotton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				closeBotton.setIcon(new ImageIcon(LoginUser.class.getResource("/gui/utils/closeBottonSelected1.png")));
				closeBotton.setToolTipText("Cerrar");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				closeBotton.setIcon(new ImageIcon(LoginUser.class.getResource("/gui/utils/closeBotton.png")));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				if(JOptionPane.showConfirmDialog(null, "¿Seguro que desea cerrar la aplicación", "Confirmación", 
						JOptionPane.YES_NO_OPTION)== JOptionPane.YES_OPTION){
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
		
		
		JLabel nameFrame = new JLabel("Bienvenido al Gestor de la Filial de Ciencias T\u00E9cnicas");
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
			
			nombreTextField = new JTextField();
			nombreTextField.setToolTipText("Nombre de usuario");
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
			lblUserIcon.setIcon(new ImageIcon(LoginUser.class.getResource("/gui/utils/userPhoto.png")));
			lblUserIcon.setBounds(93, 11, 192, 146);
			mainPanel.add(lblUserIcon);
			
			passwordField = new JPasswordField();
			passwordField.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyChar() == '\n'){
						if (nombreTextField.getText().isEmpty() || passwordField.getPassword().toString().isEmpty()){
							JOptionPane.showMessageDialog(null, "Existen campos vacíos");
						}else{
							if(!login()){
								JOptionPane.showMessageDialog(null, "Credenciales Incorrectas");
							}else{
								JOptionPane.showMessageDialog(null, "Credenciales Correctas");
								
								SwingWorker<Void, Integer> worker = new SwingWorker<Void, Integer>() {
							            @Override
							            protected Void doInBackground() throws Exception {
							                for (int i = 0; i <= 100; i++) {
							                    publish(i);
							                    Thread.sleep(7);
							                }
							                dispose();
							                return null;

							            }

							            @Override
							            protected void process(java.util.List<Integer> chunks) {
							                int lastValue = chunks.get(chunks.size() - 1);
							                progressBar.setValue(lastValue);
							            }
							        };
							     worker.execute();
							     //mainframe...
							     /**
							      * Abrir la ventana principal
							      */
							      
									EventQueue.invokeLater(new Runnable() {
										public void run() {
											try {
												Runner.frame = new MainFrame();
												ImageIcon appIcono = new ImageIcon(MainFrame.class.getResource("/gui/utils/appicon.png"));
												Runner.frame.setIconImage(appIcono.getImage());
												Runner.frame.setVisible(true);
											} catch (Exception e1) {
												e1.printStackTrace();
											}
										}
									});
							}
						}
					}
				}
			});
			passwordField.setBounds(81, 234, 223, 20);
			mainPanel.add(passwordField);
			
			final JLabel loginBotton = new JLabel("");
			loginBotton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					loginBotton.setIcon(new ImageIcon(LoginUser.class.getResource("/gui/utils/LogInBottonSelected_60x25_via_10015_io.png")));
				}
				@Override
				public void mouseExited(MouseEvent e) {
					loginBotton.setIcon(new ImageIcon(LoginUser.class.getResource("/gui/utils/LogInBotton_60x25_via_10015_io.png")));
				}
				@Override
				public void mouseClicked(MouseEvent e) {
					if (nombreTextField.getText().isEmpty() || passwordField.getPassword().toString().isEmpty()){
						JOptionPane.showMessageDialog(null, "Existen campos vacíos");
					}else{
						if(!login()){
							JOptionPane.showMessageDialog(null, "Credenciales Incorrectas");
						}else{
							JOptionPane.showMessageDialog(null, "Credenciales Correctas");
							
							SwingWorker<Void, Integer> worker = new SwingWorker<Void, Integer>() {
						            @Override
						            protected Void doInBackground() throws Exception {
						                for (int i = 0; i <= 100; i++) {
						                    publish(i);
						                    Thread.sleep(7);
						                }
						                dispose();
						                return null;

						            }

						            @Override
						            protected void process(java.util.List<Integer> chunks) {
						                int lastValue = chunks.get(chunks.size() - 1);
						                progressBar.setValue(lastValue);
						            }
						        };
						     worker.execute();
						     //mainframe...
						     /**
						      * Abrir la ventana principal
						      */
						      
								EventQueue.invokeLater(new Runnable() {
									public void run() {
										try {
											Runner.frame = new MainFrame();
											ImageIcon appIcono = new ImageIcon(MainFrame.class.getResource("/gui/utils/appicon.png"));
											Runner.frame.setIconImage(appIcono.getImage());
											Runner.frame.setVisible(true);
											//Runner.frame.setExtendedState(JFrame.NORMAL);
										} catch (Exception e1) {
											e1.printStackTrace();
										}
									}
								});
						}
					}
				}
			});
			loginBotton.setIcon(new ImageIcon(LoginUser.class.getResource("/gui/utils/LogInBotton_60x25_via_10015_io.png")));
			loginBotton.setBounds(158, 265, 60, 25);
			mainPanel.add(loginBotton);
			
			
		}
	}
	
	public boolean login(){
		boolean autorizado = false;
		String name = Runner.usuario.getName();
		String password = Runner.usuario.getPassword();
		String nameIn = nombreTextField.getText();
		String passwordIn = new String(passwordField.getPassword());
		if(nameIn.equals(name) && passwordIn.equals(password)){
			autorizado = true;
		}
		return autorizado;
	}
}
