package JDBC2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDAOImpl implements ProductDAO {

	private static Connection conn;

	public ProductDAOImpl(String username, String password) {
		conn = ConnectionProvider.getConnection(username, password);
	}

	public static Connection getConn() {
		return conn;
	}

	@Override
	public int insertProduct(Product p) {
		String insertquery = "INSERT INTO PRODUCTLIST (ID,DESCRIPTION,PRICE,QUANTITY) VALUES(?,?,?,?)";
		int result = -1;
		try {
			PreparedStatement pstmt = conn.prepareStatement(insertquery);

			pstmt.setString(1, p.getId());
			pstmt.setString(2, p.getDescription());
			pstmt.setDouble(3, p.getPrice());
			pstmt.setInt(4, p.getQuantity());

			result = pstmt.executeUpdate();

			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int updateProduct(Product p) {
		String updatequery = "UPDATE PRODUCTLIST SET DESCRIPTION=?,PRICE=?,QUANTITY=? WHERE ID=?";
		int result = -1;
		try {
			System.out.println(conn);
			PreparedStatement pstmt = conn.prepareStatement(updatequery);
			System.out.println(pstmt);
			pstmt.setString(1, p.getDescription());
			

			pstmt.setDouble(2, p.getPrice());
			

			pstmt.setInt(3, p.getQuantity());
			

			pstmt.setString(4, p.getId());
			

			result = pstmt.executeUpdate();
			System.out.println(result);
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int deleteProduct(String id) {
		String updatequery = "DELETE FROM PRODUCTLIST WHERE ID=?";
		int result = -1;
		try {
			PreparedStatement pstmt = conn.prepareStatement(updatequery);

			pstmt.setString(1, id);

			result = pstmt.executeUpdate();

			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public String[] getIDProducts() {
		String[] productids = null;

		String readquery = "SELECT ID FROM PRODUCTLIST ORDER BY ID";
		try {
			PreparedStatement pstmt = conn.prepareStatement(readquery);

			ResultSet result = pstmt.executeQuery();
			int count = 0;

			while (result.next()) {
				count++;
			}
			productids = new String[count];
			result = pstmt.executeQuery();
			result.next();
			for (int i = 0; i < count; i++) {
				productids[i] = result.getString(1);
				result.next();
			}
//			System.out.println(productids[1]);

			return productids;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return productids;
	}

	public Product getProduct(String id) {
		Product prod = null;

		String getquery = "SELECT * FROM PRODUCTLIST WHERE ID=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(getquery);
			pstmt.setString(1, id);
			ResultSet result = pstmt.executeQuery();
			prod = new Product();
			while (result.next()) {
				prod.setDescription(result.getString(2));
				prod.setPrice(result.getDouble(3));
				prod.setQuantity(result.getInt(4));
			}

			return prod;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return prod;
	}

}
