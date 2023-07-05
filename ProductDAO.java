package JDBC2;

public interface ProductDAO {
	
	int insertProduct(Product p);
	int updateProduct(Product p);
	int deleteProduct(String id);
	String[] getIDProducts();
	Product getProduct(String id) ;

	

}
