import java.util.ArrayList;
import java.util.List;

/**
 * 设计模式
 * 软件设计中常见问题的可复用解决方案
 */
public class DesignPattern_ {

    public static void main(String[] args) {

        // ========== 1. 单例模式 ==========
        System.out.println("========== 1. 单例模式 ==========");

        Singleton singleton1 = Singleton.getInstance();
        Singleton singleton2 = Singleton.getInstance();
        System.out.println("singleton1 == singleton2：" + (singleton1 == singleton2));
        singleton1.doSomething();

        // ========== 2. 工厂模式 ==========
        System.out.println("\n========== 2. 工厂模式 ==========");

        // 简单工厂
        Shape1 circle = ShapeFactory1.createShape("circle");
        Shape1 rectangle = ShapeFactory1.createShape("rectangle");
        circle.draw();
        rectangle.draw();

        // 工厂方法
        ShapeFactoryMethod1 circleFactory = new CircleFactory1();
        Shape1 circle2 = circleFactory.createShape();
        circle2.draw();

        // ========== 3. 建造者模式 ==========
        System.out.println("\n========== 3. 建造者模式 ==========");

        Computer computer = new Computer.Builder()
                .cpu("Intel i7")
                .memory("16GB")
                .disk("512GB SSD")
                .build();
        System.out.println("电脑配置：" + computer);

        // ========== 4. 代理模式 ==========
        System.out.println("\n========== 4. 代理模式 ==========");

        // 静态代理
        UserService1 userService = new UserServiceProxy();
        userService.save();

        // 动态代理（JDK 动态代理）
        UserService1 dynamicProxy = DynamicProxyFactory.createProxy(new UserServiceImpl());
        dynamicProxy.save();

        // ========== 5. 策略模式 ==========
        System.out.println("\n========== 5. 策略模式 ==========");

        PaymentContext context = new PaymentContext();

        context.setStrategy(new AlipayStrategy());
        context.pay(100);

        context.setStrategy(new WechatPayStrategy());
        context.pay(200);

        context.setStrategy(new CreditCardStrategy());
        context.pay(300);

        // ========== 6. 观察者模式 ==========
        System.out.println("\n========== 6. 观察者模式 ==========");

        NewsSubject subject = new NewsSubject();

        Observer user1 = new NewsObserver("张三");
        Observer user2 = new NewsObserver("李四");

        subject.registerObserver(user1);
        subject.registerObserver(user2);

        subject.publishNews("Java 21 发布！");

        // ========== 7. 模板方法模式 ==========
        System.out.println("\n========== 7. 模板方法模式 ==========");

        Beverage coffee = new Coffee();
        coffee.prepare();

        Beverage tea = new Tea();
        tea.prepare();

        // ========== 8. 适配器模式 ==========
        System.out.println("\n========== 8. 适配器模式 ==========");

        ChineseSpeaker chineseSpeaker = new EnglishToChineseAdapter(new EnglishSpeaker());
        chineseSpeaker.speakChinese("你好");

        // ========== 9. 装饰器模式 ==========
        System.out.println("\n========== 9. 装饰器模式 ==========");

        Coffee2 coffee2 = new SimpleCoffee();
        System.out.println("基础咖啡：" + coffee2.getDescription() + "，价格：" + coffee2.getCost());

        coffee2 = new MilkDecorator(coffee2);
        System.out.println("加牛奶后：" + coffee2.getDescription() + "，价格：" + coffee2.getCost());

        coffee2 = new SugarDecorator(coffee2);
        System.out.println("加糖后：" + coffee2.getDescription() + "，价格：" + coffee2.getCost());

        // ========== 10. 责任链模式 ==========
        System.out.println("\n========== 10. 责任链模式 ==========");

        Approver teamLeader = new TeamLeader();
        Approver manager = new Manager();
        Approver director = new Director();

        teamLeader.setNext(manager);
        manager.setNext(director);

        teamLeader.approve(500);
        teamLeader.approve(5000);
        teamLeader.approve(50000);
        teamLeader.approve(1000000);
    }
}


// ============================================================
// 1. 单例模式（双重检查锁）
// ============================================================
class Singleton {
    private static volatile Singleton instance;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    public void doSomething() {
        System.out.println("  单例模式：执行方法");
    }
}


// ============================================================
// 2. 工厂模式
// ============================================================

// 产品接口
interface Shape1 {
    void draw();
}

// 具体产品
class Circle1 implements Shape1 {
    @Override
    public void draw() {
        System.out.println("  绘制圆形");
    }
}

class Rectangle1 implements Shape1 {
    @Override
    public void draw() {
        System.out.println("  绘制矩形");
    }
}

// 简单工厂
class ShapeFactory1 {
    public static Shape1 createShape(String type) {
        if ("circle".equalsIgnoreCase(type)) {
            return new Circle1();
        } else if ("rectangle".equalsIgnoreCase(type)) {
            return new Rectangle1();
        }
        throw new IllegalArgumentException("未知形状：" + type);
    }
}

// 工厂方法：每个产品对应一个工厂
abstract class ShapeFactoryMethod1 {
    public abstract Shape1 createShape();
}

class CircleFactory1 extends ShapeFactoryMethod1 {
    @Override
    public Shape1 createShape() {
        return new Circle1();
    }
}


// ============================================================
// 3. 建造者模式
// ============================================================
class Computer {
    private String cpu;
    private String memory;
    private String disk;

    private Computer(Builder builder) {
        this.cpu = builder.cpu;
        this.memory = builder.memory;
        this.disk = builder.disk;
    }

    @Override
    public String toString() {
        return "CPU=" + cpu + ", 内存=" + memory + ", 硬盘=" + disk;
    }

    public static class Builder {
        private String cpu;
        private String memory;
        private String disk;

        public Builder cpu(String cpu) {
            this.cpu = cpu;
            return this;
        }

        public Builder memory(String memory) {
            this.memory = memory;
            return this;
        }

        public Builder disk(String disk) {
            this.disk = disk;
            return this;
        }

        public Computer build() {
            return new Computer(this);
        }
    }
}


// ============================================================
// 4. 代理模式
// ============================================================

// 接口
interface UserService1 {
    void save();
}

// 真实实现
class UserServiceImpl implements UserService1 {
    @Override
    public void save() {
        System.out.println("  真实实现：保存用户");
    }
}

// 静态代理
class UserServiceProxy implements UserService1 {
    private UserService1 target = new UserServiceImpl();

    @Override
    public void save() {
        System.out.println("  代理：开启事务");
        target.save();
        System.out.println("  代理：提交事务");
    }
}

// 动态代理（JDK 动态代理）
class DynamicProxyFactory {
    public static <T> T createProxy(T target) {
        return (T) java.lang.reflect.Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                (proxy, method, args) -> {
                    System.out.println("  动态代理：方法执行前");
                    Object result = method.invoke(target, args);
                    System.out.println("  动态代理：方法执行后");
                    return result;
                }
        );
    }
}


// ============================================================
// 5. 策略模式
// ============================================================
interface PaymentStrategy {
    void pay(double amount);
}

class AlipayStrategy implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        System.out.println("  支付宝支付：" + amount + " 元");
    }
}

class WechatPayStrategy implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        System.out.println("  微信支付：" + amount + " 元");
    }
}

class CreditCardStrategy implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        System.out.println("  信用卡支付：" + amount + " 元");
    }
}

class PaymentContext {
    private PaymentStrategy strategy;

    public void setStrategy(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public void pay(double amount) {
        strategy.pay(amount);
    }
}


// ============================================================
// 6. 观察者模式
// ============================================================
interface Observer {
    void update(String message);
}

class NewsObserver implements Observer {
    private String name;

    public NewsObserver(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println("  " + name + " 收到新闻：" + message);
    }
}

class NewsSubject {
    private List<Observer> observers = new ArrayList<>();

    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void publishNews(String news) {
        System.out.println("  发布新闻：" + news);
        for (Observer observer : observers) {
            observer.update(news);
        }
    }
}


// ============================================================
// 7. 模板方法模式
// ============================================================
abstract class Beverage {
    public final void prepare() {
        boilWater();
        brew();
        pourInCup();
        addCondiments();
    }

    abstract void brew();
    abstract void addCondiments();

    private void boilWater() {
        System.out.println("  烧水");
    }

    private void pourInCup() {
        System.out.println("  倒入杯中");
    }
}

class Coffee extends Beverage {
    @Override
    void brew() {
        System.out.println("  冲泡咖啡");
    }

    @Override
    void addCondiments() {
        System.out.println("  加糖和牛奶");
    }
}

class Tea extends Beverage {
    @Override
    void brew() {
        System.out.println("  泡茶");
    }

    @Override
    void addCondiments() {
        System.out.println("  加柠檬");
    }
}


// ============================================================
// 8. 适配器模式
// ============================================================
interface ChineseSpeaker {
    void speakChinese(String message);
}

class EnglishSpeaker {
    public void speakEnglish(String message) {
        System.out.println("  English: " + message);
    }
}

class EnglishToChineseAdapter implements ChineseSpeaker {
    private EnglishSpeaker englishSpeaker;

    public EnglishToChineseAdapter(EnglishSpeaker englishSpeaker) {
        this.englishSpeaker = englishSpeaker;
    }

    @Override
    public void speakChinese(String message) {
        englishSpeaker.speakEnglish("Translated: " + message);
    }
}


// ============================================================
// 9. 装饰器模式
// ============================================================
interface Coffee2 {
    String getDescription();
    double getCost();
}

class SimpleCoffee implements Coffee2 {
    @Override
    public String getDescription() {
        return "简单咖啡";
    }

    @Override
    public double getCost() {
        return 10.0;
    }
}

abstract class CoffeeDecorator implements Coffee2 {
    protected Coffee2 coffee;

    public CoffeeDecorator(Coffee2 coffee) {
        this.coffee = coffee;
    }
}

class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee2 coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + " + 牛奶";
    }

    @Override
    public double getCost() {
        return coffee.getCost() + 3.0;
    }
}

class SugarDecorator extends CoffeeDecorator {
    public SugarDecorator(Coffee2 coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + " + 糖";
    }

    @Override
    public double getCost() {
        return coffee.getCost() + 1.0;
    }
}


// ============================================================
// 10. 责任链模式
// ============================================================
abstract class Approver {
    protected Approver next;
    protected String name;

    public Approver(String name) {
        this.name = name;
    }

    public void setNext(Approver next) {
        this.next = next;
    }

    public abstract void approve(double amount);
}

class TeamLeader extends Approver {
    public TeamLeader() {
        super("组长");
    }

    @Override
    public void approve(double amount) {
        if (amount <= 1000) {
            System.out.println("  " + name + " 审批通过：" + amount + " 元");
        } else if (next != null) {
            System.out.println("  " + name + " 无权审批，转交上级");
            next.approve(amount);
        } else {
            System.out.println("  无人能审批：" + amount + " 元");
        }
    }
}

class Manager extends Approver {
    public Manager() {
        super("经理");
    }

    @Override
    public void approve(double amount) {
        if (amount <= 10000) {
            System.out.println("  " + name + " 审批通过：" + amount + " 元");
        } else if (next != null) {
            System.out.println("  " + name + " 无权审批，转交上级");
            next.approve(amount);
        } else {
            System.out.println("  无人能审批：" + amount + " 元");
        }
    }
}

class Director extends Approver {
    public Director() {
        super("总监");
    }

    @Override
    public void approve(double amount) {
        if (amount <= 100000) {
            System.out.println("  " + name + " 审批通过：" + amount + " 元");
        } else {
            System.out.println("  " + name + " 无权审批：" + amount + " 元");
        }
    }
}