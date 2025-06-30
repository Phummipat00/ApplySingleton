package th.ac.npru.se.pb.product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

//implemants Managable
public class ProductSingleton {
    private static ProductSingleton instance = null;

    private String product_id;
    private String product_name;
    private int product_price;

    private ProductSingleton() {
        // Constructor ส่วนตัว (singleton)
    }

    public static ProductSingleton getInstance() {
        if (instance == null) {
            instance = new ProductSingleton();
        }
        return instance;
    }

    public void setProduct(String product_id, String product_name, int product_price) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_price = product_price;
    }

    public void showProduct() {
        System.out.println("Product ID: " + this.product_id);
        System.out.println("Product Name: " + this.product_name);
        System.out.println("Product Price: " + this.product_price);
    }

    public String getProductId() {
        return product_id;
    }

    public String getProductName() {
        return product_name;
    }

    public int getProductPrice() {
        return product_price;
    }

    // Query DB เพื่อดึงชื่อสินค้าจาก product_id
    public String getProductNameById(String pid) {
        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;
        String foundProductName = "";

        try {
            // 1. เชื่อมต่อฐานข้อมูล
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testing", "root", "");

            System.out.println("Database connection successful!\n");

            // 2. สร้างคำสั่ง SQL
            myStmt = myConn.createStatement();

            // 3. รันคำสั่ง SQL
            myRs = myStmt.executeQuery("SELECT * FROM product WHERE p_id='" + pid + "'");

            // 4. อ่านค่าผลลัพธ์
            if (myRs.next()) {
                foundProductName = myRs.getString("p_name");
                System.out.println("Product found: " + foundProductName);
            } else {
                System.out.println("Product not found.");
            }

        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            try {
                if (myRs != null) myRs.close();
                if (myStmt != null) myStmt.close();
                if (myConn != null) myConn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return foundProductName;
    }
    public void insertProduct(String pid, String pname, int pprice) {
        Connection myConn = null;
        Statement myStmt = null;
//        ResultSet myRs = null;
//        String getProductId="";
        try {
            // 1. Get a connection to database
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testing", "root" , "");
            
            System.out.println("Database connection successful!\n");
            
            // 2. Create a statement
            myStmt = myConn.createStatement();
            
            // 3. Execute SQL query
            String sql = "INSERT INTO Product " +
                       "VALUES ('"+this.product_id+"','"+this.product_name+"',"+this.product_price+")";
            myStmt.executeUpdate(sql);
            
            
            // 4. Process the result set
//            while (myRs.next()) {
//                System.out.println(myRs.getString("p_id") + ", " + myRs.getString("p_name")+ ", " + myRs.getInt("p_price"));
//                getProductId = myRs.getString("p_id");
//                System.out.println(getProductId);
//            }
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}
