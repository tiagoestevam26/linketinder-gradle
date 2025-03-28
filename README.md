## Linketinder 

O **Linketinder** é um sistema que facilita o recrutamento de talentos, combinando competências de candidatos e requisitos das empresas.
Projeto faz parte do aprendizado de Groovy.

## Estrutura do Banco de Dados

A estrutura e a modelagem do banco de dados foi modelada no [dbdiagram.io](https://dbdiagram.io/), uma ferramenta que facilita a criação de diagramas de banco de dados relacionais.

### Tabelas do Banco de Dados

As principais tabelas do banco de dados são:

- **candidatos**: Armazena informações sobre os candidatos, como nome, data de nascimento, email, CPF, e outras informações pessoais.
- **empresas**: Armazena informações sobre as empresas, como nome, email, CNPJ, e outras informações corporativas.
- **competencias**: Contém as competências que os candidatos podem possuir e as que são exigidas nas vagas.
- **vagas**: Armazena informações sobre as vagas de emprego criadas pelas empresas, incluindo o nome da vaga, a descrição e o local.
- **candidatos_competencias**: Relaciona candidatos às suas competências.
- **vagas_competencias**: Relaciona vagas às competências requeridas.
- **curtidas_vagas**: Registra as curtidas dos candidatos em vagas específicas.
- **curtidas_candidatos**: Registra as curtidas das empresas em candidatos.
- **matches**: Registra os matches entre candidatos e empresas para vagas específicas.

