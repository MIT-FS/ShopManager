package shopmanager;
import com.hazelcast.core.*;

import exceptions.NoEnoughStock;
import exceptions.NotInStock;
import model.Product;
import persistency.ProductRepository;
import exceptions.UnknownRepo;
import exceptions.NotEmpty;

import com.hazelcast.config.*;

import java.util.Iterator;
import java.util.Optional;
import java.util.logging.*;
//va a seguir el patr�n singleton (Una instancia s�lo tiene un manager, pero puede haber varias instancias...)

/**
 * @author Isabel Rom�n
 * @version 0.0. 
 * Primera versi�n de MyStockManager, clase que implementa StockManager usando el patr�n singleton
 *
 */
public class MyStockManager implements StockManager{
	
	private static Logger trazador=Logger.getLogger(MyStockManager.class.getName());
	private static StockManager myInstance;
	private HazelcastInstance hzInstance;
	private Config cfg;
	//Mapa de productos que ser� compartido por todos los stockManager que se instancien
	private IMap<String, Product> mapProducts;
    /**
     * Repositorio para persistir el stock, los elementos del mismo ser�n productos
     */
	private ProductRepository repository;
	
	/**
	 * El constructor crea la instancia hazelcast, pero es privado, s�lo la propia clase puede crear una instancia (un objeto)
	 */
	
	private MyStockManager(){
		cfg = new Config();
		
		hzInstance = Hazelcast.newHazelcastInstance(cfg);
		trazador.info("Acabo de crear una instancia hazelcast para el MyStockManager");
		mapProducts = hzInstance.getMap("products");
        trazador.info("Se ha recuperado referencia al mapa products, si no se hab�a usado se ha creado");
	}
	
	public void setRepository(ProductRepository repo) {
		repository=repo;
	}
    //Inicializa el stock en memoria a partir de un repositorio que persist�a los productos y que se pasa como par�metro
	public void init(ProductRepository repo) throws NotEmpty{
		repository=repo;
		//Si el mapa est� vacio, recupera todos los productos del repositorio y los introduce en el stock (en memoria)
		if (mapProducts.isEmpty()) {
			Iterator<Product> products=repository.findAll().iterator();
	
			while(products.hasNext()) {
				addProduct(products.next());
			}
		}
		//Si el mapa no est� vac�o avisa de que no se puede iniciar, porque ya tiene productos y los cambios en el stock se perder�an
		else 
			throw new NotEmpty();
	}	
	//Inicializa el stock en memoria a partir del repositorio configurado, si no se ha establecido el repositorio lanza una excepci�n advirti�ndolo
	public void init() throws NotEmpty,UnknownRepo{
		if(repository!=null) {
			if (mapProducts.isEmpty()) {
				Iterator<Product> products=repository.findAll().iterator();
		
				while(products.hasNext()) {
					addProduct(products.next());
				}
			}
			else 
				throw new NotEmpty();
		}
		else
			throw new UnknownRepo();
		
	}
	//Persiste los datos del stock en memoria en el repositorio, si el repositorio no estaba establecido lanza una excepci�n advirti�ndolo
	public void save() throws UnknownRepo{
		if (repository!=null)
			repository.saveAll(mapProducts.values());
		else
			throw new UnknownRepo();
	}
	
	@Override
	public StockManager getManager() {
		return getInstance();
	}
	@Override
	public void clean() {
		mapProducts.clear();
	}
	/**
	 * @deprecated
	 * @see 
	 * java.lang.Object
	 * El compilador me avisa de que uso algo "deprecated" por eso a�ado la opci�n de compilaci�n en gradle, para ver m�s detalle
	*/
	@Override
	@Deprecated
	protected void finalize() throws Throwable{
		trazador.info("Finalizando el MyStockManager, apaga la instancia hazelcast");
		hzInstance.shutdown();
		}
	
	public static StockManager getInstance() {
		if (myInstance==null){
			myInstance=new MyStockManager();
		}
		return myInstance;
	}
	@Override
	public void addProduct(Product newProduct) {
		Product productTmp;
		String msg="El tama�o del mapa al entrar es " + mapProducts.size();
		trazador.info(msg);
		if(mapProducts.containsKey(newProduct.getId())){
			productTmp=searchProduct(newProduct.getId()).get();
			newProduct.setNumber(productTmp.getNumber()+newProduct.getNumber());
		}
		mapProducts.put(newProduct.getId(), newProduct);
		msg="El mapa de tama�o " + mapProducts.size()+" incluye "+newProduct;
		trazador.info(msg);
	}
	@Override
	public Optional<Product> searchProduct(String id) {
		Product product=null;
		String msg;
		if(mapProducts.containsKey(id)){
			product=mapProducts.get(id);
			msg="Hay "+ product;
			trazador.info(msg);
		
		}else {
			msg="El "+product+" no est� en el mapa";
			trazador.info(msg);
		}
		return Optional.ofNullable(product);
	}
	/**
	 * @throws CloneNotSupportedException avisa de que no se ha podido clonar 
	 * @see java.lang.Object
	 * 
	 * El m�todo clone permite crear una instancia a partir de otra, lo que en una clase que siga el patr�n singleton no se puede permitir
	 */
	@Override
	public MyStockManager clone() throws CloneNotSupportedException {
		trazador.warning("No se puede clonar un objeto de la clase MyStockManager, sigue patr�n singleton");
	    throw new CloneNotSupportedException();
		}
	@Override
	/**
	 * <P>La clase Optional es un contenedor que dentro tendr� un producto o no (seg�n se haya encontrado o no en el stock), el m�todo isPresent permite saber si est� o no
	 * Puede consultar <a href="https://docs.oracle.com/javase/8/docs/api/java/util/Optional.html">Optional en la api de Java</a></P>
	 */
	public Product lessProduct(Product product) throws NoEnoughStock,NotInStock {
		Optional<Product> productTmp = searchProduct(product.getId());
		
		if(productTmp.isPresent())
		{
			if(productTmp.get().getNumber()<product.getNumber()) {
				throw new NoEnoughStock(productTmp.get().getNumber());
			}
			product.setNumber(productTmp.get().getNumber()-product.getNumber());
			mapProducts.put(product.getId(), product);
		}
		//Si no est� deber�a lanzar la excepci�n no est� en stock
		else throw new NotInStock(product.getId());
		return product;
	}
	
}
