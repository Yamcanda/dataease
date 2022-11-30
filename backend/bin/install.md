数据可视化分析工具

安装步骤:
1. MySQL 数据库里创建 dataease 数据库，脚本如下：
CREATE DATABASE IF NOT EXISTS `dataease` default charset utf8mb4 COLLATE utf8mb4_general_ci;

SET GLOBAL group_concat_max_len=2000000;
SET SESSION group_concat_max_len=2000000;

配置文件 my.ini [mysqld] 下面追加
group_concat_max_len = 2000000;
重启数据库

查看确认参数是否生效
SELECT @@global.group_concat_max_len;

2. 修改 conf/dataease-prod.properties 配置
调整里面的服务地址、数据库连接、用户名、密码、资源路径配置等

3. 启动程序
点击 bin/startup.bat 来启动

4. 登录系统
http://ip:8081
admin/dataease


** 注意：安装在 D 盘情况下的目录结构参考如下： **
D:\dataease
	├─conf
	├─logs
	└─plugins
