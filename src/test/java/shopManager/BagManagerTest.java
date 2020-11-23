/**
 * 
 */
package shopManager;

import shopmanager.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.Mockito;


/**
 * @author isa
 *
 */
@ExtendWith(MockitoExtension.class)
class BagManagerTest {
	
	@Mock(serializable = true)
	private static Product producto1Mock= Mockito.mock(Product.class);
	@Mock(serializable = true)
	private static Product producto2Mock= Mockito.mock(Product.class);
	
	private static BagManager micestaTesteada;


	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUpBeforeClass() throws Exception {
		Mockito.when(producto1Mock.getId()).thenReturn("id1");
		Mockito.when(producto1Mock.getNumber()).thenReturn(1);
		Mockito.when(producto2Mock.getId()).thenReturn("id2");
		Mockito.when(producto2Mock.getNumber()).thenReturn(2);
		micestaTesteada=new MyBagManager();
	}

	
	/**
	 * Test method for {@link shopmanager.MyBagManager#addProduct(shopmanager.Product)}.
	 */
	@Test
	void testAddProduct() {
		System.out.println("el producto es "+producto1Mock.getId());
		micestaTesteada.addProduct(producto1Mock);
		assertEquals(1,micestaTesteada.findProduct("id1").getNumber(),"El producto insertado debía tener una unidad");
		micestaTesteada.addProduct(producto2Mock);
		assertEquals(2,micestaTesteada.findProduct("id2").getNumber(),"El producto insertado debía tener dos unidades");
		/**Cuidado con los mock, no son el objeto de verdad**/ 
		/**Analizar por qué estos dos test que vienen a continuación fallan mientras que los de arriba no*/
		micestaTesteada.addProduct(producto1Mock);
		assertEquals(2,micestaTesteada.findProduct("id1").getNumber(),"El incremento de un producto en una unidad no se hace bien");
		micestaTesteada.addProduct(producto2Mock);	
		assertEquals(4,micestaTesteada.findProduct("id2").getNumber(),"El incremento de un producto en dos unidades no se hace bien");
	}

	/**
	 * Test method for {@link shopmanager.MyBagManager#lessProduct(shopmanager.Product)}.
	 */
	@Test
	void testLessProduct() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link shopmanager.MyBagManager#removeProduct(shopmanager.Product)}.
	 */
	@Test
	void testRemoveProductProduct() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link shopmanager.MyBagManager#removeProduct(java.lang.String)}.
	 */
	@Test
	void testRemoveProductString() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link shopmanager.MyBagManager#getBag()}.
	 */
	@Test
	void testGetBag() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link shopmanager.MyBagManager#findProduct(java.lang.String)}.
	 */
	@Test
	void testFindProductString() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link shopmanager.MyBagManager#findProduct(shopmanager.Product)}.
	 */
	@Test
	void testFindProductProduct() {
		fail("Not yet implemented");
	}

}
