-- Name, Type, Weight sourced from https://www.gemdat.org/
-- Generated from a CSV file using https://www.convertcsv.com/csv-to-sql.htm
DROP DATABASE IF EXISTS OOP_CA5;
CREATE DATABASE OOP_CA5_ABK;

USE OOP_CA5_ABK;
DROP TABLE IF EXISTS Gemstones;
CREATE TABLE Gemstones(
      ID      INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
      Name    VARCHAR(15) NOT NULL,
      Type    VARCHAR(15) NOT NULL,
      Weight  DOUBLE(5,2) NOT NULL,
      Clarity DOUBLE(3,2) NOT NULL,
      Price   DOUBLE(5,2) NOT NULL,
      Stock   INTEGER  NOT NULL,
      Colour  VARCHAR(15) NOT NULL
);
CREATE TABLE Users(
    UID INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    Forename VARCHAR(20) NOT NULL,
    LastName VARCHAR(20),
    Username VARCHAR(25) UNIQUE NOT NULL,
    Password VARCHAR(25) NOT NULL,
    IsAdmin INTEGER NOT NULL
);



INSERT INTO Gemstones(Name,Type,Weight,Clarity,Price,Stock,Colour)
VALUES ('Amythest','Quartz',13.21,0.3,5,10,'Purple');
INSERT INTO Gemstones(Name,Type,Weight,Clarity,Price,Stock,Colour)
VALUES ('Beryl','Aquamarine',7.34,0.5,4,8,'Blue');
INSERT INTO Gemstones(Name,Type,Weight,Clarity,Price,Stock,Colour)
VALUES ('Sapphire','Corundum',5.57,0.2,3,14,'Blue');
INSERT INTO Gemstones(Name,Type,Weight,Clarity,Price,Stock,Colour)
VALUES ('Agate','Chalcedony',22.94,0.4,4,19,'Red');
INSERT INTO Gemstones(Name,Type,Weight,Clarity,Price,Stock,Colour)
VALUES ('Fluorite','Blue John',35.22,0.7,8,17,'White');
INSERT INTO Gemstones(Name,Type,Weight,Clarity,Price,Stock,Colour)
VALUES ('Tourmaline','Rubellite',8.51,0.3,7,3,'Pink');
INSERT INTO Gemstones(Name,Type,Weight,Clarity,Price,Stock,Colour)
VALUES ('Apatite','Moroxite',4.39,0.4,9,4,'Green');
INSERT INTO Gemstones(Name,Type,Weight,Clarity,Price,Stock,Colour)
VALUES ('Malaia Garnet','Garnet Group',3.7,0.1,6,7,'Red');
INSERT INTO Gemstones(Name,Type,Weight,Clarity,Price,Stock,Colour)
VALUES ('Star Saphire','Corundum',16.17,0.8,4,13,'Purple');
INSERT INTO Gemstones(Name,Type,Weight,Clarity,Price,Stock,Colour)
VALUES ('Tourmaline','Verdelite',8.51,0.4,5,10,'Green');


INSERT INTO Users(Forename, LastName, Username, Password, IsAdmin)
VALUES ('Anastasia', 'McCormac', 'McCormacA', 'abcd1234', 1);
INSERT INTO Users(Forename, LastName, Username, Password, IsAdmin)
VALUES ('Ben', 'McKeever', 'McKeeverB', 'abcd1234', 1);
INSERT INTO Users(Forename, LastName, Username, Password, IsAdmin)
VALUES ('Kaylon', 'Riordan', 'RiordanK', 'abcd1234', 1);
INSERT INTO Users(Forename, LastName, Username, Password, IsAdmin)
VALUES ('Cane', 'Racheal', 'CaneR', 'abcd1234', 0);
INSERT INTO Users(Forename, LastName, Username, Password, IsAdmin)
VALUES ('Connor', 'Kelly-Anne', 'ConnorK', 'abcd1234', 0);