# QQ-Chatroom

基于Swing和Java Socket搭建的群聊系统，界面仿照QQ2021进行设计，使用Maven进行项目管理

#### 已完成

- [x] 基本功能：多人聊天消息发送和消息同步、用户登录和注册等功能
- [x] 基本界面：包括登录界面、注册界面、聊天界面
- [x] UI设计：登陆界面已经完成UI设计，和QQ还是比较像的

#### TODO

由于临近期末而且大作业太多，项目先告一段落，之后可以补充的功能有：

- [ ] 其他界面的UI
- [ ] 提示框等组件的UI
- [ ] 加载历史消息、发送emoji、人物头像等

#### Quick Start

- 使用resources中的建库sql(MySQL)完成数据库建立
- 更改Server[./src/main/server/Server.java]中的数据库用户和密码（注意数据库版本和对应驱动）
- 先运行Server，然后可以并行的运行Client
