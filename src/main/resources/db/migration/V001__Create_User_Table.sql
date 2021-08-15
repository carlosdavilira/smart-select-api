CREATE TABLE usuario (
codigo BIGINT(20) primary key auto_increment,
usuario varchar(50) not null,
senha varchar(12) not null,
tipo VARCHAR(10)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


