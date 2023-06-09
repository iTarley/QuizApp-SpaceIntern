package com.space.quizapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.space.quizapp.data.local.model.entity.QuizQuestionEntity
import com.space.quizapp.data.local.model.entity.QuizSubjectEntity

@Dao
interface QuizDao {
    @Query("SELECT * FROM quiz_question where id=:id")
    suspend fun getQuizQuestion(id:Int): List<QuizQuestionEntity>

    @Query("SELECT * FROM subjects")
    suspend fun getQuiz(): List<QuizSubjectEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuizQuestion(quiz: List<QuizQuestionEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuiz(quiz: QuizSubjectEntity)
}