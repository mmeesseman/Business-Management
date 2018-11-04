
CREATE SCHEMA IF NOT EXISTS `mma` DEFAULT CHARACTER SET utf8 ;
USE `mma` ;

-- -----------------------------------------------------
-- Table `mma`.`customers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mma`.`customers` (
  `firstName` VARCHAR(30) NULL DEFAULT NULL,
  `lastName` VARCHAR(30) NULL DEFAULT NULL,
  `email` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`email`))