insert into fabricante(nome, descricao) values ('Fiat', 'Fiat Itália');
insert into fabricante(nome, descricao) values ('Renault', 'Renault França');
insert into fabricante(nome, descricao) values ('Ford', 'Ford Estados Unidos');

insert into modelo(descricao, id_fabricante, categoria) values('Uno Vivace', 1, 'HATCH');
insert into modelo(descricao, id_fabricante, categoria) values('Renault Clio', 2, 'HATCH');
insert into modelo(descricao, id_fabricante, categoria) values('Ford Ecosport', 3, 'UTILITARIO');

insert into carro(placa, chassi, valor_diaria, id_modelo) values('NPY-5678', 'JJK6577LJOOI94', '80.00', 1);
insert into carro(placa, chassi, valor_diaria, id_modelo) values('JOY-6758', 'PIU6577LJ009OR', '60.00', 2);
insert into carro(placa, chassi, valor_diaria, id_modelo) values('KOI-7988', 'UYT6577LJKKYR6', '110.00', 3);

insert into motorista(nome, cpf, cnh, sexo) values('João das Neves', '054.876.567-45','4567776677','MASCULINO');
insert into motorista(nome, cpf, cnh, sexo) values('Maria das Dores', '054.876.567-45','4567776677','FEMININO');
insert into motorista(nome, cpf, cnh, sexo) values('Joquim dos Santos', '054.876.567-45','4567776677','MASCULINO');
