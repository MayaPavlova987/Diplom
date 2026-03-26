# Дипломный проект: Автоматизация тестирования веб-сервиса "Путешествие дня"

## Описание проекта

Проект представляет собой автоматизированное тестирование веб-сервиса "Путешествие дня", который позволяет приобрести тур с помощью дебетовой карты или в кредит.

### Задачи проекта:
- Проверка функционала приобретения тура
- Проверка корректности структуры базы данных
- Проверка результатов выполнения операций

### Особенности:
- Приложение взаимодействует с эмулятором банковских сервисов (gate-simulator)
- Поддерживает работу с двумя СУБД: MySQL и PostgreSQL
- Тесты выполняются для обеих баз данных

## Технологии

| Технология | Назначение |
|------------|------------|
| Java 11 | Язык программирования |
| Gradle | Система сборки |
| JUnit 5 | Фреймворк для тестирования |
| Selenide | Автоматизация UI-тестов |
| Docker / Docker Compose | Контейнеризация |
| Allure | Формирование отчётов |
| Lombok | Сокращение шаблонного кода |
| JavaFaker | Генерация тестовых данных |
| DBUtils | Работа с базами данных |

## Запуск проекта

### 1. Клонирование репозитория

Склонируйте репозиторий:

- `git clone https://github.com/MayaPavlova987/Diplom.git`

Затем перейдите в папку проекта:


- `cd Diplom`
### 2. Запуск контейнеров
Запустите контейнеры:


- `docker-compose up -d`

Проверьте работу контейнеров:


- `docker ps`
Должны быть запущены:

- `diplom-mysql-1 (порт 3306)`

- `diplom-postgresql-1 (порт 5432)`

- `diplom-gate-simulator-1 (порт 9999)`

### 3. Запуск тестируемого приложения (SUT)
Для MySQL:

- `java -Dspring.datasource.url=jdbc:mysql://localhost:3306/app -Dspring.datasource.username=app -Dspring.datasource.password=pass -Dspring.credit-gate.url=http://localhost:9999/credit -Dspring.payment-gate.url=http://localhost:9999/payment -jar artifacts\aqa-shop.jar`
Для PostgreSQL:
bash
- `java -Dspring.datasource.url=jdbc:postgresql://localhost:5432/app -Dspring.datasource.username=app -Dspring.datasource.password=pass -Dspring.credit-gate.url=http://localhost:9999/credit -Dspring.payment-gate.url=http://localhost:9999/payment -jar artifacts\aqa-shop.jar`
Приложение будет доступно по адресу: http://localhost:8080

## Запуск тестов

Запуск всех тестов:

- `./gradlew clean test`

Запуск тестов с определённой БД:
### Для MySQL:


- `./gradlew clean test -Ddb.url=jdbc:mysql://localhost:3306/app`

### Для PostgreSQL:


- `./gradlew clean test -Ddb.url=jdbc:postgresql://localhost:5432/app`

### Запуск конкретного тестового класса:

- `./gradlew clean test --tests "ru.netology.test.PaymentTest"`
### Запуск конкретного теста:

- `./gradlew clean test --tests "ru.netology.test.PaymentTest.shouldSuccessPaymentWithApprovedCard"`Формирование отчётов
### Стандартный отчёт Gradle:

- `start build/reports/tests/test/index.html`
### Отчёт Allure:
Для просмотра Allure отчета выполните команду:


- `./gradlew allureServe`
Отчет автоматически откроется в браузере.

### Или сгенерируйте статический отчет:


- `./gradlew allureReport`
После этого откройте файл build/reports/allure-report/index.html в браузере.

##  Структура проекта

- `Diplom/`
    - `README.md`
    - `docs/`
        - `Plan.md`
        - `Report.md`
        - `Summary.md`
    - `artifacts/`
        - `aqa-shop.jar`
    - `gate-simulator/`
        - `app.js`
        - `data.json`
        - `Dockerfile`
    - `docker-compose.yml`
    - `build.gradle`
    - `src/`
        - `test/`
            - `java/`
                - `ru.netology/`
                    - `data/`
                    - `db/`
                    - `page/`
                    - `test/`
                        - `validation/`
##  Результаты тестирования

| Параметр | Значение |
|----------|----------|
| Всего тестов | 66 |
| Успешно пройдено | 38 (57%) |
| Провалено (баги) | 28 (43%) |
| Всего заведено Issues | 67 |
| Время выполнения | 5 мин 39 сек |

##  Ключевые найденные дефекты

| Issue | Описание |
|-------|----------|
| [#001](https://github.com/MayaPavlova987/Diplom/issues/1) | При покупке в кредит не заполняется `credit_id` (MySQL) |
| [#006](https://github.com/MayaPavlova987/Diplom/issues/6) | При покупке в кредит не заполняется `credit_id` (PostgreSQL) |
| [#007](https://github.com/MayaPavlova987/Diplom/issues/7) | При оплате в `payment_id` записывается `transaction_id` (MySQL) |
| [#008](https://github.com/MayaPavlova987/Diplom/issues/8) | При оплате в `payment_id` записывается `transaction_id` (PostgreSQL) |

##  Документация

- [План автоматизации](docs/Plan.md)
- [Отчёт о тестировании](docs/Report.md)
- [Итоговый отчёт](docs/Summary.md)
- [Баг-репорты (GitHub Issues)](https://github.com/MayaPavlova987/Diplom/issues)