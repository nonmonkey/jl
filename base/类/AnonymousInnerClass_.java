/**
 * 学习目标：理解匿名内部类（Anonymous Inner Class）
 * 匿名内部类 = 没有名字的内部类，一次性使用
 * 本质：创建了一个继承父类或实现接口的"临时子类"的对象
 *
 * 使用场景：只需要用一次的时候，懒得单独定义一个类
 */
public class AnonymousInnerClass_ {

    public static void main(String[] args) {
        System.out.println("========== 场景1：匿名内部类实现接口 ==========");
        // 传统方式：定义一个类实现接口，然后创建对象
        Greeting greeting1 = new EnglishGreeting();
        greeting1.sayHello();

        // 匿名内部类方式：直接 new base.接口，并实现方法
        // 语法：new 接口名() { 实现所有抽象方法 }
        Greeting greeting2 = new Greeting() {
            @Override
            public void sayHello() {
                System.out.println("👋 匿名内部类：Hello from Anonymous!");
            }
        };
        greeting2.sayHello();

        // 匿名内部类对象可以立即使用，不需要变量
        new Greeting() {
            @Override
            public void sayHello() {
                System.out.println("🔥 直接使用，连变量名都省了！");
            }
        }.sayHello();

        System.out.println("\n========== 场景2：匿名内部类继承抽象类 ==========");
        // 传统方式：定义一个子类继承抽象类
        Animal2 dog = new Dog2();
        dog.sound();

        // 匿名内部类方式：直接 new 抽象类，实现抽象方法
        Animal2 cat = new Animal2() {
            @Override
            public void sound() {
                System.out.println("🐱 匿名内部类的猫：喵喵喵～");
            }
        };
        cat.sound();

        // 匿名内部类中可以添加额外的方法，但外部无法直接调用（因为类型是父类）
        Animal2 bird = new Animal2() {
            @Override
            public void sound() {
                System.out.println("🐦 匿名内部类的鸟：叽叽喳喳");
            }

            // 额外方法
            public void fly() {
                System.out.println("鸟儿飞走了 🕊️");
            }
        };
        bird.sound();
        // bird.fly();  // ❌ 编译报错！Animal2 类型没有 fly() 方法
        // 想调用只能强转，但没人这么干

        System.out.println("\n========== 场景3：匿名内部类作为参数传递（最常用！） ==========");
        // 这就是 GUI 事件监听、排序等场景的核心用法
        performAction(new Action() {
            @Override
            public void execute() {
                System.out.println("✅ 执行了某个操作");
            }
        });

        // 多个方法的情况
        performTask(new Task() {
            @Override
            public void start() {
                System.out.println("🚀 任务开始");
            }

            @Override
            public void finish() {
                System.out.println("🏁 任务结束");
            }
        });

        System.out.println("\n========== 场景4：匿名内部类访问外部变量 ==========");
        String name = "张三";  // effectively final（没有被修改）
        int age = 25;

        Person2 person = new Person2() {
            @Override
            public void introduce() {
                System.out.println("我叫 " + name + "，今年 " + age + " 岁");
                System.out.println("匿名内部类可以访问外部局部变量（必须 final 或 effectively final）");
            }
        };
        person.introduce();

        // 如果修改外部变量，编译器会报错
        // String name2 = "李四";
        // name2 = "王五";  // 如果被修改，匿名内部类中访问会报错

        System.out.println("\n========== 场景5：对比传统方式 ==========");
        System.out.println("传统方式：需要额外创建类文件，代码分散");
        System.out.println("匿名内部类：写在现场，集中、简洁");

        System.out.println("\n========== 场景6：匿名内部类 vs Lambda（Java 8+） ==========");
        // 匿名内部类（传统）
        Calculator addOld = new Calculator() {
            @Override
            public int calculate(int a, int b) {
                return a + b;
            }
        };
        System.out.println("匿名内部类计算 3+5=" + addOld.calculate(3, 5));

        // Lambda 表达式（函数式接口才能用）
        // Calculator 是函数式接口（只有一个抽象方法）
        Calculator addNew = (a, b) -> a + b;
        System.out.println("Lambda 计算 3+5=" + addNew.calculate(3, 5));

        System.out.println("\n===== 场景7：最常见实战：排序 =====");
        String[] names = {"Bob", "Alice", "Charlie", "David"};

        // 传统匿名内部类排序
        java.util.Arrays.sort(names, new java.util.Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.length() - s2.length();  // 按长度排序
            }
        });
        System.out.println("按长度排序后：" + java.util.Arrays.toString(names));

        // 再排序：按字母顺序（用 Lambda，更简洁）
        java.util.Arrays.sort(names, (s1, s2) -> s1.compareTo(s2));
        System.out.println("按字母排序后：" + java.util.Arrays.toString(names));
    }

    // ============ 辅助方法：接收匿名内部类对象 ============
    public static void performAction(Action action) {
        System.out.println("正在执行动作...");
        action.execute();
        System.out.println("动作执行完毕");
    }

    public static void performTask(Task task) {
        System.out.println("开始执行任务...");
        task.start();
        task.finish();
        System.out.println("任务执行完毕");
    }
}

// ==================== 接口和类定义 ====================

// 场景1：base.接口
interface Greeting {
    void sayHello();
}

// 传统方式实现的类
class EnglishGreeting implements Greeting {
    @Override
    public void sayHello() {
        System.out.println("👋 Hello from EnglishGreeting!");
    }
}

// 场景2：抽象类
abstract class Animal2 {
    public abstract void sound();
}

// 传统方式继承的子类
class Dog2 extends Animal2 {
    @Override
    public void sound() {
        System.out.println("🐶 狗：汪汪汪！");
    }
}

// 场景3：作为参数的接口
interface Action {
    void execute();
}

interface Task {
    void start();
    void finish();
}

// 场景4：普通类（用来创建匿名子类）
class Person2 {
    public void introduce() {
        System.out.println("我是 Person2");
    }
}

// 场景6：函数式接口（只有一个抽象方法）
@FunctionalInterface
interface Calculator {
    int calculate(int a, int b);
}