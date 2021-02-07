CREATE TABLE usuario (
	code BIGINT(20) PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	email VARCHAR(50) NOT NULL,
	password VARCHAR(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE role (
	code BIGINT(20) PRIMARY KEY,
	description VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE usuario_role (
	code_usuario BIGINT(20) NOT NULL,
	code_role BIGINT(20) NOT NULL,
	PRIMARY KEY (code_usuario, code_role),
	FOREIGN KEY (code_usuario) REFERENCES usuario(code),
	FOREIGN KEY (code_role) REFERENCES role(code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO usuario (code, name, email, password) values (1, 'Administrador', 'admin@gmail.com', '$2a$10$X607ZPhQ4EgGNaYKt3n4SONjIv9zc.VMWdEuhCuba7oLAL5IvcL5.');
INSERT INTO usuario (code, name, email, password) values (2, 'Maria Silva', 'maria@gmail.com', '$2a$10$Zc3w6HyuPOPXamaMhh.PQOXvDnEsadztbfi6/RyZWJDzimE8WQjaq');

INSERT INTO role (code, description) values (1, 'ROLE_CADASTRAR_PESSOA');
INSERT INTO role (code, description) values (2, 'ROLE_REMOVER_PESSOA');
INSERT INTO role (code, description) values (3, 'ROLE_PESQUISAR_PESSOA');

-- admin
INSERT INTO usuario_role (code_usuario, code_role) values (1, 1);
INSERT INTO usuario_role (code_usuario, code_role) values (1, 2);
INSERT INTO usuario_role (code_usuario, code_role) values (1, 3);


-- maria
INSERT INTO usuario_role (code_usuario, code_role) values (2, 3);
