/**
 * excepci�n devuelta cuando se intenta sacar del stock una cantidad que no existe
 */
package exceptions;

/**
 * @author Isabel Rom�n
 */
public class NotEmpty extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Override
	public String getMessage(){
		return "El mapa no est� vac�o";
	}
}
