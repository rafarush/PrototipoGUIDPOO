package gui.utils;

import javax.swing.table.AbstractTableModel;

import java.util.List;

import logica.Clases.PlanificacionDocente;

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
        Object valor;
        switch (columnIndex) {
            case 0:
                valor =  planificacion.getProfesor().getNombre();
            case 1:
            	valor = planificacion.getAsignatura().getNombre();
            case 2:
            	valor = planificacion.getGrupo().getNombreGrupo();
            default:
            	valor = null;
        }
        return valor;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
}
