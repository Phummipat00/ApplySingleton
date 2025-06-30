package th.ac.npru.se.pb.product;

/**
 * อินเทอร์เฟซที่ใช้สำหรับจัดการฟังก์ชันการลบข้อมูลตาม ID
 */
public interface Managable {

    /**
     * ลบข้อมูลตามรหัส (ID)
     *
     * @param id รหัสของข้อมูลที่ต้องการลบ
     * @return {@code true} หากลบข้อมูลสำเร็จ, {@code false} หากล้มเหลว
     */
    public boolean DeleteById(int id);
}
