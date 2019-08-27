CREATE TABLE post(
	id INT NOT NULL AUTO_INCREMENT,
    title VARCHAR(400) NOT NULL,
    completed BOOLEAN NOT NULL DEFAULT 0,
    editing BOOLEAN NOT NULL DEFAULT 0,
    PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;;