\connect easylearning_db;

CREATE TABLE users (
	id serial,
	login varchar(256) NOT NULL,
	key_word varchar(256) NOT NULL,
	CONSTRAINT PK_users PRIMARY KEY(id)
);

CREATE TABLE passwords (
	id serial,
	user_id integer NOT NULL,
	password varchar(256) NOT NULL,
	CONSTRAINT PK_passwords PRIMARY KEY(id),
	CONSTRAINT FK_passwords_users FOREIGN KEY(user_id) REFERENCES users(id)
	ON DELETE CASCADE
);

CREATE TABLE modules (
	id bigserial,
	name varchar(256) NOT NULL,
	owner_id integer NOT NULL,
	CONSTRAINT PK_modules PRIMARY KEY(id),
	CONSTRAINT FK_modules_users FOREIGN KEY(owner_id) REFERENCES users(id)
);

CREATE TABLE folders (
	id bigserial,
	name varchar(256) NOT NULL,
	owner_id integer NOT NULL,
	CONSTRAINT PK_folders PRIMARY KEY(id),
	CONSTRAINT FK_folders_users FOREIGN KEY(owner_id) REFERENCES users(id)
);

CREATE TABLE modules_readers (
	module_id bigint NOT NULL,
	reader_id integer NOT NULL,
	CONSTRAINT FK_modules_readers_modules FOREIGN KEY(module_id) REFERENCES modules(id),
	CONSTRAINT FK_modules_readers_users FOREIGN KEY(reader_id) REFERENCES users(id)
);

CREATE TABLE modules_contents (
	module_id bigint NOT NULL,
	card_id integer NOT NULL,
	word varchar(256) NOT NULL,
	translation varchar(256) NOT NULL,
	CONSTRAINT PK_modules_contents PRIMARY KEY(module_id, card_id),
	CONSTRAINT FK_modules_contents_modules FOREIGN KEY(module_id) REFERENCES modules(id)
);

CREATE TABLE folders_contents (
	folder_id bigint NOT NULL,
	module_id bigint NOT NULL,
	CONSTRAINT FK_folders_contents_folders FOREIGN KEY(folder_id) REFERENCES folders(id),
	CONSTRAINT FK_folders_contents_modules FOREIGN KEY(module_id) REFERENCES modules(id)
);

