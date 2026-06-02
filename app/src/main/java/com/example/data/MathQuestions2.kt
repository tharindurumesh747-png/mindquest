package com.example.data

object MathQuestions2 {
    val list: List<Question> = generate()

    private fun generate(): List<Question> {
        val qList = mutableListOf<Question>()
        
        // Helper to generate unique options
        fun makeMathOptions(correct: Int): Pair<List<String>, Int> {
            val set = mutableSetOf(correct)
            val offsets = listOf(1, 2, -1, -2, 5, -5, 10, -10, 20, -20)
            for (offset in offsets) {
                if (set.size == 4) break
                val v = correct + offset
                if (v >= 0) set.add(v)
            }
            while (set.size < 4) {
                set.add((set.maxOrNull() ?: 0) + 1)
            }
            val shuffledList = set.toList().sorted()
            val index = shuffledList.indexOf(correct)
            return Pair(shuffledList.map { it.toString() }, index)
        }

        for (g in 6..10) {
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
                        6 -> {
                            // Grade 6
                            when (s) {
                                1 -> {
                                    // Fractions & simplification
                                    val factor = q + 2
                                    val numerator = 3 * factor
                                    val denominator = 5 * factor
                                    questionStrEn = "Simplify the fraction $numerator / $denominator. What is the numerator of the simplest form?"
                                    questionStrSi = "$numerator / $denominator භාගය සරල කරන්න. එහි සරලම ආකාරයේ ලවය කුමක්ද?"
                                    correctValue = 3
                                    hintStrEn = "Divide both the numerator and denominator by their greatest common divisor."
                                    hintStrSi = "ලවය සහ හරය යන දෙකම ඒවායේ පොදු සාධකයෙන් බෙදන්න."
                                }
                                2 -> {
                                    // Ratio matching
                                    val valA = (q + 1) * 10
                                    questionStrEn = "What is the missing value in the ratio: $valA : 30 is equivalent to 1 : ____?"
                                    questionStrSi = "$valA : 30 යන අනුපාතය 1 : ____ අනුපාතයට සමාන වේ නම් හිස්තැනට එන අගය කුමක්ද?"
                                    correctValue = 30 / (q + 1)
                                    hintStrEn = "Divide 30 by $valA of the ratio."
                                    hintStrSi = "අනුපාතය සරල කිරීමට 30 අගය $valA න් බෙදන්න."
                                }
                                3 -> {
                                    // Decimals adding
                                    val valA = q * 10 + 5
                                    questionStrEn = "Calculate: $valA point 5 plus 4 point 5?"
                                    questionStrSi = "$valA දශම පහ එකතු කිරීම හතර දශම පහ කීයද?"
                                    correctValue = valA + 5
                                    hintStrEn = "Line up the decimal numbers and add them."
                                    hintStrSi = "දශම ස්ථාන එක පෙළට තබා එකතු කරන්න."
                                }
                            }
                        }
                        7 -> {
                            // Grade 7
                            when (s) {
                                1 -> {
                                    // Area of rectangles
                                    val length = q + 8
                                    val width = 5
                                    questionStrEn = "What is active area of rectangle with length $length cm and width $width cm?"
                                    questionStrSi = "දිග සෙන්ටිමීටර $length ක් සහ පළල සෙන්ටිමීටර $width ක් වන සෘජුකෝණාස්‍රයක වර්ගඵලය කීයද?"
                                    correctValue = length * width
                                    hintStrEn = "Area is calculated by multiplying length by width."
                                    hintStrSi = "වර්ගඵලය සෙවීමට දිග පළලින් ගුණ කරන්න."
                                }
                                2 -> {
                                    // Simple algebraic term
                                    val coefficient = q + 3
                                    questionStrEn = "If $coefficient x = ${coefficient * 4}, what is the value of x?"
                                    questionStrSi = "$coefficient x = ${coefficient * 4} නම්, x හි අගය කුමක්ද?"
                                    correctValue = 4
                                    hintStrEn = "Divide both sides of the equation by $coefficient."
                                    hintStrSi = "සමීකරණයේ දෙපසම $coefficient න් බෙදන්න."
                                }
                                3 -> {
                                    // Probability scale
                                    questionStrEn = "On a six-sided die, how many outcomes are flatly even numbers?"
                                    questionStrSi = "පැති හයේ කැටයක් පෙරලීමේදී ඉරට්ටේ සංඛ්‍යාවක් ලැබීමේ හැකියාවන් ගණන කීයද?"
                                    correctValue = 3
                                    hintStrEn = "The even numbers are 2, 4, and 6."
                                    hintStrSi = "ලැබිය හැකි ඉරට්ටේ සංඛ්‍යාවන් වන්නේ 2, 4, සහ 6 ය."
                                }
                            }
                        }
                        8 -> {
                            // Grade 8
                            when (s) {
                                1 -> {
                                    // Algebra 2-step
                                    val scale = q + 2
                                    val constant = 5
                                    correctValue = 3
                                    val total = scale * correctValue + constant
                                    questionStrEn = "Solve for x: $scale x + $constant = $total?"
                                    questionStrSi = "x සඳහා විසඳන්න: $scale x + $constant = $total?"
                                    hintStrEn = "Subtract $constant first, then divide by $scale."
                                    hintStrSi = "පළමුව $constant අඩු කර, පසුව $scale න් බෙදන්න."
                                }
                                2 -> {
                                    // Square roots
                                    val bases = listOf(2, 3, 4, 5, 6, 7, 8, 9, 10, 11)
                                    val base = bases[q]
                                    val square = base * base
                                    questionStrEn = "What is the positive square root of $square?"
                                    questionStrSi = "$square හි ධන වර්ගමූලය කුමක්ද?"
                                    correctValue = base
                                    hintStrEn = "Find the positive number that multiplied by itself equals $square."
                                    hintStrSi = "එම සංඛ්‍යාවෙන්ම ගුණ කළ විට $square ලැබෙන ධන සංඛ්‍යාව සොයන්න."
                                }
                                3 -> {
                                    // Angles
                                    val angleA = q * 5 + 40
                                    questionStrEn = "If two angles are complementary, and one is $angleA degrees, what is the other?"
                                    questionStrSi = "කෝණ දෙකක් අනුපූරක කෝණ වේ නම් සහ එකක් අංශක $angleA වේ නම් අනෙක කොපමණද?"
                                    correctValue = 90 - angleA
                                    hintStrEn = "Complementary angles sum up to exactly 90 degrees."
                                    hintStrSi = "අනුපූරක කෝණ දෙකක එකතුව අංශක 90 ක් වේ."
                                }
                            }
                        }
                        9 -> {
                            // Grade 9
                            when (s) {
                                1 -> {
                                    // Simultaneous simplification
                                    val x = q + 1
                                    questionStrEn = "Find x if: x + y = ${x + 5} and y = 5?"
                                    questionStrSi = "x + y = ${x + 5} සහ y = 5 වේ නම්, x හි අගය සොයන්න?"
                                    correctValue = x
                                    hintStrEn = "Substitute y = 5 into the first equation."
                                    hintStrSi = "පළමු සමීකරණයට y වෙනුවට 5 ආදේශ කරන්න."
                                }
                                2 -> {
                                    // Volume of rectangular prism
                                    val l = q + 2
                                    val w = 3
                                    val h = 2
                                    questionStrEn = "What is the volume of a prism with length $l, width $w, and height $h?"
                                    questionStrSi = "දිග $l, පළල $w සහ උස $h වන ප්‍රිස්මයක පරිමාව කොපමණද?"
                                    correctValue = l * w * h
                                    hintStrEn = "Multiply length by width and then by height."
                                    hintStrSi = "දිග, පළල සහ උස යන අගයන් තුනම ගුණ කරන්න."
                                }
                                3 -> {
                                    // Simple discount percentage
                                    val amount = (q + 1) * 100
                                    questionStrEn = "A book worth $amount has a 10 percent discount. How many rupees is saved?"
                                    questionStrSi = "රුපියල් $amount ක් වටිනා පොතකට සියයට 10 ක වට්ටමක් ලැබුණි. ඉතිරි වන මුදල රුපියල් කීයද?"
                                    correctValue = amount / 10
                                    hintStrEn = "Find 10 percent of the total price."
                                    hintStrSi = "මුළු මුදලින් සියයට 10 ක අගය සොයන්න."
                                }
                            }
                        }
                        10 -> {
                            // Grade 10
                            when (s) {
                                1 -> {
                                    // Linear equations
                                    val a = q + 2
                                    questionStrEn = "Solve for x: $a x - 10 = ${a * 5 - 10}?"
                                    questionStrSi = "x සඳහා විසඳන්න: $a x - 10 = ${a * 5 - 10}?"
                                    correctValue = 5
                                    hintStrEn = "Add 10 and then divide both sides by $a."
                                    hintStrSi = "දෙපසටම 10 ක් එකතු කර පසුව $a න් බෙදන්න."
                                }
                                2 -> {
                                    // Indices laws
                                    val exponent = q % 3 + 2
                                    questionStrEn = "What is 2 raised to the power of $exponent?"
                                    questionStrSi = "2 හි $exponent වන බලය කොපමණද?"
                                    correctValue = Math.pow(2.0, exponent.toDouble()).toInt()
                                    hintStrEn = "Multiply 2 by itself $exponent times."
                                    hintStrSi = "2 සංඛ්‍යාව $exponent වතාවක් ගුණ කරන්න."
                                }
                                3 -> {
                                    // Probability of independent events
                                    val totalCoins = 10
                                    val blueCoins = q + 1
                                    questionStrEn = "A bag has $blueCoins blue balls and ${totalCoins - blueCoins} red balls. Out of 100 draws with replacement, how many expected are blue?"
                                    questionStrSi = "බෑගයක නිල් බෝල $blueCoins ක් සහ රතු බෝල ${totalCoins - blueCoins} ක් ඇත. බෝලයක් නැවත දමමින් 100 වතාවක් ඇදීමේදී අපේක්ෂිත නිල් බෝල ගණන කීයද?"
                                    correctValue = blueCoins * 10
                                    hintStrEn = "Multiply probability by 100."
                                    hintStrSi = "නිල් බෝලයක් ලැබීමේ සම්භාවිතාව 100 න් ගුණ කරන්න."
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
                        optionsSinhala = optResult.first,
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
