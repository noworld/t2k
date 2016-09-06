/*Create User Roles*/
INSERT INTO PUBLIC.T2K_ROLE VALUES (0,'newbie');
INSERT INTO PUBLIC.T2K_ROLE VALUES (1,'player');
INSERT INTO PUBLIC.T2K_ROLE VALUES (2,'creator');
INSERT INTO PUBLIC.T2K_ROLE VALUES (3,'admin');
INSERT INTO PUBLIC.T2K_ROLE VALUES (4,'superuser');
COMMIT;

/*Create Account Statuses*/
INSERT INTO PUBLIC.T2K_ACCOUNT_STATUS VALUES(0,'revoked');
INSERT INTO PUBLIC.T2K_ACCOUNT_STATUS VALUES(1,'suspended');
INSERT INTO PUBLIC.T2K_ACCOUNT_STATUS VALUES(2,'pending');
INSERT INTO PUBLIC.T2K_ACCOUNT_STATUS VALUES(3,'ok');
COMMIT;

INSERT INTO PUBLIC.T2K_INVITE (ID, EMAIL_ADDRESS, INVITE_TIMESTAMP, INVITE_TOKEN, REGISTRATION_TIMESTAMP)
	VALUES(1,'test@test.com', CURRENT_TIMESTAMP,'zigzag',CURRENT_TIMESTAMP);
COMMIT;

CHECKPOINT;