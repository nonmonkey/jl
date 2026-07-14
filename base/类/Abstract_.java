/**
 * 学习目标：理解 abstract（抽象类）的作用
 * 抽象类 = 用来被继承的"半成品"，不能 new，但可以包含抽象方法
 * 关键词：abstract（修饰类和方法）
 */
public class Abstract_ {

    public static void main(String[] args) {
        // ❌ 错误：抽象类不能实例化（不能 new）
        // Shape shape = new Shape();  // 编译报错！

        // ✅ 正确：用子类创建对象
        Circle circle = new Circle(5.0);
        circle.setColor("红色");
        circle.draw();          // 子类实现了抽象方法
        circle.showColor();     // 来自抽象类的具体方法（继承的）
        System.out.println("圆的面积：" + circle.calculateArea());

        System.out.println();

        Rectangle rect = new Rectangle(4.0, 6.0);
        rect.setColor("蓝色");
        rect.draw();
        rect.showColor();
        System.out.println("矩形的面积：" + rect.calculateArea());

        System.out.println();

        // 多态：父类引用指向子类对象
        Shape shape1 = new Circle(3.0);
        Shape shape2 = new Rectangle(5.0, 7.0);
        shape1.draw();  // 实际执行的是 Circle 的 draw()
        shape2.draw();  // 实际执行的是 Rectangle 的 draw()
    }
}

// ============ 抽象类 ============
// 使用 abstract 关键字定义抽象类
// 抽象类不能实例化（不能 new），专门用来被继承
abstract class Shape {
    // 抽象类可以有普通属性
    protected String color;
    protected String name;

    // 抽象类可以有构造方法（给子类调用）
    public Shape() {
        this.name = "图形";
        System.out.println("▶ 调用了 Shape 的构造方法");
    }

    // 抽象类可以有具体方法（有实现体）
    public void setColor(String color) {
        this.color = color;
        System.out.println("设置颜色为：" + color);
    }

    public void showColor() {
        System.out.println("这个 " + name + " 的颜色是 " + color);
    }

    // ============ 抽象方法 ============
    // 使用 abstract 关键字定义抽象方法
    // 只有方法声明，没有方法体（没有 {}）
    // 抽象方法必须在子类中被实现（重写）
    public abstract void draw();

    // 抽象方法可以有参数，但同样没有实现体
    public abstract double calculateArea();

    // 抽象类也可以有静态方法
    public static void showInfo() {
        System.out.println("Shape 是一个抽象类，代表所有图形");
    }
}

// ============ 子类 1：Circle 继承抽象类 ============
// 子类必须实现父类的所有抽象方法，否则子类也必须声明为 abstract
class Circle extends Shape {
    private double radius;

    public Circle(double radius) {
        super();  // 调用父类的构造方法
        this.radius = radius;
        this.name = "圆";
    }

    // 必须实现抽象方法 draw()
    @Override
    public void draw() {
        System.out.println("⚪ 画一个 " + color + " 的圆形，半径：" + radius);
    }

    // 必须实现抽象方法 calculateArea()
    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    // 子类可以扩展自己的方法
    public double getCircumference() {
        return 2 * Math.PI * radius;
    }
}

// ============ 子类 2：Rectangle 继承抽象类 ============
class Rectangle extends Shape {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        super();
        this.width = width;
        this.height = height;
        this.name = "矩形";
    }

    @Override
    public void draw() {
        System.out.println("▭ 画一个 " + color + " 的矩形，宽：" + width + "，高：" + height);
    }

    @Override
    public double calculateArea() {
        return width * height;
    }

    // 子类自己的方法
    public double getPerimeter() {
        return 2 * (width + height);
    }
}

// ============ 抽象类可以继承抽象类 ============
// 子抽象类可以不实现父类的抽象方法，留给"孙类"去实现
abstract class ThreeDimensionalShape extends Shape {
    // 不实现 draw() 和 calculateArea()
    // 但新增了自己的抽象方法
    public abstract double calculateVolume();  // 计算体积
}

// 最终子类必须实现所有未实现的抽象方法
class Sphere extends ThreeDimensionalShape {
    private double radius;

    public Sphere(double radius) {
        super();
        this.radius = radius;
        this.name = "球体";
    }

    @Override
    public void draw() {
        System.out.println("🌐 画一个 " + color + " 的球体，半径：" + radius);
    }

    @Override
    public double calculateArea() {
        return 4 * Math.PI * radius * radius;  // 表面积
    }

    @Override
    public double calculateVolume() {
        return (4.0 / 3.0) * Math.PI * radius * radius * radius;  // 体积
    }
}