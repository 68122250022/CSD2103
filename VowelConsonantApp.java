public class VowelConsonantApp {

    // เมธอดตรวจสอบว่าเป็นสระหรือไม่ (ไม่แยกตัวพิมพ์เล็ก-ใหญ่)
    private static boolean isVowel(char c) {
        c = Character.toLowerCase(c);
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }

    // เมธอดตรวจสอบว่าเป็นพยัญชนะหรือไม่ (ละเว้น ตัวเลข ช่องว่าง สัญลักษณ์)
    private static boolean isConsonant(char c) {
        c = Character.toLowerCase(c);
        return (c >= 'a' && c <= 'z') && !isVowel(c);
    }

    // ==========================================
    // อัลกอริทึมที่ 1: Recursive Counting
    // ==========================================
    public static boolean hasMoreVowelsRecursive(String s) {
        // ดักจับกรณีข้อมูลว่างเปล่า หรือ null เพื่อป้องกันโปรแกรมพัง
        if (s == null || s.isEmpty()) {
            return false;
        }
        return countRecursive(s, 0, 0, 0);
    }

    // เมธอดผู้ช่วยสำหรับเวียนเกิดและส่งค่าตัวนับ
    private static boolean countRecursive(String s, int index, int vowels, int consonants) {
        // Base Case: เมื่อประมวลผลตัวอักษรครบทุกตัวแล้ว
        if (index == s.length()) {
            return vowels > consonants;
        }

        // Recursive Case: เช็กตัวอักษรปัจจุบันแล้วส่งค่าสะสมไปยังตัวถัดไป
        char c = s.charAt(index);
        if (isVowel(c)) {
            vowels++;
        } else if (isConsonant(c)) {
            consonants++;
        }

        return countRecursive(s, index + 1, vowels, consonants);
    }

    // ==========================================
    // อัลกอริทึมที่ 2: Iterative Counting
    // ==========================================
    public static boolean hasMoreVowelsIterative(String s) {
        if (s == null || s.isEmpty()) {
            return false;
        }

        int vowels = 0;
        int consonants = 0;

        // วนลูปอ่านตัวอักษรทุกตัว
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (isVowel(c)) {
                vowels++;
            } else if (isConsonant(c)) {
                consonants++;
            }
            // ตัวเลข ช่องว่าง และเครื่องหมายพิเศษ จะถูกข้ามโดยอัตโนมัติ
        }

        return vowels > consonants;
    }

    // ==========================================
    // เมธอดหลักสำหรับทดสอบโปรแกรม
    // ==========================================
    public static void main(String[] args) {
        // ทดสอบกรณีปกติและกรณีพิเศษ
        String[] testCases = {
                "education",             // Vowels: 5, Consonants: 4 -> true
                "hello 123!",            // Vowels: 2, Consonants: 3 -> false
                "A E I O U 999!!!",      // Vowels: 5, Consonants: 0 -> true
                "Java Programming 2026", // Vowels: 5, Consonants: 10 -> false
                "",                      // สตริงว่าง -> false
                null                     // ค่า null -> false
        };

        System.out.println("=== ผลการทดสอบเปรียบเทียบสระและพยัญชนะ ===");
        for (String test : testCases) {
            String display = (test == null) ? "null" : "\"" + test + "\"";
            boolean recResult = hasMoreVowelsRecursive(test);
            boolean itResult = hasMoreVowelsIterative(test);

            System.out.printf("Input: %-25s | Recursive: %-5b | Iterative: %-5b%n",
                    display, recResult, itResult);
        }

        System.out.println("\n=== ทดสอบความเสี่ยง StackOverflowError กับข้อมูลขนาดใหญ่ ===");
        int largeSize = 10000;
        String largeInput = "a".repeat(largeSize); // สตริงยาว 10,000 ตัวอักษร

        System.out.println("Testing with string length: " + largeSize);
        System.out.println("Iterative Result: " + hasMoreVowelsIterative(largeInput));

        try {
            System.out.println("Recursive Result: " + hasMoreVowelsRecursive(largeInput));
        } catch (StackOverflowError e) {
            System.out.println("Recursive Result: เกิดข้อผิดพลาด StackOverflowError!");
        }
    }
}