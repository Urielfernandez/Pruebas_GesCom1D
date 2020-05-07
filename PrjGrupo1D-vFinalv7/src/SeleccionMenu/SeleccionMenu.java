package SeleccionMenu;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

import org.json.JSONException;

import GestionDatos.TiposDatos.Factura;
import GestionDatos.TiposDatos.Menu;
import GestionDatos.TiposDatos.Plato;
import GestionMenus.GestionMenus;
import GestionMenus.SeleccionMenuVerGestionMenu;
import GestionPagosYCalificaciones.GestionPagosYCalificaciones;
import GestionPagosYCalificaciones.SeleccionMenuVerGestionPagosYCalificaciones;

public class SeleccionMenu implements IUSeleccionMenu {
	private SeleccionMenuVerGestionMenu gestionMenu;
	private SeleccionMenuVerGestionPagosYCalificaciones gestionPagosYCalificaciones;

	public SeleccionMenu() {
		this.gestionMenu = new GestionMenus();
		this.gestionPagosYCalificaciones = new GestionPagosYCalificaciones();
	}

	@Override
	public void verPlatosMenuDia() throws IOException, JSONException {
		Menu menu = gestionMenu.obtenerMenuDelDia();
		if (menu != null) {
			System.out.print("PRIEMROS: ");
			for (Plato primero : menu.getPrimeros()) {
				System.out.print(primero.getNombre() + ", ");
			}
			System.out.print("\nSEGUNDOS: ");
			for (Plato segundo : menu.getSegundos()) {
				System.out.print(segundo.getNombre() + ", ");
			}
			System.out.print("\nPOSTRES: ");
			for (Plato postre : menu.getPostres()) {
				System.out.print(postre.getNombre() + ", ");
			}
			System.out.print("\nBEBIDAS: ");
			for (String bebida : menu.getBebidas()) {
				System.out.print(bebida + ", ");
			}
			System.out.println();
		} else {
			System.out.println("No hay ningún menú asignado para hoy");
		}
	}

	@Override
	public Factura seleccionarPlatos(Plato  primero, Plato segundo, Plato postre, String bebida) throws IllegalArgumentException, IOException, JSONException, ParseException, InterruptedException {
		Menu menu = gestionMenu.obtenerMenuDelDia();
		Factura factura = gestionPagosYCalificaciones.simularComida(menu.getId(), primero.getNombre(), segundo.getNombre(), postre.getNombre(), bebida);
		return factura;
	}
}
