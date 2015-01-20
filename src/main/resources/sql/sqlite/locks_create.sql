CREATE TABLE IF NOT EXISTS Locks (
	ID INTEGER PRIMARY KEY AUTOINCREMENT,
	X INTEGER NOT NULL,
	Y INTEGER NOT NULL,
	Z INTEGER NOT NULL,
	World VARCHAR(128) NOT NULL,
	Type VARCHAR(32) NOT NULL,
	AccessLevel INTEGER NOT NULL,
	Owner BIGINT NULL,
	AllowExternal BOOLEAN NOT NULL,
	CONSTRAINT fk_locks_owner
		FOREIGN KEY (Owner)
		REFERENCES Users(ID)
		ON DELETE SET NULL ON UPDATE CASCADE
);

CREATE INDEX ind_locks_location ON Locks (World ASC, X ASC, Y ASC, Z ASC);
CREATE INDEX ind_locks_owner ON Locks (Owner ASC);
