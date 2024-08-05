package logica;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class JTableNoEdit extends JTable{

	public JTableNoEdit(DefaultTableModel model) {
		super(model);
	}

	 @Override
	 public boolean editCellAt(int row, int column, java.util.EventObject e) {
	    return false; // No permite la edición
	 }
}
