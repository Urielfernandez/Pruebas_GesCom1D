package GestionMenus;

import GestionDatos.Menus.ExcepcionFechaMenu;
import GestionDatos.Menus.GestionMenusVerGestionDatosMenu;
import GestionDatos.Platos.GestionMenusVerGestionDatosPlatos;
import GestionDatos.TiposDatos.Menu;
import GestionDatos.Menus.MenusDAO;
import GestionDatos.TiposDatos.Plato;
import GestionDatos.Platos.PlatosDAO;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import org.json.JSONException;

public class GestionMenus implements IUDefinicionMenus, IUVisionMenusGestion, SeleccionMenuVerGestionMenu {
	private GestionMenusVerGestionDatosMenu datosMenus;
	private GestionMenusVerGestionDatosPlatos datosPlatos;

	public GestionMenus() {
		this.datosMenus = new MenusDAO();
		this.datosPlatos = new PlatosDAO();
	}

	@Override
	public void crearMenu(ArrayList<Plato> primeros, ArrayList<Plato> segundos, ArrayList<Plato> postres, Date fecha) throws IOException, JSONException, ExcepcionFechaMenu, IllegalArgumentException {
		if(primeros!= null && segundos!=null && postres!=null && fecha!=null) {
			if(primeros.size()>0 && segundos.size()>0 && postres.size()>0) {
				Menu m = new Menu(fecha,primeros,segundos,postres);
				datosMenus.insertarMenu(m);
			}else {
				throw new IllegalArgumentException("Alguno de los conjuntos de platos no contiene valores");
			}
		}else {
			throw new IllegalArgumentException("Alguno de los valores introducidos es nulo");
		}
	}

	@Override
	public void modificarMenu(Date fecha, ArrayList<Plato> primeros, ArrayList<Plato> segundos, ArrayList<Plato> postres, Date fechaNueva) throws IOException, JSONException, ExcepcionFechaMenu, IllegalArgumentException {
		Menu m = datosMenus.obtenerMenuPorFecha(fecha);
		if(primeros!=null && primeros.size()>0) {
			m.setPrimeros(primeros);
		}else {
			throw new IllegalArgumentException("Primeros toma un valor nulo o no contiene platos");
		}
		if(segundos!=null && segundos.size()>0) {
			m.setSegundos(segundos);
		}
		else {
			throw new IllegalArgumentException("Segundos toma un valor nulo o no contiene platos");
		}
		if(postres!=null && postres.size()>0) {
			m.setPostres(postres);
		}
		else {
			throw new IllegalArgumentException("Postres toma un valor nulo o no contiene platos");
		}
		if(fechaNueva!=null) {
			m.setFecha(fechaNueva);
		}else {
			throw new IllegalArgumentException("La fecha toma un valor nulo");
		}
		datosMenus.realizarModificionesMenu(m);
	}

	private void imprimirPlatosPorTipo(String tipo, ArrayList<Plato> platos) {
		System.out.println("Platos de tipo " + tipo + ":");
		for (int i = 0; i < platos.size(); i++) {
			System.out.println((i + 1) + ") " + platos.get(i).getNombre());
		}
	}

	@Override
	public ArrayList<Menu> verMenusSemana() throws IOException, JSONException, IllegalArgumentException {
		ArrayList<Menu> menusSemana = new ArrayList<>();
		Date fechad1 = new Date();
		Calendar c=Calendar.getInstance();
		c.setTime(fechad1);
		
		c.add(Calendar.DATE, 1);
		Date fechad2 = c.getTime();
		c.add(Calendar.DATE, 1);
		Date fechad3 = c.getTime();
		c.add(Calendar.DATE, 1);
		Date fechad4 = c.getTime();
		c.add(Calendar.DATE, 1);
		Date fechad5 = c.getTime();

		Menu d1 = datosMenus.obtenerMenuPorFecha(fechad1);
		if (d1 != null) {
			menusSemana.add(d1);
		}

		Menu d2 = datosMenus.obtenerMenuPorFecha(fechad2);
		if (d2 != null) {
			menusSemana.add(d2);
		}

		Menu d3 = datosMenus.obtenerMenuPorFecha(fechad3);
		if (d3 != null) {
			menusSemana.add(d3);
		}

		Menu d4 = datosMenus.obtenerMenuPorFecha(fechad4);
		if (d4 != null) {
			menusSemana.add(d4);
		}

		Menu d5 = datosMenus.obtenerMenuPorFecha(fechad5);
		if (d5 != null) {
			menusSemana.add(d5);
		}
		return menusSemana;
	}

	@Override
	public Menu obtenerMenuDelDia() throws IOException, JSONException, IllegalArgumentException {
		Menu m = null;
		m = datosMenus.obtenerMenuPorFecha(new Date());
		return m;
	}

}
