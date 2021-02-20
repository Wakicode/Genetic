import kotlin.random.Random


class GuessString (override val target: Double, override val geneset: String) : IMutable<String,Double>{
    val guess = "Porque te tengo y no porque te pienso\n" +
            "porque la noche está de ojos abiertos\n" +
            "porque la noche pasa y digo amor\n" +
            "porque has venido a recoger tu imagen\n" +
            "y eres mejor que todas tus imágenes\n" +
            "porque eres linda desde el pie hasta el alma\n" +
            "porque eres buena desde el alma a mí\n" +
            "porque te escondes dulce en el orgullo\n" +
            "pequeña y dulce\n" +
            "corazón coraza"
    override var bestParent: String = createParent(guess.length)
    override var bestFitness: Double = getFitness(bestParent)
    private var attempts = 0

    init {
        start()
    }


    override fun start()
    {
        var childFitness: Double
        var child: String

        do {
            do {
                child = mutate(bestParent)
                childFitness = getFitness(child)
            } while (bestFitness >= childFitness)

            bestFitness = childFitness
            bestParent = child
            attempts ++
        } while (!isSuccess())
        println("Intentos: $attempts")
    }

    override fun isSuccess(): Boolean {
        val value = (bestFitness.toString().toDouble() / guess.length)
        return value >= target
    }


    override fun createParent(param: Any) : String {
        val genes = StringBuilder()
        val length = geneset.length
        (0 until param.toString().toInt()).forEach {
            genes.append(geneset[Random.nextInt(length)])
        }

        return genes.toString()
    }

    override fun getFitness(obj: String): Double = guess.mapIndexed { i, c -> if (c==obj[i]) 1.0 else 0.0}.sum()


    override fun mutate(parent: String): String {
        var index = 0
        val parentFitness: Double = getFitness(parent)
        var childFitness = 0.0

        var newGene: Char
        var alternate: Char
        var definitive: Char
        var indexGeneset: Int
        var child: String

        var length =  geneset.length

        do {
            index = Random.nextInt(parent.length)
            indexGeneset = Random.nextInt( length)

            newGene = geneset[indexGeneset]

            indexGeneset = Random.nextInt( length)
            alternate = geneset[indexGeneset]
            //definitivo
            definitive = if (newGene == parent[index]) alternate else newGene

            child = parent.replaceRange(index,index+1, definitive.toString())

            childFitness = getFitness(child)


        } while (parentFitness >= childFitness)

        return child
    }
}


