package Sensores;

import java.io.IOException;

import org.json.JSONException;

import GestionDatos.Facturas.FacturasDAO;
import GestionDatos.Facturas.SensoresVerGestionDatosFacturas;

public class Sensores implements GestionPagosYCalificacionesVerSensores {
	private SensoresVerGestionDatosFacturas datosFacturas;

	public Sensores() {
		this.datosFacturas = new FacturasDAO();
	}

	@Override
	public int leerVale() throws IOException, JSONException {
		int id=this.datosFacturas.obtenerUltimoIDVale();
		id++;
		return id;
	}

	@Override
	public int crearIDBandeja() throws IOException, JSONException {
		int id=this.datosFacturas.obtenerUltimoIDBandeja();
		id++;
		return id;
	}

	@Override
	public int devolverBandeja() throws IOException, JSONException {
		return this.datosFacturas.obtenerUltimoIDBandeja();
	}

}
