/**
 * 学习目标：理解多态（Polymorphism）
 * 多态 = 父类引用指向子类对象
 * 编译看左边（类型），运行看右边（实际对象）
 *
 * 三个必要条件：
 * 1. 有继承关系
 * 2. 有方法重写
 * 3. 父类引用指向子类对象
 */
public class Polymorphism_ {

    public static void main(String[] args) {
        System.out.println("========== 1. 基本多态演示 ==========");
        // 普通方式：子类引用指向子类对象
        Dog3 dog = new Dog3("旺财");
        Cat3 cat = new Cat3("咪咪");
        dog.sound();
        cat.sound();
        System.out.println();

        // 多态方式：父类引用指向子类对象
        Animal3 animal1 = new Dog3("大黄");   // 爸爸引用指向儿子对象
        Animal3 animal2 = new Cat3("小花");   // 爸爸引用指向儿子对象
        animal1.sound();  // 实际执行 Dog 的 sound()
        animal2.sound();  // 实际执行 Cat 的 sound()
        System.out.println();

        System.out.println("========== 2. 多态的优势：统一处理 ==========");
        // 多态最牛的地方：可以用一个数组/集合存放不同类型的对象
        Animal3[] animals = {
                new Dog3("旺财"),
                new Cat3("咪咪"),
                new Dog3("大黄"),
                new Cat3("小花"),
                new Dog3("小黑")
        };

        // 循环调用，不用关心具体是什么类型
        for (Animal3 animal : animals) {
            animal.sound();  // 每个动物发出自己的声音
        }
        System.out.println();

        System.out.println("========== 3. 多态作为方法参数 ==========");
        // 方法可以接收父类类型，传入任何子类对象
        makeSound(new Dog3("阿黄"));
        makeSound(new Cat3("阿花"));
        makeSound(new Animal3("未知动物") {
            @Override
            public void sound() {
                System.out.println("🤖 我是未知生物，发出奇怪的声音");
            }
        });
        System.out.println();

        System.out.println("========== 4. 多态作为方法返回值 ==========");
        Animal3 a1 = getAnimal("dog");
        Animal3 a2 = getAnimal("cat");
        a1.sound();
        a2.sound();
        System.out.println();

        System.out.println("========== 5. 多态的局限性 ==========");
        Animal3 a = new Dog3("小黑");
        a.sound();        // ✅ 父类有这个方法
        // a.bark();      // ❌ 编译报错！父类没有 bark() 方法
        // a.fetch();     // ❌ 编译报错！父类没有 fetch() 方法

        // 想调用子类特有的方法，需要向下转型（强制类型转换）
        if (a instanceof Dog3) {
            Dog3 dog2 = (Dog3) a;  // 向下转型
            dog2.bark();         // ✅ 现在可以调用了
            dog2.fetch();
        }

        System.out.println("\n========== 6. 接口的多态 ==========");
        // 接口引用指向实现类对象，也是多态
        Flyable3 f1 = new Bird3("老鹰");
        Flyable3 f2 = new Plane3("波音747");
        f1.fly();
        f2.fly();
        System.out.println();

        System.out.println("========== 7. 多态和构造方法 ==========");
        // 创建子类对象时，先调用父类构造方法，再调用子类构造方法
        new Dog3("小黄");
        System.out.println();

        System.out.println("========== 8. 多态中的属性 ==========");
        // ⚠️ 重要：属性没有多态！属性看编译类型（左边）
        Animal3 animal = new Dog3("测试");
        System.out.println("animal.type = " + animal.type);    // 输出 "动物"
        System.out.println("animal.age = " + animal.age);      // 输出 0
        // 属性访问看左边（Animal），不看右边（Dog）

        Dog3 dogRef = (Dog3) animal;
        System.out.println("dogRef.type = " + dogRef.type);    // 输出 "狗"
        System.out.println("dogRef.age = " + dogRef.age);      // 输出 3
        System.out.println();

        System.out.println("========== 9. 多态中的静态方法 ==========");
        // ⚠️ 重要：静态方法没有多态！看左边（编译类型）
        Animal3.staticMethod();   // 输出 "Animal 静态方法"
        Dog3.staticMethod();      // 输出 "Dog 静态方法"

        Animal3 animalStatic = new Dog3("任意");
        animalStatic.staticMethod();  // 输出 "Animal 静态方法"（看左边！）
        // 虽然实际对象是 Dog，但静态方法属于类，不属于对象
    }

    // ============ 多态作为方法参数 ============
    public static void makeSound(Animal3 animal) {
        System.out.print("makeSound 收到一个动物：");
        animal.sound();
    }

    // ============ 多态作为方法返回值 ============
    public static Animal3 getAnimal(String type) {
        if ("dog".equals(type)) {
            return new Dog3("工厂生产的狗");
        } else if ("cat".equals(type)) {
            return new Cat3("工厂生产的猫");
        }
        return new Animal3("未知") {
            @Override
            public void sound() {
                System.out.println("🤷 不知道是什么");
            }
        };
    }
}

// ==================== 父类 ====================
class Animal3 {
    String name;
    String type = "动物";  // 演示属性没有多态
    int age = 0;

    public Animal3(String name) {
        this.name = name;
        System.out.println("▶ Animal 构造方法：" + name);
    }

    public void sound() {
        System.out.println(name + " 发出动物的声音");
    }

    public static void staticMethod() {
        System.out.println("Animal 静态方法");
    }
}

// ==================== 子类 Dog ====================
class Dog3 extends Animal3 {
    String type = "狗";   // 和父类同名的属性，演示属性没有多态
    int age = 3;

    public Dog3(String name) {
        super(name);
        System.out.println("▶ Dog 构造方法：" + name);
    }

    @Override
    public void sound() {
        System.out.println(name + " 🐶 汪汪汪！");
    }

    // 子类特有的方法
    public void bark() {
        System.out.println(name + " 汪汪叫！");
    }

    public void fetch() {
        System.out.println(name + " 叼回飞盘 🥏");
    }

    public static void staticMethod() {
        System.out.println("Dog 静态方法");
    }
}

// ==================== 子类 Cat ====================
class Cat3 extends Animal3 {
    String type = "猫";
    int age = 2;

    public Cat3(String name) {
        super(name);
        System.out.println("▶ Cat 构造方法：" + name);
    }

    @Override
    public void sound() {
        System.out.println(name + " 🐱 喵喵喵～");
    }

    // 子类特有的方法
    public void catchMouse() {
        System.out.println(name + " 抓老鼠 🐭");
    }

    public static void staticMethod() {
        System.out.println("Cat 静态方法");
    }
}

// ==================== 接口的多态 ====================
interface Flyable3 {
    void fly();
}

class Bird3 implements Flyable3 {
    private String name;

    public Bird3(String name) {
        this.name = name;
    }

    @Override
    public void fly() {
        System.out.println(name + " 🦅 展翅飞翔");
    }
}

class Plane3 implements Flyable3 {
    private String name;

    public Plane3(String name) {
        this.name = name;
    }

    @Override
    public void fly() {
        System.out.println(name + " ✈️ 喷气式飞行");
    }
}