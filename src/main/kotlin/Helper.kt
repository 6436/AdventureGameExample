import kotlin.system.exitProcess

data class Player(val name: String, private var health: Int) {
    fun printHealth() {
        println("You have $health health.")
    }

    fun takeDamage(enemy: Enemy) {
        health -= enemy.damage
        printDialogue("You took ${enemy.damage} damage from the ${enemy.name}.")
        if (health <= 0) {
            println("You died.")
            exitProcess(0)
        } else {
            printHealth()
        }
    }

    fun heal(healing: Int) {
        health += healing
        printDialogue("You gained $healing health.")
        printHealth()
    }
}

data class Enemy(val name: String, val damage: Int) {
    fun anger() = Enemy("angry $name", damage * 2)
}

fun printDialogue(message: String) {
    println("\n$message")
}

fun readName(message: String): String {
    printDialogue(message)
    val name = readln()
    if (name != "") {
        return name.replaceFirstChar { it.uppercaseChar() }
    }
    return readName(message)
}

fun readChoice(message: String, firstOption: String, secondOption: String): Boolean {
    printDialogue(
        "$message Choose [${firstOption[0].uppercaseChar()}]${firstOption.substring(1)} or [${secondOption[0].uppercaseChar()}]${
            secondOption.substring(
                1
            )
        }."
    )
    val choice = readln()
    if (choice != "") {
        if (firstOption.startsWith(choice, true)) {
            return true
        }
        if (secondOption.startsWith(choice, true)) {
            return false
        }
    }
    return readChoice(message, firstOption, secondOption)
}
