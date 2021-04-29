REPLACE INTO `roles` VALUES (1,'USER');
INSERT INTO `users` (`user_id`, `user_name`, `password`, `name`, `last_name`, `active`) VALUES (1, 'alex', '$2a$10$EZ1TVvvwsiHGnjXtmeMOQuJFlUXpxI4kjOGdOPhLD6o9kAzWqh38y', 'Alex', 'Friis', 1);
INSERT INTO `users` (`user_id`, `user_name`, `password`, `name`, `last_name`, `active`) VALUES (2, 'mathias', '$2a$10$EZ1TVvvwsiHGnjXtmeMOQuJFlUXpxI4kjOGdOPhLD6o9kAzWqh38y', 'Mathias', 'Nielsen', 1);
INSERT INTO `users` (`user_id`, `user_name`, `password`, `name`, `last_name`, `active`) VALUES (3, 'admin', '$2a$10$EZ1TVvvwsiHGnjXtmeMOQuJFlUXpxI4kjOGdOPhLD6o9kAzWqh38y', 'Jane', 'Doe', 1);
INSERT INTO `users` (`user_id`, `user_name`, `password`, `name`, `last_name`, `active`) VALUES (4, 'user', '$2a$10$EZ1TVvvwsiHGnjXtmeMOQuJFlUXpxI4kjOGdOPhLD6o9kAzWqh38y', 'John', 'Doe', 1);
INSERT INTO `users` (`user_id`, `user_name`, `password`, `name`, `last_name`, `active`) VALUES (5, 'temp', '$2a$10$EZ1TVvvwsiHGnjXtmeMOQuJFlUXpxI4kjOGdOPhLD6o9kAzWqh38y', 'Ryan', 'The-Temp', 1);
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES (1, 1); -- User alex has role USER
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES (2, 1); -- User mathias has role USER
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES (3, 1); -- User admin has role USER
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES (4, 1); -- User user has role USER
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES (5, 1); -- User temp has role USER
