Iterable（接口）
↑
Collection（接口）
├── List（接口）──────── 有序、可重复、有索引
│   ├── ArrayList（类）⭐ 最常用
│   ├── LinkedList（类）⭐ 常用（也实现了 Deque）
│   ├── Vector（类）【旧】
│   └── Stack（类）【旧】
│
├── Set（接口）───────── 无序、不可重复
│   ├── HashSet（类）⭐ 最常用
│   ├── LinkedHashSet（类）── 保持插入顺序
│   └── TreeSet（类）────── 自动排序
│
└── Queue（接口）──────── 队列（FIFO）
    ├── Deque（接口）──── 双端队列
    │   ├── ArrayDeque（类）⭐ 推荐
    │   └── LinkedList（类）← 上面已列出
    └── PriorityQueue（类）── 优先级队列