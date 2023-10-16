package com.example.quizapp

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
    private var mCurrentPosition: Int = 1
    private var mQuestionsList: ArrayList<Question> ?= null
    private var mSelectedOption: Int = 0
    private var progressBar: ProgressBar ?= null
    private var progression: TextView ?= null
    private var tvQues: TextView ?= null
    private var image: ImageView ?= null
    private var tvOp1: TextView ?= null
    private var tvOp2: TextView ?= null
    private var tvOp3: TextView ?= null
    private var tvOp4: TextView ?= null
    private var btnSubmit: Button ?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)
        progressBar = findViewById(R.id.progressBar)
        progression = findViewById(R.id.progress)
        tvQues = findViewById(R.id.tvQues)
        image = findViewById(R.id.Image)
        tvOp1 = findViewById(R.id.tvOp1)
        tvOp2 = findViewById(R.id.tvOp2)
        tvOp3 = findViewById(R.id.tvOp3)
        tvOp4 = findViewById(R.id.tvOp4)
        btnSubmit = findViewById(R.id.btnSubmit)
        tvOp1?.setOnClickListener(this)
        tvOp2?.setOnClickListener(this)
        tvOp3?.setOnClickListener(this)
        tvOp4?.setOnClickListener(this)
        btnSubmit?.setOnClickListener(this)
        mQuestionsList = Constants.getQuestions()
        setQuestion()
        defaultSelectView()
    }

    private fun setQuestion() {
        defaultSelectView()
        val question: Question = mQuestionsList!![mCurrentPosition - 1]
        image?.setImageResource(question.image)
        image?.setBackgroundResource(R.drawable.picture_shape)
        progressBar?.progress = mCurrentPosition
        progression?.text = "$mCurrentPosition/${progressBar?.max}"
        tvQues?.text = question.question
        tvOp1?.text = question.option1
        tvOp2?.text = question.option2
        tvOp3?.text = question.option3
        tvOp4?.text = question.option4
        if(mCurrentPosition == mQuestionsList!!.size){
            btnSubmit?.text = "FINISH"
        }else{
            btnSubmit?.text = "SUBMIT"
        }
    }
    private fun defaultSelectView(){
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
        for(option in options){
            option.setTextColor(Color.parseColor("#000000"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this, R.drawable.options_bg)
        }
    }
    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int){
        defaultSelectView()
        mSelectedOption = selectedOptionNum
        tv.setTextColor(Color.parseColor("#000000"))
        tv.setTypeface(tv.typeface,Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this, R.drawable.selected_option_bg_color)
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.tvOp1 -> tvOp1?.let {
                selectedOptionView(it,1)
            }
            R.id.tvOp2 -> tvOp2?.let {
                selectedOptionView(it,2)
            }
            R.id.tvOp3 -> tvOp3?.let {
                selectedOptionView(it,3)
            }
            R.id.tvOp4 -> tvOp4?.let {
                selectedOptionView(it,4)
            }
            R.id.btnSubmit -> {
                if(mSelectedOption == 0){
                    mCurrentPosition++
                    when{
                        mCurrentPosition <= mQuestionsList!!.size -> setQuestion()
                        else -> Toast.makeText(this, "You made it to the end!", Toast.LENGTH_LONG).show()
                    }
                }else{
                    val question = mQuestionsList?.get(mCurrentPosition - 1)
                    if(question?.correctAnswer != mSelectedOption){
                        answerView(mSelectedOption, R.drawable.wrong_option_bg)
                    }
                    answerView(question!!.correctAnswer, R.drawable.correct_option_bg)
                    if(mCurrentPosition == mQuestionsList!!.size){
                        btnSubmit?.text = "FINISH"
                    }
                    else{
                        btnSubmit?.text = "GO TO NEXT QUESTION"
                    }
                    mSelectedOption = 0
                }
            }
        }
    }
    private fun answerView(ans: Int, drawableView: Int){
        when(ans){
            1 -> tvOp1?.background = ContextCompat.getDrawable(this, drawableView)
            2 -> tvOp2?.background = ContextCompat.getDrawable(this, drawableView)
            3 -> tvOp3?.background = ContextCompat.getDrawable(this, drawableView)
            4 -> tvOp4?.background = ContextCompat.getDrawable(this, drawableView)
        }
    }
}

