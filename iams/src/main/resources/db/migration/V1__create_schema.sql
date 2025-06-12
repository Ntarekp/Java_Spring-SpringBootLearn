-- Create schema for Industrial Attachment Management System
CREATE TABLE roles (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       email VARCHAR(255) UNIQUE NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       role_id INTEGER NOT NULL,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       FOREIGN KEY (role_id) REFERENCES roles(id)
);

CREATE TABLE students (
                          id SERIAL PRIMARY KEY,
                          user_id INTEGER UNIQUE NOT NULL,
                          first_name VARCHAR(100) NOT NULL,
                          last_name VARCHAR(100) NOT NULL,
                          course VARCHAR(100),
                          year_of_study INTEGER,
                          skills TEXT,
                          cv_path VARCHAR(255),
                          FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE companies (
                           id SERIAL PRIMARY KEY,
                           user_id INTEGER UNIQUE NOT NULL,
                           name VARCHAR(255) NOT NULL,
                           description TEXT,
                           is_approved BOOLEAN DEFAULT FALSE,
                           FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE opportunities (
                               id SERIAL PRIMARY KEY,
                               company_id INTEGER NOT NULL,
                               title VARCHAR(255) NOT NULL,
                               description TEXT,
                               deadline DATE NOT NULL,
                               slots INTEGER NOT NULL,
                               is_open BOOLEAN DEFAULT TRUE,
                               created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                               FOREIGN KEY (company_id) REFERENCES companies(id)
);

CREATE TABLE applications (
                              id SERIAL PRIMARY KEY,
                              student_id INTEGER NOT NULL,
                              opportunity_id INTEGER NOT NULL,
                              status VARCHAR(50) DEFAULT 'PENDING',
                              cover_letter_path VARCHAR(255),
                              created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                              updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                              FOREIGN KEY (student_id) REFERENCES students(id),
                              FOREIGN KEY (opportunity_id) REFERENCES opportunities(id)
);

CREATE TABLE notifications (
                               id SERIAL PRIMARY KEY,
                               user_id INTEGER NOT NULL,
                               message TEXT NOT NULL,
                               is_read BOOLEAN DEFAULT FALSE,
                               created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                               FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE audit_logs (
                            id SERIAL PRIMARY KEY,
                            user_id INTEGER,
                            action VARCHAR(255) NOT NULL,
                            entity_type VARCHAR(100),
                            entity_id INTEGER,
                            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                            FOREIGN KEY (user_id) REFERENCES users(id)
);