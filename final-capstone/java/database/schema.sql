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
  CACHE 1;

CREATE SEQUENCE seq_course_id
  INCREMENT BY 1
  NO MAXVALUE
  MINVALUE 0
  CACHE 1
  START WITH 0;

CREATE SEQUENCE seq_tee_time_id
  INCREMENT BY 1
  NO MAXVALUE
  MINVALUE 0
  CACHE 1
  START WITH 0;


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
    CONSTRAINT PK_tee_time_id PRIMARY KEY(tee_time_id),
    CONSTRAINT FK_league_tee_time FOREIGN KEY(tee_time_id) REFERENCES Leagues(league_id)
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


INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');



INSERT INTO courses ( course_name, course_par, course_length, lat, lng, address, city, state, zip )
	VALUES ( 'Top Golf - West Chester', 0, 215, '39.316124235753335' ,'-84.42551718231664', '9568 Water Front Dr', 'West Chester', 'OH', '45069' );
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
INSERT INTO leagues (league_id, league_name, league_admin, league_course, day_of_week, active) VALUES (0, 'Test', 1, 0, 'Friday', true);
INSERT INTO user_league (user_id, league_id) VALUES (1,0);



COMMIT TRANSACTION;


