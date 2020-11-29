/**
 * excepci�n devuelta cuando se intenta sacar del stock una cantidad que no existe
 */
package exceptions;

/**
 * @author Isabel Rom�n
 *
 */
public class UnknownRepo extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Override
	public String getMessage(){
		return "Debe establecer previamente el repositorio";
	}
}
