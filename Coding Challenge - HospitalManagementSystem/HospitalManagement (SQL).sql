create database hospitalManagement;
use hospitalManagement;

CREATE TABLE Patient (
    patientId INT PRIMARY KEY,
    firstName VARCHAR(50),
    lastName VARCHAR(50),
    dateOfBirth DATE,
    gender VARCHAR(10),
    contactNumber VARCHAR(15),
    address VARCHAR(255)
);
desc patient;
select * from patient;

CREATE TABLE Doctor (
    doctorId INT PRIMARY KEY,
    firstName VARCHAR(50),
    lastName VARCHAR(50),
    specialization VARCHAR(100),
    contactNumber VARCHAR(15)
);
select * from doctor;

CREATE TABLE Appointment (
    appointmentId INT PRIMARY KEY,
    patientId INT,
    doctorId INT,
    appointmentDate DATE,
    description TEXT,
    FOREIGN KEY (patientId) REFERENCES Patient(patientId),
    FOREIGN KEY (doctorId) REFERENCES Doctor(doctorId)
);
select * from appointment;

INSERT INTO Patient (patientId, firstName, lastName, dateOfBirth, gender, contactNumber, address) VALUES
(101, 'Ananya', 'Rao', '1996-05-14', 'Female', '9876543210', '12 MG Road, chennai, TamilNadu'),
(102, 'Rahul', 'Verma', '1989-11-23', 'Male', '9123456780', '45 Anna Nagar, Chennai, Tamil Nadu'),
(103, 'Divya', 'Srinivasan', '1982-03-09', 'Female', '9000012345', '78 Residency Road, Coimbatore, Tamil Nadu'),
(104, 'Karan', 'Malhotra', '1993-08-02', 'Male', '9998877665', '15 Banjara Hills, Trichy, Tamil Nadu'),
(105, 'Sneha', 'Kumar', '1998-12-30', 'Female', '9789456123', '23 Salt Lake City, pondicherry,Tamil Nadu');

INSERT INTO Doctor (doctorId, firstName, lastName, specialization, contactNumber) VALUES
(201, 'Aarav', 'Sharma', 'Cardiologist', '9812345670'),
(202, 'Meera', 'Iyer', 'Dermatologist', '9823456710'),
(203, 'Rohan', 'Raj', 'Orthopedic', '9834567820'),
(204, 'Priya', 'Das', 'Pediatrician', '9845678930'),
(205, 'Vikram', 'singh', 'Neurologist', '9856789040');

INSERT INTO Appointment (appointmentId, patientId, doctorId, appointmentDate, description) VALUES
(301, 101, 201, '2025-04-10', 'Routine heart check-up'),
(302, 102, 202, '2025-04-11', 'Skin rash consultation'),
(303, 103, 203, '2025-04-12', 'Knee pain evaluation'),
(304, 104, 204, '2025-04-13', 'Child vaccination'),
(305, 105, 205, '2025-04-14', 'Migraine and neurological exam');