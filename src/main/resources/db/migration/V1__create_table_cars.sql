CREATE TABLE cars (
          id BIGINT AUTO_INCREMENT PRIMARY KEY,
          brand VARCHAR(255) NOT NULL,
          model VARCHAR(255) NOT NULL,
          car_year INT NOT NULL,
          color VARCHAR(255) NOT NULL,
          odometer INT NOT NULL,
          plate VARCHAR(8) NOT NULL UNIQUE
);
