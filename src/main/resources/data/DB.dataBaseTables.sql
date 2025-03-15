CREATE TABLE timezones (
                           id INT AUTO_INCREMENT PRIMARY KEY,
                           timezone_name VARCHAR(50) NOT NULL
);

CREATE TABLE countries (
                           id INT AUTO_INCREMENT PRIMARY KEY,
                           iso_code CHAR(2) NOT NULL UNIQUE,
                           name VARCHAR(100) NOT NULL,
                           timezone_id INT NOT NULL,
                           FOREIGN KEY (timezone_id) REFERENCES timezones(id) ON DELETE CASCADE
);

CREATE TABLE cities (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(100) NOT NULL,
                        country_id INT NOT NULL,
                        FOREIGN KEY (country_id) REFERENCES countries(id) ON DELETE CASCADE
);

CREATE TABLE bank_names (
                            id INT AUTO_INCREMENT PRIMARY KEY,
                            name VARCHAR(100) NOT NULL,
                            country_id INT NOT NULL,
                            FOREIGN KEY (country_id) REFERENCES countries(id) ON DELETE CASCADE
);

CREATE TABLE swift_codes (
                             id INT AUTO_INCREMENT PRIMARY KEY,
                             bank_id INT NOT NULL,
                             city_id INT NOT NULL,
                             swift_code VARCHAR(11) NOT NULL UNIQUE,
                             is_headquarter BOOLEAN NOT NULL DEFAULT FALSE,
                             address VARCHAR(200),
                             FOREIGN KEY (bank_id) REFERENCES bank_names(id) ON DELETE CASCADE,
                             FOREIGN KEY (city_id) REFERENCES cities(id) ON DELETE CASCADE
);
