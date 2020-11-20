package shopmanager;
/**
 * 
 * @author Isabel Rom�n
 * @version 0.0. 
 * Primera versi�n de la interfaz Product, los elementos que vende nuestra tienda
 *
 */

public interface Product {
	
	    public void setId(String id);
	    public String getId();
	    public void setNumber(int number);
	    public int getNumber();
	    /**
	     * Suma uno
	     * @return int new value
	     */
	    public int oneMore();
	    /**
	     * Resta 1
	     * @return int new value
	     */
	    public int oneLess();
	    }