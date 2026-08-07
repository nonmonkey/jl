package common;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Arrays 工具类
 * Java 提供的操作数组的静态方法工具类
 *
 * 核心特点：
 * - 全部是静态方法
 * - 操作所有类型的数组（基本类型 + 引用类型）
 * - 提供排序、查找、填充、转换等功能
 *
 * 核心方法分类：
 *
 * 【排序】
 * - sort()           → 排序（升序）
 * - parallelSort()   → 并行排序（多线程）
 *
 * 【查找】
 * - binarySearch()   → 二分查找（必须先排序）
 *
 * 【填充】
 * - fill()           → 填充数组
 *
 * 【转换】
 * - asList()         → 数组转 List
 * - toString()       → 数组转字符串
 * - deepToString()   → 多维数组转字符串
 * - toArray()        → List 转数组（集合框架）
 *
 * 【比较】
 * - equals()         → 比较数组是否相等
 * - deepEquals()     → 比较多维数组
 *
 * 【哈希】
 * - hashCode()       → 获取数组的 hashCode
 * - deepHashCode()   → 获取多维数组的 hashCode
 *
 * 【复制】
 * - copyOf()         → 复制数组（指定新长度）
 * - copyOfRange()    → 复制数组的一部分
 *
 * 【其他】
 * - stream()         → 转为 Stream（Java 8）
 * - setAll()         → 批量设置（Java 8）
 *
 * 适用场景：
 * - 数组排序 ⭐⭐⭐⭐⭐
 * - 数组查找 ⭐⭐⭐⭐
 * - 数组转 List ⭐⭐⭐⭐⭐
 * - 打印数组 ⭐⭐⭐⭐⭐
 */
public class Arrays_ {

    public static void main(String[] args) {

        // ========== 1. 排序 ==========
        System.out.println("========== 1. 排序 ==========");

        // 1.1 基本类型排序
        int[] numbers = {5, 2, 8, 1, 9, 3, 7};
        System.out.println("排序前：" + Arrays.toString(numbers));

        Arrays.sort(numbers);
        System.out.println("sort 升序：" + Arrays.toString(numbers));

        // 1.2 引用类型排序（需要 Comparable）
        String[] names = {"Banana", "Apple", "Cherry", "Date"};
        Arrays.sort(names);
        System.out.println("sort 字符串：" + Arrays.toString(names));

        // 1.3 自定义排序（Comparator）
        Integer[] nums = {5, 2, 8, 1, 9, 3, 7};
        Arrays.sort(nums, (a, b) -> b - a);
        System.out.println("sort 降序：" + Arrays.toString(nums));

        // 1.4 部分排序
        int[] partial = {5, 2, 8, 1, 9, 3, 7};
        Arrays.sort(partial, 1, 5);  // 排序 [1, 5) 区间
        System.out.println("部分排序 [1, 5)：" + Arrays.toString(partial));

        // 1.5 并行排序（多核 CPU 下性能更好）
        int[] bigArray = new int[100000];
        for (int i = 0; i < bigArray.length; i++) {
            bigArray[i] = (int) (Math.random() * 100000);
        }
        long start = System.nanoTime();
        Arrays.parallelSort(bigArray);
        long end = System.nanoTime();
        System.out.println("parallelSort 耗时：" + (end - start) / 1000000 + "ms");

        // ========== 2. 二分查找 ==========
        System.out.println("\n========== 2. 二分查找 ==========");

        int[] sorted = {1, 3, 5, 7, 9, 11, 13, 15};

        // binarySearch() → 必须已排序
        int index1 = Arrays.binarySearch(sorted, 7);
        System.out.println("binarySearch(7)：" + index1);  // 3

        int index2 = Arrays.binarySearch(sorted, 6);
        System.out.println("binarySearch(6)（不存在）：" + index2);
        // 返回 -(插入点) - 1 = -(4) - 1 = -5
        // 插入点：6 应该插入在索引 4 的位置

        // 部分查找
        int index3 = Arrays.binarySearch(sorted, 1, 7, 5);
        System.out.println("binarySearch(1, 7, 5)：" + index3);

        // ========== 3. 填充 ==========
        System.out.println("\n========== 3. 填充 ==========");

        int[] fillArray = new int[5];

        // fill() → 全部填充
        Arrays.fill(fillArray, 10);
        System.out.println("fill(10)：" + Arrays.toString(fillArray));

        // fill() → 部分填充
        Arrays.fill(fillArray, 1, 4, 5);
        System.out.println("fill(1, 4, 5)：" + Arrays.toString(fillArray));

        // ========== 4. 数组转字符串 ==========
        System.out.println("\n========== 4. 数组转字符串 ==========");

        int[] arr = {1, 2, 3, 4, 5};

        // toString() → 一维数组转字符串
        System.out.println("toString：" + Arrays.toString(arr));

        // deepToString() → 多维数组转字符串
        int[][] multi = {{1, 2}, {3, 4}, {5, 6}};
        System.out.println("deepToString（多维）：" + Arrays.deepToString(multi));

        // ========== 5. 数组比较 ==========
        System.out.println("\n========== 5. 数组比较 ==========");

        int[] arr1 = {1, 2, 3, 4, 5};
        int[] arr2 = {1, 2, 3, 4, 5};
        int[] arr3 = {1, 2, 3, 4, 6};

        // equals() → 比较一维数组
        System.out.println("equals(arr1, arr2)：" + Arrays.equals(arr1, arr2));  // true
        System.out.println("equals(arr1, arr3)：" + Arrays.equals(arr1, arr3));  // false

        // deepEquals() → 比较多维数组
        int[][] multi1 = {{1, 2}, {3, 4}};
        int[][] multi2 = {{1, 2}, {3, 4}};
        int[][] multi3 = {{1, 2}, {3, 5}};

        System.out.println("deepEquals(multi1, multi2)：" + Arrays.deepEquals(multi1, multi2));  // true
        System.out.println("deepEquals(multi1, multi3)：" + Arrays.deepEquals(multi1, multi3));  // false

        // 对象数组比较（需要重写 equals）
        Person[] p1 = {new Person("张三", 20)};
        Person[] p2 = {new Person("张三", 20)};
        System.out.println("equals 对象数组：" + Arrays.equals(p1, p2));  // true（Person 重写了 equals）

        // ========== 6. 数组转 List ==========
        System.out.println("\n========== 6. 数组转 List ==========");

        String[] strArray = {"A", "B", "C"};

        // asList() → 数组转 List
        List<String> list = Arrays.asList(strArray);
        System.out.println("asList：" + list);

        // ⚠️ 注意：asList 返回的是固定大小的 List
        // list.add("D"); // ❌ UnsupportedOperationException
        System.out.println("⚠️ asList 返回的 List 是固定大小的，不能 add/remove");

        // ✅ 正确方式：包装成 ArrayList
        List<String> mutableList = new ArrayList<>(Arrays.asList(strArray));
        mutableList.add("D");
        System.out.println("包装成 ArrayList：" + mutableList);

        // asList 是视图，修改原数组会影响 List
        strArray[0] = "Z";
        System.out.println("修改原数组后，List：" + list);
        System.out.println("⚠️ asList 是视图，修改原数组会影响 List");

        // ========== 7. 数组复制 ==========
        System.out.println("\n========== 7. 数组复制 ==========");

        int[] original = {1, 2, 3, 4, 5};

        // copyOf() → 复制数组（指定新长度）
        int[] copy1 = Arrays.copyOf(original, 3);
        System.out.println("copyOf(original, 3)：" + Arrays.toString(copy1));

        int[] copy2 = Arrays.copyOf(original, 10);
        System.out.println("copyOf(original, 10)：" + Arrays.toString(copy2));

        // copyOfRange() → 复制部分
        int[] copy3 = Arrays.copyOfRange(original, 1, 4);
        System.out.println("copyOfRange(original, 1, 4)：" + Arrays.toString(copy3));

        // ========== 8. 批量设置（Java 8） ==========
        System.out.println("\n========== 8. 批量设置（Java 8） ==========");

        int[] setArray = new int[5];

        // setAll() → 批量设置
        Arrays.setAll(setArray, i -> i * 2);
        System.out.println("setAll(i -> i * 2)：" + Arrays.toString(setArray));

        // ========== 9. 转为 Stream ==========
        System.out.println("\n========== 9. 转为 Stream ==========");

        // stream() → 数组转 Stream
        String[] streamArray = {"A", "B", "C"};
        Arrays.stream(streamArray)
                .filter(s -> !s.equals("B"))
                .forEach(s -> System.out.print(s + " "));
        System.out.println();

        // 基本类型 Stream
        int[] intArray = {1, 2, 3, 4, 5};
        int sum = Arrays.stream(intArray).sum();
        System.out.println("stream(intArray).sum()：" + sum);

        // ========== 10. hashCode ==========
        System.out.println("\n========== 10. hashCode ==========");

        int[] hashArr1 = {1, 2, 3};
        int[] hashArr2 = {1, 2, 3};

        // hashCode() → 数组的 hashCode
        System.out.println("hashCode(arr1)：" + Arrays.hashCode(hashArr1));
        System.out.println("hashCode(arr2)：" + Arrays.hashCode(hashArr2));
        System.out.println("两个数组 hashCode 是否相等：" + (Arrays.hashCode(hashArr1) == Arrays.hashCode(hashArr2)));

        // deepHashCode() → 多维数组
        int[][] deepArr1 = {{1, 2}, {3, 4}};
        int[][] deepArr2 = {{1, 2}, {3, 4}};
        System.out.println("deepHashCode：" + Arrays.deepHashCode(deepArr1));

        // ========== 11. 实际应用场景 ==========
        System.out.println("\n========== 11. 实际应用场景 ==========");

        // 场景1：数组排序 + 查找
        System.out.println("--- 场景1：数组排序 + 查找 ---");
        int[] scores = {85, 92, 78, 95, 88, 70, 90};
        Arrays.sort(scores);
        System.out.println("排序后：" + Arrays.toString(scores));

        int position = Arrays.binarySearch(scores, 88);
        System.out.println("88 分的位置：" + position);

        // 场景2：打印数组
        System.out.println("\n--- 场景2：打印数组 ---");
        String[] logArray = {"日志1", "日志2", "日志3"};
        System.out.println("日志：" + Arrays.toString(logArray));

        // 场景3：数组初始化
        System.out.println("\n--- 场景3：数组初始化 ---");
        boolean[] flags = new boolean[10];
        Arrays.fill(flags, true);
        System.out.println("fill true：" + Arrays.toString(flags));

        // 场景4：数组转 List 并处理
        System.out.println("\n--- 场景4：数组转 List ---");
        String[] data = {"apple", "banana", "orange", "grape"};
        List<String> fruitList = Arrays.asList(data);

        // 排序
        fruitList.sort(String::compareTo);
        System.out.println("排序后：" + fruitList);

        // 场景5：多维数组处理
        System.out.println("\n--- 场景5：多维数组处理 ---");
        int[][] matrix = {
                {3, 1, 4},
                {1, 5, 9},
                {2, 6, 5}
        };

        System.out.println("原始矩阵：");
        for (int[] row : matrix) {
            Arrays.sort(row);
            System.out.println("  " + Arrays.toString(row));
        }

        // ========== 12. 注意事项 ==========
        System.out.println("\n========== 12. 注意事项 ==========");

        // ⚠️ 注意1：binarySearch 必须先排序
        int[] unsorted = {5, 2, 8, 1, 9};
        // int result = Arrays.binarySearch(unsorted, 2); // ❌ 结果不可靠
        System.out.println("⚠️ binarySearch 必须先排序，否则结果不正确");

        // ⚠️ 注意2：asList 返回的是固定大小 List
        List<String> fixedList = Arrays.asList("A", "B", "C");
        // fixedList.add("D"); // ❌ UnsupportedOperationException
        // fixedList.remove(0); // ❌ UnsupportedOperationException
        fixedList.set(0, "Z");  // ✅ 可以修改元素
        System.out.println("⚠️ asList 可以修改元素（set），但不能 add/remove");

        // ⚠️ 注意3：asList 是视图
        String[] viewArray = {"A", "B", "C"};
        List<String> viewList = Arrays.asList(viewArray);
        viewArray[0] = "Z";
        System.out.println("⚠️ asList 是视图，修改原数组影响 List：" + viewList);

        // ⚠️ 注意4：基本类型数组不能直接转 List
        int[] primitive = {1, 2, 3};
        // List<int> list = Arrays.asList(primitive); // ❌ 编译错误
        // asList(primitive) 会把整个数组当作一个元素
        List<int[]> primitiveList = Arrays.asList(primitive);
        System.out.println("⚠️ 基本类型数组 asList 会把数组当作一个元素");

        // ✅ 正确方式：使用 Stream
        List<Integer> intList = Arrays.stream(primitive).boxed().collect(Collectors.toList());
        System.out.println("Stream 转换：" + intList);

        // ⚠️ 注意5：对象数组的 equals 比较
        Person[] p3 = {new Person("张三", 20)};
        Person[] p4 = {new Person("张三", 20)};
        // Arrays.equals 会调用元素的 equals
        System.out.println("⚠️ Arrays.equals 依赖元素的 equals 方法");

        // ========== 13. Arrays vs Collections vs Objects ==========
        System.out.println("\n========== 13. Arrays vs Collections vs Objects ==========");

        System.out.println("┌─────────────┬─────────────┬─────────────┬─────────────┐");
        System.out.println("│  工具类     │   Arrays    │ Collections │   Objects   │");
        System.out.println("├─────────────┼─────────────┼─────────────┼─────────────┤");
        System.out.println("│ 操作对象    │  数组       │  集合       │  任意对象   │");
        System.out.println("│ 排序        │  ✅          │  ✅          │  ❌          │");
        System.out.println("│ 二分查找    │  ✅          │  ✅          │  ❌          │");
        System.out.println("│ 填充        │  ✅          │  ✅          │  ❌          │");
        System.out.println("│ 转 List     │  ✅          │  ❌          │  ❌          │");
        System.out.println("│ 转字符串    │  ✅          │  ❌          │  ✅          │");
        System.out.println("│ 判空        │  ❌          │  ❌          │  ✅          │");
        System.out.println("│ equals      │  ✅          │  ❌          │  ✅          │");
        System.out.println("└─────────────┴─────────────┴─────────────┴─────────────┘");
    }

    // ========== 内部类 ==========

    /**
     * Person 类（重写 equals）
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
            return name + "(" + age + ")";
        }
    }
}