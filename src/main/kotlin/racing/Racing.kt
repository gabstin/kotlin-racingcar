package racing

import mu.KLogging
import racing.View.display

class Racing(
    private val carCnt: CarCnt,
    private val roundCnt: RoundCnt
) {

    companion object : KLogging()

    fun start() {
        game(roundList = roundCnt.toList(), carList = carCnt.toList())
            .display()
    }

    fun game(roundList: List<Cnt>, carList: List<Cnt>): List<MutableList<Car>> {
        val games: MutableList<MutableList<Car>> = mutableListOf()

        roundList.forEachIndexed { index, _ ->
            val prevGame = kotlin.runCatching { games[index - 1] }.getOrDefault(emptyList())

            games.add(round(carList = carList, prevGame = prevGame).toMutableList())
        }

        return games
    }


    fun round(carList: List<Cnt>, prevGame: List<Car>): List<Car> {
        return carList.map { name ->
            val prevDistance = prevGame.firstOrNull { it.name == name }?.distance ?: 0
            val newDistance = Distance.get()

            logger.debug { "car : [$name], prevDistance : $prevDistance, newDistance : $newDistance" }
            return@map Car(name = name, distance = prevDistance + newDistance)
        }
    }


}

