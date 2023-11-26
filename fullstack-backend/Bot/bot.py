import SQL_query
import SQLset
import asyncio
import logging
from aiogram import Bot, Dispatcher, types
from aiogram.contrib.middlewares.logging import LoggingMiddleware
from aiogram.contrib.fsm_storage.memory import MemoryStorage
from aiogram.dispatcher.filters.state import State, StatesGroup
from aiogram.utils import executor
from aiogram.dispatcher import FSMContext

logging.basicConfig(level=logging.INFO)
logging.basicConfig(level=logging.INFO)

API_TOKEN = '6679452964:AAHscyg7kqjqwqKupOxfpxyp8cSzVkYEPLI'
bot = Bot(token=API_TOKEN)
dp = Dispatcher(bot)
dp.middleware.setup(LoggingMiddleware())

storage = MemoryStorage()
dp = Dispatcher(bot, storage=storage)
dp.middleware.setup(LoggingMiddleware())
#################################################################################
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
################################################################################
@dp.message_handler(commands=['start', 'help'])
async def send_welcome(message: types.Message):
    await message.reply("Привет, это бот используеться для тестирования.\n-Используйте /registration для регистрации.\n-Или вы можите начать тестирования /test")

################################################################################
@dp.message_handler(commands=['start_test', 'test'])
async def start_testing(message: types.Message):
    if SQL_query.check_user_registration(message.from_user.id):
        await message.answer("Тест начинается...")
        try:
            await Testing(SQL_query.testing(), message)
        except Exception as e:
            print(f"Error: {e}")
    else:
        await message.answer("Извините, вы не зарегистрированы.\nДля начала тестирования вы должны авторизоваться командой /registration")

async def Testing(id_test, message: types.Message):
    test_dict = SQL_query.testing(id_test)

    for question_text, correct_answer in test_dict.items():
        await message.answer(f"{question_text}")

        response = await dp.bot.send_message(message.chat.id, "Ваш ответ:")

        await SQL_query.process_response(response, correct_answer)

        
   
###########################################################################################

class RegistrationStates(StatesGroup):
    FullName = State()
    Group = State()

@dp.message_handler(commands=('registration'))
async def registration_start(message: types.Message, state: FSMContext):
    if SQL_query.check_user_registration(message.from_user.id):
        await message.answer("Вы уже зарегистрированы.")
        return

    await message.answer("Введите ваше ФИО:")
    await RegistrationStates.FullName.set()

@dp.message_handler(state=RegistrationStates.FullName)
async def process_full_name(message: types.Message, state: FSMContext):
    full_name = message.text
    await state.update_data(full_name=full_name)

    await message.answer("Введите вашу группу")
    await RegistrationStates.Group.set()

@dp.message_handler(state=RegistrationStates.Group)
async def process_group(message: types.Message, state: FSMContext):
    group = message.text
    await state.update_data(group=group)

    await save(message, state)

async def save(message: types.Message, state: FSMContext):
    user_data = await state.get_data()
    user_id = message.from_user.id

    ansewer=SQL_query.insert_user(user_id, user_data['full_name'], user_data['group'])

    await message.answer(ansewer)
    await state.finish()


##########################################################################################
@dp.message_handler(commands=['status'])
async def status(message: types.Message):
    user_id = message.from_user.id
    user_data = SQL_query.get_user_data(user_id)

    if user_data:
        status_text = f"Статус пользователя {user_data['username']} {user_data['group_name']}" 
    else:
        status_text = "Пользователь не найден."

    await message.answer(status_text)
#########################################################################################



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

