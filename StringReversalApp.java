public class StringReversalApp {

    // อัลกอริทึมที่ 1: Recursive Algorithm
    static String reverseRecursive(String s) {
        // Base Case: ป้องกันโปรแกรมพังเมื่อรับข้อมูลว่างหรือ null
        if (s == null || s.isEmpty()) {
            return s;
        }
        // Recursive Case: นำตัวอักษรตัวสุดท้ายมาต่อกับผลลัพธ์ที่เหลือ
        return s.charAt(s.length() - 1) + reverseRecursive(s.substring(0, s.length() - 1));
    }

    // อัลกอริทึมที่ 2: Iterative Algorithm (ใช้เครื่องหมาย + เพื่อการเปรียบเทียบ)
    static String reverseIterativeBad(String s) {
        if (s == null || s.isEmpty()) return s;
        String result = "";
        for (int i = s.length() - 1; i >= 0; i--) {
            result += s.charAt(i); // ต่อสตริงด้วยเครื่องหมาย +
        }
        return result;
    }

    // อัลกอริทึมที่ 2 (ปรับปรุง): Iterative Algorithm (ใช้ StringBuilder)
    static String reverseIterative(String s) {
        if (s == null || s.isEmpty()) return s; // ทดสอบกรณีพิเศษ (ข้อมูลว่าง)
        StringBuilder result = new StringBuilder();
        // ใช้ลูปอ่านจากท้ายมาหน้า
        for (int i = s.length() - 1; i >= 0; i--) {
            result.append(s.charAt(i));
        }
        return result.toString();
    }

    public static void main(String[] args) {
        // ทดสอบกรณีปกติ
        String input = "pots&pans";
        System.out.println("Input: " + input); //
        System.out.println("Output (Recursive): " + reverseRecursive(input)); // คาดหวัง snap&stop

        // ทดสอบกรณีพิเศษ: ข้อมูลว่าง
        System.out.println("Output (Empty Input): '" + reverseIterative("") + "'");

        System.out.println("--------------------------------------------------");
        // งานวิเคราะห์: ทดสอบกับสตริงขนาดต่างๆ
        int[] sizes = {10, 100, 1000, 10000};

        for (int size : sizes) {
            String testStr = "A".repeat(size);
            System.out.println("Testing size: " + size);

            long start = System.nanoTime();
            reverseIterative(testStr); // ใช้ StringBuilder
            System.out.println("  Iterative (StringBuilder): " + (System.nanoTime() - start) / 1000 + " us");

            start = System.nanoTime();
            reverseIterativeBad(testStr); // ใช้เครื่องหมาย +
            System.out.println("  Iterative (String +)     : " + (System.nanoTime() - start) / 1000 + " us");

            try {
                start = System.nanoTime();
                reverseRecursive(testStr); // Recursive
                System.out.println("  Recursive Algorithm      : " + (System.nanoTime() - start) / 1000 + " us");
            } catch (StackOverflowError e) {
                System.out.println("  Recursive Algorithm      : StackOverflowError!");
            }
        }
    }
}