----------------------
-- Delete data prior
-- to insertion
----------------------
DROP TABLE PUBLIC.T2K_SKILL_LEVEL;
DROP TABLE PUBLIC.T2K_SKILL_OPTION;
DROP TABLE PUBLIC.T2K_SKILL_PACKAGE;
DROP TABLE PUBLIC.T2K_SKILL_PACKAGE_T2K_SKILL_OPTION;
DROP TABLE PUBLIC.T2K_CAREER;
DROP TABLE PUBLIC.T2K_CAREER_T2K_NATIONALITY;

TRUNCATE TABLE PUBLIC.T2K_SKILL_LEVEL;
TRUNCATE TABLE PUBLIC.T2K_SKILL_OPTION;
TRUNCATE TABLE PUBLIC.T2K_SKILL_PACKAGE;
TRUNCATE TABLE PUBLIC.T2K_SKILL_PACKAGE_T2K_SKILL_OPTION;
TRUNCATE TABLE PUBLIC.T2K_CAREER;
TRUNCATE TABLE PUBLIC.T2K_CAREER_T2K_NATIONALITY

DROP PROCEDURE IF EXISTS create_career;
DROP PROCEDURE IF EXISTS create_mil_career;
DROP PROCEDURE IF EXISTS create_skill_option;
DROP PROCEDURE IF EXISTS create_skill_package;
DROP PROCEDURE IF EXISTS create_skill_level;

-----------------------------------
-- Create stored procs for inserts
-----------------------------------

--civilian
--DROP PROCEDURE IF EXISTS create_career;
CREATE PROCEDURE create_career(career_name VARCHAR(255), career_group INT) 
MODIFIES SQL DATA
BEGIN ATOMIC
    DECLARE id_val INT DEFAULT 0;
	SET id_val = SELECT MAX(ID) FROM PUBLIC.T2K_CAREER;
	
	IF id_val IS NULL THEN
		SET id_val = 0;
	ELSE 
		SET id_val = id_val + 1;
	END IF;
	
	INSERT INTO PUBLIC.T2K_CAREER (ID,NAME,CAREERGROUP_ID,MILITARYORG_ID,MILITARYBRANCH_ID) VALUES(id_val,career_name,career_group,NULL,NULL);
	
	insert_nma: FOR SELECT ID FROM PUBLIC.T2K_NATIONALITY DO
		INSERT INTO PUBLIC.T2K_CAREER_T2K_NATIONALITY (T2KCAREER_ID,NATIONALITY_ID) VALUES(id_val,ID);	
	END FOR insert_nma;
END

--military
--DROP PROCEDURE IF EXISTS create_mil_career;
CREATE PROCEDURE create_mil_career(career_name VARCHAR(255), career_group INT, mil_org INT, mil_branch INT, nationality INT) 
MODIFIES SQL DATA
BEGIN ATOMIC

	DECLARE id_val INT DEFAULT 0;
	DECLARE existant_career INT DEFAULT 0;
	SET existant_career = SELECT ID FROM PUBLIC.T2K_CAREER c WHERE c.NAME = career_name AND c.MILITARYORG_ID = mil_org AND c.MILITARYBRANCH_ID = mil_branch;
	
	IF existant_career IS NULL THEN
			
		SET id_val = SELECT MAX(ID) FROM PUBLIC.T2K_CAREER;
		
		IF id_val IS NULL THEN
			SET id_val = 0;
		ELSE 
			SET id_val = id_val + 1;
		END IF;
	
		INSERT INTO PUBLIC.T2K_CAREER (ID,NAME,CAREERGROUP_ID,MILITARYORG_ID,MILITARYBRANCH_ID) VALUES(id_val,career_name,career_group,mil_org,mil_branch);
		INSERT INTO PUBLIC.T2K_CAREER_T2K_NATIONALITY (T2KCAREER_ID,NATIONALITY_ID) VALUES(id_val,nationality);
	ELSE
		INSERT INTO PUBLIC.T2K_CAREER_T2K_NATIONALITY (T2KCAREER_ID,NATIONALITY_ID) VALUES(existant_career,nationality);
	END IF;
				
END

--skill level
--DROP PROCEDURE IF EXISTS create_skill_level;
CREATE PROCEDURE create_skill_level(INOUT skill_level_id INT, IN skill_name VARCHAR(255), IN skill_level INT)
MODIFIES SQL DATA
BEGIN ATOMIC
    DECLARE id_val INT DEFAULT 0;
    DECLARE skill_id INT DEFAULT 0;
    
	SET id_val = SELECT MAX(ID) FROM PUBLIC.T2K_SKILL_LEVEL;
	
	IF id_val IS NULL THEN
		SET id_val = 0;
	ELSE 
		SET id_val = id_val + 1;
	END IF;
	
	SET skill_id = SELECT ID FROM PUBLIC.T2K_SKILL s WHERE s.NAME = skill_name;
	
	IF skill_id IS NOT NULL THEN
		INSERT INTO PUBLIC.T2K_SKILL_LEVEL (ID,LEVEL,SKILL_ID) VALUES (id_val,skill_level,skill_id);
		SET skill_level_id = id_val;
	END IF;

END

-- skill option
--DROP PROCEDURE IF EXISTS create_skill_option;
CREATE PROCEDURE create_skill_option(INOUT skill_option_id INT, skill_name VARCHAR(255), skill_level INT, optional BOOLEAN) 
MODIFIES SQL DATA
BEGIN ATOMIC
    DECLARE id_val INT DEFAULT 0;
    DECLARE skill_level_id INT DEFAULT 0;
    
	SET id_val = SELECT MAX(ID) FROM PUBLIC.T2K_SKILL_OPTION;
	
	IF id_val IS NULL THEN
		SET id_val = 0;
	ELSE 
		SET id_val = id_val + 1;
	END IF;
	
	SET skill_level_id = NULL;
	
	CALL create_skill_level(skill_level_id, skill_name, skill_level);
	
	IF skill_level_id IS NOT NULL THEN
		INSERT INTO PUBLIC.T2K_SKILL_OPTION (ID,OPTIONAL,SKILLLEVEL_ID) VALUES (id_val,optional,skill_level_id);	
		SET skill_option_id = id_val;
	END IF;

END

-- single skill package
--DROP PROCEDURE IF EXISTS create_skill_package;
CREATE PROCEDURE create_skill_package(INOUT skill_package_id INT, skill_package_name VARCHAR(255)) 
MODIFIES SQL DATA
BEGIN ATOMIC

	SET skill_package_id = SELECT MAX(ID) FROM PUBLIC.T2K_SKILL_PACKAGE;
	
	IF skill_package_id IS NULL THEN
		SET skill_package_id = 0;
	ELSE 
		SET skill_package_id = skill_package_id + 1;
	END IF;
	
	IF skill_package_id IS NOT NULL THEN
		INSERT INTO PUBLIC.T2K_SKILL_PACKAGE (ID,NAME) VALUES (skill_package_id,skill_package_name);	
	END IF;

END

-- SKILL PACKAGES
-- DROP PROCEDURE IF EXISTS create_skill_packages;
-- TRUNCATE TABLE PUBLIC.T2K_SKILL_PACKAGE;
CREATE PROCEDURE create_skill_packages(skill_package_name VARCHAR(255))
MODIFIES SQL DATA
BEGIN ATOMIC
	--Skill Option ID
	DECLARE soid INT;
	-- Skill Package ID
	DECLARE spid INT DEFAULT 0;
	
	SET soid = null;
	SET spid = null;
	
	CALL create_skill_option(soid, 'Autogun', 0, FALSE);

	--Skill Package ID
	IF soid IS NOT NULL THEN
		CALL create_skill_package(spid,skill_package_name);
		INSERT INTO PUBLIC.T2K_SKILL_PACKAGE_T2K_SKILL_OPTION (T2KSKILLPACKAGE_ID,SKILLOPTIONS_ID) VALUES(spid,soid);
	END IF;
END


--------------------
-- Career Tables 
--------------------

-- Skill Levels

-- Skill Options

-- Skill Packages
CALL create_skill_packages('US Army Basic Training');
COMMIT;



--------------------
-- MILITARY CAREERS
--------------------

--
-- USA
--

-- US Army
CALL create_mil_career('Armor',143,150,0,);
CALL create_mil_career('Armored Cavalry',143,150,0,);
CALL create_mil_career('Artillery',143,150,0,);
CALL create_mil_career('Aircraft Mechanic',143,150,0,);
CALL create_mil_career('Combat Engineer',143,150,0,);
CALL create_mil_career('Infantry',143,150,0,);
CALL create_mil_career('Airborne',143,150,0,);
CALL create_mil_career('Mountain Infantry',143,150,0,);
CALL create_mil_career('Ranger',143,150,0,);
CALL create_mil_career('Medic',143,150,0,);
CALL create_mil_career('Intelligence',143,150,0,);
CALL create_mil_career('Special Forces',143,150,0,);
CALL create_mil_career('Support',143,150,0,);

CALL create_mil_career('Armor',143,151,0,);
CALL create_mil_career('Armored Cavalry',143,151,0,);
CALL create_mil_career('Artillery',143,151,0,);
CALL create_mil_career('Helicopter Pilot',143,151,0,);
CALL create_mil_career('Combat Engineer',143,151,0,);
CALL create_mil_career('Infantry',143,151,0,);
CALL create_mil_career('Airborne',143,151,0,);
CALL create_mil_career('Mountain Infantry',143,151,0,);
CALL create_mil_career('Ranger',143,151,0,);
CALL create_mil_career('Nurse',143,151,0,);
CALL create_mil_career('Doctor',143,151,0,);
CALL create_mil_career('Intelligence',143,151,0,);
CALL create_mil_career('Special Forces',143,151,0,);
CALL create_mil_career('Support',143,151,0,);

-- USMC
CALL create_mil_career('Armor',143,150,1,);
CALL create_mil_career('Armored Cavalry',143,150,1,);
CALL create_mil_career('Artillery',143,150,1,);
CALL create_mil_career('Aircraft Mechanic',143,150,1,);
CALL create_mil_career('Combat Engineer',143,150,1,);
CALL create_mil_career('Infantry',143,150,1,);
CALL create_mil_career('Mountain Infantry',143,150,1,);
CALL create_mil_career('Medic',143,150,1,);
CALL create_mil_career('Intelligence',143,150,1,);
CALL create_mil_career('Support',143,150,1,);
CALL create_mil_career('Force Recon',143,150,1,);
CALL create_mil_career('Sniper',143,150,1,);

CALL create_mil_career('Armor',143,151,1,);
CALL create_mil_career('Armored Cavalry',143,151,1,);
CALL create_mil_career('Artillery',143,151,1,);
CALL create_mil_career('Helicopter Pilot',143,151,1,);
CALL create_mil_career('Combat Engineer',143,151,1,);
CALL create_mil_career('Infantry',143,151,1,);
CALL create_mil_career('Airborne',143,151,1,);
CALL create_mil_career('Mountain Infantry',143,151,1,);
CALL create_mil_career('Ranger',143,151,1,);
CALL create_mil_career('Intelligence',143,151,1,);
CALL create_mil_career('Support',143,151,1,);
CALL create_mil_career('Force Recon',143,151,1,);

-- US Navy
CALL create_mil_career('Seaman',143,150,2,);
CALL create_mil_career('Medic',143,150,2,);
CALL create_mil_career('SEAL',143,150,2,);

CALL create_mil_career('Naval Officer',143,151,2,);
CALL create_mil_career('Naval Aviator',143,151,2,);
CALL create_mil_career('Nurse',143,151,2,);
CALL create_mil_career('Doctor',143,151,2,);
CALL create_mil_career('SEAL',143,151,2,);

-- USAF
CALL create_mil_career('Airman',143,150,2,);
CALL create_mil_career('Medic',143,150,2,);
CALL create_mil_career('PJ',143,150,2,);

CALL create_mil_career('Pilot',143,151,2,);
CALL create_mil_career('Nurse',143,151,2,);
CALL create_mil_career('Doctor',143,151,2,);


--
-- Albania
--

-- Albanian Army
CALL create_mil_career('Armor',143,150,0,);
CALL create_mil_career('Armored Cavalry',143,150,0,);
CALL create_mil_career('Artillery',143,150,0,);
CALL create_mil_career('Aircraft Mechanic',143,150,0,);
CALL create_mil_career('Combat Engineer',143,150,0,);
CALL create_mil_career('Infantry',143,150,0,);
CALL create_mil_career('Medic',143,150,0,);
CALL create_mil_career('Intelligence',143,150,0,);
CALL create_mil_career('Support',143,150,0,);

CALL create_mil_career('Armor',143,151,0,);
CALL create_mil_career('Armored Cavalry',143,151,0,);
CALL create_mil_career('Artillery',143,151,0,);
CALL create_mil_career('Helicopter Pilot',143,151,0,);
CALL create_mil_career('Combat Engineer',143,151,0,);
CALL create_mil_career('Infantry',143,151,0,);
CALL create_mil_career('Nurse',143,151,0,);
CALL create_mil_career('Doctor',143,151,0,);
CALL create_mil_career('Intelligence',143,151,0,);
CALL create_mil_career('Support',143,151,0,);

-- Albanian Navy
CALL create_mil_career('Seaman',143,150,2,);
CALL create_mil_career('Medic',143,150,2,);

CALL create_mil_career('Naval Officer',143,151,2,);
CALL create_mil_career('Nurse',143,151,2,);
CALL create_mil_career('Doctor',143,151,2,);


-- Albanian Air Force
CALL create_mil_career('Airman',143,150,2,);
CALL create_mil_career('Medic',143,150,2,);

CALL create_mil_career('Pilot',143,151,2,);
CALL create_mil_career('Nurse',143,151,2,);
CALL create_mil_career('Doctor',143,151,2,);


--
-- Belgium
--

-- Belgian Army
CALL create_mil_career('Armor',143,150,0,);
CALL create_mil_career('Armored Cavalry',143,150,0,);
CALL create_mil_career('Artillery',143,150,0,);
CALL create_mil_career('Aircraft Mechanic',143,150,0,);
CALL create_mil_career('Combat Engineer',143,150,0,);
CALL create_mil_career('Infantry',143,150,0,);
CALL create_mil_career('Medic',143,150,0,);
CALL create_mil_career('Intelligence',143,150,0,);
CALL create_mil_career('Support',143,150,0,);
CALL create_mil_career('Commando',143,150,0,);
CALL create_mil_career('ESR',143,150,0,);

CALL create_mil_career('Armor',143,151,0,);
CALL create_mil_career('Armored Cavalry',143,151,0,);
CALL create_mil_career('Artillery',143,151,0,);
CALL create_mil_career('Helicopter Pilot',143,151,0,);
CALL create_mil_career('Combat Engineer',143,151,0,);
CALL create_mil_career('Infantry',143,151,0,);
CALL create_mil_career('Nurse',143,151,0,);
CALL create_mil_career('Doctor',143,151,0,);
CALL create_mil_career('Intelligence',143,151,0,);
CALL create_mil_career('Support',143,151,0,);
CALL create_mil_career('Commando',143,151,0,);
CALL create_mil_career('ESR',143,151,0,);

-- Belgian Navy
CALL create_mil_career('Seaman',143,150,2,);
CALL create_mil_career('Medic',143,150,2,);

CALL create_mil_career('Naval Officer',143,151,2,);
CALL create_mil_career('Nurse',143,151,2,);
CALL create_mil_career('Doctor',143,151,2,);


-- Belgian Air Force
CALL create_mil_career('Airman',143,150,2,);
CALL create_mil_career('Medic',143,150,2,);

CALL create_mil_career('Pilot',143,151,2,);
CALL create_mil_career('Nurse',143,151,2,);
CALL create_mil_career('Doctor',143,151,2,);


--
-- Bulgaria
--

-- Bulgarian Army
CALL create_mil_career('Armor',143,150,0,);
CALL create_mil_career('Armored Cavalry',143,150,0,);
CALL create_mil_career('Artillery',143,150,0,);
CALL create_mil_career('Aircraft Mechanic',143,150,0,);
CALL create_mil_career('Combat Engineer',143,150,0,);
CALL create_mil_career('Infantry',143,150,0,);
CALL create_mil_career('Medic',143,150,0,);
CALL create_mil_career('Intelligence',143,150,0,);
CALL create_mil_career('Support',143,150,0,);

CALL create_mil_career('Armor',143,151,0,);
CALL create_mil_career('Armored Cavalry',143,151,0,);
CALL create_mil_career('Artillery',143,151,0,);
CALL create_mil_career('Helicopter Pilot',143,151,0,);
CALL create_mil_career('Combat Engineer',143,151,0,);
CALL create_mil_career('Infantry',143,151,0,);
CALL create_mil_career('Nurse',143,151,0,);
CALL create_mil_career('Doctor',143,151,0,);
CALL create_mil_career('Intelligence',143,151,0,);
CALL create_mil_career('Support',143,151,0,);

-- Bulgarian Navy
CALL create_mil_career('Seaman',143,150,2,);
CALL create_mil_career('Medic',143,150,2,);

CALL create_mil_career('Naval Officer',143,151,2,);
CALL create_mil_career('Nurse',143,151,2,);
CALL create_mil_career('Doctor',143,151,2,);

-- Bulgarian Air Force
CALL create_mil_career('Airman',143,150,2,);
CALL create_mil_career('Medic',143,150,2,);

CALL create_mil_career('Pilot',143,151,2,);
CALL create_mil_career('Nurse',143,151,2,);
CALL create_mil_career('Doctor',143,151,2,);


--
-- Canada
--

-- Canadian Army
CALL create_mil_career('Armor',143,150,0,);
CALL create_mil_career('Armored Cavalry',143,150,0,);
CALL create_mil_career('Artillery',143,150,0,);
CALL create_mil_career('Aircraft Mechanic',143,150,0,);
CALL create_mil_career('Combat Engineer',143,150,0,);
CALL create_mil_career('Infantry',143,150,0,);
CALL create_mil_career('Medic',143,150,0,);
CALL create_mil_career('Intelligence',143,150,0,);
CALL create_mil_career('Support',143,150,0,);
CALL create_mil_career('Airborne Commando',143,150,0,);

CALL create_mil_career('Armor',143,151,0,);
CALL create_mil_career('Armored Cavalry',143,151,0,);
CALL create_mil_career('Artillery',143,151,0,);
CALL create_mil_career('Helicopter Pilot',143,151,0,);
CALL create_mil_career('Combat Engineer',143,151,0,);
CALL create_mil_career('Infantry',143,151,0,);
CALL create_mil_career('Nurse',143,151,0,);
CALL create_mil_career('Doctor',143,151,0,);
CALL create_mil_career('Intelligence',143,151,0,);
CALL create_mil_career('Support',143,151,0,);
CALL create_mil_career('Airborne Commando',143,151,0,);

-- Canadian Navy
CALL create_mil_career('Seaman',143,150,2,);
CALL create_mil_career('Medic',143,150,2,);

CALL create_mil_career('Naval Officer',143,151,2,);
CALL create_mil_career('Nurse',143,151,2,);
CALL create_mil_career('Doctor',143,151,2,);

-- Canadian Air Force
CALL create_mil_career('Airman',143,150,2,);
CALL create_mil_career('Medic',143,150,2,);

CALL create_mil_career('Pilot',143,151,2,);
CALL create_mil_career('Nurse',143,151,2,);
CALL create_mil_career('Doctor',143,151,2,);


--
-- Czechoslovakia
--

-- Czechoslovakian Army
CALL create_mil_career('Armor',143,150,0,);
CALL create_mil_career('Armored Cavalry',143,150,0,);
CALL create_mil_career('Artillery',143,150,0,);
CALL create_mil_career('Aircraft Mechanic',143,150,0,);
CALL create_mil_career('Combat Engineer',143,150,0,);
CALL create_mil_career('Motor Rifles',143,150,0,);
CALL create_mil_career('Medic',143,150,0,);
CALL create_mil_career('Intelligence',143,150,0,);
CALL create_mil_career('Support',143,150,0,);
CALL create_mil_career('Airborne',143,150,0,);
CALL create_mil_career('Airborne Special Operations',143,150,0,);

CALL create_mil_career('Armor',143,151,0,);
CALL create_mil_career('Armored Cavalry',143,151,0,);
CALL create_mil_career('Artillery',143,151,0,);
CALL create_mil_career('Helicopter Pilot',143,151,0,);
CALL create_mil_career('Combat Engineer',143,151,0,);
CALL create_mil_career('Motor Rifles',143,151,0,);
CALL create_mil_career('Nurse',143,151,0,);
CALL create_mil_career('Doctor',143,151,0,);
CALL create_mil_career('Intelligence',143,151,0,);
CALL create_mil_career('Support',143,151,0,);
CALL create_mil_career('Airborne',143,151,0,);
CALL create_mil_career('Airborne Special Operations',143,151,0,);

-- Czechoslovakian Navy
CALL create_mil_career('Seaman',143,150,2,);
CALL create_mil_career('Medic',143,150,2,);

CALL create_mil_career('Naval Officer',143,151,2,);
CALL create_mil_career('Nurse',143,151,2,);
CALL create_mil_career('Doctor',143,151,2,);

-- Czechoslovakian Air Force
CALL create_mil_career('Airman',143,150,2,);
CALL create_mil_career('Medic',143,150,2,);

CALL create_mil_career('Pilot',143,151,2,);
CALL create_mil_career('Nurse',143,151,2,);
CALL create_mil_career('Doctor',143,151,2,);


--
-- Denmark
--

-- Danish Army
CALL create_mil_career('Armor',143,150,0,);
CALL create_mil_career('Armored Cavalry',143,150,0,);
CALL create_mil_career('Artillery',143,150,0,);
CALL create_mil_career('Aircraft Mechanic',143,150,0,);
CALL create_mil_career('Combat Engineer',143,150,0,);
CALL create_mil_career('Motor Rifles',143,150,0,);
CALL create_mil_career('Medic',143,150,0,);
CALL create_mil_career('Intelligence',143,150,0,);
CALL create_mil_career('Support',143,150,0,);
CALL create_mil_career('Jager',143,150,0,);

CALL create_mil_career('Armor',143,151,0,);
CALL create_mil_career('Armored Cavalry',143,151,0,);
CALL create_mil_career('Artillery',143,151,0,);
CALL create_mil_career('Helicopter Pilot',143,151,0,);
CALL create_mil_career('Combat Engineer',143,151,0,);
CALL create_mil_career('Motor Rifles',143,151,0,);
CALL create_mil_career('Nurse',143,151,0,);
CALL create_mil_career('Doctor',143,151,0,);
CALL create_mil_career('Intelligence',143,151,0,);
CALL create_mil_career('Support',143,151,0,);
CALL create_mil_career('Jager',143,151,0,);

-- Danish Navy
CALL create_mil_career('Seaman',143,150,2,);
CALL create_mil_career('Medic',143,150,2,);
CALL create_mil_career('Frogman',143,150,2,);

CALL create_mil_career('Naval Officer',143,151,2,);
CALL create_mil_career('Nurse',143,151,2,);
CALL create_mil_career('Doctor',143,151,2,);
CALL create_mil_career('Frogman',143,151,2,);

-- Danish Air Force
CALL create_mil_career('Airman',143,150,2,);
CALL create_mil_career('Medic',143,150,2,);

CALL create_mil_career('Pilot',143,151,2,);
CALL create_mil_career('Nurse',143,151,2,);
CALL create_mil_career('Doctor',143,151,2,);


--
-- Finland
--

-- Finnish Army
CALL create_mil_career('Armor',143,150,0,);
CALL create_mil_career('Armored Cavalry',143,150,0,);
CALL create_mil_career('Artillery',143,150,0,);
CALL create_mil_career('Aircraft Mechanic',143,150,0,);
CALL create_mil_career('Combat Engineer',143,150,0,);
CALL create_mil_career('Motor Rifles',143,150,0,);
CALL create_mil_career('Medic',143,150,0,);
CALL create_mil_career('Intelligence',143,150,0,);
CALL create_mil_career('Support',143,150,0,);
CALL create_mil_career('Sissi',143,150,0,);

CALL create_mil_career('Armor',143,151,0,);
CALL create_mil_career('Armored Cavalry',143,151,0,);
CALL create_mil_career('Artillery',143,151,0,);
CALL create_mil_career('Helicopter Pilot',143,151,0,);
CALL create_mil_career('Combat Engineer',143,151,0,);
CALL create_mil_career('Motor Rifles',143,151,0,);
CALL create_mil_career('Nurse',143,151,0,);
CALL create_mil_career('Doctor',143,151,0,);
CALL create_mil_career('Intelligence',143,151,0,);
CALL create_mil_career('Support',143,151,0,);
CALL create_mil_career('Sissi',143,151,0,);

-- Finnish Marines
CALL create_mil_career('Armored Cavalry',143,150,1,);
CALL create_mil_career('Combat Engineer',143,150,1,);
CALL create_mil_career('Marine Commando',143,150,1,);
CALL create_mil_career('Artillery',143,150,1,);
CALL create_mil_career('Medic',143,150,1,);
CALL create_mil_career('Intelligence',143,150,1,);

CALL create_mil_career('Armored Cavalry',143,151,1,);
CALL create_mil_career('Combat Engineer',143,151,1,);
CALL create_mil_career('Marine Commando',143,151,1,);
CALL create_mil_career('Artillery',143,151,1,);
CALL create_mil_career('Intelligence',143,151,1,);

-- Finnish Navy
CALL create_mil_career('Seaman',143,150,2,);
CALL create_mil_career('Medic',143,150,2,);

CALL create_mil_career('Naval Officer',143,151,2,);
CALL create_mil_career('Nurse',143,151,2,);
CALL create_mil_career('Doctor',143,151,2,);

-- Finnish Air Force
CALL create_mil_career('Airman',143,150,2,);
CALL create_mil_career('Medic',143,150,2,);

CALL create_mil_career('Pilot',143,151,2,);
CALL create_mil_career('Nurse',143,151,2,);
CALL create_mil_career('Doctor',143,151,2,);


--
-- France
--

-- French Army
CALL create_mil_career('Armor',143,150,0,);
CALL create_mil_career('Armored Cavalry',143,150,0,);
CALL create_mil_career('Artillery',143,150,0,);
CALL create_mil_career('Aircraft Mechanic',143,150,0,);
CALL create_mil_career('Combat Engineer',143,150,0,);
CALL create_mil_career('Infantry',143,150,0,);
CALL create_mil_career('Medic',143,150,0,);
CALL create_mil_career('Intelligence',143,150,0,);
CALL create_mil_career('Support',143,150,0,);
CALL create_mil_career('Chasseur Alpin',143,150,0,);
CALL create_mil_career('Parachutist',143,150,0,);

CALL create_mil_career('Armor',143,151,0,);
CALL create_mil_career('Armored Cavalry',143,151,0,);
CALL create_mil_career('Artillery',143,151,0,);
CALL create_mil_career('Helicopter Pilot',143,151,0,);
CALL create_mil_career('Combat Engineer',143,151,0,);
CALL create_mil_career('Infantry',143,151,0,);
CALL create_mil_career('Nurse',143,151,0,);
CALL create_mil_career('Doctor',143,151,0,);
CALL create_mil_career('Intelligence',143,151,0,);
CALL create_mil_career('Support',143,151,0,);
CALL create_mil_career('Chasseur Alpin',143,151,0,);
CALL create_mil_career('Parachutist',143,151,0,);

-- French Marines
CALL create_mil_career('Armor',143,150,1,);
CALL create_mil_career('Armored Cavalry',143,150,1,);
CALL create_mil_career('Artillery',143,150,1,);
CALL create_mil_career('Aircraft Mechanic',143,150,1,);
CALL create_mil_career('Combat Engineer',143,150,1,);
CALL create_mil_career('Infantry',143,150,1,);
CALL create_mil_career('Medic',143,150,1,);
CALL create_mil_career('Intelligence',143,150,1,);
CALL create_mil_career('Support',143,150,1,);
INSERT INTO PUBLIC.T2K_CAREER (ID,NAME,CAREERGROUP_ID,MILITARYORG_ID,MILITARYBRANCH_ID) VALUES(,'Fusiliers-Marins Commandos',143,150,1);

CALL create_mil_career('Armor',143,151,1,);
CALL create_mil_career('Armored Cavalry',143,151,1,);
CALL create_mil_career('Artillery',143,151,1,);
CALL create_mil_career('Helicopter Pilot',143,151,1,);
CALL create_mil_career('Combat Engineer',143,151,1,);
CALL create_mil_career('Infantry',143,151,1,);
CALL create_mil_career('Intelligence',143,151,1,);
CALL create_mil_career('Support',143,151,1,);
INSERT INTO PUBLIC.T2K_CAREER (ID,NAME,CAREERGROUP_ID,MILITARYORG_ID,MILITARYBRANCH_ID) VALUES(,'Fusiliers-Marins Commandos)',143,151,1);

-- French Navy
CALL create_mil_career('Seaman',143,150,2,);
CALL create_mil_career('Medic',143,150,2,);

CALL create_mil_career('Naval Officer',143,151,2,);
CALL create_mil_career('Naval Aviator',143,151,2,);
CALL create_mil_career('Nurse',143,151,2,);
CALL create_mil_career('Doctor',143,151,2,);

-- French Air Force
CALL create_mil_career('Airman',143,150,2,);
CALL create_mil_career('Medic',143,150,2,);

CALL create_mil_career('Pilot',143,151,2,);
CALL create_mil_career('Nurse',143,151,2,);
CALL create_mil_career('Doctor',143,151,2,);


--
-- Germany
--

-- Germany Army
CALL create_mil_career('Armor',143,150,0,);
CALL create_mil_career('Armored Cavalry',143,150,0,);
CALL create_mil_career('Artillery',143,150,0,);
CALL create_mil_career('Aircraft Mechanic',143,150,0,);
CALL create_mil_career('Combat Engineer',143,150,0,);
CALL create_mil_career('Panzergrenadier',143,150,0,);
CALL create_mil_career('Jager',143,150,0,);
CALL create_mil_career('Medic',143,150,0,);
CALL create_mil_career('Intelligence',143,150,0,);
CALL create_mil_career('Support',143,150,0,);
CALL create_mil_career('Gebirgsjager',143,150,0,);
CALL create_mil_career('Fallschirmjager',143,150,0,);
CALL create_mil_career('Fernspahtruppen',143,150,0,);

CALL create_mil_career('Armor',143,151,0,);
CALL create_mil_career('Armored Cavalry',143,151,0,);
CALL create_mil_career('Artillery',143,151,0,);
CALL create_mil_career('Helicopter Pilot',143,151,0,);
CALL create_mil_career('Combat Engineer',143,151,0,);
CALL create_mil_career('Panzergrenadier',143,151,0,);
CALL create_mil_career('Jager',143,151,0,);
CALL create_mil_career('Nurse',143,151,0,);
CALL create_mil_career('Doctor',143,151,0,);
CALL create_mil_career('Intelligence',143,151,0,);
CALL create_mil_career('Support',143,151,0,);
CALL create_mil_career('Gebirgsjager',143,151,0,);
CALL create_mil_career('Fallschirmjager',143,151,0,);
CALL create_mil_career('Fernspahtruppen',143,151,0,);

-- German Navy
CALL create_mil_career('Seaman',143,150,2,);
CALL create_mil_career('Medic',143,150,2,);

CALL create_mil_career('Naval Officer',143,151,2,);
CALL create_mil_career('Naval Aviator',143,151,2,);
CALL create_mil_career('Nurse',143,151,2,);
CALL create_mil_career('Doctor',143,151,2,);

-- German Air Force
CALL create_mil_career('Airman',143,150,2,);
CALL create_mil_career('Medic',143,150,2,);

CALL create_mil_career('Pilot',143,151,2,);
CALL create_mil_career('Nurse',143,151,2,);
CALL create_mil_career('Doctor',143,151,2,);


--
-- Greece
--

-- Greek Army
CALL create_mil_career('Armor',143,150,0,);
CALL create_mil_career('Armored Cavalry',143,150,0,);
CALL create_mil_career('Artillery',143,150,0,);
CALL create_mil_career('Aircraft Mechanic',143,150,0,);
CALL create_mil_career('Combat Engineer',143,150,0,);
CALL create_mil_career('Infantry',143,150,0,);
CALL create_mil_career('Commando',143,150,0,);
CALL create_mil_career('Medic',143,150,0,);
CALL create_mil_career('Intelligence',143,150,0,);
CALL create_mil_career('Support',143,150,0,);
CALL create_mil_career('Paratrooper',143,150,0,);
CALL create_mil_career('Special Raider Forces',143,150,0,);

CALL create_mil_career('Armor',143,151,0,);
CALL create_mil_career('Armored Cavalry',143,151,0,);
CALL create_mil_career('Artillery',143,151,0,);
CALL create_mil_career('Helicopter Pilot',143,151,0,);
CALL create_mil_career('Combat Engineer',143,151,0,);
CALL create_mil_career('Infantry',143,151,0,);
CALL create_mil_career('Commando',143,151,0,);
CALL create_mil_career('Nurse',143,151,0,);
CALL create_mil_career('Doctor',143,151,0,);
CALL create_mil_career('Intelligence',143,151,0,);
CALL create_mil_career('Support',143,151,0,);
CALL create_mil_career('Paratrooper',143,151,0,);
CALL create_mil_career('Special Raider Forces',143,151,0,);

-- Greek Marines
CALL create_mil_career('Armor',143,150,1,);
CALL create_mil_career('Armored Cavalry',143,150,1,);
CALL create_mil_career('Artillery',143,150,1,);
CALL create_mil_career('Aircraft Mechanic',143,150,1,);
CALL create_mil_career('Combat Engineer',143,150,1,);
CALL create_mil_career('Infantry',143,150,1,);
CALL create_mil_career('Medic',143,150,1,);
CALL create_mil_career('Intelligence',143,150,1,);
CALL create_mil_career('Support',143,150,1,);

CALL create_mil_career('Armor',143,151,1,);
CALL create_mil_career('Armored Cavalry',143,151,1,);
CALL create_mil_career('Artillery',143,151,1,);
CALL create_mil_career('Helicopter Pilot',143,151,1,);
CALL create_mil_career('Combat Engineer',143,151,1,);
CALL create_mil_career('Infantry',143,151,1,);
CALL create_mil_career('Intelligence',143,151,1,);
CALL create_mil_career('Support',143,151,1,);

-- Greek Navy
CALL create_mil_career('Seaman',143,150,2,);
CALL create_mil_career('Medic',143,150,2,);

CALL create_mil_career('Naval Officer',143,151,2,);
CALL create_mil_career('Naval Aviator',143,151,2,);
CALL create_mil_career('Nurse',143,151,2,);
CALL create_mil_career('Doctor',143,151,2,);

-- Greek Air Force
CALL create_mil_career('Airman',143,150,2,);
CALL create_mil_career('Medic',143,150,2,);

CALL create_mil_career('Pilot',143,151,2,);
CALL create_mil_career('Nurse',143,151,2,);
CALL create_mil_career('Doctor',143,151,2,);


--
-- Hungary
--

-- Hungarian Army
CALL create_mil_career('Armor',143,150,0,);
CALL create_mil_career('Armored Cavalry',143,150,0,);
CALL create_mil_career('Artillery',143,150,0,);
CALL create_mil_career('Aircraft Mechanic',143,150,0,);
CALL create_mil_career('Combat Engineer',143,150,0,);
CALL create_mil_career('Infantry',143,150,0,);
CALL create_mil_career('Medic',143,150,0,);
CALL create_mil_career('Intelligence',143,150,0,);
CALL create_mil_career('Support',143,150,0,);
CALL create_mil_career('Airborne',143,150,0,);

CALL create_mil_career('Armor',143,151,0,);
CALL create_mil_career('Armored Cavalry',143,151,0,);
CALL create_mil_career('Artillery',143,151,0,);
CALL create_mil_career('Helicopter Pilot',143,151,0,);
CALL create_mil_career('Combat Engineer',143,151,0,);
CALL create_mil_career('Infantry',143,151,0,);
CALL create_mil_career('Nurse',143,151,0,);
CALL create_mil_career('Doctor',143,151,0,);
CALL create_mil_career('Intelligence',143,151,0,);
CALL create_mil_career('Support',143,151,0,);
CALL create_mil_career('Airborne',143,151,0,);

-- Hungarian Navy
CALL create_mil_career('Seaman',143,150,2,);
CALL create_mil_career('Medic',143,150,2,);

CALL create_mil_career('Naval Officer',143,151,2,);
CALL create_mil_career('Nurse',143,151,2,);
CALL create_mil_career('Doctor',143,151,2,);

-- Hungarian Air Force
CALL create_mil_career('Airman',143,150,2,);
CALL create_mil_career('Medic',143,150,2,);

CALL create_mil_career('Pilot',143,151,2,);
CALL create_mil_career('Nurse',143,151,2,);
CALL create_mil_career('Doctor',143,151,2,);


--
-- Italy
--

-- Italian Army
CALL create_mil_career('Armor',143,150,0,);
CALL create_mil_career('Armored Cavalry',143,150,0,);
CALL create_mil_career('Artillery',143,150,0,);
CALL create_mil_career('Aircraft Mechanic',143,150,0,);
CALL create_mil_career('Combat Engineer',143,150,0,);
CALL create_mil_career('Infantry',143,150,0,);
CALL create_mil_career('Medic',143,150,0,);
CALL create_mil_career('Intelligence',143,150,0,);
CALL create_mil_career('Support',143,150,0,);
CALL create_mil_career('Alpini',143,150,0,);
CALL create_mil_career('Paracadutisti',143,150,0,);
CALL create_mil_career('Paracadutisti Incusore',143,150,0,);

CALL create_mil_career('Armor',143,151,0,);
CALL create_mil_career('Armored Cavalry',143,151,0,);
CALL create_mil_career('Artillery',143,151,0,);
CALL create_mil_career('Helicopter Pilot',143,151,0,);
CALL create_mil_career('Combat Engineer',143,151,0,);
CALL create_mil_career('Infantry',143,151,0,);
CALL create_mil_career('Nurse',143,151,0,);
CALL create_mil_career('Doctor',143,151,0,);
CALL create_mil_career('Intelligence',143,151,0,);
CALL create_mil_career('Support',143,151,0,);
CALL create_mil_career('Alpini',143,151,0,);
CALL create_mil_career('Paracadutisti',143,151,0,);
CALL create_mil_career('Paracadutisti Incusore',143,151,0,);

-- Italian Marines
CALL create_mil_career('Armor',143,150,1,);
CALL create_mil_career('Armored Cavalry',143,150,1,);
CALL create_mil_career('Artillery',143,150,1,);
CALL create_mil_career('Aircraft Mechanic',143,150,1,);
CALL create_mil_career('Combat Engineer',143,150,1,);
CALL create_mil_career('Lagunari',143,150,1,);
CALL create_mil_career('Medic',143,150,1,);
CALL create_mil_career('Intelligence',143,150,1,);
CALL create_mil_career('Support',143,150,1,);

CALL create_mil_career('Armor',143,151,1,);
CALL create_mil_career('Armored Cavalry',143,151,1,);
CALL create_mil_career('Artillery',143,151,1,);
CALL create_mil_career('Helicopter Pilot',143,151,1,);
CALL create_mil_career('Combat Engineer',143,151,1,);
CALL create_mil_career('Lagunari',143,151,1,);
CALL create_mil_career('Intelligence',143,151,1,);
CALL create_mil_career('Support',143,151,1,);

-- Italian Navy
CALL create_mil_career('Seaman',143,150,2,);
CALL create_mil_career('Medic',143,150,2,);
CALL create_mil_career('San Marcos',143,150,2,);

CALL create_mil_career('Naval Officer',143,151,2,);
CALL create_mil_career('Naval Aviator',143,151,2,);
CALL create_mil_career('Nurse',143,151,2,);
CALL create_mil_career('Doctor',143,151,2,);
CALL create_mil_career('San Marcos',143,151,2,);

-- Italian Air Force
CALL create_mil_career('Airman',143,150,2,);
CALL create_mil_career('Medic',143,150,2,);

CALL create_mil_career('Pilot',143,151,2,);
CALL create_mil_career('Nurse',143,151,2,);
CALL create_mil_career('Doctor',143,151,2,);


--
-- Ex-Yugoslav:
-- Serbia, Croatia, Slovenia,
-- Dalmatia, Montenegro, Macedonia
--

-- Yugoslav Army
CALL create_mil_career('Armor',143,150,0,);
CALL create_mil_career('Armored Cavalry',143,150,0,);
CALL create_mil_career('Artillery',143,150,0,);
CALL create_mil_career('Aircraft Mechanic',143,150,0,);
CALL create_mil_career('Combat Engineer',143,150,0,);
CALL create_mil_career('Infantry',143,150,0,);
CALL create_mil_career('Medic',143,150,0,);
CALL create_mil_career('Intelligence',143,150,0,);
CALL create_mil_career('Support',143,150,0,);
CALL create_mil_career('Mountain Infantry',143,150,0,);
CALL create_mil_career('Paratrooper',143,150,0,);

CALL create_mil_career('Armor',143,151,0,);
CALL create_mil_career('Armored Cavalry',143,151,0,);
CALL create_mil_career('Artillery',143,151,0,);
CALL create_mil_career('Helicopter Pilot',143,151,0,);
CALL create_mil_career('Combat Engineer',143,151,0,);
CALL create_mil_career('Infantry',143,151,0,);
CALL create_mil_career('Nurse',143,151,0,);
CALL create_mil_career('Doctor',143,151,0,);
CALL create_mil_career('Intelligence',143,151,0,);
CALL create_mil_career('Support',143,151,0,);
CALL create_mil_career('Mountain Infantry',143,151,0,);
CALL create_mil_career('Paratrooper',143,151,0,);

-- Yugoslav Marines
CALL create_mil_career('Armor',143,150,1,);
CALL create_mil_career('Armored Cavalry',143,150,1,);
CALL create_mil_career('Artillery',143,150,1,);
CALL create_mil_career('Aircraft Mechanic',143,150,1,);
CALL create_mil_career('Combat Engineer',143,150,1,);
CALL create_mil_career('Naval Infantry',143,150,1,);
CALL create_mil_career('Medic',143,150,1,);
CALL create_mil_career('Intelligence',143,150,1,);
CALL create_mil_career('Support',143,150,1,);

CALL create_mil_career('Armor',143,151,1,);
CALL create_mil_career('Armored Cavalry',143,151,1,);
CALL create_mil_career('Artillery',143,151,1,);
CALL create_mil_career('Helicopter Pilot',143,151,1,);
CALL create_mil_career('Combat Engineer',143,151,1,);
CALL create_mil_career('Naval Infantry',143,151,1,);
CALL create_mil_career('Intelligence',143,151,1,);
CALL create_mil_career('Support',143,151,1,);

-- Yugoslav Navy
CALL create_mil_career('Seaman',143,150,2,);
CALL create_mil_career('Medic',143,150,2,);

CALL create_mil_career('Naval Officer',143,151,2,);
CALL create_mil_career('Nurse',143,151,2,);
CALL create_mil_career('Doctor',143,151,2,);

-- Yugoslav Air Force
CALL create_mil_career('Airman',143,150,2,);
CALL create_mil_career('Medic',143,150,2,);

CALL create_mil_career('Pilot',143,151,2,);
CALL create_mil_career('Nurse',143,151,2,);
CALL create_mil_career('Doctor',143,151,2,);


--
-- Lithuania
--

-- Lithuanian Army
CALL create_mil_career('Armor',143,150,0,);
CALL create_mil_career('Armored Cavalry',143,150,0,);
CALL create_mil_career('Artillery',143,150,0,);
CALL create_mil_career('Aircraft Mechanic',143,150,0,);
CALL create_mil_career('Combat Engineer',143,150,0,);
CALL create_mil_career('Infantry',143,150,0,);
CALL create_mil_career('Medic',143,150,0,);
CALL create_mil_career('Intelligence',143,150,0,);
CALL create_mil_career('Support',143,150,0,);
CALL create_mil_career('Mountain Infantry',143,150,0,);
CALL create_mil_career('Paratrooper',143,150,0,);

CALL create_mil_career('Armor',143,151,0,);
CALL create_mil_career('Armored Cavalry',143,151,0,);
CALL create_mil_career('Artillery',143,151,0,);
CALL create_mil_career('Helicopter Pilot',143,151,0,);
CALL create_mil_career('Combat Engineer',143,151,0,);
CALL create_mil_career('Infantry',143,151,0,);
CALL create_mil_career('Nurse',143,151,0,);
CALL create_mil_career('Doctor',143,151,0,);
CALL create_mil_career('Intelligence',143,151,0,);
CALL create_mil_career('Support',143,151,0,);
CALL create_mil_career('Mountain Infantry',143,151,0,);
CALL create_mil_career('Paratrooper',143,151,0,);

-- Lithuanian Marines
CALL create_mil_career('Armor',143,150,1,);
CALL create_mil_career('Armored Cavalry',143,150,1,);
CALL create_mil_career('Artillery',143,150,1,);
CALL create_mil_career('Aircraft Mechanic',143,150,1,);
CALL create_mil_career('Combat Engineer',143,150,1,);
CALL create_mil_career('Naval Infantry',143,150,1,);
CALL create_mil_career('Medic',143,150,1,);
CALL create_mil_career('Intelligence',143,150,1,);
CALL create_mil_career('Support',143,150,1,);

CALL create_mil_career('Armor',143,151,1,);
CALL create_mil_career('Armored Cavalry',143,151,1,);
CALL create_mil_career('Artillery',143,151,1,);
CALL create_mil_career('Helicopter Pilot',143,151,1,);
CALL create_mil_career('Combat Engineer',143,151,1,);
CALL create_mil_career('Naval Infantry',143,151,1,);
CALL create_mil_career('Intelligence',143,151,1,);
CALL create_mil_career('Support',143,151,1,);

-- Lithuanian Navy
CALL create_mil_career('Seaman',143,150,2,);
CALL create_mil_career('Medic',143,150,2,);

CALL create_mil_career('Naval Officer',143,151,2,);
CALL create_mil_career('Nurse',143,151,2,);
CALL create_mil_career('Doctor',143,151,2,);

-- Lithuanian Air Force
CALL create_mil_career('Airman',143,150,2,);
CALL create_mil_career('Medic',143,150,2,);

CALL create_mil_career('Pilot',143,151,2,);
CALL create_mil_career('Nurse',143,151,2,);
CALL create_mil_career('Doctor',143,151,2,);



--
-- The Netherlands
--

-- Dutch Army
CALL create_mil_career('Armor',143,150,0,);
CALL create_mil_career('Armored Cavalry',143,150,0,);
CALL create_mil_career('Artillery',143,150,0,);
CALL create_mil_career('Aircraft Mechanic',143,150,0,);
CALL create_mil_career('Combat Engineer',143,150,0,);
CALL create_mil_career('Infantry',143,150,0,);
CALL create_mil_career('Medic',143,150,0,);
CALL create_mil_career('Intelligence',143,150,0,);
CALL create_mil_career('Commando',143,150,0,);
CALL create_mil_career('Support',143,150,0,);

CALL create_mil_career('Armor',143,151,0,);
CALL create_mil_career('Armored Cavalry',143,151,0,);
CALL create_mil_career('Artillery',143,151,0,);
CALL create_mil_career('Helicopter Pilot',143,151,0,);
CALL create_mil_career('Combat Engineer',143,151,0,);
CALL create_mil_career('Infantry',143,151,0,);
CALL create_mil_career('Nurse',143,151,0,);
CALL create_mil_career('Doctor',143,151,0,);
CALL create_mil_career('Intelligence',143,151,0,);
CALL create_mil_career('Commando',143,151,0,);
CALL create_mil_career('Support',143,151,0,);

-- Dutch Marines
CALL create_mil_career('Armor',143,150,1,);
CALL create_mil_career('Armored Cavalry',143,150,1,);
CALL create_mil_career('Artillery',143,150,1,);
CALL create_mil_career('Aircraft Mechanic',143,150,1,);
CALL create_mil_career('Combat Engineer',143,150,1,);
CALL create_mil_career('Infantry',143,150,1,);
CALL create_mil_career('Mountain Infantry',143,150,1,);
CALL create_mil_career('Medic',143,150,1,);
CALL create_mil_career('Intelligence',143,150,1,);
CALL create_mil_career('Support',143,150,1,);

CALL create_mil_career('Armor',143,151,1,);
CALL create_mil_career('Armored Cavalry',143,151,1,);
CALL create_mil_career('Artillery',143,151,1,);
CALL create_mil_career('Helicopter Pilot',143,151,1,);
CALL create_mil_career('Combat Engineer',143,151,1,);
CALL create_mil_career('Infantry',143,151,1,);
CALL create_mil_career('Airborne',143,151,1,);
CALL create_mil_career('Mountain Infantry',143,151,1,);
CALL create_mil_career('Ranger',143,151,1,);
CALL create_mil_career('Intelligence',143,151,1,);
CALL create_mil_career('Support',143,151,1,);

-- Dutch Navy
CALL create_mil_career('Seaman',143,150,2,);
CALL create_mil_career('Medic',143,150,2,);
CALL create_mil_career('SBS',143,150,2,);

CALL create_mil_career('Naval Officer',143,151,2,);
CALL create_mil_career('Naval Aviator',143,151,2,);
CALL create_mil_career('Nurse',143,151,2,);
CALL create_mil_career('Doctor',143,151,2,);
CALL create_mil_career('SBS',143,151,2,);

-- Dutch Air Force
CALL create_mil_career('Airman',143,150,2,);
CALL create_mil_career('Medic',143,150,2,);

CALL create_mil_career('Pilot',143,151,2,);
CALL create_mil_career('Nurse',143,151,2,);
CALL create_mil_career('Doctor',143,151,2,);


--
-- Norway
--

-- Norwegian Army
CALL create_mil_career('Armor',143,150,0,);
CALL create_mil_career('Armored Cavalry',143,150,0,);
CALL create_mil_career('Artillery',143,150,0,);
CALL create_mil_career('Aircraft Mechanic',143,150,0,);
CALL create_mil_career('Combat Engineer',143,150,0,);
CALL create_mil_career('Infantry',143,150,0,);
CALL create_mil_career('Mountain Infantry',143,150,0,);
CALL create_mil_career('Jaeger',143,150,0,);
CALL create_mil_career('Medic',143,150,0,);
CALL create_mil_career('Intelligence',143,150,0,);
CALL create_mil_career('Support',143,150,0,);

CALL create_mil_career('Armor',143,151,0,);
CALL create_mil_career('Armored Cavalry',143,151,0,);
CALL create_mil_career('Artillery',143,151,0,);
CALL create_mil_career('Helicopter Pilot',143,151,0,);
CALL create_mil_career('Combat Engineer',143,151,0,);
CALL create_mil_career('Infantry',143,151,0,);
CALL create_mil_career('Mountain Infantry',143,151,0,);
CALL create_mil_career('Jaeger',143,151,0,);
CALL create_mil_career('Nurse',143,151,0,);
CALL create_mil_career('Doctor',143,151,0,);
CALL create_mil_career('Intelligence',143,151,0,);
CALL create_mil_career('Support',143,151,0,);

-- Norwegian Navy
CALL create_mil_career('Seaman',143,150,2,);
CALL create_mil_career('Medic',143,150,2,);
CALL create_mil_career('Jaeger',143,150,2,);

CALL create_mil_career('Naval Officer',143,151,2,);
CALL create_mil_career('Nurse',143,151,2,);
CALL create_mil_career('Doctor',143,151,2,);
CALL create_mil_career('Jaeger',143,151,2,);

-- Norwegian Air Force
CALL create_mil_career('Airman',143,150,2,);
CALL create_mil_career('Medic',143,150,2,);

CALL create_mil_career('Pilot',143,151,2,);
CALL create_mil_career('Nurse',143,151,2,);
CALL create_mil_career('Doctor',143,151,2,);



--
-- Poland
--

-- Polish Army
CALL create_mil_career('Armor',143,150,0,);
CALL create_mil_career('Armored Cavalry',143,150,0,);
CALL create_mil_career('Artillery',143,150,0,);
CALL create_mil_career('Aircraft Mechanic',143,150,0,);
CALL create_mil_career('Combat Engineer',143,150,0,);
CALL create_mil_career('Infantry',143,150,0,);
CALL create_mil_career('Air Assault Division',143,150,0,);
CALL create_mil_career('Highland Brigade',143,150,0,);
CALL create_mil_career('Medic',143,150,0,);
CALL create_mil_career('Intelligence',143,150,0,);
CALL create_mil_career('Special Operations',143,150,0,);
CALL create_mil_career('Support',143,150,0,);

CALL create_mil_career('Armor',143,151,0,);
CALL create_mil_career('Armored Cavalry',143,151,0,);
CALL create_mil_career('Artillery',143,151,0,);
CALL create_mil_career('Helicopter Pilot',143,151,0,);
CALL create_mil_career('Combat Engineer',143,151,0,);
CALL create_mil_career('Infantry',143,151,0,);
CALL create_mil_career('Air Assault Division',143,151,0,);
CALL create_mil_career('Highland Brigade',143,151,0,);
CALL create_mil_career('Nurse',143,151,0,);
CALL create_mil_career('Doctor',143,151,0,);
CALL create_mil_career('Intelligence',143,151,0,);
CALL create_mil_career('Special Operations',143,151,0,);
CALL create_mil_career('Support',143,151,0,);

-- Polish Marines
CALL create_mil_career('Armor',143,150,1,);
CALL create_mil_career('Armored Cavalry',143,150,1,);
CALL create_mil_career('Artillery',143,150,1,);
CALL create_mil_career('Aircraft Mechanic',143,150,1,);
CALL create_mil_career('Combat Engineer',143,150,1,);
CALL create_mil_career('Infantry',143,150,1,);
CALL create_mil_career('Medic',143,150,1,);
CALL create_mil_career('Intelligence',143,150,1,);
CALL create_mil_career('Support',143,150,1,);

CALL create_mil_career('Armor',143,151,1,);
CALL create_mil_career('Armored Cavalry',143,151,1,);
CALL create_mil_career('Artillery',143,151,1,);
CALL create_mil_career('Helicopter Pilot',143,151,1,);
CALL create_mil_career('Combat Engineer',143,151,1,);
CALL create_mil_career('Infantry',143,151,1,);
CALL create_mil_career('Intelligence',143,151,1,);
CALL create_mil_career('Support',143,151,1,);

-- Polish Navy
CALL create_mil_career('Seaman',143,150,2,);
CALL create_mil_career('Medic',143,150,2,);

CALL create_mil_career('Naval Officer',143,151,2,);
CALL create_mil_career('Naval Aviator',143,151,2,);
CALL create_mil_career('Nurse',143,151,2,);
CALL create_mil_career('Doctor',143,151,2,);

-- Polish Air Force
CALL create_mil_career('Airman',143,150,2,);
CALL create_mil_career('Medic',143,150,2,);

CALL create_mil_career('Pilot',143,151,2,);
CALL create_mil_career('Nurse',143,151,2,);
CALL create_mil_career('Doctor',143,151,2,);



--
-- Romania
--

-- Romanian Army
CALL create_mil_career('Armor',143,150,0,);
CALL create_mil_career('Armored Cavalry',143,150,0,);
CALL create_mil_career('Artillery',143,150,0,);
CALL create_mil_career('Aircraft Mechanic',143,150,0,);
CALL create_mil_career('Combat Engineer',143,150,0,);
CALL create_mil_career('Infantry',143,150,0,);
CALL create_mil_career('Parachutist',143,150,0,);
CALL create_mil_career('Mountain Infantry',143,150,0,);
CALL create_mil_career('Medic',143,150,0,);
CALL create_mil_career('Intelligence',143,150,0,);
CALL create_mil_career('Support',143,150,0,);

CALL create_mil_career('Armor',143,151,0,);
CALL create_mil_career('Armored Cavalry',143,151,0,);
CALL create_mil_career('Artillery',143,151,0,);
CALL create_mil_career('Helicopter Pilot',143,151,0,);
CALL create_mil_career('Combat Engineer',143,151,0,);
CALL create_mil_career('Infantry',143,151,0,);
CALL create_mil_career('Parachutist',143,151,0,);
CALL create_mil_career('Mountain Infantry',143,151,0,);
CALL create_mil_career('Nurse',143,151,0,);
CALL create_mil_career('Doctor',143,151,0,);
CALL create_mil_career('Intelligence',143,151,0,);
CALL create_mil_career('Support',143,151,0,);

-- Romanian Naval Infantry
CALL create_mil_career('Armor',143,150,1,);
CALL create_mil_career('Armored Cavalry',143,150,1,);
CALL create_mil_career('Artillery',143,150,1,);
CALL create_mil_career('Aircraft Mechanic',143,150,1,);
CALL create_mil_career('Combat Engineer',143,150,1,);
CALL create_mil_career('Naval Infantry',143,150,1,);
CALL create_mil_career('Medic',143,150,1,);
CALL create_mil_career('Intelligence',143,150,1,);
CALL create_mil_career('Support',143,150,1,);

CALL create_mil_career('Armor',143,151,1,);
CALL create_mil_career('Armored Cavalry',143,151,1,);
CALL create_mil_career('Artillery',143,151,1,);
CALL create_mil_career('Helicopter Pilot',143,151,1,);
CALL create_mil_career('Combat Engineer',143,151,1,);
CALL create_mil_career('Naval Infantry',143,151,1,);
CALL create_mil_career('Intelligence',143,151,1,);
CALL create_mil_career('Support',143,151,1,);

-- Romanian Navy
CALL create_mil_career('Seaman',143,150,2,);
CALL create_mil_career('Medic',143,150,2,);

CALL create_mil_career('Naval Officer',143,151,2,);
CALL create_mil_career('Nurse',143,151,2,);
CALL create_mil_career('Doctor',143,151,2,);

-- Romanian Air Force
CALL create_mil_career('Airman',143,150,2,);
CALL create_mil_career('Medic',143,150,2,);

CALL create_mil_career('Pilot',143,151,2,);
CALL create_mil_career('Nurse',143,151,2,);
CALL create_mil_career('Doctor',143,151,2,);


--
-- Soviet Union
--

-- Red Army
CALL create_mil_career('Armor',143,150,0,);
CALL create_mil_career('Armored Cavalry',143,150,0,);
CALL create_mil_career('Artillery',143,150,0,);
CALL create_mil_career('Aircraft Mechanic',143,150,0,);
CALL create_mil_career('Combat Engineer',143,150,0,);
CALL create_mil_career('Motor Rifles',143,150,0,);
CALL create_mil_career('Airborne',143,150,0,);
CALL create_mil_career('Mountain Infantry',143,150,0,);
CALL create_mil_career('Ranger',143,150,0,);
CALL create_mil_career('Medic',143,150,0,);
CALL create_mil_career('Intelligence',143,150,0,);
CALL create_mil_career('Spetsnaz',143,150,0,);
CALL create_mil_career('Support',143,150,0,);

CALL create_mil_career('Armor',143,151,0,);
CALL create_mil_career('Armored Cavalry',143,151,0,);
CALL create_mil_career('Artillery',143,151,0,);
CALL create_mil_career('Frontal Aviation Pilot',143,151,0,);
CALL create_mil_career('Combat Engineer',143,151,0,);
CALL create_mil_career('Motor Rifles',143,151,0,);
CALL create_mil_career('Airborne',143,151,0,);
CALL create_mil_career('Mountain Infantry',143,151,0,);
CALL create_mil_career('Ranger',143,151,0,);
CALL create_mil_career('Nurse',143,151,0,);
CALL create_mil_career('Doctor',143,151,0,);
CALL create_mil_career('Intelligence',143,151,0,);
CALL create_mil_career('Spetsnaz',143,151,0,);
CALL create_mil_career('Support',143,151,0,);

-- Soviet Naval Infantry
CALL create_mil_career('Armor',143,150,1,);
CALL create_mil_career('Armored Cavalry',143,150,1,);
CALL create_mil_career('Artillery',143,150,1,);
CALL create_mil_career('Aircraft Mechanic',143,150,1,);
CALL create_mil_career('Combat Engineer',143,150,1,);
CALL create_mil_career('Naval Infantry',143,150,1,);
CALL create_mil_career('Mountain Infantry',143,150,1,);
CALL create_mil_career('Medic',143,150,1,);
CALL create_mil_career('Intelligence',143,150,1,);
CALL create_mil_career('Support',143,150,1,);

CALL create_mil_career('Armor',143,151,1,);
CALL create_mil_career('Armored Cavalry',143,151,1,);
CALL create_mil_career('Artillery',143,151,1,);
CALL create_mil_career('Helicopter Pilot',143,151,1,);
CALL create_mil_career('Combat Engineer',143,151,1,);
CALL create_mil_career('Naval Infantry',143,151,1,);
CALL create_mil_career('Intelligence',143,151,1,);

-- Soviet Navy
CALL create_mil_career('Seaman',143,150,2,);
CALL create_mil_career('Medic',143,150,2,);
CALL create_mil_career('Naval Spetsnaz',143,150,2,);

CALL create_mil_career('Naval Officer',143,151,2,);
CALL create_mil_career('Naval Aviator',143,151,2,);
CALL create_mil_career('Nurse',143,151,2,);
CALL create_mil_career('Doctor',143,151,2,);
CALL create_mil_career('Naval Spetsnaz',143,151,2,);

-- Red Air Force
CALL create_mil_career('Airman',143,150,2,);
CALL create_mil_career('Medic',143,150,2,);

CALL create_mil_career('Pilot',143,151,2,);
CALL create_mil_career('Nurse',143,151,2,);
CALL create_mil_career('Doctor',143,151,2,);



--
-- Turkey
--

-- Turkish Army
CALL create_mil_career('Armor',143,150,0,);
CALL create_mil_career('Armored Cavalry',143,150,0,);
CALL create_mil_career('Artillery',143,150,0,);
CALL create_mil_career('Aircraft Mechanic',143,150,0,);
CALL create_mil_career('Combat Engineer',143,150,0,);
CALL create_mil_career('Infantry',143,150,0,);
CALL create_mil_career('Paracutcu',143,150,0,);
CALL create_mil_career('Medic',143,150,0,);
CALL create_mil_career('Intelligence',143,150,0,);
CALL create_mil_career('Paracutcu Commando',143,150,0,);
CALL create_mil_career('Support',143,150,0,);

CALL create_mil_career('Armor',143,151,0,);
CALL create_mil_career('Armored Cavalry',143,151,0,);
CALL create_mil_career('Artillery',143,151,0,);
CALL create_mil_career('Helicopter Pilot',143,151,0,);
CALL create_mil_career('Combat Engineer',143,151,0,);
CALL create_mil_career('Infantry',143,151,0,);
CALL create_mil_career('Paracutcu',143,151,0,);
CALL create_mil_career('Nurse',143,151,0,);
CALL create_mil_career('Doctor',143,151,0,);
CALL create_mil_career('Intelligence',143,151,0,);
CALL create_mil_career('Paracutcu Commando',143,151,0,);
CALL create_mil_career('Support',143,151,0,);

-- Turkish Amphibious Rifles
CALL create_mil_career('Armor',143,150,1,);
CALL create_mil_career('Armored Cavalry',143,150,1,);
CALL create_mil_career('Artillery',143,150,1,);
CALL create_mil_career('Aircraft Mechanic',143,150,1,);
CALL create_mil_career('Combat Engineer',143,150,1,);
CALL create_mil_career('Amphibious Rifles',143,150,1,);
CALL create_mil_career('Medic',143,150,1,);
CALL create_mil_career('Intelligence',143,150,1,);
CALL create_mil_career('Support',143,150,1,);

CALL create_mil_career('Armor',143,151,1,);
CALL create_mil_career('Armored Cavalry',143,151,1,);
CALL create_mil_career('Artillery',143,151,1,);
CALL create_mil_career('Helicopter Pilot',143,151,1,);
CALL create_mil_career('Combat Engineer',143,151,1,);
CALL create_mil_career('Amphibious Rifles',143,151,1,);
CALL create_mil_career('Intelligence',143,151,1,);
CALL create_mil_career('Support',143,151,1,);

-- Turkish Navy
CALL create_mil_career('Seaman',143,150,2,);
CALL create_mil_career('Medic',143,150,2,);

CALL create_mil_career('Naval Officer',143,151,2,);
CALL create_mil_career('Naval Aviator',143,151,2,);
CALL create_mil_career('Nurse',143,151,2,);
CALL create_mil_career('Doctor',143,151,2,);

-- Turkish Air Force
CALL create_mil_career('Airman',143,150,2,);
CALL create_mil_career('Medic',143,150,2,);

CALL create_mil_career('Pilot',143,151,2,);
CALL create_mil_career('Nurse',143,151,2,);
CALL create_mil_career('Doctor',143,151,2,);



--
-- The United Kingdom
--

-- British Army
CALL create_mil_career('Armor',143,150,0,);
CALL create_mil_career('Armored Cavalry',143,150,0,);
CALL create_mil_career('Artillery',143,150,0,);
CALL create_mil_career('Aircraft Mechanic',143,150,0,);
CALL create_mil_career('Combat Engineer',143,150,0,);
CALL create_mil_career('Infantry',143,150,0,);
CALL create_mil_career('Airborne',143,150,0,);
CALL create_mil_career('Medic',143,150,0,);
CALL create_mil_career('Intelligence',143,150,0,);
CALL create_mil_career('SAS',143,150,0,);
CALL create_mil_career('Support',143,150,0,);

CALL create_mil_career('Armor',143,151,0,);
CALL create_mil_career('Armored Cavalry',143,151,0,);
CALL create_mil_career('Artillery',143,151,0,);
CALL create_mil_career('Helicopter Pilot',143,151,0,);
CALL create_mil_career('Combat Engineer',143,151,0,);
CALL create_mil_career('Infantry',143,151,0,);
CALL create_mil_career('Airborne',143,151,0,);
CALL create_mil_career('Nurse',143,151,0,);
CALL create_mil_career('Doctor',143,151,0,);
CALL create_mil_career('Intelligence',143,151,0,);
CALL create_mil_career('SAS',143,151,0,);
CALL create_mil_career('Support',143,151,0,);

-- Royal Marines
CALL create_mil_career('Armor',143,150,1,);
CALL create_mil_career('Armored Cavalry',143,150,1,);
CALL create_mil_career('Artillery',143,150,1,);
CALL create_mil_career('Aircraft Mechanic',143,150,1,);
CALL create_mil_career('Combat Engineer',143,150,1,);
CALL create_mil_career('Royal Marine Commandoes',143,150,1,);
CALL create_mil_career('Mountain Infantry',143,150,1,);
CALL create_mil_career('Medic',143,150,1,);
CALL create_mil_career('Intelligence',143,150,1,);
CALL create_mil_career('Support',143,150,1,);

CALL create_mil_career('Armor',143,151,1,);
CALL create_mil_career('Armored Cavalry',143,151,1,);
CALL create_mil_career('Artillery',143,151,1,);
CALL create_mil_career('Helicopter Pilot',143,151,1,);
CALL create_mil_career('Combat Engineer',143,151,1,);
CALL create_mil_career('Royal Marine Commandoes',143,151,1,);
CALL create_mil_career('Intelligence',143,151,1,);
CALL create_mil_career('Support',143,151,1,);

-- Royal Navy
CALL create_mil_career('Seaman',143,150,2,);
CALL create_mil_career('Medic',143,150,2,);
CALL create_mil_career('SBS',143,150,2,);

CALL create_mil_career('Naval Officer',143,151,2,);
CALL create_mil_career('Naval Aviator',143,151,2,);
CALL create_mil_career('Nurse',143,151,2,);
CALL create_mil_career('Doctor',143,151,2,);
CALL create_mil_career('SBS',143,151,2,);

-- Royal Air Force
CALL create_mil_career('Airman',143,150,2,);
CALL create_mil_career('Medic',143,150,2,);

CALL create_mil_career('Pilot',143,151,2,);
CALL create_mil_career('Nurse',143,151,2,);
CALL create_mil_career('Doctor',143,151,2,);


--
-- French Foreign Legion
--

-- French Foreign Legion
CALL create_mil_career('Etranger Infantry',143,150,1,31);
CALL create_mil_career('Etranger Armor',143,150,1,31);
CALL create_mil_career('Etranger Artillery',143,150,1,31);
CALL create_mil_career('Etranger Parachutist',143,150,1,31);

CALL create_mil_career('Etranger Infantry',143,151,1,31);
CALL create_mil_career('Etranger Armor',143,151,1,31);
CALL create_mil_career('Etranger Artillery',143,151,1,31);
CALL create_mil_career('Etranger Parachutist',143,151,1,31);

COMMIT;

--
-- Educational Careers
--
CALL create_career('Undergraduate University',141);
CALL create_career('National Military Academy',141);
CALL create_career('Graduate University',141);
CALL create_career('Law School',141);
CALL create_career('Medical School',141);
CALL create_career('Technical School',141);
COMMIT;

--
-- Civilian Careers
--
CALL create_career('Attorney',142);
CALL create_career('Civil Engineer',142);
CALL create_career('Commercial Pilot',142);
CALL create_career('Computer Programmer',142);
CALL create_career('Computer Programmer',142); 
CALL create_career('Construction Worker',142);
CALL create_career('Criminal',142);
CALL create_career('Entertainer',142);
CALL create_career('Factory Worker',142);
CALL create_career('Farmer',142);
CALL create_career('Federal Law Enforcement',142);
CALL create_career('Government Agent',142);
CALL create_career('Idle Rich',142);
CALL create_career('Journalist',142);
CALL create_career('Manager',142);
CALL create_career('Mechanic',142);
CALL create_career('Medical Doctor',142);
CALL create_career('Registered Nurse',142);
CALL create_career('Paramedic',142);
CALL create_career('Poiltician',142);
CALL create_career('Prison',142);
CALL create_career('Private Investigator',142);
CALL create_career('Professor',142);
CALL create_career('Local Law Enforcement',142);
CALL create_career('Truck Driver',142);
COMMIT;
CHECKPOINT;