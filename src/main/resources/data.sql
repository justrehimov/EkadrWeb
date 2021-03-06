CREATE TABLE AGE
(
    ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    AGE VARCHAR(10) NOT NULL,
    ACTIVE INTEGER DEFAULT 1
);

CREATE TABLE CITY
(
    ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    CITY VARCHAR(25) NOT NULL,
    ACTIVE INTEGER DEFAULT 1
);

CREATE TABLE CATEGORY
(
    ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    CATEGORY VARCHAR(40) NOT NULL,
    ACTIVE INTEGER DEFAULT 1
);

CREATE TABLE EXPERIENCE
(
    ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    EXPERIENCE VARCHAR(25) NOT NULL,
    ACTIVE INTEGER DEFAULT 1
);

CREATE TABLE EDUCATION
(
    ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    EDUCATION VARCHAR(30) NOT NULL,
    ACTIVE INTEGER DEFAULT 1
);

CREATE TABLE WORKMODE
(
    ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    WORKMODE VARCHAR(25) NOT NULL,
    ACTIVE INTEGER DEFAULT 1
);



CREATE TABLE COMPANY
(
    ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    COMPANY_NAME VARCHAR(50) NOT NULL,
    NAME VARCHAR(30) NOT NULL,
    SURNAME VARCHAR(30) NOT NULL,
    EMAIL VARCHAR(30) UNIQUE NOT NULL,
    PHONE VARCHAR(15) NOT NULL,
    PASSWORD VARCHAR(25) NOT NULL,
    CITY_ID INTEGER NOT NULL,
    ABOUT_COMPANY VARCHAR(1000),
    LOGO BLOB,
    DATA_DATE DATE,
    VERIFIED INTEGER DEFAULT 0,
    ACTIVE INTEGER DEFAULT 1,
    WEBSITE VARCHAR(30),
    CONSTRAINT CITY_ID_FK FOREIGN KEY(CITY_ID) REFERENCES CITY(ID)
);



CREATE TABLE VACANCY
(
    ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    VACANCY_NAME VARCHAR(100) NOT NULL,
    INFORMATION VARCHAR(3000) NOT NULL,
    REQUIREMENTS VARCHAR(3000) NOT NULL,
    SALARY VARCHAR(50) NOT NULL,
    ADDRESS VARCHAR(100),
    AGE_ID INTEGER NOT NULL,
    CATEGORY_ID INTEGER NOT NULL,
    COMPANY_ID INTEGER NOT NULL,
    EXPERIENCE_ID INTEGER NOT NULL,
    EDUCATION_ID INTEGER NOT NULL,
    WORKMODE_ID INTEGER NOT NULL,
    ACTIVE INTEGER DEFAULT 1,
    DATA_DATE DATE,
    EXP_DATE DATE NOT NULL,
    CONSTRAINT AGE_ID_FK FOREIGN KEY(AGE_ID) REFERENCES AGE(ID),
    CONSTRAINT CATEGORY_ID_FK FOREIGN KEY(CATEGORY_ID) REFERENCES CATEGORY(ID),
    CONSTRAINT COMPANY_ID_FK FOREIGN KEY(COMPANY_ID) REFERENCES COMPANY(ID),
    CONSTRAINT EXPERIENCE_ID_FK FOREIGN KEY(EXPERIENCE_ID) REFERENCES EXPERIENCE(ID),
    CONSTRAINT EDUCATION_ID_FK FOREIGN KEY(EDUCATION_ID) REFERENCES EDUCATION(ID),
    CONSTRAINT WORKMODE_ID_FK FOREIGN KEY(WORKMODE_ID) REFERENCES WORKMODE(ID)
);

