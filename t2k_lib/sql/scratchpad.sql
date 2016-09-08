UPDATE PUBLIC.T2K_INVITE SET REGISTRATION_TIMESTAMP = NULL WHERE ID = 1;
COMMIT;
SELECT * FROM PUBLIC.T2K_INVITE;
SELECT * FROM PUBLIC.T2K_ROLE;
SELECT * FROM PUBLIC.T2K_SKILL;
SELECT ID, USER_ID, FIRST_NAME, LAST_NAME, EMAIL_ADDRESS, LAST_LOGIN_TIMESTAMP FROM T2K_USER;

TRUNCATE TABLE PUBLIC.T2K_INVITE;
TRUNCATE TABLE PUBLIC.T2K_USER;
TRUNCATE TABLE PUBLIC.T2K_ENUMERATED_VALUE;
DROP TABLE PUBLIC.T2K_ENUMERATED_VALUE;
DROP TABLE PUBLIC.T2K_ARMY;
DROP TABLE PUBLIC.T2K_NATIONALITY;
DROP TABLE PUBLIC.T2K_SKILL;
DROP TABLE PUBLIC.T2K_DEFAULT_SKILL;
DROP TABLE PUBLIC.T2K_NATIVE_LANGUAGE;
DROP TABLE PUBLIC.T2K_NATIONALITY_T2KNATIVELANGUAGE;

select * from T2K_ENUMERATED_VALUE where ID >= 57 and ID <= 123;
select CONCAT('REPLACE(', ID+1, ',''Language(', ENUM_VALUE ,')'',5,3,',ID,');') from T2K_ENUMERATED_VALUE where ID >= 57 and ID <= 123;
select CONCAT('INSERT INTO PUBLIC.T2K_NATIVE_LANGUAGE (ID,TARGET_NUMBER,LANGUAGESKILL_ID) VALUES(', ID-58,',20,',ID,'); /*',NAME,'*/') from T2K_SKILL where ID >= 58 and ID <= 124;

SELECT t0.id, t0.ARMY_ID, t1.id, t1.enum_value, t1.ordinal, t1.value_group, 
        t0.name 
    FROM t2k_nationality t0 LEFT OUTER JOIN t2k_enumerated_value t1 ON 
        t0.FACTION_ID = t1.id 