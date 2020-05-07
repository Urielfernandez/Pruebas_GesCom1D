package GestionDatos.Estadisticas;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;

import GestionDatos.TiposDatos.Estadistica;

public interface AnalisisYEstadisticasVerGestionDatosEstadisticas {
    public ArrayList<Estadistica> obtenerEstadisticas() throws IOException, JSONException;
	public int obtenerValoracion(int idVale, String plato) throws IOException, IllegalArgumentException, JSONException;
}
