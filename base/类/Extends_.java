/**
 * 学习目标：理解 extends（继承）的作用
 * 继承 = 子类继承父类的属性和方法，实现代码复用
 * 关键词：extends
 */
public class Extends_ {

    public static void main(String[] args) {
        // 创建子类 Dog 的对象
        Dog dog = new Dog();
        dog.name = "旺财";
        dog.age = 3;
        dog.eat();      // 来自父类 Animal 的方法（继承来的）
        dog.sleep();    // 来自父类 Animal 的方法（继承来的）
        dog.bark();     // 子类自己的方法（扩展的）
        System.out.println();

        // 创建子类 Cat 的对象
        Cat cat = new Cat();
        cat.name = "咪咪";
        cat.age = 2;
        cat.eat();      // 来自父类
        cat.sleep();    // 来自父类
        cat.catchMouse(); // 子类自己的方法
        System.out.println();

        // 演示：父类引用指向子类对象（多态的雏形，第9个模块会细讲）
        Animal animal = new Dog();
        animal.eat();   // 实际执行的是 Dog 重写后的 eat()
    }
}

// ============ 父类（基类/超类） ============
class Animal {
    String name;
    int age;

    void eat() {
        System.out.println(name + " 正在吃东西");
    }

    void sleep() {
        System.out.println(name + " 正在睡觉");
    }
}

// ============ 子类 1：Dog 继承 Animal ============
// 使用 extends 关键字，Dog 自动拥有了 Animal 的所有属性和方法
class Dog extends Animal {
    // 子类可以扩展自己的新方法
    void bark() {
        System.out.println(name + " 汪汪叫！");
    }

    // 子类可以重写（覆盖）父类的方法
    // @Override 是注解，表示"我重写了父类方法"，帮助编译器检查
    @Override
    void eat() {
        System.out.println(name + " 正在啃骨头 🦴");
    }
}

// ============ 子类 2：Cat 继承 Animal ============
class Cat extends Animal {
    void catchMouse() {
        System.out.println(name + " 正在抓老鼠 🐭");
    }

    @Override
    void eat() {
        System.out.println(name + " 正在吃鱼 🐟");
    }
}