package shopManager;

import shopManager.MyProduct;
import shopManager.Product;

/**
 * @author Guillermo Roche
 *
 */

public class PriceComparator {
	int compare (MyProduct producto1, MyProduct producto2) {
		float precio1 = 0;
		float precio2 = 0;
		int resultado = 0;
		precio1 = producto1.getPrice();
		precio2 = producto2.getPrice();
		if(precio1 > precio2) {
			resultado = -1;
		}
		else if (precio2 > precio1) {
			resultado = 1;
		}
		else {
			resultado = 0;
		}
		return resultado;
	}
}
