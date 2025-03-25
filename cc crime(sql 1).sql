create database crimeManagement;
use crimeManagement;

CREATE TABLE Crime (
 CrimeID INT PRIMARY KEY,
 IncidentType VARCHAR(255),
 IncidentDate DATE,
 Location VARCHAR(255),
 Description TEXT,
 Status VARCHAR(20)
);
desc crime;
select * from crime;

CREATE TABLE Victim (
 VictimID INT PRIMARY KEY,
 CrimeID INT,
 Name VARCHAR(255),
 ContactInfo VARCHAR(255),
 Injuries VARCHAR(255),
 FOREIGN KEY (CrimeID) REFERENCES Crime(CrimeID)
);
select * from victim;

CREATE TABLE Suspect (
 SuspectID INT PRIMARY KEY,
 CrimeID INT,
 Name VARCHAR(255),
 Description TEXT,
 CriminalHistory TEXT,
 FOREIGN KEY (CrimeID) REFERENCES Crime(CrimeID)
);
select * from suspect;

INSERT INTO Crime (CrimeID, IncidentType, IncidentDate, Location, Description, Status)
VALUES
 (1, 'Robbery', '2023-09-15', '123 Main St, Cityville', 'Armed robbery at a convenience store', 'Open'),
 (2, 'Homicide', '2023-09-20', '456 Elm St, Townsville', 'Investigation into a murder case', 'Under Investigation'),
 (3, 'Theft', '2023-09-10', '789 Oak St, Villagetown', 'Shoplifting incident at a mall', 'Closed'),
 (4, 'Assault', '2023-10-05', '321 Maple St, Metrocity', 'Physical assault reported in a park', 'Open'),
 (5, 'Burglary', '2023-10-12', '654 Pine St, Smalltown', 'House break-in reported', 'Under Investigation'),
 (6, 'Fraud', '2023-10-18', '987 Birch St, Bigcity', 'Online banking fraud case', 'Closed');
select * from crime;

INSERT INTO Victim (VictimID, CrimeID, Name, ContactInfo, Injuries)
VALUES
 (1, 1, 'John Doe', 'johndoe@example.com', 'Minor injuries'),
 (2, 2, 'Jane Smith', 'janesmith@example.com', 'Deceased'),
 (3, 2, 'Robert Brown', 'robertbrown@example.com', 'Gunshot wounds'),
 (4, 3, 'Alice Johnson', 'alicejohnson@example.com', 'None'),  
 (5, 4, 'Michael Brown', 'michaelbrown@example.com', 'Bruises and cuts'),
 (6, 4, 'Emma Wilson', 'emmawilson@example.com', 'Broken arm'),
 (7, 5, 'Emily White', 'emilywhite@example.com', 'Shock and trauma'),
 (8, 6, 'David Green', 'davidgreen@example.com', 'Financial loss');
 select * from victim;

INSERT INTO Suspect (SuspectID, CrimeID, Name, Description, CriminalHistory)
VALUES
 (1, 1, 'Robber 1', 'Armed and masked robber', 'Previous robbery convictions'),
 (2, 2, 'Unknown', 'Investigation ongoing', NULL),
 (3, 3, 'Suspect 1', 'Shoplifting suspect', 'Prior shoplifting arrests'),
 (4, 4, 'Attacker 1', 'Wearing a red hoodie', 'History of violence'),
 (5, 5, 'Burglar 2', 'Broke in through a window', 'Prior burglary charges'),
 (6, 6, 'Scammer X', 'Conducted online scams', 'Previous fraud convictions');
select * from suspect;