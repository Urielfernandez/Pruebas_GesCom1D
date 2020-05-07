package GestionDatos.Estadisticas;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import GestionDatos.TiposDatos.Estadistica;

public class EstadisticasDAO
		implements AnalisisYEstadisticasVerGestionDatosEstadisticas, GestionPagosVerGestionDatosEstadisticas {

	public EstadisticasDAO() {

	}

	@Override
	public void insertarEstadistica(Estadistica estadistica)
			throws IllegalArgumentException, IOException, JSONException {
		if (estadistica != null) {
			String texto = "";
			File f = new File("estadisticas.json");

			texto = new String(Files.readAllBytes(f.toPath()));

			JSONArray array = new JSONArray(texto);
			JSONObject obj = new JSONObject();
			obj.put("plato", estadistica.getPlato());
			obj.put("puntuacion", estadistica.getPuntuacion());
			obj.put("idVale", estadistica.getIdVale());

			array.put(obj);
			String resultado = "[";
			for (int i = 0; i < array.length() - 1; i++) {
				resultado += array.getJSONObject(i).toString() + ",";
			}
			resultado += array.getJSONObject(array.length() - 1).toString() + "]";
			
			try (FileWriter file = new FileWriter("estadisticas.json")) {
				file.write(resultado);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		} else {
			throw new IllegalArgumentException("No se ha pasado ninguna estadística como parámetro");
		}

	}

	@Override
	public ArrayList<Estadistica> obtenerEstadisticas() throws IOException, JSONException {
		ArrayList<Estadistica> estadisticas = new ArrayList<>();
		String texto = "";
		File f = new File("estadisticas.json");

		texto = new String(Files.readAllBytes(f.toPath()));

		JSONArray array = new JSONArray(texto);
		for (int i = 0; i < array.length(); i++) {
			JSONObject obj = array.getJSONObject(i);
			int idVale = obj.getInt("idVale");
			String nombrePlato = obj.getString("plato");
			int puntuacion = obj.getInt("puntuacion");
			Estadistica est = new Estadistica(idVale, nombrePlato, puntuacion);
			estadisticas.add(est);
		}

		return estadisticas;
	}

	@Override
	public int obtenerValoracion(int idVale, String plato) throws IOException, IllegalArgumentException, JSONException {
		if (idVale > -1) {
			if (plato != null) {
				int valoracion = -1;
				String texto = "";
				File f = new File("estadisticas.json");

				texto = new String(Files.readAllBytes(f.toPath()));

				JSONArray array = new JSONArray(texto);
				for (int i = 0; i < array.length(); i++) {
					if (array.getJSONObject(i).getInt("idVale") == idVale
							&& array.getJSONObject(i).getString("plato").equals(plato)) {
						JSONObject obj = array.getJSONObject(i);
						valoracion = obj.getInt("puntuacion");
					}
				}

				return valoracion;
			} else {
				throw new IllegalArgumentException("No se ha pasado ningún nombre de plato como parámetro");
			}
		} else {
			throw new IllegalArgumentException("El idVale debe ser un entero positivo.");

		}

	}

}
