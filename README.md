# Окружение

- Установить IntelliJ IDEA Ultimate


https://www.jetbrains.com/ru-ru/idea/download/?section=windows


- Установить  Docker Desktop


https://www.docker.com/products/docker-desktop/


# Процедура запуска автотестов


- Открыть  Docker Desktop


- Открыть IntelliJ IDEA Ultimate


- С помощью команды open найти в папке файл Diplom56


- Открыть терминал в IntelliJ IDEA Ultimate поочередно нажать команды:


1. docker-compose up  (запуск контейнера)


2. java -jar ./artifacts/aqa-shop.jar (запуск джар-файла)


3. ./gradlew clean test -D db.url=jdbc:mysql://localhost:3306/app (запуск автотестов)


4. ./gradlew allureServe (отчет о результате тестирования,открывается автоматически в новом окне браузера)

 
*Для каждой команды команды открывается новая вкладка терминала 
