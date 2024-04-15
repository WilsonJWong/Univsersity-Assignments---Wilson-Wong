/******PLEASE ENTER YOUR DETAILS BELOW******/
/*T3-cr-dml.sql*/

/* ITO Assignment 2 Task 3*/

/*Student ID: 29704154*/
/*Student Name: Wilson Wong*/

/* Comments for your marker:

For (b) - although the phone number as of now may be unique in the current 
competitor date, this may not be the case in the future. To future proof the
code lookup, first name and last name conditions have been used in addtion
to the phone number.

*/

/* ENSURE that your SQL code is formatted and has a semicolon (;)*/
/* at the end of every statement. When marked this will be run as*/
/* a script.*/

/*
(a)
*/

/* COMPETTITOR */
DROP SEQUENCE competitor_seq;

CREATE SEQUENCE competitor_seq START WITH 100 INCREMENT BY 1;

COMMIT; 

/* ENTRY*/
DROP SEQUENCE entry_seq;

CREATE SEQUENCE entry_seq START WITH 100 INCREMENT BY 1;

COMMIT;

/* TEAM*/
DROP SEQUENCE team_seq;

CREATE SEQUENCE team_seq START WITH 100 INCREMENT BY 1;

COMMIT;

/*
(b) 
*/
INSERT INTO entry (
    entry_id,
    entry_starttime,
    entry_finishtime,
    char_name,
    comp_no,
    carn_date,
    eventtype_code,
    team_id
) VALUES (
    entry_seq.NEXTVAL,
    NULL,
    NULL,
    'Amnesty International',
    (
        SELECT
            comp_no
        FROM
            competitor
        WHERE
                upper(comp_fname) = upper('Brigid')
            AND upper(comp_lname) = upper('Radcliffe')
            AND comp_phone = '1234567890'
    ),
    (
        SELECT
            carn_date
        FROM
            carnival
        WHERE
            upper(carn_name) = upper('CR Summer Series Melbourne 2024')
    ),
    (
        SELECT
            eventtype_code
        FROM
            eventtype
        WHERE
            upper(eventtype_desc) = upper('21.1 Km Half Marathon')
    ),
    NULL
);

COMMIT;

/*
(c)
*/

UPDATE entry
SET
    eventtype_code = (
        SELECT
            eventtype_code
        FROM
            eventtype
        WHERE
            upper(eventtype_desc) = upper('10 Km Run')
    )
WHERE
    entry_id = (
        SELECT
            entry_id
        FROM
            entry
        WHERE
                comp_no = (
                    SELECT
                        comp_no
                    FROM
                        competitor
                    WHERE
                            upper(comp_fname) = upper('Brigid')
                        AND upper(comp_lname) = upper('Radcliffe')
                        AND comp_phone = '1234567890'
                )
            AND carn_date = (
                SELECT
                    carn_date
                FROM
                    carnival
                WHERE
                    upper(carn_name) = upper('CR Summer Series Melbourne 2024')
            )
    );

INSERT INTO team (
    team_id,
    team_name,
    carn_date,
    team_no_members,
    char_name,
    entry_id
) VALUES (
    team_seq.NEXTVAL,
    'Kenya Speedstars',
    (
        SELECT
            carn_date
        FROM
            carnival
        WHERE
            upper(carn_name) = upper('CR Summer Series Melbourne 2024')
    ),
    1,
    'Beyond Blue',
    (
        SELECT
            entry_id
        FROM
            entry
        WHERE
                comp_no = (
                    SELECT
                        comp_no
                    FROM
                        competitor
                    WHERE
                            upper(comp_fname) = upper('Brigid')
                        AND upper(comp_lname) = upper('Radcliffe')
                        AND comp_phone = '1234567890'
                )
            AND carn_date = (
                SELECT
                    carn_date
                FROM
                    carnival
                WHERE
                    upper(carn_name) = upper('CR Summer Series Melbourne 2024')
            )
    )
);

UPDATE entry
SET
    team_id = (
        SELECT
            team_id
        FROM
            team
        WHERE
                team_name = 'Kenya Speedstars'
            AND carn_date = (
                SELECT
                    carn_date
                FROM
                    carnival
                WHERE
                    upper(carn_name) = upper('CR Summer Series Melbourne 2024')
            )
    )
WHERE
    entry_id = (
        SELECT
            entry_id
        FROM
            entry
        WHERE
                comp_no = (
                    SELECT
                        comp_no
                    FROM
                        competitor
                    WHERE
                            upper(comp_fname) = upper('Brigid')
                        AND upper(comp_lname) = upper('Radcliffe')
                        AND comp_phone = '1234567890'
                )
            AND carn_date = (
                SELECT
                    carn_date
                FROM
                    carnival
                WHERE
                    upper(carn_name) = upper('CR Summer Series Melbourne 2024')
            )
    );

COMMIT;

/*
(d) 
*/

UPDATE entry
SET
    team_id = NULL
WHERE
    team_id = (
        SELECT
            team_id
        FROM
            team
        WHERE
                team_name = 'Kenya Speedstars'
            AND carn_date = (
                SELECT
                    carn_date
                FROM
                    carnival
                WHERE
                    upper(carn_name) = upper('CR Summer Series Melbourne 2024')
            )
    );

DELETE FROM team
WHERE
    team_id = (
        SELECT
            team_id
        FROM
            team
        WHERE
                team_name = 'Kenya Speedstars'
            AND carn_date = (
                SELECT
                    carn_date
                FROM
                    carnival
                WHERE
                    upper(carn_name) = upper('CR Summer Series Melbourne 2024')
            )
    );

DELETE FROM entry
WHERE
        comp_no = (
            SELECT
                comp_no
            FROM
                competitor
            WHERE
                    upper(comp_fname) = upper('Brigid')
                AND upper(comp_lname) = upper('Radcliffe')
                AND comp_phone = '1234567890'
        )
    AND carn_date = (
        SELECT
            carn_date
        FROM
            carnival
        WHERE
            upper(carn_name) = upper('CR Summer Series Melbourne 2024')
    );

COMMIT;