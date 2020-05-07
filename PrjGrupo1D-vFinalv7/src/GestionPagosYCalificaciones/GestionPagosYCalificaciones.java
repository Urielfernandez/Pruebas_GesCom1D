package GestionPagosYCalificaciones;

import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.json.JSONException;

import GestionDatos.Estadisticas.EstadisticasDAO;
import GestionDatos.Estadisticas.GestionPagosVerGestionDatosEstadisticas;
import GestionDatos.Facturas.FacturasDAO;
import GestionDatos.Facturas.GestionPagosVerGestionDatosFacturas;
import GestionDatos.TiposDatos.Estadistica;
import GestionDatos.TiposDatos.Factura;
import GestionDatos.TiposDatos.Menu;
import GestionDatos.TiposDatos.Plato;
import Sensores.GestionPagosYCalificacionesVerSensores;
import Sensores.Sensores;

public class GestionPagosYCalificaciones implements IUValoraciones, SeleccionMenuVerGestionPagosYCalificaciones {
	private GestionPagosYCalificacionesVerSensores sensor;
	private GestionPagosVerGestionDatosFacturas datosFacturas;
	private GestionPagosVerGestionDatosEstadisticas datosEstadisticas;

	public GestionPagosYCalificaciones() {
		this.sensor = new Sensores();
		this.datosEstadisticas = new EstadisticasDAO();
		this.datosFacturas = new FacturasDAO();
	}

	@Override
	public Factura simularComida(int idMenu, String primero, String segundo, String postre, String bebida)
			throws IllegalArgumentException, IOException, JSONException, ParseException, InterruptedException {
		this.solicitarVale();
		int idVale = sensor.leerVale();
		int idBandeja = sensor.crearIDBandeja();
		this.crearFactura(idBandeja, idVale, idMenu, primero, segundo, postre, bebida);
		Random rand = new Random();
		int tiempo = rand.nextInt(5);
		tiempo += 1;
		System.out.println("Inicio comida.");
		TimeUnit.SECONDS.sleep(tiempo);
		System.out.println("Final comida.");
		int idBandejaDevuelta = sensor.devolverBandeja();
		int tiempoReal = tiempo * 10;
		Factura factura = datosFacturas.obtenerFacturaSinFechaDevolucion(idBandejaDevuelta);
		if (factura != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(factura.getFechaRecogida());
			calendar.add(Calendar.MINUTE, tiempoReal);
			factura.setFechaDevolucion(calendar.getTime());
			this.anhadirFechaDevolucionFactura(factura);
		}

		return factura;
	}

	@Override
	public void valorarPlatos(Factura factura, int puntuacionPrimero, int puntuacionSegundo, int puntuacionPostre)
			throws IllegalArgumentException, IOException, JSONException {
		if (puntuacionPrimero < 0 || puntuacionPrimero > 10) {
			throw new IllegalArgumentException("Primer plato: error, el valor introducido no es válido.\n");
		} else {
			this.crearEstadistica(factura.getIdVale(), factura.getPrimero(), puntuacionPrimero);
		}
		if (puntuacionSegundo < 0 || puntuacionSegundo > 10) {
			throw new IllegalArgumentException("Segundo plato: error, el valor introducido no es válido.\n");
		} else {
			this.crearEstadistica(factura.getIdVale(), factura.getSegundo(), puntuacionSegundo);
		}
		if (puntuacionPostre < 0 || puntuacionPostre > 10) {
			throw new IllegalArgumentException("Postre: error, el valor introducido no es válido.\n");
		} else {
			this.crearEstadistica(factura.getIdVale(), factura.getPostre(), puntuacionPostre);
		}

	}

	public void solicitarVale() {
		System.out.println("Debe mostrar un vale al lector.");
	}

	public void crearFactura(int idBandeja, int idVale, int idMenu, String primero, String segundo, String postre,
			String bebida) throws IOException, JSONException, IllegalArgumentException {
		Date fechaEntrega = new Date();
		Factura factura = new Factura(idVale, idBandeja, idMenu, primero, segundo, postre, bebida, fechaEntrega, null);
		datosFacturas.insertarFactura(factura);
	}

	public void crearEstadistica(int idVale, String plato, int puntuacion)
			throws IllegalArgumentException, IOException, JSONException {
		Estadistica estadistica = new Estadistica(idVale, plato, puntuacion);
		datosEstadisticas.insertarEstadistica(estadistica);
	}

	public void anhadirFechaDevolucionFactura(Factura factura)
			throws IOException, JSONException, IllegalArgumentException {
		datosFacturas.actualizarFactura(factura);
	}

}
