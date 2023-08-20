create table produtos(
id bigserial not null,
nome varchar(50) not null,
descricao varchar(100) not null,
preco decimal(9,2) not null,
quantidade int not null,
dt_cadastro timestamp not null
);
alter table produtos add constraint produtos_pk primary key (id);