use VehicleReservationDB;

-- Insert into customer table
insert into customer (FirstName, LastName, Email, PhoneNumber, Address, Username, Password)
values 
('Kirthi', 'Devi', 'kirthi.devi@gmail.com', '9876543210', 'Chennai, Tamil Nadu', 'kirthidevi', 'Abc@1234'),
('Manu', 'Karthick', 'manumadhu@gmail.com', '9898989898', 'Coimbatore, Tamil Nadu', 'manukarthick', 'Xyz@5678'),
('Kayal', 'Singaram', 'kayalsingaram83@gmail.com', '9789789789', 'Madurai, Tamil Nadu', 'kayalsingaram', 'Qwerty@123'),
('Kishore', 'Shiva', 'kishore11@gmail.com', '9765432109', 'Salem, Tamil Nadu', 'kishoreshiva', 'Pass@4567'),
('Priya', 'Kani', 'kani99@gmail.com', '9888888888', 'Trichy, Tamil Nadu', 'priyakani', 'Secure@9999');
select * from customer;

-- Insert into vehicle
insert into vehicle (Model, Make, Year, Color, RegistrationNumber, Availability, DailyRate)
values
('Swift', 'Maruti', 2022, 'White', 'TN01AB1234', TRUE, 1500.00),
('i20', 'Hyundai', 2023, 'Blue', 'TN02CD5678', TRUE, 1800.00),
('City', 'Honda', 2021, 'Red', 'TN03EF9101', FALSE, 2000.00),
('Thar', 'Mahindra', 2024, 'Black', 'TN04GH2345', TRUE, 2500.00),
('Altroz', 'Tata', 2022, 'Silver', 'TN05IJ6789', TRUE, 1600.00);
select * from vehicle;

-- Insert into reservation
insert into reservation (CustomerID, VehicleID, StartDate, EndDate, TotalCost, Status)
values
(1, 1, '2025-04-01 10:00:00', '2025-04-03 10:00:00', 4500.00, 'Confirmed'),
(2, 2, '2025-04-05 09:00:00', '2025-04-07 09:00:00', 3600.00, 'Pending'),
(3, 3, '2025-04-10 11:00:00', '2025-04-12 11:00:00', 4000.00, 'Completed'),
(4, 4, '2025-04-15 08:00:00', '2025-04-17 08:00:00', 5000.00, 'Confirmed'),
(5, 5, '2025-04-20 12:00:00', '2025-04-22 12:00:00', 3200.00, 'Cancelled');
select * from reservation;

-- Insert into admin 
insert into admin (FirstName, LastName, Email, PhoneNumber, Username, Password, Role)
values
('Rahul', 'Verma', 'rahul1989@gmail.com', '9876543210', 'rahulverma', 'Rahul@123', 'Super Admin'),
('Amit', 'Sharma', 'amit.sharma@gmail.com', '9786543211', 'amitsharma', 'Amit@456', 'Fleet Manager'),
('Priya', 'Shankar', 'priya789@gmail.com', '9654321987', 'priyashankar', 'Priya@789', 'Admin'),
('Ravi', 'Kumar', 'kumarravi01@gmail.com', '9765432102', 'ravikumar', 'Ravi@234', 'Manager'),
('Sneha', 'Reddy', 'snereddy00@gmail.com', '9567432105', 'snehareddy', 'Sneha@890', 'Super Admin');
select * from admin;