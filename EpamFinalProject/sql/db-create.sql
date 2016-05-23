--==============================================================
-- ST4Example DB creation script for PostgreSQL
--==============================================================

-- these commands remove all tables from the database
-- it implies an error if tables not exist in DB, just ignore it

DROP TABLE flights_users;
DROP TABLE requests;
DROP TABLE flights;
DROP TABLE users;
DROP TABLE roles;
DROP TABLE status;
DROP TABLE trucks;

--==============================================================
-- DB creation roles
--==============================================================
CREATE TABLE roles (
  id   SERIAL PRIMARY KEY NOT NULL,
  role VARCHAR(10)
);

INSERT INTO roles (role) VALUES ('admin');
INSERT INTO roles (role) VALUES ('driver');
INSERT INTO roles (role) VALUES ('dispatcher');

--==============================================================
-- DB creation status
--==============================================================
CREATE TABLE status (
  id     SERIAL PRIMARY KEY NOT NULL,
  status VARCHAR(11)
);

INSERT INTO status (status) VALUES ('open');
INSERT INTO status (status) VALUES ('in progress');
INSERT INTO status (status) VALUES ('closed');
INSERT INTO status (status) VALUES ('canceled');

--==============================================================
-- DB creation trucks
--==============================================================
CREATE TABLE trucks (
  id                     SERIAL PRIMARY KEY NOT NULL,
  truck_name             VARCHAR(25)        NOT NULL,
  truck_carrying         DECIMAL,
  truck_capacity         DECIMAL,
  truck_length           DECIMAL,
  truck_lorry_with_sides BOOLEAN DEFAULT FALSE,
  truck_refrigerator     BOOLEAN DEFAULT FALSE,
  truck_serviceable      BOOLEAN DEFAULT TRUE,
  truck_photo_link       VARCHAR(25),
  truck_count_flights    INT
);

INSERT INTO trucks (truck_name, truck_carrying, truck_capacity, truck_length, truck_serviceable, truck_count_flights)
VALUES ('Gazel', 1.5, 8, 3, 'false', 0);
INSERT INTO trucks (truck_name, truck_carrying, truck_capacity, truck_length, truck_lorry_with_sides, truck_photo_link, truck_count_flights)
VALUES ('Nissan', 1, 4, 2.5, 'true', 'truck01.jpg', 0);
INSERT INTO trucks (truck_name, truck_carrying, truck_capacity, truck_length, truck_photo_link, truck_count_flights)
VALUES ('MAN', 20, 79, 13.6, 'truck02.jpg', 0);
INSERT INTO trucks (truck_name, truck_carrying, truck_capacity, truck_length, truck_lorry_with_sides, truck_photo_link, truck_count_flights)
VALUES ('Mercedes', 18, 36, 9.2, 'true', 'truck03.jpg', 0);
INSERT INTO trucks (truck_name, truck_carrying, truck_capacity, truck_length, truck_lorry_with_sides, truck_photo_link, truck_count_flights)
VALUES ('Toyota ToyoAce', 2, 7.5, 2.8, 'true', 'truck04.jpg', 0);
INSERT INTO trucks (truck_name, truck_carrying, truck_capacity, truck_length, truck_photo_link, truck_count_flights)
VALUES ('Peugeot', 3, 18, 2.9, 'truck05.jpg', 0);
INSERT INTO trucks (truck_name, truck_carrying, truck_capacity, truck_length, truck_refrigerator, truck_photo_link, truck_count_flights)
VALUES ('GMC', 8, 39.7, 7.3, 'true', 'truck06.jpg', 0);
INSERT INTO trucks (truck_name, truck_carrying, truck_capacity, truck_length, truck_lorry_with_sides, truck_photo_link, truck_count_flights)
VALUES ('ISUZU', 12, 34.2, 9.3, 'true', 'truck07.jpg', 0);
INSERT INTO trucks (truck_name, truck_carrying, truck_capacity, truck_length, truck_refrigerator, truck_photo_link, truck_count_flights)
VALUES ('Mercedes', 12, 63.9, 6.3, 'true', 'truck08.jpg', 0);
INSERT INTO trucks (truck_name, truck_carrying, truck_capacity, truck_length, truck_refrigerator, truck_photo_link, truck_count_flights)
VALUES ('TATA', 25.2, 41, 3.2, 'true', 'truck09.jpg', 0);
INSERT INTO trucks (truck_name, truck_carrying, truck_capacity, truck_length, truck_lorry_with_sides, truck_photo_link, truck_count_flights)
VALUES ('ЗИЛ', 2.5, 6, 2.2, 'true', 'truck10.jpg', 0);

--==============================================================
-- DB creation users
--==============================================================
CREATE TABLE users (
  id              SERIAL PRIMARY KEY NOT NULL,
  user_login      VARCHAR(10)        NOT NULL UNIQUE,
  user_password   VARCHAR(10)        NOT NULL,
  user_first_name VARCHAR(20)        NOT NULL,
  user_last_name  VARCHAR(20)        NOT NULL,
  user_photo_link VARCHAR(25),
  user_mail       VARCHAR(35),
  user_role_id    INT                NOT NULL REFERENCES roles (id)
  ON DELETE CASCADE
);

INSERT INTO users (user_login, user_password, user_first_name, user_last_name, user_photo_link, user_role_id)
VALUES ('admin', 'admin', 'Иван', 'Иванов', 'user1.jpg', 1);
INSERT INTO users (user_login, user_password, user_first_name, user_last_name, user_photo_link, user_mail, user_role_id)
VALUES ('driver', 'driver', 'Petr', 'Petrov', 'user2.jpg', 'alex.dnepr@i.ua', 2);
INSERT INTO users (user_login, user_password, user_first_name, user_last_name, user_photo_link, user_role_id)
VALUES ('dispatcher', 'dispatcher', 'Вася', 'Васин', 'user3.jpg', 3);
INSERT INTO users (user_login, user_password, user_first_name, user_last_name, user_photo_link, user_mail, user_role_id)
VALUES ('Вася', 'вася', 'Джейсон', 'Стэтхэм', 'user4.jpg', 'alexdnepr9999@gmail.com', 2);
INSERT INTO users (user_login, user_password, user_first_name, user_last_name, user_photo_link, user_mail, user_role_id)
VALUES ('epam', 'epam', 'Алексей', 'Михалыч', 'user5.jpg', 'alex.dnepr@i.ua', 2);
INSERT INTO users (user_login, user_password, user_first_name, user_last_name, user_photo_link, user_mail, user_role_id)
VALUES ('1', '1', 'Александр', 'Сергеевич', 'user4.jpg', 'anna.btl@mail.ru', 1);

--==============================================================
-- DB creation flight
--==============================================================
CREATE TABLE flights (
  id                           SERIAL PRIMARY KEY NOT NULL,
  flight_number                INT                NOT NULL,
  flight_date_creation         DATE,
  flight_date_departure        DATE               NOT NULL,
  flight_destination           VARCHAR(35)        NOT NULL,
  flight_distance              DECIMAL,
  flight_status_id             INT REFERENCES status (id),
  flight_truck_id              INT REFERENCES trucks (id),
  flight_driver_id             INT REFERENCES users (id),
  flight_dispatcher_id_create  INT REFERENCES users (id),
  flight_dispatcher_id_approve INT REFERENCES users (id)
);

INSERT INTO flights (flight_number, flight_date_creation, flight_date_departure, flight_destination, flight_distance,
                     flight_status_id, flight_dispatcher_id_create)
VALUES ('147', '2016-01-01', '2016-03-01', 'наб. Заводская, 63', 18.1, 1, 1);
INSERT INTO flights (flight_number, flight_date_creation, flight_date_departure, flight_destination, flight_distance,
                     flight_status_id, flight_truck_id, flight_driver_id, flight_dispatcher_id_create,
                     flight_dispatcher_id_approve)
VALUES ('4', '2015-11-21', '2016-03-11', 'ул. Спортивная, 1', 28.2, 2, 2, 2, 1, 1);
INSERT INTO flights (flight_number, flight_date_creation, flight_date_departure, flight_destination, flight_distance,
                     flight_status_id, flight_truck_id, flight_driver_id, flight_dispatcher_id_create,
                     flight_dispatcher_id_approve)
VALUES ('65', '2016-01-21', '2016-03-21', 'пр. К.Маркса, 13', 78.4, 3, 3, 5, 1, 1);
INSERT INTO flights (flight_number, flight_date_creation, flight_date_departure, flight_destination, flight_distance,
                     flight_status_id, flight_truck_id, flight_driver_id, flight_dispatcher_id_create,
                     flight_dispatcher_id_approve)
VALUES ('45', '2016-02-01', '2016-03-03', 'г. Киев, пр. Ленина, 63', 228.5, 4, 4, 4, 1, 1);
INSERT INTO flights (flight_number, flight_date_creation, flight_date_departure, flight_destination, flight_distance,
                     flight_status_id, flight_dispatcher_id_create)
VALUES ('12', '2016-03-01', '2016-03-05', 'пр. Пушкина, 11', 13.2, 1, 1);
INSERT INTO flights (flight_number, flight_date_creation, flight_date_departure, flight_destination, flight_distance,
                     flight_status_id, flight_dispatcher_id_create)
VALUES ('137', '2016-02-27', '2016-03-03', 'пр. Кирова, 7', 59.7, 1, 1);
INSERT INTO flights (flight_number, flight_date_creation, flight_date_departure, flight_destination, flight_distance,
                     flight_status_id, flight_dispatcher_id_create)
VALUES ('189', '2016-02-28', '2016-03-05', 'ул. Ленина, 97', 43.2, 1, 1);
--==============================================================
-- DB creation request
--==============================================================
CREATE TABLE requests (
  id                       SERIAL PRIMARY KEY NOT NULL,
  request_flight_id        INT                NOT NULL REFERENCES flights (id),
  request_carrying         DECIMAL,
  request_capacity         DECIMAL,
  request_length           DECIMAL,
  request_lorry_with_sides BOOLEAN DEFAULT FALSE,
  request_refrigerator     BOOLEAN DEFAULT FALSE,
  request_driver_id        INT REFERENCES users (id)
);

INSERT INTO requests (request_flight_id, request_carrying, request_capacity, request_length, request_driver_id)
VALUES (1, 11, 21, 11, 4);
INSERT INTO requests (request_flight_id, request_carrying, request_capacity, request_length, request_driver_id,
                      request_lorry_with_sides)
VALUES (5, 1.9, 5, 2.1, 2, 'true');
INSERT INTO requests (request_flight_id, request_carrying, request_capacity, request_length, request_driver_id,
                      request_refrigerator)
VALUES (1, 1.7, 2.5, 3.1, 5, 'true');
INSERT INTO requests (request_flight_id, request_carrying, request_capacity, request_length, request_driver_id,
                      request_lorry_with_sides)
VALUES (5, 11.1, 25, 9, 5, 'true');

--==============================================================
-- DB creation flights_users
--==============================================================
CREATE TABLE flights_users (
  id                      SERIAL PRIMARY KEY NOT NULL,
  flights_users_flight_id INT REFERENCES flights (id),
  flights_users_driver_id INT REFERENCES users (id)
);

INSERT INTO flights_users (flights_users_flight_id, flights_users_driver_id) VALUES (1, 4);
INSERT INTO flights_users (flights_users_flight_id, flights_users_driver_id) VALUES (5, 2);
INSERT INTO flights_users (flights_users_flight_id, flights_users_driver_id) VALUES (1, 5);
INSERT INTO flights_users (flights_users_flight_id, flights_users_driver_id) VALUES (5, 5);