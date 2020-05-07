package GestionDatos.Facturas;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import GestionDatos.TiposDatos.Factura;
import GestionDatos.TiposDatos.Plato;

public class FacturasDAO implements AnalisisYEstadisticasVerGestionDatosFacturas, GestionPagosVerGestionDatosFacturas,
		SensoresVerGestionDatosFacturas {

	public FacturasDAO() {

	}

	@Override
	public void insertarFactura(Factura factura) throws IOException, JSONException, IllegalArgumentException {
		if (factura != null) {
			String texto = "";
			File f = new File("facturas.json");

			texto = new String(Files.readAllBytes(f.toPath()));

			JSONArray array = new JSONArray(texto);
			JSONObject obj = new JSONObject();
			obj.put("idVale", factura.getIdVale());
			obj.put("idBandeja", factura.getIdBandeja());
			obj.put("idMenu", factura.getIdMenu());
			obj.put("primero", factura.getPrimero());
			obj.put("segundo", factura.getSegundo());
			obj.put("postre", factura.getPostre());
			obj.put("bebida", factura.getBebida());
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy kk:mm");
			obj.put("fechaRecogida", dateFormat.format(factura.getFechaRecogida()));
			obj.put("fechaDevolucion", "null");

			array.put(obj);
			String resultado = "[";
			for (int i = 0; i < array.length() - 1; i++) {
				resultado += array.getJSONObject(i).toString() + ",";
			}
			resultado += array.getJSONObject(array.length() - 1).toString() + "]";
			try (FileWriter file = new FileWriter("facturas.json")) {
				file.write(resultado);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			
		} else {
			throw new IllegalArgumentException("No se ha pasado ninguna factura como parámetro");
		}

	}

	@Override
	public void actualizarFactura(Factura factura) throws IOException, JSONException, IllegalArgumentException {
		if (factura != null) {
			String texto = "";
			int indice = -1;
			File f = new File("facturas.json");

			texto = new String(Files.readAllBytes(f.toPath()));

			JSONArray array = new JSONArray(texto);
			for (int i = 0; i < array.length(); i++) {
				if (array.getJSONObject(i).getInt("idVale") == factura.getIdVale()) {
					indice = i;
					break;
				}
			}

			JSONObject obj = new JSONObject();
			obj.put("idVale", factura.getIdVale());
			obj.put("idBandeja", factura.getIdBandeja());
			obj.put("idMenu", factura.getIdMenu());
			obj.put("primero", factura.getPrimero());
			obj.put("segundo", factura.getSegundo());
			obj.put("postre", factura.getPostre());
			obj.put("bebida", factura.getBebida());
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy kk:mm");
			obj.put("fechaRecogida", dateFormat.format(factura.getFechaRecogida()));
			obj.put("fechaDevolucion", dateFormat.format(factura.getFechaDevolucion()));

			array.put(indice, obj);

			String resultado = "[";
			for (int j = 0; j < array.length() - 1; j++) {
				resultado += array.getJSONObject(j).toString() + ",";
			}
			resultado += array.getJSONObject(array.length() - 1).toString() + "]";
			try (FileWriter file = new FileWriter("facturas.json")) {
				file.write(resultado);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		} else {
			throw new IllegalArgumentException("No se ha pasado ninguna factura como parámetro");
		}

	}

	@Override
	public Factura obtenerFacturaSinFechaDevolucion(int idBandeja)
			throws IllegalArgumentException, IOException, JSONException, ParseException {
		if (idBandeja > -1) {
			Factura factura = null;
			String texto = "";
			File f = new File("facturas.json");

			texto = new String(Files.readAllBytes(f.toPath()));

			JSONArray array = new JSONArray(texto);
			for (int i = 0; i < array.length(); i++) {
				if (array.getJSONObject(i).getInt("idBandeja") == idBandeja
						&& array.getJSONObject(i).getString("fechaDevolucion").equals("null")) {
					JSONObject obj = array.getJSONObject(i);
					int idVale = obj.getInt("idVale");
					int idMenu = obj.getInt("idMenu");
					String primero = obj.getString("primero");
					String segundo = obj.getString("segundo");
					String postre = obj.getString("postre");
					String bebida = obj.getString("bebida");
					String fechaRecogida = obj.getString("fechaRecogida");
					SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy kk:mm");

					Date fechaR = formatter.parse(fechaRecogida);
					factura = new Factura(idVale, idBandeja, idMenu, primero, segundo, postre, bebida, fechaR, null);

				}
			}

			return factura;
		} else {
			throw new IllegalArgumentException("El idBandeja debe ser un número positivo.");
		}

	}

	@Override
	public ArrayList<Plato> obtenerPlatosConsumidos(Date fecha)
			throws IllegalArgumentException, IOException, JSONException {
		if (fecha != null) {
			ArrayList<Plato> platos = new ArrayList<>();
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			String fechaFormateada = dateFormat.format(fecha);
			String texto = "";
			File f = new File("facturas.json");

			texto = new String(Files.readAllBytes(f.toPath()));

			JSONArray array = new JSONArray(texto);
			for (int i = 0; i < array.length(); i++) {
				String fechaDevolucion = array.getJSONObject(i).getString("fechaDevolucion");
				if (!fechaDevolucion.equals("null")) {
					String[] splited = fechaDevolucion.split("\\s+");
					if (splited[0].equals(fechaFormateada)) {
						JSONObject obj = array.getJSONObject(i);
						String nombrePrimero = obj.getString("primero");
						String nombreSegundo = obj.getString("segundo");
						String nombrePostre = obj.getString("postre");
						Plato primero = new Plato(nombrePrimero, "primero");
						Plato segundo = new Plato(nombreSegundo, "segundo");
						Plato postre = new Plato(nombrePostre, "postre");
						platos.add(primero);
						platos.add(segundo);
						platos.add(postre);
					}
				}
			}

			return platos;
		} else {
			throw new IllegalArgumentException("No se ha pasado ninguna fecha como parámetro");
		}

	}

	@Override
	public ArrayList<Integer> obtenerValesCanjeados(Date fecha)
			throws IllegalArgumentException, IOException, JSONException {
		if (fecha != null) {
			ArrayList<Integer> vales = new ArrayList<>();
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			String fechaFormateada = dateFormat.format(fecha);
			String texto = "";
			File f = new File("facturas.json");

			texto = new String(Files.readAllBytes(f.toPath()));

			JSONArray array = new JSONArray(texto);
			for (int i = 0; i < array.length(); i++) {
				String fechaDevolucion = array.getJSONObject(i).getString("fechaDevolucion");
				if (!fechaDevolucion.equals("null")) {
					String[] splited = fechaDevolucion.split("\\s+");
					if (splited[0].equals(fechaFormateada)) {
						JSONObject obj = array.getJSONObject(i);
						int idVale = obj.getInt("idVale");
						vales.add(idVale);
					}
				}
			}

			return vales;
		} else {
			throw new IllegalArgumentException("No se ha pasado ninguna fecha como parámetro");
		}

	}

	@Override
	public ArrayList<Factura> obtenerFacturasSinFechaDevolucion() throws IOException, JSONException, ParseException {
		ArrayList<Factura> facturas = new ArrayList<>();
		String texto = "";
		File f = new File("facturas.json");

		texto = new String(Files.readAllBytes(f.toPath()));

		JSONArray array = new JSONArray(texto);
		for (int i = 0; i < array.length(); i++) {
			if (array.getJSONObject(i).getString("fechaDevolucion").equals("null")) {
				JSONObject obj = array.getJSONObject(i);
				int idVale = obj.getInt("idVale");
				int idMenu = obj.getInt("idMenu");
				int idBandeja = obj.getInt("idBandeja");
				String primero = obj.getString("primero");
				String segundo = obj.getString("segundo");
				String postre = obj.getString("postre");
				String bebida = obj.getString("bebida");
				String fechaRecogida = obj.getString("fechaRecogida");
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy kk:mm");

				Date fechaR = formatter.parse(fechaRecogida);
				Factura factura = new Factura(idVale, idBandeja, idMenu, primero, segundo, postre, bebida, fechaR,
						null);
				facturas.add(factura);

			}
		}

		return facturas;
	}

	@Override
	public HashMap<Date, Date> obtenerParesFechas() throws IOException, JSONException {
		HashMap<Date, Date> pares = new HashMap<>();
		String texto = "";
		File f = new File("facturas.json");

		texto = new String(Files.readAllBytes(f.toPath()));

		JSONArray array = new JSONArray(texto);
		for (int i = 0; i < array.length(); i++) {
			if (!array.getJSONObject(i).getString("fechaDevolucion").equals("null")) {
				JSONObject obj = array.getJSONObject(i);
				String fechaRecogida = obj.getString("fechaRecogida");
				String fechaDevolucion = obj.getString("fechaDevolucion");
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy kk:mm");
				try {
					Date fechaR = formatter.parse(fechaRecogida);
					Date fechaD = formatter.parse(fechaDevolucion);
					pares.put(fechaR, fechaD);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return pares;
	}

	@Override
	public int obtenerUltimoIDVale() throws IOException, JSONException {
		int id;
		ArrayList<Integer> vales = new ArrayList<>();
		String texto = "";
		File f = new File("facturas.json");

		texto = new String(Files.readAllBytes(f.toPath()));

		JSONArray array = new JSONArray(texto);
		for (int i = 0; i < array.length(); i++) {
			JSONObject obj = array.getJSONObject(i);
			int idVale = obj.getInt("idVale");
			vales.add(idVale);

		}
		Collections.sort(vales);

		if (vales.size() > 0) {
			id = vales.get(vales.size() - 1);
		} else {
			id = -1;
		}
		return id;
	}

	@Override
	public int obtenerUltimoIDBandeja() throws IOException, JSONException {
		int id;
		ArrayList<Integer> bandejas = new ArrayList<>();
		String texto = "";
		File f = new File("facturas.json");

		texto = new String(Files.readAllBytes(f.toPath()));

		JSONArray array = new JSONArray(texto);
		for (int i = 0; i < array.length(); i++) {
			JSONObject obj = array.getJSONObject(i);
			int idBandeja = obj.getInt("idBandeja");
			bandejas.add(idBandeja);

		}
		Collections.sort(bandejas);

		if (bandejas.size() > 0) {
			id = bandejas.get(bandejas.size() - 1);
		} else {
			id = -1;
		}

		return id;
	}

}
