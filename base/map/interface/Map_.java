import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.TreeMap;
import java.util.Set;
import java.util.Collection;
import java.util.Map.Entry;
import java.util.Iterator;

/**
 * Map 接口
 * Java 集合框架的根接口之一，代表键值对（Key-Value）映射
 *
 * 核心特点：
 * - 键值对存储：每个键映射到一个值
 * - 键不可重复：键（Key）是唯一的（通过 equals 判断）
 * - 值可重复：值（Value）可以重复
 * - 独立体系：不继承 Collection（但提供视图）
 * - 支持 null：HashMap/LinkedHashMap 允许 null 键和 null 值
 *
 * 核心方法：
 *
 * 【基本操作】
 * - put          → 添加键值对（键存在则覆盖）
 * - get          → 根据键获取值
 * - remove       → 根据键删除键值对
 * - containsKey  → 判断是否包含某个键
 * - containsValue → 判断是否包含某个值
 * - size         → 获取键值对个数
 * - isEmpty      → 判断是否为空
 * - clear        → 清空所有键值对
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
 * 主要实现类：
 * - HashMap      → 基于哈希表，无序，性能最好 ⭐
 * - LinkedHashMap → 基于哈希表+链表，有序（插入顺序/访问顺序）
 * - TreeMap      → 基于红黑树，排序（自然/自定义）
 * - Hashtable    → 线程安全，旧版，不推荐
 * - ConcurrentHashMap_.java → 线程安全，并发专用
 *
 * 适用场景：
 * - 需要键值对存储
 * - 需要通过键快速查找值
 * - 需要去重（键唯一）
 *
 * 与 Collection 的关系：
 * - Map 不继承 Collection
 * - 但可以通过 keySet()、values()、entrySet() 获取 Collection 视图
 */
public class Map_ {

    public static void main(String[] args) {

        // ========== 1. Map 的基本使用 ==========
        System.out.println("========== 1. Map 的基本使用 ==========");

        Map<String, Integer> map = new HashMap<>();

        // put → 添加键值对
        map.put("语文", 90);
        map.put("数学", 95);
        map.put("英语", 88);
        System.out.println("添加后：" + map);

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
        map.remove("英语");
        System.out.println("删除 '英语' 后：" + map);

        // put → 覆盖已存在的键
        map.put("语文", 92);
        System.out.println("修改 '语文' 成绩后：" + map);

        // clear → 清空
        map.clear();
        System.out.println("清空后：" + map);

        // ========== 2. putAll（批量添加） ==========
        System.out.println("\n========== 2. putAll（批量添加） ==========");

        Map<String, Integer> putAllMap = new HashMap<>();
        putAllMap.put("A", 1);
        putAllMap.put("B", 2);
        System.out.println("原 map：" + putAllMap);

        // 创建另一个 Map
        Map<String, Integer> sourceMap = new HashMap<>();
        sourceMap.put("B", 20);   // 覆盖
        sourceMap.put("C", 3);    // 新增
        sourceMap.put("D", 4);    // 新增
        System.out.println("源 map：" + sourceMap);

        // putAll → 批量添加（键存在则覆盖）
        putAllMap.putAll(sourceMap);
        System.out.println("putAll 后：" + putAllMap);
        System.out.println("✅ putAll 可以批量添加，键存在则覆盖");

        // ========== 3. compute 方法 ==========
        System.out.println("\n========== 3. compute 方法 ==========");

        Map<String, Integer> computeMap = new HashMap<>();
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
        Integer result1 = computeMap.computeIfAbsent("A", key -> 999);
        System.out.println("computeIfAbsent('A', key -> 999) 返回：" + result1 + "（已存在，不计算）");

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

        // ========== 4. merge 方法 ==========
        System.out.println("\n========== 4. merge 方法 ==========");

        Map<String, Integer> mergeMap = new HashMap<>();
        mergeMap.put("A", 10);
        mergeMap.put("B", 20);

        System.out.println("原 map：" + mergeMap);

        // merge → 合并值（键存在则合并，不存在则直接插入）
        mergeMap.merge("A", 5, (oldVal, newVal) -> oldVal + newVal);
        System.out.println("merge('A', 5, Integer::sum)：" + mergeMap);

        mergeMap.merge("C", 30, (oldVal, newVal) -> oldVal + newVal);
        System.out.println("merge('C', 30, Integer::sum)：" + mergeMap);

        // merge 删除键（返回 null）
        mergeMap.merge("A", 10, (oldVal, newVal) -> null);
        System.out.println("merge('A', 10, (old, new) -> null) 删除键：" + mergeMap);

        // ========== 5. merge 实际应用：单词计数 ==========
        System.out.println("\n========== 5. merge 实际应用：单词计数 ==========");

        Map<String, Integer> wordCount = new HashMap<>();
        String[] words = {"apple", "banana", "apple", "orange", "banana", "apple"};

        for (String word : words) {
            wordCount.merge(word, 1, Integer::sum);
        }
        System.out.println("单词计数：" + wordCount);

        // ========== 6. 三种 Map 实现对比 ==========
        System.out.println("\n========== 6. 三种 Map 实现对比 ==========");

        // HashMap（无序）
        Map<String, Integer> hashMap = new HashMap<>();
        hashMap.put("B", 2);
        hashMap.put("A", 1);
        hashMap.put("C", 3);
        hashMap.put("D", 4);
        System.out.println("HashMap（无序）：" + hashMap);

        // LinkedHashMap（插入顺序）
        Map<String, Integer> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("B", 2);
        linkedHashMap.put("A", 1);
        linkedHashMap.put("C", 3);
        linkedHashMap.put("D", 4);
        System.out.println("LinkedHashMap（插入顺序）：" + linkedHashMap);

        // TreeMap（自然排序）
        Map<String, Integer> treeMap = new TreeMap<>();
        treeMap.put("B", 2);
        treeMap.put("A", 1);
        treeMap.put("C", 3);
        treeMap.put("D", 4);
        System.out.println("TreeMap（自然排序）：" + treeMap);

        // ========== 7. 注意事项 ==========
        System.out.println("\n========== 7. 注意事项 ==========");

        // ⚠️ 注意1：Map 不继承 Collection
        System.out.println("⚠️ Map 独立于 Collection 体系");
        System.out.println("   但可以通过 keySet()、values()、entrySet() 获取 Collection 视图");

        // ⚠️ 注意2：键必须正确实现 equals 和 hashCode
        System.out.println("⚠️ HashMap/LinkedHashMap 依赖键的 equals 和 hashCode");
        System.out.println("   自定义类作为键时必须重写这两个方法");

        // ⚠️ 注意3：不是线程安全
        System.out.println("⚠️ HashMap/LinkedHashMap/TreeMap 都不是线程安全的");
        System.out.println("   解决方案：Collections.synchronizedMap() 或 ConcurrentHashMap_.java");

        // ⚠️ 注意4：null 支持
        Map<String, String> nullMap = new HashMap<>();
        nullMap.put(null, "nullKey");
        nullMap.put("nullValue", null);
        nullMap.put(null, "nullKey2");  // 覆盖
        System.out.println("HashMap 允许 null 键和 null 值：" + nullMap);

        // ⚠️ 注意5：遍历时不能直接修改
        Map<String, Integer> failMap = new HashMap<>();
        failMap.put("A", 1);
        failMap.put("B", 2);
        failMap.put("C", 3);

        try {
            for (String key : failMap.keySet()) {
                if (key.equals("B")) {
                    failMap.put("D", 4);
                }
            }
        } catch (java.util.ConcurrentModificationException e) {
            System.out.println("⚠️ 遍历时修改 Map 抛出 ConcurrentModificationException");
        }

        // ✅ 正确删除
        Map<String, Integer> correctMap = new HashMap<>(failMap);
        Iterator<String> correctIt = correctMap.keySet().iterator();
        while (correctIt.hasNext()) {
            String key = correctIt.next();
            if (key.equals("B")) {
                correctIt.remove();
            }
        }
        System.out.println("✅ 使用 Iterator.remove() 安全删除：" + correctMap);
    }
}