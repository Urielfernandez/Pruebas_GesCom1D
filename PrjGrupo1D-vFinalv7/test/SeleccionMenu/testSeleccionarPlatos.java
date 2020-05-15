package SeleccionMenu;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;

import org.json.JSONException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import GestionDatos.TiposDatos.*;
import SeleccionMenu.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;


/**
 * @author André Campos Álvarez
 *
 */

class testSeleccionarPlatos {
	private static SeleccionMenu sm;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		sm=new SeleccionMenu();
	}


	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Test
	@DisplayName("P-03-C1") 
	void PO3_C1() {
		//Arrange
		Plato primero=new Plato( "lentejas", "primero");
		Plato segundo=new Plato( "escalope (cerdo)", "segundo");
		Plato postre=new Plato( "naranja", "postre");
		String bebida= "agua";
		Factura f;
		//Act
		try {
			 f=sm.seleccionarPlatos(primero, segundo, postre, bebida);
		}
		catch(Exception e) {
			 f=null;
		}
			
		//Assert
		assertEquals(f.getPrimero(),primero.getNombre());
		assertEquals(f.getSegundo(),segundo.getNombre());
		assertEquals(f.getPostre(),postre.getNombre());
		assertEquals(f.getBebida(),bebida);
		
	}
	
	
	

}
