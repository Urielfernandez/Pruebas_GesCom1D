/**
 * 
 */
package ValorarPlatos;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import GestionDatos.TiposDatos.Factura;
import GestionPagosYCalificaciones.GestionPagosYCalificaciones;

/**
 * @author Damián Cruz García
 *
 */
class TestValorarPlatos {
	private static GestionPagosYCalificaciones gestionCalificaciones;

	/**
	 * @throws java.lang.Exception
	 */

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		gestionCalificaciones = new GestionPagosYCalificaciones();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	/////
	@Test
	@DisplayName("P-02-01")
	void testP02_C1() {
		// Arrange
		boolean fallo=false;
		String primero = "sopa de estrellas";
		String segundo = "tortilla";
		String postre = "flan de huevo";
		String bebida = "agua";
		Date hoy = new Date();
		Factura factura = new Factura(1, 1, 1, primero, segundo, postre, bebida, hoy, hoy);
		int notaPrimero = 5;
		int notaSegundo = 2;
		int notaPostre = 8;
		// Act
		try {
			gestionCalificaciones.valorarPlatos(factura, notaPrimero, notaSegundo, notaPostre);
		}catch(Exception e) {
			fallo=true;
		}
		// Assert
		assertFalse(fallo,"Se ha producido una excepcion no esperada");
	}

	@Test
	@DisplayName("P-02-02")
	void testP02_C2() {
		// Arrange
		String primero = "sopa de estrellas";
		String segundo = "tortilla";
		String postre = "flan de huevo";
		String bebida = "agua";
		Factura factura = null;// <-en este caso de prueba "factura" será nulo
		int notaPrimero = 5;
		int notaSegundo = 4;
		int notaPostre = 9;
		// Assert
		Exception excepcion = assertThrows(Exception.class, () -> {
			gestionCalificaciones.valorarPlatos(factura, notaPrimero, notaSegundo, notaPostre);
		}, "Se esperaba una excepción al valorar los platos y no se ha producido");
	}

	@Test
	@DisplayName("P-02-03")
	void testP02_C3() {
		// Arrange
		String primero = "sopa de estrellas";
		String segundo = "tortilla";
		String postre = "flan de huevo";
		String bebida = "agua";
		Date hoy = new Date();
		Factura factura = new Factura(1, 1, 1, primero, segundo, postre, bebida, hoy, hoy);
		int notaPrimero = -1;// valor1 fuera del rango por la izquierda
		int notaSegundo = 8;
		int notaPostre = 8;
		// Assert
		IllegalArgumentException excepcion = assertThrows(IllegalArgumentException.class, () -> {
			gestionCalificaciones.valorarPlatos(factura, notaPrimero, notaSegundo, notaPostre);
		}, "Se esperaba una excepción al valorar los platos y no se ha producido");
	}

	@Test
	@DisplayName("P-02-04")
	void testP02_C4() {
		// Arrange
		String primero = "sopa de estrellas";
		String segundo = "tortilla";
		String postre = "flan de huevo";
		String bebida = "agua";
		Date hoy = new Date();
		Factura factura = new Factura(1, 1, 1, primero, segundo, postre, bebida, hoy, hoy);
		int notaPrimero = 11;// valor1 fuera del rango por la derecha
		int notaSegundo = 4;
		int notaPostre = 9;

		// Assert
		IllegalArgumentException excepcion = assertThrows(IllegalArgumentException.class, () -> {
			gestionCalificaciones.valorarPlatos(factura, notaPrimero, notaSegundo, notaPostre);
		}, "Se esperaba una excepción al valorar los platos y no se ha producido");
	}

	@Test
	@DisplayName("P-02-05")
	void testP02_C5() {
		// Arrange
		String primero = "sopa de estrellas";
		String segundo = "tortilla";
		String postre = "flan de huevo";
		String bebida = "agua";
		Date hoy = new Date();
		Factura factura = new Factura(1, 1, 1, primero, segundo, postre, bebida, hoy, hoy);
		int notaPrimero = 5;
		int notaSegundo = -1;// valor2 fuera del rango por la izquierda
		int notaPostre = 9;

		// Assert
		IllegalArgumentException excepcion = assertThrows(IllegalArgumentException.class, () -> {
			gestionCalificaciones.valorarPlatos(factura, notaPrimero, notaSegundo, notaPostre);
		}, "Se esperaba una excepción al valorar los platos y no se ha producido");
	}

	@Test
	@DisplayName("P-02-06")
	void testP02_C6() {
		// Arrange
		String primero = "sopa de estrellas";
		String segundo = "tortilla";
		String postre = "flan de huevo";
		String bebida = "agua";
		Date hoy = new Date();
		Factura factura = new Factura(1, 1, 1, primero, segundo, postre, bebida, hoy, hoy);
		int notaPrimero = 5;
		int notaSegundo = 11;// valor2 fuera del rango por la derecha
		int notaPostre = 9;

		// Assert
		IllegalArgumentException excepcion = assertThrows(IllegalArgumentException.class, () -> {
			gestionCalificaciones.valorarPlatos(factura, notaPrimero, notaSegundo, notaPostre);
		}, "Se esperaba una excepción al valorar los platos y no se ha producido");
	}

	@Test
	@DisplayName("P-02-07")
	void testP02_C7() {
		// Arrange
		String primero = "sopa de estrellas";
		String segundo = "tortilla";
		String postre = "flan de huevo";
		String bebida = "agua";
		Date hoy = new Date();
		Factura factura = new Factura(1, 1, 1, primero, segundo, postre, bebida, hoy, hoy);
		int notaPrimero = 5;
		int notaSegundo = 4;
		int notaPostre = -1;// valor3 fuera del rango por la izquierda
		
		// Assert
		IllegalArgumentException excepcion = assertThrows(IllegalArgumentException.class, () -> {
			gestionCalificaciones.valorarPlatos(factura, notaPrimero, notaSegundo, notaPostre);
		}, "Se esperaba una excepción al valorar los platos y no se ha producido");
	}

	@Test
	@DisplayName("P-02-08")
	void testP02_C8() {
		// Arrange
		String primero = "sopa de estrellas";
		String segundo = "tortilla";
		String postre = "flan de huevo";
		String bebida = "agua";
		Date hoy = new Date();
		Factura factura = new Factura(1, 1, 1, primero, segundo, postre, bebida, hoy, hoy);
		int notaPrimero = 5;
		int notaSegundo = 4;
		int notaPostre = 11;// valor3 fuera del rango por la derecha

		// Assert
		IllegalArgumentException excepcion = assertThrows(IllegalArgumentException.class, () -> {
			gestionCalificaciones.valorarPlatos(factura, notaPrimero, notaSegundo, notaPostre);
		}, "Se esperaba una excepción al valorar los platos y no se ha producido");
	}

}
