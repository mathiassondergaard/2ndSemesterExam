REPLACE INTO `roles` VALUES (1,'PROJECT_OWNER');
REPLACE INTO `roles` VALUES (2,'PROJECT_MANAGER');
REPLACE INTO `roles` VALUES (3,'TEAM_MEMBER');
INSERT INTO `users` (`user_id`, `user_name`, `password`, `active`) VALUES (1, 'alex', '$2a$10$EZ1TVvvwsiHGnjXtmeMOQuJFlUXpxI4kjOGdOPhLD6o9kAzWqh38y', 1);
INSERT INTO `users` (`user_id`, `user_name`, `password`, `active`) VALUES (2, 'mathias', '$2a$10$EZ1TVvvwsiHGnjXtmeMOQuJFlUXpxI4kjOGdOPhLD6o9kAzWqh38y', 1);
INSERT INTO `users` (`user_id`, `user_name`, `password`, `active`) VALUES (3, 'admin', '$2a$10$EZ1TVvvwsiHGnjXtmeMOQuJFlUXpxI4kjOGdOPhLD6o9kAzWqh38y', 1);
INSERT INTO `users` (`user_id`, `user_name`, `password`, `active`) VALUES (4, 'user', '$2a$10$EZ1TVvvwsiHGnjXtmeMOQuJFlUXpxI4kjOGdOPhLD6o9kAzWqh38y', 1);
INSERT INTO `users` (`user_id`, `user_name`, `password`, `active`) VALUES (5, 'projectman1', '$2a$10$EZ1TVvvwsiHGnjXtmeMOQuJFlUXpxI4kjOGdOPhLD6o9kAzWqh38y', 1);
INSERT INTO `users` (`user_id`, `user_name`, `password`, `active`) VALUES (6, 'projectman2', '$2a$10$EZ1TVvvwsiHGnjXtmeMOQuJFlUXpxI4kjOGdOPhLD6o9kAzWqh38y', 1);
INSERT INTO `users` (`user_id`, `user_name`, `password`, `active`) VALUES (7, 'projectman3', '$2a$10$EZ1TVvvwsiHGnjXtmeMOQuJFlUXpxI4kjOGdOPhLD6o9kAzWqh38y', 1);
INSERT INTO `users` (`user_id`, `user_name`, `password`, `active`) VALUES (8, 'temp', '$2a$10$EZ1TVvvwsiHGnjXtmeMOQuJFlUXpxI4kjOGdOPhLD6o9kAzWqh38y', 1);
INSERT INTO `users` (`user_id`, `user_name`, `password`, `active`) VALUES (9, 'teamman1', '$2a$10$EZ1TVvvwsiHGnjXtmeMOQuJFlUXpxI4kjOGdOPhLD6o9kAzWqh38y', 1);
INSERT INTO `users` (`user_id`, `user_name`, `password`, `active`) VALUES (10, 'teamman2', '$2a$10$EZ1TVvvwsiHGnjXtmeMOQuJFlUXpxI4kjOGdOPhLD6o9kAzWqh38y', 1);
INSERT INTO `users` (`user_id`, `user_name`, `password`, `active`) VALUES (11, 'teamman3', '$2a$10$EZ1TVvvwsiHGnjXtmeMOQuJFlUXpxI4kjOGdOPhLD6o9kAzWqh38y', 1);
INSERT INTO `users` (`user_id`, `user_name`, `password`, `active`) VALUES (12, 'teamman4', '$2a$10$EZ1TVvvwsiHGnjXtmeMOQuJFlUXpxI4kjOGdOPhLD6o9kAzWqh38y', 1);
INSERT INTO `users` (`user_id`, `user_name`, `password`, `active`) VALUES (13, 'teamman5', '$2a$10$EZ1TVvvwsiHGnjXtmeMOQuJFlUXpxI4kjOGdOPhLD6o9kAzWqh38y', 1);
INSERT INTO `users` (`user_id`, `user_name`, `password`, `active`) VALUES (14, 'teamman6', '$2a$10$EZ1TVvvwsiHGnjXtmeMOQuJFlUXpxI4kjOGdOPhLD6o9kAzWqh38y', 1);
INSERT INTO `users` (`user_id`, `user_name`, `password`, `active`) VALUES (15, 'teamman7', '$2a$10$EZ1TVvvwsiHGnjXtmeMOQuJFlUXpxI4kjOGdOPhLD6o9kAzWqh38y', 1);
INSERT INTO `users` (`user_id`, `user_name`, `password`, `active`) VALUES (16, 'teamman8', '$2a$10$EZ1TVvvwsiHGnjXtmeMOQuJFlUXpxI4kjOGdOPhLD6o9kAzWqh38y', 1);
INSERT INTO `users` (`user_id`, `user_name`, `password`, `active`) VALUES (17, 'teamman9', '$2a$10$EZ1TVvvwsiHGnjXtmeMOQuJFlUXpxI4kjOGdOPhLD6o9kAzWqh38y', 1);
INSERT INTO `users` (`user_id`, `user_name`, `password`, `active`) VALUES (18, 'teamman10', '$2a$10$EZ1TVvvwsiHGnjXtmeMOQuJFlUXpxI4kjOGdOPhLD6o9kAzWqh38y', 1);
INSERT INTO `users` (`user_id`, `user_name`, `password`, `active`) VALUES (19, 'teamman11', '$2a$10$EZ1TVvvwsiHGnjXtmeMOQuJFlUXpxI4kjOGdOPhLD6o9kAzWqh38y', 1);
INSERT INTO `users` (`user_id`, `user_name`, `password`, `active`) VALUES (20, 'teamman12', '$2a$10$EZ1TVvvwsiHGnjXtmeMOQuJFlUXpxI4kjOGdOPhLD6o9kAzWqh38y', 1);
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES (1, 1); -- User "alex" has role PROJECT OWNER, PROJECT MANAGER, TEAM MEMBER
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES (1, 2);
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES (1, 3);
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES (2, 1); -- User "mathias" has role PROJECT OWNER, PROJECT MANAGER, TEAM MEMBER
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES (2, 2);
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES (2, 3);
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES (3, 1); -- User "admin" has role PROJECT OWNER
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES (4, 2); -- User "user" has role PROJECT MANAGER
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES (5, 2); -- User "projectman1" has role PROJECT MANAGER
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES (6, 2); -- User "Projectman2" has role PROJECT MANAGER
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES (7, 2); -- User "Projectman1" has role PROJECT MANAGER
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES (8, 3); -- User "temp" has role TEAM MEMBER
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES (9, 3); -- User "teamman1" has role TEAM MEMBER
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES (10, 3); -- User "teamman2" has role TEAM MEMBER
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES (11, 3); -- User "teamman3" has role TEAM MEMBER
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES (12, 3); -- User "teamman4" has role TEAM MEMBER
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES (13, 3); -- User "teamman5" has role TEAM MEMBER
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES (14, 3); -- User "teamman6" has role TEAM MEMBER
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES (15, 3); -- User "teamman7" has role TEAM MEMBER
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES (16, 3); -- User "teamman8" has role TEAM MEMBER
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES (17, 3); -- User "teamman9" has role TEAM MEMBER
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES (18, 3); -- User "teamman10" has role TEAM MEMBER
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES (19, 3); -- User "teamman11" has role TEAM MEMBER
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES (20, 3); -- User "teamman12" has role TEAM MEMBER
INSERT INTO `person` (`person_id`, `competence`, `last_name`, `name`) VALUES (1, 'codemonkey', 'friis', 'alex');
INSERT INTO `person` (`person_id`, `competence`, `last_name`, `name`) VALUES (2, 'codemonkey', 'Soendergaard', 'mathias');
INSERT INTO `person` (`person_id`, `competence`, `last_name`, `name`) VALUES (3, 'admin', 'admin', 'admin');
INSERT INTO `person` (`person_id`, `competence`, `last_name`, `name`) VALUES (4, 'user', 'user', 'user');
INSERT INTO `person` (`person_id`, `competence`, `last_name`, `name`) VALUES (5, 'projectmanager', 'man1', 'project');
INSERT INTO `person` (`person_id`, `competence`, `last_name`, `name`) VALUES (6, 'projectmanager', 'man2', 'project');
INSERT INTO `person` (`person_id`, `competence`, `last_name`, `name`) VALUES (7, 'projectmanager', 'man3', 'project');
INSERT INTO `person` (`person_id`, `competence`, `last_name`, `name`) VALUES (9, 'temp', 'temp', 'temp');
INSERT INTO `person` (`person_id`, `competence`, `last_name`, `name`) VALUES (10, 'javamonkey', 'man1', 'team');
INSERT INTO `person` (`person_id`, `competence`, `last_name`, `name`) VALUES (11, 'javamonkey', 'man2', 'team');
INSERT INTO `person` (`person_id`, `competence`, `last_name`, `name`) VALUES (12, 'javamonkey', 'man3', 'team');
INSERT INTO `person` (`person_id`, `competence`, `last_name`, `name`) VALUES (13, 'designmonkey', 'des1', 'team');
INSERT INTO `person` (`person_id`, `competence`, `last_name`, `name`) VALUES (14, 'designmonkey', 'des2', 'team');
INSERT INTO `person` (`person_id`, `competence`, `last_name`, `name`) VALUES (15, 'designmonkey', 'des3', 'team');
INSERT INTO `person` (`person_id`, `competence`, `last_name`, `name`) VALUES (16, 'frontendmonkey', 'front1', 'team');
INSERT INTO `person` (`person_id`, `competence`, `last_name`, `name`) VALUES (17, 'frontendmonkey', 'front2', 'team');
INSERT INTO `person` (`person_id`, `competence`, `last_name`, `name`) VALUES (18, 'frontendmonkey', 'front3', 'team');
INSERT INTO `person` (`person_id`, `competence`, `last_name`, `name`) VALUES (19, 'backendmonkey', 'back1', 'team');
INSERT INTO `person` (`person_id`, `competence`, `last_name`, `name`) VALUES (20, 'backendmonkey', 'back2', 'team');
REPLACE INTO `user_person` (`user_id`, `person_id`) VALUES (1, 1);
REPLACE INTO `user_person` (`user_id`, `person_id`) VALUES (2, 2);
REPLACE INTO `user_person` (`user_id`, `person_id`) VALUES (3, 3);
REPLACE INTO `user_person` (`user_id`, `person_id`) VALUES (4, 4);
REPLACE INTO `user_person` (`user_id`, `person_id`) VALUES (5, 5);
REPLACE INTO `user_person` (`user_id`, `person_id`) VALUES (6, 6);
REPLACE INTO `user_person` (`user_id`, `person_id`) VALUES (7, 7);
REPLACE INTO `user_person` (`user_id`, `person_id`) VALUES (8, 8);
REPLACE INTO `user_person` (`user_id`, `person_id`) VALUES (9, 9);
REPLACE INTO `user_person` (`user_id`, `person_id`) VALUES (10, 10);
REPLACE INTO `user_person` (`user_id`, `person_id`) VALUES (11, 11);
REPLACE INTO `user_person` (`user_id`, `person_id`) VALUES (12, 12);
REPLACE INTO `user_person` (`user_id`, `person_id`) VALUES (13, 13);
REPLACE INTO `user_person` (`user_id`, `person_id`) VALUES (14, 14);
REPLACE INTO `user_person` (`user_id`, `person_id`) VALUES (15, 15);
REPLACE INTO `user_person` (`user_id`, `person_id`) VALUES (16, 16);
REPLACE INTO `user_person` (`user_id`, `person_id`) VALUES (17, 17);
REPLACE INTO `user_person` (`user_id`, `person_id`) VALUES (19, 18);
REPLACE INTO `user_person` (`user_id`, `person_id`) VALUES (19, 19);
REPLACE INTO `user_person` (`user_id`, `person_id`) VALUES (20, 20);