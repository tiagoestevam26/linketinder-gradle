
###Tiago Estevam Siqueira Costa
# Linketinder

O **Linketinder** é um sistema que facilita o recrutamento de talentos, combinando competências de candidatos e requisitos das empresas.
Este projeto faz parte do aprendizado de **Groovy** e utiliza **Gradle** como ferramenta de gerenciamento de dependências e automação de build.

## Configuração do Projeto

### Requisitos

Antes de rodar o projeto, certifique-se de ter instalado:
- **Java 11+**
- **Gradle 7+**
- **PostgreSQL** (para o banco de dados)

### Como rodar o projeto

1. Clone este repositório:
   ```sh
   git clone https://github.com/seu-usuario/linketinder.git
   cd linketinder
   ```
2. Execute o seguinte comando para baixar as dependências:
   ```sh
   gradle build
   ```
3. Para rodar o projeto, use:
   ```sh
   gradle run
   ```

## Estrutura do Banco de Dados

A estrutura e a modelagem do banco de dados foram criadas no [dbdiagram.io](https://dbdiagram.io/), uma ferramenta que facilita a criação de diagramas de banco de dados relacionais.

### Tabelas do Banco de Dados

As principais tabelas do banco de dados são:

- **candidatos**: Armazena informações sobre os candidatos, como nome, data de nascimento, email, CPF e outras informações pessoais.
- **empresas**: Armazena informações sobre as empresas, como nome, email, CNPJ e outras informações corporativas.
- **competencias**: Contém as competências que os candidatos podem possuir e as que são exigidas nas vagas.
- **vagas**: Armazena informações sobre as vagas de emprego criadas pelas empresas, incluindo o nome da vaga, a descrição e o local.
- **candidatos_competencias**: Relaciona candidatos às suas competências.
- **vagas_competencias**: Relaciona vagas às competências requeridas.
- **curtidas_vagas**: Registra as curtidas dos candidatos em vagas específicas.
- **curtidas_candidatos**: Registra as curtidas das empresas em candidatos.
- **matches**: Registra os matches entre candidatos e empresas para vagas específicas.

## Configuração do Banco de Dados

1. Certifique-se de que o **PostgreSQL** está instalado e rodando.
2. Crie um banco de dados para o projeto:
   ```sh
   psql -U seu_usuario -c "CREATE DATABASE linketinder;"
   ```
3. Configure as credenciais no arquivo **application.properties** ou dentro do código onde a conexão é estabelecida:
   ```properties
   db.url=jdbc:postgresql://localhost:5432/linketinder
   db.user=seu_usuario
   db.password=sua_senha
   ```
4. Execute os scripts de criação das tabelas, caso necessário.

## Dependências do Gradle

O projeto utiliza o **Gradle** para gerenciar as dependências. Algumas das principais dependências incluídas são:

```groovy
dependencies {
    implementation 'org.postgresql:postgresql:42.6.0'
    implementation 'org.codehaus.groovy:groovy:3.0.9'
}
```
