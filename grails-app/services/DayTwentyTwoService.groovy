/**
 * Created by tomwallace on 12/7/15.
 */
class DayTwentyTwoService {

    final static Entity BOSS = new Entity(hitPoints: 71, damage: 10, armor: 0)

    final static List<Map> SPELLS = [
            [name: 'magicMissile', cost: 53, damage: 4, armor: 0, hitPoints: 0, mana: 0],
            [name: 'drain', cost: 73, damage: 2, armor: 0, hitPoints: 2, mana: 0],
            [name: 'shield', cost: 113, damage: 0, armor: 7, hitPoints: 0, mana: 0, duration: 6],
            [name: 'poison', cost: 173, damage: 3, armor: 0, hitPoints: 0, mana: 0, duration: 6],
            [name: 'recharge', cost: 229, damage: 0, armor: 0, hitPoints: 0, mana: 101, duration: 5]
    ]

    List<Integer> MANA_SPENT = []

    // http://adventofcode.com/day/22

    /*
Little Henry Case decides that defeating bosses with swords and stuff is boring. Now he's playing the game with a wizard. Of course, he gets stuck on another boss and needs your help again.

In this version, combat still proceeds with the player and the boss taking alternating turns. The player still goes first. Now, however, you don't get any equipment; instead, you must choose one of your spells to cast. The first character at or below 0 hit points loses.

Since you're a wizard, you don't get to wear armor, and you can't attack normally. However, since you do magic damage, your opponent's armor is ignored, and so the boss effectively has zero armor as well. As before, if armor (from a spell, in this case) would reduce damage below 1, it becomes 1 instead - that is, the boss' attacks always deal at least 1 damage.

On each of your turns, you must select one of your spells to cast. If you cannot afford to cast any spell, you lose. Spells cost mana; you start with 500 mana, but have no maximum limit. You must have enough mana to cast a spell, and its cost is immediately deducted when you cast it. Your spells are Magic Missile, Drain, Shield, Poison, and Recharge.

Magic Missile costs 53 mana. It instantly does 4 damage.
Drain costs 73 mana. It instantly does 2 damage and heals you for 2 hit points.
Shield costs 113 mana. It starts an effect that lasts for 6 turns. While it is active, your armor is increased by 7.
Poison costs 173 mana. It starts an effect that lasts for 6 turns. At the start of each turn while it is active, it deals the boss 3 damage.
Recharge costs 229 mana. It starts an effect that lasts for 5 turns. At the start of each turn while it is active, it gives you 101 new mana.
Effects all work the same way. Effects apply at the start of both the player's turns and the boss' turns. Effects are created with a timer (the number of turns they last); at the start of each turn, after they apply any effect they have, their timer is decreased by one. If this decreases the timer to zero, the effect ends. You cannot cast a spell that would start an effect which is already active. However, effects can be started on the same turn they end.

For example, suppose the player has 10 hit points and 250 mana, and that the boss has 13 hit points and 8 damage:

-- Player turn --
- Player has 10 hit points, 0 armor, 250 mana
- Boss has 13 hit points
Player casts Poison.

-- Boss turn --
- Player has 10 hit points, 0 armor, 77 mana
- Boss has 13 hit points
Poison deals 3 damage; its timer is now 5.
Boss attacks for 8 damage.

-- Player turn --
- Player has 2 hit points, 0 armor, 77 mana
- Boss has 10 hit points
Poison deals 3 damage; its timer is now 4.
Player casts Magic Missile, dealing 4 damage.

-- Boss turn --
- Player has 2 hit points, 0 armor, 24 mana
- Boss has 3 hit points
Poison deals 3 damage. This kills the boss, and the player wins.
Now, suppose the same initial conditions, except that the boss has 14 hit points instead:

-- Player turn --
- Player has 10 hit points, 0 armor, 250 mana
- Boss has 14 hit points
Player casts Recharge.

-- Boss turn --
- Player has 10 hit points, 0 armor, 21 mana
- Boss has 14 hit points
Recharge provides 101 mana; its timer is now 4.
Boss attacks for 8 damage!

-- Player turn --
- Player has 2 hit points, 0 armor, 122 mana
- Boss has 14 hit points
Recharge provides 101 mana; its timer is now 3.
Player casts Shield, increasing armor by 7.

-- Boss turn --
- Player has 2 hit points, 7 armor, 110 mana
- Boss has 14 hit points
Shield's timer is now 5.
Recharge provides 101 mana; its timer is now 2.
Boss attacks for 8 - 7 = 1 damage!

-- Player turn --
- Player has 1 hit point, 7 armor, 211 mana
- Boss has 14 hit points
Shield's timer is now 4.
Recharge provides 101 mana; its timer is now 1.
Player casts Drain, dealing 2 damage, and healing 2 hit points.

-- Boss turn --
- Player has 3 hit points, 7 armor, 239 mana
- Boss has 12 hit points
Shield's timer is now 3.
Recharge provides 101 mana; its timer is now 0.
Recharge wears off.
Boss attacks for 8 - 7 = 1 damage!

-- Player turn --
- Player has 2 hit points, 7 armor, 340 mana
- Boss has 12 hit points
Shield's timer is now 2.
Player casts Poison.

-- Boss turn --
- Player has 2 hit points, 7 armor, 167 mana
- Boss has 12 hit points
Shield's timer is now 1.
Poison deals 3 damage; its timer is now 5.
Boss attacks for 8 - 7 = 1 damage!

-- Player turn --
- Player has 1 hit point, 7 armor, 167 mana
- Boss has 9 hit points
Shield's timer is now 0.
Shield wears off, decreasing armor by 7.
Poison deals 3 damage; its timer is now 4.
Player casts Magic Missile, dealing 4 damage.

-- Boss turn --
- Player has 1 hit point, 0 armor, 114 mana
- Boss has 2 hit points
Poison deals 3 damage. This kills the boss, and the player wins.
You start with 50 hit points and 500 mana points. The boss's actual stats are in your puzzle input. What is the least amount of mana you can spend and still win the fight? (Do not include mana recharge effects as "spending" negative mana.)

     */
    Integer leastManaSpentToWin(Entity me, Entity boss) {
        List<Integer> costsToSuccessfullyKillBoss = []

        SPELLS.each { Map spell ->
            Map effects = [:]
            fightRound(me, boss, effects, spell)
        }

        return MANA_SPENT.sort()[0]
    }

    protected void fightRound(Entity me, Entity boss, Map effects, Map spellToBeCast) {
        if (me.hitPoints <= 0 || me.mana <= 0) {
            println("I was killed and the boss won.")
            // reset values
            // TODO: Figure out a better way to store this information
            boss.hitPoints = 71
            me.hitPoints = 50
            me.mana = 500
            me.manaSpentDuringFight = 0
            return
        }

        if (boss.hitPoints <= 0) {
            println("I WON. Mana remaining is: ${me.mana}")
            // reset values
            boss.hitPoints = 71
            me.hitPoints = 50
            me.mana = 500
            MANA_SPENT << me.manaSpentDuringFight
            return
        }

        // Fight my round
        applyEffects(me, boss, effects)

        // Cast the spell
       // Cannot cast a spell with an effect in play
        if (effects.containsKey(spellToBeCast.name)) {
            return
        }
        // Add  spells with effects to effects
        if (spellToBeCast.duration) {
            effects["${spellToBeCast.name}"] = 0
        } else {
            boss.hitPoints -= spellToBeCast.damage
            me.hitPoints += spellToBeCast.hitPoints
            me.mana -= spellToBeCast.cost
            me.manaSpentDuringFight += spellToBeCast.cost
        }

        // Boss turn
        applyEffects(me, boss, effects)
        me.hitPoints -= ((boss.damage - me.armor) > 1 ? (boss.damage - me.armor) : 1)

        // Pick next spell to cast
        List<Map> updatedSpells = SPELLS.clone()
        updatedSpells.remove(updatedSpells.findIndexOf { it.cost == spellToBeCast.cost})
        updatedSpells.each { spell ->
            fightRound(me, boss, effects, spell)
        }
    }

    protected void applyEffects(Entity me, Entity boss, Map effects) {
        // Calculate effects
        def i = 0
        effects.each { effect ->
            Map matchingSpell = SPELLS.find { it.name == effect.key}
            boss.hitPoints -= matchingSpell.damage
            me.armor = matchingSpell.armor
            me.mana += matchingSpell.mana

            // Increase duration and remove if exceeds
            ++effect.value
            if (effect.value >= matchingSpell.duration) {
                effects.remove(i)
            }
            ++i
        }
    }

}

