/*
  ITO4132 Assignment 2
  
  --City Runners Schema File and Initial Data--
  
  Description: 
  This file creates the City Runners tables 
  and populates several of the tables (those shown in purple on the supplied model). 
  You should read this schema file carefully  and be sure you understand the various 
  data requirements.

Author: FIT Database Teaching Team
License: Copyright © Monash University, unless otherwise stated. All Rights Reserved.
COPYRIGHT WARNING
Warning
This material is protected by copyright. For use within Monash University only. NOT FOR RESALE.
Do not remove this notice. 
  
*/


DROP TABLE carnival CASCADE CONSTRAINTS;

DROP TABLE charity CASCADE CONSTRAINTS;

DROP TABLE competitor CASCADE CONSTRAINTS;

DROP TABLE emercontact CASCADE CONSTRAINTS;

DROP TABLE entry CASCADE CONSTRAINTS;

DROP TABLE event CASCADE CONSTRAINTS;

DROP TABLE eventtype CASCADE CONSTRAINTS;

DROP TABLE team CASCADE CONSTRAINTS;

-- predefined type, no DDL - MDSYS.SDO_GEOMETRY

-- predefined type, no DDL - XMLTYPE

CREATE TABLE carnival (
    carn_date     DATE NOT NULL,
    carn_name     VARCHAR2(50) NOT NULL,
    carn_director VARCHAR2(50) NOT NULL,
    carn_location VARCHAR2(50) NOT NULL
);

COMMENT ON COLUMN carnival.carn_date IS
    'Date of carnival (unique identifier)';

COMMENT ON COLUMN carnival.carn_name IS
    'Carnival name';

COMMENT ON COLUMN carnival.carn_director IS
    'Carnival director''s name';

COMMENT ON COLUMN carnival.carn_location IS
    'Carnival''s location';

ALTER TABLE carnival ADD CONSTRAINT carnival_pk PRIMARY KEY ( carn_date );

ALTER TABLE carnival ADD CONSTRAINT carnival_uq UNIQUE ( carn_name );

CREATE TABLE charity (
    char_name    VARCHAR2(30) NOT NULL,
    char_contact VARCHAR2(50) NOT NULL,
    char_phone   CHAR(10) NOT NULL
);

COMMENT ON COLUMN charity.char_name IS
    'Approved charity name';

COMMENT ON COLUMN charity.char_contact IS
    'Charity contact person name';

COMMENT ON COLUMN charity.char_phone IS
    'Charity phone number';

ALTER TABLE charity ADD CONSTRAINT charity_pk PRIMARY KEY ( char_name );

CREATE TABLE competitor (
    comp_no              NUMBER(5) NOT NULL,
    comp_fname           VARCHAR2(30),
    comp_lname           VARCHAR2(30),
    comp_gender          CHAR(1) NOT NULL,
    comp_dob             DATE NOT NULL,
    comp_email           VARCHAR2(50) NOT NULL,
    comp_phone           CHAR(10) NOT NULL,
    comp_ec_relationship CHAR(1) NOT NULL,
    ec_phone             CHAR(10) NOT NULL
);

ALTER TABLE competitor
    ADD CONSTRAINT check_comp_gender CHECK ( comp_gender IN ( 'F', 'M', 'U' ) );

ALTER TABLE competitor
    ADD CONSTRAINT check_ecrelationship CHECK ( comp_ec_relationship IN ( 'F', 'G', 'P'
    , 'T' ) );

COMMENT ON COLUMN competitor.comp_no IS
    'Competitor registration number (unique)';

COMMENT ON COLUMN competitor.comp_fname IS
    'Competitor first name';

COMMENT ON COLUMN competitor.comp_lname IS
    'Competitor last name';

COMMENT ON COLUMN competitor.comp_gender IS
    'Competitor gender (M - Male, F - Female, U - Undisclosed)';

COMMENT ON COLUMN competitor.comp_dob IS
    'Competitor date of birth';

COMMENT ON COLUMN competitor.comp_email IS
    'Competitor email address';

COMMENT ON COLUMN competitor.comp_phone IS
    'Competitor phone number';

COMMENT ON COLUMN competitor.comp_ec_relationship IS
    'Emergency contact relationship to competitor (P or  T or G or F)
P = Parent
T = Partner
G = Guardian
F = Friend
';

COMMENT ON COLUMN competitor.ec_phone IS
    'Emergency contact phone number';

ALTER TABLE competitor ADD CONSTRAINT competitor_pk PRIMARY KEY ( comp_no );

CREATE TABLE emercontact (
    ec_phone CHAR(10) NOT NULL,
    ec_fname VARCHAR2(30),
    ec_lname VARCHAR2(30)
);

COMMENT ON COLUMN emercontact.ec_phone IS
    'Emergency contact phone number';

COMMENT ON COLUMN emercontact.ec_fname IS
    'Emergency contact first name';

COMMENT ON COLUMN emercontact.ec_lname IS
    'Emergency contact last name';

ALTER TABLE emercontact ADD CONSTRAINT emercontact_pk PRIMARY KEY ( ec_phone );

CREATE TABLE entry (
    entry_id         NUMBER(5) NOT NULL,
    entry_starttime  DATE,
    entry_finishtime DATE,
    char_name        VARCHAR2(30),
    comp_no          NUMBER(5) NOT NULL,
    carn_date        DATE NOT NULL,
    eventtype_code   VARCHAR2(3) NOT NULL,
    team_id          NUMBER(3)
);

COMMENT ON COLUMN entry.entry_id IS
    'Entry number';

COMMENT ON COLUMN entry.entry_starttime IS
    'The entrant start time';

COMMENT ON COLUMN entry.entry_finishtime IS
    'The entrant finish time';

COMMENT ON COLUMN entry.char_name IS
    'Approved charity name';

COMMENT ON COLUMN entry.comp_no IS
    'Competitor registration number (unique)';

COMMENT ON COLUMN entry.carn_date IS
    'Date of carnival (unique identifier)';

COMMENT ON COLUMN entry.eventtype_code IS
    'Even type code, reflects the distance of the event, e.g 10K is for 10 kilometer '
    ;

COMMENT ON COLUMN entry.team_id IS
    'Team identifier (unique)';

ALTER TABLE entry ADD CONSTRAINT entry_pk PRIMARY KEY ( entry_id );

CREATE TABLE event (
    carn_date      DATE NOT NULL,
    eventtype_code VARCHAR2(3) NOT NULL
);

COMMENT ON COLUMN event.carn_date IS
    'Date of carnival (unique identifier)';

COMMENT ON COLUMN event.eventtype_code IS
    'Even type code, reflects the distance of the event, e.g 10K is for 10 kilometer '
    ;

ALTER TABLE event ADD CONSTRAINT event_pk PRIMARY KEY ( carn_date,
                                                        eventtype_code );

CREATE TABLE eventtype (
    eventtype_code VARCHAR2(3) NOT NULL,
    eventtype_desc VARCHAR2(50) NOT NULL
);

COMMENT ON COLUMN eventtype.eventtype_code IS
    'Even type code, reflects the distance of the event, e.g 10K is for 10 kilometer '
    ;

COMMENT ON COLUMN eventtype.eventtype_desc IS
    'Even type description';

ALTER TABLE eventtype ADD CONSTRAINT eventtype_pk PRIMARY KEY ( eventtype_code );

ALTER TABLE eventtype ADD CONSTRAINT eventtype_uq UNIQUE ( eventtype_desc );

CREATE TABLE team (
    team_id         NUMBER(3) NOT NULL,
    team_name       VARCHAR2(30) NOT NULL,
    carn_date       DATE NOT NULL,
    team_no_members NUMBER(2) NOT NULL,
    char_name       VARCHAR2(30),
    entry_id        NUMBER(5) NOT NULL
);

COMMENT ON COLUMN team.team_id IS
    'Team identifier (unique)';

COMMENT ON COLUMN team.team_name IS
    'Team name';

COMMENT ON COLUMN team.carn_date IS
    'Date of carnival (unique identifier)';

COMMENT ON COLUMN team.team_no_members IS
    'Number of team members';

COMMENT ON COLUMN team.char_name IS
    'Approved charity name';

COMMENT ON COLUMN team.entry_id IS
    'Entry number';

CREATE UNIQUE INDEX team__idx ON
    team (
        entry_id
    ASC );

ALTER TABLE team ADD CONSTRAINT team_pk PRIMARY KEY ( team_id );

ALTER TABLE team ADD CONSTRAINT team_nk UNIQUE ( carn_date,
                                                 team_name );

ALTER TABLE team
    ADD CONSTRAINT carnival_team FOREIGN KEY ( carn_date )
        REFERENCES carnival ( carn_date );

ALTER TABLE event
    ADD CONSTRAINT carvinal_event FOREIGN KEY ( carn_date )
        REFERENCES carnival ( carn_date );

ALTER TABLE entry
    ADD CONSTRAINT charity_entry FOREIGN KEY ( char_name )
        REFERENCES charity ( char_name );

ALTER TABLE team
    ADD CONSTRAINT charity_team FOREIGN KEY ( char_name )
        REFERENCES charity ( char_name );

ALTER TABLE entry
    ADD CONSTRAINT competitor_entry FOREIGN KEY ( comp_no )
        REFERENCES competitor ( comp_no );

ALTER TABLE competitor
    ADD CONSTRAINT emercontact_competitor FOREIGN KEY ( ec_phone )
        REFERENCES emercontact ( ec_phone );

ALTER TABLE entry
    ADD CONSTRAINT event_entry FOREIGN KEY ( carn_date,
                                             eventtype_code )
        REFERENCES event ( carn_date,
                           eventtype_code );

ALTER TABLE team
    ADD CONSTRAINT entry_team FOREIGN KEY ( entry_id )
        REFERENCES entry ( entry_id );

ALTER TABLE event
    ADD CONSTRAINT eventype_event_fk FOREIGN KEY ( eventtype_code )
        REFERENCES eventtype ( eventtype_code );

ALTER TABLE entry
    ADD CONSTRAINT team_entry FOREIGN KEY ( team_id )
        REFERENCES team ( team_id );

-- *****************************************************************
-- NO FURTHER DATA MAY BE ADDED TO THESE TABLES NOR THE SUPPPLIED
-- DATA MODIFIED IN ANY WAY
-- *****************************************************************

-- INSERTING into CARNIVAL

INSERT INTO carnival (
    carn_date,
    carn_name,
    carn_director,
    carn_location
) VALUES (
    TO_DATE('19/SEP/2021', 'DD/MON/YYYY'),
    'CR Spring Series Melbourne 2021',
    'Cathy Bolter',
    'The Tan, Birdwood Ave, Melbourne, 3004'
);

INSERT INTO carnival (
    carn_date,
    carn_name,
    carn_director,
    carn_location
) VALUES (
    TO_DATE('04/SEP/2022', 'DD/MON/YYYY'),
    'CR Spring Series Melbourne 2022',
    'Cathy Bolta',
    'The Tan, Birdwood Ave, Melbourne, 3004'
);

INSERT INTO carnival (
    carn_date,
    carn_name,
    carn_director,
    carn_location
) VALUES (
    TO_DATE('01/FEB/2023', 'DD/MON/YYYY'),
    'CR Summer Series Sydney 2023',
    'Steve Mona',
    'Bare Creek, Garigal National Park, Sydney, 2075'
);

INSERT INTO carnival (
    carn_date,
    carn_name,
    carn_director,
    carn_location
) VALUES (
    TO_DATE('06/APR/2023', 'DD/MON/YYYY'),
    'CR Autumn Series Brisbane 2023',
    'Usain Bolt',
    'Open Park, Massey Place, Indooroopilly, QLD, 4068'
);

INSERT INTO carnival (
    carn_date,
    carn_name,
    carn_director,
    carn_location
) VALUES (
    TO_DATE('08/SEP/2023', 'DD/MON/YYYY'),
    'CR Spring Series Adelaide 2023',
    'Elena Roman',
    'William Park, Devon Park, SA, 5008'
);

INSERT INTO carnival (
    carn_date,
    carn_name,
    carn_director,
    carn_location
) VALUES (
    TO_DATE('20/FEB/2024', 'DD/MON/YYYY'),
    'CR Summer Series Melbourne 2024',
    'Cathy Bolta',
    'Yarra Bend Park, Yarra Bend Rd, Fairfield, 3078'
);

-- INSERTING into CHARITY
INSERT INTO charity (
    char_name,
    char_contact,
    char_phone
) VALUES (
    'RSPCA',
    'Ms. Susan Madden',
    '6140020002'
);

INSERT INTO charity (
    char_name,
    char_contact,
    char_phone
) VALUES (
    'Beyond Blue',
    'Ms. Julia Gillard',
    '6140040004'
);

INSERT INTO charity (
    char_name,
    char_contact,
    char_phone
) VALUES (
    'Salvation Army',
    'Mr. Michael Jackson',
    '6140080008'
);

INSERT INTO charity (
    char_name,
    char_contact,
    char_phone
) VALUES (
    'Amnesty International',
    'Ms. Navinda Pal',
    '6140081234'
);

-- INSERTING into EVENTTYPE
INSERT INTO eventtype (
    eventtype_code,
    eventtype_desc
) VALUES (
    '42K',
    '42.2 Km Marathon'
);

INSERT INTO eventtype (
    eventtype_code,
    eventtype_desc
) VALUES (
    '21K',
    '21.1 Km Half Marathon'
);

INSERT INTO eventtype (
    eventtype_code,
    eventtype_desc
) VALUES (
    '10K',
    '10 Km Run'
);

INSERT INTO eventtype (
    eventtype_code,
    eventtype_desc
) VALUES (
   '5K',
    '5 Km Run'
);

INSERT INTO eventtype (
    eventtype_code,
    eventtype_desc
) VALUES (
   '3K',
    '3 Km Community Run/Walk'
);

-- INSERTING into EVENT

-- 19/SEP/2021
INSERT INTO event (
    carn_date,
    eventtype_code
) VALUES (
    TO_DATE('19/SEP/2021', 'DD/MON/YYYY'),
    '10K'
);

INSERT INTO event (
    carn_date,
    eventtype_code
) VALUES (
    TO_DATE('19/SEP/2021', 'DD/MON/YYYY'),
   '5K'
);

-- 04/SEP/2022

INSERT INTO event (
    carn_date,
    eventtype_code
) VALUES (
    TO_DATE('04/SEP/2022', 'DD/MON/YYYY'),
    '10K'
);

INSERT INTO event (
    carn_date,
    eventtype_code
) VALUES (
    TO_DATE('04/SEP/2022', 'DD/MON/YYYY'),
   '5K'
);

--

INSERT INTO event (
    carn_date,
    eventtype_code
) VALUES (
    TO_DATE('01/FEB/2023', 'DD/MON/YYYY'),
    '10K'
);

INSERT INTO event (
    carn_date,
    eventtype_code
) VALUES (
    TO_DATE('01/FEB/2023', 'DD/MON/YYYY'),
    '21K'
);

INSERT INTO event (
    carn_date,
    eventtype_code
) VALUES (
    TO_DATE('01/FEB/2023', 'DD/MON/YYYY'),
   '5K'
);

INSERT INTO event (
    carn_date,
    eventtype_code
) VALUES (
    TO_DATE('01/FEB/2023', 'DD/MON/YYYY'),
   '3K'
);

--- 06/APR/2023

INSERT INTO event (
    carn_date,
    eventtype_code
) VALUES (
    TO_DATE('06/APR/2023', 'DD/MON/YYYY'),
   '5K'
);

INSERT INTO event (
    carn_date,
    eventtype_code
) VALUES (
    TO_DATE('06/APR/2023', 'DD/MON/YYYY'),
    '10K'
);

INSERT INTO event (
    carn_date,
    eventtype_code
) VALUES (
    TO_DATE('06/APR/2023', 'DD/MON/YYYY'),
    '21K'
);

INSERT INTO event (
    carn_date,
    eventtype_code
) VALUES (
    TO_DATE('06/APR/2023', 'DD/MON/YYYY'),
   '3K'
);

INSERT INTO event (
    carn_date,
    eventtype_code
) VALUES (
    TO_DATE('06/APR/2023', 'DD/MON/YYYY'),
    '42K'
);

--- 08/SEP/2023

INSERT INTO event (
    carn_date,
    eventtype_code
) VALUES (
    TO_DATE('08/SEP/2023', 'DD/MON/YYYY'),
   '3K'
);

INSERT INTO event (
    carn_date,
    eventtype_code
) VALUES (
    TO_DATE('08/SEP/2023', 'DD/MON/YYYY'),
   '5K'
);

INSERT INTO event (
    carn_date,
    eventtype_code
) VALUES (
    TO_DATE('08/SEP/2023', 'DD/MON/YYYY'),
    '42K'
);

-- 20/FEB/2024

INSERT INTO event (
    carn_date,
    eventtype_code
) VALUES (
    TO_DATE('20/FEB/2024', 'DD/MON/YYYY'),
    '10K'
);

INSERT INTO event (
    carn_date,
    eventtype_code
) VALUES (
    TO_DATE('20/FEB/2024', 'DD/MON/YYYY'),
    '21K'
);

INSERT INTO event (
    carn_date,
    eventtype_code
) VALUES (
    TO_DATE('20/FEB/2024', 'DD/MON/YYYY'),
   '5K'
);

-- INSERTING into EMERCONTACT

INSERT INTO emercontact (
    ec_phone,
    ec_fname,
    ec_lname
) VALUES (
    '0433377788',
    'Dave',
    'Freeman'
);

INSERT INTO emercontact (
    ec_phone,
    ec_fname,
    ec_lname
) VALUES (
    '0433555666',
    'Sonya',
    'De Costella'
);

INSERT INTO emercontact (
    ec_phone,
    ec_fname,
    ec_lname
) VALUES (
    '0433666777',
    'Matthew',
    'Smith'
);

INSERT INTO emercontact (
    ec_phone,
    ec_fname,
    ec_lname
) VALUES (
    '0435123567',
    'Elizabeth',
    'Dunn'
);

INSERT INTO emercontact (
    ec_phone,
    ec_fname,
    ec_lname
) VALUES (
    '0411222345',
    'Bob',
    'Ryan'
);

INSERT INTO emercontact (
    ec_phone,
    ec_fname,
    ec_lname
) VALUES (
    '0450789690',
    'Nithin',
    'Pal'
);

INSERT INTO emercontact (
    ec_phone,
    ec_fname,
    ec_lname
) VALUES (
    '0421909808',
    'Ling',
    'Shu'
);

INSERT INTO emercontact (
    ec_phone,
    ec_fname,
    ec_lname
) VALUES (
    '6112345678',
    'Fernando',
    'Rose'
);

INSERT INTO emercontact (
    ec_phone,
    ec_fname,
    ec_lname
) VALUES (
    '6187654321',
    'Adrianna',
    'Rose'
);

INSERT INTO emercontact emercontact (
    ec_phone,
    ec_fname,
    ec_lname
) VALUES (
    '0987654321',
    'Dave',
    'Radcliffe'
);


-- INSERTING into COMPETITOR

INSERT INTO competitor (
    comp_no,
    comp_fname,
    comp_lname,
    comp_gender,
    comp_dob,
    comp_email,
    comp_phone,
    comp_ec_relationship,
    ec_phone
) VALUES (
    1,
    'Cathy',
    'Freeman',
    'F',
    TO_DATE('02/JAN/1977', 'DD/MON/YYYY'),
    'cathy@gmail.com',
    '0422666777',
    'T',
    '0433377788'
);

INSERT INTO competitor (
    comp_no,
    comp_fname,
    comp_lname,
    comp_gender,
    comp_dob,
    comp_email,
    comp_phone,
    comp_ec_relationship,
    ec_phone
) VALUES (
    2,
    'Rob',
    'De Costella',
    'M',
    TO_DATE('15/MAR/1952', 'DD/MON/YYYY'),
    'rob@gmail.com',
    '0422888999',
    'T',
    '0433555666'
);

INSERT INTO competitor (
    comp_no,
    comp_fname,
    comp_lname,
    comp_gender,
    comp_dob,
    comp_email,
    comp_phone,
    comp_ec_relationship,
    ec_phone
) VALUES (
    3,
    'Brigid',
    'Radcliffe',
    'F',
    TO_DATE('10/OCT/1999', 'DD/MON/YYYY'),
    'brigid@freespirit.net',
    '1234567890',
    'T',
    '0987654321'
);


INSERT INTO competitor (
    comp_no,
    comp_fname,
    comp_lname,
    comp_gender,
    comp_dob,
    comp_email,
    comp_phone,
    comp_ec_relationship,
    ec_phone
) VALUES (
    4,
    'Bob',
    'Ryan',
    'M',
    TO_DATE('02/NOV/1957', 'DD/MON/YYYY'),
    'bob@outlook.com',
    '0411222345',
    'T',
    '0435123567'
);

INSERT INTO competitor (
    comp_no,
    comp_fname,
    comp_lname,
    comp_gender,
    comp_dob,
    comp_email,
    comp_phone,
    comp_ec_relationship,
    ec_phone
) VALUES (
    5,
    'Sam',
    'Ryan',
    'U',
    TO_DATE('15/APR/2011', 'DD/MON/YYYY'),
    'bob@outlook.com',
    '0411222345',
    'G',
    '0411222345'
);

INSERT INTO competitor (
    comp_no,
    comp_fname,
    comp_lname,
    comp_gender,
    comp_dob,
    comp_email,
    comp_phone,
    comp_ec_relationship,
    ec_phone
) VALUES (
    6,
    'Jane',
    'Ryan',
    'F',
    TO_DATE('01/JAN/2008', 'DD/MON/YYYY'),
    'bob@outlook.com',
    '0411222345',
    'G',
    '0411222345'
);

INSERT INTO competitor (
    comp_no,
    comp_fname,
    comp_lname,
    comp_gender,
    comp_dob,
    comp_email,
    comp_phone,
    comp_ec_relationship,
    ec_phone
) VALUES (
    7,
    'Dan',
    'Chu',
    'M',
    TO_DATE('06/JUN/1957', 'DD/MON/YYYY'),
    'dan@outlook.com',
    '0403999808',
    'F',
    '0450789690'
);

INSERT INTO competitor (
    comp_no,
    comp_fname,
    comp_lname,
    comp_gender,
    comp_dob,
    comp_email,
    comp_phone,
    comp_ec_relationship,
    ec_phone
) VALUES (
    8,
    'Srini',
    'Vash',
    'M',
    TO_DATE('31/AUG/2000', 'DD/MON/YYYY'),
    'srini@outlook.com',
    '0411234567',
    'F',
    '0421909808'
);

INSERT INTO competitor (
    comp_no,
    comp_fname,
    comp_lname,
    comp_gender,
    comp_dob,
    comp_email,
    comp_phone,
    comp_ec_relationship,
    ec_phone
) VALUES (
    9,
    'Nithin',
    'Pal',
    'M',
    TO_DATE('30/OCT/2000', 'DD/MON/YYYY'),
    'nithin@outlook.com',
    '0450789690',
    'F',
    '0421909808'
);

INSERT INTO competitor (
    comp_no,
    comp_fname,
    comp_lname,
    comp_gender,
    comp_dob,
    comp_email,
    comp_phone,
    comp_ec_relationship,
    ec_phone
) VALUES (
    11,
    'Nan',
    'Shu',
    'F',
    TO_DATE('13/FEB/2012', 'DD/MON/YYYY'),
    'shu@GMAIL.COM',
    '0421909808',
    'P',
    '0421909808'
);

INSERT INTO competitor (
    comp_no,
    comp_fname,
    comp_lname,
    comp_gender,
    comp_dob,
    comp_email,
    comp_phone,
    comp_ec_relationship,
    ec_phone
) VALUES (
    12,
    'Fan',
    'Shu',
    'M',
    TO_DATE('31/AUG/2007', 'DD/MON/YYYY'),
    'shu@gmail.com',
    '0421909808',
    'P',
    '0421909808'
);

INSERT INTO competitor (
    comp_no,
    comp_fname,
    comp_lname,
    comp_gender,
    comp_dob,
    comp_email,
    comp_phone,
    comp_ec_relationship,
    ec_phone
) VALUES (
    13,
    'William',
    'Wang',
    'M',
    TO_DATE('23/OCT/2010', 'DD/MON/YYYY'),
    'shu@gmail.com',
    '0421909808',
    'G',
    '0421909808'
);

INSERT INTO competitor (
    comp_no,
    comp_fname,
    comp_lname,
    comp_gender,
    comp_dob,
    comp_email,
    comp_phone,
    comp_ec_relationship,
    ec_phone
) VALUES (
    14,
    'Pamela',
    'Sim',
    'F',
    TO_DATE('12/DEC/1992', 'DD/MON/YYYY'),
    'sim@Gmail.Com',
    '0430450678',
    'F',
    '0435123567'
);

INSERT INTO competitor (
    comp_no,
    comp_fname,
    comp_lname,
    comp_gender,
    comp_dob,
    comp_email,
    comp_phone,
    comp_ec_relationship,
    ec_phone
) VALUES (
    15,
    'Sebastian',
    'Coe',
    'M',
    TO_DATE('11/NOV/1962', 'DD/MON/YYYY'),
    'coe@gmail.com',
    '0421990880',
    'F',
    '0433377788'
);

INSERT INTO competitor (
    comp_no,
    comp_fname,
    comp_lname,
    comp_gender,
    comp_dob,
    comp_email,
    comp_phone,
    comp_ec_relationship,
    ec_phone
) VALUES (
    20,
    'Lynn',
    'Nguyen',
    'F',
    TO_DATE('02/OCT/2000', 'DD/MON/YYYY'),
    'lynn@gmail.com',
    '0433123456',
    'F',
    '0433666777'
);

INSERT INTO competitor (
    comp_no,
    comp_fname,
    comp_lname,
    comp_gender,
    comp_dob,
    comp_email,
    comp_phone,
    comp_ec_relationship,
    ec_phone
) VALUES (
    10,
    'Ling',
    'Shu',
    'U',
    TO_DATE('23/JUL/1997', 'DD/MON/YYYY'),
    'shu@gmail.com',
    '0421909808',
    'F',
    '0433666777'
);

INSERT INTO competitor (
    comp_no,
    comp_fname,
    comp_lname,
    comp_gender,
    comp_dob,
    comp_email,
    comp_phone,
    comp_ec_relationship,
    ec_phone
) VALUES (
    17,
    'Adrianna',
    'Rose',
    'F',
    TO_DATE('11/NOV/1973', 'DD/MON/YYYY'),
    'adrianna@freespirit.net',
    '6187654321',
    'T',
    '6112345678'
);

INSERT INTO competitor (
    comp_no,
    comp_fname,
    comp_lname,
    comp_gender,
    comp_dob,
    comp_email,
    comp_phone,
    comp_ec_relationship,
    ec_phone
) VALUES (
    16,
    'Fernando',
    'Rose',
    'M',
    TO_DATE('10/OCT/2009', 'DD/MON/YYYY'),
    'fernando@freespirit.net',
    '6112345678',
    'T',
    '6187654321'
);

INSERT INTO competitor (
    comp_no,
    comp_fname,
    comp_lname,
    comp_gender,
    comp_dob,
    comp_email,
    comp_phone,
    comp_ec_relationship,
    ec_phone
) VALUES (
    18,
    'Annamaria',
    'Rose',
    'F',
    TO_DATE('12/DEC/2006', 'DD/MON/YYYY'),
    'fernando@freespirit.net',
    '6112345678',
    'P',
    '6112345678'
);

INSERT INTO competitor (
    comp_no,
    comp_fname,
    comp_lname,
    comp_gender,
    comp_dob,
    comp_email,
    comp_phone,
    comp_ec_relationship,
    ec_phone
) VALUES (
    19,
    'Juan',
    'Rose',
    'M',
    TO_DATE('01/JAN/2008', 'DD/MON/YYYY'),
    'fernando@freespirit.net',
    '6112345678',
    'P',
    '6112345678'
);
COMMIT;
