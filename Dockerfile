FROM adoptopenjdk/openjdk11:ubi
ARG JAR_FILE=target/*.jar
ENV BOT_NAME=Spencer_My_Telegram_bot
ENV BOT_TOKEN=5296153351:AAGFAvT3vYJsggYZ-pzZQ24A1DIVmceBSfU
ENV BOT_DB_USERNAME=mtb_db_user
ENV BOT_DB_PASSWORD=mtb_db_password
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Dspring.datasource.password=${BOT_DB_PASSWORD}", "-Dbot.username=${BOT_NAME}", "-Dbot.username=${BOT_NAME}", "-Dbot.token=${BOT_TOKEN}", "-jar", "/app.jar"]