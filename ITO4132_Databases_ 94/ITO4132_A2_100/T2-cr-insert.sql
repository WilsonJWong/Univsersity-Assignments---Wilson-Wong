/******PLEASE ENTER YOUR DETAILS BELOW******/
/*T2-cr-insert.sql*/

/* ITO Assignment 2 Task 2*/

/*Student ID: 29704154*/
/*Student Name: Wilson Wong*/

/* Comments for your marker:

The time inserts include an accompanying date so that the data entry is 
consistent with the event date.

Assumption: event for 20/01/24 has yet to release their start times for 
each event

*/

/* ENSURE that your SQL code is formatted and has a semicolon (;)*/
/* at the end of every statement. When marked this will be run as*/
/* a script.*/

/* Insert into ENTRY*/

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
    1,
    TO_DATE('19/SEP/2021 12:00:00', 'DD/MON/YYYY HH24:MI:SS'),
    TO_DATE('19/SEP/2021 12:45:00', 'DD/MON/YYYY HH24:MI:SS'),
    'Beyond Blue',
    1,
    TO_DATE('19/SEP/2021', 'DD/MON/YYYY'),
    '10K',
    NULL
);

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
    2,
    TO_DATE('19/SEP/2021 12:00:00', 'DD/MON/YYYY HH24:MI:SS'),
    TO_DATE('19/SEP/2021 12:50:40', 'DD/MON/YYYY HH24:MI:SS'),
    NULL,
    2,
    TO_DATE('19/SEP/2021', 'DD/MON/YYYY'),
    '10K',
    NULL
);

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
    3,
    TO_DATE('19/SEP/2021 12:00:00', 'DD/MON/YYYY HH24:MI:SS'),
    TO_DATE('19/SEP/2021 13:02:20', 'DD/MON/YYYY HH24:MI:SS'),
    'RSPCA',
    3,
    TO_DATE('19/SEP/2021', 'DD/MON/YYYY'),
    '10K',
    NULL
);

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
    4,
    TO_DATE('19/SEP/2021 12:30:00', 'DD/MON/YYYY HH24:MI:SS'),
    TO_DATE('19/SEP/2021 12:59:23', 'DD/MON/YYYY HH24:MI:SS'),
    'Beyond Blue',
    4,
    TO_DATE('19/SEP/2021', 'DD/MON/YYYY'),
    '5K',
    NULL
);

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
    5,
    TO_DATE('04/SEP/2022 12:30:00', 'DD/MON/YYYY HH24:MI:SS'),
    TO_DATE('04/SEP/2022 13:00:00', 'DD/MON/YYYY HH24:MI:SS'),
    'Salvation Army',
    2,
    TO_DATE('04/SEP/2022', 'DD/MON/YYYY'),
    '5K',
    NULL
);

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
    6,
    TO_DATE('04/SEP/2022 12:30:00', 'DD/MON/YYYY HH24:MI:SS'),
    TO_DATE('04/SEP/2022 13:30:00', 'DD/MON/YYYY HH24:MI:SS'),
    'Salvation Army',
    5,
    TO_DATE('04/SEP/2022', 'DD/MON/YYYY'),
    '5K',
    NULL
);

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
    7,
    TO_DATE('01/FEB/2023 10:00:00', 'DD/MON/YYYY HH24:MI:SS'),
    TO_DATE('01/FEB/2023 11:00:00', 'DD/MON/YYYY HH24:MI:SS'),
    'Beyond Blue',
    1,
    TO_DATE('01/FEB/2023', 'DD/MON/YYYY'),
    '10K',
    NULL
);

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
    8,
    TO_DATE('01/FEB/2023 09:00:00', 'DD/MON/YYYY HH24:MI:SS'),
    TO_DATE('01/FEB/2023 11:15:00', 'DD/MON/YYYY HH24:MI:SS'),
    'Beyond Blue',
    2,
    TO_DATE('01/FEB/2023', 'DD/MON/YYYY'),
    '21K',
    NULL
);

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
    9,
    TO_DATE('01/FEB/2023 12:00:00', 'DD/MON/YYYY HH24:MI:SS'),
    TO_DATE('01/FEB/2023 12:40:20', 'DD/MON/YYYY HH24:MI:SS'),
    'Beyond Blue',
    6,
    TO_DATE('01/FEB/2023', 'DD/MON/YYYY'),
    '5K',
    NULL
);

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
    10,
    TO_DATE('01/FEB/2023 12:30:00', 'DD/MON/YYYY HH24:MI:SS'),
    TO_DATE('01/FEB/2023 12:50:00', 'DD/MON/YYYY HH24:MI:SS'),
    NULL,
    7,
    TO_DATE('01/FEB/2023', 'DD/MON/YYYY'),
    '3K',
    NULL
);

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
    11,
    TO_DATE('06/APR/2023 09:00:00', 'DD/MON/YYYY HH24:MI:SS'),
    TO_DATE('06/APR/2023 09:45:30', 'DD/MON/YYYY HH24:MI:SS'),
    'RSPCA',
    8,
    TO_DATE('06/APR/2023', 'DD/MON/YYYY'),
    '5K',
    NULL
);

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
    12,
    TO_DATE('06/APR/2023 09:00:00', 'DD/MON/YYYY HH24:MI:SS'),
    TO_DATE('06/APR/2023 09:35:20', 'DD/MON/YYYY HH24:MI:SS'),
    'RSPCA',
    9,
    TO_DATE('06/APR/2023', 'DD/MON/YYYY'),
    '5K',
    NULL
);

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
    13,
    TO_DATE('06/APR/2023 08:00:00', 'DD/MON/YYYY HH24:MI:SS'),
    TO_DATE('06/APR/2023 08:50:00', 'DD/MON/YYYY HH24:MI:SS'),
    'Salvation Army',
    7,
    TO_DATE('06/APR/2023', 'DD/MON/YYYY'),
    '21K',
    NULL
);

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
    14,
    TO_DATE('06/APR/2023 08:00:00', 'DD/MON/YYYY HH24:MI:SS'),
    TO_DATE('06/APR/2023 09:01:00', 'DD/MON/YYYY HH24:MI:SS'),
    'Salvation Army',
    10,
    TO_DATE('06/APR/2023', 'DD/MON/YYYY'),
    '21K',
    NULL
);

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
    15,
    TO_DATE('06/APR/2023 08:00:00', 'DD/MON/YYYY HH24:MI:SS'),
    TO_DATE('06/APR/2023 10:00:00', 'DD/MON/YYYY HH24:MI:SS'),
    'Salvation Army',
    1,
    TO_DATE('06/APR/2023', 'DD/MON/YYYY'),
    '21K',
    NULL
);

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
    16,
    TO_DATE('06/APR/2023 08:00:00', 'DD/MON/YYYY HH24:MI:SS'),
    TO_DATE('06/APR/2023 10:30:01', 'DD/MON/YYYY HH24:MI:SS'),
    NULL,
    11,
    TO_DATE('06/APR/2023', 'DD/MON/YYYY'),
    '21K',
    NULL
);

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
    17,
    TO_DATE('06/APR/2023 07:00:00', 'DD/MON/YYYY HH24:MI:SS'),
    NULL,
    'Salvation Army',
    2,
    TO_DATE('06/APR/2023', 'DD/MON/YYYY'),
    '42K',
    NULL
);

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
    18,
    TO_DATE('08/SEP/2023 10:00:00', 'DD/MON/YYYY HH24:MI:SS'),
    TO_DATE('08/SEP/2023 10:30:00', 'DD/MON/YYYY HH24:MI:SS'),
    'RSPCA',
    12,
    TO_DATE('08/SEP/2023', 'DD/MON/YYYY'),
    '5K',
    NULL
);

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
    19,
    TO_DATE('08/SEP/2023 10:00:00', 'DD/MON/YYYY HH24:MI:SS'),
    TO_DATE('08/SEP/2023 10:27:00', 'DD/MON/YYYY HH24:MI:SS'),
    'RSPCA',
    13,
    TO_DATE('08/SEP/2023', 'DD/MON/YYYY'),
    '5K',
    NULL
);

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
    20,
    TO_DATE('08/SEP/2023 08:00:00', 'DD/MON/YYYY HH24:MI:SS'),
    TO_DATE('08/SEP/2023 12:00:00', 'DD/MON/YYYY HH24:MI:SS'),
    'Amnesty International',
    2,
    TO_DATE('08/SEP/2023', 'DD/MON/YYYY'),
    '42K',
    NULL
);

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
    21,
    TO_DATE('08/SEP/2023 08:00:00', 'DD/MON/YYYY HH24:MI:SS'),
    TO_DATE('08/SEP/2023 12:30:00', 'DD/MON/YYYY HH24:MI:SS'),
    NULL,
    11,
    TO_DATE('08/SEP/2023', 'DD/MON/YYYY'),
    '42K',
    NULL
);

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
    22,
    NULL,
    NULL,
    NULL,
    13,
    TO_DATE('20/FEB/2024', 'DD/MON/YYYY'),
    '5K',
    NULL
);

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
    23,
    NULL,
    NULL,
    'Beyond Blue',
    14,
    TO_DATE('20/FEB/2024', 'DD/MON/YYYY'),
    '10K',
    NULL
);

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
    24,
    NULL,
    NULL,
    'Beyond Blue',
    1,
    TO_DATE('20/FEB/2024', 'DD/MON/YYYY'),
    '21K',
    NULL
);

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
    25,
    NULL,
    NULL,
    'Beyond Blue',
    2,
    TO_DATE('20/FEB/2024', 'DD/MON/YYYY'),
    '21K',
    NULL
);

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
    26,
    NULL,
    NULL,
    'Beyond Blue',
    11,
    TO_DATE('20/FEB/2024', 'DD/MON/YYYY'),
    '21K',
    NULL
);

/* Insert into TEAM*/

INSERT INTO team (
    team_id,
    team_name,
    carn_date,
    team_no_members,
    char_name,
    entry_id
) VALUES (
    1,
    'Astroid',
    TO_DATE('19/SEP/2021', 'DD/MON/YYYY'),
    2,
    'RSPCA',
    1
);

UPDATE entry
SET
    team_id = 1
WHERE
    entry_id IN ( 1, 2 );

INSERT INTO team (
    team_id,
    team_name,
    carn_date,
    team_no_members,
    char_name,
    entry_id
) VALUES (
    2,
    'Blast',
    TO_DATE('19/SEP/2021', 'DD/MON/YYYY'),
    1,
    'Beyond Blue',
    3
);

UPDATE entry
SET
    team_id = 2
WHERE
    entry_id = 3;

INSERT INTO team (
    team_id,
    team_name,
    carn_date,
    team_no_members,
    char_name,
    entry_id
) VALUES (
    3,
    'Astroid',
    TO_DATE('04/SEP/2022', 'DD/MON/YYYY'),
    2,
    'Salvation Army',
    5
);

UPDATE entry
SET
    team_id = 3
WHERE
    entry_id IN ( 5, 6 );

INSERT INTO team (
    team_id,
    team_name,
    carn_date,
    team_no_members,
    char_name,
    entry_id
) VALUES (
    4,
    'Canary',
    TO_DATE('01/FEB/2023', 'DD/MON/YYYY'),
    2,
    'Amnesty International',
    9
);

UPDATE entry
SET
    team_id = 4
WHERE
    entry_id IN ( 9, 10 );

INSERT INTO team (
    team_id,
    team_name,
    carn_date,
    team_no_members,
    char_name,
    entry_id
) VALUES (
    5,
    'Dusty',
    TO_DATE('06/APR/2023', 'DD/MON/YYYY'),
    3,
    NULL,
    14
);

UPDATE entry
SET
    team_id = 5
WHERE
    entry_id IN ( 14, 15, 16 );

INSERT INTO team (
    team_id,
    team_name,
    carn_date,
    team_no_members,
    char_name,
    entry_id
) VALUES (
    6,
    'Elves',
    TO_DATE('08/SEP/2023', 'DD/MON/YYYY'),
    2,
    NULL,
    18
);

UPDATE entry
SET
    team_id = 6
WHERE
    entry_id IN ( 18, 19 );

INSERT INTO team (
    team_id,
    team_name,
    carn_date,
    team_no_members,
    char_name,
    entry_id
) VALUES (
    7,
    'Fruits',
    TO_DATE('20/FEB/2024', 'DD/MON/YYYY'),
    1,
    'RSPCA',
    22
);

UPDATE entry
SET
    team_id = 7
WHERE
    entry_id = 22;

INSERT INTO team (
    team_id,
    team_name,
    carn_date,
    team_no_members,
    char_name,
    entry_id
) VALUES (
    8,
    'Blast',
    TO_DATE('20/FEB/2024', 'DD/MON/YYYY'),
    2,
    'RSPCA',
    24
);

UPDATE entry
SET
    team_id = 8
WHERE
    entry_id IN ( 24, 25, 26 );

COMMIT;