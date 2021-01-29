/**
 * Excepci�n devuelta si se intenta eliminar o modificar un producto que no est� en la cesta
 */
package exceptions;

/**
 * @author Isabel Rom�n
 *
 */
public class NotInBag extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	public NotInBag(String productId){
		this.id=productId;
	}
	
	@Override
	public String getMessage() {
		return "El producto con id "+id+" no existe en la cesta";
	}

}
