CREATE TABLE IF NOT EXISTS RequestQueueReceivers (
	QueueID BIGINT NOT NULL,
	UserID BIGINT NOT NULL,
	Receiving BOOLEAN NOT NULL,
	AutoAssign BOOLEAN NOT NULL,
	PRIMARY KEY (QueueID, UserID),
	CONSTRAINT fk_requestqueuereceivers_queueid
		FOREIGN KEY (QueueID)
		REFERENCES RequestQueues(ID)
		ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT fk_requestqueuereceivers_userid
		FOREIGN KEY (UserID)
		REFERENCES Users(ID)
		ON DELETE CASCADE ON UPDATE CASCADE
);
