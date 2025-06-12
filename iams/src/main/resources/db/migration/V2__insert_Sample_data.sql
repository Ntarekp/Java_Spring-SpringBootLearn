-- Insert initial roles
INSERT INTO roles (name) VALUES ('STUDENT'), ('COMPANY'), ('COORDINATOR'), ('ADMIN');

-- Insert sample users
INSERT INTO users (email, password, role_id, created_at, updated_at)
VALUES
    ('student1@example.com', '$2a$10$3zHzV2z.9q3k3z3Qz3Qz3eQz3Qz3Qz3Qz3Qz3Qz3Qz3Qz3Qz3Qz3', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('company1@example.com', '$2a$10$3zHzV2z.9q3k3z3Qz3Qz3eQz3Qz3Qz3Qz3Qz3Qz3Qz3Qz3Qz3Qz3', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('admin1@example.com', '$2a$10$3zHzV2z.9q3k3z3Qz3Qz3eQz3Qz3Qz3Qz3Qz3Qz3Qz3Qz3Qz3Qz3', 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insert sample students
INSERT INTO students (user_id, first_name, last_name, course, year_of_study, skills, cv_path)
VALUES
    (1, 'John', 'Doe', 'Computer Science', 3, 'Java, Python', '/uploads/cv1.pdf');

-- Insert sample companies
INSERT INTO companies (user_id, name, description, is_approved)
VALUES
    (2, 'Tech Corp', 'Leading tech company', TRUE);

-- Insert sample opportunities
INSERT INTO opportunities (company_id, title, description, deadline, slots, is_open, created_at)
VALUES
    (1, 'Software Intern', 'Develop web applications', '2025-12-31', 5, TRUE, CURRENT_TIMESTAMP);

-- Insert sample application
INSERT INTO applications (student_id, opportunity_id, status, cover_letter_path, created_at, updated_at)
VALUES
    (1, 1, 'PENDING', '/uploads/cover1.pdf', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);