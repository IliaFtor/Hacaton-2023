import mysql.connector
import random

def connect ():
    db = None
    try:
        db = mysql.connector.connect(
            host="127.0.0.1",
            port=3308,
            user="root",
            password="654780Jdm!",
            database="hacaton"
        )
        return db
    except mysql.connector.Error as err:
        print(f"Error: {err}")
def setup_database(flag=True):
    db = connect()
    try:
        cursor = db.cursor()

        cursor.execute("CREATE DATABASE IF NOT EXISTS hacaton")
        db.commit()

        #cursor.execute("SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0")
        #cursor.execute("SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0")
        #cursor.execute("SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION'")
        
        cursor.execute("USE hacaton")

        cursor.execute('''
                    CREATE TABLE IF NOT EXISTS hacaton.Roles (
                        role_id INT(11) NOT NULL,
                        role_name VARCHAR(45) NULL DEFAULT NULL,
                        PRIMARY KEY (role_id)
                        );
                        CREATE TABLE IF NOT EXISTS Users (
                            user_id INT(11) NOT NULL AUTO_INCREMENT,
                            username VARCHAR(45) NULL DEFAULT NULL,
                            login VARCHAR(45) NULL DEFAULT NULL,
                            password VARCHAR(45) NULL DEFAULT NULL,
                            role_id INT(11) NOT NULL,
                            PRIMARY KEY (user_id),
                            UNIQUE INDEX id_UNIQUE (user_id ASC) VISIBLE,
                            INDEX fk_Users_Roles1_idx (role_id ASC) VISIBLE,
                            CONSTRAINT fk_Users_Roles1
                                FOREIGN KEY (role_id)
                                REFERENCES Roles (role_id)
                                ON DELETE NO ACTION
                                ON UPDATE NO ACTION
                        ) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8;
                       
                        CREATE TABLE IF NOT EXISTS UGroups (
                            u_group_id INT(11) NOT NULL AUTO_INCREMENT,
                            u_group_name VARCHAR(45) NULL DEFAULT NULL,
                            PRIMARY KEY (u_group_id),
                            UNIQUE INDEX idStudentGroups_UNIQUE (u_group_id ASC) VISIBLE)
                            ENGINE = InnoDB
                            DEFAULT CHARACTER SET = utf8;
                            
                
                        CREATE TABLE IF NOT EXISTS Disciplines (
                            discipline_id INT(11) NOT NULL AUTO_INCREMENT,
                            discipline_name VARCHAR(45) NOT NULL,
                            PRIMARY KEY (discipline_id),
                            UNIQUE INDEX idDiscipline_UNIQUE (discipline_id ASC) VISIBLE)
                            ENGINE = InnoDB
                            DEFAULT CHARACTER SET = utf8;
                            
                
                        CREATE TABLE IF NOT EXISTS Question_Banks (
                            question_bank_id INT(11) NOT NULL AUTO_INCREMENT,
                            question_bank_name VARCHAR(45) NOT NULL,
                            discipline_id INT(11) NOT NULL,
                            PRIMARY KEY (question_bank_id),
                            INDEX fk_QuestionBanks_Discipline1_idx (discipline_id ASC) VISIBLE,
                            UNIQUE INDEX question_bank_id_UNIQUE (question_bank_id ASC) VISIBLE,
                            CONSTRAINT fk_QuestionBanks_Discipline1
                                FOREIGN KEY (discipline_id)
                                REFERENCES Disciplines (discipline_id)
                                ON DELETE NO ACTION
                                ON UPDATE NO ACTION
                        ) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8;

                        CREATE TABLE IF NOT EXISTS User_Groups (
                            user_group_id INT(11) NOT NULL,
                            user_id INT(11) NULL DEFAULT NULL,
                            u_group_id INT(11) NULL DEFAULT NULL,
                            PRIMARY KEY (user_group_id),
                            INDEX fk_Users_has_StudentGroups_Users1_idx (user_id ASC) VISIBLE,
                            INDEX fk_Users_has_StudentGroups_StudentGroups1_idx (u_group_id ASC) VISIBLE,
                            UNIQUE INDEX user_group_id_UNIQUE (user_group_id ASC) VISIBLE,
                            CONSTRAINT fk_Users_has_StudentGroups_Users1
                                FOREIGN KEY (user_id)
                                REFERENCES Users (user_id)
                                ON DELETE NO ACTION
                                ON UPDATE NO ACTION,
                            CONSTRAINT fk_Users_has_StudentGroups_StudentGroups1
                                FOREIGN KEY (u_group_id)
                                REFERENCES UGroups (u_group_id)
                                ON DELETE NO ACTION
                                ON UPDATE NO ACTION)
                            ENGINE = InnoDB
                            DEFAULT CHARACTER SET = utf8;

                        CREATE TABLE IF NOT EXISTS Disciplines (
                            discipline_id INT(11) NOT NULL AUTO_INCREMENT,
                            discipline_name VARCHAR(45) NOT NULL,
                            PRIMARY KEY (discipline_id),
                            UNIQUE INDEX idDiscipline_UNIQUE (discipline_id ASC) VISIBLE)
                            ENGINE = InnoDB
                            DEFAULT CHARACTER SET = utf8;

                        CREATE TABLE IF NOT EXISTS UGroup_Didciplines (
                            u_group_discipline_id INT(11) NOT NULL,
                            discipline_id INT(11) NOT NULL,
                            u_group_id INT(11) NOT NULL,
                            PRIMARY KEY (u_group_discipline_id),
                            INDEX fk_Discipline_has_StudentGroups_StudentGroups1_idx (u_group_id ASC) VISIBLE,
                            INDEX fk_Discipline_has_StudentGroups_Discipline1_idx (discipline_id ASC) VISIBLE,
                            UNIQUE INDEX group_discipline_id_UNIQUE (u_group_discipline_id ASC) VISIBLE,
                            CONSTRAINT fk_Discipline_has_StudentGroups_Discipline1
                                FOREIGN KEY (discipline_id)
                                REFERENCES Disciplines (discipline_id)
                                ON DELETE NO ACTION
                                ON UPDATE NO ACTION,
                            CONSTRAINT fk_Discipline_has_StudentGroups_StudentGroups1
                                FOREIGN KEY (u_group_id)
                                REFERENCES UGroups (u_group_id)
                                ON DELETE NO ACTION
                                ON UPDATE NO ACTION)
                            ENGINE = InnoDB
                            DEFAULT CHARACTER SET = utf8;

                            CREATE TABLE IF NOT EXISTS Question_Groups (
                                question_group_id INT(11) NOT NULL AUTO_INCREMENT,
                                question_group_name VARCHAR(45) NOT NULL,
                                question_bank_id INT(11) NOT NULL,
                                PRIMARY KEY (question_group_id),
                                INDEX fk_QuestionGroups_QuestionBanks1_idx (question_bank_id ASC) VISIBLE,
                                UNIQUE INDEX question_group_id_UNIQUE (question_group_id ASC) VISIBLE,
                                CONSTRAINT fk_QuestionGroups_QuestionBanks1
                                    FOREIGN KEY (question_bank_id)
                                    REFERENCES Question_Banks (question_bank_id)
                                    ON DELETE NO ACTION
                                    ON UPDATE NO ACTION)
                                ENGINE = InnoDB
                                DEFAULT CHARACTER SET = utf8;

                                CREATE TABLE IF NOT EXISTS Tests (
                                    test_id INT(11) NOT NULL AUTO_INCREMENT,
                                    test_name VARCHAR(45) NOT NULL,
                                    question_group_id INT(11) NOT NULL,
                                    PRIMARY KEY (test_id),
                                    UNIQUE INDEX test_id_UNIQUE (test_id ASC) VISIBLE,
                                    INDEX fk_Tests_Question_Groups1_idx (question_group_id ASC) VISIBLE,
                                    CONSTRAINT fk_Tests_Question_Groups1
                                        FOREIGN KEY (question_group_id)
                                        REFERENCES Question_Groups (question_group_id)
                                        ON DELETE NO ACTION
                                        ON UPDATE NO ACTION)
                                    ENGINE = InnoDB
                                    DEFAULT CHARACTER SET = utf8;

                                CREATE TABLE IF NOT EXISTS Student_Test_Results (
                                    result_id INT(11) NOT NULL AUTO_INCREMENT,
                                    user_id INT(11) NULL DEFAULT NULL,
                                    test_id INT(11) NULL DEFAULT NULL,
                                    score_all FLOAT(11) NULL DEFAULT NULL,
                                    timelimit_in_seconds INT(11) NULL DEFAULT NULL,
                                    PRIMARY KEY (result_id),
                                    INDEX fk_Users_has_Tests_Tests1_idx (test_id ASC) VISIBLE,
                                    INDEX fk_Users_has_Tests_Users1_idx (user_id ASC) VISIBLE,
                                    UNIQUE INDEX result_id_UNIQUE (result_id ASC) VISIBLE,
                                    CONSTRAINT fk_Users_has_Tests_Users1
                                        FOREIGN KEY (user_id)
                                        REFERENCES Users (user_id)
                                        ON DELETE NO ACTION
                                        ON UPDATE NO ACTION,
                                    CONSTRAINT fk_Users_has_Tests_Tests1
                                        FOREIGN KEY (test_id)
                                        REFERENCES Tests (test_id)
                                        ON DELETE NO ACTION
                                        ON UPDATE NO ACTION)
                                    ENGINE = InnoDB
                                    DEFAULT CHARACTER SET = utf8;

                                CREATE TABLE IF NOT EXISTS Questions (
                                    question_id INT(11) NOT NULL AUTO_INCREMENT,
                                    question_text VARCHAR(45) NOT NULL,
                                    question_group_id INT(11) NOT NULL,
                                    PRIMARY KEY (question_id),
                                    UNIQUE INDEX question_id_UNIQUE (question_id ASC) VISIBLE,
                                    INDEX fk_Questions_Question_Groups1_idx (question_group_id ASC) VISIBLE,
                                    CONSTRAINT fk_Questions_Question_Groups1
                                        FOREIGN KEY (question_group_id)
                                        REFERENCES Question_Groups (question_group_id)
                                        ON DELETE NO ACTION
                                        ON UPDATE NO ACTION)
                                    ENGINE = InnoDB
                                    DEFAULT CHARACTER SET = utf8;

                                CREATE TABLE IF NOT EXISTS Answers (
                                    answer_id INT(11) NOT NULL AUTO_INCREMENT,
                                    question_id INT(11) NOT NULL,
                                    is_correct TINYINT(4) NULL DEFAULT NULL,
                                    answer_score FLOAT(11) NOT NULL,
                                    PRIMARY KEY (answer_id),
                                    INDEX fk_Answers_Questions1_idx (question_id ASC) VISIBLE,
                                    UNIQUE INDEX answer_id_UNIQUE (answer_id ASC) VISIBLE,
                                    CONSTRAINT fk_Answers_Questions1
                                        FOREIGN KEY (question_id)
                                        REFERENCES Questions (question_id)
                                        ON DELETE NO ACTION
                                        ON UPDATE NO ACTION)
                                    ENGINE = InnoDB
                                    DEFAULT CHARACTER SET = utf8;

                                CREATE TABLE IF NOT EXISTS Roles (
                                    role_id INT(11) NOT NULL,
                                    role_name VARCHAR(45) NULL DEFAULT NULL,
                                    PRIMARY KEY (role_id))
                                    ENGINE = InnoDB
                                    DEFAULT CHARACTER SET = utf8;
                ''',multi=True)
        
        db.commit()
        print("The database has been created or is ready for use")

    except mysql.connector.Error as err:
        print(f"Error: {err}")

    finally:
        if db is not None and db.is_connected():
            db.close()