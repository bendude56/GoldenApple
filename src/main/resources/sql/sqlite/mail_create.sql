CREATE TABLE IF NOT EXISTS Mail (
	ID INTEGER PRIMARY KEY AUTOINCREMENT,
	Sender INTEGER NULL,
	Receiver INTEGER NOT NULL,
	Sent TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	Status CHAR(1) NOT NULL,
	LocalizedMessage VARCHAR(128) NULL,
	Subject VARCHAR(64) NULL,
	Contents TEXT NULL,
	CONSTRAINT fk_mail_sender
		FOREIGN KEY (Sender)
		REFERENCES Users(ID)
		ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT fk_mail_receiver
		FOREIGN KEY (Receiver)
		REFERENCES Users(ID)
		ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE INDEX ind_mail_receiver_sent ON Mail (Receiver ASC, Sent DESC);