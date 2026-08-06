import java.util.Collection;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Collection 接口
 * Java 集合框架的根接口之一，定义了集合操作的基本规范
 *
 * 核心特点：
 * - 是 List、Set、Queue 的父接口
 * - 定义了集合的通用方法：添加、删除、查找、遍历等
 * - 不提供具体实现，只定义规范
 * - 子接口各自扩展了不同的特性（有序/无序、可重复/不可重复）
 *
 * 核心方法：
 * 一、添加操作
 * - add          → 添加元素
 * - addAll       → 批量添加
 * 二、删除操作
 * - remove       → 删除指定元素
 * - removeAll    → 批量删除
 * - removeIf     → 批量删除
 * - clear        → 清空所有元素
 * 三、查找操作
 * - contains     → 判断是否包含某个元素
 * - containsAll  → 判断是否包含所有元素
 * - size         → 获取元素个数
 * - isEmpty      → 判断是否为空
 * 四、遍历操作
 * - iterator     → 获取迭代器
 * 四、其他操作
 * - toArray      → 转为指定类型数组
 * - retainAll    → 只保留指定集合中的元素
 * - equals       → 比较两个集合是否相等
 * - hashCode     → 获取集合的哈希码
 *
 * 注意事项：
 * - Collection 是接口，不能直接实例化
 * - 所有子类都必须实现这些方法
 * - 某些实现可能不支持所有操作（如 UnsupportedOperationException）
 */
public class Collection_ {

    public static void main(String[] args) {

        // ========== 1. Collection 的基本使用（以 ArrayList 为例） ==========
        System.out.println("========== 1. Collection 的基本使用 ==========");

        Collection<String> collection = new ArrayList<>();

        // add → 添加元素
        collection.add("Java");
        collection.add("Python");
        collection.add("JavaScript");
        System.out.println("添加元素后：" + collection);

        // size → 获取元素个数
        System.out.println("元素个数：" + collection.size());

        // isEmpty → 判断是否为空
        System.out.println("是否为空：" + collection.isEmpty());

        // contains → 判断是否包含某个元素
        System.out.println("是否包含 'Java'：" + collection.contains("Java"));
        System.out.println("是否包含 'C++'：" + collection.contains("C++"));

        // ========== 2. 批量操作 ==========
        System.out.println("\n========== 2. 批量操作 ==========");

        Collection<String> collection2 = new ArrayList<>();
        collection2.add("Java");
        collection2.add("Go");
        collection2.add("Rust");

        // addAll → 批量添加
        Collection<String> result = new ArrayList<>(collection);
        result.addAll(collection2);
        System.out.println("addAll 后：" + result);

        // containsAll → 判断是否包含所有元素
        System.out.println("是否包含 collection2 所有元素：" + result.containsAll(collection2));

        // removeIf → 条件删除（Java 8）
        result.removeIf(item -> item.equals("Go"));
        System.out.println("removeIf 删除所有 Go：" + result);

        // removeAll → 批量删除
        result.removeAll(collection2);
        System.out.println("removeAll 后：" + result);

        // ========== 3. 删除操作 ==========
        System.out.println("\n========== 3. 删除操作 ==========");

        Collection<String> delCollection = new ArrayList<>();
        delCollection.add("A");
        delCollection.add("B");
        delCollection.add("C");
        delCollection.add("D");
        System.out.println("原始集合：" + delCollection);

        // remove → 删除指定元素
        boolean isRemoved = delCollection.remove("B");
        System.out.println("删除 'B' 是否成功：" + isRemoved);
        System.out.println("删除后：" + delCollection);

        // removeAll → 删除指定集合中的所有元素
        delCollection.removeAll(Arrays.asList("C", "D"));
        System.out.println("删除 C 和 D 后：" + delCollection);

        // clear → 清空所有元素
        delCollection.clear();
        System.out.println("清空后：" + delCollection);
        System.out.println("是否为空：" + delCollection.isEmpty());

        // ========== 4. 保留操作 ==========
        System.out.println("\n========== 4. 保留操作 ==========");

        Collection<String> retainCollection = new ArrayList<>();
        retainCollection.add("A");
        retainCollection.add("B");
        retainCollection.add("C");
        retainCollection.add("D");
        retainCollection.add("E");
        System.out.println("原始集合：" + retainCollection);

        // retainAll → 只保留指定集合中的元素
        retainCollection.retainAll(Arrays.asList("B", "D", "F"));
        System.out.println("只保留 B、D、F 后：" + retainCollection);

        // ========== 5. 遍历方式 ==========
        System.out.println("\n========== 5. 遍历方式 ==========");

        Collection<String> traverseCollection = new ArrayList<>();
        traverseCollection.add("苹果");
        traverseCollection.add("香蕉");
        traverseCollection.add("橙子");

        // 方式1：增强 for 循环
        System.out.print("方式1（增强 for）：");
        for (String item : traverseCollection) {
            System.out.print(item + " ");
        }
        System.out.println();

        // 方式2：Iterator 迭代器
        System.out.print("方式2（Iterator）：");
        Iterator<String> it = traverseCollection.iterator();
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }
        System.out.println();

        // 方式3：Java 8 forEach
        System.out.print("方式3（forEach）：");
        traverseCollection.forEach(item -> System.out.print(item + " "));
        System.out.println();

        // ========== 6. 转换为数组 ==========
        System.out.println("\n========== 6. 转换为数组 ==========");

        Collection<String> arrCollection = new ArrayList<>();
        arrCollection.add("A");
        arrCollection.add("B");
        arrCollection.add("C");

        // toArray → 转为 Object[] 数组
        Object[] objArray = arrCollection.toArray();
        System.out.println("Object[] 数组：" + Arrays.toString(objArray));

        // toArray → 转为指定类型数组
        String[] strArray = arrCollection.toArray(new String[0]);
        System.out.println("String[] 数组：" + Arrays.toString(strArray));

        // ========== 7. Collection 与 Set ==========
        System.out.println("\n========== 7. Collection 与 Set ==========");

        Collection<String> setCollection = new HashSet<>();
        setCollection.add("Java");
        setCollection.add("Python");
        setCollection.add("Java");
        System.out.println("HashSet 中的元素（自动去重）：" + setCollection);
        System.out.println("Set 的大小：" + setCollection.size());

        // ========== 8. Collection 的通用操作 ==========
        System.out.println("\n========== 8. Collection 的通用操作 ==========");

        Collection<String> col1 = new ArrayList<>(Arrays.asList("A", "B", "C"));
        Collection<String> col2 = new ArrayList<>(Arrays.asList("A", "B", "C"));
        Collection<String> col3 = new ArrayList<>(Arrays.asList("A", "B", "D"));

        // equals → 比较两个集合是否相等
        System.out.println("col1 和 col2 是否相等：" + col1.equals(col2));
        System.out.println("col1 和 col3 是否相等：" + col1.equals(col3));

        // hashCode → 获取集合的哈希码
        System.out.println("col1 的 hashCode：" + col1.hashCode());
        System.out.println("col2 的 hashCode：" + col2.hashCode());

        // ========== 9. 注意事项 ==========
        System.out.println("\n========== 9. 注意事项 ==========");

        // ⚠️ 注意1：Collection 是接口，不能实例化
        // Collection<String> c = new Collection<>(); // ❌ 编译错误
        System.out.println("✅ Collection 是接口，使用具体实现类实例化");

        // ⚠️ 注意2：某些操作可能不支持
        Collection<String> fixed = Arrays.asList("A", "B", "C");
        // fixed.add("D"); // ❌ 会抛出 UnsupportedOperationException
        System.out.println("⚠️ Arrays.asList() 返回的 Collection 不支持 add/remove");

        // ✅ 正确方式
        Collection<String> mutable = new ArrayList<>(Arrays.asList("A", "B", "C"));
        mutable.add("D");
        System.out.println("可变的 Collection：" + mutable);

        // ⚠️ 注意3：Collection 不保证顺序（由子类决定）
        System.out.println("⚠️ Collection 本身不保证顺序，由具体实现决定");
        System.out.println("   List：有序（按插入顺序）");
        System.out.println("   Set：无序（HashSet）或有序（TreeSet）");
    }
}
