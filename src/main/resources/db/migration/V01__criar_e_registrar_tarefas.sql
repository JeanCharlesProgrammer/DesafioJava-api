CREATE TABLE tarefa (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(50) NOT NULL,
    status VARCHAR(20) NOT NULL,
    descricao VARCHAR(200),
    data_criacao DATE NOT NULL,
    data_edicao DATE,
    data_remocao DATE,
    data_conclusao DATE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO tarefa (titulo, status, data_criacao ) values ('Backup Banco de dados','ABERTO','2018-06-08');
INSERT INTO tarefa (titulo, status, data_criacao ) values ('Criar crud Cliente','ABERTO','2018-12-05');
INSERT INTO tarefa (titulo, status, data_criacao ) values ('Atualizar servidor homologacao','ABERTO','2018-12-02');
INSERT INTO tarefa (titulo, status, data_criacao ) values ('Deploy producao','ABERTO','2019-02-02');



