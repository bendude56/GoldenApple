CREATE TABLE IF NOT EXISTS Locks (
	ID BIGINT PRIMARY KEY AUTO_INCREMENT,
	X BIGINT NOT NULL,
	Y BIGINT NOT NULL,
	Z BIGINT NOT NULL,
	World VARCHAR(128) NOT NULL,
	Type VARCHAR(32) NOT NULL,
	AccessLevel INT NOT NULL,
	Owner BIGINT NULL,
	AllowExternal BOOLEAN NOT NULL,
	INDEX ind_locks_location (World ASC, X ASC, Y ASC, Z ASC),
	INDEX ind_locks_owner (Owner ASC),
	CONSTRAINT fk_locks_owner
		FOREIGN KEY (Owner)
		REFERENCES Users(ID)
		ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB