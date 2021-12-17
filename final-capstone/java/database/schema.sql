BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS user_league;
DROP TABLE IF EXISTS leagues;
DROP TABLE IF EXISTS courses;
DROP TABLE IF EXISTS tee_times;
DROP TABLE IF EXISTS user_tee_time;
DROP TABLE IF EXISTS scores;
DROP SEQUENCE IF EXISTS seq_user_id;
DROP SEQUENCE IF EXISTS seq_league_id;
DROP SEQUENCE IF EXISTS seq_tee_time_id;
DROP SEQUENCE IF EXISTS seq_course_id;

CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  NO MAXVALUE
  MINVALUE 0
  CACHE 1;

CREATE SEQUENCE seq_league_id
  INCREMENT BY 1
  NO MAXVALUE
  MINVALUE 0
  START WITH 6
  CACHE 1;

CREATE SEQUENCE seq_course_id
  INCREMENT BY 1
  NO MAXVALUE
  MINVALUE 0
  CACHE 1;

CREATE SEQUENCE seq_tee_time_id
  INCREMENT BY 1
  NO MAXVALUE
  MINVALUE 0
  CACHE 1
  START WITH 14;


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
	course_par int DEFAULT 0,
	course_length int DEFAULT 0,
    lng varchar(50) NOT NULL,
    lat varchar(50) NOT NULL,
	address varchar(50) DEFAULT '',
	city varchar(50) DEFAULT '',
	state varchar(50) DEFAULT '',
	zip varchar(10) DEFAULT '',
    CONSTRAINT PK_course PRIMARY KEY (course_id)
);

CREATE TABLE leagues (
    league_id int DEFAULT nextval('seq_league_id'::regclass) NOT NULL,
    league_name varchar(50) NOT NULL,
    league_admin int NOT NULL,
    league_course int NOT NULL,
    day_of_week varchar(20),
    active boolean DEFAULT true,
    CONSTRAINT PK_league PRIMARY KEY (league_id),
    CONSTRAINT FK_leagues_league_course FOREIGN KEY(league_course) REFERENCES courses(course_id),
    CONSTRAINT FK_league_league_admin FOREIGN KEY(league_admin) REFERENCES users(user_id)
);

CREATE TABLE tee_times (
    tee_time_id int DEFAULT nextval('seq_tee_time_id'::regclass) NOT NULL,
    tee_time_date varchar(50) NOT NULL,
    tee_time varchar(50) NOT NULL,
    CONSTRAINT PK_tee_time_id PRIMARY KEY(tee_time_id)
);

CREATE TABLE user_tee_time_score (
    user_id int NOT NULL,
    tee_time_id int NOT NULL,
    score int NOT NULL,
    CONSTRAINT PK_user_tee_time PRIMARY KEY(user_id, tee_time_id),
    CONSTRAINT FK_user_tee_time_user FOREIGN KEY(user_id) REFERENCES users(user_id),
    CONSTRAINT FK_user_tee_time_tee_time FOREIGN KEY(tee_time_id) REFERENCES tee_times(tee_time_id)
);

CREATE TABLE user_league (
    user_id int NOT NULL,
    league_id int NOT NULL,
    CONSTRAINT PK_user_league PRIMARY KEY(user_id, league_id),
    CONSTRAINT FK_user_league_user FOREIGN KEY(user_id) REFERENCES users(user_id),
    CONSTRAINT FK_user_league_league FOREIGN KEY(league_id) REFERENCES leagues(league_id)
);

CREATE TABLE tee_time_league (
    tee_time_id int NOT NULL,
    league_id int NOT NULL,
    CONSTRAINT PK_tee_time_league PRIMARY KEY(tee_time_id, league_id),
    CONSTRAINT FK_tee_time_league_tee_time FOREIGN KEY(tee_time_id) REFERENCES tee_times(tee_time_id),
    CONSTRAINT FK_tee_time_league_league FOREIGN KEY(league_id) REFERENCES leagues(league_id)
);


INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');
INSERT INTO users (username,password_hash,role) VALUES ('Tiger','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('Phil','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('Arnold','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('Jack','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('Ben','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('Rory','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('Gary','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('John','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('Walter','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('Jordan','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('Rickey','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('Bubba','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('Ernie','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('Zach','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('Brian','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('Gavin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('Andrew','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('Harry','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('Darth','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');


INSERT INTO courses ( course_name, course_par, course_length, lat, lng, address, city, state, zip )
	VALUES ( 'Hyde Park Country Club', 71, 6449, '39.145647' ,'-84.414353', '3740 Erie Ave', 'Cincinnati', 'OH', '45008' );
INSERT INTO courses ( course_name, course_par, course_length, lat, lng, address, city, state, zip )
	VALUES ('Jamaica Run Golf Club', 72, 6094, '39.617206321639166' ,' -84.33192808915973', '8781 Jamaica Rd', 'Germantown', 'OH', '45327' );
INSERT INTO courses ( course_name, course_par, course_length, lat, lng, address, city, state, zip )	
	VALUES ('The Golf Club at Yankee Trace', 69, 6077, '39.599330732123256' ,' -84.1976116215202', '10000 Yankee St', 'Washington Township', 'OH', '45458' );
INSERT INTO courses ( course_name, course_par, course_length, lat, lng, address, city, state, zip )	
	VALUES ('Heatherwoode Golf Club', 71, 6001, '39.5395770067503' ,' -84.23117233200432', '88 Heatherwoode Blvd', 'Springboro', 'OH', '45066' );
INSERT INTO courses ( course_name, course_par, course_length, lat, lng, address, city, state, zip )
	VALUES ('Avon Fields Golf Course', 66, 4587, '39.159968292511905' ,' -84.47809775374196', '10965 Springfield Pk', 'Cincinnati', 'OH', '45246' );
INSERT INTO courses ( course_name, course_par, course_length, lat, lng, address, city, state, zip )
	VALUES ('Shaker Run Golf Club', 72, 6070, '39.45399919680888' ,' -84.29617164014978', '1320 Golf Club Dr', 'Lebanon', 'OH', '45036' );
INSERT INTO courses ( course_name, course_par, course_length, lat, lng, address, city, state, zip )	
	VALUES ('Pebble Beach Golf Links', 72, 6094, '36.56982404634914' ,' -121.94966420217276', '1700 17-Mile Dr', 'Pebble Beach', 'CA', '93953' );

INSERT INTO leagues (league_id, league_name, league_admin, league_course, day_of_week, active) VALUES (0, 'Senior Sundays', 1, 1, 'Sunday', true);
INSERT INTO leagues (league_id, league_name, league_admin, league_course, day_of_week, active) VALUES (1, 'Bro league', 2, 2, 'Friday', true);
INSERT INTO leagues (league_id, league_name, league_admin, league_course, day_of_week, active) VALUES (2, 'The Semi Semi Pros', 3, 3, 'Thursday', true);
INSERT INTO leagues (league_id, league_name, league_admin, league_course, day_of_week, active) VALUES (3, 'Ladies Only', 4, 4, 'Wednesday', true);
INSERT INTO leagues (league_id, league_name, league_admin, league_course, day_of_week, active) VALUES (4, 'Bobs League', 5, 5, 'Tuesday', true);
INSERT INTO leagues (league_id, league_name, league_admin, league_course, day_of_week, active) VALUES (5, 'The Rookies', 6, 6, 'Saturday', true);

INSERT INTO user_league (user_id, league_id) VALUES (20,0);
INSERT INTO user_league (user_id, league_id) VALUES (19,0);
INSERT INTO user_league (user_id, league_id) VALUES (2,0);
INSERT INTO user_league (user_id, league_id) VALUES (3,0);
INSERT INTO user_league (user_id, league_id) VALUES (4,0);
INSERT INTO user_league (user_id, league_id) VALUES (5,0);
INSERT INTO user_league (user_id, league_id) VALUES (6,1);
INSERT INTO user_league (user_id, league_id) VALUES (7,1);
INSERT INTO user_league (user_id, league_id) VALUES (8,1);
INSERT INTO user_league (user_id, league_id) VALUES (9,1);
INSERT INTO user_league (user_id, league_id) VALUES (10,1);
INSERT INTO user_league (user_id, league_id) VALUES (11,2);
INSERT INTO user_league (user_id, league_id) VALUES (12,2);
INSERT INTO user_league (user_id, league_id) VALUES (13,2);
INSERT INTO user_league (user_id, league_id) VALUES (14,2);
INSERT INTO user_league (user_id, league_id) VALUES (15,2);
INSERT INTO user_league (user_id, league_id) VALUES (16,3);
INSERT INTO user_league (user_id, league_id) VALUES (17,3);
INSERT INTO user_league (user_id, league_id) VALUES (18,3);
INSERT INTO user_league (user_id, league_id) VALUES (19,3);
INSERT INTO user_league (user_id, league_id) VALUES (20,3);
INSERT INTO user_league (user_id, league_id) VALUES (1,4);
INSERT INTO user_league (user_id, league_id) VALUES (2,4);
INSERT INTO user_league (user_id, league_id) VALUES (3,4);
INSERT INTO user_league (user_id, league_id) VALUES (4,4);
INSERT INTO user_league (user_id, league_id) VALUES (5,5);
INSERT INTO user_league (user_id, league_id) VALUES (6,5);
INSERT INTO user_league (user_id, league_id) VALUES (7,5);
INSERT INTO user_league (user_id, league_id) VALUES (8,5);
INSERT INTO user_league (user_id, league_id) VALUES (9,5);

INSERT INTO tee_times (tee_time_id, tee_time_date, tee_time)
    VALUES (0, '12-16-2021', '7:15am');
INSERT INTO tee_times (tee_time_id, tee_time_date, tee_time)
    VALUES (1, '12-16-2021', '8:00am');
INSERT INTO tee_times (tee_time_id, tee_time_date, tee_time)
    VALUES (2, '12-16-2021', '8:25am');
INSERT INTO tee_times (tee_time_id, tee_time_date, tee_time)
    VALUES (3, '12-16-2021', '8:45am');
INSERT INTO tee_times (tee_time_id, tee_time_date, tee_time)
    VALUES (4, '12-16-2021', '9:10am');
INSERT INTO tee_times (tee_time_id, tee_time_date, tee_time)
    VALUES (5, '12-17-2021', '7:25am');
INSERT INTO tee_times (tee_time_id, tee_time_date, tee_time)
    VALUES (6, '12-17-2021', '7:50am');
INSERT INTO tee_times (tee_time_id, tee_time_date, tee_time)
    VALUES (7, '12-17-2021', '8:05am');
INSERT INTO tee_times (tee_time_id, tee_time_date, tee_time)
    VALUES (8, '12-17-2021', '8:35am');
INSERT INTO tee_times (tee_time_id, tee_time_date, tee_time)
    VALUES (9, '12-17-2021', '8:50am');
INSERT INTO tee_times (tee_time_id, tee_time_date, tee_time)
    VALUES (10, '12-18-2021', '7:15am');
INSERT INTO tee_times (tee_time_id, tee_time_date, tee_time)
    VALUES (11, '12-18-2021', '7:35am');
INSERT INTO tee_times (tee_time_id, tee_time_date, tee_time)
    VALUES (12, '12-18-2021', '8:00am');
INSERT INTO tee_times (tee_time_id, tee_time_date, tee_time)
    VALUES (13, '12-18-2021', '8:35am');

COMMIT TRANSACTION;


