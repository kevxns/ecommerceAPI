CREATE TABLE products(
     id BIGSERIAL PRIMARY KEY,
     name VARCHAR(255) NOT NULL UNIQUE,
     description VARCHAR(70) NOT NULL,
     price DECIMAL(10, 2) NOT NULL,
     stock INTEGER NOT NULL,
     status VARCHAR(50) NOT NULL,
     created_at TIMESTAMP NOT NULL DEFAULT NOW(),
     updated_at TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE OR REPLACE FUNCTION update_updated_at_column()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = NOW();
RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_products_updated_at
    BEFORE UPDATE ON products
    FOR EACH ROW
EXECUTE FUNCTION update_updated_at_column();

