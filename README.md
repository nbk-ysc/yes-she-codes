# Yes, she-codes!

### Uau! É funcional!!! 

Projeto bancário para mergulhar no mundo da **programação funcional** com Clojure + Datomic!


Desenvolvido por Vitória Galli.


### Desenvolvimento

O repositório está dividido em duas partes:

1. STUDY

Separado por semana, esse espaço é destinado ao estudo abordado em cada período. O propósito é de praticar individualmente as tarefas propostas.

2. PROJECT

Com o avanço do bootcamp, foi identificada a necessidade de integrar os escopos e treinar práticas de design de software com baixo acoplamento.
Dessa forma, o diretório de projeto vem para aplicar padrões focados na [Arquitetura Hexagonal](https://alistair.cockburn.us/hexagonal-architecture/).



### 1. STUDY

##### WEEK 1

- Clojure: Introdução à programação funcional & Coleções no dia a dia
- Trabalha com estruturas de dados para representar e manipular dados das estruturas Cliente, Cartão e Compra;
- Realiza listagem de compras com critérios diferentes;
- Agrega dados para relatório de gastos por categoria, e por mês;
- Trabalha com biblioteca de datas nativa no Java;
- Lê e processa arquivos CSV.

##### WEEK 2

- Clojure: Mutabilidade com átomos e refs & Record, protocol e multi method
- Trabalha com records para representar e manipular dados das estruturas Cliente, Cartão e Compra;
- Usa polimorfismo com uso de multi methods;
- Manipula átomos para processamento concorrente.

##### WEEK 3

- Clojure: Schemas & Explorando testes
- Trabalha a validação de entradas e saídas de funções por meio de schemas;
- Cria schemas de validações próprios;
- Criar testes automatizados em Clojure.

##### WEEK 4
- Datomic: um banco cronológico & Queries
- Apresenta a finalidade e as vantagens do banco de dados imutável e cronológico Datomic;
- Insere, "atualiza" e "remove" datoms;
- Apresenta boas práticas para modelar estruturas armazenadas no Datomic;
- Realiza consultas avançadas com filtros, relacionamentos e agregações no Datomic.



### 2. PROJECT

Estrutura adotada:

##### DIPLOMAT
- Camada reponsável por interagir com os recursos externos
- Nesse caso a única fonte de informação externa é a leitura dos arquivos csv

##### WIRE
- Contém os schemas das informações que estão chegando e saindo do nosso serviço
- No projeto, o fluxo é feito a partir de leitura e escrita em arquivos csv
- Dados de entrada devem ser do tipo loose schemas, isso é, caso o arquivo sofra alguma alteração e venha com informação a mais do que precisamos, não deve interferir na solução.

##### MODEL
- Representa o modelo das entidades do seu serviço
- Nela é definido o schema que será utilizado dentro da sua lógica de negócio
- Oferece documentação do que é esperado
- Nesse caso temos duas representações de Modelo diferente para cada entidade (Entidade e EntidadeComComponentes).
 1. Entidade, para trabalhar com mutabilidades de átomos com os dados provenientes do csv.
 2. EntidadeComComponentes, para trabalhar com os resultantes do banco de dados, onde foi possível fazer a relação entre eles.
- Lembrando que o projeto é apenas para fins de aprendizado, por isso foi adotada essa solução.

##### ADAPTER
- Responável por transaformar os dados entre as camadas externas e de domínio
- Lidam com diferentes modelos provenientes de diversas fontes (csv, datomic) a fim de traduzi-los para o modelo do nosso domínio.
- Usado em conjunto com schemas pode validar o contrato entre dois serviços.
- No projeto fizemos uso de alguns adapters como csv->model, model->csv, model->datomic, datomic->model.

##### LOGIC
- Parte pura da camada de domínio, contendo apenas regras de negócio.
- Essa camada deve estar desacoplada de qualquer infraestrutura.

##### CONTROLLER
- A parte inpura da camada de domínio
- Faz a ponte entre a lógica e a infraestrtura 
- Coordena o fluxo de dados entre as outras camadas.

##### DB
- Camada responsável pelas configurações do datomic
- Nela estão as funções que vão se conectar diretamente com o banco
- Seja para fazer uma query ou inserir/atualizar alguma informação no banco

##### TEST

- Alguma cobertura de testes unitários foi desenvolvida nesse projeto
- Ele segue a mesma estrutura adotada no fonte


### REQUISITOS

- [Leiningen](https://leiningen.org)
- [Datomic](https://www.datomic.com/get-datomic.html)

```bash
$ lein repl
$ ...
$ lein test
```

<details>
  <summary>Informações Extras</summary>

Modelo Aruitetura Hexagonal

![hexagonal-arch](https://user-images.githubusercontent.com/56961723/171958124-67da033c-12f3-44dd-a8ce-831fe815cf84.png)



Relacionamento entre as entidades.

![Entidades](https://user-images.githubusercontent.com/56961723/171956173-719cff59-292d-4aff-9b2d-a299e5784283.jpg)

