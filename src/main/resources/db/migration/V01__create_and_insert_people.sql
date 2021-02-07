CREATE TABLE person (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(50) NOT NULL,
	cpf_cnpj VARCHAR(14) NOT NULL,
	status BOOLEAN NOT NULL,
	public_place VARCHAR(50),
	number VARCHAR(10),
	complement VARCHAR(50),
	neighborhood VARCHAR(30),
	zip_code VARCHAR(10),
	city VARCHAR(25),
	state VARCHAR(20)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO person (name, cpf_cnpj, status, public_place, number, complement, neighborhood, zip_code, city, state) values ('Bruno', '12345678912', true, "QS 05 rua 120", "504", "Residencia Costa Azul, Apt 504 A", "Aguas Claras", "71963180", "Brasilia", "DF");
INSERT INTO person (name, cpf_cnpj, status, public_place, number, complement, neighborhood, zip_code, city, state) values ('João', '98765432198', false, "QN 7 Conj 28", "11", "Casa", "Riacho Fundo 1", "71805728", "Brasilia", "DF");