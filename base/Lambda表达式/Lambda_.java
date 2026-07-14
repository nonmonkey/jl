import java.util.*;

/**
 * 简化代码	取代匿名内部类的冗长写法
 * 函数式编程	把行为（逻辑）当作数据传递
 * 集合操作	配合 Stream API 进行链式处理
 * 延迟执行	定义代码但不立即执行，由框架调用
 */
public class Lambda_ {
    public static void main(String[] args) {
        System.out.println("========== 1. 线程 (Runnable) ==========");
        // 匿名内部类 (老写法)
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("匿名内部类线程");
            }
        }).start();

        // Lambda (新写法)
        new Thread(() -> System.out.println("Lambda 线程")).start();

        System.out.println("\n========== 2. 排序 (Comparator) ==========");
        List<String> list = new ArrayList<>(Arrays.asList("Banana", "Apple", "Pear"));

        // 匿名内部类
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.length() - s2.length();
            }
        });
        System.out.println("按长度排序(匿名类): " + list);

        // Lambda (新写法)
        List<String> list2 = new ArrayList<>(Arrays.asList("Banana", "Apple", "Pear"));
        Collections.sort(list2, (s1, s2) -> s1.length() - s2.length());
        System.out.println("按长度排序(Lambda): " + list2);

        /**
         * 更简洁的 Lambda （方法引用 Method Reference）
         * String::length     → "字符串的 length 方法"
         * System.out::println → "System.out 的 println 方法"
         * Person::getName    → "Person 的 getName 方法"
         * Person::new        → "Person 的构造方法"
         * Math::abs          → "Math 的 abs 静态方法"
         */
        List<String> list3 = new ArrayList<>(Arrays.asList("Banana", "Apple", "Pear"));
        Collections.sort(list3, Comparator.comparingInt(String::length));
        System.out.println("方法引用排序: " + list3);
    }
}