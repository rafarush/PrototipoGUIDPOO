package gui.utils;

import gui.utils.ModeloGrupoEdit;
import gui.utils.ModeloPlanificacionDocenteEdit;
import gui.utils.ModeloProfesorEdit;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class JTableNoEdit extends JTable{

	public JTableNoEdit(DefaultTableModel model) {
		super(model);
	}
	public JTableNoEdit(ModeloPlanificacionDocenteEdit model) {
		super(model);
	}
	public JTableNoEdit(ModeloProfesorEdit model) {
		super(model);
	}
	public JTableNoEdit(ModeloGrupoEdit model) {
		super(model);
	}

	 @Override
	 public boolean editCellAt(int row, int column, java.util.EventObject e) {
	    return false; // No permite la edición
	 }
	 
}
