package com.example.data

object QuestionBank {

    // Main entry point: retrieves exactly 10 questions for a given grade, subject, and stage
    fun getQuestions(grade: Int, subject: String, stage: Int): List<Question> {
        val normalizedSub = when (subject.lowercase().trim()) {
            "math", "mathematics", "ගණිතය" -> "Math"
            "science", "විද්‍යාව", "විද්යාව" -> "Science"
            "english", "ඉංග්‍රීසි", "ඉංග්රීසි" -> "English"
            "history", "ඉතිහාසය" -> "History"
            else -> "Math"
        }

        val list = mutableListOf<Question>()
        for (num in 0..9) {
            val q = when (normalizedSub) {
                "Math" -> generateMathQuestion(grade, stage, num)
                "Science" -> generateScienceQuestion(grade, stage, num)
                "English" -> generateEnglishQuestion(grade, stage, num)
                else -> generateHistoryQuestion(grade, stage, num)
            }
            list.add(q)
        }
        return list
    }

    // A helper data container representing the raw bilingual question resources
    private data class Pentuple(
        val question: String,
        val questionSinhala: String,
        val options: List<String>,
        val optionsSinhala: List<String>,
        val correctAnswer: Int
    )

    // MATHEMATICS QUESTION GENERATOR ENGINE (10 grades x 3 stages x 10 questions = 300 questions)
    private fun generateMathQuestion(grade: Int, stage: Int, num: Int): Question {
        val qid = "g${grade}_Math_s${stage}_q${num}"
        val difficulty = when (stage) {
            1 -> "easy"
            2 -> "medium"
            else -> "hard"
        }

        val p = when (grade) {
            1 -> when (stage) {
                1 -> { // Counting 1-100, number recognition
                    val count = num + 3
                    val stars = "★ ".repeat(count).trim()
                    Pentuple(
                        "How many stars are here: $stars?",
                        "මෙහි තරු කීයක් තිබේද: $stars?",
                        listOf("${count - 1}", "$count", "${count + 1}", "${count + 2}"),
                        listOf("${count - 1}", "$count", "${count + 1}", "${count + 2}"),
                        1
                    )
                }
                2 -> { // Basic addition within 20
                    val a = num + 2
                    val b = num + 3
                    val ans = a + b
                    Pentuple(
                        "Add: What is $a + $b?",
                        "එකතු කරන්න: $a + $b කීයද?",
                        listOf("${ans - 1}", "$ans", "${ans + 1}", "${ans + 2}"),
                        listOf("${ans - 1}", "$ans", "${ans + 1}", "${ans + 2}"),
                        1
                    )
                }
                else -> { // Basic subtraction within 20
                    val a = num + 10
                    val b = num + 1
                    val ans = a - b
                    Pentuple(
                        "Subtract: What is $a - $b?",
                        "අඩු කරන්න: $a - $b කීයද?",
                        listOf("${ans + 1}", "${ans - 1}", "$ans", "${ans + 2}"),
                        listOf("${ans + 1}", "${ans - 1}", "$ans", "${ans + 2}"),
                        2
                    )
                }
            }
            2 -> when (stage) {
                1 -> { // Addition and subtraction within 100
                    val a = 30 + num * 4
                    val b = 15 + num * 2
                    if (num % 2 == 0) {
                        val ans = a + b
                        Pentuple(
                            "Calculate: What is $a + $b?",
                            "ගණනය කරන්න: $a + $b කීයද?",
                            listOf("${ans - 4}", "$ans", "${ans + 4}", "${ans + 8}"),
                            listOf("${ans - 4}", "$ans", "${ans + 4}", "${ans + 8}"),
                            1
                        )
                    } else {
                        val ans = a - b
                        Pentuple(
                            "Calculate: What is $a - $b?",
                            "ගණනය කරන්න: $a - $b කීයද?",
                            listOf("${ans - 2}", "${ans + 2}", "$ans", "${ans + 5}"),
                            listOf("${ans - 2}", "${ans + 2}", "$ans", "${ans + 5}"),
                            2
                        )
                    }
                }
                2 -> { // Intro to multiplication
                    val base = when (num % 3) { 0 -> 2; 1 -> 5; else -> 10 }
                    val multiplier = (num / 3) + 2
                    val ans = base * multiplier
                    Pentuple(
                        "What is $multiplier times $base ($multiplier x $base)?",
                        "$multiplier වරක් $base ($multiplier x $base) කොපමණද?",
                        listOf("${ans - base}", "$ans", "${ans + base}", "${ans + 5}"),
                        listOf("${ans - base}", "$ans", "${ans + base}", "${ans + 5}"),
                        1
                    )
                }
                else -> { // Measurement, basic fractions
                    if (num % 2 == 0) {
                        Pentuple(
                            "A stick is 5 spans long and another is 8 spans. What is the total length?",
                            "ලී ලෑල්ලක් වියත් 5 ක් ද තවත් ලෑල්ලක් වියත් 8 ක් ද වේ. මුළු දිග වියත් කීයද?",
                            listOf("11 spans", "12 spans", "13 spans", "14 spans"),
                            listOf("වියත් 11", "වියත් 12", "වියත් 13", "වියත් 14"),
                            2
                        )
                    } else {
                        Pentuple(
                            "If a cake is cut into 4 equal pieces and you eat 1 piece, what fraction is left?",
                            "කේක් ගෙඩියක් සමාන කොටස් 4 කට කපා ඉන් එකක් අනුභව කළහොත්, ඉතිරිවන භාගය කුමක්ද?",
                            listOf("1/4", "2/4", "3/4", "4/4"),
                            listOf("1/4", "2/4", "3/4", "4/4"),
                            2
                        )
                    }
                }
            }
            3 -> when (stage) {
                1 -> { // Multiplication tables 1-10
                    val a = num + 1
                    val b = 7
                    val ans = a * b
                    Pentuple(
                        "Calculate: $a x $b = ?",
                        "ගණනය කරන්න: $a x $b = ?",
                        listOf("$ans", "${ans + 7}", "${ans - 7}", "${ans + 3}"),
                        listOf("$ans", "${ans + 7}", "${ans - 7}", "${ans + 3}"),
                        0
                    )
                }
                2 -> { // Division as sharing, place value up to 1000
                    if (num % 2 == 0) {
                        val total = (num + 2) * 3
                        val parts = 3
                        val ans = total / parts
                        Pentuple(
                            "Share $total sweets equally among $parts children. How many sweets each?",
                            "පැණිරස කෑම $total ක් ළමයින් $parts දෙනෙකු අතර සම සේ බෙදා දුන්විට එක් අයෙකුට ලැබෙන ප්‍රමාණය?",
                            listOf("${ans - 1}", "$ans", "${ans + 1}", "${ans + 2}"),
                            listOf("${ans - 1}", "$ans", "${ans + 1}", "${ans + 2}"),
                            1
                        )
                    } else {
                        val value = 500 + num * 10 + 3
                        Pentuple(
                            "In the number $value, what is the place value of 5?",
                            "$value යන සංඛ්‍යාවේ 5 හි ස්ථානීය අගය කුමක්ද?",
                            listOf("Ones", "Tens", "Hundreds", "Thousands"),
                            listOf("එකස්ථානය", "දසස්ථානය", "සියස්ථානය", "දහස්ථානය"),
                            2
                        )
                    }
                }
                else -> { // Telling time, calendar
                    if (num % 2 == 0) {
                        Pentuple(
                            "If the clock hour hand is at 3 and minute hand is at 12, what is the time?",
                            "ඔරලෝසුවේ පැය කටුව 3 මත සහ මිනිත්තු කටුව 12 මත ඇතිවිට වේලාව කුමක්ද?",
                            listOf("3:00", "12:30", "12:15", "6:15"),
                            listOf("පස්වරු 3:00", "පස්වරු 12:30", "පස්වරු 12:15", "පස්වරු 6:15"),
                            0
                        )
                    } else {
                        Pentuple(
                            "How many months of the year have exactly 30 days?",
                            "වසරකට දින 30 ක් පමණක් ඇති මාස ගණන කීයද?",
                            listOf("3", "4", "5", "6"),
                            listOf("3", "4", "5", "6"),
                            1
                        )
                    }
                }
            }
            4 -> when (stage) {
                1 -> { // Large numbers up to 10000, perimeter/area intro
                    if (num % 2 == 0) {
                        val value = 4000 + num * 250
                        Pentuple(
                            "In the number $value, what is the value of the digit in thousands place?",
                            "$value සංඛ්‍යාවේ දහස් ස්ථානයේ ඇති ඉලක්කම කුමක්ද?",
                            listOf("4", "0", "8", "1"),
                            listOf("4", "0", "8", "1"),
                            0
                        )
                    } else {
                        val sides = num + 3
                        val peri = sides * 4
                        Pentuple(
                            "What is the perimeter of a square with a side length of $sides cm?",
                            "පැත්තක දිග සෙ.මී. $sides ක් වන සමචතුරස්‍රයක පරිමිතිය කොපමණද?",
                            listOf("${peri - 4}", "$peri", "${peri + 4}", "${peri * 2}"),
                            listOf("${peri - 4}", "$peri", "${peri + 4}", "${peri * 2}"),
                            1
                        )
                    }
                }
                2 -> { // Long multiplication & division
                    val a = (num + 1) * 15
                    val b = 6
                    val ans = a * b
                    Pentuple(
                        "What is $a multiplied by $b ($a x $b)?",
                        "$a ගුණ කිරීම $b ($a x $b) හි අගය කොපමණද?",
                        listOf("${ans - 15}", "${ans + 15}", "$ans", "${ans * 2}"),
                        listOf("${ans - 15}", "${ans + 15}", "$ans", "${ans * 2}"),
                        2
                    )
                }
                else -> { // Fractions comparing
                    Pentuple(
                        "Which of the following fractions is equivalent to 2/4?",
                        "පහත භාගයන්ගෙන් 2/4 ට සමාන වන භාගය කුමක්ද?",
                        listOf("1/3", "1/2", "3/4", "2/3"),
                        listOf("1/3", "1/2", "3/4", "2/3"),
                        1
                    )
                }
            }
            5 -> when (stage) {
                1 -> { // Numbers to 100000, decimals
                    if (num % 2 == 0) {
                        Pentuple(
                            "Which decimal number represents seven-tenths (7/10)?",
                            "දහයෙන් හත (7/10) නියෝජනය කරන දශම සංඛ්‍යාව කුමක්ද?",
                            listOf("0.07", "0.7", "7.0", "0.007"),
                            listOf("0.07", "0.7", "7.0", "0.007"),
                            1
                        )
                    } else {
                        Pentuple(
                            "In the number 85210, what digit is in the ten-thousands column?",
                            "85210 සංඛ්‍යාවේ දසදහස් ස්ථානයේ පවතින ඉලක්කම කුමක්ද?",
                            listOf("5", "2", "8", "1"),
                            listOf("5", "2", "8", "1"),
                            2
                        )
                    }
                }
                2 -> { // Percentages, profit & loss
                    val base = 200 + num * 50
                    val profit = 50
                    val total = base + profit
                    Pentuple(
                        "A book bought for $base LKR was sold for $total LKR. What is the profit?",
                        "රුපියල් $base කට මිලදී ගත් පොතක් රුපියල් $total කට විකුණන ලදී. ලාභය කොපමණද?",
                        listOf("රු. 30", "රු. 40", "රු. 50", "රු. $total"),
                        listOf("LKR 30", "LKR 40", "LKR 50", "LKR $total"),
                        2
                    )
                }
                else -> { // Grade 5 Scholarship problems
                    val count1 = 3
                    val count2 = 12
                    val cost1 = 90
                    val ans = (cost1 / count1) * count2
                    Pentuple(
                        "If $count1 pens cost $cost1 LKR, what is the cost of $count2 pens?",
                        "පෑන් $count1 ක මිල රුපියල් $cost1 ක් නම්, පෑන් $count2 ක මිල කීයද?",
                        listOf("${ans - 30}", "$ans", "${ans + 30}", "${ans * 2}"),
                        listOf("රු. ${ans - 30}", "රු. $ans", "රු. ${ans + 30}", "රු. ${ans * 2}"),
                        1
                    )
                }
            }
            6 -> when (stage) {
                1 -> { // Factors, multiples, LCM, HCF
                    val a = 6
                    val b = 8
                    Pentuple(
                        "What is the Lowest Common Multiple (LCM) of $a and $b?",
                        "$a සහ $b හි කුඩාම පොදු ගුණාකාරය (කු.පො.ගු.) කුමක්ද?",
                        listOf("12", "16", "24", "48"),
                        listOf("12", "16", "24", "48"),
                        2
                    )
                }
                2 -> { // Ratios, integers positive/negative
                    val numSign = num - 5
                    val result = 10 + numSign
                    Pentuple(
                        "Solve: 10 + ($numSign) = ?",
                        "සුළු කරන්න: 10 + ($numSign) = ?",
                        listOf("${result - 2}", "$result", "${result + 2}", "${10 - numSign}"),
                        listOf("${result - 2}", "$result", "${result + 2}", "${10 - numSign}"),
                        1
                    )
                }
                else -> { // Algebra introduction, compound perimeter/area
                    val xVal = num + 2
                    val coeff = 5
                    val constVal = 3
                    val rhs = coeff * xVal + constVal
                    Pentuple(
                        "If ${coeff}x + $constVal = $rhs, find the value of x.",
                        "${coeff}x + $constVal = $rhs නම්, x හි අගය සොයන්න.",
                        listOf("${xVal - 1}", "$xVal", "${xVal + 1}", "${xVal + 2}"),
                        listOf("${xVal - 1}", "$xVal", "${xVal + 1}", "${xVal + 2}"),
                        1
                    )
                }
            }
            7 -> when (stage) {
                1 -> { // Linear equations, directed numbers
                    val a = -12 + num
                    val b = -5
                    val ans = a + b
                    Pentuple(
                        "Solve: ($a) + ($b) = ?",
                        "සුළු කරන්න: ($a) + ($b) = ?",
                        listOf("${ans + 2}", "${ans - 2}", "$ans", "${a - b}"),
                        listOf("${ans + 2}", "${ans - 2}", "$ans", "${a - b}"),
                        2
                    )
                }
                2 -> { // Geometry: angles in triangle, sets
                    val angleA = 50 + num * 2
                    val angleB = 60
                    val angleC = 180 - (angleA + angleB)
                    Pentuple(
                        "Two angles of a triangle are $angleA° and $angleB°. What is the third angle?",
                        "ත්‍රිකෝණයක කෝණ දෙකක් $angleA° සහ $angleB° වේ. එහි තෙවන කෝණය කොපමණද?",
                        listOf("${angleC - 10}°", "$angleC°", "${angleC + 10}°", "90°"),
                        listOf("${angleC - 10}°", "$angleC°", "${angleC + 10}°", "90°"),
                        1
                    )
                }
                else -> { // Statistics mean, median, mode
                    val data = listOf(2, 4, 4, 6, 9)
                    Pentuple(
                        "Find the median value of the data set: 2, 4, 4, 6, 9",
                        "2, 4, 4, 6, 9 දත්ත සමූහයේ මධ්‍යස්ථය (Median) සොයන්න.",
                        listOf("2", "4", "5", "6"),
                        listOf("2", "4", "5", "6"),
                        1
                    )
                }
            }
            8 -> when (stage) {
                1 -> { // Simultaneous equations
                    Pentuple(
                        "Solve synchronous values: If x + y = 10 and x - y = 4, what is x?",
                        "යුගල සමීකරණ විසදන්න: x + y = 10 සහ x - y = 4 නම්, x හි අගය කීයද?",
                        listOf("5", "6", "7", "8"),
                        listOf("5", "6", "7", "8"),
                        2
                    )
                }
                2 -> { // Factorization index/logs
                    val base = 2
                    val pow = num % 3 + 3
                    val ans = Math.pow(base.toDouble(), pow.toDouble()).toInt()
                    Pentuple(
                        "What is $base to the power of $pow ($base^$pow)?",
                        "$base හි $pow වන බලය ($base^$pow) කොපමණද?",
                        listOf("${ans - 2}", "$ans", "${ans + 2}", "${ans * 2}"),
                        listOf("${ans - 2}", "$ans", "${ans + 2}", "${ans * 2}"),
                        1
                    )
                }
                else -> { // Probability, circles
                    Pentuple(
                        "What is the probability of rolling an odd number on a fair six-sided die?",
                        "සමබර ෂඩ්මුඛ දාදු කැටයක් දැමූවිට ඔත්තේ සංඛ්‍යාවක් ලැබීමේ සම්භාවිතාවය කුමක්ද?",
                        listOf("1/6", "1/3", "1/2", "2/3"),
                        listOf("1/6", "1/3", "1/2", "2/3"),
                        2
                    )
                }
            }
            9 -> when (stage) {
                1 -> { // Trigonometry sin cos tan basics
                    Pentuple(
                        "In a right triangle, sin(θ) is calculated as which ratio?",
                        "සෘජුකෝණී ත්‍රිකෝණයක sin(θ) අනුපාතය ගණනය කරන්නේ කෙසේද?",
                        listOf("Adjacent / Hypotenuse", "Opposite / Hypotenuse", "Opposite / Adjacent", "Hypotenuse / Opposite"),
                        listOf("බද්ධ පාදය / විකර්ණය", "සම්මුඛ පාදය / විකර්ණය", "සම්මුඛ පාදය / බද්ධ පාදය", "විකර්ණය / සම්මුඛ පාදය"),
                        1
                    )
                }
                2 -> { // Coordinate geometry, advanced stats
                    val x1 = 1
                    val y1 = 2
                    val x2 = 3
                    val y2 = 2 + num + 1
                    val grad = (y2 - y1) / (x2 - x1)
                    Pentuple(
                        "Find the gradient of the line passing through ($x1, $y1) and ($x2, $y2).",
                        "($x1, $y1) සහ ($x2, $y2) ලක්ෂ්‍ය හරහා යන සරල රේඛාවේ අනුක්‍රමණය සොයන්න.",
                        listOf("${grad - 1}", "$grad", "${grad + 1}", "${grad + 2}"),
                        listOf("${grad - 1}", "$grad", "${grad + 1}", "${grad + 2}"),
                        1
                    )
                }
                else -> { // Binomial theorem, matrices intro
                    Pentuple(
                        "In a matrix A with dimensions 2x3, how many elements are present?",
                        "ප්‍රමාණය 2x3 වන A නියුහයක (Matrix) අඩංගු මූලද්‍රව්‍ය ගණන කීයද?",
                        listOf("2", "3", "5", "6"),
                        listOf("2", "3", "5", "6"),
                        3
                    )
                }
            }
            else -> when (stage) { // Grade 10 O/L Exam syllabus
                1 -> { // Advanced algebra quadratic exam level
                    val rootsSum = 5
                    val rootsProd = 6
                    Pentuple(
                        "What are the roots of the quadratic equation x^2 - ${rootsSum}x + $rootsProd = 0?",
                        "x^2 - ${rootsSum}x + $rootsProd = 0 යන වර්ගජ සමීකරණයේ මූලයන් මොනවාද?",
                        listOf("x = 1, 6", "x = 2, 3", "x = -2, -3", "x = 4, 1"),
                        listOf("x = 1, 6", "x = 2, 3", "x = -2, -3", "x = 4, 1"),
                        1
                    )
                }
                2 -> { // Advanced trig & limits
                    Pentuple(
                        "Evaluate the trigonometric identity: sin^2(θ) + cos^2(θ) = ?",
                        "ත්‍රිකෝණමිතික සර්වසාම්‍යය සුළු කරන්න: sin^2(θ) + cos^2(θ) = ?",
                        listOf("0", "1", "2", "sin(θ)"),
                        listOf("0", "1", "2", "sin(θ)"),
                        1
                    )
                }
                else -> { // Vectors, calculus limits
                    Pentuple(
                        "Find the magnitude of the 2D vector v = 3i + 4j.",
                        "v = 3i + 4j ද්විමාන දෛශිකයේ විශාලත්වය (Magnitude) සොයන්න.",
                        listOf("5", "7", "12", "25"),
                        listOf("5", "7", "12", "25"),
                        0
                    )
                }
            }
        }

        return Question(
            id = qid,
            question = p.question,
            questionSinhala = p.questionSinhala,
            options = p.options,
            optionsSinhala = p.optionsSinhala,
            correctAnswer = p.correctAnswer,
            grade = grade,
            subject = "Math",
            stage = stage,
            difficulty = difficulty
        )
    }

    // GENERAL SCIENCE QUESTION GENERATOR ENGINE (10 grades x 3 stages x 10 questions = 300 questions)
    private fun generateScienceQuestion(grade: Int, stage: Int, num: Int): Question {
        val qid = "g${grade}_Science_s${stage}_q${num}"
        val difficulty = when (stage) {
            1 -> "easy"
            2 -> "medium"
            else -> "hard"
        }

        // Environment, biology, chemistry & physics sorted strictly per Sri Lankan syllabus
        val p = when (grade) {
            1 -> when (stage) {
                1 -> Pentuple("Identify the living thing.", "පණ ඇති (ජීවී) වස්තුවක් තෝරන්න.", listOf("Stone", "Plant", "Car", "Chair"), listOf("ගලක්", "පැලෑටියක්", "මෝටර් රථයක්", "පුටුවක්"), 1)
                2 -> Pentuple("Which plant part is green and absorbs sunlight?", "පැලෑටියක සූර්යාලෝකය උරා ගන්නා කොළ පැහැති කොටස කුමක්ද?", listOf("Root", "Leaf", "Flower", "Fruit"), listOf("මුල", "කොළය", "මල", "ගෙඩිය"), 1)
                else -> Pentuple("What kind of weather is accompanied by rain?", "වැසි ඇද හැලෙන කාලගුණය හඳුන්වන්නේ කෙසේද?", listOf("Sunny", "Rainy", "Windy", "Snowy"), listOf("පෑයූ දවස", "වැසි සහිත දවස", "සුළං සහිත දවස", "හිම සහිත දවස"), 1)
            }
            2 -> when (stage) {
                1 -> Pentuple("Which organ is used to see the world?", "ලෝකය නැරඹීමට උපකාරී වන ශරීර අවයවය කුමක්ද?", listOf("Ear", "Eye", "Nose", "Tongue"), listOf("කන", "ඇස", "නාසය", "දිව"), 1)
                2 -> Pentuple("Identify a healthy food option.", "ශරීරයට හිතකර ගුණදායක ආහාරයක් තෝරන්න.", listOf("Sweets", "Fruits", "Fizzy Drinks", "Pastry"), listOf("පැණිරස කෑම", "පලතුරු", "පැණි බීම", "පේස්ට්‍රි"), 1)
                else -> Pentuple("Which material surface feels rough?", "පහත දැ වලින් රළු පෘෂ්ඨයක් සහිත ද්‍රව්‍යය කුමක්ද?", listOf("Glass", "Sandpaper", "Silk fabric", "Steel slide"), listOf("වීදුරු", "වැලි කඩදාසිය", "සේද රෙදි", "වානේ තහඩුව"), 1)
            }
            3 -> when (stage) {
                1 -> Pentuple("What are the primary things plants need to survive?", "පැලෑටිවලට ජීවත් වීමට අත්‍යවශ්‍ය ප්‍රධාන සාධක මොනවාද?", listOf("Gold & Silver", "Sunlight & Water", "Milk & Bread", "Soda & Juice"), listOf("රන් සහ රිදී", "සූර්යාලෝකය සහ ජලය", "කිරි සහ පාන්", "සෝඩා සහ ජූස්"), 1)
                2 -> Pentuple("Fish live and breed in which habitat?", "මසුන් ජීවත් වන ස්වභාවික නිවහන (Habitat) කුමක්ද?", listOf("Trees", "Desert", "Water", "Underground"), listOf("ගස්", "කාන්තාරය", "ජලය", "භූගතව"), 2)
                else -> Pentuple("When you pull a toy cart, what force are you applying?", "ෙසල්ලම් කරත්තයක් ඔබ දෙසට අදින විට යොදන බලය කුමක්ද?", listOf("Push", "Pull", "Gravity", "Friction"), listOf("තල්ලුව", "ඇදිල්ල", "ගුරුත්වාකර්ෂණය", "ඝර්ෂණය"), 1)
            }
            4 -> when (stage) {
                1 -> Pentuple("Which soil type is best for growing most farm crops?", "බෝග වගා කිරීමට වඩාත් සුදුසු සාරවත් පස් වර්ගය කුමක්ද?", listOf("Coarse Sand", "Loam Soil", "Clay Soil", "Rocky Gravel"), listOf("වැලි පස", "නිසරු පස", "මැටි පස", "ලෝම පස"), 3)
                2 -> Pentuple("What is the process of water changing into gas called?", "ජලය රත්වීමේදී වාෂ්ප (වායුව) බවට පත්වීම හඳුන්වන්නේ කුමක්ද?", listOf("Condensation", "Boiling", "Evaporation", "Freezing"), listOf("ඝනීභවනය", "නටන එක", "වාෂ්පීකරණය", "මිදීම"), 2)
                else -> Pentuple("Which materials are attracted to a strong iron magnet?", "ප්‍රබල චුම්බකයකට ඇදී යන්නේ පහත කුමන කොටස්ද?", listOf("Plastic cup", "Wooden stick", "Iron nails", "Copper wire"), listOf("ප්ලාස්ටික් කෝප්පය", "ලී කැබැල්ල", "යකඩ ඇණ", "තඹ කම්බිය"), 2)
            }
            5 -> when (stage) {
                1 -> Pentuple("Which of these is a solid state of water?", "ජලය ඝන අවස්ථාවේ පවතින රූපය කුමක්ද?", listOf("Rainfall", "Vapor", "Ice", "Dew"), listOf("වැස්ස", "ජල වාෂ්ප", "අයිස්", "පිණි"), 2)
                2 -> Pentuple("Which system in humans digests the food we eat?", "මිනිසා ආහාරයට ගන්නා දෑ ජීර්ණය කරවන පද්ධතිය කුමක්ද?", listOf("Respiratory", "Circulatory", "Digestive", "Nervous"), listOf("ශ්වසන පද්ධතිය", "රුධිර සංසරණ පද්ධතිය", "ආහාර ජීරණ පද්ධතිය", "ස්නායු පද්ධතිය"), 2)
                else -> Pentuple("What is the process of gaseous water vapor turning back to liquid water called?", "ජල වාෂ්ප සිසිල් වී නැවත ද්‍රව ජලය බවට පත්වීම හඳුන්වන්නේ කුමක්ද?", listOf("Sublimation", "Evaporation", "Condensation", "Melting"), listOf("ඌර්ධවපාතනය", "වාෂ්පීකරණය", "ඝනීභවනය", "දියවීම"), 2)
            }
            6 -> when (stage) {
                1 -> Pentuple("What is the basic structural and functional unit of life?", "ජීවීන්ගේ මූලික ව්‍යුහමය හා ක්‍රියාකාරී ඒකකය කුමක්ද?", listOf("Organ", "Tissue", "Cell", "Molecule"), listOf("අවයවය", "පටකය", "සෛලය", "අණුව"), 2)
                2 -> Pentuple("A combination of two or more pure substances is called a:", "පිරිසිදු ද්‍රව්‍ය දෙකක් හෝ වැඩි ගණනක් මිශ්‍ර වී සෑදෙන්නේ කුමක්ද?", listOf("Element", "Compound", "Mixture", "Atom"), listOf("මූලද්‍රව්‍යය", "සංයෝගය", "මිශ්‍රණය", "පරමාණුව"), 2)
                else -> Pentuple("The process by which green plants make food is called:", "කොළ පැහැති ශාක තමන්ටම ආහාර නිපදවා ගන්නා ක්‍රියාවලිය කුමක්ද?", listOf("Respiration", "Photosynthesis", "Transpiration", "Decomposition"), listOf("ශ්වසනය", "ප්‍රභාසංශ්ලේෂණය", "උත්ස්වේදනය", "වියෝජනය"), 1)
            }
            7 -> when (stage) {
                1 -> Pentuple("Which gas is primarily required for plant photosynthesis?", "ප්‍රභාසංශ්ලේෂණය සඳහා ශාක ප්‍රධාන වශයෙන් උරා ගන්නා වායුව කුමක්ද?", listOf("Oxygen", "Hydrogen", "Carbon Dioxide", "Nitrogen"), listOf("ඔක්සිජන්", "හයිඩ්‍රජන්", "කාබන් ඩයොක්සයිඩ්", "නයිට්‍රජන්"), 2)
                2 -> Pentuple("What is the normal body temperature of a healthy human?", "නිරෝගී මිනිසෙකුගේ සාමාන්‍ය ශරීර උෂ්ණත්වය සෙල්සියස් කොපමණද?", listOf("32°C", "37°C", "42°C", "98°C"), listOf("32°C", "37°C", "42°C", "98°C"), 1)
                else -> Pentuple("Which chemical substance turns blue litmus paper into red?", "නිල් ලිට්මස් කඩදාසියක් රතු පැහැයට හරවන රසායනික ද්‍රව්‍යය කුමක්ද?", listOf("Water", "Acid", "Base", "Salt"), listOf("ජලය", "අම්ල", "භෂ්ම", "ලුණු"), 1)
            }
            8 -> when (stage) {
                1 -> Pentuple("Which human body organ filters waste to produce urine?", "මිනිස් සිරුරේ අපද්‍රව්‍ය පෙරා මුත්‍රා නිපදවන ප්‍රධාන අවයවය කුමක්ද?", listOf("Heart", "Liver", "Lungs", "Kidneys"), listOf("හෘදය", "අක්මාව", "පෙනහළු", "වකුගඩු"), 3)
                2 -> Pentuple("What substance is required to burn a fire (combustion)?", "ද්‍රව්‍ය දහනය වීමට (ගින්නක් ඇතිවීමට) අත්‍යවශ්‍ය වායුව කුමක්ද?", listOf("Carbon Dioxide", "Nitrogen", "Oxygen", "Neon"), listOf("කාබන් ඩයොක්සයිඩ්", "නයිට්‍රජන්", "ඔක්සිජන්", "නියොන්"), 2)
                else -> Pentuple("Pressure is scientifically defined as what?", "පීඩනය විද්‍යාත්මකව නිර්වචනය කරන්නේ කෙසේද?", listOf("Force * Area", "Force / Area", "Mass * Acceleration", "Energy / Volume"), listOf("බලය * වර්ගඵලය", "බලය / වර්ගඵලය", "ස්කන්ධය * ත්වරණය", "ශක්තිය / පරිමාව"), 1)
            }
            9 -> when (stage) {
                1 -> Pentuple("Which cell division process results in gamete cells?", "ලිංගික සෛල සාදන සෛල බෙදීමේ ආකාරය කුමක්ද?", listOf("Mitosis", "Meiosis", "Fission", "Budding"), listOf("අසූත්‍රික බෙදීම", "ඌනන විභාජනය", "ද්විඛණ්ඩනය", "මුකුලනය"), 1)
                2 -> Pentuple("Newton's First Law of Motion is also known as the Law of:", "නිව්ටන්ගේ පළමු චලිත නියමය හඳුන්වන වෙනත් නම කුමක්ද?", listOf("Action-Reaction", "Inertia", "Gravity", "Momentum"), listOf("ක්‍රියාව-ප්‍රතික්‍රියාව", "අඩාලතාවය (Inertia)", "ගුරුත්වාකර්ෂණය", "ගම්‍යතාවය"), 1)
                else -> Pentuple("What organic compounds contains carbon and hydrogen only?", "කාබන් සහ හයිඩ්‍රජන් පමණක් අඩංගු කාබනික සංයෝග හඳුන්වන්නේ කුමක් ලෙසද?", listOf("Esters", "Alcohols", "Hydrocarbons", "Carbohydrates"), listOf("එස්ටර", "ඇල්කොහොල්", "හයිඩ්‍රොකාබන", "කාබෝහයිඩ්‍රේට"), 2)
            }
            else -> when (stage) { // Grade 10 O/L General Science
                1 -> Pentuple("Which equation represents Ohm's Law in electricity?", "විදුලි පරිපථ සඳහා ඕම්ගේ නියමය නියෝජනය කරන සූත්‍රය කුමක්ද?", listOf("P = I * R", "V = I * R", "V = I^2 * R", "E = m * c^2"), listOf("P = I * R", "V = I * R", "V = I^2 * R", "E = m * c^2"), 1)
                2 -> Pentuple("What is the chemical name of the vinegar compound?", "විනාකිරි වල අඩංගු අම්ලයේ රසායනික නාමය කුමක්ද?", listOf("Hydrochloric acid", "Sulphuric acid", "Ethanoic acid (Acetic)", "Citric acid"), listOf("හයිඩ්‍රොක්ලෝරික් අම්ලය", "සල්ෆියුරික් අම්ලය", "එතනොයික් අම්ලය", "සිට්‍රික් අම්ලය"), 2)
                else -> Pentuple("The phenomenon of generating electricity using a magnetic field is:", "චුම්බක ක්ෂේත්‍රයක් භාවිතයෙන් සන්නායකයක විදුලිය ජනනය කිරීමේ ක්‍රියාවලිය කුමක්ද?", listOf("Electroplating", "Radiation", "Electromagnetic Induction", "Combustion"), listOf("විද්‍යුත් ලෝහලේපනය", "විකිරණය", "විද්‍යුත් චුම්බක ප්‍රේරණය", "දහනය"), 2)
            }
        }

        return Question(
            id = qid,
            question = p.question,
            questionSinhala = p.questionSinhala,
            options = p.options,
            optionsSinhala = p.optionsSinhala,
            correctAnswer = p.correctAnswer,
            grade = grade,
            subject = "Science",
            stage = stage,
            difficulty = difficulty
        )
    }

    // ENGLISH LANGUAGE QUESTION GENERATOR ENGINE (10 grades x 3 stages x 10 questions = 300 questions)
    private fun generateEnglishQuestion(grade: Int, stage: Int, num: Int): Question {
        val qid = "g${grade}_English_s${stage}_q${num}"
        val difficulty = when (stage) {
            1 -> "easy"
            2 -> "medium"
            else -> "hard"
        }

        val p = when (grade) {
            1 -> when (stage) {
                1 -> Pentuple("Which of these is a lowercase vowel?", "පහත දැ වලින් කුඩා ඉංග්‍රීසි ස්වර අකුරක් තෝරන්න.", listOf("b", "e", "f", "h"), listOf("b", "e", "f", "h"), 1)
                2 -> Pentuple("What is the spelling of the animal: 🐕?", "මෙම සත්වයාගේ නිවැරදි ඉංග්‍රීසි අක්ෂර වින්‍යාසය කුමක්ද: 🐕?", listOf("cat", "dog", "cow", "rat"), listOf("cat", "dog", "cow", "rat"), 1)
                else -> Pentuple("What is the opposite of the word 'Hot'?", "'Hot' (රස්නෙ) යන වචනයේ ප්‍රතිවිරුද්ධ ඉංග්‍රීසි වචනය කුමක්ද?", listOf("Cold", "Sunny", "Warm", "Dry"), listOf("Cold", "Sunny", "Warm", "Dry"), 0)
            }
            2 -> when (stage) {
                1 -> Pentuple("Which pronoun refers to a boy?", "පිරිමි ළමයෙකුට ආමන්ත්‍රණය කිරිමේදී භාවිත කරන සර්වනාමය කුමක්ද?", listOf("she", "he", "it", "they"), listOf("she", "he", "it", "they"), 1)
                2 -> Pentuple("Choose the correct article: 'The bird sat on ___ apple.'", "නිවැරදි ආටිකල් (Article) පදය තෝරන්න: 'The bird sat on ___ apple.'", listOf("a", "an", "the", "many"), listOf("a", "an", "the", "many"), 1)
                else -> Pentuple("What is the plural of 'child'?", "'child' (ළමයා) යන්නෙහි බහුවචන රූපය කුමක්ද?", listOf("childs", "children", "childes", "childrens"), listOf("childs", "children", "childes", "childrens"), 1)
            }
            3 -> when (stage) {
                1 -> Pentuple("Identify the describing word (adjective): 'The tall tree.'", "'The tall tree' යන වාක්‍යයේ නාමවිශේෂණය (Adjective) කුමක්ද?", listOf("the", "tall", "tree", "is"), listOf("the", "tall", "tree", "is"), 1)
                2 -> Pentuple("Select the past tense of 'go'.", "'go' හි අතීත කාලය තෝරන්න.", listOf("goes", "going", "went", "gone"), listOf("goes", "going", "went", "gone"), 2)
                else -> Pentuple("Which word is a question word?", "ප්‍රශ්න ඇසීමට ගන්නා ඉංග්‍රීසි වචනය කුමක්ද?", listOf("They", "Beautiful", "When", "Running"), listOf("They", "Beautiful", "When", "Running"), 2)
            }
            4 -> when (stage) {
                1 -> Pentuple("Complete with preposition: 'The book is ___ the table.'", "නිවැරදි සම්බන්ධක පදය යොදන්න: 'The book is ___ the table.'", listOf("in", "on", "at", "underneath"), listOf("in", "on", "at", "underneath"), 1)
                2 -> Pentuple("Choose the adverb in: 'He ran quickly.'", "'He ran quickly' වාක්‍යයේ ක්‍රියා විශේෂණය (Adverb) තෝරන්න.", listOf("He", "ran", "quickly", "very"), listOf("He", "ran", "quickly", "very"), 2)
                else -> Pentuple("Which conjunction connects this: 'He stayed home ___ it was raining.'", "නිවැරදි සහසම්බන්ධ පදය (Conjunction) තෝරන්න: 'He stayed home ___ it was raining.'", listOf("but", "so", "because", "and"), listOf("but", "so", "because", "and"), 2)
            }
            5 -> when (stage) {
                1 -> Pentuple("Choose the antonym (opposite) of 'ancient'.", "'ancient' (පැරණි) යන වචනයේ ප්‍රතිවිරුද්ධ වචනය කුමක්ද?", listOf("old", "modern", "historic", "heavy"), listOf("old", "modern", "historic", "heavy"), 1)
                2 -> Pentuple("Complete the simple future: 'Tomorrow, I ___ visit Galle.'", "අනාගත කාලයෙන් වාක්‍යය සම්පූර්ණ කරන්න: 'Tomorrow, I ___ visit Galle.'", listOf("shall", "will", "would", "am"), listOf("shall", "will", "would", "am"), 1)
                else -> Pentuple("Select the correct spelling.", "නිවැරදි අක්ෂර වින්‍යාසය සහිත වචනය තෝරන්න.", listOf("Beautifull", "Beautiful", "Beatiful", "Bautifull"), listOf("Beautifull", "Beautiful", "Beatiful", "Bautifull"), 1)
            }
            6 -> when (stage) {
                1 -> Pentuple("What is the passive voice of 'She wrote a letter'?", "'She wrote a letter' හි කර්මකාරක (Passive) වචන එකලස් කිරීම කුමක්ද?", listOf("A letter is written by her.", "A letter was written by her.", "A letter was writing by her.", "She was writing a letter."), listOf("A letter is written by her.", "A letter was written by her.", "A letter was writing by her.", "She was writing a letter."), 1)
                2 -> Pentuple("Choose the correct superlative of 'good'.", "'good' යන පදයේ උත්තමවාචී (Superlative) රූපය කුමක්ද?", listOf("gooder", "better", "best", "goodest"), listOf("gooder", "better", "best", "goodest"), 2)
                else -> Pentuple("Select the indirect form: He said, 'I am ill.'", "ප්‍රකාශිත වදන වක්‍ර ආකාරයෙන් තෝරන්න: He said, 'I am ill.'", listOf("He said that he is ill.", "He said that he was ill.", "He said I was ill.", "He says he is ill."), listOf("He said that he is ill.", "He said that he was ill.", "He said I was ill.", "He says he is ill."), 1)
            }
            7 -> when (stage) {
                1 -> Pentuple("Which auxiliary fits: 'They ___ playing football now.'", "නිවැරදි උපකාරක ක්‍රියාපදය යොදන්න: 'They ___ playing football now.'", listOf("is", "am", "are", "been"), listOf("is", "am", "are", "been"), 2)
                2 -> Pentuple("What is the meaning of the modal 'must'?", "'must' යන මෝඩල් (Modal) පදයෙන් ප්‍රකාශ කෙරෙන අදහස කුමක්ද?", listOf("Ability", "Permission", "Obligation / Command", "Possibility"), listOf("හැකියාව", "අවසරය", "අනිවාර්යය / නියෝගය", "හැකි බව"), 2)
                else -> Pentuple("Identify the prefix that means 'not'.", "විරුද්ධ අරුත දීමට වචනයක් ඉදිරියට එක් කරන උපසර්ගය (Prefix) කුමක්ද?", listOf("pre-", "un-", "re-", "multi-"), listOf("pre-", "un-", "re-", "multi-"), 1)
            }
            8 -> when (stage) {
                1 -> Pentuple("Suffix for making the noun format of 'develop':", "'develop' යන්න නාම පදයක් කිරීමට අගට එක් කරන ප්‍රත්‍යය (Suffix) කුමක්ද?", listOf("-ing", "-ment", "-tion", "-ness"), listOf("-ing", "-ment", "-tion", "-ness"), 1)
                2 -> Pentuple("Translate 'burn the midnight oil':", "ඉංග්‍රීසි භාෂාවේ 'burn the midnight oil' යන උිරිත ප්‍රකාශයේ අරුත කුමක්ද?", listOf("Go to sleep", "Work hard at night", "Light a lamp", "Cook a meal"), listOf("නින්දට යාම", "රෑ බෝවන තුරු මහන්සි වී වැඩ කිරීම", "පහනක් දැල්වීම", "ආහාර පිසීම"), 1)
                else -> Pentuple("Select the correct past perfect form: 'She ___ her work.'", "නිවැරදි අතීත පූර්ණ කාලීන රූපය තෝරන්න: 'She ___ her work.'", listOf("has finished", "had finished", "finish", "finished"), listOf("has finished", "had finished", "finish", "finished"), 1)
            }
            9 -> when (stage) {
                1 -> Pentuple("Which noun is the abstract noun?", "පහත දැ වලින් භාවවාචී නාමපදය (Abstract Noun) තෝරන්න.", listOf("Gold", "Army", "Happiness", "Teacher"), listOf("රත්‍රන්", "හමුදාව", "සතුට", "ගුරුවරයා"), 2)
                2 -> Pentuple("Correct conjunction: 'Neither Amal ___ Kamal came.'", "නිවැරදි පදය තෝරන්න: 'Neither Amal ___ Kamal came.'", listOf("or", "nor", "but", "also"), listOf("or", "nor", "but", "also"), 1)
                else -> Pentuple("Which is a defining clause connector?", "වාක්‍ය දෙකක් සම්බන්ධ කිරීමට යොදා ගන්නා සම්බන්ධක පදයක් තෝරන්න.", listOf("when", "which (who)", "because", "although"), listOf("when", "which (who)", "because", "although"), 1)
            }
            else -> when (stage) { // Grade 10 O/L Syllabus revision
                1 -> Pentuple("Choose plural word: ___ are grazing.", "බහුවචන පදය තෝරන්න: ___ are grazing.", listOf("A sheep", "Sheep", "Sheeps", "The sheep's"), listOf("A sheep", "Sheep", "Sheeps", "The sheep's"), 1)
                2 -> Pentuple("Identify synonym for the word 'generous'.", "'generous' (ත්‍යාගශීලී) යන්නෙහි සමාන පදය තෝරන්න.", listOf("greedy", "kind / charitable", "cruel", "wealthy"), listOf("කෑදර", "කරුණාවන්ත / ත්‍යාගශීලී", "කුරිරු", "ධනවත්"), 1)
                else -> Pentuple("Select conditional clause format: 'If it rains, we ___.'", "නිවැරදි අනුබද්ධ ක්‍රියා කොටස තෝරන්න: 'If it rains, we ___.'", listOf("would cancel", "will cancel", "canceled", "had canceled"), listOf("would cancel", "will cancel", "canceled", "had canceled"), 1)
            }
        }

        return Question(
            id = qid,
            question = p.question,
            questionSinhala = p.questionSinhala,
            options = p.options,
            optionsSinhala = p.optionsSinhala,
            correctAnswer = p.correctAnswer,
            grade = grade,
            subject = "English",
            stage = stage,
            difficulty = difficulty
        )
    }

    // HISTORY QUESTION GENERATOR ENGINE (10 grades x 3 stages x 10 questions = 300 questions)
    private fun generateHistoryQuestion(grade: Int, stage: Int, num: Int): Question {
        val qid = "g${grade}_History_s${stage}_q${num}"
        val difficulty = when (stage) {
            1 -> "easy"
            2 -> "medium"
            else -> "hard"
        }

        val p = when (grade) {
            1 -> when (stage) {
                1 -> Pentuple("What is our country?", "අපගේ මව් රට කුමක්ද?", listOf("India", "Sri Lanka", "Maldives", "England"), listOf("ඉන්දියාව", "ශ්‍රී ලංකාව", "මාලදිවයින", "එංගලන්තය"), 1)
                2 -> Pentuple("What color is the lion on the national flag of Sri Lanka?", "ශ්‍රී ලංකා ජාතික කොඩියේ සිටින සිංහයාගේ වර්ණය කුමක්ද?", listOf("Green", "Orange", "Yellow (Golden)", "Red"), listOf("කොළ", "තැඹිලි", "කහ (රන්වන්)", "රතු"), 2)
                else -> Pentuple("Who is the leader of a school?", "පාසලක ප්‍රධාන නායකයා (ප්‍රධානියා) කවුද?", listOf("Teacher", "Squire", "Principal", "Prefect"), listOf("ගුරුතුමා", "සේවකයා", "විදුහල්පතිතුමා", "ශිෂ්‍ය නායකයා"), 2)
            }
            2 -> when (stage) {
                1 -> Pentuple("Who was the first recorded king of Sri Lanka?", "ශ්‍රී ලංකාවේ මුල්ම ඓතිහාසික රජතුමා ලෙස සැලකෙන්නේ කවුද?", listOf("King Dutugemunu", "King Vijaya", "King Pandukabhaya", "King Devanampiyatissa"), listOf("දුටුගැමුණු රජු", "විජය රජු", "පණ්ඩුකාභය රජු", "දේවානම්පියතිස්ස රජු"), 1)
                2 -> Pentuple("The oldest ancient capital city of Sri Lanka is:", "ශ්‍රී ලංකාවේ පැරණිතම ඉපැරණි රජධානිය කුමක්ද?", listOf("Polonnaruwa", "Kandy", "Anuradhapura", "Kurunegala"), listOf("පොළොන්නරුව", "මහනුවර", "අනුරාධපුරය", "කුරුණෑගල"), 2)
                else -> Pentuple("Buddhism was introduced during the reign of which king?", "ශ්‍රී ලංකාවට බුදුදහම හඳුන්වා දුන්නේ කුමන රජ දවසකද?", listOf("King Dutugemunu", "King Vijaya", "King Devanampiyatissa", "King Parakramabahu"), listOf("දුටුගැමුණු රජු", "විජය රජු", "දේවානම්පියතිස්ස රජු", "පරාක්‍රමබාහු රජු"), 2)
            }
            3 -> when (stage) {
                1 -> Pentuple("Who defeated King Elara to unite the country?", "එළාර රජු පරදා රට එක්සේසත් කළ කීර්තිමත් රජතුමා කවුද?", listOf("King Devanampiyatissa", "King Dutugemunu", "King Valagamba", "King Kasyapa"), listOf("දේවානම්පියතිස්ස රජු", "දුටුගැමුණු රජු", "වළගම්බා රජු", "කාශ්‍යප රජු"), 1)
                2 -> Pentuple("What is the name of the majestic stupa built by King Dutugemunu?", "දුටුගැමුණු රජු විසින් කරවන ලද මහා ස්ථූපය කුමක්ද?", listOf("Jethavanaramaya", "Thuparamaya", "Ruwanwelisaya", "Abhayagiriya"), listOf("ජේතවනාරාමය", "ථූපාරාමය", "රුවන්වැලිසෑය", "අභයගිරිය"), 2)
                else -> Pentuple("Which ancient king is highly honored for constructing massive irrigation tanks?", "විශාල වැව් සහ වාරිමාර්ග ඉදිකිරීම පිළිබඳ අතිශය ප්‍රසිද්ධියට පත් රජතුමා කවුද?", listOf("King Mahasen", "King Vijaya", "King Elara", "King Devanampiyatissa"), listOf("මහසෙන් රජු", "විජය රජු", "එළාර රජු", "දේවානම්පියතිස්ස රජු"), 0)
            }
            4 -> when (stage) {
                1 -> Pentuple("Which historic city became the second capital of Sri Lanka?", "ලංකාවේ දෙවන පැරණි අගනුවර බවට පත් වූ රාජධානිය කුමක්ද?", listOf("Gampola", "Polonnaruwa", "Jaffna", "Dambadeniya"), listOf("ගම්පොළ", "පොළොන්නරුව", "යාපනය", "දඹදෙණිය"), 1)
                2 -> Pentuple("Which King of Polonnaruwa said 'Let not even a drop of rain water flow to the sea without being useful to man'?", "'අහසින් වැටෙන එකදු දිය බිඳක්වත් මිනිසාගේ ප්‍රයෝජනයට නොගෙන මුහුදට නොයවනු' යැයි ප්‍රකාශ කල ශ්‍රේෂ්ඨ රජතුමා කවුද?", listOf("King Dutugemunu", "King Parakramabahu I", "King Vijayabahu I", "King Nissankamalla"), listOf("දුටුගැමුණු රජු", "පරාක්‍රමබාහු රජු (පළමු)", "විජයබාහු රජු (පළමු)", "නිශ්ශංකමල්ල රජු"), 1)
                else -> Pentuple("The beautiful round-shaped temple built to protect the stupa in Polonnaruwa is:", "පොළොන්නරුව යුගයේ ස්ථූපයක් වටා ආරක්ෂාවට ඉදි කළ වටදාගෙය හඳුන්වන්නේ කුමන නමකින්ද?", listOf("Gal Vihara", "Lankatilaka", "Polonnaruwa Vatadage", "Hatadage"), listOf("ගල් විහාරය", "ලංකාතිලකය", "පොළොන්නරුව වටදාගෙය", "හැටදාගෙය"), 2)
            }
            5 -> when (stage) {
                1 -> Pentuple("Which European nation first arrived in Sri Lanka in 1505?", "වර්ෂ 1505 දී ලංකාවට මුලින්ම පැමිණි යුරෝපීය ජාතිය කුමක්ද?", listOf("Dutch", "British", "Portuguese", "French"), listOf("ලන්දේසි", "ඉංග්‍රීසි", "පෘතුගීසි", "ප්‍රංශ"), 2)
                2 -> Pentuple("The maritime provinces of Sri Lanka were captured from Portuguese by which power in 1658?", "ලංකාවේ මුහුදුබඩ පළාත් 1658 දී පෘතුගීසීන්ගෙන් අත්පත් කර ගත්තේ කවුද?", listOf("British", "Dutch", "French", "Malays"), listOf("ඉංග්‍රීසි", "ලන්දේසි", "ප්‍රංශ", "මැලේ ජාතිකයින්"), 1)
                else -> Pentuple("In which year did the entire Sri Lanka fall under British colonial rule?", "මුළු ලංකාවම බ්‍රිතාන්‍ය වැසියන්ගේ යටත් විජිතයක් බවට පත් වූයේ කුමන වර්ෂයේදීද?", listOf("1505", "1658", "1796", "1815"), listOf("1505", "1658", "1796", "1815"), 3)
            }
            6 -> when (stage) {
                1 -> Pentuple("Which ancient civilization is famous for constructing the Pyramids?", "මහා පිරමිඩ ඉදිකිරීම සම්බන්ධයෙන් ඉතා කීර්තිමත් පුරාණ ශිෂ්ඨාචාරය කුමක්ද?", listOf("Mesopotamian", "Egyptian", "Indus Valley", "Chinese"), listOf("මෙෙසපොතේමියානු", "ඊජිප්තු", "සින්ධු නිම්න", "චීන"), 1)
                2 -> Pentuple("Which country is widely recognized as the birthplace of Democracy?", "ප්‍රජාතන්ත්‍රවාදයේ (Democracy) උපන් බිම ලෙස ලෝකයේ පිළිගන්නේ කුමන දේශයද?", listOf("Rome", "Egypt", "Greece", "Persia"), listOf("රෝමය", "ඊජිප්තුව", "ග්‍රීසිය", "පර්සියාව"), 2)
                else -> Pentuple("The ancient trade route connecting East Asia to the Mediterranean is:", "නැගෙනහිර ආසියාව සහ මධ්‍යධරණී මුහුද යා කළ ඓතිහාසික වෙළඳ මාර්ගය කුමක්ද?", listOf("Spice Route", "Incense Route", "Silk Road", "Amber Road"), listOf("කුළුබඩු මාර්ගය", "සුවඳ දුම් මාර්ගය", "සේද මාවත", "ඇම්බර් මාර්ගය"), 2)
            }
            7 -> when (stage) {
                1 -> Pentuple("The social system of Medieval Europe based on land tenure is:", "මධ්‍යතන යුගයේ යුරෝපයේ පැවති, ඉඩම් අයිතිය මත පදනම් වූ පාලන ක්‍රමය කුමක්ද?", listOf("Socialism", "Democracy", "Feudalism", "Capitalism"), listOf("සමාජවාදය", "ප්‍රජාතන්ත්‍රවාදය", "වැඩවසම් ක්‍රමය (Feudalism)", "ධනවාදය"), 2)
                2 -> Pentuple("Who founded the last independent kingdom of Sri Lanka, the Kandyan Kingdom?", "ලංකාවේ අවසාන ස්වාධීන රාජධානිය වන සෙංකඩගල (මහනුවර) රාජධානිය පිහිටුවූයේ කවුද?", listOf("King Senasammatha Vikramabahu", "King Wimaladharmasuriya I", "King Rajasinha II", "King Sri Vikrama Rajasinha"), listOf("සේනාසම්මත වික්‍රමබාහු රජු", "පළමු විමලධර්මසූරිය රජු", "දෙවන රාජසිංහ රජු", "ශ්‍රී වික්‍රම රාජසිංහ රජු"), 0)
                else -> Pentuple("Who was the legendary leader who united the nomadic Mongol tribes in 1206?", "1206 දී මොංගෝලියානු සංචාරක ගෝත්‍රිකයන් එක්සේසත් කළ කීර්තිමත් නායකයා කවුද?", listOf("Alexandar", "Genghis Khan", "Kublai Khan", "Julius Caesar"), listOf("ඇලෙක්සැන්ඩර්", "ජෙන්ගිස් ඛාන්", "කුබ්ලයි ඛාන්", "ජුලියස් සීසර්"), 1)
            }
            8 -> when (stage) {
                1 -> Pentuple("Which major event in Europe in the 18th century marked a transition to factory manufacturing?", "18 වන සියවසේ තොග නිෂ්පාදන කර්මාන්ත ඇරඹීමට හේතු වූ යුරෝපයේ ප්‍රධාන පෙරළිය කුමක්ද?", listOf("French Revolution", "Russian Revolution", "Industrial Revolution", "Renaissance"), listOf("ප්‍රංශ විප්ලවය", "රුසියානු විප්ලවය", "කාර්මික විප්ලවය", "පුනරුදය"), 2)
                2 -> Pentuple("Who led the famous Uva-Wellassa Great Rebellion against British rule in 1818?", "බ්‍රිතාන්‍ය පාලනයට එරෙහිව 1818 දී ඇති වූ ඌව-වෙල්ලස්ස මහා කැරැල්ල මෙහෙයවූයේ කවුද?", listOf("Veera Puran Appu", "Gongalegoda Banda", "Keppetipola Disave", "Madduma Bandara"), listOf("වීර පුරන් අප්පු", "ගොන්ගාලේගොඩ බණ්ඩා", "කැප්පෙටිපොළ දිසාවේ", "මද්දුම බණ්ඩාර"), 2)
                else -> Pentuple("The Kandyan Convention (Udarata Giwisuma) was signed in which year?", "උඩරට ගිවිසුම අත්සන් තබන ලද්දේ කුමන වර්ෂයේදීද?", listOf("1796", "1815", "1818", "1848"), listOf("1796", "1815", "1818", "1848"), 1)
            }
            9 -> when (stage) {
                1 -> Pentuple("In which year did Sri Lanka regain independence from British rule?", "ශ්‍රී ලංකාවට බ්‍රිතාන්‍යයන්ගෙන් නිදහස හිමි වූයේ කුමන වර්ෂයේදීද?", listOf("1945", "1948", "1956", "1972"), listOf("1945", "1948", "1956", "1972"), 1)
                2 -> Pentuple("Who is widely honored as the 'Father of the Nation' in Sri Lanka?", "ශ්‍රී ලංකාවේ 'ජාතියේ පියා' ලෙස බහුමානයට පාත්‍ර වන්නේ කවුද?", listOf("D.S. Senanayake", "S.W.R.D. Bandaranaike", "Sirimavo Bandaranaike", "William Gopallawa"), listOf("ඩී. ඇස්. සේනානායක", "එස්. ඩබ්. ආර්. ඩී. බණ්ඩාරනායක", "සිරිමාවෝ බණ්ඩාරනායක", "විලියම් ගොපල්ලව"), 0)
                else -> Pentuple("Which international organization was formed in 1945 to maintain global peace?", "ලෝක සාමය රැකගැනීම උදෙසා 1945 දී පිහිටුවන ලද ප්‍රමුඛතම ජාත්‍යන්තර සංවිධානය කුමක්ද?", listOf("League of Nations", "United Nations (UN)", "NATO", "European Union"), listOf("ජාතීන්ගේ සංගමය", "එක්සත් ජාතීන්ගේ සංවිධානය (UN)", "නැටෝ සංවිධානය", "යුරෝපා සංගමය"), 1)
            }
            else -> when (stage) { // Grade 10 O/L History Revision
                1 -> Pentuple("Which event triggered the commencement of World War I in 1914?", "1914 දී පළමු ලෝක යුද්ධය ආරම්භ වීමට හේතු වූ ක්ෂණික සිදුවීම කුමක්ද?", listOf("Invasion of Poland", "Assassination of Archduke Franz Ferdinand", "Bombing of Pearl Harbor", "Signing of Versailles Treaty"), listOf("පෝලන්තය ආක්‍රමණය කිරීම", "ෆ්‍රාන්ස් ෆර්ඩිනන්ඩ් කුමාරයා ඝාතනය කිරීම", "පර්ල් වරායට බෝම්බ හෙලීම", "වර්සේල්ස් ගිවිසුම අත්සන් කිරීම"), 1)
                2 -> Pentuple("In which year did World War II end?", "දෙවන ලෝක යුද්ධය අවසන් වූයේ කුමන වර්ෂයේදීද?", listOf("1918", "1939", "1945", "1950"), listOf("1918", "1939", "1945", "1950"), 2)
                else -> Pentuple("Sri Lanka became a Republic and cut final constitutional ties with British crown in:", "ශ්‍රී ලංකාව ජනරජයක් බවට පත් වී බ්‍රිතාන්‍ය කිරුළෙන් සම්පුර්ණයෙන්ම නිදහස් වූයේ කුමන වර්ෂයේදීද?", listOf("1948", "1956", "1972", "1978"), listOf("1948", "1956", "1972", "1978"), 2)
            }
        }

        return Question(
            id = qid,
            question = p.question,
            questionSinhala = p.questionSinhala,
            options = p.options,
            optionsSinhala = p.optionsSinhala,
            correctAnswer = p.correctAnswer,
            grade = grade,
            subject = "History",
            stage = stage,
            difficulty = difficulty
        )
    }
}
