REPLACE INTO `roles` VALUES (1,'PROJECT_OWNER');
REPLACE INTO `roles` VALUES (2,'PROJECT_MANAGER');
REPLACE INTO `roles` VALUES (3,'TEAM_MEMBER');
INSERT INTO `person` (`person_id`, `competence`, `last_name`, `name`) VALUES (1, 'sysdev', 'friis', 'alex');
INSERT INTO `person` (`person_id`, `competence`, `last_name`, `name`) VALUES (2, 'sysdev', 'Soendergaard', 'mathias');
INSERT INTO `person` (`person_id`, `competence`, `last_name`, `name`) VALUES (3, 'admin', 'admin', 'admin');
INSERT INTO `person` (`person_id`, `competence`, `last_name`, `name`) VALUES (4, 'user', 'user', 'user');
INSERT INTO `person` (`person_id`, `competence`, `last_name`, `name`) VALUES (5, 'projectowner', 'levinson', 'jan');
INSERT INTO `person` (`person_id`, `competence`, `last_name`, `name`) VALUES (6, 'projectmanager', 'scott', 'michael');
INSERT INTO `person` (`person_id`, `competence`, `last_name`, `name`) VALUES (9, 'temp', 'temp', 'temp');
INSERT INTO `person` (`person_id`, `competence`, `last_name`, `name`) VALUES (10, 'developer', 'howard_the_temp', 'ryan');
INSERT INTO `person` (`person_id`, `competence`, `last_name`, `name`) VALUES (11, 'developer', 'hudson', 'stanley');
INSERT INTO `person` (`person_id`, `competence`, `last_name`, `name`) VALUES (12, 'developer', 'palmer', 'meredith');
INSERT INTO `person` (`person_id`, `competence`, `last_name`, `name`) VALUES (13, 'developer', 'beesly', 'pam');
INSERT INTO `person` (`person_id`, `competence`, `last_name`, `name`) VALUES (14, 'developer', 'bratton', 'creed');
INSERT INTO `person` (`person_id`, `competence`, `last_name`, `name`) VALUES (15, 'developer', 'flax', 'holly');
INSERT INTO `person` (`person_id`, `competence`, `last_name`, `name`) VALUES (16, 'developer', 'martin', 'angela');
INSERT INTO `person` (`person_id`, `competence`, `last_name`, `name`) VALUES (17, 'developer', 'martinez', 'oscar');
INSERT INTO `person` (`person_id`, `competence`, `last_name`, `name`) VALUES (18, 'developer', 'kapoor', 'kelly');
INSERT INTO `person` (`person_id`, `competence`, `last_name`, `name`) VALUES (19, 'developer', 'vance', 'phyllis');
INSERT INTO `person` (`person_id`, `competence`, `last_name`, `name`) VALUES (20, 'developer', 'schrute', 'dwight');
INSERT INTO `users` (`user_id`, `user_name`, `password`, `active`, `person_id`) VALUES (1, 'alex', '$2a$10$EZ1TVvvwsiHGnjXtmeMOQuJFlUXpxI4kjOGdOPhLD6o9kAzWqh38y', 1, 1);
INSERT INTO `users` (`user_id`, `user_name`, `password`, `active`, `person_id`) VALUES (2, 'mathias', '$2a$10$EZ1TVvvwsiHGnjXtmeMOQuJFlUXpxI4kjOGdOPhLD6o9kAzWqh38y', 1, 2);
INSERT INTO `users` (`user_id`, `user_name`, `password`, `active`, `person_id`) VALUES (3, 'admin', '$2a$10$EZ1TVvvwsiHGnjXtmeMOQuJFlUXpxI4kjOGdOPhLD6o9kAzWqh38y', 1, 3);
INSERT INTO `users` (`user_id`, `user_name`, `password`, `active`, `person_id`) VALUES (4, 'user', '$2a$10$EZ1TVvvwsiHGnjXtmeMOQuJFlUXpxI4kjOGdOPhLD6o9kAzWqh38y', 1, 4);
INSERT INTO `users` (`user_id`, `user_name`, `password`, `active`, `person_id`) VALUES (5, 'janlevinson', '$2a$10$EZ1TVvvwsiHGnjXtmeMOQuJFlUXpxI4kjOGdOPhLD6o9kAzWqh38y', 1, 5);
INSERT INTO `users` (`user_id`, `user_name`, `password`, `active`, `person_id`) VALUES (6, 'michaelscott', '$2a$10$EZ1TVvvwsiHGnjXtmeMOQuJFlUXpxI4kjOGdOPhLD6o9kAzWqh38y', 1, 6);
INSERT INTO `users` (`user_id`, `user_name`, `password`, `active`, `person_id`) VALUES (9, 'teamman1', '$2a$10$EZ1TVvvwsiHGnjXtmeMOQuJFlUXpxI4kjOGdOPhLD6o9kAzWqh38y', 1, 9);
INSERT INTO `users` (`user_id`, `user_name`, `password`, `active`, `person_id`) VALUES (10, 'teamman2', '$2a$10$EZ1TVvvwsiHGnjXtmeMOQuJFlUXpxI4kjOGdOPhLD6o9kAzWqh38y', 1, 10);
INSERT INTO `users` (`user_id`, `user_name`, `password`, `active`, `person_id`) VALUES (11, 'teamman3', '$2a$10$EZ1TVvvwsiHGnjXtmeMOQuJFlUXpxI4kjOGdOPhLD6o9kAzWqh38y', 1, 11);
INSERT INTO `users` (`user_id`, `user_name`, `password`, `active`, `person_id`) VALUES (12, 'teamman4', '$2a$10$EZ1TVvvwsiHGnjXtmeMOQuJFlUXpxI4kjOGdOPhLD6o9kAzWqh38y', 1, 12);
INSERT INTO `users` (`user_id`, `user_name`, `password`, `active`, `person_id`) VALUES (13, 'teamman5', '$2a$10$EZ1TVvvwsiHGnjXtmeMOQuJFlUXpxI4kjOGdOPhLD6o9kAzWqh38y', 1, 13);
INSERT INTO `users` (`user_id`, `user_name`, `password`, `active`, `person_id`) VALUES (14, 'teamman6', '$2a$10$EZ1TVvvwsiHGnjXtmeMOQuJFlUXpxI4kjOGdOPhLD6o9kAzWqh38y', 1, 14);
INSERT INTO `users` (`user_id`, `user_name`, `password`, `active`, `person_id`) VALUES (15, 'teamman7', '$2a$10$EZ1TVvvwsiHGnjXtmeMOQuJFlUXpxI4kjOGdOPhLD6o9kAzWqh38y', 1, 15);
INSERT INTO `users` (`user_id`, `user_name`, `password`, `active`, `person_id`) VALUES (16, 'teamman8', '$2a$10$EZ1TVvvwsiHGnjXtmeMOQuJFlUXpxI4kjOGdOPhLD6o9kAzWqh38y', 1, 16);
INSERT INTO `users` (`user_id`, `user_name`, `password`, `active`, `person_id`) VALUES (17, 'teamman9', '$2a$10$EZ1TVvvwsiHGnjXtmeMOQuJFlUXpxI4kjOGdOPhLD6o9kAzWqh38y', 1, 17);
INSERT INTO `users` (`user_id`, `user_name`, `password`, `active`, `person_id`) VALUES (18, 'teamman10', '$2a$10$EZ1TVvvwsiHGnjXtmeMOQuJFlUXpxI4kjOGdOPhLD6o9kAzWqh38y', 1, 18);
INSERT INTO `users` (`user_id`, `user_name`, `password`, `active`, `person_id`) VALUES (19, 'teamman11', '$2a$10$EZ1TVvvwsiHGnjXtmeMOQuJFlUXpxI4kjOGdOPhLD6o9kAzWqh38y', 1, 19);
INSERT INTO `users` (`user_id`, `user_name`, `password`, `active`, `person_id`) VALUES (20, 'teamman12', '$2a$10$EZ1TVvvwsiHGnjXtmeMOQuJFlUXpxI4kjOGdOPhLD6o9kAzWqh38y', 1, 20);
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES (1, 1); -- User "alex" has role PROJECT OWNER, PROJECT MANAGER, TEAM MEMBER
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES (1, 2);
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES (1, 3);
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES (2, 1); -- User "mathias" has role PROJECT OWNER, PROJECT MANAGER, TEAM MEMBER
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES (2, 2);
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES (2, 3);
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES (3, 1); -- User "admin" has role PROJECT OWNER
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES (4, 2); -- User "user" has role PROJECT MANAGER
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES (5, 1); -- User "janlevinson" has role PROJECT OWNER
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES (6, 2); -- User "michaelscott" has role PROJECT MANAGER
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
