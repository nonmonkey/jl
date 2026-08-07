package base.工具类;

import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;
import java.util.Collections;

/**
 * Comparator 比较器
 * Java 提供的函数式接口，用于定义自定义排序规则
 *
 * 核心特点：
 * - 函数式接口：可以使用 Lambda 表达式
 * - 用于排序：Collections.sort()、Arrays.sort()、TreeSet、TreeMap
 * - 灵活：可以定义多种排序规则
 * - 链式调用：支持 thenComparing、reversed 等
 *
 * 核心方法：
 *
 * 【抽象方法】
 * - compare()      → 比较两个对象（必须实现）
 *
 * 【默认方法】
 * - reversed()     → 返回反向比较器
 * - thenComparing() → 链式比较（次要排序）
 * - thenComparingInt() → 链式比较 int 类型
 * - thenComparingLong() → 链式比较 long 类型
 * - thenComparingDouble() → 链式比较 double 类型
 *
 * 【静态方法】
 * - naturalOrder()   → 自然顺序
 * - reverseOrder()   → 自然顺序的反向
 * - nullsFirst()     → null 值排在前面
 * - nullsLast()      → null 值排在后面
 * - comparing()      → 根据某个字段比较
 * - comparingInt()   → 根据 int 字段比较
 * - comparingLong()  → 根据 long 字段比较
 * - comparingDouble() → 根据 double 字段比较
 *
 * 与 Comparable 的区别：
 * - Comparable：内部比较器（类自身实现）
 * - Comparator：外部比较器（独立的比较器）
 *
 * 适用场景：
 * - 多种排序方式 ⭐⭐⭐⭐⭐
 * - 第三方类的排序 ⭐⭐⭐⭐⭐
 * - 链式排序 ⭐⭐⭐⭐
 * - null 值处理 ⭐⭐⭐⭐
 */
public class Comparator_ {

    public static void main(String[] args) {

        // ========== 1. 基本用法（多种实现方式） ==========
        System.out.println("========== 1. 基本用法（多种实现方式） ==========");

        List<Integer> numbers = new ArrayList<>(Arrays.asList(5, 2, 8, 1, 9, 3, 7));

        // 方式1：Lambda 表达式
        Comparator<Integer> asc1 = (a, b) -> a - b;
        Collections.sort(numbers, asc1);
        System.out.println("方式1（Lambda）：" + numbers);
        numbers = new ArrayList<>(Arrays.asList(5, 2, 8, 1, 9, 3, 7));

        // 方式2：方法引用
        Comparator<Integer> asc2 = Integer::compareTo;
        Collections.sort(numbers, asc2);
        System.out.println("方式2（方法引用）：" + numbers);
        numbers = new ArrayList<>(Arrays.asList(5, 2, 8, 1, 9, 3, 7));

        // 方式3：匿名内部类（Java 8 之前）
        Comparator<Integer> asc3 = new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return a - b;
            }
        };
        Collections.sort(numbers, asc3);
        System.out.println("方式3（匿名内部类）：" + numbers);
        numbers = new ArrayList<>(Arrays.asList(5, 2, 8, 1, 9, 3, 7));

        // 方式4：直接传入 Lambda（不声明变量）
        Collections.sort(numbers, (a, b) -> a - b);
        System.out.println("方式4（直接传入 Lambda）：" + numbers);
        numbers = new ArrayList<>(Arrays.asList(5, 2, 8, 1, 9, 3, 7));

        // 方式5：Comparator.naturalOrder()
        Comparator<Integer> asc5 = Comparator.naturalOrder();
        Collections.sort(numbers, asc5);
        System.out.println("方式5（naturalOrder）：" + numbers);
        numbers = new ArrayList<>(Arrays.asList(5, 2, 8, 1, 9, 3, 7));

        // 方式6：Comparator.reverseOrder()
        Comparator<Integer> desc1 = Comparator.reverseOrder();
        Collections.sort(numbers, desc1);
        System.out.println("方式6（reverseOrder 降序）：" + numbers);
        numbers = new ArrayList<>(Arrays.asList(5, 2, 8, 1, 9, 3, 7));

        // 方式7：Comparator.comparing()
        Comparator<Integer> asc7 = Comparator.comparing(i -> i);
        Collections.sort(numbers, asc7);
        System.out.println("方式7（comparing）：" + numbers);
        numbers = new ArrayList<>(Arrays.asList(5, 2, 8, 1, 9, 3, 7));

        // 方式8：Comparator.comparingInt()
        Comparator<Integer> asc8 = Comparator.comparingInt(i -> i);
        Collections.sort(numbers, asc8);
        System.out.println("方式8（comparingInt）：" + numbers);
        numbers = new ArrayList<>(Arrays.asList(5, 2, 8, 1, 9, 3, 7));

        // 方式9：自定义 Comparator 类
        Comparator<Integer> asc9 = new IntegerComparator();
        Collections.sort(numbers, asc9);
        System.out.println("方式9（自定义类）：" + numbers);
        numbers = new ArrayList<>(Arrays.asList(5, 2, 8, 1, 9, 3, 7));

        // 方式10：Lambda 降序
        Collections.sort(numbers, (a, b) -> b - a);
        System.out.println("方式10（Lambda 降序）：" + numbers);

        // ========== 2. 自然顺序 / 反向 ==========
        System.out.println("\n========== 2. 自然顺序 / 反向 ==========");

        List<String> names = new ArrayList<>(Arrays.asList("Banana", "Apple", "Cherry", "Date"));

        // naturalOrder() → 自然顺序
        Collections.sort(names, Comparator.naturalOrder());
        System.out.println("naturalOrder：" + names);

        // reverseOrder() → 自然顺序的反向
        Collections.sort(names, Comparator.reverseOrder());
        System.out.println("reverseOrder：" + names);

        // ========== 3. null 值处理 ==========
        System.out.println("\n========== 3. null 值处理 ==========");

        List<String> withNull = new ArrayList<>(Arrays.asList("Banana", null, "Apple", null, "Cherry"));

        // nullsFirst() → null 排在前面
        withNull.sort(Comparator.nullsFirst(Comparator.naturalOrder()));
        System.out.println("nullsFirst：" + withNull);

        // nullsLast() → null 排在后面
        withNull.sort(Comparator.nullsLast(Comparator.naturalOrder()));
        System.out.println("nullsLast：" + withNull);

        // 不处理 null 会抛异常
        try {
            withNull.sort(Comparator.naturalOrder());
        } catch (NullPointerException e) {
            System.out.println("⚠️ naturalOrder 遇到 null 会抛 NPE：" + e);
        }

        // ========== 4. comparing() 按字段排序 ==========
        System.out.println("\n========== 4. comparing() 按字段排序 ==========");

        List<Person> people = new ArrayList<>(Arrays.asList(
                new Person("张三", 25, 5000.0),
                new Person("李四", 30, 6000.0),
                new Person("王五", 22, 4500.0),
                new Person("赵六", 28, 5500.0),
                new Person("钱七", 25, 5200.0)
        ));

        // comparing() → 按年龄排序（升序）
        people.sort(Comparator.comparing(Person::getAge));
        System.out.println("按年龄升序：");
        people.forEach(p -> System.out.println("  " + p));

        // comparing() → 按姓名排序
        people.sort(Comparator.comparing(Person::getName));
        System.out.println("\n按姓名升序：");
        people.forEach(p -> System.out.println("  " + p));

        // comparingInt() → 按 int 字段排序
        people.sort(Comparator.comparingInt(Person::getAge));
        System.out.println("\ncomparingInt 按年龄升序：");
        people.forEach(p -> System.out.println("  " + p));

        // comparingDouble() → 按 double 字段排序
        people.sort(Comparator.comparingDouble(Person::getSalary));
        System.out.println("\ncomparingDouble 按工资升序：");
        people.forEach(p -> System.out.println("  " + p));

        // ========== 5. 链式排序 ==========
        System.out.println("\n========== 5. 链式排序 ==========");

        List<Person> people2 = new ArrayList<>(Arrays.asList(
                new Person("张三", 25, 5000.0),
                new Person("李四", 30, 6000.0),
                new Person("王五", 22, 4500.0),
                new Person("赵六", 28, 5500.0),
                new Person("钱七", 25, 5200.0),
                new Person("孙八", 25, 4800.0)
        ));

        // thenComparing() → 先按年龄升序，再按姓名升序
        people2.sort(Comparator
                .comparingInt(Person::getAge)
                .thenComparing(Person::getName));
        System.out.println("先按年龄升序，再按姓名升序：");
        people2.forEach(p -> System.out.println("  " + p));

        // 先按年龄降序，再按工资升序
        people2.sort(Comparator
                .comparingInt(Person::getAge)
                .reversed()
                .thenComparingDouble(Person::getSalary));
        System.out.println("\n先按年龄降序，再按工资升序：");
        people2.forEach(p -> System.out.println("  " + p));

        // ========== 6. 自定义对象排序 ==========
        System.out.println("\n========== 6. 自定义对象排序 ==========");

        // 6.1 使用 Comparator（不修改原类）
        List<Product> products = new ArrayList<>(Arrays.asList(
                new Product("iPhone", 6999.0, 10),
                new Product("MacBook", 9999.0, 5),
                new Product("iPad", 3999.0, 8),
                new Product("AirPods", 1299.0, 20)
        ));

        // 按价格排序
        products.sort(Comparator.comparingDouble(Product::getPrice));
        System.out.println("按价格升序：");
        products.forEach(p -> System.out.println("  " + p));

        // 按库存排序
        products.sort(Comparator.comparingInt(Product::getStock));
        System.out.println("\n按库存升序：");
        products.forEach(p -> System.out.println("  " + p));

        // 多条件：先按价格，再按库存
        products.sort(Comparator
                .comparingDouble(Product::getPrice)
                .thenComparingInt(Product::getStock));
        System.out.println("\n先按价格，再按库存：");
        products.forEach(p -> System.out.println("  " + p));

        // 6.2 Product 实现了 Comparable，但用 Comparator 覆盖
        products.sort((p1, p2) -> p2.getPrice().compareTo(p1.getPrice()));
        System.out.println("\nComparator 覆盖 Comparable（价格降序）：");
        products.forEach(p -> System.out.println("  " + p));

        // ========== 7. 与 Comparable 配合使用 ==========
        System.out.println("\n========== 7. 与 Comparable 配合使用 ==========");

        // Person2 实现了 Comparable（按年龄排序）
        List<Person2> people3 = new ArrayList<>(Arrays.asList(
                new Person2("张三", 25),
                new Person2("李四", 30),
                new Person2("王五", 22),
                new Person2("赵六", 28)
        ));

        // 默认排序（Comparable）
        Collections.sort(people3);
        System.out.println("Comparable 默认（按年龄升序）：");
        people3.forEach(p -> System.out.println("  " + p));

        // 使用 Comparator 覆盖（按姓名排序）
        people3.sort(Comparator.comparing(Person2::getName));
        System.out.println("\nComparator 覆盖（按姓名升序）：");
        people3.forEach(p -> System.out.println("  " + p));

        // ========== 8. 实际应用场景 ==========
        System.out.println("\n========== 8. 实际应用场景 ==========");

        // 场景1：成绩排行榜
        System.out.println("--- 场景1：成绩排行榜 ---");
        List<Score> scores = new ArrayList<>(Arrays.asList(
                new Score("张三", 95),
                new Score("李四", 88),
                new Score("王五", 92),
                new Score("赵六", 85),
                new Score("钱七", 90)
        ));

        // 按成绩降序
        scores.sort(Comparator
                .comparingInt(Score::getScore)
                .reversed());
        System.out.println("成绩排行榜（从高到低）：");
        for (int i = 0; i < scores.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + scores.get(i));
        }

        // 场景2：按多个字段排序（部门 + 工资）
        System.out.println("\n--- 场景2：多字段排序 ---");
        List<Employee> employees = new ArrayList<>(Arrays.asList(
                new Employee("张三", "技术部", 15000),
                new Employee("李四", "市场部", 12000),
                new Employee("王五", "技术部", 18000),
                new Employee("赵六", "市场部", 14000),
                new Employee("钱七", "人事部", 10000),
                new Employee("孙八", "技术部", 16000)
        ));

        // 先按部门，再按工资降序
        employees.sort(Comparator
                .comparing(Employee::getDepartment)
                .thenComparing(Employee::getSalary, Comparator.reverseOrder()));
        System.out.println("先按部门，再按工资降序：");
        for (Employee e : employees) {
            System.out.println("  " + e);
        }

        // 场景3：找最高工资
        System.out.println("\n--- 场景3：找最高工资 ---");
        Employee maxSalary = employees.stream()
                .max(Comparator.comparingDouble(Employee::getSalary))
                .orElse(null);
        System.out.println("最高工资员工：" + maxSalary);

        // 场景4：TreeSet 使用 Comparator
        System.out.println("\n--- 场景4：TreeSet 使用 Comparator ---");
        Set<Product> productSet = new TreeSet<>(
                Comparator.comparingDouble(Product::getPrice)
        );
        productSet.add(new Product("iPhone", 6999.0, 10));
        productSet.add(new Product("MacBook", 9999.0, 5));
        productSet.add(new Product("iPad", 3999.0, 8));
        productSet.add(new Product("AirPods", 1299.0, 20));

        System.out.println("TreeSet 按价格排序：");
        for (Product p : productSet) {
            System.out.println("  " + p);
        }

        // 场景5：comparing 链式查找
        System.out.println("\n--- 场景5：comparing 链式查找 ---");
        // 找工资最高的人
        Person richest = people.stream()
                .max(Comparator.comparingDouble(Person::getSalary))
                .orElse(null);
        System.out.println("工资最高的人：" + richest);

        // ========== 9. 注意事项 ==========
        System.out.println("\n========== 9. 注意事项 ==========");

        // ⚠️ 注意1：Comparator 与 Comparable 的关系
        System.out.println("⚠️ Comparator 可以覆盖 Comparable");
        System.out.println("   Comparable：类自身定义默认排序");
        System.out.println("   Comparator：外部定义灵活排序");

        // ⚠️ 注意2：返回值规则
        System.out.println("⚠️ compare 返回值：");
        System.out.println("   负数 → 第一个参数小于第二个");
        System.out.println("   零   → 相等");
        System.out.println("   正数 → 第一个参数大于第二个");

        // ⚠️ 注意3：链式排序顺序
        System.out.println("⚠️ thenComparing 按书写顺序执行");
        System.out.println("   先调用的先排序，后调用的作为次要排序");

        // ⚠️ 注意4：null 处理
        System.out.println("⚠️ 使用 nullsFirst/nullLast 处理 null 值");
        System.out.println("   否则会抛出 NullPointerException");

        // ⚠️ 注意5：Comparator 是函数式接口
        System.out.println("⚠️ Comparator 可以使用 Lambda 表达式");
        System.out.println("   (a, b) -> a.compareTo(b)");

        // ⚠️ 注意6：方法引用简化
        System.out.println("⚠️ 方法引用可以简化 Comparator");
        System.out.println("   Comparator.comparing(Person::getAge)");
        System.out.println("   代替 (p1, p2) -> p1.getAge() - p2.getAge()");

        // ========== 10. 总结对比 ==========
        System.out.println("\n========== 10. 总结对比 ==========");

        System.out.println("┌─────────────┬─────────────┬────────────────┐");
        System.out.println("│   方式      │  Comparable │   Comparator   │");
        System.out.println("├─────────────┼─────────────┼────────────────┤");
        System.out.println("│ 位置        │  类内部     │  外部独立      │");
        System.out.println("│ 修改原类    │  需要       │  不需要        │");
        System.out.println("│ 排序种类    │  单一       │  多种          │");
        System.out.println("│ 第三方类    │  不能       │  可以          │");
        System.out.println("│ Lambda      │  不支持     │  支持          │");
        System.out.println("│ 链式        │  不支持     │  支持          │");
        System.out.println("│ 方法        │  compareTo  │  compare       │");
        System.out.println("└─────────────┴─────────────┴────────────────┘");
    }

    // ========== 自定义 Comparator 类 ==========

    /**
     * 自定义 Integer 比较器
     */
    static class IntegerComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer a, Integer b) {
            return a - b;
        }
    }

    // ========== 内部类 ==========

    /**
     * Person 类（用于 Comparator 演示）
     */
    static class Person {
        private String name;
        private int age;
        private double salary;

        public Person(String name, int age, double salary) {
            this.name = name;
            this.age = age;
            this.salary = salary;
        }

        public String getName() { return name; }
        public int getAge() { return age; }
        public double getSalary() { return salary; }

        @Override
        public String toString() {
            return name + "（" + age + "岁，工资：" + salary + "）";
        }
    }

    /**
     * Person2 类（实现 Comparable）
     */
    static class Person2 implements Comparable<Person2> {
        private String name;
        private int age;

        public Person2(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() { return name; }
        public int getAge() { return age; }

        @Override
        public int compareTo(Person2 other) {
            return this.age - other.age;  // 按年龄升序
        }

        @Override
        public String toString() {
            return name + "（" + age + "岁）";
        }
    }

    /**
     * Product 类（实现 Comparable）
     */
    static class Product implements Comparable<Product> {
        private String name;
        private Double price;
        private int stock;

        public Product(String name, Double price, int stock) {
            this.name = name;
            this.price = price;
            this.stock = stock;
        }

        public String getName() { return name; }
        public Double getPrice() { return price; }
        public int getStock() { return stock; }

        @Override
        public int compareTo(Product other) {
            return this.price.compareTo(other.price);  // 按价格升序
        }

        @Override
        public String toString() {
            return name + "（价格：" + price + "，库存：" + stock + "）";
        }
    }

    /**
     * Score 类（成绩）
     */
    static class Score {
        private String name;
        private int score;

        public Score(String name, int score) {
            this.name = name;
            this.score = score;
        }

        public String getName() { return name; }
        public int getScore() { return score; }

        @Override
        public String toString() {
            return name + "：" + score + "分";
        }
    }

    /**
     * Employee 类（员工）
     */
    static class Employee {
        private String name;
        private String department;
        private double salary;

        public Employee(String name, String department, double salary) {
            this.name = name;
            this.department = department;
            this.salary = salary;
        }

        public String getName() { return name; }
        public String getDepartment() { return department; }
        public double getSalary() { return salary; }

        @Override
        public String toString() {
            return name + "（" + department + "，工资：" + salary + "）";
        }
    }
}