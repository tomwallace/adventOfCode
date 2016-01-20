import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * Created by tomwallace on 1/20/16.
 */
@TestFor(DayTwentyFourService)
class DayTwentyFourServiceSpecification extends Specification {


    def "test packBags with set scenario"() {
        when:
        def result = service.packBags(100, [3,4,6,2,7,44,12,90,15])
//        def result = service.packBags(520, [1, 2, 3, 7, 11, 13, 17, 19, 23, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113])

        then:
        result
        result.size() == 2
        // result.size() == 417649
    }

    def "test getQuantumEntanglement"() {
        when:
        def result = service.getQuantumEntanglement(input)

        then:
        result == expected

        where:
        expected  | input
        99 | [11, 9]
        216 | [9, 8, 3]
        420 | [7, 5, 4, 3, 1]
    }

    // Commented out because it takes about 10 min to run due to all the permutations
    /*
    def "test with actual part a"() {
        when:
        List<Integer> packageWeights = service.getPackageWeights(service.PUZZLE_INPUT)
        def result = service.getSmallestQuantumEntanglements(packageWeights, 3)

        then:

        result
        result == 11846773891
    }
    */

    def "test with actual part b"() {
        when:
        List<Integer> packageWeights = service.getPackageWeights(service.PUZZLE_INPUT)
        def result = service.getSmallestQuantumEntanglements(packageWeights, 4)

        then:

        result
        result == 80393059
    }

}
