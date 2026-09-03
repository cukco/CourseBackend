DO $$
DECLARE
    i INT;
    v_student_id INT;
    v_course_id INT;
BEGIN
    -- Cleanup previous test students
    DELETE FROM student_enrollments WHERE student_id IN (SELECT id FROM students WHERE email LIKE 'student%@example.com');
    DELETE FROM students WHERE email LIKE 'student%@example.com';
    DELETE FROM users WHERE email LIKE 'student%@example.com';

    FOR i IN 1..50 LOOP
        -- Insert into students
        INSERT INTO students (email, name)
        VALUES ('student' || i || '@example.com', 'Học viên ' || i)
        RETURNING id INTO v_student_id;

        -- Insert into users
        INSERT INTO users (email, password, role)
        VALUES ('student' || i || '@example.com', '$2a$10$8Ltf6jYTNrxCipBh5mTlFuGkwg46AmxxYLjrQdzsOQnSE1xQP2/I6', 'ROLE_STUDENT');

        -- Enroll in 1 to 3 random courses
        FOR j IN 1..(1 + floor(random() * 3)) LOOP
            -- Select a random course ID from existing courses
            SELECT id INTO v_course_id FROM courses ORDER BY random() LIMIT 1;
            
            -- Insert enrollment
            BEGIN
                INSERT INTO student_enrollments (student_id, course_id, created_at)
                VALUES (v_student_id, v_course_id, NOW());
            EXCEPTION WHEN unique_violation THEN
                -- Do nothing if already enrolled
            END;
        END LOOP;
    END LOOP;
END $$;
