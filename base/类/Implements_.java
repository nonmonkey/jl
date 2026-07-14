/**
 * 学习目标：理解 implements（实现接口）的作用
 * base.接口 = 行为规范/合同，实现接口的类必须实现所有抽象方法
 * 关键词：interface（定义接口），implements（实现接口）
 */
public class Implements_ {

    public static void main(String[] args) {
        // 创建实现了接口的对象
        Plane plane = new Plane("空客A380");
        plane.fly();        // 必须实现的方法
        plane.takeOff();    // 必须实现的方法
        plane.landing();

        System.out.println();

        Bird bird = new Bird("老鹰");
        bird.fly();
        bird.takeOff();
        bird.landing();

        System.out.println();

        // 接口引用指向实现类对象（多态，详见第9模块）
        Flyable flyable1 = new Plane("波音747");
        Flyable flyable2 = new Bird("麻雀");
        flyable1.fly();
        flyable2.fly();
    }
}

// ============ 1. 定义接口 ============
// 接口使用 interface 关键字定义
// 接口中的方法默认是 public abstract（公共抽象方法），不需要写修饰符
interface Flyable {
    // 抽象方法：只有方法声明，没有方法体（没有 {} 和实现代码）
    void fly();

    void takeOff();

    // 接口中也可以有默认方法（Java 8+），有默认实现
    default void landing() {
        System.out.println("正在降落... 请系好安全带");
    }

    // 接口中可以有静态方法（Java 8+）
    static void showMessage() {
        System.out.println("✈️ 能飞的东西都实现了 Flyable base.接口");
    }
}

// ============ 2. 类实现接口 ============
// 使用 implements 关键字实现接口
// 必须实现接口中的所有抽象方法，否则编译报错
class Plane implements Flyable {
    private String model;

    public Plane(String model) {
        this.model = model;
    }

    // 必须实现 fly() 方法
    @Override
    public void fly() {
        System.out.println(model + " 正在飞行，速度 900km/h");
    }

    // 必须实现 takeOff() 方法
    @Override
    public void takeOff() {
        System.out.println(model + " 正在跑道滑行起飞 🛫");
    }

    // 可以不重写 default 方法（因为有默认实现）
    // 但可以按需重写
    @Override
    public void landing() {
        System.out.println(model + " 正在自动降落，放下起落架 🛬");
    }
}

// ============ 另一个类实现同一个接口 ============
class Bird implements Flyable {
    private String name;

    public Bird(String name) {
        this.name = name;
    }

    @Override
    public void fly() {
        System.out.println(name + " 展翅飞翔，速度 50km/h");
    }

    @Override
    public void takeOff() {
        System.out.println(name + " 扑扇翅膀，腾空而起 🦅");
    }

    // 不重写 landing()，使用接口的默认实现
    // 所以会输出："正在降落... 请系好安全带"
}

// ============ 3. 一个类可以实现多个接口 ============
// 接口弥补了 Java 单继承的不足
interface Swimmable {
    void swim();
}

interface Runnable {
    void run();
}

// 类用逗号分隔，可以实现多个接口
class Duck implements Flyable, Swimmable, Runnable {
    private String name;

    public Duck(String name) {
        this.name = name;
    }

    // 实现 Flyable 的两个抽象方法
    @Override
    public void fly() {
        System.out.println(name + " 飞得不高，但也能飞 🦆");
    }

    @Override
    public void takeOff() {
        System.out.println(name + " 从水面起飞，溅起水花");
    }

    // 实现 Swimmable 的方法
    @Override
    public void swim() {
        System.out.println(name + " 在水里游泳 🏊");
    }

    // 实现 Runnable 的方法
    @Override
    public void run() {
        System.out.println(name + " 在岸上摇摇摆摆走路 🚶");
    }
}

// ============ 4. 接口可以继承接口 ============
interface SuperFlyable extends Flyable {
    void supersonicFlight(); // 超音速飞行
}

// 实现这个接口的类，必须实现所有抽象方法（包括父接口的）
class SupersonicPlane implements SuperFlyable {
    private String name;

    public SupersonicPlane(String name) {
        this.name = name;
    }

    @Override
    public void fly() {
        System.out.println(name + " 超音速巡航");
    }

    @Override
    public void takeOff() {
        System.out.println(name + " 垂直起飞 🔥");
    }

    @Override
    public void supersonicFlight() {
        System.out.println(name + " 突破音障 💥 1.5 马赫");
    }
}