# 👩‍💻 The Import Users Kata

> This is a refactoring kata 🔧

The kata consists of reading, parsing & printing data from different sources (csv & network).

---

A former colleague created a simple script to read user information. However, after they left the company, the code became more challenging to maintain as modifications were made. You’ve recently made some updates, but the script has become even harder to work with. Moreover, you’ve noticed a specific issue: the printed dates of birth are incorrect and need fixing.

You discussed the situation with your manager, who has agreed to give you time to refactor the script for better readability and maintainability, and to resolve the date of birth display issue. The current code is straightforward but overly tangled, and team members are hesitant to make further changes.

- Refactor the current code for clarity and maintainability, ensuring the original behavior is preserved.
- Correct the date of birth display to ensure accuracy.

### ⏩ Next iteration

Instead of displaying the output through terminal, store the information
somewhere else, like a text file or a SQLite database.

### ⏩ Next iteration

We've another data source, a SQLite database file named users-source.db.
Please update the code to also read data from there.

JDBC url: `jdbc:sqlite:file:users-source.db`


# Instructions

This kata is available in the following languages:

### 🐘 PHP

From `php` folder, run:

```bash
php run.php
```

### ✨ Javascript

From `javascript` folder, run:

```bash
npm install
node run.js
```

> It is required at least `Node 18` to have access to the `fetch()` library

### ☕ Java

From `java` folder, run:

```bash
gradlew run
```

### 🎁 Kotlin

From `kotlin` folder, run:

```bash
gradlew build
gradlew run
```

### :snake: Python

From `python` folder, run:

```bash
python3 main.py
```
