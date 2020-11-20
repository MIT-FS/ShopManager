package shopmanager;

/**
 * @author Isabel Rom�n
 * @version 0.0.
 * Primera versi�n de la interfaz StockManager, la forma de gestionar el stock compartido por distintas instancias hazelcast
 *
 */
public interface StockManager {
/**
 * 
 * @param newProduct
 * El producto a insertar en el stock, el campo de unidades dentro del producto indica cu�ntos se insertan.
 * Si ya estaba en el mapa se suma el n�mero de unidades.
 * Si no estaba en el mapa se inserta el producto nuevo con el n�mero de unidades indicado.
 */
	void addProduct(Product newProduct);
	/**
	 * 
	 * @param id
	 * Identificador del producto a buscar
	 * @return
	 * El producto buscado, que incluye el n�mero de unidades existentes, null is no est� en el mapa
	 */
	Product searchProduct(String id);
	
}

