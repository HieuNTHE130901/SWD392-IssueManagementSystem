-- Create the database
CREATE DATABASE IMS_GROUP4;

-- Switch to the newly created database
USE IMS_GROUP4;

-- Create the user table
CREATE TABLE user (
    user_id INT PRIMARY KEY,
    full_name VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    mobile VARCHAR(20) UNIQUE,
    password VARCHAR(255),
    avatar_image VARCHAR(255),
    verification_code VARCHAR(255),
    user_role VARCHAR(50)
);

-- Create the semester table
CREATE TABLE semester (
    semester_id INT PRIMARY KEY,
    semester_name VARCHAR(255),
    start_date DATE,
    end_date DATE
);

-- Create the subject table
CREATE TABLE subject (
    subject_id INT PRIMARY KEY,
    manager_id INT,
    subject_code VARCHAR(255),
    subject_name VARCHAR(255),
    description TEXT,
    FOREIGN KEY (manager_id) REFERENCES user (user_id)
);

-- Create the class table
CREATE TABLE class (
    class_id INT PRIMARY KEY,
    semester_id INT,
    subject_id INT,
    teacher_id INT,
    class_name VARCHAR(255),
    status VARCHAR(255),
    description TEXT,
    FOREIGN KEY (semester_id) REFERENCES semester (semester_id),
    FOREIGN KEY (subject_id) REFERENCES subject (subject_id),
    FOREIGN KEY (teacher_id) REFERENCES user (user_id)
);

-- Create the assignment table
CREATE TABLE assignment (
    assignment_id INT PRIMARY KEY,
    subject_id INT,
    assignment_name VARCHAR(255),
    description TEXT,
    FOREIGN KEY (subject_id) REFERENCES subject (subject_id)
);

-- Create the class_member table
CREATE TABLE class_member (
    class_id INT,
    user_id INT,
    FOREIGN KEY (class_id) REFERENCES class (class_id),
    FOREIGN KEY (user_id) REFERENCES user (user_id)
);

-- Create the project table
CREATE TABLE project (
    project_id INT PRIMARY KEY,
    class_id INT,
    team_leader_id INT,
    project_code VARCHAR(255),
    project_name VARCHAR(255),
    description TEXT,
    FOREIGN KEY (class_id) REFERENCES class (class_id),
    FOREIGN KEY (team_leader_id) REFERENCES user (user_id)
);

-- Create the project_member table
CREATE TABLE project_member (
    project_id INT,
    member_id INT,
    FOREIGN KEY (project_id) REFERENCES project (project_id),
    FOREIGN KEY (member_id) REFERENCES user (user_id)
);

-- Create the milestone table
CREATE TABLE milestone (
    milestone_id INT PRIMARY KEY,
    class_id INT,
    project_id INT,
    milestone_name VARCHAR(255),
    description TEXT,
    start_date DATE,
    end_date DATE,
    FOREIGN KEY (class_id) REFERENCES class (class_id),
    FOREIGN KEY (project_id) REFERENCES project (project_id)
);

-- Create the issue table
CREATE TABLE issue (
    issue_id INT PRIMARY KEY,
    project_id INT,
    milestone_id INT,
    assigner_id INT,
    assignee_id INT,
    description TEXT,
    created_date DATETIME,
    updated_date DATETIME,
    FOREIGN KEY (project_id) REFERENCES project (project_id),
    FOREIGN KEY (milestone_id) REFERENCES milestone (milestone_id),
    FOREIGN KEY (assigner_id) REFERENCES user (user_id),
    FOREIGN KEY (assignee_id) REFERENCES user (user_id)
);

-- Create the issue_setting table
CREATE TABLE issue_setting (
    issue_setting_id INT PRIMARY KEY,
    issue_id INT,
    issue_type VARCHAR(255),
    issue_status VARCHAR(255),
    work_process VARCHAR(255),
    FOREIGN KEY (issue_id) REFERENCES issue (issue_id)
);
ALTER TABLE `issue_setting` ADD `issue_complexity` VARCHAR(255) NULL AFTER `issue_status`;

-- Create the system_setting table
CREATE TABLE system_setting (
    setting_id INT PRIMARY KEY,
    setting_name VARCHAR(255),
    setting_value VARCHAR(255)
);


INSERT INTO user (user_id, full_name, email, mobile, password, avatar_image, verification_code, user_role)
VALUES (1, 'John Doe', 'admin@ims.com', '1234567890', '123123', 'avatar1.jpg', '123456', 'admin');
INSERT INTO user (user_id, full_name, email, mobile, password, avatar_image, verification_code, user_role)
VALUES (2, 'Jane Smith', 'manager@ims.com', '9876543210', '123123', 'avatar2.jpg', '654321', 'manager');
INSERT INTO user (user_id, full_name, email, mobile, password, avatar_image, verification_code, user_role)
VALUES (3, 'KienNT', 'teacher@ims.com', '5555555555', '123123', 'avatar3.jpg', '987654', 'teacher');
INSERT INTO user (user_id, full_name, email, mobile, password, avatar_image, verification_code, user_role)
VALUES (4, 'Thomas Edison', 'leader@ims.com', '1231231231', '123123', 'avatar3.jpg', '987654', 'leader');
INSERT INTO user (user_id, full_name, email, mobile, password, avatar_image, verification_code, user_role)
VALUES (5, 'Mikel Arketa', 'student@ims.com', '1233211231', '123123', 'avatar3.jpg', '987654', 'student');
INSERT INTO user (user_id, full_name, email, mobile, password, avatar_image, verification_code, user_role)
VALUES (6, 'trunghieu', 'trunghieu@ims.com', '0987654321', '123123', 'avatar3.jpg', '987654', 'student');
INSERT INTO user (user_id, full_name, email, mobile, password, avatar_image, verification_code, user_role)
VALUES (7, 'huyhung', 'huyhung@ims.com', '0987654322', '123123', 'avatar3.jpg', '987654', 'student');
INSERT INTO user (user_id, full_name, email, mobile, password, avatar_image, verification_code, user_role)
VALUES (8, 'tuanhuy', 'tuanhuy@ims.com', '0987654323', '123123', 'avatar3.jpg', '987654', 'student');
INSERT INTO user (user_id, full_name, email, mobile, password, avatar_image, verification_code, user_role)
VALUES (9, 'minhduc', 'minhduc@ims.com', '0987654324', '123123', 'avatar3.jpg', '987654', 'student');
INSERT INTO `user` (`user_id`, `full_name`, `email`, `mobile`, `password`, `avatar_image`, `verification_code`, `user_role`) VALUES ('10', 'manager 2', 'manager2@ims.com', '0374184543', '123123', '123123', '123123', 'manager');
INSERT INTO `user` (`user_id`, `full_name`, `email`, `mobile`, `password`, `avatar_image`, `verification_code`, `user_role`) VALUES ('11', 'DuongTB', 'duongtb@ims.com', '0123456784', '123123', '123123', '123123', 'teacher');
INSERT INTO `system_setting` (`setting_id`, `setting_name`, `setting_value`) VALUES ('1', 'permitted domain', '@ims.com'), ('2', 'permitted domain', '@gmail.com');
INSERT INTO `subject` (`subject_id`, `manager_id`, `subject_code`, `subject_name`, `description`) VALUES ('1', '2', 'SWD392', 'Software Architecture', 'Software Architecture description'), ('2', '2', 'PRM392', 'Mobile Programing', 'Mobile Programing description');
INSERT INTO `semester` (`semester_id`, `semester_name`, `start_date`, `end_date`) VALUES ('1', 'Fall 22', '2022-09-01', '2022-12-01'), ('2', 'Spring 23', '2023-01-01', '2023-04-01'), ('3', 'Summer 23', '2023-05-01', '2023-08-01'), ('4', 'Fall 23', '2023-09-01', '2023-12-01');
INSERT INTO `class` (`class_id`, `semester_id`, `subject_id`, `teacher_id`, `class_name`, `status`, `description`) VALUES ('1', '4', '1', '3', 'SE9999', 'Active', NULL), ('2', '4', '2', '11', 'SE1234', NULL, NULL);
INSERT INTO `project` (`project_id`, `class_id`, `team_leader_id`, `project_code`, `project_name`, `description`) VALUES ('1', '1', '4', 'IMS_v1', 'Issue management system', 'Very hard');
INSERT INTO `project` (`project_id`, `class_id`, `team_leader_id`, `project_code`, `project_name`, `description`) VALUES ('2', '1', '6', 'IMS_v9', 'Issue management system version 9', NULL);
INSERT INTO `project_member` (`project_id`, `member_id`) VALUES ('2', '6'), ('2', '7'), ('2', '9'), ('2', '8');
INSERT INTO `project_member` (`project_id`, `member_id`) VALUES ('1', '5');
INSERT INTO `milestone` (`milestone_id`, `class_id`, `project_id`, `milestone_name`, `description`, `start_date`, `end_date`) VALUES ('1', '1', '2', 'Milestone 1', NULL, NULL, NULL);
INSERT INTO `milestone` (`milestone_id`, `class_id`, `project_id`, `milestone_name`, `description`, `start_date`, `end_date`) VALUES ('2', '1', '2', 'Milestone 2', NULL, NULL, NULL);-- Insert data into the issue table
INSERT INTO issue (issue_id, project_id, milestone_id, assigner_id, assignee_id, description, created_date, updated_date)
VALUES ('1', '2', '1', '3', '6', NULL, NULL, NULL);
INSERT INTO issue (issue_id, project_id, milestone_id, assigner_id, assignee_id, description, created_date, updated_date)
VALUES ('2', '2', '2', '3', '8', NULL, NULL, NULL);
INSERT INTO `class_member` (`class_id`, `user_id`) VALUES ('1', '6'), ('1', '7'), ('1', '8'), ('1', '9'), ('2', '5');
INSERT INTO `assignment` (`assignment_id`, `subject_id`, `assignment_name`, `description`) VALUES ('1', '1', 'Assignment 1', NULL), ('2', '2', 'Assignment 1', NULL);

INSERT INTO `issue_setting` (`issue_setting_id`, `issue_id`, `issue_type`, `issue_status`, `work_process`) VALUES ('1', '1', 'Bug', 'Open', 'working'), ('2', '2', 'Feature', 'Open', NULL);
UPDATE `issue_setting` SET `issue_complexity` = 'Complex' WHERE `issue_setting`.`issue_setting_id` = 1
UPDATE `issue_setting` SET `issue_complexity` = 'Medium' WHERE `issue_setting`.`issue_setting_id` = 2
UPDATE `issue_setting` SET `issue_complexity` = 'Simple' WHERE `issue_setting`.`issue_setting_id` = 3

