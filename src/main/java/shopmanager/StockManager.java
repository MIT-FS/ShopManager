package shopmanager;

import java.util.Iterator;
import java.util.Optional;
import exceptions.NoEnoughStock;
import exceptions.NotEmpty;
import exceptions.NotInStock;
import exceptions.UnknownRepo;
import model.Product;
import persistency.ProductRepository;

/**
 * @author Isabel Rom�n
 * @version 0.0.
 * Primera versi�n de la interfaz StockManager, la forma de gestionar el stock compartido por distintas instancias hazelcast
 *
 */
public interface StockManager {
	/**
	 * Establece el repositorio e inicializa el stock con el contenido del mismo, siempre que estuviera vac�o
	 * @param repo repositorio para la persistencia de productos
	 * @throws NotEmpty si el stock no estaba vac�o
	 */
	public void init(ProductRepository repo) throws NotEmpty;
	
	/**
	 * Inicializa el stock con el contenido del repositorio, si no est� establecido lanza una excepci�n
	 *
	 * @throws UnknownRepo si no est� establecido el repositorio
	 * @throws NotEmpty si no est� vac�o el stock (como medida des eguridad para no borrar lo que ya hay)
	 */
	public void init() throws NotEmpty,UnknownRepo;
	/**
	 * Establece el repostorio para la persistencia
	 * @param repo repositorio de productos
	 */
	public void setRepository(ProductRepository repo);

	/**
	 * Persiste el stock (guarda lo que hay en memoria en el repositorio
	 * @throws UnknownRepo si no se estableci� el repositorio
	 */
	public void save() throws UnknownRepo;
	/**
	 * 
	 * @param newProduct
	 * El producto a insertar en el stock, el campo de unidades dentro del producto indica cu�ntos se insertan.
	 * Si ya estaba en el mapa se suma el n�mero de unidades.
	 * Si no estaba en el mapa se inserta el producto nuevo con el n�mero de unidades indicado.
	 */
	void addProduct(Product newProduct);
	/**
	 * Disminuye el n�mero de unidades que se indique en el producto que se pasa
	 * @param product producto del que se quieren disminuir las unidades
	 * @return devuelve el producto tal y como ha quedado en el stock (el n�mero de unidades actualizado)
	 * @throws NoEnoughStock en caso de que no hubiera unidades suficientes, deja el producto como estaba
	 * @throws NotInStock si el producto no estaba en el stock
	 */
	
	Product lessProduct(Product product) throws NoEnoughStock, NotInStock;
	/**
	 * 
	 * Devuelve el producto 
	 * @param id Identificador del producto a buscar
	 * @return El producto buscado (con el n�mero de unidades existentes), encapsulado en un objeto Optional
	 * @see java.util.Optional
	 */
	Optional<Product> searchProduct(String id);
	/**
	 * Vacia el stock
	 */
	void clean();
	/**
	 * Devuelve una instancia de StockManager
	 * 
	 * @return objeto �nico StockManager
	 */
	StockManager getManager();
	
}

