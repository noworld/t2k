UPDATE PUBLIC.T2K_INVITE SET REGISTRATION_TIMESTAMP = NULL WHERE ID = 1;
COMMIT;
SELECT * FROM PUBLIC.T2K_INVITE;
SELECT * FROM PUBLIC.T2K_ROLE;
SELECT ID, USER_ID, FIRST_NAME, LAST_NAME, EMAIL_ADDRESS, LAST_LOGIN_TIMESTAMP FROM T2K_USER;

TRUNCATE TABLE PUBLIC.T2K_INVITE;
TRUNCATE TABLE PUBLIC.T2K_USER;
TRUNCATE TABLE PUBLIC.T2K_ENUMERATED_VALUE;
DROP TABLE PUBLIC.T2K_ENUMERATED_VALUE;
