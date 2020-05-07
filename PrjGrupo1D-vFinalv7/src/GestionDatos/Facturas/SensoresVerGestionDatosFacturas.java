package GestionDatos.Facturas;

import java.io.IOException;

import org.json.JSONException;

public interface SensoresVerGestionDatosFacturas {

	public int obtenerUltimoIDVale() throws IOException, JSONException;
	public int obtenerUltimoIDBandeja() throws IOException, JSONException;
}
