package shopManager;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import model.MyProduct;
import model.Product;
import shopmanager.GetIteratorByPrice;
import shopmanager.MyBagManager;

class PrizeComparatorTest {

	@Test
	@Tag("unidad")
	@DisplayName("Prueba del iterador que recorre la cesta de menor a menor precio")
	void testCompare() {
		MyProduct producto1 = new MyProduct("Producto1");
		MyProduct producto2 = new MyProduct("Producto2");
		
		producto1.setPrize(6);
		producto2.setPrize(15);
		
		MyBagManager micestaTesteada;
		
		micestaTesteada.addProduct(producto1);
		micestaTesteada.addProduct(producto2);
		
		//Deberia devolver el test primero producto2 y luego producto 1.
		PrizeIterator = new PrizeIterator(micestaTesteada);
		
		assert(assertEquals(15,PrizeIterator.getNext().getPrize(),"Primero debería salir el producto 2 del iterador"));
		assert(assertEquals(6,PrizeIterator.getNext().getPrize(),"Segundo debería salir el producto 1 del iterador"));
		
		MyProduct producto3 = new MyProduct("Producto3");
		MyProduct producto4 = new MyProduct("Producto4");
		MyProduct producto5 = new MyProduct("Producto5");
		MyProduct producto6 = new MyProduct("Producto6");
		
		producto3.setPrize(6);
		producto4.setPrize(25);
		producto5.setPrize(36);
		producto6.setPrize(5);
		
		MyBagManager micestaTesteada2;
		
		micestaTesteada2.addProduct(producto3);
		micestaTesteada2.addProduct(producto4);
		micestaTesteada2.addProduct(producto5);
		micestaTesteada2.addProduct(producto6);
		
		//Deberia devolver el test primero producto2 y luego producto 1.
		PrizeIterator2 = new PrizeIterator(micestaTesteada2);
		
		assert(assertEquals(36,PrizeIterator2.getNext().getPrize(),"Primero debería salir el producto 5 del iterador"));
		assert(assertEquals(25,PrizeIterator2.getNext().getPrize(),"Segundo debería salir el producto 4 del iterador"));
		assert(assertEquals(6,PrizeIterator2.getNext().getPrize(),"Tercero debería salir el producto 3 del iterador"));
		assert(assertEquals(5,PrizeIterator2.getNext().getPrize(),"Cuarto debería salir el producto 6 del iterador"));
		
		
	}

}