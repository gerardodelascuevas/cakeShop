CREATE TABLE size (
    id SERIAL PRIMARY KEY,
    size_name VARCHAR(255) NOT NULL,
    available BOOLEAN NOT NULL DEFAULT TRUE
);

INSERT INTO size (size_name, available) VALUES
('SIZE_10', TRUE),
('SIZE_15', TRUE),
('SIZE_25', FALSE);

ALTER TABLE "user" ADD CONSTRAINT unique_email UNIQUE (email);
