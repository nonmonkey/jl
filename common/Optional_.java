import java.util.Optional;
import java.util.OptionalInt;
import java.util.OptionalDouble;
import java.util.OptionalLong;
import java.util.stream.*;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.function.Supplier;

/**
 * Optional
 * Java 8 引入的容器类，用于可能为 null 的值
 *
 * 核心目的：
 * - 明确表达一个值可能为空
 * - 避免 NullPointerException
 * - 提供更优雅的空值处理方式
 * - 强制调用者思考空值情况
 *
 * 核心特点：
 * - 容器：可以包含一个值，也可以为空
 * - 不可变：一旦创建不可修改
 * - 不能为 null：Optional 本身不能为 null
 * - 函数式：提供 map、filter、flatMap 等方法
 *
 * 核心方法：
 *
 * 【创建 Optional】
 * - of()           → 创建包含非 null 值的 Optional（值不能为 null）
 * - ofNullable()   → 创建可能为 null 的 Optional
 * - empty()        → 创建空 Optional
 *
 * 【判断】
 * - isPresent()    → 判断值是否存在
 * - isEmpty()      → 判断值是否为空（Java 11）
 *
 * 【获取值】
 * - get()          → 获取值（值为空则抛异常）
 * - orElse()       → 获取值，为空返回默认值
 * - orElseGet()    → 获取值，为空执行 Supplier 并返回
 * - orElseThrow()  → 获取值，为空抛出异常
 * - or()           → 获取值，为空返回另一个 Optional（Java 9）
 *
 * 【转换】
 * - map()          → 转换值（返回 Optional）
 * - flatMap()      → 扁平转换（返回 Optional）
 * - filter()       → 过滤值（返回 Optional）
 *
 * 【消费】
 * - ifPresent()    → 值存在时执行 Consumer
 * - ifPresentOrElse() → 值存在执行 Consumer，否则执行 Runnable（Java 9）
 *
 * 适用场景：
 * - 方法的返回值（替代 null） ⭐⭐⭐⭐⭐
 * - 链式操作（map/flatMap/filter） ⭐⭐⭐⭐⭐
 * - 流式处理（Stream 的终端操作） ⭐⭐⭐⭐⭐
 * - 配置默认值 ⭐⭐⭐⭐
 *
 * 注意事项：
 * - Optional 不能作为字段（会增加内存开销）
 * - Optional 不能作为方法参数（增加调用负担）
 * - Optional 不能序列化（不实现 Serializable）
 * - Optional 不适合作为集合的元素
 */
public class Optional_ {

    public static void main(String[] args) {

        // ========== 1. 创建 Optional ==========
        System.out.println("========== 1. 创建 Optional ==========");

        // 1.1 of() → 必须非 null
        Optional<String> opt1 = Optional.of("Hello");
        System.out.println("Optional.of(\"Hello\")：" + opt1);

        // 1.2 ofNullable() → 可以为 null
        Optional<String> opt2 = Optional.ofNullable("World");
        Optional<String> opt3 = Optional.ofNullable(null);
        System.out.println("Optional.ofNullable(\"World\")：" + opt2);
        System.out.println("Optional.ofNullable(null)：" + opt3);

        // 1.3 empty() → 创建空 Optional
        Optional<String> opt4 = Optional.empty();
        System.out.println("Optional.empty()：" + opt4);

        // 1.4 of() 传入 null 会抛异常
        // Optional<String> opt5 = Optional.of(null); // ❌ NullPointerException

        // ========== 2. 判断方法 ==========
        System.out.println("\n========== 2. 判断方法 ==========");

        Optional<String> present = Optional.of("Java");
        Optional<String> empty = Optional.empty();

        // isPresent() → 判断值是否存在
        System.out.println("present.isPresent()：" + present.isPresent());
        System.out.println("empty.isPresent()：" + empty.isPresent());

        // isEmpty() → 判断值是否为空（Java 11）
        System.out.println("present.isEmpty()：" + present.isEmpty());
        System.out.println("empty.isEmpty()：" + empty.isEmpty());

        // ========== 3. 获取值 ==========
        System.out.println("\n========== 3. 获取值 ==========");

        Optional<String> opt = Optional.of("Java");

        // get() → 直接获取（空则抛异常）
        System.out.println("get()：" + opt.get());

        // orElse() → 为空返回默认值
        System.out.println("orElse(\"Default\")：" + opt.orElse("Default"));
        System.out.println("empty.orElse(\"Default\")：" + Optional.empty().orElse("Default"));

        // orElseGet() → 为空执行 Supplier
        System.out.println("orElseGet()：" + opt.orElseGet(() -> "Generated"));
        System.out.println("empty.orElseGet()：" + Optional.empty().orElseGet(() -> "Generated"));

        // orElseThrow() → 为空抛异常
        try {
            Optional.empty().orElseThrow();
        } catch (NoSuchElementException e) {
            System.out.println("orElseThrow() 抛出：" + e.getClass().getSimpleName());
        }

        // orElseThrow(Supplier) → 自定义异常
        try {
            Optional.empty().orElseThrow(() -> new RuntimeException("自定义异常"));
        } catch (RuntimeException e) {
            System.out.println("orElseThrow(Supplier) 抛出：" + e.getMessage());
        }

        // or() → 为空返回另一个 Optional（Java 9）
        Optional<String> fallback = Optional.of("Fallback");
        System.out.println("or(fallback)：" + opt.or(() -> fallback));
        System.out.println("empty.or(fallback)：" + Optional.empty().or(() -> fallback));

        // ========== 4. 转换方法 ==========
        System.out.println("\n========== 4. 转换方法 ==========");

        Optional<String> nameOpt = Optional.of("张三zs");

        // map → 转换值
        Optional<Integer> lengthOpt = nameOpt.map(String::length);
        System.out.println("map(String::length)：" + lengthOpt.orElse(0));

        // map 为空时返回 empty
        Optional<Integer> emptyLength = Optional.<String>empty().map(String::length);
        System.out.println("empty.map(String::length)：" + emptyLength.orElse(0));

        // flatMap → 扁平转换（返回 Optional）
        Optional<String> upperOpt = nameOpt.flatMap(s -> Optional.of(s.toUpperCase()));
        System.out.println("flatMap(s -> Optional.of(s.toUpperCase()))：" + upperOpt.orElse(""));

        // filter → 过滤
        Optional<String> filtered1 = nameOpt.filter(s -> s.length() > 2);
        Optional<String> filtered2 = nameOpt.filter(s -> s.length() > 5);
        System.out.println("filter(长度 > 2)：" + filtered1.orElse("不存在"));
        System.out.println("filter(长度 > 5)：" + filtered2.orElse("不存在"));

        // ========== 5. 消费方法 ==========
        System.out.println("\n========== 5. 消费方法 ==========");

        // ifPresent() → 值存在时执行
        System.out.print("ifPresent()：");
        Optional.of("Java").ifPresent(s -> System.out.println("值存在：" + s));

        System.out.print("empty.ifPresent()：");
        Optional.empty().ifPresent(s -> System.out.println("不会执行"));

        // ifPresentOrElse() → 值存在执行 Consumer，否则执行 Runnable（Java 9）
        System.out.print("ifPresentOrElse()：");
        Optional.of("Java").ifPresentOrElse(
                s -> System.out.println("值存在：" + s),
                () -> System.out.println("值为空")
        );

        System.out.print("empty.ifPresentOrElse()：");
        Optional.empty().ifPresentOrElse(
                s -> System.out.println("值存在：" + s),
                () -> System.out.println("值为空")
        );

        // ========== 6. 链式操作 ==========
        System.out.println("\n========== 6. 链式操作 ==========");

        // 传统写法（层层判空）
        System.out.println("--- 传统写法 ---");
        User user = new User("张三", new Address("北京", "朝阳区"));
        String city = null;
        if (user != null) {
            Address address = user.getAddress();
            if (address != null) {
                city = address.getCity();
            }
        }
        System.out.println("city：" + city);

        // Optional 写法
        System.out.println("\n--- Optional 写法 ---");
        Optional<User> userOpt = Optional.ofNullable(user);

        String city2 = userOpt
                .map(User::getAddress)
                .map(Address::getCity)
                .orElse("未知城市");
        System.out.println("city：" + city2);

        // 更复杂的链式操作
        String result = Optional.ofNullable(user)
                .map(User::getAddress)
                .map(Address::getCity)
                .filter(c -> c.length() > 2)
                .map(c -> "城市：" + c)
                .orElse("未知");
        System.out.println("链式操作结果：" + result);

        // ========== 7. 流式处理中的 Optional ==========
        System.out.println("\n========== 7. 流式处理中的 Optional ==========");

        List<String> names = Arrays.asList("张三", null, "李四", null, "王五");

        // 传统方式：过滤 null
        List<String> nonNullNames = new ArrayList<>();
        for (String name : names) {
            if (name != null) {
                nonNullNames.add(name);
            }
        }
        System.out.println("传统方式过滤 null：" + nonNullNames);

        // Optional + Stream
        List<String> optionalNames = names.stream()
                .map(Optional::ofNullable)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
        System.out.println("Optional 方式过滤 null：" + optionalNames);

        // 更简洁的方式（使用 flatMap）
        List<String> flatMapNames = names.stream()
                .flatMap(s -> Optional.ofNullable(s).stream())
                .collect(Collectors.toList());
        System.out.println("flatMap 方式过滤 null：" + flatMapNames);

        // ========== 8. 实际应用场景 ==========
        System.out.println("\n========== 8. 实际应用场景 ==========");

        // 场景1：从 Map 中安全取值
        System.out.println("--- 场景1：从 Map 中安全取值 ---");
        java.util.Map<String, String> config = new java.util.HashMap<>();
        config.put("host", "localhost");

        String host = Optional.ofNullable(config.get("host"))
                .orElse("127.0.0.1");
        String port = Optional.ofNullable(config.get("port"))
                .orElse("8080");
        System.out.println("host：" + host);
        System.out.println("port：" + port);

        // 场景2：查找列表中的元素
        System.out.println("\n--- 场景2：查找列表中的元素 ---");
        List<String> fruits = Arrays.asList("apple", "banana", "orange");

        Optional<String> found = fruits.stream()
                .filter(f -> f.startsWith("b"))
                .findFirst();

        found.ifPresent(f -> System.out.println("找到：" + f));
        System.out.println("未找到时的默认值：" + found.orElse("未找到"));

        // 场景3：配置默认值 + 转换
        System.out.println("\n--- 场景3：配置默认值 + 转换 ---");
        String timeoutStr = Optional.ofNullable(config.get("timeout"))
                .orElse("5000");
        int timeout = 0;
        try {
            timeout = Integer.parseInt(timeoutStr);
        } catch (NumberFormatException e) {
            timeout = 5000;
        }
        System.out.println("传统方式 timeout：" + timeout);

        // Optional 方式
        int timeout2 = Optional.ofNullable(config.get("timeout"))
                .map(Integer::parseInt)
                .orElse(5000);
        System.out.println("Optional 方式 timeout：" + timeout2);

        // 场景4：空值安全的方法调用
        System.out.println("\n--- 场景4：空值安全的方法调用 ---");
        String text = Optional.ofNullable("Hello World")
                .map(String::toUpperCase)
                .map(s -> s.substring(0, 5))
                .orElse("默认");
        System.out.println("text：" + text);

        // ========== 9. 基本类型 Optional ==========
        System.out.println("\n========== 9. 基本类型 Optional ==========");

        // OptionalInt
        OptionalInt optInt = OptionalInt.of(10);
        System.out.println("OptionalInt：" + optInt.getAsInt());
        System.out.println("OptionalInt.orElse(0)：" + OptionalInt.empty().orElse(0));

        // OptionalLong
        OptionalLong optLong = OptionalLong.of(100L);
        System.out.println("OptionalLong：" + optLong.getAsLong());

        // OptionalDouble
        OptionalDouble optDouble = OptionalDouble.of(3.14);
        System.out.println("OptionalDouble：" + optDouble.getAsDouble());

        // ========== 10. 注意事项 ==========
        System.out.println("\n========== 10. 注意事项 ==========");

        // ⚠️ 注意1：不要用 null 赋值给 Optional
        // Optional<String> bad = null; // ❌ 不推荐

        // ⚠️ 注意2：不要在字段中使用 Optional
        // class BadUser {
        //     private Optional<String> name; // ❌ 不推荐
        // }

        // ⚠️ 注意3：不要在方法参数中使用 Optional
        // public void setName(Optional<String> name) {} // ❌ 不推荐

        // ⚠️ 注意4：不要在集合中使用 Optional
        // List<Optional<String>> list = ... // ❌ 不推荐

        // ⚠️ 注意5：orElse() 和 orElseGet() 的区别
        System.out.println("orElse() vs orElseGet()：");
        System.out.println("  orElse() 无论值是否存在都会执行");
        System.out.println("  orElseGet() 只在值为空时执行（懒加载）");

        // 示例：orElse 会执行
        System.out.println("--- orElse 示例 ---");
        Optional<String> presentOpt = Optional.of("存在");
        String result1 = presentOpt.orElse(getDefaultValue());
        System.out.println("orElse 结果：" + result1);

        // orElseGet 不会执行
        System.out.println("--- orElseGet 示例 ---");
        String result2 = presentOpt.orElseGet(() -> getDefaultValue());
        System.out.println("orElseGet 结果：" + result2);

        // ⚠️ 注意6：Optional 不能序列化
        // class BadUser implements Serializable {
        //     private Optional<String> name; // ❌ 序列化问题
        // }

        // ========== 11. Optional 最佳实践 ==========
        System.out.println("\n========== 11. Optional 最佳实践 ==========");

        System.out.println("✅ 适合使用 Optional 的场景：");
        System.out.println("  1. 方法的返回值可能为 null");
        System.out.println("  2. 链式调用中的空值处理");
        System.out.println("  3. Stream 的终端操作（findFirst、max 等）");
        System.out.println("  4. 配置默认值");

        System.out.println("\n❌ 不适合使用 Optional 的场景：");
        System.out.println("  1. 类的字段");
        System.out.println("  2. 方法的参数");
        System.out.println("  3. 集合的元素");
        System.out.println("  4. 需要序列化的对象");
    }

    // ========== 辅助方法 ==========

    private static String getDefaultValue() {
        System.out.println("  > getDefaultValue() 被执行");
        return "默认值";
    }

    // ========== 内部类 ==========

    static class User {
        private String name;
        private Address address;

        public User(String name, Address address) {
            this.name = name;
            this.address = address;
        }

        public String getName() { return name; }
        public Address getAddress() { return address; }
    }

    static class Address {
        private String city;
        private String district;

        public Address(String city, String district) {
            this.city = city;
            this.district = district;
        }

        public String getCity() { return city; }
        public String getDistrict() { return district; }
    }
}