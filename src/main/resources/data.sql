CREATE EXTENSION IF NOT EXISTS pgcrypto;

--TRUNCATE TABLE role RESTART IDENTITY CASCADE;
-- Insert sample data into role
INSERT INTO role (id, created_at, last_modify_at, name)
VALUES (1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'ADMINISTRATOR'),
       (2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'PEDAGOGICO'),
       (3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'RECRUITER'),
       (4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'teacher'),
       (5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'student')
ON CONFLICT (id) DO NOTHING;


--TRUNCATE TABLE "user" RESTART IDENTITY CASCADE;

INSERT INTO "user" (created_at, last_modify_at, password, username, name, email, id_role, image)
VALUES (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, crypt('admin123', gen_salt('bf')),
        'admin', 'Heverton L. Roieski','admin@admin.com', 1,
        'data:image/jpeg;base64,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'
        )
ON CONFLICT (username) DO NOTHING;

--TRUNCATE TABLE course RESTART IDENTITY CASCADE;

DO '
BEGIN
    IF NOT EXISTS (SELECT 1 FROM course WHERE name = ''Engenharia de Software'') THEN
        INSERT INTO course (name, created_at, last_modify_at) VALUES (''Engenharia de Software'', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
    END IF;
    IF NOT EXISTS (SELECT 1 FROM course WHERE name = ''Medicina'') THEN
        INSERT INTO course (name, created_at, last_modify_at) VALUES (''Medicina'', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
    END IF;
    IF NOT EXISTS (SELECT 1 FROM course WHERE name = ''Direito'') THEN
        INSERT INTO course (name, created_at, last_modify_at) VALUES (''Direito'', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
    END IF;
    IF NOT EXISTS (SELECT 1 FROM course WHERE name = ''Administração'') THEN
        INSERT INTO course (name, created_at, last_modify_at) VALUES (''Administração'', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
    END IF;
END ';

--TRUNCATE TABLE subject RESTART IDENTITY CASCADE;

DO '
BEGIN
    IF NOT EXISTS (SELECT 1 FROM subject WHERE name = ''Matemática'' AND id_course = 1) THEN
        INSERT INTO subject (created_at, last_modify_at, name, id_course) VALUES (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, ''Matemática'', 1);
    END IF;
    IF NOT EXISTS (SELECT 1 FROM subject WHERE name = ''História'' AND id_course = 1) THEN
        INSERT INTO subject (created_at, last_modify_at, name, id_course) VALUES (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, ''História'', 1);
    END IF;
    IF NOT EXISTS (SELECT 1 FROM subject WHERE name = ''Química'' AND id_course = 1) THEN
        INSERT INTO subject (created_at, last_modify_at, name, id_course) VALUES (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, ''Química'', 1);
    END IF;
    IF NOT EXISTS (SELECT 1 FROM subject WHERE name = ''Física'' AND id_course = 1) THEN
        INSERT INTO subject (created_at, last_modify_at, name, id_course) VALUES (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, ''Física'', 1);
    END IF;
    IF NOT EXISTS (SELECT 1 FROM subject WHERE name = ''Inglês'' AND id_course = 1) THEN
        INSERT INTO subject (created_at, last_modify_at, name, id_course) VALUES (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, ''Inglês'', 1);
    END IF;
    IF NOT EXISTS (SELECT 1 FROM subject WHERE name = ''Ciências'' AND id_course = 1) THEN
        INSERT INTO subject (created_at, last_modify_at, name, id_course) VALUES (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, ''Ciências'', 1);
    END IF;
END ';

--TRUNCATE TABLE teacher RESTART IDENTITY CASCADE;

DO '
BEGIN
    -- Insert new teacher users
    WITH new_teacher_users AS (
        INSERT INTO "user" (created_at, last_modify_at, password, username, name, email, id_role)
        VALUES
            (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, crypt(''password123'', gen_salt(''bf'')), ''joao.silva'',''João Silva'', ''joao.silva@example.com'', 4),
            (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, crypt(''S3cur3Pass'', gen_salt(''bf'')), ''maria.oliveira'',''Maria Oliveira'', ''maria.oliveira@example.com'', 4),
            (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, crypt(''password123'', gen_salt(''bf'')), ''carlos.pereira'',''Carlos Pereira'', ''carlos.pereira@example.com'', 4),
            (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, crypt(''password123'', gen_salt(''bf'')), ''ana.costa'',''Ana Costa'', ''ana.costa@example.com'', 4),
            (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, crypt(''password123'', gen_salt(''bf'')), ''eduardo.lima'',''Eduardo Lima'', ''eduardo.lima@example.com'', 4),
            (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, crypt(''password123'', gen_salt(''bf'')), ''fernanda.oliveira'',''Fernanda Oliveira'', ''fernanda.oliveira@example.com'', 4),
            (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, crypt(''password123'', gen_salt(''bf'')), ''gabriel.santos'',''Gabriel Santos'', ''gabriel.santos@example.com'', 4),
            (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, crypt(''password123'', gen_salt(''bf'')), ''helena.martins'',''Helena Martins'', ''helena.martins@example.com'', 4),
            (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, crypt(''S3cur3Pass'', gen_salt(''bf'')), ''maria.souza'',''Maria Souza'', ''maria.souza@example.com'', 4)
        ON CONFLICT (username) DO NOTHING
        RETURNING id, username
    )
    INSERT INTO teacher (created_at, last_modify_at, name, id_user, birthday, cpf, gender, phone, rg, civil_state, nationality)
    SELECT CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, ''João Silva'', id, CAST(''1980-01-01'' AS DATE), ''123.456.789-00'', 0, ''(11) 1234-5678'', ''SP-123.456.789-00'', ''SINGLE'', ''Brazil'' FROM new_teacher_users WHERE username = ''joao.silva''
    UNION ALL
    SELECT CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, ''Maria Oliveira'', id, CAST(''1985-02-15'' AS DATE), ''234.567.890-01'', 1, ''(21) 2345-6789'', ''RJ-234.567.890-01'', ''MARRIED'', ''Brazil'' FROM new_teacher_users WHERE username = ''maria.oliveira''
    UNION ALL
    SELECT CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, ''Carlos Pereira'', id, CAST(''1990-03-20'' AS DATE), ''345.678.901-02'', 0, ''(31) 3456-7890'', ''MG-345.678.901-02'', ''DIVORCED'', ''Brazil'' FROM new_teacher_users WHERE username = ''carlos.pereira''
    UNION ALL
    SELECT CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, ''Ana Costa'', id, CAST(''1995-04-25'' AS DATE), ''456.789.012-03'', 1, ''(41) 4567-8901'', ''PR-456.789.012-03'', ''WIDOWED'', ''Brazil'' FROM new_teacher_users WHERE username = ''ana.costa''
    UNION ALL
    SELECT CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, ''Eduardo Lima'', id, CAST(''2000-05-30'' AS DATE), ''567.890.123-04'', 0, ''(51) 5678-9012'', ''RS-567.890.123-04'', ''SEPARATED'', ''Brazil'' FROM new_teacher_users WHERE username = ''eduardo.lima''
    UNION ALL
    SELECT CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, ''Fernanda Oliveira'', id, CAST(''1982-06-10'' AS DATE), ''678.901.234-05'', 1, ''(61) 6789-0123'', ''DF-678.901.234-05'', ''SINGLE'', ''Brazil'' FROM new_teacher_users WHERE username = ''fernanda.oliveira''
    UNION ALL
    SELECT CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, ''Gabriel Santos'', id, CAST(''1987-07-15'' AS DATE), ''789.012.345-06'', 0, ''(71) 7890-1234'', ''BA-789.012.345-06'', ''MARRIED'', ''Brazil'' FROM new_teacher_users WHERE username = ''gabriel.santos''
    UNION ALL
    SELECT CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, ''Helena Martins'', id, CAST(''1992-08-20'' AS DATE), ''890.123.456-07'', 1, ''(81) 8901-2345'', ''PE-890.123.456-07'', ''DIVORCED'', ''Brazil'' FROM new_teacher_users WHERE username = ''helena.martins''
    UNION ALL
    SELECT CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, ''Maria Souza'', id, CAST(''1997-09-25'' AS DATE), ''901.234.567-08'', 1, ''(91) 9012-3456'', ''AM-901.234.567-08'', ''WIDOWED'', ''Brazil'' FROM new_teacher_users WHERE username = ''maria.souza''
    ON CONFLICT (id_user) DO NOTHING;
END ';



--TRUNCATE TABLE classroom RESTART IDENTITY CASCADE;

DO '
BEGIN
    IF NOT EXISTS (SELECT 1 FROM classroom WHERE name = ''Matemática'' AND id_course = (SELECT id FROM course WHERE name = ''Engenharia de Software'')) THEN
        INSERT INTO classroom (created_at, last_modify_at, "name", id_course)
        VALUES (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, ''Matemática'', (SELECT id FROM course WHERE name = ''Engenharia de Software''));
    END IF;
    IF NOT EXISTS (SELECT 1 FROM classroom WHERE name = ''História'' AND id_course = (SELECT id FROM course WHERE name = ''Medicina'')) THEN
        INSERT INTO classroom (created_at, last_modify_at, "name", id_course)
        VALUES (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, ''História'', (SELECT id FROM course WHERE name = ''Medicina''));
    END IF;
    IF NOT EXISTS (SELECT 1 FROM classroom WHERE name = ''Química'' AND id_course = (SELECT id FROM course WHERE name = ''Direito'')) THEN
        INSERT INTO classroom (created_at, last_modify_at, "name", id_course)
        VALUES (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, ''Química'', (SELECT id FROM course WHERE name = ''Direito''));
    END IF;
    IF NOT EXISTS (SELECT 1 FROM classroom WHERE name = ''Física'' AND id_course = (SELECT id FROM course WHERE name = ''Administração'')) THEN
        INSERT INTO classroom (created_at, last_modify_at, "name", id_course)
        VALUES (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, ''Física'', (SELECT id FROM course WHERE name = ''Administração''));
    END IF;
    IF NOT EXISTS (SELECT 1 FROM classroom WHERE name = ''Inglês'' AND id_course = (SELECT id FROM course WHERE name = ''Engenharia de Software'')) THEN
        INSERT INTO classroom (created_at, last_modify_at, "name", id_course)
        VALUES (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, ''Inglês'', (SELECT id FROM course WHERE name = ''Engenharia de Software''));
    END IF;
    IF NOT EXISTS (SELECT 1 FROM classroom WHERE name = ''teste classroom'' AND id_course = (SELECT id FROM course WHERE name = ''Engenharia de Software'')) THEN
        INSERT INTO classroom (created_at, last_modify_at, "name", id_course)
        VALUES (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, ''teste classroom'', (SELECT id FROM course WHERE name = ''Engenharia de Software''));
    END IF;

    INSERT INTO teacher_classroom (id_classroom, id_teacher)
      SELECT classroom.id, teacher.id
      FROM classroom
      JOIN teacher ON teacher.name = CASE
          WHEN classroom.name = ''Matemática'' THEN ''João Silva''
          WHEN classroom.name = ''História'' THEN ''Maria Oliveira''
          WHEN classroom.name = ''Química'' THEN ''Carlos Pereira''
          WHEN classroom.name = ''Física'' THEN ''Ana Costa''
          WHEN classroom.name = ''Inglês'' THEN ''Eduardo Lima''
          WHEN classroom.name = ''teste classroom'' THEN ''Carlos Pereira''
      END;

END ';


--TRUNCATE TABLE student RESTART IDENTITY CASCADE;

WITH new_users AS (
    INSERT INTO "user" (created_at, last_modify_at, email, image, password, username, name, id_role)
    VALUES
        (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'ana.silva@example.com', 'https://randomuser.me/api/portraits/women/1.jpg', crypt('password123', gen_salt('bf')), 'ana.silva', 'Ana Silva', 5),
        (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'carlos.souza@example.com', 'https://randomuser.me/api/portraits/men/2.jpg', crypt('password456', gen_salt('bf')), 'carlos.souza', 'Carlos Souza', 5),
        (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'beatriz.lima@example.com', 'https://randomuser.me/api/portraits/women/3.jpg', crypt('password789', gen_salt('bf')), 'beatriz.lima', 'Beatriz Lima', 5),
        (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'daniel.oliveira@example.com', 'https://randomuser.me/api/portraits/men/4.jpg', crypt('password321', gen_salt('bf')), 'daniel.oliveira', 'Daniel Oliveira', 5),
        (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'eduarda.santos@example.com', 'https://randomuser.me/api/portraits/women/5.jpg', crypt('password654', gen_salt('bf')), 'eduarda.santos', 'Eduarda Santos', 5),
        (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'felipe.costa@example.com', 'https://randomuser.me/api/portraits/men/6.jpg', crypt('password987', gen_salt('bf')), 'felipe.costa', 'Felipe Costa', 5),
        (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'gabriela.almeida@example.com', 'https://randomuser.me/api/portraits/women/7.jpg', crypt('password321', gen_salt('bf')), 'gabriela.almeida', 'Gabriela Almeida', 5),
        (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'henrique.pereira@example.com', 'https://randomuser.me/api/portraits/men/8.jpg', crypt('password654', gen_salt('bf')), 'henrique.pereira', 'Henrique Pereira', 5),
        (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'isabela.rocha@example.com', 'https://randomuser.me/api/portraits/women/9.jpg', crypt('password987', gen_salt('bf')), 'isabela.rocha', 'Isabela Rocha', 5),
        (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'joao.mendes@example.com', 'https://randomuser.me/api/portraits/men/10.jpg', crypt('password123', gen_salt('bf')), 'joao.mendes', 'João Mendes', 5)
    ON CONFLICT (username) DO NOTHING
    RETURNING id, username
)
INSERT INTO student (created_at, last_modify_at, name, birthday, cpf, gender, phone, rg, id_user, id_classroom, placeofbirth)
SELECT CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Ana Silva', CAST('2000-05-15' AS DATE), '123.456.789-00', 0, '(11) 1234-5678', 'SP-123.456.789-00', id, 1, 'São Paulo' FROM new_users WHERE username = 'ana.silva'
UNION ALL
SELECT CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Carlos Souza', CAST('1999-08-22' AS DATE), '234.567.890-01', 0, '(21) 2345-6789', 'RJ-234.567.890-01', id, 1, 'Rio de Janeiro' FROM new_users WHERE username = 'carlos.souza'
UNION ALL
SELECT CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Beatriz Lima', CAST('2001-03-10' AS DATE), '345.678.901-02', 1, '(31) 3456-7890', 'MG-345.678.901-02', id, 1, 'Belo Horizonte' FROM new_users WHERE username = 'beatriz.lima'
UNION ALL
SELECT CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Daniel Oliveira', CAST('2002-07-25' AS DATE), '456.789.012-03', 0, '(41) 4567-8901', 'PR-456.789.012-03', id, 1, 'Curitiba' FROM new_users WHERE username = 'daniel.oliveira'
UNION ALL
SELECT CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Eduarda Santos', CAST('2000-11-30' AS DATE), '567.890.123-04', 1, '(51) 5678-9012', 'RS-567.890.123-04', id, 1, 'Porto Alegre' FROM new_users WHERE username = 'eduarda.santos'
UNION ALL
SELECT CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Felipe Costa', CAST('2001-01-15' AS DATE), '678.901.234-05', 0, '(61) 6789-0123', 'DF-678.901.234-05', id, 1, 'Brasília' FROM new_users WHERE username = 'felipe.costa'
UNION ALL
SELECT CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Gabriela Almeida', CAST('2002-09-05' AS DATE), '789.012.345-06', 1, '(71) 7890-1234', 'BA-789.012.345-06', id, 1, 'Salvador' FROM new_users WHERE username = 'gabriela.almeida'
UNION ALL
SELECT CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Henrique Pereira', CAST('2000-12-20' AS DATE), '890.123.456-07', 0, '(81) 8901-2345', 'PE-890.123.456-07', id, 1, 'Recife' FROM new_users WHERE username = 'henrique.pereira'
UNION ALL
SELECT CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Isabela Rocha', CAST('2001-06-18' AS DATE), '901.234.567-08', 1, '(91) 9012-3456', 'AM-901.234.567-08', id, 1, 'Manaus' FROM new_users WHERE username = 'isabela.rocha'
UNION ALL
SELECT CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'João Mendes', CAST('2002-04-22' AS DATE), '012.345.678-90', 0, '(21) 0123-4567', 'CE-012.345.678-90', id, 1, 'Fortaleza' FROM new_users WHERE username = 'joao.mendes';
