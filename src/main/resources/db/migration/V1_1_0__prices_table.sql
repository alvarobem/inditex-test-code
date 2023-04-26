CREATE TABLE inditex_code_challenge.prices (
    id int NOT NULL AUTO_INCREMENT,
    start_date datetime NOT NULL,
    end_date datetime NOT NULL,
    price decimal NOT NULL,
    price_list int NOT NULL,
    product_id bigint NOT NULL,
    brand_id bigint NOT NULL,
    priority int NOT NULL,
    currency varchar(3) NOT NULL
)