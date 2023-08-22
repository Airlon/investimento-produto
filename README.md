
# API de Produtos

Bem-vindo à documentação da API de produtos. Esta API permite a consulta e criação de produtos, 
como adicional foi implementado  a publicação do evento na fila PRODUTOS pelo RabbitMQ.

## baixar imagem postgress, RabbitMQ e Jenkins

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

http://localhost:8081/

Após acessar o jenkins com o usuario e senha criado, você precisa seguir os passos abaixo:

- criar o projeto Ex: "investimento_produto", configurar o repositório do github e suas credenciais. 

- Você tem que instalar 2 plugins: Docker plugine Docker Pipeline direto no Jenkins caminho:
  Jenkins > Gerenciar Jenkins > Gerenciar plug-ins > Disponível

- Clicar em construir agora.

## Terraform com AWS

No projeto foi criado a pasta terraform,  simulando as configurações para o projeto.

### Arquivo main.tf

1 - VPC (Virtual Private Cloud):
Define uma VPC com o bloco CIDR "10.0.0.0/16".
Ativa o suporte a DNS para resolução de nomes.
Cria uma tag para identificação.

2 - Subnet Padrão:
Define uma subnet na VPC com o bloco CIDR "10.0.0.0/24".
Localizada na zona de disponibilidade "us-west-2a".
Habilita IP público para instâncias lançadas nessa subnet.
Cria uma tag para identificação.

3 - Internet Gateway (IGW):
Cria um Internet Gateway e associa-o à VPC.
Permite que recursos dentro da VPC acessem a Internet.
Cria uma tag para identificação.

4- Tabela de Roteamento e Rota Padrão:
Cria uma tabela de roteamento associada à VPC.
Define uma rota padrão que encaminha todo o tráfego para o IGW.
Permite que instâncias na VPC acessem a Internet.

5 - Associação da Subnet à Tabela de Roteamento:
Associa a subnet criada à tabela de roteamento.
Garante que as instâncias na subnet usem as rotas corretas.

6- Instância EC2:
Cria uma instância EC2 do tipo "t2.micro".
Define a chave SSH para acesso à instância.
Define o grupo de segurança da VPC para a instância.
Coloca a instância na subnet criada.
Especifica a AMI a ser usada (imagem do sistema operacional).
Define o tamanho do disco raiz como 8 GB.
Cria uma tag para identificação.

### Arquivo provider.tf

Neste arquivo, foi configurando o provedor AWS para o Terraform. O provedor é uma forma de se comunicar com a plataforma de nuvem e criar/gerenciar os recursos nela. 
Está sendo usado o provedor hashicorp/aws na versão ~> 5.0 que configura algumas informações de autenticação e região.

### Arquivo security.tf

Este arquivo contém a definição de um grupo de segurança (security group) na AWS. Grupos de segurança são utilizados para controlar as regras de tráfego de entrada e saída para as instâncias EC2. 
Define algumas regras de tráfego, como permitir tráfego de saída para qualquer destino (egress), permitir tráfego SSH de entrada (ingress) e permitir tráfego HTTPS de entrada (ingress). 
Isso permite que as instâncias possam se comunicar com a Internet e receber tráfego SSH e HTTPS.

### Arquivo datasource.tf

Estou um datasource para buscar uma imagem AMI da AWS. O datasource aws_ami permite que eu encontre informações sobre uma imagem AMI específica (Amazon Machine Image) que será usada para criar instâncias EC2. 
Também estou buscando a imagem mais recente do proprietário com ID 000995655648654(Exemplo) e filtrando pelo nome da imagem.

