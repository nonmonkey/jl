import java.util.*;
import java.util.stream.*;
import java.util.stream.Stream.Builder;
import java.util.function.*;

/**
 * Stream 流
 * Java 8 引入的函数式编程接口，用于对集合进行高效、链式的批量操作
 *
 * 核心特点：
 * - 不存储数据：Stream 不是数据结构，不存储元素
 * - 函数式编程：使用 Lambda 表达式
 * - 惰性执行：中间操作不会立即执行，只有遇到终止操作才执行
 * - 链式调用：方法返回 Stream 本身，支持链式操作
 * - 不可变：Stream 不会修改原数据源
 * - 一次性：Stream 只能被消费一次，不能重复使用
 *
 * 操作分类：
 *
 * 【创建 Stream】
 * - stream()           → 从集合创建
 * - of()               → 从元素创建
 * - iterate()          → 迭代生成
 * - generate()         → 生成无限流
 * - empty()            → 创建空流
 * - builder()          → 使用构建器创建
 *
 * 【中间操作（惰性）】
 * - filter      → 过滤（保留满足条件的元素）
 * - map         → 映射（转换每个元素）
 * - flatMap     → 扁平映射（将每个元素转为流，再合并）
 * - distinct    → 去重
 * - sorted      → 排序
 * - limit       → 截取前 N 个
 * - skip        → 跳过前 N 个
 * - peek        → 查看中间结果（调试用）
 *
 * 【终止操作（立即执行）】
 * - forEach     → 遍历每个元素
 * - collect     → 收集到集合
 * - toList      → 收集到 List
 * - toSet       → 收集到 Set
 * - toMap       → 收集到 Map
 * - reduce      → 归约（聚合）
 * - count       → 计数
 * - anyMatch    → 是否存在匹配元素
 * - allMatch    → 是否所有元素都匹配
 * - noneMatch   → 是否所有元素都不匹配
 * - findFirst   → 查找第一个
 * - findAny     → 查找任意一个
 * - max         → 最大值
 * - min         → 最小值
 *
 * 适用场景：
 * - 集合的批量操作 ⭐⭐⭐⭐⭐
 * - 数据过滤、转换、聚合 ⭐⭐⭐⭐⭐
 * - 并行处理 ⭐⭐⭐⭐
 *
 * 注意事项：
 * - Stream 不能重复使用
 * - 并行流要注意线程安全
 * - 惰性求值：没有终止操作就不会执行
 */
public class Stream_ {

    public static void main(String[] args) {

        // ========== 1. 创建 Stream ==========
        System.out.println("========== 1. 创建 Stream ==========");

        // 1.1 从集合创建
        List<String> list = Arrays.asList("A", "B", "C");
        Stream<String> stream1 = list.stream();
        System.out.println("从集合创建：" + stream1.toList());

        // 1.2 从数组创建
        String[] array = {"A", "B", "C"};
        Stream<String> stream2 = Arrays.stream(array);
        System.out.println("从数组创建：" + stream2.toList());

        // 1.3 使用 Stream.of()
        Stream<String> stream3 = Stream.of("A", "B", "C");
        System.out.println("Stream.of()：" + stream3.toList());

        // 1.4 使用 Stream.iterate()（无限流，需配合 limit）
        Stream<Integer> stream4 = Stream.iterate(0, n -> n + 1).limit(5);
        System.out.println("Stream.iterate()：" + stream4.toList());

        // 1.5 使用 Stream.generate()（无限流）
        Stream<Double> stream5 = Stream.generate(Math::random).limit(3);
        System.out.println("Stream.generate()：" + stream5.toList());

        // 1.6 创建空流
        Stream<Object> stream6 = Stream.empty();
        System.out.println("空流：" + stream6.toList());

        // 1.7 使用 builder 构建
        Stream<String> stream7 = Stream.<String>builder()
                .add("A")
                .add("B")
                .add("C")
                .build();
        System.out.println("builder 构建：" + stream7.toList());

        // 1.8 使用 builder 构建
        Builder<String> builder = Stream.builder();
        builder.add("A");
        builder.add("B");
        builder.add("C");
        Stream<String> stream8 = builder.build();
        System.out.println("builder 构建：" + stream8.toList());

        // ========== 2. 中间操作 ==========
        System.out.println("\n========== 2. 中间操作 ==========");

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // filter → 过滤（保留偶数）
        System.out.print("filter（偶数）：");
        numbers.stream()
                .filter(n -> n % 2 == 0)
                .forEach(n -> System.out.print(n + " "));
        System.out.println();

        // map → 映射（转换为字符串）
        System.out.print("map（转字符串）：");
        numbers.stream()
                .map(n -> "数字" + n)
                .forEach(s -> System.out.print(s + " "));
        System.out.println();

        // distinct → 去重
        List<Integer> duplicates = Arrays.asList(1, 2, 2, 3, 3, 3, 4, 4, 4, 4);
        System.out.print("distinct（去重）：");
        duplicates.stream()
                .distinct()
                .forEach(n -> System.out.print(n + " "));
        System.out.println();

        // sorted → 排序（降序）
        List<Integer> unsorted = Arrays.asList(5, 2, 8, 1, 9, 3);
        System.out.print("sorted（降序）：");
        unsorted.stream()
                .sorted((a, b) -> b - a)
                .forEach(n -> System.out.print(n + " "));
        System.out.println();

        // limit → 截取前 N 个
        System.out.print("limit（前 3 个）：");
        numbers.stream()
                .limit(3)
                .forEach(n -> System.out.print(n + " "));
        System.out.println();

        // skip → 跳过前 N 个
        System.out.print("skip（跳过前 3 个）：");
        numbers.stream()
                .skip(3)
                .forEach(n -> System.out.print(n + " "));
        System.out.println();

        // peek → 查看中间结果（调试用）
        System.out.print("peek（调试）：");
        numbers.stream()
                .filter(n -> n % 2 == 0)
                .peek(n -> System.out.print("[" + n + "]"))
                .map(n -> n * 2)
                .forEach(n -> System.out.print(n + " "));
        System.out.println();

        // ========== 3. 链式操作 ==========
        System.out.println("\n========== 3. 链式操作 ==========");

        List<String> words = Arrays.asList("apple", "banana", "apple", "orange", "banana", "grape");

        System.out.println("原始数据：" + words);

        // 链式操作：去重 → 按长度排序 → 转大写 → 收集
        List<String> result = words.stream()
                .distinct()                          // 去重
                .sorted((a, b) -> a.length() - b.length())  // 按长度排序
                .map(String::toUpperCase)            // 转大写
                .toList();       // 收集

        System.out.println("去重 → 按长度排序 → 转大写：" + result);

        // ========== 4. flatMap（扁平映射） ==========
        System.out.println("\n========== 4. flatMap（扁平映射） ==========");

        // flatMap → 将每个元素转为流，再合并
        List<List<Integer>> nestedList = Arrays.asList(
                Arrays.asList(1, 2),
                Arrays.asList(3, 4),
                Arrays.asList(5, 6)
        );

        System.out.println("嵌套列表：" + nestedList);

        // 展开为单层
        List<Integer> flatList = nestedList.stream()
                .flatMap(Collection::stream)
                .toList();

        System.out.println("flatMap 展开后：" + flatList);

        // 示例：将句子拆分为单词
        List<String> sentences = Arrays.asList(
                "Hello World",
                "Java Stream",
                "FlatMap Example"
        );

        List<String> words2 = sentences.stream()
                .flatMap(s -> Arrays.stream(s.split(" ")))
                .map(String::toLowerCase)
                .toList();

        System.out.println("句子拆分单词：" + words2);

        // ========== 5. 终止操作 ==========
        System.out.println("\n========== 5. 终止操作 ==========");

        List<Integer> data = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // forEach → 遍历
        System.out.print("forEach：");
        data.stream().forEach(n -> System.out.print(n + " "));
        System.out.println();

        // count → 计数
        long count = data.stream().count();
        System.out.println("count：" + count);

        // collect → 收集到集合
        List<Integer> evenList = data.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());
        System.out.println("collect（偶数集合）：" + evenList);

        // toList → Java 16+，收集到 List
        // List<Integer> evenList2 = data.stream()
        //         .filter(n -> n % 2 == 0)
        //         .toList();

        // reduce → 归约（求和）
        int sum = data.stream()
                .reduce(0, Integer::sum);
        System.out.println("reduce（求和）：" + sum);

        // reduce → 归约（最大值）
        int max = data.stream()
                .reduce(Integer::max)
                .orElse(0);
        System.out.println("reduce（最大值）：" + max);

        // anyMatch → 是否存在匹配元素
        boolean hasEven = data.stream().anyMatch(n -> n % 2 == 0);
        System.out.println("anyMatch（是否存在偶数）：" + hasEven);

        // allMatch → 是否所有元素都匹配
        boolean allEven = data.stream().allMatch(n -> n % 2 == 0);
        System.out.println("allMatch（是否全是偶数）：" + allEven);

        // noneMatch → 是否所有元素都不匹配
        boolean noneNegative = data.stream().noneMatch(n -> n < 0);
        System.out.println("noneMatch（是否都是非负数）：" + noneNegative);

        // findFirst → 查找第一个
        Optional<Integer> first = data.stream().findFirst();
        System.out.println("findFirst：" + first.orElse(0));

        // findAny → 查找任意一个
        Optional<Integer> any = data.stream().findAny();
        System.out.println("findAny：" + any.orElse(0));

        // max → 最大值
        Optional<Integer> max2 = data.stream().max(Integer::compareTo);
        System.out.println("max：" + max2.orElse(0));

        // min → 最小值
        Optional<Integer> min = data.stream().min(Integer::compareTo);
        System.out.println("min：" + min.orElse(0));

        // ========== 6. collect 收集器 ==========
        System.out.println("\n========== 6. collect 收集器 ==========");

        List<Person> people = Arrays.asList(
                new Person("张三", 20),
                new Person("李四", 25),
                new Person("王五", 20),
                new Person("赵六", 30),
                new Person("钱七", 25)
        );

        // toList → 收集为 List
        List<String> names = people.stream()
                .map(Person::getName)
                .collect(Collectors.toList());
        System.out.println("toList（姓名列表）：" + names);

        // toSet → 收集为 Set（去重）
        Set<Integer> ages = people.stream()
                .map(Person::getAge)
                .collect(Collectors.toSet());
        System.out.println("toSet（年龄去重）：" + ages);

        // toMap → 收集为 Map
        Map<String, Integer> personMap = people.stream()
                .collect(Collectors.toMap(
                        Person::getName,
                        Person::getAge,
                        (old, newV) -> old  // 冲突处理
                ));
        System.out.println("toMap（姓名→年龄）：" + personMap);

        // groupingBy → 分组
        Map<Integer, List<Person>> groupByAge = people.stream()
                .collect(Collectors.groupingBy(Person::getAge));
        System.out.println("groupingBy（按年龄分组）：");
        groupByAge.forEach((age, personList) -> {
            System.out.println("  " + age + "岁：" + personList);
        });

        // partitioningBy → 分区（true/false 两组）
        Map<Boolean, List<Person>> partitionByAge = people.stream()
                .collect(Collectors.partitioningBy(p -> p.getAge() >= 25));
        System.out.println("partitioningBy（是否 >= 25 岁）：");
        System.out.println("  >= 25：" + partitionByAge.get(true));
        System.out.println("  < 25：" + partitionByAge.get(false));

        // joining → 连接字符串
        String joinedNames = people.stream()
                .map(Person::getName)
                .collect(Collectors.joining(", ", "[", "]"));
        System.out.println("joining：" + joinedNames);

        // ========== 7. 基本类型 Stream ==========
        System.out.println("\n========== 7. 基本类型 Stream ==========");

        // IntStream
        IntStream intStream = IntStream.range(1, 10);
        System.out.println("IntStream.range(1, 10)：" + intStream.boxed().toList());

        IntStream intStream2 = IntStream.rangeClosed(1, 10);
        System.out.println("IntStream.rangeClosed(1, 10)：" + intStream2.boxed().toList());

        // sum
        int sum2 = IntStream.range(1, 10).sum();
        System.out.println("IntStream.sum()：" + sum2);

        // average
        OptionalDouble avg = IntStream.range(1, 10).average();
        System.out.println("IntStream.average()：" + avg.orElse(0));

        // ========== 8. 并行流 ==========
        System.out.println("\n========== 8. 并行流 ==========");

        List<Integer> bigList = IntStream.range(0, 30000000)
                .boxed()
                .toList();

        // 串行流
        long start1 = System.currentTimeMillis();
        long count1 = bigList.stream()
                .filter(n -> n % 2 == 0)
                .count();
        long end1 = System.currentTimeMillis();
        System.out.println("串行流（偶数个数：" + count1 + "）耗时：" + (end1 - start1) + "ms");

        // 并行流
        long start2 = System.currentTimeMillis();
        long count2 = bigList.parallelStream()
                .filter(n -> n % 2 == 0)
                .count();
        long end2 = System.currentTimeMillis();
        System.out.println("并行流（偶数个数：" + count2 + "）耗时：" + (end2 - start2) + "ms");

        System.out.println("✅ 并行流利用多核 CPU，大幅提升性能");

        // ========== 9. 实际应用场景 ==========
        System.out.println("\n========== 9. 实际应用场景 ==========");

        // 场景1：成绩统计
        System.out.println("--- 场景1：成绩统计 ---");
        List<Integer> scores2 = Arrays.asList(85, 92, 78, 95, 88, 70, 90, 82, 66, 80);

        double average = scores2.stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0);
        System.out.println("平均分：" + average);

        long passCount = scores2.stream()
                .filter(s -> s >= 60)
                .count();
        System.out.println("及格人数：" + passCount);

        Optional<Integer> topScore = scores2.stream()
                .max(Integer::compareTo);
        System.out.println("最高分：" + topScore.orElse(0));

        // 场景2：按部门分组统计
        System.out.println("\n--- 场景2：员工统计 ---");
        List<Employee> employees = Arrays.asList(
                new Employee("张三", "技术部", 15000),
                new Employee("李四", "技术部", 18000),
                new Employee("王五", "市场部", 12000),
                new Employee("赵六", "市场部", 14000),
                new Employee("钱七", "人事部", 10000)
        );

        // 按部门分组
        Map<String, Double> deptAvgSalary = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.averagingDouble(Employee::getSalary)
                ));
        System.out.println("各部门平均工资：");
        deptAvgSalary.forEach((dept, avgSalary) ->
                System.out.println("  " + dept + "：" + avgSalary)
        );

        // 总工资
        double totalSalary = employees.stream()
                .mapToDouble(Employee::getSalary)
                .sum();
        System.out.println("总工资：" + totalSalary);

        // 场景3：查找特定员工
        System.out.println("\n--- 场景3：查找特定员工 ---");
        Optional<Employee> found = employees.stream()
                .filter(e -> e.getName().equals("张三"))
                .findFirst();
        found.ifPresent(e -> System.out.println("找到：" + e));

        // ========== 10. 注意事项 ==========
        System.out.println("\n========== 10. 注意事项 ==========");

        // ⚠️ 注意1：Stream 不能重复使用
        Stream<Integer> reusable = Stream.of(1, 2, 3);
        reusable.forEach(System.out::println);
        // reusable.forEach(System.out::println); // ❌ IllegalStateException

        // ⚠️ 注意2：没有终止操作就不会执行
        System.out.println("⚠️ 没有终止操作，中间操作不会执行：");
        Stream.of(1, 2, 3)
                .filter(n -> {
                    System.out.println("filter: " + n);
                    return n > 1;
                });
        System.out.println("  上面的 filter 没有执行！");

        // ⚠️ 注意3：并行流使用要谨慎
        System.out.println("⚠️ 并行流要注意线程安全，避免共享可变状态");

        // ⚠️ 注意4：无限流必须配合 limit
        // Stream.iterate(0, n -> n + 1).forEach(System.out::println); // ❌ 无限循环
        Stream.iterate(0, n -> n + 1).limit(5).forEach(System.out::println);

        // ⚠️ 注意5：null 值问题
        List<String> withNull = Arrays.asList("A", null, "C");
        // withNull.stream().forEach(System.out::println); // 可以处理 null
        // withNull.stream().map(String::toUpperCase).collect(Collectors.toList()); // ❌ 空指针

        System.out.println("⚠️ 如果元素可能为 null，需要先过滤：");
        withNull.stream()
                .filter(Objects::nonNull)
                .forEach(System.out::println);
    }

    // ========== 内部类 ==========

    static class Person {
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() { return name; }
        public int getAge() { return age; }

        @Override
        public String toString() {
            return name + "(" + age + ")";
        }
    }

    static class Employee {
        private String name;
        private String department;
        private double salary;

        public Employee(String name, String department, double salary) {
            this.name = name;
            this.department = department;
            this.salary = salary;
        }

        public String getName() { return name; }
        public String getDepartment() { return department; }
        public double getSalary() { return salary; }

        @Override
        public String toString() {
            return name + "(" + department + ", " + salary + ")";
        }
    }
}