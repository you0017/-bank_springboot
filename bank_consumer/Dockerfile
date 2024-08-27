# 使用 OpenJDK 17 基础镜像
FROM openjdk:17-jdk-alpine

# 设置工作目录
WORKDIR /app

# 复制子项目的 JAR 文件到容器中
# 需要替换为实际的子项目 JAR 文件名
COPY target/bank_consumer.jar bank_consumer.jar

# 暴露应用的端口
EXPOSE 8081

# 运行应用
CMD ["java", "-jar", "bank_consumer.jar"]
