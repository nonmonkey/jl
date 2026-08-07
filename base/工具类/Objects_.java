package base.工具类;

import java.util.Objects;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Objects 工具类
 * Java 7 引入的工具类，提供操作对象（Object）的静态方法
 *
 * 核心目的：
 * - 安全处理 null：避免 NullPointerException
 * - 简化 equals / hashCode 实现
 * - 提供对象比较、判空等常用操作
 *
 * 核心方法：
 *
 * 【判空】
 * - requireNonNull()      → 检查对象是否为 null，为 null 抛异常
 * - requireNonNullElse()  → 检查对象是否为 null，为 null 返回默认值（Java 9）
 * - requireNonNullElseGet() → 检查对象是否为 null，为 null 执行 Supplier（Java 9）
 * - isNull()              → 判断是否为 null（Java 8）
 * - nonNull()             → 判断是否不为 null（Java 8）
 *
 * 【比较】
 * - equals()              → 安全比较两个对象（处理 null）
 * - deepEquals()          → 深度比较（数组比较内容）
 * - compare()             → 安全比较（使用 Comparator）
 *
 * 【哈希】
 * - hashCode()            → 获取对象的 hashCode（处理 null）
 * - hash()                → 计算多个值的 hashCode
 *
 * 【字符串】
 * - toString()            → 安全转字符串（处理 null）
 * - toString()            → 为 null 返回默认值（重载）
 *
 * 适用场景：
 * - equals 和 hashCode 的重写 ⭐⭐⭐⭐⭐
 * - 方法参数校验 ⭐⭐⭐⭐⭐
 * - 防御式编程（避免 NPE） ⭐⭐⭐⭐⭐
 *
 * 注意事项：
 * - 所有方法都是静态方法
 * - 处理 null 安全，不会抛 NPE
 */
public class Objects_ {

    public static void main(String[] args) {

        // ========== 1. 判空方法 ==========
        System.out.println("========== 1. 判空方法 ==========");

        String str = "Hello";
        String nullStr = null;

        // isNull() → 判断是否为 null（Java 8）
        System.out.println("Objects.isNull(str)：" + Objects.isNull(str));           // false
        System.out.println("Objects.isNull(nullStr)：" + Objects.isNull(nullStr));   // true

        // nonNull() → 判断是否不为 null（Java 8）
        System.out.println("Objects.nonNull(str)：" + Objects.nonNull(str));         // true
        System.out.println("Objects.nonNull(nullStr)：" + Objects.nonNull(nullStr)); // false

        // ========== 2. requireNonNull（参数校验） ==========
        System.out.println("\n========== 2. requireNonNull（参数校验） ==========");

        // requireNonNull() → 为 null 抛出 NullPointerException
        System.out.println("requireNonNull(str)：" + Objects.requireNonNull(str));

        try {
            Objects.requireNonNull(nullStr);
        } catch (NullPointerException e) {
            System.out.println("requireNonNull(null) 抛出：" + e);
        }

        // requireNonNull(T obj, String message) → 自定义异常信息
        try {
            Objects.requireNonNull(nullStr, "参数不能为 null");
        } catch (NullPointerException e) {
            System.out.println("requireNonNull 自定义信息：" + e.getMessage());
        }

        // requireNonNull(T obj, Supplier<String> messageSupplier) → 懒加载异常信息（Java 8）
        try {
            Objects.requireNonNull(nullStr, () -> "参数不能为 null，调用时间：" + System.currentTimeMillis());
        } catch (NullPointerException e) {
            System.out.println("requireNonNull 懒加载异常：" + e.getMessage());
        }

        // ========== 3. requireNonNullElse（null 时返回默认值，Java 9） ==========
        System.out.println("\n========== 3. requireNonNullElse（Java 9） ==========");

        // requireNonNullElse() → 为 null 返回默认值
        String result1 = Objects.requireNonNullElse(str, "默认值");
        String result2 = Objects.requireNonNullElse(nullStr, "默认值");
        System.out.println("requireNonNullElse(str, '默认值')：" + result1);
        System.out.println("requireNonNullElse(nullStr, '默认值')：" + result2);

        // requireNonNullElseGet() → 为 null 执行 Supplier 并返回（懒加载）
        String result3 = Objects.requireNonNullElseGet(nullStr, () -> {
            System.out.println("  > Supplier 被执行（懒加载）");
            return "生成的默认值";
        });
        System.out.println("requireNonNullElseGet(nullStr, Supplier)：" + result3);

        // 非 null 时不执行 Supplier
        String result4 = Objects.requireNonNullElseGet(str, () -> {
            System.out.println("  > 非 null 时不会执行");
            return "不会执行";
        });
        System.out.println("requireNonNullElseGet(str, Supplier)：" + result4);

        // ========== 4. equals（安全比较） ==========
        System.out.println("\n========== 4. equals（安全比较） ==========");

        String a = "Hello";
        String b = "Hello";
        String c = "World";
        String d = null;

        // Objects.equals() → 安全比较（处理 null）
        System.out.println("Objects.equals(a, b)：" + Objects.equals(a, b));   // true
        System.out.println("Objects.equals(a, c)：" + Objects.equals(a, c));   // false
        System.out.println("Objects.equals(a, d)：" + Objects.equals(a, d));   // false
        System.out.println("Objects.equals(d, d)：" + Objects.equals(d, d));   // true
        System.out.println("Objects.equals(null, null)：" + Objects.equals(null, null)); // true

        // 对比传统方式
        // a.equals(b)  // 如果 a 为 null 会抛 NPE
        // Objects.equals(a, b)  // 安全

        // ========== 5. deepEquals（深度比较） ==========
        System.out.println("\n========== 5. deepEquals（深度比较） ==========");

        // deepEquals() → 深度比较（数组比较内容）
        int[] arr1 = {1, 2, 3};
        int[] arr2 = {1, 2, 3};
        int[] arr3 = {4, 5, 6};

        System.out.println("Objects.equals(arr1, arr2)：" + Objects.equals(arr1, arr2));     // false（比较引用）
        System.out.println("Objects.deepEquals(arr1, arr2)：" + Objects.deepEquals(arr1, arr2)); // true（比较内容）
        System.out.println("Objects.deepEquals(arr1, arr3)：" + Objects.deepEquals(arr1, arr3)); // false

        // 嵌套数组
        int[][] nested1 = {{1, 2}, {3, 4}};
        int[][] nested2 = {{1, 2}, {3, 4}};

        System.out.println("嵌套数组 deepEquals：" + Objects.deepEquals(nested1, nested2)); // true

        // deepEquals 处理 null
        System.out.println("Objects.deepEquals(null, null)：" + Objects.deepEquals(null, null)); // true
        System.out.println("Objects.deepEquals(arr1, null)：" + Objects.deepEquals(arr1, null)); // false

        // ========== 6. compare（安全比较） ==========
        System.out.println("\n========== 6. compare（安全比较） ==========");

        // compare() → 使用 Comparator 安全比较（处理 null）
        Comparator<String> cmp = String::compareTo;
        // 等价于 Lambda 写法
        // Comparator<String> cmp = (s1, s2) -> s1.compareTo(s2);
        // 等价于匿名内部类
        /*
            Comparator<String> cmp = new Comparator<String>() {
                @Override
                public int compare(String s1, String s2) {
                    return s1.compareTo(s2);
                }
            };
        */
        System.out.println("Objects.compare(\"A\", \"B\", cmp)：" + Objects.compare("A", "B", cmp)); // -1
        System.out.println("Objects.compare(\"B\", \"A\", cmp)：" + Objects.compare("B", "A", cmp)); // 1
        System.out.println("Objects.compare(\"A\", \"A\", cmp)：" + Objects.compare("A", "A", cmp)); // 0

        // 处理 null：Comparator 需要自己处理 null
        // 可以使用 Comparator.nullsFirst() 或 Comparator.nullsLast()
        Comparator<String> nullSafeCmp = Comparator.nullsFirst(String::compareTo);
        System.out.println("Objects.compare(null, \"A\", nullSafeCmp)：" + Objects.compare(null, "A", nullSafeCmp)); // -1
        System.out.println("Objects.compare(\"A\", null, nullSafeCmp)：" + Objects.compare("A", null, nullSafeCmp)); // 1

        // ========== 7. hashCode 方法 ==========
        System.out.println("\n========== 7. hashCode 方法 ==========");

        String hStr = "Hello";
        String hStr2 = null;

        // hashCode() → 获取对象的 hashCode（处理 null）
        System.out.println("Objects.hashCode(hStr)：" + Objects.hashCode(hStr));     // 正常 hashCode
        System.out.println("Objects.hashCode(hStr2)：" + Objects.hashCode(hStr2));   // 0

        // hashCode() 简化 equals + hashCode 实现
        Person p1 = new Person("张三", 20);
        Person p2 = new Person("张三", 20);
        Person p3 = new Person("李四", 25);

        System.out.println("p1.equals(p2)：" + p1.equals(p2));   // true（使用 Objects.equals）
        System.out.println("p1.equals(p3)：" + p1.equals(p3));   // false
        System.out.println("p1.hashCode()：" + p1.hashCode());   // 使用 Objects.hash()

        // hash() → 计算多个值的 hashCode
        // 等价于 Objects.hash(name, age)
        System.out.println("Objects.hash(\"张三\", 20)：" + Objects.hash("张三", 20));

        // ========== 8. toString 方法 ==========
        System.out.println("\n========== 8. toString 方法 ==========");

        String tStr = "Hello";
        String tStr2 = null;

        // toString() → 安全转字符串（处理 null）
        System.out.println("Objects.toString(tStr)：" + Objects.toString(tStr));     // Hello
        System.out.println("Objects.toString(tStr2)：" + Objects.toString(tStr2));   // null（是 "null" 字符串）

        // toString(T obj, String nullDefault) → 为 null 返回默认值
        System.out.println("Objects.toString(tStr, \"默认\")：" + Objects.toString(tStr, "默认"));
        System.out.println("Objects.toString(tStr2, \"默认\")：" + Objects.toString(tStr2, "默认"));

        // ========== 9. 实际应用场景 ==========
        System.out.println("\n========== 9. 实际应用场景 ==========");

        // 场景1：方法参数校验
        System.out.println("--- 场景1：方法参数校验 ---");
        try {
            processUser(null);
        } catch (NullPointerException e) {
            System.out.println("参数校验失败：" + e.getMessage());
        }

        // 场景2：重写 equals 和 hashCode
        System.out.println("\n--- 场景2：重写 equals 和 hashCode ---");
        Product product1 = new Product("iPhone", 6999.0);
        Product product2 = new Product("iPhone", 6999.0);
        Product product3 = new Product("MacBook", 9999.0);

        System.out.println("product1.equals(product2)：" + product1.equals(product2)); // true
        System.out.println("product1.equals(product3)：" + product1.equals(product3)); // false
        System.out.println("product1.hashCode()：" + product1.hashCode());
        System.out.println("product2.hashCode()：" + product2.hashCode());

        // 场景3：集合判空处理
        System.out.println("\n--- 场景3：集合判空处理 ---");
        List<String> list1 = new ArrayList<>(Arrays.asList("A", "B", "C"));
        List<String> list2 = null;

        // 安全获取 size
        int size1 = Objects.toString(list1, "[]").length();
        int size2 = Objects.toString(list2, "[]").length();
        System.out.println("list1 size：" + (list1 != null ? list1.size() : 0));
        System.out.println("list2 size：" + (list2 != null ? list2.size() : 0));

        // 使用 Objects 简写
        System.out.println("Objects.toString(list1)：" + Objects.toString(list1));
        System.out.println("Objects.toString(list2, \"空列表\")：" + Objects.toString(list2, "空列表"));

        // 场景4：条件判空过滤
        System.out.println("\n--- 场景4：条件判空过滤 ---");
        List<String> names = Arrays.asList("张三", null, "李四", null, "王五");

        long count = names.stream()
                .filter(Objects::nonNull)   // 过滤 null
                .count();
        System.out.println("非空元素个数：" + count);

        // ========== 10. 注意事项 ==========
        System.out.println("\n========== 10. 注意事项 ==========");

        // ⚠️ 注意1：requireNonNull 抛出的是 NullPointerException
        System.out.println("⚠️ requireNonNull 抛出 NullPointerException，不是 IllegalArgumentException");

        // ⚠️ 注意2：Objects.equals 与 Object.equals 的区别
        System.out.println("⚠️ Objects.equals(null, null) 返回 true");
        System.out.println("   Object.equals(null, null) 会抛 NPE");

        // ⚠️ 注意3：deepEquals 用于数组深度比较
        System.out.println("⚠️ deepEquals 可以比较嵌套数组，equals 只比较引用");

        // ⚠️ 注意4：hashCode(null) 返回 0
        System.out.println("⚠️ Objects.hashCode(null) 返回 0，不会抛异常");

        // ⚠️ 注意5：requireNonNullElse 是 Java 9 引入
        System.out.println("⚠️ requireNonNullElse 和 requireNonNullElseGet 需要 Java 9+");
    }

    // ========== 辅助方法 ==========

    private static void processUser(User user) {
        // 参数校验
        Objects.requireNonNull(user, "用户不能为 null");
        System.out.println("处理用户：" + user.getName());
    }

    // ========== 内部类 ==========

    /**
     * Person 类 - 演示如何用 Objects 重写 equals 和 hashCode
     */
    static class Person {
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Person person = (Person) o;
            // 使用 Objects.equals() 安全比较
            return age == person.age &&
                    Objects.equals(name, person.name);
        }

        @Override
        public int hashCode() {
            // 使用 Objects.hash() 计算多个字段的 hashCode
            return Objects.hash(name, age);
        }

        @Override
        public String toString() {
            return "Person{name='" + name + "', age=" + age + "}";
        }
    }

    /**
     * Product 类 - 演示另一个例子
     */
    static class Product {
        private String name;
        private double price;

        public Product(String name, double price) {
            this.name = name;
            this.price = price;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Product product = (Product) o;
            return Double.compare(product.price, price) == 0 &&
                    Objects.equals(name, product.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, price);
        }

        @Override
        public String toString() {
            return "Product{name='" + name + "', price=" + price + "}";
        }
    }

    /**
     * User 类 - 演示参数校验
     */
    static class User {
        private String name;

        public User(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}