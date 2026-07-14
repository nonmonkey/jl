import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Exception_ {

    public static void main(String[] args) {
        System.out.println("========== 1. 基本 try-catch ==========");
        basicTryCatch();

        System.out.println("\n========== 2. 多个 catch ==========");
        multipleCatch();

        System.out.println("\n========== 3. finally ==========");
        finallyDemo();

        System.out.println("\n========== 4. throw / throws ==========");
        try {
            checkAge(-5);
        } catch (IllegalArgumentException e) {
            System.out.println("捕获到异常：" + e.getMessage());
        }

        System.out.println("\n========== 5. 受检异常处理 ==========");
        readFile("not_exist.txt");

        System.out.println("\n========== 6. try-with-resources (Java 7+) ==========");
        tryWithResources();

        System.out.println("\n========== 7. 自定义异常 ==========");
        try {
            withdraw(100, 50);
        } catch (InsufficientBalanceException e) {
            System.out.println("自定义异常：" + e.getMessage() + "，余额：" + e.getBalance());
        }
    }

    // ============ 1. 基本 try-catch ============
    public static void basicTryCatch() {
        try {
            int result = 10 / 0;  // 会抛出 ArithmeticException
            System.out.println("结果：" + result);
        } catch (ArithmeticException e) {
            System.out.println("捕获到异常：" + e.getMessage());
        }
        System.out.println("程序继续运行");
    }

    // ============ 2. 多个 catch ============
    public static void multipleCatch() {
        String str = null;
        try {
            // 这行会抛出 NullPointerException，不会执行后续代码
            System.out.println(str.length());
            int[] arr = {1, 2, 3};
            System.out.println(arr[5]);
        } catch (NullPointerException e) {
            System.out.println("空指针异常：" + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("数组越界：" + e.getMessage());
        } catch (Exception e) {
            // 放在最后，捕获所有未被捕获的异常
            System.out.println("其他异常：" + e.getMessage());
        }
    }

    // ============ 3. finally ============
    public static void finallyDemo() {
        try {
            System.out.println("try 执行");
            int result = 10 / 2;
            // return;  // 即使有 return，finally 也会执行
        } catch (Exception e) {
            System.out.println("catch 执行");
        } finally {
            System.out.println("✅ finally 永远执行（释放资源）");
        }
    }

    // ============ 4. throw / throws ============
    // throws：声明这个方法可能抛出异常，让调用者处理
    public static void checkAge(int age) throws IllegalArgumentException {
        if (age < 0) {
            // throw：手动抛出异常
            throw new IllegalArgumentException("年龄不能为负数：" + age);
        }
        System.out.println("年龄合法：" + age);
    }

    // ============ 5. 受检异常处理 ============
    // 受检异常必须处理（try-catch 或 throws）
    public static void readFile(String filename) {
        BufferedReader br = null;
        try {
            FileReader reader = new FileReader(filename);
            br = new BufferedReader(reader);
            System.out.println("第一行：" + br.readLine());
        } catch (IOException e) {
            System.out.println("读取失败：" + e.getMessage());
        } finally {
            // 在这里关闭，保证一定会执行
            if (br != null) {
                try {
                    br.close(); // 注意：close() 本身也可能抛异常，需要再包一层 try
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // ============ 6. try-with-resources (Java 7+) ============
    // 自动关闭资源，不用手动 close()
    public static void tryWithResources() {
        // 资源类必须实现 AutoCloseable 接口
        try (FileReader reader = new FileReader("test.txt");
             BufferedReader br = new BufferedReader(reader)) {
            // 即使文件不存在，也会自动关闭资源
            System.out.println("读取内容：" + br.readLine());
        } catch (IOException e) {
            System.out.println("文件操作失败：" + e.getMessage());
        }
    }

    // ============ 7. 自定义异常 ============
    // 继承 Exception（受检）或 RuntimeException（非受检）
    static class InsufficientBalanceException extends RuntimeException {
        private double balance;

        public InsufficientBalanceException(String message, double balance) {
            super(message);
            this.balance = balance;
        }

        public double getBalance() {
            return balance;
        }
    }

    public static void withdraw(double amount, double balance) {
        if (amount > balance) {
            throw new InsufficientBalanceException(
                    "余额不足！需要：" + amount + "，余额：" + balance,
                    balance
            );
        }
        System.out.println("取款成功，剩余：" + (balance - amount));
    }
}