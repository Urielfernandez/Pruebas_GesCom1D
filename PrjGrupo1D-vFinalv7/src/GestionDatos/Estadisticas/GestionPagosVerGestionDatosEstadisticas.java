package GestionDatos.Estadisticas;

import java.io.IOException;

import org.json.JSONException;

import GestionDatos.TiposDatos.Estadistica;

public interface GestionPagosVerGestionDatosEstadisticas {
	public void insertarEstadistica(Estadistica estadistica)
			throws IllegalArgumentException, IOException, JSONException;

}
