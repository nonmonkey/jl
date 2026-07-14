import java.lang.annotation.*;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.*;

/**
 * 学习目标：理解注解（Annotation）的作用和使用
 * 注解 = 给代码贴标签，让编译器/框架在运行时读取并执行逻辑
 *
 * 三个内置注解（最常见）：
 * - @Override      → 检查是否真的重写了父类方法
 * - @Deprecated    → 标记方法已过时，不推荐使用
 * - @SuppressWarnings → 抑制编译警告
 *
 * 四个元注解（用来定义注解的注解）：
 * - @Target        → 注解能贴在什么地方（类、方法、属性...）
 * - @Retention     → 注解保留到什么时候（源码、编译期、运行期）
 * - @Documented    → 是否生成到 Javadoc
 * - @Inherited     → 子类是否继承父类的注解
 */
public class Annotation_ {

    public static void main(String[] args) throws Exception {
        System.out.println("========== 1. 内置注解演示 ==========");

        // @Override：编译器会检查这个方法是否真的重写了父类方法
        Child2 child = new Child2();
        child.sayHello();   // 输出：Child2 说你好

        // @Deprecated：调用过时方法会有警告
        child.oldMethod();  // IDE 会划掉这个方法，提示已过时

        // @SuppressWarnings：抑制警告（看类上的注解）

        System.out.println("\n========== 2. 自定义注解 + 反射读取 ==========");

        // 使用自定义注解
        UserService service = new UserService();

        // 通过反射读取类上的注解
        Class<?> clazz = service.getClass();

        // 读取类上的 @MyAnnotation
        if (clazz.isAnnotationPresent(MyAnnotation.class)) {
            MyAnnotation classAnno = clazz.getAnnotation(MyAnnotation.class);
            System.out.println("类上的注解：value=" + classAnno.value() +
                    ", priority=" + classAnno.priority());
        }

        // 读取方法上的注解
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(MyAnnotation.class)) {
                MyAnnotation methodAnno = method.getAnnotation(MyAnnotation.class);
                System.out.println("方法 " + method.getName() +
                        " 上的注解：value=" + methodAnno.value() +
                        ", priority=" + methodAnno.priority());

                // 模拟框架：根据 priority 值决定执行顺序
                if (methodAnno.priority() == 1) {
                    System.out.println("  → 高优先级，先执行");
                }
            }
        }

        System.out.println("\n========== 3. 注解模拟 Junit @Test2 ==========");
        Test2Runner.run(Test2Class.class);
    }
}

// ==================== 内置注解演示 ====================

class Parent2 {
    public void sayHello() {
        System.out.println("Parent2 说你好");
    }
}

class Child2 extends Parent2 {
    // @Override：告诉编译器"我要重写父类方法"
    // 如果父类没有这个方法，编译器会报错
    @Override
    public void sayHello() {
        System.out.println("Child2 说你好");
    }

    // @Deprecated：标记这个方法已过时
    @Deprecated
    public void oldMethod() {
        System.out.println("这是一个过时的方法，不建议使用");
    }
}

// @SuppressWarnings：抑制编译警告
//@SuppressWarnings("all")
@SuppressWarnings({"unused", ""})
class SuppressDemo {
    // 这里即使有未使用的变量，也不会警告
    private String unused;
    private List list;
}

// ==================== 自定义注解 ====================

// 1. @Target：指定这个注解能贴在哪里
//    ElementType.TYPE → 类/接口
//    ElementType.METHOD → 方法
//    ElementType.FIELD → 属性
//    ElementType.PARAMETER → 参数
//    可以多个：{ ElementType.TYPE, ElementType.METHOD }

// 2. @Retention：指定注解保留到什么时候
//    RetentionPolicy.SOURCE → 只在源码中（编译后丢失）
//    RetentionPolicy.CLASS → 保留到字节码（默认）
//    RetentionPolicy.RUNTIME → 保留到运行时（反射可读取）

// 3. @Documented：生成 Javadoc 时包含注解

// 4. @Inherited：子类继承父类的注解

@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@interface MyAnnotation {
    // 注解的属性：类似方法声明
    String value() default "默认值";  // 常用属性名用 value
    int priority() default 5;         // 优先级
}

// ==================== 使用自定义注解 ====================

@MyAnnotation(value = "用户服务类", priority = 1)
class UserService {

    @MyAnnotation(value = "查询用户", priority = 1)
    public void queryUser() {
        System.out.println("查询用户...");
    }

    @MyAnnotation(value = "删除用户", priority = 5)
    public void deleteUser() {
        System.out.println("删除用户...");
    }

    @MyAnnotation(value = "更新用户", priority = 3)
    public void updateUser() {
        System.out.println("更新用户...");
    }
}

// ==================== 模拟 @Test2 注解 ====================

// 定义 @Test2 注解
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface Test2 {
    // 可以设置期望的异常类型
    Class<? extends Throwable> expected() default None.class;

    // 占位类，表示没有期望异常
    class None extends Throwable {}
}

// 模拟 JUnit 测试运行器
class Test2Runner {
    public static void run(Class<?> clazz) throws Exception {
        System.out.println("=== 开始运行测试 ===");

        // 获取所有方法
        Method[] methods = clazz.getDeclaredMethods();

        // 统计
        int passed = 0;
        int failed = 0;

        for (Method method : methods) {
            // 检查是否有 @Test2 注解
            if (method.isAnnotationPresent(Test2.class)) {
                System.out.println("\n执行测试方法：" + method.getName());

                try {
                    // 创建测试类实例
                    Object instance = clazz.getDeclaredConstructor().newInstance();

                    // 执行测试方法
                    method.invoke(instance);

                    System.out.println("  ✅ 测试通过");
                    passed++;

                } catch (Exception e) {
                    System.out.println("  ❌ 测试失败：" + e.getCause().getMessage());
                    failed++;
                }
            }
        }

        System.out.println("\n=== 测试结果 ===");
        System.out.println("通过：" + passed);
        System.out.println("失败：" + failed);
    }
}

// 使用 @Test2 注解的测试类
class Test2Class {

    @Test2
    public void testAdd() {
        System.out.println("  测试加法：1+1=2");
        assert 1 + 1 == 2;
    }

    @Test2
    public void testSubtract() {
        System.out.println("  测试减法：2-1=1");
        assert 2 - 1 == 1;
    }

    @Test2
    public void testFail() {
        System.out.println("  测试失败案例");
        assert 1 + 1 == 3;  // 这个断言会失败
    }
}