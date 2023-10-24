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
    private var mQuestionsList: ArrayList<Question>? = null
    private var mSelectedOption: Int = 0
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
        progressBar = findViewById(R.id.progressBar)
        progression = findViewById(R.id.progress)
        tvQues = findViewById(R.id.tvQues)
        image = findViewById(R.id.Image)
        tvOp1 = findViewById(R.id.tvOp1)
        tvOp2 = findViewById(R.id.tvOp2)
        tvOp3 = findViewById(R.id.tvOp3)
        tvOp4 = findViewById(R.id.tvOp4)
        btnSubmit = findViewById(R.id.btnSubmit)
        mQuestionsList = Constants.getQuestions()
        applyQuestion()
        defaultOptionView()
    }

    private fun applyQuestion(){
        defaultOptionView()
        val question: Question = mQuestionsList!![mCurrentPosition - 1]
        progressBar?.progress = mCurrentPosition
        progression?.text = "$mCurrentPosition/${mQuestionsList!!.size}"
        tvQues?.text = question.question
        tvOp1?.text = question.option1
        tvOp2?.text = question.option2
        tvOp3?.text = question.option3
        tvOp4?.text = question.option4
        image?.setImageResource(R.drawable.q1)
        image?.setBackgroundResource(R.drawable.picture_shape)
        if(mCurrentPosition == mQuestionsList!!.size){
            btnSubmit?.text = "FINISH"
        }else{
            btnSubmit?.text = "SUBMIT"
        }
    }
    private fun defaultOptionView(){
        val allOptions = ArrayList<TextView>()
        tvOp1?.let {
            allOptions.add(0,it)
        }
        tvOp2?.let {
            allOptions.add(1,it)
        }
        tvOp3?.let {
            allOptions.add(2,it)
        }
        tvOp4?.let {
            allOptions.add(3,it)
        }
        for (eachOption in allOptions){
            eachOption.background = ContextCompat.getDrawable(this,R.drawable.options_bg)
            eachOption.typeface = Typeface.DEFAULT
            eachOption.setTextColor(Color.parseColor("#000000"))
        }
    }

    override fun onClick(view: View) {
        TODO("Not yet implemented")
    }
}