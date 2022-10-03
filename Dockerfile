FROM adoptopenjdk/openjdk11:ubi
ARG JAR_FILE=target/*.jar
ENV BOT_NAME=Spencer_My_Telegram_bot
ENV BOT_TOKEN=5296153351:AAGFAvT3vYJsggYZ-pzZQ24A1DIVmceBSfU
#COPY ${JAR_FILE} app.jar
#ENTRYPOINT ["java", "-Dbot.username=${BOT_NAME}", "-Dbot.token=${BOT_TOKEN}", "-jar", "app.jar"]
ENV BOT_DB_USERNAME=mtbtb_db_user
ENV BOT_DB_PASSWORD=mtb_db_password
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Dspring.datasource.password=${BOT_DB_PASSWORD}", "-Dbot.username=${BOT_NAME}", "-Dbot.token=${BOT_TOKEN}", "-Dspring.datasource.username=${BOT_DB_USERNAME}", "-jar", "app.jar"]
#FROM adoptopenjdk/openjdk11:ubi
 #ARG JAR_FILE=target/*.jar
 #ENV BOT_NAME=test.javarush_community_bot
 #ENV BOT_TOKEN=1375780501:AAE4A6Rz0BSnIGzeu896OjQnjzsMEG6_uso
 #ENV BOT_DB_USERNAME=jrtb_db_user
 #ENV BOT_DB_PASSWORD=jrtb_db_password
 #COPY ${JAR_FILE} app.jar
 #ENTRYPOINT ["java","-Dspring.datasource.password=${BOT_DB_PASSWORD}", "-Dbot.username=${BOT_NAME}", "-Dbot.token=${BOT_TOKEN}", "-Dspring.datasource.username=${BOT_DB_USERNAME}", "-jar", "app.jar"]