
/**
 * Created by tomwallace on 12/7/15.
 */
class DayTwentyTwoService {

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

    Integer leastManaSpentToWin(Integer bossHitPoints, Boolean hard) {
        PriorityQueue<Wizard> wizards = new PriorityQueue<Wizard>(1, new Comparator<Wizard>() {
            public int compare(Wizard a, Wizard b) {
                a.manaSpentDuringFight <=> b.manaSpentDuringFight
            }
        })
        Integer minMana = Integer.MAX_VALUE
        wizards.add(new Wizard(50, 500, bossHitPoints))
        while (wizards.size() > 0) {
            Wizard current = wizards.poll()

            if (hard) {
                --current.hitPoints
            }

            // Apply effects before Wizard turn
            current.applyEffects()

            for (int spell = 0; spell < Wizard.spells.size(); spell++) {
                if (current.canCast(spell)) {
                    Wizard next = current.clone()

                    // Cast the spell
                    next.castSpell(spell)

                    // apply effects at start of boss's turn
                    next.applyEffects()

                    if (next.bossHp <= 0) {
                        // We win
                        minMana = next.manaSpentDuringFight < minMana ? next.manaSpentDuringFight : minMana
                        // Trim any wizards off list that have spent more than that amount of mana already
                        wizards.removeAll { w -> w.manaSpentDuringFight > minMana }
                    } else {
                        // Wizard takes damage
                        next.hitPoints -= ((10 - next?.armor) > 1 ? (10 - next?.armor) : 1)
                        // If still alive, keep them on the stack
                        if (next.hitPoints > 0 && next.mana > 0 && next.manaSpentDuringFight < minMana) {
                            wizards.add(next)
                        }
                    }
                }
            }

        }

        return minMana
    }
}

class Wizard implements Cloneable {

    final static List<Map> spells = [
            [name: 'magicMissile', cost: 53, damage: 4, armor: 0, hitPoints: 0, mana: 0, duration: 0],
            [name: 'drain', cost: 73, damage: 2, armor: 0, hitPoints: 2, mana: 0, duration: 0],
            [name: 'shield', cost: 113, damage: 0, armor: 7, hitPoints: 0, mana: 0, duration: 6],
            [name: 'poison', cost: 173, damage: 3, armor: 0, hitPoints: 0, mana: 0, duration: 6],
            [name: 'recharge', cost: 229, damage: 0, armor: 0, hitPoints: 0, mana: 101, duration: 5]
    ]

    Integer hitPoints
    Integer armor
    Integer mana
    Integer manaSpentDuringFight = 0

    Integer bossHp   // Hit Points and Damage

    Map effects = [:]

    Wizard(Integer hp, Integer m, Integer b) {
        hitPoints = hp
        mana = m
        bossHp = b
    }

    boolean canCast(Integer i) {
        return mana >= spells[i].cost && (!effects.keySet().contains(spells[i].name))
    }

    void castSpell(Integer i) {
        mana -= spells[i].cost
        manaSpentDuringFight += spells[i].cost

        if (spells[i].duration != 0) {
            effects["${spells[i].name}"] = 0
        } else {
            bossHp -= spells[i].damage
            hitPoints += spells[i].hitPoints
        }

    }
    void applyEffects() {
        List<String> keysToRemove = []
        armor = 0
        effects.each { effect ->
            Map matchingSpell = spells.find { it.name == effect.key}
            bossHp -= matchingSpell.damage
            if (matchingSpell.name == 'shield') {
                armor = 7
            }
            mana += matchingSpell.mana

            // Increase duration and remove if exceeds
            ++effect.value
            if (effect.value >= matchingSpell.duration) {
                keysToRemove << effect.key
            }
        }
        keysToRemove.each {
            effects.remove(it)
        }
    }

    Wizard clone() {
        Wizard wizard = new Wizard(hitPoints, mana, bossHp)
        wizard.armor = armor
        wizard.manaSpentDuringFight = manaSpentDuringFight
        wizard.effects = effects.clone()
        return wizard
    }
}
