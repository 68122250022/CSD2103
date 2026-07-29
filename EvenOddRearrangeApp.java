import java.util.Arrays;

public class EvenOddRearrangeApp {

    // เมธอดช่วยสลับค่าในอาเรย์ (Helper Function)
    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    // ==========================================
    // อัลกอริทึมที่ 1: Recursive Two-Pointer
    // ==========================================
    static void rearrangeRecursive(int[] a, int left, int right) {
        // Base Case: ดักจับอาเรย์ว่าง/null หรือเมื่อตัวชี้ชนกัน
        if (a == null || left >= right) {
            return;
        }

        // Recursive Case
        if (Math.abs(a[left] % 2) == 0) { // เลขคู่ทางซ้าย
            rearrangeRecursive(a, left + 1, right);
        } else if (Math.abs(a[right] % 2) != 0) { // เลขคี่ทางขวา
            rearrangeRecursive(a, left, right - 1);
        } else { // ซ้ายเป็นคี่ ขวาเป็นคู่ ให้สลับ
            swap(a, left, right);
            rearrangeRecursive(a, left + 1, right - 1);
        }
    }

    // ==========================================
    // อัลกอริทึมที่ 2: Iterative Two-Pointer
    // ==========================================
    static void rearrangeTwoPointer(int[] a) {
        if (a == null || a.length <= 1) return;

        int left = 0;
        int right = a.length - 1;

        while (left < right) {
            // เลื่อนขวาจนกว่าจะเจอเลขคี่
            while (left < right && Math.abs(a[left] % 2) == 0) {
                left++;
            }
            // เลื่อนซ้ายจนกว่าจะเจอเลขคู่
            while (left < right && Math.abs(a[right] % 2) != 0) {
                right--;
            }
            // สลับเลขคี่ทางซ้ายกับเลขคู่ทางขวา
            if (left < right) {
                swap(a, left, right);
                left++;
                right--;
            }
        }
    }

    // ==========================================
    // อัลกอริทึมที่ 3: Extra Array
    // ==========================================
    static int[] rearrangeExtraArray(int[] a) {
        if (a == null) return null;

        int[] result = new int[a.length];
        int index = 0;

        // รอบที่ 1: เก็บเฉพาะเลขคู่ลงในอาเรย์ใหม่
        for (int val : a) {
            if (Math.abs(val % 2) == 0) {
                result[index++] = val;
            }
        }

        // รอบที่ 2: เก็บเฉพาะเลขคี่ต่อท้าย
        for (int val : a) {
            if (Math.abs(val % 2) != 0) {
                result[index++] = val;
            }
        }

        return result;
    }

    // ==========================================
    // เมธอดหลักสำหรับการทดสอบ
    // ==========================================
    public static void main(String[] args) {
        // 1. ทดสอบตัวอย่างตามโจทย์ข้อ 4
        int[] input1 = {7, 2, 9, 4, 1, 6, 3, 8};
        System.out.println("=== การทดสอบชุดข้อมูลที่ 1 ===");
        System.out.println("Input Original: " + Arrays.toString(input1));

        int[] arr1 = input1.clone();
        rearrangeRecursive(arr1, 0, arr1.length - 1);
        System.out.println("1. Recursive Two-Pointer Output : " + Arrays.toString(arr1));

        int[] arr2 = input1.clone();
        rearrangeTwoPointer(arr2);
        System.out.println("2. Iterative Two-Pointer Output : " + Arrays.toString(arr2));

        int[] arr3 = rearrangeExtraArray(input1);
        System.out.println("3. Extra Array Output           : " + Arrays.toString(arr3));

        System.out.println("\n--------------------------------------------------");

        // 2. ทดสอบความเสถียร (Stability Check) ตามโจทย์กำหนด
        int[] stabilityInput = {5, 2, 7, 4, 9, 6};
        System.out.println("=== การทดสอบความเสถียร (Stability Check) ===");
        System.out.println("Input: " + Arrays.toString(stabilityInput));
        System.out.println("Expected Stable Output: [2, 4, 6, 5, 7, 9]");

        int[] s1 = stabilityInput.clone();
        rearrangeRecursive(s1, 0, s1.length - 1);
        System.out.println("1. Recursive Two-Pointer Output : " + Arrays.toString(s1));

        int[] s2 = stabilityInput.clone();
        rearrangeTwoPointer(s2);
        System.out.println("2. Iterative Two-Pointer Output : " + Arrays.toString(s2));

        int[] s3 = rearrangeExtraArray(stabilityInput);
        System.out.println("3. Extra Array Output           : " + Arrays.toString(s3));
    }
}