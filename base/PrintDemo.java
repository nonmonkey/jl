public class PrintDemo {
    public static void main(String[] args) {
        String name = "小明";
        int age = 18;
        double height = 175.23351;

        // 1. print - 不换行
        System.out.print("姓名：");
        System.out.print(name);
        System.out.print("，");

        // 2. println - 换行
        System.out.println("年龄：" + age);
        System.out.println("--- 分隔线 ---");

        String s = """
                   SELECT * FROM
                     users
                   WHERE id > 100
                   ORDER BY name DESC
                   """;
        System.out.println(s); // 多行

        // 3. printf - 格式化
        System.out.printf("姓名：%s，年龄：%d岁，身高：%.2f，%n", name, age, height);

        // 4. format - 同 printf
        System.out.format("姓名：%s，年龄：%d岁，身高：%.2f，%n", name, age, height);
    }
}
