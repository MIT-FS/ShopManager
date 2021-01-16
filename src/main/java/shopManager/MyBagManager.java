/**
 * 
 */
package shopmanager;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import exceptions.NoEnoughStock;
import exceptions.NotInStock;
import exceptions.UnknownRepo;
import model.MyOrder;
import model.MyProduct;
import model.Order;
import model.Product;
import persistency.OrderRepository;
import shopmanager.StockManager;
/**
 * @author Isabel Rom�n
 * @author Ezequiel Montero
 *
 */
public class MyBagManager implements BagManager {
	private static Logger trazador=Logger.getLogger(MyStockManager.class.getName());
	private Map<String,Product> cesta;
	private OrderRepository repositorio;
	private StockManager stock;
	private Order order;
	private PriceComparator comparadorPrecio= new PriceComparator();
	
	/**
	 * Al hacer privado el constructor obligo a que la construcci�n del objeto se haga siempre a trav�s de newBag
	 */
	public MyBagManager(){
		cesta=new HashMap<String,Product>();
	}

	public MyBagManager(OrderRepository repo,StockManager stockManager){
		cesta=new HashMap<String,Product>();
		repositorio=repo;
		stock=stockManager;
	}



	@Override
	public Product addProduct(Product newProduct)throws NoEnoughStock, NotInStock{
		String msg="El id del producto es "+newProduct.getId()+" y las unidades a a�adir"+newProduct.getNumber();
		trazador.info(msg);
		//quito del stock las unidades solicitadas, si no hubiera suficientes lanza NoEnoughStock, si el producto no existe lanza NotInStock
		//stock.lessProduct(newProduct);
		if(cesta.containsKey(newProduct.getId())){
				int antes=cesta.get(newProduct.getId()).getNumber();
				newProduct.setNumber(newProduct.getNumber()+antes);
				cesta.put(newProduct.getId(), newProduct);
			}
		else
			cesta.put(newProduct.getId(), newProduct);
		return newProduct;
	}
	
	@Override
	public Product lessProduct(Product oldProduct)throws NotInStock {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeProduct(Product oldProduct)throws NotInStock {
		// TODO Auto-generated method stub
		return false;

	}

	@Override
	public void removeProduct(String productId)throws NotInStock {
		// TODO Auto-generated method stub

	}

	@Override
	public Collection<Product> getBag() {
			
		return (List<Product>) cesta.values();
	}

	@Override
	public Optional<Product> findProduct(String productId) {
		Product producto=null;
		if(cesta.containsKey(productId))
			producto=cesta.get(productId);
			
		return Optional.ofNullable(producto);
	}

	@Override
	public Optional<Product> findProduct(Product product) {
		return findProduct(product.getId());
	}
	
	@Override
	public Order order() {
		// No crea el objeto order, a�n no est� resuelto qui�n ser� el responsable de elegir el tipo concreto
		try{ 	       
		   trazador.info("Intento persistir el stock");
	       stock.save();	
	       trazador.info("Actualizo el pedido");
	       order.setProducts(cesta.values());	
	       trazador.info("Persisto el pedido");
	       repositorio.save(order);
	    
		} catch (UnknownRepo ex) {
			trazador.info("No ha sido posible guardar el pedido, no se estableci� el repositorio en el stock");
		}
	    	
		return order;
	}

	@Override
	public void reset() {
		// Deber�a restaurar el stock, pero por ahora no se hace, s�lo borra
		cesta.clear();
		
	}

	public Iterator<Product> getPriceIterator(){
		//Cesta es un mapa String, Product. Necesito obtener los productos en una lista
		List<Product> productos = new ArrayList(cesta.values());
		//List<Product> productos = cesta.values().stream().collect(Collectors.toList());
		//List<Product> productos = (List<Product>) this.getBag();
		Collections.sort(productos, new PriceComparator());
		Iterator iterador = productos.iterator();
		return iterador;
	}
}
