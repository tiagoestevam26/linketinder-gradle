-- Criação das tabelas
CREATE TABLE "candidatos"
(
    "id"              SERIAL PRIMARY KEY,
    "nome"            VARCHAR        NOT NULL,
    "data_nascimento" DATE,
    "email"           VARCHAR UNIQUE NOT NULL,
    "cpf"             VARCHAR UNIQUE NOT NULL,
    "pais"            VARCHAR,
    "cep"             VARCHAR,
    "descricao"       TEXT,
    "senha"           VARCHAR        NOT NULL
);

CREATE TABLE "empresas"
(
    "id"        SERIAL PRIMARY KEY,
    "nome"      VARCHAR        NOT NULL,
    "email"     VARCHAR UNIQUE NOT NULL,
    "cnpj"      VARCHAR UNIQUE NOT NULL,
    "pais"      VARCHAR,
    "cep"       VARCHAR,
    "descricao" TEXT,
    "senha"     VARCHAR        NOT NULL
);

CREATE TABLE "competencias"
(
    "id"   SERIAL PRIMARY KEY,
    "nome" VARCHAR UNIQUE NOT NULL
);

CREATE TABLE "vagas"
(
    "id"         SERIAL PRIMARY KEY,
    "empresa_id" INTEGER NOT NULL REFERENCES "empresas" ("id"),
    "nome"       VARCHAR NOT NULL,
    "descricao"  TEXT,
    "local"      VARCHAR,
    "created_at" TIMESTAMP DEFAULT NOW()
);

CREATE TABLE "candidatos_competencias"
(
    "candidato_id"   INTEGER NOT NULL REFERENCES "candidatos" ("id"),
    "competencia_id" INTEGER NOT NULL REFERENCES "competencias" ("id"),
    "created_at"     TIMESTAMP DEFAULT NOW(),
    PRIMARY KEY ("candidato_id", "competencia_id")
);

CREATE TABLE "vagas_competencias"
(
    "vaga_id"        INTEGER NOT NULL REFERENCES "vagas" ("id"),
    "competencia_id" INTEGER NOT NULL REFERENCES "competencias" ("id"),
    "created_at"     TIMESTAMP DEFAULT NOW(),
    PRIMARY KEY ("vaga_id", "competencia_id")
);

CREATE TABLE "curtidas_vagas"
(
    "id"           SERIAL PRIMARY KEY,
    "candidato_id" INTEGER NOT NULL REFERENCES "candidatos" ("id"),
    "vaga_id"      INTEGER NOT NULL REFERENCES "vagas" ("id"),
    "created_at"   TIMESTAMP DEFAULT NOW()
);

CREATE TABLE "curtidas_candidatos"
(
    "id"           SERIAL PRIMARY KEY,
    "empresa_id"   INTEGER NOT NULL REFERENCES "empresas" ("id"),
    "candidato_id" INTEGER NOT NULL REFERENCES "candidatos" ("id"),
    "created_at"   TIMESTAMP DEFAULT NOW()
);

CREATE TABLE "matches"
(
    "id"           SERIAL PRIMARY KEY,
    "candidato_id" INTEGER NOT NULL REFERENCES "candidatos" ("id"),
    "empresa_id"   INTEGER NOT NULL REFERENCES "empresas" ("id"),
    "vaga_id"      INTEGER NOT NULL REFERENCES "vagas" ("id"),
    "data_match"   TIMESTAMP DEFAULT NOW()
);

-- Inserts de candidatos
INSERT INTO "candidatos" ("nome", "data_nascimento", "email", "cpf", "pais", "cep", "descricao", "senha")
VALUES ('João Silva', '1990-05-12', 'joao@email.com', '123.456.789-00', 'Brasil', '01001-000', 'Desenvolvedor Backend',
        'senha123'),
       ('Maria Souza', '1995-08-25', 'maria@email.com', '987.654.321-00', 'Brasil', '02002-000',
        'Engenheira de Software', 'senha123'),
       ('Carlos Mendes', '1988-11-30', 'carlos@email.com', '111.222.333-44', 'Brasil', '03003-000', 'Analista de Dados',
        'senha123'),
       ('Ana Paula', '1993-02-15', 'ana@email.com', '555.666.777-88', 'Brasil', '04004-000', 'Front-end Developer',
        'senha123'),
       ('Pedro Henrique', '2000-07-07', 'pedro@email.com', '999.888.777-66', 'Brasil', '05005-000', 'DevOps Engineer',
        'senha123');

-- Inserts de empresas
INSERT INTO "empresas" ("nome", "email", "cnpj", "pais", "cep", "descricao", "senha")
VALUES ('Tech Solutions', 'tech@email.com', '12.345.678/0001-90', 'Brasil', '10000-000',
        'Empresa de tecnologia focada em IA', 'senha123'),
       ('Inova Web', 'inova@email.com', '98.765.432/0001-10', 'Brasil', '11000-000', 'Desenvolvimento web e mobile',
        'senha123'),
       ('Data Corp', 'datacorp@email.com', '56.789.123/0001-55', 'Brasil', '12000-000', 'Análises e Big Data',
        'senha123'),
       ('SoftWare House', 'software@email.com', '34.567.890/0001-22', 'Brasil', '13000-000',
        'Criação de software corporativo', 'senha123'),
       ('Cloud Systems', 'cloud@email.com', '45.678.901/0001-33', 'Brasil', '14000-000', 'Soluções em nuvem',
        'senha123');

-- Inserts de competências
INSERT INTO "competencias" ("nome")
VALUES ('JavaScript'),
       ('Python'),
       ('SQL'),
       ('DevOps'),
       ('Machine Learning');