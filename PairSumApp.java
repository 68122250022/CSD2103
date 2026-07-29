public class PairSumApp {

    // ==========================================
    // อัลกอริทึมที่ 1: Brute Force
    // ==========================================
    public static boolean findPairBruteForce(int[] a, int k) {
        if (a == null || a.length < 2) return false;

        for (int i = 0; i < a.length - 1; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[i] + a[j] == k) {
                    System.out.println("Pair found: " + a[i] + " and " + a[j]);
                    return true;
                }
            }
        }
        return false;
    }

    // ==========================================
    // อัลกอริทึมที่ 2: Recursive Two-Pointer
    // ==========================================
    public static boolean findPairRecursive(int[] a, int k, int left, int right) {
        if (a == null || left >= right) {
            return false;
        }

        int sum = a[left] + a[right];

        if (sum == k) {
            System.out.println("Pair found: " + a[left] + " and " + a[right]);
            return true;
        } else if (sum < k) {
            return findPairRecursive(a, k, left + 1, right);
        } else {
            return findPairRecursive(a, k, left, right - 1);
        }
    }

    // ==========================================
    // อัลกอริทึมที่ 3: Binary Search
    // ==========================================
    public static boolean findPairBinarySearch(int[] a, int k) {
        if (a == null || a.length < 2) return false;

        for (int i = 0; i < a.length - 1; i++) {
            int target = k - a[i];
            // ทำ Binary Search ในช่วงตั้งแต่ตำแหน่ง i + 1 เป็นต้นไป
            int matchIndex = binarySearchHelper(a, i + 1, a.length - 1, target);

            if (matchIndex != -1) {
                System.out.println("Pair found: " + a[i] + " and " + a[matchIndex]);
                return true;
            }
        }
        return false;
    }

    // เมธอดช่วย Binary Search
    private static int binarySearchHelper(int[] a, int low, int high, int target) {
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (a[mid] == target) {
                return mid;
            } else if (a[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    // ==========================================
    // เมธอดหลักสำหรับทดสอบโปรแกรม
    // ==========================================
    public static void main(String[] args) {
        // ตัวอย่างตามโจทย์กำหนด
        int[] A = {2, 4, 7, 11, 15, 20};
        int k = 18;

        System.out.println("Input Array A : [2, 4, 7, 11, 15, 20]");
        System.out.println("Target Sum k  : " + k);
        System.out.println("--------------------------------------------------");

        System.out.print("1. Brute Force Output          : ");
        boolean res1 = findPairBruteForce(A, k);

        System.out.print("2. Recursive Two-Pointer Output: ");
        boolean res2 = findPairRecursive(A, k, 0, A.length - 1);

        System.out.print("3. Binary Search Output        : ");
        boolean res3 = findPairBinarySearch(A, k);
    }
}