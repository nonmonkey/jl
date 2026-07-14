import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class WrapperClass_ {
    public static void main(String[] args) {
        // 1. 基本类型不能放集合里
        // ArrayList<int> list = new ArrayList<>(); // ❌ 报错

        // 必须用包装类
        ArrayList<Integer> list = new ArrayList<>();
        list.add(10);   // 自动装箱
        list.add(20);
        System.out.println(list); // [10, 20]

        // 2. 基本类型没有方法
        int a = 10;
        // a.  → 没方法

        Integer b = 10;
        System.out.println(b.toString());       // "10"
        System.out.println(Integer.toBinaryString(10)); // "1010"

        // 3. 基本类型不能为 null
        // int score = null; // ❌ 报错

        Integer score = null; // ✅ 可以
        System.out.println(score);
    }
}

// 装箱和拆箱
class BoxingDemo {
    public static void main(String[] args) {
        // ===== 手动装箱/拆箱 =====
        Integer num1 = Integer.valueOf(100); // 手动装箱
        int n1 = num1.intValue();            // 手动拆箱

        // ===== 自动装箱/拆箱（编译器帮忙） =====
        Integer num2 = 200;   // 自动装箱：Integer.valueOf(200)
        int n2 = num2;        // 自动拆箱：num2.intValue()

        // ===== 运算中自动拆箱 =====
        Integer a = 10;
        Integer b = 20;
        int sum = a + b; // 先拆箱成 int，再相加
        System.out.println(sum); // 30

        // ===== 比较中自动拆箱 =====
        System.out.println(a > b); // false
    }
}

// 包装类的常用方法
class WrapperMethods {
    public static void main(String[] args) {
        System.out.println("========== Integer 常用方法 ==========");

        // 1. 字符串 ↔ 整数
        int num = Integer.parseInt("123");   // String → int
        Integer num2 = Integer.valueOf("456"); // String → Integer
        String str = Integer.toString(789);   // int → String

        System.out.println("parseInt: " + num);
        System.out.println("valueOf: " + num2);
        System.out.println("toString: " + str);

        // 2. 进制转换
        System.out.println("10的二进制: " + Integer.toBinaryString(10));
        System.out.println("10的八进制: " + Integer.toOctalString(10));
        System.out.println("10的十六进制: " + Integer.toHexString(10));

        // 3. 常量
        System.out.println("int最大值: " + Integer.MAX_VALUE);
        System.out.println("int最小值: " + Integer.MIN_VALUE);

        // 4. 比较
        System.out.println("compare(10,20): " + Integer.compare(10, 20)); // -1

        System.out.println("\n========== Character 常用方法 ==========");
        System.out.println("isDigit('5'): " + Character.isDigit('5'));
        System.out.println("isLetter('A'): " + Character.isLetter('A'));
        System.out.println("isWhitespace(' '): " + Character.isWhitespace(' '));
        System.out.println("toUpperCase('a'): " + Character.toUpperCase('a'));
    }
}

// 缓存机制
class CacheDemo {
    public static void main(String[] args) {
        System.out.println("========== 缓存机制 ==========");

        // 在缓存范围内，复用同一个对象
        Integer a = 100;
        Integer b = 100;
        System.out.println("100 == 100: " + (a == b)); // true

        // 超出缓存范围，创建新对象
        Integer c = 200;
        Integer d = 200;
        System.out.println("200 == 200: " + (c == d)); // false

        // ✅ 正确比较：用 equals()
        System.out.println("200.equals(200): " + c.equals(d)); // true

        // 原因解释
        System.out.println("\n原因：");
        System.out.println("  Integer a = 100; 实际是 Integer.valueOf(100)");
        System.out.println("  valueOf() 在 -128~127 范围内会返回缓存对象");
        System.out.println("  超出范围则 new 一个新对象");
    }
}

// 常见陷阱
class WrapperPitfalls {
    public static void main(String[] args) {
        System.out.println("========== 陷阱1：== 比较 ==========");
        Integer a = 127;
        Integer b = 127;
        System.out.println("127 == 127: " + (a == b)); // true（缓存）

        Integer c = 128;
        Integer d = 128;
        System.out.println("128 == 128: " + (c == d)); // false
        System.out.println("✅ 用 equals: " + c.equals(d)); // true

        System.out.println("\n========== 陷阱2：null 拆箱 ==========");
        Integer num = null;
        try {
            int n = num; // 自动拆箱 → NullPointerException
        } catch (NullPointerException e) {
            System.out.println("❌ 报错: " + e);
        }
        // ✅ 正确做法：先判空
        if (num != null) {
            int n = num;
        }

        System.out.println("\n========== 陷阱3：性能问题 ==========");
        // ❌ 循环中使用包装类累加，频繁装箱拆箱
        Integer sum = 0;
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            sum += i;
        }
        long end = System.currentTimeMillis();
        System.out.println("包装类累加耗时: " + (end - start) + "ms");

        // ✅ 使用基本类型
        int sum2 = 0;
        start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            sum2 += i;
        }
        end = System.currentTimeMillis();
        System.out.println("基本类型累加耗时: " + (end - start) + "ms");
    }
}

// 实际应用场景
class WrapperUsage {
    public static void main(String[] args) {
        System.out.println("========== 场景1：集合中使用 ==========");
        List<Integer> scores = new ArrayList<>();
        scores.add(95);
        scores.add(88);
        scores.add(92);

        int total = 0;
        for (Integer s : scores) {
            total += s; // 自动拆箱
        }
        System.out.println("总分: " + total);

        System.out.println("\n========== 场景2：数据库 null 值 ==========");
        // 模拟从数据库读取，年龄可能为 null
        Integer age = null; // 数据库里没填
        if (age == null) {
            System.out.println("年龄为空，使用默认值 0");
            age = 0;
        }
        System.out.println("年龄: " + age);

        System.out.println("\n========== 场景3：字符串转数字 ==========");
        String userInput = "25";
        int age2 = Integer.parseInt(userInput);
        System.out.println("用户年龄: " + age2);

        // 处理异常
        try {
            int n = Integer.parseInt("abc");
        } catch (NumberFormatException e) {
            System.out.println("❌ 格式错误: " + e.getMessage());
        }

        System.out.println("\n========== 场景4：泛型中使用 ==========");
        // 泛型必须用包装类
        Map<String, Integer> scoreMap = new HashMap<>();
        scoreMap.put("张三", 95);
        scoreMap.put("李四", 88);
        System.out.println("成绩表: " + scoreMap);
    }
}