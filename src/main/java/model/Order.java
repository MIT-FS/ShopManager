package model;

import java.util.Iterator;

import java.util.Collection;

public interface Order {
	

	/**
	 * Pone al pedido un identificador
	 * 
	 * @param id del pedido
	 */
	void setId(String id);
	/**
	 * Recupera el identificador del pedido
	 * @return identificador
	 */
	String getId();
	/**
	 * Establece los productos incluidos en el pedido, que se pasan como un iterador de productos
	 * @param products productos del pedido
	 */
	void setProducts(Iterator<Product> products);
	/**
	 * Establece los productos incluidos en el pedido, que se pasan como una colecci�n de productos
	 * @param products la colecci�n de productos que incluye el pedido
	 */
	void setProducts(Collection<Product> products);
	/**
	 * Obtiene un iterador que recorre los productos en orden alfab�tico seg�n su id
	 * @return iterador por identificador
	 */
	Iterator<Product> getProductsById();
	/**
	 * Obtiene un iterador que recorre los productos en orden de m�s caro a m�s barato
	 * @return iterador por precio (de mayor a menor)
	 */
	Iterator<Product> getProductsByPrice();
	/**
	 * Obtiene un iterador que recorre los productos ordenados por el n�mero de unidades, desde el producto con m�s unidades al que tiene menos
	 * @return iterador por unidades (de mayor a menor)
	 */
	Iterator<Product> getProductsByUnits();
	

}
