package gui.utils;

import javax.swing.table.AbstractTableModel;

import java.util.List;

import logica.Grupo;
import logica.PlanificacionDocente;

public class ModeloGrupoEdit extends AbstractTableModel {

	private List<Grupo> grupos;
    private String[] columnNames = {"Nombre", "Año", "Grupo"};

    public ModeloGrupoEdit(List<Grupo> grupos) {
        this.grupos = grupos;
    }

    @Override
    public int getRowCount() {
        return grupos.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Grupo grupo = grupos.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return grupo.getNombreGrupo();
            case 1:
                return grupo.getAnnoAcademico();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
}
