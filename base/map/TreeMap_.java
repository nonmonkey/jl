import java.util.TreeMap;
import java.util.Map;
import java.util.Set;
import java.util.Collection;
import java.util.Map.Entry;
import java.util.Iterator;
import java.util.Comparator;
import java.util.NavigableMap;
import java.util.SortedMap;
import java.util.Objects;

/**
 * TreeMap
 * Map 接口的实现类，基于红黑树实现
 *
 * 继承关系
 * java.lang.Object
 *    └── java.util.AbstractMap<K,V>
 *          └── java.util.TreeMap<K,V>
 *
 * 核心特点：
 * - 键值对存储：每个键映射到一个值
 * - 键不可重复：键（Key）是唯一的（通过 compareTo/compare 判断）
 * - 有序：按照键的自然顺序或自定义顺序排序
 * - 不允许 null 键：不能存储 null 键（但 null 值可以）
 * - 非线程安全：多线程环境下需要额外同步
 * - 性能：基本操作 O(log n)
 *
 * 底层数据结构：
 * - 红黑树（自平衡的二叉搜索树）
 * - 左子树 < 根节点 < 右子树
 * - 红黑树保证查询、插入、删除的时间复杂度为 O(log n)
 *
 * 核心方法：
 *
 * 【Map 基本操作】
 * - put          → 添加键值对（键存在则覆盖）
 * - get          → 根据键获取值
 * - remove       → 根据键删除键值对
 * - containsKey  → 判断是否包含某个键
 * - containsValue → 判断是否包含某个值
 * - size         → 获取键值对个数
 * - isEmpty      → 判断是否为空
 * - clear        → 清空
 *
 * 【导航方法】
 * - firstKey         → 获取第一个（最小）键
 * - lastKey          → 获取最后一个（最大）键
 * - lowerKey         → 获取严格小于指定键的最大键
 * - floorKey         → 获取小于等于指定键的最大键
 * - higherKey        → 获取严格大于指定键的最小键
 * - ceilingKey       → 获取大于等于指定键的最小键
 * - firstEntry       → 获取第一个键值对
 * - lastEntry        → 获取最后一个键值对
 * - lowerEntry       → 获取严格小于指定键的最大键值对
 * - floorEntry       → 获取小于等于指定键的最大键值对
 * - higherEntry      → 获取严格大于指定键的最小键值对
 * - ceilingEntry     → 获取大于等于指定键的最小键值对
 * - pollFirstEntry   → 获取并删除最小的一个键值对
 * - pollLastEntry    → 获取并删除最大的一个键值对
 *
 * 【范围视图】
 * - subMap           → 获取子映射 [from, to)
 * - headMap          → 获取头部子映射 [first, to)
 * - tailMap          → 获取尾部子映射 [from, last]
 * - descendingMap    → 返回降序视图
 * - descendingKeySet → 返回降序键集
 *
 * 排序方式：
 * - 自然排序：键实现 Comparable 接口
 * - 自定义排序：传入 Comparator
 *
 * 适用场景：
 * - 需要排序的键值对存储 ⭐⭐⭐⭐⭐
 * - 需要范围查询 ⭐⭐⭐⭐⭐
 * - 需要获取最大/最小键 ⭐⭐⭐⭐
 * - 需要有序遍历 ⭐⭐⭐⭐
 *
 * 与 HashMap/LinkedHashMap 对比：
 * - HashMap：无序，O(1)
 * - LinkedHashMap：插入顺序，O(1)
 * - TreeMap：排序，O(log n)
 */
public class TreeMap_ {

    public static void main(String[] args) {

        // ========== 1. 创建 TreeMap ==========
        System.out.println("========== 1. 创建 TreeMap ==========");

        // 方式1：自然排序（键必须实现 Comparable）
        Map<String, Integer> map1 = new TreeMap<>();
        System.out.println("自然排序：" + map1);

        // 方式2：自定义比较器（降序）
        Map<String, Integer> map2 = new TreeMap<>((a, b) -> b.compareTo(a));
        System.out.println("自定义比较器（降序）：" + map2);

        // 方式3：使用 Comparator.reverseOrder()
        Map<String, Integer> map3 = new TreeMap<>(Comparator.reverseOrder());
        System.out.println("Comparator.reverseOrder()：" + map3);

        // 方式4：从其他 Map 创建
        Map<String, Integer> map4 = new TreeMap<>(Map.of("B", 2, "A", 1, "C", 3, "D", 4));
        System.out.println("从其他 Map 创建（自动排序）：" + map4);

        // ========== 2. 基本操作 ==========
        System.out.println("\n========== 2. 基本操作 ==========");

        TreeMap<String, Integer> map = new TreeMap<>();

        // put → 添加键值对（自动排序）
        map.put("Banana", 3);
        map.put("Apple", 5);
        map.put("Orange", 2);
        map.put("Grape", 4);
        System.out.println("添加后（自动排序）：" + map);

        // put → 覆盖已存在的键
        map.put("Apple", 8);
        System.out.println("修改 'Apple' 后：" + map);

        // get → 根据键获取值
        System.out.println("Apple 的值：" + map.get("Apple"));
        System.out.println("Watermelon（不存在）：" + map.get("Watermelon"));

        // size → 键值对个数
        System.out.println("键值对个数：" + map.size());

        // containsKey → 判断是否包含某个键
        System.out.println("是否包含 'Apple'：" + map.containsKey("Apple"));
        System.out.println("是否包含 'Watermelon'：" + map.containsKey("Watermelon"));

        // containsValue → 判断是否包含某个值
        System.out.println("是否包含 5：" + map.containsValue(5));
        System.out.println("是否包含 100：" + map.containsValue(100));

        // firstKey → 获取第一个（最小）键
        System.out.println("第一个键：" + map.firstKey());

        // lastKey → 获取最后一个（最大）键
        System.out.println("最后一个键：" + map.lastKey());

        // remove → 根据键删除
        map.remove("Orange");
        System.out.println("删除 'Orange' 后：" + map);

        // ========== 3. 导航方法 ==========
        System.out.println("\n========== 3. 导航方法 ==========");

        TreeMap<Integer, String> navMap = new TreeMap<>();
        navMap.put(1, "一");
        navMap.put(3, "三");
        navMap.put(5, "五");
        navMap.put(7, "七");
        navMap.put(9, "九");
        navMap.put(11, "十一");
        navMap.put(13, "十三");
        navMap.put(15, "十五");

        System.out.println("集合：" + navMap);

        // ---- 键的导航方法 ----
        // lowerKey → 严格小于指定键的最大键
        System.out.println("lowerKey(7)：" + navMap.lowerKey(7));   // 5
        System.out.println("lowerKey(1)：" + navMap.lowerKey(1));   // null

        // floorKey → 小于等于指定键的最大键
        System.out.println("floorKey(7)：" + navMap.floorKey(7));   // 7
        System.out.println("floorKey(8)：" + navMap.floorKey(8));   // 7

        // higherKey → 严格大于指定键的最小键
        System.out.println("higherKey(7)：" + navMap.higherKey(7)); // 9
        System.out.println("higherKey(15)：" + navMap.higherKey(15)); // null

        // ceilingKey → 大于等于指定键的最小键
        System.out.println("ceilingKey(7)：" + navMap.ceilingKey(7)); // 7
        System.out.println("ceilingKey(8)：" + navMap.ceilingKey(8)); // 9

        // ---- 键值对的导航方法 ----
        // firstEntry → 获取第一个键值对
        System.out.println("firstEntry：" + navMap.firstEntry());

        // lastEntry → 获取最后一个键值对
        System.out.println("lastEntry：" + navMap.lastEntry());

        // lowerEntry → 严格小于指定键的最大键值对
        System.out.println("lowerEntry(7)：" + navMap.lowerEntry(7));

        // floorEntry → 小于等于指定键的最大键值对
        System.out.println("floorEntry(7)：" + navMap.floorEntry(7));

        // higherEntry → 严格大于指定键的最小键值对
        System.out.println("higherEntry(7)：" + navMap.higherEntry(7));

        // ceilingEntry → 大于等于指定键的最小键值对
        System.out.println("ceilingEntry(7)：" + navMap.ceilingEntry(7));

        // ---- 删除并获取 ----
        // pollFirstEntry → 获取并删除第一个键值对
        Map.Entry<Integer, String> first = navMap.pollFirstEntry();
        System.out.println("pollFirstEntry：" + first + "，剩余：" + navMap);

        // pollLastEntry → 获取并删除最后一个键值对
        Map.Entry<Integer, String> last = navMap.pollLastEntry();
        System.out.println("pollLastEntry：" + last + "，剩余：" + navMap);

        // ========== 4. 范围视图 ==========
        System.out.println("\n========== 4. 范围视图 ==========");

        TreeMap<Integer, String> rangeMap = new TreeMap<>();
        for (int i = 1; i <= 10; i++) {
            rangeMap.put(i, "值" + i);
        }

        System.out.println("原集合：" + rangeMap);

        // subMap → 获取子映射 [from, to)
        SortedMap<Integer, String> subMap = rangeMap.subMap(3, 8);
        System.out.println("subMap(3, 8)：" + subMap);  // [3, 8)

        // headMap → 获取头部子映射 [first, to)
        SortedMap<Integer, String> headMap = rangeMap.headMap(5);
        // rangeMap.headMap(5, true);   // 包含 5
        System.out.println("headMap(5)：" + headMap);   // [1, 5)

        // tailMap → 获取尾部子映射 [from, last]
        SortedMap<Integer, String> tailMap = rangeMap.tailMap(6);
        // rangeMap.tailMap(6, false);  // 不包含 6
        System.out.println("tailMap(6)：" + tailMap);   // [6, 10]

        // ⚠️ 注意：子映射是原映射的视图
        subMap.put(4, "值4_修改");
        System.out.println("修改子映射后，原集合：" + rangeMap);
        System.out.println("⚠️ 子映射是原 Map 的视图，修改会相互影响");

        // ========== 5. 降序操作 ==========
        System.out.println("\n========== 5. 降序操作 ==========");

        TreeMap<String, Integer> descMap = new TreeMap<>();
        descMap.put("A", 1);
        descMap.put("B", 2);
        descMap.put("C", 3);
        descMap.put("D", 4);
        descMap.put("E", 5);

        System.out.println("原集合（升序）：" + descMap);

        // descendingMap → 降序视图
        NavigableMap<String, Integer> descendingMap = descMap.descendingMap();
        System.out.println("降序视图：" + descendingMap);

        // descendingKeySet → 降序键集
        System.out.println("降序键集：" + descMap.descendingKeySet());

        // 遍历降序
        System.out.print("降序遍历：");
        for (Map.Entry<String, Integer> entry : descMap.descendingMap().entrySet()) {
            System.out.print(entry.getKey() + "=" + entry.getValue() + " ");
        }
        System.out.println();

        // ========== 6. 自定义排序 ==========
        System.out.println("\n========== 6. 自定义排序 ==========");

        // 6.1 使用 Comparator（Lambda）
        TreeMap<String, Integer> customMap = new TreeMap<>(
                (s1, s2) -> s2.length() - s1.length()
        );
        customMap.put("Apple", 5);
        customMap.put("Banana", 3);
        customMap.put("Orange", 2);
        customMap.put("Grape", 4);
        System.out.println("按字符串长度降序：" + customMap);

        // 6.2 自定义对象排序（实现 Comparable）
        TreeMap<Student, String> studentMap = new TreeMap<>();

        studentMap.put(new Student("张三", 20, 90), "优秀");
        studentMap.put(new Student("李四", 22, 85), "良好");
        studentMap.put(new Student("王五", 19, 95), "优秀");
        studentMap.put(new Student("赵六", 21, 88), "良好");
        studentMap.put(new Student("张三", 20, 90), "优秀");  // 重复

        System.out.println("\n学生 Map（按成绩降序）：");
        for (Map.Entry<Student, String> entry : studentMap.entrySet()) {
            System.out.println("  " + entry.getKey() + " → " + entry.getValue());
        }

        // 6.3 使用外部 Comparator
        TreeMap<Student, String> studentsByAge = new TreeMap<>(
                new Comparator<Student>() {
                    @Override
                    public int compare(Student s1, Student s2) {
                        return s1.age - s2.age;  // 按年龄升序
                    }
                }
        );

        studentsByAge.put(new Student("张三", 20, 90), "优秀");
        studentsByAge.put(new Student("李四", 22, 85), "良好");
        studentsByAge.put(new Student("王五", 19, 95), "优秀");

        System.out.println("\n学生 Map（按年龄升序）：");
        for (Map.Entry<Student, String> entry : studentsByAge.entrySet()) {
            System.out.println("  " + entry.getKey() + " → " + entry.getValue());
        }

        // ========== 7. 遍历方式 ==========
        System.out.println("\n========== 7. 遍历方式 ==========");

        TreeMap<Integer, String> traverseMap = new TreeMap<>();
        traverseMap.put(5, "五");
        traverseMap.put(2, "二");
        traverseMap.put(8, "八");
        traverseMap.put(1, "一");
        traverseMap.put(9, "九");

        // 方式1：entrySet 增强 for（升序）
        System.out.print("方式1（entrySet 升序）：");
        for (Map.Entry<Integer, String> entry : traverseMap.entrySet()) {
            System.out.print(entry.getKey() + "=" + entry.getValue() + " ");
        }
        System.out.println();

        // 方式2：keySet 遍历
        System.out.print("方式2（keySet）：");
        for (Integer key : traverseMap.keySet()) {
            System.out.print(key + "=" + traverseMap.get(key) + " ");
        }
        System.out.println();

        // 方式3：forEach
        System.out.print("方式3（forEach）：");
        traverseMap.forEach((key, value) -> System.out.print(key + "=" + value + " "));
        System.out.println();

        // 方式4：降序遍历
        System.out.print("方式4（降序）：");
        for (Map.Entry<Integer, String> entry : traverseMap.descendingMap().entrySet()) {
            System.out.print(entry.getKey() + "=" + entry.getValue() + " ");
        }
        System.out.println();

        // ========== 8. compute 和 merge ==========
        System.out.println("\n========== 8. compute 和 merge ==========");

        TreeMap<String, Integer> computeMap = new TreeMap<>();
        computeMap.put("A", 10);
        computeMap.put("B", 20);

        System.out.println("原 map：" + computeMap);

        // compute → 计算新值
        computeMap.compute("A", (key, val) -> val + 5);
        System.out.println("compute('A', val -> val + 5)：" + computeMap);

        computeMap.computeIfAbsent("C", key -> 30);
        System.out.println("computeIfAbsent('C', key -> 30)：" + computeMap);

        // merge → 合并值
        computeMap.merge("B", 5, Integer::sum);
        System.out.println("merge('B', 5, Integer::sum)：" + computeMap);

        // ========== 9. 实际应用场景 ==========
        System.out.println("\n========== 9. 实际应用场景 ==========");

        // 场景1：学生成绩排名
        System.out.println("--- 场景1：学生成绩排名 ---");
        TreeMap<Integer, String> scoreRanking = new TreeMap<>(Comparator.reverseOrder());
        scoreRanking.put(95, "王五");
        scoreRanking.put(90, "张三");
        scoreRanking.put(88, "赵六");
        scoreRanking.put(85, "李四");

        System.out.println("成绩排名（从高到低）：");
        for (Map.Entry<Integer, String> entry : scoreRanking.entrySet()) {
            System.out.println("  " + entry.getValue() + "：" + entry.getKey() + "分");
        }

        // 场景2：范围查询（按日期范围）
        System.out.println("\n--- 场景2：按日期范围查询 ---");
        TreeMap<String, String> events = new TreeMap<>();
        events.put("2024-01-01", "元旦");
        events.put("2024-02-14", "情人节");
        events.put("2024-05-01", "劳动节");
        events.put("2024-06-01", "儿童节");
        events.put("2024-10-01", "国庆节");
        events.put("2024-12-25", "圣诞节");

        System.out.println("所有节日：");
        for (Map.Entry<String, String> entry : events.entrySet()) {
            System.out.println("  " + entry.getKey() + " → " + entry.getValue());
        }

        System.out.println("\n2024年上半年的节日（1月1日 ~ 6月30日）：");
        SortedMap<String, String> halfYear = events.subMap("2024-01-01", "2024-07-01");
        for (Map.Entry<String, String> entry : halfYear.entrySet()) {
            System.out.println("  " + entry.getKey() + " → " + entry.getValue());
        }

        // 场景3：最近邻查找
        System.out.println("\n--- 场景3：最近邻查找 ---");
        TreeMap<Double, String> prices = new TreeMap<>();
        prices.put(10.5, "商品A");
        prices.put(15.2, "商品B");
        prices.put(20.0, "商品C");
        prices.put(25.8, "商品D");
        prices.put(30.1, "商品E");

        double targetPrice = 22.0;
        Double floorPrice = prices.floorKey(targetPrice);
        Double ceilingPrice = prices.ceilingKey(targetPrice);

        System.out.println("目标价格：" + targetPrice);
        System.out.println("不超过目标价格的最接近商品：" + floorPrice + " → " + prices.get(floorPrice));
        System.out.println("不低于目标价格的最接近商品：" + ceilingPrice + " → " + prices.get(ceilingPrice));

        // 场景4：统计区间数据
        System.out.println("\n--- 场景4：统计区间数据 ---");
        TreeMap<Integer, Integer> ageDistribution = new TreeMap<>();
        ageDistribution.put(18, 15);
        ageDistribution.put(22, 20);
        ageDistribution.put(25, 18);
        ageDistribution.put(28, 22);
        ageDistribution.put(30, 12);
        ageDistribution.put(35, 8);
        ageDistribution.put(40, 5);

        // 统计 25-35 岁的人数
        SortedMap<Integer, Integer> ageRange = ageDistribution.subMap(25, 36);
        int total = 0;
        for (int count : ageRange.values()) {
            total += count;
        }
        System.out.println("年龄分布：" + ageDistribution);
        System.out.println("25-35 岁人数：" + total);

        // ========== 10. 性能测试 ==========
        System.out.println("\n========== 10. 性能测试 ==========");

        int testSize = 100000;

        // HashMap 插入
        Map<Integer, Integer> hashMapPerf = new java.util.HashMap<>();
        long start = System.nanoTime();
        for (int i = 0; i < testSize; i++) {
            hashMapPerf.put(i, i * 2);
        }
        long end = System.nanoTime();
        System.out.println("HashMap 插入 " + testSize + " 个键值对：" + (end - start) / 1000000 + "ms");

        // TreeMap 插入
        Map<Integer, Integer> treeMapPerf = new TreeMap<>();
        start = System.nanoTime();
        for (int i = 0; i < testSize; i++) {
            treeMapPerf.put(i, i * 2);
        }
        end = System.nanoTime();
        System.out.println("TreeMap 插入 " + testSize + " 个键值对：" + (end - start) / 1000000 + "ms");

        System.out.println("✅ TreeMap O(log n) vs HashMap O(1)");

        // ========== 11. 注意事项 ==========
        System.out.println("\n========== 11. 注意事项 ==========");

        // ⚠️ 注意1：键必须可比较
        // class Person { String name; }
        // TreeMap<Person, String> invalid = new TreeMap<>();
        // invalid.put(new Person(), "value"); // ❌ ClassCastException
        System.out.println("⚠️ TreeMap 要求键实现 Comparable 或传入 Comparator");

        // ⚠️ 注意2：不允许 null 键
        TreeMap<String, String> nullTest = new TreeMap<>();
        // nullTest.put(null, "value"); // ❌ NullPointerException
        System.out.println("⚠️ TreeMap 不允许 null 键");

        // ⚠️ 注意3：允许 null 值
        TreeMap<String, String> nullValueTest = new TreeMap<>();
        nullValueTest.put("key", null);
        System.out.println("✅ TreeMap 允许 null 值：" + nullValueTest);

        // ⚠️ 注意4：重复判断依赖 compareTo/compare
        System.out.println("⚠️ TreeMap 使用 compareTo/compare 判断重复，不是 equals");
        System.out.println("   所以 compareTo 返回 0 时视为重复键");

        // ⚠️ 注意5：不是线程安全
        System.out.println("⚠️ TreeMap 不是线程安全的");
        System.out.println("   解决方案：Collections.synchronizedMap(new TreeMap<>())");

        // ⚠️ 注意6：子映射修改会影响原映射
        TreeMap<Integer, String> subTest = new TreeMap<>();
        for (int i = 1; i <= 5; i++) {
            subTest.put(i, "值" + i);
        }
        SortedMap<Integer, String> sub = subTest.subMap(2, 5);
        sub.put(4, "值4_修改");
        System.out.println("⚠️ 子映射修改影响原映射：" + subTest);

        // ⚠️ 注意7：修改键会影响排序
        System.out.println("⚠️ 如果修改了 TreeMap 中的键，排序不会自动更新");
        System.out.println("   需要删除后重新插入");

        // ========== 12. 三种 Map 对比总结 ==========
        System.out.println("\n========== 12. 三种 Map 对比总结 ==========");

        System.out.println("┌──────────────┬─────────────┬─────────────────┬─────────────┐");
        System.out.println("│   特性       │  HashMap    │  LinkedHashMap  │  TreeMap    │");
        System.out.println("├──────────────┼─────────────┼─────────────────┼─────────────┤");
        System.out.println("│ 底层结构     │  哈希表     │  哈希表+链表    │  红黑树     │");
        System.out.println("│ 顺序         │  无序       │  插入/访问顺序  │  排序       │");
        System.out.println("│ 时间复杂度   │  O(1)       │  O(1)           │  O(log n)   │");
        System.out.println("│ null 键      │  允许       │  允许           │  不允许     │");
        System.out.println("│ null 值      │  允许       │  允许           │  允许       │");
        System.out.println("│ 导航方法     │  ❌          │  ❌              │  ✅          │");
        System.out.println("│ 范围查询     │  ❌          │  ❌              │  ✅          │");
        System.out.println("│ 降序操作     │  ❌          │  ❌              │  ✅          │");
        System.out.println("│ 线程安全     │  否         │  否             │  否         │");
        System.out.println("│ 适用场景     │  一般存储   │  保持顺序/LRU   │  排序/范围  │");
        System.out.println("└──────────────┴─────────────┴─────────────────┴─────────────┘");
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