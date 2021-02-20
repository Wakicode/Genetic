interface IMutable<T,F: Comparable<F>>
{
    val geneset: T
    val target: F
    var bestParent: T
    var bestFitness: F


    /**
     * Return true when target is reached
     * @return Boolean
     */
    fun isSuccess(): Boolean

    /**
     * Start algorithm
     * @sample
     * CreateParent
     * Mutate
     * GetFitness
     * Compare
     * IsSuccess
     */
    fun start()


    /**
     * Return a new parent object
     * @param Parameter of generation
     * @return Type T
     */
    fun createParent(param: Any): T

    /**
     * Get object fitness
     * @return F value
     */
    fun getFitness(obj: T): F

    /**
     * Return a mutation of a parent object
     * @param Parent element
     * @return type T mutated
     */
    fun mutate (parent: T): T
}

interface ICrossOver<T, F: Comparable<F>> : IMutable<T,F>
{
    /**
     * Return a crossover of two objects of same type
     * @param Parent element
     * @return type T crossed over
     */
    fun crossOver(father: T, mother: T): T
}

interface IMultipleCrossOver<T1, T2, F: Comparable<F>> : ICrossOver<T1,F>
{
    /**
     * Return a crossover of two objects of same type
     * @param Parent element
     * @return type T crossed over
     */
    fun multipleCrossOver(father: T1, mother: T2): T1
}