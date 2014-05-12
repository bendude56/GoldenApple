CREATE TABLE IF NOT EXISTS Requests (
	ID BIGINT PRIMARY KEY,
	QueueID BIGINT NOT NULL,
	Sender BIGINT NOT NULL,
	AssignedReceiver BIGINT NULL,
	Message VARCHAR(256) NOT NULL,
	Created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	Closed TIMESTAMP NULL,
	OnHold BOOLEAN NOT NULL DEFAULT 0,
	CONSTRAINT fk_requests_queueid
		FOREIGN KEY (QueueID)
		REFERENCES RequestQueues(ID)
		ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT fk_requests_sender
		FOREIGN KEY (Sender)
		REFERENCES Users(ID)
		ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT fk_requests_assignedreceiver
		FOREIGN KEY (AssignedReceiver)
		REFERENCES Users(ID)
		ON DELETE SET NULL ON UPDATE CASCADE
);

INDEX ind_requests_queueid (QueueID ASC, Created ASC);
