/**
 * 
 */
package shopmanager;

/**
 * @author isa
 *
 */
import java.util.List;

/**
 * @author Isabel Román
 * Gestor de la cesta de la compra
 *
 */
public interface BagManager {
	/**
	 * 
	 * @param newProduct producto a añadir, en el número de unidades se indica cuántas unidades se añaden
	 * debe verificar si hay en stock, si no no se añaden y debería lanzar una excepción (to do)
	 * actualiza stock disminuyendo el número de unidades aÃñadidas y el número de unidades en la cesta
	 * @return true si se pudo aÃ±adir
	 */
	boolean addProduct(Product newProduct);
	/**
	 * 
	 * @param oldProduct producto a eliminar, se eliminan las unidades que se marquen, actualiza stock aumentando estas unidades liberadas
	 * @return newProduct, el producto, indicando el número de unidades que quedan en la cesta
	 */
	Product lessProduct(Product oldProduct);
	/**
	 * 
	 * @param oldProduct
	 * Elimina completamente el producto, actualiza stock sumando las unidades liberadas
	 */
	void removeProduct(Product oldProduct);
	/**
	 * 
	 * @param oldProduct
	 * Elimina completamente el producto, actualiza stock sumando las unidades liberadas
	 */
	void removeProduct(String productId);
	
	/**
	 * 
	 * @return devuelve la cesta como una lista de productos
	 */
	List<Product> getBag();
	/**
	 * 
	 * @param producto a buscar
	 * @return el producto, con el número de unidades del mismo, si es cero es que no estaba en la cesta
	 */
	Product findProduct(String productId);
	/**
	 * 
	 * @param producto a buscar
	 * @return el producto, con el número de unidades del mismo, si es cero es que no estaba en la cesta
	 */
	Product findProduct(Product product);

}
