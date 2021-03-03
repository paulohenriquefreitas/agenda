
## Tecnologias utilizadas
### Back End
##### Foi criada uma Api  Rest, utilizando a linguagem Java 8 com o Framework SpringBoot 2.0 . Por se tratar de uma linguagem robusta e muito utilizada no mercado de TI, o autor do projeto decidiu utilizá-la para o backend.
O desenvolvimento seguiu o padrão MVC, utilizando os seguintes recursos:

* [Maven](https://maven.apache.org/) e suas features para gestão de dependências e start do projeto.
* Para a persistência foi utilizada base de dados relacional [Mysql](https://www.mysql.com/).
* Para testes foram utilizados [Junit 4 e 5](https://junit.org/junit5/).

#### Como rodar a aplicação(Primeira opção)

#### 01 Clone
Clonar a aplicação do repositório https://github.com/paulohenriquefreitas/agenda.git ou git@github.com:paulohenriquefreitas/agenda.git
#### 02 Rodar a aplicação
Entre na raiz do projeto e digite o comando **make run** e para rodar script de sql digite **make setup**

#### Como rodar a aplicação(Segunda opção)

Para criar uma base de dados mysql é preciso rodar o seguinte comando:
##### docker-compose -f docker-compose.yml up -d

Para rodar a aplicação em um ambiente isolado com Virtual Machine ou mesmo no sistema operacional, é utilizado o recurso do Docker. Para subir uma instância do docker é preciso rodar os seguintes comandos:
##### docker build . --tag demo 
##### docker run -it -p8080:8080 demo:latest 

#### 04 Testes
Rodar os testes digite o comando  **mvn test** na raiz do projeto.

#### 03 Postman Collections
Na rais do projeto está o arquivo **Desafio.json** com os requests usados para testar o projetos pelo postman.

## Dúvidas

Quaisquer dúvidas entre em contato pelo email paulohfreitas44@gmail.com