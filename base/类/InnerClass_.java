/**
 * 学习目标：理解内部类（Inner Class）
 * 内部类 = 定义在类内部的类，可以访问外部类的所有成员
 * 分类：成员内部类、静态内部类、局部内部类、匿名内部类（第7个模块单独讲）
 */
public class InnerClass_ {

    public static void main(String[] args) {
        System.out.println("========== 1. 成员内部类 ==========");
        // 创建外部类对象
        Outer outer = new Outer("苹果公司");
        // 创建成员内部类对象：先有外部类对象，才能有内部类对象
        Outer.Engine engine = outer.new Engine("M1芯片");
        engine.work();

        System.out.println("\n========== 2. 静态内部类 ==========");
        // 创建静态内部类对象：不需要外部类对象
        Outer.StaticEngine staticEngine = new Outer.StaticEngine("Intel芯片");
        staticEngine.work();

        System.out.println("\n========== 3. 局部内部类 ==========");
        outer.testLocalInnerClass();

        System.out.println("\n========== 4. 在方法内部使用局部内部类 ==========");
        outer.testInnerClassInMethod();
    }
}

// ==================== 外部类 ====================
class Outer {
    private String companyName;
    private static String TYPE = "电子产品";

    public Outer(String companyName) {
        this.companyName = companyName;
    }

    // 外部类的方法
    public void showInfo() {
        System.out.println("公司：" + companyName);
    }

    // ============================================
    // 1. 成员内部类（非静态内部类）
    // 特点：可以访问外部类的所有成员（包括 private）
    // 依赖：必须通过外部类对象才能创建
    // ============================================
    class Engine {
        private String chipName;

        public Engine(String chipName) {
            this.chipName = chipName;
        }

        public void work() {
            // 内部类可以直接访问外部类的 private 成员
            System.out.println("公司：" + companyName + "，正在使用 " + chipName + " 工作");
            System.out.println("产品类型：" + TYPE);  // 也能访问静态成员
            // 调用外部类的方法
            showInfo();
        }

        // 内部类可以访问外部类的成员，即使同名也能区分
        public void testThis() {
            // Outer.this 指向外部类对象
            System.out.println("外部类公司名：" + Outer.this.companyName);
            // this 指向内部类对象
            System.out.println("内部类芯片名：" + this.chipName);
        }
    }

    // ============================================
    // 2. 静态内部类（嵌套类）
    // 特点：用 static 修饰，不依赖外部类对象
    // 限制：只能访问外部类的静态成员
    // ============================================
    static class StaticEngine {
        private String chipName;

        public StaticEngine(String chipName) {
            this.chipName = chipName;
        }

        public void work() {
            // ⚠️ 静态内部类不能访问外部类的非静态成员
            // System.out.println(companyName);  // 编译报错！
            System.out.println("静态内部类：使用 " + chipName + "，产品类型：" + TYPE);
        }
    }

    // ============================================
    // 3. 局部内部类（方法内部类）
    // 特点：定义在方法内部，作用域仅限于该方法
    // 限制：只能在该方法中使用，外部完全不可见
    // ============================================
    public void testLocalInnerClass() {
        // 局部内部类
        class LocalHelper {
            private String message;

            public LocalHelper(String message) {
                this.message = message;
            }

            public void print() {
                System.out.println("局部内部类：" + message + "，公司：" + companyName);
            }
        }

        // 只能在方法内部使用这个类
        LocalHelper helper = new LocalHelper("测试数据");
        helper.print();

        // 可以多次创建
        LocalHelper helper2 = new LocalHelper("另一个数据");
        helper2.print();
    }

    // ============================================
    // 4. 在方法内部使用局部内部类（更常见的场景）
    // ============================================
    public void testInnerClassInMethod() {
        // 局部内部类通常用于临时封装一些逻辑
        class DataProcessor {
            private String data;

            public DataProcessor(String data) {
                this.data = data;
            }

            public String process() {
                return "处理后的数据：" + data.toUpperCase();
            }
        }

        DataProcessor processor = new DataProcessor("hello java");
        String result = processor.process();
        System.out.println(result);
    }

    // ============================================
    // 5. 内部类可以访问外部类的 private 方法
    // ============================================
    private void privateMethod() {
        System.out.println("这是外部类的 private 方法");
    }

    class AnotherInnerClass {
        public void callPrivateMethod() {
            // 内部类可以调用外部类的 private 方法
            privateMethod();
        }
    }

    // ============================================
    // 6. 外部类可以访问内部类的 private 成员吗？
    //    可以！外部类能访问内部类的一切
    // ============================================
    public void accessInnerClassPrivate() {
        Engine e = new Engine("测试芯片");
        // 外部类可以访问内部类的 private 成员
        // System.out.println(e.chipName);  // 编译报错！❌ 实测说明：外部类不能直接访问内部类的 private 成员
        // 正确说法：外部类可以通过内部类的公共方法访问
        System.out.println("通过方法访问内部类私有数据");
        e.work();  // 内部类自己显示数据
    }
}

// ============================================
// 7. 内部类可以在外部被使用吗？
//    可以！但需要完整的类名：外部类.内部类
// ============================================
class UseInnerClassOutside {
    public void use() {
        // 创建外部类对象
        Outer outer = new Outer("外部公司");

        // 创建成员内部类对象
        Outer.Engine engine = outer.new Engine("外部芯片");
        engine.work();

        // 创建静态内部类对象
        Outer.StaticEngine staticEngine = new Outer.StaticEngine("外部静态芯片");
        staticEngine.work();
    }
}