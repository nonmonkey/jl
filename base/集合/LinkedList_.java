import java.util.List;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Deque;
import java.util.Queue;

/**
 * LinkedList
 * List 接口和 Deque 接口的实现类，基于双向链表实现
 *
 * 继承关系：
 * java.lang.Object
 *   └── java.util.AbstractCollection<E>
 *         └── java.util.AbstractList<E>
 *               └── java.util.AbstractSequentialList<E>
 *                     └── java.util.LinkedList<E>
 *
 * 实现接口：
 * - List<E>                 → 有序集合，支持索引访问
 * - Deque<E>                → 双端队列，支持两端操作
 * - Queue<E>                → 队列（通过 Deque 间接继承）
 * - Cloneable               → 支持克隆（浅拷贝）
 * - java.io.Serializable    → 支持序列化
 *
 * 核心特点：
 * - 有序：元素按照插入顺序存储
 * - 可重复：允许存储重复元素
 * - 支持 null：可以存储 null 值
 * - 插入/删除快：在头部或尾部插入/删除时间复杂度 O(1)
 * - 随机访问慢：按索引访问需要遍历链表，时间复杂度 O(n)
 * - 实现了 Deque 接口：可以作为双端队列使用
 * - 非线程安全：多线程环境下需要额外同步
 *
 * 底层数据结构：
 * - 双向链表（每个节点包含 prev、next、item）
 * - 节点结构：Node<E> { E item; Node<E> prev; Node<E> next; }
 *
 * 核心方法（List 基础上增加）：
 * - addFirst    → 在头部添加
 * - addLast     → 在尾部添加
 * - getFirst    → 获取头部元素
 * - getLast     → 获取尾部元素
 * - removeFirst → 删除头部元素
 * - removeLast  → 删除尾部元素
 * - offer       → 尾部插入（队列操作）
 * - poll        → 获取并删除头部（队列操作）
 * - peek        → 获取但不删除头部（队列操作）
 * - push        → 头部插入（栈操作）
 * - pop         → 获取并删除头部（栈操作）
 *
 * 适用场景：
 * - 频繁在头部/尾部插入/删除
 * - 实现栈（Stack）或队列（Queue）
 * - 不需要频繁随机访问
 *
 * 与 ArrayList 对比：
 * - ArrayList：随机访问快，增删慢（中间位置），尾部增删快
 * - LinkedList：随机访问慢，增删快（首尾位置），内存占用更大
 */
public class LinkedList_ {

    public static void main(String[] args) {

        // ========== 1. 创建 LinkedList ==========
        System.out.println("========== 1. 创建 LinkedList ==========");

        // 作为 List 使用
        List<String> list1 = new LinkedList<>();
        System.out.println("作为 List：" + list1);

        // 作为 Deque 使用
        Deque<String> deque1 = new LinkedList<>();
        System.out.println("作为 Deque：" + deque1);

        // 作为 Queue 使用
        Queue<String> queue1 = new LinkedList<>();
        System.out.println("作为 Queue：" + queue1);

        // 从集合创建
        LinkedList<String> list2 = new LinkedList<>(Arrays.asList("A", "B", "C"));
        System.out.println("从集合创建：" + list2);

        // ========== 2. 作为 List 使用 ==========
        System.out.println("\n========== 2. 作为 List 使用 ==========");

        LinkedList<String> list = new LinkedList<>();

        // add → 尾部添加
        list.add("苹果");
        list.add("香蕉");
        list.add("橙子");
        System.out.println("尾部添加：" + list);

        // add → 指定位置插入
        list.add(1, "葡萄");
        System.out.println("索引1插入'葡萄'：" + list);

        // addAll → 批量添加
        list.addAll(Arrays.asList("草莓", "西瓜"));
        System.out.println("批量添加：" + list);

        // get → 通过索引获取（O(n)，不推荐频繁使用）
        System.out.println("索引0：" + list.get(0));
        System.out.println("索引2：" + list.get(2));

        // indexOf → 获取第一次出现的索引
        System.out.println("'香蕉'第一次出现位置：" + list.indexOf("香蕉"));

        // set → 修改指定位置
        list.set(1, "哈密瓜");
        System.out.println("修改索引1为'哈密瓜'：" + list);

        // remove → 删除指定位置
        list.remove(2);
        System.out.println("删除索引2：" + list);

        // ========== 3. 作为 Deque 使用（双端队列） ==========
        System.out.println("\n========== 3. 作为 Deque 使用 ==========");

        Deque<String> deque = new LinkedList<>();

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

        // ========== 4. 作为 Queue 使用（队列 FIFO） ==========
        System.out.println("\n========== 4. 作为 Queue 使用 ==========");

        Queue<String> queue = new LinkedList<>();

        // offer → 尾部插入（队列操作）
        queue.offer("任务1");
        queue.offer("任务2");
        queue.offer("任务3");
        System.out.println("offer 后：" + queue);

        // peek → 查看头部（不删除）
        System.out.println("peek 查看头部：" + queue.peek());

        // poll → 获取并删除头部
        String task = queue.poll();
        System.out.println("poll 取出：" + task + "，剩余：" + queue);

        // 循环取出所有元素
        System.out.print("循环取出所有任务：");
        while (!queue.isEmpty()) {
            System.out.print(queue.poll() + " ");
        }
        System.out.println();

        // ========== 5. 作为 Stack 使用（栈 LIFO） ==========
        System.out.println("\n========== 5. 作为 Stack 使用 ==========");

        Deque<String> stack = new LinkedList<>();

        // push → 压栈（头部插入）
        stack.push("第1层");
        stack.push("第2层");
        stack.push("第3层");
        System.out.println("push 后：" + stack);

        // peek → 查看栈顶（不删除）
        System.out.println("栈顶元素：" + stack.peek());

        // pop → 弹栈（获取并删除头部）
        String top = stack.pop();
        System.out.println("pop 弹出：" + top + "，剩余：" + stack);

        // 循环弹栈
        System.out.print("循环弹栈：");
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
        System.out.println();

        // ========== 6. 遍历方式 ==========
        System.out.println("\n========== 6. 遍历方式 ==========");

        LinkedList<String> traverseList = new LinkedList<>(Arrays.asList("Java", "Python", "Go", "Rust"));

        // 方式1：增强 for
        System.out.print("方式1（增强 for）：");
        for (String lang : traverseList) {
            System.out.print(lang + " ");
        }
        System.out.println();

        // 方式2：Iterator
        System.out.print("方式2（Iterator）：");
        Iterator<String> it = traverseList.iterator();
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }
        System.out.println();

        // 方式3：普通 for（不推荐，性能差）
        System.out.print("方式3（普通 for + 索引）：");
        for (int i = 0; i < traverseList.size(); i++) {
            System.out.print(traverseList.get(i) + " ");  // O(n²) 性能
        }
        System.out.println();

        // 方式4：descendingIterator（反向遍历）
        System.out.print("方式4（descendingIterator 反向）：");
        Iterator<String> descIt = traverseList.descendingIterator();
        while (descIt.hasNext()) {
            System.out.print(descIt.next() + " ");
        }
        System.out.println();

        // ========== 7. 特殊方法 ==========
        System.out.println("\n========== 7. 特殊方法 ==========");

        LinkedList<String> specialList = new LinkedList<>(Arrays.asList("A", "B", "C", "D"));

        // element → 查看头部（Queue 方法）
        System.out.println("element 查看头部：" + specialList.element());

        // remove → 删除头部（Queue 方法）
        String removed = specialList.remove();
        System.out.println("remove 删除头部：" + removed + "，剩余：" + specialList);

        // offerFirst → 头部插入
        specialList.offerFirst("X");
        System.out.println("offerFirst 后：" + specialList);

        // offerLast → 尾部插入
        specialList.offerLast("Z");
        System.out.println("offerLast 后：" + specialList);

        // pollFirst → 获取并删除头部
        System.out.println("pollFirst：" + specialList.pollFirst() + "，剩余：" + specialList);

        // pollLast → 获取并删除尾部
        System.out.println("pollLast：" + specialList.pollLast() + "，剩余：" + specialList);

        // ========== 8. 队列和栈的实际应用 ==========
        System.out.println("\n========== 8. 队列和栈的实际应用 ==========");

        // 队列应用：任务队列
        System.out.println("--- 队列应用：任务处理 ---");
        Queue<String> taskQueue = new LinkedList<>();
        taskQueue.offer("任务A");
        taskQueue.offer("任务B");
        taskQueue.offer("任务C");

        System.out.println("待处理任务：" + taskQueue);
        while (!taskQueue.isEmpty()) {
            String currentTask = taskQueue.poll();
            System.out.println("  处理：" + currentTask);
            // 处理过程中可能产生新任务
            if (currentTask.equals("任务B")) {
                taskQueue.offer("任务B-子任务1");
                taskQueue.offer("任务B-子任务2");
                System.out.println("  产生子任务：B-子任务1, B-子任务2");
            }
        }
        System.out.println("所有任务处理完成");

        // 栈应用：括号匹配检查
        System.out.println("\n--- 栈应用：括号匹配 ---");
        Deque<Character> bracketStack = new LinkedList<>();
        String expression = "({[]})";
        boolean valid = true;

        for (char c : expression.toCharArray()) {
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
            System.out.println("表达式 " + expression + " 括号匹配 ✅");
        } else {
            System.out.println("表达式 " + expression + " 括号不匹配 ❌");
        }

        // ========== 9. 性能对比演示 ==========
        System.out.println("\n========== 9. 性能对比演示 ==========");

        // 头部插入（LinkedList 擅长）
        LinkedList<Integer> linkedList = new LinkedList<>();
        long start = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            linkedList.addFirst(i);
        }
        long end = System.nanoTime();
        System.out.println("LinkedList 头部插入 10 万元素耗时：" + (end - start) / 1000000 + "ms");

        // ArrayList 头部插入（不擅长）
        List<Integer> arrayList = new java.util.ArrayList<>();
        start = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            arrayList.add(0, i);
        }
        end = System.nanoTime();
        System.out.println("ArrayList 头部插入 10 万元素耗时：" + (end - start) / 1000000 + "ms");
        System.out.println("✅ LinkedList 在头部插入/删除性能远优于 ArrayList");

        // ========== 10. 注意事项 ==========
        System.out.println("\n========== 10. 注意事项 ==========");

        // ⚠️ 注意1：随机访问慢
        System.out.println("⚠️ LinkedList 的 get(index) 是 O(n)，避免频繁使用");
        System.out.println("   错误示例：for (int i = 0; i < list.size(); i++) list.get(i)");
        System.out.println("   正确示例：使用 Iterator 或增强 for");

        // ⚠️ 注意2：内存占用大
        System.out.println("⚠️ LinkedList 每个节点都需要存储 prev、next 指针");
        System.out.println("   内存占用比 ArrayList 大 2-3 倍");

        // ⚠️ 注意3：不是线程安全
        System.out.println("⚠️ LinkedList 不是线程安全的");
        System.out.println("   解决方案：Collections.synchronizedList(new LinkedList<>())");

        // ⚠️ 注意4：LinkedList 实现 Deque，可以作为栈/队列使用
        System.out.println("✅ LinkedList 实现了 Deque 接口，可以同时用作 List、Queue、Stack");
        System.out.println("   推荐使用 Deque<String> stack = new LinkedList<>();");

        // ⚠️ 注意5：null 值支持
        LinkedList<String> nullList = new LinkedList<>();
        nullList.add(null);
        nullList.add("A");
        nullList.add(null);
        System.out.println("包含 null 的 LinkedList：" + nullList);
    }
}