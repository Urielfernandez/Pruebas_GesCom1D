package GestionDatos.Facturas;

import java.io.IOException;
import java.text.ParseException;

import org.json.JSONException;

import GestionDatos.TiposDatos.Factura;

public interface GestionPagosVerGestionDatosFacturas {
	   public void insertarFactura(Factura factura) throws IOException, JSONException, IllegalArgumentException;
	    public void actualizarFactura(Factura factura) throws IOException, JSONException, IllegalArgumentException;
	    public Factura obtenerFacturaSinFechaDevolucion(int idBandeja) throws IOException, JSONException, ParseException, IllegalArgumentException;
}
