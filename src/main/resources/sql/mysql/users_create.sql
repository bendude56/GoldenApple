CREATE TABLE IF NOT EXISTS Users (
	ID BIGINT PRIMARY KEY AUTO_INCREMENT,
	Name VARCHAR(128) NOT NULL UNIQUE,
	UUID VARCHAR(36) CHARACTER SET ASCII COLLATE ascii_general_ci NOT NULL UNIQUE,
	Locale VARCHAR(128) NULL,
	ComplexCommands BOOLEAN NOT NULL,
	AutoLock BOOLEAN NOT NULL
) ENGINE=InnoDB