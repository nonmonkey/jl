import java.util.List;
import java.util.Arrays;

/**
 * Arrays 工具类使用示例
 *
 * 核心方法：
 * toString()        → 打印一维数组
 * deepToString()    → 打印多维数组
 * sort()            → 升序排序（支持全量/范围/并行）
 * binarySearch()    → 二分查找（支持全量/范围）
 * equals()          → 比较数组
 * deepEquals()      → 比较多维数组
 * fill()            → 填充数组（支持全量/范围）
 * copyOf()          → 复制数组
 * copyOfRange()     → 复制指定范围
 * asList()          → 转 List 集合
 * stream()          → 转流操作
 * setAll()          → 按索引设置值（支持并行）
 * hashCode()        → 获取数组哈希码（支持多维）
 * mismatch()        → 找首次不匹配位置
 */
public class Arrays_ {
    public static void main(String[] args) {
        int[] arr = {5, 2, 9, 1};
        int[] arr2 = {1, 2, 5, 9};
        Integer[] integerArr = {5, 2, 9, 1};
        String[] strArr = {"apple", "banana", "cherry"};

        // ==================== 一、base.打印 ====================
        System.out.println("\n========== 一、base.打印 ==========");
        // toString()：将数组转换为字符串
        System.out.println("toString: " + Arrays.toString(arr));
        // deepToString()：打印多维数组内容
        int[][] multiArr = {{1, 2}, {3, 4}};
        System.out.println("deepToString: " + Arrays.deepToString(multiArr));

        // ==================== 二、排序 ====================
        System.out.println("\n========== 二、排序 ==========");
        // sort()：对数组升序排序（原地修改）
        Arrays.sort(arr);
        System.out.println("sort后: " + Arrays.toString(arr));
        // parallelSort()：并行排序，大数据量时性能更好
        int[] bigArr = {5, 2, 9, 1};
        Arrays.parallelSort(bigArr);
        System.out.println("parallelSort后: " + Arrays.toString(bigArr));
        // sort() 指定范围排序 [fromIndex, toIndex)
        int[] rangeArr = {5, 2, 9, 1, 7};
        Arrays.sort(rangeArr, 1, 4);
        System.out.println("范围排序[1,4): " + Arrays.toString(rangeArr));

        // ==================== 三、查找 ====================
        System.out.println("\n========== 三、查找 ==========");
        // binarySearch()：二分查找（数组必须已排序），返回索引，未找到返回负数
        int index = Arrays.binarySearch(arr, 5);
        System.out.println("二分查找5的位置: " + index);
        // binarySearch() 指定范围查找
        int indexRange = Arrays.binarySearch(arr, 0, 3, 2);
        System.out.println("在[0,3)查找2: " + indexRange);

        // ==================== 四、比较 ====================
        System.out.println("\n========== 四、比较 ==========");
        // equals()：比较两个数组是否相等（长度和元素都相同）
        boolean isEqual = Arrays.equals(arr, arr2);
        System.out.println("arr与arr2相等? " + isEqual);
        // deepEquals()：比较多维数组是否相等
        int[][] multi1 = {{1, 2}, {3, 4}};
        int[][] multi2 = {{1, 2}, {3, 4}};
        System.out.println("多维数组相等? " + Arrays.deepEquals(multi1, multi2));

        // ==================== 五、填充 ====================
        System.out.println("\n========== 五、填充 ==========");
        // fill()：用指定值填充数组（原地修改）
        int[] fillArr = new int[5];
        Arrays.fill(fillArr, 10);
        System.out.println("fill填充10: " + Arrays.toString(fillArr));
        // fill() 指定范围填充
        Arrays.fill(fillArr, 1, 4, 99);
        System.out.println("范围填充[1,4)=99: " + Arrays.toString(fillArr));

        // ==================== 六、复制 ====================
        System.out.println("\n========== 六、复制 ==========");
        // copyOf()：复制数组（可指定新长度，截断或补默认值）
        int[] copyArr1 = Arrays.copyOf(arr, 3);
        System.out.println("copyOf前3个: " + Arrays.toString(copyArr1));
        int[] copyArr2 = Arrays.copyOf(arr, 6);
        System.out.println("copyOf扩容到6: " + Arrays.toString(copyArr2));
        // copyOfRange()：复制指定范围 [fromIndex, toIndex)
        int[] copyRange = Arrays.copyOfRange(arr, 1, 3);
        System.out.println("copyOfRange[1,3): " + Arrays.toString(copyRange));

        // ==================== 七、转集合 ====================
        System.out.println("\n========== 七、转集合 ==========");
        // asList()：将数组转为List（注意：基本类型数组需用包装类）
        List<String> strList = Arrays.asList(strArr);
        System.out.println("asList: " + strList);
        List<Integer> intList = Arrays.asList(integerArr);
        System.out.println("asList(包装类): " + intList);

        // ==================== 八、流操作 ====================
        System.out.println("\n========== 八、流操作 ==========");
        // stream()：将数组转为Stream（基本类型有专用流）
        int sum = Arrays.stream(arr).sum();
        System.out.println("stream求和: " + sum);
        // 指定范围流
        int sumRange = Arrays.stream(arr, 0, 3).sum();
        System.out.println("stream[0,3)求和: " + sumRange);

        // ==================== 九、设置默认值 ====================
        System.out.println("\n========== 九、设置默认值 ==========");
        // setAll()：根据索引设置值
        int[] setArr = new int[5];
        Arrays.setAll(setArr, i -> i * 2);
        System.out.println("setAll(索引*2): " + Arrays.toString(setArr));
        // parallelSetAll()：并行版本
        Arrays.parallelSetAll(setArr, i -> i * 3);
        System.out.println("parallelSetAll(索引*3): " + Arrays.toString(setArr));

        // ==================== 十、杂项 ====================
        System.out.println("\n========== 十、杂项 ==========");
        // hashCode()：获取数组哈希码
        System.out.println("arr的hashCode: " + Arrays.hashCode(arr));
        // deepHashCode()：多维数组哈希码
        System.out.println("多维数组hashCode: " + Arrays.deepHashCode(multiArr));
        // mismatch()：返回两个数组首次不匹配的位置
        int[] a = {1, 2, 3, 4};
        int[] b = {1, 2, 5, 4};
        System.out.println("首次不匹配位置: " + Arrays.mismatch(a, b));
    }
}