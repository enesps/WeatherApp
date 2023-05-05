# Kotlin uygulamasının temel Docker imajını belirleyin
FROM openjdk:11

# Uygulama kodunu Docker imajına kopyalayın
COPY . /app

# Uygulamayı derleyin
RUN cd /app && kotlinc Hello.kt -include-runtime -d hello.jar

# Çalıştırılacak komutu belirtin
CMD ["java", "-jar", "/app/hello.jar"]
