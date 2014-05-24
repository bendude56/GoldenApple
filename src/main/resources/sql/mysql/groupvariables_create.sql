CREATE TABLE IF NOT EXISTS GroupVariables (
	GroupID BIGINT NOT NULL,
	VariableName VARCHAR(32) NOT NULL,
	Value TEXT NOT NULL,
	CONSTRAINT fk_groupvariables_groupid
		FOREIGN KEY (GroupID)
		REFERENCES Groups(ID)
		ON DELETE CASCADE ON UPDATE CASCADE,
	PRIMARY KEY (GroupID, VariableName)
) Engine=InnoDB