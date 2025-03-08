-- MySQL commands
-- mysql -u ApexCalvin -p : Login
-- enter ZCW pw
-- SHOW DATABASES : Shows all DB in acc
-- create DB, and USE (change into it)
-- SHOW TABLES
-- describe "" : Looks at table set up
-- SELECT * FROM Students : Looks at value

-- exercise 1 : creating databases
-- CREATE DATABASE zipcode;
CREATE SCHEMA zipcode;

-- exercise 2 : deleting databases
-- CREATE DATABASE zipcode;
DROP SCHEMA zipcode;

-- exercise 3 : creating tables
USE myNewDB;
CREATE TABLE Users (
    userID INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
    lastname VARCHAR(255) NOT NULL,
    firstname VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    city VARCHAR(255) NOT NULL
);

-- exercise 4 : deleting tables
DROP TABLE Users;

-- exercise 5 : deleting tables (TRUNCATE: deletes all data inside)
TRUNCATE TABLE Users;

-- exercise 6 : altering tables (add column)
-- ALTER TABLE users ADD COLUMN birthday DATE;
ALTER TABLE Users ADD birthday DATE;

-- exercise 7 : altering tables (remove column)
-- ALTER TABLE users DROP COLUMN birthday
ALTER TABLE Users DROP birthday

-- exercise 8 : Inserting records
CREATE TABLE Students (
    id INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
    studentName VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    city VARCHAR(255) NOT NULL,
    postalCode VARCHAR(255),
    country VARCHAR(255) NOT NULL
);

INSERT INTO Students (studentName, address, city, postalCode, country)
VALUES('Jane Doe', '57 Union St', 'cGlasgow', 'G13RB', 'Scotland')
,('Calvin Tran', '57 Union St', 'Aarlington', 'G13RB', 'Scotland')
,'Nina Chen', '57 Union St', 'Philadelphia', 'G13RB', 'Scotland')
,('Collin Cleveland', '57 Union St', 'erenton', 'G13RB', 'Scotland')
,('Anna Fu', '57 Union St', 'sEdinburgh', 'G13RB', 'Japan')
,('Freddy Mintarja', '57 Union St', 'ladinburgh', 'Null', 'Japan')
,('Carolina D', '57 Union St', 'Haiti', '', 'Japan');

INSERT INTO Students (studentName, address, city, postalCode, country)
VALUES('Andrew Ascone', '123 Fake St', 'Springfield', '908', 'America')

INSERT INTO Students (studentName, address, city, country)
VALUE('Yingjie', '57 Union St', 'Sint Maarten', 'Japan');

-- exercise 9 : Selecting Records (WHERE)
--- select WILDCARD/ELEMENT from Students table where City value is not PHL
SELECT * FROM Students WHERE City!='Philadelphia';

-- exercise 10 : Selecting Records (WHERE, OR)
SELECT * FROM Students WHERE City='Philadelphia' OR 'Trenton';

-- exercise 11 : Order by ASC
SELECT City FROM Students ORDER BY City ASC;
-- ^ returns only City block, v Returns entire table ordered
SELECT * FROM Students ORDER BY City

-- exercise 12 : Order by DESC
SELECT * FROM Students ORDER BY City DESC;

-- exercise 13 : Order by multiple columns
SELECT * FROM Students ORDER BY Country, City;

-- exercise 14 : Null values : Check for Null;
-- can also check for IS NOT NULL;
SELECT * FROM Students WHERE PostalCode IS NULL;
--Change Table, give postalCode Type Null;

-- exercise 15 : Null values : Check for Empty
SELECT * FROM Students WHERE postalCode='';

-- exercise 16 : Updating records (Set all column to something)
UPDATE Students SET City='Edinburgh';

-- exercise 17 : Updating records (Set something under specific condition-Multiple)
UPDATE Students SET City='Edinburgh' WHERE Country='Scotland';

-- exercise 18 : Updating records (Set something under specific condition-Single)
UPDATE Students SET City='Edinburgh' WHERE id=35;

-- exercise 19 : deleting records (specific records)
DELETE FROM Students WHERE Country='Scotland';

-- exercise 20 : deleting records (delete all values in table)
TRUNCATE TABLE Students;

--

SELECT * FROM Students WHERE city LIKE 'a%' OR city LIKE 'c%' OR city LIKE 's%';

-- exercise 3 : LIKE (WILDCARDS)
SELECT * FROM Students WHERE city BETWEEN 'a%' AND 'f%';

-- exercise 4 : LIKE (WILDCARDS)
SELECT * FROM Students WHERE city NOT LIKE 'a%' AND city NOT LIKE 'c%' AND city NOT LIKE 'f%';

-- exercise 5 : IN
SELECT * FROM Students WHERE Country IN ('Sint Maarten', 'HaitiDELD');

-- exercise 6 : NOT IN
SELECT * FROM Students WHERE Country NOT IN ('Sint Maarten', 'Haiti');

-- exercise 7: BETWEEN
SELECT * FROM Courses WHERE CreditHours BETWEEN 10 AND 20;

-- exercise 8 : NOT BETWEEN
SELECT * FROM Courses WHERE CreditHours NOT BETWEEN 10 AND 20;

-- exercise 9 : BETWEEN ALPHABETICALLY
SELECT * FROM Courses WHERE CourseName BETWEEN 'ColdFusion' AND 'PYTHON' ORDER BY CourseName;

-- exercise 10 : ALIASES (Giving AS to Column)
SELECT postalCode AS Zip FROM Students;

-- exercise 11 : ALIASES (Giving AS to table)
SELECT * FROM Students AS Learners;

--

CREATE TABLE Enrollment (
    id INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
    studentName VARCHAR(255) NOT NULL,
    class VARCHAR(255) NOT NULL
);

INSERT INTO Enrollment (studentName, class)
VALUES( 'Jane Doe',                 'English')
,(      'Calvin Tran',              'Math')
(       'Nina Chen',                'History');
,(      'Collin Cleveland',         'Biology')
,(      'Anna Fu',                  'Biology')
,(      'Freddy Mintarja',          'Math')
,(      'Carolina Diazgranados',    'Math')
, (     'Andrew Ascone',            'History')
, (     'Yingjie',                  'English');

-- Exercise 1: Join
SELECT * FROM Enrollment LEFT JOIN Students ON Enrollment.id = Students.id;

-- Exercise 2: Join
SELECT * FROM Enrollment INNER JOIN Students ON Enrollment.id = Students.id;
SELECT * FROM Enrollment JOIN Students ON Enrollment.id = Students.id;

-- Exercise 3: Join
SELECT * FROM ENROLLMENT RIGHT JOIN Students ON Enrollment.id = Students.id;

-- Exercise 4: Grouping
-- NEEDS GROUP BY
SELECT COUNT(id), country FROM Students GROUP BY country;

-- Exercise 5: Join
SELECT COUNT(id), country FROM Students GROUP BY country
ORDER BY COUNT(id) DESC;

-- Exercise 6: Join
SELECT COUNT(id), country FROM Students GROUP BY country
HAVING COUNT(id) > 10
ORDER BY COUNT(id) DESC;