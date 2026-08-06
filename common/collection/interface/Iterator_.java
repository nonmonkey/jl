import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ConcurrentModificationException;

/**
 * Iterator 迭代器
 * 集合的遍历工具
 * 就像一个“探针”或“游标”，可以逐个访问集合中的元素，同时保证遍历过程中的安全性
 *
 * 核心方法：
 * - hasNext   → 问“还有吗？”
 * - next      → 取下一个
 * - remove    → 删除当前（安全删除）
 */
public class Iterator_ {

    public static void main(String[] args) {

        // ========== 1. 基本遍历 ==========
        System.out.println("========== 1. 基本遍历 ==========");

        List<String> names = new ArrayList<>();
        names.add("张三");
        names.add("李四");
        names.add("王五");

        // 获取迭代器
        Iterator<String> it = names.iterator();

        // 循环遍历
        while (it.hasNext()) {
            String name = it.next();
            System.out.println("名字：" + name);
        }

        // ⚠️ 注意：迭代器是一次性的，遍历完后不能再使用
        // it.next(); // ❌ 会抛出 NoSuchElementException

        // ========== 2. 遍历时安全删除 ==========
        System.out.println("\n========== 2. 遍历时安全删除 ==========");

        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        System.out.println("原始列表：" + list);

        // 删除所有等于 "B" 或 "C" 的元素
        Iterator<String> it2 = list.iterator();
        while (it2.hasNext()) {
            String item = it2.next();
            if (item.equals("B") || item.equals("C")) {
                it2.remove();  // ✅ 安全删除
                System.out.println("  删除了：" + item);
            }
        }
        System.out.println("删除后列表：" + list);

        // ========== 3. 错误示范：遍历时直接删除 ==========
        System.out.println("\n========== 3. 错误示范：遍历时直接删除 ==========");

        List<String> errorList = new ArrayList<>();
        errorList.add("A");
        errorList.add("B");
        errorList.add("C");

        try {
            for (String item : errorList) {
                if (item.equals("A")) {
                    System.out.println("item = " + item);
                    errorList.remove(item); // ❌ 取消注释会抛 ConcurrentModificationException
                }
            }
        } catch (ConcurrentModificationException e) {
            System.out.println("❌ 报错了！不能在遍历时直接修改集合：" + e.getClass().getSimpleName());
            System.out.println("  原因：增强 for 循环底层用的是 Iterator，");
            System.out.println("  但集合自己的 remove() 不会通知 Iterator，导致状态不一致。");
        }

        // ========== 4. 正确的删除方式 ==========
        System.out.println("\n========== 4. 正确的删除方式 ==========");

        List<String> correctList = new ArrayList<>();
        correctList.add("A");
        correctList.add("B");
        correctList.add("C");

        System.out.println("原始：" + correctList);

        // 方式1：用 Iterator.remove()（推荐）
        Iterator<String> it3 = correctList.iterator();
        while (it3.hasNext()) {
            if (it3.next().equals("B")) {
                it3.remove();
            }
        }
        System.out.println("方式1（Iterator.remove()）：" + correctList);

        // 方式2：用 removeIf()（Java 8，更简洁）
        List<String> list2 = new ArrayList<>(Arrays.asList("A", "B", "C"));
        list2.removeIf(item -> item.equals("B"));
        System.out.println("方式2（removeIf()）：" + list2);

        // ========== 5. 遍历不同集合 ==========
        System.out.println("\n========== 5. 遍历不同集合 ==========");

        // Set
        Set<Integer> set = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5));
        Iterator<Integer> itSet = set.iterator();
        System.out.print("Set 遍历：");
        while (itSet.hasNext()) {
            System.out.print(itSet.next() + " ");
        }
        System.out.println();

        // Map（Map 本身没有 Iterator，需要通过 entrySet()）
        Map<String, Integer> map = new HashMap<>();
        map.put("语文", 90);
        map.put("数学", 95);
        map.put("英语", 88);

        Iterator<Map.Entry<String, Integer>> itMap = map.entrySet().iterator();
        System.out.println("Map 遍历：");
        while (itMap.hasNext()) {
            Map.Entry<String, Integer> entry = itMap.next();
            System.out.println("  " + entry.getKey() + " = " + entry.getValue());
        }

        // ========== 6. 增强 for 循环 vs Iterator ==========
        System.out.println("\n========== 6. 增强 for 循环 vs Iterator ==========");

        List<String> list3 = Arrays.asList("A", "B", "C");

        // 方式1：增强 for 循环（代码简洁，但不能删除）
        System.out.print("增强 for 循环：");
        for (String item : list3) {
            System.out.print(item + " ");
        }
        System.out.println();

        // 方式2：Iterator（代码稍多，但功能更强）
        System.out.print("Iterator：");
        Iterator<String> it4 = list3.iterator();
        while (it4.hasNext()) {
            System.out.print(it4.next() + " ");
        }
        System.out.println();

        // ========== 7. 迭代器是一次性的 ==========
        System.out.println("\n========== 7. 迭代器是一次性的 ==========");

        List<String> list4 = Arrays.asList("A", "B", "C");
        Iterator<String> it5 = list4.iterator();

        System.out.println("第一次遍历：");
        while (it5.hasNext()) {
            System.out.print(it5.next() + " ");
        }
        System.out.println();

        System.out.println("第二次遍历（不会执行，因为迭代器已经到末尾了）：");
        while (it5.hasNext()) {
            System.out.print(it5.next() + " ");  // 不会执行
        }
        System.out.println("（没有输出）");

        // 需要重新获取迭代器
        Iterator<String> it6 = list4.iterator();
        System.out.print("重新获取迭代器后：");
        while (it6.hasNext()) {
            System.out.print(it6.next() + " ");
        }
        System.out.println();
    }
}