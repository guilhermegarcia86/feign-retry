DROP TABLE IF EXISTS response;

CREATE TABLE response (
  cep VARCHAR(10) PRIMARY KEY,
  create_at DATE NOT NULL,
  update_at DATE,
  logradouro VARCHAR(250),
  bairro VARCHAR(250),
  cidade VARCHAR(250),
  estado VARCHAR(250),
  success BOOLEAN DEFAULT FALSE
);