## springboot 练习

## 部署
-yum update
-yum install git
## 资料
[spring文档](https://spring.io.guides)

[springboot 集成mybatis](http://mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/index.html)

[Flayway数据库控制工具](https://flywaydb.org/)

[Bootstrap网站](https://v3.bootcss.com/components/)

[MyMatis Generateor](http://mybatis.org/generator/)
## 按照bootstrap模版创建一个导航栏

## 脚本
```sql
create table USER
(
	ID int auto_increment
		primary key,
	ACCOUNT_ID VARCHAR(100),
	NAME VARCHAR(50),
	TOKEN CHAR(36),
	GMT_CREATE BIGINT,
	GMT_MODIFIED BIGINT
);

create table publish
(
	id int auto_increment primary key,
	title varchar(50),
	description text,
	gmt_create bigint,
	gmt_modified bigint,
	creator int,
	comment_count int default 0,
	view_count int default 0,
	like_count int default 0,
	tag varchar(256)
);


```
### bash
mvn flyway:migrate
mvn -Dmybatis.generator.overwrite=true mybatis-generator:generate

