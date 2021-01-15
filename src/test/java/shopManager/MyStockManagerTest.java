/**
 * 
 */
package shopManager;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;

import shopmanager.MyStockManager;
import shopmanager.StockManager;

import exceptions.NotInStock;
import model.MyProduct;
import model.Product;
import exceptions.NoEnoughStock;


/**
 * 
 * Clase para los tests de MyStockManager
 * @author Isabel Rom�n
 * 
 *
 */

public class MyStockManagerTest {

	StockManager underTest;
	String underTestAsString;
	
	
		
	@BeforeEach
	public void setup() {
		MyStockManager.getInstance().clean();
	}

	/**
	 * Test para probar {@link shopmanager.MyStockManager#getInstance()}.
	 */
	@Test
	@Tag("unidad")
	@Tag("integracion")
	@DisplayName("Prueba del m�todo que devuelve la instancia �nica")
	public void testGetInstance() {
		underTest=MyStockManager.getInstance();
		underTestAsString=underTest.toString();
				
		assertNotNull(underTest,"getInstance no devuelve una referencia v�lida");
		assertEquals(underTest,MyStockManager.getInstance(),"getInstance debe devolver siempre la misma instancia");
	}

	/**
	 * Test para probar {@link shopmanager.MyStockManager#addProduct(model.Product)}.
	 */
	@Test
	@Tag("integracion")
	@DisplayName("Prueba para el m�todo que a�ade un producto")
	public void testAddProduct() {
		Product product=new MyProduct("nuevoProducto",1);
	
		MyStockManager.getInstance().addProduct(product);
	
		assertEquals(product.toString(),MyStockManager.getInstance().searchProduct("nuevoProducto").get().toString(),"El objeto obtenido debe ser igual al introducido");
		assertEquals("nuevoProducto", MyStockManager.getInstance().searchProduct("nuevoProducto").get().getId(),"El id del producto recuperado no es el buscado");
		assertEquals(1, MyStockManager.getInstance().searchProduct("nuevoProducto").get().getNumber(),"El n�mero de unidades del producto recuperado no es el esperado");	
		
		MyStockManager.getInstance().addProduct(product);
		assertEquals(2, MyStockManager.getInstance().searchProduct("nuevoProducto").get().getNumber(),"Si a�ado un producto que ya estaba se a�ade el n�mero de unidades");	
	}
	
	/**
	 * Test para probar {@link shopmanager.MyStockManager#searchProduct(java.lang.String)}.
	 */
	@Test
	@Tag("integracion")
	@DisplayName("Prueba para el m�todo de b�squeda de un producto")
	public void testSearchProduct() {
		assertFalse(MyStockManager.getInstance().searchProduct("noexiste").isPresent(),"No debe encontrar un producto que no existe");
		
		Product product=new MyProduct("nuevoProducto",1);
		MyStockManager.getInstance().addProduct(product);
		
		assertTrue(MyStockManager.getInstance().searchProduct("nuevoProducto").isPresent(),"Optional no debe estar vac�o");
		assertEquals(product.toString(),MyStockManager.getInstance().searchProduct("nuevoProducto").get().toString(),"El objeto obtenido debe ser igual al introducido");
	}
	
	/**
	 * Test para probar  {@link shopmanager.MyStockManager#lessProduct(Product)}
	 * @throws NoEnoughStock si se intenta eliminar unidades de un producto sin suficiente cantidad lanzar la excepci�n NoEnoughStock, el m�todo de test lo verifica, pero no gestiona la excepci�n
	 * @throws NotInStock si se intenta eliminar unidades de un producto que no existe en el stock se debe lanzar NotInStock, el m�todo de test lo verifica, pero no gestiona la excepci�n
	 */
	@Test
	@Tag("integracion")
	@DisplayName("Prueba para el m�todo que reduce el n�mero de unidades en stock")
	
	public void testLessProduct() throws NoEnoughStock, NotInStock {
		
		 //creamos un producto tipo "id1" con 5 unidades, todav�a no lo introduzco en el stock
		 Product product1=new MyProduct("id1",5);
	
		//Si intento reducir unidades de un producto que no existe debe lanzar la excepci�n NotInStock
		 assertThrows(NotInStock.class,()->{MyStockManager.getInstance().lessProduct(product1);},"Deber�a lanzar una excepci�n de tipo NotInStock si no existe este producto");
		 
		 //Ahora s�, lo a�ado al stock
		 
		 MyStockManager.getInstance().addProduct(product1);
	
		 //Si se intentan eliminar m�s unidades de las que hay debe lanzar la excepci�n NoEnoughStock
		 product1.setNumber(8);
		 assertThrows(NoEnoughStock.class,()->{MyStockManager.getInstance().lessProduct(product1);},"Deber�a lanzar una excepci�n de tipo NoEnougStock si no hay suficientes unidades de este producto");
		 product1.setNumber(1);
		 //Si a 5 que hab�a le quito 1 deber�an quedar 4
		 assertEquals(4,MyStockManager.getInstance().lessProduct(product1).getNumber(),"Deber�a restar el n�mero de unidades pasadas a las que hab�a y devolver el producto actualizado"); 		
	}
	
	/**
	 *  <P>Test para probar  {@link shopmanager.MyStockManager#clone()}
	 *  
	 *  Usa expresiones <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html">Expresiones lambda en Oracle</a>
	 *  </P>
	 *  @see java.lang.reflect.Executable
	 */
	@Test
	@Tag("unidad")
	@Tag("integracion")
	@DisplayName ("Aseguro que no se puede clonar el StockManager")
	public void testClone() {
		assertThrows(CloneNotSupportedException.class,()->{((MyStockManager)MyStockManager.getInstance()).clone();},"Debe lanzar la excepci�n clone no soportado al intentar clonar");
		
	}
}
