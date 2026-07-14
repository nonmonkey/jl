public class Class_ {

    public static void main(String[] args) {
        /**
         * 方式1：使用构造方法创建对象
         */
        Student stu1 = new Student("小明", 18);
        stu1.study();

        System.out.println("\n========== 测试访问权限 ==========");

        // ✅ 可以访问 public 属性
        System.out.println("public 属性（课程）：" + stu1.course);

        // ✅ 可以访问 protected 属性（同包）
        System.out.println("protected 属性（学号）：" + stu1.studentId);

        // ✅ 可以访问 default 属性（同包）
        System.out.println("default 属性（年级）：" + stu1.grade);

        // ❌ 不能访问 private 属性（编译错误）
        // System.out.println(stu1.name);  // 编译错误
    }
}

/**
 * 学生类 —— 演示 4 种访问修饰符
 */
class Student {
    // ============ 4 种访问修饰符的属性 ============

    // 1. private：只能在本类内部访问
    private String name = "默认姓名";

    // 2. default（无修饰符）：同包内可访问
    String grade = "大一";

    // 3. protected：同包 + 子类可访问
    protected String studentId = "2024001";

    // 4. public：任何地方都能访问
    public String course = "Java编程";

    // ============ 构造方法 ============
    public Student(String name, int age) {
        this.name = name;
        System.out.println("创建学生：" + name);
    }

    // 无参构造
    public Student() {
        System.out.println("创建了默认学生");
    }

    // ============ 业务方法 ============
    public void study() {
        // 本类内部：所有属性都能访问
        System.out.println("\n【本类内部访问】");
        System.out.println("  private name：" + name);      // ✅ 可以
        System.out.println("  default grade：" + grade);    // ✅ 可以
        System.out.println("  protected studentId：" + studentId); // ✅ 可以
        System.out.println("  public course：" + course);   // ✅ 可以
    }

    // ============ getter 方法（供外部访问 private） ============
    public String getName() {
        return name;
    }
}

/**
 * 子类 —— 演示 protected 和 default 的区别
 */
class GraduateStudent extends Student {

    public GraduateStudent(String name) {
        super(name, 22);
    }

    public void testAccess() {
        System.out.println("\n【子类内部访问】");

        // ✅ protected：子类可以访问（同包 + 不同包都可以）
        System.out.println("  protected studentId：" + studentId);

        // ✅ default：子类可以访问（因为同包）
        System.out.println("  default grade：" + grade);

        // ✅ public：子类可以访问
        System.out.println("  public course：" + course);

        // ❌ private：子类不能访问
//         System.out.println(name);  // 编译错误！
    }
}