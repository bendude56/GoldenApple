CREATE TABLE IF NOT EXISTS LockUsers (
	LockID BIGINT NOT NULL,
	GuestID BIGINT NOT NULL,
	AccessLevel INT NOT NULL,
	CONSTRAINT fk_lockusers_lockid
		FOREIGN KEY (LockID)	
		REFERENCES Locks(ID)
		ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT fk_lockusers_guestid
		FOREIGN KEY (GuestID)
		REFERENCES Users(ID)
		ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE INDEX ind_lockusers_lockid ON LockUsers (LockID ASC);
CREATE INDEX ind_lockusers_guestid ON LocksUsers (GuestID ASC);
