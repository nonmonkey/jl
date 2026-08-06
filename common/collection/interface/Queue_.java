import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Iterator;
import java.util.Arrays;

/**
 * Queue 队列
 * 先进先出（FIFO：First In First Out）的数据结构
 * 就像排队买票，先来的先服务
 *
 * 核心特点：
 * - 元素在队尾（tail）入队，在队头（head）出队
 * - 大部分实现是不允许插入 null 的（如 ArrayDeque）
 * - 主要实现类：LinkedList、ArrayDeque、PriorityQueue
 *
 * 核心方法（两组）：
 * 第一组：抛出异常
 * - add        → 队尾插入元素（失败抛异常）
 * - remove     → 队头移除元素（队列为空抛异常）
 * - element    → 查看队头元素（队列为空抛异常）
 *
 * 第二组：返回特殊值（推荐）
 * - offer      → 队尾插入元素（失败返回 false）
 * - poll       → 队头移除元素（队列为空返回 null）
 * - peek       → 查看队头元素（队列为空返回 null）
 *
 * 注意事项：
 * - Queue 是接口，不能直接实例化
 * - 推荐使用 offer/poll/peek 方法（返回特殊值，更安全）
 * - 不同实现类的特性不同（是否有序、是否可重复、是否允许 null）
 */
public class Queue_ {

    public static void main(String[] args) {

        // ========== 1. 基本使用（LinkedList 实现） ==========
        System.out.println("========== 1. 基本使用 ==========");

        Queue<String> queue = new LinkedList<>();

        // offer → 入队（返回特殊值）
        queue.offer("第1个");
        queue.offer("第2个");
        queue.offer("第3个");
        System.out.println("入队后：" + queue);

        // peek → 查看队头（不删除）
        System.out.println("peek 查看队头：" + queue.peek()); // 第1个

        // poll → 出队（删除并返回）
        System.out.println("poll 出队：" + queue.poll()); // 第1个
        System.out.println("poll 出队：" + queue.poll()); // 第2个
        System.out.println("poll 出队：" + queue.poll()); // 第3个
        System.out.println("poll 空队列：" + queue.poll()); // null（不抛异常）

        // ========== 2. add/remove/element（抛出异常版本） ==========
        System.out.println("\n========== 2. add/remove/element ==========");

        Queue<String> queue2 = new LinkedList<>();

        // add → 入队（失败抛异常）
        queue2.add("A");
        queue2.add("B");
        System.out.println("add 后：" + queue2);

        // element → 查看队头（空队列抛异常）
        System.out.println("element 查看队头：" + queue2.element()); // A

        // remove → 出队（空队列抛异常）
        System.out.println("remove 出队：" + queue2.remove()); // A
        System.out.println("remove 出队：" + queue2.remove()); // B

        // ⚠️ 空队列调用 remove 会抛异常
        try {
            queue2.remove(); // ❌ 抛出 NoSuchElementException
        } catch (Exception e) {
            System.out.println("❌ 空队列 remove 抛异常：" + e.getClass().getSimpleName());
        }

        // ========== 3. offer/poll/peek vs add/remove/element ==========
        System.out.println("\n========== 3. 两组方法对比 ==========");

        System.out.println("┌─────────────┬──────────────────┬─────────────────┐");
        System.out.println("│   操作      │  返回特殊值      │   抛出异常      │");
        System.out.println("├─────────────┼──────────────────┼─────────────────┤");
        System.out.println("│   入队      │  offer(e)        │  add(e)         │");
        System.out.println("│   出队      │  poll()          │  remove()       │");
        System.out.println("│   查看      │  peek()          │  element()      │");
        System.out.println("└─────────────┴──────────────────┴─────────────────┘");

        System.out.println("\n推荐使用 offer/poll/peek（更安全，不会抛异常）");

        // ========== 4. ArrayDeque 实现队列 ==========
        System.out.println("\n========== 4. ArrayDeque 实现队列 ==========");

        // ArrayDeque 也可以当队列用（性能更好）
        Queue<String> arrayQueue = new ArrayDeque<>();
        arrayQueue.offer("A");
        arrayQueue.offer("B");
        arrayQueue.offer("C");
        System.out.println("ArrayDeque 队列：" + arrayQueue);

        while (!arrayQueue.isEmpty()) {
            System.out.println("  出队：" + arrayQueue.poll());
        }

        // ========== 5. PriorityQueue 优先级队列 ==========
        System.out.println("\n========== 5. PriorityQueue 优先级队列 ==========");

        // PriorityQueue：按优先级出队（默认自然顺序，最小的优先）
        Queue<Integer> priorityQueue = new PriorityQueue<>();
        priorityQueue.offer(30);
        priorityQueue.offer(10);
        priorityQueue.offer(20);
        priorityQueue.offer(5);

        System.out.println("PriorityQueue 插入顺序：30, 10, 20, 5");
        System.out.print("出队顺序（自然顺序）：");
        while (!priorityQueue.isEmpty()) {
            System.out.print(priorityQueue.poll() + " ");
        }
        System.out.println(); // 5, 10, 20, 30

        // 自定义优先级（降序）
        Queue<Integer> priorityQueue2 = new PriorityQueue<>((a, b) -> b - a);
        priorityQueue2.offer(30);
        priorityQueue2.offer(10);
        priorityQueue2.offer(20);
        priorityQueue2.offer(5);
        System.out.print("出队顺序（降序）：");
        while (!priorityQueue2.isEmpty()) {
            System.out.print(priorityQueue2.poll() + " ");
        }
        System.out.println(); // 30, 20, 10, 5

        // ========== 6. Queue 的遍历 ==========
        System.out.println("\n========== 6. 遍历方式 ==========");

        Queue<String> traverseQueue = new LinkedList<>();
        traverseQueue.offer("A");
        traverseQueue.offer("B");
        traverseQueue.offer("C");

        // 方式1：增强 for（不删除）
        System.out.print("方式1（增强 for）：");
        for (String item : traverseQueue) {
            System.out.print(item + " ");
        }
        System.out.println();

        // 方式2：Iterator（不删除）
        System.out.print("方式2（Iterator）：");
        Iterator<String> it = traverseQueue.iterator();
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }
        System.out.println();

        // 方式3：poll 遍历（会清空队列）
        System.out.print("方式3（poll 遍历，会清空）：");
        while (!traverseQueue.isEmpty()) {
            System.out.print(traverseQueue.poll() + " ");
        }
        System.out.println();
        System.out.println("遍历后队列是否为空：" + traverseQueue.isEmpty());

        // ========== 7. Queue 与 List 的区别 ==========
        System.out.println("\n========== 7. Queue vs List ==========");

        System.out.println("┌─────────────┬──────────────────┬─────────────────┐");
        System.out.println("│   特性      │  Queue           │  List           │");
        System.out.println("├─────────────┼──────────────────┼─────────────────┤");
        System.out.println("│   访问方式  │  只能队头/队尾   │  任意索引       │");
        System.out.println("│   主要场景  │  FIFO 缓冲       │  随机存取       │");
        System.out.println("│   是否有序  │  是              │  是             │");
        System.out.println("│   索引操作  │  不支持          │  支持           │");
        System.out.println("└─────────────┴──────────────────┴─────────────────┘");

        // ========== 8. 常见应用场景 ==========
        System.out.println("\n========== 8. 常见应用场景 ==========");

        // 场景1：任务队列（待处理任务）
        System.out.println("场景1：任务队列");
        Queue<String> tasks = new LinkedList<>();
        tasks.offer("任务1");
        tasks.offer("任务2");
        tasks.offer("任务3");
        while (!tasks.isEmpty()) {
            System.out.println("  执行：" + tasks.poll());
        }

        // 场景2：消息队列（生产者-消费者）
        System.out.println("场景2：消息队列");
        Queue<String> messages = new LinkedList<>();
        // 生产者
        messages.offer("消息A");
        messages.offer("消息B");
        // 消费者
        while (!messages.isEmpty()) {
            System.out.println("  消费：" + messages.poll());
        }

        // 场景3：优先级队列（任务调度）
        System.out.println("场景3：优先级队列");
        Queue<Task> taskQueue = new PriorityQueue<>((t1, t2) -> t2.priority - t1.priority);
        taskQueue.offer(new Task("写文档", 1));
        taskQueue.offer(new Task("修bug", 5));
        taskQueue.offer(new Task("发版本", 3));
        while (!taskQueue.isEmpty()) {
            Task t = taskQueue.poll();
            System.out.println("  执行：" + t.name + "（优先级：" + t.priority + "）");
        }

        // ========== 9. 注意事项 ==========
        System.out.println("\n========== 9. 注意事项 ==========");

        // ⚠️ 注意1：Queue 是接口，不能实例化
        // Queue<String> q = new Queue<>(); // ❌ 编译错误

        // ⚠️ 注意2：LinkedList 允许 null，ArrayDeque 不允许
        Queue<String> linkedQueue = new LinkedList<>();
        linkedQueue.offer(null); // ✅ LinkedList 允许 null

        Queue<String> arrayQueue2 = new ArrayDeque<>();
        // arrayQueue2.offer(null); // ❌ NullPointerException

        // ⚠️ 注意3：PriorityQueue 不允许 null
        Queue<Integer> pq = new PriorityQueue<>();
        // pq.offer(null); // ❌ NullPointerException

        // ⚠️ 注意4：PriorityQueue 不保证遍历顺序
        Queue<Integer> pq2 = new PriorityQueue<>(Arrays.asList(3, 1, 2));
        System.out.print("PriorityQueue 遍历顺序（不保证）：");
        for (Integer num : pq2) {
            System.out.print(num + " ");
        }
        System.out.println();
        System.out.println("   poll 出队才是按优先级顺序：" + pq2.poll() + ", " + pq2.poll() + ", " + pq2.poll());
    }

    // 内部类：用于优先级队列示例
    static class Task {
        String name;
        int priority;

        Task(String name, int priority) {
            this.name = name;
            this.priority = priority;
        }
    }
}