SET FOREIGN_KEY_CHECKS = 0;

-- REFERÊNCIAS
INSERT INTO tb_referencia (id, nome, referencia, acao) VALUES (1, 'Camiseta Slim', '01', 'CRIADO');
INSERT INTO tb_referencia (id, nome, referencia, acao) VALUES (2, 'Camiseta Manga Longa', '01.1', 'CRIADO');
INSERT INTO tb_referencia (id, nome, referencia, acao) VALUES (4, 'Camiseta Babylook', '02', 'CRIADO');
INSERT INTO tb_referencia (id, nome, referencia, acao) VALUES (5, 'Camiseta Regular', '03', 'CRIADO');
INSERT INTO tb_referencia (id, nome, referencia, acao) VALUES (6, 'Camiseta Gola Polo', '04', 'CRIADO');
INSERT INTO tb_referencia (id, nome, referencia, acao) VALUES (7, 'Camiseta de Compressão', '05', 'CRIADO');
INSERT INTO tb_referencia (id, nome, referencia, acao) VALUES (8, 'Camiseta Raglã', '06', 'CRIADO');
INSERT INTO tb_referencia (id, nome, referencia, acao) VALUES (9, 'Regata Machão', '07', 'CRIADO');
INSERT INTO tb_referencia (id, nome, referencia, acao) VALUES (10, 'Regata Nadador', '08', 'CRIADO');
INSERT INTO tb_referencia (id, nome, referencia, acao) VALUES (11, 'Regata Alça Fina', '09', 'CRIADO');
INSERT INTO tb_referencia (id, nome, referencia, acao) VALUES (12, 'Regata Cropped', '10', 'CRIADO');

-- ESTOQUE DE MATERIAIS
INSERT INTO tb_estoque_material (id, tipo, nome, fornecedor, quantidade, rendimento, total_de_pecas, data_de_criacao, data_atualizacao, acao, ativo, favorito) VALUES (1, 'Dryfit', 'Alumínio', 'Coltex', 16.4, 5.4, 88.56, '2021-01-01 03:45:00', '2021-01-01 03:45:00', 'CRIADO', TRUE, FALSE);
INSERT INTO tb_estoque_material (id, tipo, nome, fornecedor, quantidade, rendimento, total_de_pecas, data_de_criacao, data_atualizacao, acao, ativo, favorito) VALUES (2, 'Dryfit', 'Preto', 'Coltex', 12.5, 5.4, 67.50, '2021-01-01 00:00:00', '2021-01-01 00:00:00', 'CRIADO', TRUE, FALSE);
INSERT INTO tb_estoque_material (id, tipo, nome, fornecedor, quantidade, rendimento, total_de_pecas, data_de_criacao, data_atualizacao, acao, ativo, favorito) VALUES (3, 'Dryfit', 'Branco', 'Coltex', 20.85, 5.4, 112.59, '2021-01-01 00:00:00', '2021-01-01 00:00:00', 'CRIADO', TRUE, FALSE);
INSERT INTO tb_estoque_material (id, tipo, nome, fornecedor, quantidade, rendimento, total_de_pecas, data_de_criacao, data_atualizacao, acao, ativo, favorito) VALUES (4, 'Dryfit', 'Amarelo', 'Coltex', 39.2, 5.4, 211.68, '2021-01-01 00:00:00', '2021-01-01 00:00:00', 'CRIADO', TRUE, FALSE);
INSERT INTO tb_estoque_material (id, tipo, nome, fornecedor, quantidade, rendimento, total_de_pecas, data_de_criacao, data_atualizacao, acao, ativo, favorito) VALUES (5, 'Dryfit', 'Azul', 'Coltex', 13.6, 5.4, 73.44, '2021-01-01 00:00:00', '2021-01-01 00:00:00', 'CRIADO', TRUE, FALSE);
INSERT INTO tb_estoque_material (id, tipo, nome, fornecedor, quantidade, rendimento, total_de_pecas, data_de_criacao, data_atualizacao, acao, ativo, favorito) VALUES (6, 'Dryfit', 'Vermelho', 'Coltex', 11.7, 5.4, 63.18, '2021-01-01 00:00:00', '2021-01-01 00:00:00', 'CRIADO', TRUE, FALSE);

-- CLIENTES
INSERT INTO tb_cliente (id, nome, email, telefone, cpf_cnpj) VALUES (1, 'João da Silva', 'joao@gmail.com', '(11) 99999-9999', '123.456.000-00');
INSERT INTO tb_cliente (id, nome, email, telefone, cpf_cnpj) VALUES (2, 'Maria da Silva', 'maria@gmail.com', '(11) 99999-9999', '123.456.712-00');
INSERT INTO tb_cliente (id, nome, email, telefone, cpf_cnpj) VALUES (3, 'Pedro da Silva', 'pedro@gmail.com', '(11) 99999-9999', '123.456.722-00');
INSERT INTO tb_cliente (id, nome, email, telefone, cpf_cnpj) VALUES (4, 'Ana da Silva', 'ana@gmail.com', '(11) 99999-9999', '123.456.787-00');

-- TERCEIROS
INSERT INTO tb_terceiro (id, nome, bairro, telefone, tipo_servico) VALUES (1, 'João da Silva', 'SANTO AMARO', '(11) 99999-9999', 'ESTAMPARIA');
INSERT INTO tb_terceiro (id, nome, bairro, telefone, tipo_servico) VALUES (2, 'Maria da Silva', 'CENTRO', '(11) 99999-9999', 'COSTURA');
INSERT INTO tb_terceiro (id, nome, bairro, telefone, tipo_servico) VALUES (3, 'Pedro da Silva', 'JARDIM DA SONIA', '(11) 99999-9999', 'CORTE');

-- USUÁRIOS
INSERT INTO tb_usuario (id, nome, email, senha, tipo_acesso) VALUES (1, 'Admin', 'admin@gmail.com', '$2a$10$U5FSP0sCpsNaqIpvYetITeHcgNsHspKbZypi.qQd0JeupxrQt.LXm', 'ADMINISTRADOR');
INSERT INTO tb_usuario (id, nome, email, senha, tipo_acesso) VALUES (2, 'Gerente João', 'gerente@gmail.com', '$2a$10$U5FSP0sCpsNaqIpvYetITeHcgNsHspKbZypi.qQd0JeupxrQt.LXm', 'GERENTE');
INSERT INTO tb_usuario (id, nome, email, senha, tipo_acesso) VALUES (3, 'Produção Maria', 'producao@gmail.com', '$2a$10$U5FSP0sCpsNaqIpvYetITeHcgNsHspKbZypi.qQd0JeupxrQt.LXm', 'PRODUCAO');
INSERT INTO tb_usuario (id, nome, email, senha, tipo_acesso) VALUES (4, 'Vendas Pedro', 'vendas@gmail.com', '$2a$10$U5FSP0sCpsNaqIpvYetITeHcgNsHspKbZypi.qQd0JeupxrQt.LXm', 'VENDAS');

SET FOREIGN_KEY_CHECKS = 1;
