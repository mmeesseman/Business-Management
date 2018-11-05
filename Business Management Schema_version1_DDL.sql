-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema business_management_schema
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema business_management_schema
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `business_management_schema` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `business_management_schema` ;

-- -----------------------------------------------------
-- Table `business_management_schema`.`contact_info`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `business_management_schema`.`contact_info` (
  `contact_info_id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `phone_number` VARCHAR(45) NOT NULL COMMENT '',
  `cell_phone_number` VARCHAR(45) NOT NULL COMMENT '',
  `email_address` VARCHAR(45) NOT NULL COMMENT '',
  PRIMARY KEY (`contact_info_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `business_management_schema`.`Address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `business_management_schema`.`Address` (
  `address_id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `street_address` VARCHAR(45) NOT NULL COMMENT '',
  `city` VARCHAR(45) NOT NULL COMMENT '',
  `state` VARCHAR(45) NOT NULL COMMENT '',
  `zip_code` VARCHAR(45) NOT NULL COMMENT '',
  `unit_number` VARCHAR(45) NOT NULL COMMENT '',
  PRIMARY KEY (`address_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `business_management_schema`.`customer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `business_management_schema`.`customer` (
  `customer_id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `last_name` VARCHAR(45) NOT NULL COMMENT '',
  `first_name` VARCHAR(45) NOT NULL COMMENT '',
  `contact_info_contact_info_id` INT NOT NULL COMMENT '',
  `Address_address_id` INT NOT NULL COMMENT '',
  PRIMARY KEY (`customer_id`),
  INDEX `fk_customer_contact_info1_idx` (`contact_info_contact_info_id` ASC),
  INDEX `fk_customer_Address1_idx` (`Address_address_id` ASC),
  CONSTRAINT `fk_customer_contact_info1`
    FOREIGN KEY (`contact_info_contact_info_id`)
    REFERENCES `business_management_schema`.`contact_info` (`contact_info_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_customer_Address1`
    FOREIGN KEY (`Address_address_id`)
    REFERENCES `business_management_schema`.`Address` (`address_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `business_management_schema`.`company`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `business_management_schema`.`company` (
  `company_id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `Address_address_id` INT NOT NULL COMMENT '',
  `contact_info_contact_info_id` INT NOT NULL COMMENT '',
  `company_name` VARCHAR(45) NOT NULL COMMENT '',
  PRIMARY KEY (`company_id`),
  INDEX `fk_company_Address1_idx` (`Address_address_id` ASC),
  INDEX `fk_company_contact_info1_idx` (`contact_info_contact_info_id` ASC),
  CONSTRAINT `fk_company_Address1`
    FOREIGN KEY (`Address_address_id`)
    REFERENCES `business_management_schema`.`Address` (`address_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_company_contact_info1`
    FOREIGN KEY (`contact_info_contact_info_id`)
    REFERENCES `business_management_schema`.`contact_info` (`contact_info_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `business_management_schema`.`employee`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `business_management_schema`.`employee` (
  `employee_id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `last_name` VARCHAR(45) NOT NULL COMMENT '',
  `first_name` VARCHAR(45) NOT NULL COMMENT '',
  `contact_info_contact_info_id` INT NOT NULL COMMENT '',
  `Address_address_id` INT NOT NULL COMMENT '',
  PRIMARY KEY (`employee_id`),
  INDEX `fk_employee_contact_info1_idx` (`contact_info_contact_info_id` ASC),
  INDEX `fk_employee_Address1_idx` (`Address_address_id` ASC),
  CONSTRAINT `fk_employee_contact_info1`
    FOREIGN KEY (`contact_info_contact_info_id`)
    REFERENCES `business_management_schema`.`contact_info` (`contact_info_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_employee_Address1`
    FOREIGN KEY (`Address_address_id`)
    REFERENCES `business_management_schema`.`Address` (`address_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `business_management_schema`.`invoice`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `business_management_schema`.`invoice` (
  `invoice_number` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `date` DATE NOT NULL COMMENT '',
  `time` VARCHAR(45) NOT NULL COMMENT '',
  `customer_customer_id` INT NOT NULL COMMENT '',
  `employee_employee_id` INT NOT NULL COMMENT '',
  `notes` VARCHAR(255) COMMENT '',
  `comments` VARCHAR(255) COMMENT '',
  PRIMARY KEY (`invoice_number`),
  INDEX `fk_invoice_customer1_idx` (`customer_customer_id` ASC),
  INDEX `fk_invoice_employee1_idx` (`employee_employee_id` ASC),
  CONSTRAINT `fk_invoice_customer1`
    FOREIGN KEY (`customer_customer_id`)
    REFERENCES `business_management_schema`.`customer` (`customer_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_invoice_employee1`
    FOREIGN KEY (`employee_employee_id`)
    REFERENCES `business_management_schema`.`employee` (`employee_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `business_management_schema`.`invoice_line_item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `business_management_schema`.`invoice_line_item` (
  `invoice_line_number` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `invoice_invoice_number` INT NOT NULL COMMENT '',
  `quantity_purchased` VARCHAR(45) NOT NULL COMMENT '',
  `service_item` VARCHAR(100) NOT NULL COMMENT '',
  PRIMARY KEY (`invoice_line_number`),
  INDEX `fk_invoice_line_item_invoice1_idx` (`invoice_invoice_number` ASC),
  CONSTRAINT `fk_invoice_line_item_invoice1`
    FOREIGN KEY (`invoice_invoice_number`)
    REFERENCES `business_management_schema`.`invoice` (`invoice_number`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `business_management_schema`.`login`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `login` (
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
