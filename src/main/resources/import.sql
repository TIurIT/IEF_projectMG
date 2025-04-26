SET FOREIGN_KEY_CHECKS=0;

INSERT INTO tb_produto (id, nome, referencia) VALUES (1, 'Camiseta Slim', '01');
INSERT INTO tb_produto (id, nome, referencia) VALUES (2, 'Camiseta Manga Longa', '01.1');
INSERT INTO tb_produto (id, nome, referencia) VALUES (4, 'Camiseta Babylook', '02');
INSERT INTO tb_produto (id, nome, referencia) VALUES (5, 'Camiseta Regular', '03');
INSERT INTO tb_produto (id, nome, referencia) VALUES (6, 'Camiseta Gola Polo', '04');
INSERT INTO tb_produto (id, nome, referencia) VALUES (7, 'Camiseta de Compressão', '05');
INSERT INTO tb_produto (id, nome, referencia) VALUES (8, 'Camiseta Raglã', '06');
INSERT INTO tb_produto (id, nome, referencia) VALUES (9, 'Regata Machão', '07');
INSERT INTO tb_produto (id, nome, referencia) VALUES (10, 'Regata Nadador', '08');
INSERT INTO tb_produto (id, nome, referencia) VALUES (11, 'Regata Alça Fina', '09');
INSERT INTO tb_produto (id, nome, referencia) VALUES (12, 'Regata Cropped', '10');

INSERT INTO tb_estoque_material (id, tipo, nome, marca, quantidade, data_de_criacao) VALUES (1, 'Dryfit', 'Alumínio', 'Coltex', 100, '2021-01-01');
INSERT INTO tb_estoque_material (id, tipo, nome, marca, quantidade, data_de_criacao) VALUES (2, 'Dryfit', 'Preto', 'Coltex', 100, '2021-01-01');
INSERT INTO tb_estoque_material (id, tipo, nome, marca, quantidade, data_de_criacao) VALUES (3, 'Dryfit', 'Branco', 'Coltex', 100, '2021-01-01');
INSERT INTO tb_estoque_material (id, tipo, nome, marca, quantidade, data_de_criacao) VALUES (4, 'Dryfit', 'Amarelo', 'Coltex', 100, '2021-01-01');
INSERT INTO tb_estoque_material (id, tipo, nome, marca, quantidade, data_de_criacao) VALUES (5, 'Dryfit', 'Azul', 'Coltex', 100, '2021-01-01');
INSERT INTO tb_estoque_material (id, tipo, nome, marca, quantidade, data_de_criacao) VALUES (6, 'Dryfit', 'Vermelho', 'Coltex', 100, '2021-01-01');

INSERT INTO tb_cliente (id, nome, email, telefone, cpf_cnpj) VALUES (1, 'João da Silva', 'joao@gmail.com', '(11) 99999-9999', '123.456.000-00');
INSERT INTO tb_cliente (id, nome, email, telefone, cpf_cnpj) VALUES (2, 'Maria da Silva', 'maria@gmail.com', '(11) 99999-9999', '123.456.712-00');
INSERT INTO tb_cliente (id, nome, email, telefone, cpf_cnpj) VALUES (3, 'Pedro da Silva', 'pedro@gmail.com', '(11) 99999-9999', '123.456.722-00');
INSERT INTO tb_cliente (id, nome, email, telefone, cpf_cnpj) VALUES (4, 'Ana da Silva', 'ana@gmail.com', '(11) 99999-9999', '123.456.787-00');

INSERT INTO tb_terceiro (id, nome, bairro, telefone, servico) VALUES (1, 'João da Silva', 'SANTO AMARO', '(11) 99999-9999', 'ESTAMPARIA');
INSERT INTO tb_terceiro (id, nome, bairro, telefone, servico) VALUES (2, 'Maria da Silva', 'CENTRO', '(11) 99999-9999', 'COSTUREIRA');
INSERT INTO tb_terceiro (id, nome, bairro, telefone, servico) VALUES (3, 'Pedro da Silva', 'JARDIM DA SONIA', '(11) 99999-9999', 'CORTADOR');


SET FOREIGN_KEY_CHECKS=1;

