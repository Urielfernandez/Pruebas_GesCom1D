package GestionPagosYCalificaciones;

import java.io.IOException;
import java.text.ParseException;

import org.json.JSONException;

import GestionDatos.TiposDatos.Factura;

public interface SeleccionMenuVerGestionPagosYCalificaciones {
	public Factura simularComida(int idMenu, String primero, String segundo, String postre, String bebida)
			throws IllegalArgumentException, IOException, JSONException, ParseException, InterruptedException;
}
