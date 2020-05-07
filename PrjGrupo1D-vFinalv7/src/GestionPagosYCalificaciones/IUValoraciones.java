package GestionPagosYCalificaciones;

import java.io.IOException;

import org.json.JSONException;

import GestionDatos.TiposDatos.Factura;

public interface IUValoraciones {
	public void valorarPlatos(Factura factura, int puntuacionPrimero, int puntuacionSegundo, int puntuacionPostre) throws IllegalArgumentException, IOException, JSONException;
}
