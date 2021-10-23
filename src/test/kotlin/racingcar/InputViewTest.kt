package racingcar

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource
import racingcar.exception.RacingCarException.READ_LINE_IS_NOT_A_NUMBER_EXCEPTION
import racingcar.exception.RacingCarException.READ_LINE_IS_NOT_POSITIVE_NUM_EXCEPTION
import racingcar.view.InputView
import java.lang.IllegalArgumentException

class InputViewTest {

    private lateinit var inputView: InputView

    @ParameterizedTest(name = "Test {index}: value == {0} result == {1}")
    @CsvSource(value = ["1, 1", "2, 2", "3, 3", "4, 4", "5, 5", "6, 6", "10, 10", "100, 100"])
    fun `getNumberOfCars() 올바른 입력 Test`(value: String, expected: Int) {
        inputView = InputView { value }
        assertThat(inputView.getNumberOfCars()).isEqualTo(expected)
    }

    @ParameterizedTest(name = "Test {index}: value == {0} result == {1}")
    @CsvSource(value = ["1, 1", "2, 2", "3, 3", "4, 4", "5, 5", "6, 6", "10, 10", "100, 100"])
    fun `getNumberOfAttempts() 올바른 입력 Test`(value: String, expected: Int) {
        inputView = InputView { value }
        assertThat(inputView.getNumberOfAttempts()).isEqualTo(expected)
    }

    @ParameterizedTest(name = "Test {index}: value == {0} result == {1}")
    @ValueSource(strings = ["가나다", "라마바", "!@#$", "   "])
    fun `getNumberOfCars() Integer 아닌 값 들어올 경우 Exception 테스트`(value: String) {
        inputView = InputView { value }
        Assertions.assertThatExceptionOfType(IllegalArgumentException::class.java).isThrownBy {
            inputView.getNumberOfCars()
        }.withMessage(READ_LINE_IS_NOT_A_NUMBER_EXCEPTION)
    }

    @ParameterizedTest(name = "Test {index}: value == {0} result == {1}")
    @ValueSource(strings = ["가나다", "라마바", "!@#$", "   "])
    fun `getNumberOfAttempts() Integer 아닌 값 들어올 경우 Exception 테스트`(value: String) {
        inputView = InputView { value }
        Assertions.assertThatExceptionOfType(IllegalArgumentException::class.java).isThrownBy {
            inputView.getNumberOfAttempts()
        }.withMessage(READ_LINE_IS_NOT_A_NUMBER_EXCEPTION)
    }

    @ParameterizedTest(name = "Test {index}: value == {0} result == {1}")
    @ValueSource(strings = ["-1", "-2", "-3", "-4"])
    fun `getNumberOfCars() 음의 정수 값 들어올 경우 Exception 테스트`(value: String) {
        inputView = InputView { value }
        Assertions.assertThatExceptionOfType(IllegalArgumentException::class.java).isThrownBy {
            inputView.getNumberOfCars()
        }.withMessage(READ_LINE_IS_NOT_POSITIVE_NUM_EXCEPTION)
    }

    @ParameterizedTest(name = "Test {index}: value == {0} result == {1}")
    @ValueSource(strings = ["-1", "-2", "-3", "-4"])
    fun `getNumberOfAttempts() 음의 정수 값 들어올 경우 Exception 테스트`(value: String) {
        inputView = InputView { value }
        Assertions.assertThatExceptionOfType(IllegalArgumentException::class.java).isThrownBy {
            inputView.getNumberOfAttempts()
        }.withMessage(READ_LINE_IS_NOT_POSITIVE_NUM_EXCEPTION)
    }
}