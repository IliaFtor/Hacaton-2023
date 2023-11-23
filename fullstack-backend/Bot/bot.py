import sqlite3
import SQLset
import asyncio
import logging
from aiogram import Bot, Dispatcher, types
from aiogram.contrib.middlewares.logging import LoggingMiddleware
from aiogram.contrib.fsm_storage.memory import MemoryStorage
from aiogram.dispatcher.filters.state import State, StatesGroup
from aiogram.utils import executor
from aiogram.dispatcher import FSMContext
from SQLset import insert_user, get_user_data

logging.basicConfig(level=logging.INFO)
logging.basicConfig(level=logging.INFO)

API_TOKEN = '6679452964:AAHscyg7kqjqwqKupOxfpxyp8cSzVkYEPLI'
bot = Bot(token=API_TOKEN)
dp = Dispatcher(bot)
dp.middleware.setup(LoggingMiddleware())

storage = MemoryStorage()
dp = Dispatcher(bot, storage=storage)
dp.middleware.setup(LoggingMiddleware())

async def on_startup(dp):
    print("Бот был запущен")
    await CreateDataBase()

async def CreateDataBase():
    try:
        SQLset.setup_database()
    except Exception as e:
        print(f"Ошибка при выполнении скрипта: {e}")

async def on_shutdown(dp):
    print()

@dp.message_handler(commands=['start', 'help'])
async def send_welcome(message: types.Message):
    await message.reply("Привет, это бот для регистрации пользователей. Используйте /registration для регистрации.")

###########################################################################################

class RegistrationStates(StatesGroup):
    Name = State()
    SoName = State()
    Group = State()

@dp.message_handler(commands=['registration'])
async def registration_start(message: types.Message):
    await message.answer("Введите ваше имя:")
    await RegistrationStates.Name.set()    

@dp.message_handler(state=RegistrationStates.Name)
async def process_name(message: types.Message, state: FSMContext):
    name = message.text
    await state.update_data(name=name)

    await message.answer("Введите вашу фамилию:")
    await RegistrationStates.SoName.set()

@dp.message_handler(state=RegistrationStates.SoName)
async def process_soName(message: types.Message, state: FSMContext):
    soName = message.text
    await state.update_data(soName=soName)

    await message.answer("Введите номер вашей группы:")
    await RegistrationStates.Group.set()

@dp.message_handler(state=RegistrationStates.Group)
async def process_group(message: types.Message, state: FSMContext):
    group = message.text
    await state.update_data(group=group)

    user_data = await state.get_data()
    user_id = message.from_user.id

    insert_user(user_id, user_data['name'], user_data['soName'], user_data['group'])
    await message.answer("Регистрация завершена. Спасибо!")
    await state.finish()

##########################################################################################

@dp.message_handler(commands=['status'])
async def status(message: types.Message):
    user_id = message.from_user.id
    user_data = await get_user_data(user_id)

    if user_data:
        status_text = f"Статус пользователя {user_data['name']} {user_data['soName']}: {user_data['group']}"
    else:
        status_text = "Пользователь не найден."

    await message.answer(status_text)

async def get_user_data(user_id: int) -> dict:
    conn = None  
    try:
        conn = sqlite3.connect('QslBD.db')
        cursor = conn.cursor()

        cursor.execute("SELECT * FROM users WHERE id=?", (user_id,))
        user_data = cursor.fetchone()

        if user_data:
            return {
                'id': user_data[0],
                'name': user_data[1],
                'soName': user_data[2],
                'group': user_data[3],
            }
        else:
            try:
                if conn:
                    conn.close()
            except UnboundLocalError:
                pass 

    except sqlite3.Error as e:
        print(f"Ошибка при выполнении запроса: {e}")

    finally:
        if conn:
            conn.close()

if __name__ == '__main__':
    from aiogram import executor

    loop = asyncio.get_event_loop()
    loop.create_task(on_startup(dp))
    loop.create_task(on_shutdown(dp))
    executor.start_polling(dp, skip_updates=True)
    try:
        executor.start_polling(dp, skip_updates=True, on_startup=on_startup, on_shutdown=on_shutdown)
        loop.run_until_complete(asyncio.gather(*dp.cleanup()))
    finally:
        loop.close()

