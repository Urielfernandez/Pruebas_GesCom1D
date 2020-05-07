package AnalisisYEstadisticas;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;

import org.json.JSONException;

import GestionDatos.Estadisticas.AnalisisYEstadisticasVerGestionDatosEstadisticas;
import GestionDatos.Estadisticas.EstadisticasDAO;
import GestionDatos.Facturas.AnalisisYEstadisticasVerGestionDatosFacturas;
import GestionDatos.Facturas.FacturasDAO;
import GestionDatos.Menus.ExcepcionFechaMenu;
import GestionDatos.Menus.GestionMenusVerGestionDatosMenu;
import GestionDatos.TiposDatos.Estadistica;
import GestionDatos.TiposDatos.Factura;
import GestionDatos.TiposDatos.Plato;

public class AnalisisYEstadisticas implements IUVisionMenusEstadisticas {
	private AnalisisYEstadisticasVerGestionDatosFacturas datosFacturas;
	private AnalisisYEstadisticasVerGestionDatosEstadisticas datosEstadisticas;

	public AnalisisYEstadisticas() {
		this.datosFacturas = new FacturasDAO();
		this.datosEstadisticas = new EstadisticasDAO();
	}

	@Override
	public HashMap<String, Double> verPuntuacionesPlatosDia() throws IllegalArgumentException, IOException, JSONException{
		ArrayList<Plato> platos = new ArrayList<>();
		ArrayList<Integer> vales = new ArrayList<>();
		HashMap<Plato, Integer> puntuaciones = new HashMap<>();
		HashMap<Plato, Integer> apariciones = new HashMap<>();
		ArrayList<Plato> nomPlatos = new ArrayList<>();
		int i, j, valoracion;
		Plato plato;
		
		HashMap<String, Double> resultado = new HashMap<>();

		platos = datosFacturas.obtenerPlatosConsumidos(new Date());
		vales = datosFacturas.obtenerValesCanjeados(new Date());

		for (i = 0; i < platos.size(); i++) {
			if (!nomPlatos.contains(platos.get(i))) {
				nomPlatos.add(platos.get(i));
			}
		}

		for (i = 0; i < vales.size(); i++) {
			for (j = 0; j < nomPlatos.size(); j++) {
				plato = nomPlatos.get(j);
				valoracion = datosEstadisticas.obtenerValoracion(vales.get(i), plato.getNombre());
				if (valoracion != -1) {
					if (puntuaciones.containsKey(plato)) {
						puntuaciones.replace(plato, puntuaciones.get(plato) + valoracion);
						apariciones.replace(plato, apariciones.get(plato) + 1);
					} else {
						puntuaciones.put(plato, valoracion);
						apariciones.put(plato, 1);
					}
				}
			}
		}
		
		for (Entry<Plato, Integer> entrada : puntuaciones.entrySet()) {
			resultado.put(entrada.getKey().getNombre(), Double.valueOf(entrada.getValue()) / apariciones.get(entrada.getKey()));
		}
		
		return resultado;
	}

	@Override
	public int verOcupacionActual() throws IOException, JSONException, ParseException{
		ArrayList<Factura> ocupado = new ArrayList<>();

		ocupado = datosFacturas.obtenerFacturasSinFechaDevolucion();

		return ocupado.size();
	}

	@Override
	public HashMap<Integer, Double> verHorasMasConcurridas() throws IOException, JSONException {
		HashMap<Date, Date> tiempos = new HashMap<>();
		HashMap<Integer, Integer> apariciones = new HashMap<>();
		HashMap<Integer, ArrayList<Integer>> media = new HashMap<>();
		int i, maxOcu, horaMaxOcu = 0;
		int quedan = 1;
		int hora, dia;
		
		HashMap<Integer, Double> resultado = new HashMap<>();
		
		tiempos = datosFacturas.obtenerParesFechas();
		
		for (Entry<Date, Date> comidas : tiempos.entrySet()) {
			hora = comidas.getKey().getHours();
			dia = comidas.getKey().getDay();
			if (apariciones.containsKey(hora)) {
				apariciones.replace(hora, apariciones.get(hora) + 1);
				if (!media.get(hora).contains(dia)) {
					media.get(hora).add(dia);
				}
			} else {
				apariciones.put(hora, 1);
				media.put(hora, new ArrayList<>());
				media.get(hora).add(dia);
			}
		}
		
		while (quedan == 1) {
			maxOcu = 0;
			quedan = 0;
			for (Entry<Integer, Integer> horas : apariciones.entrySet()) {
				if (horas.getValue() > maxOcu) {
					maxOcu = horas.getValue();
					horaMaxOcu = horas.getKey();
					quedan = 1;
				}
			}
			if (quedan == 1) {
				apariciones.remove(horaMaxOcu);
			
				resultado.put(horaMaxOcu, Double.valueOf(maxOcu) / media.get(horaMaxOcu).size());
			}
		}
		
		return resultado;
	}

	@Override
	public HashMap<String, Double> verRankginPlatos() throws IOException, JSONException {
		HashMap<String, Double> ranking = new HashMap<>();
		HashMap<String, Integer> apariciones = new HashMap<>();
		ArrayList<Estadistica> estadisticas = new ArrayList<>();
		int i;
		String plato = null;
		int puntuacion;
		double puntuacionTop;
		int tamanho;
		
		HashMap<String, Double> resultado = new HashMap<>();
		
		estadisticas = datosEstadisticas.obtenerEstadisticas();

		for (i = 0; i < estadisticas.size(); i++) {
			plato = estadisticas.get(i).getPlato();
			puntuacion = estadisticas.get(i).getPuntuacion();
			if (ranking.containsKey(plato)) {
				ranking.replace(plato, ranking.get(plato) + puntuacion);
				apariciones.replace(plato, apariciones.get(plato) + 1);
			} else {
				ranking.put(plato, Double.valueOf(puntuacion));
				apariciones.put(plato, 1);
			}
		}

		for (Entry<String, Double> actualiza : ranking.entrySet()) {
			plato = actualiza.getKey();
			ranking.replace(plato, actualiza.getValue() / apariciones.get(plato));
		}

		if (ranking.size() >= 10) {
			tamanho = 10;
		} else {
			tamanho = ranking.size();
		}

		for (i = 0; i < tamanho; i++) {
			puntuacionTop = 0;
			for (Entry<String, Double> entrada : ranking.entrySet()) {
				if (entrada.getValue() > puntuacionTop) {
					plato = entrada.getKey();
					puntuacionTop = entrada.getValue();
				}
			}
			ranking.remove(plato);
			resultado.put(plato, puntuacionTop);
		}
		
		return resultado;
	}

	@Override
	public HashMap<Plato, Integer> verPlatosMasComidos(Date fecha) throws IllegalArgumentException, IOException, JSONException, ExcepcionFechaAnalisis{
		if (fecha != null) {
			if (!fecha.after(new Date())) {
				HashMap<Plato, Integer> apariciones = new HashMap<>();
				ArrayList<Plato> platosCons = new ArrayList<>();
				int i;
				Plato plato = null;
				int maxApariciones, numPlatos;
				
				HashMap<Plato, Integer> resultado = new HashMap<>();

				platosCons = datosFacturas.obtenerPlatosConsumidos(fecha);

				for (i = 0; i < platosCons.size(); i++) {
					plato = platosCons.get(i);
					if (apariciones.containsKey(plato)) {
						apariciones.replace(plato, apariciones.get(plato) + 1);
					} else {
						apariciones.put(plato, 1);
					}
				}

				numPlatos = apariciones.size();
				for (i = 0; i < numPlatos; i++) {
					maxApariciones = 0;
					for (Entry<Plato, Integer> entrada : apariciones.entrySet()) {
						if (entrada.getValue() >= maxApariciones) {
							plato = entrada.getKey();
							maxApariciones = entrada.getValue();
						}
					}
					apariciones.remove(plato);
					resultado.put(plato, maxApariciones);
				}
				
				return resultado;
			} else {
				throw new ExcepcionFechaAnalisis("La fecha indicada es superior a la actual.");
			}
		} else {
			throw new IllegalArgumentException("No se ha pasado ninguna fecha como parámetro.");
		}
	}

	@Override
	public Double verDuracionMediaComidas() throws IOException, JSONException{
		HashMap<Date, Date> tiempos = new HashMap<>();
		int duracion = 0;
		int numComidas = 0;

		tiempos = datosFacturas.obtenerParesFechas();

		for (Entry<Date, Date> comida : tiempos.entrySet()) {
			duracion += comida.getValue().getTime() - comida.getKey().getTime();
			numComidas++;
		}

		duracion = duracion / numComidas;

		return (Double.valueOf(duracion) / (60 * 60 * 1000));
	}

}
