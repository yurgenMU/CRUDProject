CREATE DATABASE IF NOT EXISTS vityaAK;

USE vityaAK;

CREATE TABLE IF NOT EXISTS vityaAK.user (
  `id`          INT         NOT NULL AUTO_INCREMENT,
  `FirstName`   VARCHAR(45) NOT NULL,
  `LastName`    VARCHAR(45) NOT NULL,
  `Speciality`  VARCHAR(45),
  `Email`       VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`)
  COMMENT ''
);
