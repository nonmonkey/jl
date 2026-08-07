import java.util.Set;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.TreeSet;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Set 接口
 * Collection 的子接口，代表无序、不可重复的元素集合
 *
 * 核心特点：
 * - 不可重复：不允许存储重复元素（通过 equals 判断）
 * - 无序：不保证元素的插入顺序（HashSet）
 * - 支持 null：允许存储一个 null 值（HashSet/LinkedHashSet）
 * - 基于 Map 实现：底层都是对应的 Map（HashMap/LinkedHashMap/TreeMap）
 *
 * 核心方法（与 Collection 基本一致，没有 List 的索引方法）：
 * - add          → 添加元素（重复返回 false）
 * - remove       → 删除指定元素
 * - contains     → 判断是否包含
 * - size         → 获取元素个数
 * - isEmpty      → 判断是否为空
 * - clear        → 清空
 * - iterator     → 获取迭代器
 * - toArray      → 转换为数组
 * - addAll       → 批量添加（自动去重）
 * - removeAll    → 批量删除
 * - retainAll    → 保留指定元素
 * - removeIf     → 条件删除
 *
 * 主要实现类：
 * - HashSet       → 基于 HashMap，无序，性能最好 ⭐
 * - LinkedHashSet → 基于 LinkedHashMap，有序（插入顺序）
 * - TreeSet       → 基于 TreeMap，有序（自然排序/比较器排序）
 *
 * 适用场景：
 * - 需要去重
 * - 需要快速查找
 * - 不需要索引访问
 *
 * 与 List 对比：
 * - List：有序、可重复、有索引
 * - Set：无序（大部分）、不可重复、无索引
 */
public class Set_ {

    public static void main(String[] args) {

        // ========== 1. Set 的基本使用 ==========
        System.out.println("========== 1. Set 的基本使用 ==========");

        // Set 是接口，使用 HashSet 实现
        Set<String> set = new HashSet<>();

        // add → 添加元素（重复返回 false）
        System.out.println("添加 'Java'：" + set.add("Java"));
        System.out.println("添加 'Python'：" + set.add("Python"));
        System.out.println("添加 'Java'（重复）：" + set.add("Java"));
        System.out.println("集合：" + set);

        // size → 元素个数
        System.out.println("元素个数：" + set.size());

        // contains → 判断是否包含
        System.out.println("是否包含 'Java'：" + set.contains("Java"));
        System.out.println("是否包含 'C++'：" + set.contains("C++"));

        // remove → 删除元素
        set.remove("Python");
        System.out.println("删除 'Python' 后：" + set);

        // isEmpty → 判断是否为空
        System.out.println("是否为空：" + set.isEmpty());

        // clear → 清空
        set.clear();
        System.out.println("清空后：" + set);

        // ========== 2. Set 自动去重 ==========
        System.out.println("\n========== 2. Set 自动去重 ==========");

        Set<String> duplicateSet = new HashSet<>();

        duplicateSet.add("A");
        duplicateSet.add("B");
        duplicateSet.add("A");  // 重复
        duplicateSet.add("C");
        duplicateSet.add("B");  // 重复
        duplicateSet.add("D");

        System.out.println("添加 A,B,A,C,B,D 后：" + duplicateSet);
        System.out.println("元素个数：" + duplicateSet.size());
        System.out.println("✅ Set 自动去重，重复元素只保留一个");

        // ========== 3. 批量操作与去重 ==========
        System.out.println("\n========== 3. 批量操作与去重 ==========");

        Set<String> batchSet = new HashSet<>();

        // addAll → 批量添加（自动去重）
        batchSet.addAll(Arrays.asList("A", "B", "C", "A", "D", "B", "E"));
        System.out.println("批量添加后：" + batchSet);

        // removeAll → 批量删除
        batchSet.removeAll(Arrays.asList("D", "E"));
        System.out.println("删除 D、E 后：" + batchSet);

        // retainAll → 只保留指定元素
        batchSet.retainAll(Arrays.asList("A", "B", "C", "X"));
        System.out.println("只保留 A、B、C、X 后：" + batchSet);

        // ========== 4. 三种 Set 实现对比 ==========
        System.out.println("\n========== 4. 三种 Set 实现对比 ==========");

        // HashSet（无序）
        Set<String> hashSet = new HashSet<>();
        hashSet.add("B");
        hashSet.add("A");
        hashSet.add("C");
        hashSet.add("D");
        System.out.println("HashSet（无序）：" + hashSet);

        // LinkedHashSet（插入顺序）
        Set<String> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.add("B");
        linkedHashSet.add("A");
        linkedHashSet.add("C");
        linkedHashSet.add("D");
        System.out.println("LinkedHashSet（插入顺序）：" + linkedHashSet);

        // TreeSet（自然排序）
        Set<String> treeSet = new TreeSet<>();
        treeSet.add("B");
        treeSet.add("A");
        treeSet.add("C");
        treeSet.add("D");
        System.out.println("TreeSet（自然排序）：" + treeSet);

        // ========== 5. 遍历方式 ==========
        System.out.println("\n========== 5. 遍历方式 ==========");

        Set<Integer> traverseSet = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5));

        // 方式1：增强 for
        System.out.print("方式1（增强 for）：");
        for (Integer num : traverseSet) {
            System.out.print(num + " ");
        }
        System.out.println();

        // 方式2：Iterator
        System.out.print("方式2（Iterator）：");
        Iterator<Integer> it = traverseSet.iterator();
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }
        System.out.println();

        // 方式3：forEach
        System.out.print("方式3（forEach）：");
        traverseSet.forEach(num -> System.out.print(num + " "));
        System.out.println();

        // ========== 6. 实际应用场景 ==========
        System.out.println("\n========== 6. 实际应用场景 ==========");

        // 场景1：去重
        System.out.println("--- 场景1：数组去重 ---");
        String[] words = {"apple", "banana", "apple", "orange", "banana", "grape"};
        Set<String> uniqueWords = new HashSet<>(Arrays.asList(words));
        System.out.println("原始数组：" + Arrays.toString(words));
        System.out.println("去重后：" + uniqueWords);

        // 场景2：集合运算
        System.out.println("\n--- 场景2：集合运算 ---");
        Set<Integer> set1 = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5));
        Set<Integer> set2 = new HashSet<>(Arrays.asList(4, 5, 6, 7, 8));

        // 交集
        Set<Integer> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);
        System.out.println("set1：" + set1);
        System.out.println("set2：" + set2);
        System.out.println("交集：" + intersection);

        // 并集
        Set<Integer> union = new HashSet<>(set1);
        union.addAll(set2);
        System.out.println("并集：" + union);

        // 差集（set1 - set2）
        Set<Integer> difference = new HashSet<>(set1);
        difference.removeAll(set2);
        System.out.println("差集（set1 - set2）：" + difference);

        // 场景3：判断重复
        System.out.println("\n--- 场景3：判断重复 ---");
        int[] numbers = {1, 2, 3, 4, 2, 5, 6, 3};
        Set<Integer> checkSet = new HashSet<>();

        System.out.print("数组 " + Arrays.toString(numbers) + " 中重复的元素：");
        for (int num : numbers) {
            if (!checkSet.add(num)) {
                System.out.print(num + " ");
            }
        }
        System.out.println();

        // ========== 7. 注意事项 ==========
        System.out.println("\n========== 7. 注意事项 ==========");

        // ⚠️ 注意1：Set 没有索引
        System.out.println("⚠️ Set 没有索引，不能通过 get(index) 访问");
        System.out.println("   必须通过遍历或迭代器访问元素");

        // ⚠️ 注意2：HashSet 不保证顺序
        Set<Integer> orderSet = new HashSet<>();
        orderSet.add(1);
        orderSet.add(2);
        orderSet.add(3);
        orderSet.add(4);
        System.out.println("HashSet 不保证顺序：" + orderSet);

        // ⚠️ 注意3：元素必须正确实现 equals 和 hashCode
        System.out.println("⚠️ HashSet/LinkedHashSet 依赖 equals 和 hashCode");
        System.out.println("   自定义类必须重写这两个方法才能正确去重");

        // ⚠️ 注意4：TreeSet 要求元素可比较
        // class Person { String name; } // 没有实现 Comparable
        // Set<Person> invalid = new TreeSet<>();
        // invalid.add(new Person()); // ❌ ClassCastException

        // ⚠️ 注意5：Set 不是线程安全
        System.out.println("⚠️ HashSet/LinkedHashSet/TreeSet 都不是线程安全的");
        System.out.println("   解决方案：Collections.synchronizedSet() 或 ConcurrentHashMap_.java.newKeySet()");

        // ⚠️ 注意6：null 值支持
        Set<String> nullSet = new HashSet<>();
        nullSet.add(null);
        nullSet.add("A");
        nullSet.add(null);  // 重复 null
        System.out.println("HashSet 允许一个 null：" + nullSet);

        Set<String> treeNullSet = new TreeSet<>();
        // treeNullSet.add(null); // ❌ TreeSet 不允许 null
        System.out.println("⚠️ TreeSet 不允许 null 值");

        // ========== 8. Set vs List ==========
        System.out.println("\n========== 8. Set vs List ==========");

        Set<String> setVsList = new HashSet<>();
        setVsList.add("A");
        setVsList.add("B");
        setVsList.add("A");
        setVsList.add("C");

        System.out.println("Set（自动去重）：" + setVsList);
        System.out.println("Set 没有顺序，不能通过索引访问");

        java.util.List<String> listVsSet = new java.util.ArrayList<>();
        listVsSet.add("A");
        listVsSet.add("B");
        listVsSet.add("A");
        listVsSet.add("C");

        System.out.println("List（允许重复）：" + listVsSet);
        System.out.println("List 有顺序，可以通过索引访问");
    }
}