# 블로그 프로젝트

## 테이블 스키마
```sql
-- metadb

create table user_tb(
    u_id int auto_increment primary key,
    u_username varchar(100) not null,
    u_password varchar(100) not null,
    u_email varchar(100) not null
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

create table board_tb(
    b_id int auto_increment primary key,
    b_title varchar(1000) not null,
    b_content varchar(1000) not null,
    u_id int
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

insert into user_tb(u_username, u_password, u_email)  values('ssar', '1234', 'ssar@nate.com');
insert into user_tb(u_username, u_password, u_email) 
values('cos', '1234', 'cos@nate.com');

insert into board_tb(b_title, b_content, u_id) values('제목1', '내용1', 1);
insert into board_tb(b_title, b_content, u_id) 
values('제목2', '내용2', 1);
insert into board_tb(b_title, b_content, u_id) 
values('제목3', '내용3', 2);
```