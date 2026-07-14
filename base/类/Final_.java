/**
 * 学习目标：理解 final 关键字的三个作用
 * 1. final 类 → 不能被继承
 * 2. final 方法 → 不能被重写
 * 3. final 变量 → 值不能改变（常量）
 */
public class Final_ {

    public static void main(String[] args) {
        System.out.println("========== 1. final 变量 ==========");
        // final 修饰基本类型：值不能改变
        final int MAX_COUNT = 100;
        System.out.println("MAX_COUNT = " + MAX_COUNT);
        // MAX_COUNT = 200;  // ❌ 编译报错！final 变量不能重新赋值

        // final 修饰引用类型：引用不能变，但对象内部可以变
        final StringBuilder sb = new StringBuilder("Hello");
        sb.append(" World");  // ✅ 对象内容可以改变
        System.out.println("sb = " + sb);
        // sb = new StringBuilder("New");  // ❌ 编译报错！引用不能变

        // final 修饰方法参数：参数在方法内不能被修改
        printMessage("Hello Java");

        System.out.println("\n========== 2. final 方法 ==========");
        Parent p = new Parent();
        p.normalMethod();    // 普通方法
        p.finalMethod();     // final 方法

        Child c = new Child();
        c.normalMethod();    // ✅ 重写了普通方法
        c.finalMethod();     // ✅ 继承自父类，但不能重写

        System.out.println("\n========== 3. final 类 ==========");
        // final 类可以正常创建对象
        FinalClass fc = new FinalClass();
        fc.show();

        // ❌ 但不能被继承！
        // class SubClass extends FinalClass {}  // 编译报错！

        System.out.println("\n========== 4. final 与 内部类 ==========");
        // 匿名内部类访问的外部变量必须是 final 或 effectively final
        String message = "外部消息";  // effectively final（没有被修改）
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("匿名内部类访问：" + message);
            }
        };
        r.run();

        // String another = "test";
        // another = "changed";  // 如果修改了，匿名内部类访问会报错

        System.out.println("\n========== 5. final + static = 全局常量 ==========");
        System.out.println("PI = " + MathConstants.PI);
        System.out.println("APP_NAME = " + MathConstants.APP_NAME);
        // MathConstants.PI = 3.14;  // ❌ 编译报错！final 变量不能修改
    }

    // final 修饰方法参数
    public static void printMessage(final String msg) {
        System.out.println("参数 msg = " + msg);
        // msg = "新消息";  // ❌ 编译报错！final 参数不能修改
    }
}

// ============================================
// 场景1：final 修饰类 → 不能被继承
// ============================================
final class FinalClass {
    public void show() {
        System.out.println("这是 final 类，不能被继承");
    }
}

// ❌ 下面的代码编译报错
// class SubFinalClass extends FinalClass {}  // 报错：cannot inherit from final FinalClass

// ============================================
// 场景2：final 修饰方法 → 不能被重写
// ============================================
class Parent {
    // 普通方法：子类可以重写
    public void normalMethod() {
        System.out.println("Parent 的普通方法");
    }

    // final 方法：子类不能重写
    public final void finalMethod() {
        System.out.println("Parent 的 final 方法（不能被子类重写）");
    }
}

class Child extends Parent {
    // ✅ 可以重写普通方法
    @Override
    public void normalMethod() {
        System.out.println("Child 重写了普通方法");
    }

    // ❌ 不能重写 final 方法
    // @Override
    // public void finalMethod() {}  // 编译报错！不能重写 final 方法

    // 但是可以有自己的方法
    public void childMethod() {
        System.out.println("Child 自己的方法");
    }
}

// ============================================
// 场景3：final + static = 全局常量
// ============================================
class MathConstants {
    // static final = 全局常量，用大写字母 + 下划线命名
    public static final double PI = 3.14159265359;
    public static final String APP_NAME = "MyApplication";
    public static final int MAX_USERS = 1000;

    // 常量的值必须在声明时或静态代码块中初始化
    public static final String VERSION;

    static {
        VERSION = "1.0.0";  // 在静态代码块中初始化
        // VERSION = "2.0.0";  // ❌ 编译报错！只能赋值一次
    }
}

// ============================================
// 场景4：final 修饰的变量有不同的初始化时机
// ============================================
class FinalVariableDemo {
    // 1. 实例变量：必须在构造方法或实例初始化块中赋值
    private final String instanceFinal;

    // 2. 静态变量：必须在静态代码块中赋值
    private static final String staticFinal;

    static {
        staticFinal = "静态常量";
        // staticFinal = "第二次赋值";  // ❌ 只能赋值一次
    }

    // 构造方法中给 final 实例变量赋值
    public FinalVariableDemo(String value) {
        this.instanceFinal = value;  // ✅ 每个对象可以有不同的值，但一旦赋值就不能改
        // this.instanceFinal = "again";  // ❌ 只能赋值一次
    }

    public FinalVariableDemo() {
        this("默认值");  // 通过 this() 调用另一个构造方法
    }

    public void show() {
        System.out.println("instanceFinal = " + instanceFinal);
        System.out.println("staticFinal = " + staticFinal);
    }
}

// ============================================
// 场景5：final 与 匿名内部类的关系
// ============================================
class AnonymousFinalDemo {
    public void test() {
        String local = "局部变量";  // effectively final

        // 匿名内部类只能访问 final 或 effectively final 的变量
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("local = " + local);
                // 这里可以访问，因为 local 没有被修改
            }
        };
        r.run();

        // local = "修改";  // 如果取消注释，上面的匿名内部类会报错
    }
}
