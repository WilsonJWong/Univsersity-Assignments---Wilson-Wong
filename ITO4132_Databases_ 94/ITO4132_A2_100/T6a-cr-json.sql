/******PLEASE ENTER YOUR DETAILS BELOW******/
/*T6a-cr-json.sql*/

/* ITO Assignment 2 Task 6a*/

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

SET PAGESIZE 300

SELECT
    JSON_OBJECT(
        '_id' VALUE to_char(c.carn_date, 'DDMMYYYY')
                    || '_'
                    || et.eventtype_code,
                'carnival_date' VALUE to_char(c.carn_date, 'DD-Mon-YYYY'),
                'carnival_location' VALUE c.carn_location,
                'event' VALUE
            JSON_OBJECT(
                'type' VALUE et.eventtype_code,
                'description' VALUE et.eventtype_desc
            ),
                'no_competitors' VALUE COUNT(*),
                'competitors' VALUE JSON_ARRAYAGG(
            JSON_OBJECT(
                'comp_no' VALUE e.comp_no,
                'name' VALUE c.comp_fname
                             || ' '
                             || c.comp_lname,
                'gender' VALUE c.comp_gender,
                'phone' VALUE c.comp_phone
            )
        )
    FORMAT JSON)
FROM
         entry e
    JOIN competitor c
    ON e.comp_no = c.comp_no
    JOIN eventtype  et
    ON e.eventtype_code = et.eventtype_code
    JOIN carnival   c
    ON e.carn_date = c.carn_date
GROUP BY
    to_char(c.carn_date, 'DDMMYYYY')
    || '_'
    || et.eventtype_code,
    c.carn_date,
    et.eventtype_code,
    c.carn_location,
    et.eventtype_desc
ORDER BY
    c.carn_date,
    et.eventtype_code;