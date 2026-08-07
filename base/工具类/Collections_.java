package base.工具类;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.Map;
import java.util.Comparator;

/**
 * Collections 工具类
 * Java 提供操作集合（Collection）的静态方法工具类
 *
 * 核心特点：
 * - 全部是静态方法
 * - 操作 List、Set、Map 等集合
 * - 提供排序、查找、同步、不可变集合等功能
 *
 * 核心方法分类：
 *
 * 【排序相关】
 * - sort()           → 排序
 * - reverse()        → 反转顺序
 * - shuffle()        → 打乱顺序
 * - swap()           → 交换元素
 * - rotate()         → 旋转
 *
 * 【查找相关】
 * - binarySearch()   → 二分查找
 * - max()            → 最大值
 * - min()            → 最小值
 * - frequency()      → 统计出现次数
 *
 * 【集合操作】
 * - addAll()         → 批量添加
 * - copy()           → 复制
 * - fill()           → 填充
 * - replaceAll()     → 替换所有
 *
 * 【同步包装】
 * - synchronizedCollection() → 同步集合
 * - synchronizedList()       → 同步 List
 * - synchronizedSet()        → 同步 Set
 * - synchronizedMap()        → 同步 Map
 *
 * 【不可变集合】
 * - emptyList()      → 空 List
 * - emptySet()       → 空 Set
 * - emptyMap()       → 空 Map
 * - singletonList()  → 单元素 List
 * - unmodifiableList() → 不可变 List
 *
 * 【其他】
 * - reverseOrder()   → 反向比较器
 * - nCopies()        → 创建多个副本
 *
 * 适用场景：
 * - 集合排序 ⭐⭐⭐⭐⭐
 * - 集合查找 ⭐⭐⭐⭐
 * - 线程安全包装 ⭐⭐⭐⭐
 * - 不可变集合 ⭐⭐⭐
 */
public class Collections_ {

    public static void main(String[] args) {

        // ========== 1. 排序相关 ==========
        System.out.println("========== 1. 排序相关 ==========");

        List<Integer> numbers = new ArrayList<>(Arrays.asList(5, 2, 8, 1, 9, 3, 7));

        // sort() → 升序排序
        Collections.sort(numbers);
        System.out.println("sort 升序：" + numbers);

        // sort() + Comparator → 降序
        Collections.sort(numbers, (a, b) -> b - a);
        System.out.println("sort 降序：" + numbers);

        // reverse() → 反转
        Collections.reverse(numbers);
        System.out.println("reverse 反转：" + numbers);

        // shuffle() → 打乱
        Collections.shuffle(numbers);
        System.out.println("shuffle 打乱：" + numbers);

        // swap() → 交换
        Collections.swap(numbers, 0, 1);
        System.out.println("swap(0,1)：" + numbers);

        // rotate() → 旋转（向右移动 2 位）
        Collections.rotate(numbers, 2);
        System.out.println("rotate 右移2位：" + numbers);

        // ========== 2. 查找相关 ==========
        System.out.println("\n========== 2. 查找相关 ==========");

        List<Integer> searchList = new ArrayList<>(Arrays.asList(1, 3, 5, 7, 9, 11, 13));

        // binarySearch() → 二分查找（必须先排序）
        Collections.sort(searchList);
        System.out.println("排序后：" + searchList);
        int index = Collections.binarySearch(searchList, 7);
        System.out.println("binarySearch(7) 位置：" + index);
        System.out.println("binarySearch(6)（不存在）：" + Collections.binarySearch(searchList, 6));

        // max() → 最大值
        System.out.println("max：" + Collections.max(searchList));

        // min() → 最小值
        System.out.println("min：" + Collections.min(searchList));

        // max() + Comparator
        System.out.println("max（降序比较器）：" + Collections.max(searchList, (a, b) -> b - a));

        // frequency() → 统计出现次数
        List<Integer> freqList = Arrays.asList(1, 2, 3, 2, 4, 2, 5, 2);
        System.out.println("freqList：" + freqList);
        System.out.println("2 出现次数：" + Collections.frequency(freqList, 2));

        // ========== 3. 集合操作 ==========
        System.out.println("\n========== 3. 集合操作 ==========");

        List<String> opList = new ArrayList<>(Arrays.asList("A", "B", "C"));

        // addAll() → 批量添加
        Collections.addAll(opList, "D", "E", "F");
        System.out.println("addAll 后：" + opList);

        // copy() → 复制（目标必须足够大）
        List<String> src = Arrays.asList("X", "Y", "Z");
        List<String> dest = new ArrayList<>(Arrays.asList("", "", ""));
        Collections.copy(dest, src);
        System.out.println("copy(src -> dest)：" + dest);

        // fill() → 填充
        List<String> fillList = new ArrayList<>(Arrays.asList("A", "B", "C"));
        Collections.fill(fillList, "X");
        System.out.println("fill('X')：" + fillList);

        // replaceAll() → 替换所有
        List<String> replaceList = new ArrayList<>(Arrays.asList("A", "B", "A", "C", "A"));
        Collections.replaceAll(replaceList, "A", "Z");
        System.out.println("replaceAll('A' -> 'Z')：" + replaceList);

        // ========== 4. 同步包装 ==========
        System.out.println("\n========== 4. 同步包装 ==========");

        // synchronizedList() → 将普通 List 转为线程安全的 List
        List<String> syncList = Collections.synchronizedList(new ArrayList<>());
        syncList.add("A");
        syncList.add("B");
        System.out.println("synchronizedList：" + syncList);

        // synchronizedSet() → 同步 Set
        Set<String> syncSet = Collections.synchronizedSet(new java.util.HashSet<>());
        syncSet.add("A");
        syncSet.add("B");
        System.out.println("synchronizedSet：" + syncSet);

        // synchronizedMap() → 同步 Map
        Map<String, String> syncMap = Collections.synchronizedMap(new java.util.HashMap<>());
        syncMap.put("key", "value");
        System.out.println("synchronizedMap：" + syncMap);

        // ⚠️ 注意：同步包装后，迭代时仍需要手动同步
        System.out.println("⚠️ 使用同步集合时，迭代需要 synchronized 块");

        // ========== 5. 不可变集合 ==========
        System.out.println("\n========== 5. 不可变集合 ==========");

        // emptyList() → 空 List（不可变）
        List<String> emptyList = Collections.emptyList();
        System.out.println("emptyList：" + emptyList);
        // emptyList.add("A"); // ❌ UnsupportedOperationException

        // emptySet() → 空 Set
        Set<String> emptySet = Collections.emptySet();
        System.out.println("emptySet：" + emptySet);

        // emptyMap() → 空 Map
        Map<String, String> emptyMap = Collections.emptyMap();
        System.out.println("emptyMap：" + emptyMap);

        // singletonList() → 单元素 List（不可变）
        List<String> singleList = Collections.singletonList("A");
        System.out.println("singletonList：" + singleList);
        // singleList.add("B"); // ❌ UnsupportedOperationException

        // nCopies() → 创建多个副本
        List<String> copies = Collections.nCopies(5, "Copy");
        System.out.println("nCopies(5, 'Copy')：" + copies);

        // unmodifiableList() → 不可变 List
        List<String> mutable = new ArrayList<>(Arrays.asList("A", "B", "C"));
        List<String> unmodifiable = Collections.unmodifiableList(mutable);
        System.out.println("unmodifiableList：" + unmodifiable);
        // unmodifiable.add("D"); // ❌ UnsupportedOperationException

        // 修改原始 List 会影响不可变 List
        mutable.add("D");
        System.out.println("修改 mutable 后，unmodifiable：" + unmodifiable);
        System.out.println("⚠️ unmodifiableList 是视图，修改原始集合会影响它");

        // ========== 6. 比较器 ==========
        System.out.println("\n========== 6. 比较器 ==========");

        List<String> strList = new ArrayList<>(Arrays.asList("banana", "apple", "cherry", "date"));

        // reverseOrder() → 自然顺序的反向
        Collections.sort(strList, Collections.reverseOrder());
        System.out.println("reverseOrder 排序：" + strList);

        // reverseOrder(Comparator) → 指定比较器的反向
        Collections.sort(strList, Collections.reverseOrder(Comparator.comparing(String::length)));
        System.out.println("按长度升序的反向：" + strList);

        // ========== 7. 实际应用场景 ==========
        System.out.println("\n========== 7. 实际应用场景 ==========");

        // 场景1：排行榜排序
        System.out.println("--- 场景1：排行榜排序 ---");
        List<Score> scores = new ArrayList<>();
        scores.add(new Score("张三", 95));
        scores.add(new Score("李四", 88));
        scores.add(new Score("王五", 92));
        scores.add(new Score("赵六", 85));

        Collections.sort(scores, (s1, s2) -> s2.score - s1.score);
        System.out.println("成绩排行榜（从高到低）：");
        for (int i = 0; i < scores.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + scores.get(i));
        }

        // 场景2：查找最高分
        Score maxScore = Collections.max(scores, (s1, s2) -> s1.score - s2.score);
        System.out.println("最高分：" + maxScore);

        // 场景3：线程安全集合
        System.out.println("\n--- 场景3：线程安全集合 ---");
        List<Integer> safeList = Collections.synchronizedList(new ArrayList<>());
        System.out.println("✅ Collections.synchronizedList 创建线程安全 List");

        // 场景4：不可变配置
        System.out.println("\n--- 场景4：不可变配置 ---");
        List<String> config = Collections.unmodifiableList(Arrays.asList("host=localhost", "port=8080"));
        System.out.println("不可变配置：" + config);
        // config.add("timeout=5000"); // ❌ 不能修改

        // 场景5：空集合安全返回
        System.out.println("\n--- 场景5：空集合安全返回 ---");
        List<String> result = getSearchResult("不存在");
        System.out.println("搜索结果：" + result);
        System.out.println("result 是否为空：" + result.isEmpty());

        // ========== 8. 注意事项 ==========
        System.out.println("\n========== 8. 注意事项 ==========");

        // ⚠️ 注意1：binarySearch 必须先排序
        List<Integer> unsorted = Arrays.asList(5, 2, 8, 1);
        // Collections.binarySearch(unsorted, 2); // ❌ 结果不可靠
        System.out.println("⚠️ binarySearch 必须先排序");

        // ⚠️ 注意2：不可变集合不能修改
        List<String> immutable = Collections.singletonList("A");
        // immutable.add("B"); // ❌ UnsupportedOperationException
        System.out.println("⚠️ singletonList 不能修改");

        // ⚠️ 注意3：同步集合迭代需要同步
        List<String> sync = Collections.synchronizedList(new ArrayList<>());
        sync.add("A");
        sync.add("B");
        System.out.println("⚠️ 遍历同步集合需要 synchronized 块：");
        System.out.println("  synchronized(sync) {");
        System.out.println("      for (String item : sync) { ... }");
        System.out.println("  }");

        // ⚠️ 注意4：unmodifiableList 是视图
        List<String> orig = new ArrayList<>(Arrays.asList("A", "B"));
        List<String> view = Collections.unmodifiableList(orig);
        orig.add("C");
        System.out.println("修改 orig，view 也被修改：" + view);
        System.out.println("⚠️ unmodifiableList 不是独立副本，是视图");

        // ⚠️ 注意5：sort 会修改原集合
        List<Integer> toSort = new ArrayList<>(Arrays.asList(3, 1, 2));
        Collections.sort(toSort);
        System.out.println("⚠️ sort() 会修改原集合");

        // ========== 9. Collections vs Arrays vs Objects ==========
        System.out.println("\n========== 9. Collections vs Arrays vs Objects ==========");

        System.out.println("┌─────────────┬─────────────┬─────────────┬─────────────┐");
        System.out.println("│  工具类     │  Collections│   Arrays    │   Objects   │");
        System.out.println("├─────────────┼─────────────┼─────────────┼─────────────┤");
        System.out.println("│ 操作对象    │  集合       │  数组       │  任意对象   │");
        System.out.println("│ 排序        │  ✅          │  ✅          │  ❌          │");
        System.out.println("│ 查找        │  ✅          │  ✅          │  ❌          │");
        System.out.println("│ 判空        │  ❌          │  ❌          │  ✅          │");
        System.out.println("│ 同步包装    │  ✅          │  ❌          │  ❌          │");
        System.out.println("│ 不可变集合  │  ✅          │  ❌          │  ❌          │");
        System.out.println("│ equals      │  ❌          │  ✅          │  ✅          │");
        System.out.println("└─────────────┴─────────────┴─────────────┴─────────────┘");
    }

    // ========== 辅助方法 ==========

    /**
     * 安全返回搜索结果（空集合）
     */
    private static List<String> getSearchResult(String keyword) {
        // 模拟搜索
        if ("存在".equals(keyword)) {
            return Arrays.asList("结果1", "结果2");
        }
        // 返回空集合（不可变），避免返回 null
        return Collections.emptyList();
    }

    // ========== 内部类 ==========

    static class Score {
        private String name;
        private int score;

        public Score(String name, int score) {
            this.name = name;
            this.score = score;
        }

        @Override
        public String toString() {
            return name + "：" + score + "分";
        }
    }
}