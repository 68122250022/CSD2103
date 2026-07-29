public class PalindromeApp {

    // เมธอดสำหรับทำความสะอาดข้อความ (ละเว้นตัวพิมพ์เล็ก-ใหญ่, ช่องว่าง, วรรคตอน)
    public static String cleanString(String s) {
        if (s == null) return "";
        // ใช้ Regex กรองเอาเฉพาะตัวอักษรและตัวเลข พร้อมปรับเป็นตัวพิมพ์เล็ก
        return s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
    }

    // อัลกอริทึมที่ 1: Reverse and Compare
    static boolean isPalindromeByReverse(String s) {
        if (s == null) return false;
        String cleanStr = cleanString(s);

        // สร้างสตริงย้อนกลับ
        StringBuilder reversed = new StringBuilder();
        for (int i = cleanStr.length() - 1; i >= 0; i--) {
            reversed.append(cleanStr.charAt(i));
        }

        // เปรียบเทียบสตริงเดิมกับสตริงย้อนกลับ
        return cleanStr.equals(reversed.toString());
    }

    // อัลกอริทึมที่ 2: Recursive Two-Pointer
    static boolean isPalindromeRecursive(String s, int left, int right) {
        if (s == null) return false;

        // Base Case 1: ตัวชี้ชนหรือเหลื่อมกัน แปลว่าตรวจสอบผ่านหมดทุกคู่แล้ว
        if (left >= right) {
            return true;
        }

        // Base Case 2: เจอตัวอักษรที่ไม่ตรงกัน หยุดทำงานทันที (Early Exit)
        if (s.charAt(left) != s.charAt(right)) {
            return false;
        }

        // Recursive Case: ขยับตัวชี้ซ้ายไปขวา ขยับตัวชี้ขวาไปซ้าย
        return isPalindromeRecursive(s, left + 1, right - 1);
    }

    // เมธอดครอบสำหรับเรียกใช้ Recursive แบบส่งเฉพาะ String สวยๆ
    static boolean isPalindromeRecursiveHelper(String s) {
        if (s == null) return false;
        String cleanStr = cleanString(s);
        return isPalindromeRecursive(cleanStr, 0, cleanStr.length() - 1);
    }

    public static void main(String[] args) {
        // ชุดข้อมูลทดสอบตามโจทย์กำหนด
        String[] testCases = {
                "racecar",
                "level",
                "algorithm",
                "gohangasalamiimalasagnahog",
                "A man, a plan, a canal: Panama", // เงื่อนไขพิเศษ
                "",                               // กรณีข้อมูลว่าง
                null                              // กรณีค่า null
        };

        System.out.println("=== ผลการทดสอบการตรวจสอบ Palindrome ===");
        for (String test : testCases) {
            String displayStr = (test == null) ? "null" : "\"" + test + "\"";
            boolean res1 = isPalindromeByReverse(test);
            boolean res2 = isPalindromeRecursiveHelper(test);

            System.out.printf("Input: %-35s | Reverse: %-5b | Recursive: %-5b%n",
                    displayStr, res1, res2);
        }
    }
}