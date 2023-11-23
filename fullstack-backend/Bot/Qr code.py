import qrcode

bot_username = '#@CAABotMyProdject_bot'
telegram_link = f'https://t.me/{bot_username}'

qr = qrcode.QRCode(
    version=1,
    error_correction=qrcode.constants.ERROR_CORRECT_L,
    box_size=10,
    border=4,
)
qr.add_data(telegram_link)
qr.make(fit=True)

img = qr.make_image(fill_color="black", back_color="white")

img.save('telegram_bot_qr.png')

img.show()