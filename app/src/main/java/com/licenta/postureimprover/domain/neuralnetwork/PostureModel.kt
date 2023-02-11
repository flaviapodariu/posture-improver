package com.licenta.postureimprover.domain.neuralnetwork

import org.jetbrains.kotlinx.dl.api.core.Sequential
import org.jetbrains.kotlinx.dl.api.core.layer.core.Dense
import org.jetbrains.kotlinx.dl.api.core.layer.core.Input
import org.jetbrains.kotlinx.dl.api.core.layer.reshaping.Flatten
import org.jetbrains.kotlinx.dl.api.core.loss.Losses
import org.jetbrains.kotlinx.dl.api.core.metric.Metrics
import org.jetbrains.kotlinx.dl.api.core.optimizer.Adam
import org.jetbrains.kotlinx.dl.api.summary.printSummary
import org.jetbrains.kotlinx.dl.dataset.embedded.mnist

val stringLabels = mapOf(
    0 to "Correct",
    1 to "Head forward",
    2 to "Kyphotic",
    3 to "Swayback",
    4 to "Flat back"
)


fun mockup(a: Boolean, b: Boolean) {

}

fun main() {
    val (train, test) = mnist()
    val model = Sequential.of(
        Input(28,28,1),
        Flatten(),
        Dense(300),
        Dense(100),
        Dense(10)
    )

    model.use {
        it.compile(
            optimizer = Adam(),
            loss = Losses.SOFT_MAX_CROSS_ENTROPY_WITH_LOGITS,
            metric = Metrics.ACCURACY
        )

        it.printSummary()

        // You can think of the training process as "fitting" the model to describe the given data :)
        it.fit(
            dataset = train,
            epochs = 10,
            batchSize = 100
        )

        val accuracy = it.evaluate(dataset = test, batchSize = 100).metrics[Metrics.ACCURACY]
    }
}