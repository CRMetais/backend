USE crmetais;

-- ==========================================================
-- TABELA: usuario (sem FK)
-- ==========================================================
INSERT INTO usuario (nome, senha, email, cargo) VALUES
                                                    ('Celso Ricardo', '123456', 'celso@empresa.com', 'ADMIN'),
                                                    ('Maria Oliveira', '123456', 'maria@empresa.com', 'COMUM');

-- ==========================================================
-- TABELA: endereco (base p/ fornecedor e cliente)
-- ==========================================================
INSERT INTO endereco (estado, cidade, bairro, logradouro, complemento, numero, cep) VALUES
                                                                                         ('SP', 'São Paulo', 'Lapa', 'Rua Guaicurus', 'Galpão A', '150', '05033000'),
                                                                                         ('RJ', 'Angra dos Reis', 'Praia do Anil', 'Av. Ayrton Senna', 'Loja 4', '200', '23906010'),
                                                                                         ('MG', 'Belo Horizonte', 'Savassi', 'Rua Pernambuco', 'Sala 12', '300', '30130150'),
                                                                                         ('PR', 'Curitiba', 'Batel', 'Av. Vicente Machado', 'Conjunto 8', '400', '80420010');

-- ==========================================================
-- TABELA: tabela_preco (base p/ cliente e fornecedor)
-- ==========================================================
INSERT INTO tabela_preco (tipo, nome_tabela, versao, data_inicio_validade, data_fim_validade, ativa) VALUES
                                                                                                         ('C', 'PADRÃO', 1.0, '2026-03-01', '2026-04-01', 0),   -- compra
                                                                                                         ('V', 'VITAL', 1.0, '2026-03-01', '2026-04-01' , 0),   -- venda
                                                                                                         ('C', 'PADRÃO', 1.1, '2026-04-01', null, 1);  -- compra


-- ==========================================================
-- TABELA: produto (sem FK)
-- ==========================================================
INSERT INTO produto (nome, tipo_produto) VALUES
                                             ('Aerosol', 'Metal'),
                                             ('Aerosol Sujo', 'Metal'),
                                             ('Bateria KG', 'Metal'),
                                             ('Bloco BR', 'Metal'),
                                             ('Bloco limpo', 'Metal'),
                                             ('Bloco misto', 'Metal'),
                                             ('Bronze', 'Metal'),
                                             ('Cavaco de alumínio', 'Metal'),
                                             ('Cavaco de inox', 'Metal'),
                                             ('Cavaco de metal', 'Metal'),
                                             ('Chaparia', 'Metal'),
                                             ('Chumbo de roda', 'Metal'),
                                             ('Chumbo mole', 'Metal'),
                                             ('Cobre de quarta', 'Metal'),
                                             ('Cobre mel', 'Metal'),
                                             ('Cobre misto', 'Metal'),
                                             ('Ferroso', 'Metal'),
                                             ('Fio instalação', 'Metal'),
                                             ('Fio misto', 'Metal'),
                                             ('Inox', 'Metal'),
                                             ('Lata', 'Metal'),
                                             ('Metal', 'Metal'),
                                             ('Motor de alumínio', 'Metal'),
                                             ('Motor KG', 'Metal'),
                                             ('Panela', 'Metal'),
                                             ('Panela de inox', 'Metal'),
                                             ('Panela mista', 'Metal'),
                                             ('Perfil misto', 'Metal'),
                                             ('Perfil natural', 'Metal'),
                                             ('Perfil pintado', 'Metal'),
                                             ('Persiana', 'Metal'),
                                             ('Radiador com cobre', 'Metal'),
                                             ('Radiador de metal', 'Metal'),
                                             ('Roda', 'Metal'),
                                             ('Zamac', 'Metal'),
                                             ('Zamac bijuteria', 'Metal');

-- ==========================================================
-- TABELA: estoque (FK -> produto)
-- ==========================================================
INSERT INTO estoque (quantidade_disponivel, localizacao, fk_produto) VALUES
                                                                         (500, 'Galpão Principal', 1),
                                                                         (300, 'Galpão Principal', 2),
                                                                         (450, 'Galpão Principal', 3),
                                                                         (200, 'Galpão Principal', 4),
                                                                         (600, 'Galpão Principal', 5),
                                                                         (350, 'Galpão Principal', 6),
                                                                         (100, 'Galpão Principal', 7),
                                                                         (550, 'Galpão Principal', 8),
                                                                         (150, 'Galpão Principal', 9),
                                                                         (400, 'Galpão Principal', 10),
                                                                         (700, 'Galpão Principal', 11),
                                                                         (250, 'Galpão Principal', 12),
                                                                         (300, 'Galpão Principal', 13),
                                                                         (120, 'Galpão Principal', 14),
                                                                         (180, 'Galpão Principal', 15),
                                                                         (220, 'Galpão Principal', 16),
                                                                         (800, 'Galpão Principal', 17),
                                                                         (350, 'Galpão Principal', 18),
                                                                         (280, 'Galpão Principal', 19),
                                                                         (650, 'Galpão Principal', 20),
                                                                         (900, 'Galpão Principal', 21),
                                                                         (150, 'Galpão Principal', 22),
                                                                         (320, 'Galpão Principal', 23),
                                                                         (480, 'Galpão Principal', 24),
                                                                         (550, 'Galpão Principal', 25),
                                                                         (200, 'Galpão Principal', 26),
                                                                         (420, 'Galpão Principal', 27),
                                                                         (380, 'Galpão Principal', 28),
                                                                         (310, 'Galpão Principal', 29),
                                                                         (290, 'Galpão Principal', 30),
                                                                         (470, 'Galpão Principal', 31),
                                                                         (180, 'Galpão Principal', 32),
                                                                         (340, 'Galpão Principal', 33),
                                                                         (520, 'Galpão Principal', 34),
                                                                         (260, 'Galpão Principal', 35),
                                                                         (190, 'Galpão Principal', 36);

-- ==========================================================
-- TABELA: preco_produto_tabela (FK -> produto, tabela_preco)
-- ==========================================================
-- compra
INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
                                                                                  (1, 1, 8.40),
                                                                                  (2, 1, 2.00),
                                                                                  (3, 1, 3.50),
                                                                                  (4, 1, 1.00),
                                                                                  (5, 1, 2.80),
                                                                                  (6, 1, 1.40),
                                                                                  (7, 1, 38.80),
                                                                                  (8, 1, 2.80),
                                                                                  (9, 1, 22.80),
                                                                                  (10, 1, 17.80),
                                                                                  (11, 1, 7.50),
                                                                                  (12, 1, 3.80),
                                                                                  (13, 1, 5.30),
                                                                                  (14, 1, 40.30),
                                                                                  (15, 1, 50.90),
                                                                                  (16, 1, 46.80),
                                                                                  (17, 1, 0.70),
                                                                                  (18, 1, 28.30),
                                                                                  (19, 1, 14.30),
                                                                                  (20, 1, 2.20),
                                                                                  (21, 1, 8.40),
                                                                                  (22, 1, 31.80),
                                                                                  (23, 1, 7.30),
                                                                                  (24, 1, 3.30),
                                                                                  (25, 1, 10.30),
                                                                                  (26, 1, 20.00),
                                                                                  (27, 1, 5.00),
                                                                                  (28, 1, 10.30),
                                                                                  (29, 1, 11.90),
                                                                                  (30, 1, 10.40),
                                                                                  (31, 1, 4.00),
                                                                                  (32, 1, 23.80),
                                                                                  (33, 1, 13.40),
                                                                                  (34, 1, 14.50),
                                                                                  (35, 1, 5.00),
                                                                                  (36, 1, 3.30);

 INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
 (1, 3, 8.90),
 (2, 3, 2.30),
 (3, 3, 3.80),
 (4, 3, 1.20),
 (5, 3, 3.10),
 (6, 3, 1.60),
 (7, 3, 39.50),
 (8, 3, 3.00),
 (9, 3, 23.50),
 (10, 3, 18.40),
 (11, 3, 7.90),
 (12, 3, 4.10),
 (13, 3, 5.60),
 (14, 3, 41.00),
 (15, 3, 51.80),
 (16, 3, 47.50),
 (17, 3, 0.90),
 (18, 3, 29.00),
 (19, 3, 15.00),
 (20, 3, 2.50),
 (21, 3, 9.00),
 (22, 3, 32.50),
 (23, 3, 7.80),
 (24, 3, 3.60),
 (25, 3, 11.00),
 (26, 3, 21.50),
 (27, 3, 5.40),
 (28, 3, 11.20),
 (29, 3, 12.40),
 (30, 3, 11.00),
 (31, 3, 4.50),
 (32, 3, 24.50),
 (33, 3, 14.00),
 (34, 3, 15.20),
 (35, 3, 5.40),
 (36, 3, 3.80);

-- venda
-- ============================================================
-- Script de historico semanal - Tabela VITAL (2024 ~ jan/2026)
-- Tabela VITAL atual: id_tabela = 2
-- ============================================================


-- Versao 1.1 | 2024-01-01 → '2024-01-07'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 1.1, '2024-01-01', '2024-01-07', 0);

SET @id_0 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_0, 10.14),
  (2, @id_0, 3.00),
  (3, @id_0, 4.00),
  (4, @id_0, 4.25),
  (5, @id_0, 4.82),
  (6, @id_0, 3.00),
  (7, @id_0, 41.00),
  (8, @id_0, 3.00),
  (9, @id_0, 25.00),
  (10, @id_0, 20.00),
  (11, @id_0, 9.70),
  (12, @id_0, 6.86),
  (13, @id_0, 7.50),
  (14, @id_0, 42.50),
  (15, @id_0, 52.99),
  (16, @id_0, 48.27),
  (17, @id_0, 1.10),
  (18, @id_0, 30.50),
  (19, @id_0, 16.10),
  (20, @id_0, 4.40),
  (21, @id_0, 10.60),
  (22, @id_0, 32.36),
  (23, @id_0, 8.74),
  (24, @id_0, 3.50),
  (25, @id_0, 12.06),
  (26, @id_0, 22.20),
  (27, @id_0, 6.54),
  (28, @id_0, 11.88),
  (29, @id_0, 14.26),
  (30, @id_0, 12.60),
  (31, @id_0, 6.20),
  (32, @id_0, 26.00),
  (33, @id_0, 15.20),
  (34, @id_0, 16.70),
  (35, @id_0, 7.20),
  (36, @id_0, 6.11);

-- Versao 1.2 | 2024-01-08 → '2024-01-14'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 1.2, '2024-01-08', '2024-01-14', 0);

SET @id_1 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_1, 10.14),
  (2, @id_1, 3.00),
  (3, @id_1, 4.00),
  (4, @id_1, 3.57),
  (5, @id_1, 5.16),
  (6, @id_1, 3.33),
  (7, @id_1, 41.00),
  (8, @id_1, 2.32),
  (9, @id_1, 25.00),
  (10, @id_1, 21.28),
  (11, @id_1, 10.12),
  (12, @id_1, 6.86),
  (13, @id_1, 7.50),
  (14, @id_1, 42.50),
  (15, @id_1, 52.99),
  (16, @id_1, 47.38),
  (17, @id_1, 1.10),
  (18, @id_1, 31.15),
  (19, @id_1, 15.59),
  (20, @id_1, 4.40),
  (21, @id_1, 10.60),
  (22, @id_1, 32.36),
  (23, @id_1, 8.74),
  (24, @id_1, 3.50),
  (25, @id_1, 11.60),
  (26, @id_1, 22.20),
  (27, @id_1, 5.98),
  (28, @id_1, 11.88),
  (29, @id_1, 14.26),
  (30, @id_1, 12.60),
  (31, @id_1, 7.41),
  (32, @id_1, 26.00),
  (33, @id_1, 15.20),
  (34, @id_1, 16.70),
  (35, @id_1, 6.62),
  (36, @id_1, 5.24);

-- Versao 1.3 | 2024-01-15 → '2024-01-21'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 1.3, '2024-01-15', '2024-01-21', 0);

SET @id_2 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_2, 10.14),
  (2, @id_2, 4.05),
  (3, @id_2, 4.00),
  (4, @id_2, 3.57),
  (5, @id_2, 5.42),
  (6, @id_2, 3.33),
  (7, @id_2, 41.00),
  (8, @id_2, 1.43),
  (9, @id_2, 25.00),
  (10, @id_2, 21.28),
  (11, @id_2, 10.12),
  (12, @id_2, 5.98),
  (13, @id_2, 7.50),
  (14, @id_2, 43.45),
  (15, @id_2, 52.99),
  (16, @id_2, 48.24),
  (17, @id_2, 1.94),
  (18, @id_2, 31.15),
  (19, @id_2, 15.59),
  (20, @id_2, 4.40),
  (21, @id_2, 9.71),
  (22, @id_2, 31.98),
  (23, @id_2, 8.74),
  (24, @id_2, 2.55),
  (25, @id_2, 11.60),
  (26, @id_2, 22.20),
  (27, @id_2, 5.98),
  (28, @id_2, 13.05),
  (29, @id_2, 14.26),
  (30, @id_2, 12.60),
  (31, @id_2, 7.41),
  (32, @id_2, 26.00),
  (33, @id_2, 14.72),
  (34, @id_2, 16.70),
  (35, @id_2, 6.62),
  (36, @id_2, 5.62);

-- Versao 1.4 | 2024-01-22 → '2024-01-28'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 1.4, '2024-01-22', '2024-01-28', 0);

SET @id_3 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_3, 10.14),
  (2, @id_3, 3.37),
  (3, @id_3, 4.00),
  (4, @id_3, 3.57),
  (5, @id_3, 5.42),
  (6, @id_3, 3.33),
  (7, @id_3, 41.00),
  (8, @id_3, 1.43),
  (9, @id_3, 25.00),
  (10, @id_3, 21.28),
  (11, @id_3, 10.12),
  (12, @id_3, 6.40),
  (13, @id_3, 6.63),
  (14, @id_3, 43.45),
  (15, @id_3, 52.99),
  (16, @id_3, 48.64),
  (17, @id_3, 1.94),
  (18, @id_3, 30.54),
  (19, @id_3, 15.59),
  (20, @id_3, 5.63),
  (21, @id_3, 9.71),
  (22, @id_3, 31.98),
  (23, @id_3, 8.74),
  (24, @id_3, 2.55),
  (25, @id_3, 11.60),
  (26, @id_3, 23.24),
  (27, @id_3, 5.79),
  (28, @id_3, 13.05),
  (29, @id_3, 14.26),
  (30, @id_3, 12.28),
  (31, @id_3, 7.41),
  (32, @id_3, 26.00),
  (33, @id_3, 14.72),
  (34, @id_3, 16.70),
  (35, @id_3, 6.62),
  (36, @id_3, 5.12);

-- Versao 1.5 | 2024-01-29 → '2024-02-04'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 1.5, '2024-01-29', '2024-02-04', 0);

SET @id_4 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_4, 9.54),
  (2, @id_4, 3.37),
  (3, @id_4, 3.15),
  (4, @id_4, 2.64),
  (5, @id_4, 5.17),
  (6, @id_4, 3.33),
  (7, @id_4, 41.00),
  (8, @id_4, 1.43),
  (9, @id_4, 25.00),
  (10, @id_4, 21.28),
  (11, @id_4, 9.78),
  (12, @id_4, 7.14),
  (13, @id_4, 6.63),
  (14, @id_4, 42.45),
  (15, @id_4, 52.99),
  (16, @id_4, 48.96),
  (17, @id_4, 1.94),
  (18, @id_4, 29.92),
  (19, @id_4, 15.59),
  (20, @id_4, 5.81),
  (21, @id_4, 9.71),
  (22, @id_4, 31.98),
  (23, @id_4, 8.50),
  (24, @id_4, 2.38),
  (25, @id_4, 11.60),
  (26, @id_4, 23.24),
  (27, @id_4, 6.22),
  (28, @id_4, 13.05),
  (29, @id_4, 13.94),
  (30, @id_4, 12.28),
  (31, @id_4, 8.10),
  (32, @id_4, 26.00),
  (33, @id_4, 14.72),
  (34, @id_4, 16.42),
  (35, @id_4, 7.05),
  (36, @id_4, 5.12);

-- Versao 1.6 | 2024-02-05 → '2024-02-11'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 1.6, '2024-02-05', '2024-02-11', 0);

SET @id_5 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_5, 10.06),
  (2, @id_5, 3.37),
  (3, @id_5, 3.35),
  (4, @id_5, 2.64),
  (5, @id_5, 4.17),
  (6, @id_5, 3.33),
  (7, @id_5, 41.00),
  (8, @id_5, 1.43),
  (9, @id_5, 24.11),
  (10, @id_5, 20.85),
  (11, @id_5, 9.78),
  (12, @id_5, 6.41),
  (13, @id_5, 8.11),
  (14, @id_5, 42.56),
  (15, @id_5, 52.62),
  (16, @id_5, 50.37),
  (17, @id_5, 1.94),
  (18, @id_5, 29.92),
  (19, @id_5, 15.59),
  (20, @id_5, 5.81),
  (21, @id_5, 9.71),
  (22, @id_5, 33.08),
  (23, @id_5, 8.50),
  (24, @id_5, 2.85),
  (25, @id_5, 11.60),
  (26, @id_5, 22.38),
  (27, @id_5, 6.22),
  (28, @id_5, 13.05),
  (29, @id_5, 13.94),
  (30, @id_5, 12.28),
  (31, @id_5, 9.09),
  (32, @id_5, 26.00),
  (33, @id_5, 15.59),
  (34, @id_5, 16.42),
  (35, @id_5, 7.05),
  (36, @id_5, 5.12);

-- Versao 1.7 | 2024-02-12 → '2024-02-18'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 1.7, '2024-02-12', '2024-02-18', 0);

SET @id_6 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_6, 9.15),
  (2, @id_6, 3.37),
  (3, @id_6, 2.73),
  (4, @id_6, 2.64),
  (5, @id_6, 4.17),
  (6, @id_6, 3.33),
  (7, @id_6, 40.18),
  (8, @id_6, 0.52),
  (9, @id_6, 24.11),
  (10, @id_6, 20.85),
  (11, @id_6, 9.78),
  (12, @id_6, 7.75),
  (13, @id_6, 8.11),
  (14, @id_6, 42.88),
  (15, @id_6, 51.74),
  (16, @id_6, 49.54),
  (17, @id_6, 1.82),
  (18, @id_6, 30.49),
  (19, @id_6, 14.77),
  (20, @id_6, 4.98),
  (21, @id_6, 9.71),
  (22, @id_6, 33.33),
  (23, @id_6, 7.63),
  (24, @id_6, 2.85),
  (25, @id_6, 11.09),
  (26, @id_6, 22.38),
  (27, @id_6, 6.64),
  (28, @id_6, 13.05),
  (29, @id_6, 13.94),
  (30, @id_6, 12.28),
  (31, @id_6, 8.12),
  (32, @id_6, 26.00),
  (33, @id_6, 16.25),
  (34, @id_6, 15.84),
  (35, @id_6, 6.85),
  (36, @id_6, 4.86);

-- Versao 1.8 | 2024-02-19 → '2024-02-25'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 1.8, '2024-02-19', '2024-02-25', 0);

SET @id_7 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_7, 8.81),
  (2, @id_7, 3.37),
  (3, @id_7, 2.73),
  (4, @id_7, 3.18),
  (5, @id_7, 4.99),
  (6, @id_7, 3.33),
  (7, @id_7, 40.18),
  (8, @id_7, 0.52),
  (9, @id_7, 24.21),
  (10, @id_7, 20.27),
  (11, @id_7, 10.92),
  (12, @id_7, 8.36),
  (13, @id_7, 8.11),
  (14, @id_7, 43.44),
  (15, @id_7, 51.74),
  (16, @id_7, 48.79),
  (17, @id_7, 1.82),
  (18, @id_7, 30.49),
  (19, @id_7, 14.77),
  (20, @id_7, 4.98),
  (21, @id_7, 9.71),
  (22, @id_7, 33.33),
  (23, @id_7, 7.63),
  (24, @id_7, 2.14),
  (25, @id_7, 10.44),
  (26, @id_7, 22.38),
  (27, @id_7, 6.64),
  (28, @id_7, 13.05),
  (29, @id_7, 13.94),
  (30, @id_7, 12.28),
  (31, @id_7, 8.12),
  (32, @id_7, 26.75),
  (33, @id_7, 16.25),
  (34, @id_7, 15.84),
  (35, @id_7, 6.85),
  (36, @id_7, 4.04);

-- Versao 1.9 | 2024-02-26 → '2024-03-03'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 1.9, '2024-02-26', '2024-03-03', 0);

SET @id_8 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_8, 8.81),
  (2, @id_8, 3.37),
  (3, @id_8, 2.73),
  (4, @id_8, 3.75),
  (5, @id_8, 4.21),
  (6, @id_8, 3.74),
  (7, @id_8, 40.18),
  (8, @id_8, 0.52),
  (9, @id_8, 24.21),
  (10, @id_8, 20.27),
  (11, @id_8, 10.75),
  (12, @id_8, 8.36),
  (13, @id_8, 8.48),
  (14, @id_8, 44.23),
  (15, @id_8, 51.74),
  (16, @id_8, 48.90),
  (17, @id_8, 3.00),
  (18, @id_8, 30.49),
  (19, @id_8, 14.77),
  (20, @id_8, 4.98),
  (21, @id_8, 9.14),
  (22, @id_8, 33.33),
  (23, @id_8, 7.63),
  (24, @id_8, 1.63),
  (25, @id_8, 9.74),
  (26, @id_8, 21.74),
  (27, @id_8, 5.74),
  (28, @id_8, 14.16),
  (29, @id_8, 13.94),
  (30, @id_8, 11.69),
  (31, @id_8, 7.35),
  (32, @id_8, 26.75),
  (33, @id_8, 16.25),
  (34, @id_8, 15.84),
  (35, @id_8, 8.02),
  (36, @id_8, 4.04);

-- Versao 2.0 | 2024-03-04 → '2024-03-10'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 2.0, '2024-03-04', '2024-03-10', 0);

SET @id_9 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_9, 9.29),
  (2, @id_9, 3.37),
  (3, @id_9, 2.73),
  (4, @id_9, 3.75),
  (5, @id_9, 4.21),
  (6, @id_9, 3.74),
  (7, @id_9, 40.18),
  (8, @id_9, 0.52),
  (9, @id_9, 25.30),
  (10, @id_9, 20.27),
  (11, @id_9, 10.75),
  (12, @id_9, 8.36),
  (13, @id_9, 8.48),
  (14, @id_9, 44.23),
  (15, @id_9, 51.74),
  (16, @id_9, 48.27),
  (17, @id_9, 4.10),
  (18, @id_9, 31.12),
  (19, @id_9, 14.77),
  (20, @id_9, 4.98),
  (21, @id_9, 10.43),
  (22, @id_9, 32.85),
  (23, @id_9, 8.49),
  (24, @id_9, 2.04),
  (25, @id_9, 9.74),
  (26, @id_9, 21.74),
  (27, @id_9, 5.74),
  (28, @id_9, 14.16),
  (29, @id_9, 14.61),
  (30, @id_9, 11.69),
  (31, @id_9, 7.35),
  (32, @id_9, 26.75),
  (33, @id_9, 16.39),
  (34, @id_9, 15.84),
  (35, @id_9, 8.02),
  (36, @id_9, 4.04);

-- Versao 2.1 | 2024-03-11 → '2024-03-17'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 2.1, '2024-03-11', '2024-03-17', 0);

SET @id_10 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_10, 9.58),
  (2, @id_10, 3.37),
  (3, @id_10, 2.73),
  (4, @id_10, 3.75),
  (5, @id_10, 4.21),
  (6, @id_10, 3.74),
  (7, @id_10, 40.81),
  (8, @id_10, 1.78),
  (9, @id_10, 25.30),
  (10, @id_10, 20.27),
  (11, @id_10, 10.75),
  (12, @id_10, 8.36),
  (13, @id_10, 8.07),
  (14, @id_10, 45.42),
  (15, @id_10, 52.90),
  (16, @id_10, 47.99),
  (17, @id_10, 4.10),
  (18, @id_10, 31.12),
  (19, @id_10, 14.77),
  (20, @id_10, 5.87),
  (21, @id_10, 10.43),
  (22, @id_10, 34.02),
  (23, @id_10, 8.49),
  (24, @id_10, 3.19),
  (25, @id_10, 9.74),
  (26, @id_10, 20.77),
  (27, @id_10, 5.74),
  (28, @id_10, 14.16),
  (29, @id_10, 14.61),
  (30, @id_10, 13.13),
  (31, @id_10, 7.35),
  (32, @id_10, 26.96),
  (33, @id_10, 17.37),
  (34, @id_10, 15.84),
  (35, @id_10, 9.31),
  (36, @id_10, 4.31);

-- Versao 2.2 | 2024-03-18 → '2024-03-24'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 2.2, '2024-03-18', '2024-03-24', 0);

SET @id_11 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_11, 8.78),
  (2, @id_11, 3.37),
  (3, @id_11, 2.73),
  (4, @id_11, 3.75),
  (5, @id_11, 4.21),
  (6, @id_11, 3.74),
  (7, @id_11, 39.97),
  (8, @id_11, 1.78),
  (9, @id_11, 24.63),
  (10, @id_11, 19.53),
  (11, @id_11, 10.75),
  (12, @id_11, 9.00),
  (13, @id_11, 8.07),
  (14, @id_11, 44.84),
  (15, @id_11, 52.90),
  (16, @id_11, 47.99),
  (17, @id_11, 4.10),
  (18, @id_11, 30.27),
  (19, @id_11, 14.77),
  (20, @id_11, 5.55),
  (21, @id_11, 10.43),
  (22, @id_11, 34.65),
  (23, @id_11, 8.49),
  (24, @id_11, 2.93),
  (25, @id_11, 9.74),
  (26, @id_11, 21.35),
  (27, @id_11, 5.01),
  (28, @id_11, 14.16),
  (29, @id_11, 14.61),
  (30, @id_11, 12.95),
  (31, @id_11, 8.13),
  (32, @id_11, 28.03),
  (33, @id_11, 18.16),
  (34, @id_11, 16.07),
  (35, @id_11, 9.31),
  (36, @id_11, 4.84);

-- Versao 2.3 | 2024-03-25 → '2024-03-31'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 2.3, '2024-03-25', '2024-03-31', 0);

SET @id_12 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_12, 8.78),
  (2, @id_12, 3.37),
  (3, @id_12, 2.73),
  (4, @id_12, 3.75),
  (5, @id_12, 4.77),
  (6, @id_12, 4.22),
  (7, @id_12, 39.97),
  (8, @id_12, 1.78),
  (9, @id_12, 24.63),
  (10, @id_12, 19.53),
  (11, @id_12, 10.75),
  (12, @id_12, 9.00),
  (13, @id_12, 8.07),
  (14, @id_12, 44.84),
  (15, @id_12, 54.37),
  (16, @id_12, 47.99),
  (17, @id_12, 4.10),
  (18, @id_12, 30.27),
  (19, @id_12, 14.77),
  (20, @id_12, 5.55),
  (21, @id_12, 10.43),
  (22, @id_12, 34.65),
  (23, @id_12, 8.49),
  (24, @id_12, 2.93),
  (25, @id_12, 9.50),
  (26, @id_12, 21.63),
  (27, @id_12, 4.47),
  (28, @id_12, 13.54),
  (29, @id_12, 14.61),
  (30, @id_12, 12.95),
  (31, @id_12, 8.13),
  (32, @id_12, 28.03),
  (33, @id_12, 17.63),
  (34, @id_12, 15.61),
  (35, @id_12, 9.31),
  (36, @id_12, 5.02);

-- Versao 2.4 | 2024-04-01 → '2024-04-07'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 2.4, '2024-04-01', '2024-04-07', 0);

SET @id_13 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_13, 8.78),
  (2, @id_13, 3.37),
  (3, @id_13, 2.73),
  (4, @id_13, 3.54),
  (5, @id_13, 5.72),
  (6, @id_13, 4.64),
  (7, @id_13, 39.97),
  (8, @id_13, 1.78),
  (9, @id_13, 24.63),
  (10, @id_13, 18.54),
  (11, @id_13, 10.75),
  (12, @id_13, 9.00),
  (13, @id_13, 9.50),
  (14, @id_13, 44.84),
  (15, @id_13, 54.37),
  (16, @id_13, 48.31),
  (17, @id_13, 4.10),
  (18, @id_13, 30.27),
  (19, @id_13, 14.77),
  (20, @id_13, 5.55),
  (21, @id_13, 10.43),
  (22, @id_13, 34.65),
  (23, @id_13, 8.49),
  (24, @id_13, 4.01),
  (25, @id_13, 9.50),
  (26, @id_13, 23.02),
  (27, @id_13, 5.45),
  (28, @id_13, 14.95),
  (29, @id_13, 14.61),
  (30, @id_13, 12.95),
  (31, @id_13, 9.20),
  (32, @id_13, 27.87),
  (33, @id_13, 17.63),
  (34, @id_13, 15.61),
  (35, @id_13, 10.20),
  (36, @id_13, 5.02);

-- Versao 2.5 | 2024-04-08 → '2024-04-14'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 2.5, '2024-04-08', '2024-04-14', 0);

SET @id_14 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_14, 8.78),
  (2, @id_14, 4.45),
  (3, @id_14, 2.18),
  (4, @id_14, 3.54),
  (5, @id_14, 5.72),
  (6, @id_14, 4.64),
  (7, @id_14, 39.97),
  (8, @id_14, 1.78),
  (9, @id_14, 24.63),
  (10, @id_14, 18.54),
  (11, @id_14, 9.90),
  (12, @id_14, 9.00),
  (13, @id_14, 8.50),
  (14, @id_14, 45.32),
  (15, @id_14, 54.37),
  (16, @id_14, 49.07),
  (17, @id_14, 4.10),
  (18, @id_14, 30.27),
  (19, @id_14, 14.77),
  (20, @id_14, 6.34),
  (21, @id_14, 10.43),
  (22, @id_14, 34.65),
  (23, @id_14, 8.49),
  (24, @id_14, 5.31),
  (25, @id_14, 9.50),
  (26, @id_14, 23.52),
  (27, @id_14, 4.62),
  (28, @id_14, 14.95),
  (29, @id_14, 14.61),
  (30, @id_14, 12.95),
  (31, @id_14, 9.20),
  (32, @id_14, 27.41),
  (33, @id_14, 17.63),
  (34, @id_14, 14.92),
  (35, @id_14, 10.20),
  (36, @id_14, 5.02);

-- Versao 2.6 | 2024-04-15 → '2024-04-21'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 2.6, '2024-04-15', '2024-04-21', 0);

SET @id_15 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_15, 8.78),
  (2, @id_15, 4.45),
  (3, @id_15, 3.16),
  (4, @id_15, 3.92),
  (5, @id_15, 5.72),
  (6, @id_15, 4.15),
  (7, @id_15, 40.24),
  (8, @id_15, 1.13),
  (9, @id_15, 24.63),
  (10, @id_15, 18.54),
  (11, @id_15, 9.90),
  (12, @id_15, 9.00),
  (13, @id_15, 8.50),
  (14, @id_15, 44.40),
  (15, @id_15, 54.37),
  (16, @id_15, 49.07),
  (17, @id_15, 3.55),
  (18, @id_15, 31.08),
  (19, @id_15, 14.77),
  (20, @id_15, 5.36),
  (21, @id_15, 10.43),
  (22, @id_15, 34.65),
  (23, @id_15, 8.49),
  (24, @id_15, 4.75),
  (25, @id_15, 9.50),
  (26, @id_15, 23.52),
  (27, @id_15, 5.30),
  (28, @id_15, 14.39),
  (29, @id_15, 14.26),
  (30, @id_15, 12.95),
  (31, @id_15, 9.20),
  (32, @id_15, 26.87),
  (33, @id_15, 17.63),
  (34, @id_15, 14.92),
  (35, @id_15, 10.20),
  (36, @id_15, 5.02);

-- Versao 2.7 | 2024-04-22 → '2024-04-28'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 2.7, '2024-04-22', '2024-04-28', 0);

SET @id_16 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_16, 8.78),
  (2, @id_16, 4.45),
  (3, @id_16, 3.16),
  (4, @id_16, 3.92),
  (5, @id_16, 6.62),
  (6, @id_16, 4.15),
  (7, @id_16, 40.40),
  (8, @id_16, 2.22),
  (9, @id_16, 24.18),
  (10, @id_16, 19.79),
  (11, @id_16, 9.35),
  (12, @id_16, 9.00),
  (13, @id_16, 8.50),
  (14, @id_16, 44.40),
  (15, @id_16, 53.48),
  (16, @id_16, 49.07),
  (17, @id_16, 3.55),
  (18, @id_16, 31.08),
  (19, @id_16, 15.44),
  (20, @id_16, 5.68),
  (21, @id_16, 10.43),
  (22, @id_16, 34.25),
  (23, @id_16, 9.50),
  (24, @id_16, 3.88),
  (25, @id_16, 9.50),
  (26, @id_16, 23.52),
  (27, @id_16, 6.46),
  (28, @id_16, 14.26),
  (29, @id_16, 14.26),
  (30, @id_16, 14.34),
  (31, @id_16, 8.43),
  (32, @id_16, 26.87),
  (33, @id_16, 17.63),
  (34, @id_16, 16.24),
  (35, @id_16, 10.20),
  (36, @id_16, 6.38);

-- Versao 2.8 | 2024-04-29 → '2024-05-05'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 2.8, '2024-04-29', '2024-05-05', 0);

SET @id_17 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_17, 8.78),
  (2, @id_17, 4.96),
  (3, @id_17, 3.16),
  (4, @id_17, 3.92),
  (5, @id_17, 6.62),
  (6, @id_17, 4.15),
  (7, @id_17, 40.74),
  (8, @id_17, 2.22),
  (9, @id_17, 23.52),
  (10, @id_17, 19.79),
  (11, @id_17, 9.12),
  (12, @id_17, 9.00),
  (13, @id_17, 8.50),
  (14, @id_17, 44.40),
  (15, @id_17, 53.48),
  (16, @id_17, 48.16),
  (17, @id_17, 3.87),
  (18, @id_17, 31.08),
  (19, @id_17, 15.44),
  (20, @id_17, 5.68),
  (21, @id_17, 11.12),
  (22, @id_17, 34.25),
  (23, @id_17, 9.22),
  (24, @id_17, 3.88),
  (25, @id_17, 9.50),
  (26, @id_17, 23.52),
  (27, @id_17, 6.46),
  (28, @id_17, 14.26),
  (29, @id_17, 13.89),
  (30, @id_17, 13.65),
  (31, @id_17, 8.43),
  (32, @id_17, 26.87),
  (33, @id_17, 18.20),
  (34, @id_17, 16.24),
  (35, @id_17, 10.20),
  (36, @id_17, 6.02);

-- Versao 2.9 | 2024-05-06 → '2024-05-12'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 2.9, '2024-05-06', '2024-05-12', 0);

SET @id_18 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_18, 9.73),
  (2, @id_18, 4.96),
  (3, @id_18, 4.13),
  (4, @id_18, 5.03),
  (5, @id_18, 7.89),
  (6, @id_18, 5.51),
  (7, @id_18, 41.27),
  (8, @id_18, 2.22),
  (9, @id_18, 23.52),
  (10, @id_18, 20.02),
  (11, @id_18, 9.12),
  (12, @id_18, 9.00),
  (13, @id_18, 8.50),
  (14, @id_18, 44.40),
  (15, @id_18, 52.90),
  (16, @id_18, 47.31),
  (17, @id_18, 3.87),
  (18, @id_18, 30.55),
  (19, @id_18, 14.67),
  (20, @id_18, 5.68),
  (21, @id_18, 11.12),
  (22, @id_18, 34.02),
  (23, @id_18, 8.35),
  (24, @id_18, 5.35),
  (25, @id_18, 9.50),
  (26, @id_18, 23.52),
  (27, @id_18, 6.58),
  (28, @id_18, 14.26),
  (29, @id_18, 13.89),
  (30, @id_18, 14.69),
  (31, @id_18, 8.43),
  (32, @id_18, 25.92),
  (33, @id_18, 18.20),
  (34, @id_18, 16.24),
  (35, @id_18, 10.20),
  (36, @id_18, 5.41);

-- Versao 3.0 | 2024-05-13 → '2024-05-19'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 3.0, '2024-05-13', '2024-05-19', 0);

SET @id_19 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_19, 9.43),
  (2, @id_19, 4.96),
  (3, @id_19, 3.21),
  (4, @id_19, 5.03),
  (5, @id_19, 7.89),
  (6, @id_19, 5.51),
  (7, @id_19, 40.90),
  (8, @id_19, 2.22),
  (9, @id_19, 23.52),
  (10, @id_19, 19.80),
  (11, @id_19, 9.12),
  (12, @id_19, 9.00),
  (13, @id_19, 8.02),
  (14, @id_19, 44.72),
  (15, @id_19, 52.90),
  (16, @id_19, 47.31),
  (17, @id_19, 3.87),
  (18, @id_19, 30.55),
  (19, @id_19, 14.54),
  (20, @id_19, 5.68),
  (21, @id_19, 11.12),
  (22, @id_19, 33.15),
  (23, @id_19, 8.35),
  (24, @id_19, 6.21),
  (25, @id_19, 9.50),
  (26, @id_19, 23.52),
  (27, @id_19, 6.58),
  (28, @id_19, 14.26),
  (29, @id_19, 13.38),
  (30, @id_19, 15.60),
  (31, @id_19, 8.43),
  (32, @id_19, 25.92),
  (33, @id_19, 18.20),
  (34, @id_19, 16.24),
  (35, @id_19, 10.20),
  (36, @id_19, 5.41);

-- Versao 3.1 | 2024-05-20 → '2024-05-26'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 3.1, '2024-05-20', '2024-05-26', 0);

SET @id_20 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_20, 9.82),
  (2, @id_20, 4.96),
  (3, @id_20, 2.81),
  (4, @id_20, 5.03),
  (5, @id_20, 7.79),
  (6, @id_20, 5.28),
  (7, @id_20, 40.90),
  (8, @id_20, 2.22),
  (9, @id_20, 23.52),
  (10, @id_20, 19.94),
  (11, @id_20, 9.12),
  (12, @id_20, 9.00),
  (13, @id_20, 8.02),
  (14, @id_20, 44.72),
  (15, @id_20, 52.90),
  (16, @id_20, 48.15),
  (17, @id_20, 4.10),
  (18, @id_20, 31.37),
  (19, @id_20, 14.54),
  (20, @id_20, 5.68),
  (21, @id_20, 10.98),
  (22, @id_20, 34.46),
  (23, @id_20, 8.35),
  (24, @id_20, 6.21),
  (25, @id_20, 9.50),
  (26, @id_20, 23.52),
  (27, @id_20, 6.58),
  (28, @id_20, 15.05),
  (29, @id_20, 13.21),
  (30, @id_20, 15.60),
  (31, @id_20, 8.62),
  (32, @id_20, 25.92),
  (33, @id_20, 18.20),
  (34, @id_20, 16.24),
  (35, @id_20, 9.62),
  (36, @id_20, 5.41);

-- Versao 3.2 | 2024-05-27 → '2024-06-02'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 3.2, '2024-05-27', '2024-06-02', 0);

SET @id_21 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_21, 10.06),
  (2, @id_21, 6.00),
  (3, @id_21, 3.99),
  (4, @id_21, 5.43),
  (5, @id_21, 7.79),
  (6, @id_21, 5.28),
  (7, @id_21, 40.90),
  (8, @id_21, 2.22),
  (9, @id_21, 23.52),
  (10, @id_21, 19.94),
  (11, @id_21, 9.12),
  (12, @id_21, 9.00),
  (13, @id_21, 8.02),
  (14, @id_21, 44.56),
  (15, @id_21, 52.72),
  (16, @id_21, 48.15),
  (17, @id_21, 4.10),
  (18, @id_21, 31.37),
  (19, @id_21, 14.54),
  (20, @id_21, 5.68),
  (21, @id_21, 10.98),
  (22, @id_21, 34.46),
  (23, @id_21, 8.35),
  (24, @id_21, 6.50),
  (25, @id_21, 9.50),
  (26, @id_21, 22.88),
  (27, @id_21, 6.58),
  (28, @id_21, 15.05),
  (29, @id_21, 13.21),
  (30, @id_21, 15.60),
  (31, @id_21, 8.62),
  (32, @id_21, 25.49),
  (33, @id_21, 18.20),
  (34, @id_21, 15.38),
  (35, @id_21, 8.78),
  (36, @id_21, 6.41);

-- Versao 3.3 | 2024-06-03 → '2024-06-09'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 3.3, '2024-06-03', '2024-06-09', 0);

SET @id_22 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_22, 10.06),
  (2, @id_22, 5.15),
  (3, @id_22, 3.99),
  (4, @id_22, 5.43),
  (5, @id_22, 8.00),
  (6, @id_22, 5.28),
  (7, @id_22, 40.90),
  (8, @id_22, 1.61),
  (9, @id_22, 23.52),
  (10, @id_22, 19.94),
  (11, @id_22, 9.26),
  (12, @id_22, 9.00),
  (13, @id_22, 8.02),
  (14, @id_22, 44.56),
  (15, @id_22, 52.72),
  (16, @id_22, 49.34),
  (17, @id_22, 4.10),
  (18, @id_22, 31.37),
  (19, @id_22, 14.54),
  (20, @id_22, 5.68),
  (21, @id_22, 12.02),
  (22, @id_22, 34.46),
  (23, @id_22, 9.13),
  (24, @id_22, 6.50),
  (25, @id_22, 9.50),
  (26, @id_22, 23.14),
  (27, @id_22, 6.58),
  (28, @id_22, 15.05),
  (29, @id_22, 13.99),
  (30, @id_22, 15.60),
  (31, @id_22, 8.62),
  (32, @id_22, 25.49),
  (33, @id_22, 18.20),
  (34, @id_22, 15.38),
  (35, @id_22, 8.78),
  (36, @id_22, 6.41);

-- Versao 3.4 | 2024-06-10 → '2024-06-16'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 3.4, '2024-06-10', '2024-06-16', 0);

SET @id_23 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_23, 10.06),
  (2, @id_23, 5.15),
  (3, @id_23, 3.99),
  (4, @id_23, 5.78),
  (5, @id_23, 8.00),
  (6, @id_23, 6.00),
  (7, @id_23, 40.07),
  (8, @id_23, 1.61),
  (9, @id_23, 23.38),
  (10, @id_23, 19.94),
  (11, @id_23, 8.38),
  (12, @id_23, 9.00),
  (13, @id_23, 8.02),
  (14, @id_23, 45.24),
  (15, @id_23, 52.72),
  (16, @id_23, 49.34),
  (17, @id_23, 4.10),
  (18, @id_23, 30.71),
  (19, @id_23, 13.61),
  (20, @id_23, 5.68),
  (21, @id_23, 11.70),
  (22, @id_23, 34.46),
  (23, @id_23, 9.13),
  (24, @id_23, 6.50),
  (25, @id_23, 9.50),
  (26, @id_23, 23.14),
  (27, @id_23, 6.58),
  (28, @id_23, 15.05),
  (29, @id_23, 13.99),
  (30, @id_23, 15.60),
  (31, @id_23, 8.62),
  (32, @id_23, 25.08),
  (33, @id_23, 18.20),
  (34, @id_23, 15.07),
  (35, @id_23, 8.78),
  (36, @id_23, 6.41);

-- Versao 3.5 | 2024-06-17 → '2024-06-23'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 3.5, '2024-06-17', '2024-06-23', 0);

SET @id_24 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_24, 11.22),
  (2, @id_24, 5.50),
  (3, @id_24, 4.78),
  (4, @id_24, 6.20),
  (5, @id_24, 8.00),
  (6, @id_24, 5.39),
  (7, @id_24, 41.04),
  (8, @id_24, 0.95),
  (9, @id_24, 23.38),
  (10, @id_24, 19.94),
  (11, @id_24, 8.38),
  (12, @id_24, 9.00),
  (13, @id_24, 8.02),
  (14, @id_24, 45.24),
  (15, @id_24, 52.72),
  (16, @id_24, 49.34),
  (17, @id_24, 4.10),
  (18, @id_24, 30.71),
  (19, @id_24, 13.50),
  (20, @id_24, 4.81),
  (21, @id_24, 12.51),
  (22, @id_24, 34.46),
  (23, @id_24, 9.13),
  (24, @id_24, 6.50),
  (25, @id_24, 9.50),
  (26, @id_24, 22.45),
  (27, @id_24, 6.58),
  (28, @id_24, 15.05),
  (29, @id_24, 13.99),
  (30, @id_24, 15.60),
  (31, @id_24, 8.62),
  (32, @id_24, 24.67),
  (33, @id_24, 18.20),
  (34, @id_24, 15.07),
  (35, @id_24, 8.78),
  (36, @id_24, 6.41);

-- Versao 3.6 | 2024-06-24 → '2024-06-30'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 3.6, '2024-06-24', '2024-06-30', 0);

SET @id_25 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_25, 11.22),
  (2, @id_25, 5.50),
  (3, @id_25, 4.78),
  (4, @id_25, 6.20),
  (5, @id_25, 8.00),
  (6, @id_25, 5.39),
  (7, @id_25, 41.04),
  (8, @id_25, 0.95),
  (9, @id_25, 24.39),
  (10, @id_25, 19.94),
  (11, @id_25, 8.38),
  (12, @id_25, 9.00),
  (13, @id_25, 9.04),
  (14, @id_25, 44.35),
  (15, @id_25, 52.72),
  (16, @id_25, 49.34),
  (17, @id_25, 4.10),
  (18, @id_25, 30.71),
  (19, @id_25, 13.50),
  (20, @id_25, 4.81),
  (21, @id_25, 12.51),
  (22, @id_25, 34.08),
  (23, @id_25, 9.13),
  (24, @id_25, 6.50),
  (25, @id_25, 10.76),
  (26, @id_25, 23.51),
  (27, @id_25, 6.58),
  (28, @id_25, 15.50),
  (29, @id_25, 13.99),
  (30, @id_25, 15.60),
  (31, @id_25, 8.62),
  (32, @id_25, 24.67),
  (33, @id_25, 17.66),
  (34, @id_25, 15.07),
  (35, @id_25, 8.78),
  (36, @id_25, 6.41);

-- Versao 3.7 | 2024-07-01 → '2024-07-07'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 3.7, '2024-07-01', '2024-07-07', 0);

SET @id_26 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_26, 11.03),
  (2, @id_26, 6.00),
  (3, @id_26, 4.65),
  (4, @id_26, 6.20),
  (5, @id_26, 8.00),
  (6, @id_26, 5.39),
  (7, @id_26, 41.04),
  (8, @id_26, 0.16),
  (9, @id_26, 24.14),
  (10, @id_26, 19.94),
  (11, @id_26, 8.38),
  (12, @id_26, 9.00),
  (13, @id_26, 9.04),
  (14, @id_26, 44.35),
  (15, @id_26, 53.45),
  (16, @id_26, 49.34),
  (17, @id_26, 4.10),
  (18, @id_26, 30.19),
  (19, @id_26, 13.50),
  (20, @id_26, 3.82),
  (21, @id_26, 12.51),
  (22, @id_26, 34.08),
  (23, @id_26, 9.13),
  (24, @id_26, 6.50),
  (25, @id_26, 9.92),
  (26, @id_26, 23.29),
  (27, @id_26, 6.58),
  (28, @id_26, 15.50),
  (29, @id_26, 13.99),
  (30, @id_26, 15.60),
  (31, @id_26, 7.75),
  (32, @id_26, 24.67),
  (33, @id_26, 18.14),
  (34, @id_26, 14.84),
  (35, @id_26, 8.78),
  (36, @id_26, 6.11);

-- Versao 3.8 | 2024-07-08 → '2024-07-14'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 3.8, '2024-07-08', '2024-07-14', 0);

SET @id_27 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_27, 11.03),
  (2, @id_27, 6.00),
  (3, @id_27, 4.65),
  (4, @id_27, 6.20),
  (5, @id_27, 8.00),
  (6, @id_27, 5.39),
  (7, @id_27, 40.60),
  (8, @id_27, 0.16),
  (9, @id_27, 23.28),
  (10, @id_27, 21.15),
  (11, @id_27, 8.38),
  (12, @id_27, 9.00),
  (13, @id_27, 8.31),
  (14, @id_27, 44.35),
  (15, @id_27, 54.18),
  (16, @id_27, 49.34),
  (17, @id_27, 4.10),
  (18, @id_27, 30.19),
  (19, @id_27, 13.50),
  (20, @id_27, 4.66),
  (21, @id_27, 12.51),
  (22, @id_27, 34.08),
  (23, @id_27, 9.13),
  (24, @id_27, 6.50),
  (25, @id_27, 9.92),
  (26, @id_27, 24.03),
  (27, @id_27, 6.58),
  (28, @id_27, 14.86),
  (29, @id_27, 13.99),
  (30, @id_27, 15.60),
  (31, @id_27, 7.75),
  (32, @id_27, 24.67),
  (33, @id_27, 18.14),
  (34, @id_27, 15.28),
  (35, @id_27, 8.78),
  (36, @id_27, 6.33);

-- Versao 3.9 | 2024-07-15 → '2024-07-21'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 3.9, '2024-07-15', '2024-07-21', 0);

SET @id_28 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_28, 10.87),
  (2, @id_28, 6.00),
  (3, @id_28, 4.65),
  (4, @id_28, 5.60),
  (5, @id_28, 8.00),
  (6, @id_28, 5.39),
  (7, @id_28, 42.07),
  (8, @id_28, 0.16),
  (9, @id_28, 23.28),
  (10, @id_28, 21.73),
  (11, @id_28, 9.36),
  (12, @id_28, 9.00),
  (13, @id_28, 8.31),
  (14, @id_28, 44.35),
  (15, @id_28, 54.18),
  (16, @id_28, 49.08),
  (17, @id_28, 4.10),
  (18, @id_28, 31.17),
  (19, @id_28, 13.50),
  (20, @id_28, 5.40),
  (21, @id_28, 12.51),
  (22, @id_28, 34.08),
  (23, @id_28, 9.13),
  (24, @id_28, 6.50),
  (25, @id_28, 9.50),
  (26, @id_28, 23.44),
  (27, @id_28, 6.58),
  (28, @id_28, 15.23),
  (29, @id_28, 13.99),
  (30, @id_28, 15.60),
  (31, @id_28, 7.75),
  (32, @id_28, 24.67),
  (33, @id_28, 18.14),
  (34, @id_28, 14.51),
  (35, @id_28, 8.33),
  (36, @id_28, 5.78);

-- Versao 4.0 | 2024-07-22 → '2024-07-28'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 4.0, '2024-07-22', '2024-07-28', 0);

SET @id_29 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_29, 10.87),
  (2, @id_29, 5.43),
  (3, @id_29, 3.90),
  (4, @id_29, 5.60),
  (5, @id_29, 8.00),
  (6, @id_29, 5.39),
  (7, @id_29, 42.07),
  (8, @id_29, 0.16),
  (9, @id_29, 23.81),
  (10, @id_29, 22.43),
  (11, @id_29, 9.95),
  (12, @id_29, 9.00),
  (13, @id_29, 7.89),
  (14, @id_29, 44.00),
  (15, @id_29, 53.23),
  (16, @id_29, 49.08),
  (17, @id_29, 4.10),
  (18, @id_29, 30.46),
  (19, @id_29, 13.50),
  (20, @id_29, 6.59),
  (21, @id_29, 12.51),
  (22, @id_29, 34.08),
  (23, @id_29, 9.74),
  (24, @id_29, 6.50),
  (25, @id_29, 9.50),
  (26, @id_29, 24.49),
  (27, @id_29, 6.15),
  (28, @id_29, 15.50),
  (29, @id_29, 13.99),
  (30, @id_29, 15.60),
  (31, @id_29, 8.20),
  (32, @id_29, 24.67),
  (33, @id_29, 18.20),
  (34, @id_29, 14.51),
  (35, @id_29, 8.33),
  (36, @id_29, 5.78);

-- Versao 4.1 | 2024-07-29 → '2024-08-04'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 4.1, '2024-07-29', '2024-08-04', 0);

SET @id_30 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_30, 10.87),
  (2, @id_30, 5.43),
  (3, @id_30, 3.54),
  (4, @id_30, 5.06),
  (5, @id_30, 8.00),
  (6, @id_30, 4.88),
  (7, @id_30, 41.57),
  (8, @id_30, 0.16),
  (9, @id_30, 25.09),
  (10, @id_30, 22.43),
  (11, @id_30, 9.95),
  (12, @id_30, 9.00),
  (13, @id_30, 7.89),
  (14, @id_30, 43.13),
  (15, @id_30, 54.63),
  (16, @id_30, 50.29),
  (17, @id_30, 4.10),
  (18, @id_30, 30.46),
  (19, @id_30, 13.50),
  (20, @id_30, 6.59),
  (21, @id_30, 12.65),
  (22, @id_30, 34.08),
  (23, @id_30, 9.74),
  (24, @id_30, 5.90),
  (25, @id_30, 9.72),
  (26, @id_30, 24.49),
  (27, @id_30, 5.40),
  (28, @id_30, 15.50),
  (29, @id_30, 15.24),
  (30, @id_30, 15.60),
  (31, @id_30, 7.52),
  (32, @id_30, 24.67),
  (33, @id_30, 18.20),
  (34, @id_30, 13.71),
  (35, @id_30, 8.68),
  (36, @id_30, 5.29);

-- Versao 4.2 | 2024-08-05 → '2024-08-11'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 4.2, '2024-08-05', '2024-08-11', 0);

SET @id_31 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_31, 10.72),
  (2, @id_31, 5.43),
  (3, @id_31, 3.54),
  (4, @id_31, 5.06),
  (5, @id_31, 8.00),
  (6, @id_31, 5.46),
  (7, @id_31, 40.66),
  (8, @id_31, 0.10),
  (9, @id_31, 25.96),
  (10, @id_31, 22.43),
  (11, @id_31, 9.95),
  (12, @id_31, 9.00),
  (13, @id_31, 7.89),
  (14, @id_31, 43.13),
  (15, @id_31, 54.39),
  (16, @id_31, 51.40),
  (17, @id_31, 4.10),
  (18, @id_31, 30.46),
  (19, @id_31, 13.50),
  (20, @id_31, 6.59),
  (21, @id_31, 13.56),
  (22, @id_31, 35.25),
  (23, @id_31, 9.74),
  (24, @id_31, 6.50),
  (25, @id_31, 10.23),
  (26, @id_31, 23.96),
  (27, @id_31, 4.68),
  (28, @id_31, 15.50),
  (29, @id_31, 15.24),
  (30, @id_31, 15.60),
  (31, @id_31, 7.52),
  (32, @id_31, 24.67),
  (33, @id_31, 18.20),
  (34, @id_31, 14.25),
  (35, @id_31, 9.81),
  (36, @id_31, 4.31);

-- Versao 4.3 | 2024-08-12 → '2024-08-18'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 4.3, '2024-08-12', '2024-08-18', 0);

SET @id_32 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_32, 10.72),
  (2, @id_32, 5.43),
  (3, @id_32, 3.54),
  (4, @id_32, 5.06),
  (5, @id_32, 8.00),
  (6, @id_32, 5.46),
  (7, @id_32, 40.66),
  (8, @id_32, 0.10),
  (9, @id_32, 25.96),
  (10, @id_32, 22.43),
  (11, @id_32, 10.40),
  (12, @id_32, 8.76),
  (13, @id_32, 7.89),
  (14, @id_32, 43.13),
  (15, @id_32, 54.39),
  (16, @id_32, 52.00),
  (17, @id_32, 4.10),
  (18, @id_32, 30.46),
  (19, @id_32, 13.50),
  (20, @id_32, 6.59),
  (21, @id_32, 13.01),
  (22, @id_32, 34.96),
  (23, @id_32, 9.74),
  (24, @id_32, 6.50),
  (25, @id_32, 10.23),
  (26, @id_32, 23.27),
  (27, @id_32, 4.68),
  (28, @id_32, 15.50),
  (29, @id_32, 14.75),
  (30, @id_32, 14.85),
  (31, @id_32, 8.13),
  (32, @id_32, 24.67),
  (33, @id_32, 18.20),
  (34, @id_32, 14.25),
  (35, @id_32, 9.81),
  (36, @id_32, 4.31);

-- Versao 4.4 | 2024-08-19 → '2024-08-25'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 4.4, '2024-08-19', '2024-08-25', 0);

SET @id_33 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_33, 10.72),
  (2, @id_33, 5.92),
  (3, @id_33, 2.83),
  (4, @id_33, 5.06),
  (5, @id_33, 7.55),
  (6, @id_33, 6.00),
  (7, @id_33, 39.88),
  (8, @id_33, 0.10),
  (9, @id_33, 25.96),
  (10, @id_33, 22.43),
  (11, @id_33, 10.40),
  (12, @id_33, 8.76),
  (13, @id_33, 7.89),
  (14, @id_33, 43.13),
  (15, @id_33, 54.39),
  (16, @id_33, 52.00),
  (17, @id_33, 4.10),
  (18, @id_33, 30.79),
  (19, @id_33, 13.50),
  (20, @id_33, 6.59),
  (21, @id_33, 13.01),
  (22, @id_33, 34.38),
  (23, @id_33, 10.26),
  (24, @id_33, 6.07),
  (25, @id_33, 11.70),
  (26, @id_33, 23.27),
  (27, @id_33, 5.80),
  (28, @id_33, 15.50),
  (29, @id_33, 14.75),
  (30, @id_33, 14.05),
  (31, @id_33, 9.04),
  (32, @id_33, 25.59),
  (33, @id_33, 18.20),
  (34, @id_33, 15.22),
  (35, @id_33, 9.58),
  (36, @id_33, 4.45);

-- Versao 4.5 | 2024-08-26 → '2024-09-01'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 4.5, '2024-08-26', '2024-09-01', 0);

SET @id_34 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_34, 10.52),
  (2, @id_34, 5.92),
  (3, @id_34, 2.83),
  (4, @id_34, 5.06),
  (5, @id_34, 8.00),
  (6, @id_34, 6.00),
  (7, @id_34, 39.12),
  (8, @id_34, 0.10),
  (9, @id_34, 25.96),
  (10, @id_34, 22.43),
  (11, @id_34, 10.40),
  (12, @id_34, 9.00),
  (13, @id_34, 7.89),
  (14, @id_34, 43.13),
  (15, @id_34, 53.98),
  (16, @id_34, 52.00),
  (17, @id_34, 4.10),
  (18, @id_34, 31.86),
  (19, @id_34, 13.87),
  (20, @id_34, 6.59),
  (21, @id_34, 13.01),
  (22, @id_34, 34.38),
  (23, @id_34, 9.89),
  (24, @id_34, 6.07),
  (25, @id_34, 11.70),
  (26, @id_34, 23.81),
  (27, @id_34, 5.80),
  (28, @id_34, 14.68),
  (29, @id_34, 14.75),
  (30, @id_34, 14.05),
  (31, @id_34, 9.20),
  (32, @id_34, 24.75),
  (33, @id_34, 17.38),
  (34, @id_34, 15.22),
  (35, @id_34, 9.58),
  (36, @id_34, 5.03);

-- Versao 4.6 | 2024-09-02 → '2024-09-08'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 4.6, '2024-09-02', '2024-09-08', 0);

SET @id_35 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_35, 10.52),
  (2, @id_35, 5.92),
  (3, @id_35, 2.83),
  (4, @id_35, 5.06),
  (5, @id_35, 8.00),
  (6, @id_35, 5.27),
  (7, @id_35, 39.12),
  (8, @id_35, 1.02),
  (9, @id_35, 25.96),
  (10, @id_35, 23.00),
  (11, @id_35, 10.57),
  (12, @id_35, 9.00),
  (13, @id_35, 8.13),
  (14, @id_35, 43.24),
  (15, @id_35, 53.98),
  (16, @id_35, 51.53),
  (17, @id_35, 3.21),
  (18, @id_35, 31.86),
  (19, @id_35, 13.87),
  (20, @id_35, 6.20),
  (21, @id_35, 13.60),
  (22, @id_35, 34.38),
  (23, @id_35, 9.89),
  (24, @id_35, 6.07),
  (25, @id_35, 13.11),
  (26, @id_35, 23.55),
  (27, @id_35, 5.54),
  (28, @id_35, 13.68),
  (29, @id_35, 14.75),
  (30, @id_35, 14.05),
  (31, @id_35, 8.23),
  (32, @id_35, 23.86),
  (33, @id_35, 17.38),
  (34, @id_35, 15.22),
  (35, @id_35, 9.58),
  (36, @id_35, 4.67);

-- Versao 4.7 | 2024-09-09 → '2024-09-15'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 4.7, '2024-09-09', '2024-09-15', 0);

SET @id_36 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_36, 9.93),
  (2, @id_36, 5.92),
  (3, @id_36, 1.96),
  (4, @id_36, 5.06),
  (5, @id_36, 8.00),
  (6, @id_36, 5.27),
  (7, @id_36, 39.12),
  (8, @id_36, 1.02),
  (9, @id_36, 25.96),
  (10, @id_36, 23.00),
  (11, @id_36, 11.78),
  (12, @id_36, 9.00),
  (13, @id_36, 8.48),
  (14, @id_36, 42.33),
  (15, @id_36, 53.42),
  (16, @id_36, 51.53),
  (17, @id_36, 3.57),
  (18, @id_36, 31.86),
  (19, @id_36, 13.87),
  (20, @id_36, 6.20),
  (21, @id_36, 13.60),
  (22, @id_36, 34.38),
  (23, @id_36, 11.33),
  (24, @id_36, 6.07),
  (25, @id_36, 13.23),
  (26, @id_36, 24.62),
  (27, @id_36, 5.54),
  (28, @id_36, 13.68),
  (29, @id_36, 14.75),
  (30, @id_36, 14.05),
  (31, @id_36, 8.23),
  (32, @id_36, 23.13),
  (33, @id_36, 17.38),
  (34, @id_36, 15.22),
  (35, @id_36, 9.21),
  (36, @id_36, 5.08);

-- Versao 4.8 | 2024-09-16 → '2024-09-22'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 4.8, '2024-09-16', '2024-09-22', 0);

SET @id_37 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_37, 9.93),
  (2, @id_37, 5.92),
  (3, @id_37, 2.91),
  (4, @id_37, 5.06),
  (5, @id_37, 8.00),
  (6, @id_37, 5.27),
  (7, @id_37, 39.12),
  (8, @id_37, 1.43),
  (9, @id_37, 25.96),
  (10, @id_37, 23.00),
  (11, @id_37, 11.78),
  (12, @id_37, 9.00),
  (13, @id_37, 8.69),
  (14, @id_37, 42.33),
  (15, @id_37, 53.42),
  (16, @id_37, 51.53),
  (17, @id_37, 3.57),
  (18, @id_37, 31.86),
  (19, @id_37, 13.87),
  (20, @id_37, 6.20),
  (21, @id_37, 13.60),
  (22, @id_37, 34.38),
  (23, @id_37, 11.33),
  (24, @id_37, 6.07),
  (25, @id_37, 13.23),
  (26, @id_37, 24.62),
  (27, @id_37, 5.03),
  (28, @id_37, 13.68),
  (29, @id_37, 14.75),
  (30, @id_37, 14.05),
  (31, @id_37, 8.23),
  (32, @id_37, 23.00),
  (33, @id_37, 17.38),
  (34, @id_37, 14.45),
  (35, @id_37, 9.21),
  (36, @id_37, 5.08);

-- Versao 4.9 | 2024-09-23 → '2024-09-29'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 4.9, '2024-09-23', '2024-09-29', 0);

SET @id_38 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_38, 9.93),
  (2, @id_38, 5.92),
  (3, @id_38, 1.97),
  (4, @id_38, 5.06),
  (5, @id_38, 7.87),
  (6, @id_38, 5.27),
  (7, @id_38, 40.29),
  (8, @id_38, 0.75),
  (9, @id_38, 25.96),
  (10, @id_38, 22.56),
  (11, @id_38, 10.86),
  (12, @id_38, 9.00),
  (13, @id_38, 9.72),
  (14, @id_38, 42.33),
  (15, @id_38, 54.01),
  (16, @id_38, 51.53),
  (17, @id_38, 3.57),
  (18, @id_38, 31.86),
  (19, @id_38, 14.28),
  (20, @id_38, 6.20),
  (21, @id_38, 13.60),
  (22, @id_38, 34.38),
  (23, @id_38, 12.19),
  (24, @id_38, 6.07),
  (25, @id_38, 12.91),
  (26, @id_38, 24.62),
  (27, @id_38, 5.03),
  (28, @id_38, 12.93),
  (29, @id_38, 14.75),
  (30, @id_38, 13.57),
  (31, @id_38, 9.20),
  (32, @id_38, 23.00),
  (33, @id_38, 17.38),
  (34, @id_38, 14.45),
  (35, @id_38, 9.21),
  (36, @id_38, 5.08);

-- Versao 5.0 | 2024-09-30 → '2024-10-06'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 5.0, '2024-09-30', '2024-10-06', 0);

SET @id_39 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_39, 10.10),
  (2, @id_39, 6.00),
  (3, @id_39, 1.97),
  (4, @id_39, 5.06),
  (5, @id_39, 7.72),
  (6, @id_39, 5.27),
  (7, @id_39, 40.29),
  (8, @id_39, 0.10),
  (9, @id_39, 25.96),
  (10, @id_39, 22.56),
  (11, @id_39, 11.41),
  (12, @id_39, 9.00),
  (13, @id_39, 9.72),
  (14, @id_39, 42.74),
  (15, @id_39, 54.01),
  (16, @id_39, 51.53),
  (17, @id_39, 2.68),
  (18, @id_39, 31.20),
  (19, @id_39, 14.28),
  (20, @id_39, 6.20),
  (21, @id_39, 12.65),
  (22, @id_39, 35.58),
  (23, @id_39, 12.19),
  (24, @id_39, 6.07),
  (25, @id_39, 12.91),
  (26, @id_39, 24.62),
  (27, @id_39, 5.79),
  (28, @id_39, 12.56),
  (29, @id_39, 15.98),
  (30, @id_39, 12.96),
  (31, @id_39, 8.60),
  (32, @id_39, 23.00),
  (33, @id_39, 17.38),
  (34, @id_39, 14.45),
  (35, @id_39, 10.20),
  (36, @id_39, 4.90);

-- Versao 5.1 | 2024-10-07 → '2024-10-13'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 5.1, '2024-10-07', '2024-10-13', 0);

SET @id_40 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_40, 10.47),
  (2, @id_40, 6.00),
  (3, @id_40, 2.91),
  (4, @id_40, 4.79),
  (5, @id_40, 8.00),
  (6, @id_40, 5.27),
  (7, @id_40, 40.29),
  (8, @id_40, 0.10),
  (9, @id_40, 25.85),
  (10, @id_40, 21.59),
  (11, @id_40, 11.41),
  (12, @id_40, 8.47),
  (13, @id_40, 9.72),
  (14, @id_40, 43.13),
  (15, @id_40, 53.41),
  (16, @id_40, 52.00),
  (17, @id_40, 2.68),
  (18, @id_40, 31.20),
  (19, @id_40, 14.28),
  (20, @id_40, 6.20),
  (21, @id_40, 12.65),
  (22, @id_40, 35.58),
  (23, @id_40, 11.67),
  (24, @id_40, 5.15),
  (25, @id_40, 12.91),
  (26, @id_40, 24.62),
  (27, @id_40, 5.79),
  (28, @id_40, 12.56),
  (29, @id_40, 15.98),
  (30, @id_40, 12.96),
  (31, @id_40, 7.98),
  (32, @id_40, 23.00),
  (33, @id_40, 17.38),
  (34, @id_40, 14.45),
  (35, @id_40, 10.20),
  (36, @id_40, 4.35);

-- Versao 5.2 | 2024-10-14 → '2024-10-20'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 5.2, '2024-10-14', '2024-10-20', 0);

SET @id_41 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_41, 11.53),
  (2, @id_41, 6.00),
  (3, @id_41, 1.94),
  (4, @id_41, 5.58),
  (5, @id_41, 8.00),
  (6, @id_41, 5.61),
  (7, @id_41, 41.07),
  (8, @id_41, 0.10),
  (9, @id_41, 26.03),
  (10, @id_41, 21.59),
  (11, @id_41, 10.46),
  (12, @id_41, 9.00),
  (13, @id_41, 9.72),
  (14, @id_41, 42.82),
  (15, @id_41, 53.52),
  (16, @id_41, 52.00),
  (17, @id_41, 2.68),
  (18, @id_41, 32.23),
  (19, @id_41, 15.06),
  (20, @id_41, 6.20),
  (21, @id_41, 12.65),
  (22, @id_41, 35.58),
  (23, @id_41, 11.67),
  (24, @id_41, 5.15),
  (25, @id_41, 12.91),
  (26, @id_41, 24.50),
  (27, @id_41, 5.08),
  (28, @id_41, 11.64),
  (29, @id_41, 15.98),
  (30, @id_41, 12.96),
  (31, @id_41, 7.98),
  (32, @id_41, 23.00),
  (33, @id_41, 17.38),
  (34, @id_41, 14.11),
  (35, @id_41, 10.20),
  (36, @id_41, 4.35);

-- Versao 5.3 | 2024-10-21 → '2024-10-27'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 5.3, '2024-10-21', '2024-10-27', 0);

SET @id_42 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_42, 11.53),
  (2, @id_42, 6.00),
  (3, @id_42, 1.64),
  (4, @id_42, 6.20),
  (5, @id_42, 8.00),
  (6, @id_42, 5.84),
  (7, @id_42, 41.07),
  (8, @id_42, 0.10),
  (9, @id_42, 26.03),
  (10, @id_42, 21.59),
  (11, @id_42, 10.46),
  (12, @id_42, 9.00),
  (13, @id_42, 9.72),
  (14, @id_42, 43.71),
  (15, @id_42, 53.33),
  (16, @id_42, 52.00),
  (17, @id_42, 2.68),
  (18, @id_42, 32.23),
  (19, @id_42, 14.16),
  (20, @id_42, 6.20),
  (21, @id_42, 12.65),
  (22, @id_42, 35.58),
  (23, @id_42, 10.76),
  (24, @id_42, 5.15),
  (25, @id_42, 12.91),
  (26, @id_42, 24.12),
  (27, @id_42, 4.45),
  (28, @id_42, 12.74),
  (29, @id_42, 15.98),
  (30, @id_42, 12.96),
  (31, @id_42, 7.98),
  (32, @id_42, 23.00),
  (33, @id_42, 16.93),
  (34, @id_42, 13.71),
  (35, @id_42, 10.20),
  (36, @id_42, 3.60);

-- Versao 5.4 | 2024-10-28 → '2024-11-03'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 5.4, '2024-10-28', '2024-11-03', 0);

SET @id_43 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_43, 11.53),
  (2, @id_43, 5.85),
  (3, @id_43, 1.64),
  (4, @id_43, 5.79),
  (5, @id_43, 8.00),
  (6, @id_43, 5.84),
  (7, @id_43, 41.21),
  (8, @id_43, 0.10),
  (9, @id_43, 25.49),
  (10, @id_43, 21.59),
  (11, @id_43, 9.97),
  (12, @id_43, 9.00),
  (13, @id_43, 9.72),
  (14, @id_43, 44.93),
  (15, @id_43, 53.54),
  (16, @id_43, 52.00),
  (17, @id_43, 3.67),
  (18, @id_43, 32.77),
  (19, @id_43, 14.16),
  (20, @id_43, 6.20),
  (21, @id_43, 12.65),
  (22, @id_43, 35.58),
  (23, @id_43, 10.76),
  (24, @id_43, 5.15),
  (25, @id_43, 12.91),
  (26, @id_43, 24.12),
  (27, @id_43, 4.45),
  (28, @id_43, 12.74),
  (29, @id_43, 15.98),
  (30, @id_43, 12.96),
  (31, @id_43, 7.98),
  (32, @id_43, 23.00),
  (33, @id_43, 16.93),
  (34, @id_43, 13.71),
  (35, @id_43, 10.20),
  (36, @id_43, 3.60);

-- Versao 5.5 | 2024-11-04 → '2024-11-10'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 5.5, '2024-11-04', '2024-11-10', 0);

SET @id_44 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_44, 11.53),
  (2, @id_44, 4.94),
  (3, @id_44, 1.03),
  (4, @id_44, 5.79),
  (5, @id_44, 8.00),
  (6, @id_44, 5.84),
  (7, @id_44, 41.21),
  (8, @id_44, 0.10),
  (9, @id_44, 25.49),
  (10, @id_44, 21.59),
  (11, @id_44, 9.97),
  (12, @id_44, 9.00),
  (13, @id_44, 9.72),
  (14, @id_44, 44.93),
  (15, @id_44, 53.54),
  (16, @id_44, 52.00),
  (17, @id_44, 3.67),
  (18, @id_44, 32.77),
  (19, @id_44, 14.16),
  (20, @id_44, 5.80),
  (21, @id_44, 13.60),
  (22, @id_44, 35.58),
  (23, @id_44, 10.76),
  (24, @id_44, 4.28),
  (25, @id_44, 12.69),
  (26, @id_44, 24.12),
  (27, @id_44, 4.20),
  (28, @id_44, 12.74),
  (29, @id_44, 15.01),
  (30, @id_44, 12.96),
  (31, @id_44, 8.77),
  (32, @id_44, 23.00),
  (33, @id_44, 16.93),
  (34, @id_44, 13.71),
  (35, @id_44, 9.75),
  (36, @id_44, 3.60);

-- Versao 5.6 | 2024-11-11 → '2024-11-17'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 5.6, '2024-11-11', '2024-11-17', 0);

SET @id_45 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_45, 11.53),
  (2, @id_45, 4.94),
  (3, @id_45, 1.03),
  (4, @id_45, 6.20),
  (5, @id_45, 7.48),
  (6, @id_45, 6.00),
  (7, @id_45, 41.21),
  (8, @id_45, 1.35),
  (9, @id_45, 26.16),
  (10, @id_45, 21.59),
  (11, @id_45, 10.41),
  (12, @id_45, 9.00),
  (13, @id_45, 9.92),
  (14, @id_45, 44.40),
  (15, @id_45, 53.54),
  (16, @id_45, 52.00),
  (17, @id_45, 2.95),
  (18, @id_45, 32.77),
  (19, @id_45, 14.76),
  (20, @id_45, 5.22),
  (21, @id_45, 13.60),
  (22, @id_45, 36.00),
  (23, @id_45, 10.76),
  (24, @id_45, 4.44),
  (25, @id_45, 11.98),
  (26, @id_45, 24.12),
  (27, @id_45, 5.65),
  (28, @id_45, 12.74),
  (29, @id_45, 15.01),
  (30, @id_45, 12.96),
  (31, @id_45, 8.77),
  (32, @id_45, 23.00),
  (33, @id_45, 16.93),
  (34, @id_45, 13.71),
  (35, @id_45, 9.75),
  (36, @id_45, 3.60);

-- Versao 5.7 | 2024-11-18 → '2024-11-24'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 5.7, '2024-11-18', '2024-11-24', 0);

SET @id_46 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_46, 12.45),
  (2, @id_46, 4.94),
  (3, @id_46, 1.03),
  (4, @id_46, 6.20),
  (5, @id_46, 7.48),
  (6, @id_46, 6.00),
  (7, @id_46, 41.01),
  (8, @id_46, 1.12),
  (9, @id_46, 26.16),
  (10, @id_46, 21.59),
  (11, @id_46, 10.41),
  (12, @id_46, 9.00),
  (13, @id_46, 9.92),
  (14, @id_46, 44.40),
  (15, @id_46, 54.09),
  (16, @id_46, 52.00),
  (17, @id_46, 2.75),
  (18, @id_46, 32.09),
  (19, @id_46, 15.09),
  (20, @id_46, 4.90),
  (21, @id_46, 13.60),
  (22, @id_46, 36.00),
  (23, @id_46, 10.76),
  (24, @id_46, 4.13),
  (25, @id_46, 11.98),
  (26, @id_46, 24.12),
  (27, @id_46, 6.35),
  (28, @id_46, 12.74),
  (29, @id_46, 15.39),
  (30, @id_46, 12.96),
  (31, @id_46, 8.77),
  (32, @id_46, 23.39),
  (33, @id_46, 18.16),
  (34, @id_46, 13.71),
  (35, @id_46, 9.75),
  (36, @id_46, 3.33);

-- Versao 5.8 | 2024-11-25 → '2024-12-01'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 5.8, '2024-11-25', '2024-12-01', 0);

SET @id_47 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_47, 12.45),
  (2, @id_47, 4.94),
  (3, @id_47, 1.03),
  (4, @id_47, 5.63),
  (5, @id_47, 8.00),
  (6, @id_47, 6.00),
  (7, @id_47, 41.01),
  (8, @id_47, 0.46),
  (9, @id_47, 25.42),
  (10, @id_47, 21.59),
  (11, @id_47, 10.63),
  (12, @id_47, 9.00),
  (13, @id_47, 9.92),
  (14, @id_47, 44.40),
  (15, @id_47, 54.09),
  (16, @id_47, 52.00),
  (17, @id_47, 2.75),
  (18, @id_47, 32.09),
  (19, @id_47, 15.09),
  (20, @id_47, 4.90),
  (21, @id_47, 13.60),
  (22, @id_47, 36.00),
  (23, @id_47, 10.76),
  (24, @id_47, 4.13),
  (25, @id_47, 11.98),
  (26, @id_47, 24.12),
  (27, @id_47, 6.35),
  (28, @id_47, 12.74),
  (29, @id_47, 15.39),
  (30, @id_47, 12.96),
  (31, @id_47, 8.77),
  (32, @id_47, 23.00),
  (33, @id_47, 18.16),
  (34, @id_47, 13.71),
  (35, @id_47, 10.20),
  (36, @id_47, 2.79);

-- Versao 5.9 | 2024-12-02 → '2024-12-08'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 5.9, '2024-12-02', '2024-12-08', 0);

SET @id_48 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_48, 12.45),
  (2, @id_48, 4.94),
  (3, @id_48, 1.54),
  (4, @id_48, 5.63),
  (5, @id_48, 8.00),
  (6, @id_48, 6.00),
  (7, @id_48, 41.01),
  (8, @id_48, 1.63),
  (9, @id_48, 25.42),
  (10, @id_48, 21.59),
  (11, @id_48, 10.63),
  (12, @id_48, 9.00),
  (13, @id_48, 9.92),
  (14, @id_48, 44.26),
  (15, @id_48, 54.95),
  (16, @id_48, 51.81),
  (17, @id_48, 1.97),
  (18, @id_48, 32.09),
  (19, @id_48, 14.59),
  (20, @id_48, 4.90),
  (21, @id_48, 13.10),
  (22, @id_48, 35.20),
  (23, @id_48, 10.76),
  (24, @id_48, 3.50),
  (25, @id_48, 11.98),
  (26, @id_48, 25.11),
  (27, @id_48, 6.35),
  (28, @id_48, 12.74),
  (29, @id_48, 15.39),
  (30, @id_48, 12.96),
  (31, @id_48, 8.77),
  (32, @id_48, 23.00),
  (33, @id_48, 18.16),
  (34, @id_48, 13.71),
  (35, @id_48, 10.20),
  (36, @id_48, 3.30);

-- Versao 6.0 | 2024-12-09 → '2024-12-15'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 6.0, '2024-12-09', '2024-12-15', 0);

SET @id_49 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_49, 12.45),
  (2, @id_49, 3.95),
  (3, @id_49, 1.77),
  (4, @id_49, 4.82),
  (5, @id_49, 7.01),
  (6, @id_49, 5.79),
  (7, @id_49, 40.69),
  (8, @id_49, 2.89),
  (9, @id_49, 26.47),
  (10, @id_49, 21.59),
  (11, @id_49, 10.63),
  (12, @id_49, 9.00),
  (13, @id_49, 9.92),
  (14, @id_49, 44.26),
  (15, @id_49, 56.10),
  (16, @id_49, 51.81),
  (17, @id_49, 1.97),
  (18, @id_49, 32.09),
  (19, @id_49, 14.16),
  (20, @id_49, 4.51),
  (21, @id_49, 13.10),
  (22, @id_49, 36.00),
  (23, @id_49, 11.50),
  (24, @id_49, 4.10),
  (25, @id_49, 11.98),
  (26, @id_49, 25.11),
  (27, @id_49, 6.35),
  (28, @id_49, 12.74),
  (29, @id_49, 15.39),
  (30, @id_49, 12.96),
  (31, @id_49, 8.37),
  (32, @id_49, 23.00),
  (33, @id_49, 18.16),
  (34, @id_49, 13.71),
  (35, @id_49, 10.20),
  (36, @id_49, 3.47);

-- Versao 6.1 | 2024-12-16 → '2024-12-22'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 6.1, '2024-12-16', '2024-12-22', 0);

SET @id_50 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_50, 12.45),
  (2, @id_50, 4.69),
  (3, @id_50, 1.77),
  (4, @id_50, 4.32),
  (5, @id_50, 7.01),
  (6, @id_50, 5.51),
  (7, @id_50, 40.97),
  (8, @id_50, 3.21),
  (9, @id_50, 26.12),
  (10, @id_50, 22.04),
  (11, @id_50, 10.63),
  (12, @id_50, 8.75),
  (13, @id_50, 9.92),
  (14, @id_50, 44.26),
  (15, @id_50, 56.10),
  (16, @id_50, 52.00),
  (17, @id_50, 1.97),
  (18, @id_50, 32.09),
  (19, @id_50, 14.16),
  (20, @id_50, 4.51),
  (21, @id_50, 13.21),
  (22, @id_50, 36.00),
  (23, @id_50, 11.02),
  (24, @id_50, 4.10),
  (25, @id_50, 11.98),
  (26, @id_50, 25.11),
  (27, @id_50, 6.59),
  (28, @id_50, 12.74),
  (29, @id_50, 15.17),
  (30, @id_50, 14.11),
  (31, @id_50, 8.37),
  (32, @id_50, 23.00),
  (33, @id_50, 18.16),
  (34, @id_50, 13.71),
  (35, @id_50, 10.20),
  (36, @id_50, 4.45);

-- Versao 6.2 | 2024-12-23 → '2024-12-29'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 6.2, '2024-12-23', '2024-12-29', 0);

SET @id_51 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_51, 12.45),
  (2, @id_51, 4.69),
  (3, @id_51, 3.07),
  (4, @id_51, 4.32),
  (5, @id_51, 7.01),
  (6, @id_51, 6.00),
  (7, @id_51, 40.97),
  (8, @id_51, 2.83),
  (9, @id_51, 26.12),
  (10, @id_51, 22.04),
  (11, @id_51, 10.63),
  (12, @id_51, 8.75),
  (13, @id_51, 10.50),
  (14, @id_51, 45.33),
  (15, @id_51, 55.41),
  (16, @id_51, 52.00),
  (17, @id_51, 1.97),
  (18, @id_51, 32.09),
  (19, @id_51, 14.67),
  (20, @id_51, 4.51),
  (21, @id_51, 12.25),
  (22, @id_51, 36.00),
  (23, @id_51, 11.02),
  (24, @id_51, 4.10),
  (25, @id_51, 11.98),
  (26, @id_51, 25.11),
  (27, @id_51, 5.60),
  (28, @id_51, 12.52),
  (29, @id_51, 14.56),
  (30, @id_51, 14.11),
  (31, @id_51, 9.20),
  (32, @id_51, 23.00),
  (33, @id_51, 18.16),
  (34, @id_51, 13.71),
  (35, @id_51, 10.20),
  (36, @id_51, 4.45);

-- Versao 6.3 | 2024-12-30 → '2025-01-05'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 6.3, '2024-12-30', '2025-01-05', 0);

SET @id_52 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_52, 12.45),
  (2, @id_52, 5.50),
  (3, @id_52, 3.87),
  (4, @id_52, 4.32),
  (5, @id_52, 7.23),
  (6, @id_52, 6.00),
  (7, @id_52, 41.46),
  (8, @id_52, 2.83),
  (9, @id_52, 26.12),
  (10, @id_52, 22.04),
  (11, @id_52, 10.05),
  (12, @id_52, 8.75),
  (13, @id_52, 10.50),
  (14, @id_52, 45.50),
  (15, @id_52, 56.10),
  (16, @id_52, 51.85),
  (17, @id_52, 1.97),
  (18, @id_52, 32.09),
  (19, @id_52, 14.67),
  (20, @id_52, 5.04),
  (21, @id_52, 13.39),
  (22, @id_52, 36.00),
  (23, @id_52, 12.10),
  (24, @id_52, 4.10),
  (25, @id_52, 11.41),
  (26, @id_52, 25.20),
  (27, @id_52, 5.60),
  (28, @id_52, 12.52),
  (29, @id_52, 15.04),
  (30, @id_52, 13.32),
  (31, @id_52, 9.20),
  (32, @id_52, 23.00),
  (33, @id_52, 18.16),
  (34, @id_52, 14.62),
  (35, @id_52, 10.20),
  (36, @id_52, 4.45);

-- Versao 6.4 | 2025-01-06 → '2025-01-12'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 6.4, '2025-01-06', '2025-01-12', 0);

SET @id_53 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_53, 12.45),
  (2, @id_53, 5.50),
  (3, @id_53, 3.87),
  (4, @id_53, 4.32),
  (5, @id_53, 7.23),
  (6, @id_53, 6.00),
  (7, @id_53, 41.46),
  (8, @id_53, 3.25),
  (9, @id_53, 26.12),
  (10, @id_53, 22.04),
  (11, @id_53, 9.73),
  (12, @id_53, 8.75),
  (13, @id_53, 10.50),
  (14, @id_53, 45.50),
  (15, @id_53, 55.23),
  (16, @id_53, 51.85),
  (17, @id_53, 1.20),
  (18, @id_53, 32.09),
  (19, @id_53, 14.67),
  (20, @id_53, 5.04),
  (21, @id_53, 13.60),
  (22, @id_53, 36.00),
  (23, @id_53, 12.10),
  (24, @id_53, 4.10),
  (25, @id_53, 11.15),
  (26, @id_53, 24.22),
  (27, @id_53, 5.44),
  (28, @id_53, 12.52),
  (29, @id_53, 15.04),
  (30, @id_53, 12.93),
  (31, @id_53, 9.20),
  (32, @id_53, 23.00),
  (33, @id_53, 18.16),
  (34, @id_53, 14.62),
  (35, @id_53, 10.20),
  (36, @id_53, 5.20);

-- Versao 6.5 | 2025-01-13 → '2025-01-19'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 6.5, '2025-01-13', '2025-01-19', 0);

SET @id_54 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_54, 12.45),
  (2, @id_54, 5.50),
  (3, @id_54, 4.24),
  (4, @id_54, 3.54),
  (5, @id_54, 7.23),
  (6, @id_54, 6.00),
  (7, @id_54, 41.46),
  (8, @id_54, 3.25),
  (9, @id_54, 26.12),
  (10, @id_54, 21.15),
  (11, @id_54, 9.63),
  (12, @id_54, 9.00),
  (13, @id_54, 10.50),
  (14, @id_54, 45.50),
  (15, @id_54, 55.23),
  (16, @id_54, 51.85),
  (17, @id_54, 1.20),
  (18, @id_54, 31.35),
  (19, @id_54, 14.67),
  (20, @id_54, 4.13),
  (21, @id_54, 13.60),
  (22, @id_54, 36.00),
  (23, @id_54, 11.92),
  (24, @id_54, 4.10),
  (25, @id_54, 11.15),
  (26, @id_54, 24.05),
  (27, @id_54, 5.44),
  (28, @id_54, 12.52),
  (29, @id_54, 14.73),
  (30, @id_54, 12.93),
  (31, @id_54, 9.20),
  (32, @id_54, 23.00),
  (33, @id_54, 18.20),
  (34, @id_54, 14.62),
  (35, @id_54, 10.20),
  (36, @id_54, 5.20);

-- Versao 6.6 | 2025-01-20 → '2025-01-26'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 6.6, '2025-01-20', '2025-01-26', 0);

SET @id_55 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_55, 13.00),
  (2, @id_55, 5.50),
  (3, @id_55, 4.24),
  (4, @id_55, 3.54),
  (5, @id_55, 7.23),
  (6, @id_55, 6.00),
  (7, @id_55, 41.17),
  (8, @id_55, 3.25),
  (9, @id_55, 26.12),
  (10, @id_55, 21.15),
  (11, @id_55, 9.63),
  (12, @id_55, 9.00),
  (13, @id_55, 10.50),
  (14, @id_55, 45.10),
  (15, @id_55, 55.23),
  (16, @id_55, 51.85),
  (17, @id_55, 1.20),
  (18, @id_55, 31.35),
  (19, @id_55, 15.13),
  (20, @id_55, 4.00),
  (21, @id_55, 13.60),
  (22, @id_55, 36.00),
  (23, @id_55, 12.37),
  (24, @id_55, 4.10),
  (25, @id_55, 10.92),
  (26, @id_55, 24.05),
  (27, @id_55, 4.46),
  (28, @id_55, 12.52),
  (29, @id_55, 14.29),
  (30, @id_55, 13.93),
  (31, @id_55, 9.20),
  (32, @id_55, 23.00),
  (33, @id_55, 18.08),
  (34, @id_55, 14.62),
  (35, @id_55, 9.70),
  (36, @id_55, 5.20);

-- Versao 6.7 | 2025-01-27 → '2025-02-02'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 6.7, '2025-01-27', '2025-02-02', 0);

SET @id_56 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_56, 12.15),
  (2, @id_56, 5.50),
  (3, @id_56, 4.24),
  (4, @id_56, 3.54),
  (5, @id_56, 7.23),
  (6, @id_56, 6.00),
  (7, @id_56, 40.71),
  (8, @id_56, 3.25),
  (9, @id_56, 26.46),
  (10, @id_56, 21.15),
  (11, @id_56, 9.63),
  (12, @id_56, 9.00),
  (13, @id_56, 10.37),
  (14, @id_56, 45.50),
  (15, @id_56, 55.23),
  (16, @id_56, 51.85),
  (17, @id_56, 1.20),
  (18, @id_56, 31.35),
  (19, @id_56, 15.13),
  (20, @id_56, 4.00),
  (21, @id_56, 13.60),
  (22, @id_56, 35.43),
  (23, @id_56, 12.50),
  (24, @id_56, 5.59),
  (25, @id_56, 11.52),
  (26, @id_56, 24.05),
  (27, @id_56, 4.46),
  (28, @id_56, 11.97),
  (29, @id_56, 14.29),
  (30, @id_56, 13.93),
  (31, @id_56, 9.20),
  (32, @id_56, 23.00),
  (33, @id_56, 17.28),
  (34, @id_56, 14.62),
  (35, @id_56, 9.12),
  (36, @id_56, 6.70);

-- Versao 6.8 | 2025-02-03 → '2025-02-09'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 6.8, '2025-02-03', '2025-02-09', 0);

SET @id_57 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_57, 11.96),
  (2, @id_57, 5.97),
  (3, @id_57, 3.31),
  (4, @id_57, 3.54),
  (5, @id_57, 6.93),
  (6, @id_57, 5.18),
  (7, @id_57, 40.71),
  (8, @id_57, 2.39),
  (9, @id_57, 26.46),
  (10, @id_57, 21.15),
  (11, @id_57, 8.90),
  (12, @id_57, 8.87),
  (13, @id_57, 10.50),
  (14, @id_57, 45.50),
  (15, @id_57, 55.23),
  (16, @id_57, 51.85),
  (17, @id_57, 0.31),
  (18, @id_57, 31.35),
  (19, @id_57, 15.13),
  (20, @id_57, 4.00),
  (21, @id_57, 13.60),
  (22, @id_57, 35.43),
  (23, @id_57, 12.50),
  (24, @id_57, 5.59),
  (25, @id_57, 11.52),
  (26, @id_57, 23.77),
  (27, @id_57, 4.46),
  (28, @id_57, 13.36),
  (29, @id_57, 13.90),
  (30, @id_57, 13.93),
  (31, @id_57, 9.20),
  (32, @id_57, 23.00),
  (33, @id_57, 16.41),
  (34, @id_57, 14.18),
  (35, @id_57, 8.66),
  (36, @id_57, 6.70);

-- Versao 6.9 | 2025-02-10 → '2025-02-16'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 6.9, '2025-02-10', '2025-02-16', 0);

SET @id_58 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_58, 11.96),
  (2, @id_58, 5.97),
  (3, @id_58, 3.31),
  (4, @id_58, 2.73),
  (5, @id_58, 6.25),
  (6, @id_58, 5.18),
  (7, @id_58, 39.99),
  (8, @id_58, 1.58),
  (9, @id_58, 26.46),
  (10, @id_58, 21.15),
  (11, @id_58, 8.90),
  (12, @id_58, 8.87),
  (13, @id_58, 10.50),
  (14, @id_58, 45.50),
  (15, @id_58, 54.78),
  (16, @id_58, 50.88),
  (17, @id_58, 0.31),
  (18, @id_58, 32.74),
  (19, @id_58, 14.83),
  (20, @id_58, 4.00),
  (21, @id_58, 13.37),
  (22, @id_58, 35.86),
  (23, @id_58, 12.25),
  (24, @id_58, 5.41),
  (25, @id_58, 12.51),
  (26, @id_58, 23.27),
  (27, @id_58, 4.46),
  (28, @id_58, 13.36),
  (29, @id_58, 13.90),
  (30, @id_58, 13.93),
  (31, @id_58, 9.20),
  (32, @id_58, 23.00),
  (33, @id_58, 16.41),
  (34, @id_58, 15.64),
  (35, @id_58, 8.12),
  (36, @id_58, 6.70);

-- Versao 7.0 | 2025-02-17 → '2025-02-23'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 7.0, '2025-02-17', '2025-02-23', 0);

SET @id_59 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_59, 11.96),
  (2, @id_59, 5.97),
  (3, @id_59, 3.31),
  (4, @id_59, 2.04),
  (5, @id_59, 6.58),
  (6, @id_59, 5.18),
  (7, @id_59, 39.62),
  (8, @id_59, 1.58),
  (9, @id_59, 26.29),
  (10, @id_59, 21.15),
  (11, @id_59, 8.90),
  (12, @id_59, 9.00),
  (13, @id_59, 10.32),
  (14, @id_59, 45.50),
  (15, @id_59, 55.56),
  (16, @id_59, 50.88),
  (17, @id_59, 0.10),
  (18, @id_59, 32.50),
  (19, @id_59, 14.83),
  (20, @id_59, 4.00),
  (21, @id_59, 13.37),
  (22, @id_59, 36.00),
  (23, @id_59, 12.50),
  (24, @id_59, 5.41),
  (25, @id_59, 12.51),
  (26, @id_59, 23.27),
  (27, @id_59, 4.46),
  (28, @id_59, 13.12),
  (29, @id_59, 13.90),
  (30, @id_59, 13.93),
  (31, @id_59, 9.20),
  (32, @id_59, 23.94),
  (33, @id_59, 16.41),
  (34, @id_59, 15.64),
  (35, @id_59, 8.12),
  (36, @id_59, 6.70);

-- Versao 7.1 | 2025-02-24 → '2025-03-02'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 7.1, '2025-02-24', '2025-03-02', 0);

SET @id_60 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_60, 11.96),
  (2, @id_60, 5.59),
  (3, @id_60, 3.31),
  (4, @id_60, 2.04),
  (5, @id_60, 6.81),
  (6, @id_60, 5.18),
  (7, @id_60, 39.62),
  (8, @id_60, 1.15),
  (9, @id_60, 26.29),
  (10, @id_60, 21.15),
  (11, @id_60, 8.90),
  (12, @id_60, 9.00),
  (13, @id_60, 10.32),
  (14, @id_60, 45.50),
  (15, @id_60, 56.10),
  (16, @id_60, 50.88),
  (17, @id_60, 0.10),
  (18, @id_60, 32.50),
  (19, @id_60, 14.20),
  (20, @id_60, 4.00),
  (21, @id_60, 12.53),
  (22, @id_60, 36.00),
  (23, @id_60, 12.50),
  (24, @id_60, 4.88),
  (25, @id_60, 11.98),
  (26, @id_60, 23.27),
  (27, @id_60, 4.20),
  (28, @id_60, 13.12),
  (29, @id_60, 13.08),
  (30, @id_60, 14.90),
  (31, @id_60, 9.20),
  (32, @id_60, 24.28),
  (33, @id_60, 15.44),
  (34, @id_60, 15.43),
  (35, @id_60, 8.12),
  (36, @id_60, 6.21);

-- Versao 7.2 | 2025-03-03 → '2025-03-09'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 7.2, '2025-03-03', '2025-03-09', 0);

SET @id_61 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_61, 11.30),
  (2, @id_61, 5.26),
  (3, @id_61, 2.39),
  (4, @id_61, 2.04),
  (5, @id_61, 6.81),
  (6, @id_61, 5.18),
  (7, @id_61, 39.62),
  (8, @id_61, 1.15),
  (9, @id_61, 26.29),
  (10, @id_61, 20.34),
  (11, @id_61, 9.73),
  (12, @id_61, 9.00),
  (13, @id_61, 10.32),
  (14, @id_61, 45.50),
  (15, @id_61, 56.10),
  (16, @id_61, 52.00),
  (17, @id_61, 0.10),
  (18, @id_61, 32.50),
  (19, @id_61, 14.20),
  (20, @id_61, 4.00),
  (21, @id_61, 12.53),
  (22, @id_61, 36.00),
  (23, @id_61, 12.50),
  (24, @id_61, 4.88),
  (25, @id_61, 12.13),
  (26, @id_61, 23.27),
  (27, @id_61, 4.20),
  (28, @id_61, 14.19),
  (29, @id_61, 13.08),
  (30, @id_61, 14.50),
  (31, @id_61, 9.20),
  (32, @id_61, 25.67),
  (33, @id_61, 15.32),
  (34, @id_61, 15.06),
  (35, @id_61, 8.12),
  (36, @id_61, 6.21);

-- Versao 7.3 | 2025-03-10 → '2025-03-16'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 7.3, '2025-03-10', '2025-03-16', 0);

SET @id_62 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_62, 11.54),
  (2, @id_62, 5.04),
  (3, @id_62, 1.89),
  (4, @id_62, 1.70),
  (5, @id_62, 6.81),
  (6, @id_62, 5.54),
  (7, @id_62, 39.62),
  (8, @id_62, 0.38),
  (9, @id_62, 27.60),
  (10, @id_62, 19.78),
  (11, @id_62, 9.73),
  (12, @id_62, 9.00),
  (13, @id_62, 10.32),
  (14, @id_62, 45.50),
  (15, @id_62, 55.21),
  (16, @id_62, 52.00),
  (17, @id_62, 0.10),
  (18, @id_62, 31.65),
  (19, @id_62, 14.20),
  (20, @id_62, 4.00),
  (21, @id_62, 11.80),
  (22, @id_62, 36.00),
  (23, @id_62, 11.95),
  (24, @id_62, 5.15),
  (25, @id_62, 12.13),
  (26, @id_62, 23.27),
  (27, @id_62, 4.20),
  (28, @id_62, 14.19),
  (29, @id_62, 12.45),
  (30, @id_62, 14.50),
  (31, @id_62, 9.20),
  (32, @id_62, 24.90),
  (33, @id_62, 15.82),
  (34, @id_62, 15.06),
  (35, @id_62, 8.12),
  (36, @id_62, 6.21);

-- Versao 7.4 | 2025-03-17 → '2025-03-23'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 7.4, '2025-03-17', '2025-03-23', 0);

SET @id_63 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_63, 10.67),
  (2, @id_63, 6.00),
  (3, @id_63, 1.89),
  (4, @id_63, 1.70),
  (5, @id_63, 6.81),
  (6, @id_63, 6.00),
  (7, @id_63, 39.62),
  (8, @id_63, 0.10),
  (9, @id_63, 27.71),
  (10, @id_63, 19.78),
  (11, @id_63, 9.36),
  (12, @id_63, 8.32),
  (13, @id_63, 10.32),
  (14, @id_63, 45.50),
  (15, @id_63, 55.21),
  (16, @id_63, 52.00),
  (17, @id_63, 0.10),
  (18, @id_63, 32.90),
  (19, @id_63, 14.20),
  (20, @id_63, 4.00),
  (21, @id_63, 11.80),
  (22, @id_63, 36.00),
  (23, @id_63, 11.95),
  (24, @id_63, 6.10),
  (25, @id_63, 12.13),
  (26, @id_63, 23.27),
  (27, @id_63, 4.20),
  (28, @id_63, 13.51),
  (29, @id_63, 12.11),
  (30, @id_63, 15.60),
  (31, @id_63, 9.20),
  (32, @id_63, 24.90),
  (33, @id_63, 17.18),
  (34, @id_63, 14.24),
  (35, @id_63, 9.14),
  (36, @id_63, 7.32);

-- Versao 7.5 | 2025-03-24 → '2025-03-30'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 7.5, '2025-03-24', '2025-03-30', 0);

SET @id_64 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_64, 12.17),
  (2, @id_64, 6.00),
  (3, @id_64, 1.89),
  (4, @id_64, 1.70),
  (5, @id_64, 6.81),
  (6, @id_64, 6.00),
  (7, @id_64, 39.62),
  (8, @id_64, 0.51),
  (9, @id_64, 27.71),
  (10, @id_64, 21.15),
  (11, @id_64, 10.17),
  (12, @id_64, 7.46),
  (13, @id_64, 10.06),
  (14, @id_64, 45.50),
  (15, @id_64, 55.21),
  (16, @id_64, 52.00),
  (17, @id_64, 0.10),
  (18, @id_64, 32.90),
  (19, @id_64, 14.77),
  (20, @id_64, 4.00),
  (21, @id_64, 11.80),
  (22, @id_64, 36.00),
  (23, @id_64, 12.50),
  (24, @id_64, 6.10),
  (25, @id_64, 12.13),
  (26, @id_64, 22.75),
  (27, @id_64, 4.20),
  (28, @id_64, 13.35),
  (29, @id_64, 11.56),
  (30, @id_64, 15.16),
  (31, @id_64, 9.20),
  (32, @id_64, 24.90),
  (33, @id_64, 16.96),
  (34, @id_64, 14.24),
  (35, @id_64, 9.14),
  (36, @id_64, 7.32);

-- Versao 7.6 | 2025-03-31 → '2025-04-06'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 7.6, '2025-03-31', '2025-04-06', 0);

SET @id_65 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_65, 11.66),
  (2, @id_65, 6.00),
  (3, @id_65, 3.08),
  (4, @id_65, 1.70),
  (5, @id_65, 7.16),
  (6, @id_65, 6.00),
  (7, @id_65, 39.62),
  (8, @id_65, 0.85),
  (9, @id_65, 28.00),
  (10, @id_65, 21.37),
  (11, @id_65, 11.16),
  (12, @id_65, 7.46),
  (13, @id_65, 9.62),
  (14, @id_65, 45.50),
  (15, @id_65, 55.21),
  (16, @id_65, 52.00),
  (17, @id_65, 0.10),
  (18, @id_65, 32.90),
  (19, @id_65, 14.50),
  (20, @id_65, 4.98),
  (21, @id_65, 11.80),
  (22, @id_65, 36.00),
  (23, @id_65, 12.50),
  (24, @id_65, 6.10),
  (25, @id_65, 12.13),
  (26, @id_65, 22.75),
  (27, @id_65, 4.20),
  (28, @id_65, 13.35),
  (29, @id_65, 12.49),
  (30, @id_65, 15.16),
  (31, @id_65, 8.20),
  (32, @id_65, 24.90),
  (33, @id_65, 16.54),
  (34, @id_65, 14.24),
  (35, @id_65, 8.21),
  (36, @id_65, 7.32);

-- Versao 7.7 | 2025-04-07 → '2025-04-13'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 7.7, '2025-04-07', '2025-04-13', 0);

SET @id_66 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_66, 11.66),
  (2, @id_66, 6.00),
  (3, @id_66, 4.01),
  (4, @id_66, 1.70),
  (5, @id_66, 7.16),
  (6, @id_66, 6.00),
  (7, @id_66, 39.29),
  (8, @id_66, 0.85),
  (9, @id_66, 28.00),
  (10, @id_66, 20.67),
  (11, @id_66, 11.29),
  (12, @id_66, 8.14),
  (13, @id_66, 9.62),
  (14, @id_66, 45.50),
  (15, @id_66, 55.21),
  (16, @id_66, 52.00),
  (17, @id_66, 1.08),
  (18, @id_66, 32.90),
  (19, @id_66, 15.53),
  (20, @id_66, 4.98),
  (21, @id_66, 12.09),
  (22, @id_66, 35.81),
  (23, @id_66, 12.50),
  (24, @id_66, 6.10),
  (25, @id_66, 12.13),
  (26, @id_66, 22.75),
  (27, @id_66, 4.20),
  (28, @id_66, 13.35),
  (29, @id_66, 12.49),
  (30, @id_66, 15.16),
  (31, @id_66, 8.71),
  (32, @id_66, 24.90),
  (33, @id_66, 16.54),
  (34, @id_66, 14.24),
  (35, @id_66, 7.81),
  (36, @id_66, 7.32);

-- Versao 7.8 | 2025-04-14 → '2025-04-20'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 7.8, '2025-04-14', '2025-04-20', 0);

SET @id_67 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_67, 13.00),
  (2, @id_67, 5.30),
  (3, @id_67, 4.01),
  (4, @id_67, 1.70),
  (5, @id_67, 7.16),
  (6, @id_67, 6.00),
  (7, @id_67, 39.29),
  (8, @id_67, 0.85),
  (9, @id_67, 27.63),
  (10, @id_67, 20.67),
  (11, @id_67, 11.29),
  (12, @id_67, 8.14),
  (13, @id_67, 9.62),
  (14, @id_67, 45.50),
  (15, @id_67, 54.83),
  (16, @id_67, 52.00),
  (17, @id_67, 1.24),
  (18, @id_67, 32.20),
  (19, @id_67, 15.53),
  (20, @id_67, 4.98),
  (21, @id_67, 12.09),
  (22, @id_67, 36.00),
  (23, @id_67, 12.50),
  (24, @id_67, 5.84),
  (25, @id_67, 12.13),
  (26, @id_67, 22.41),
  (27, @id_67, 4.20),
  (28, @id_67, 12.53),
  (29, @id_67, 13.71),
  (30, @id_67, 15.16),
  (31, @id_67, 8.89),
  (32, @id_67, 24.66),
  (33, @id_67, 16.54),
  (34, @id_67, 14.24),
  (35, @id_67, 7.10),
  (36, @id_67, 7.32);

-- Versao 7.9 | 2025-04-21 → '2025-04-27'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 7.9, '2025-04-21', '2025-04-27', 0);

SET @id_68 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_68, 13.00),
  (2, @id_68, 5.30),
  (3, @id_68, 4.01),
  (4, @id_68, 2.97),
  (5, @id_68, 6.94),
  (6, @id_68, 5.80),
  (7, @id_68, 39.29),
  (8, @id_68, 0.85),
  (9, @id_68, 27.63),
  (10, @id_68, 20.67),
  (11, @id_68, 12.34),
  (12, @id_68, 9.00),
  (13, @id_68, 9.62),
  (14, @id_68, 44.80),
  (15, @id_68, 54.83),
  (16, @id_68, 52.00),
  (17, @id_68, 1.47),
  (18, @id_68, 32.65),
  (19, @id_68, 15.53),
  (20, @id_68, 5.62),
  (21, @id_68, 11.49),
  (22, @id_68, 35.85),
  (23, @id_68, 12.50),
  (24, @id_68, 4.97),
  (25, @id_68, 11.96),
  (26, @id_68, 22.41),
  (27, @id_68, 4.20),
  (28, @id_68, 12.38),
  (29, @id_68, 13.71),
  (30, @id_68, 15.60),
  (31, @id_68, 8.89),
  (32, @id_68, 24.66),
  (33, @id_68, 16.04),
  (34, @id_68, 13.74),
  (35, @id_68, 7.10),
  (36, @id_68, 7.32);

-- Versao 8.0 | 2025-04-28 → '2025-05-04'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 8.0, '2025-04-28', '2025-05-04', 0);

SET @id_69 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_69, 13.00),
  (2, @id_69, 5.30),
  (3, @id_69, 4.01),
  (4, @id_69, 2.97),
  (5, @id_69, 6.94),
  (6, @id_69, 6.00),
  (7, @id_69, 39.29),
  (8, @id_69, 0.85),
  (9, @id_69, 27.63),
  (10, @id_69, 22.10),
  (11, @id_69, 12.34),
  (12, @id_69, 9.00),
  (13, @id_69, 9.62),
  (14, @id_69, 44.80),
  (15, @id_69, 54.83),
  (16, @id_69, 52.00),
  (17, @id_69, 2.57),
  (18, @id_69, 32.65),
  (19, @id_69, 15.94),
  (20, @id_69, 5.62),
  (21, @id_69, 10.52),
  (22, @id_69, 35.85),
  (23, @id_69, 12.50),
  (24, @id_69, 5.13),
  (25, @id_69, 11.96),
  (26, @id_69, 22.41),
  (27, @id_69, 5.31),
  (28, @id_69, 12.38),
  (29, @id_69, 14.07),
  (30, @id_69, 15.15),
  (31, @id_69, 8.89),
  (32, @id_69, 25.76),
  (33, @id_69, 16.04),
  (34, @id_69, 13.74),
  (35, @id_69, 6.25),
  (36, @id_69, 7.32);

-- Versao 8.1 | 2025-05-05 → '2025-05-11'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 8.1, '2025-05-05', '2025-05-11', 0);

SET @id_70 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_70, 13.00),
  (2, @id_70, 6.00),
  (3, @id_70, 4.01),
  (4, @id_70, 2.97),
  (5, @id_70, 6.94),
  (6, @id_70, 6.00),
  (7, @id_70, 38.44),
  (8, @id_70, 0.85),
  (9, @id_70, 27.63),
  (10, @id_70, 21.84),
  (11, @id_70, 12.34),
  (12, @id_70, 9.00),
  (13, @id_70, 9.62),
  (14, @id_70, 45.50),
  (15, @id_70, 54.83),
  (16, @id_70, 52.00),
  (17, @id_70, 1.65),
  (18, @id_70, 33.13),
  (19, @id_70, 14.97),
  (20, @id_70, 5.62),
  (21, @id_70, 10.52),
  (22, @id_70, 35.85),
  (23, @id_70, 12.50),
  (24, @id_70, 5.13),
  (25, @id_70, 13.26),
  (26, @id_70, 23.82),
  (27, @id_70, 5.69),
  (28, @id_70, 12.89),
  (29, @id_70, 14.07),
  (30, @id_70, 15.60),
  (31, @id_70, 9.20),
  (32, @id_70, 25.76),
  (33, @id_70, 16.59),
  (34, @id_70, 13.74),
  (35, @id_70, 6.25),
  (36, @id_70, 7.32);

-- Versao 8.2 | 2025-05-12 → '2025-05-18'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 8.2, '2025-05-12', '2025-05-18', 0);

SET @id_71 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_71, 13.00),
  (2, @id_71, 6.00),
  (3, @id_71, 5.29),
  (4, @id_71, 2.97),
  (5, @id_71, 6.11),
  (6, @id_71, 6.00),
  (7, @id_71, 38.44),
  (8, @id_71, 0.85),
  (9, @id_71, 28.00),
  (10, @id_71, 22.17),
  (11, @id_71, 12.34),
  (12, @id_71, 9.00),
  (13, @id_71, 8.90),
  (14, @id_71, 45.50),
  (15, @id_71, 54.98),
  (16, @id_71, 51.81),
  (17, @id_71, 0.88),
  (18, @id_71, 32.36),
  (19, @id_71, 14.97),
  (20, @id_71, 5.62),
  (21, @id_71, 11.74),
  (22, @id_71, 35.85),
  (23, @id_71, 12.50),
  (24, @id_71, 5.13),
  (25, @id_71, 12.28),
  (26, @id_71, 23.82),
  (27, @id_71, 5.69),
  (28, @id_71, 12.89),
  (29, @id_71, 14.07),
  (30, @id_71, 15.60),
  (31, @id_71, 8.35),
  (32, @id_71, 25.03),
  (33, @id_71, 17.75),
  (34, @id_71, 13.74),
  (35, @id_71, 7.44),
  (36, @id_71, 6.96);

-- Versao 8.3 | 2025-05-19 → '2025-05-25'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 8.3, '2025-05-19', '2025-05-25', 0);

SET @id_72 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_72, 13.00),
  (2, @id_72, 6.00),
  (3, @id_72, 5.29),
  (4, @id_72, 2.97),
  (5, @id_72, 6.11),
  (6, @id_72, 6.00),
  (7, @id_72, 38.44),
  (8, @id_72, 0.85),
  (9, @id_72, 28.00),
  (10, @id_72, 22.17),
  (11, @id_72, 12.34),
  (12, @id_72, 9.00),
  (13, @id_72, 8.90),
  (14, @id_72, 45.50),
  (15, @id_72, 54.98),
  (16, @id_72, 51.16),
  (17, @id_72, 1.67),
  (18, @id_72, 32.36),
  (19, @id_72, 14.97),
  (20, @id_72, 6.17),
  (21, @id_72, 11.74),
  (22, @id_72, 35.85),
  (23, @id_72, 12.50),
  (24, @id_72, 5.13),
  (25, @id_72, 12.28),
  (26, @id_72, 23.82),
  (27, @id_72, 5.69),
  (28, @id_72, 12.89),
  (29, @id_72, 14.07),
  (30, @id_72, 15.60),
  (31, @id_72, 8.35),
  (32, @id_72, 25.03),
  (33, @id_72, 18.20),
  (34, @id_72, 13.70),
  (35, @id_72, 7.33),
  (36, @id_72, 7.68);

-- Versao 8.4 | 2025-05-26 → '2025-06-01'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 8.4, '2025-05-26', '2025-06-01', 0);

SET @id_73 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_73, 12.35),
  (2, @id_73, 6.00),
  (3, @id_73, 5.84),
  (4, @id_73, 2.02),
  (5, @id_73, 6.11),
  (6, @id_73, 6.00),
  (7, @id_73, 38.44),
  (8, @id_73, 0.10),
  (9, @id_73, 28.00),
  (10, @id_73, 21.67),
  (11, @id_73, 12.34),
  (12, @id_73, 8.73),
  (13, @id_73, 8.90),
  (14, @id_73, 45.25),
  (15, @id_73, 54.40),
  (16, @id_73, 51.16),
  (17, @id_73, 1.67),
  (18, @id_73, 33.50),
  (19, @id_73, 14.49),
  (20, @id_73, 6.17),
  (21, @id_73, 11.74),
  (22, @id_73, 36.00),
  (23, @id_73, 12.50),
  (24, @id_73, 4.36),
  (25, @id_73, 12.28),
  (26, @id_73, 23.82),
  (27, @id_73, 5.69),
  (28, @id_73, 12.89),
  (29, @id_73, 14.07),
  (30, @id_73, 15.60),
  (31, @id_73, 8.35),
  (32, @id_73, 25.03),
  (33, @id_73, 18.20),
  (34, @id_73, 13.70),
  (35, @id_73, 8.04),
  (36, @id_73, 7.68);

-- Versao 8.5 | 2025-06-02 → '2025-06-08'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 8.5, '2025-06-02', '2025-06-08', 0);

SET @id_74 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_74, 13.00),
  (2, @id_74, 6.00),
  (3, @id_74, 6.76),
  (4, @id_74, 2.22),
  (5, @id_74, 5.91),
  (6, @id_74, 6.00),
  (7, @id_74, 38.44),
  (8, @id_74, 0.81),
  (9, @id_74, 28.00),
  (10, @id_74, 22.43),
  (11, @id_74, 12.34),
  (12, @id_74, 8.73),
  (13, @id_74, 8.90),
  (14, @id_74, 45.50),
  (15, @id_74, 54.17),
  (16, @id_74, 52.00),
  (17, @id_74, 2.79),
  (18, @id_74, 33.50),
  (19, @id_74, 13.89),
  (20, @id_74, 6.17),
  (21, @id_74, 11.74),
  (22, @id_74, 36.00),
  (23, @id_74, 12.50),
  (24, @id_74, 4.36),
  (25, @id_74, 11.91),
  (26, @id_74, 25.18),
  (27, @id_74, 5.69),
  (28, @id_74, 12.89),
  (29, @id_74, 14.89),
  (30, @id_74, 15.60),
  (31, @id_74, 8.35),
  (32, @id_74, 24.76),
  (33, @id_74, 18.20),
  (34, @id_74, 14.99),
  (35, @id_74, 7.10),
  (36, @id_74, 7.16);

-- Versao 8.6 | 2025-06-09 → '2025-06-15'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 8.6, '2025-06-09', '2025-06-15', 0);

SET @id_75 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_75, 13.00),
  (2, @id_75, 6.00),
  (3, @id_75, 6.76),
  (4, @id_75, 2.22),
  (5, @id_75, 6.69),
  (6, @id_75, 6.00),
  (7, @id_75, 38.44),
  (8, @id_75, 1.05),
  (9, @id_75, 28.00),
  (10, @id_75, 22.43),
  (11, @id_75, 12.34),
  (12, @id_75, 9.00),
  (13, @id_75, 8.90),
  (14, @id_75, 45.50),
  (15, @id_75, 54.17),
  (16, @id_75, 52.00),
  (17, @id_75, 2.79),
  (18, @id_75, 33.50),
  (19, @id_75, 13.89),
  (20, @id_75, 6.17),
  (21, @id_75, 12.45),
  (22, @id_75, 35.78),
  (23, @id_75, 12.50),
  (24, @id_75, 4.36),
  (25, @id_75, 11.91),
  (26, @id_75, 25.18),
  (27, @id_75, 5.69),
  (28, @id_75, 12.89),
  (29, @id_75, 16.17),
  (30, @id_75, 15.37),
  (31, @id_75, 8.35),
  (32, @id_75, 24.76),
  (33, @id_75, 18.03),
  (34, @id_75, 14.99),
  (35, @id_75, 7.10),
  (36, @id_75, 7.16);

-- Versao 8.7 | 2025-06-16 → '2025-06-22'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 8.7, '2025-06-16', '2025-06-22', 0);

SET @id_76 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_76, 13.00),
  (2, @id_76, 6.00),
  (3, @id_76, 6.76),
  (4, @id_76, 2.22),
  (5, @id_76, 6.09),
  (6, @id_76, 6.00),
  (7, @id_76, 38.00),
  (8, @id_76, 1.66),
  (9, @id_76, 28.00),
  (10, @id_76, 23.00),
  (11, @id_76, 12.34),
  (12, @id_76, 9.00),
  (13, @id_76, 8.90),
  (14, @id_76, 45.50),
  (15, @id_76, 54.17),
  (16, @id_76, 52.00),
  (17, @id_76, 2.14),
  (18, @id_76, 33.50),
  (19, @id_76, 13.89),
  (20, @id_76, 6.17),
  (21, @id_76, 12.45),
  (22, @id_76, 35.09),
  (23, @id_76, 11.74),
  (24, @id_76, 4.36),
  (25, @id_76, 11.91),
  (26, @id_76, 25.18),
  (27, @id_76, 5.69),
  (28, @id_76, 12.89),
  (29, @id_76, 16.17),
  (30, @id_76, 15.37),
  (31, @id_76, 8.35),
  (32, @id_76, 24.33),
  (33, @id_76, 18.03),
  (34, @id_76, 14.99),
  (35, @id_76, 7.10),
  (36, @id_76, 6.77);

-- Versao 8.8 | 2025-06-23 → '2025-06-29'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 8.8, '2025-06-23', '2025-06-29', 0);

SET @id_77 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_77, 13.00),
  (2, @id_77, 5.15),
  (3, @id_77, 6.76),
  (4, @id_77, 2.22),
  (5, @id_77, 6.09),
  (6, @id_77, 6.00),
  (7, @id_77, 39.27),
  (8, @id_77, 1.66),
  (9, @id_77, 28.00),
  (10, @id_77, 22.44),
  (11, @id_77, 12.34),
  (12, @id_77, 8.46),
  (13, @id_77, 8.90),
  (14, @id_77, 45.50),
  (15, @id_77, 54.17),
  (16, @id_77, 52.00),
  (17, @id_77, 2.14),
  (18, @id_77, 33.50),
  (19, @id_77, 13.89),
  (20, @id_77, 6.17),
  (21, @id_77, 12.72),
  (22, @id_77, 35.09),
  (23, @id_77, 10.99),
  (24, @id_77, 4.36),
  (25, @id_77, 11.91),
  (26, @id_77, 25.18),
  (27, @id_77, 5.69),
  (28, @id_77, 12.77),
  (29, @id_77, 16.17),
  (30, @id_77, 15.37),
  (31, @id_77, 8.35),
  (32, @id_77, 24.48),
  (33, @id_77, 18.03),
  (34, @id_77, 14.99),
  (35, @id_77, 7.10),
  (36, @id_77, 6.77);

-- Versao 8.9 | 2025-06-30 → '2025-07-06'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 8.9, '2025-06-30', '2025-07-06', 0);

SET @id_78 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_78, 12.36),
  (2, @id_78, 5.15),
  (3, @id_78, 6.76),
  (4, @id_78, 3.25),
  (5, @id_78, 6.09),
  (6, @id_78, 6.00),
  (7, @id_78, 39.27),
  (8, @id_78, 0.78),
  (9, @id_78, 28.00),
  (10, @id_78, 21.80),
  (11, @id_78, 12.70),
  (12, @id_78, 8.46),
  (13, @id_78, 9.37),
  (14, @id_78, 45.50),
  (15, @id_78, 54.17),
  (16, @id_78, 52.00),
  (17, @id_78, 2.14),
  (18, @id_78, 33.50),
  (19, @id_78, 13.89),
  (20, @id_78, 6.17),
  (21, @id_78, 12.17),
  (22, @id_78, 35.09),
  (23, @id_78, 10.99),
  (24, @id_78, 4.36),
  (25, @id_78, 11.91),
  (26, @id_78, 25.20),
  (27, @id_78, 6.49),
  (28, @id_78, 12.77),
  (29, @id_78, 16.17),
  (30, @id_78, 15.37),
  (31, @id_78, 9.20),
  (32, @id_78, 23.68),
  (33, @id_78, 18.20),
  (34, @id_78, 14.99),
  (35, @id_78, 7.10),
  (36, @id_78, 6.77);

-- Versao 9.0 | 2025-07-07 → '2025-07-13'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 9.0, '2025-07-07', '2025-07-13', 0);

SET @id_79 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_79, 12.36),
  (2, @id_79, 4.34),
  (3, @id_79, 6.76),
  (4, @id_79, 3.25),
  (5, @id_79, 7.26),
  (6, @id_79, 6.00),
  (7, @id_79, 39.27),
  (8, @id_79, 0.78),
  (9, @id_79, 28.00),
  (10, @id_79, 21.45),
  (11, @id_79, 12.23),
  (12, @id_79, 8.46),
  (13, @id_79, 10.50),
  (14, @id_79, 45.50),
  (15, @id_79, 54.17),
  (16, @id_79, 52.00),
  (17, @id_79, 3.20),
  (18, @id_79, 33.50),
  (19, @id_79, 13.89),
  (20, @id_79, 6.17),
  (21, @id_79, 12.63),
  (22, @id_79, 34.61),
  (23, @id_79, 10.99),
  (24, @id_79, 4.36),
  (25, @id_79, 13.07),
  (26, @id_79, 25.20),
  (27, @id_79, 7.27),
  (28, @id_79, 12.77),
  (29, @id_79, 16.17),
  (30, @id_79, 15.37),
  (31, @id_79, 9.20),
  (32, @id_79, 23.68),
  (33, @id_79, 18.20),
  (34, @id_79, 14.30),
  (35, @id_79, 7.83),
  (36, @id_79, 7.10);

-- Versao 9.1 | 2025-07-14 → '2025-07-20'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 9.1, '2025-07-14', '2025-07-20', 0);

SET @id_80 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_80, 12.36),
  (2, @id_80, 4.34),
  (3, @id_80, 6.76),
  (4, @id_80, 3.25),
  (5, @id_80, 8.00),
  (6, @id_80, 6.00),
  (7, @id_80, 39.17),
  (8, @id_80, 0.78),
  (9, @id_80, 28.00),
  (10, @id_80, 20.96),
  (11, @id_80, 12.23),
  (12, @id_80, 8.46),
  (13, @id_80, 10.50),
  (14, @id_80, 45.50),
  (15, @id_80, 54.17),
  (16, @id_80, 51.35),
  (17, @id_80, 3.20),
  (18, @id_80, 33.50),
  (19, @id_80, 13.50),
  (20, @id_80, 6.88),
  (21, @id_80, 12.63),
  (22, @id_80, 35.84),
  (23, @id_80, 10.99),
  (24, @id_80, 4.36),
  (25, @id_80, 13.07),
  (26, @id_80, 25.20),
  (27, @id_80, 7.27),
  (28, @id_80, 12.77),
  (29, @id_80, 16.17),
  (30, @id_80, 15.37),
  (31, @id_80, 9.20),
  (32, @id_80, 23.68),
  (33, @id_80, 17.40),
  (34, @id_80, 14.30),
  (35, @id_80, 7.83),
  (36, @id_80, 7.10);

-- Versao 9.2 | 2025-07-21 → '2025-07-27'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 9.2, '2025-07-21', '2025-07-27', 0);

SET @id_81 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_81, 12.36),
  (2, @id_81, 4.54),
  (3, @id_81, 6.76),
  (4, @id_81, 3.25),
  (5, @id_81, 7.08),
  (6, @id_81, 6.00),
  (7, @id_81, 38.82),
  (8, @id_81, 1.15),
  (9, @id_81, 27.38),
  (10, @id_81, 20.96),
  (11, @id_81, 12.23),
  (12, @id_81, 8.46),
  (13, @id_81, 10.50),
  (14, @id_81, 45.50),
  (15, @id_81, 53.35),
  (16, @id_81, 50.46),
  (17, @id_81, 2.58),
  (18, @id_81, 33.50),
  (19, @id_81, 13.50),
  (20, @id_81, 7.40),
  (21, @id_81, 12.12),
  (22, @id_81, 35.84),
  (23, @id_81, 10.99),
  (24, @id_81, 4.36),
  (25, @id_81, 13.07),
  (26, @id_81, 25.20),
  (27, @id_81, 8.76),
  (28, @id_81, 13.19),
  (29, @id_81, 16.17),
  (30, @id_81, 14.81),
  (31, @id_81, 9.20),
  (32, @id_81, 23.36),
  (33, @id_81, 17.40),
  (34, @id_81, 15.55),
  (35, @id_81, 7.83),
  (36, @id_81, 6.57);

-- Versao 9.3 | 2025-07-28 → '2025-08-03'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 9.3, '2025-07-28', '2025-08-03', 0);

SET @id_82 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_82, 12.36),
  (2, @id_82, 4.20),
  (3, @id_82, 6.05),
  (4, @id_82, 2.70),
  (5, @id_82, 7.08),
  (6, @id_82, 6.00),
  (7, @id_82, 38.82),
  (8, @id_82, 1.15),
  (9, @id_82, 27.38),
  (10, @id_82, 21.28),
  (11, @id_82, 12.23),
  (12, @id_82, 7.75),
  (13, @id_82, 10.50),
  (14, @id_82, 45.50),
  (15, @id_82, 53.35),
  (16, @id_82, 50.46),
  (17, @id_82, 3.37),
  (18, @id_82, 32.59),
  (19, @id_82, 13.50),
  (20, @id_82, 6.48),
  (21, @id_82, 12.12),
  (22, @id_82, 35.84),
  (23, @id_82, 10.99),
  (24, @id_82, 4.36),
  (25, @id_82, 13.84),
  (26, @id_82, 25.20),
  (27, @id_82, 8.76),
  (28, @id_82, 13.19),
  (29, @id_82, 16.51),
  (30, @id_82, 14.08),
  (31, @id_82, 9.20),
  (32, @id_82, 23.48),
  (33, @id_82, 17.40),
  (34, @id_82, 15.55),
  (35, @id_82, 7.83),
  (36, @id_82, 7.03);

-- Versao 9.4 | 2025-08-04 → '2025-08-10'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 9.4, '2025-08-04', '2025-08-10', 0);

SET @id_83 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_83, 12.36),
  (2, @id_83, 4.20),
  (3, @id_83, 6.05),
  (4, @id_83, 3.31),
  (5, @id_83, 7.08),
  (6, @id_83, 6.00),
  (7, @id_83, 39.44),
  (8, @id_83, 0.80),
  (9, @id_83, 27.38),
  (10, @id_83, 21.28),
  (11, @id_83, 12.23),
  (12, @id_83, 7.75),
  (13, @id_83, 10.50),
  (14, @id_83, 45.50),
  (15, @id_83, 53.35),
  (16, @id_83, 50.46),
  (17, @id_83, 3.37),
  (18, @id_83, 32.59),
  (19, @id_83, 13.50),
  (20, @id_83, 6.22),
  (21, @id_83, 12.12),
  (22, @id_83, 34.98),
  (23, @id_83, 10.99),
  (24, @id_83, 5.56),
  (25, @id_83, 13.84),
  (26, @id_83, 25.20),
  (27, @id_83, 8.76),
  (28, @id_83, 13.19),
  (29, @id_83, 16.69),
  (30, @id_83, 13.55),
  (31, @id_83, 9.20),
  (32, @id_83, 23.48),
  (33, @id_83, 17.40),
  (34, @id_83, 15.55),
  (35, @id_83, 7.50),
  (36, @id_83, 7.03);

-- Versao 9.5 | 2025-08-11 → '2025-08-17'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 9.5, '2025-08-11', '2025-08-17', 0);

SET @id_84 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_84, 12.36),
  (2, @id_84, 3.23),
  (3, @id_84, 6.05),
  (4, @id_84, 3.31),
  (5, @id_84, 7.42),
  (6, @id_84, 6.00),
  (7, @id_84, 40.22),
  (8, @id_84, 2.15),
  (9, @id_84, 27.38),
  (10, @id_84, 21.28),
  (11, @id_84, 12.53),
  (12, @id_84, 7.75),
  (13, @id_84, 10.10),
  (14, @id_84, 45.50),
  (15, @id_84, 53.19),
  (16, @id_84, 50.46),
  (17, @id_84, 3.37),
  (18, @id_84, 32.59),
  (19, @id_84, 13.50),
  (20, @id_84, 6.22),
  (21, @id_84, 12.60),
  (22, @id_84, 34.98),
  (23, @id_84, 10.56),
  (24, @id_84, 5.56),
  (25, @id_84, 13.37),
  (26, @id_84, 25.20),
  (27, @id_84, 8.76),
  (28, @id_84, 14.22),
  (29, @id_84, 15.96),
  (30, @id_84, 13.55),
  (31, @id_84, 9.20),
  (32, @id_84, 23.48),
  (33, @id_84, 17.40),
  (34, @id_84, 14.96),
  (35, @id_84, 7.50),
  (36, @id_84, 7.03);

-- Versao 9.6 | 2025-08-18 → '2025-08-24'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 9.6, '2025-08-18', '2025-08-24', 0);

SET @id_85 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_85, 12.58),
  (2, @id_85, 3.23),
  (3, @id_85, 6.05),
  (4, @id_85, 4.65),
  (5, @id_85, 7.42),
  (6, @id_85, 6.00),
  (7, @id_85, 40.22),
  (8, @id_85, 2.43),
  (9, @id_85, 27.25),
  (10, @id_85, 22.58),
  (11, @id_85, 12.53),
  (12, @id_85, 7.75),
  (13, @id_85, 9.51),
  (14, @id_85, 45.50),
  (15, @id_85, 53.19),
  (16, @id_85, 49.73),
  (17, @id_85, 3.37),
  (18, @id_85, 32.59),
  (19, @id_85, 14.49),
  (20, @id_85, 6.22),
  (21, @id_85, 12.60),
  (22, @id_85, 35.51),
  (23, @id_85, 10.56),
  (24, @id_85, 5.56),
  (25, @id_85, 12.87),
  (26, @id_85, 25.20),
  (27, @id_85, 8.76),
  (28, @id_85, 14.22),
  (29, @id_85, 15.96),
  (30, @id_85, 12.69),
  (31, @id_85, 9.20),
  (32, @id_85, 23.48),
  (33, @id_85, 17.40),
  (34, @id_85, 14.01),
  (35, @id_85, 7.50),
  (36, @id_85, 7.03);

-- Versao 9.7 | 2025-08-25 → '2025-08-31'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 9.7, '2025-08-25', '2025-08-31', 0);

SET @id_86 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_86, 12.58),
  (2, @id_86, 3.23),
  (3, @id_86, 6.05),
  (4, @id_86, 4.65),
  (5, @id_86, 7.42),
  (6, @id_86, 6.00),
  (7, @id_86, 40.22),
  (8, @id_86, 2.43),
  (9, @id_86, 27.25),
  (10, @id_86, 23.00),
  (11, @id_86, 12.53),
  (12, @id_86, 7.75),
  (13, @id_86, 9.51),
  (14, @id_86, 45.50),
  (15, @id_86, 54.56),
  (16, @id_86, 49.01),
  (17, @id_86, 3.37),
  (18, @id_86, 33.50),
  (19, @id_86, 14.49),
  (20, @id_86, 5.96),
  (21, @id_86, 12.60),
  (22, @id_86, 36.00),
  (23, @id_86, 10.56),
  (24, @id_86, 5.92),
  (25, @id_86, 12.87),
  (26, @id_86, 25.20),
  (27, @id_86, 8.76),
  (28, @id_86, 14.22),
  (29, @id_86, 15.96),
  (30, @id_86, 12.69),
  (31, @id_86, 9.20),
  (32, @id_86, 23.48),
  (33, @id_86, 17.40),
  (34, @id_86, 14.01),
  (35, @id_86, 7.50),
  (36, @id_86, 7.03);

-- Versao 9.8 | 2025-09-01 → '2025-09-07'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 9.8, '2025-09-01', '2025-09-07', 0);

SET @id_87 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_87, 13.00),
  (2, @id_87, 3.23),
  (3, @id_87, 6.61),
  (4, @id_87, 3.69),
  (5, @id_87, 7.42),
  (6, @id_87, 6.00),
  (7, @id_87, 40.22),
  (8, @id_87, 2.43),
  (9, @id_87, 26.41),
  (10, @id_87, 22.79),
  (11, @id_87, 12.70),
  (12, @id_87, 8.47),
  (13, @id_87, 9.66),
  (14, @id_87, 45.50),
  (15, @id_87, 55.39),
  (16, @id_87, 48.48),
  (17, @id_87, 3.91),
  (18, @id_87, 33.50),
  (19, @id_87, 14.65),
  (20, @id_87, 5.39),
  (21, @id_87, 12.60),
  (22, @id_87, 36.00),
  (23, @id_87, 9.68),
  (24, @id_87, 5.92),
  (25, @id_87, 12.87),
  (26, @id_87, 25.20),
  (27, @id_87, 8.76),
  (28, @id_87, 14.22),
  (29, @id_87, 15.96),
  (30, @id_87, 12.80),
  (31, @id_87, 9.20),
  (32, @id_87, 24.25),
  (33, @id_87, 17.40),
  (34, @id_87, 14.01),
  (35, @id_87, 7.50),
  (36, @id_87, 7.03);

-- Versao 9.9 | 2025-09-08 → '2025-09-14'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 9.9, '2025-09-08', '2025-09-14', 0);

SET @id_88 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_88, 13.00),
  (2, @id_88, 4.63),
  (3, @id_88, 5.95),
  (4, @id_88, 3.69),
  (5, @id_88, 8.00),
  (6, @id_88, 6.00),
  (7, @id_88, 40.22),
  (8, @id_88, 1.62),
  (9, @id_88, 27.64),
  (10, @id_88, 22.79),
  (11, @id_88, 12.70),
  (12, @id_88, 9.00),
  (13, @id_88, 9.66),
  (14, @id_88, 44.96),
  (15, @id_88, 55.39),
  (16, @id_88, 48.48),
  (17, @id_88, 4.10),
  (18, @id_88, 33.50),
  (19, @id_88, 14.65),
  (20, @id_88, 5.88),
  (21, @id_88, 11.68),
  (22, @id_88, 36.00),
  (23, @id_88, 9.68),
  (24, @id_88, 5.92),
  (25, @id_88, 12.87),
  (26, @id_88, 25.20),
  (27, @id_88, 8.76),
  (28, @id_88, 14.22),
  (29, @id_88, 16.59),
  (30, @id_88, 12.80),
  (31, @id_88, 9.20),
  (32, @id_88, 23.45),
  (33, @id_88, 17.40),
  (34, @id_88, 15.03),
  (35, @id_88, 7.50),
  (36, @id_88, 7.03);

-- Versao 10.0 | 2025-09-15 → '2025-09-21'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 10.0, '2025-09-15', '2025-09-21', 0);

SET @id_89 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_89, 13.00),
  (2, @id_89, 4.63),
  (3, @id_89, 5.95),
  (4, @id_89, 4.24),
  (5, @id_89, 8.00),
  (6, @id_89, 6.00),
  (7, @id_89, 40.22),
  (8, @id_89, 1.40),
  (9, @id_89, 27.36),
  (10, @id_89, 22.79),
  (11, @id_89, 12.70),
  (12, @id_89, 9.00),
  (13, @id_89, 8.97),
  (14, @id_89, 44.96),
  (15, @id_89, 55.85),
  (16, @id_89, 48.48),
  (17, @id_89, 4.10),
  (18, @id_89, 33.21),
  (19, @id_89, 14.65),
  (20, @id_89, 5.71),
  (21, @id_89, 11.53),
  (22, @id_89, 36.00),
  (23, @id_89, 9.21),
  (24, @id_89, 5.92),
  (25, @id_89, 14.12),
  (26, @id_89, 25.20),
  (27, @id_89, 8.76),
  (28, @id_89, 14.22),
  (29, @id_89, 16.59),
  (30, @id_89, 11.84),
  (31, @id_89, 9.20),
  (32, @id_89, 24.29),
  (33, @id_89, 17.40),
  (34, @id_89, 15.03),
  (35, @id_89, 7.50),
  (36, @id_89, 7.38);

-- Versao 10.1 | 2025-09-22 → '2025-09-28'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 10.1, '2025-09-22', '2025-09-28', 0);

SET @id_90 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_90, 13.00),
  (2, @id_90, 4.63),
  (3, @id_90, 6.55),
  (4, @id_90, 4.24),
  (5, @id_90, 8.00),
  (6, @id_90, 6.00),
  (7, @id_90, 40.22),
  (8, @id_90, 1.40),
  (9, @id_90, 27.36),
  (10, @id_90, 22.79),
  (11, @id_90, 12.20),
  (12, @id_90, 9.00),
  (13, @id_90, 8.97),
  (14, @id_90, 44.57),
  (15, @id_90, 55.85),
  (16, @id_90, 49.18),
  (17, @id_90, 4.10),
  (18, @id_90, 33.21),
  (19, @id_90, 14.65),
  (20, @id_90, 7.11),
  (21, @id_90, 11.53),
  (22, @id_90, 35.50),
  (23, @id_90, 9.21),
  (24, @id_90, 6.50),
  (25, @id_90, 14.12),
  (26, @id_90, 25.20),
  (27, @id_90, 8.39),
  (28, @id_90, 14.22),
  (29, @id_90, 16.59),
  (30, @id_90, 11.72),
  (31, @id_90, 8.48),
  (32, @id_90, 24.29),
  (33, @id_90, 17.40),
  (34, @id_90, 15.03),
  (35, @id_90, 7.26),
  (36, @id_90, 7.38);

-- Versao 10.2 | 2025-09-29 → '2025-10-05'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 10.2, '2025-09-29', '2025-10-05', 0);

SET @id_91 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_91, 13.00),
  (2, @id_91, 4.63),
  (3, @id_91, 6.55),
  (4, @id_91, 4.24),
  (5, @id_91, 8.00),
  (6, @id_91, 5.82),
  (7, @id_91, 40.22),
  (8, @id_91, 0.64),
  (9, @id_91, 28.00),
  (10, @id_91, 22.79),
  (11, @id_91, 11.48),
  (12, @id_91, 9.00),
  (13, @id_91, 8.33),
  (14, @id_91, 44.57),
  (15, @id_91, 56.10),
  (16, @id_91, 49.18),
  (17, @id_91, 4.10),
  (18, @id_91, 33.50),
  (19, @id_91, 14.18),
  (20, @id_91, 6.23),
  (21, @id_91, 11.53),
  (22, @id_91, 36.00),
  (23, @id_91, 9.21),
  (24, @id_91, 6.50),
  (25, @id_91, 14.12),
  (26, @id_91, 25.20),
  (27, @id_91, 8.39),
  (28, @id_91, 14.22),
  (29, @id_91, 16.59),
  (30, @id_91, 11.72),
  (31, @id_91, 8.48),
  (32, @id_91, 24.94),
  (33, @id_91, 17.40),
  (34, @id_91, 15.03),
  (35, @id_91, 7.26),
  (36, @id_91, 7.38);

-- Versao 10.3 | 2025-10-06 → '2025-10-12'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 10.3, '2025-10-06', '2025-10-12', 0);

SET @id_92 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_92, 13.00),
  (2, @id_92, 4.63),
  (3, @id_92, 6.55),
  (4, @id_92, 4.24),
  (5, @id_92, 8.00),
  (6, @id_92, 5.82),
  (7, @id_92, 40.22),
  (8, @id_92, 0.64),
  (9, @id_92, 28.00),
  (10, @id_92, 23.00),
  (11, @id_92, 11.48),
  (12, @id_92, 9.00),
  (13, @id_92, 8.33),
  (14, @id_92, 44.94),
  (15, @id_92, 56.10),
  (16, @id_92, 49.18),
  (17, @id_92, 4.10),
  (18, @id_92, 33.50),
  (19, @id_92, 14.00),
  (20, @id_92, 6.92),
  (21, @id_92, 11.53),
  (22, @id_92, 36.00),
  (23, @id_92, 9.21),
  (24, @id_92, 6.50),
  (25, @id_92, 14.12),
  (26, @id_92, 24.55),
  (27, @id_92, 7.89),
  (28, @id_92, 14.22),
  (29, @id_92, 16.59),
  (30, @id_92, 11.44),
  (31, @id_92, 8.48),
  (32, @id_92, 26.23),
  (33, @id_92, 17.40),
  (34, @id_92, 15.03),
  (35, @id_92, 6.33),
  (36, @id_92, 6.54);

-- Versao 10.4 | 2025-10-13 → '2025-10-19'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 10.4, '2025-10-13', '2025-10-19', 0);

SET @id_93 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_93, 13.00),
  (2, @id_93, 4.89),
  (3, @id_93, 6.55),
  (4, @id_93, 4.24),
  (5, @id_93, 8.00),
  (6, @id_93, 5.82),
  (7, @id_93, 40.22),
  (8, @id_93, 0.64),
  (9, @id_93, 28.00),
  (10, @id_93, 23.00),
  (11, @id_93, 11.78),
  (12, @id_93, 9.00),
  (13, @id_93, 8.33),
  (14, @id_93, 45.10),
  (15, @id_93, 56.10),
  (16, @id_93, 49.18),
  (17, @id_93, 4.10),
  (18, @id_93, 33.50),
  (19, @id_93, 14.11),
  (20, @id_93, 6.92),
  (21, @id_93, 11.53),
  (22, @id_93, 35.31),
  (23, @id_93, 9.09),
  (24, @id_93, 5.82),
  (25, @id_93, 14.12),
  (26, @id_93, 24.55),
  (27, @id_93, 7.70),
  (28, @id_93, 14.22),
  (29, @id_93, 16.01),
  (30, @id_93, 11.44),
  (31, @id_93, 8.48),
  (32, @id_93, 26.23),
  (33, @id_93, 17.28),
  (34, @id_93, 15.03),
  (35, @id_93, 6.33),
  (36, @id_93, 6.54);

-- Versao 10.5 | 2025-10-20 → '2025-10-26'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 10.5, '2025-10-20', '2025-10-26', 0);

SET @id_94 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_94, 13.00),
  (2, @id_94, 4.89),
  (3, @id_94, 6.55),
  (4, @id_94, 4.24),
  (5, @id_94, 8.00),
  (6, @id_94, 5.27),
  (7, @id_94, 39.56),
  (8, @id_94, 0.10),
  (9, @id_94, 27.87),
  (10, @id_94, 23.00),
  (11, @id_94, 11.78),
  (12, @id_94, 9.00),
  (13, @id_94, 7.64),
  (14, @id_94, 45.10),
  (15, @id_94, 56.10),
  (16, @id_94, 48.53),
  (17, @id_94, 4.10),
  (18, @id_94, 33.50),
  (19, @id_94, 14.11),
  (20, @id_94, 6.00),
  (21, @id_94, 11.53),
  (22, @id_94, 35.31),
  (23, @id_94, 9.09),
  (24, @id_94, 5.82),
  (25, @id_94, 14.12),
  (26, @id_94, 23.67),
  (27, @id_94, 7.70),
  (28, @id_94, 15.05),
  (29, @id_94, 16.01),
  (30, @id_94, 10.46),
  (31, @id_94, 8.48),
  (32, @id_94, 25.30),
  (33, @id_94, 17.28),
  (34, @id_94, 15.03),
  (35, @id_94, 6.12),
  (36, @id_94, 6.54);

-- Versao 10.6 | 2025-10-27 → '2025-11-02'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 10.6, '2025-10-27', '2025-11-02', 0);

SET @id_95 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_95, 12.25),
  (2, @id_95, 6.00),
  (3, @id_95, 6.55),
  (4, @id_95, 4.24),
  (5, @id_95, 8.00),
  (6, @id_95, 4.37),
  (7, @id_95, 39.21),
  (8, @id_95, 0.35),
  (9, @id_95, 27.87),
  (10, @id_95, 23.00),
  (11, @id_95, 11.78),
  (12, @id_95, 9.00),
  (13, @id_95, 7.64),
  (14, @id_95, 45.10),
  (15, @id_95, 56.10),
  (16, @id_95, 49.30),
  (17, @id_95, 4.10),
  (18, @id_95, 33.50),
  (19, @id_95, 14.11),
  (20, @id_95, 5.46),
  (21, @id_95, 11.53),
  (22, @id_95, 35.31),
  (23, @id_95, 9.09),
  (24, @id_95, 5.94),
  (25, @id_95, 14.12),
  (26, @id_95, 23.67),
  (27, @id_95, 9.18),
  (28, @id_95, 15.24),
  (29, @id_95, 15.02),
  (30, @id_95, 9.92),
  (31, @id_95, 8.48),
  (32, @id_95, 25.30),
  (33, @id_95, 17.28),
  (34, @id_95, 15.03),
  (35, @id_95, 6.12),
  (36, @id_95, 7.17);

-- Versao 10.7 | 2025-11-03 → '2025-11-09'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 10.7, '2025-11-03', '2025-11-09', 0);

SET @id_96 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_96, 12.25),
  (2, @id_96, 6.00),
  (3, @id_96, 6.55),
  (4, @id_96, 4.24),
  (5, @id_96, 7.19),
  (6, @id_96, 4.37),
  (7, @id_96, 39.21),
  (8, @id_96, 0.21),
  (9, @id_96, 27.30),
  (10, @id_96, 23.00),
  (11, @id_96, 12.66),
  (12, @id_96, 9.00),
  (13, @id_96, 7.81),
  (14, @id_96, 45.10),
  (15, @id_96, 56.10),
  (16, @id_96, 49.30),
  (17, @id_96, 3.58),
  (18, @id_96, 33.50),
  (19, @id_96, 14.29),
  (20, @id_96, 5.46),
  (21, @id_96, 11.53),
  (22, @id_96, 34.80),
  (23, @id_96, 9.35),
  (24, @id_96, 6.50),
  (25, @id_96, 14.41),
  (26, @id_96, 23.67),
  (27, @id_96, 9.18),
  (28, @id_96, 15.24),
  (29, @id_96, 15.02),
  (30, @id_96, 10.30),
  (31, @id_96, 8.48),
  (32, @id_96, 25.30),
  (33, @id_96, 18.20),
  (34, @id_96, 16.14),
  (35, @id_96, 6.12),
  (36, @id_96, 7.17);

-- Versao 10.8 | 2025-11-10 → '2025-11-16'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 10.8, '2025-11-10', '2025-11-16', 0);

SET @id_97 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_97, 12.25),
  (2, @id_97, 6.00),
  (3, @id_97, 6.55),
  (4, @id_97, 4.24),
  (5, @id_97, 8.00),
  (6, @id_97, 3.93),
  (7, @id_97, 39.21),
  (8, @id_97, 1.59),
  (9, @id_97, 27.30),
  (10, @id_97, 23.00),
  (11, @id_97, 12.40),
  (12, @id_97, 9.00),
  (13, @id_97, 7.81),
  (14, @id_97, 45.50),
  (15, @id_97, 56.10),
  (16, @id_97, 49.30),
  (17, @id_97, 3.58),
  (18, @id_97, 33.50),
  (19, @id_97, 14.29),
  (20, @id_97, 4.87),
  (21, @id_97, 11.53),
  (22, @id_97, 34.49),
  (23, @id_97, 9.35),
  (24, @id_97, 6.50),
  (25, @id_97, 14.41),
  (26, @id_97, 23.67),
  (27, @id_97, 9.62),
  (28, @id_97, 15.24),
  (29, @id_97, 15.90),
  (30, @id_97, 10.16),
  (31, @id_97, 8.48),
  (32, @id_97, 25.45),
  (33, @id_97, 17.50),
  (34, @id_97, 15.77),
  (35, @id_97, 5.98),
  (36, @id_97, 7.17);

-- Versao 10.9 | 2025-11-17 → '2025-11-23'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 10.9, '2025-11-17', '2025-11-23', 0);

SET @id_98 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_98, 12.25),
  (2, @id_98, 6.00),
  (3, @id_98, 7.00),
  (4, @id_98, 4.59),
  (5, @id_98, 8.00),
  (6, @id_98, 3.93),
  (7, @id_98, 39.03),
  (8, @id_98, 1.59),
  (9, @id_98, 27.30),
  (10, @id_98, 23.00),
  (11, @id_98, 12.40),
  (12, @id_98, 9.00),
  (13, @id_98, 7.81),
  (14, @id_98, 45.50),
  (15, @id_98, 56.10),
  (16, @id_98, 49.78),
  (17, @id_98, 3.58),
  (18, @id_98, 33.50),
  (19, @id_98, 15.25),
  (20, @id_98, 4.87),
  (21, @id_98, 12.51),
  (22, @id_98, 33.95),
  (23, @id_98, 10.76),
  (24, @id_98, 6.14),
  (25, @id_98, 14.95),
  (26, @id_98, 23.67),
  (27, @id_98, 9.20),
  (28, @id_98, 15.24),
  (29, @id_98, 15.90),
  (30, @id_98, 10.16),
  (31, @id_98, 9.20),
  (32, @id_98, 25.45),
  (33, @id_98, 18.20),
  (34, @id_98, 15.67),
  (35, @id_98, 5.98),
  (36, @id_98, 7.44);

-- Versao 11.0 | 2025-11-24 → '2025-11-30'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 11.0, '2025-11-24', '2025-11-30', 0);

SET @id_99 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_99, 11.43),
  (2, @id_99, 5.53),
  (3, @id_99, 7.00),
  (4, @id_99, 4.59),
  (5, @id_99, 8.00),
  (6, @id_99, 3.93),
  (7, @id_99, 39.03),
  (8, @id_99, 2.12),
  (9, @id_99, 27.30),
  (10, @id_99, 23.00),
  (11, @id_99, 12.40),
  (12, @id_99, 9.00),
  (13, @id_99, 8.77),
  (14, @id_99, 45.50),
  (15, @id_99, 56.10),
  (16, @id_99, 49.78),
  (17, @id_99, 3.58),
  (18, @id_99, 33.50),
  (19, @id_99, 16.25),
  (20, @id_99, 4.87),
  (21, @id_99, 13.60),
  (22, @id_99, 33.95),
  (23, @id_99, 10.76),
  (24, @id_99, 6.50),
  (25, @id_99, 15.50),
  (26, @id_99, 23.67),
  (27, @id_99, 9.74),
  (28, @id_99, 15.24),
  (29, @id_99, 15.90),
  (30, @id_99, 11.38),
  (31, @id_99, 8.78),
  (32, @id_99, 25.10),
  (33, @id_99, 18.20),
  (34, @id_99, 15.01),
  (35, @id_99, 5.37),
  (36, @id_99, 7.44);

-- Versao 11.1 | 2025-12-01 → '2025-12-07'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 11.1, '2025-12-01', '2025-12-07', 0);

SET @id_100 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_100, 11.43),
  (2, @id_100, 4.75),
  (3, @id_100, 7.00),
  (4, @id_100, 4.59),
  (5, @id_100, 8.00),
  (6, @id_100, 3.93),
  (7, @id_100, 39.03),
  (8, @id_100, 2.12),
  (9, @id_100, 27.01),
  (10, @id_100, 23.00),
  (11, @id_100, 12.40),
  (12, @id_100, 9.00),
  (13, @id_100, 8.27),
  (14, @id_100, 45.50),
  (15, @id_100, 56.10),
  (16, @id_100, 49.78),
  (17, @id_100, 3.58),
  (18, @id_100, 32.74),
  (19, @id_100, 16.25),
  (20, @id_100, 5.17),
  (21, @id_100, 12.76),
  (22, @id_100, 33.24),
  (23, @id_100, 10.76),
  (24, @id_100, 6.50),
  (25, @id_100, 15.50),
  (26, @id_100, 23.67),
  (27, @id_100, 9.02),
  (28, @id_100, 15.24),
  (29, @id_100, 15.90),
  (30, @id_100, 11.38),
  (31, @id_100, 8.78),
  (32, @id_100, 25.10),
  (33, @id_100, 18.20),
  (34, @id_100, 15.01),
  (35, @id_100, 5.37),
  (36, @id_100, 7.05);

-- Versao 11.2 | 2025-12-08 → '2025-12-14'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 11.2, '2025-12-08', '2025-12-14', 0);

SET @id_101 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_101, 11.43),
  (2, @id_101, 4.75),
  (3, @id_101, 7.00),
  (4, @id_101, 4.59),
  (5, @id_101, 8.00),
  (6, @id_101, 3.93),
  (7, @id_101, 39.03),
  (8, @id_101, 2.12),
  (9, @id_101, 27.83),
  (10, @id_101, 23.00),
  (11, @id_101, 12.40),
  (12, @id_101, 9.00),
  (13, @id_101, 8.27),
  (14, @id_101, 45.50),
  (15, @id_101, 56.10),
  (16, @id_101, 49.78),
  (17, @id_101, 4.10),
  (18, @id_101, 32.74),
  (19, @id_101, 16.25),
  (20, @id_101, 5.17),
  (21, @id_101, 13.01),
  (22, @id_101, 33.24),
  (23, @id_101, 10.76),
  (24, @id_101, 6.50),
  (25, @id_101, 15.50),
  (26, @id_101, 23.67),
  (27, @id_101, 8.29),
  (28, @id_101, 15.50),
  (29, @id_101, 15.16),
  (30, @id_101, 12.23),
  (31, @id_101, 8.00),
  (32, @id_101, 25.10),
  (33, @id_101, 18.20),
  (34, @id_101, 14.08),
  (35, @id_101, 5.37),
  (36, @id_101, 7.05);

-- Versao 11.3 | 2025-12-15 → '2025-12-21'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 11.3, '2025-12-15', '2025-12-21', 0);

SET @id_102 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_102, 12.52),
  (2, @id_102, 4.75),
  (3, @id_102, 7.00),
  (4, @id_102, 4.59),
  (5, @id_102, 7.34),
  (6, @id_102, 3.48),
  (7, @id_102, 40.06),
  (8, @id_102, 3.46),
  (9, @id_102, 27.83),
  (10, @id_102, 23.00),
  (11, @id_102, 12.40),
  (12, @id_102, 9.00),
  (13, @id_102, 8.27),
  (14, @id_102, 45.50),
  (15, @id_102, 56.10),
  (16, @id_102, 49.78),
  (17, @id_102, 4.10),
  (18, @id_102, 31.92),
  (19, @id_102, 16.25),
  (20, @id_102, 6.38),
  (21, @id_102, 13.01),
  (22, @id_102, 33.24),
  (23, @id_102, 10.76),
  (24, @id_102, 6.50),
  (25, @id_102, 15.05),
  (26, @id_102, 23.67),
  (27, @id_102, 7.66),
  (28, @id_102, 15.50),
  (29, @id_102, 16.02),
  (30, @id_102, 12.23),
  (31, @id_102, 8.21),
  (32, @id_102, 24.48),
  (33, @id_102, 18.20),
  (34, @id_102, 14.08),
  (35, @id_102, 5.37),
  (36, @id_102, 7.05);

-- Versao 11.4 | 2025-12-22 → '2025-12-28'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 11.4, '2025-12-22', '2025-12-28', 0);

SET @id_103 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_103, 12.26),
  (2, @id_103, 4.59),
  (3, @id_103, 7.00),
  (4, @id_103, 3.80),
  (5, @id_103, 7.34),
  (6, @id_103, 3.48),
  (7, @id_103, 40.29),
  (8, @id_103, 2.56),
  (9, @id_103, 27.16),
  (10, @id_103, 23.00),
  (11, @id_103, 12.40),
  (12, @id_103, 9.00),
  (13, @id_103, 8.27),
  (14, @id_103, 45.50),
  (15, @id_103, 56.10),
  (16, @id_103, 49.78),
  (17, @id_103, 4.10),
  (18, @id_103, 31.92),
  (19, @id_103, 15.88),
  (20, @id_103, 5.89),
  (21, @id_103, 12.60),
  (22, @id_103, 33.24),
  (23, @id_103, 10.76),
  (24, @id_103, 6.04),
  (25, @id_103, 15.05),
  (26, @id_103, 23.67),
  (27, @id_103, 7.66),
  (28, @id_103, 15.50),
  (29, @id_103, 16.02),
  (30, @id_103, 13.48),
  (31, @id_103, 8.21),
  (32, @id_103, 23.60),
  (33, @id_103, 18.20),
  (34, @id_103, 14.73),
  (35, @id_103, 5.37),
  (36, @id_103, 7.05);

-- Versao 11.5 | 2025-12-29 → '2026-01-04'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 11.5, '2025-12-29', '2026-01-04', 0);

SET @id_104 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_104, 12.26),
  (2, @id_104, 4.59),
  (3, @id_104, 7.00),
  (4, @id_104, 3.80),
  (5, @id_104, 7.34),
  (6, @id_104, 2.62),
  (7, @id_104, 40.29),
  (8, @id_104, 1.86),
  (9, @id_104, 27.16),
  (10, @id_104, 23.00),
  (11, @id_104, 12.40),
  (12, @id_104, 9.00),
  (13, @id_104, 8.27),
  (14, @id_104, 45.32),
  (15, @id_104, 56.10),
  (16, @id_104, 49.78),
  (17, @id_104, 4.10),
  (18, @id_104, 31.92),
  (19, @id_104, 15.88),
  (20, @id_104, 5.89),
  (21, @id_104, 12.60),
  (22, @id_104, 33.24),
  (23, @id_104, 10.76),
  (24, @id_104, 6.04),
  (25, @id_104, 15.50),
  (26, @id_104, 23.36),
  (27, @id_104, 7.66),
  (28, @id_104, 15.50),
  (29, @id_104, 16.02),
  (30, @id_104, 13.48),
  (31, @id_104, 7.60),
  (32, @id_104, 23.86),
  (33, @id_104, 18.20),
  (34, @id_104, 15.03),
  (35, @id_104, 6.85),
  (36, @id_104, 6.25);

-- Versao 11.6 | 2026-01-05 → '2026-01-11'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 11.6, '2026-01-05', '2026-01-11', 0);

SET @id_105 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_105, 12.26),
  (2, @id_105, 4.59),
  (3, @id_105, 6.82),
  (4, @id_105, 3.80),
  (5, @id_105, 8.00),
  (6, @id_105, 2.62),
  (7, @id_105, 41.51),
  (8, @id_105, 1.86),
  (9, @id_105, 27.16),
  (10, @id_105, 23.00),
  (11, @id_105, 12.70),
  (12, @id_105, 9.00),
  (13, @id_105, 8.27),
  (14, @id_105, 45.50),
  (15, @id_105, 55.28),
  (16, @id_105, 49.78),
  (17, @id_105, 4.10),
  (18, @id_105, 31.15),
  (19, @id_105, 16.77),
  (20, @id_105, 5.89),
  (21, @id_105, 12.60),
  (22, @id_105, 33.24),
  (23, @id_105, 10.76),
  (24, @id_105, 5.21),
  (25, @id_105, 15.50),
  (26, @id_105, 23.36),
  (27, @id_105, 7.66),
  (28, @id_105, 15.15),
  (29, @id_105, 16.02),
  (30, @id_105, 13.48),
  (31, @id_105, 7.49),
  (32, @id_105, 23.86),
  (33, @id_105, 18.20),
  (34, @id_105, 15.03),
  (35, @id_105, 6.85),
  (36, @id_105, 6.25);

-- Versao 11.7 | 2026-01-12 → '2026-01-18'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 11.7, '2026-01-12', '2026-01-18', 0);

SET @id_106 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_106, 11.92),
  (2, @id_106, 5.37),
  (3, @id_106, 7.00),
  (4, @id_106, 3.80),
  (5, @id_106, 8.00),
  (6, @id_106, 2.62),
  (7, @id_106, 41.27),
  (8, @id_106, 1.86),
  (9, @id_106, 26.37),
  (10, @id_106, 23.00),
  (11, @id_106, 12.70),
  (12, @id_106, 9.00),
  (13, @id_106, 8.85),
  (14, @id_106, 45.50),
  (15, @id_106, 55.28),
  (16, @id_106, 49.78),
  (17, @id_106, 4.10),
  (18, @id_106, 31.15),
  (19, @id_106, 17.92),
  (20, @id_106, 5.89),
  (21, @id_106, 12.60),
  (22, @id_106, 34.00),
  (23, @id_106, 10.76),
  (24, @id_106, 5.10),
  (25, @id_106, 15.00),
  (26, @id_106, 24.81),
  (27, @id_106, 7.82),
  (28, @id_106, 14.48),
  (29, @id_106, 15.53),
  (30, @id_106, 12.61),
  (31, @id_106, 7.49),
  (32, @id_106, 23.86),
  (33, @id_106, 18.20),
  (34, @id_106, 15.68),
  (35, @id_106, 8.00),
  (36, @id_106, 7.17);

-- Versao 11.8 | 2026-01-19 → '2026-01-25'
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 11.8, '2026-01-19', '2026-01-25', 0);

SET @id_107 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_107, 11.92),
  (2, @id_107, 5.37),
  (3, @id_107, 7.00),
  (4, @id_107, 3.80),
  (5, @id_107, 8.00),
  (6, @id_107, 2.62),
  (7, @id_107, 41.27),
  (8, @id_107, 1.86),
  (9, @id_107, 26.37),
  (10, @id_107, 23.00),
  (11, @id_107, 12.70),
  (12, @id_107, 9.00),
  (13, @id_107, 8.85),
  (14, @id_107, 45.50),
  (15, @id_107, 54.59),
  (16, @id_107, 48.82),
  (17, @id_107, 4.10),
  (18, @id_107, 31.15),
  (19, @id_107, 17.70),
  (20, @id_107, 7.25),
  (21, @id_107, 12.60),
  (22, @id_107, 33.09),
  (23, @id_107, 11.21),
  (24, @id_107, 5.10),
  (25, @id_107, 14.33),
  (26, @id_107, 24.46),
  (27, @id_107, 7.82),
  (28, @id_107, 13.69),
  (29, @id_107, 15.53),
  (30, @id_107, 12.61),
  (31, @id_107, 7.49),
  (32, @id_107, 24.50),
  (33, @id_107, 18.20),
  (34, @id_107, 15.68),
  (35, @id_107, 7.66),
  (36, @id_107, 7.17);

-- Versao 11.9 | 2026-01-26 → NULL
INSERT INTO tabela_preco (nome_tabela, tipo, versao, data_inicio_validade, data_fim_validade, ativa)
  VALUES ('VITAL', 'V', 11.9, '2026-01-26', NULL, 1);

SET @id_108 = LAST_INSERT_ID();

INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
  (1, @id_108, 11.92),
  (2, @id_108, 4.72),
  (3, @id_108, 7.00),
  (4, @id_108, 3.80),
  (5, @id_108, 8.00),
  (6, @id_108, 3.03),
  (7, @id_108, 42.65),
  (8, @id_108, 2.10),
  (9, @id_108, 26.37),
  (10, @id_108, 23.00),
  (11, @id_108, 12.70),
  (12, @id_108, 9.00),
  (13, @id_108, 8.62),
  (14, @id_108, 45.50),
  (15, @id_108, 55.06),
  (16, @id_108, 50.19),
  (17, @id_108, 4.10),
  (18, @id_108, 31.15),
  (19, @id_108, 17.16),
  (20, @id_108, 7.25),
  (21, @id_108, 11.80),
  (22, @id_108, 33.95),
  (23, @id_108, 11.21),
  (24, @id_108, 4.26),
  (25, @id_108, 14.33),
  (26, @id_108, 24.46),
  (27, @id_108, 7.40),
  (28, @id_108, 14.35),
  (29, @id_108, 16.97),
  (30, @id_108, 12.61),
  (31, @id_108, 7.49),
  (32, @id_108, 24.33),
  (33, @id_108, 18.20),
  (34, @id_108, 16.69),
  (35, @id_108, 7.66),
  (36, @id_108, 7.17);

-- ============================================================
-- Marca versao original (id=2) como historico
-- ============================================================
UPDATE tabela_preco SET data_fim_validade = '2024-01-07', ativa = 0 WHERE id_tabela = 2;

COMMIT;

-- Total: 109 versoes | 3924 registros de preco
INSERT INTO preco_produto_tabela (fk_produto, fk_tabela_preco, preco_produto) VALUES
(1, 4, 11.50),
(2, 4, 2.20),
(3, 4, 5.50),
(4, 4, 2.50),
(5, 4, 6.80),
(6, 4, 2.00),
(7, 4, 42.80),
(8, 4, 4.20),
(9, 4, 26.80),
(10, 4, 18.50),
(11, 4, 10.80),
(12, 4, 4.50),
(13, 4, 8.90),
(14, 4, 44.00),
(15, 4, 54.80),
(16, 4, 50.50),
(17, 4, 2.80),
(18, 4, 31.90),
(19, 4, 17.90),
(20, 4, 3.20),
(21, 4, 11.90),
(22, 4, 34.70),
(23, 4, 8.00),
(24, 4, 5.00),
(25, 4, 13.90),
(26, 4, 23.80),
(27, 4, 6.00),
(28, 4, 13.80),
(29, 4, 15.50),
(30, 4, 11.00),
(31, 4, 7.80),
(32, 4, 27.50),
(33, 4, 16.80),
(34, 4, 18.20),
(35, 4, 8.50),
(36, 4, 6.80);

-- ==========================================================
-- TABELA: cliente (FK -> endereco, tabela_preco)
-- ==========================================================
-- ADICIONADO O CAMPO 'ativo' COM O VALOR 1 (TRUE) PARA EVITAR O ERRO ANTERIOR
-- ==========================================================
-- CNPJs atualizados com formatos válidos para passar no Hibernate Validator
INSERT INTO cliente (CNPJ, razao_social, tel_contato, fk_endereco, fk_tabela_preco, ativo) VALUES
('43758362000100', 'Vital', '11977773333', 3, 2, 1),
('21683935000180', 'Metalúrgica Beta', '11988882222', 4, 2, 1);

-- ==========================================================
-- TABELA: fornecedor (FK -> endereco, tabela_preco)
-- ==========================================================
INSERT INTO fornecedor (fk_endereco, fk_tabela_preco, fk_usuario, nome, documento, tipo_fornecedor, telefone, apelido, ativo) VALUES
    (1, 1, 1, 'Carlos Eduardo Mendes',    '12345678901', 'PESSOA_FISICA', '11991234567', 'Carlos',   1),
    (2, 1, 1, 'Ana   Beatriz Ferreira',     '23456789012', 'PESSOA_FISICA', '21997654321', 'Ana',      1),
    (3, 1, 1, 'Ricardo Lopes da Silva',   '34567890123', 'PESSOA_FISICA', '31998887766', 'Ricardo',  1),
    (4, 1, 1, 'Mariana Santos Oliveira',  '45678901234', 'PESSOA_FISICA', '41998776655', 'Mariana',  1),
    (1, 1, 1, 'João Pedro Cavalcante',    '56789012345', 'PESSOA_FISICA', '11990011223', 'João',     1),
    (2, 1, 1, 'Letícia Moura Almeida',    '67890123456', 'PESSOA_FISICA', '21990022334', 'Leticia',  1),
    (3, 1, 1, 'Fernando Augusto Ribeiro', '78901234567', 'PESSOA_FISICA', '31995554433', 'Fernando', 1),
    (4, 1, 1, 'Patrícia Gomes Andrade',   '89012345678', 'PESSOA_FISICA', '41998889900', 'Patricia', 1),
    (1, 1, 1, 'Gustavo Henrique Martins', '90123456789', 'PESSOA_FISICA', '11993335577', 'Gustavo',  1),
    (2, 1, 1, 'Camila Rodrigues Silva',   '01234567890', 'PESSOA_FISICA', '21992228844', 'Camila',   1);


-- ==========================================================
-- TABELA: conta_pagamento (FK -> fornecedor)
-- ==========================================================
INSERT INTO conta_pagamento
(pix, banco, agencia, conta, tipo_conta, chave_pix, nome, pertence_fornecedor, documento, conta_ativa, fk_fornecedor) VALUES
                                                                                                                          (1, 'Banco do Brasil', '1234', '56789-0', 'C', 'carlos.mendes@pix.com',
                                                                                                                           'Carlos Eduardo Mendes', 1, '12345678901', 1, 1),

                                                                                                                          (0, 'Caixa', '4321', '12345-9', 'P', NULL,
                                                                                                                           'Ana Beatriz Ferreira', 1, '23456789012', 1, 2);

-- ==========================================================
-- TABELA: compra (FK -> fornecedor)
-- ==========================================================
INSERT INTO compra (fk_fornecedor, data_compra) VALUES
                                                    (1, '2026-04-03'),
                                                    (2, '2026-04-04'),
                                                    (3, '2026-04-05'),
                                                    (4, '2026-04-06'),
                                                    (5, '2026-04-07'),
                                                    (6, '2026-04-08'),
                                                    (7, '2026-04-09'),
                                                    (8, '2026-04-03'),
                                                    (9, '2026-04-04'),
                                                    (10,'2026-04-05'),

                                                    (1, '2026-04-06'),
                                                    (2, '2026-04-07'),
                                                    (3, '2026-04-08'),
                                                    (4, '2026-04-09'),
                                                    (5, '2026-04-03'),
                                                    (6, '2026-04-04'),
                                                    (7, '2026-04-05'),
                                                    (8, '2026-04-06'),
                                                    (9, '2026-04-07'),
                                                    (10,'2026-04-08'),

                                                    (1, '2026-04-09'),
                                                    (2, '2026-04-03'),
                                                    (3, '2026-04-04'),
                                                    (4, '2026-04-05'),
                                                    (5, '2026-04-06'),
                                                    (6, '2026-04-07'),
                                                    (7, '2026-04-08'),
                                                    (8, '2026-04-09'),
                                                    (9, '2026-04-03'),
                                                    (10,'2026-04-04'),

                                                    (1, '2026-04-05'),
                                                    (2, '2026-04-06'),
                                                    (3, '2026-04-07'),
                                                    (4, '2026-04-08'),
                                                    (5, '2026-04-09'),
                                                    (6, '2026-04-03'),
                                                    (7, '2026-04-04'),
                                                    (8, '2026-04-05'),
                                                    (9, '2026-04-06'),
                                                    (10,'2026-04-07'),

                                                    (1, '2026-04-08'),
                                                    (2, '2026-04-09'),
                                                    (3, '2026-04-03'),
                                                    (4, '2026-04-04'),
                                                    (5, '2026-04-05'),
                                                    (6, '2026-04-06'),
                                                    (7, '2026-04-07'),
                                                    (8, '2026-04-08'),
                                                    (9, '2026-04-09'),
                                                    (10,'2026-04-03');


-- ==========================================================
-- TABELA: item_pedido_compra (FK -> compra, produto)
-- ==========================================================

INSERT INTO item_pedido_compra (id_fk_compra, id_fk_produto, peso_kg, preco_unitario, rendimento) VALUES
                                                                                                      (1, 21, 300, 8.40, 660.00),
                                                                                                      (1, 28, 9, 10.30, 19.80),
                                                                                                      (1, 3, 8, 3.50, 4.00),
                                                                                                      (1, 17, 6, 0.70, 2.40),
                                                                                                      (1, 34, 9, 14.50, 19.80),
                                                                                                      (2, 21, 445, 8.40, 979.00),
                                                                                                      (2, 14, 10, 40.30, 22.00),
                                                                                                      (2, 4, 5, 1.00, 11.00),
                                                                                                      (2, 7, 10, 38.80, 22.00),
                                                                                                      (2, 19, 8, 14.30, 17.60),
                                                                                                      (3, 21, 500, 8.40, 1100.00),
                                                                                                      (3, 10, 7, 17.80, 15.40),
                                                                                                      (3, 33, 7, 13.40, 12.60),
                                                                                                      (3, 22, 8, 31.80, 9.60),
                                                                                                      (3, 36, 5, 3.30, 11.00),
                                                                                                      (4, 21, 65, 8.40, 143.00),
                                                                                                      (4, 31, 6, 4.00, 13.20),
                                                                                                      (4, 24, 7, 3.30, 1.40),
                                                                                                      (4, 12, 9, 3.80, 19.80),
                                                                                                      (4, 29, 7, 11.90, 15.40),
                                                                                                      (5, 21, 140, 8.40, 308.00),
                                                                                                      (5, 5, 9, 2.80, 19.80),
                                                                                                      (5, 23, 7, 7.30, 15.40),
                                                                                                      (5, 1, 4, 8.40, 6.40),
                                                                                                      (5, 8, 5, 2.80, 1.00),
                                                                                                      (6, 21, 345, 8.40, 759.00),
                                                                                                      (6, 11, 4, 7.50, 8.80),
                                                                                                      (6, 20, 10, 2.20, 22.00),
                                                                                                      (6, 27, 2, 5.00, 4.40),
                                                                                                      (6, 32, 5, 23.80, 11.00),
                                                                                                      (7, 21, 300, 8.40, 660.00),
                                                                                                      (7, 35, 10, 5.00, 22.00),
                                                                                                      (7, 6, 6, 1.40, 9.60),
                                                                                                      (7, 18, 8, 28.30, 17.60),
                                                                                                      (7, 2, 3, 2.00, 3.00),
                                                                                                      (8, 21, 140, 8.40, 308.00),
                                                                                                      (8, 26, 10, 20.00, 22.00),
                                                                                                      (8, 13, 6, 5.30, 13.20),
                                                                                                      (8, 9, 10, 22.80, 22.00),
                                                                                                      (8, 30, 2, 10.40, 4.40),
                                                                                                      (9, 21, 200, 8.40, 440.00),
                                                                                                      (9, 16, 4, 46.80, 8.80),
                                                                                                      (9, 25, 10, 10.30, 22.00),
                                                                                                      (9, 15, 6, 50.90, 13.20),
                                                                                                      (9, 3, 6, 3.50, 3.00),
                                                                                                      (10, 21, 305, 8.40, 671.00),
                                                                                                      (10, 33, 4, 13.40, 7.20),
                                                                                                      (10, 22, 4, 31.80, 4.80),
                                                                                                      (10, 11, 4, 7.50, 8.80),
                                                                                                      (10, 18, 3, 28.30, 6.60),
                                                                                                      (11, 21, 445, 8.40, 979.00),
                                                                                                      (11, 34, 2, 14.50, 4.40),
                                                                                                      (11, 31, 3, 4.00, 6.60),
                                                                                                      (11, 7, 7, 38.80, 15.40),
                                                                                                      (11, 32, 9, 23.80, 19.80),
                                                                                                      (12, 21, 200, 8.40, 440.00),
                                                                                                      (12, 19, 10, 14.30, 22.00),
                                                                                                      (12, 10, 7, 17.80, 15.40),
                                                                                                      (12, 5, 3, 2.80, 6.60),
                                                                                                      (12, 23, 9, 7.30, 19.80),
                                                                                                      (13, 21, 400, 8.40, 880.00),
                                                                                                      (13, 1, 5, 8.40, 8.00),
                                                                                                      (13, 26, 3, 20.00, 6.60),
                                                                                                      (13, 35, 4, 5.00, 8.80),
                                                                                                      (13, 8, 10, 2.80, 2.00),
                                                                                                      (14, 21, 455, 8.40, 1001.00),
                                                                                                      (14, 2, 3, 2.00, 3.00),
                                                                                                      (14, 20, 2, 2.20, 4.40),
                                                                                                      (14, 9, 3, 22.80, 6.60),
                                                                                                      (14, 14, 3, 40.30, 6.60),
                                                                                                      (15, 21, 215, 8.40, 473.00),
                                                                                                      (15, 24, 2, 3.30, 0.40),
                                                                                                      (15, 6, 7, 1.40, 11.20),
                                                                                                      (15, 3, 5, 3.50, 2.50),
                                                                                                      (15, 27, 8, 5.00, 17.60),
                                                                                                      (16, 21, 300, 8.40, 660.00),
                                                                                                      (16, 33, 3, 13.40, 5.40),
                                                                                                      (16, 25, 2, 10.30, 4.40),
                                                                                                      (16, 28, 9, 10.30, 19.80),
                                                                                                      (16, 11, 6, 7.50, 13.20),
                                                                                                      (17, 21, 160, 8.40, 352.00),
                                                                                                      (17, 30, 10, 10.40, 22.00),
                                                                                                      (17, 19, 9, 14.30, 19.80),
                                                                                                      (17, 34, 5, 14.50, 11.00),
                                                                                                      (17, 17, 7, 0.70, 2.80),
                                                                                                      (18, 21, 270, 8.40, 594.00),
                                                                                                      (18, 36, 9, 3.30, 19.80),
                                                                                                      (18, 12, 3, 3.80, 6.60),
                                                                                                      (18, 10, 4, 17.80, 8.80),
                                                                                                      (18, 13, 7, 5.30, 15.40),
                                                                                                      (19, 21, 235, 8.40, 515.00),
                                                                                                      (19, 15, 10, 50.90, 22.00),
                                                                                                      (19, 4, 6, 1.00, 13.20),
                                                                                                      (19, 22, 6, 31.80, 7.20),
                                                                                                      (19, 7, 10, 38.80, 22.00),
                                                                                                      (20, 21, 80, 8.40, 176.00),
                                                                                                      (20, 8, 5, 2.80, 1.00),
                                                                                                      (20, 32, 10, 23.80, 22.00),
                                                                                                      (20, 1, 6, 8.40, 9.60),
                                                                                                      (20, 29, 10, 11.90, 22.00),
                                                                                                      (21, 21, 355, 8.40, 781.00),
                                                                                                      (21, 9, 7, 22.80, 15.40),
                                                                                                      (21, 3, 2, 3.50, 1.00),
                                                                                                      (21, 14, 7, 40.30, 15.40),
                                                                                                      (21, 6, 7, 1.40, 11.20),
                                                                                                      (22, 21, 155, 8.40, 341.00),
                                                                                                      (22, 24, 9, 3.30, 1.80),
                                                                                                      (22, 18, 5, 28.30, 11.00),
                                                                                                      (22, 23, 2, 7.30, 4.40),
                                                                                                      (22, 30, 10, 10.40, 22.00),
                                                                                                      (23, 21, 485, 8.40, 1067.00),
                                                                                                      (23, 27, 4, 5.00, 8.80),
                                                                                                      (23, 31, 10, 4.00, 22.00),
                                                                                                      (23, 15, 8, 50.90, 17.60),
                                                                                                      (23, 26, 10, 20.00, 22.00),
                                                                                                      (24, 21, 120, 8.40, 264.00),
                                                                                                      (24, 17, 9, 0.70, 3.60),
                                                                                                      (24, 16, 8, 46.80, 17.60),
                                                                                                      (24, 10, 6, 17.80, 13.20),
                                                                                                      (24, 1, 7, 8.40, 11.20),
                                                                                                      (25, 21, 410, 8.40, 902.00),
                                                                                                      (25, 35, 5, 5.00, 11.00),
                                                                                                      (25, 32, 5, 23.80, 11.00),
                                                                                                      (25, 6, 6, 1.40, 9.60),
                                                                                                      (25, 25, 10, 10.30, 22.00),
                                                                                                      (26, 21, 365, 8.40, 803.00),
                                                                                                      (26, 9, 10, 22.80, 22.00),
                                                                                                      (26, 7, 3, 38.80, 6.60),
                                                                                                      (26, 11, 6, 7.50, 13.20),
                                                                                                      (26, 20, 5, 2.20, 11.00),
                                                                                                      (27, 21, 270, 8.40, 594.00),
                                                                                                      (27, 5, 3, 2.80, 6.60),
                                                                                                      (27, 22, 10, 31.80, 12.00),
                                                                                                      (27, 13, 2, 5.30, 4.40),
                                                                                                      (27, 3, 5, 3.50, 2.50),
                                                                                                      (28, 21, 205, 8.40, 451.00),
                                                                                                      (28, 10, 3, 17.80, 6.60),
                                                                                                      (28, 34, 3, 14.50, 6.60),
                                                                                                      (28, 8, 2, 2.80, 0.40),
                                                                                                      (28, 33, 4, 13.40, 7.20),
                                                                                                      (29, 21, 300, 8.40, 660.00),
                                                                                                      (29, 29, 3, 11.90, 6.60),
                                                                                                      (29, 31, 8, 4.00, 17.60),
                                                                                                      (29, 4, 6, 1.00, 13.20),
                                                                                                      (29, 17, 7, 0.70, 2.80),
                                                                                                      (30, 21, 400, 8.40, 880.00),
                                                                                                      (30, 19, 7, 14.30, 15.40),
                                                                                                      (30, 28, 5, 10.30, 11.00),
                                                                                                      (30, 18, 3, 28.30, 6.60),
                                                                                                      (30, 12, 3, 3.80, 6.60),
                                                                                                      (31, 21, 390, 8.40, 858.00),
                                                                                                      (31, 14, 7, 40.30, 15.40),
                                                                                                      (31, 32, 6, 23.80, 13.20),
                                                                                                      (31, 2, 8, 2.00, 8.00),
                                                                                                      (31, 27, 10, 5.00, 22.00),
                                                                                                      (32, 21, 465, 8.40, 1023.00),
                                                                                                      (32, 7, 4, 38.80, 8.80),
                                                                                                      (32, 9, 8, 22.80, 17.60),
                                                                                                      (32, 1, 3, 8.40, 4.80),
                                                                                                      (32, 25, 9, 10.30, 19.80),
                                                                                                      (33, 21, 145, 8.40, 319.00),
                                                                                                      (33, 16, 5, 46.80, 11.00),
                                                                                                      (33, 5, 2, 2.80, 4.40),
                                                                                                      (33, 35, 8, 5.00, 17.60),
                                                                                                      (33, 11, 4, 7.50, 8.80),
                                                                                                      (34, 21, 320, 8.40, 704.00),
                                                                                                      (34, 4, 9, 1.00, 19.80),
                                                                                                      (34, 26, 8, 20.00, 17.60),
                                                                                                      (34, 3, 8, 3.50, 4.00),
                                                                                                      (34, 24, 3, 3.30, 0.60),
                                                                                                      (35, 21, 140, 8.40, 308.00),
                                                                                                      (35, 18, 10, 28.30, 22.00),
                                                                                                      (35, 28, 2, 10.30, 4.40),
                                                                                                      (35, 30, 9, 10.40, 19.80),
                                                                                                      (35, 36, 6, 3.30, 13.20),
                                                                                                      (36, 21, 450, 8.40, 990.00),
                                                                                                      (36, 6, 7, 1.40, 11.20),
                                                                                                      (36, 31, 5, 4.00, 11.00),
                                                                                                      (36, 19, 7, 14.30, 15.40),
                                                                                                      (36, 23, 7, 7.30, 15.40),
                                                                                                      (37, 21, 220, 8.40, 484.00),
                                                                                                      (37, 32, 8, 23.80, 17.60),
                                                                                                      (37, 8, 2, 2.80, 0.40),
                                                                                                      (37, 20, 8, 2.20, 17.60),
                                                                                                      (37, 29, 9, 11.90, 19.80),
                                                                                                      (38, 21, 365, 8.40, 803.00),
                                                                                                      (38, 12, 10, 3.80, 22.00),
                                                                                                      (38, 14, 2, 40.30, 4.40),
                                                                                                      (38, 7, 10, 38.80, 22.00),
                                                                                                      (38, 9, 3, 22.80, 6.60),
                                                                                                      (39, 21, 230, 8.40, 506.00),
                                                                                                      (39, 30, 4, 10.40, 8.80),
                                                                                                      (39, 27, 4, 5.00, 8.80),
                                                                                                      (39, 10, 4, 17.80, 8.80),
                                                                                                      (39, 34, 3, 14.50, 6.60),
                                                                                                      (40, 21, 260, 8.40, 572.00),
                                                                                                      (40, 1, 8, 8.40, 12.80),
                                                                                                      (40, 26, 5, 20.00, 11.00),
                                                                                                      (40, 21, 100, 8.40, 220.00),
                                                                                                      (40, 28, 6, 10.30, 13.20),
                                                                                                      (41, 21, 140, 8.40, 308.00),
                                                                                                      (41, 17, 10, 0.70, 4.00),
                                                                                                      (41, 33, 7, 13.40, 12.60),
                                                                                                      (41, 3, 10, 3.50, 5.00),
                                                                                                      (41, 18, 2, 28.30, 4.40),
                                                                                                      (42, 21, 480, 8.40, 1056.00),
                                                                                                      (42, 13, 9, 5.30, 19.80),
                                                                                                      (42, 16, 3, 46.80, 6.60),
                                                                                                      (42, 21, 60, 8.40, 132.00),
                                                                                                      (42, 32, 7, 23.80, 15.40),
                                                                                                      (43, 21, 350, 8.40, 770.00),
                                                                                                      (43, 15, 4, 50.90, 8.80),
                                                                                                      (43, 2, 4, 2.00, 4.00),
                                                                                                      (43, 25, 9, 10.30, 19.80),
                                                                                                      (43, 5, 8, 2.80, 17.60),
                                                                                                      (44, 21, 205, 8.40, 451.00),
                                                                                                      (44, 31, 5, 4.00, 11.00),
                                                                                                      (44, 29, 4, 11.90, 8.80),
                                                                                                      (44, 6, 7, 1.40, 11.20),
                                                                                                      (44, 23, 5, 7.30, 11.00),
                                                                                                      (45, 21, 310, 8.40, 682.00),
                                                                                                      (45, 24, 7, 3.30, 2.80),
                                                                                                      (45, 12, 4, 3.80, 8.80),
                                                                                                      (45, 9, 6, 22.80, 13.20),
                                                                                                      (45, 22, 3, 31.80, 3.60),
                                                                                                      (46, 21, 390, 8.40, 858.00),
                                                                                                      (46, 19, 4, 14.30, 8.80),
                                                                                                      (46, 35, 8, 5.00, 17.60),
                                                                                                      (46, 7, 5, 38.80, 11.00),
                                                                                                      (46, 28, 3, 10.30, 6.60),
                                                                                                      (47, 21, 185, 8.40, 407.00),
                                                                                                      (47, 20, 3, 2.20, 6.60),
                                                                                                      (47, 34, 5, 14.50, 11.00),
                                                                                                      (47, 11, 4, 7.50, 8.80),
                                                                                                      (47, 18, 10, 28.30, 22.00),
                                                                                                      (48, 21, 280, 8.40, 616.00),
                                                                                                      (48, 36, 7, 3.30, 15.40),
                                                                                                      (48, 27, 9, 5.00, 19.80),
                                                                                                      (48, 4, 4, 1.00, 8.80),
                                                                                                      (48, 30, 8, 10.40, 17.60),
                                                                                                      (49, 21, 300, 8.40, 660.00),
                                                                                                      (49, 32, 10, 23.80, 22.00),
                                                                                                      (49, 5, 3, 2.80, 6.60),
                                                                                                      (49, 17, 8, 0.70, 3.20),
                                                                                                      (49, 16, 3, 46.80, 6.60),
                                                                                                      (50, 21, 260, 8.40, 572.00),
                                                                                                      (50, 33, 8, 13.40, 16.00),
                                                                                                      (50, 1, 4, 8.40, 6.40),
                                                                                                      (50, 26, 7, 20.00, 15.40),
                                                                                                      (50, 29, 6, 11.90, 13.20);


-- ==========================================================
-- TABELA: pagamento_compra (FK -> compra, conta_pagamento)
-- ==========================================================
INSERT INTO pagamento_compra (fk_compra, data_pagamento_compra, fk_conta_pagamento) VALUES
                                                                                        (1, '2026-04-10', 1),
                                                                                        (2, '2026-04-11', 2);

-- ==========================================================
-- TABELA: venda (FK -> cliente, tabela_preco)
-- ==========================================================
INSERT INTO venda (fk_cliente, data_venda) VALUES
                                               (1, '2026-04-12'),
                                               (2, '2026-04-13');

-- ==========================================================
-- TABELA: item_pedido_venda (FK -> produto, venda)
-- ==========================================================
INSERT INTO item_pedido_venda (id_fk_produto, id_fk_venda, peso_kg, preco_unitario) VALUES
                                                                                        (1, 1, 20, 10.00),
                                                                                        (2, 1, 10, 3.00),
                                                                                        (3, 2, 30, 4.00);
