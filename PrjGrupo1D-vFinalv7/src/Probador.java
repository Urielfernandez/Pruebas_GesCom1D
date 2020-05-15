
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import AnalisisYEstadisticas.AnalisisYEstadisticas;
import AnalisisYEstadisticas.IUVisionMenusEstadisticas;
import GestionDatos.Estadisticas.EstadisticasDAO;
import GestionDatos.Facturas.FacturasDAO;
import GestionDatos.Menus.MenusDAO;
import GestionDatos.Platos.*;
import GestionDatos.TiposDatos.Estadistica;
import GestionDatos.TiposDatos.Factura;
import GestionDatos.TiposDatos.Menu;
import GestionDatos.TiposDatos.Plato;
import GestionMenus.GestionMenus;
import GestionMenus.IUDefinicionMenus;
import GestionMenus.IUVisionMenusGestion;
import GestionPagosYCalificaciones.GestionPagosYCalificaciones;
import GestionPagosYCalificaciones.IUValoraciones;
import SeleccionMenu.IUSeleccionMenu;
import SeleccionMenu.SeleccionMenu;

public class Probador {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//EJEMPLOS DE USO GESTION DE MENÚS
		IUDefinicionMenus dmg=new GestionMenus();
		IUVisionMenusGestion vmg=(IUVisionMenusGestion)dmg;
		/*Scanner sc = new Scanner(System.in);
		System.out.println("Introduzca el año en el que está disponible el menú que desea modificar");
		String anho = sc.next();
		System.out.println("Introduzca el mes (número) en el que estara disponible el menú que desea modificar");
		String mes = sc.next();
		System.out.println("Introduzca el día (número) en el que estara disponible el menú que desea modificar");
		String dia = sc.next();

		String fechaString = dia + "/" + mes + "/" + anho;
		Date fecha = null;
		try {
			fecha = new SimpleDateFormat("dd/MM/yyyy").parse(fechaString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		ArrayList<Plato> primeros = new ArrayList<>();
		primeros.add(new Plato("ensalada con tomates","primero"));
		primeros.add(new Plato("lentejas","primero"));
		primeros.add(new Plato("sopa","primero"));
		ArrayList<Plato> segundos = new ArrayList<>();
		segundos.add(new Plato("salmón cocido","segundo"));
		segundos.add(new Plato("albóndigas","segundo"));
		segundos.add(new Plato("merluza a la plancha","segundo"));
		ArrayList<Plato> postres = new ArrayList<>();
		postres.add(new Plato("pera","postre"));
		postres.add(new Plato("manzana","postre"));
		postres.add(new Plato("naranja","postre"));*/
		/*try {
			dmg.crearMenu(primeros, segundos, postres, fecha);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}*/
		/*System.out.println("Introduzca el año en el que está disponible el menú que desea introducir");
		String anhonuevo = sc.next();
		System.out.println("Introduzca el mes (número) en el que estara disponible el menú que desea introducir");
		String mesnuevo = sc.next();
		System.out.println("Introduzca el día (número) en el que estara disponible el menú que desea introducir");
		String dianuevo = sc.next();

		String fechaStringnueva = dianuevo + "/" + mesnuevo + "/" + anhonuevo;
		Date fechanueva = null;
		try {
			fechanueva = new SimpleDateFormat("dd/MM/yyyy").parse(fechaStringnueva);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			dmg.modificarMenu(fecha, primeros, segundos, postres, fechanueva);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}*/
		//vmg.verMenusSemana();
		
		//EJEMPLOS DE USO SELECCIÓN MENÚ
		IUSeleccionMenu sm=new SeleccionMenu();
		//sm.verPlatosMenuDia();
		Plato primero=new Plato( "lentejas", "primero");
		Plato segundo=new Plato( "escalope (cerdo)", "segundo");
		Plato postre=new Plato( "naranja", "postre");
		String bebida= "agua";
		try {
		Factura factura=sm.seleccionarPlatos(primero,segundo,postre,bebida);
		System.out.println(factura.getPrimero());}
		catch(Exception e) {
			
		}
		
		
		
		//EJEMPLO DE USO GESTION DE PAGOS
		IUValoraciones v=new GestionPagosYCalificaciones();
		//v.valorarPlatos(factura);
		
		//EJEMPLOS DE USO ANÁLISIS Y ESTADÍSTICAS
		IUVisionMenusEstadisticas vme=new AnalisisYEstadisticas();
		//vme.verHorasMasConcurridas();
		//vme.verDuracionMediaComidas();
		//vme.verOcupacionActual();
		//vme.verPlatosMasComidos(new Date());
		//vme.verPuntuacionesPlatosDia();
		//vme.verRankginPlatos();
		
	}

}
