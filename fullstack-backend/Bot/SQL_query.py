import SQLset
import mysql.connector
from aiogram import types


def check_user_registration(user_id):
    user_data = get_user_data(user_id)
    return user_data is not None
###################################################################
def insert_user(user_id, username, student_group_id):
    try:
        db = SQLset.connect()  
        cursor = db.cursor()

        group_query = "SELECT * FROM ugroups WHERE u_group_name = %s"
        group_values = (student_group_id,)
        cursor.execute(group_query, group_values)
        group_result = cursor.fetchone()

        if group_result:  
            user_query = "INSERT INTO users(user_id, username, login, password, role_id) VALUES (%s, %s, %s, %s, %s)"
            user_values = (user_id, username, None, None, 1)
            cursor.execute(user_query, user_values)
            db.commit()
            return("User inserted successfully.")
        else:
            return(f"Error: Group with ID {student_group_id} does not exist.")

    except Exception as e:
        print(f"Error while executing the query: {e}")

    finally:
        if db is not None and db.is_connected():
            db.close()
#####################################################################
def testing(id_test):
    db = SQLset.connect()
    try:
        db = SQLset.connect()
        cursor = db.cursor()
        cursor.execute("""
            SELECT question_text, correct_answer
            FROM questions
            WHERE id_test = %s
        """, (id_test,))
        questions = cursor.fetchall()
        test_dict = {question[0]: question[1] for question in questions}
        return test_dict
    except Exception as e:
        print(f"Error: {e}")
###############################################################
async def process_response(response: types.Message, question_text: str):
    try:
        db = SQLset.connect()
        cursor = db.cursor()

        # Запрос в базу данных для получения ответа на текущий вопрос
        cursor.execute("""
            SELECT answer_text
            FROM answers
            WHERE question_text = %s
        """, (question_text,))

        result = cursor.fetchone()

        if result:
            answer_text = result[0]
            await insert_answer(response.text, answer_text)
        else:
            await response.answer(f"Ответ на вопрос '{question_text}' не найден в базе данных.")

    except Exception as e:
        print(f"Ошибка: {e}")
    finally:
        db.close()

async def insert_answer(question_text, answer_text):
    try:
        db = SQLset.connect()
        cursor = db.cursor()

        # Запрос в базу данных для вставки нового ответа
        cursor.execute("""
            INSERT INTO answers (question_text, answer_text)
            VALUES (%s, %s)
        """, (question_text, answer_text))

        # Подтверждение изменений
        db.commit()

    except Exception as e:
        print(f"Ошибка: {e}")
    finally:
        db.close()
###############################################################
def get_user_data(user_id):
    try:
        db = SQLset.connect()
        cursor = db.cursor()
        cursor.execute("""
            SELECT u.username, g.u_group_name
            FROM users u
            JOIN user_groups m ON u.user_id = m.user_id
            JOIN ugroups g ON m.u_group_id = g.u_group_id
            WHERE u.user_id = %s
        """, (user_id,))

        user_data = cursor.fetchone()

        if user_data:
            return {
                'username': user_data[0],
                'group_name': user_data[1],
            }
    except mysql.connector.Error as err:
        print(f"Error while executing the query: {err}")
        return None
    finally:
        if db is not None and db.is_connected():
            db.close()