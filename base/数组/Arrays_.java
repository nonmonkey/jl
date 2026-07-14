import java.util.List;

public class Arrays_ {
    public static void main(String[] args) {
        int[] arr = {5, 2, 9, 1};
        int[] arr2 = {1, 2, 5, 9};
        Integer[] integerArr = {5, 2, 9, 1};
        String[] strArr = {"apple", "banana", "cherry"};

        // ==================== 一、base.打印 ====================
        System.out.println("\n========== 一、base.打印 ==========");
        // toString()：将数组转换为字符串
        System.out.println("toString: " + java.util.Arrays.toString(arr));
        // deepToString()：打印多维数组内容
        int[][] multiArr = {{1, 2}, {3, 4}};
        System.out.println("deepToString: " + java.util.Arrays.deepToString(multiArr));

        // ==================== 二、排序 ====================
        System.out.println("\n========== 二、排序 ==========");
        // sort()：对数组升序排序（原地修改）
        java.util.Arrays.sort(arr);
        System.out.println("sort后: " + java.util.Arrays.toString(arr));
        // parallelSort()：并行排序，大数据量时性能更好
        int[] bigArr = {5, 2, 9, 1};
        java.util.Arrays.parallelSort(bigArr);
        System.out.println("parallelSort后: " + java.util.Arrays.toString(bigArr));
        // sort() 指定范围排序 [fromIndex, toIndex)
        int[] rangeArr = {5, 2, 9, 1, 7};
        java.util.Arrays.sort(rangeArr, 1, 4);
        System.out.println("范围排序[1,4): " + java.util.Arrays.toString(rangeArr));

        // ==================== 三、查找 ====================
        System.out.println("\n========== 三、查找 ==========");
        // binarySearch()：二分查找（数组必须已排序），返回索引，未找到返回负数
        int index = java.util.Arrays.binarySearch(arr, 5);
        System.out.println("二分查找5的位置: " + index);
        // binarySearch() 指定范围查找
        int indexRange = java.util.Arrays.binarySearch(arr, 0, 3, 2);
        System.out.println("在[0,3)查找2: " + indexRange);

        // ==================== 四、比较 ====================
        System.out.println("\n========== 四、比较 ==========");
        // equals()：比较两个数组是否相等（长度和元素都相同）
        boolean isEqual = java.util.Arrays.equals(arr, arr2);
        System.out.println("arr与arr2相等? " + isEqual);
        // deepEquals()：比较多维数组是否相等
        int[][] multi1 = {{1, 2}, {3, 4}};
        int[][] multi2 = {{1, 2}, {3, 4}};
        System.out.println("多维数组相等? " + java.util.Arrays.deepEquals(multi1, multi2));

        // ==================== 五、填充 ====================
        System.out.println("\n========== 五、填充 ==========");
        // fill()：用指定值填充数组（原地修改）
        int[] fillArr = new int[5];
        java.util.Arrays.fill(fillArr, 10);
        System.out.println("fill填充10: " + java.util.Arrays.toString(fillArr));
        // fill() 指定范围填充
        java.util.Arrays.fill(fillArr, 1, 4, 99);
        System.out.println("范围填充[1,4)=99: " + java.util.Arrays.toString(fillArr));

        // ==================== 六、复制 ====================
        System.out.println("\n========== 六、复制 ==========");
        // copyOf()：复制数组（可指定新长度，截断或补默认值）
        int[] copyArr1 = java.util.Arrays.copyOf(arr, 3);
        System.out.println("copyOf前3个: " + java.util.Arrays.toString(copyArr1));
        int[] copyArr2 = java.util.Arrays.copyOf(arr, 6);
        System.out.println("copyOf扩容到6: " + java.util.Arrays.toString(copyArr2));
        // copyOfRange()：复制指定范围 [fromIndex, toIndex)
        int[] copyRange = java.util.Arrays.copyOfRange(arr, 1, 3);
        System.out.println("copyOfRange[1,3): " + java.util.Arrays.toString(copyRange));

        // ==================== 七、转集合 ====================
        System.out.println("\n========== 七、转集合 ==========");
        // asList()：将数组转为List（注意：基本类型数组需用包装类）
        List<String> strList = java.util.Arrays.asList(strArr);
        System.out.println("asList: " + strList);
        List<Integer> intList = java.util.Arrays.asList(integerArr);
        System.out.println("asList(包装类): " + intList);

        // ==================== 八、流操作 ====================
        System.out.println("\n========== 八、流操作 ==========");
        // stream()：将数组转为Stream（基本类型有专用流）
        int sum = java.util.Arrays.stream(arr).sum();
        System.out.println("stream求和: " + sum);
        // 指定范围流
        int sumRange = java.util.Arrays.stream(arr, 0, 3).sum();
        System.out.println("stream[0,3)求和: " + sumRange);

        // ==================== 九、设置默认值 ====================
        System.out.println("\n========== 九、设置默认值 ==========");
        // setAll()：根据索引设置值
        int[] setArr = new int[5];
        java.util.Arrays.setAll(setArr, i -> i * 2);
        System.out.println("setAll(索引*2): " + java.util.Arrays.toString(setArr));
        // parallelSetAll()：并行版本
        java.util.Arrays.parallelSetAll(setArr, i -> i * 3);
        System.out.println("parallelSetAll(索引*3): " + java.util.Arrays.toString(setArr));

        // ==================== 十、杂项 ====================
        System.out.println("\n========== 十、杂项 ==========");
        // hashCode()：获取数组哈希码
        System.out.println("arr的hashCode: " + java.util.Arrays.hashCode(arr));
        // deepHashCode()：多维数组哈希码
        System.out.println("多维数组hashCode: " + java.util.Arrays.deepHashCode(multiArr));
        // mismatch()：返回两个数组首次不匹配的位置
        int[] a = {1, 2, 3, 4};
        int[] b = {1, 2, 5, 4};
        System.out.println("首次不匹配位置: " + java.util.Arrays.mismatch(a, b));
    }
}