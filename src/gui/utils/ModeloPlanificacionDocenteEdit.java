package gui.utils;

import javax.swing.table.AbstractTableModel;

import java.util.List;

import logica.PlanificacionDocente;

public class ModeloPlanificacionDocenteEdit extends AbstractTableModel {

	private List<PlanificacionDocente> planificaciones;
    private String[] columnNames = {"Profesor", "Asignatura", "Grupo"};

    public ModeloPlanificacionDocenteEdit(List<PlanificacionDocente> planificaciones) {
        this.planificaciones = planificaciones;
    }

    @Override
    public int getRowCount() {
        return planificaciones.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        PlanificacionDocente planificacion = planificaciones.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return planificacion.getProfesor().getNombre();
            case 1:
                return planificacion.getAsignatura().getNombre();
            case 2:
                return planificacion.getGrupo().getNombreGrupo();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
}
