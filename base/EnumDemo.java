import java.util.*;

public class EnumDemo {
    public static void main(String[] args) {
        // ===== 1. 基本使用 =====
        System.out.println("===== 1. 基本使用 =====");

        // 声明枚举变量
        Season season = Season.SPRING;
        System.out.println("当前季节：" + season); // SPRING

        // 用 == 比较枚举（推荐）
        if (season == Season.SPRING) {
            System.out.println("是春天！");
        }

        // 用 switch 配合枚举
        switch (season) {
            case SPRING:
                System.out.println("春天来了");
                break;
            case SUMMER:
                System.out.println("夏天来了");
                break;
            case AUTUMN:
                System.out.println("秋天来了");
                break;
            case WINTER:
                System.out.println("冬天来了");
                break;
        }

        // ===== 2. 遍历所有枚举值 =====
        System.out.println("\n===== 2. 遍历所有枚举值 =====");
        for (Season s : Season.values()) {
            System.out.println(s);
        }

        // ===== 3. 根据字符串获取枚举 =====
        System.out.println("\n===== 3. 根据字符串获取枚举 =====");
        Season s = Season.valueOf("SUMMER");
        System.out.println("Season.valueOf(\"SUMMER\") = " + s);

        // ===== 4. ordinal() 获取序号 =====
        System.out.println("\n===== 4. ordinal() 获取序号 =====");
        System.out.println("SPRING.ordinal() = " + Season.SPRING.ordinal()); // 0
        System.out.println("WINTER.ordinal() = " + Season.WINTER.ordinal()); // 3

        // ===== 5. 带字段和方法的枚举 =====
        System.out.println("\n===== 5. 带字段和方法的枚举 =====");
        Status status = Status.PAID;
        System.out.println("状态名称：" + status.name());
        System.out.println("状态描述：" + status.getDesc());
        System.out.println("是否已完成：" + status.isCompleted());

        // ===== 6. 枚举配合集合使用 =====
        System.out.println("\n===== 6. 枚举配合集合使用 =====");
        Map<Status, String> statusMap = new HashMap<>();
        statusMap.put(Status.PENDING, "待处理");
        statusMap.put(Status.PAID, "已支付");
        statusMap.put(Status.SHIPPED, "已发货");
        statusMap.put(Status.COMPLETED, "已完成");

        System.out.println("PENDING 对应：" + statusMap.get(Status.PENDING));

        // EnumSet - 专为枚举优化的集合
        EnumSet<Status> activeStatuses = EnumSet.of(Status.PENDING, Status.PAID);
        System.out.println("活跃状态：" + activeStatuses);

        // ===== 7. 枚举实现接口 =====
        System.out.println("\n===== 7. 枚举实现接口 =====");
        Color red = Color.RED;
        System.out.println("颜色：" + red);
        System.out.println("RGB 值：" + red.getRgb());
        System.out.println("是否暖色：" + red.isWarm());

        // ===== 8. 枚举 + 抽象方法（策略模式） =====
        System.out.println("\n===== 8. 枚举 + 抽象方法 =====");
        System.out.println("10 + 5 = " + Operation.ADD.apply(10, 5));
        System.out.println("10 - 5 = " + Operation.SUBTRACT.apply(10, 5));
        System.out.println("10 * 5 = " + Operation.MULTIPLY.apply(10, 5));
    }
}

/**
 * 1. 最简单的枚举
 */
enum Season {
    SPRING, SUMMER, AUTUMN, WINTER
}

/**
 * 2. 带字段、构造方法、方法的枚举
 */
enum Status {
    // 枚举常量，括号里传参给构造方法
    PENDING("待支付", false),
    PAID("已支付", false),
    SHIPPED("已发货", false),
    COMPLETED("已完成", true),
    CANCELLED("已取消", true);

    // 字段
    private String desc;
    private boolean completed;

    // 构造方法（必须是 private，默认也是 private）
    Status(String desc, boolean completed) {
        this.desc = desc;
        this.completed = completed;
    }

    // getter 方法
    public String getDesc() {
        return desc;
    }

    public boolean isCompleted() {
        return completed;
    }
}

/**
 * 3. 枚举实现接口
 */
interface ColorInfo {
    String getRgb();
    boolean isWarm();
}

enum Color implements ColorInfo {
    RED("#FF0000"),
    ORANGE("#FFA500"),
    BLUE("#0000FF"),
    GREEN("#00FF00");

    private String rgb;

    Color(String rgb) {
        this.rgb = rgb;
    }

    @Override
    public String getRgb() {
        return rgb;
    }

    @Override
    public boolean isWarm() {
        return this == RED || this == ORANGE;
    }
}

/**
 * 4. 枚举 + 抽象方法（每个常量自己实现）
 */
enum Operation {
    ADD {
        @Override
        public int apply(int a, int b) {
            return a + b;
        }
    },
    SUBTRACT {
        @Override
        public int apply(int a, int b) {
            return a - b;
        }
    },
    MULTIPLY {
        @Override
        public int apply(int a, int b) {
            return a * b;
        }
    };

    // 抽象方法，每个枚举常量必须实现
    public abstract int apply(int a, int b);
}