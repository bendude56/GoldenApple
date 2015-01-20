CREATE TABLE IF NOT EXISTS Mutes (
	ID INTEGER PRIMARY KEY AUTOINCREMENT,
	Target INTEGER NOT NULL,
	Admin INTEGER NULL,
	Reason VARCHAR(256) NOT NULL,
	StartTime TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	Duration INTEGER NULL,
	Voided BOOLEAN NOT NULL,
	Channel CHAR(32) NULL,
	CONSTRAINT fk_mutes_target
		FOREIGN KEY (Target)
		REFERENCES Users(ID)
		ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT fk_mutes_admin
		FOREIGN KEY (Admin)
		REFERENCES Users(ID)
		ON DELETE SET NULL ON UPDATE CASCADE,
	CONSTRAINT fk_mutes_channel
		FOREIGN KEY (Channel)
		REFERENCES Channels(Identifier)
		ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE INDEX ind_mutes_target ON Mutes (Target ASC, Channel ASC);
CREATE INDEX ind_mutes_admin ON Mutes (Admin ASC);
