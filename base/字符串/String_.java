public class String_ {
    public static void main(java.lang.String[] args) {
        java.lang.String str = "Hello World";
        java.lang.String str2 = "  Hello  World  ";
        java.lang.String str3 = "Hello";
        java.lang.String str4 = "world";

        // ==================== 一、字符串长度和判断 ====================
        System.out.println("\n========== 一、字符串长度和判断 ==========");
        // length()：返回字符串长度
        System.out.println("length: " + str.length());
        // isEmpty()：判断是否为空字符串
        System.out.println("isEmpty: \"" + "\".isEmpty() = " + "".isEmpty());
        // isBlank()：判断是否为空白字符串（Java 11+）
        System.out.println("isBlank: \"   \".isBlank() = " + "   ".isBlank());

        // ==================== 二、比较 ====================
        System.out.println("\n========== 二、比较 ==========");
        // equals()：比较内容是否相等
        System.out.println("equals: " + str.equals("Hello World"));
        // equalsIgnoreCase()：忽略大小写比较
        System.out.println("equalsIgnoreCase: " + str.equalsIgnoreCase("hello world"));
        // compareTo()：按字典顺序比较
        System.out.println("compareTo: " + str3.compareTo(str4));
        // compareToIgnoreCase()：忽略大小写比较
        System.out.println("compareToIgnoreCase: " + "Hello".compareToIgnoreCase("hello"));

        // ==================== 三、查找 ====================
        System.out.println("\n========== 三、查找 ==========");
        // charAt()：获取指定位置的字符
        System.out.println("charAt(0): " + str.charAt(0));
        // indexOf()：查找字符或子串第一次出现的位置
        System.out.println("indexOf('l'): " + str.indexOf('l'));
        System.out.println("indexOf(\"World\"): " + str.indexOf("World"));
        // lastIndexOf()：查找字符或子串最后一次出现的位置
        System.out.println("lastIndexOf('l'): " + str.lastIndexOf('l'));
        // contains()：判断是否包含子串
        System.out.println("contains(\"World\"): " + str.contains("World"));
        // startsWith()：判断是否以指定前缀开头
        System.out.println("startsWith(\"Hello\"): " + str.startsWith("Hello"));
        // endsWith()：判断是否以指定后缀结尾
        System.out.println("endsWith(\"World\"): " + str.endsWith("World"));

        // ==================== 四、截取 ====================
        System.out.println("\n========== 四、截取 ==========");
        // substring()：截取子串
        System.out.println("substring(6): " + str.substring(6));
        System.out.println("substring(0, 5): " + str.substring(0, 5));

        // ==================== 五、转换 ====================
        System.out.println("\n========== 五、转换 ==========");
        // toLowerCase()：转小写
        System.out.println("toLowerCase: " + str.toLowerCase());
        // toUpperCase()：转大写
        System.out.println("toUpperCase: " + str.toUpperCase());
        // trim()：去除首尾空格
        System.out.println("trim: \"" + str2.trim() + "\"");
        // strip()：去除首尾空白字符（Java 11+）
        System.out.println("strip: \"" + str2.strip() + "\"");
        // stripLeading()：去除前导空白
        System.out.println("stripLeading: \"" + str2.stripLeading() + "\"");
        // stripTrailing()：去除尾部空白
        System.out.println("stripTrailing: \"" + str2.stripTrailing() + "\"");

        // ==================== 六、替换 ====================
        System.out.println("\n========== 六、替换 ==========");
        // replace()：替换所有字符
        System.out.println("replace('l', 'L'): " + str.replace('l', 'L'));
        // replaceAll()：使用正则表达式替换
        System.out.println("replaceAll(\"l\", \"L\"): " + str.replaceAll("l", "L"));
        // replaceFirst()：替换第一个匹配的
        System.out.println("replaceFirst(\"l\", \"L\"): " + str.replaceFirst("l", "L"));

        // ==================== 七、分割和拼接 ====================
        System.out.println("\n========== 七、分割和拼接 ==========");
        // split()：按分隔符分割
        java.lang.String[] parts = "apple,banana,cherry".split(",");
        System.out.println("split: " + java.util.Arrays.toString(parts));
        // join()：拼接字符串（静态方法）
        System.out.println("join: " + java.lang.String.join("-", "a", "b", "c"));
        // concat()：拼接字符串
        System.out.println("concat: " + str.concat("!!!"));
        // repeat()：重复字符串（Java 11+）
        System.out.println("repeat(3): " + "Hi".repeat(3));

        // ==================== 八、格式化和转换 ====================
        System.out.println("\n========== 八、格式化和转换 ==========");
        // format()：格式化字符串
        System.out.println("format: " + java.lang.String.format("姓名：%s，年龄：%d", "张三", 25));
        // valueOf()：将其他类型转为字符串
        System.out.println("valueOf(123): " + java.lang.String.valueOf(123));
        System.out.println("valueOf(true): " + java.lang.String.valueOf(true));
        // toCharArray()：转为字符数组
        char[] chars = str.toCharArray();
        System.out.println("toCharArray: " + java.util.Arrays.toString(chars));
        // getBytes()：转为字节数组
        byte[] bytes = str.getBytes();
        System.out.println("getBytes: " + java.util.Arrays.toString(bytes));

        // ==================== 九、判断内容 ====================
        System.out.println("\n========== 九、判断内容 ==========");
        // matches()：正则匹配
        System.out.println("matches(\"\\\\d+\"): \"123\".matches(\"\\\\d+\") = " + "123".matches("\\d+"));
        // isBlank() 已经演示过
        // isEmpty() 已经演示过

        // ==================== 十、其他方法 ====================
        System.out.println("\n========== 十、其他方法 ==========");
        // intern()：将字符串放入常量池
        java.lang.String s1 = new java.lang.String("abc");
        java.lang.String s2 = s1.intern();
        System.out.println("intern: " + (s2 == "abc"));
        // lines()：按行分割（Java 11+）
        java.lang.String multiLine = "line1\nline2\nline3";
        System.out.println("lines: " + multiLine.lines().toArray());
        // indent()：添加缩进（Java 12+）
        System.out.println("indent:\n" + "Hello".indent(4));
        // transform()：转换（Java 12+）
        System.out.println("transform: " + "123".transform(s -> Integer.parseInt(s) * 2));
    }
}