FROM adoptopenjdk/openjdk11:ubi
ARG JAR_FILE=target/*.jar
ENV BOT_NAME=Spencer_My_Telegram_bot
ENV BOT_TOKEN=5296153351:AAGFAvT3vYJsggYZ-pzZQ24A1DIVmceBSfU
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Dbot.username=${BOT_NAME}", "-Dbot.token=${BOT_TOKEN}", "-jar", "/app.jar"]