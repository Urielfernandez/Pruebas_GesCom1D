package GestionDatos.Facturas;

import GestionDatos.TiposDatos.Factura;
import GestionDatos.TiposDatos.Plato;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.json.JSONException;

public interface AnalisisYEstadisticasVerGestionDatosFacturas {
    public ArrayList<Plato> obtenerPlatosConsumidos(Date fecha) throws IllegalArgumentException, IOException, JSONException;
    public ArrayList<Integer> obtenerValesCanjeados(Date fecha) throws IllegalArgumentException, IOException, JSONException;
    public ArrayList<Factura> obtenerFacturasSinFechaDevolucion() throws IOException, JSONException, ParseException;
    public HashMap<Date,Date> obtenerParesFechas() throws IOException, JSONException;
}
