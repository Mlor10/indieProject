delete from user;
delete from card;
ALTER TABLE user AUTO_INCREMENT = 1;
ALTER TABLE card AUTO_INCREMENT = 1;
INSERT INTO user (user_name, password, email) VALUES ('matt1', 'pass1', 'm1@madisoncollege.edu'), ('matt2', 'pass2', 'm2@madisoncollege.edu'), ('matt3', 'pass3', 'm3@madisoncollege.edu'), ('matt4', 'pass4', 'm4@madisoncollege.edu'), ('matt5', 'pass5', 'm5@madisoncollege.edu'), ('matt6', 'pass6', 'm6@madisoncollege.edu');
INSERT INTO card (card_name, card_description, price, user_id) VALUES ('Omnimon', 'example description', '12.99', '1'), ('Gallantmon', 'example description', '11.00', '1'), ('Dukemon', 'example description', '16.50', '2'), ('Agumon', 'example description', '4.99', '3'), ('Veemon', 'example description', '3.99', '4'), ('Lucemon', 'example description', '14.00', '5');