package cinema

fun main() {
    // write your code here
    println("Enter the number of rows:")
    val rows = readLine()!!.toInt()
    println("Enter the number of seats in each row:")
    val seats = readLine()!!.toInt()

    val cinema = Array(rows + 1){CharArray(seats + 1){'S'}}
    cinema[0][0] = ' '

    var numberTicketSold = 0
    var currentIncome = 0

    for (i in 1..seats) cinema[0][i] = i.toString().first()
    for (i in 1..rows) cinema[i][0] = i.toString().first()

    while (true) {

        println("\n1. Show the seats\n" +
                "2. Buy a ticket\n" +
                "3. Statistics\n" +
                "0. Exit")

        when (readLine()!!) {

            "0" -> break
            "1" -> printCinema(cinema, rows, seats)
            "2" -> {

                while (true) {

                    println("Enter a row number:")
                    val row = readLine()!!.toInt()

                    println("Enter a seat number in that row:")
                    val seat = readLine()!!.toInt()

                    if (row > rows || seat > seats) {
                        println("Wrong input!")
                        continue
                    } else if (cinema[row][seat] == 'B') {
                        println("That ticket has already been purchased!")
                        continue
                    } else {
                        cinema[row][seat] = 'B'
                        numberTicketSold++
                        currentIncome += getPrice(rows, seats, row)
                        println("Ticket price: \$${getPrice(rows, seats, row)}")
                        break
                    }
                }
            }
            "3" -> {

                println("Number of purchased tickets: $numberTicketSold\n" +
                        "Percentage: ${getPercentage(rows * seats, numberTicketSold)}%\n" +
                        "Current income: \$$currentIncome\n" +
                        "Total income: \$${calcProfit(rows, seats)}")
            }
        }
    }
}

fun getPrice(rows: Int, seats: Int, row: Int): Int {

    val totalOfSeats = rows * seats

    return if (totalOfSeats > 60) {

        if (row > rows / 2) 8 else 10
    } else {
        10
    }

}

fun calcProfit(rows: Int, seats: Int): Int {

    val totalOfSeats = rows * seats
    val midOfSeats = (rows / 2) * seats
    return when {

        totalOfSeats <= 60 -> totalOfSeats * 10
        else -> midOfSeats * 10 + ((totalOfSeats - midOfSeats) * 8)
    }
}

fun printCinema(cinema: Array<CharArray>, rows: Int, seats: Int) {

    println("\nCinema:")
    for (row in 0..rows) {
        for (seat in 0..seats) {

            print("${cinema[row][seat]} ")
        }
        println()
    }
}

fun getPercentage(totalSeats: Int, occupiedSeats: Int): String {

    return String.format("%.2f", occupiedSeats.toFloat() / totalSeats.toFloat() * 100.0)

}