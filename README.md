# ReqRes API Test Framework

Автоматизированное тестирование REST API [ReqRes](https://reqres.in) на Java + REST Assured + TestNG.

## 🛠 Стек
- Java 11
- REST Assured 5.4.0
- TestNG 7.9.0
- Jackson 2.16.1
- JSON Schema Validator
- Maven
## 📁 Структура проекта
src/test/java/API/
├── config/ # ApiConfig
├── models/ # POJO: User, UserResponse, LoginRequest, RegisterRequest
├── specs/ # ApiSpec (Request/Response спецификации)
├── tests/ # UserTest, AuthTest, ResourcesTests, SchemaTests
└── BaseTest/ # BaseTest
## ✅ Тесты (16)

| Класс | Тесты | Описание |
|-------|-------|----------|
| `UserTest` | 5 | CRUD пользователей |
| `AuthTest` | 4 | Регистрация и логин |
| `ResourcesTests` | 5 | CRUD ресурсов /unknown |
| `SchemaTests` | 2 | JSON Schema валидация |

## 🚀 Запуск

```bash
mvn clean test
