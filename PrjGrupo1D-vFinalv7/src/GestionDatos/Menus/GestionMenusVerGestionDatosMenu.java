package GestionDatos.Menus;

import GestionDatos.TiposDatos.Menu;

import java.io.IOException;
import java.util.Date;

import org.json.JSONException;

public interface GestionMenusVerGestionDatosMenu {
    public void insertarMenu(Menu menu) throws IOException, JSONException, ExcepcionFechaMenu, IllegalArgumentException;
    public void realizarModificionesMenu(Menu menu) throws ExcepcionFechaMenu, IOException, JSONException, IllegalArgumentException;
    public Menu obtenerMenuPorFecha(Date fecha) throws IllegalArgumentException, IOException, JSONException;
}
