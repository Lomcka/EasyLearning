\connect easylearning_db;

CREATE TABLE users (
	id bigserial,
	login varchar(256) NOT NULL,
	key_word varchar(256) NOT NULL,
	CONSTRAINT PK_users PRIMARY KEY(id),
	CONSTRAINT UNIQUE_users_login UNIQUE(login)
);

CREATE TABLE passwords (
	user_id bigint NOT NULL,
	user_password varchar(256) NOT NULL,
	CONSTRAINT FK_passwords_users FOREIGN KEY(user_id) REFERENCES users(id)
	ON DELETE CASCADE,
	CONSTRAINT UNIQUE_passwords_user_id UNIQUE(user_id)
);

CREATE TABLE modules (
	id bigserial,
	name varchar(256) NOT NULL,
	owner_id bigint NOT NULL,
	CONSTRAINT PK_modules PRIMARY KEY(id),
	CONSTRAINT FK_modules_users FOREIGN KEY(owner_id) REFERENCES users(id),
	CONSTRAINT UNIQUE_modules_name_owner_id UNIQUE(name, owner_id)
);

CREATE TABLE folders (
	id bigserial,
	name varchar(256) NOT NULL,
	owner_id bigint NOT NULL,
	CONSTRAINT PK_folders PRIMARY KEY(id),
	CONSTRAINT FK_folders_users FOREIGN KEY(owner_id) REFERENCES users(id),
	CONSTRAINT UNIQUE_folders_name_owner_id UNIQUE(name, owner_id)
);

CREATE TABLE modules_readers (
	module_id bigint NOT NULL,
	reader_id bigint NOT NULL,
	CONSTRAINT FK_modules_readers_modules FOREIGN KEY(module_id) REFERENCES modules(id),
	CONSTRAINT FK_modules_readers_users FOREIGN KEY(reader_id) REFERENCES users(id),
	CONSTRAINT UNIQUE_modules_readers_module_id_reader_id UNIQUE(module_id, reader_id)
);

CREATE TABLE modules_contents (
	module_id bigint NOT NULL,
	card_id bigint NOT NULL,
	word varchar(256) NOT NULL,
	translation varchar(256) NOT NULL,
	CONSTRAINT PK_modules_contents PRIMARY KEY(module_id, card_id),
	CONSTRAINT FK_modules_contents_modules FOREIGN KEY(module_id) REFERENCES modules(id)
);

CREATE TABLE folders_contents (
	folder_id bigint NOT NULL,
	module_id bigint NOT NULL,
	CONSTRAINT FK_folders_contents_folders FOREIGN KEY(folder_id) REFERENCES folders(id),
	CONSTRAINT FK_folders_contents_modules FOREIGN KEY(module_id) REFERENCES modules(id),
	CONSTRAINT UNIQUE_folders_contents_folder_id_module_id UNIQUE(folder_id, module_id)
);

