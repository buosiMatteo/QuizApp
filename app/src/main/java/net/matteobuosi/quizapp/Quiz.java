package net.matteobuosi.quizapp;

public class Quiz {
    private String questionText;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
    private int trueAnswer;

    public Quiz(String questionText, String answer1, String answer2, String answer3, String answer4, int trueAnswer) {
        this.questionText = questionText;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        if (trueAnswer > 0 && trueAnswer < 5) {
            this.trueAnswer = trueAnswer;
        } else {
            System.out.println("The value is not between 1 and 4");
        }

    }

    private int correctAnswer() {
        int ris = 0;

        switch (trueAnswer) {
            case 1:
                ris = 1;
                break;
            case 2:
                ris = 2;
                break;
            case 3:
                ris = 3;
                break;
            case 4:
                ris = 4;
                break;
        }
        return ris;
    }

    public boolean isCorrectAnswer(int answer) {
        if (answer == correctAnswer()) {
            return true;
        } else {
            return false;
        }
    }

    public String getQuestionText() {
        return questionText;
    }

    public String getAnswer1() {
        return answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public String getAnswer4() {
        return answer4;
    }

    public int getTrueAnswer() {
        return trueAnswer;
    }
}
