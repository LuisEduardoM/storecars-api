CREATE TABLE vehicle(
    id BIGINT PRIMARY KEY,
    owner_id BIGINT,
    license_plate VARCHAR(10) NOT NULL,
    category VARCHAR(50) NOT NULL,
    color VARCHAR(20) NOT NULL,
    year_model VARCHAR(10) NOT NULL,
    car_brand VARCHAR (30) NOT NULL,
    FOREIGN KEY(owner_id) REFERENCES owner(id),
    UNIQUE (license_plate)
);