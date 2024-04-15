/******PLEASE ENTER YOUR DETAILS BELOW******/
/*T4-cr-queries.sql*/

/* ITO Assignment 2 Task 4*/

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
SELECT
    to_char(ca.carn_date, 'DY DD Month YYYY') AS carnival_date,
    ca.carn_name                              AS carnname,
    et.eventtype_desc                         AS eventtypedesc,
    co.comp_fname
    || ' '
    || co.comp_lname                          AS fullname
FROM
         competitor co
    JOIN entry     e
    ON co.comp_no = e.comp_no
    JOIN carnival  ca
    ON e.carn_date = ca.carn_date
    JOIN eventtype et
    ON e.eventtype_code = et.eventtype_code
WHERE
    lower(co.comp_email) LIKE '%@gmail.com%'
ORDER BY
    ca.carn_date,
    fullname;

/*
(b)
*/
SELECT
    c.carn_date,
    co.comp_fname
    || ' '
    || co.comp_lname AS runner,
    cha.char_name,
    cha.char_contact,
    et.eventtype_desc
FROM
         competitor co
    JOIN entry     e
    ON co.comp_no = e.comp_no
    JOIN carnival  c
    ON e.carn_date = c.carn_date
    JOIN eventtype et
    ON e.eventtype_code = et.eventtype_code
    JOIN charity   cha
    ON e.char_name = cha.char_name
WHERE
        upper(et.eventtype_desc) = upper('42.2 km Marathon')
    AND e.char_name IS NOT NULL
ORDER BY
    c.carn_date,
    cha.char_name,
    runner;

/*
(c)
*/
SELECT
    compno,
    compfname,
    complname,
    compgender,
    twoyrsback,
    lastcalyear,
    CASE
        WHEN twoyrsback + lastcalyear = 0 THEN
            'Completed No Runs'
        ELSE
            to_char(twoyrsback + lastcalyear)
    END AS last2calendaryears
FROM
    (
        SELECT
            co.comp_no     AS compno,
            co.comp_fname  AS compfname,
            co.comp_lname  AS complname,
            co.comp_gender AS compgender,
            COUNT(
                CASE
                    WHEN to_char(e.carn_date, 'YYYY') = to_char(sysdate, 'YYYY') - 2 THEN
                        1
                END
            )              AS twoyrsback,
            COUNT(
                CASE
                    WHEN to_char(e.carn_date, 'YYYY') = to_char(sysdate, 'YYYY') - 1 THEN
                        1
                END
            )              AS lastcalyear
        FROM
                 competitor co
            JOIN entry e
            ON co.comp_no = e.comp_no
        GROUP BY
            co.comp_no,
            co.comp_fname,
            co.comp_lname,
            co.comp_gender
    )
ORDER BY
    last2calendaryears DESC,
    compno;

/*
(d) 
*/
SELECT
    to_char(c.carn_date, 'DD-Mon-YYYY') AS carnival_date,
    c.carn_name                         AS carnname,
    COUNT(*)                            AS total_entries5km
FROM
         carnival c
    JOIN entry     e
    ON c.carn_date = e.carn_date
    JOIN eventtype et
    ON e.eventtype_code = et.eventtype_code
WHERE
        to_char(c.carn_date, 'YYYY') = '2023'
    AND upper(et.eventtype_desc) = upper('5 Km Run')
GROUP BY
    c.carn_date,
    c.carn_name
ORDER BY
    total_entries5km DESC,
    c.carn_date;

/* 
(e) 
*/

SELECT
    to_char(c.carn_date, 'DD-Mon-YYYY') AS carnival_date,
    c.carn_name                         AS carnname,
    et.eventtype_desc                   AS eventtypedesc
FROM
         carnival c
    JOIN event     ev
    ON c.carn_date = ev.carn_date
    JOIN eventtype et
    ON ev.eventtype_code = et.eventtype_code
WHERE
        c.carn_date < sysdate
    AND c.carn_date NOT IN (
        SELECT
            e.carn_date
        FROM
            entry e
        WHERE
            e.eventtype_code = ev.eventtype_code
    )
GROUP BY
    c.carn_date,
    c.carn_name,
    et.eventtype_desc
ORDER BY
    TO_DATE(c.carn_date, 'DD-Mon-YYYY'),
    eventtypedesc;
    
/*
(f)
*/
SELECT
    t.team_name                         AS teamname,
    to_char(t.carn_date, 'DD-Mon-YYYY') AS carnivaldate,
    lpad(e.comp_no, 4, '0')
    || ' '
    || c.comp_fname
    || ' '
    || c.comp_lname                     AS teamleader,
    t.team_no_members                   AS teamnomembers
FROM
         entry e
    JOIN team       t
    ON e.entry_id = t.entry_id
    JOIN competitor c
    ON e.comp_no = c.comp_no
    JOIN (
        SELECT
            team_name,
            COUNT(*) AS team_count
        FROM
            team
        GROUP BY
            team_name
    )          o
    ON t.team_name = o.team_name
WHERE
    team_count = (
        SELECT
            MAX(team_count)
        FROM
            (
                SELECT
                    team_name,
                    COUNT(*) AS team_count
                FROM
                    team
                GROUP BY
                    team_name
            )
    )
ORDER BY
    teamname,
    t.carn_date;