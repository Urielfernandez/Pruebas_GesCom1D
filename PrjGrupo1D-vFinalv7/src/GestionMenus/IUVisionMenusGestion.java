package GestionMenus;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;

import GestionDatos.TiposDatos.Menu;

public interface IUVisionMenusGestion {
	public ArrayList<Menu> verMenusSemana() throws IOException, JSONException, IllegalArgumentException;
}
