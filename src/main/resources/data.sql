INSERT INTO papel (id, created_at, created_by, last_modify_at, last_modify_by, nome) VALUES
 (1, CURRENT_TIMESTAMP, NULL, CURRENT_TIMESTAMP, NULL, 'ADM'),
 (2, CURRENT_TIMESTAMP, NULL, CURRENT_TIMESTAMP, NULL, 'PEDAGOGICO'),
 (3, CURRENT_TIMESTAMP, NULL, CURRENT_TIMESTAMP, NULL, 'RECRUITER'),
 (4, CURRENT_TIMESTAMP, NULL, CURRENT_TIMESTAMP, NULL, 'PROFESSOR'),
 (5, CURRENT_TIMESTAMP, NULL, CURRENT_TIMESTAMP, NULL, 'ALUNO')
ON CONFLICT (id) DO NOTHING;