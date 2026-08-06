import java.util.TreeSet;
import java.util.Set;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Comparator;
import java.util.Objects;

/**
 * TreeSet
 * Set 接口的实现类，基于 TreeMap 实现
 *
 * 继承关系：
 * java.lang.Object
 *   └── java.util.AbstractCollection<E>
 *         └── java.util.AbstractSet<E>
 *               └── java.util.TreeSet<E>
 *
 * 实现接口：
 * - Set<E>                  → 无序、不可重复集合
 * - NavigableSet<E>         → 扩展的导航接口（提供 first/lower/ceiling 等方法）
 * - SortedSet<E>            → 排序集合（提供 first/last/headSet/subSet/tailSet）
 * - Cloneable               → 支持克隆（浅拷贝）
 * - java.io.Serializable    → 支持序列化
 *
 * 核心特点：
 * - 不可重复：不允许存储重复元素（通过 compareTo/compare 判断）
 * - 有序：按照元素的自然顺序或自定义顺序排序
 * - 不允许 null：不能存储 null 值
 * - 非线程安全：多线程环境下需要额外同步
 * - 性能：基本操作 O(log n)
 *
 * 底层数据结构：
 * - 基于 TreeMap（红黑树）
 * - 红黑树：自平衡的二叉搜索树
 * - 元素按照 key 的顺序存储
 *
 * 核心方法（Set + 导航方法）：
 *
 * 【Set 基本操作】
 * - add      → 添加元素（重复返回 false）
 * - remove   → 删除指定元素
 * - contains → 判断是否包含
 * - size     → 获取元素个数
 * - isEmpty  → 判断是否为空
 * - clear    → 清空
 * - iterator → 获取迭代器（升序）
 *
 * 【导航方法】
 * - first              → 获取第一个（最小）元素
 * - last               → 获取最后一个（最大）元素
 * - lower              → 获取严格小于指定元素的最大元素
 * - floor              → 获取小于等于指定元素的最大元素
 * - higher             → 获取严格大于指定元素的最小元素
 * - ceiling            → 获取大于等于指定元素的最小元素
 * - pollFirst          → 获取并删除第一个元素
 * - pollLast           → 获取并删除最后一个元素
 * - descendingSet      → 返回降序视图
 * - descendingIterator → 降序迭代器
 *
 * 【范围视图】
 * - subSet          → 获取子集 [from, to)
 * - headSet         → 获取头部子集 [first, to)
 * - tailSet         → 获取尾部子集 [from, last]
 *
 * 排序方式：
 * - 自然排序：元素实现 Comparable 接口
 * - 自定义排序：传入 Comparator
 *
 * 适用场景：
 * - 需要排序的去重集合 ⭐⭐⭐⭐⭐
 * - 需要范围查询 ⭐⭐⭐⭐⭐
 * - 需要获取最大/最小元素 ⭐⭐⭐⭐
 * - 需要有序遍历 ⭐⭐⭐⭐
 *
 * 与 HashSet/LinkedHashSet 对比：
 * - HashSet：无序，O(1)
 * - LinkedHashSet：插入顺序，O(1)
 * - TreeSet：排序，O(log n)
 */
public class TreeSet_ {

    public static void main(String[] args) {

        // ========== 1. 创建 TreeSet ==========
        System.out.println("========== 1. 创建 TreeSet ==========");

        // 方式1：自然排序（元素必须实现 Comparable）
        Set<Integer> set1 = new TreeSet<>();
        System.out.println("自然排序：" + set1);

        // 方式2：自定义比较器
        Set<Integer> set2 = new TreeSet<>((a, b) -> b - a);
        System.out.println("自定义比较器（降序）：" + set2);

        // 方式3：使用 Comparator.reverseOrder()
        Set<Integer> set3 = new TreeSet<>(Comparator.reverseOrder());
        System.out.println("Comparator.reverseOrder()：" + set3);

        // 方式4：从其他集合创建
        Set<Integer> set4 = new TreeSet<>(Arrays.asList(5, 2, 8, 1, 9, 3, 2, 5));
        System.out.println("从集合创建（自动排序+去重）：" + set4);

        // ========== 2. 基本操作 ==========
        System.out.println("\n========== 2. 基本操作 ==========");

        TreeSet<String> set = new TreeSet<>();

        // add → 添加元素（重复返回 false）
        set.add("Banana");
        set.add("Apple");
        set.add("Orange");
        set.add("Apple");  // 重复
        set.add("Grape");
        System.out.println("添加后（自动排序）：" + set);

        // size → 元素个数
        System.out.println("元素个数：" + set.size());

        // contains → 判断是否包含
        System.out.println("是否包含 'Apple'：" + set.contains("Apple"));
        System.out.println("是否包含 'Watermelon'：" + set.contains("Watermelon"));

        // remove → 删除元素
        boolean removed = set.remove("Orange");
        System.out.println("删除 'Orange'：" + removed + "，剩余：" + set);

        // first → 获取第一个（最小）元素
        System.out.println("第一个元素：" + set.first());

        // last → 获取最后一个（最大）元素
        System.out.println("最后一个元素：" + set.last());

        // ========== 3. 导航方法 ==========
        System.out.println("\n========== 3. 导航方法 ==========");

        TreeSet<Integer> navSet = new TreeSet<>(Arrays.asList(1, 3, 5, 7, 9, 11, 13, 15));

        System.out.println("集合：" + navSet);

        // lower → 严格小于指定元素的最大元素
        System.out.println("lower(7)：" + navSet.lower(7));   // 5
        System.out.println("lower(1)：" + navSet.lower(1));   // null

        // floor → 小于等于指定元素的最大元素
        System.out.println("floor(7)：" + navSet.floor(7));   // 7
        System.out.println("floor(8)：" + navSet.floor(8));   // 7

        // higher → 严格大于指定元素的最小元素
        System.out.println("higher(7)：" + navSet.higher(7)); // 9
        System.out.println("higher(15)：" + navSet.higher(15)); // null

        // ceiling → 大于等于指定元素的最小元素
        System.out.println("ceiling(7)：" + navSet.ceiling(7)); // 7
        System.out.println("ceiling(8)：" + navSet.ceiling(8)); // 9

        // pollFirst → 获取并删除第一个元素
        Integer first = navSet.pollFirst();
        System.out.println("pollFirst：" + first + "，剩余：" + navSet);

        // pollLast → 获取并删除最后一个元素
        Integer last = navSet.pollLast();
        System.out.println("pollLast：" + last + "，剩余：" + navSet);

        // ========== 4. 范围视图 ==========
        System.out.println("\n========== 4. 范围视图 ==========");

        TreeSet<Integer> rangeSet = new TreeSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));

        System.out.println("原集合：" + rangeSet);

        // subSet → 获取子集 [from, to)
        Set<Integer> subSet = rangeSet.subSet(3, 8);
        System.out.println("subSet(3, 8)：" + subSet);  // [3, 8)

        // headSet → 获取头部子集 [first, to)
        Set<Integer> headSet = rangeSet.headSet(5);
        System.out.println("headSet(5)：" + headSet);   // [1, 5)

        // tailSet → 获取尾部子集 [from, last]
        Set<Integer> tailSet = rangeSet.tailSet(6);
        System.out.println("tailSet(6)：" + tailSet);   // [6, 10]

        // ⚠️ 注意：子集是原集合的视图
        subSet.add(7);  // 在子集中添加
        System.out.println("在 subSet 中添加 7 后，原集合：" + rangeSet);

        // ========== 5. 降序操作 ==========
        System.out.println("\n========== 5. 降序操作 ==========");

        TreeSet<String> descSet = new TreeSet<>(Arrays.asList("A", "B", "C", "D", "E"));

        // descendingSet → 降序视图
        Set<String> descendingSet = descSet.descendingSet();
        System.out.println("原集合（升序）：" + descSet);
        System.out.println("降序视图：" + descendingSet);

        // descendingIterator → 降序迭代器
        System.out.print("降序迭代器：");
        Iterator<String> descIt = descSet.descendingIterator();
        while (descIt.hasNext()) {
            System.out.print(descIt.next() + " ");
        }
        System.out.println();

        // ========== 6. 自定义排序 ==========
        System.out.println("\n========== 6. 自定义排序 ==========");

        // 6.1 使用 Comparator（Lambda）
        TreeSet<String> customSet = new TreeSet<>((s1, s2) -> s2.length() - s1.length());
        customSet.add("Apple");
        customSet.add("Banana");
        customSet.add("Orange");
        customSet.add("Grape");
        System.out.println("按字符串长度降序：" + customSet);

        // 6.2 自定义对象排序（实现 Comparable）
        TreeSet<Student> students = new TreeSet<>();

        students.add(new Student("张三", 20, 90));
        students.add(new Student("李四", 22, 85));
        students.add(new Student("王五", 19, 95));
        students.add(new Student("赵六", 21, 88));
        students.add(new Student("张三", 20, 90));  // 重复

        System.out.println("\n学生（按成绩降序）：");
        for (Student s : students) {
            System.out.println("  " + s);
        }

        // 6.3 使用外部 Comparator
        TreeSet<Student> studentsByAge = new TreeSet<>(new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return s1.age - s2.age;  // 按年龄升序
            }
        });

        studentsByAge.addAll(students);
        System.out.println("\n学生（按年龄升序）：");
        for (Student s : studentsByAge) {
            System.out.println("  " + s);
        }

        // ========== 7. 遍历方式 ==========
        System.out.println("\n========== 7. 遍历方式 ==========");

        TreeSet<Integer> traverseSet = new TreeSet<>(Arrays.asList(5, 2, 8, 1, 9, 3));

        // 方式1：增强 for（升序）
        System.out.print("方式1（增强 for 升序）：");
        for (Integer num : traverseSet) {
            System.out.print(num + " ");
        }
        System.out.println();

        // 方式2：Iterator（升序）
        System.out.print("方式2（Iterator 升序）：");
        Iterator<Integer> it = traverseSet.iterator();
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }
        System.out.println();

        // 方式3：descendingIterator（降序）
        System.out.print("方式3（descendingIterator 降序）：");
        Iterator<Integer> descIt2 = traverseSet.descendingIterator();
        while (descIt2.hasNext()) {
            System.out.print(descIt2.next() + " ");
        }
        System.out.println();

        // 方式4：forEach
        System.out.print("方式4（forEach）：");
        traverseSet.forEach(num -> System.out.print(num + " "));
        System.out.println();

        // ========== 8. 实际应用场景 ==========
        System.out.println("\n========== 8. 实际应用场景 ==========");

        // 场景1：获取 Top N
        System.out.println("--- 场景1：获取 Top 3 成绩 ---");
        TreeSet<Integer> scores = new TreeSet<>(Arrays.asList(85, 92, 78, 95, 88, 90, 82, 96));
        System.out.println("所有成绩：" + scores);

        // 获取最高的 3 个
        System.out.print("Top 3 成绩：");
        int count = 0;
        Iterator<Integer> descScoreIt = scores.descendingIterator();
        while (descScoreIt.hasNext() && count < 3) {
            System.out.print(descScoreIt.next() + " ");
            count++;
        }
        System.out.println();

        // 场景2：范围查询
        System.out.println("\n--- 场景2：范围查询 ---");
        TreeSet<Integer> ages = new TreeSet<>(Arrays.asList(18, 22, 25, 28, 30, 35, 40, 45, 50));

        System.out.println("所有年龄：" + ages);
        System.out.println("25-40 岁：" + ages.subSet(25, 41));
        System.out.println("30 岁以下：" + ages.headSet(30));
        System.out.println("35 岁以上：" + ages.tailSet(35));

        // 场景3：好友列表（按名字排序）
        System.out.println("\n--- 场景3：好友列表（按名字排序）---");
        TreeSet<String> friends = new TreeSet<>();
        friends.add("Alice");
        friends.add("Bob");
        friends.add("Charlie");
        friends.add("David");
        friends.add("Eve");

        System.out.println("好友列表：" + friends);

        // 场景4：时间轴排序
        System.out.println("\n--- 场景4：时间轴排序 ---");
        TreeSet<String> events = new TreeSet<>();
        events.add("2024-01-15: 项目启动");
        events.add("2024-02-20: 第一阶段完成");
        events.add("2024-01-01: 年度计划");
        events.add("2024-03-10: 第二阶段开始");
        events.add("2024-02-01: 需求评审");

        System.out.println("事件时间轴：");
        for (String event : events) {
            System.out.println("  " + event);
        }

        // ========== 9. 性能测试 ==========
        System.out.println("\n========== 9. 性能测试 ==========");

        int testSize = 100000;

        // HashSet 插入
        Set<Integer> hashSet = new java.util.HashSet<>();
        long start = System.nanoTime();
        for (int i = 0; i < testSize; i++) {
            hashSet.add(i);
        }
        long end = System.nanoTime();
        System.out.println("HashSet 插入 " + testSize + " 个元素：" + (end - start) / 1000000 + "ms");

        // TreeSet 插入
        TreeSet<Integer> treeSet = new TreeSet<>();
        start = System.nanoTime();
        for (int i = 0; i < testSize; i++) {
            treeSet.add(i);
        }
        end = System.nanoTime();
        System.out.println("TreeSet 插入 " + testSize + " 个元素：" + (end - start) / 1000000 + "ms");

        System.out.println("✅ HashSet O(1) vs TreeSet O(log n)");

        // ========== 10. 注意事项 ==========
        System.out.println("\n========== 10. 注意事项 ==========");

        // ⚠️ 注意1：元素必须可比较
        // class Person2 { String name; }
        // TreeSet<Person2> invalid = new TreeSet<>();
        // invalid.add(new Person2()); // ❌ ClassCastException
        System.out.println("⚠️ TreeSet 要求元素实现 Comparable 或传入 Comparator");

        // ⚠️ 注意2：不允许 null
        TreeSet<String> nullTest = new TreeSet<>();
        // nullTest.add(null); // ❌ NullPointerException
        System.out.println("⚠️ TreeSet 不允许存储 null 值");

        // ⚠️ 注意3：重复判断依赖 compareTo/compare
        System.out.println("⚠️ TreeSet 使用 compareTo/compare 判断重复，不是 equals");
        System.out.println("   所以 compareTo 返回 0 时视为重复");

        // ⚠️ 注意4：不是线程安全
        System.out.println("⚠️ TreeSet 不是线程安全的");
        System.out.println("   解决方案：Collections.synchronizedSet(new TreeSet<>())");

        // ⚠️ 注意5：子集修改会影响原集合
        TreeSet<Integer> subTest = new TreeSet<>(Arrays.asList(1, 2, 3, 4, 5));
        Set<Integer> sub = subTest.subSet(2, 5);
        sub.add(4);
        System.out.println("⚠️ 子集修改影响原集合：" + subTest);

        // ⚠️ 注意6：修改元素会影响排序
        System.out.println("⚠️ 如果修改了 TreeSet 中的元素，排序不会自动更新");
        System.out.println("   需要删除后重新插入");

        // ========== 11. 三种 Set 对比总结 ==========
        System.out.println("\n========== 11. 三种 Set 对比总结 ==========");

        System.out.println("┌─────────────┬─────────────┬─────────────────┬─────────────┐");
        System.out.println("│   特性      │  HashSet    │  LinkedHashSet  │  TreeSet    │");
        System.out.println("├─────────────┼─────────────┼─────────────────┼─────────────┤");
        System.out.println("│ 底层结构    │  HashMap    │  LinkedHashMap  │  TreeMap    │");
        System.out.println("│ 顺序        │  无序       │  插入顺序       │  排序       │");
        System.out.println("│ 时间复杂度  │  O(1)       │  O(1)           │  O(log n)   │");
        System.out.println("│ null        │  允许       │  允许           │  不允许     │");
        System.out.println("│ 线程安全    │  否         │  否             │  否         │");
        System.out.println("│ 适用场景    │  一般去重   │  去重+保留顺序  │  去重+排序  │");
        System.out.println("└─────────────┴─────────────┴─────────────────┴─────────────┘");
    }

    // ========== 内部类 ==========

    /**
     * Student 类（实现 Comparable）
     */
    static class Student implements Comparable<Student> {
        private String name;
        private int age;
        private int score;

        public Student(String name, int age, int score) {
            this.name = name;
            this.age = age;
            this.score = score;
        }

        @Override
        public int compareTo(Student other) {
            // 按成绩降序排序
            if (this.score != other.score) {
                return other.score - this.score;  // 成绩高的在前
            }
            // 成绩相同按姓名排序
            return this.name.compareTo(other.name);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Student student = (Student) o;
            return age == student.age &&
                    score == student.score &&
                    Objects.equals(name, student.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, age, score);
        }

        @Override
        public String toString() {
            return name + "（年龄：" + age + "，成绩：" + score + "）";
        }
    }
}