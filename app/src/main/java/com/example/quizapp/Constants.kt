package com.example.quizapp

object Constants {
    fun getQuestions(): ArrayList<Question>{
        val questionsList = ArrayList<Question>()
        // 1
        val que1 = Question(1,
            "What will the output of this code be?",
            R.drawable.q1,
            "The numbers 1 through 15 will be printed.",
            "The numbers 1 through 14 will be printed.",
            "The number 14 will be printed.",
            "The number 15 will be printed.",
            4)
        questionsList.add(que1)
        //2
        val que2 = Question(2,
            "Find Error/Output in following code",
            R.drawable.q2,
            "128",
            "129",
            "130",
            "131",
            2)
        questionsList.add(que2)
        //3
        val que3 = Question(3,
            "Find Error/Output in following code",
            R.drawable.q3,
            "10 and 11",
            "10",
            "11",
            "11 and 11",
            2)
        questionsList.add(que3)
        //4
        val que4 = Question(4,
            "Find Error/Output in following code",
            R.drawable.q4,
            "Syntax error because of invalid operator symbol",
            "25 16",
            "16 16",
            "Syntax error because of invalid array initialization",
            3)
        questionsList.add(que4)
        //5
        val que5 = Question(5,
            "Find Error/Output in following code",
            R.drawable.q5,
            "8 6 4 2",
            "Infinite output",
            "Invalid because main function can't call itself.",
            "6 4 2 0",
            4)
        questionsList.add(que5)
        //6
        val que6 = Question(6,
            "Find Error/Output in following code",
            R.drawable.q6,
            "4160",
            "Infinite output",
            "844",
            "None of the above",
            1)
        questionsList.add(que6)
        //7
        val que7 = Question(7,
            "Find Error/Output in following code",
            R.drawable.q7,
            "38 753",
            "75 538",
            "538 38",
            "0 753",
            1)
        questionsList.add(que7)
        //8
        val que8 = Question(8,
            "Find Error/Output in following code",
            R.drawable.q8,
            "0 times",
            "Infinite times",
            "10 times",
            "1 times",
            2)
        questionsList.add(que8)
        //9
        val que9 = Question(9,
            "Find Error/Output in following code",
            R.drawable.q9,
            "hello",
            "No output",
            "Infinite time hello display",
            "Error in code",
            2)
        questionsList.add(que9)
        //10
        val que10 = Question(10,
            "Find Error/Output in following code",
            R.drawable.q10,
            "Syntax error due to Invalid expression in printf",
            "Print junk value",
            "16",
            "10",
            4)
        questionsList.add(que10)

        return questionsList
    }
}