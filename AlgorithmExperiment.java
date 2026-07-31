public class AlgorithmExperiment {

    // ==========================================
    // อัลกอริทึมที่ 1: Brute Force
    // ==========================================
    public static boolean findPairBruteForce(int[] a, int k) {
        if (a == null || a.length < 2) return false;
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[i] + a[j] == k) return true;
            }
        }
        return false;
    }

    // ==========================================
    // อัลกอริทึมที่ 2: Recursive Two-Pointer
    // ==========================================
    public static boolean findPairRecursive(int[] a, int k, int left, int right) {
        if (a == null || left >= right) return false;

        int sum = a[left] + a[right];
        if (sum == k) {
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
            int low = i + 1, high = a.length - 1;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (a[mid] == target) return true;
                else if (a[mid] < target) low = mid + 1;
                else high = mid - 1;
            }
        }
        return false;
    }

    // ==========================================
    // เมธอดหลัก: สำหรับจำลองงานทดลองเปรียบเทียบประสิทธิภาพ
    // ==========================================
    public static void main(String[] args) {
        int[] sizes = {100, 1000, 10000, 100000};
        int runs = 5;

        System.out.println("ขนาด (n)\tAlgo 1 (Brute)\tAlgo 2 (Recursive)\tAlgo 3 (Binary Search)");

        for (int n : sizes) {
            // สร้างอาร์เรย์เรียงลำดับ 1 ถึง n
            int[] a = new int[n];
            for (int i = 0; i < n; i++) a[i] = i + 1;
            int k = -1; // กำหนดค่า k ที่ไม่มีทางหาเจอ เพื่อบังคับให้เกิด Worst Case

            long totalTime1 = 0, totalTime2 = 0, totalTime3 = 0;

            for (int r = 0; r < runs; r++) {
                long start, end;

                // 1. วัดเวลา Brute Force
                start = System.nanoTime();
                findPairBruteForce(a, k);
                end = System.nanoTime();
                totalTime1 += (end - start);

                // 2. วัดเวลา Recursive Two-Pointer
                try {
                    start = System.nanoTime();
                    findPairRecursive(a, k, 0, n - 1);
                    end = System.nanoTime();
                    totalTime2 += (end - start);
                } catch (StackOverflowError e) {
                    // ป้องกันโปรแกรมเด้งเมื่อ n มีขนาดใหญ่เกินไป
                }

                // 3. วัดเวลา Binary Search
                start = System.nanoTime();
                findPairBinarySearch(a, k);
                end = System.nanoTime();
                totalTime3 += (end - start);
            }

            // คำนวณเวลาเฉลี่ย
            long avg1 = totalTime1 / runs;
            long avg2 = totalTime2 / runs;
            long avg3 = totalTime3 / runs;

            System.out.printf("%d\t\t%d\t\t%d\t\t\t%d\n", n, avg1, (totalTime2 == 0 ? -1 : avg2), avg3);
        }
    }
}