DROP TABLE IF EXISTS users, galleries, images, image_gallery CASCADE;
CREATE TABLE users (
    user_id 	SERIAL PRIMARY KEY,
    firstname   varchar(50),
    lastname    varchar(50),
    login       varchar(50) NOT NULL,
    email       varchar(50) NOT NULL,
    password    varchar(60) NOT NULL,
    created     timestamp,
    role 	varchar(50) NOT NULL
);

CREATE TABLE galleries (
    gallery_id 	SERIAL PRIMARY KEY,
    name        varchar(50) NOT NULL,
    user_id   int REFERENCES users(user_id),
    created     timestamp
);

CREATE TABLE images (
    image_id 	SERIAL PRIMARY KEY,
    name        varchar(50) NOT NULL,
    image       bytea,
    imageicon   bytea,
    uploaded    timestamp,
    user_id     int REFERENCES users(user_id)
);

CREATE TABLE image_gallery (
    id 	SERIAL PRIMARY KEY,
    gallery_id   int REFERENCES galleries(gallery_id),
    image_id   int REFERENCES images(image_id)
);