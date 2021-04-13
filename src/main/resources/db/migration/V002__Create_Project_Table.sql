CREATE TABLE projeto (
codigo BIGINT(20) primary key auto_increment,
descricao LONGTEXT not null,
habilidades LONGTEXT not null,
tempos LONGTEXT not null
) ENGINE=InnoDB DEFAULT CHARSET=utf8;