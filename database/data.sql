 
 --insert query for admin
 insert into user (name,email,password,role,status,created_at) values("System Admin","tutorial@admin.com","$2a$10$X34cI.Y5t0RBsXDNUE1hDeA5Ih.RfKsKUtmadoaDrfEfQhICEEYSe","Admin","Active",NOW());
 insert into user (name,email,password,role,status,created_at) values("Project Admin","project@admin.com","$2a$10$J7FW1dwrGfMYEPXhwjtvfO4NLoDkJ3Uuk4cDJ76vh7h72uUhX/7o2","Admin","Active",NOW());

