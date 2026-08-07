import java.util.Deque;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Iterator;

/**
 * Deque 双端队列
 * 全称：Double Ended Queue（双端队列）
 * 支持在两端（队头 + 队尾）进行插入、删除、查看操作
 *
 * 核心特点：
 * - 既可以从头部操作，也可以从尾部操作
 * - 既可以当队列（FIFO）用，也可以当栈（LIFO）用
 * - 官方推荐替代 Stack 类
 * - 主要实现类：ArrayDeque（推荐）、LinkedList
 *
 * 核心方法（三组对称操作）：
 * 第一组：头部操作
 * - addFirst / offerFirst（推荐）   → 头部插入
 * - removeFirst / pollFirst（推荐） → 头部删除
 * - getFirst / peekFirst（推荐）    → 查看头部
 *
 * 第二组：尾部操作
 * - addLast / offerLast（推荐）    → 尾部插入
 * - removeLast / pollLast（推荐）  → 尾部删除
 * - getLast / peekLast（推荐）     → 查看尾部
 *
 * 第三组：栈方法（等效于头部操作）
 * - push    → 入栈（等效 addFirst）
 * - pop     → 出栈（等效 removeFirst）
 * - peek    → 查看栈顶（等效 peekFirst）
 *
 * 注意事项：
 * - Deque 是接口，不能直接实例化
 * - ArrayDeque 性能最好，推荐使用
 * - ArrayDeque 不允许 null 元素
 * - LinkedList 允许 null，但性能稍差
 */
public class Deque_ {

    public static void main(String[] args) {

        // ========== 1. 作为队列（FIFO） ==========
        System.out.println("========== 1. 作为队列（FIFO） ==========");

        Deque<String> queue = new ArrayDeque<>();

        // 入队：尾部插入
        queue.offer("第1个");
        queue.offer("第2个");
        queue.offer("第3个");
        System.out.println("入队后：" + queue); // [第1个, 第2个, 第3个]

        // 出队：头部删除
        System.out.println("出队：" + queue.poll()); // 第1个
        System.out.println("出队：" + queue.poll()); // 第2个
        System.out.println("出队：" + queue.poll()); // 第3个
        System.out.println("空队列出队：" + queue.poll()); // null

        // ========== 2. 作为栈（LIFO） ==========
        System.out.println("\n========== 2. 作为栈（LIFO） ==========");

        Deque<String> stack = new ArrayDeque<>();

        // 压栈：头部插入
        stack.push("第1个");
        stack.push("第2个");
        stack.push("第3个");
        System.out.println("压栈后：" + stack); // [第3个, 第2个, 第1个]

        // 查看栈顶
        System.out.println("peek 查看栈顶：" + stack.peek()); // 第3个

        // 弹栈：头部删除
        System.out.println("弹栈：" + stack.pop()); // 第3个
        System.out.println("弹栈：" + stack.pop()); // 第2个
        System.out.println("弹栈：" + stack.pop()); // 第1个
        System.out.println("空栈弹栈：" + stack.poll()); // null（用 poll 更安全）

        // ========== 3. 双端操作 ==========
        System.out.println("\n========== 3. 双端操作 ==========");

        Deque<String> deque = new ArrayDeque<>();

        // 两端插入
        deque.addFirst("头部A");
        deque.addLast("尾部Z");
        deque.offerFirst("头部B");
        deque.offerLast("尾部Y");
        System.out.println("两端插入后：" + deque); // [头部B, 头部A, 尾部Z, 尾部Y]

        // 两端查看
        System.out.println("getFirst 查看头部：" + deque.getFirst()); // 头部B
        System.out.println("getLast 查看尾部：" + deque.getLast());   // 尾部Y
        System.out.println("peekFirst 查看头部：" + deque.peekFirst()); // 头部B
        System.out.println("peekLast 查看尾部：" + deque.peekLast());   // 尾部Y

        // 两端删除
        System.out.println("removeFirst 删除头部：" + deque.removeFirst()); // 头部B
        System.out.println("removeLast 删除尾部：" + deque.removeLast());   // 尾部Y
        System.out.println("删除后：" + deque); // [头部A, 尾部Z]

        // ========== 4. 三组方法对比 ==========
        System.out.println("\n========== 4. 三组方法对比 ==========");

        System.out.println("┌─────────────┬───────────────────────────┬─────────────────────────┬────────────────┐");
        System.out.println("│   操作      │  头部方法                 │  尾部方法               │  栈方法        │");
        System.out.println("├─────────────┼───────────────────────────┼─────────────────────────┼────────────────┤");
        System.out.println("│   插入      │  addFirst / offerFirst    │ addLast / offerLast     │ push           │");
        System.out.println("│   删除      │  removeFirst / pollFirst  │ removeLast / pollLast   │ pop            │");
        System.out.println("│   查看      │  getFirst / peekFirst     │ getLast / peekLast      │ peek           │");
        System.out.println("└─────────────┴───────────────────────────┴─────────────────────────┴────────────────┘");

        // ========== 5. ArrayDeque vs LinkedList ==========
        System.out.println("\n========== 5. ArrayDeque vs LinkedList ==========");

        System.out.println("┌─────────────┬──────────────────┬─────────────────┐");
        System.out.println("│   特性      │  ArrayDeque      │  LinkedList     │");
        System.out.println("├─────────────┼──────────────────┼─────────────────┤");
        System.out.println("│   底层      │  数组            │  双向链表       │");
        System.out.println("│   性能      │  更好            │  稍差           │");
        System.out.println("│   内存      │  更紧凑          │  每个节点有指针 │");
        System.out.println("│   允许 null │  ❌ 不允许        │  ✅ 允许         │");
        System.out.println("│   推荐程度  │  ⭐⭐⭐⭐⭐ 推荐      │  ⭐⭐⭐ 一般       │");
        System.out.println("└─────────────┴──────────────────┴─────────────────┘");

        // null 测试
        Deque<String> linkedDeque = new LinkedList<>();
        linkedDeque.offer(null); // ✅ LinkedList 允许 null

        Deque<String> arrayDeque = new ArrayDeque<>();
        // arrayDeque.offer(null); // ❌ NullPointerException

        // ========== 6. 遍历方式 ==========
        System.out.println("\n========== 6. 遍历方式 ==========");

        Deque<String> traverseDeque = new ArrayDeque<>();
        traverseDeque.offer("A");
        traverseDeque.offer("B");
        traverseDeque.offer("C");

        // 方式1：增强 for
        System.out.print("方式1（增强 for）：");
        for (String item : traverseDeque) {
            System.out.print(item + " ");
        }
        System.out.println();

        // 方式2：Iterator
        System.out.print("方式2（Iterator）：");
        Iterator<String> it = traverseDeque.iterator();
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }
        System.out.println();

        // 方式3：poll 遍历（会清空）
        System.out.print("方式3（poll 遍历，会清空）：");
        while (!traverseDeque.isEmpty()) {
            System.out.print(traverseDeque.poll() + " ");
        }
        System.out.println();
        System.out.println("遍历后是否为空：" + traverseDeque.isEmpty());

        // ========== 7. 队列 vs 栈 vs 双端队列 ==========
        System.out.println("\n========== 7. 队列 vs 栈 vs 双端队列 ==========");

        System.out.println("┌─────────────┬──────────────────┬──────────────────┬─────────────────┐");
        System.out.println("│   特性      │  Queue（队列）   │  Stack（栈）     │  Deque（双端）  │");
        System.out.println("├─────────────┼──────────────────┼──────────────────┼─────────────────┤");
        System.out.println("│   操作端    │  尾进头出        │  头进头出        │  两端都可以     │");
        System.out.println("│   顺序      │  FIFO            │  LIFO            │  FIFO / LIFO    │");
        System.out.println("│   典型方法  │  offer/poll/peek │  push/pop/peek   │  addFirst/Last  │");
        System.out.println("│   推荐实现  │  ArrayDeque      │  ArrayDeque      │  ArrayDeque     │");
        System.out.println("└─────────────┴──────────────────┴──────────────────┴─────────────────┘");

        // ========== 8. 常见应用场景 ==========
        System.out.println("\n========== 8. 常见应用场景 ==========");

        // 场景1：用 Deque 实现队列（FIFO）
        System.out.println("场景1：消息队列（FIFO）");
        Deque<String> messageQueue = new ArrayDeque<>();
        messageQueue.offer("消息1");
        messageQueue.offer("消息2");
        messageQueue.offer("消息3");
        while (!messageQueue.isEmpty()) {
            System.out.println("  消费：" + messageQueue.poll());
        }

        // 场景2：用 Deque 实现栈（LIFO）
        System.out.println("场景2：撤销操作（LIFO）");
        Deque<String> undoStack = new ArrayDeque<>();
        // 用户操作
        undoStack.push("输入文字");
        undoStack.push("删除文字");
        undoStack.push("复制粘贴");
        System.out.println("  当前栈：" + undoStack);
        // 撤销：后进先出
        System.out.println("  撤销：" + undoStack.pop());
        System.out.println("  撤销：" + undoStack.pop());
        System.out.println("  撤销后栈：" + undoStack);

        // 场景3：双端队列（两端处理）
        System.out.println("场景3：双端任务处理");
        Deque<Integer> taskDeque = new ArrayDeque<>();
        taskDeque.addFirst(1);
        taskDeque.addLast(10);
        taskDeque.addFirst(0);
        taskDeque.addLast(20);
        System.out.println("  任务队列：" + taskDeque);
        System.out.println("  处理头部：" + taskDeque.pollFirst());
        System.out.println("  处理尾部：" + taskDeque.pollLast());
        System.out.println("  剩余任务：" + taskDeque);

        // ========== 9. 注意事项 ==========
        System.out.println("\n========== 9. 注意事项 ==========");

        // ⚠️ 注意1：Deque 是接口，不能实例化
        // Deque<String> d = new Deque<>(); // ❌ 编译错误

        // ⚠️ 注意2：ArrayDeque 不允许 null
        Deque<String> ad = new ArrayDeque<>();
        // ad.offer(null); // ❌ NullPointerException

        // ⚠️ 注意3：push 和 addFirst 等效
        Deque<String> d1 = new ArrayDeque<>();
        d1.push("A"); // 头部插入
        d1.addFirst("B"); // 头部插入，等效

        // ⚠️ 注意4：pop 和 removeFirst 等效
        Deque<String> d2 = new ArrayDeque<>();
        d2.push("A");
        d2.push("B");
        System.out.println("pop：" + d2.pop()); // B
        System.out.println("removeFirst：" + d2.removeFirst()); // A
    }
}