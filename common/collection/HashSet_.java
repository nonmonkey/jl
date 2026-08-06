import java.util.HashSet;
import java.util.Set;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

/**
 * HashSet
 * Set 接口的实现类，基于 HashMap 实现
 *
 * 继承关系：
 * java.lang.Object
 *   └── java.util.AbstractCollection<E>
 *         └── java.util.AbstractSet<E>
 *               └── java.util.HashSet<E>
 *
 * 实现接口：
 * - Set<E>                  → 无序、不可重复集合
 * - Collection<E>           → 通过 AbstractCollection 间接实现
 * - Cloneable               → 支持克隆（浅拷贝）
 * - java.io.Serializable    → 支持序列化
 *
 * 核心特点：
 * - 不可重复：不允许存储重复元素（通过 equals 和 hashCode 判断）
 * - 无序：不保证元素的插入顺序
 * - 允许 null：可以存储一个 null 值
 * - 非线程安全：多线程环境下需要额外同步
 * - 性能优秀：基本操作 O(1)
 *
 * 底层数据结构：
 * - 基于 HashMap（数组 + 链表 + 红黑树）
 * - 默认初始容量：16
 * - 负载因子：0.75
 * - 扩容机制：当元素个数 > 容量 × 负载因子 时扩容（翻倍）
 *
 * 核心方法（实际调用 HashMap 的方法）：
 * - add      → 添加元素（重复返回 false）
 * - remove   → 删除指定元素
 * - contains → 判断是否包含（O(1)）
 * - size     → 获取元素个数
 * - isEmpty  → 判断是否为空
 * - clear    → 清空
 * - iterator → 获取迭代器
 * - toArray  → 转换为数组
 *
 * 工作原理：
 * 1. 添加元素时，先计算 hashCode()
 * 2. 根据 hashCode 找到对应的桶（数组索引）
 * 3. 如果桶为空，直接插入
 * 4. 如果桶不为空，用 equals() 比较是否已存在
 * 5. 存在则不插入，不存在则插入（链表/红黑树）
 *
 * 适用场景：
 * - 需要去重 ⭐⭐⭐⭐⭐
 * - 需要快速查找 ⭐⭐⭐⭐⭐
 * - 不需要保持顺序 ⭐⭐⭐⭐⭐
 * - 不需要索引访问 ⭐⭐⭐⭐⭐
 *
 * 注意事项：
 * - 自定义类必须重写 equals 和 hashCode
 * - 迭代器是 fail-fast 的
 * - 不是线程安全的
 */
public class HashSet_ {

    public static void main(String[] args) {

        // ========== 1. 创建 HashSet ==========
        System.out.println("========== 1. 创建 HashSet ==========");

        // 方式1：无参构造（默认容量16，负载因子0.75）
        Set<String> set1 = new HashSet<>();
        System.out.println("无参构造：" + set1);

        // 方式2：指定初始容量
        Set<String> set2 = new HashSet<>(100);
        System.out.println("指定容量100：" + set2);

        // 方式3：指定容量和负载因子
        Set<String> set3 = new HashSet<>(100, 0.8f);
        System.out.println("指定容量100，负载因子0.8：" + set3);

        // 方式4：从其他集合创建
        Set<String> set4 = new HashSet<>(Arrays.asList("A", "B", "C", "A", "B"));
        System.out.println("从集合创建（自动去重）：" + set4);

        // ========== 2. 基本操作 ==========
        System.out.println("\n========== 2. 基本操作 ==========");

        HashSet<String> set = new HashSet<>();

        // add → 添加元素（重复返回 false）
        System.out.println("添加 'Java'：" + set.add("Java"));
        System.out.println("添加 'Python'：" + set.add("Python"));
        System.out.println("添加 'Java'（重复）：" + set.add("Java"));
        System.out.println("添加 'Go'：" + set.add("Go"));
        System.out.println("集合：" + set);

        // size → 元素个数
        System.out.println("元素个数：" + set.size());

        // contains → 判断是否包含（O(1)）
        System.out.println("是否包含 'Java'：" + set.contains("Java"));
        System.out.println("是否包含 'C++'：" + set.contains("C++"));

        // remove → 删除元素
        boolean removed = set.remove("Python");
        System.out.println("删除 'Python'：" + removed + "，剩余：" + set);

        // remove → 删除不存在的元素
        boolean removed2 = set.remove("C++");
        System.out.println("删除 'C++'：" + removed2 + "，剩余：" + set);

        // isEmpty → 判断是否为空
        System.out.println("是否为空：" + set.isEmpty());

        // clear → 清空
        set.clear();
        System.out.println("清空后：" + set);
        System.out.println("是否为空：" + set.isEmpty());

        // ========== 3. 自动去重 ==========
        System.out.println("\n========== 3. 自动去重 ==========");

        HashSet<Integer> numbers = new HashSet<>();

        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(2);  // 重复
        numbers.add(4);
        numbers.add(1);  // 重复
        numbers.add(5);

        System.out.println("添加 1,2,3,2,4,1,5 后：" + numbers);
        System.out.println("元素个数：" + numbers.size());
        System.out.println("✅ HashSet 自动去重");

        // ========== 4. 遍历方式 ==========
        System.out.println("\n========== 4. 遍历方式 ==========");

        HashSet<String> traverseSet = new HashSet<>(Arrays.asList("A", "B", "C", "D", "E"));

        // 方式1：增强 for
        System.out.print("方式1（增强 for）：");
        for (String item : traverseSet) {
            System.out.print(item + " ");
        }
        System.out.println();

        // 方式2：Iterator
        System.out.print("方式2（Iterator）：");
        Iterator<String> it = traverseSet.iterator();
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }
        System.out.println();

        // 方式3：forEach
        System.out.print("方式3（forEach）：");
        traverseSet.forEach(item -> System.out.print(item + " "));
        System.out.println();

        // ========== 5. 批量操作 ==========
        System.out.println("\n========== 5. 批量操作 ==========");

        HashSet<String> batchSet = new HashSet<>();

        // addAll → 批量添加（自动去重）
        batchSet.addAll(Arrays.asList("A", "B", "C", "A", "D", "B", "E"));
        System.out.println("批量添加后：" + batchSet);

        // removeAll → 批量删除
        batchSet.removeAll(Arrays.asList("D", "E"));
        System.out.println("删除 D、E 后：" + batchSet);

        // retainAll → 只保留指定元素
        batchSet.retainAll(Arrays.asList("A", "B", "C", "X"));
        System.out.println("只保留 A、B、C、X 后：" + batchSet);

        // containsAll → 判断是否包含所有
        boolean containsAll = batchSet.containsAll(Arrays.asList("A", "B"));
        System.out.println("是否包含 A 和 B：" + containsAll);
        System.out.println("是否包含 A 和 X：" + batchSet.containsAll(Arrays.asList("A", "X")));

        // ========== 6. 自定义对象去重 ==========
        System.out.println("\n========== 6. 自定义对象去重 ==========");

        // Person 类正确重写了 equals 和 hashCode
        HashSet<Person> people = new HashSet<>();

        Person p1 = new Person("张三", 20);
        Person p2 = new Person("李四", 25);
        Person p3 = new Person("张三", 20);  // 与 p1 内容相同
        Person p4 = new Person("王五", 22);
        Person p5 = new Person("张三", 20);  // 与 p1 内容相同

        people.add(p1);
        people.add(p2);
        people.add(p3);
        people.add(p4);
        people.add(p5);

        System.out.println("添加 5 个 Person 对象后：");
        System.out.println("  元素个数：" + people.size());
        System.out.println("  集合内容：");
        for (Person p : people) {
            System.out.println("    " + p);
        }
        System.out.println("✅ 内容相同的 Person 被去重（依赖 equals 和 hashCode）");

        // 不重写 equals 和 hashCode 的类
        System.out.println("\n⚠️ 错误示例：不重写 equals 和 hashCode");
        HashSet<BadPerson> badPeople = new HashSet<>();

        BadPerson bp1 = new BadPerson("张三", 20);
        BadPerson bp2 = new BadPerson("张三", 20);  // 内容相同但视为不同对象

        badPeople.add(bp1);
        badPeople.add(bp2);

        System.out.println("添加两个内容相同的 BadPerson：");
        System.out.println("  元素个数：" + badPeople.size());  // 输出 2，没有去重
        System.out.println("⚠️ 没有重写 equals 和 hashCode 导致无法正确去重");

        // ========== 7. 实际应用场景 ==========
        System.out.println("\n========== 7. 实际应用场景 ==========");

        // 场景1：数据去重
        System.out.println("--- 场景1：数据去重 ---");
        String[] rawData = {"apple", "banana", "apple", "orange", "banana", "grape"};
        HashSet<String> uniqueData = new HashSet<>(Arrays.asList(rawData));
        System.out.println("原始数据：" + Arrays.toString(rawData));
        System.out.println("去重后：" + uniqueData);

        // 场景2：快速查找
        System.out.println("\n--- 场景2：快速查找 ---");
        HashSet<String> phoneBook = new HashSet<>();
        phoneBook.add("张三");
        phoneBook.add("李四");
        phoneBook.add("王五");
        phoneBook.add("赵六");

        String[] searchNames = {"张三", "钱七", "赵六"};
        for (String name : searchNames) {
            System.out.println("查找 '" + name + "'：" + (phoneBook.contains(name) ? "✅ 存在" : "❌ 不存在"));
        }

        // 场景3：判断重复
        System.out.println("\n--- 场景3：判断重复 ---");
        int[] nums = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5};
        HashSet<Integer> seen = new HashSet<>();

        System.out.print("数组 " + Arrays.toString(nums) + " 中重复的元素：");
        for (int num : nums) {
            if (!seen.add(num)) {
                System.out.print(num + " ");
            }
        }
        System.out.println();

        // 场景4：集合运算
        System.out.println("\n--- 场景4：集合运算 ---");
        HashSet<Integer> setA = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5));
        HashSet<Integer> setB = new HashSet<>(Arrays.asList(4, 5, 6, 7, 8));

        // 交集
        HashSet<Integer> intersection = new HashSet<>(setA);
        intersection.retainAll(setB);
        System.out.println("setA：" + setA);
        System.out.println("setB：" + setB);
        System.out.println("交集：" + intersection);

        // 并集
        HashSet<Integer> union = new HashSet<>(setA);
        union.addAll(setB);
        System.out.println("并集：" + union);

        // 差集
        HashSet<Integer> diff = new HashSet<>(setA);
        diff.removeAll(setB);
        System.out.println("差集（setA - setB）：" + diff);

        // ========== 8. 性能测试 ==========
        System.out.println("\n========== 8. 性能测试 ==========");

        int testSize = 100000;

        // HashSet 插入性能
        HashSet<Integer> perfSet = new HashSet<>();
        long start = System.nanoTime();
        for (int i = 0; i < testSize; i++) {
            perfSet.add(i);
        }
        long end = System.nanoTime();
        System.out.println("HashSet 插入 " + testSize + " 个元素耗时：" + (end - start) / 1000000 + "ms");

        // HashSet 查找性能
        start = System.nanoTime();
        for (int i = 0; i < testSize; i++) {
            perfSet.contains(i);
        }
        end = System.nanoTime();
        System.out.println("HashSet 查找 " + testSize + " 个元素耗时：" + (end - start) / 1000000 + "ms");

        System.out.println("✅ HashSet 基本操作 O(1)，性能优秀");

        // ========== 9. 注意事项 ==========
        System.out.println("\n========== 9. 注意事项 ==========");

        // ⚠️ 注意1：不保证顺序
        HashSet<Integer> orderSet = new HashSet<>();
        orderSet.add(3);
        orderSet.add(1);
        orderSet.add(4);
        orderSet.add(1);
        orderSet.add(5);
        orderSet.add(2);
        System.out.println("HashSet 不保证顺序：" + orderSet);

        // ⚠️ 注意2：必须重写 equals 和 hashCode
        System.out.println("⚠️ 自定义类作为元素时，必须重写 equals 和 hashCode");
        System.out.println("   否则 HashSet 无法正确去重");

        // ⚠️ 注意3：不是线程安全
        System.out.println("⚠️ HashSet 不是线程安全的");
        System.out.println("   解决方案1：Collections.synchronizedSet(new HashSet<>())");
        System.out.println("   解决方案2：ConcurrentHashMap_.java.newKeySet()（并发专用）");

        // ⚠️ 注意4：迭代器是 fail-fast
        HashSet<String> failFastSet = new HashSet<>(Arrays.asList("A", "B", "C"));
        try {
            Iterator<String> failIt = failFastSet.iterator();
            while (failIt.hasNext()) {
                String item = failIt.next();
                if (item.equals("B")) {
                    failFastSet.remove("C");  // 遍历时修改
                }
            }
        } catch (java.util.ConcurrentModificationException e) {
            System.out.println("⚠️ 遍历时修改集合抛出 ConcurrentModificationException");
        }

        // ✅ 正确删除方式
        HashSet<String> correctSet = new HashSet<>(Arrays.asList("A", "B", "C"));
        Iterator<String> correctIt = correctSet.iterator();
        while (correctIt.hasNext()) {
            if (correctIt.next().equals("B")) {
                correctIt.remove();  // 使用迭代器的 remove
            }
        }
        System.out.println("✅ 使用 Iterator.remove() 安全删除：" + correctSet);

        // ⚠️ 注意5：允许一个 null
        HashSet<String> nullSet = new HashSet<>();
        nullSet.add(null);
        nullSet.add("A");
        nullSet.add(null);
        System.out.println("HashSet 允许一个 null：" + nullSet);

        // ⚠️ 注意6：初始容量影响性能
        System.out.println("⚠️ 合理设置初始容量可以提升性能");
        System.out.println("   预期 10000 个元素：new HashSet<>(20000)");
        System.out.println("   避免频繁扩容");
    }

    // ========== 内部类 ==========

    /**
     * 正确重写 equals 和 hashCode 的 Person 类
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
            return age == person.age && Objects.equals(name, person.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, age);
        }

        @Override
        public String toString() {
            return "Person{name='" + name + "', age=" + age + "}";
        }
    }

    /**
     * 没有重写 equals 和 hashCode 的类（错误示例）
     */
    static class BadPerson {
        private String name;
        private int age;

        public BadPerson(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "BadPerson{name='" + name + "', age=" + age + "}";
        }
        // ⚠️ 没有重写 equals 和 hashCode
    }
}