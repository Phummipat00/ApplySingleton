package th.ac.npru.se.pb.product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Singleton class สำหรับจัดการข้อมูลสินค้า (Product)
 * และเชื่อมต่อกับฐานข้อมูลเพื่อดึงข้อมูลหรือเพิ่มข้อมูลสินค้า
 */
public class ProductSingleton {
    /** อินสแตนซ์เดียวของคลาส */
    private static ProductSingleton instance = null;

    private String product_id;
    private String product_name;
    private int product_price;

    /**
     * Constructor แบบ private เพื่อป้องกันการสร้างอินสแตนซ์จากภายนอก (Singleton pattern)
     */
    private ProductSingleton() {
        // Constructor ส่วนตัว (singleton)
    }

    /**
     * คืนค่าอินสแตนซ์เดียวของ ProductSingleton
     * 
     * @return อินสแตนซ์ของ ProductSingleton
     */
    public static ProductSingleton getInstance() {
        if (instance == null) {
            instance = new ProductSingleton();
        }
        return instance;
    }

    /**
     * กำหนดค่ารายละเอียดสินค้า
     * 
     * @param product_id รหัสสินค้า
     * @param product_name ชื่อสินค้า
     * @param product_price ราคาสินค้า
     */
    public void setProduct(String product_id, String product_name, int product_price) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_price = product_price;
    }

    /**
     * แสดงรายละเอียดของสินค้าในระบบ console
     */
    public void showProduct() {
        System.out.println("Product ID: " + this.product_id);
        System.out.println("Product Name: " + this.product_name);
        System.out.println("Product Price: " + this.product_price);
    }

    /**
     * ดึงรหัสสินค้า
     * 
     * @return รหัสสินค้า
     */
    public String getProductId() {
        return product_id;
    }

    /**
     * ดึงชื่อสินค้า
     * 
     * @return ชื่อสินค้า
     */
    public String getProductName() {
        return product_name;
    }

    /**
     * ดึงราคาสินค้า
     * 
     * @return ราคาสินค้า
     */
    public int getProductPrice() {
        return product_price;
    }

    /**
     * ค้นหาชื่อสินค้าจากรหัสสินค้าในฐานข้อมูล
     * 
     * @param pid รหัสสินค้าที่ต้องการค้นหา
     * @return ชื่อสินค้าที่พบ หรือข้อความว่างถ้าไม่พบ
     */
    public String getProductNameById(String pid) {
        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;
        String foundProductName = "";

        try {
            // เชื่อมต่อฐานข้อมูล
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testing", "root", "");

            System.out.println("Database connection successful!\n");

            // สร้างคำสั่ง SQL
            myStmt = myConn.createStatement();

            // รัน SQL
            myRs = myStmt.executeQuery("SELECT * FROM product WHERE p_id='" + pid + "'");

            // อ่านค่าผลลัพธ์
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

    /**
     * แทรกข้อมูลสินค้าใหม่ลงในฐานข้อมูล โดยใช้ข้อมูลในอินสแตนซ์นี้
     * 
     * @param pid รหัสสินค้า (ไม่ได้ใช้ใน SQL ปัจจุบัน)
     * @param pname ชื่อสินค้า (ไม่ได้ใช้ใน SQL ปัจจุบัน)
     * @param pprice ราคาสินค้า (ไม่ได้ใช้ใน SQL ปัจจุบัน)
     */
    public void insertProduct(String pid, String pname, int pprice) {
        Connection myConn = null;
        Statement myStmt = null;

        try {
            // เชื่อมต่อฐานข้อมูล
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testing", "root" , "");

            System.out.println("Database connection successful!\n");

            // สร้างคำสั่ง SQL
            myStmt = myConn.createStatement();

            // แทรกข้อมูล
            String sql = "INSERT INTO Product " +
                         "VALUES ('"+this.product_id+"','"+this.product_name+"',"+this.product_price+")";
            myStmt.executeUpdate(sql);

        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}
