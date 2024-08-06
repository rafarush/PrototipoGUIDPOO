package logica;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JList;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

public class ComboBoxTextInicial extends BasicComboBoxRenderer{

	private String textoInicial;

	public ComboBoxTextInicial(String textoInicial) {
		setTextoInicial(textoInicial);
	}

	public String getTextoInicial() {
		return textoInicial;
	}

	public void setTextoInicial(String textoInicial) {
		this.textoInicial = textoInicial;
	}
	
	
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        
        // Si no hay un valor seleccionado, muestra el texto del placeholder
        if (value == null) {
            setText(textoInicial);
            setForeground(Color.GRAY); // Cambia el color del texto para simular un placeholder
        } else {
            setForeground(Color.BLACK); // Color normal para elementos seleccionables
        }
        
        return this;
    }
}
