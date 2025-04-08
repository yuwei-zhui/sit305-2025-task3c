# Quiz App

The Quiz App is an Android application that allows users to participate in a multiple-choice quiz and also includes a simple calculator feature. The app offers a clean, unified user interface, clear navigation, and interactive feedback for each question.

## Features

- **Welcome Screen:**  
  The quiz screen greets the user with a personalized welcome message using the name provided in the main screen.

- **Multiple-Choice Quiz:**  
  - Users can answer multiple-choice questions.  
  - Each question provides four options.
  - The app highlights the selected option in green if it is correct and red if it is incorrect.
  - Users must tap a **Submit** button to check their answer, and then a **Next** button to continue to the next question.
  - The progress bar at the top displays the user's progress through the quiz.

- **Result Screen:**  
  Once all questions are answered, the app navigates to a result screen that displays the final score. Options are provided to either retake the quiz or exit the app.

- **Calculator (Extra Feature):**  
  A simple calculator is available for performing basic operations (addition and subtraction) between two numbers.

- **Consistent Design:**  
  The user interface is designed to be clean and unified, with consistent color schemes and typography. Custom drawables and styles are used to give a subtle, modern look.

## How to Build and Run

1. **Clone the Repository:**

   ```sh
   git clone <repository_link>
   cd Quiz
   ```

2. **Open the Project in Android Studio:**

   - Select **File > Open** and navigate to the project directory.
   - Allow Android Studio to import and sync the project.

3. **Build and Run:**

   - Click on the **Run** button or press **Shift+F10**.
   - Select your emulator or connected device to install and test the application.

## Usage

- On launch, users are prompted to enter their name.
- Tapping **Start Quiz** navigates to the quiz interface where a welcome message ("Welcome to the quiz, \<name>") is displayed.
- Users select an answer and press the **Submit** button to check their response.
- The answer is highlighted in green (if correct) or red (if incorrect), and then the **Next** button becomes available.
- After all questions are answered, the result screen shows the final score with options to retake the quiz or exit the app.
- The calculator can be accessed from the main screen.

