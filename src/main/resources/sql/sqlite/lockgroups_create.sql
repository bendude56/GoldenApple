CREATE TABLE IF NOT EXISTS LockGroups (
	LockID INTEGER NOT NULL,
	GuestID INTEGER NOT NULL,
	AccessLevel INTEGER NOT NULL,
	CONSTRAINT fk_lockgroups_lockid
		FOREIGN KEY (LockID)	
		REFERENCES Locks(ID)
		ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT fk_lockgroups_guestid
		FOREIGN KEY (GuestID)
		REFERENCES Groups(ID)
		ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE INDEX ind_lockgroups_lockid ON LockGroups (LockID ASC);
CREATE INDEX ind_lockgroups_guestid ON LockGroups (QuestID ASC);
