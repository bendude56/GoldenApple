CREATE TABLE IF NOT EXISTS LockGroups (
	LockID BIGINT NOT NULL,
	GuestID BIGINT NOT NULL,
	AccessLevel INT NOT NULL,
	INDEX ind_lockgroups_lockid (LockID ASC),
	INDEX ind_lockgroups_guestid (GuestID ASC),
	CONSTRAINT fk_lockgroups_lockid
		FOREIGN KEY (LockID)	
		REFERENCES Locks(ID)
		ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT fk_lockgroups_guestid
		FOREIGN KEY (GuestID)
		REFERENCES Groups(ID)
		ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB