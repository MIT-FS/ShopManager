/**
 * 
 */
package shopManager;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;

import shopmanager.MyProduct;
import shopmanager.Product;

import java.util.logging.*;


/**
 * @author Isabel Román
 *
 */
@Tag("unidad")
public class ProductTest {
	private static Logger trazador=Logger.getLogger(ProductTest.class.getName());
	static Product producto;


	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	public static void setUp() throws Exception {
		producto=new MyProduct("primerId");
	    assertEquals(1,producto.getNumber(),"Al crear producto sin número de unidades debe tener 1");
	    assertEquals("primerId",producto.getId(),"El id del producto es el pasado en el constructor");
		
	}

	
	@Test
	public void TestSetId() {
		trazador.info("Test del setId");
		producto.setId("nuevoId");
		assertEquals("nuevoId",producto.getId(),"El setId no funciona ");
		
	}
	
	@Test
	public void TestSetNumber() {
		trazador.info("Test del setNumber");
		producto.setNumber(33);
		assertEquals(33,producto.getNumber(),"El setNumber no funciona ");
	}
	
	@Test
	public void TestOneMore() {
		trazador.info("Test del oneMore");
		producto.setNumber(33);
		
		assertEquals(34,producto.oneMore(),"El oneMore no funciona ");
	}
	@Test
	public void TestOneLess() {
		trazador.info("Test del OneLess");
		producto.setNumber(1);
		
		assertEquals(0,producto.oneLess(),"El oneLess no funciona cuando es distinto de 0 ");
		producto.oneLess();
		assertEquals(0,producto.oneLess(),"El oneLess no funciona cuando es 0 ");
	}
	

}
