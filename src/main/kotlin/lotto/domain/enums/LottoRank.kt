package lotto.domain.enums

enum class LottoRank(val matchingCount: Int, val rewardPrice: Long) {
    FIRST(6, 2_000_000_000),
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000),
    NONE(-1, 0);

    fun sumOfPrice(numberOfLotto: Int): Long {
        return rewardPrice * numberOfLotto
    }

    companion object {
        fun of(matchingCount: Int): LottoRank {
            return values().find { it.matchingCount == matchingCount } ?: NONE
        }
    }
}