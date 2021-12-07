BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS user_league;
DROP TABLE IF EXISTS leagues;
DROP TABLE IF EXISTS courses;
DROP SEQUENCE IF EXISTS seq_user_id;
DROP SEQUENCE IF EXISTS seq_league_id;
DROP SEQUENCE IF EXISTS seq_course_id;

CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

CREATE SEQUENCE seq_league_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

CREATE SEQUENCE seq_course_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;



CREATE TABLE users (
	user_id int DEFAULT nextval('seq_user_id'::regclass) NOT NULL,
	username varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE courses (
    course_id int DEFAULT nextval('seq_course_id'::regclass) NOT NULL,
    course_name varchar(50) NOT NULL,
    coordinate varchar(50) NOT NULL,
    CONSTRAINT PK_course PRIMARY KEY (course_id)
);

CREATE TABLE leagues (
    league_id int DEFAULT nextval('seq_league_id'::regclass) NOT NULL,
    league_name varchar(50) NOT NULL,
    league_admin int NOT NULL,
    league_course int NOT NULL,
    day_of_week varchar(20),
    isActive boolean DEFAULT true,
    CONSTRAINT PK_league PRIMARY KEY (league_id),
    CONSTRAINT FK_leagues_league_course FOREIGN KEY(league_course) REFERENCES courses(course_id),
    CONSTRAINT FK_league_league_admin FOREIGN KEY(league_admin) REFERENCES users(user_id)
);

CREATE TABLE user_league (
    user_id int NOT NULL,
    league_id int NOT NULL,
    CONSTRAINT PK_user_league PRIMARY KEY(user_id, league_id),
    CONSTRAINT FK_user_league_user FOREIGN KEY(user_id) REFERENCES users(user_id),
    CONSTRAINT FK_user_league_league FOREIGN KEY(league_id) REFERENCES leagues(league_id)
);

CREATE TABLE scores (
    user_id int NOT NULL,
    course_id int NOT NULL,
    league_id int,
    score int NOT NULL,
    date_played date NOT NULL,
    CONSTRAINT FK_scores_user FOREIGN KEY(user_id) REFERENCES users(user_id),
    CONSTRAINT FK_scores_course FOREIGN KEY(course_id) REFERENCES courses(course_id),
    CONSTRAINT FK_scores_league FOREIGN KEY(league_id) REFERENCES leagues(league_id)

);



INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');


COMMIT TRANSACTION;


