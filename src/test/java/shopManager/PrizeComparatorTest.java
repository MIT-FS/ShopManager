/**
 * 
 */
package shopManager;

import static java.lang.System.*;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import shopManager.MyProduct;
import shopManager.PriceComparator;

class PrizeComparatorTest {
	@Before
	@Test
	@Tag("unidad")
	@DisplayName("Prueba del metodo que compara dos precios")
	void testCompare() {
		MyProduct producto1 = new MyProduct("Producto1");
		MyProduct producto2 = new MyProduct("Producto2");
		PriceComparator comparador = new PriceComparator();
		producto1.setPrice(3);
		producto2.setPrice(3);
		//Probamos el caso de precios de igual producto
		assertEquals("El comparador no detecta que los precios son iguales", 0, comparador.compare(producto1, producto2));
		//Probamos el caso de que el segundo precio sea mayor
		producto2.setPrice(4);
		assertTrue(comparador.compare(producto1, producto2)<0,"El comparador no detecta que el segundo precio es mayor");
		//Probamos el caso de que el primer precio sea mayor
		assertTrue(comparador.compare(producto2, producto1)>0,"El comparador no detecta que el primer precio es mayor");
	}

}
