-- MySQL Workbench Synchronization
-- Generated: 2023-05-04 15:11
-- Model: New Model
-- Version: 1.0
-- Project: Name of the project
-- Author: robert

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

DROP DATABASE IF EXISTS mydbProg;

CREATE SCHEMA IF NOT EXISTS `mydbProg` DEFAULT CHARACTER SET utf8 ;
use mydbProg;
CREATE TABLE IF NOT EXISTS `mydbProg`.`comunitats_autonomes` (
  `comunitat_aut_id` TINYINT(3) UNSIGNED NOT NULL AUTO_INCREMENT,
  `nom` VARCHAR(45) NULL DEFAULT NULL,
  `codi_ine` CHAR(2) NOT NULL,
  PRIMARY KEY (`comunitat_aut_id`),
  UNIQUE INDEX `uk_com_aut_codi_ine` (`codi_ine` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `mydbProg`.`provincies` (
  `provincia_id` TINYINT(3) UNSIGNED NOT NULL AUTO_INCREMENT,
  `comunitat_aut_id` TINYINT(3) UNSIGNED NOT NULL,
  `nom` VARCHAR(45) NULL DEFAULT NULL,
  `codi_ine` CHAR(2) NOT NULL,
  `num_escons` TINYINT(3) UNSIGNED NULL DEFAULT NULL COMMENT 'Numero d\'escons que li pertoquen a aquella provincia',
  PRIMARY KEY (`provincia_id`),
  UNIQUE INDEX `uk_provincies_codi_ine` (`codi_ine` ASC) VISIBLE,
  INDEX `idx_fk_provincies_comunitats_autonomes` (`comunitat_aut_id` ASC) VISIBLE,
  CONSTRAINT `fk_provincies_comunitats_autonomes`
    FOREIGN KEY (`comunitat_aut_id`)
    REFERENCES `mydbProg`.`comunitats_autonomes` (`comunitat_aut_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `mydbProg`.`municipis` (
  `municipi_id` SMALLINT(5) UNSIGNED NOT NULL AUTO_INCREMENT,
  `nom` VARCHAR(100) NULL DEFAULT NULL,
  `codi_ine` CHAR(3) NOT NULL,
  `provincia_id` TINYINT(3) UNSIGNED NOT NULL,
  `districte` CHAR(2) NULL DEFAULT NULL COMMENT 'Número de districte municipal , sinó el seu valor serà 99. Per exemple aquí municiís com Blanes el seu valor serà 99, però en ciutats com Barcelona hi haurà el número de districte',
  PRIMARY KEY (`municipi_id`),
  UNIQUE INDEX `uk_municipis_codi` (`codi_ine` ASC, `provincia_id` ASC, `districte` ASC) VISIBLE,
  INDEX `idx_fk_municipis_provincies1` (`provincia_id` ASC) VISIBLE,
  CONSTRAINT `fk_municipis_provincies`
    FOREIGN KEY (`provincia_id`)
    REFERENCES `mydbProg`.`provincies` (`provincia_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `mydbProg`.`candidatures` (
  `candidatura_id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `eleccio_id` TINYINT(3) UNSIGNED NOT NULL,
  `codi_candidatura` CHAR(6) NULL DEFAULT NULL,
  `nom_curt` VARCHAR(50) NULL DEFAULT NULL COMMENT 'Sigles de la candidatura',
  `nom_llarg` VARCHAR(150) NULL DEFAULT NULL COMMENT 'Nom llarg de la candidatura (denominació)',
  `codi_acumulacio_provincia` CHAR(6) NULL DEFAULT NULL COMMENT 'Codi de la candidatura d\'acumulació a nivell provincial.',
  `codi_acumulacio_ca` CHAR(6) NULL DEFAULT NULL COMMENT 'Codi de la candidatura d\'acumulació a nivell de comunitat autònoma',
  `codi_acumulario_nacional` CHAR(6) NULL DEFAULT NULL,
  PRIMARY KEY (`candidatura_id`),
  UNIQUE INDEX `uk_eleccions_partits` (`eleccio_id` ASC, `codi_candidatura` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `mydbProg`.`persones` (
  `persona_id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `nom` VARCHAR(30) NULL DEFAULT NULL,
  `cog1` VARCHAR(30) NULL DEFAULT NULL,
  `cog2` VARCHAR(30) NULL DEFAULT NULL,
  `sexe` ENUM('M', 'F') NULL DEFAULT NULL COMMENT 'M=Masculí, F=Femení',
  `data_naixement` DATE NULL DEFAULT NULL,
  `dni` CHAR(10)  NULL,
  PRIMARY KEY (`persona_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `mydbProg`.`candidats` (
  `candidat_id` BIGINT(19) UNSIGNED NOT NULL AUTO_INCREMENT,
  `candidatura_id` INT(10) UNSIGNED NOT NULL,
  `persona_id` INT(10) UNSIGNED NOT NULL,
  `provincia_id` TINYINT(3) UNSIGNED NOT NULL,
  `num_ordre` TINYINT(4) NULL DEFAULT NULL COMMENT 'Num ordre del candidatdins la llista del partit dins de la circumpscripció que es presenta.',
  `tipus` ENUM('T', 'S') NULL DEFAULT NULL COMMENT 'T=Titular, S=Suplent',
  PRIMARY KEY (`candidat_id`),
  INDEX `fk_candidats_provincies1_idx` (`provincia_id` ASC) VISIBLE,
  INDEX `fk_candidats_persones1_idx` (`persona_id` ASC) VISIBLE,
  INDEX `fk_candidats_candidatures1_idx` (`candidatura_id` ASC) VISIBLE,
  UNIQUE INDEX `uk_candidats_persona_cand` (`candidatura_id` ASC, `persona_id` ASC) VISIBLE,
  CONSTRAINT `fk_candidats_provincies1`
    FOREIGN KEY (`provincia_id`)
    REFERENCES `mydbProg`.`provincies` (`provincia_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_candidats_persones1`
    FOREIGN KEY (`persona_id`)
    REFERENCES `mydbProg`.`persones` (`persona_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_candidats_candidatures1`
    FOREIGN KEY (`candidatura_id`)
    REFERENCES `mydbProg`.`candidatures` (`candidatura_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
