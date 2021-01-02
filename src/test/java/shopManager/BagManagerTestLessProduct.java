package shopManager;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import shopmanager.*;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.mockitoSession;

import java.util.logging.Logger;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;

import exceptions.NoEnoughStock;
import exceptions.NotInStock;
import exceptions.UnknownRepo;
import model.Product;
import model.MyProduct;
import model.Order;
import persistency.OrderRepository;


import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

/**
 * @author Daniel Neira
 * Clase para realizar los test de Less Product a la clase MyBagManager, o a cualquier otra clase que implemente BagManager siempre que se sustituya la declaraci�n private static MyBagManager micestaTesteada;
 *
 */
@ExtendWith(MockitoExtension.class)
class BagManagerTestLessProduct {

private static Logger trazador=Logger.getLogger(ProductTest.class.getName());
	
	//Creo los objetos sustitutos (representantes o mocks)
	//Son objetos contenidos en MyBagManager de los que a�n no disponemos el c�digo
	@Mock(serializable = true)
	private static Product producto1Mock= Mockito.mock(Product.class);
	@Mock(serializable = true)
	private static Product producto2Mock= Mockito.mock(Product.class);
	@Mock
	private static StockManager stockMock= Mockito.mock(StockManager.class);
	@Mock 
	private static OrderRepository repositoryMock= Mockito.mock(OrderRepository.class);
	@Mock
	private static Order orderMock=Mockito.mock(Order.class);
	
	//Inyecci�n de dependencias
	//Los objetos contenidos en micestaTesteada son reemplazados autom�ticamente por los sustitutos (mocks)
	@InjectMocks
	private static MyBagManager micestaTesteada;

	/**
	 * @see BeforeEach {@link org.junit.jupiter.api.BeforeEach}
	 */
	
	@BeforeEach
	void setUpBeforeClass(){
		//Todos los tests empiezan con la bolsa vac�a
		
		   micestaTesteada.reset();
	}
	
	/**
	 * Test method for {@link shopmanager.BagManager#lessProduct(model.Product)}.
	 * @throws NotInStock lanza cualquier excepci�n de sus clientes, no las gestiona siempre internamente
	 */
	@Test
	@Tag("unidad")
	@DisplayName("Prueba del m�todo que elimina unidades de un producto")
	void testLessProduct() throws NotInStock {
		
		Product producto1 = new MyProduct("id1", 7);
		Product producto2 =new MyProduct("id1", 3);
	
		/* Intentamos eliminar unidades de un producto que no existe, deber�a lanzar la excepci�n NoInStock */
		try {
			if(micestaTesteada.lessProduct(producto1)==null) {
				trazador.info("Si el producto no existe no podemos eliminarlo. OK");
			}else {
				/* Debe saltar la excepci�n as� que no debe llegar aqu� */
				fail("Deberia devolver null ya que el producto no esta en la cesta");
			}
		}
		catch(NotInStock e) {
			/* Si es la excepcion que esperamos, la capturamos y proseguimos con el test */
			trazador.info("Si el producto no existe no podemos eliminarlo. OK");
			assertEquals("El producto con id id1 no existe en el Stock",e.getMessage(),"El mensaje de la excepci�n no es correcto");
		}
		
		/* Necesitamos a�adir un producto para comprobar que se eliminan unidades correctamente */
		try {
			/* No deber�a haber problemas, pero lo hacemos en un try-catch para controlar mejor las excepciones */
			micestaTesteada.addProduct(producto1);
			trazador.info(""+micestaTesteada.findProduct("id1").get().getNumber());
			/* Nos aseguramos de que el producto est� a�adido correctamente */
			assertFalse(micestaTesteada.findProduct("id1").isEmpty(), "No deber�a estar vac�o");
			assertEquals(7,micestaTesteada.findProduct("id1").get().getNumber(),"El producto insertado deb�a tener 7 unidades");

		}
		catch(NoEnoughStock e) {
			/* Si es la excepcion que esperamos, la capturamos y proseguimos con el test */
			trazador.info("No se ha podido a�adir el producto para despu�s eliminarlo. ERROR");
	    	fail("No se ha podido a�adir el producto para despu�s eliminarlo");

		}
				
		/* Una vez a�adido si podemos eliminar las unidades del producto */
		try {
			/*En primer lugar vamos a eliminar 3 unidades*/
			producto2.setNumber(3);
			if(micestaTesteada.lessProduct(producto2)!=null) {
				trazador.info("Eliminamos las unidades indicadas. OK");
			}else {
				/* No debe llegar aqu� */
				fail("No deberia devolver null ya que intentamos eliminar 3 unidades habiendo 7");
			}
		}
		catch(NotInStock e) {
			
			/* No debe saltar la excepci�n as� que no deber�a llegar aqu� */
	    	fail("Salta la excepci�n del stock que no deber�a. ERROR");
			
		}
				
	    //Aseguro que se ha eliminado las unidades deseadas de la cesta
	    assertEquals(4,micestaTesteada.findProduct("id1").get().getNumber(),"No se han borrado las unidades indicadas");
	    
	    /* Una vez eliminadas 3 unidades, quedan 4 e intentamos eliminar mas unidades de las que hay */
		try {
			producto1.setNumber(5);
			if(micestaTesteada.lessProduct(producto1)==null) {
				trazador.info("Devuelve null ya que se intenta borrar mas unidades de las que hay. OK");
			}else {
				/* No deber�a llegar aqu� */
				fail("No devuelve null al intentar borrar mas unidades de las que hay");
			}
	    	
		}
		catch(NotInStock e) {
			
			/* No debe saltar la excepci�n as� que no deber�a llegar aqu� */
	    	fail("Salta la excepci�n del stock que no deber�a. ERROR");
			
		}
		//Aseguro que no se han eliminado las unidades deseadas de la cesta ya que se ha intentado borrar mas de las que hay
	    assertEquals(4,micestaTesteada.findProduct("id1").get().getNumber(),"No se han borrado las unidades indicadas");
	    
	    /* Intentamos eliminar las ultimas 4 unidades */
		try {
			producto2.setNumber(4);;
			if(micestaTesteada.lessProduct(producto2)!=null) {
				trazador.info("Eliminamos las unidades indicadas. OK");
			}else {
				/* No debe llegar aqu� */
				fail("No deberia devolver null ya que intentamos eliminar 4 unidades habiendo 4");
			}
		}
		catch(NotInStock e) {
			
			/* No debe saltar la excepci�n as� que no deber�a llegar aqu� */
	    	fail("Salta la excepci�n del stock que no deber�a. ERROR");
			
		}
	    
	    //Aseguro que se ha eliminado las unidades deseadas de la cesta
	    assertEquals(0,micestaTesteada.findProduct("id1").get().getNumber(),"No se han borrado las unidades indicadas");
	}

}
