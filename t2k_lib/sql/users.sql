UPDATE PUBLIC.T2K_INVITE SET REGISTRATION_TIMESTAMP = NULL WHERE ID = 1;
COMMIT;
SELECT * FROM PUBLIC.T2K_INVITE;
SELECT ID, USER_ID, FIRST_NAME, LAST_NAME, EMAIL_ADDRESS, LAST_LOGIN_TIMESTAMP FROM T2K_USER;