
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';


-- -----------------------------------------------------
-- Schema good_couriers
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `good_couriers` DEFAULT CHARACTER SET utf8 ;
USE `good_couriers` ;

-- -----------------------------------------------------
-- Table `good_couriers`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `good_couriers`.`user` (
  `userId` INT(11) NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NOT NULL,
  `role` ENUM('admin', 'courier', 'customer') NOT NULL,
  `hashPassword` VARCHAR(255) NOT NULL DEFAULT '1',
  PRIMARY KEY (`userId`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `good_couriers`.`customerorder`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `good_couriers`.`customerorder` (
  `orderId` INT(11) NOT NULL AUTO_INCREMENT,
  `from` VARCHAR(255) NOT NULL,
  `to` VARCHAR(255) NOT NULL,
  `introductionDate` DATE NOT NULL,
  `status` ENUM('posted', 'delivered', 'completed', 'denied') NOT NULL,
  `goodsDescription` VARCHAR(255) NOT NULL,
  `price` INT(11) NOT NULL DEFAULT '0',
  `customerId` INT(11) NOT NULL,
  PRIMARY KEY (`orderId`),
  INDEX `fk_customerorder_user1_idx` (`customerId` ASC) VISIBLE,
  CONSTRAINT `fk_customerorder_user1`
    FOREIGN KEY (`customerId`)
    REFERENCES `good_couriers`.`user` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `good_couriers`.`courier_has_customerorder`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `good_couriers`.`courier_has_customerorder` (
  `user_has_CustomerId` INT(11) NOT NULL AUTO_INCREMENT,
  `courierId` INT(11) NOT NULL,
  `orderId` INT(11) NOT NULL,
  PRIMARY KEY (`user_has_CustomerId`),
  INDEX `fk_User_has_CustomerOrder_CustomerOrder1_idx` (`orderId` ASC) VISIBLE,
  INDEX `fk_courier_has_customerorder_user1_idx` (`courierId` ASC) VISIBLE,
  CONSTRAINT `fk_User_has_CustomerOrder_CustomerOrder1`
    FOREIGN KEY (`orderId`)
    REFERENCES `good_couriers`.`customerorder` (`orderId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_courier_has_customerorder_user1`
    FOREIGN KEY (`courierId`)
    REFERENCES `good_couriers`.`user` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `good_couriers`.`courierrecord`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `good_couriers`.`courierrecord` (
  `courierRecordId` INT(11) NOT NULL AUTO_INCREMENT,
  `userId` INT(11) NOT NULL,
  `markQuality` DOUBLE NULL DEFAULT NULL,
  `markPoliteness` DOUBLE NULL DEFAULT NULL,
  `markPunctuality` DOUBLE NULL DEFAULT NULL,
  `markCommon` DOUBLE NULL DEFAULT NULL,
  `status` TINYINT(1) NOT NULL DEFAULT '0',
  `votesNumber` VARCHAR(45) NOT NULL DEFAULT '0',
  PRIMARY KEY (`courierRecordId`),
  INDEX `fk_CourierRecord_User1_idx` (`userId` ASC) VISIBLE,
  CONSTRAINT `fk_CourierRecord_User1`
    FOREIGN KEY (`userId`)
    REFERENCES `good_couriers`.`user` (`userId`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `good_couriers`.`goods`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `good_couriers`.`goods` (
  `goodsId` INT(11) NOT NULL AUTO_INCREMENT,
  `typeGoods` ENUM('food', 'tech', 'furniture', 'easy-to-beat', 'another') NOT NULL,
  `courierRecordId` INT(11) NOT NULL,
  PRIMARY KEY (`goodsId`),
  INDEX `fk_Goods_CourierRecord1_idx` (`courierRecordId` ASC) VISIBLE,
  CONSTRAINT `fk_Goods_CourierRecord1`
    FOREIGN KEY (`courierRecordId`)
    REFERENCES `good_couriers`.`courierrecord` (`courierRecordId`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `good_couriers`.`transport`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `good_couriers`.`transport` (
  `transportId` INT(11) NOT NULL AUTO_INCREMENT,
  `typeTransport` ENUM('cycle', 'car', 'motorcycle', 'truck') NOT NULL,
  `courierRecordId` INT(11) NOT NULL,
  PRIMARY KEY (`transportId`),
  INDEX `fk_Transport_CourierRecord1_idx` (`courierRecordId` ASC) VISIBLE,
  CONSTRAINT `fk_Transport_CourierRecord1`
    FOREIGN KEY (`courierRecordId`)
    REFERENCES `good_couriers`.`courierrecord` (`courierRecordId`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
