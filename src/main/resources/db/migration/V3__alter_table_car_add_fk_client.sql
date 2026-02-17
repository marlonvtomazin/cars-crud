-- Add client_id column
ALTER TABLE cars
    ADD COLUMN client_id BIGINT NOT NULL;

-- Add foreign key constraint
ALTER TABLE cars
    ADD CONSTRAINT fk_car_client
        FOREIGN KEY (client_id)
            REFERENCES clients(id)
            ON DELETE RESTRICT;

-- Optional: index for performance
CREATE INDEX idx_car_client ON cars(client_id);
