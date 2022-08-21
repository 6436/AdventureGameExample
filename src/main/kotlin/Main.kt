fun main() {
    // introduction

    printDialogue("Welcome to the adventure game!")

    val name = readName("Please enter a name.")

    val health =
        if (readChoice("Would you like to play on easy or hard mode?", "easy", "hard")) {
            15
        } else {
            10
        }

    val player = Player(name, health)

    printDialogue("Welcome ${player.name}.")
    player.printHealth()

    printDialogue("You enter a dark forest and walk along a single path.")

    // choice 1

    var wolf: String? = null

    if (readChoice(
            "Through the trees, you spy a group of trolls gathered in a clearing. Do you introduce yourself?",
            "yes",
            "no"
        )
    ) {
        printDialogue("You step into the clearing. 'Hey peeps! I'm ${player.name}!'")
        printDialogue("The trolls take turns beating you. Luckily, they get bored after a while.")

        player.takeDamage(Enemy("trolls", 7))
    } else {
        printDialogue("A wolf steps out from behind a tree.")

        if (readChoice(
                "Do you give it your last piece of beef jerky?",
                "yes",
                "no"
            )
        ) {
            printDialogue("You drop the beef jerky and the wolf happily munches it up.")

            wolf =
                readName("Now, it insists on following you everywhere. What do you name your new friend?")

            printDialogue("You and $wolf play freeze tag for a few hours, then stay still for a few hours.")
        } else {
            printDialogue("You pop the beef jerky in your mouth.")
            player.heal(2)

            printDialogue("The wolf charges you.")
            player.takeDamage(Enemy("wolf", 4))
        }
    }

    // choice 2

    var rock = Enemy("rock", 1)

    while (readChoice(
            "Eventually, you dust yourself off and continue along your path. Soon, you encounter a split. Which path do you choose?",
            "left",
            "right"
        )
    ) {
        printDialogue("You walk down the left path. After about five minutes, you trip on a rock.")
        player.takeDamage(rock)

        rock = rock.anger()
    }

    // choice 3

    if (readChoice(
            "You walk down the right path. After a while, you meet a lumberjack guarding the road. What do you do?",
            "run away",
            "approach"
        )
    ) {
        printDialogue("You turn away from the lumberjack.")
    } else {
        printDialogue("You cautiously approach the lumberjack.")
    }

    printDialogue("The lumberjack runs at you and tackles you.")

    var lumberjack = Enemy("lumberjack", 3)

    if (wolf != null) {
        printDialogue("$wolf pulls the lumberjack off you, angering the lumberjack.")
        printDialogue("$wolf perishes in solo combat against the lumberjack.")

        lumberjack = lumberjack.anger()
    }

    player.takeDamage(lumberjack)

    // conclusion

    printDialogue("After persuading the lumberjack to let you go, you soon arrive at your grandmother's cottage. Congratulations, you arrived!")
}
