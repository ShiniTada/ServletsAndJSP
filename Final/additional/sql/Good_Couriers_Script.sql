-- MySQL Script generated by MySQL Workbench
-- Fri Apr 19 10:51:07 2019
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema Good_Couriers
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema Good_Couriers
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Good_Couriers` DEFAULT CHARACTER SET utf8 ;
USE `Good_Couriers` ;

-- -----------------------------------------------------
-- Table `Good_Couriers`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Good_Couriers`.`User` (
  `userId` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(128) NOT NULL,
  `login` VARCHAR(45) NOT NULL,
  `role` ENUM('admin', 'courier', 'customer') NOT NULL,
  `hashPassword` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`userId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Good_Couriers`.`CourierRecord`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Good_Couriers`.`CourierRecord` (
  `courierRecordId` INT NOT NULL AUTO_INCREMENT,
  `userId` INT NOT NULL,
  `markCommon` DOUBLE NULL DEFAULT NULL,
  PRIMARY KEY (`courierRecordId`),
  INDEX `fk_CourierRecord_User1_idx` (`userId` ASC) VISIBLE,
  CONSTRAINT `fk_CourierRecord_User1`
    FOREIGN KEY (`userId`)
    REFERENCES `Good_Couriers`.`User` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Good_Couriers`.`CustomerOrder`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Good_Couriers`.`CustomerOrder` (
  `orderId` INT NOT NULL AUTO_INCREMENT,
  `from` VARCHAR(255) NOT NULL,
  `to` VARCHAR(255) NOT NULL,
  `introductionDate` DATE NOT NULL,
  `status` ENUM('posted', 'completed') NOT NULL,
  `goodsDescription` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`orderId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Good_Couriers`.`Goods`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Good_Couriers`.`Goods` (
  `goodsId` INT NOT NULL AUTO_INCREMENT,
  `typeGoods` ENUM('food', 'tech', 'furniture', 'easy-to-beat', 'another') NOT NULL,
  `courierRecordId` INT NULL,
  `orderId` INT NULL,
  PRIMARY KEY (`goodsId`),
  INDEX `fk_Goods_CourierRecord1_idx` (`courierRecordId` ASC) VISIBLE,
  INDEX `fk_Goods_CustomerOrder1_idx` (`orderId` ASC) VISIBLE,
  CONSTRAINT `fk_Goods_CourierRecord1`
    FOREIGN KEY (`courierRecordId`)
    REFERENCES `Good_Couriers`.`CourierRecord` (`courierRecordId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Goods_CustomerOrder1`
    FOREIGN KEY (`orderId`)
    REFERENCES `Good_Couriers`.`CustomerOrder` (`orderId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Good_Couriers`.`Transport`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Good_Couriers`.`Transport` (
  `transportId` INT NOT NULL AUTO_INCREMENT,
  `typeTransport` ENUM('cycle', 'car', 'motorcycle', 'truck') NOT NULL,
  `courierRecordId` INT NULL,
  `orderId` INT NULL,
  PRIMARY KEY (`transportId`),
  INDEX `fk_Transport_CourierRecord1_idx` (`courierRecordId` ASC) VISIBLE,
  INDEX `fk_Transport_CustomerOrder1_idx` (`orderId` ASC) VISIBLE,
  CONSTRAINT `fk_Transport_CourierRecord1`
    FOREIGN KEY (`courierRecordId`)
    REFERENCES `Good_Couriers`.`CourierRecord` (`courierRecordId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Transport_CustomerOrder1`
    FOREIGN KEY (`orderId`)
    REFERENCES `Good_Couriers`.`CustomerOrder` (`orderId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Good_Couriers`.`User_has_CustomerOrder`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Good_Couriers`.`User_has_CustomerOrder` (
  `user_has_CustomerId` INT NOT NULL AUTO_INCREMENT,
  `userId` INT NOT NULL,
  `orderId` INT NOT NULL,
  INDEX `fk_User_has_CustomerOrder_CustomerOrder1_idx` (`orderId` ASC) VISIBLE,
  INDEX `fk_User_has_CustomerOrder_User1_idx` (`userId` ASC) VISIBLE,
  PRIMARY KEY (`user_has_CustomerId`),
  CONSTRAINT `fk_User_has_CustomerOrder_User1`
    FOREIGN KEY (`userId`)
    REFERENCES `Good_Couriers`.`User` (`userId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_User_has_CustomerOrder_CustomerOrder1`
    FOREIGN KEY (`orderId`)
    REFERENCES `Good_Couriers`.`CustomerOrder` (`orderId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
