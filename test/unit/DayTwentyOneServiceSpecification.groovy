import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * Created by tomwallace on 12/7/15.
 */
@TestFor(DayTwentyOneService)
class DayTwentyOneServiceSpecification extends Specification {


    def "test entity canBeatOther"() {
        when:
        def me = new Entity(hitPoints: 8, damage: 5, armor: 5)
        def boss = new Entity(hitPoints: 12, damage: 7, armor: 2)

        then:
        me.canBeatEntity(boss, true)

        when:
        boss.armor = 3

        then:
        !me.canBeatEntity(boss, true)

    }


    def "test with actual part a"() {
        when:
        def me = new Entity(hitPoints: 100, damage: 0, armor: 0)
        def result = service.moneySpent(me, service.BOSS)

        then:
        result.win
        result.win == 121
    }


    def "test with actual part b"() {
        when:
        def me = new Entity(hitPoints: 100, damage: 0, armor: 0)
        def result = service.moneySpent(me, service.BOSS)

        then:
        result.lose
        result.lose == 201
    }


}
