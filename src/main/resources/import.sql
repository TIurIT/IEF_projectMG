-- REFERÊNCIAS
INSERT INTO tb_referencia ( nome, referencia, rendimento, data_atualizacao, acao, ativo) VALUES ( 'Camiseta Slim', '01', 5.4, '2021-01-01 03:45:00', 'CRIADO', TRUE);
INSERT INTO tb_referencia ( nome, referencia, rendimento, data_atualizacao, acao, ativo) VALUES ( 'Camiseta Baby', '02', 5.4, '2021-01-01 03:45:00', 'CRIADO', TRUE);
INSERT INTO tb_referencia ( nome, referencia, rendimento, data_atualizacao, acao, ativo) VALUES ( 'Camiseta Slim Manga Longa', '03', 3.6, '2021-01-01 03:45:00', 'CRIADO', TRUE);

-- ESTOQUE DE MATERIAIS
INSERT INTO tb_estoque_material ( tipo, nome, fornecedor, quantidade, rendimento, total_de_pecas, data_de_criacao, data_atualizacao, acao, ativo, favorito) VALUES ( 'Dryfit', 'Alumínio', 'Coltex', 16.4, 5.4, 88.56, '2021-01-01 03:45:00', '2021-01-01 03:45:00', 'CRIADO', TRUE, FALSE);
INSERT INTO tb_estoque_material ( tipo, nome, fornecedor, quantidade, rendimento, total_de_pecas, data_de_criacao, data_atualizacao, acao, ativo, favorito) VALUES ( 'Dryfit', 'Preto', 'Coltex', 12.5, 5.4, 67.50, '2021-01-01 00:00:00', '2021-01-01 00:00:00', 'CRIADO', TRUE, FALSE);
INSERT INTO tb_estoque_material ( tipo, nome, fornecedor, quantidade, rendimento, total_de_pecas, data_de_criacao, data_atualizacao, acao, ativo, favorito) VALUES ( 'Dryfit', 'Branco', 'Coltex', 20.85, 5.4, 112.59, '2021-01-01 00:00:00', '2021-01-01 00:00:00', 'CRIADO', TRUE, FALSE);
INSERT INTO tb_estoque_material ( tipo, nome, fornecedor, quantidade, rendimento, total_de_pecas, data_de_criacao, data_atualizacao, acao, ativo, favorito) VALUES ( 'Dryfit', 'Amarelo', 'Coltex', 39.2, 5.4, 211.68, '2021-01-01 00:00:00', '2021-01-01 00:00:00', 'CRIADO', TRUE, FALSE);
INSERT INTO tb_estoque_material ( tipo, nome, fornecedor, quantidade, rendimento, total_de_pecas, data_de_criacao, data_atualizacao, acao, ativo, favorito) VALUES ( 'Dryfit', 'Azul', 'Coltex', 13.6, 5.4, 73.44, '2021-01-01 00:00:00', '2021-01-01 00:00:00', 'CRIADO', TRUE, FALSE);
INSERT INTO tb_estoque_material ( tipo, nome, fornecedor, quantidade, rendimento, total_de_pecas, data_de_criacao, data_atualizacao, acao, ativo, favorito) VALUES ( 'Dryfit', 'Vermelho', 'Coltex', 11.7, 5.4, 63.18, '2021-01-01 00:00:00', '2021-01-01 00:00:00', 'CRIADO', TRUE, FALSE);

-- CLIENTES
INSERT INTO tb_cliente ( nome, email, telefone, cpf_cnpj) VALUES ( 'João da Silva', 'joao@gmail.com', '(11) 99999-9999', '123.456.000-00');
INSERT INTO tb_cliente ( nome, email, telefone, cpf_cnpj) VALUES ( 'Maria da Silva', 'maria@gmail.com', '(11) 99999-9999', '123.456.712-00');
INSERT INTO tb_cliente ( nome, email, telefone, cpf_cnpj) VALUES ( 'Pedro da Silva', 'pedro@gmail.com', '(11) 99999-9999', '123.456.722-00');
INSERT INTO tb_cliente ( nome, email, telefone, cpf_cnpj) VALUES ( 'Ana da Silva', 'ana@gmail.com', '(11) 99999-9999', '123.456.787-00');

-- TERCEIROS
INSERT INTO tb_terceiro ( nome, bairro, telefone, cnpj, tipo_servico) VALUES ( 'João da Silva', 'SANTO AMARO', '(11) 99999-9999', '4654844848' ,'ESTAMPARIA');
INSERT INTO tb_terceiro ( nome, bairro, telefone, cnpj,tipo_servico) VALUES ( 'Maria da Silva', 'CENTRO', '(11) 99999-9999', '58498489','COSTURA');
INSERT INTO tb_terceiro ( nome, bairro, telefone, cnpj,tipo_servico) VALUES ( 'Pedro da Silva', 'JARDIM DA SONIA', '(11) 99999-9999', '6848465822723','CORTE');

---- USUÁRIOS
INSERT INTO tb_usuario ( nome, email, senha, tipo_acesso) VALUES ( 'Admin', 'admin@gmail.com', '$2a$10$U5FSP0sCpsNaqIpvYetITeHcgNsHspKbZypi.qQd0JeupxrQt.LXm', 'ADMIN');
--INSERT INTO tb_usuario ( nome, email, senha, tipo_acesso) VALUES ( 'Gerente João', 'gerente@gmail.com', '$2a$10$U5FSP0sCpsNaqIpvYetITeHcgNsHspKbZypi.qQd0JeupxrQt.LXm', 'GERENTE');
--INSERT INTO tb_usuario ( nome, email, senha, tipo_acesso) VALUES ( 'Produção Maria', 'producao@gmail.com', '$2a$10$U5FSP0sCpsNaqIpvYetITeHcgNsHspKbZypi.qQd0JeupxrQt.LXm', 'PRODUCAO');
--INSERT INTO tb_usuario ( nome, email, senha, tipo_acesso) VALUES ( 'Vendas Pedro', 'vendas@gmail.com', '$2a$10$U5FSP0sCpsNaqIpvYetITeHcgNsHspKbZypi.qQd0JeupxrQt.LXm', 'VENDAS');

