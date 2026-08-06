import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.ListIterator;

/**
 * List 接口
 * Collection 的子接口，代表有序、可重复的元素集合
 *
 * 核心特点：
 * - 有序：元素按照插入顺序存储，每个元素都有索引（从0开始）
 * - 可重复：允许存储重复元素（equals 比较返回 true）
 * - 支持 null：允许存储 null 值
 * - 可以通过索引精确访问、插入、删除元素
 * - 可以控制元素的位置
 *
 * 核心方法（Collection 基础上增加）：
 * - get          → 根据索引获取元素
 * - set          → 替换指定索引的元素
 * - add          → 在指定索引插入元素
 * - remove       → 删除指定索引的元素
 * - indexOf      → 获取元素第一次出现的索引
 * - lastIndexOf  → 获取元素最后一次出现的索引
 * - subList      → 获取子列表视图
 * - listIterator → 获取列表迭代器（可以双向遍历）
 * - sort         → 排序
 *
 * 主要实现类：
 * - ArrayList    → 基于动态数组，随机访问快，增删慢（尾部除外）
 * - LinkedList   → 基于双向链表，增删快（首尾除外），随机访问慢
 * - Vector       → 类似 ArrayList，但线程安全（旧版，不推荐）
 * - Stack        → 继承 Vector，实现栈（旧版，不推荐）
 *
 * 适用场景：
 * - 需要保持元素的插入顺序
 * - 需要通过索引访问元素
 * - 允许存储重复元素
 */
public class List_ {

    public static void main(String[] args) {

        // ========== 1. 创建 List ==========
        System.out.println("========== 1. 创建 List ==========");

        // 方式1：ArrayList（最常用）
        List<String> list1 = new ArrayList<>();
        System.out.println("ArrayList：" + list1);

        // 方式2：指定初始容量
        List<String> list2 = new ArrayList<>(20);
        System.out.println("指定容量 20：" + list2);

        // 方式3：从其他集合创建
        List<String> list3 = new ArrayList<>(Arrays.asList("A", "B", "C"));
        System.out.println("从集合创建：" + list3);

        // 方式4：LinkedList
        List<String> list4 = new LinkedList<>();
        System.out.println("LinkedList：" + list4);

        // ========== 2. 添加元素 ==========
        System.out.println("\n========== 2. 添加元素 ==========");

        List<String> list = new ArrayList<>();

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

        // get → 通过索引获取
        System.out.println("索引0：" + list.get(0));
        System.out.println("索引2：" + list.get(2));

        // indexOf → 获取第一次出现的索引
        System.out.println("'香蕉'第一次出现位置：" + list.indexOf("香蕉"));
        System.out.println("'榴莲'位置（不存在）：" + list.indexOf("榴莲"));

        // lastIndexOf → 获取最后一次出现的索引
        list.add("苹果");
        System.out.println("列表：" + list);
        System.out.println("'苹果'最后一次出现位置：" + list.lastIndexOf("苹果"));

        // contains → 判断是否包含
        System.out.println("是否包含'西瓜'：" + list.contains("西瓜"));
        System.out.println("是否包含'芒果'：" + list.contains("芒果"));

        // size → 获取元素个数
        System.out.println("元素个数：" + list.size());

        // isEmpty → 判断是否为空
        System.out.println("是否为空：" + list.isEmpty());

        // ========== 4. 修改元素 ==========
        System.out.println("\n========== 4. 修改元素 ==========");

        System.out.println("修改前：" + list);
        String oldValue = list.set(2, "哈密瓜");
        System.out.println("将索引2改为'哈密瓜'，被替换的是：" + oldValue);
        System.out.println("修改后：" + list);

        // ========== 5. 删除元素 ==========
        System.out.println("\n========== 5. 删除元素 ==========");

        List<String> delList = new ArrayList<>(Arrays.asList("A", "B", "C", "D", "E", "B"));

        // remove → 按索引删除
        String removed = delList.remove(2);
        System.out.println("删除索引2（" + removed + "）：" + delList);

        // remove → 按对象删除（只删除第一个匹配的）
        boolean isRemoved = delList.remove("B");
        System.out.println("删除'B'（" + isRemoved + "）：" + delList);

        // removeAll → 删除所有匹配的元素
        delList.removeAll(Arrays.asList("D", "E"));
        System.out.println("删除 D 和 E：" + delList);

        // clear → 清空所有
        delList.clear();
        System.out.println("清空后：" + delList);

        // ========== 6. 子列表 ==========
        System.out.println("\n========== 6. 子列表 ==========");

        List<String> subList = new ArrayList<>(Arrays.asList("A", "B", "C", "D", "E", "F"));

        // subList → 获取子列表（包左不包右）
        List<String> sub = subList.subList(1, 4);
        System.out.println("原列表：" + subList);
        System.out.println("子列表 [1, 4)：" + sub);

        // ⚠️ 注意：子列表是原列表的视图，修改子列表会影响原列表
        sub.set(0, "X");
        System.out.println("修改子列表后，原列表：" + subList);

        // ========== 7. 遍历方式 ==========
        System.out.println("\n========== 7. 遍历方式 ==========");

        List<String> traverseList = Arrays.asList("Java", "Python", "Go", "Rust");

        // 方式1：普通 for + 索引（List 特有）
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

        // 方式5：forEach + Lambda
        System.out.print("方式5（forEach）：");
        traverseList.forEach(lang -> System.out.print(lang + " "));
        System.out.println();

        // ========== 8. 排序 ==========
        System.out.println("\n========== 8. 排序 ==========");

        List<Integer> numbers = new ArrayList<>(Arrays.asList(5, 2, 8, 1, 9, 3));

        // sort → 自然排序（升序）
        numbers.sort(null);
        System.out.println("自然排序（升序）：" + numbers);

        // sort → 降序排序
        numbers.sort((a, b) -> b - a);
        System.out.println("降序排序：" + numbers);

        // Collections.sort
        List<String> words = new ArrayList<>(Arrays.asList("banana", "apple", "cherry", "date"));
        java.util.Collections.sort(words);
        System.out.println("Collections.sort 排序：" + words);

        // ========== 9. ListIterator 高级用法 ==========
        System.out.println("\n========== 9. ListIterator 高级用法 ==========");

        List<String> iteratorList = new ArrayList<>(Arrays.asList("A", "B", "C", "D"));
        System.out.println("原始列表：" + iteratorList);

        ListIterator<String> lit = iteratorList.listIterator();
        while (lit.hasNext()) {
            String item = lit.next();
            if (item.equals("B")) {
                lit.set("X");  // 替换当前元素
                System.out.println("  将 B 替换为 X");
            }
            if (item.equals("C")) {
                lit.add("Y");  // 在当前元素后插入
                System.out.println("  在 C 后插入 Y");
            }
        }
        System.out.println("修改后：" + iteratorList);

        // ========== 10. 转换为数组 ==========
        System.out.println("\n========== 10. 转换为数组 ==========");

        List<String> arrList = Arrays.asList("A", "B", "C");

        // toArray → 转为 Object[] 数组
        Object[] objArray = arrList.toArray();
        System.out.println("Object[] 数组：" + Arrays.toString(objArray));

        // toArray → 转为指定类型数组
        String[] strArray = arrList.toArray(new String[0]);
        System.out.println("String[] 数组：" + Arrays.toString(strArray));

        // ========== 11. 注意事项 ==========
        System.out.println("\n========== 11. 注意事项 ==========");

        // ⚠️ 注意1：Arrays.asList() 返回的列表是固定大小的
        List<String> fixedList = Arrays.asList("A", "B", "C");
        System.out.println("Arrays.asList() 创建的列表：" + fixedList);
        // fixedList.add("D"); // ❌ 会抛出 UnsupportedOperationException
        System.out.println("⚠️ Arrays.asList() 返回的列表不支持 add/remove 操作");

        // ✅ 正确方式
        List<String> mutableList = new ArrayList<>(Arrays.asList("A", "B", "C"));
        mutableList.add("D");
        System.out.println("包装后的可变列表：" + mutableList);

        // ⚠️ 注意2：List 允许 null
        List<String> nullList = new ArrayList<>();
        nullList.add(null);
        nullList.add("A");
        nullList.add(null);
        System.out.println("包含 null 的列表：" + nullList);

        // ⚠️ 注意3：List 不是线程安全的
        System.out.println("⚠️ ArrayList/LinkedList 都不是线程安全的");
        System.out.println("   解决方案：Collections.synchronizedList() 或 CopyOnWriteArrayList");

        // ⚠️ 注意4：List 的 equals 比较的是元素顺序和内容
        List<String> l1 = Arrays.asList("A", "B", "C");
        List<String> l2 = Arrays.asList("A", "B", "C");
        List<String> l3 = Arrays.asList("C", "B", "A");

        List<String> l22 = new ArrayList<>(l2);
        l22.sort((a, b) -> b.compareTo(a));

        System.out.println("l1.equals(l2)：" + l1.equals(l2));
        System.out.println("l1.equals(l22)：" + l1.equals(l22));
        System.out.println("l1.equals(l3)：" + l1.equals(l3));
    }
}