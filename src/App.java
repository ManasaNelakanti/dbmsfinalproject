import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class App {
    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "manasa1GIT --234")) {
              // ! 3. The product p1 changes its name to pp1 in Product and Stock.
                
                
              String alterQuery ="ALTER TABLE stock DROP CONSTRAINT fk_stock_product; ALTER TABLE stock ADD CONSTRAINT fk_stock_product FOREIGN KEY (prod_id) REFERENCES product(prod_id) ON UPDATE CASCADE;";
                
              try(PreparedStatement alterStockStatement= connection.prepareStatement(alterQuery)) {
                  int alterresultSet = alterStockStatement.executeUpdate();
                  System.out.println("Table Updated. Rows affected: " + Integer.toString(alterresultSet));
              }
              
    
              String updateQuery = "UPDATE product SET prod_id = ? WHERE prod_id = ?; " +
              "UPDATE stock SET prod_id = ? WHERE prod_id = ?";
              try(PreparedStatement updateStatement1 = connection.prepareStatement(updateQuery);
              ){
                  updateStatement1.clearParameters();
                  updateStatement1.setObject(1, "pp1"); 
                  updateStatement1.setObject(2, "P1"); 
                  updateStatement1.setObject(3, "pp1"); 
                  updateStatement1.setObject(4, "P1"); 
                  int stockresultSet =updateStatement1.executeUpdate();
                  System.out.println("Table Updated. Rows affected: " + Integer.toString(stockresultSet));
              }
              // ! 4. The depot d1 changes its name to dd1 in Depot and Stock.
             
              
              String alterQuery1 ="ALTER TABLE stock DROP CONSTRAINT fk_stock_depot; ALTER TABLE stock ADD CONSTRAINT fk_stock_depot FOREIGN KEY (dept_id) REFERENCES depot(dept_id) ON UPDATE CASCADE;";
              
              try(PreparedStatement alterStockStatement= connection.prepareStatement(alterQuery1)) {
                  int resultSet = alterStockStatement.executeUpdate();
                  System.out.println("Table Updated. Rows affected: " + Integer.toString(resultSet));
              }
              
             
              
              String updateQuery1 = "UPDATE depot SET dept_id = ? WHERE dept_id = ?; " +
              "UPDATE stock SET dept_id = ? WHERE dept_id = ?";
              try(PreparedStatement updateStatement1 = connection.prepareStatement(updateQuery1);
              ){
                  updateStatement1.clearParameters();
                  updateStatement1.setObject(1, "dd1"); 
                  updateStatement1.setObject(2, "D1"); 
                  updateStatement1.setObject(3, "dd1"); 
                  updateStatement1.setObject(4, "D1"); 
                  int resultSet =updateStatement1.executeUpdate();
                  System.out.println("Table Updated. Rows affected: " + Integer.toString(resultSet));
              }

                 //  List of Stock Table 

              try (PreparedStatement StockStatement = connection.prepareStatement("SELECT * FROM stock");
              ResultSet resultSet = StockStatement.executeQuery()) {
             System.out.println("Stock");
             System.out.println("\n");
             while (resultSet.next()) {
                 System.out.print(resultSet.getString("prod_id") + " " + resultSet.getString("dept_id") + " " + resultSet.getString("quantity"));
                 System.out.println("\n");
             }
             System.out.println("\n");
         }

         //  List of Depot Table

         try (PreparedStatement DepotStatement = connection.prepareStatement("SELECT * FROM depot");
              ResultSet resultSet = DepotStatement.executeQuery()) {
                  System.out.println("Depot");
                  System.out.println("\n");
             while (resultSet.next()) {

                 System.out.print(resultSet.getString("dept_id") + " " + resultSet.getString("addr") + " " + resultSet.getString("volume"));
                 System.out.println("\n");
             }
             System.out.println("\n");
         }

         //  List of Product Table

         try (PreparedStatement ProductStatement = connection.prepareStatement("SELECT * FROM product");
              ResultSet resultSet = ProductStatement.executeQuery()) {
                  System.out.println("Product");
                  System.out.println("\n");
             while (resultSet.next()) {

                 System.out.print(resultSet.getString("prod_id") + " " + resultSet.getString("pname") + " " + resultSet.getString("price"));
                 System.out.println("\n");
             }
         }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}