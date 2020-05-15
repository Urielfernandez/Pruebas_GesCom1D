/**
 * 
 */
package GestionPagosYCalificaciones;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.text.ParseException;

import org.json.JSONException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import GestionDatos.TiposDatos.Factura;

/**
 * @author Uriel Fernandez Granha
 *
 */
class TestGestionPagosYCualificaciones {
	private static GestionPagosYCalificaciones pc;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		//Genermos la estancia de la clase, para que todos los métodos de prueba puedan hacer uso de ella
		pc = new GestionPagosYCalificaciones();
	}
	
	///// Caso de prueba válido
	@Test
	@DisplayName("P-01-C01")
	void P01_C01() {
		//Arrange
		int idMenu = 0;
		String primero = "macarrones con salsa de tomate ";
		String segundo = "merluza a la plancha";
		String postre = "pera ";
		String bebida = "agua ";
		Factura factura = null;
		//Act
		try {
			factura = pc.simularComida(idMenu, primero, segundo, postre, bebida);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Assert
		assertEquals(factura.getIdBandeja(), idMenu, "Los ids de la factura e introducidos no coinciden");
		assertEquals(factura.getPrimero(), primero, "El primero de la factura e introducidos no coinciden");
		assertEquals(factura.getSegundo(), segundo, "El segundo de la factura e introducidos no coinciden");
		assertEquals(factura.getPostre(), postre, "El postre de la factura e introducidos no coinciden");
		assertEquals(factura.getBebida(), bebida, "La bebida de la factura e introducidos no coinciden");
	}
	
	//El id del menú no es válido
	@Test
	@DisplayName("P-01-C02")
	void P01_C02() {
		//Arrange
		int idMenu = 50;
		String primero = "macarrones con salsa de tomate";
		String segundo = "merluza a la plancha";
		String postre = "pera";
		String bebida = "agua";
		Factura factura = null;
		//Act
		
		//Assert
		assertThrows(IllegalArgumentException.class ,()->{pc.simularComida(idMenu, primero, segundo, postre, bebida);}, "El idMenú no se ha podido utilizar");
		assertEquals(factura.getIdMenu(), idMenu, "Los campos del idMenu no se corresponden");
	}
	
	//El primer plato no es válido
	@Test
	@DisplayName("P-01-C03")
	void P01_C03() {
		//Arrange
		int idMenu = 0;
		String primero = "Jamón asado";
		String segundo = "merluza a la plancha";
		String postre = "pera ";
		String bebida = "agua ";
		Factura factura = null;
		//Act
		
		//Assert
		assertThrows(IllegalArgumentException.class ,()->{pc.simularComida(idMenu, primero, segundo, postre, bebida);}, "El 'primero' no se ha podido utilizar");
		assertEquals(factura.getPrimero(), primero, "Los campos del primer plato no se corresponden");
	}
	
	//El segundo plato no es válido
	@Test
	@DisplayName("P-01-C04")
	void P01_C04() {
		//Arrange
		int idMenu = 0;
		String primero = "macarrones con salsa de tomate";
		String segundo = "kanelettus ";
		String postre = "pera";
		String bebida = "agua";
		Factura factura = null;
		//Act
		
		//Assert
		assertThrows(IllegalArgumentException.class ,()->{pc.simularComida(idMenu, primero, segundo, postre, bebida);}, "El 'segundo' no se ha podido utilizar");
		assertEquals(factura.getSegundo(), segundo, "Los campos del segundo plato no se corresponden");
	}
	
	//El postre no es válido
	@Test
	@DisplayName("P-01-C05")
	void P01_C05() {
		//Arrange
		int idMenu = 0;
		String primero = "macarrones con salsa de tomate";
		String segundo = "merluza a la plancha";
		String postre = "Roscas de atún";
		String bebida = "agua";
		Factura factura = null;
		//Act
		
		//Assert
		assertThrows(IllegalArgumentException.class ,()->{pc.simularComida(idMenu, primero, segundo, postre, bebida);}, "El 'postre' no se ha podido utilizar");
		assertEquals(factura.getPostre(), postre, "Los campos del postre no se corresponden");
	}
	
	//La bebida no es válida
	@Test
	@DisplayName("P-01-C06")
	void P01_C06() {
		//Arrange
		int idMenu = 0;
		String primero = "macarrones con salsa de tomate";
		String segundo = "merluza a la plancha";
		String postre = "pera ";
		String bebida = "Agua de grifo ";
		Factura factura = null;
		//Act
		
		//Assert
		assertThrows(IllegalArgumentException.class ,()->{pc.simularComida(idMenu, primero, segundo, postre, bebida);}, "La 'bebida' no se ha podido utilizar");
		assertEquals(factura.getBebida(), bebida, "Los campos del postre no se corresponden");
	}

}
