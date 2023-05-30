package com.space.quizapp.data.local.model.mapper.quiz

import com.space.quizapp.data.local.model.dto.QuizDtoItem
import com.space.quizapp.domain.model.QuizDomainModelItem
import com.space.quizapp.utils.mapper.ModelMapper

class QuizDtoDomainMapper(
    private val quizQuestionDtoDomainMapper: QuizQuestionDtoDomainMapper
) : ModelMapper<QuizDtoItem, QuizDomainModelItem> {
    override fun invoke(model: QuizDtoItem): QuizDomainModelItem =
        QuizDomainModelItem(
            id = model.id,
            image = model.image,
            questions = model.questions.map { quizQuestionDtoDomainMapper(it) },
            questionsCount = model.questionsCount,
            quizDescription = model.quizDescription,
            quizTitle = model.quizTitle
        )
}

class QuizQuestionDtoDomainMapper :
    ModelMapper<QuizDtoItem.QuestionDto, QuizDomainModelItem.Question> {

    override fun invoke(model: QuizDtoItem.QuestionDto): QuizDomainModelItem.Question =
        QuizDomainModelItem.Question(
            questionTitle = model.questionTitle,
            answers = model.answers,
            correctAnswer = model.correctAnswer,
            questionIndex = model.questionIndex
        )
}

