start transaction;
CREATE DATABASE  IF NOT EXISTS `Acme-SixPack` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `Acme-SixPack`;


create user 'acme-user'@'%' identified by password '*4F10007AADA9EE3DBB2CC36575DFC6F4FDE27577';
create user 'acme-manager'@'%' identified by password '*FDB8CD304EB2317D10C95D797A4BD7492560F55F';


grant select, insert, update, delete on `Acme-SixPack`.* to 'acme-user'@'%';
grant select, insert, update, delete, create, drop, references, index, alter, create temporary tables, lock tables, create view, create routine, alter routine, execute, trigger, show view on `Acme-SixPack`.* to 'acme-manager'@'%';