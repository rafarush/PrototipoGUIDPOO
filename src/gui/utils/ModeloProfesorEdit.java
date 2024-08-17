package gui.utils;

import javax.swing.table.AbstractTableModel;

import java.util.List;

import logica.Profesor;

public class ModeloProfesorEdit extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private List<Profesor> profesores;
    private String[] columnNames = {"Carné de Identidad", "Nombre", "Categoría Científica","Categoría Docente",
    		"Centro Laboral","Organismo","Dirección","Salario"};

    public ModeloProfesorEdit(List<Profesor> profesores) {
        this.profesores = profesores;
    }

    @Override
    public int getRowCount() {
        return profesores.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
    	Profesor profesor = profesores.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return profesor.getID();
            case 1:
                return profesor.getNombre();
            case 2:
                return profesor.getCategoriaCientifica();
            case 3:
                return profesor.getCategoriaDocente();
            case 4:
                return profesor.getCentroLaboral();
            case 5:
                return profesor.getOrganismo();
            case 6:
                return profesor.getDireccion();
            case 7:
                return profesor.calcularSalario();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
}
