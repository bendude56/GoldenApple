CREATE TABLE IF NOT EXISTS WarpGroups (
	Warp VARCHAR(32) NOT NULL,
	GroupID BIGINT NOT NULL,
	CONSTRAINT fk_warpgroups_warp
		FOREIGN KEY (Warp)
		REFERENCES Warps(Name)
		ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT fk_warpgroups_group
		FOREIGN KEY (GroupID)
		REFERENCES Groups(ID)
		ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE INDEX ind_warpgroups_warp ON Warps (Warp ASC);
