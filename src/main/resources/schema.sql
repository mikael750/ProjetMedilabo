-- Create database
CREATE DATABASE IF NOT EXISTS medilabo;

-- Use database
USE medilabo;

-- Dropping all tables first
DROP TABLE IF EXISTS `patient`;

-- Creating table patient
CREATE TABLE `patient` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `prenom` VARCHAR(255) DEFAULT NULL,
  `nom` VARCHAR(255) DEFAULT NULL,
  `date_naissance` DATE DEFAULT NULL,
  `genre` enum('M','F') DEFAULT NULL,
  `adresse_postale` VARCHAR(255) DEFAULT NULL,
  `numero_telephone` VARCHAR(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
