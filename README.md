  docker-compose up   

   java -jar ./artifacts/aqa-shop.jar




   ./gradlew clean test -D db.url=jdbc:mysql://localhost:3306/app 



./gradlew allureServe
