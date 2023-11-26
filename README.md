# Hacaton-2023
Чтобы запустить бота нужно ввести сввой токен в полу API_TOKEN в файлу Bot
Также нужно установить библиотеки:
asyncio
logging
mysql.connector
Для работы с бд нужно корректно указать данные в файле SQLset в методе connect
connect ():
    try:
        db = mysql.connector.connect(
            host="IP",
            port=port,
            user="root",
            password="********",
            database="Названия бд"
        )
бд сама построиться с помощью метода setup_database.       
