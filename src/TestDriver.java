import th.ac.npru.se.pb.product.ProductSingleton;

public class TestDriver {

    public static void main(String[] args) {
        ProductSingleton p1 = ProductSingleton.getInstance();

        // ��駤�Ң������Թ���
//        p1.setProduct("P003", "Switch2", 19800);
        // �ʴ��������Թ���
        p1.showProduct();
        // �֧�����Թ��Ҩҡ�ҹ���������� product ID
        String productNameFromDb = p1.getProductNameById("P003");
        System.out.println("Queried Product Name from DB: " + productNameFromDb);
    }
    
}
