CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `user_fname` varchar(255) DEFAULT NULL,
  `user_lname` varchar(255) DEFAULT NULL,
  `user_email` varchar(255) DEFAULT NULL,
  `user_pass` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
);

CREATE TABLE `survey` (
  `survey_id` int NOT NULL AUTO_INCREMENT,
  `date_proposed` date DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `location_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`survey_id`),
  KEY `FK_user_id_idx` (`user_id`),
  CONSTRAINT `FK_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
);

CREATE TABLE `vote` (
  `vote_id` int NOT NULL AUTO_INCREMENT,
  `survey_id` int DEFAULT NULL,
  `vote_count` int DEFAULT NULL,
  PRIMARY KEY (`vote_id`),
  KEY `FK_survey_id_idx` (`survey_id`),
  CONSTRAINT `FK_survey_id` FOREIGN KEY (`survey_id`) REFERENCES `survey` (`survey_id`) ON DELETE SET NULL
);

INSERT INTO user VALUES ('1','User','Admin','user@admin.com','root');