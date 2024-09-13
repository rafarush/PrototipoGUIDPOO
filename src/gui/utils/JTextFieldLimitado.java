package gui.utils;

import javax.swing.JTextField;

public class JTextFieldLimitado extends JTextField{

	private static final long serialVersionUID = 1L;
	private int limite = -1;
	
	public int getLimite(){
		return this.limite;
	}
	
	public void setLimite(int limite){
		if(limite>=-1){
			this.limite = limite;
		}
	}
	
}
