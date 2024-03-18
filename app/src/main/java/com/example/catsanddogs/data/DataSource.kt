package com.example.catsanddogs.data

import com.example.catsanddogs.R
import com.example.catsanddogs.screens.Cure
import com.example.catsanddogs.screens.Foster

class DataSource {
    fun loadCures(): List<Cure> {
        return listOf<Cure>(
            Cure(R.string.topic1, R.string.cat_vomit),
            Cure(R.string.topic2, R.string.cat_fleas),
            Cure(R.string.topic3, R.string.dog_parasites),
            Cure(R.string.topic4, R.string.cat_tapeworms),


            )
    }

    fun loadFoster(): List<Foster> {
        return listOf<Foster>(
            Foster(R.string.topic5, R.string.dhaka),
            Foster(R.string.topic6, R.string.sylhet),
            Foster(R.string.topic7, R.string.chittagong),
            Foster(R.string.topic8, R.string.khulna)
        )
    }
}