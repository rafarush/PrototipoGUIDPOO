package gui.utils;

import javax.swing.*;
import javax.swing.InputVerifier;

public class InputVerificadorPersonalizado extends InputVerifier {
	
	private int limit;

    public InputVerificadorPersonalizado(int limit) {
        this.limit = limit;
    }

    @Override
    public boolean verify(JComponent input) {
        JTextField textField = (JTextField) input;
        return textField.getText().length() <= limit;
    }
}
