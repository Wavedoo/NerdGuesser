package com.example.nerdguesser.model.repositories

import com.example.nerdguesser.R
import com.example.nerdguesser.model.classes.AnswerData
import com.example.nerdguesser.model.classes.Hints

//TODO: Learn about singletons/objects/repositories/interfaces/etc
class MockServer {
    fun getGameData(gameNumber: Int): AnswerData {
        val name = "Frieren"
        val hints = Hints()
        val imageIds = listOf<Int>(
            R.drawable.frieren_village_1,
            R.drawable.frieren_ring_2,
            R.drawable.himmel_dead_3,
            R.drawable.ubel_4,
            R.drawable.fern_stark_5,
            R.drawable.frieren_landscape_6
        )
        return AnswerData(name = name, hints = hints, images = imageIds)
    }
}