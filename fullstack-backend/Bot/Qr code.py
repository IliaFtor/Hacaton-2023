import mysql.connector
def create_database():
    try:
        db = mysql.connector.connect(
            host="127.0.0.1",
            port=3308,
            user="root",
            password="654780Jdm!"
        )

        cursor = db.cursor()

        # Create the hacaton database if it doesn't exist
        cursor.execute("CREATE DATABASE IF NOT EXISTS hacaton")

    except mysql.connector.Error as err:
        print(f"Error: {err}")

    finally:
        if db is not None and db.is_connected():
            db.close()

create_database()
a = input()            