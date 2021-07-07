CREATE TABLE colaborador (
codigo BIGINT(20) primary key auto_increment,
nome LONGTEXT not null,
projeto_atual LONGTEXT not null,
habilidades LONGTEXT not null,
gerente_atual VARCHAR(50) null,
codigo_usuario BIGINT(20) null,
FOREIGN KEY(codigo_usuario) REFERENCES usuario(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE experiencias (
codigo BIGINT(20) primary key auto_increment,
nome_empresa LONGTEXT not null,
cargo LONGTEXT not null,
atividades_realizadas LONGTEXT not null,
inicio VARCHAR(10) null,
fim VARCHAR(10) null,
codigo_colaborador BIGINT(20) null,
FOREIGN KEY(codigo_colaborador) REFERENCES colaborador(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;