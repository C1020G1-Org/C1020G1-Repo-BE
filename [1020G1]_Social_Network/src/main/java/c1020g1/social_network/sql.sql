CREATE TABLE province(
    province_id INT PRIMARY KEY AUTO_INCREMENT,
    province_name VARCHAR(50)
);

CREATE TABLE district(
    district_id INT PRIMARY KEY AUTO_INCREMENT,
    province_id INT NOT NULL,
    FOREIGN KEY (province_id) REFERENCES province.province_id,
    district_name VARCHAR(50),
);

CREATE TABLE ward(
    ward_id INT PRIMARY KEY  AUTO_INCREMENT,
    district_id INT NOT NULL ,
    FOREIGN KEY (district_id) REFERENCES district.district_id,
    ward_name VARCHAR(50),
);

CREATE TABLE favourite(
    favourite_id INT PRIMARY KEY AUTO_INCREMENT,
    favourite_name VARCHAR(50),
);

CREATE TABLE favourite_user(
    favourite_user_id INT PRIMARY KEY AUTO_INCREMENT,
    favourite_id INT NOT NULL ,
    FOREIGN KEY (favourite_id) REFERENCES favourite.favourite.id,
    user_id INT NOT NULL,
    FOREIGN KEY (user_id) REFEENCES user.user_id,
);