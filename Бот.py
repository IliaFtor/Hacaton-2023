import asyncio
import aiogram
import vk_api
import sqlite3

class BotHandler:
    def __init__(self, tg_token, vk_token, db_connection):
        self.tg_bot = aiogram.Bot(token=tg_token)
        self.vk_bot = vk_api.VkApi(token=vk_token)
        self.db_connection = db_connection
        self.registration_in_progress = {}
        self.user_data = {}

    async def process_question(self, question, options):
        user_id = 12345  # Замените на реальный идентификатор пользователя

        # Создаем клавиатуру с кнопками ответов
        keyboard = aiogram.types.ReplyKeyboardMarkup(resize_keyboard=True, selective=True)
        
        # Добавляем кнопки ответов в клавиатуру
        for option in options:
            keyboard.add(option)

        # Создаем текст сообщения с вопросом
        message_text = f"Вопрос: {question}"

        # Отправляем сообщение с клавиатурой
        await self.tg_bot.send_message(user_id, message_text, reply_markup=keyboard)

        
    async def process_question(self, question, options):
    user_id = 12345  # Замените на реальный идентификатор пользователя

    # Создаем клавиатуру с кнопками ответов
    keyboard = {
        "one_time": False,
        "buttons": [
            [
                {
                    "action": {
                        "type": "text",
                        "label": options[0]
                    },
                    "color": "primary"
                },
                {
                    "action": {
                        "type": "text",
                        "label": options[1]
                    },
                    "color": "primary"
                }
            ],
            [
                {
                    "action": {
                        "type": "text",
                        "label": options[2]
                    },
                    "color": "primary"
                },
                {
                    "action": {
                        "type": "text",
                        "label": options[3]
                    },
                    "color": "primary"
                }
            ]
        ]
    }

    # Создаем сообщение с текстом и клавиатурой
    message = f"Вопрос: {question}"
    
    self.vk_bot.method("messages.send", {
        "user_id": user_id,
        "message": message,
        "keyboard": json.dumps(keyboard),
        "random_id": random.randint(1, 1000)  # Случайный идентификатор сообщения
    })
    

    async def register_user(self, user_id, name, group, institution):
        try:
            cursor = self.db_connection.cursor()
            insert_query = "INSERT INTO users (user_id, name, group, institution) VALUES (?, ?, ?, ?)"
            cursor.execute(insert_query, (user_id, name, group, institution))
            self.db_connection.commit()
            cursor.close()
            self.user_data.pop(user_id, None)
            return True
        except Exception as e:
            print(f"Ошибка при регистрации пользователя: {str(e)}")
            return False

    async def vk_main(self):
        while True:
            # Получение новых сообщений из ВКонтакте
            messages = self.vk_bot.method("messages.get")
            for message in messages["items"]:
                if "text" in message and "user_id" in message:
                    vk_user_id = message["user_id"]
                    text = message["text"]

                    if vk_user_id not in self.registration_in_progress:
                        # Начать процесс регистрации
                        self.registration_in_progress[vk_user_id] = {"name": None, "group": None, "institution": None}
                        self.vk_bot.method("messages.send", {"user_id": vk_user_id, "message": "Давайте зарегистрируем вас! Введите ваше имя:"})
                    else:
                        registration_data = self.registration_in_progress[vk_user_id]

                        if not registration_data["name"]:
                            registration_data["name"] = text
                            self.vk_bot.method("messages.send", {"user_id": vk_user_id, "message": "Введите вашу группу:"})
                        elif not registration_data["group"]:
                            registration_data["group"] = text
                            self.vk_bot.method("messages.send", {"user_id": vk_user_id, "message": "Введите название учебного заведения:"})
                        elif not registration_data["institution"]:
                            registration_data["institution"] = text
                            if await self.register_user(vk_user_id, registration_data["name"], registration_data["group"], registration_data["institution"]):
                                self.vk_bot.method("messages.send", {"user_id": vk_user_id, "message": "Вы успешно зарегистрированы!"})

    async def tg_main(self):
        while True:
            messages = await self.tg_bot.get_updates()
            for message in messages:
                if message.text:
                    if message.text == '/start':
                        user_id = message.from_user.id
                        self.user_data[user_id] = {"name": None, "group": None, "institution": None}
                        await self.tg_bot.send_message(user_id, "Давайте зарегистрируем вас! Введите ваше имя:")
                    elif self.user_data.get(message.from_user.id):
                        user_id = message.from_user.id
                        registration_data = self.user_data[user_id]

                        if not registration_data["name"]:
                            registration_data["name"] = message.text
                            await self.tg_bot.send_message(user_id, "Введите вашу группу:")
                        elif not registration_data["group"]:
                            registration_data["group"] = message.text
                            await self.tg_bot.send_message(user_id, "Введите название учебного заведения:")
                        elif not registration_data["institution"]:
                            registration_data["institution"] = message.text
                            if await self.register_user(user_id, registration_data["name"], registration_data["group"], registration_data["institution"]):
                                await self.tg_bot.send_message(user_id, "Вы успешно зарегистрированы!")

    async def start(self):
        # Запуск обработчиков для Telegram и ВКонтакте
        await asyncio.gather(self.tg_main(), self.vk_main())


user_data = {} 
if __name__ == "__main__":
    tg_token = "TOKEN"
    vk_token = "TOKEN"
    db_connection = None  # Инициализируйте соединение с базой данных

    bot = BotHandler(tg_token, vk_token, db_connection)
    asyncio.run(bot.start())
