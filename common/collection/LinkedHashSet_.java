import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

/**
 * LinkedHashSet
 * Set 接口的实现类，基于 LinkedHashMap 实现
 *
 * 继承关系：
 * java.lang.Object
 *   └── java.util.AbstractCollection<E>
 *         └── java.util.AbstractSet<E>
 *               └── java.util.HashSet<E>
 *                     └── java.util.LinkedHashSet<E>
 *
 * 实现接口：
 * - Set<E>                  → 无序、不可重复集合
 * - Collection<E>           → 通过 AbstractCollection 间接实现
 * - Cloneable               → 支持克隆（浅拷贝）
 * - java.io.Serializable    → 支持序列化
 *
 * 核心特点：
 * - 不可重复：不允许存储重复元素（通过 equals 和 hashCode 判断）
 * - 有序：按照元素的插入顺序存储（维护双向链表）
 * - 允许 null：可以存储一个 null 值
 * - 非线程安全：多线程环境下需要额外同步
 * - 性能优秀：基本操作 O(1)，略低于 HashSet
 *
 * 底层数据结构：
 * - 基于 LinkedHashMap（数组 + 双向链表）
 * - 默认初始容量：16
 * - 负载因子：0.75
 * - 扩容机制：当元素个数 > 容量 × 负载因子 时扩容（翻倍）
 * - 多维护一个双向链表来记录插入顺序
 *
 * 核心方法（与 HashSet 完全一致）：
 * - add      → 添加元素（重复返回 false）
 * - remove   → 删除指定元素
 * - contains → 判断是否包含
 * - size     → 获取元素个数
 * - isEmpty  → 判断是否为空
 * - clear    → 清空
 * - iterator → 获取迭代器（按插入顺序）
 * - toArray  → 转换为数组
 *
 * 与 HashSet 对比：
 * - HashSet：无序，性能更高（少维护链表）
 * - LinkedHashSet：有序（插入顺序），性能略低
 *
 * 适用场景：
 * - 需要去重 + 保持插入顺序 ⭐⭐⭐⭐⭐
 * - 需要可预测的迭代顺序 ⭐⭐⭐⭐⭐
 * - LRU 缓存实现（配合 accessOrder） ⭐⭐⭐⭐
 *
 * 注意事项：
 * - 自定义类必须重写 equals 和 hashCode
 * - 迭代器是 fail-fast 的
 * - 不是线程安全的
 */
public class LinkedHashSet_ {

    public static void main(String[] args) {

        // ========== 1. 创建 LinkedHashSet ==========
        System.out.println("========== 1. 创建 LinkedHashSet ==========");

        // 方式1：无参构造（默认容量16，负载因子0.75）
        Set<String> set1 = new LinkedHashSet<>();
        System.out.println("无参构造：" + set1);

        // 方式2：指定初始容量
        Set<String> set2 = new LinkedHashSet<>(100);
        System.out.println("指定容量100：" + set2);

        // 方式3：指定容量和负载因子
        Set<String> set3 = new LinkedHashSet<>(100, 0.8f);
        System.out.println("指定容量100，负载因子0.8：" + set3);

        // 方式4：从其他集合创建
        Set<String> set4 = new LinkedHashSet<>(Arrays.asList("A", "B", "C", "A", "B"));
        System.out.println("从集合创建（自动去重）：" + set4);

        // ========== 2. 基本操作（与 HashSet 相同） ==========
        System.out.println("\n========== 2. 基本操作 ==========");

        LinkedHashSet<String> set = new LinkedHashSet<>();

        // add → 添加元素（重复返回 false）
        System.out.println("添加 'Java'：" + set.add("Java"));
        System.out.println("添加 'Python'：" + set.add("Python"));
        System.out.println("添加 'Java'（重复）：" + set.add("Java"));
        System.out.println("添加 'Go'：" + set.add("Go"));
        System.out.println("集合：" + set);

        // size → 元素个数
        System.out.println("元素个数：" + set.size());

        // contains → 判断是否包含
        System.out.println("是否包含 'Java'：" + set.contains("Java"));
        System.out.println("是否包含 'C++'：" + set.contains("C++"));

        // remove → 删除元素
        boolean removed = set.remove("Python");
        System.out.println("删除 'Python'：" + removed + "，剩余：" + set);

        // clear → 清空
        set.clear();
        System.out.println("清空后：" + set);

        // ========== 3. 核心特性：保持插入顺序 ==========
        System.out.println("\n========== 3. 核心特性：保持插入顺序 ==========");

        // 对比 HashSet 和 LinkedHashSet
        System.out.println("--- HashSet（无序）---");
        Set<Integer> hashSet = new java.util.HashSet<>();
        hashSet.add(3);
        hashSet.add(1);
        hashSet.add(4);
        hashSet.add(1);
        hashSet.add(5);
        hashSet.add(2);
        System.out.println("HashSet：" + hashSet);
        System.out.println("  不保证顺序，每次可能不同");

        System.out.println("\n--- LinkedHashSet（插入顺序）---");
        Set<Integer> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.add(3);
        linkedHashSet.add(1);
        linkedHashSet.add(4);
        linkedHashSet.add(1);  // 重复，不会改变顺序
        linkedHashSet.add(5);
        linkedHashSet.add(2);
        System.out.println("LinkedHashSet：" + linkedHashSet);
        System.out.println("  ✅ 保持插入顺序：3 → 1 → 4 → 5 → 2");

        // 重新插入已存在的元素不会改变顺序
        linkedHashSet.add(3);  // 重复元素
        System.out.println("再次插入 3 后：" + linkedHashSet);
        System.out.println("  ✅ 顺序不变：3 → 1 → 4 → 5 → 2");

        // ========== 4. 自动去重 + 保持顺序 ==========
        System.out.println("\n========== 4. 自动去重 + 保持顺序 ==========");

        String[] words = {"banana", "apple", "banana", "orange", "apple", "grape"};
        LinkedHashSet<String> uniqueWords = new LinkedHashSet<>(Arrays.asList(words));

        System.out.println("原始顺序：" + Arrays.toString(words));
        System.out.println("去重后（保持首次出现顺序）：" + uniqueWords);

        // ========== 5. 遍历方式 ==========
        System.out.println("\n========== 5. 遍历方式 ==========");

        LinkedHashSet<String> traverseSet = new LinkedHashSet<>(Arrays.asList("A", "B", "C", "D", "E"));

        // 方式1：增强 for（按插入顺序）
        System.out.print("方式1（增强 for）：");
        for (String item : traverseSet) {
            System.out.print(item + " ");
        }
        System.out.println();

        // 方式2：Iterator（按插入顺序）
        System.out.print("方式2（Iterator）：");
        Iterator<String> it = traverseSet.iterator();
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }
        System.out.println();

        // 方式3：forEach（按插入顺序）
        System.out.print("方式3（forEach）：");
        traverseSet.forEach(item -> System.out.print(item + " "));
        System.out.println();

        // ========== 6. 批量操作 ==========
        System.out.println("\n========== 6. 批量操作 ==========");

        LinkedHashSet<String> batchSet = new LinkedHashSet<>();

        // addAll → 批量添加（自动去重，保持首次出现顺序）
        batchSet.addAll(Arrays.asList("A", "B", "C", "A", "D", "B", "E"));
        System.out.println("批量添加后：" + batchSet);

        // removeAll → 批量删除
        batchSet.removeAll(Arrays.asList("D", "E"));
        System.out.println("删除 D、E 后：" + batchSet);

        // retainAll → 只保留指定元素
        batchSet.retainAll(Arrays.asList("A", "B", "C", "X"));
        System.out.println("只保留 A、B、C、X 后：" + batchSet);

        // ========== 7. 自定义对象去重 + 保持顺序 ==========
        System.out.println("\n========== 7. 自定义对象去重 + 保持顺序 ==========");

        LinkedHashSet<Person> people = new LinkedHashSet<>();

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
        System.out.println("  插入顺序：");
        int index = 1;
        for (Person p : people) {
            System.out.println("    " + index++ + ". " + p);
        }
        System.out.println("✅ 去重 + 保持首次插入顺序");

        // ========== 8. 实际应用场景 ==========
        System.out.println("\n========== 8. 实际应用场景 ==========");

        // 场景1：去重并保留原始顺序
        System.out.println("--- 场景1：去重并保留原始顺序 ---");
        String[] logs = {
                "用户登录", "查看页面", "用户登录", "点击按钮",
                "查看页面", "退出登录", "用户登录"
        };
        LinkedHashSet<String> uniqueLogs = new LinkedHashSet<>(Arrays.asList(logs));
        System.out.println("原始日志：" + Arrays.toString(logs));
        System.out.println("去重后（保留操作顺序）：");
        for (String log : uniqueLogs) {
            System.out.println("  " + log);
        }

        // 场景2：保持插入顺序的缓存
        System.out.println("\n--- 场景2：保持插入顺序的缓存 ---");
        LinkedHashSet<String> cache = new LinkedHashSet<>();
        cache.add("缓存A");
        cache.add("缓存B");
        cache.add("缓存C");
        System.out.println("缓存内容（插入顺序）：" + cache);

        // 访问缓存不会改变顺序
        cache.contains("缓存B");
        System.out.println("访问 '缓存B' 后：" + cache);
        System.out.println("  ✅ 顺序不变");

        // 场景3：URL 去重（保留访问顺序）
        System.out.println("\n--- 场景3：URL 去重（保留访问顺序）---");
        String[] urls = {
                "/home",
                "/product",
                "/home",      // 重复
                "/about",
                "/product",   // 重复
                "/contact",
                "/home"       // 重复
        };
        LinkedHashSet<String> uniqueUrls = new LinkedHashSet<>(Arrays.asList(urls));
        System.out.println("用户访问 URL（去重后）：");
        int step = 1;
        for (String url : uniqueUrls) {
            System.out.println("  " + step++ + ". " + url);
        }

        // 场景4：LRU 缓存模拟（通过重写）
        System.out.println("\n--- 场景4：LRU 缓存模拟 ---");
        // 使用 LinkedHashSet 无法直接实现 LRU（没有访问顺序功能）
        // 但可以使用 LinkedHashMap（后面会学）
        System.out.println("⚠️ LinkedHashSet 只维护插入顺序，不维护访问顺序");
        System.out.println("   LRU 缓存需要 LinkedHashMap（accessOrder=true）");

        // ========== 9. 性能对比 ==========
        System.out.println("\n========== 9. 性能对比 ==========");

        int testSize = 100000;

        // HashSet 插入
        Set<Integer> hashSetPerf = new java.util.HashSet<>();
        long start = System.nanoTime();
        for (int i = 0; i < testSize; i++) {
            hashSetPerf.add(i);
        }
        long end = System.nanoTime();
        System.out.println("HashSet 插入 " + testSize + " 个元素：" + (end - start) / 1000000 + "ms");

        // LinkedHashSet 插入
        Set<Integer> linkedSetPerf = new LinkedHashSet<>();
        start = System.nanoTime();
        for (int i = 0; i < testSize; i++) {
            linkedSetPerf.add(i);
        }
        end = System.nanoTime();
        System.out.println("LinkedHashSet 插入 " + testSize + " 个元素：" + (end - start) / 1000000 + "ms");

        System.out.println("✅ LinkedHashSet 性能略低于 HashSet（维护链表的开销）");

        // ========== 10. 注意事项 ==========
        System.out.println("\n========== 10. 注意事项 ==========");

        // ⚠️ 注意1：顺序是插入顺序，不是访问顺序
        LinkedHashSet<Integer> orderTest = new LinkedHashSet<>();
        orderTest.add(3);
        orderTest.add(1);
        orderTest.add(2);
        orderTest.add(3);  // 重复，不会移动
        System.out.println("⚠️ 重复元素不会改变顺序：" + orderTest);

        // ⚠️ 注意2：必须重写 equals 和 hashCode
        System.out.println("⚠️ 自定义类作为元素时，必须重写 equals 和 hashCode");
        System.out.println("   否则 LinkedHashSet 无法正确去重");

        // ⚠️ 注意3：不是线程安全
        System.out.println("⚠️ LinkedHashSet 不是线程安全的");
        System.out.println("   解决方案：Collections.synchronizedSet(new LinkedHashSet<>())");

        // ⚠️ 注意4：迭代器是 fail-fast
        LinkedHashSet<String> failFastSet = new LinkedHashSet<>(Arrays.asList("A", "B", "C"));
        try {
            Iterator<String> failIt = failFastSet.iterator();
            while (failIt.hasNext()) {
                String item = failIt.next();
                if (item.equals("B")) {
                    failFastSet.remove("C");
                }
            }
        } catch (java.util.ConcurrentModificationException e) {
            System.out.println("⚠️ 遍历时修改集合抛出 ConcurrentModificationException");
        }

        // ✅ 正确删除
        LinkedHashSet<String> correctSet = new LinkedHashSet<>(Arrays.asList("A", "B", "C"));
        Iterator<String> correctIt = correctSet.iterator();
        while (correctIt.hasNext()) {
            if (correctIt.next().equals("B")) {
                correctIt.remove();
            }
        }
        System.out.println("✅ 使用 Iterator.remove() 安全删除：" + correctSet);

        // ⚠️ 注意5：允许一个 null
        LinkedHashSet<String> nullSet = new LinkedHashSet<>();
        nullSet.add(null);
        nullSet.add("A");
        nullSet.add(null);
        System.out.println("LinkedHashSet 允许一个 null：" + nullSet);

        // ⚠️ 注意6：选择建议
        System.out.println("\n选择建议：");
        System.out.println("  - 不需要顺序，追求性能         → HashSet");
        System.out.println("  - 需要保持插入顺序            → LinkedHashSet ✅");
        System.out.println("  - 需要排序（自然/自定义）     → TreeSet");
    }

    // ========== 内部类 ==========

    /**
     * Person 类（正确重写 equals 和 hashCode）
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
}