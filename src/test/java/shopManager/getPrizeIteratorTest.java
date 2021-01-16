package shopManager;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import exceptions.NoEnoughStock;
import exceptions.NotInStock;
import model.MyProduct;
import model.Product;
import shopmanager.MyBagManager;

class getPrizeIteratorTest{

	@Test
	@Tag("unidad")
	@DisplayName("Prueba del iterador que recorre la cesta de menor a menor precio")
	void testCompare() {
		MyProduct producto1 = new MyProduct("Producto1");
		MyProduct producto2 = new MyProduct("Producto2");
		
		producto1.setPrice(6);
		producto2.setPrice(15);
		
		MyBagManager micestaTesteada = new MyBagManager();
		
		try {
			micestaTesteada.addProduct(producto1);
		} catch (NoEnoughStock | NotInStock e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			micestaTesteada.addProduct(producto2);
		} catch (NoEnoughStock | NotInStock e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Deberia devolver el test primero producto2 y luego producto 1.
		Iterator it = micestaTesteada.getPriceIterator();
		
		assertEquals((float)15, ((MyProduct) it.next()).getPrice(),"Primero debería salir el producto 2 del iterador");
		assertEquals((float)6,((MyProduct) it.next()).getPrice(),"Segundo debería salir el producto 1 del iterador");
		
		
		MyProduct producto3 = new MyProduct("Producto3");
		MyProduct producto4 = new MyProduct("Producto4");
		MyProduct producto5 = new MyProduct("Producto5");
		MyProduct producto6 = new MyProduct("Producto6");
		
		producto3.setPrice(6);
		producto4.setPrice(25);
		producto5.setPrice(36);
		producto6.setPrice(5);
		
		MyBagManager micestaTesteada2 = new MyBagManager();
		
		try {
			micestaTesteada2.addProduct(producto3);
		} catch (NoEnoughStock | NotInStock e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			micestaTesteada2.addProduct(producto4);
		} catch (NoEnoughStock | NotInStock e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			micestaTesteada2.addProduct(producto5);
		} catch (NoEnoughStock | NotInStock e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			micestaTesteada2.addProduct(producto6);
		} catch (NoEnoughStock | NotInStock e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Deberia devolver el test primero producto2 y luego producto 1.
		Iterator it2 = micestaTesteada2.getPriceIterator();
		
		assertEquals((float)36,((MyProduct) it2.next()).getPrice(),"Primero debería salir el producto 5 del iterador");
		assertEquals((float)25,((MyProduct) it2.next()).getPrice(),"Segundo debería salir el producto 4 del iterador");
		assertEquals((float)6,((MyProduct) it2.next()).getPrice(),"Tercero debería salir el producto 3 del iterador");
		assertEquals((float)5,((MyProduct) it2.next()).getPrice(),"Cuarto debería salir el producto 6 del iterador");
		
		
	}

}