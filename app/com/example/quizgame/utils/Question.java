package com.example.quizgame.utils;

import com.example.quizgame.models.Question;
import com.example.quizgame.utils.QuestionBank;

import java.util.ArrayList;
import java.util.List;

public class QuestionBank {
    public static List<Question> getQuestions() {
        List<Question> questions = new ArrayList<>();

        questions.add(new Question("What is the full form of E-Mail?",
                new String[]{"Electric Mail", "Exchange Mail", "Electronic Mail", "Engagement Mail"}, 2));

        questions.add(new Question("Who is the Father of the Computer?",
                new String[]{"Charles Babbage", "Thomas Edison", "Albert Einstein", "Isaac Newton"}, 0));

        questions.add(new Question("WWW stands for?",
                new String[]{"World Without Windows", "World Wide Web", "World Wide Web Applications", "World Wide Warehouse"}, 1));

        return questions;
    }
}
