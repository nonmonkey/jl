import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.Collection;
import java.util.Map.Entry;
import java.util.Iterator;
import java.util.Objects;

/**
 * LinkedHashMap
 * Map 接口的实现类，基于 HashMap + 双向链表实现
 *
 * 继承关系
 * java.lang.Object
 *    └── java.util.AbstractMap<K,V>
 *          └── java.util.HashMap<K,V>
 *                └── java.util.LinkedHashMap<K,V>
 *
 * 核心特点：
 * - 键值对存储：每个键映射到一个值
 * - 键不可重复：键（Key）是唯一的（通过 equals 和 hashCode 判断）
 * - 有序：维护键值对的顺序（插入顺序或访问顺序）
 * - 允许 null：允许一个 null 键和多个 null 值
 * - 非线程安全：多线程环境下需要额外同步
 * - 性能：略低于 HashMap（维护链表的开销）
 *
 * 底层数据结构：
 * - 基于 HashMap（数组 + 链表 + 红黑树）
 * - 额外维护一个双向链表来记录顺序
 * - 默认初始容量：16
 * - 负载因子：0.75
 * - 扩容机制：当元素个数 > 容量 × 负载因子 时扩容（翻倍）
 *
 * 两种顺序模式：
 * 1. 插入顺序（默认）：按照元素插入的顺序
 * 2. 访问顺序：按照元素被访问的顺序（get/put），用于 LRU 缓存
 *
 * 核心方法（与 HashMap 基本一致）：
 * - put          → 添加键值对
 * - get          → 根据键获取值（访问顺序模式下会移动元素到尾部）
 * - remove       → 根据键删除键值对
 * - containsKey  → 判断是否包含某个键
 * - containsValue → 判断是否包含某个值
 * - size         → 获取键值对个数
 * - isEmpty      → 判断是否为空
 * - clear        → 清空
 * - keySet       → 获取所有键的 Set 视图（按顺序）
 * - values       → 获取所有值的 Collection 视图（按顺序）
 * - entrySet     → 获取所有键值对的 Set 视图（按顺序）
 *
 * 特有方法：
 * - removeEldestEntry → 删除最旧的条目（用于 LRU 缓存）
 *
 * 适用场景：
 * - 需要保持插入顺序 ⭐⭐⭐⭐⭐
 * - 需要 LRU 缓存（访问顺序） ⭐⭐⭐⭐⭐
 * - 需要可预测的迭代顺序 ⭐⭐⭐⭐⭐
 *
 * 与 HashMap 对比：
 * - HashMap：无序，性能更高
 * - LinkedHashMap：有序（插入/访问），性能略低
 */
public class LinkedHashMap_ {

    public static void main(String[] args) {

        // ========== 1. 创建 LinkedHashMap ==========
        System.out.println("========== 1. 创建 LinkedHashMap ==========");

        // 方式1：无参构造（默认容量16，负载因子0.75，插入顺序）
        Map<String, Integer> map1 = new LinkedHashMap<>();
        System.out.println("无参构造：" + map1);

        // 方式2：指定初始容量
        Map<String, Integer> map2 = new LinkedHashMap<>(100);
        System.out.println("指定容量100：" + map2);

        // 方式3：指定容量和负载因子
        Map<String, Integer> map3 = new LinkedHashMap<>(100, 0.8f);
        System.out.println("指定容量100，负载因子0.8：" + map3);

        // 方式4：指定访问顺序模式
        LinkedHashMap<String, Integer> accessOrderMap = new LinkedHashMap<>(16, 0.75f, true);
        System.out.println("访问顺序模式（LRU）：" + accessOrderMap);

        // 方式5：从其他 Map 创建
        Map<String, Integer> map4 = new LinkedHashMap<>(Map.of("A", 1, "B", 2, "C", 3));
        System.out.println("从其他 Map 创建：" + map4);

        // ========== 2. 基本操作 ==========
        System.out.println("\n========== 2. 基本操作 ==========");

        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();

        // put → 添加键值对
        map.put("语文", 90);
        map.put("数学", 95);
        map.put("英语", 88);
        System.out.println("添加后：" + map);

        // put → 覆盖已存在的键（顺序不变）
        map.put("语文", 92);
        System.out.println("修改 '语文' 后：" + map);
        System.out.println("✅ 修改已存在的键不会改变顺序");

        // get → 根据键获取值
        System.out.println("语文成绩：" + map.get("语文"));

        // size → 键值对个数
        System.out.println("键值对个数：" + map.size());

        // containsKey → 判断是否包含某个键
        System.out.println("是否包含 '数学'：" + map.containsKey("数学"));

        // containsValue → 判断是否包含某个值
        System.out.println("是否包含 95 分：" + map.containsValue(95));

        // remove → 根据键删除
        map.remove("英语");
        System.out.println("删除 '英语' 后：" + map);

        // ========== 3. 核心特性：保持插入顺序 ==========
        System.out.println("\n========== 3. 核心特性：保持插入顺序 ==========");

        // 对比 HashMap 和 LinkedHashMap
        System.out.println("--- HashMap（无序）---");
        Map<Integer, String> hashMap = new java.util.HashMap<>();
        hashMap.put(3, "three");
        hashMap.put(1, "one");
        hashMap.put(4, "four");
        hashMap.put(2, "two");
        System.out.println("HashMap：" + hashMap);
        System.out.println("  不保证顺序");

        System.out.println("\n--- LinkedHashMap（插入顺序）---");
        Map<Integer, String> linkedMap = new LinkedHashMap<>();
        linkedMap.put(3, "three");
        linkedMap.put(1, "one");
        linkedMap.put(4, "four");
        linkedMap.put(2, "two");
        System.out.println("LinkedHashMap：" + linkedMap);
        System.out.println("  ✅ 保持插入顺序：3 → 1 → 4 → 2");

        // 重新插入已存在的键不会改变顺序
        linkedMap.put(3, "THREE");
        System.out.println("重新插入 3 后：" + linkedMap);
        System.out.println("  ✅ 顺序不变：3 → 1 → 4 → 2");

        // ========== 4. 访问顺序模式（LRU 缓存） ==========
        System.out.println("\n========== 4. 访问顺序模式（LRU 缓存） ==========");

        // 访问顺序模式：每次 get/put 会将元素移到尾部
        LinkedHashMap<String, Integer> accessMap = new LinkedHashMap<>(16, 0.75f, true);

        accessMap.put("A", 1);
        accessMap.put("B", 2);
        accessMap.put("C", 3);
        System.out.println("初始（插入顺序）：" + accessMap);

        // get 操作会移动元素到尾部
        accessMap.get("A");
        System.out.println("get('A') 后：" + accessMap);
        System.out.println("  ✅ A 被移动到尾部");

        accessMap.get("B");
        System.out.println("get('B') 后：" + accessMap);
        System.out.println("  ✅ B 被移动到尾部");

        // put 操作也会移动
        accessMap.put("C", 30);
        System.out.println("put('C', 30) 后：" + accessMap);
        System.out.println("  ✅ C 被移动到尾部");

        // ========== 5. LRU 缓存实现 ==========
        System.out.println("\n========== 5. LRU 缓存实现 ==========");

        // 创建 LRU 缓存，容量为 3
        LRUCache<String, Integer> lruCache = new LRUCache<>(3);

        lruCache.put("A", 1);
        lruCache.put("B", 2);
        lruCache.put("C", 3);
        System.out.println("缓存（容量3）：" + lruCache);

        // 访问 A，A 变为最近使用
        lruCache.get("A");
        System.out.println("访问 A 后：" + lruCache);

        // 插入 D，容量超出，移除最久未使用的（B）
        lruCache.put("D", 4);
        System.out.println("插入 D 后：" + lruCache);
        System.out.println("  ✅ 最久未使用的 B 被移除");

        // 再访问 C，C 变为最近使用
        lruCache.get("C");
        System.out.println("访问 C 后：" + lruCache);

        // 插入 E，移除最久未使用的（A）
        lruCache.put("E", 5);
        System.out.println("插入 E 后：" + lruCache);
        System.out.println("  ✅ 最久未使用的 A 被移除");

        // ========== 6. 遍历方式 ==========
        System.out.println("\n========== 6. 遍历方式 ==========");

        LinkedHashMap<String, Integer> traverseMap = new LinkedHashMap<>();
        traverseMap.put("语文", 90);
        traverseMap.put("数学", 95);
        traverseMap.put("英语", 88);
        traverseMap.put("物理", 92);

        // 方式1：遍历 entrySet（按插入顺序）
        System.out.println("方式1（entrySet 增强 for）：");
        for (Map.Entry<String, Integer> entry : traverseMap.entrySet()) {
            System.out.println("  " + entry.getKey() + " = " + entry.getValue());
        }

        // 方式2：遍历 keySet（按插入顺序）
        System.out.println("方式2（keySet）：");
        for (String key : traverseMap.keySet()) {
            System.out.println("  " + key + " = " + traverseMap.get(key));
        }

        // 方式3：forEach（按插入顺序）
        System.out.println("方式3（forEach）：");
        traverseMap.forEach((key, value) -> {
            System.out.println("  " + key + " = " + value);
        });

        // ========== 7. getOrDefault 和 putIfAbsent ==========
        System.out.println("\n========== 7. getOrDefault 和 putIfAbsent ==========");

        LinkedHashMap<String, Integer> defaultMap = new LinkedHashMap<>();
        defaultMap.put("A", 1);
        defaultMap.put("B", 2);

        System.out.println("原 map：" + defaultMap);

        // getOrDefault → 键不存在返回默认值
        System.out.println("getOrDefault('A', 0)：" + defaultMap.getOrDefault("A", 0));
        System.out.println("getOrDefault('C', 0)：" + defaultMap.getOrDefault("C", 0));

        // putIfAbsent → 键不存在才添加
        defaultMap.putIfAbsent("A", 10);
        defaultMap.putIfAbsent("C", 3);
        System.out.println("putIfAbsent 后：" + defaultMap);

        // ========== 8. replace 方法 ==========
        System.out.println("\n========== 8. replace 方法 ==========");

        LinkedHashMap<String, Integer> replaceMap = new LinkedHashMap<>();
        replaceMap.put("A", 1);
        replaceMap.put("B", 2);
        replaceMap.put("C", 3);

        System.out.println("原 map：" + replaceMap);

        // replace(key, newValue)
        replaceMap.replace("B", 20);
        System.out.println("replace('B', 20) 后：" + replaceMap);

        // replace(key, oldValue, newValue)
        replaceMap.replace("A", 1, 10);
        System.out.println("replace('A', 1, 10) 后：" + replaceMap);

        // ========== 9. compute 和 merge ==========
        System.out.println("\n========== 9. compute 和 merge ==========");

        LinkedHashMap<String, Integer> computeMap = new LinkedHashMap<>();
        computeMap.put("A", 10);
        computeMap.put("B", 20);

        System.out.println("原 map：" + computeMap);

        // compute → 计算新值
        computeMap.compute("A", (key, val) -> val + 5);
        System.out.println("compute('A', val -> val + 5)：" + computeMap);

        // computeIfAbsent → 键不存在才计算
        computeMap.computeIfAbsent("C", key -> 30);
        System.out.println("computeIfAbsent('C', key -> 30)：" + computeMap);

        // merge → 合并值
        computeMap.merge("B", 5, Integer::sum);
        System.out.println("merge('B', 5, Integer::sum)：" + computeMap);

        // ========== 10. 实际应用场景 ==========
        System.out.println("\n========== 10. 实际应用场景 ==========");

        // 场景1：保持插入顺序的配置
        System.out.println("--- 场景1：配置管理（保持顺序） ---");
        LinkedHashMap<String, String> config = new LinkedHashMap<>();
        config.put("host", "localhost");
        config.put("port", "8080");
        config.put("timeout", "5000");
        config.put("maxConnections", "100");

        System.out.println("配置（按添加顺序）：");
        for (Map.Entry<String, String> entry : config.entrySet()) {
            System.out.println("  " + entry.getKey() + " = " + entry.getValue());
        }

        // 场景2：用户最近访问记录
        System.out.println("\n--- 场景2：用户最近访问记录 ---");
        LinkedHashMap<String, String> recentAccess = new LinkedHashMap<>();

        recentAccess.put("2024-01-15 10:00", "用户登录");
        recentAccess.put("2024-01-15 10:05", "查看首页");
        recentAccess.put("2024-01-15 10:10", "浏览商品");
        recentAccess.put("2024-01-15 10:15", "添加购物车");

        System.out.println("用户操作记录（按时间顺序）：");
        for (Map.Entry<String, String> entry : recentAccess.entrySet()) {
            System.out.println("  " + entry.getKey() + " → " + entry.getValue());
        }

        // 场景3：缓存系统（使用 LRU）
        System.out.println("\n--- 场景3：缓存系统（LRU） ---");
        LRUCache<String, String> cache = new LRUCache<>(3);

        cache.put("user:1", "张三");
        cache.put("user:2", "李四");
        cache.put("user:3", "王五");
        System.out.println("缓存：" + cache);

        // 访问 user:1
        cache.get("user:1");
        System.out.println("访问 user:1 后：" + cache);

        // 添加新用户，移除最久未使用的 user:2
        cache.put("user:4", "赵六");
        System.out.println("添加 user:4 后：" + cache);

        // ========== 11. 性能测试 ==========
        System.out.println("\n========== 11. 性能测试 ==========");

        int testSize = 100000;

        // HashMap 插入
        Map<Integer, Integer> hashMapPerf = new java.util.HashMap<>();
        long start = System.nanoTime();
        for (int i = 0; i < testSize; i++) {
            hashMapPerf.put(i, i * 2);
        }
        long end = System.nanoTime();
        System.out.println("HashMap 插入 " + testSize + " 个键值对：" + (end - start) / 1000000 + "ms");

        // LinkedHashMap 插入
        Map<Integer, Integer> linkedMapPerf = new LinkedHashMap<>();
        start = System.nanoTime();
        for (int i = 0; i < testSize; i++) {
            linkedMapPerf.put(i, i * 2);
        }
        end = System.nanoTime();
        System.out.println("LinkedHashMap 插入 " + testSize + " 个键值对：" + (end - start) / 1000000 + "ms");

        System.out.println("✅ LinkedHashMap 性能略低于 HashMap（维护链表的开销）");

        // ========== 12. 注意事项 ==========
        System.out.println("\n========== 12. 注意事项 ==========");

        // ⚠️ 注意1：默认是插入顺序
        LinkedHashMap<String, Integer> defaultOrder = new LinkedHashMap<>();
        defaultOrder.put("B", 2);
        defaultOrder.put("A", 1);
        defaultOrder.put("C", 3);
        System.out.println("默认（插入顺序）：" + defaultOrder);

        // ⚠️ 注意2：访问顺序模式需要传入 true
        LinkedHashMap<String, Integer> accessOrder = new LinkedHashMap<>(16, 0.75f, true);
        accessOrder.put("B", 2);
        accessOrder.put("A", 1);
        accessOrder.put("C", 3);
        accessOrder.get("B");
        System.out.println("访问顺序模式：" + accessOrder);

        // ⚠️ 注意3：键必须重写 equals 和 hashCode
        System.out.println("⚠️ 自定义类作为键时，必须重写 equals 和 hashCode");

        // ⚠️ 注意4：不是线程安全
        System.out.println("⚠️ LinkedHashMap 不是线程安全的");
        System.out.println("   解决方案：Collections.synchronizedMap(new LinkedHashMap<>())");

        // ⚠️ 注意5：允许 null
        LinkedHashMap<String, String> nullMap = new LinkedHashMap<>();
        nullMap.put(null, "nullKey");
        nullMap.put("nullValue", null);
        System.out.println("LinkedHashMap 允许 null 键和 null 值：" + nullMap);

        // ⚠️ 注意6：迭代器是 fail-fast
        LinkedHashMap<String, Integer> failMap = new LinkedHashMap<>();
        failMap.put("A", 1);
        failMap.put("B", 2);
        failMap.put("C", 3);

        try {
            Iterator<String> it = failMap.keySet().iterator();
            while (it.hasNext()) {
                String key = it.next();
                if (key.equals("B")) {
                    failMap.put("D", 4);
                }
            }
        } catch (java.util.ConcurrentModificationException e) {
            System.out.println("⚠️ 遍历时修改 Map 抛出 ConcurrentModificationException");
        }

        // ✅ 正确删除
        LinkedHashMap<String, Integer> correctMap = new LinkedHashMap<>(failMap);
        Iterator<String> correctIt = correctMap.keySet().iterator();
        while (correctIt.hasNext()) {
            String key = correctIt.next();
            if (key.equals("B")) {
                correctIt.remove();
            }
        }
        System.out.println("✅ 使用 Iterator.remove() 安全删除：" + correctMap);

        // ========== 13. 三种 Map 对比总结 ==========
        System.out.println("\n========== 13. 三种 Map 对比总结 ==========");

        System.out.println("┌──────────────┬─────────────┬─────────────────┬─────────────┐");
        System.out.println("│   特性       │  HashMap    │  LinkedHashMap  │  TreeMap    │");
        System.out.println("├──────────────┼─────────────┼─────────────────┼─────────────┤");
        System.out.println("│ 底层结构     │  哈希表     │  哈希表+链表    │  红黑树     │");
        System.out.println("│ 顺序         │  无序       │  插入/访问顺序  │  排序       │");
        System.out.println("│ 时间复杂度   │  O(1)       │  O(1)           │  O(log n)   │");
        System.out.println("│ null 键      │  允许       │  允许           │  不允许     │");
        System.out.println("│ null 值      │  允许       │  允许           │  允许       │");
        System.out.println("│ 线程安全     │  否         │  否             │  否         │");
        System.out.println("│ 适用场景     │  一般存储   │  保持顺序/LRU   │  排序       │");
        System.out.println("└──────────────┴─────────────┴─────────────────┴─────────────┘");
    }

    // ========== LRU 缓存类 ==========

    /**
     * LRU 缓存（使用 LinkedHashMap 访问顺序模式）
     */
    static class LRUCache<K, V> extends LinkedHashMap<K, V> {
        private final int maxCapacity;

        public LRUCache(int maxCapacity) {
            super(16, 0.75f, true);  // 访问顺序模式
            this.maxCapacity = maxCapacity;
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
            // 当缓存大小超过最大容量时，移除最久未使用的条目
            return size() > maxCapacity;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("{");
            for (Map.Entry<K, V> entry : entrySet()) {
                sb.append(entry.getKey()).append("=").append(entry.getValue()).append(", ");
            }
            if (sb.length() > 1) {
                sb.setLength(sb.length() - 2);
            }
            sb.append("}");
            return sb.toString();
        }
    }
}