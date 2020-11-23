/**
 * 
 */
package shopmanager;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import java.util.logging.Logger;

import shopmanager.Product;
/**
 * @author isa
 *
 */
public class MyBagManager implements BagManager {
	private static Logger trazador=Logger.getLogger(MyStockManager.class.getName());
	private Map<String,Product> cesta;
	
	public MyBagManager(){
		cesta=new HashMap<String,Product>();
	}

	@Override
	public boolean addProduct(Product newProduct) {
		boolean existe=false;
		String msg="El id del producto es "+newProduct.getId()+" y las unidades "+newProduct.getNumber();
		trazador.fine(msg);
		if(cesta.containsKey(newProduct.getId())){
			existe=true;
			int antes=cesta.get(newProduct.getId()).getNumber();
			newProduct.setNumber(newProduct.getNumber()+antes);
			cesta.put(newProduct.getId(), newProduct);
		}
		else
			cesta.put(newProduct.getId(), newProduct);
		return existe;
	}

	@Override
	public Product lessProduct(Product oldProduct) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeProduct(Product oldProduct) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeProduct(String productId) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Product> getBag() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product findProduct(String productId) {
		Product producto=null;
		if(cesta.containsKey(productId))
			producto=cesta.get(productId);
			
		return producto;
	}

	@Override
	public Product findProduct(Product product) {
		// TODO Auto-generated method stub
		return null;
	}

}
