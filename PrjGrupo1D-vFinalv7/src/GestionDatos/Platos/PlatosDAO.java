package GestionDatos.Platos;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

import GestionDatos.TiposDatos.Plato;

public class PlatosDAO implements GestionMenusVerGestionDatosPlatos {

	public PlatosDAO() {

	}

	@Override
	public ArrayList<Plato> obtenerPlatosPorTipo(String tipo)
			throws IOException, IllegalArgumentException, JSONException {
		if (tipo != null) {
			if (tipo.equals("primero") || tipo.contentEquals("segundo") || tipo.equals("postre")) {
				ArrayList<Plato> platos = new ArrayList<Plato>();
				String nombrePlato = "";
				String tipoPlato = "";
				String texto = "";
				File f = new File("platos.json");

				texto = new String(Files.readAllBytes(f.toPath()));

				JSONArray array = new JSONArray(texto);
				for (int i = 0; i < array.length(); i++) {
					nombrePlato = array.getJSONObject(i).getString("nombre");
					tipoPlato = array.getJSONObject(i).getString("tipo");
					if (tipoPlato.equals(tipo)) {
						Plato plato = new Plato(nombrePlato, tipoPlato);
						platos.add(plato);
					}
				}

				return platos;
			} else {
				throw new IllegalArgumentException("El tipo que se ha pasado no es ni primero, ni segundo, ni postre.");
			}
		} else {
			throw new IllegalArgumentException("No se ha pasado ningún tipo como parámetro");
		}
	}
}
