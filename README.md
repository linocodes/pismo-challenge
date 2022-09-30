# PISMO backend test

O projeto foi construído utilizando microservices e cada service esta em um container docker.

## Utilização de banco de dados

Utilizei o banco de dados mysql com as seguintes credenciais

 - user: root
 - password: root

## Sobre os portas

|  Service | Context |  Docker Port | Local Port |
|---|---|---|---|
| Account | /api-account | 18090 | 8090 |
| Transaction | /api-transaction | 18091 | 8091 |

## Documentacao Swagger

|  Service | Docker Swagger | Local Swagger                                                |
|---|----|--------------------------------------------------------------|
| Account |  http://localhost:18090/api-account/swagger-ui/index.html | http://localhost:18090/api-account/swagger-ui/index.html     |   
| Transaction |  http://localhost:18091/api-transaction/swagger-ui/index.html | http://localhost:8091/api-transaction/swagger-ui/index.html  |

# Api Account

A api-account  é responsável por cadastrar e retornar dados do cliente

## Endpoint cadastro

Cadastro um novo cliente

```
curl -X 'POST' \
'http://localhost:8090/api-account/v1/accounts' \
-H 'accept: */*' \
-H 'Content-Type: application/json' \
-d '{
"document_number": "01150456600"
}'
```

## Endpoint consulta

Pesquisa um cliente

```
curl -X 'GET' \
  'http://localhost:18090/api-account/v1/accounts/5' \
  -H 'accept: */*'
```

Erro caso não exista o registro pesquisado

Error: response status is 404

```
{
"time": "2022-09-28 16:17:41:210 +0000",
"message": "Account id (7) not found",
"statusCode": "NOT_FOUND"
}
```

# Api Transaction

Realiza as transações para os clientes cadastrados.

## Endpoint transaction

Cria novas transaçoes para o cliente.


```
curl -X 'POST' \
  'http://localhost:18091/api-transaction/v1/transactions' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "account_id": 1,
  "operation_type_id": 4,
  "amount": 5000
}'
```

# Deploy da aplicação local

Siga os passos para realizar o deploy da aplicação local

Faça o clone para a ide usando o repositório

```
git clone https://github.com/linocodes/pismo-back-test
```

Rode o mysql como container docker 

```
docker run -p 3306:3306 --name pismodb-local -e MYSQL_ROOT_PASSWORD=root -d mysql:8
``` 
Crie esquema pismo-test

```
CREATE DATABASE pismo-test
```

Execute os scripts da pasta /scripts para a criacao das tabelas e insert inicial do banco.

  - schema.sql
  - data.sql


# Deploy da aplicação docker

Siga os passos para realizar o deploy da aplicação com docker

Faça o clone para a ide usando o repositório

```
git clone https://github.com/linocodes/pismo-back-test
```

Move para a raiz do projeto e rode o comando

```
mvn clean package
```

docker-compose up --build
