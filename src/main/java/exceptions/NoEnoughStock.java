/**
 * excepci�n devuelta cuando se intenta sacar del stock una cantidad que no existe
 */
package exceptions;

/**
 * @author Isabel Rom�n
 */
public class NoEnoughStock extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int unidades;
	/**
	 * 
	 * @param unidades el n�mero de unidades que quedan en el stock
	 */
	public NoEnoughStock(int unidades){
		this.unidades=unidades;
	}
	@Override
	public String getMessage(){
		return "No hay suficientes unidades en el Stock, s�lo quedan "+unidades;
	}
}
