CREATE TABLE IF NOT EXISTS bin_features(
ID smallint not null auto_increment,
NAME varchar (10) not null,
WIDTH smallint not null,
LENGTH smallint not null,
HEIGHT smallint not null,
PRICE double,
AMOUNT bigint not null, 
PRIMARY KEY (ID)
);

insert into bin_features values
(1,'AB1234', 594, 396, 280, 9.37, 200),
(2,'AB2345', 1240, 835, 966, 91.69, 200),
(3,'AB3456', 1240, 835, 966, 116.93, 200),
(4,'AB4567', 3020, 1300, 970, 381.17, 200),
(5,'AB5678', 3600, 1200, 970, 427.53, 200),
(6,'AB6789', 500, 1200, 245, 40.18, 200),
(7,'AB7890', 926, 866, 600, 81.39, 200),
(8,'BC1234', 926, 866, 880, 99.93, 200),
(9,'BC2345', 800, 700, 770, 61.30, 200),
(10,'BC3456', 926, 866, 300, 47.39, 200),
(11,'BC4567', 800, 800, 950, 97.87, 200),
(12,'BC5678', 926, 866, 880, 139.08, 200),
(13,'BC6789', 926, 866, 880, 139.08, 200),
(14,'BC7890', 926, 866, 600, 123.62, 200),
(15,'CD1234', 926, 866, 880, 139.08, 200),
(16,'CD2345', 926, 866, 880, 139.08, 200),
(17,'CD3456', 926, 866, 880, 139.08, 200),
(18,'CD4567', 926, 866, 880, 139.08, 200),
(19,'CD5678', 926, 866, 1040, 149.38, 200),
(20,'CD6789', 926, 866, 880, 0.00, 200),
(21,'CD7890', 926, 866, 1040, 149.38, 200),
(22,'DE1234', 926, 866, 880, 139.08, 200),
(23,'DE2345', 926, 866, 1040, 149.38, 200),
(24,'DE3456', 926, 866, 1040, 149.38, 200),
(25,'DE4567', 926, 866, 880, 139.08, 200),
(26,'DE5678', 926, 866, 880, 139.08, 200),
(27,'DE6789', 926, 866, 600, 123.62, 200),
(28,'DE7890', 926, 866, 880, 139.08, 200),
(29,'EF1234', 926, 866, 1040, 149.38, 200),
(30,'EF2345', 926, 866, 880, 149.38, 200),
(31,'EF3456', 926, 866, 880, 139.08, 200),
(32,'EF4567', 926, 866, 874, 139.08, 200),
(33,'EF5678', 926, 866, 880, 139.08, 200),
(34,'EF6789', 926, 866, 880, 139.08, 200),
(35,'EF7890', 926, 866, 874, 139.08, 200),
(36,'FG1234', 926, 866, 880, 0.00, 200),
(37,'FG2345', 1200, 1000, 1000, 0.00, 200),
(38,'FG3456', 1200, 1000, 1000, 236.95, 200),
(39,'FG4567', 1200, 1000, 1000, 236.95, 200);


alter table bin_features add VOLUME double;
alter table bin_features add LEASING_PRICE double;

SET SQL_SAFE_UPDATES = 0;
update bin_features set VOLUME = (WIDTH * LENGTH * HEIGHT)/1000000000;
update bin_features set LEASING_PRICE = (PRICE * AMOUNT);
SET SQL_SAFE_UPDATES = 1;

CREATE TABLE IF NOT EXISTS inbounds(
ID smallint not null auto_increment,
TYPE varchar (15) not null,
QUANTITY smallint  not null,
CHECKED_AT datetime not null,
LOCATION varchar(10) not null,
VOLUME_AMOUNT smallint  not null,
PRIMARY KEY (ID)
);

insert into inbounds values
(1,'HOMOGENEOUS', 6,'2009-11-10 16:00:00', 'MLADA', 3000),
(2,'MIX', 8,'2010-11-10 16:00:00','KASSEL', 3500),
(3,'WOODEN',17,'2012-11-10 16:00:00', 'OTHER', 4000),
(4,'MIX',9,'2017-11-10 18:00:00', 'KASSEL', 4500),
(5,'HOMOGENEOUS',21,'2012-05-30 20:00:00', 'OTHER', 5000);

CREATE TABLE IF NOT EXISTS bin_feat_inbounds(
inbounds_ID smallint  not null,
bin_features_ID smallint  not null,
FOREIGN KEY (inbounds_ID) references inbounds(ID)
ON DELETE RESTRICT ON UPDATE CASCADE,
FOREIGN KEY (bin_features_ID) references bin_features(ID)
ON DELETE RESTRICT ON UPDATE CASCADE,
PRIMARY KEY(inbounds_ID, bin_features_ID)
);

insert into bin_feat_inbounds
values(1,1),
(2,2),
(3,3),
(4,4),
(4,3),
(5,2),
(3,1),
(5,5),
(4,5),
(5,4);

CREATE TABLE IF NOT EXISTS portal_user (
  PU_ID INT NOT NULL AUTO_INCREMENT,
  PU_LOGIN VARCHAR(45) NULL DEFAULT NULL,
  PU_FIRSTNAME VARCHAR(45) NOT NULL,
  PU_LASTNAME VARCHAR(45) NOT NULL,
  PU_EMAIL VARCHAR(45) NOT NULL,
  PU_PASSWORD VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (PU_ID),
  UNIQUE INDEX PU_LOGIN_UNIQUE (PU_LOGIN ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS role (
  RO_ID INT NOT NULL AUTO_INCREMENT,
  RO_NAME VARCHAR(45) NOT NULL,
  PRIMARY KEY (RO_ID))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS portal_user_roles (
  PUR_PU_ID INT NOT NULL,
  PUR_RO_ID INT NOT NULL,
  INDEX PUR_PU_ID_idx (PUR_PU_ID ASC),
  INDEX PUR_RO_ID_idx (PUR_RO_ID ASC),
  CONSTRAINT PUR_PU_ID
    FOREIGN KEY (PUR_PU_ID)
    REFERENCES portal_user (PU_ID),
  CONSTRAINT PUR_RO_ID
    FOREIGN KEY (PUR_RO_ID)
    REFERENCES spring.role (RO_ID))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

INSERT INTO portal_user
values (null,'loki','Jan', 'Kowalski','jan.kow@test.pl','$2y$12$bIsJTV7Vh5Ftr3cmq7JB4OUIO61FKetlUYL9l7ubtHGYYp/VE6MsC');
INSERT INTO portal_user
values (null,'sroko1','Marcin','Sroka','marcin.sroka@test.pl','$2y$12$2DP5flAFpC6KMOG7Lfp53eSaXa5/EvJcOh7JXP6j4ErCgeGkXTZ1.');
insert into portal_user
values (null, 'czerwina','Lukasz','Red','czerwina.user@test.pl','$2y$12$ObwxsIeCHK.XTDQnJi64QOT1D29DAuqYyEGNQdrLH3epShldB1mV2 ' );
insert into portal_user
values (null, 'cygan','Stefan','Maro','stefan.jakistam@test.pl','$2y$12$ObwxsIeCHK.XTDQnJi64QOT1D29DAuqYyEGNQdrLH3epShldB1mV2 ');
insert into portal_user
values (null, 'shogun','Piter','Obama','obama.nazwisko@test.pl','$2y$12$ObwxsIeCHK.XTDQnJi64QOT1D29DAuqYyEGNQdrLH3epShldB1mV2 ');


INSERT INTO role
values (null,'ADMIN');
INSERT INTO role
values (null,'USER');
INSERT INTO portal_user_roles
values (4,3);
INSERT INTO portal_user_roles
values (3,4);
INSERT INTO portal_user_roles
values (4,4);
INSERT INTO portal_user_roles
values (5,4);
INSERT INTO portal_user_roles
values (6,4);
INSERT INTO portal_user_roles
values (7,4);

CREATE TABLE IF NOT EXISTS outbounds(
ID smallint not null auto_increment,
TYPE varchar (15) not null,
QUANTITY smallint  not null,
CHECKED_AT datetime not null,
LOCATION varchar(10) not null,
VOLUME_AMOUNT smallint  not null,
PRIMARY KEY (ID)
);

insert into outbounds values
(1,'HOMOGENEOUS', 6,'2009-11-10 16:00:00', 'MLADA', 3000),
(2,'MIX', 8,'2010-11-10 16:00:00','KASSEL', 3500),
(3,'WOODEN',17,'2012-11-10 16:00:00', 'OTHER', 4000),
(4,'MIX',9,'2017-11-10 18:00:00', 'KASSEL', 4500),
(5,'HOMOGENEOUS',21,'2012-05-30 20:00:00', 'OTHER', 5000);


CREATE TABLE IF NOT EXISTS bin_feat_outbounds(
outbounds_ID smallint  not null,
bin_features_ID smallint  not null,
FOREIGN KEY (outbounds_ID) references outbounds(ID)
ON DELETE RESTRICT ON UPDATE CASCADE,
FOREIGN KEY (bin_features_ID) references bin_features(ID)
ON DELETE RESTRICT ON UPDATE CASCADE,
PRIMARY KEY(bin_features_ID, outbounds_ID)
);

