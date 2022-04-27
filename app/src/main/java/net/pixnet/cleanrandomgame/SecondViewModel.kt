package net.pixnet.cleanrandomgame

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SecondViewModel : ViewModel() {
    private val randomNumberLiveData: MutableLiveData<Coordinate> = MutableLiveData<Coordinate>()
    private var generateCoordinateJob: Job? = null
    private val scope = MainScope()

    fun startUpdates(inputX: Int, inputY: Int) {

        generateCoordinateJob = scope.launch {
            while (true) {
                val coordinate = getRandomCoordinate(inputX, inputY)
                println("x: ${coordinate.x} y:${coordinate.y} index:${coordinate.index}")
                randomNumberLiveData.value = coordinate

                println("index: ${coordinate.index}")
                delay(10000)
            }
        }
    }

    fun stopUpdates() {
        generateCoordinateJob?.cancel()
        generateCoordinateJob = null
    }

    private fun getRandomCoordinate(limitX: Int, limitY: Int): Coordinate {
        return Coordinate((1 until limitX).random(), (1 until limitY).random(), limitX)
    }

    fun getRandomNumberLiveData(): MutableLiveData<Coordinate> {
        return randomNumberLiveData
    }
}