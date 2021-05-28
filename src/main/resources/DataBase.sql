SET FOREIGN_KEY_CHECKS = 0;

CREATE SCHEMA IF NOT EXISTS mydb;
USE mydb;

DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS client;#Y
DROP TABLE IF EXISTS client_card;#Y
DROP TABLE IF EXISTS card_type;#Y
DROP TABLE IF EXISTS card_service;#Y
DROP TABLE IF EXISTS service;#Y
DROP TABLE IF EXISTS gym;#Y

CREATE TABLE orders
(
    id        INT NOT NULL AUTO_INCREMENT,
    client_id INT NOT NULL,
    gym_id    INT NOT NULL,
    service_id INT NOT NULL,
    PRIMARY KEY (id),
    INDEX fk_orders_gym1_idx (gym_id ASC) VISIBLE,
    INDEX fk_orders_client1_idx (client_id ASC) VISIBLE,
    CONSTRAINT fk_orders_gym1
        FOREIGN KEY (gym_id)
            REFERENCES gym (id),
    CONSTRAINT fk_orders_client1
        FOREIGN KEY (client_id)
            REFERENCES `client` (id),
    CONSTRAINT fk_orders_service1
        FOREIGN KEY (service_id)
            REFERENCES service(id)
)
    ENGINE = InnoDB;

CREATE TABLE client_card
(
    id             INT         NOT NULL AUTO_INCREMENT,
    card_type_id INT NOT NULL,
    PRIMARY KEY (id),
    INDEX `fk_client_card_card_type1_idx` (card_type_id ASC) VISIBLE,
    CONSTRAINT `fk_client_card_card_type1`
        FOREIGN KEY (card_type_id)
            REFERENCES `mydb`.`card_type` (id)
)
    ENGINE = InnoDB;

CREATE TABLE client
(
    id             INT         NOT NULL AUTO_INCREMENT,
    name           VARCHAR(45) NOT NULL,
    surname        VARCHAR(45) NULL,
    email          VARCHAR(45) NULL,
    client_card_id INT         NOT NULL,
    PRIMARY KEY (id, client_card_id),
    INDEX fk_client_client_card1_idx (client_card_id ASC) VISIBLE,
    CONSTRAINT fk_client_client_card1
        FOREIGN KEY (client_card_id)
            REFERENCES client_card (id)
)
    ENGINE = InnoDB;



CREATE TABLE card_type
(
    id   INT         NOT NULL AUTO_INCREMENT,
    name VARCHAR(45) NOT NULL,
    PRIMARY KEY (id)
)
    ENGINE = InnoDB;


CREATE TABLE card_service
(

    service_id   INT NOT NULL,
    card_type_id INT NOT NULL,
    PRIMARY KEY (service_id, card_type_id),
    CONSTRAINT fk_card_service_service1
        FOREIGN KEY (service_id)
            REFERENCES service (id),
    CONSTRAINT fk_card_service_card_type1
        FOREIGN KEY (card_type_id)
            REFERENCES card_type (id)
)
    ENGINE = InnoDB;


CREATE TABLE service
(
    id    INT           NOT NULL AUTO_INCREMENT,
    name  VARCHAR(45)   NOT NULL,
    price DECIMAL(8, 2) NULL,
    UNIQUE INDEX name_UNIQUE (name ASC) VISIBLE,
    PRIMARY KEY (id)
)
    ENGINE = InnoDB;


CREATE TABLE gym
(
    id       INT          NOT NULL AUTO_INCREMENT,
    location VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
)
    ENGINE = InnoDB;


SET FOREIGN_KEY_CHECKS = 1;



INSERT INTO card_type(name)
VALUES ('Gold'),
       ('Premium'),
       ('Platinum');

INSERT INTO client_card(id, card_type_id)
VALUES (1, 1),
       (2, 2),
       (3, 3);

INSERT INTO `client`(name, client_card_id)
VALUES ('Orest', 1),
       ('Kate', 2),
       ('Mike', 3);



INSERT INTO gym(location)
VALUES ('Lviv'),
       ('Kyiv'),
       ('Dnipro');


INSERT INTO service(name)
VALUES ('Gym'),
       ('Pool'),
       ('SPA'),
       ('Massage');


INSERT INTO card_service(card_type_id, service_id)
VALUES (1, 1),
       (2, 1),
       (3, 1),
       (2, 2),
       (3, 3);

INSERT INTO orders (client_id,
                    gym_id, service_id)
VALUES (1, 1, 1),
       (2, 3, 1),
       (3, 2, 1);


