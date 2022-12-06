import org.junit.jupiter.api.Test
import kotlin.test.assertSame

class Day6Test {

    @Test
    fun testPart1() {
        // test start-of-packet marker
        listOf(
            "mjqjpqmgbljsphdztnvjfqwrcgsmlb" to 7,
            "bvwbjplbgvbhsrlpgdmjqwftvncz" to 5,
            "nppdvjthqldpwncqszvftbrmjlhg" to 6,
            "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg" to 10,
            "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw" to 11,
        ).forEach { (testInput, expectedResult) ->
            assertSame(expectedResult, Day6.part1(testInput))
        }
    }
}