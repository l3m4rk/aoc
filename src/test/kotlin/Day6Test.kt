import org.junit.jupiter.api.Test
import year2022.Day6
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

    @Test
    fun testPart2() {
        // test start-of-message marker
        listOf(
            "mjqjpqmgbljsphdztnvjfqwrcgsmlb" to 19,
            "bvwbjplbgvbhsrlpgdmjqwftvncz" to 23,
            "nppdvjthqldpwncqszvftbrmjlhg" to 23,
            "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg" to 29,
            "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw" to 26,
        ).forEach { (testInput, expectedResult) ->
            assertSame(expectedResult, Day6.part2(testInput))
        }
    }
}