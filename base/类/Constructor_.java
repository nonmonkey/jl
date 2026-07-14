/**
 * 学习目标：理解构造方法的作用
 * 构造方法 = new 对象时自动调用，用来给属性赋初始值
 */
public class Constructor_ {

    public static void main(String[] args) {
        // 使用带参数的构造方法创建对象，new 的时候直接传值
        Person p1 = new Person("张三", 25);
        p1.introduce();

        // 使用无参数的构造方法创建对象，属性为默认值
        Person p2 = new Person();
        p2.introduce();

        // 再创建一个带参数的对象
        Person p3 = new Person("李四", 30);
        p3.introduce();
    }
}

class Person {
    String name;
    int age;

    // 【构造方法 1】无参数构造方法（默认构造方法）
    // 如果没写任何构造方法，系统会自动提供一个无参构造
    // 但一旦写了其他构造方法，建议手动把无参的也写上
    public Person() {
        System.out.println("▶ 调用了无参构造方法");
        this.name = "未命名";
        this.age = 0;
    }

    // 【构造方法 2】带参数的构造方法（重载）
    // 方法名必须和类名完全相同，没有返回值类型（连 void 都没有）
    public Person(String name, int age) {
        System.out.println("▶ 调用了带参构造方法");
        this.name = name;   // this.name 是当前对象的属性
        this.age = age;     // 右边的 name 是传入的参数
    }

    void introduce() {
        System.out.println("大家好，我叫 " + name + "，今年 " + age + " 岁");
    }
}