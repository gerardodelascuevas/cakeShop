
CREATE TABLE flavor (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) UNIQUE NOT NULL
);


INSERT INTO flavor (name) VALUES
('CHOCOLAT'),
('VANILLA'),
('NUTS'),
('CARROT'),
('LEMON');


CREATE TABLE order_flavors (
    order_id BIGINT NOT NULL,
    flavor_id INT NOT NULL,
    PRIMARY KEY (order_id, flavor_id),
    FOREIGN KEY (order_id) REFERENCES "order"(id) ON DELETE CASCADE,
    FOREIGN KEY (flavor_id) REFERENCES flavor(id) ON DELETE CASCADE
);
