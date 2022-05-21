package calculator

import calculator.exception.NegativeNumericException
import calculator.exception.NotNumericException

class StringAddCalculator {

    fun calculate(text: String): Int {
        val tokens = CUSTOM_DELIMITERS.find(text)
            ?.let {
                val customDelimiter = it.groupValues[1]
                it.groupValues[2].split(customDelimiter)
            } ?: text.split(*DEFAULT_DELIMITERS)

        return addCalculate(tokens)
    }

    fun addCalculate(tokens: List<String>): Int {
        return tokens
            .map { getNumeric(it) }
            .sumOf { it }
    }

    fun getNumeric(token: String?): Int {
        return if (token.isNullOrBlank()) {
            0
        } else {
            validateNumeric(token)
            val tokenToNumeric = token.toInt()
            validateNegativeNumeric(tokenToNumeric)
            tokenToNumeric
        }
    }

    fun validateNumeric(toCheck: String) {
        if (toCheck.toIntOrNull() == null) {
            throw NotNumericException("숫자가 아닌 문자가 입력되었습니다.")
        }
    }

    fun validateNegativeNumeric(toCheck: Int) {
        if (toCheck < 0) {
            throw NegativeNumericException("음수가 입력되었습니다.")
        }
    }

    companion object {
        private val DEFAULT_DELIMITERS = arrayOf(":", ",")
        private val CUSTOM_DELIMITERS = Regex("//(.)₩n(.*)")
    }
}
