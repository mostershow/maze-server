# maze-server

## 在线多人迷宫服务端

技术栈：
Kotlin + Gradle + Netty + Protobuf
目前无数据存储

### 业务概述

* 长连接
    1. channel 连接
      连接鉴权(not imp)&连接记录
    2. channel handler 
      将每一个长连接中处理的业务，独立为handler，初始化channel时注入
    3. channel 管理
       连接鉴权后，对channel进行自定义管理，设置不同属性或分组
    4. 消息编解码
       消息自定义编解码方式，目前支持 Json & Protobuf
       

* 迷宫
    1. 迷宫生成
    2. 迷宫记录

* 小人
    1. 小人移动
    2. 小人状态

### 目录结构

```txt
├── config/                   // CORS & Web
├── message/                  // Message Struct
├── protocol/                 // Decoder & Encoder
├── server/                   // Server & Biz
│   ├── controller/            // http controller
│   ├── entity/               // Maze & Walker
│   ├── handler/               // Netty message handler
│   ├── message_bus/            // Message sync (not implemented)
│   ├── service/              // Biz
│   ├── session/              // Session & Group(Room) 
│   ├── ChatServer            // NettyServer start          
│   └── ChatServerInitializer  // initChannelHandler
├── util/                     // util pack
│   ├── JWT      
│   └── Logger
└── Application.kt               // 程序入口
```

