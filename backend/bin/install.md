数据可视化分析工具(Windows 环境下安装, 数据库、JDK 安装省略)

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

2. 修改数据库等配置（conf/dataease-prod.properties）
调整里面的服务地址、数据库连接、用户名、密码等

3. 插件配置（conf/dataease-prod.properties）
将插件文件 plugins.zip 解压到下面配置的文件夹下
配置下面的地址路径(示例)
dataease.plugin.dir=D:/dataease/plugins/

示例文件夹结构参考:
D:\dataease\plugins
				├─default
				└─thridpart

4. 地图配置（conf/dataease-prod.properties）
将地图文件 full.7z 解压到下面配置的文件夹下
配置下面的地址路径(示例)
geo.custom.rootpath=file:///D:/dataease/data/custom/
geo.rootpath=file:///D:/dataease/data/custom/

示例文件夹结构参考:
D:\dataease\data\custom
					└─full
						├─000
						└─156

5. 启动程序
点击 bin/startup.bat 来启动

6. 登录系统
http://ip:8081
admin/dataease


7. 自定义驱动（可选）
登录系统后，数据源设置里面可以自定义驱动，按需要从 drivers.7z 里面获取需要的驱动 jar 上传即可。

** 注意：安装在 D 盘情况下的目录结构参考如下： **
D:\dataease
	├─bin
	├─conf
	└─lib
