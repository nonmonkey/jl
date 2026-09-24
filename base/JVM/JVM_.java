import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.lang.management.RuntimeMXBean;
import java.util.ArrayList;
import java.util.List;

/**
 * JVM（Java Virtual Machine）
 * Java 虚拟机，运行 Java 字节码的虚拟计算机
 *
 * 核心概念：
 * - JVM：Java 虚拟机，负责运行 Java 字节码
 * - JRE：Java 运行环境 = JVM + 核心类库
 * - JDK：Java 开发工具包 = JRE + 编译工具
 *
 * JVM 内存结构（运行时数据区）：
 *
 * 1. 线程私有（每个线程独立）
 *    - 程序计数器（PC Register）：当前线程执行的字节码行号
 *    - 虚拟机栈（VM Stack）：方法调用栈帧
 *    - 本地方法栈（Native Method Stack）：native 方法栈帧
 *
 * 2. 线程共享（所有线程共享）
 *    - 堆（Heap）：对象实例、数组
 *    - 方法区（Method Area）：类信息、常量、静态变量
 *    - 运行时常量池（Runtime Constant Pool）：字面量、符号引用
 *
 * 3. 直接内存（Direct Memory）
 *    - NIO 使用的堆外内存
 *
 * 堆内存结构：
 * - 新生代（Young Generation）
 *   - Eden 区（伊甸园）
 *   - Survivor 区（幸存区）
 *     - S0（From Survivor）
 *     - S1（To Survivor）
 * - 老年代（Old Generation / Tenured）
 *
 * 垃圾回收（GC）：
 * - 判断对象是否可回收：可达性分析
 * - GC Roots：
 *   - 虚拟机栈中引用的对象
 *   - 方法区中静态属性引用的对象
 *   - 方法区中常量引用的对象
 *   - 本地方法栈中引用的对象
 *
 * GC 算法：
 * - 标记-清除（Mark-Sweep）：标记垃圾，清除
 * - 复制（Copying）：将存活对象复制到另一块区域
 * - 标记-整理（Mark-Compact）：标记垃圾，整理存活对象
 * - 分代收集（Generational）：新生代用复制，老年代用标记-整理
 *
 * GC 收集器：
 * - Serial：串行，单线程
 * - ParNew：并行，多线程
 * - Parallel Scavenge：并行，吞吐量优先
 * - CMS：并发标记清除，低延迟
 * - G1：分区收集，兼顾吞吐和延迟
 * - ZGC：超低延迟（Java 11+）
 *
 * 类加载机制：
 * - 加载 → 验证 → 准备 → 解析 → 初始化 → 使用 → 卸载
 * - 类加载器：
 *   - Bootstrap ClassLoader：加载 JDK 核心类
 *   - Extension ClassLoader：加载扩展类
 *   - Application ClassLoader：加载应用类
 *   - 自定义 ClassLoader：用户自定义
 *
 * 双亲委派模型：
 * - 类加载请求先交给父加载器
 * - 父加载器无法加载时，才自己加载
 * - 保证核心类不被篡改
 *
 * 常用 JVM 参数：
 * - -Xms：初始堆大小
 * - -Xmx：最大堆大小
 * - -Xmn：新生代大小
 * - -XX:MetaspaceSize：元空间大小
 * - -XX:+UseG1GC：使用 G1 收集器
 * - -XX:+PrintGCDetails：打印 GC 详情
 *
 * 常用 JVM 工具：
 * - jps：查看 Java 进程
 * - jstat：查看 GC 统计
 * - jmap：查看内存快照
 * - jstack：查看线程堆栈
 * - jconsole：可视化监控
 * - jvisualvm：性能分析
 *
 * 适用场景：
 * - 性能调优 ⭐⭐⭐⭐⭐
 * - 内存泄漏排查 ⭐⭐⭐⭐⭐
 * - 面试必问 ⭐⭐⭐⭐⭐
 *
 * 注意事项：
 * - 理解内存结构是调优的基础
 * - GC 不是万能的，要注意内存泄漏
 * - 类加载机制是框架的基础
 */
public class JVM_ {

    public static void main(String[] args) {

        // ========== 1. JVM 内存结构演示 ==========
        System.out.println("========== 1. JVM 内存结构 ==========");

        System.out.println("JVM（Java Virtual Machine） 内存结构：");
        System.out.println("  线程私有：");
        System.out.println("    - 程序计数器（PC Register）（Program Counter Register）");
        System.out.println("    - 虚拟机栈（VM Stack）");
        System.out.println("    - 本地方法栈（Native Method Stack）");
        System.out.println("  线程共享：");
        System.out.println("    - 堆（Heap）");
        System.out.println("    - 方法区（Method Area）");
        System.out.println("    - 运行时常量池（Runtime Constant Pool）");
        System.out.println("  直接内存：");
        System.out.println("    - Direct Memory（NIO 使用）");

        // ========== 2. 堆内存结构 ==========
        System.out.println("\n========== 2. 堆内存结构 ==========");

        System.out.println("堆内存结构：");
        System.out.println("  新生代（Young Generation）：");
        System.out.println("    - Eden 区（伊甸园）");
        System.out.println("    - Survivor 区（幸存区）");
        System.out.println("      - S0（From Survivor）");
        System.out.println("      - S1（To Survivor）");
        System.out.println("  老年代（Old Generation / Tenured）");
        System.out.println();
        System.out.println("默认比例：");
        System.out.println("  Eden : S0 : S1 = 8 : 1 : 1");
        System.out.println("  新生代 : 老年代 = 1 : 2");

        // ========== 3. 获取 JVM 内存信息 ==========
        System.out.println("\n========== 3. 获取 JVM 内存信息 ==========");

        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();

        // 堆内存
        MemoryUsage heapMemory = memoryMXBean.getHeapMemoryUsage();
        System.out.println("堆内存：");
        System.out.println("  初始：" + formatBytes(heapMemory.getInit()));
        System.out.println("  已用：" + formatBytes(heapMemory.getUsed()));
        System.out.println("  最大：" + formatBytes(heapMemory.getMax()));
        System.out.println("  已提交：" + formatBytes(heapMemory.getCommitted()));

        // 非堆内存（方法区/元空间）
        MemoryUsage nonHeapMemory = memoryMXBean.getNonHeapMemoryUsage();
        System.out.println("\n非堆内存（方法区/元空间）：");
        System.out.println("  初始：" + formatBytes(nonHeapMemory.getInit()));
        System.out.println("  已用：" + formatBytes(nonHeapMemory.getUsed()));
        System.out.println("  最大：" + formatBytes(nonHeapMemory.getMax()));

        // ========== 4. 获取 JVM 运行时信息 ==========
        System.out.println("\n========== 4. JVM 运行时信息 ==========");

        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();

        System.out.println("JVM 名称：" + runtimeMXBean.getVmName());
        System.out.println("JVM 版本：" + runtimeMXBean.getVmVersion());
        System.out.println("JVM 厂商：" + runtimeMXBean.getVmVendor());
        System.out.println("启动时间：" + runtimeMXBean.getStartTime());
        System.out.println("运行时长：" + runtimeMXBean.getUptime() + "ms");
        System.out.println("输入参数：" + runtimeMXBean.getInputArguments());

        // ========== 5. 获取处理器和内存信息 ==========
        System.out.println("\n========== 5. 处理器和内存信息 ==========");

        Runtime runtime = Runtime.getRuntime();

        System.out.println("CPU 核心数：" + runtime.availableProcessors());
        System.out.println("JVM 最大内存：" + formatBytes(runtime.maxMemory()));
        System.out.println("JVM 总内存：" + formatBytes(runtime.totalMemory()));
        System.out.println("JVM 空闲内存：" + formatBytes(runtime.freeMemory()));
        System.out.println("JVM 已用内存：" + formatBytes(runtime.totalMemory() - runtime.freeMemory()));

        // ========== 6. 垃圾回收演示 ==========
        System.out.println("\n========== 6. 垃圾回收演示 ==========");

        // 分配大量对象，触发 GC
        System.out.println("分配对象前：");
        System.out.println("  已用内存：" + formatBytes(runtime.totalMemory() - runtime.freeMemory()));

        List<byte[]> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(new byte[1024 * 1024]);  // 每个 1MB
        }

        System.out.println("分配对象后：");
        System.out.println("  已用内存：" + formatBytes(runtime.totalMemory() - runtime.freeMemory()));

        // 释放对象
        list.clear();
        System.gc();  // 建议 GC

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("GC 后：");
        System.out.println("  已用内存：" + formatBytes(runtime.totalMemory() - runtime.freeMemory()));

        // ========== 7. GC 算法 ==========
        System.out.println("\n========== 7. GC 算法 ==========");

        System.out.println("GC 算法：");
        System.out.println("  1. 标记-清除（Mark-Sweep）");
        System.out.println("     - 标记垃圾对象，然后清除");
        System.out.println("     - 缺点：内存碎片");
        System.out.println();
        System.out.println("  2. 复制（Copying）");
        System.out.println("     - 将存活对象复制到另一块区域");
        System.out.println("     - 优点：无碎片");
        System.out.println("     - 缺点：浪费一半空间");
        System.out.println("     - 适用：新生代");
        System.out.println();
        System.out.println("  3. 标记-整理（Mark-Compact）");
        System.out.println("     - 标记垃圾，整理存活对象");
        System.out.println("     - 优点：无碎片");
        System.out.println("     - 缺点：效率较低");
        System.out.println("     - 适用：老年代");
        System.out.println();
        System.out.println("  4. 分代收集（Generational）");
        System.out.println("     - 新生代用复制算法");
        System.out.println("     - 老年代用标记-整理算法");

        // ========== 8. GC 收集器 ==========
        System.out.println("\n========== 8. GC 收集器 ==========");

        System.out.println("GC 收集器：");
        System.out.println("  1. Serial（串行）");
        System.out.println("     - 单线程");
        System.out.println("     - 适用：客户端、小内存");
        System.out.println();
        System.out.println("  2. ParNew（并行）");
        System.out.println("     - 多线程");
        System.out.println("     - 适用：服务端、配合 CMS");
        System.out.println();
        System.out.println("  3. Parallel Scavenge（并行）");
        System.out.println("     - 多线程，吞吐量优先");
        System.out.println("     - 适用：后台计算");
        System.out.println();
        System.out.println("  4. CMS（并发标记清除）");
        System.out.println("     - 并发，低延迟");
        System.out.println("     - 适用：Web 应用");
        System.out.println();
        System.out.println("  5. G1（Garbage First）");
        System.out.println("     - 分区收集，兼顾吞吐和延迟");
        System.out.println("     - 适用：大内存、服务端（Java 9+ 默认）");
        System.out.println();
        System.out.println("  6. ZGC（Java 11+）");
        System.out.println("     - 超低延迟（<10ms）");
        System.out.println("     - 适用：超大内存、低延迟");

        // ========== 9. 类加载机制 ==========
        System.out.println("\n========== 9. 类加载机制 ==========");

        System.out.println("类加载过程：");
        System.out.println("  1. 加载（Loading）");
        System.out.println("     - 读取字节码，生成 Class 对象");
        System.out.println();
        System.out.println("  2. 验证（Verification）");
        System.out.println("     - 验证字节码合法性");
        System.out.println();
        System.out.println("  3. 准备（Preparation）");
        System.out.println("     - 为静态变量分配内存，设默认值");
        System.out.println();
        System.out.println("  4. 解析（Resolution）");
        System.out.println("     - 符号引用转直接引用");
        System.out.println();
        System.out.println("  5. 初始化（Initialization）");
        System.out.println("     - 执行静态代码块，赋值静态变量");
        System.out.println();
        System.out.println("  6. 使用（Using）");
        System.out.println("  7. 卸载（Unloading）");

        System.out.println("\n类加载器：");
        System.out.println("  Bootstrap ClassLoader（启动类加载器）");
        System.out.println("    - 加载 JDK 核心类（java.lang.*）");
        System.out.println("    - C++ 实现，Java 中为 null");
        System.out.println();
        System.out.println("  Extension ClassLoader（扩展类加载器）");
        System.out.println("    - 加载扩展类（jre/lib/ext）");
        System.out.println();
        System.out.println("  Application ClassLoader（应用类加载器）");
        System.out.println("    - 加载应用类（classpath）");
        System.out.println();
        System.out.println("  自定义 ClassLoader");
        System.out.println("    - 用户自定义加载逻辑");

        // 演示类加载器
        ClassLoader appClassLoader = JVM_.class.getClassLoader();
        System.out.println("\n当前类的类加载器：" + appClassLoader);

        ClassLoader parent = appClassLoader.getParent();
        System.out.println("父加载器：" + parent);

        ClassLoader grandParent = parent != null ? parent.getParent() : null;
        System.out.println("祖父加载器：" + grandParent + "（Bootstrap 为 null）");

        // ========== 10. 双亲委派模型 ==========
        System.out.println("\n========== 10. 双亲委派模型 ==========");

        System.out.println("双亲委派模型：");
        System.out.println("  1. 类加载请求先交给父加载器");
        System.out.println("  2. 父加载器无法加载时，才自己加载");
        System.out.println();
        System.out.println("加载顺序：");
        System.out.println("  Bootstrap → Extension → Application → 自定义");
        System.out.println();
        System.out.println("优点：");
        System.out.println("  - 保证核心类不被篡改");
        System.out.println("  - 避免类重复加载");
        System.out.println();
        System.out.println("打破双亲委派：");
        System.out.println("  - 重写 loadClass() 方法");
        System.out.println("  - 线程上下文类加载器（Thread.currentThread().getContextClassLoader()）");
        System.out.println("  - OSGi、Tomcat 等框架会打破");

        // ========== 11. 常用 JVM 参数 ==========
        System.out.println("\n========== 11. 常用 JVM 参数 ==========");

        System.out.println("内存参数：");
        System.out.println("  -Xms512m          初始堆大小");
        System.out.println("  -Xmx1024m         最大堆大小");
        System.out.println("  -Xmn256m          新生代大小");
        System.out.println("  -XX:MetaspaceSize=128m  元空间大小");
        System.out.println("  -XX:MaxMetaspaceSize=256m 元空间最大值");
        System.out.println();
        System.out.println("GC 参数：");
        System.out.println("  -XX:+UseG1GC      使用 G1 收集器");
        System.out.println("  -XX:+UseConcMarkSweepGC  使用 CMS");
        System.out.println("  -XX:+PrintGCDetails  打印 GC 详情");
        System.out.println("  -XX:+HeapDumpOnOutOfMemoryError  OOM 时导出堆快照");
        System.out.println("  -XX:HeapDumpPath=/path  堆快照路径");
        System.out.println();
        System.out.println("其他参数：");
        System.out.println("  -XX:+DisableExplicitGC  禁用 System.gc()");
        System.out.println("  -XX:SurvivorRatio=8     Eden:S0:S1 比例");
        System.out.println("  -XX:MaxTenuringThreshold=15  对象晋升老年代年龄");

        // ========== 12. 常用 JVM 工具 ==========
        System.out.println("\n========== 12. 常用 JVM 工具 ==========");

        System.out.println("命令行工具：");
        System.out.println("  jps              → 查看 Java 进程");
        System.out.println("  jstat            → 查看 GC 统计");
        System.out.println("  jmap             → 查看内存快照");
        System.out.println("  jstack           → 查看线程堆栈");
        System.out.println("  jinfo            → 查看 JVM 参数");
        System.out.println("  jcmd             → 多功能命令");
        System.out.println();
        System.out.println("可视化工具：");
        System.out.println("  jconsole         → 监控工具");
        System.out.println("  jvisualvm        → 性能分析");
        System.out.println("  MAT              → 内存分析");
        System.out.println("  Arthas           → 阿里诊断工具");

        // ========== 13. 内存泄漏排查 ==========
        System.out.println("\n========== 13. 内存泄漏排查 ==========");

        System.out.println("排查步骤：");
        System.out.println("  1. jps 找到进程 ID");
        System.out.println("  2. jmap -dump:format=b,file=heap.hprof <pid>");
        System.out.println("  3. 用 MAT 或 jvisualvm 分析堆快照");
        System.out.println("  4. 找占用内存最多的对象");
        System.out.println("  5. 分析 GC Roots 引用链");
        System.out.println();
        System.out.println("常见内存泄漏：");
        System.out.println("  - 静态集合持有对象");
        System.out.println("  - 未关闭的资源（连接、流）");
        System.out.println("  - ThreadLocal 未 remove");
        System.out.println("  - 监听器未注销");
        System.out.println("  - 缓存未设置过期");

        // ========== 14. 注意事项 ==========
        System.out.println("\n========== 14. 注意事项 ==========");

        System.out.println("⚠️ 堆内存不是越大越好");
        System.out.println("   过大会导致 GC 时间长");

        System.out.println("\n⚠️ 新生代和老年代比例");
        System.out.println("   默认 1:2");
        System.out.println("   对象朝生夕死，新生代可以大一些");

        System.out.println("\n⚠️ System.gc() 只是建议");
        System.out.println("   不保证立即执行");

        System.out.println("\n⚠️ 元空间（Metaspace）替代永久代");
        System.out.println("   Java 8 开始，永久代被元空间替代");
        System.out.println("   元空间使用本地内存");

        System.out.println("\n⚠️ 内存泄漏 vs 内存溢出");
        System.out.println("   内存泄漏：对象无法回收，内存逐渐减少");
        System.out.println("   内存溢出：内存不足，抛出 OutOfMemoryError");

        System.out.println("\n⚠️ 类加载器是框架的基础");
        System.out.println("   Tomcat、Spring Boot 等都有自定义类加载器");
    }

    // ========== 辅助方法 ==========

    /**
     * 格式化字节大小
     */
    private static String formatBytes(long bytes) {
        if (bytes < 1024) return bytes + " B";
        if (bytes < 1024 * 1024) return String.format("%.2f KB", bytes / 1024.0);
        if (bytes < 1024 * 1024 * 1024) return String.format("%.2f MB", bytes / (1024.0 * 1024));
        return String.format("%.2f GB", bytes / (1024.0 * 1024 * 1024));
    }
}