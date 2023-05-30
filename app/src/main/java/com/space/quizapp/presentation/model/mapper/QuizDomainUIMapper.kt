package com.space.quizapp.presentation.model.mapper

import com.space.quizapp.data.local.model.dto.QuizDtoItem
import com.space.quizapp.data.local.model.mapper.quiz.QuizQuestionDtoDomainMapper
import com.space.quizapp.domain.model.QuizDomainModelItem
import com.space.quizapp.presentation.model.QuizUIModel
import com.space.quizapp.utils.mapper.ModelMapper

class QuizDomainUIMapper(
    private val quizQuestionDomainUIMapper: QuizQuestionDomainUIMapper
) : ModelMapper<QuizDomainModelItem, QuizUIModel> {
    override fun invoke(model: QuizDomainModelItem): QuizUIModel =
        QuizUIModel(
            id = model.id,
            image = model.image,
            questions = model.questions.map { quizQuestionDomainUIMapper(it) },
            questionsCount = model.questionsCount,
            quizDescription = model.quizDescription,
            quizTitle = model.quizTitle
        )
}

class QuizQuestionDomainUIMapper :
    ModelMapper<QuizDomainModelItem.Question, QuizUIModel.Question> {

    override fun invoke(model: QuizDomainModelItem.Question): QuizUIModel.Question =
        QuizUIModel.Question(
            questionTitle = model.questionTitle,
            answers = model.answers,
            correctAnswer = model.correctAnswer,
            questionIndex = model.questionIndex
        )
}