CREATE SCHEMA car_db;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `firstname` varchar(45) NOT NULL,
  `lastname` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` varbinary(255) NOT NULL,
  `phone` varchar(45) NOT NULL,
  `age` int NOT NULL,
  `role` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
INSERT INTO users (firstname, lastname, email, password, phone, age, role) VALUES ('Manu','Bombardierul','admin',aes_encrypt(concat('1234', 'admin'), 'key1234'),'0745678912','40','admin');

CREATE TABLE `cars` (
  `id` int NOT NULL AUTO_INCREMENT,
  `carname` varchar(45) NOT NULL,
  `carprice` int NOT NULL,
  `carimg` varchar(45) NOT NULL,
  `cardetails` varchar(100) NOT NULL,
  `color1` varchar(45) NOT NULL,
  `color2` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `carimg_UNIQUE` (`carimg`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO cars(carname, carprice, carimg, cardetails, color1, color2) VALUES ('BMW 1500', 170, 'masini/bmw2.png', '- Made in 1962\n- 5 seats', '9c917C', '4F483F');
INSERT INTO cars(carname, carprice, carimg, cardetails, color1, color2) VALUES ('BMW Oldtimer 1502', 150, 'masini/bmw1.png', '- Made in 1983\n- 5 seats', '8B9692', '46844F');
INSERT INTO cars(carname, carprice, carimg, cardetails, color1, color2) VALUES ('Mercedes Benz 300se', 180, 'masini/mercedes1.png', '- Made in 1978\n- 2 seats', '8896A0', '31444A');
INSERT INTO cars(carname, carprice, carimg, cardetails, color1, color2) VALUES ('Chevrolet Bel Air"', 210, 'masini/chevrolet1.png', '- Series 2400 C\n- Made in 1978\n- 5 seats', 'FFB291', '964439');
INSERT INTO cars(carname, carprice, carimg, cardetails, color1, color2) VALUES ('Volkswagen Bus T1', 230, 'masini/vw-4309412_640.png', '- Designed for the perfect\n camping experience\n- 9 seats', '019F8E', '2A52A4');
INSERT INTO cars(carname, carprice, carimg, cardetails, color1, color2) VALUES ('Mercedes Benz Dash-8', 145, 'masini/mercedes-benz-3168571_1280(1).png', '- Made in 1989\n- 2 seats', 'F9DD78', 'FD6A03');
INSERT INTO cars(carname, carprice, carimg, cardetails, color1, color2) VALUES ('Volkswagen Karmann Ghia', 170, 'masini/volkswagen-3754571_640.png', '- Made in 1969\n- Isolated or convertible\n- 2 seats', 'A5FE83', '02A08E');
INSERT INTO cars(carname, carprice, carimg, cardetails, color1, color2) VALUES ('Audi Austin 7', 120, 'masini/audi1.png', '- Manufactured in 1934\n- 1 seats', '692D78', 'ED80EF');
INSERT INTO cars(carname, carprice, carimg, cardetails, color1, color2) VALUES ('Opel Blitz', 195, 'masini/opel-blitz-5301880_1280.png', '- Made in 1969\n- 6 seats', 'F9D648', '356A03');
INSERT INTO cars(carname, carprice, carimg, cardetails, color1, color2) VALUES ('Packard One-Twenty', 180, 'masini/packard-one-twenty-4818885_640.png', '- Ony manufactured during\n the 1930\'s and 1940\'s \n- 5 seats', 'F8D717', 'EBB419');
INSERT INTO cars(carname, carprice, carimg, cardetails, color1, color2) VALUES ('Jeep All Terrain Vehicley', 190, 'masini/jeep-3874584_640.png', '- Made for the military\n  Isolated or convertible\n- 3 seats', 'B03D2A', 'FF6A59');
INSERT INTO cars(carname, carprice, carimg, cardetails, color1, color2) VALUES ('BMW 500 Motorcyle', 120, 'masini/bmw-500-4344066_640.png', '- Vintage motorcycle with isolated sidecar.\n- Made in 1965\n- For 2 persons only.', '004A53', '1BA8FE');

CREATE TABLE `car_db`.`orders` (
  `idOrders` INT NOT NULL AUTO_INCREMENT,
  `userId` INT NOT NULL,
  `carId` INT NOT NULL,
  `status` VARCHAR(45) NOT NULL,
  `dateFrom` VARCHAR(45) NOT NULL,
  `dateTo` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idOrders`),
  UNIQUE INDEX `idOrders_UNIQUE` (`idOrders` ASC) VISIBLE);
  
  CREATE TABLE `contact` (
  `idcontact` int NOT NULL AUTO_INCREMENT,
  `fullname` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `message` text NOT NULL,
  PRIMARY KEY (`idcontact`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
