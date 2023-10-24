package com.example.quizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {
    private var quesCurrentPosition: Int = 1
    private var questionsList: ArrayList<Question> ?= null
    private var mSelectedOptionNum: Int = 0
    private var username: String ?= null
    private var correctAnswers: Int = 0
    private var progressBar: ProgressBar? = null
    private var progression: TextView? = null
    private var tvQues: TextView? = null
    private var image: ImageView? = null
    private var tvOp1: TextView? = null
    private var tvOp2: TextView? = null
    private var tvOp3: TextView? = null
    private var tvOp4: TextView? = null
    private var btnSubmit: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        username = intent.getStringExtra(Constants.USER_NAME)

        progressBar = findViewById(R.id.progressBar)
        progression = findViewById(R.id.progress)
        tvQues = findViewById(R.id.tvQues)
        image = findViewById(R.id.Image)
        tvOp1 = findViewById(R.id.tvOp1)
        tvOp2 = findViewById(R.id.tvOp2)
        tvOp3 = findViewById(R.id.tvOp3)
        tvOp4 = findViewById(R.id.tvOp4)
        btnSubmit = findViewById(R.id.btnSubmit)
        questionsList = Constants.getQuestions()
        tvOp1?.setOnClickListener(this)
        tvOp2?.setOnClickListener(this)
        tvOp3?.setOnClickListener(this)
        tvOp4?.setOnClickListener(this)
        btnSubmit?.setOnClickListener (this)
        applyQuestion()
        defaultOptionView()
    }

    private fun applyQuestion(){
        defaultOptionView()
        val eachQuestion = questionsList?.get(quesCurrentPosition - 1)
        progressBar?.progress = quesCurrentPosition
        progression?.text = "$quesCurrentPosition/${questionsList?.size}"
        tvQues?.text = eachQuestion?.question
        tvOp1?.text = eachQuestion?.option1
        tvOp2?.text = eachQuestion?.option2
        tvOp3?.text = eachQuestion?.option3
        tvOp4?.text = eachQuestion?.option4
        image?.setImageResource(R.drawable.q1)
        image?.setBackgroundResource(R.drawable.picture_shape)
        if(quesCurrentPosition == questionsList?.size){
            btnSubmit?.text = "FINISH"
        }else{
            btnSubmit?.text = "SUBMIT"
        }
    }
    private fun defaultOptionView(){
        val options = ArrayList<TextView>()
        tvOp1?.let {
            options.add(0,it)
        }
        tvOp2?.let {
            options.add(1,it)
        }
        tvOp3?.let {
            options.add(2,it)
        }
        tvOp4?.let {
            options.add(3,it)
        }
        for (eachOption in options){
            eachOption.background = ContextCompat.getDrawable(this,R.drawable.options_bg)
            eachOption.typeface = Typeface.DEFAULT
            eachOption.setTextColor(Color.parseColor("#000000"))
        }
    }

    private fun answerView(answer: Int, drawableView: Int) {
        when (answer) {
            1 -> {
                tvOp1?.background = ContextCompat.getDrawable(this, drawableView)
            }
            2 -> {
                tvOp2?.background = ContextCompat.getDrawable(this, drawableView)
            }
            3 -> {
                tvOp3?.background = ContextCompat.getDrawable(this, drawableView)
            }
            4 -> {
                tvOp4?.background = ContextCompat.getDrawable(this, drawableView)
            }
        }
    }

    private fun selectedOptionView(option: TextView, mSelectedOption: Int) {
        defaultOptionView()
        mSelectedOptionNum = mSelectedOption
        option.setTextColor(Color.parseColor("#000000"))
        option.typeface = Typeface.DEFAULT_BOLD
        option.background = ContextCompat.getDrawable(this, R.drawable.selected_option_bg_color)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.tvOp1 -> {
                tvOp1?.let { selectedOptionView(it, 1) }
            }
            R.id.tvOp2 -> {
                tvOp2?.let { selectedOptionView(it, 2) }
            }
            R.id.tvOp3 -> {
                tvOp3?.let { selectedOptionView(it, 3) }
            }
            R.id.tvOp4 -> {
                tvOp4?.let { selectedOptionView(it, 4) }
            }
            R.id.btnSubmit->{
                //If no option is selected (mselectedOptionNum is 0), we move to the next question.
                //We ensure that we haven't reached the end of the question list before showing the next question.
                if (mSelectedOptionNum == 0) {
                    quesCurrentPosition++
                    when {
                        quesCurrentPosition <= questionsList!!.size -> {
                            applyQuestion()
                        }
                        else -> {
                            val intent = Intent(this,ResultPage::class.java)
                            intent.putExtra(Constants.USER_NAME,username)
                            intent.putExtra(Constants.CORRECT_ANSWERS, correctAnswers)
                            intent.putExtra(Constants.TOTAL_QUESTIONS,questionsList?.size)
                            startActivity(intent)
                            finish()
                        }
                    }
                } else {
                    val questions = questionsList?.get(quesCurrentPosition - 1)
                    if (mSelectedOptionNum != questions?.correctAnswer) {
                        answerView(mSelectedOptionNum, R.drawable.wrong_option_bg)
                    }
                    else {
                        correctAnswers++
                    }
                    answerView(questions!!.correctAnswer, R.drawable.correct_option_bg)
                    if (quesCurrentPosition == questionsList?.size) {
                        btnSubmit?.text = "FINISH"
                    }
                    else {
                        btnSubmit?.text = "GO TO NEXT QUESTION"
                    }
                    mSelectedOptionNum = 0
                }
            }
        }
    }
}