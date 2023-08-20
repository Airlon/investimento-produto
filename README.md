
# API de Produtos

Bem-vindo à documentação da API de produtos. Esta API permite a consulta e criação de produtos, 
como adicional foi implementado  a publicação do evento na fila PRODUTOS pelo RabbitMQ.

## baixar imagem postgress e RabbitMQ

Para baixar a imagem do postgress e rabbitMQ é necessário acessar o diretório da pasta "database-docker/"
pelo Git e executar o comando abaixo:

docker-compose up -d

## Banco de dados

Em seguida pode utilizar uma ferramenta para acessar o banco de dados, exemplo: DBeaver

### Configurações de acesso ao banco de dados Postgress:
jdbc:postgresql://0.0.0.0:5432/investimento
username: postgresql
password: 123

**Obs**: Você encontra essas informacões também no arquivo application.yml

Após isso você executa o sql abaixo para criar a tabela dentro do database "investimento":

#### Primeira query:

create table produtos(
id bigserial not null,
nome varchar(50) not null,
descricao varchar(100) not null,
preco decimal(9,2) not null,
quantidade int not null,
dt_cadastro timestamp not null
);

#### Segunda query:

alter table produtos add constraint produtos_pk primary key (id);

**Obs**: Você encontra essas informacões tambémna pasta "database-docker" arquivo "criar-tabela.sql"


## Acesso ao RabbitMQ

**URL**
http://localhost:15672/#/
username: admin
password: 123456

Está sendo usado a exchange padrão do RabbitMQ:
Exchange: amq.direct

## Endpoints 

Executar as APIS por alguma ferramenta, por exemplo Postman.

### Listar Produtos

Retorna uma lista de todos os produtos cadastrados

**URL**
GET http://localhost:8080/produtos

### Criação de Produtos

Cria um novo produto


**URL**
POST http://localhost:8080/produtos

**EXEMPLO JSON**
{
"nome": "Cadeira",
"descricao": "Cadeira ergonômica para escritório",
"preco": 149.50,
"quantidade": 20,
"dtCadastro": "2023-08-18T16:15:00Z"
}

## Dockerfile

Executar os comandos abaixo:

docker build -t investimento .
docker images
docker run -p 8080:8080 investimento


## Jenkins

