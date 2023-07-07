package com.space.util.mapper

interface ModelMapper<in MODEL_A, out MODEL_B> {
    operator fun invoke(model: MODEL_A): MODEL_B
}