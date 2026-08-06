import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Comparator;
import java.util.Arrays;
import java.util.Iterator;

/**
 * PriorityQueue
 * Queue 接口的实现类，基于优先级堆（Priority Heap）实现的无界优先级队列
 *
 * 继承关系：
 * java.lang.Object
 *   └── java.util.AbstractCollection<E>
 *         └── java.util.AbstractQueue<E>
 *               └── java.util.PriorityQueue<E>
 *
 * 实现接口：
 * - Queue<E>                 → 队列
 * - Collection<E>            → 通过 AbstractCollection 间接实现
 * - java.io.Serializable     → 支持序列化
 *
 * 核心特点：
 * - 优先级排序：元素按照优先级顺序出队（不是 FIFO）
 * - 默认小顶堆：优先级最高（最小）的元素最先出队
 * - 不允许 null：不能存储 null 值
 * - 非线程安全：多线程环境下需要额外同步
 * - 无界队列：容量可自动扩容
 *
 * 底层数据结构：
 * - 二叉堆（完全二叉树）
 * - 默认初始容量：11
 * - 扩容机制：当容量 < 64 时翻倍，否则增加 50%
 * - 存储方式：数组（父节点索引 = (i-1)/2，子节点 = 2i+1 / 2i+2）
 *
 * 核心方法：
 *
 * 【队列操作】
 * - offer    → 入队（插入元素）
 * - poll     → 出队（获取并删除头部）
 * - peek     → 查看队首（不删除）
 * - remove   → 删除指定元素
 * - contains → 判断是否包含
 * - size     → 获取元素个数
 * - clear    → 清空
 * - iterator → 遍历（不保证顺序）
 *
 * 【自定义排序】
 * - 自然排序：元素实现 Comparable 接口
 * - 比较器排序：传入 Comparator
 *
 * 适用场景：
 * - 任务调度（按优先级执行）
 * - 求 Top K 问题
 * - 合并 K 个有序链表
 * - 求中位数
 * - Dijkstra 最短路径算法
 *
 * 注意事项：
 * - 迭代器不保证顺序
 * - 不是 FIFO 队列
 * - 线程不安全
 */
public class PriorityQueue_ {

    public static void main(String[] args) {

        // ========== 1. 创建 PriorityQueue ==========
        System.out.println("========== 1. 创建 PriorityQueue ==========");

        // 方式1：默认自然排序（小顶堆）
        Queue<Integer> pq1 = new PriorityQueue<>();
        System.out.println("默认容量 11：" + pq1);

        // 方式2：指定初始容量
        Queue<Integer> pq2 = new PriorityQueue<>(20);
        System.out.println("指定容量 20：" + pq2);

        // 方式3：从其他集合创建
        Queue<Integer> pq3 = new PriorityQueue<>(Arrays.asList(5, 2, 8, 1, 9, 3));
        System.out.println("从集合创建：" + pq3);

        // 方式4：自定义比较器（大顶堆）
        Queue<Integer> pq4 = new PriorityQueue<>((a, b) -> b - a);
        pq4.offer(1);
        pq4.offer(5);
        pq4.offer(8);
        pq4.offer(3);
        System.out.println("自定义比较器（大顶堆）：" + pq4);

        // ========== 2. 基本操作 ==========
        System.out.println("\n========== 2. 基本操作 ==========");

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        // offer → 入队
        pq.offer(5);
        pq.offer(1);
        pq.offer(3);
        pq.offer(2);
        pq.offer(4);
        System.out.println("入队 5,1,3,2,4 后：" + pq);
        System.out.println("注意：打印顺序不是优先级顺序，内部是堆结构");

        // peek → 查看队首（不删除）
        System.out.println("队首元素：" + pq.peek());

        // size → 元素个数
        System.out.println("元素个数：" + pq.size());

        // contains → 判断是否包含
        System.out.println("是否包含 3：" + pq.contains(3));
        System.out.println("是否包含 6：" + pq.contains(6));

        // poll → 出队（按优先级）
        System.out.print("按优先级出队：");
        while (!pq.isEmpty()) {
            System.out.print(pq.poll() + " ");
        }
        System.out.println();

        // ========== 3. 自然排序（默认小顶堆） ==========
        System.out.println("\n========== 3. 自然排序（默认小顶堆） ==========");

        PriorityQueue<Integer> naturalPq = new PriorityQueue<>();
        naturalPq.addAll(Arrays.asList(10, 3, 7, 1, 9, 5, 8, 2, 4, 6));

        System.out.print("自然排序出队（从小到大）：");
        while (!naturalPq.isEmpty()) {
            System.out.print(naturalPq.poll() + " ");
        }
        System.out.println();

        // ========== 4. 自定义比较器（大顶堆） ==========
        System.out.println("\n========== 4. 自定义比较器（大顶堆） ==========");

        // 方式1：Lambda 表达式
        PriorityQueue<Integer> maxPq = new PriorityQueue<>((a, b) -> b - a);

        // 方式2：Comparator.reverseOrder()
        // PriorityQueue<Integer> maxPq = new PriorityQueue<>(Comparator.reverseOrder());

        maxPq.addAll(Arrays.asList(10, 3, 7, 1, 9, 5, 8, 2, 4, 6));

        System.out.print("大顶堆出队（从大到小）：");
        while (!maxPq.isEmpty()) {
            System.out.print(maxPq.poll() + " ");
        }
        System.out.println();

        // ========== 5. 自定义对象排序 ==========
        System.out.println("\n========== 5. 自定义对象排序 ==========");

        // 任务类实现 Comparable
        PriorityQueue<Task> taskQueue = new PriorityQueue<>();

        taskQueue.offer(new Task("高优先级任务", 10));
        taskQueue.offer(new Task("低优先级任务", 1));
        taskQueue.offer(new Task("中优先级任务", 5));

        System.out.println("任务队列（按优先级从高到低）：");
        while (!taskQueue.isEmpty()) {
            System.out.println("  " + taskQueue.poll());
        }

        // ========== 6. 实际应用场景 ==========
        System.out.println("\n========== 6. 实际应用场景 ==========");

        // 场景1：任务调度（按优先级执行）
        System.out.println("--- 场景1：任务调度 ---");
        PriorityQueue<Task> scheduler = new PriorityQueue<>();

        scheduler.offer(new Task("紧急修复", 10));
        scheduler.offer(new Task("日常维护", 3));
        scheduler.offer(new Task("性能优化", 7));
        scheduler.offer(new Task("功能开发", 5));

        System.out.println("执行任务（按优先级）：");
        while (!scheduler.isEmpty()) {
            Task task = scheduler.poll();
            System.out.println("  执行：" + task);
        }

        // 场景2：求 Top K 大元素
        System.out.println("\n--- 场景2：求 Top K 大元素 ---");
        int[] nums = {3, 1, 5, 7, 2, 8, 4, 9, 6};
        int k = 3;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int num : nums) {
            minHeap.offer(num);
            if (minHeap.size() > k) {
                minHeap.poll();  // 移除最小的
            }
        }

        System.out.println("数组：" + Arrays.toString(nums));
        System.out.println("Top " + k + " 大元素：" + minHeap);

        // 场景3：合并 K 个有序数组
        System.out.println("\n--- 场景3：合并 K 个有序数组 ---");

        // 定义三个有序数组
        int[][] arrays = {
                {1, 4, 7, 10},
                {2, 5, 8, 11},
                {3, 6, 9, 12}
        };

        // 使用优先队列合并
        PriorityQueue<Node> mergeHeap = new PriorityQueue<>(
                (a, b) -> a.value - b.value
        );

        // 初始化：每个数组的第一个元素入队
        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i].length > 0) {
                mergeHeap.offer(new Node(i, 0, arrays[i][0]));
            }
        }

        System.out.print("合并结果：");
        while (!mergeHeap.isEmpty()) {
            Node node = mergeHeap.poll();
            System.out.print(node.value + " ");

            // 如果当前数组还有下一个元素，入队
            int nextIndex = node.index + 1;
            if (nextIndex < arrays[node.arrayIndex].length) {
                mergeHeap.offer(new Node(
                        node.arrayIndex,
                        nextIndex,
                        arrays[node.arrayIndex][nextIndex]
                ));
            }
        }
        System.out.println();

        // ========== 7. 遍历方式 ==========
        System.out.println("\n========== 7. 遍历方式 ==========");

        PriorityQueue<Integer> traversePq = new PriorityQueue<>();
        traversePq.addAll(Arrays.asList(5, 2, 8, 1, 9, 3));

        // 方式1：增强 for（不保证顺序）
        System.out.print("方式1（增强 for，不保证顺序）：");
        for (Integer num : traversePq) {
            System.out.print(num + " ");
        }
        System.out.println();

        // 方式2：Iterator（不保证顺序）
        System.out.print("方式2（Iterator，不保证顺序）：");
        Iterator<Integer> it = traversePq.iterator();
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }
        System.out.println();

        // 方式3：poll 循环（按优先级顺序，但会清空队列）
        System.out.print("方式3（poll 循环，按优先级）：");
        while (!traversePq.isEmpty()) {
            System.out.print(traversePq.poll() + " ");
        }
        System.out.println();

        // ========== 8. 注意事项 ==========
        System.out.println("\n========== 8. 注意事项 ==========");

        // ⚠️ 注意1：不允许 null
        PriorityQueue<String> nullTest = new PriorityQueue<>();
        // nullTest.offer(null); // ❌ 抛出 NullPointerException
        System.out.println("⚠️ PriorityQueue 不允许存储 null 值");

        // ⚠️ 注意2：迭代器不保证顺序
        PriorityQueue<Integer> orderTest = new PriorityQueue<>();
        orderTest.addAll(Arrays.asList(10, 20, 15, 5, 25));
        System.out.println("PriorityQueue：" + orderTest);
        System.out.println("⚠️ 迭代器遍历顺序不是优先级顺序");

        // ⚠️ 注意3：元素必须可比较
        // 如果元素没有实现 Comparable，也没有传入 Comparator，会抛出 ClassCastException
        // class Person { String name; } // 没有实现 Comparable
        // PriorityQueue<Person> invalid = new PriorityQueue<>();
        // invalid.offer(new Person()); // ❌ ClassCastException

        // ⚠️ 注意4：不是线程安全
        System.out.println("⚠️ PriorityQueue 不是线程安全的");
        System.out.println("   解决方案：PriorityBlockingQueue（并发专用）");

        // ⚠️ 注意5：remove 是 O(n)
        System.out.println("⚠️ remove(Object) 和 contains(Object) 是 O(n)");
        System.out.println("   因为需要遍历查找元素");

        // ⚠️ 注意6：修改元素会影响优先级
        System.out.println("⚠️ 如果修改了队列中的元素，优先级不会自动调整");
        System.out.println("   需要删除后重新插入才能更新顺序");
    }

    // ========== 内部类 ==========

    /**
     * 任务类（实现 Comparable，按优先级排序）
     */
    static class Task implements Comparable<Task> {
        private String name;
        private int priority;  // 数字越大优先级越高

        public Task(String name, int priority) {
            this.name = name;
            this.priority = priority;
        }

        @Override
        public int compareTo(Task other) {
            return other.priority - this.priority;  // 降序：高优先级在前
        }

        @Override
        public String toString() {
            return name + "（优先级：" + priority + "）";
        }
    }

    /**
     * 数组节点（用于合并有序数组）
     */
    static class Node {
        int arrayIndex;  // 数组索引
        int index;       // 元素在数组中的索引
        int value;       // 元素值

        public Node(int arrayIndex, int index, int value) {
            this.arrayIndex = arrayIndex;
            this.index = index;
            this.value = value;
        }
    }
}
