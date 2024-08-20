CREATE TABLE IF NOT EXISTS USERS (
  user_id INT PRIMARY KEY auto_increment,
  user_name VARCHAR(20) UNIQUE,
  salt VARCHAR(255),
  password VARCHAR(255),
  first_name VARCHAR(20),
  last_name VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS NOTES (
    note_id INT PRIMARY KEY auto_increment,
    note_title VARCHAR(20),
    note_description VARCHAR (1000),
    user_id INT,
    foreign key (user_id) references USERS(user_id)
);

CREATE TABLE IF NOT EXISTS FILES (
    file_id INT PRIMARY KEY auto_increment,
    file_name VARCHAR(255),
    content_type VARCHAR(255),
    file_size VARCHAR(255),
    user_id INT,
    file_data BLOB,
    foreign key (user_id) references USERS(user_id)
);

CREATE TABLE IF NOT EXISTS CREDENTIALS (
    credential_id INT PRIMARY KEY auto_increment,
    url VARCHAR(100),
    user_name VARCHAR (30),
    credential_key VARCHAR(255),
    password VARCHAR(255),
    user_id INT,
    foreign key (user_id) references USERS(user_id)
);
