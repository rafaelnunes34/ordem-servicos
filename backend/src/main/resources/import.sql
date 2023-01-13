INSERT INTO tb_role (authority) VALUES ('ADMINISTRADOR');
INSERT INTO tb_role (authority) VALUES ('ATENDENTE');

INSERT INTO tb_user (name, email, password) VALUES ('Rafael Nunes', 'rafael@gmail.com', '123456');
INSERT INTO tb_user (name, email, password) VALUES ('Pedro Alves', 'pedro@gmail.com', '123456');

INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 2);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 2);

INSERT INTO tb_service (description, price) VALUES ('TROCA DE OLEO E FILTRO', 80.00);
INSERT INTO tb_service (description, price) VALUES ('RECARGA DE AR NO AR-CONDICIONADO', 120.00);
INSERT INTO tb_service (description, price) VALUES ('TROCA DE LAMPADA FAROL OU LANTERNA', 15.00);
INSERT INTO tb_service (description, price) VALUES ('UTILIZAR SCANNER', 75.00);
INSERT INTO tb_service (description, price) VALUES ('TROCAR ROLAMENTO', 50.00);

INSERT INTO tb_brand_vehicle (name) VALUES ('FIAT');
INSERT INTO tb_brand_vehicle (name) VALUES ('CHEVROLET');

INSERT INTO tb_model_vehicle (name, vehicle_brand_id) VALUES ('PALIO FIRE 1.0', 1);
INSERT INTO tb_model_vehicle (name, vehicle_brand_id) VALUES ('CELTA LTS 1.5', 2);

INSERT INTO tb_client (name, cpf, phone) VALUES ('Dayane Bastos', '11111111111', '21999999999');

INSERT INTO tb_vehicle (license_plate, color, year_manufacture, model_id, client_id) VALUES ('LS527J82', 'VERMELHO', '2015', 2, 1);

INSERT INTO tb_order (moment, status, user_id, client_id, vehicle_id) VALUES (TIMESTAMP WITH TIME ZONE '2022-10-20T09:00:00Z', 'FINALIZADO', 2, 1, 1);
INSERT INTO tb_order_service_item (unity, price, order_id, service_id) VALUES (1, 80.0, 1, 1);

INSERT INTO tb_payment (moment, type, price, discount, order_id) VALUES (TIMESTAMP WITH TIME ZONE '2022-10-20T12:05:00Z', 'DINHEIRO', 80.0, null, 1);

INSERT INTO tb_order (moment, status, user_id, client_id, vehicle_id) VALUES (TIMESTAMP WITH TIME ZONE '2023-01-10T03:00:00Z', 'PENDENTE', 1, 1, 1);
INSERT INTO tb_order_service_item (unity, price, order_id, service_id) VALUES (1, 120.0, 2, 2);