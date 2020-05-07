package GestionMenus;

import java.io.IOException;

import org.json.JSONException;

import GestionDatos.TiposDatos.Menu;

public interface SeleccionMenuVerGestionMenu {
    public Menu obtenerMenuDelDia() throws IOException, JSONException, IllegalArgumentException;
}
