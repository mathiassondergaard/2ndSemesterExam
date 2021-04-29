REPLACE INTO `roles` VALUES (1,'PROJECT_OWNER');
REPLACE INTO `roles` VALUES (2,'PROJECT_MANAGER');
REPLACE INTO `roles` VALUES (3,'TEAM_MEMBER');
INSERT INTO `users` (`user_id`, `user_name`, `password`, `active`) VALUES (1, 'alex', '$2a$10$EZ1TVvvwsiHGnjXtmeMOQuJFlUXpxI4kjOGdOPhLD6o9kAzWqh38y', 1);
INSERT INTO `users` (`user_id`, `user_name`, `password`, `active`) VALUES (2, 'mathias', '$2a$10$EZ1TVvvwsiHGnjXtmeMOQuJFlUXpxI4kjOGdOPhLD6o9kAzWqh38y', 1);
INSERT INTO `users` (`user_id`, `user_name`, `password`, `active`) VALUES (3, 'admin', '$2a$10$EZ1TVvvwsiHGnjXtmeMOQuJFlUXpxI4kjOGdOPhLD6o9kAzWqh38y', 1);
INSERT INTO `users` (`user_id`, `user_name`, `password`, `active`) VALUES (4, 'user', '$2a$10$EZ1TVvvwsiHGnjXtmeMOQuJFlUXpxI4kjOGdOPhLD6o9kAzWqh38y', 1);
INSERT INTO `users` (`user_id`, `user_name`, `password`, `active`) VALUES (5, 'temp', '$2a$10$EZ1TVvvwsiHGnjXtmeMOQuJFlUXpxI4kjOGdOPhLD6o9kAzWqh38y', 1);
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES (1, 1); -- User "alex" has role PROJECT OWNER, PROJECT MANAGER, TEAM MEMBER
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES (1, 2);
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES (1, 3);
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES (2, 1); -- User "mathias" has role PROJECT OWNER, PROJECT MANAGER, TEAM MEMBER
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES (2, 2);
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES (2, 3);
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES (3, 1); -- User "admin" has role PROJECT OWNER
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES (4, 2); -- User "user" has role PROJECT MANAGER
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES (5, 3); -- User "temp" has role TEAM MEMBER
