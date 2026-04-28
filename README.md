# Hang-Man-Project-JavaFX
An OOP course university final project

# 🎮 Hangman Game (JavaFX)

A fully interactive **Hangman game** built using **JavaFX**, featuring multiple game modes, custom UI design, and audio integration. This project was developed as part of an Object-Oriented Programming assignment.

---

## 🏷️ Badges

![Java](https://img.shields.io/badge/Java-17-orange)
![JavaFX](https://img.shields.io/badge/JavaFX-UI-blue)
![OOP](https://img.shields.io/badge/Concepts-OOP-green)
![Status](https://img.shields.io/badge/Status-Completed-brightgreen)
![License](https://img.shields.io/badge/License-MIT-lightgrey)

---

## 🚀 Features

- 🤖 **VS CPU Mode**
  - Random word generation
  - Select word length (4, 5, or 6 letters)

- 👥 **2 Players Mode**
  - One player inputs a word (3–7 letters)
  - Second player guesses

- 🎨 **Modern UI**
  - JavaFX layouts (`VBox`, `HBox`, `StackPane`, `BorderPane`)
  - Custom fonts and styled buttons
  - Background images

- 🔊 **Audio System**
  - Background music (toggle on/off)
  - Sound effects (click, win, loss)

- 🧠 **Game Logic**
  - Tracks used letters
  - Input validation (letters only)
  - Progressive hangman drawing
  - Win/Loss detection screens

---

## 🧠 How It Works

- A word is either randomly generated (CPU) or entered by a player
- Letters are hidden and displayed as blanks
- Player guesses one letter at a time
- Correct guesses reveal letters
- Wrong guesses draw the hangman
- Game ends when:
  - ✅ Word is fully guessed → **Victory**
  - ❌ Max wrong guesses reached → **Loss**

---

## 📸 Screenshots


### 🏠 Home Screen
![Home Screen](screenshots/home.png)

### 🎮 Gameplay
![Game Modes](screenshots/modes.png)

### 🤖 VS CPU Screen
![VS CPU Gameplay](screenshots/vs-cpu.png)

### 👥 2 Players Mode
![2 Players Mode](screenshots/pvp.png)

### 🏆 Victory Screen
![Victory](screenshots/victory.png)

### ❌ Loss Screen
![Loss](screenshots/loss.png)

---

## 🛠️ Technologies Used

- **Java**
- **JavaFX**
- **Java Media API**
- **OOP Principles**

---

## 📂 Project Structure
src/
└── com/example/projectoop/
└── HangManProject.java

resources/
├── images/
├── audios/
└── fonts/
