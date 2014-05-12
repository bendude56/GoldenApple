CREATE TABLE IF NOT EXISTS Groups (
	ID INTEGER PRIMARY KEY AUTOINCREMENT,
	Name VARCHAR(128) NOT NULL UNIQUE,
	Priority INTEGER NOT NULL,
	ChatColor CHAR(1) NULL,
	Prefix VARCHAR(32) NULL
);
