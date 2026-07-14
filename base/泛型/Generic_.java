import java.util.*;

/**
 * 学习目标：理解泛型（Generic）
 * 泛型 = 把类型作为参数，让代码更安全、更通用
 *
 * 三种使用方式：
 * 1. 泛型类：class 类名<T> { ... }
 * 2. 泛型方法：public <T> T 方法名(T t) { ... }
 * 3. 泛型接口：interface 接口名<T> { ... }
 *
 * 通配符：
 * 4. ? extends T：上界通配符（T 或 T 的子类）→ 用于读取
 * 5. ? super T：下界通配符（T 或 T 的父类）→ 用于写入
 */
public class Generic_ {

    public static void main(String[] args) {
        System.out.println("========== 1. 泛型类 ==========");
        // 使用泛型类时，指定具体类型
        Box<String> stringBox = new Box<>("Hello 泛型");
        Box<Integer> intBox = new Box<>(100);

        System.out.println("stringBox 的值：" + stringBox.getValue());
        System.out.println("intBox 的值：" + intBox.getValue());
        System.out.println("stringBox 的类型：" + stringBox.getType());
        System.out.println("intBox 的类型：" + intBox.getType());

        // Box<String> wrongBox = new Box<>(123); // ❌ 编译报错！类型不匹配

        System.out.println("\n========== 2. 泛型方法 ==========");
        GenericMethods gm = new GenericMethods();

        // 调用泛型方法，类型自动推断
        String result1 = gm.printAndReturn("Hello World");
        Integer result2 = gm.printAndReturn(123);
        Double result3 = gm.printAndReturn(3.14);

        System.out.println("返回值：" + result1);
        System.out.println("返回值：" + result2);
        System.out.println("返回值：" + result3);

        // 泛型方法 + 多参数
        String[] arr = {"A", "B", "C"};
        String first = gm.getFirst(arr);
        System.out.println("数组第一个元素：" + first);

        System.out.println("\n========== 3. 泛型接口 ==========");
        // 方式1：实现类指定具体类型
        Pair<String, Integer> pair1 = new KeyValuePair("年龄", 25);
        System.out.println(pair1.getKey() + " = " + pair1.getValue());

        // 方式2：实现类保留泛型
        MyPair<String, Double> pair2 = new MyPair<>("价格", 99.99);
        System.out.println(pair2.getKey() + " = " + pair2.getValue());

        System.out.println("\n========== 4. 集合中的泛型 ==========");
        // ArrayList
        List<String> names = new ArrayList<>();
        names.add("张三");
        names.add("李四");
        // names.add(123);  // ❌ 编译报错

        for (String name : names) {
            System.out.println("名字：" + name);  // 不需要转型
        }

        // HashMap
        Map<String, Integer> scores = new HashMap<>();
        scores.put("张三", 95);
        scores.put("李四", 88);

        for (Map.Entry<String, Integer> entry : scores.entrySet()) {
            System.out.println(entry.getKey() + " 考了 " + entry.getValue() + " 分");
        }

        /**
         * 5. 有界泛型（Bounded Type Parameters）
         * 有界泛型中的 extends 关键字用于指定上界（Upper Bound）。它限制了类型参数必须是某个类型或其子类型。
         */
        System.out.println("\n========== 6. 有界泛型（Bounded Type Parameters） ==========");
        // 只接受 Number 及其子类（Integer, Double, Float...）
        NumberBox<Integer> numBox1 = new NumberBox<>(100);
        NumberBox<Double> numBox2 = new NumberBox<>(99.99);
        // NumberBox<String> numBox3 = new NumberBox<>("hello"); // ❌ 编译报错！

        System.out.println("numBox1 的值：" + numBox1.getValue());
        System.out.println("numBox2 的值：" + numBox2.getValue());
        System.out.println("numBox1 的 double 值：" + numBox1.doubleValue());
        System.out.println("numBox2 的 double 值：" + numBox2.doubleValue());

        /**
         * 6. 无界通配符（Unbounded Wildcard）
         */
        System.out.println("\n========== 5. 无界通配符（Unbounded Wildcard） ==========");
        List<Integer> intList = Arrays.asList(1, 2, 3);
        List<Double> doubleList = Arrays.asList(1.1, 2.2, 3.3);
        List<String> strList = Arrays.asList("A", "B", "C");

        System.out.print("intList 的元素：");
        printList(intList);
        System.out.print("doubleList 的元素：");
        printList(doubleList);
        System.out.print("strList 的元素：");
        printList(strList);

        /**
         * 7. 上界通配符（Upper Bounded Wildcard）
         * - ? extends T 用在“使用泛型”的地方（比如方法参数、变量声明），而不是“声明泛型”的地方。
         */
        System.out.println("\n========== 7. 上界通配符（Upper Bounded Wildcard） ==========");

        List<Dog4> dogs = Arrays.asList(new Dog4("旺财"), new Dog4("小黑"), new Puppy("小黄"));
        List<Puppy> puppies = Arrays.asList(new Puppy("小黄"), new Puppy("小白"));
        List<Animal4> animals = Arrays.asList(new Animal4("动物"), new Dog4("大黄"));

        System.out.println("--- 读取元素（安全） ---");
        // ? extends Animal4：可以接收 Animal4 及其子类的 List
        printAnimals(dogs);      // ✅ List<Dog4>
        printAnimals(puppies);   // ✅ List<Puppy>
        printAnimals(animals);   // ✅ List<Animal4>

        System.out.println("--- 不能添加元素（除了 null） ---");
        // 尝试添加会编译报错：
        // List<? extends Animal4> extendsList = new ArrayList<Dog4>();
        // extendsList.add(new Dog4("测试")); // ❌ 编译错误！
        // extendsList.add(new Animal4("测试")); // ❌ 编译错误！
        // extendsList.add(null); // ✅ 只能添加 null

        /**
         * 8. 下界通配符（Lower Bounded Wildcard）
         * 它和上界通配符正好相反：
         * - ? extends T → 限制为 T 的子类（上限）
         * - ? super T → 限制为 T 的父类（下限）
         */
        System.out.println("\n========== 8. 下界通配符（Lower Bounded Wildcard） ==========");

        List<Animal4> animalList = new ArrayList<>();
        List<Dog4> dogList = new ArrayList<>();
        // List<Object> objectList = new ArrayList<>();

        System.out.println("--- 添加元素（安全） ---");
        // ? super Dog4：可以接收 Dog4 及其父类的 List
        addDogs(animalList);   // ✅ List<Animal4>（Dog4 的父类）
        addDogs(dogList);      // ✅ List<Dog4>（Dog4 本身）
        // addDogs(puppies);   // ❌ List<Puppy>（Puppy 是 Dog4 的子类，不是父类）

        System.out.println("animalList 中的元素：" + animalList);
        System.out.println("dogList 中的元素：" + dogList);

        System.out.println("--- 读取元素（只能读到 Object） ---");
        // ? super Dog4 读取时只能读到 Object
        List<? super Dog4> superList = new ArrayList<Animal4>();
        superList.add(new Dog4("小黑"));
        superList.add(new Puppy("小黄"));  // Puppy 是 Dog4 的子类，可以添加

        Object obj = superList.get(0);  // ✅ 只能读到 Object
        // Dog4 d = superList.get(0);    // ❌ 编译错误！不能转成 Dog4
        System.out.println("读取到的元素（Object）：" + obj);

        /**
         * 9. PECS 原则实战（Producer Extends, Consumer Super）
         * - 从集合中读取（生产）→ 用 extends
         * - 向集合中写入（消费）→ 用 super
         */
        System.out.println("\n========== 9. PECS 原则实战 ==========");

        // Producer（生产者）：用 extends
        List<Integer> nums = Arrays.asList(10, 20, 30);
        System.out.println("Producer（读取）：" + sumNumbers(nums));

        // Consumer（消费者）：用 super
        List<Number> destList = new ArrayList<>();
        copyNumbers(destList, nums);
        System.out.println("Consumer（写入）：" + destList);

        /**
         * 10. 泛型擦除
         */
        System.out.println("\n========== 10. 泛型擦除 ==========");
        // 运行时，泛型信息被擦除了
        List<String> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        System.out.println("list1 的类：" + list1.getClass().getName());
        System.out.println("list2 的类：" + list2.getClass().getName());
        System.out.println("两个在运行时是同一个类：" + (list1.getClass() == list2.getClass()));
    }

    // ============ 无界通配符方法 ============
    // ? 表示任意类型，但不能修改集合（只能读）
    public static void printList(List<?> list) {
        for (Object obj : list) {
            System.out.print(obj + " ");
        }
        System.out.println();
        // list.add("new"); // ❌ 编译报错！不能添加元素（除了 null）
    }

    // ============ 通配符 extends（上界） ============
    // ? extends Animal4：可以接收 Animal4 及其子类的 List
    // 只能读取，不能写入（除了 null）
    public static void printAnimals(List<? extends Animal4> animals) {
        // ✅ 可以读取，返回 Animal4 类型
        for (Animal4 animal : animals) {
            System.out.println("  动物：" + animal.getName());
        }
        // ❌ 不能添加（除了 null）
        // animals.add(new Animal4("测试"));  // 编译错误！
        // animals.add(new Dog4("测试"));     // 编译错误！
        // animals.add(null);               // ✅ 只能加 null
    }

    // ============ 通配符 super（下界） ============
    // ? super Dog4：可以接收 Dog4 及其父类的 List
    // 可以安全地添加 Dog4 及其子类
    public static void addDogs(List<? super Dog4> list) {
        // ✅ 可以添加 Dog4 及其子类
        list.add(new Dog4("旺财"));
        list.add(new Puppy("小黄"));
        // ❌ 不能添加 Dog4 的父类
        // list.add(new Animal4("测试"));  // 编译错误！

        // ❌ 读取时只能读到 Object
        // Dog4 d = list.get(0);  // 编译错误！
        // Animal4 a = list.get(0); // 编译错误！
        Object obj = list.get(0);  // ✅ 只能读到 Object
        System.out.println("  addDogs 添加了一个元素：" + obj);
    }

    // ============ PECS 原则：Producer Extends ============
    // 从集合中读取数据 → 用 ? extends T
    public static double sumNumbers(List<? extends Number> numbers) {
        double sum = 0;
        for (Number n : numbers) {
            sum += n.doubleValue();
        }
        return sum;
    }

    // ============ PECS 原则：Consumer Super ============
    // 向集合中写入数据 → 用 ? super T
    public static <T> void copyNumbers(List<? super T> dest, List<? extends T> src) {
        // dest 是 Consumer（写入），用 super
        // src 是 Producer（读取），用 extends
        for (T item : src) {
            dest.add(item);  // 安全：dest 能装下 T 及其父类
        }
    }
}

// ==================== 1. 泛型类 ====================
// T 是类型参数（Type），也可以用 E（Element）、K（Key）、V（Value）等
class Box<T> {
    private T value;

    public Box(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    // 获取类型的类名
    public String getType() {
        return value.getClass().getSimpleName();
    }
}

// ==================== 2. 泛型方法 ====================
class GenericMethods {
    // 泛型方法：<T> 放在返回值前面
    public <T> T printAndReturn(T input) {
        System.out.println("收到参数：" + input + "，类型：" + input.getClass().getSimpleName());
        return input;
    }

    // 泛型方法 + 可变参数
    public <T> T getFirst(T[] array) {
        if (array == null || array.length == 0) {
            return null;
        }
        return array[0];
    }

    // 泛型方法 + 返回类型是泛型
    public <T> List<T> arrayToList(T[] array) {
        List<T> list = new ArrayList<>();
        for (T item : array) {
            list.add(item);
        }
        return list;
    }
}

// ==================== 3. 泛型接口 ====================
interface Pair<K, V> {
    K getKey();
    V getValue();
}

// 实现方式1：实现时指定具体类型
class KeyValuePair implements Pair<String, Integer> {
    private String key;
    private Integer value;

    public KeyValuePair(String key, Integer value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public Integer getValue() {
        return value;
    }
}

// 实现方式2：实现时保留泛型
class MyPair<K, V> implements Pair<K, V> {
    private K key;
    private V value;

    public MyPair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }
}

// ==================== 6. 有界泛型 ====================
// 限定 T 必须是 Number 或其子类（上界）
class NumberBox<T extends Number> {
    private T value;

    public NumberBox(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    // 可以调用 Number 类的方法
    public double doubleValue() {
        return value.doubleValue();
    }
}

// ==================== 7/8. 通配符示例 ====================
// 继承关系：Animal4 ← Dog4 ← Puppy
class Animal4 {
    private String name;

    public Animal4(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Animal4{" + name + "}";
    }
}

class Dog4 extends Animal4 {
    public Dog4(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "Dog4{" + getName() + "}";
    }
}

class Puppy extends Dog4 {
    public Puppy(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "Puppy{" + getName() + "}";
    }
}