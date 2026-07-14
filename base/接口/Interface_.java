/**
 * 新章节：接口深度解析
 * 目标：理解接口的语法、作用以及 Java 8/9 之后的新特性
 */
public class Interface_ {

    public static void main(String[] args) {
        System.out.println("========== 1. 接口的基本使用：多态 ==========");
        // 接口引用指向实现类（这就是多态）
        Flyable4 f1 = new Bird2("小燕子");
        Flyable4 f2 = new Rocket("长征五号");

        f1.fly(); // 输出：小燕子 正在扑腾翅膀飞
        f2.fly(); // 输出：长征五号 正在喷射火焰升空

        System.out.println("\n========== 2. 接口的多继承能力 ==========");
        // 一个类可以实现多个接口
        Duck2 duck = new Duck2("唐老鸭");
        duck.fly();    // 来自 Flyable4
        duck.swim();   // 来自 Swimmable4
        duck.walk();   // 来自 Walkable

        System.out.println("\n========== 3. Java 8 新特性：默认方法 (default) ==========");
        // 接口可以拥有默认实现的方法
        Car car = new Car("特斯拉");
        car.run();     // 使用接口默认的 run() 方法
        car.stop();    // 重写了默认的 stop() 方法

        System.out.println("\n========== 4. Java 8 新特性：静态方法 ==========");
        // 接口可以直接拥有静态方法，通过 接口名.方法名() 调用
        Vehicle.checkStatus();
    }
}

// ==================== 1. 定义接口：行为规范 ====================
// 接口使用 interface 关键字
interface Flyable4 {
    // 接口中的变量默认是 public static final（常量）
    int MAX_SPEED = 1000;

    // 抽象方法：默认是 public abstract（不能有方法体）
    void fly();
}

// ==================== 2. 实现多接口：一个类可以拥有多种能力 ====================
interface Swimmable4 {
    void swim();
}

interface Walkable {
    void walk();
}

// 实现类必须实现所有抽象方法，除非它是抽象类
class Duck2 implements Flyable4, Swimmable4, Walkable {
    private String name;

    public Duck2(String name) {
        this.name = name;
    }

    @Override
    public void fly() {
        System.out.println(name + " 正在飞");
    }

    @Override
    public void swim() {
        System.out.println(name + " 正在游泳");
    }

    @Override
    public void walk() {
        System.out.println(name + " 正在走路");
    }
}

// 其他实现类
class Bird2 implements Flyable4 {
    private String name;
    public Bird2(String name) { this.name = name; }

    @Override
    public void fly() {
        System.out.println(name + " 正在扑腾翅膀飞");
    }
}

class Rocket implements Flyable4 {
    private String name;
    public Rocket(String name) { this.name = name; }

    @Override
    public void fly() {
        System.out.println(name + " 正在喷射火焰升空");
    }
}

// ==================== 3. Java 8 特性：默认方法 (default) ====================
// 允许接口提供默认实现，避免修改所有实现类
interface Vehicle {
    // 抽象方法
    void run();

    // 默认方法：有方法体，实现类可以选择重写或者直接继承
    default void stop() {
        System.out.println("车辆正在刹车停止...");
    }

    // Java 8 接口静态方法
    static void checkStatus() {
        System.out.println("检查车辆状态: 一切正常");
    }
}

class Car implements Vehicle {
    private String name;

    public Car(String name) { this.name = name; }

    @Override
    public void run() {
        System.out.println(name + " 正在行驶");
    }

//    // 重写默认方法（也可以不重写，直接用默认的）
//    @Override
//    public void stop() {
//        System.out.println(name + " 正在使用 ABS 防抱死系统刹车");
//    }
}