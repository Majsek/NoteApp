INSERT INTO users (username, password, role) VALUES ('admin', '$2a$12$PUEz62.Y/3Et/HzaQ/WESeC8qou7GSmbL.C1ey9WBy9RgpIr/uagW', 'ADMIN');

-- INSERT INTO user_details (email, full_name, user_id) VALUES ('ardolf@email.com', 'Adolf Rudolf', 1);

INSERT INTO users (username, password, role) VALUES ('user', '$2a$12$PUEz62.Y/3Et/HzaQ/WESeC8qou7GSmbL.C1ey9WBy9RgpIr/uagW', 'USER');
INSERT INTO users (username, password, role) VALUES ('franta', '$2a$12$PUEz62.Y/3Et/HzaQ/WESeC8qou7GSmbL.C1ey9WBy9RgpIr/uagW', 'USER');
INSERT INTO users (username, password, role) VALUES ('adolf', '$2a$12$PUEz62.Y/3Et/HzaQ/WESeC8qou7GSmbL.C1ey9WBy9RgpIr/uagW', 'USER');



INSERT INTO boards (owner_id, description, name) VALUES (1, 'prostě poznámky', 'Poznámky');
INSERT INTO boards (owner_id, description, name) VALUES (1, 'prostě další poznámky', 'Další Poznámky');

INSERT INTO board_collaborators (board_id, user_id) VALUES (1, 3);
INSERT INTO board_collaborators (board_id, user_id) VALUES (1, 2);





-- INSERT INTO user_details (email, full_name, user_id) VALUES ('franta.vomyc@email.com', 'František Vomáčka', 2);
-- INSERT INTO notes (content, title) VALUES ('Obsah poznámky', 'Nadpis');  