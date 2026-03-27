-- SQL script to insert an admin user into the employee table
-- The password is 'admin123' hashed using BCrypt
INSERT INTO employee (email, fname, lname, password, role) 
VALUES ('admin@grind.com', 'System', 'Admin', '$2a$10$wTfD3w.n/gU05yZ1SGHb.u6iYfN.IfJ7D9RkK28B9X6Sxy9h4mXvG', 'ROLE_ADMIN');
