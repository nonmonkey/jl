import java.util.Deque;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Iterator;
import java.util.Arrays;

/**
 * ArrayDeque
 * Deque 接口的实现类，基于动态数组实现的双端队列
 *
 * 继承关系：
 * java.lang.Object
 *   └── java.util.AbstractCollection<E>
 *         └── java.util.ArrayDeque<E>
 *
 * 实现接口：
 * - Deque<E>                → 双端队列
 * - Queue<E>                → 队列（通过 Deque 间接继承）
 * - Cloneable               → 支持克隆（浅拷贝）
 * - java.io.Serializable    → 支持序列化
 *
 * 核心特点：
 * - 双端操作：可以在头部和尾部进行插入/删除
 * - 可作为栈使用（LIFO）：push/pop/peek
 * - 可作为队列使用（FIFO）：offer/poll/peek
 * - 不支持 null：不允许存储 null 值
 * - 非线程安全：多线程环境下需要额外同步
 * - 性能优秀：比 LinkedList 更快，比 Stack 更安全
 *
 * 底层数据结构：
 * - 动态环形数组（循环数组）
 * - 默认初始容量：16
 * - 扩容机制：当容量不足时，容量翻倍
 *
 * 核心方法：
 *
 * 【返回特殊值版（false/null）】
 * - offerFirst  → 头部插入
 * - offerLast   → 尾部插入
 * - pollFirst   → 头部删除
 * - pollLast    → 尾部删除
 * - peekFirst   → 查看头部
 * - peekLast    → 查看尾部
 *
 * 【抛出异常版】
 * - addFirst    → 头部插入
 * - addLast     → 尾部插入
 * - removeFirst → 头部删除
 * - removeLast  → 尾部删除
 * - getFirst    → 查看头部
 * - getLast     → 查看尾部
 *
 * 【栈操作】
 * - push        → 压栈（等价于 addFirst）
 * - pop         → 弹栈（等价于 removeFirst）
 * - peek        → 查看栈顶（等价于 peekFirst）
 *
 * 【队列操作】
 * - offer       → 入队（等价于 offerLast）
 * - poll        → 出队（等价于 pollFirst）
 * - peek        → 查看队首（等价于 peekFirst）
 *
 * 【其他】
 * - size     → 获取元素个数
 * - isEmpty  → 判断是否为空
 * - contains → 判断是否包含
 * - clear    → 清空
 * - remove   → 删除指定元素
 * - iterator → 正向遍历
 * - descendingIterator → 反向遍历
 *
 * 适用场景：
 * - 作为栈使用（替代 Stack）      ⭐⭐⭐⭐⭐
 * - 作为双端队列使用              ⭐⭐⭐⭐⭐
 * - 作为普通队列使用              ⭐⭐⭐⭐
 *
 * 与 LinkedList 对比：
 * - ArrayDeque：基于数组，不允许 null，性能更好
 * - LinkedList：基于链表，允许 null，内存占用更大
 */
public class ArrayDeque_ {

    public static void main(String[] args) {

        // ========== 1. 创建 ArrayDeque ==========
        System.out.println("========== 1. 创建 ArrayDeque ==========");

        // 方式1：无参构造（默认容量 16）
        Deque<String> deque1 = new ArrayDeque<>();
        System.out.println("无参构造：" + deque1);

        // 方式2：指定初始容量
        Deque<String> deque2 = new ArrayDeque<>(20);
        System.out.println("指定容量 20：" + deque2);

        // 方式3：从其他集合创建
        Deque<String> deque3 = new ArrayDeque<>(Arrays.asList("A", "B", "C"));
        System.out.println("从集合创建：" + deque3);

        // ========== 2. 作为栈使用（LIFO） ==========
        System.out.println("\n========== 2. 作为栈使用（LIFO） ==========");

        Deque<String> stack = new ArrayDeque<>();

        // push → 压栈（头部插入）
        stack.push("第1层");
        stack.push("第2层");
        stack.push("第3层");
        stack.push("第4层");
        System.out.println("栈：" + stack);

        // peek → 查看栈顶（不删除）
        System.out.println("栈顶元素：" + stack.peek());

        // pop → 弹栈（获取并删除头部）
        String top = stack.pop();
        System.out.println("弹出：" + top);
        System.out.println("剩余栈：" + stack);

        // isEmpty → 判断是否为空
        System.out.println("是否为空：" + stack.isEmpty());

        // 循环弹栈
        System.out.print("循环弹栈：");
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
        System.out.println();

        // ========== 3. 作为队列使用（FIFO） ==========
        System.out.println("\n========== 3. 作为队列使用（FIFO） ==========");

        // 作为 Queue 使用
        Queue<String> queue = new ArrayDeque<>();

        // offer → 入队（尾部插入）
        queue.offer("任务1");
        queue.offer("任务2");
        queue.offer("任务3");
        System.out.println("队列：" + queue);

        // peek → 查看队首（不删除）
        System.out.println("队首元素：" + queue.peek());

        // poll → 出队（获取并删除队首）
        String task = queue.poll();
        System.out.println("出队：" + task);
        System.out.println("剩余队列：" + queue);

        // 循环出队
        System.out.print("循环出队：");
        while (!queue.isEmpty()) {
            System.out.print(queue.poll() + " ");
        }
        System.out.println();

        // ========== 4. 作为双端队列使用 ==========
        System.out.println("\n========== 4. 作为双端队列使用 ==========");

        Deque<String> deque = new ArrayDeque<>();

        // addFirst → 头部添加
        deque.addFirst("头部1");
        deque.addFirst("头部2");
        System.out.println("addFirst 后：" + deque);

        // addLast → 尾部添加
        deque.addLast("尾部1");
        deque.addLast("尾部2");
        System.out.println("addLast 后：" + deque);

        // getFirst → 获取头部（不删除）
        System.out.println("头部元素：" + deque.getFirst());

        // getLast → 获取尾部（不删除）
        System.out.println("尾部元素：" + deque.getLast());

        // removeFirst → 删除头部
        String first = deque.removeFirst();
        System.out.println("删除头部：" + first + "，剩余：" + deque);

        // removeLast → 删除尾部
        String last = deque.removeLast();
        System.out.println("删除尾部：" + last + "，剩余：" + deque);

        // ========== 5. 所有操作方法对比 ==========
        System.out.println("\n========== 5. 所有操作方法对比 ==========");

        Deque<String> testDeque = new ArrayDeque<>();

        System.out.println("--- 插入操作 ---");
        // 头部插入
        testDeque.offerFirst("offerFirst");
        testDeque.addFirst("addFirst");
        testDeque.push("push");  // 等价于 addFirst
        System.out.println("头部插入后：" + testDeque);

        // 尾部插入
        testDeque.offerLast("offerLast");
        testDeque.addLast("addLast");
        testDeque.offer("offer");  // 等价于 offerLast
        testDeque.add("add");      // 等价于 addLast
        System.out.println("尾部插入后：" + testDeque);

        System.out.println("\n--- 删除操作 ---");
        // 头部删除
        System.out.println("removeFirst：" + testDeque.removeFirst());
        System.out.println("pollFirst：" + testDeque.pollFirst());
        System.out.println("pop：" + testDeque.pop());  // 等价于 removeFirst
        System.out.println("poll：" + testDeque.poll()); // 等价于 pollFirst
        System.out.println("删除后：" + testDeque);

        // 尾部删除
        System.out.println("removeLast：" + testDeque.removeLast());
        System.out.println("pollLast：" + testDeque.pollLast());
        System.out.println("删除后：" + testDeque);

        System.out.println("\n--- 查看操作 ---");
        testDeque.clear();
        testDeque.add("A");
        testDeque.add("B");
        testDeque.add("C");
        System.out.println("当前：" + testDeque);
        System.out.println("getFirst：" + testDeque.getFirst());
        System.out.println("peekFirst：" + testDeque.peekFirst());
        System.out.println("peek：" + testDeque.peek());  // 等价于 peekFirst
        System.out.println("getLast：" + testDeque.getLast());
        System.out.println("peekLast：" + testDeque.peekLast());

        // ========== 6. 遍历方式 ==========
        System.out.println("\n========== 6. 遍历方式 ==========");

        Deque<Integer> traverseDeque = new ArrayDeque<>();
        traverseDeque.addAll(Arrays.asList(1, 2, 3, 4, 5));

        // 方式1：增强 for（从头部到尾部）
        System.out.print("方式1（增强 for）：");
        for (Integer num : traverseDeque) {
            System.out.print(num + " ");
        }
        System.out.println();

        // 方式2：Iterator（从头部到尾部）
        System.out.print("方式2（Iterator）：");
        Iterator<Integer> it = traverseDeque.iterator();
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }
        System.out.println();

        // 方式3：descendingIterator（从尾部到头部）
        System.out.print("方式3（descendingIterator 反向）：");
        Iterator<Integer> descIt = traverseDeque.descendingIterator();
        while (descIt.hasNext()) {
            System.out.print(descIt.next() + " ");
        }
        System.out.println();

        // 方式4：forEach
        System.out.print("方式4（forEach）：");
        traverseDeque.forEach(num -> System.out.print(num + " "));
        System.out.println();

        // ========== 7. 其他常用方法 ==========
        System.out.println("\n========== 7. 其他常用方法 ==========");

        Deque<String> otherDeque = new ArrayDeque<>();
        otherDeque.add("A");
        otherDeque.add("B");
        otherDeque.add("C");
        otherDeque.add("D");

        // size → 获取元素个数
        System.out.println("size：" + otherDeque.size());

        // contains → 判断是否包含
        System.out.println("contains 'B'：" + otherDeque.contains("B"));
        System.out.println("contains 'Z'：" + otherDeque.contains("Z"));

        // remove → 删除指定元素（第一次出现）
        boolean removed = otherDeque.remove("C");
        System.out.println("删除 'C'：" + removed + "，剩余：" + otherDeque);

        // removeFirstOccurrence → 从头部开始删除指定元素
        otherDeque.add("B");
        System.out.println("添加 B 后：" + otherDeque);
        otherDeque.removeFirstOccurrence("B");
        System.out.println("removeFirstOccurrence('B')：" + otherDeque);

        // removeLastOccurrence → 从尾部开始删除指定元素
        otherDeque.add("A");
        System.out.println("添加 A 后：" + otherDeque);
        otherDeque.removeLastOccurrence("A");
        System.out.println("removeLastOccurrence('A')：" + otherDeque);

        // toArray → 转换为数组
        Object[] array = otherDeque.toArray();
        System.out.println("toArray：" + Arrays.toString(array));

        // ========== 8. 实际应用场景 ==========
        System.out.println("\n========== 8. 实际应用场景 ==========");

        // 场景1：浏览器历史记录（栈）
        System.out.println("--- 场景1：浏览器历史记录 ---");
        Deque<String> history = new ArrayDeque<>();
        history.push("首页");
        history.push("新闻");
        history.push("视频");
        System.out.println("浏览历史：" + history);
        System.out.println("回退到：" + history.pop());
        System.out.println("当前历史：" + history);

        // 场景2：任务队列（队列）
        System.out.println("\n--- 场景2：任务队列 ---");
        Queue<String> taskQueue = new ArrayDeque<>();
        taskQueue.offer("任务A");
        taskQueue.offer("任务B");
        taskQueue.offer("任务C");
        System.out.println("任务队列：" + taskQueue);

        while (!taskQueue.isEmpty()) {
            String currentTask = taskQueue.poll();
            System.out.println("执行：" + currentTask);
        }

        // 场景3：括号匹配（栈）
        System.out.println("\n--- 场景3：括号匹配 ---");
        String expr = "({[]})";
        Deque<Character> bracketStack = new ArrayDeque<>();
        boolean valid = true;

        for (char c : expr.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                bracketStack.push(c);
            } else if (c == ')' || c == ']' || c == '}') {
                if (bracketStack.isEmpty()) {
                    valid = false;
                    break;
                }
                char open = bracketStack.pop();
                if (!((open == '(' && c == ')') ||
                        (open == '[' && c == ']') ||
                        (open == '{' && c == '}'))) {
                    valid = false;
                    break;
                }
            }
        }
        if (valid && bracketStack.isEmpty()) {
            System.out.println(expr + " → ✅ 括号匹配");
        } else {
            System.out.println(expr + " → ❌ 括号不匹配");
        }

        // ========== 9. 性能对比 ==========
        System.out.println("\n========== 9. 性能对比 ==========");

        // ArrayDeque vs LinkedList
        int testSize = 100000;

        // ArrayDeque 头部插入
        Deque<Integer> arrayDeque = new ArrayDeque<>();
        long start = System.nanoTime();
        for (int i = 0; i < testSize; i++) {
            arrayDeque.addFirst(i);
        }
        long end = System.nanoTime();
        System.out.println("ArrayDeque 头部插入 " + testSize + " 次：" + (end - start) / 1000000 + "ms");

        // LinkedList 头部插入
        Deque<Integer> linkedDeque = new java.util.LinkedList<>();
        start = System.nanoTime();
        for (int i = 0; i < testSize; i++) {
            linkedDeque.addFirst(i);
        }
        end = System.nanoTime();
        System.out.println("LinkedList 头部插入 " + testSize + " 次：" + (end - start) / 1000000 + "ms");

        System.out.println("✅ ArrayDeque 性能优于 LinkedList（内存连续，缓存友好）");

        // ========== 10. 注意事项 ==========
        System.out.println("\n========== 10. 注意事项 ==========");

        // ⚠️ 注意1：不允许 null
        Deque<String> nullTest = new ArrayDeque<>();
        // nullTest.add(null); // ❌ 抛出 NullPointerException
        System.out.println("⚠️ ArrayDeque 不允许存储 null 值");

        // ⚠️ 注意2：不是线程安全
        System.out.println("⚠️ ArrayDeque 不是线程安全的");
        System.out.println("   解决方案：使用 Collections.synchronizedDeque()");

        // ⚠️ 注意3：迭代器是 fail-fast 的
        Deque<String> failFast = new ArrayDeque<>();
        failFast.add("A");
        failFast.add("B");
        failFast.add("C");

        try {
            Iterator<String> fastIt = failFast.iterator();
            while (fastIt.hasNext()) {
                String item = fastIt.next();
                if (item.equals("B")) {
                    failFast.remove("C");  // 遍历时修改
                }
            }
        } catch (java.util.ConcurrentModificationException e) {
            System.out.println("⚠️ 遍历时修改集合会抛出 ConcurrentModificationException");
        }

        // ⚠️ 注意4：推荐用于栈和队列场景
        System.out.println("✅ 推荐用法：");
        System.out.println("   Deque<String> stack = new ArrayDeque<>();  // 作为栈");
        System.out.println("   Queue<String> queue = new ArrayDeque<>();  // 作为队列");
        System.out.println("   Deque<String> deque = new ArrayDeque<>();  // 作为双端队列");
    }
}