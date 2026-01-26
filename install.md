# 数据可视化分析工具

    Windows 环境下安装, 数据库、JDK 安装省略。MySQL 版本要求 8.0 以上。

## 安装步骤

### 1. 创建数据库

- MySQL 数据库里创建 dataeasev2 数据库，脚本如下：

```sql
CREATE DATABASE IF NOT EXISTS `dataeasev2` default charset utf8mb4 COLLATE utf8mb4_general_ci;

SET GLOBAL group_concat_max_len=1024000;
SET SESSION group_concat_max_len=1024000;
```

- 配置文件 my.ini 参考如下：

```properties
[client]
default-character-set = utf8mb4

[mysql]
default-character-set = utf8mb4

[mysqld]
bind-address = 0.0.0.0
port = 3306

basedir = D:/db/mysql/mysql-8.0.44-winx64
datadir = D:/db/mysql8data

max_connections = 2000
character-set-server = utf8mb4
default-storage-engine = INNODB

lower_case_table_names = 1
table_open_cache = 128
max_connect_errors = 6000
innodb_file_per_table = 1
innodb_buffer_pool_size = 1G
max_allowed_packet = 64M
transaction_isolation = READ-COMMITTED
group_concat_max_len = 1024000
```

- 重启数据库
- 查看确认参数是否生效
- 特别注意参数：character_set_server、lower_case_table_names、group_concat_max_len

```sql
SELECT @@global.group_concat_max_len;
```

### 2. 数据库配置

- 创建 conf 文件夹，并在文件夹下创建 application.yml 配置文件

```yml
server:
  tomcat:
    connection-timeout: 70000
spring:
  servlet:
    multipart:
      max-file-size: 500MB
      max-request-size: 500MB
  datasource:
    url: jdbc:mysql://192.168.0.1:3306/dataease2?autoReconnect=false&useUnicode=true&characterEncoding=UTF-8&characterSetResults=UTF-8&zeroDateTimeBehavior=convertToNull&useSSL=false&allowPublicKeyRetrieval=true
    username: <db_user>
    password: <db_password>

logging:
  file:
    path: logs

dataease:
  path:
    data: data
    driver: drivers
    ehcache: cache
```

### 5. 启动程序

- Windows 下点击 startup.bat 来启动
- Linux 下: ./start.sh 来启动
- 自定义 JDK 路径如果需要修改的话，在启动脚本中进行调整即可

### 6. 登录系统

- 浏览器访问 http://ip:8100
- 默认账号：admin/DataEase@123456，登录后修改默认密码
- **注意：Windows 下安装在 D 盘情况下的目录结构参考如下：**

```bash
# 启动前
D:\dataeasev2
    ├─ conf
    ├─ data
    └─ drivers

# 启动后
D:\dataeasev2
    ├─ cache
    ├─ conf
    ├─ data
    ├─ drivers
    └─ logs
```
