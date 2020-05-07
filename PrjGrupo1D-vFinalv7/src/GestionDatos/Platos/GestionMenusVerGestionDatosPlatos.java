package GestionDatos.Platos;

import GestionDatos.TiposDatos.Plato;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;

public interface GestionMenusVerGestionDatosPlatos {
	 public ArrayList<Plato> obtenerPlatosPorTipo(String tipo) throws IOException, IllegalArgumentException, JSONException;
}
