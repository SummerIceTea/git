DROP TABLE IF EXISTS mylibrary;
DROP TABLE IF EXISTS user;



CREATE TABLE user
(
    id INT PRIMARY KEY AUTO_INCREMENT,
    login_id VARCHAR(50) NOT NULL,
    name VARCHAR(50) NOT NULL,
    password VARCHAR(100) NOT NULL
);

CREATE TABLE mylibrary
(
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    title VARCHAR(100) NOT NULL,
    author VARCHAR(100)NOT NULL,
    shop_name VARCHAR(100),
    buy_date DATE,
    comments VARCHAR(255),
    FOREIGN KEY(user_id) REFERENCES user(id)
);