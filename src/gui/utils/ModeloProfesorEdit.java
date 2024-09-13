package gui.utils;

import javax.swing.table.AbstractTableModel;

import java.util.List;

import logica.Clases.Profesor;

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
    	Object valor;
        switch (columnIndex) {
            case 0:
                valor = profesor.getID();
            case 1:
            	valor =  profesor.getNombre();
            case 2:
            	valor =  profesor.getCategoriaCientifica();
            case 3:
            	valor =  profesor.getCategoriaDocente();
            case 4:
            	valor =  profesor.getCentroLaboral();
            case 5:
            	valor =  profesor.getOrganismo();
            case 6:
            	valor =  profesor.getDireccion();
            case 7:
            	valor =  profesor.calcularSalario();
            default:
            	valor =  null;
        }
        return valor;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
}
