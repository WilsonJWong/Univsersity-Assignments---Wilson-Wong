/******PLEASE ENTER YOUR DETAILS BELOW******/
/*T5-cr-mods.sql*/

/* ITO Assignment 2 Task 5*/

/*Student ID: 29704154*/
/*Student Name: Wilson Wong*/

/* Comments for your marker:


*/

/* ENSURE that your SQL code is formatted and has a semicolon (;)*/
/* at the end of every statement. When marked this will be run as*/
/* a script.*/

/*
(a)
*/
ALTER TABLE entry ADD CONSTRAINT entry_uq UNIQUE ( comp_no,
                                                   carn_date );

COMMIT;
   
/*
(b)
*/
ALTER TABLE entry ADD entry_elapsedtime NUMBER(5, 2);

COMMENT ON COLUMN entry.entry_elapsedtime IS
    'Elapsed time from start time to finish time';

UPDATE entry
SET
    entry_elapsedtime = round((entry_finishtime - entry_starttime) * 24 * 60, 2);

COMMIT;

/*
(c)
*/
DROP TABLE competitor_emercontact CASCADE CONSTRAINTS;

CREATE TABLE competitor_emercontact (
    cont_id              NUMBER(5) NOT NULL,
    comp_no              NUMBER(5) NOT NULL,
    ec_phone             CHAR(10) NOT NULL,
    comp_ec_relationship CHAR(1) NOT NULL
);

COMMENT ON COLUMN competitor_emercontact.cont_id IS
    'Emergency contact number (unique)';

COMMENT ON COLUMN competitor_emercontact.comp_no IS
    'Competitor registration number (unique)';

COMMENT ON COLUMN competitor_emercontact.ec_phone IS
    'Emergency contact phone number';

ALTER TABLE competitor DROP COLUMN ec_phone;

ALTER TABLE competitor DROP COLUMN comp_ec_relationship;

ALTER TABLE competitor_emercontact ADD CONSTRAINT ce_pk PRIMARY KEY ( cont_id );

ALTER TABLE competitor_emercontact ADD CONSTRAINT ce_uq UNIQUE ( comp_no,
                                                                 ec_phone );

ALTER TABLE competitor_emercontact
    ADD CONSTRAINT ce_competitor_fk FOREIGN KEY ( comp_no )
        REFERENCES competitor ( comp_no );

ALTER TABLE competitor_emercontact
    ADD CONSTRAINT ce_emercontact_fk FOREIGN KEY ( ec_phone )
        REFERENCES emercontact ( ec_phone );

ALTER TABLE competitor_emercontact
    ADD CONSTRAINT check_cerelationship CHECK ( comp_ec_relationship IN ( 'F', 'G', 'P'
    , 'T' ) );

COMMIT;