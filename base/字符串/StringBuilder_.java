public class StringBuilder_ {
    public static void main(String[] args) {
        // ========== String 的问题：不可变性 ==========
        System.out.println("========== String 的问题 ==========");

        String str = "Hello";
        System.out.println("原始地址: " + System.identityHashCode(str));

        // 每次修改都会创建新对象
        str = str + " World";
        System.out.println("修改后地址: " + System.identityHashCode(str));
        str = str + "!";
        System.out.println("再次修改: " + System.identityHashCode(str));
        System.out.println("每次修改都创建了新对象！\n");

        // ========== StringBuilder 的优势 ==========
        System.out.println("========== StringBuilder 的优势 ==========");

        StringBuilder sb = new StringBuilder("Hello");
        System.out.println("原始地址: " + System.identityHashCode(sb));

        // 在同一个对象上修改
        sb.append(" World");
        System.out.println("append后地址: " + System.identityHashCode(sb));
        sb.append("!");
        System.out.println("再次append: " + System.identityHashCode(sb));
        System.out.println("地址不变，同一个对象！");
    }
}

class CreateStringBuilder {
    public static void main(String[] args) {
        System.out.println("========== 创建 StringBuilder ==========");

        // 1. 空构造器（初始容量16）
        StringBuilder sb1 = new StringBuilder();
        System.out.println("空构造器: " + sb1 + " (容量: " + sb1.capacity() + ")");

        // 2. 指定初始容量
        StringBuilder sb2 = new StringBuilder(50);
        System.out.println("指定容量50: " + sb2 + " (容量: " + sb2.capacity() + ")");

        // 3. 从字符串创建
        StringBuilder sb3 = new StringBuilder("Hello");
        System.out.println("从字符串创建: " + sb3 + " (容量: " + sb3.capacity() + ")");
        // 容量 = 字符串长度 + 16 = 5 + 16 = 21

        // 4. 从 CharSequence 创建
        CharSequence cs = "Java";
        StringBuilder sb4 = new StringBuilder(cs);
        System.out.println("从CharSequence创建: " + sb4);
    }
}

class AppendMethods {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();

        System.out.println("========== append() - 追加内容 ==========");

        // 追加各种类型
        sb.append("Hello");           // String
        sb.append(" ");               // 空格
        sb.append(123);               // int
        sb.append(" ");               // 空格
        sb.append(3.14);              // double
        sb.append(" ");               // 空格
        sb.append(true);              // boolean
        sb.append(' ');               // char
        sb.append(new char[]{'J', 'a', 'v', 'a'});  // char数组

        System.out.println("结果: " + sb);
        // base.输出: Hello 123 3.14 true Java

        // 链式调用
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Hello")
                .append(" ")
                .append("World")
                .append("!")
                .append(2024);
        System.out.println("链式调用: " + sb2);
        // base.输出: Hello World!2024
    }
}

class InsertMethods {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("Hello World");

        System.out.println("========== insert() - 插入内容 ==========");
        System.out.println("原始: " + sb);

        // 在指定位置插入
        sb.insert(5, " Beautiful");
        System.out.println("插入字符串: " + sb);
        // base.输出: Hello Beautiful World

        sb.insert(0, "Hi, ");
        System.out.println("插入到开头: " + sb);
        // base.输出: Hi, Hello Beautiful World

        sb.insert(sb.length(), "!!!");
        System.out.println("插入到末尾: " + sb);
        // base.输出: Hi, Hello Beautiful World!!!

        // 插入各种类型
        StringBuilder sb2 = new StringBuilder("abc");
        sb2.insert(1, 123);
        System.out.println("插入数字: " + sb2);  // a123bc
    }
}

class DeleteMethods {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("Hello Beautiful World");

        System.out.println("========== delete() - 删除内容 ==========");
        System.out.println("原始: " + sb);

        // 删除指定范围 [start, end)
        sb.delete(5, 15);
        System.out.println("删除 'Beautiful ': " + sb);
        // base.输出: Hello World

        // 删除单个字符
        sb.deleteCharAt(5);
        System.out.println("删除位置5的字符: " + sb);
        // base.输出: HelloWorld

        // 清空所有内容
        sb.delete(0, sb.length());
        System.out.println("清空后: '" + sb + "' (长度: " + sb.length() + ")");
    }
}

class ReplaceMethods {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("Hello World");

        System.out.println("========== replace() - 替换内容 ==========");
        System.out.println("原始: " + sb);

        // 替换指定范围 [start, end)
        sb.replace(6, 11, "Java");
        System.out.println("替换 'World' 为 'Java': " + sb);
        // base.输出: Hello Java

        // 替换单个字符
        sb.replace(0, 1, "h");
        System.out.println("替换首字母: " + sb);
        // base.输出: hello Java
    }
}

class ReverseMethod {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("Hello World");

        System.out.println("========== reverse() - 反转 ==========");
        System.out.println("原始: " + sb);

        sb.reverse();
        System.out.println("反转: " + sb);
        // base.输出: dlroW olleH

        // 再次反转恢复
        sb.reverse();
        System.out.println("再次反转: " + sb);
        // base.输出: Hello World

        // 实用：判断回文
        String word = "racecar";
        StringBuilder sb2 = new StringBuilder(word);
        boolean isPalindrome = sb2.reverse().toString().equals(word);
        System.out.println("\n'" + word + "' 是回文吗? " + isPalindrome);  // true
    }
}

class QueryMethods {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("Hello World");

        System.out.println("========== 查询方法 ==========");

        // 长度
        System.out.println("长度: " + sb.length());  // 11

        // 容量
        System.out.println("容量: " + sb.capacity());  // 27 (11 + 16)

        // 获取字符
        System.out.println("位置0的字符: " + sb.charAt(0));  // H

        // 查找位置
        System.out.println("'l' 第一次出现: " + sb.indexOf("l"));  // 2
        System.out.println("'l' 最后一次出现: " + sb.lastIndexOf("l"));  // 9
        System.out.println("'World' 的位置: " + sb.indexOf("World"));  // 6
        System.out.println("不存在的子串: " + sb.indexOf("XYZ"));  // -1

        // 截取子串
        System.out.println("substring(6): " + sb.substring(6));  // World
        System.out.println("substring(0, 5): " + sb.substring(0, 5));  // Hello
    }
}

class CapacityManagement {
    public static void main(String[] args) {
        System.out.println("========== 容量管理 ==========");

        StringBuilder sb = new StringBuilder(10);
        System.out.println("初始容量: " + sb.capacity());  // 10

        // 追加内容
        sb.append("Hello");
        System.out.println("append后容量: " + sb.capacity());  // 10 (未超)

        sb.append(" World Java");
        System.out.println("追加更多内容: " + sb);
        System.out.println("容量自动扩容: " + sb.capacity());  // 22

        // 手动确保容量
        sb.ensureCapacity(50);
        System.out.println("ensureCapacity(50)后: " + sb.capacity());  // 50

        // 压缩容量到当前长度
        sb.trimToSize();
        System.out.println("trimToSize后: " + sb.capacity());  // 17 (实际长度)
        System.out.println("实际长度: " + sb.length());  // 17
    }
}

// 性能对比
class PerformanceCompare {
    public static void main(String[] args) {
        int iterations = 100000;

        System.out.println("========== 性能对比 ==========");
        System.out.println("测试: 拼接 " + iterations + " 次\n");

        // ========== String 拼接 ==========
        long start1 = System.currentTimeMillis();
        String str = "";
        for (int i = 0; i < iterations; i++) {
            str += "a";  // 每次创建新对象
        }
        long end1 = System.currentTimeMillis();
        System.out.println("String 拼接: " + (end1 - start1) + " ms");

        // ========== StringBuilder ==========
        long start2 = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < iterations; i++) {
            sb.append("a");  // 修改同一个对象
        }
        long end2 = System.currentTimeMillis();
        System.out.println("StringBuilder: " + (end2 - start2) + " ms");

        // ========== StringBuffer (线程安全) ==========
        long start3 = System.currentTimeMillis();
        StringBuffer sbf = new StringBuffer();
        for (int i = 0; i < iterations; i++) {
            sbf.append("a");
        }
        long end3 = System.currentTimeMillis();
        System.out.println("StringBuffer:  " + (end3 - start3) + " ms");

        System.out.println("\n结论: StringBuilder 最快");
        System.out.println("String 最慢 (因为创建大量临时对象)");
    }
}