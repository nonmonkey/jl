import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.LongAdder;

/**
 * ConcurrentHashMap
 * 线程安全的 HashMap 实现，专为并发场景设计
 *
 * 继承关系
 * java.lang.Object
 *    └── java.util.AbstractMap<K,V>
 *          └── java.util.concurrent.ConcurrentHashMap<K,V>
 *
 * 核心特点：
 * - 线程安全：多线程环境下无需额外同步
 * - 高并发性能：使用分段锁/CAS + synchronized 实现
 * - 键值对存储：每个键映射到一个值
 * - 键不可重复：键（Key）是唯一的（通过 equals 和 hashCode 判断）
 * - 不允许 null：不能存储 null 键和 null 值（与 HashMap 不同）
 * - 保持顺序：不保证遍历顺序
 *
 * 底层数据结构（Java 8+）：
 * - 数组 + 链表 + 红黑树
 * - 使用 CAS（乐观锁）+ synchronized（悲观锁）
 * - 默认初始容量：16
 * - 负载因子：0.75
 * - 扩容机制：当元素个数 > 容量 × 负载因子 时扩容
 *
 * 核心方法（与 HashMap 基本一致，但线程安全）：
 * - put           → 添加键值对
 * - get           → 根据键获取值
 * - remove        → 根据键删除键值对
 * - containsKey   → 判断是否包含某个键
 * - containsValue → 判断是否包含某个值（⚠️ 注意：遍历整个表，性能较低）
 * - size          → 获取键值对个数（⚠️ 注意：不是精确值，是估算值）
 * - mappingCount  → 获取键值对个数（更精确，用于大集合）
 * - isEmpty       → 判断是否为空（近似值）
 * - clear         → 清空
 * - keySet        → 获取所有键的 Set 视图
 * - values        → 获取所有值的 Collection 视图
 * - entrySet      → 获取所有键值对的 Set 视图
 * - putIfAbsent   → 键不存在时才添加（原子操作）
 * - getOrDefault  → 键不存在返回默认值
 * - replace       → 替换键的值（原子操作）
 * - compute       → 计算值（原子操作）
 * - merge         → 合并值（原子操作）
 * - forEach       → 遍历
 * - reduce        → 归约操作
 * - reduceKeys    → 归约操作 key
 * - reduceValues    → 归约操作 value
 * - search        → 搜索
 *
 * 并发特性：
 * - 读操作无锁：get 方法无锁，性能极高
 * - 写操作加锁：只锁住特定节点（不是整个表）
 * - CAS 操作：使用 Compare-And-Swap 实现原子操作
 * - 扩容并发：多线程协同完成扩容
 *
 * 适用场景：
 * - 高并发读写 ⭐⭐⭐⭐⭐
 * - 缓存系统 ⭐⭐⭐⭐⭐
 * - 共享数据存储 ⭐⭐⭐⭐⭐
 *
 * 与 HashMap 对比：
 * - HashMap：非线程安全，允许 null
 * - ConcurrentHashMap：线程安全，不允许 null
 *
 * 与 Hashtable 对比：
 * - Hashtable：全局锁，性能差
 * - ConcurrentHashMap：分段锁/细粒度锁，性能好
 */
public class ConcurrentHashMap_ {

    public static void main(String[] args) {

        // ========== 1. 创建 ConcurrentHashMap ==========
        System.out.println("========== 1. 创建 ConcurrentHashMap ==========");

        // 方式1：无参构造（默认容量16）
        ConcurrentMap<String, Integer> map1 = new ConcurrentHashMap<>();
        System.out.println("无参构造：" + map1);

        // 方式2：指定初始容量
        ConcurrentMap<String, Integer> map2 = new ConcurrentHashMap<>(100);
        System.out.println("指定容量100：" + map2);

        // 方式3：指定容量和负载因子
        ConcurrentMap<String, Integer> map3 = new ConcurrentHashMap<>(100, 0.8f);
        System.out.println("指定容量100，负载因子0.8：" + map3);

        // 方式4：指定并发级别（旧版本参数，Java 8+ 已无实际作用）
        // ConcurrentHashMap<String, Integer> map4 = new ConcurrentHashMap<>(16, 0.75f, 16);

        // 方式5：从其他 Map 创建
        ConcurrentMap<String, Integer> map4 = new ConcurrentHashMap<>(Map.of("A", 1, "B", 2, "C", 3));
        System.out.println("从其他 Map 创建：" + map4);

        // ========== 2. 基本操作 ==========
        System.out.println("\n========== 2. 基本操作 ==========");

        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

        // put → 添加键值对（线程安全）
        map.put("语文", 90);
        map.put("数学", 95);
        map.put("英语", 88);
        System.out.println("添加后：" + map);

        // get → 根据键获取值（无锁，高性能）
        System.out.println("语文成绩：" + map.get("语文"));
        System.out.println("物理成绩（不存在）：" + map.get("物理"));

        // size → 获取键值对个数（估算值，非精确）
        System.out.println("键值对个数（估算）：" + map.size());

        // mappingCount → 获取键值对个数（更精确，用于大集合）
        System.out.println("键值对个数（mappingCount）：" + map.mappingCount());

        // containsKey → 判断是否包含某个键
        System.out.println("是否包含 '数学'：" + map.containsKey("数学"));
        System.out.println("是否包含 '物理'：" + map.containsKey("物理"));

        // containsValue → 判断是否包含某个值（⚠️ 需要遍历整个表）
        System.out.println("是否包含 95 分：" + map.containsValue(95));
        System.out.println("是否包含 100 分：" + map.containsValue(100));

        // remove → 根据键删除
        map.remove("英语");
        System.out.println("删除 '英语' 后：" + map);

        // put → 覆盖已存在的键
        map.put("语文", 92);
        System.out.println("修改 '语文' 成绩后：" + map);

        // ========== 3. 原子操作 ==========
        System.out.println("\n========== 3. 原子操作 ==========");

        ConcurrentHashMap<String, Integer> atomicMap = new ConcurrentHashMap<>();
        atomicMap.put("A", 1);
        atomicMap.put("B", 2);

        System.out.println("原 map：" + atomicMap);

        // putIfAbsent → 键不存在才添加（原子操作）
        atomicMap.putIfAbsent("A", 10);
        atomicMap.putIfAbsent("C", 3);
        System.out.println("putIfAbsent 后：" + atomicMap);

        // replace → 替换键的值（原子操作）
        atomicMap.replace("B", 20);
        System.out.println("replace('B', 20) 后：" + atomicMap);

        // replace(key, oldValue, newValue) → 只有匹配旧值才替换（原子操作）
        atomicMap.replace("A", 1, 5);
        System.out.println("replace('A', 1, 5) 后：" + atomicMap);

        // remove(key, value) → 只有匹配值才删除（原子操作）
        atomicMap.remove("B", 20);
        System.out.println("remove('B', 20) 后：" + atomicMap);

        // ========== 4. compute 和 merge（原子操作） ==========
        System.out.println("\n========== 4. compute 和 merge（原子操作） ==========");

        ConcurrentHashMap<String, Integer> computeMap = new ConcurrentHashMap<>();
        computeMap.put("A", 10);
        computeMap.put("B", 20);

        System.out.println("原 map：" + computeMap);

        // compute → 计算新值（原子操作）
        computeMap.compute("A", (key, val) -> val + 5);
        System.out.println("compute('A', val -> val + 5)：" + computeMap);

        // computeIfAbsent → 键不存在才计算（原子操作）
        computeMap.computeIfAbsent("C", key -> 30);
        System.out.println("computeIfAbsent('C', key -> 30)：" + computeMap);

        // merge → 合并值（原子操作）
        computeMap.merge("B", 5, Integer::sum);
        System.out.println("merge('B', 5, Integer::sum)：" + computeMap);

        // ========== 5. 遍历方式 ==========
        System.out.println("\n========== 5. 遍历方式 ==========");

        ConcurrentHashMap<String, Integer> traverseMap = new ConcurrentHashMap<>();
        traverseMap.put("语文", 90);
        traverseMap.put("数学", 95);
        traverseMap.put("英语", 88);
        traverseMap.put("物理", 92);

        // 方式1：entrySet（弱一致性，允许并发修改）
        System.out.println("方式1（entrySet）：");
        for (Map.Entry<String, Integer> entry : traverseMap.entrySet()) {
            System.out.println("  " + entry.getKey() + " = " + entry.getValue());
        }

        // 方式2：keySet
        System.out.println("方式2（keySet）：");
        for (String key : traverseMap.keySet()) {
            System.out.println("  " + key + " = " + traverseMap.get(key));
        }

        // 方式3：forEach（Java 8）
        System.out.println("方式3（forEach）：");
        traverseMap.forEach((key, value) -> {
            System.out.println("  " + key + " = " + value);
        });

        // 方式4：并行遍历（使用多线程）
        System.out.println("方式4（并行遍历）：");
        traverseMap.forEach(1, (key, value) -> {
            System.out.println("  " + key + " = " + value);
        });

        // ========== 6. 批量操作（Java 8） ==========
        System.out.println("\n========== 6. 批量操作（Java 8） ==========");

        ConcurrentHashMap<String, Integer> batchMap = new ConcurrentHashMap<>();
        batchMap.put("A", 1);
        batchMap.put("B", 2);
        batchMap.put("C", 3);
        batchMap.put("D", 4);
        batchMap.put("E", 5);

        System.out.println("原 map：" + batchMap);

        // forEach → 遍历并执行操作
        System.out.print("forEach 打印：");
        batchMap.forEach((key, value) -> System.out.print(key + "=" + value + " "));
        System.out.println();

        // forEach 带转换
        System.out.print("forEach 转换：");
        batchMap.forEach(1, (key, value) -> key + ":" + value, System.out::print);
        System.out.println();

        // reduce → 归约操作
        Integer sum = batchMap.reduce(1, (key, value) -> value, Integer::sum);
        System.out.println("所有值的总和：" + sum);

        // reduceKeys → 键的归约
        String maxKey = batchMap.reduceKeys(1, (k1, k2) -> k1.compareTo(k2) > 0 ? k1 : k2);
        System.out.println("最大的键：" + maxKey);

        // reduceValues → 值的归约
        Integer maxValue = batchMap.reduceValues(1, Integer::max);
        System.out.println("最大的值：" + maxValue);

        // search → 搜索
        Integer result = batchMap.search(1, (key, value) -> value > 3 ? value : null);
        System.out.println("搜索值 > 3 的第一个结果：" + result);

        // ========== 7. 实际应用场景 ==========
        System.out.println("\n========== 7. 实际应用场景 ==========");

        // 场景1：并发计数器
        System.out.println("--- 场景1：并发计数器 ---");
        ConcurrentHashMap<String, LongAdder> counter = new ConcurrentHashMap<>();

        // 模拟并发计数
        String[] words2 = {"hello", "world", "hello", "java", "world", "hello"};
        for (String word : words2) {
            counter.computeIfAbsent(word, k -> new LongAdder()).increment();
        }

        System.out.println("单词计数：");
        for (Map.Entry<String, LongAdder> entry : counter.entrySet()) {
            System.out.println("  " + entry.getKey() + " = " + entry.getValue().sum());
        }

        // 场景2：缓存系统
        System.out.println("\n--- 场景2：缓存系统 ---");
        ConcurrentHashMap<String, String> cache = new ConcurrentHashMap<>();

        // 模拟从数据库加载数据到缓存
        cache.put("user:1", "张三");
        cache.put("user:2", "李四");
        cache.put("user:3", "王五");

        // 从缓存获取（线程安全）
        String userId = "user:2";
        String userName = cache.get(userId);
        if (userName != null) {
            System.out.println("从缓存获取到：" + userName);
        } else {
            System.out.println("缓存未命中，从数据库查询...");
        }

        // 场景3：线程安全的配置管理
        System.out.println("\n--- 场景3：线程安全的配置管理 ---");
        ConcurrentHashMap<String, String> config = new ConcurrentHashMap<>();
        config.put("host", "localhost");
        config.put("port", "8080");
        config.put("timeout", "5000");

        // 配置更新（线程安全）
        config.compute("port", (key, val) -> "9090");
        System.out.println("配置：" + config);

        // ========== 8. 并发测试 ==========
        System.out.println("\n========== 8. 并发测试 ==========");

        int threadCount = 10;
        int operationsPerThread = 10000;

        ConcurrentHashMap<Integer, Integer> concurrentMap = new ConcurrentHashMap<>();

        // 多个线程同时写入
        Thread[] threads = new Thread[threadCount];
        long start = System.nanoTime();

        for (int i = 0; i < threadCount; i++) {
            final int threadId = i;
            threads[i] = new Thread(() -> {
                for (int j = 0; j < operationsPerThread; j++) {
                    int key = threadId * operationsPerThread + j;
                    concurrentMap.put(key, j);
                }
            });
            threads[i].start();
        }

        // 等待所有线程完成
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long end = System.nanoTime();
        System.out.println(threadCount + " 个线程各写入 " + operationsPerThread + " 次，共 " +
                concurrentMap.size() + " 个键值对");
        System.out.println("耗时：" + (end - start) / 1000000 + "ms");
        System.out.println("✅ ConcurrentHashMap 支持高并发写入");

        // ========== 9. 注意事项 ==========
        System.out.println("\n========== 9. 注意事项 ==========");

        // ⚠️ 注意1：不允许 null 键和 null 值
        ConcurrentHashMap<String, String> nullTest = new ConcurrentHashMap<>();
        // nullTest.put(null, "value");  // ❌ NullPointerException
        // nullTest.put("key", null);    // ❌ NullPointerException
        System.out.println("⚠️ ConcurrentHashMap 不允许 null 键和 null 值");

        // ⚠️ 注意2：size() 是近似值
        System.out.println("⚠️ size() 返回的是估算值，不是精确值");
        System.out.println("   原因：在并发环境下，size 会不断变化");

        // ⚠️ 注意3：containsValue 性能较低
        System.out.println("⚠️ containsValue() 需要遍历整个表，性能较低");
        System.out.println("   在并发环境下使用要谨慎");

        // ⚠️ 注意4：迭代器是弱一致性
        System.out.println("⚠️ 迭代器是弱一致性的，不会抛出 ConcurrentModificationException");
        System.out.println("   遍历过程中允许并发修改，但不保证看到最新数据");

        // ⚠️ 注意5：keySet/values/entrySet 不是复制
        System.out.println("⚠️ keySet/values/entrySet 返回的是视图，不是复制");
        System.out.println("   修改视图会影响原 Map");

        // ⚠️ 注意6：批量操作不能保证原子性
        ConcurrentHashMap<String, Integer> batchNotAtomic = new ConcurrentHashMap<>();
        batchNotAtomic.put("A", 1);
        batchNotAtomic.put("B", 2);

        // 以下两个操作不是原子的
        if (batchNotAtomic.containsKey("A")) {
            batchNotAtomic.put("C", 3);  // 不是原子操作
        }

        // ✅ 使用 compute 等原子操作
        batchNotAtomic.compute("D", (key, val) -> val == null ? 4 : val + 1);

        // ========== 10. 三种并发 Map 对比 ==========
        System.out.println("\n========== 10. 三种并发 Map 对比 ==========");

        System.out.println("┌──────────────┬─────────────┬─────────────────┬─────────────┐");
        System.out.println("│   特性       │  Hashtable  │  Collections    │  Concurrent │");
        System.out.println("│              │             │  .synchronized  │  HashMap    │");
        System.out.println("├──────────────┼─────────────┼─────────────────┼─────────────┤");
        System.out.println("│ 锁机制       │  全局锁     │  全局锁         │  CAS+分段锁 │");
        System.out.println("│ 性能         │  差         │  差             │  好         │");
        System.out.println("│ null 键      │  不允许     │  允许           │  不允许     │");
        System.out.println("│ null 值      │  不允许     │  允许           │  不允许     │");
        System.out.println("│ 迭代器       │  快速失败   │  快速失败       │  弱一致性   │");
        System.out.println("│ 并发扩容     │  不支持     │  不支持         │  支持       │");
        System.out.println("│ 适用场景     │  旧代码     │  低并发         │  高并发     │");
        System.out.println("└──────────────┴─────────────┴─────────────────┴─────────────┘");
    }
}