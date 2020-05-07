package GestionMenus;

import GestionDatos.Menus.ExcepcionFechaMenu;
import GestionDatos.TiposDatos.Plato;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import org.json.JSONException;

public interface IUDefinicionMenus {
    public void crearMenu(ArrayList<Plato> primeros, ArrayList<Plato> segundos, ArrayList<Plato> postres, Date fecha) throws IOException, JSONException, ExcepcionFechaMenu, IllegalArgumentException;
    public void modificarMenu(Date fecha, ArrayList<Plato> primeros, ArrayList<Plato> segundos, ArrayList<Plato> postres, Date fechaNueva) throws IOException, JSONException, ExcepcionFechaMenu, IllegalArgumentException;
}
