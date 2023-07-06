package com.space.quizapp.domain.usecase.quiz.get.quiz_gpa

import com.space.quizapp.domain.model.QuizDomainModel

interface GetQuizForGpaUseCase {
    suspend fun invoke(userId:String):List<QuizDomainModel>
}