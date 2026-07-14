import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

/**
 * 学习目标：理解反射（Reflection）
 * 反射 = 运行时获取类的信息 + 动态操作对象
 *
 * 核心类：
 * - Class      → 代表一个类
 * - Constructor → 代表构造方法
 * - Method     → 代表方法
 * - Field      → 代表属性
 *
 * 应用场景：框架（Spring、MyBatis）、注解处理器、动态代理
 */
public class Reflection_ {

    public static void main(String[] args) throws Exception {
        System.out.println("========== 1. 获取 Class 对象的三种方式 ==========");
        // 方式1：类名.class
        Class<?> clazz1 = Student2.class;
        System.out.println("方式1：Student2.class → " + clazz1.getName());

        // 方式2：对象.getClass()
        Student2 stu = new Student2("张三", 20, "计算机");
        Class<?> clazz2 = stu.getClass();
        System.out.println("方式2：对象.getClass() → " + clazz2.getName());

        // 方式3：Class.forName("全类名")（最常用，动态加载）
        Class<?> clazz3 = Class.forName("Student2");
        System.out.println("方式3：Class.forName() → " + clazz3.getName());

        // 三个引用指向同一个 Class 对象（同一个类只有一个 Class 对象）
        System.out.println("clazz1 == clazz2 ? " + (clazz1 == clazz2));
        System.out.println("clazz1 == clazz3 ? " + (clazz1 == clazz3));

        System.out.println("\n========== 2. 获取类的信息 ==========");
        Class<?> clazz = Student2.class;

        // 获取类名
        System.out.println("类名：" + clazz.getName());
        System.out.println("简单类名：" + clazz.getSimpleName());
        System.out.println("包名：" + clazz.getPackage().getName());

        // 获取修饰符
        int modifiers = clazz.getModifiers();
        System.out.println("是否是 public：" + Modifier.isPublic(modifiers));
        System.out.println("是否是 abstract：" + Modifier.isAbstract(modifiers));
        System.out.println("是否是 final：" + Modifier.isFinal(modifiers));

        // 获取父类
        System.out.println("父类：" + clazz.getSuperclass().getSimpleName());

        // 获取实现的接口
        System.out.println("实现的接口：" + Arrays.toString(clazz.getInterfaces()));

        System.out.println("\n========== 3. 获取构造方法 ==========");
        System.out.println("--- 所有构造方法 ---");
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        for (Constructor<?> c : constructors) {
            System.out.println("  " + c);
        }

        // 获取特定构造方法并创建对象
        System.out.println("\n--- 使用构造方法创建对象 ---");
        // 无参构造
        Constructor<?> noArgConstructor = clazz.getDeclaredConstructor();
        Student2 stu1 = (Student2) noArgConstructor.newInstance();
        System.out.println("无参构造创建：" + stu1);

        // 带参构造
        Constructor<?> argConstructor = clazz.getDeclaredConstructor(String.class, int.class, String.class);
        Student2 stu2 = (Student2) argConstructor.newInstance("李四", 22, "数学");
        System.out.println("带参构造创建：" + stu2);

        System.out.println("\n========== 4. 获取方法 ==========");
        System.out.println("--- 所有方法（包括私有） ---");
        Method[] methods = clazz.getDeclaredMethods();
        for (Method m : methods) {
            System.out.println("  " + m.getName() + "() 返回类型：" + m.getReturnType().getSimpleName());
        }

        System.out.println("\n--- 调用方法 ---");
        // 获取特定方法
        Method setNameMethod = clazz.getDeclaredMethod("setName", String.class);
        Method getNameMethod = clazz.getDeclaredMethod("getName");
        Method studyMethod = clazz.getDeclaredMethod("study");
        Method privateMethod = clazz.getDeclaredMethod("privateMethod");

        // 创建对象
        Student2 stu3 = new Student2("王五", 23, "物理");

        // 调用方法
        System.out.println("原始名字：" + getNameMethod.invoke(stu3));
        setNameMethod.invoke(stu3, "赵六");
        System.out.println("修改后名字：" + getNameMethod.invoke(stu3));

        // 调用无参方法
        studyMethod.invoke(stu3);

        // 调用私有方法：需要 setAccessible(true) 暴力破解
        System.out.println("\n--- 调用私有方法 ---");
        // 私有方法需要设置可访问
        privateMethod.setAccessible(true);  // 暴力反射！
        privateMethod.invoke(stu3);

        System.out.println("\n========== 5. 获取属性 ==========");
        System.out.println("--- 所有属性（包括私有） ---");
        Field[] fields = clazz.getDeclaredFields();
        for (Field f : fields) {
            System.out.println("  " + f.getName() + " 类型：" + f.getType().getSimpleName() + "，修饰符：" + Modifier.toString(f.getModifiers()));
        }

        System.out.println("\n--- 修改属性（包括私有） ---");
        Student2 stu4 = new Student2("小明", 18, "英语");

        // 获取私有属性 name
        Field nameField = clazz.getDeclaredField("name");
        nameField.setAccessible(true);  // 暴力破解
        System.out.println("修改前 name = " + nameField.get(stu4));
        nameField.set(stu4, "小红");
        System.out.println("修改后 name = " + nameField.get(stu4));

        // 获取私有属性 age
        Field ageField = clazz.getDeclaredField("age");
        ageField.setAccessible(true);
        System.out.println("修改前 age = " + ageField.get(stu4));
        ageField.set(stu4, 20);
        System.out.println("修改后 age = " + ageField.get(stu4));

        System.out.println("\n========== 6. 反射性能对比 ==========");
        // 直接调用 vs 反射调用
        Student2 stu5 = new Student2("测试", 18, "测试");

        // 直接调用
        long start1 = System.nanoTime();
        for (int i = 0; i < 10; i++) {
            stu5.study();
        }
        long end1 = System.nanoTime();
        System.out.println("直接调用 10 次：" + (end1 - start1) / 10 + " ms");

        // 反射调用（不关闭安全检查）
        Method studyMethod2 = clazz.getDeclaredMethod("study");
        long start2 = System.nanoTime();
        for (int i = 0; i < 10; i++) {
            studyMethod2.invoke(stu5);
        }
        long end2 = System.nanoTime();
        System.out.println("反射调用 10 次：" + (end2 - start2) / 10 + " ms");

        // 反射调用（关闭安全检查）
        studyMethod2.setAccessible(true);
        long start3 = System.nanoTime();
        for (int i = 0; i < 10; i++) {
            studyMethod2.invoke(stu5);
        }
        long end3 = System.nanoTime();
        System.out.println("反射调用（setAccessible）10 次：" + (end3 - start3) / 10 + " ms");

        System.out.println("\n========== 7. 反射获取泛型信息 ==========");
        // 反射可以获取泛型类型（比较复杂，这里简单演示）
        Class<?> listClass = java.util.ArrayList.class;
        Method addMethod = listClass.getDeclaredMethod("add", Object.class);
        System.out.println("ArrayList.add() 参数类型：" + addMethod.getParameterTypes()[0].getSimpleName());

        System.out.println("\n========== 8. 反射创建数组 ==========");
        // 使用反射创建数组
        Object array = java.lang.reflect.Array.newInstance(String.class, 3);
        java.lang.reflect.Array.set(array, 0, "元素1");
        java.lang.reflect.Array.set(array, 1, "元素2");
        java.lang.reflect.Array.set(array, 2, "元素3");
        System.out.println("数组长度：" + java.lang.reflect.Array.getLength(array));
        System.out.println("数组内容：" + Arrays.toString((String[]) array));
    }
}

// ==================== 被反射操作的类 ====================
// 这个类实现了 Serializable base.接口，为了方便演示
class Student2 implements java.io.Serializable {
    private String name;
    private int age;
    private String major;

    // 无参构造
    public Student2() {
        this.name = "默认";
        this.age = 0;
        this.major = "未定";
        System.out.println("▶ 调用了无参构造");
    }

    // 带参构造
    public Student2(String name, int age, String major) {
        this.name = name;
        this.age = age;
        this.major = major;
        System.out.println("▶ 调用了带参构造：" + name);
    }

    // 私有构造方法（演示能获取到）
    private Student2(String name) {
        this.name = name;
        this.age = 18;
        this.major = "默认";
        System.out.println("▶ 调用了私有构造方法：" + name);
    }

    // getter / setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    // 公共方法
    public void study() {
        System.out.println(name + " 正在学习 " + major);
    }

    // 私有方法
    private void privateMethod() {
        System.out.println("🔒 这是私有方法，反射可以调用它！");
    }

    // 重写 toString
    @Override
    public String toString() {
        return "Student2{name='" + name + "', age=" + age + ", major='" + major + "'}";
    }
}