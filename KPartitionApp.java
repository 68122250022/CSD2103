import java.util.Arrays;

public class KPartitionApp {

    // เมธอดช่วยสลับค่าตำแหน่งในอาร์เรย์ (Helper Function)
    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    // ==========================================
    // อัลกอริทึมที่ 1: Recursive Partition
    // ==========================================
    public static void partitionRecursive(int[] a, int k, int left, int right) {
        if (a == null || left >= right) {
            return;
        }

        // ขยับตัวชี้ซ้ายหาตัวที่ > k
        while (left < right && a[left] <= k) {
            left++;
        }
        // ขยับตัวชี้ขวาหาตัวที่ <= k
        while (left < right && a[right] > k) {
            right--;
        }

        // หากยังไม่ชนกัน ให้สลับค่าและเรียก Recursive ในช่วงถัดไป
        if (left < right) {
            swap(a, left, right);
            partitionRecursive(a, k, left + 1, right - 1);
        }
    }

    // ==========================================
    // อัลกอริทึมที่ 2: Iterative Partition
    // ==========================================
    public static void partitionIterative(int[] a, int k) {
        if (a == null || a.length <= 1) return;

        int left = 0;
        int right = a.length - 1;

        while (left < right) {
            while (left < right && a[left] <= k) {
                left++;
            }
            while (left < right && a[right] > k) {
                right--;
            }

            if (left < right) {
                swap(a, left, right);
                left++;
                right--;
            }
        }
    }

    // ==========================================
    // อัลกอริทึมที่ 3: Sorting-Based Algorithm
    // ==========================================
    public static void partitionBySorting(int[] a, int k) {
        if (a == null || a.length <= 1) return;

        // 1. เรียงอาร์เรย์ทั้งหมด
        Arrays.sort(a);

        // 2. ค้นหาตำแหน่งสุดท้ายที่มีค่าน้อยกว่าหรือเท่ากับ k
        int lastIndex = -1;
        for (int i = 0; i < a.length; i++) {
            if (a[i] <= k) {
                lastIndex = i;
            } else {
                break;
            }
        }

        // แสดงผลตำแหน่งสุดท้ายเพื่อการตรวจสอบ
        System.out.println("  [Sort Info] ตำแหน่งสุดท้ายที่ <= " + k + " คือ Index: " + lastIndex);
    }

    // ==========================================
    // เมธอดหลักสำหรับการทดสอบ
    // ==========================================
    public static void main(String[] args) {
        int[] originalArray = {12, 4, 7, 15, 3, 10, 8};
        int k = 8;

        System.out.println("Input Original Array : " + Arrays.toString(originalArray));
        System.out.println("Pivot Value (k)       : " + k);
        System.out.println("--------------------------------------------------");

        // 1. ทดสอบ Recursive Partition
        int[] arr1 = originalArray.clone();
        partitionRecursive(arr1, k, 0, arr1.length - 1);
        System.out.println("1. Recursive Partition Output : " + Arrays.toString(arr1));

        // 2. ทดสอบ Iterative Partition
        int[] arr2 = originalArray.clone();
        partitionIterative(arr2, k);
        System.out.println("2. Iterative Partition Output : " + Arrays.toString(arr2));

        // 3. ทดสอบ Sorting-Based Algorithm
        int[] arr3 = originalArray.clone();
        System.out.println("3. Sorting-Based Output       :");
        partitionBySorting(arr3, k);
        System.out.println("   Result Array               : " + Arrays.toString(arr3));
    }
}