FROM node:18-alpine

WORKDIR /app

# Копируем файлы приложения
COPY app.js .
COPY data.json .
COPY package.json .

# Устанавливаем зависимости
RUN npm install

# Запускаем приложение
EXPOSE 9999
CMD ["node", "app.js"]