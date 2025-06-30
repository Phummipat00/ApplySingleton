import th.ac.npru.se.pb.product.ProductSingleton;

public class TestDriver {

    public static void main(String[] args) {
        ProductSingleton p1 = ProductSingleton.getInstance();

        // ตั้งค่าข้อมูลสินค้า
//        p1.setProduct("P003", "Switch2", 19800);
        // แสดงข้อมูลสินค้า
        p1.showProduct();
        // ดึงชื่อสินค้าจากฐานข้อมูลโดยใช้ product ID
        String productNameFromDb = p1.getProductNameById("P003");
        System.out.println("Queried Product Name from DB: " + productNameFromDb);
    }
    
}
