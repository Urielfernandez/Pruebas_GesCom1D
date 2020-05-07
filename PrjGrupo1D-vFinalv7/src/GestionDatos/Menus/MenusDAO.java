package GestionDatos.Menus;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import GestionDatos.TiposDatos.Menu;
import GestionDatos.TiposDatos.Plato;

public class MenusDAO implements GestionMenusVerGestionDatosMenu {

	public MenusDAO() {

	}

	@Override
	public void insertarMenu(Menu menu)
			throws IOException, JSONException, ExcepcionFechaMenu, IllegalArgumentException {
		if (menu != null) {
			if (obtenerMenuPorFecha(menu.getFecha()) == null) {
				String texto = "";
				int idAnterior = 0;
				File f = new File("menus.json");
				texto = new String(Files.readAllBytes(f.toPath()));

				JSONArray array = new JSONArray(texto);
				idAnterior = array.getJSONObject(array.length() - 1).getInt("id");
				JSONObject obj = new JSONObject();
				obj.put("id", idAnterior + 1);
				ArrayList<String> bebidas = new ArrayList<>();
				bebidas.add("agua");
				bebidas.add("cocacola");
				bebidas.add("fantaLimon");
				bebidas.add("fantaNaranja");
				obj.put("bebidas", new JSONArray(bebidas));
				ArrayList<String> nombrePrimeros = new ArrayList<>();
				for (Plato plato : menu.getPrimeros()) {
					nombrePrimeros.add(plato.getNombre());
				}
				obj.putOnce("primeros", new JSONArray(nombrePrimeros));
				ArrayList<String> nombreSegundos = new ArrayList<>();
				for (Plato plato : menu.getSegundos()) {
					nombreSegundos.add(plato.getNombre());
				}
				obj.putOnce("segundos", new JSONArray(nombreSegundos));
				ArrayList<String> nombrePostres = new ArrayList<>();
				for (Plato plato : menu.getPostres()) {
					nombrePostres.add(plato.getNombre());
				}
				obj.putOnce("postres", new JSONArray(nombrePostres));
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				String fecha = dateFormat.format(menu.getFecha());
				obj.put("fecha", fecha);
				array.put(obj);
				String resultado = "[";
				for (int i = 0; i < array.length() - 1; i++) {
					resultado += array.getJSONObject(i).toString() + ",";
				}
				resultado += array.getJSONObject(array.length() - 1).toString() + "]";

				try (FileWriter file = new FileWriter("menus.json")) {
					file.write(resultado);
				} catch (IOException ex) {
					ex.printStackTrace();
				}
				

			} else {
				throw new ExcepcionFechaMenu("Ya existe un menú para la fecha indicada.");
			}
		} else {
			throw new IllegalArgumentException("No se ha pasado ningún menú como parámetro");
		}

	}

	@Override
	public void realizarModificionesMenu(Menu menu)
			throws ExcepcionFechaMenu, IOException, JSONException, IllegalArgumentException {
		if (menu != null) {
			if (obtenerMenuPorFecha(menu.getFecha()) == null) {
				String texto = "";
				int indice = -1;
				File f = new File("menus.json");

				texto = new String(Files.readAllBytes(f.toPath()));

				JSONArray array = new JSONArray(texto);
				for (int i = 0; i < array.length(); i++) {
					if (array.getJSONObject(i).getInt("id") == menu.getId()) {
						indice = i;
						break;
					}
				}

				JSONObject obj = new JSONObject();
				obj.put("id", menu.getId());
				ArrayList<String> bebidas = new ArrayList<>();
				bebidas.add("agua");
				bebidas.add("cocacola");
				bebidas.add("fantaLimon");
				bebidas.add("fantaNaranja");
				obj.put("bebidas", new JSONArray(bebidas));

				ArrayList<String> nombrePrimeros = new ArrayList<>();
				for (Plato plato : menu.getPrimeros()) {
					nombrePrimeros.add(plato.getNombre());
				}
				obj.putOnce("primeros", new JSONArray(nombrePrimeros));

				ArrayList<String> nombreSegundos = new ArrayList<>();
				for (Plato plato : menu.getSegundos()) {
					nombreSegundos.add(plato.getNombre());
				}
				obj.putOnce("segundos", new JSONArray(nombreSegundos));

				ArrayList<String> nombrePostres = new ArrayList<>();
				for (Plato plato : menu.getPostres()) {
					nombrePostres.add(plato.getNombre());
				}
				obj.putOnce("postres", new JSONArray(nombrePostres));

				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				String fecha = dateFormat.format(menu.getFecha());
				obj.put("fecha", fecha);

				array.put(indice, obj);

				String resultado = "[";
				for (int j = 0; j < array.length() - 1; j++) {
					resultado += array.getJSONObject(j).toString() + ",";
				}
				resultado += array.getJSONObject(array.length() - 1).toString() + "]";

				try (FileWriter file = new FileWriter("menus.json")) {
					file.write(resultado);
				} catch (IOException ex) {
					ex.printStackTrace();
				}

			} else {
				throw new ExcepcionFechaMenu("Ya existe un menú para la fecha indicada.");
			}
		} else {
			throw new IllegalArgumentException("No se ha pasado ningún menú como parámetro");
		}

	}

	@Override
	public Menu obtenerMenuPorFecha(Date fecha) throws IllegalArgumentException, IOException, JSONException {
		if (fecha != null) {
			String texto = "";
			int indice = -1;
			int i;
			Menu menu = null;
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			String fechaFormateada = dateFormat.format(fecha);
			File f = new File("menus.json");

			texto = new String(Files.readAllBytes(f.toPath()));
			JSONArray array = new JSONArray(texto);
			for (i = 0; i < array.length(); i++) {
				if (array.getJSONObject(i).getString("fecha").equals(fechaFormateada)) {
					indice = i;
					break;
				}
			}
			if (indice > -1) {
				JSONObject obj = array.getJSONObject(indice);
				int id = obj.getInt("id");
				ArrayList<String> bebidas = new ArrayList<>();
				bebidas.add("agua");
				bebidas.add("cocacola");
				bebidas.add("fantaLimon");
				bebidas.add("fantaNaranja");
				obj.put("bebidas", new JSONArray(bebidas));

				ArrayList<Plato> primeros = new ArrayList<>();
				ArrayList<Plato> segundos = new ArrayList<>();
				ArrayList<Plato> postres = new ArrayList<>();
				Plato plato = null;
				String nombre = "";
				JSONArray arrayPrimeros = (JSONArray) obj.get("primeros");
				JSONArray arraySegundos = (JSONArray) obj.get("segundos");
				JSONArray arrayPostres = (JSONArray) obj.get("postres");

				for (i = 0; i < arrayPrimeros.length(); i++) {
					nombre = arrayPrimeros.getString(i);
					plato = new Plato(nombre, "primero");
					primeros.add(plato);
				}
				for (i = 0; i < arraySegundos.length(); i++) {
					nombre = arraySegundos.getString(i);
					plato = new Plato(nombre, "segundo");
					segundos.add(plato);
				}
				for (i = 0; i < arrayPostres.length(); i++) {
					nombre = arrayPostres.getString(i);
					plato = new Plato(nombre, "postre");
					postres.add(plato);
				}

				menu = new Menu(id, fecha, primeros, segundos, postres, bebidas);
				
			} 
			return menu;

		} else {
			throw new IllegalArgumentException("No se ha pasado ninguna fecha como parámetro");
		}

	}

}
