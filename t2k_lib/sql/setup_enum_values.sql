---------------------------
-- Enumerated Values Table 
---------------------------

--Create Generic String Values
INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(0, 0, 'ROOT', 0);

--Language Tree Root
INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(1, 0, 'Language Tree', 0);

--Langage Families
INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(2, 1, 'Germanic', 0);
INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(3, 1, 'Romance', 1);
INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(4, 1, 'Celtic', 2);
INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(5, 1, 'Greek', 3);
INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(6, 1, 'Balto-Slavic', 4);
INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(7, 1, 'Albanian', 5);
INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(8, 1, 'Armenian', 6);
INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(9, 1, 'Indo-Iranian', 7);
INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(10, 1, 'Caucasian', 8);
INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(11, 1, 'Sino-Tibetan', 9);
INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(12, 1, 'Semito-Hamitic', 10);
INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(13, 1, 'Dravidian', 11);
INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(14, 1, 'Japanese', 12);
INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(15, 1, 'Altaic', 13);
INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(16, 1, 'Vietnamese', 14);
INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(17, 1, 'Mon-Khmer', 15);
INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(18, 1, 'Korean', 16);
INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(19, 1, 'Bantu', 17);
INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(20, 1, 'Malayo-Polynesian', 18);
INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(21, 1, 'Armerindian', 19);

--Language Groups
INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(22, 2, 'Anglic', 0);
INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(23, 2, 'West Germanic', 1);
INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(24, 2, 'North Germanic', 2);

INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(25, 3, 'East Romance', 0);
INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(26, 3, 'West Romance', 1);
INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(27, 3, 'E&W Romance', 2);

INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(28, 4, 'Goidelic', 0);
INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(29, 4, 'Brythonic', 1);

INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(30, 5, 'Greek', 0);

INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(31, 6, 'Baltic', 0);
INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(32, 6, 'East Slavic', 1);
INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(33, 6, 'West Slavic', 2);
INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(34, 6, 'South Slavic', 3);

INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(35, 7, 'Albanian', 0);

INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(36, 8, 'Armenian', 0);

INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(37, 9, 'Indic', 0);
INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(38, 9, 'Iranian', 1);

INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(39, 10, 'South Caucasian', 0);

INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(40, 11, 'Sinitic', 0);
INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(41, 11, 'Tibeto-Burman', 1);

INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(42, 12, 'Semitic', 0);
INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(43, 12, 'Hamitic', 1);

INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(44, 13, 'Dravidian', 0);

INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(45, 14, 'Japanese', 0);

INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(46, 15, 'Turkic', 0);
INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(47, 15, 'Ugric', 1);
INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(48, 15, 'Finnic', 2);

INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(49, 16, 'Vietnamese', 0);

INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(50, 17, 'Mon-Khemer', 0);

INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(51, 18, 'Korean', 0);

INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(52, 19, 'Bantu', 0);

INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(53, 20, 'W. Malayo-Polynesian', 0);

INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(54, 21, 'South Amerindian', 0);
INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(55, 21, 'Athabascan', 1);
INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(56, 21, 'Uto-Aztecan', 2);

--Languages
INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(57, 22, 'English', 0);

INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(58, 23, 'German', 0);
INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(59, 23, 'Dutch', 1);
INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(60, 23, 'Yiddish', 2);
INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(61, 23, 'Flemish', 3);

INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(62, 24, 'Danish', 0);
INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(63, 24, 'Swedish', 1);
INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(64, 24, 'Norwegian', 2);

INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(65, 25, 'Italian', 0);
INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(66, 25, 'Romanian', 1);

INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(67, 26, 'Spanish', 0);
INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(68, 26, 'French', 1);
INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(69, 26, 'Portuguese', 2);

INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(70, 27, 'Latin', 0);

INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(71, 28, 'Scots Gaelic', 0);
INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(72, 28, 'Irish', 1);

INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(73, 29, 'Welsh', 0);
INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(74, 29, 'Breton', 1);

INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(75, 30, 'Greek', 0);

INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(76, 31, 'Lithuanian', 0);
INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(77, 31, 'Latvian', 1);

INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(78, 32, 'Russian', 0);

INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(79, 33, 'Polish', 0);
INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(80, 33, 'Czech', 1);
INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(81, 33, 'Slovak', 2);

INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(82, 34, 'Serbo-Croat', 0);
INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(83, 34, 'Bulgarian', 1);
INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(84, 34, 'Slovenian', 2);
INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(85, 34, 'Macedonian', 3);

INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(86, 35, 'Albanian', 0);

INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(87, 36, 'Armenian', 0);

INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(88, 37, 'Hindi-Urdu', 0);
INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(89, 37, 'Bengali', 1);
INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(90, 37, 'Romany', 2);

INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(91, 38, 'Taijik', 0);
INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(92, 38, 'Farsi', 1);

INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(93, 39, 'Georgian', 0);

INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(94, 40, 'Mandarin', 0);
INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(95, 40, 'Cantonese', 1);

INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(96, 41, 'Thai', 0);
INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(97, 41, 'Burmese', 1);

INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(98, 42, 'Arabic', 0);
INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(99, 42, 'Hebrew', 1);

INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(100, 43, 'Berber', 0);
INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(101, 43, 'Hausa', 1);

INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(102, 44, 'Tamil', 0);

INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(103, 45, 'Japanese', 0);

INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(104, 46, 'Turkish', 0);
INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(105, 46, 'Azerbaijani', 1);
INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(106, 46, 'Uzbek', 2);
INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(107, 46, 'Kazakh', 3);
INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(108, 46, 'Tartar', 4);
INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(109, 46, 'Chuvash', 5);
INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(110, 46, 'Kirzig', 6);
INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(111, 46, 'Turkoman', 7);

INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(112, 47, 'Hungarian', 0);

INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(113, 48, 'Finnish', 0);
INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(114, 48, 'Estonian', 1);
INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(115, 48, 'Mordvinian', 2);

INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(116, 49, 'Vietnamese', 0);

INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(117, 50, 'Cambodian', 0);

INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(118, 51, 'Korean', 0);

INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(119, 52, 'Swahili', 0);

INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(120, 53, 'Malay-Indonesian', 0);

INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(121, 54, 'Maya', 0);

INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(122, 55, 'Navaho', 0);

INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(123, 56, 'Nahuati', 0);

--Gender
INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(124, 0, 'Gender', 0);
INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(125, 124, 'Male', 1);
INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(126, 124, 'Female', 2);

--Faction
INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(127, 0, 'Faction', 0);

INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(128, 127, 'NATO', 0);
INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(129, 127, 'Warsaw Pact', 1);
INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(130, 127, 'Neutral', 2);
INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(131, 127, 'Warlord', 3);
INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(132, 127, 'Other', 4);

--Skill Group
INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(133, 0, 'Skill Group', 0);

INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(134, 133, 'None', 0);
INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(135, 133, 'Small Arms', 1);
INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(136, 133, 'Medical', 2);
INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(137, 133, 'Language', 3);
INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(138, 133, 'Ground Vehicle', 4);
INSERT INTO PUBLIC.T2K_ENUMERATED_VALUE (ID,VALUE_GROUP,ENUM_VALUE,ORDINAL) VALUES(139, 133, 'Pilot', 5);
COMMIT;

CHECKPOINT;
