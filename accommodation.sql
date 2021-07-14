CREATE DATABASE accommodation;
CREATE TABLE accommodation.student_details (
  `id` int NOT NULL AUTO_INCREMENT,
  `Student_name` varchar(30) DEFAULT NULL,
  `Student_age` int DEFAULT NULL,
  `Student_address` varchar(40) DEFAULT NULL,
  `Student_contact` varchar(20) DEFAULT NULL,
  `User_id` int DEFAULT NULL,
  PRIMARY KEY (`id`)
  );
	

CREATE TABLE accommodation.user_info (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_name` varchar(30) DEFAULT NULL,
  `password` varchar(225) DEFAULT NULL,
  `role_id` int DEFAULT NULL,
  PRIMARY KEY (`id`)
	);

CREATE TABLE accommodation.role (
  `id` int NOT NULL AUTO_INCREMENT,
  `role_name` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
	);

CREATE TABLE accommodation.room_details (
  `id` int NOT NULL AUTO_INCREMENT,
  `type_id` varchar(30) DEFAULT NULL,
  `room_location` varchar(40) DEFAULT NULL,
  `monthly_charge` int DEFAULT NULL,
  `roomtype_name` varchar(50) DEFAULT NULL,
  `room_available` tinyint(1) DEFAULT NULL,
  `room_description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
	);
        
CREATE TABLE accommodation.room_type (
  `id` int NOT NULL AUTO_INCREMENT,
  `typename` varchar(30) DEFAULT NULL,
  `available` int DEFAULT NULL,
  PRIMARY KEY (`id`)
    );
    
   CREATE TABLE accommodation.student_room (
  `id` int NOT NULL AUTO_INCREMENT,
  `student_id` int DEFAULT NULL,
  `room_id` int DEFAULT NULL,
  `payement_status` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
    );
    