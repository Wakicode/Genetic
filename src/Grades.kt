import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt
import kotlin.random.Random

class Grades (
    override val target: Double, val deviationTarget: Double = 1.0, private val count:Int
) :IMutable<MutableList<Double>, Double> {

    override val geneset: MutableList<Double> =  mutableListOf()
    override var bestParent: MutableList<Double> = createParent(count)
    override var bestFitness: Double = getFitness(bestParent)
    val precision = 0.01
    private val factor
        get() = 0.5

    val deviation
        get()= bestParent.stdDeviation()

    /**
     * 1.- Create parent
     * 2.- Calculate parent fitness
     * 3.- Start.
     *  3.1.- Create child, compare and mutate
     *  3.2.- IsSuccess?
     */
    init {
        start()
    }

    override fun isSuccess(): Boolean = 1- bestFitness <= precision


    override fun start() {
        var childFitness: Double
        var child: MutableList<Double>
        var attemps = 0
        do {
            do {
                child = mutate(bestParent)
                childFitness = getFitness(child)
                attemps ++
            } while (bestFitness >= childFitness )

//            //Display(child, bestFitness)

            bestFitness = childFitness
            bestParent = child
            println("Average: ${bestParent.average()} Deviation: $deviation Attemps: $attemps")
        } while (!isSuccess())
    }

    override fun createParent(param: Any): MutableList<Double> = (0..count).map { Random.nextDouble(10.0) }.toMutableList()

    override fun getFitness(obj: MutableList<Double>): Double {
        val a = obj.average()
        val d = obj.stdDeviation()
        val av = ((target -  abs(a - target))  / target)
        val absdev= abs(deviationTarget - d)
        val dev = (( if (absdev>deviationTarget) deviationTarget else absdev) / deviationTarget) * 0.2
        val result = av - dev
        return result
    }

    override fun mutate(parent: MutableList<Double>): MutableList<Double> {
        val sort = parent.sort()
        val index = if (parent.average()>target) parent.size-1 else 0

        parent[index] = Random.nextDouble(10.0)
        return parent
    }


}

fun MutableList<Double>.variance(): Double {
    val n = this.count()
    val average = this.average()
    return this.map { (it - average).pow(2.0) / n}.sum()
}

fun MutableList<Double>.stdDeviation() = sqrt(this.variance())