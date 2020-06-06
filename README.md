# Projeto BOP
* Arquivos de treinamento para o projeto BOP *

O treinamento foi composto de diversas tarefas para praticar conceitos que poderiam ser utilizados no projeto.  
Quando essas tarefas estavam sendo feitos, ainda estava ocorrendo o estudo do domínio e não sabíamos exatamente
do que iríamos precisar, portanto esse repositório está bem simples e incompleto. Boa parte do que encontra-se aqui acabou não sendo aproveitado apos a evolução do projeto.


* 1ª tarefa
    - Realizar um acesso ao banco de dados através do driver JDBC e exibir informações desse banco na página JSP usando o servidor Tomcat

    - A classe NotasDAO, através do método listarNotas, realiza o acesso ao banco, a conexão é feita pela classe DBConfig, através do método getConnection, a um banco mysql.

* 2ª tarefa
    - Criar uma biblioteca (Jar) que busca um dado disponível na web, por exemplo, quantidade de citações de uma palavra a um usuário do Github (API usada: https://api.github.com/search/code?q=java+user:carlosbazilio).
Acesar um método da classe da biblioteca no JSP: UtilidadeGitgub.getFrequencia("carlosbazilio", "java")

    - A biblioteca requestapi.jar, através do método doRequest da classe Requester, realiza um acesso através da classe HttpsURLConnection à API, que retorna um Json. A partir desse Json, retiramos a informação desejada.

* 3ª tarefa
  - Registrar a biblioteca criada no item anterior usando o método forName, de forma que não dê erro caso a biblioteca não esteja no diretório lib do Tomcat 
Criar outra biblioteca, que acesse outra API na web. Estas bibliotecas devem funcionar como plugins, ou seja, caso a biblioteca exista no servidor, o seu conteúdo é exibido na página web. Caso contrário, nada aparece (não dá erro) 
(Ver opções de API em: https://www.programmableweb.com/apis)
Alterar a implementação utilizando java.util.ServiceLoader, de forma que a manipulação das bibliotecas possa ocorrer de maneira uniforme e mais abstrata

  - O método loadAPI da classe DBConfig chama o método da classe Requester após importá-la pelo Class.forName.
Para essa tarefa, uma segunda biblioteca, githubrequester.jar, foi criada para adequar-se aos requisitos do uso da classe ServiceLoader. Para realizar a tarefa, foi utilizada a API Coindesk (https://api.coindesk.com/v1/bpi/currentprice/BRL.json) para retornar o valor da moeda Bitcoin em reais no momento da requisição.
A classe DBConfig, através do método loadAPI2, realiza essa função, adicionando os resultados das chamadas em uma lista de Strings.

* 4a Tarefa:
  - Implementar na biblioteca algum método que acesse a internet de tempos em tempos e busque alguma informação que altere com frequência, como cotação diária do dólar, temperatura atual numa dada cidade, ou alguma outra coisa do tipo que altere com frequência maior 
  - Usar AJAX para atualização assíncrona da página, baseada no retorno deste novo método
  - Consultar o Github para ver como realizar chamadas JS para esta tarefa

  - Foi utilizado jquery para fazer a requisição no javascript da API e React, usando a função setInterval, para definir um intervalo de tempo em que as APIs seriam requisitadas.
