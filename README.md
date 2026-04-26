# 🧪 SauceDemo UI Automation Project

🌐 https://www.saucedemo.com/

---

## 📌 О проекте

Этот проект представляет собой UI automation framework для тестирования веб-приложения SauceDemo.

Цель проекта:
- Проверка ключевых пользовательских сценариев
- Тестирование UI и бизнес-логики интернет-магазина
- Построение автотестов с использованием Page Object Model

---

## ⚙️ Технологии

- Java 17
- Selenium WebDriver
- TestNG
- Maven
- Page Object Model (POM)

---

## 🧭 Структура проекта

```text
src/test/java/

├── pages/ # Page Object слой
│   ├── BasePage.java
│   ├── LoginPage.java
│   ├── ProductsPage.java
│   ├── CartPage.java
│   ├── CheckoutInformationPage.java
│   ├── CheckoutOverviewPage.java
│   ├── CheckoutCompletePage.java
│
├── tests/ # Тестовый слой
│   ├── BaseTest.java
│   ├── AuthBaseTest.java
│   ├── LoginTest.java
│   ├── ProductsTest.java
│   ├── CartTest.java
│   ├── CheckoutInformationTest.java
│   ├── CheckoutOverviewTest.java
│   ├── CheckoutCompleteTest.java
```

---

## 🧠 Архитектура

Проект построен по принципам Page Object Model:

- **BasePage** — базовый класс с WebDriver и общими методами
- **Page-классы** — содержат локаторы и действия со страницами
- **Тесты** — содержат только проверки (assert) и бизнес-логику

📌 Тесты не работают напрямую с локаторами

---

## 🧪 Покрытие тестами

---

## 🔐 LoginPage

- ✔ Успешный вход с валидными данными → переход на Products
- ❌ Ошибка при пустом пароле
- ❌ Ошибка при пустом логине
- ❌ Ошибка при неверных данных

---

## 🛍 ProductsPage

- 📦 Отображение списка товаров
- 💰 Проверка названия и цены товара
- ➕ Добавление товара в корзину
- 🔄 Кнопка Add to cart → Remove
- 🛒 Увеличение бейджа корзины

---

## 🛒 CartPage

- 📦 Товар отображается в корзине
- 💲 Проверка цены товара
- ❌ Удаление товара из корзины
- 🔘 Кнопки Checkout и Continue Shopping
- 🧾 Переход на checkout flow

---

## 🧾 Checkout Flow

---

## 🧾 CheckoutInformationTest

- 🧾 Отображение полей First Name / Last Name / Zip
- ✍️ Ввод данных в форму checkout
- ❌ Валидация обязательных полей (пустая форма)
- ⚠️ Проверка текста ошибки при некорректном вводе
- ➡️ Переход на Checkout Overview после заполнения формы
- 🔙 Возврат в корзину через кнопку Cancel

---

## 📦 CheckoutOverviewTest

- 📦 Отображение товаров из корзины
- 💰 Проверка отображения стоимости заказа (subtotal / tax / total)
- 🧾 Проверка информации пользователя
- 🔙 Кнопка Back → возврат на Information
- ❌ Кнопка Cancel → возврат в Cart
- 🎯 Кнопка Finish → завершение заказа

---

## 🎉 CheckoutCompleteTest

- 🎉 Сообщение об успешном оформлении заказа
- 📦 Проверка статуса завершённого заказа
- 🏠 Кнопка Back Home → возврат на Products

---

## 🔁 Основной пользовательский сценарий (E2E)

Login → Products → Cart → Checkout → Overview → Complete

---

## ▶️ Как запустить тесты

### Через IntelliJ IDEA:
1. Открыть проект
2. Запустить нужный тест (ПКМ → Run)

---

## 💡 Особенности проекта

- Page Object Model (POM)
- Разделение checkout на 3 уровня тестирования
- Возможность модульного и E2E тестирования
- Чистая архитектура pages / tests
- Тесты не зависят от локаторов

---

## 👩‍💻 Автор

Zvezdina Aleksandra