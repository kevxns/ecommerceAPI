CREATE TABLE order_items (
     id BIGSERIAL PRIMARY KEY,
     product_id BIGINT NOT NULL,
     quantity INTEGER NOT NULL,
     price NUMERIC(19, 2) NOT NULL,
     order_id BIGINT NOT NULL,

     CONSTRAINT fk_order_items_order
         FOREIGN KEY (order_id)
         REFERENCES orders (id)
         ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS idx_order_items_order_id
    ON order_items (order_id);
