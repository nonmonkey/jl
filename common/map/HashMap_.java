import java.util.*;
import java.util.Map.Entry;

/**
 * HashMap
 * Map 接口的实现类，基于哈希表实现
 *
 * 继承关系
 * java.lang.Object
 *    └── java.util.AbstractMap<K,V>
 *          └── java.util.HashMap<K,V>
 *
 * 核心特点：
 * - 键值对存储：每个键映射到一个值
 * - 键不可重复：键（Key）是唯一的（通过 equals 和 hashCode 判断）
 * - 无序：不保证键值对的顺序
 * - 允许 null：允许一个 null 键和多个 null 值
 * - 非线程安全：多线程环境下需要额外同步
 * - 性能优秀：基本操作 O(1)
 *
 * 底层数据结构：
 * - 数组 + 链表 + 红黑树（Java 8+）
 * - 默认初始容量：16
 * - 负载因子：0.75
 * - 扩容机制：当元素个数 > 容量 × 负载因子 时扩容（翻倍）
 * - 树化阈值：链表长度 ≥ 8 且数组长度 ≥ 64 时转为红黑树
 * - 链化阈值：红黑树节点 ≤ 6 时转为链表
 *
 * 核心方法：
 *
 * 【基本操作】
 * - put          → 添加键值对
 * - get          → 根据键获取值
 * - remove       → 根据键删除键值对
 * - containsKey  → 判断是否包含某个键
 * - containsValue → 判断是否包含某个值
 * - size         → 获取键值对个数
 * - isEmpty      → 判断是否为空
 * - clear        → 清空
 *
 * 【批量操作】
 * - putAll       → 批量添加
 *
 * 【视图操作】
 * - keySet       → 获取所有键的 Set 视图
 * - values       → 获取所有值的 Collection 视图
 * - entrySet     → 获取所有键值对的 Set 视图
 *
 * 【Java 8 新增】
 * - putIfAbsent  → 键不存在时才添加
 * - getOrDefault → 键不存在返回默认值
 * - replace      → 替换键的值
 * - compute      → 计算值
 * - computeIfAbsent  → 键不存在才计算
 * - computeIfPresent → 键存在才计算
 * - merge        → 合并值
 *
 * 工作原理：
 * 1. 计算键的 hashCode()
 * 2. 通过哈希函数计算数组索引
 * 3. 如果索引处为空，直接插入
 * 4. 如果索引处不为空，遍历链表/红黑树
 * 5. 通过 equals() 比较键是否存在
 * 6. 存在则覆盖值，不存在则插入
 *
 * 适用场景：
 * - 需要键值对存储 ⭐⭐⭐⭐⭐
 * - 需要通过键快速查找值 ⭐⭐⭐⭐⭐
 * - 不需要保持顺序 ⭐⭐⭐⭐⭐
 * - 需要去重（键唯一） ⭐⭐⭐⭐⭐
 *
 * 注意事项：
 * - 自定义类作为键必须重写 equals 和 hashCode
 * - 迭代器是 fail-fast 的
 * - 不是线程安全的
 */
public class HashMap_ {
    public static void main(String[] args) {

        // ========== 1. 创建 HashMap ==========
        System.out.println("========== 1. 创建 HashMap ==========");

        // 方式1：无参构造（默认容量16，负载因子0.75）
        Map<String, Integer> map1 = new HashMap<>();
        System.out.println("无参构造：" + map1);

        // 方式2：指定初始容量
        Map<String, Integer> map2 = new HashMap<>(100);
        System.out.println("指定容量100：" + map2);

        // 方式3：指定容量和负载因子
        Map<String, Integer> map3 = new HashMap<>(100, 0.8f);
        System.out.println("指定容量100，负载因子0.8：" + map3);

        // 方式4：从其他 Map 创建
        Map<String, Integer> map4 = new HashMap<>(Map.of("A", 1, "B", 2, "C", 3));
        System.out.println("从其他 Map 创建：" + map4);

        // ========== 2. 基本操作 ==========
        System.out.println("\n========== 2. 基本操作 ==========");

        HashMap<String, Integer> map = new HashMap<>();

        // put → 添加键值对
        map.put("语文", 90);
        map.put("数学", 95);
        map.put("英语", 88);
        System.out.println("添加后：" + map);

        // put → 覆盖已存在的键
        Integer oldValue = map.put("语文", 92);
        System.out.println("修改 '语文'，旧值：" + oldValue + "，新值：92");
        System.out.println("当前 map：" + map);

        // get → 根据键获取值
        System.out.println("语文成绩：" + map.get("语文"));
        System.out.println("物理成绩（不存在）：" + map.get("物理"));

        // size → 键值对个数
        System.out.println("键值对个数：" + map.size());

        // containsKey → 判断是否包含某个键
        System.out.println("是否包含 '数学'：" + map.containsKey("数学"));
        System.out.println("是否包含 '物理'：" + map.containsKey("物理"));

        // containsValue → 判断是否包含某个值
        System.out.println("是否包含 95 分：" + map.containsValue(95));
        System.out.println("是否包含 100 分：" + map.containsValue(100));

        // isEmpty → 判断是否为空
        System.out.println("是否为空：" + map.isEmpty());

        // remove → 根据键删除
        Integer removed = map.remove("英语");
        System.out.println("删除 '英语'，被删除的值：" + removed);
        System.out.println("删除后：" + map);

        // clear → 清空
        map.clear();
        System.out.println("清空后：" + map);
        System.out.println("是否为空：" + map.isEmpty());

        // ========== 3. putAll（批量添加） ==========
        System.out.println("\n========== 3. putAll（批量添加） ==========");

        HashMap<String, Integer> putAllMap = new HashMap<>();
        putAllMap.put("A", 1);
        putAllMap.put("B", 2);
        System.out.println("原 map：" + putAllMap);

        // 创建另一个 Map
        HashMap<String, Integer> sourceMap = new HashMap<>();
        sourceMap.put("B", 20);   // 覆盖
        sourceMap.put("C", 3);    // 新增
        sourceMap.put("D", 4);    // 新增
        System.out.println("源 map：" + sourceMap);

        // putAll → 批量添加（键存在则覆盖）
        putAllMap.putAll(sourceMap);
        System.out.println("putAll 后：" + putAllMap);
        System.out.println("✅ putAll 可以批量添加，键存在则覆盖");

        // ========== 4. putIfAbsent（键不存在才添加） ==========
        System.out.println("\n========== 4. putIfAbsent ==========");

        HashMap<String, String> absentMap = new HashMap<>();
        absentMap.put("A", "A1");
        absentMap.put("B", "B1");

        System.out.println("原 map：" + absentMap);

        // putIfAbsent → 键不存在时才添加
        String result1 = absentMap.putIfAbsent("A", "A2");
        System.out.println("putIfAbsent('A', 'A2') 返回：" + result1 + "（已存在，不覆盖）");

        String result2 = absentMap.putIfAbsent("C", "C1");
        System.out.println("putIfAbsent('C', 'C1') 返回：" + result2 + "（不存在，添加）");

        System.out.println("最终 map：" + absentMap);

        // ========== 5. getOrDefault（Java 8） ==========
        System.out.println("\n========== 5. getOrDefault ==========");

        HashMap<String, Integer> defaultMap = new HashMap<>();
        defaultMap.put("A", 1);
        defaultMap.put("B", 2);

        System.out.println("原 map：" + defaultMap);
        System.out.println("getOrDefault('A', 0)：" + defaultMap.getOrDefault("A", 0));
        System.out.println("getOrDefault('C', 0)：" + defaultMap.getOrDefault("C", 0));
        System.out.println("getOrDefault('D', 99)：" + defaultMap.getOrDefault("D", 99));

        // ========== 6. replace 方法 ==========
        System.out.println("\n========== 6. replace 方法 ==========");

        HashMap<String, Integer> replaceMap = new HashMap<>();
        replaceMap.put("A", 1);
        replaceMap.put("B", 2);
        replaceMap.put("C", 3);

        System.out.println("原 map：" + replaceMap);

        // replace(key, newValue) → 替换键的值
        Integer oldVal = replaceMap.replace("B", 20);
        System.out.println("replace('B', 20) 返回旧值：" + oldVal);
        System.out.println("replace('D', 40) 返回：" + replaceMap.replace("D", 40));  // null

        // replace(key, oldValue, newValue) → 只有匹配旧值才替换
        boolean replaced = replaceMap.replace("A", 1, 10);
        System.out.println("replace('A', 1, 10)：" + replaced);
        System.out.println("replace('B', 99, 99)：" + replaceMap.replace("B", 99, 99));

        System.out.println("最终 map：" + replaceMap);

        // ========== 7. compute 方法（Java 8） ==========
        System.out.println("\n========== 7. compute 方法 ==========");

        HashMap<String, Integer> computeMap = new HashMap<>();
        computeMap.put("A", 10);
        computeMap.put("B", 20);
        computeMap.put("C", null);  // 值为 null

        System.out.println("原 map：" + computeMap);

        // compute → 计算新值（键存在则计算，不存在也计算）
        computeMap.compute("A", (key, val) -> val + 5);
        System.out.println("compute('A', val -> val + 5)：" + computeMap);

        // 键存在但值为 null，val 为 null
        computeMap.compute("C", (key, val) -> val == null ? 100 : val + 10);
        System.out.println("compute('C', val -> val == null ? 100 : val + 10)：" + computeMap);

        // 键不存在，val 为 null
        computeMap.compute("D", (key, val) -> val == null ? 50 : val + 10);
        System.out.println("compute('D', val -> val == null ? 50 : val + 10)：" + computeMap);

        // computeIfAbsent → 键不存在或值为 null 时才计算
        computeMap.computeIfAbsent("E", key -> key.length() * 10);
        System.out.println("computeIfAbsent('E', key -> key.length() * 10)：" + computeMap);

        // 键已存在且值不为 null，不计算
        Integer result3 = computeMap.computeIfAbsent("A", key -> 999);
        System.out.println("computeIfAbsent('A', key -> 999) 返回：" + result3 + "（已存在，不计算）");

        // 键存在但值为 null，会计算
        computeMap.put("F", null);
        computeMap.computeIfAbsent("F", key -> 777);
        System.out.println("computeIfAbsent('F', key -> 777)：" + computeMap);

        // computeIfPresent → 键存在且值不为 null 时才计算
        computeMap.computeIfPresent("B", (key, val) -> val * 2);
        System.out.println("computeIfPresent('B', (key, val) -> val * 2)：" + computeMap);

        // 键不存在，不计算
        computeMap.computeIfPresent("Z", (key, val) -> 999);
        System.out.println("computeIfPresent('Z', ...)：" + computeMap);  // 不变

        // 键存在但值为 null，不计算
        computeMap.computeIfPresent("F", (key, val) -> 999);
        System.out.println("computeIfPresent('F', ...)：" + computeMap);  // 不变

        // ========== 8. merge 方法（Java 8） ==========
        System.out.println("\n========== 8. merge 方法 ==========");

        HashMap<String, Integer> mergeMap = new HashMap<>();
        mergeMap.put("A", 10);
        mergeMap.put("B", 20);

        System.out.println("原 map：" + mergeMap);

        // merge → 合并值（键存在则合并，不存在则直接插入）
        mergeMap.merge("A", 5, (oldVal2, newVal) -> oldVal2 + newVal);
        System.out.println("merge('A', 5, Integer::sum)：" + mergeMap);

        mergeMap.merge("C", 30, (oldVal2, newVal) -> oldVal2 + newVal);
        System.out.println("merge('C', 30, Integer::sum)：" + mergeMap);

        // 使用 merge 实现单词计数
        System.out.println("\n--- merge 实现单词计数 ---");
        HashMap<String, Integer> wordCount = new HashMap<>();
        String[] words = {"apple", "banana", "apple", "orange", "banana", "apple"};

        for (String word : words) {
            wordCount.merge(word, 1, Integer::sum);
        }
        System.out.println("单词计数：" + wordCount);

        // merge 删除键（返回 null）
        mergeMap.merge("A", 10, (oldVal2, newVal) -> null);
        System.out.println("merge('A', 10, (old, new) -> null) 删除键：" + mergeMap);

        // ========== 9. 获取视图 ==========
        System.out.println("\n========== 9. 获取视图 ==========");

        HashMap<String, Integer> viewMap = new HashMap<>();
        viewMap.put("语文", 90);
        viewMap.put("数学", 95);
        viewMap.put("英语", 88);
        viewMap.put("物理", 92);

        // keySet → 获取所有键的 Set
        Set<String> keys = viewMap.keySet();
        System.out.println("keys：" + keys);

        // values → 获取所有值的 Collection
        Collection<Integer> values = viewMap.values();
        System.out.println("values：" + values);

        // entrySet → 获取所有键值对的 Set
        Set<Entry<String, Integer>> entries = viewMap.entrySet();
        System.out.println("entries：" + entries);

        // ⚠️ 注意：视图随原集合变化
        viewMap.put("化学", 85);
        System.out.println("添加新键后，keys：" + keys);
        System.out.println("⚠️ 视图是原 Map 的映射，修改 Map 会影响视图");

        // ========== 10. 遍历 Map ==========
        System.out.println("\n========== 10. 遍历 Map ==========");

        HashMap<String, Integer> traverseMap = new HashMap<>();
        traverseMap.put("语文", 90);
        traverseMap.put("数学", 95);
        traverseMap.put("英语", 88);
        traverseMap.put("物理", 92);

        // 方式1：遍历 entrySet（增强 for）
        System.out.println("方式1（entrySet 增强 for）：");
        for (Entry<String, Integer> entry : traverseMap.entrySet()) {
            System.out.println("  " + entry.getKey() + " = " + entry.getValue());
        }

        // 方式2：遍历 entrySet（Iterator）
        System.out.println("方式2（entrySet Iterator）：");
        Iterator<Entry<String, Integer>> it = traverseMap.entrySet().iterator();
        while (it.hasNext()) {
            Entry<String, Integer> entry = it.next();
            System.out.println("  " + entry.getKey() + " = " + entry.getValue());
        }

        // 方式3：遍历 keySet（通过 key 获取 value）
        System.out.println("方式3（keySet）：");
        for (String key : traverseMap.keySet()) {
            System.out.println("  " + key + " = " + traverseMap.get(key));
        }

        // 方式4：forEach（Java 8）
        System.out.println("方式4（forEach）：");
        traverseMap.forEach((key, value) -> {
            System.out.println("  " + key + " = " + value);
        });

        // ========== 11. 自定义对象作为键 ==========
        System.out.println("\n========== 11. 自定义对象作为键 ==========");

        // Person 类正确重写了 equals 和 hashCode
        HashMap<Person, String> personMap = new HashMap<>();

        Person p1 = new Person("张三", 20);
        Person p2 = new Person("李四", 25);
        Person p3 = new Person("张三", 20);  // 与 p1 内容相同

        personMap.put(p1, "学生A");
        personMap.put(p2, "学生B");
        personMap.put(p3, "学生C");  // 覆盖 p1

        System.out.println("personMap 大小：" + personMap.size());
        System.out.println("personMap 内容：");
        for (Entry<Person, String> entry : personMap.entrySet()) {
            System.out.println("  " + entry.getKey() + " = " + entry.getValue());
        }
        System.out.println("✅ 内容相同的键被覆盖（依赖 equals 和 hashCode）");

        // ========== 12. 实际应用场景 ==========
        System.out.println("\n========== 12. 实际应用场景 ==========");

        // 场景1：单词计数（使用 merge）
        System.out.println("--- 场景1：单词计数（使用 merge） ---");
        HashMap<String, Integer> countMap = new HashMap<>();
        String[] textWords = {"hello", "world", "hello", "java", "world", "hello"};

        for (String word : textWords) {
            countMap.merge(word, 1, Integer::sum);
        }
        System.out.println("单词统计：" + countMap);

        // 场景2：分组统计
        System.out.println("\n--- 场景2：分组统计 ---");
        String[] items = {"苹果", "香蕉", "苹果", "橙子", "香蕉", "苹果"};
        HashMap<String, Integer> groupMap = new HashMap<>();

        for (String item : items) {
            groupMap.merge(item, 1, Integer::sum);
        }
        System.out.println("商品统计：" + groupMap);

        // 场景3：批量导入配置
        System.out.println("\n--- 场景3：批量导入配置 ---");
        HashMap<String, String> defaultConfig = new HashMap<>();
        defaultConfig.put("host", "localhost");
        defaultConfig.put("port", "8080");

        HashMap<String, String> userConfig = new HashMap<>();
        userConfig.put("port", "9090");    // 覆盖默认
        userConfig.put("timeout", "5000"); // 新增

        // 使用 putAll 合并配置
        HashMap<String, String> finalConfig = new HashMap<>(defaultConfig);
        finalConfig.putAll(userConfig);
        System.out.println("默认配置：" + defaultConfig);
        System.out.println("用户配置：" + userConfig);
        System.out.println("最终配置：" + finalConfig);

        // ========== 13. 性能测试 ==========
        System.out.println("\n========== 13. 性能测试 ==========");

        int testSize = 100000;

        // HashMap 插入性能
        HashMap<Integer, Integer> perfMap = new HashMap<>();
        long start = System.nanoTime();
        for (int i = 0; i < testSize; i++) {
            perfMap.put(i, i * 2);
        }
        long end = System.nanoTime();
        System.out.println("HashMap 插入 " + testSize + " 个键值对：" + (end - start) / 1000000 + "ms");

        // HashMap 查找性能
        start = System.nanoTime();
        for (int i = 0; i < testSize; i++) {
            perfMap.get(i);
        }
        end = System.nanoTime();
        System.out.println("HashMap 查找 " + testSize + " 个键：" + (end - start) / 1000000 + "ms");

        System.out.println("✅ HashMap 基本操作 O(1)，性能优秀");

        // ========== 14. 注意事项 ==========
        System.out.println("\n========== 14. 注意事项 ==========");

        // ⚠️ 注意1：不保证顺序
        HashMap<Integer, String> orderMap = new HashMap<>();
        orderMap.put(3, "three");
        orderMap.put(1, "one");
        orderMap.put(4, "four");
        orderMap.put(2, "two");
        System.out.println("HashMap 不保证顺序：" + orderMap);

        // ⚠️ 注意2：键必须重写 equals 和 hashCode
        System.out.println("⚠️ 自定义类作为键时，必须重写 equals 和 hashCode");
        System.out.println("   否则 HashMap 无法正确查找和去重");

        // ⚠️ 注意3：不是线程安全
        System.out.println("⚠️ HashMap 不是线程安全的");
        System.out.println("   解决方案1：Collections.synchronizedMap(new HashMap<>())");
        System.out.println("   解决方案2：ConcurrentHashMap_.java（并发专用）");

        // ⚠️ 注意4：迭代器是 fail-fast
        HashMap<String, Integer> failMap = new HashMap<>();
        failMap.put("A", 1);
        failMap.put("B", 2);
        failMap.put("C", 3);

        try {
            Iterator<String> it2 = failMap.keySet().iterator();
            while (it2.hasNext()) {
                String key = it2.next();
                if (key.equals("B")) {
                    failMap.put("D", 4);
                }
            }
        } catch (java.util.ConcurrentModificationException e) {
            System.out.println("⚠️ 遍历时修改 Map 抛出 ConcurrentModificationException");
        }

        // ✅ 正确删除
        HashMap<String, Integer> correctMap = new HashMap<>(failMap);
        Iterator<String> correctIt = correctMap.keySet().iterator();
        while (correctIt.hasNext()) {
            String key = correctIt.next();
            if (key.equals("B")) {
                correctIt.remove();
            }
        }
        System.out.println("✅ 使用 Iterator.remove() 安全删除：" + correctMap);

        // ⚠️ 注意5：允许 null
        HashMap<String, String> nullMap = new HashMap<>();
        nullMap.put(null, "nullKey");
        nullMap.put("nullValue", null);
        nullMap.put(null, "nullKey2");
        System.out.println("HashMap 允许 null 键和 null 值：" + nullMap);
    }

    // ========== 内部类 ==========

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
}