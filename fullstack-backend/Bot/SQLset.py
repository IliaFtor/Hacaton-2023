import mysql.connector

def setup_database(flag=True):
    db = None
    try:
        db = mysql.connector.connect(
            host="127.0.0.1",
            port=3308,
            user="root",
            password="654780Jdm!",
            database="hacaton"  # Выбирайте базу данных, если она существует
        )

        cursor = db.cursor()

        create_database_query = "CREATE DATABASE IF NOT EXISTS hacaton"
        cursor.execute(create_database_query)

        cursor.execute("SHOW TABLES LIKE 'Users';")
        users_table_exists = cursor.fetchone()

        if not users_table_exists and flag:
            flag = False
            print("Create Data Base...")
            cursor.execute('''
                CREATE TABLE IF NOT EXISTS Discipline (
                    discipline_id INT PRIMARY KEY,
                    name VARCHAR(255)
                    -- Other fields for Discipline
                );

                CREATE TABLE IF NOT EXISTS StudentGroup (
                    student_group_id INT PRIMARY KEY,
                    name VARCHAR(255),
                    -- Other fields for StudentGroup

                    -- Foreign key references
                    discipline_id INT,
                    FOREIGN KEY (discipline_id) REFERENCES Discipline(discipline_id)
                );

                CREATE TABLE IF NOT EXISTS Users (
                    user_id INT PRIMARY KEY,
                    username VARCHAR(255),
                    login VARCHAR(255),
                    role INT,
                    id INT,
                    -- Other fields for Users

                    -- Foreign key references
                    student_group_id INT,
                    FOREIGN KEY (student_group_id) REFERENCES StudentGroup(student_group_id)
                );
            ''')
            db.commit()

            print("The database has been created or is ready for use")

    except mysql.connector.Error as err:
        print(f"Error: {err}")

    finally:
        if db is not None and db.is_connected():
            db.close()

def insert_user(user_id, name, soName, group):
    try:
        db = mysql.connector.connect(
            host="127.0.0.1",
            port=3308,
            user="root",
            password="654780Jdm!",
            database="hacaton"
        )
        cursor = db.cursor()
        cursor.execute("INSERT INTO Users (user_id, username, login, role, id, student_group_id) VALUES (%s, %s, %s, %s, %s, %s)",
                       (user_id, name, soName, group, None, None))
        db.commit()

    except Exception as e:
        print(f"Error while executing the query: {e}")

    finally:
        if db is not None and db.is_connected():
            db.close()

def get_user_data(user_id):
    try:
        with mysql.connector.connect(
            host="127.0.0.1",
            port=3308,
            user="root",
            password="654780Jdm!",
            database="hacaton"
        ) as db:
            cursor = db.cursor()
            cursor.execute("SELECT * FROM users WHERE user_id=%s", (user_id,))
            user_data = cursor.fetchone()

            if user_data:
                return {
                    'user_id': user_data[0],
                    'username': user_data[1],
                    'login': user_data[2],
                    'role': user_data[3],
                    'id': user_data[4],
                    'student_group_id': user_data[5],
                }
            else:
                return None

    except mysql.connector.Error as err:
        print(f"Error while executing the query: {err}")
        return None
