package com.example.data

object MathQuestions {
    val list: List<Question> = generate()

    private fun generate(): List<Question> {
        val qList = mutableListOf<Question>()
        
        // Helper to generate unique options
        fun makeMathOptions(correct: Int): Pair<List<String>, Int> {
            val set = mutableSetOf(correct)
            val offsets = listOf(1, 2, -1, -2, 3, -3, 5, -5, 10, -10)
            for (offset in offsets) {
                if (set.size == 4) break
                val v = correct + offset
                if (v >= 0) set.add(v)
            }
            while (set.size < 4) {
                set.add((set.maxOrNull() ?: 0) + 1)
            }
            val shuffledList = set.toList().sorted()
            // We want to return (option strings, correct index)
            val index = shuffledList.indexOf(correct)
            return Pair(shuffledList.map { it.toString() }, index)
        }

        for (g in 1..5) {
            for (s in 1..3) {
                for (q in 0..9) {
                    val id = "math_g${g}_s${s}_$q"
                    val difficulty = when (s) {
                        1 -> "easy"
                        2 -> "medium"
                        else -> "hard"
                    }

                    var questionStrEn = ""
                    var questionStrSi = ""
                    var correctValue = 0
                    var hintStrEn = ""
                    var hintStrSi = ""

                    when (g) {
                        1 -> {
                            // Grade 1: Numbers up to 10
                            when (s) {
                                1 -> {
                                    // Easy Addition
                                    val a = q + 1
                                    val b = 2
                                    questionStrEn = "What is $a plus $b?"
                                    questionStrSi = "$a එකතු කිරීම $b කීයද?"
                                    correctValue = a + b
                                    hintStrEn = "Count $b steps forward from $a."
                                    hintStrSi = "$a සිට ඉදිරියට පියවර $b ක් ගණන් කරන්න."
                                }
                                2 -> {
                                    // Easy Subtraction
                                    val a = q + 5
                                    val b = 3
                                    questionStrEn = "What is $a minus $b?"
                                    questionStrSi = "$a අඩු කිරීම $b කීයද?"
                                    correctValue = a - b
                                    hintStrEn = "Take away $b from $a."
                                    hintStrSi = "$a න් $b ක් ඉවත් කරන්න."
                                }
                                3 -> {
                                    // Basic geometry and counting
                                    val shapes = listOf(
                                        Pair("triangle", "ත්‍රිකෝණයකට"),
                                        Pair("square", "සමචතුරස්‍රයකට"),
                                        Pair("rectangle", "සෘජුකෝණාස්‍රයකට")
                                    )
                                    val shape = shapes[q % shapes.size]
                                    if (q % 2 == 0) {
                                        questionStrEn = "How many corners does a ${shape.first} have?"
                                        questionStrSi = "${shape.second} කොන් කීයක් තිබේද?"
                                        correctValue = if (shape.first == "triangle") 3 else 4
                                        hintStrEn = "Count the sharp points on the shape."
                                        hintStrSi = "හැඩයේ ඇති උල් කොන් ගණන් කරන්න."
                                    } else {
                                        questionStrEn = "How many sides does a ${shape.first} have?"
                                        questionStrSi = "${shape.second} පැති කීයක් තිබේද?"
                                        correctValue = if (shape.first == "triangle") 3 else 4
                                        hintStrEn = "Count the straight border lines."
                                        hintStrSi = "හැඩයේ ඇති සෘජු මායිම් රේඛා ගණන් කරන්න."
                                    }
                                }
                            }
                        }
                        2 -> {
                            // Grade 2: Numbers up to 50
                            when (s) {
                                1 -> {
                                    // Addition up to 30
                                    val a = q * 2 + 10
                                    val b = 5
                                    questionStrEn = "What is the sum of $a and $b?"
                                    questionStrSi = "$a සහ $b හි එකතුව කොපමණද?"
                                    correctValue = a + b
                                    hintStrEn = "Combine $a and $b together."
                                    hintStrSi = "$a ට $b ක් එකතු කරන්න."
                                }
                                2 -> {
                                    // Subtraction within 30
                                    val a = q * 2 + 20
                                    val b = 7
                                    questionStrEn = "What is $a minus $b?"
                                    questionStrSi = "$a අඩු කිරීම $b කීයද?"
                                    correctValue = a - b
                                    hintStrEn = "Subtract 7 from the starting number."
                                    hintStrSi = "ආරම්භක සංඛ්‍යාවෙන් 7 ක් අඩු කරන්න."
                                }
                                3 -> {
                                    // Simple patterns
                                    val start = q * 3
                                    questionStrEn = "What number comes next in the pattern: $start, ${start + 2}, ${start + 4}, ____ ?"
                                    questionStrSi = "පහත රටාවේ ඊළඟට එන සංඛ්‍යාව කුමක්ද: $start, ${start + 2}, ${start + 4}, ____ ?"
                                    correctValue = start + 6
                                    hintStrEn = "The numbers are increasing by 2 each time."
                                    hintStrSi = "සංඛ්‍යාවන් එක් වරකට 2 බැගින් වැඩි වේ."
                                }
                            }
                        }
                        3 -> {
                            // Grade 3: Numbers up to 100
                            when (s) {
                                1 -> {
                                    // Addition with carry
                                    val a = q * 5 + 35
                                    val b = 18
                                    questionStrEn = "What is the sum of $a and $b?"
                                    questionStrSi = "$a සහ $b හි එකතුව කොපමණද?"
                                    correctValue = a + b
                                    hintStrEn = "Add the ones column first, then the tens."
                                    hintStrSi = "පළමුව එකස්ථානය එකතු කර පසුව දහස්ථානය එකතු කරන්න."
                                }
                                2 -> {
                                    // Easy multiplication
                                    val factors = listOf(2, 3, 5, 10)
                                    val factor = factors[q % factors.size]
                                    val b = q + 2
                                    questionStrEn = "What is $factor multiplied by $b?"
                                    questionStrSi = "$factor ගුණ කිරීම $b කීයද?"
                                    correctValue = factor * b
                                    hintStrEn = "Add $factor to itself $b times."
                                    hintStrSi = "$factor බැගින් $b වතාවක් එකතු කරන්න."
                                }
                                3 -> {
                                    // Simple measurements (cm/m)
                                    val cmValue = (q + 1) * 100
                                    questionStrEn = "How many meters are equal to $cmValue centimeters?"
                                    questionStrSi = "සෙන්ටිමීටර $cmValue ක් මීටර කීයකට සමානද?"
                                    correctValue = cmValue / 100
                                    hintStrEn = "Remember that 100 centimeters is equal to 1 meter."
                                    hintStrSi = "සෙන්ටිමීටර 100 ක් යනු එක් මීටරයක් බව මතක තබා ගන්න."
                                }
                            }
                        }
                        4 -> {
                            // Grade 4: Numbers up to 1000
                            when (s) {
                                1 -> {
                                    // Multiplication double digit by single
                                    val a = q * 4 + 12
                                    val b = 6
                                    questionStrEn = "What is $a times $b?"
                                    questionStrSi = "$a වතාවක් $b කීයද?"
                                    correctValue = a * b
                                    hintStrEn = "Calculate $a multiplied by $b."
                                    hintStrSi = "$a සංඛ්‍යාව $b න් ගුණ කරන්න."
                                }
                                2 -> {
                                    // Perimeter of shapes
                                    val side = q + 5
                                    questionStrEn = "What is the perimeter of a square with side length of $side cm?"
                                    questionStrSi = "පැත්තක දිග සෙන්ටිමීටර $side ක් වූ සමචතුරස්‍රයක පරිමිතිය කොපමණද?"
                                    correctValue = side * 4
                                    hintStrEn = "A square has 4 equal sides. Multiply the length by 4."
                                    hintStrSi = "සමචතුරස්‍රයකට එක සමාන පැති 4 ක් ඇත. පැත්තක දිග 4 න් ගුණ කරන්න."
                                }
                                3 -> {
                                    // Time conversion
                                    val hours = q + 2
                                    questionStrEn = "How many minutes are there in $hours hours?"
                                    questionStrSi = "පැය $hours ක් තුළ මිනිත්තු කීයක් තිබේද?"
                                    correctValue = hours * 60
                                    hintStrEn = "1 hour is equal to 60 minutes."
                                    hintStrSi = "පැය එකක් මිනිත්තු 60 කට සමාන වේ."
                                }
                            }
                        }
                        5 -> {
                            // Grade 5: Decimals, fractions, averages
                            when (s) {
                                1 -> {
                                    // Basic average of three numbers
                                    val base = q * 3 + 10
                                    val n1 = base - 2
                                    val n2 = base
                                    val n3 = base + 2
                                    questionStrEn = "What is the average of $n1, $n2, and $n3?"
                                    questionStrSi = "$n1, $n2 සහ $n3 යන සංඛ්‍යාවල සාමාන්‍යය කීයද?"
                                    correctValue = base
                                    hintStrEn = "Sum the three values and divide the result by 3."
                                    hintStrSi = "සංඛ්‍යාවන් තුනේ එකතුව ලබාගෙන එය 3 න් බෙදන්න."
                                }
                                2 -> {
                                    // Percentages
                                    val amount = (q + 1) * 200
                                    questionStrEn = "What is 50 percent of $amount?"
                                    questionStrSi = "$amount න් සියයට 50 ක් යනු කොපමණද?"
                                    correctValue = amount / 2
                                    hintStrEn = "50 percent represents half of the total amount."
                                    hintStrSi = "සියයට 50 ක් යනු මුළු වටිනාකමෙන් අඩකි."
                                }
                                3 -> {
                                    // Simple algebraic equations
                                    val constant = q * 5 + 10
                                    questionStrEn = "If x + $constant = ${constant * 3}, what is the value of x?"
                                    questionStrSi = "x + $constant = ${constant * 3} නම්, x හි අගය කුමක්ද?"
                                    correctValue = constant * 2
                                    hintStrEn = "Subtract $constant from ${constant * 3} to find x."
                                    hintStrSi = "x හි අගය සෙවීමට ${constant * 3} න් $constant ක් අඩු කරන්න."
                                }
                            }
                        }
                    }

                    val optResult = makeMathOptions(correctValue)
                    qList.add(Question(
                        id = id,
                        question = questionStrEn,
                        questionSinhala = questionStrSi,
                        options = optResult.first,
                        optionsSinhala = optResult.first, // Numeric options are the same
                        correctAnswerIndex = optResult.second,
                        grade = g,
                        subject = "Math",
                        stage = s,
                        difficulty = difficulty,
                        hint = hintStrEn,
                        hintSinhala = hintStrSi
                    ))
                }
            }
        }
        return qList
    }
}
