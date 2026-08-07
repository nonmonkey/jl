import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.ListIterator;

/**
 * ArrayList
 * List 接口的实现类，基于动态数组实现
 *
 * 继承关系：
 * java.lang.Object
 *   └── java.util.AbstractCollection<E>
 *         └── java.util.AbstractList<E>
 *               └── java.util.ArrayList<E>
 *
 * 实现接口：
 * - List<E>                 → 有序集合，支持索引访问
 * - RandomAccess            → 支持快速随机访问（标记接口）
 * - Cloneable               → 支持克隆（浅拷贝）
 * - java.io.Serializable    → 支持序列化
 *
 * 核心特点：
 * - 有序：元素按照插入顺序存储
 * - 可重复：允许存储重复元素
 * - 支持 null：可以存储 null 值
 * - 随机访问快：通过索引访问元素的时间复杂度 O(1)
 * - 插入/删除慢：中间位置插入或删除需要移动元素，时间复杂度 O(n)
 * - 尾部插入/删除快：时间复杂度 O(1)（扩容除外）
 * - 非线程安全：多线程环境下需要额外同步
 *
 * 底层数据结构：
 * - Object[] 数组
 * - 默认初始容量：10
 * - 扩容机制：新容量 = 旧容量 + 旧容量 >> 1（即 1.5 倍）
 *
 * 核心方法：
 * - ensureCapacity → 手动扩容（提前分配空间）
 * - trimToSize     → 将容量调整为当前元素个数（节省内存）
 * - clone          → 浅拷贝
 *
 * 适用场景：
 * - 频繁随机访问（get/set）
 * - 尾部插入/删除较多
 * - 不需要线程安全
 *
 * 与 LinkedList 对比：
 * - ArrayList：随机访问快，增删慢（中间位置）
 * - LinkedList：随机访问慢，增删快（首尾位置）
 */
public class ArrayList_ {

    public static void main(String[] args) {

        // ========== 1. 创建 ArrayList ==========
        System.out.println("========== 1. 创建 ArrayList ==========");

        // 方式1：无参构造（默认容量 10）
        List<String> list1 = new ArrayList<>();
        System.out.println("无参构造（容量 10）：" + list1);

        // 方式2：指定初始容量（性能优化）
        List<String> list2 = new ArrayList<>(20);
        System.out.println("指定容量 20：" + list2);

        // 方式3：从其他集合创建
        List<String> list3 = new ArrayList<>(Arrays.asList("A", "B", "C"));
        System.out.println("从集合创建：" + list3);

        // ========== 2. 添加元素 ==========
        System.out.println("\n========== 2. 添加元素 ==========");

        ArrayList<String> list = new ArrayList<>();

        // add → 尾部添加
        list.add("苹果");
        list.add("香蕉");
        list.add("橙子");
        System.out.println("尾部添加：" + list);

        // add → 指定位置插入
        list.add(1, "葡萄");
        System.out.println("索引1插入'葡萄'：" + list);

        // addAll → 批量添加
        List<String> fruits = Arrays.asList("草莓", "西瓜");
        list.addAll(fruits);
        System.out.println("批量添加：" + list);

        // addAll → 指定位置批量添加
        list.addAll(2, Arrays.asList("芒果", "桃子"));
        System.out.println("索引2批量添加：" + list);

        // ========== 3. 获取元素 ==========
        System.out.println("\n========== 3. 获取元素 ==========");

        // get → 通过索引获取（O(1)）
        System.out.println("索引0：" + list.get(0));
        System.out.println("索引2：" + list.get(2));
        System.out.println("索引5：" + list.get(5));

        // indexOf → 获取第一次出现的索引
        System.out.println("'香蕉'第一次出现位置：" + list.indexOf("香蕉"));
        System.out.println("'芒果'位置（不存在）：" + list.indexOf("芒果"));

        // lastIndexOf → 获取最后一次出现的索引
        list.add("苹果");
        System.out.println("列表：" + list);
        System.out.println("'苹果'最后一次出现位置：" + list.lastIndexOf("苹果"));

        // ========== 4. 修改元素 ==========
        System.out.println("\n========== 4. 修改元素 ==========");

        System.out.println("修改前：" + list);
        String oldValue = list.set(2, "哈密瓜");
        System.out.println("将索引2改为'哈密瓜'，被替换的是：" + oldValue);
        System.out.println("修改后：" + list);

        // ========== 5. 删除元素 ==========
        System.out.println("\n========== 5. 删除元素 ==========");

        ArrayList<String> delList = new ArrayList<>(Arrays.asList("A", "B", "C", "D", "E", "B"));

        // remove → 按索引删除
        String removed = delList.remove(2);
        System.out.println("删除索引2（" + removed + "）：" + delList);

        // remove → 按对象删除（只删除第一个匹配的）
        boolean isRemoved = delList.remove("B");
        System.out.println("删除'B'（" + isRemoved + "）：" + delList);

        // removeAll → 删除所有匹配的元素
        delList.removeAll(Arrays.asList("D", "E"));
        System.out.println("删除 D 和 E：" + delList);

        // removeIf → 条件删除（Java 8）
        delList.removeIf(item -> item.equals("B"));
        System.out.println("删除所有 B：" + delList);

        // clear → 清空所有
        delList.clear();
        System.out.println("清空后：" + delList);
        System.out.println("是否为空：" + delList.isEmpty());

        // ========== 6. 遍历方式 ==========
        System.out.println("\n========== 6. 遍历方式 ==========");

        ArrayList<String> traverseList = new ArrayList<>(Arrays.asList("Java", "Python", "Go", "Rust"));

        // 方式1：普通 for + 索引（最快）
        System.out.print("方式1（普通 for + 索引）：");
        for (int i = 0; i < traverseList.size(); i++) {
            System.out.print(traverseList.get(i) + " ");
        }
        System.out.println();

        // 方式2：增强 for
        System.out.print("方式2（增强 for）：");
        for (String lang : traverseList) {
            System.out.print(lang + " ");
        }
        System.out.println();

        // 方式3：Iterator
        System.out.print("方式3（Iterator）：");
        Iterator<String> it = traverseList.iterator();
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }
        System.out.println();

        // 方式4：ListIterator（可以双向遍历）
        System.out.print("方式4（ListIterator 正向）：");
        ListIterator<String> listIt = traverseList.listIterator();
        while (listIt.hasNext()) {
            System.out.print(listIt.next() + " ");
        }
        System.out.println();

        System.out.print("方式4（ListIterator 反向）：");
        while (listIt.hasPrevious()) {
            System.out.print(listIt.previous() + " ");
        }
        System.out.println();

        // 方式5：forEach
        System.out.print("方式5（forEach）：");
        traverseList.forEach(lang -> System.out.print(lang + " "));
        System.out.println();

        // ========== 7. 排序 ==========
        System.out.println("\n========== 7. 排序 ==========");

        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(5, 2, 8, 1, 9, 3));

        // sort → 自然排序（升序）
        numbers.sort(null);
        System.out.println("自然排序（升序）：" + numbers);

        // sort → 降序排序
        numbers.sort((a, b) -> b - a);
        System.out.println("降序排序：" + numbers);

        // Collections.sort
        ArrayList<String> words = new ArrayList<>(Arrays.asList("banana", "apple", "cherry", "date"));
        Collections.sort(words);
        System.out.println("Collections.sort 排序：" + words);

        // ========== 8. 容量操作 ==========
        System.out.println("\n========== 8. 容量操作 ==========");

        // ArrayList 特有方法（需要通过 ArrayList 类型引用）

        // ensureCapacity → 手动扩容（提前分配空间，避免多次扩容）
        ArrayList<String> capList = new ArrayList<>();
        capList.ensureCapacity(100);  // 预分配 100 容量
        System.out.println("确保容量至少 100");

        // trimToSize → 将容量调整为当前元素个数
        capList.add("A");
        capList.add("B");
        capList.add("C");
        capList.trimToSize();  // 容量从 100 缩减到 3
        System.out.println("trimToSize 后：" + capList);

        // ========== 9. clone（浅拷贝） ==========
        System.out.println("\n========== 9. clone（浅拷贝） ==========");

        ArrayList<String> original = new ArrayList<>(Arrays.asList("A", "B", "C"));
        @SuppressWarnings("unchecked")
        ArrayList<String> cloned = (ArrayList<String>) original.clone();

        System.out.println("原始列表：" + original);
        System.out.println("克隆列表：" + cloned);

        // 修改克隆不影响原始
        cloned.add("D");
        System.out.println("克隆添加 D 后：" + cloned);
        System.out.println("原始列表不变：" + original);

        // ========== 10. 性能对比演示 ==========
        System.out.println("\n========== 10. 性能对比演示 ==========");

        // 尾部插入（ArrayList 擅长）
        ArrayList<Integer> tailList = new ArrayList<>();
        long start = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            tailList.add(i);
        }
        long end = System.nanoTime();
        System.out.println("尾部插入 10 万元素耗时：" + (end - start) / 1000000 + "ms");

        // 头部插入（ArrayList 不擅长）
        start = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            tailList.add(0, i);
        }
        end = System.nanoTime();
        System.out.println("头部插入 1 万元素耗时：" + (end - start) / 1000000 + "ms");
        System.out.println("⚠️ 中间/头部插入越靠前，性能越差（需要移动元素）");

        // ========== 11. 注意事项 ==========
        System.out.println("\n========== 11. 注意事项 ==========");

        // ⚠️ 注意1：ArrayList 允许 null
        ArrayList<String> nullList = new ArrayList<>();
        nullList.add(null);
        nullList.add("A");
        nullList.add(null);
        System.out.println("包含 null 的列表：" + nullList);

        // ⚠️ 注意2：ArrayList 不是线程安全的
        System.out.println("⚠️ ArrayList 不是线程安全的");
        System.out.println("   解决方案1：Collections.synchronizedList(new ArrayList<>())");
        System.out.println("   解决方案2：CopyOnWriteArrayList（读多写少）");

        // ⚠️ 注意3：频繁扩容影响性能
        System.out.println("⚠️ 频繁扩容会影响性能，建议预估容量");
        System.out.println("   ArrayList<String> list = new ArrayList<>(1000);  // 预分配");

        // ⚠️ 注意4：remove 操作会移动元素
        ArrayList<Integer> shiftList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        shiftList.remove(2);  // 删除索引2，后面的元素会前移
        System.out.println("删除索引2后：" + shiftList);
        System.out.println("⚠️ 删除中间元素会导致后续元素整体前移");

        // ⚠️ 注意5：subList 是视图
        ArrayList<String> viewList = new ArrayList<>(Arrays.asList("A", "B", "C", "D", "E"));
        List<String> sub = viewList.subList(1, 3);
        sub.set(0, "X");
        System.out.println("修改子列表后，原列表：" + viewList);
        System.out.println("⚠️ subList 是原列表的视图，修改会相互影响");
    }
}