public class DataType_ {
    public static void main(String[] args) {

        // ========== 一、基本数据类型（8种）==========
        System.out.println("========== 基本数据类型 ==========");

        // 1. 整数类型（4种）
        byte b = 127;                    // 8位，范围：-128 ~ 127
        short s = 32767;                 // 16位，范围：-32768 ~ 32767
        int i = 2100000000;              // 32位，最常用
        long l = 9223372036854775807L;   // 64位，需要加 L 后缀

        System.out.println("byte: " + b);
        System.out.println("short: " + s);
        System.out.println("int: " + i);
        System.out.println("long: " + l);

        // 2. 浮点类型（2种）
        float f = 3.14159f;              // 32位，需要加 f 后缀
        double d = 3.141592653589793;    // 64位，默认浮点类型，最常用

        System.out.println("float: " + f);
        System.out.println("double: " + d);

        // 3. 字符类型（1种）
        char c = 'A';                    // 16位，用单引号，存储 Unicode 字符
        int ic = 'A';                    // 字母 “A” 的 Unicodde 编码是65

        System.out.println("char: " + c);
        System.out.println("int char: " + ic);

        // 4. 布尔类型（1种）
        boolean bool = true;             // 只有 true 和 false 两个值

        System.out.println("boolean: " + bool);

        // ========== 二、引用数据类型 ==========
        System.out.println("\n========== 引用数据类型 ==========");

        // 1. base.类（Class）包括 String、自定义类、包装类
        String str = "Hello Java";       // String 是 Java 提供的类
        DataType_ demo = new DataType_(); // 自定义类

        System.out.println("String: " + str);
        System.out.println("自定义类: " + demo);

        // 2. 数组（Array）
        int[] intArray = {1, 2, 3, 4, 5};   // 等同于 int[] intArray = new int[] {1, 2, 3, 4, 5};
        String[] stringArray = {"A", "B", "C"};

        System.out.println("数组 int[]: " + java.util.Arrays.toString(intArray));
        System.out.println("数组 String[]: " + java.util.Arrays.toString(stringArray));

        // 3. base.枚举（Enum）
        enum Day {
            MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
        }
        Day today = Day.MONDAY;

        System.out.println("base.枚举: " + today);

        // 4. base.接口（Interface）- 这里用匿名实现演示
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("接口实现");
            }
        };

        // ========== 三、特殊关键字：void ==========
        System.out.println("\n========== 特殊关键字 void ==========");

        DataType_.printHello();
    }

    // void 表示这个方法没有返回值
    static void printHello() {
        System.out.println("Hello");
    }
}
