package JDBC2;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ProductInformation extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_3;
	private JTextField textField_2;
	private JTextField textField_1;
	private JFrame frame1;
	private Product productlist;
	private static ProductDAO dao;
	private String username = "scott", password = "tiger";
	private static JLabel lblNewLabel;
	private static JComboBox<String> comboBox ;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductInformation frame = new ProductInformation();
					frame.setLocationRelativeTo(null);
					frame.frame1.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ProductInformation() {
		initialize();

	}

	private void initialize() {
		frame1 = new JFrame("Product Information");
		
		frame1.setBounds(100, 100, 1231, 518);
		frame1.getContentPane().setLayout(null);
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel label = new JLabel("Product Information");
		label.setForeground(Color.BLUE);
		label.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 30));
		label.setBounds(388, 0, 689, 92);
		frame1.getContentPane().add(label);

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField.setBounds(489, 91, 121, 30);
		frame1.getContentPane().add(textField);
		textField.setColumns(10);

		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblId.setBounds(449, 96, 73, 19);
		frame1.getContentPane().add(lblId);

		textField_3 = new JTextField();
		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField_3.setColumns(10);
		textField_3.setBounds(489, 287, 244, 30);
		frame1.getContentPane().add(textField_3);

		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField_2.setColumns(10);
		textField_2.setBounds(489, 221, 244, 30);
		frame1.getContentPane().add(textField_2);

		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField_1.setColumns(10);
		textField_1.setBounds(489, 156, 244, 30);
		frame1.getContentPane().add(textField_1);
		
		dao=new ProductDAOImpl(username,password);
		comboBox = new JComboBox<>(new ProductDAOImpl(username, password).getIDProducts());
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 19));

		comboBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				ProductDAO dao = new ProductDAOImpl(username, password);
				

				Product p = dao.getProduct((String) comboBox.getSelectedItem());
				textField.setText((String) comboBox.getSelectedItem());
				textField_1.setText(p.getDescription());
				textField_2.setText(p.getPrice() + "");
				textField_3.setText(p.getQuantity() + "");
			}

		});

		comboBox.setBounds(622, 91, 110, 30);
		frame1.getContentPane().add(comboBox);

		JButton btnNewButton_1 = new JButton("INSERT");
		btnNewButton_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String id = textField.getText();
				String description = textField_1.getText();
				double price = Double.parseDouble(textField_2.getText());
				int quantity = Integer.parseInt(textField_3.getText());
				productlist = new Product(id, description, price, quantity);
				dao = new ProductDAOImpl(username, password);
				if (ProductDAOImpl.getConn() != null) {
					int resp = dao.insertProduct(productlist);
					lblNewLabel.setVisible(true);

					if (resp > 0) {
						lblNewLabel.setText("Product inserted successfully");
						lblNewLabel.setForeground(Color.GREEN);
					} else {
						lblNewLabel.setText("Error!!! Cannot insert the product");
						lblNewLabel.setForeground(Color.RED);
					}
				} else {
					lblNewLabel.setText("Connection is not established");
					lblNewLabel.setForeground(Color.RED);
				}

			}

		});

		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBounds(77, 392, 176, 36);
		frame1.getContentPane().add(btnNewButton_1);

		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				ProductDAO dao = new ProductDAOImpl(username, password);
				Product p = new Product();
				p.setId(textField.getText());
				p.setDescription(textField_1.getText());
				p.setPrice(Double.parseDouble(textField_2.getText()));
				p.setQuantity(Integer.parseInt(textField_3.getText()));
				lblNewLabel.setVisible(true);

				if (ProductDAOImpl.getConn() != null && p != null) {
					int resp = dao.updateProduct(p);

					if (resp > 0) {
						lblNewLabel.setText("Product upated successfully");
						lblNewLabel.setForeground(Color.GREEN);
					} else {
						lblNewLabel.setText("Error!!! Cannot update the product");
						lblNewLabel.setForeground(Color.RED);
					}
				} else {
					lblNewLabel.setText("Connection is not established");
					lblNewLabel.setForeground(Color.RED);
				}
			}

		});
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnUpdate.setBounds(302, 392, 176, 36);
		frame1.getContentPane().add(btnUpdate);

		JButton btnDelete = new JButton("DELETE\r\n");
		btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ProductDAO dao = new ProductDAOImpl(username, password);
				productlist = new Product();
				productlist.setId(textField.getText());

				if (ProductDAOImpl.getConn() != null) {
					int resp = dao.updateProduct(productlist);
					lblNewLabel.setVisible(true);

					if (resp > 0) {
						lblNewLabel.setText("Product deleted successfully");
						lblNewLabel.setForeground(Color.GREEN);
					} else {
						lblNewLabel.setText("Error!!! Cannot delete the product");
						lblNewLabel.setForeground(Color.RED);
					}
				} else {
					lblNewLabel.setText("Connection is not established");
					lblNewLabel.setForeground(Color.RED);
				}
			}

		});
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnDelete.setBounds(526, 392, 176, 36);
		frame1.getContentPane().add(btnDelete);

		JButton btnClear = new JButton("CLEAR");
		btnClear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				comboBox.setModel(new DefaultComboBoxModel<String>(dao.getIDProducts()));
				textField.setText("");
				textField_2.setText("");
				textField_1.setText("");
				textField_3.setText("");
				lblNewLabel.setText("");
				lblNewLabel.setVisible(false);
			}

		});
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnClear.setBounds(751, 392, 176, 36);
		frame1.getContentPane().add(btnClear);

		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame1.dispose();
			}

		});
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnExit.setBounds(977, 392, 176, 36);
		frame1.getContentPane().add(btnExit);

		JLabel lblDescription = new JLabel("Description");
		lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDescription.setBounds(394, 161, 121, 16);
		frame1.getContentPane().add(lblDescription);

		JLabel lblPrice = new JLabel("Price");
		lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPrice.setBounds(422, 225, 90, 16);
		frame1.getContentPane().add(lblPrice);

		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblQuantity.setBounds(394, 287, 125, 25);
		frame1.getContentPane().add(lblQuantity);

		lblNewLabel = new JLabel("New label");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblNewLabel.setBounds(475, 341, 362, 30);
		frame1.getContentPane().add(lblNewLabel);
		lblNewLabel.setVisible(false);
	}
}
