package com.example.data

data class Question(
    val question: String,
    val options: List<String>,
    val correctAnswer: Int, // index 0-3
    val grade: Int,
    val subject: String,
    val difficulty: String,
    val hint: String,
    val id: String = ""
) {
    // Backward compatibility getters for previous screens reading .text and .correctAnswerIndex
    val text: String get() = question
    val correctAnswerIndex: Int get() = correctAnswer
    val questionId: String get() = if (id.isNotEmpty()) id else "grade${grade}_${subject}_${question.hashCode()}"
}

object QuestionPool {

    // Main offline questions query: returns exactly 20 tailored questions
    fun getOfflineQuestions(grade: Int, subject: String, lang: String): List<Question> {
        val isSinhala = lang == "si"
        val questions = mutableListOf<Question>()
        for (index in 1..20) {
            questions.add(generateQuestionFor(grade, subject, index, isSinhala))
        }
        return questions
    }

    // Returns over 200 bonus questions for random gameplay inserts
    fun getBonusQuestions(lang: String): List<Question> {
        val isSinhala = lang == "si"
        val bonus = mutableListOf<Question>()
        for (i in 1..220) {
            val subject = when (i % 4) {
                0 -> "Math"
                1 -> "Science"
                2 -> "English"
                else -> "History"
            }
            val grade = (i % 10) + 1
            bonus.add(generateBonusQuestion(grade, subject, i, isSinhala))
        }
        return bonus
    }

    // Determines difficulty based on grade level (Sri Lankan standards)
    private fun getDifficultyForGrade(grade: Int): String {
        return when (grade) {
            in 1..3 -> "easy"
            in 4..6 -> "medium"
            else -> "hard"
        }
    }

    // Generator function for standard curricular questions
    private fun generateQuestionFor(grade: Int, subject: String, index: Int, isSinhala: Boolean): Question {
        val difficulty = getDifficultyForGrade(grade)
        
        return when (subject.lowercase()) {
            "math" -> generateMathQuestion(grade, index, difficulty, isSinhala)
            "science" -> generateScienceQuestion(grade, index, difficulty, isSinhala)
            "english" -> generateEnglishQuestion(grade, index, difficulty, isSinhala)
            else -> generateHistoryQuestion(grade, index, difficulty, isSinhala)
        }
    }

    // 1. MATHEMATICS Offline Question Engine
    private fun generateMathQuestion(grade: Int, index: Int, difficulty: String, isSinhala: Boolean): Question {
        val questionText: String
        val options: List<String>
        val correctAnswer: Int
        val hint: String

        if (grade in 1..3) {
            // Very simple math: basic addition, subtraction, shapes, counting
            val a = index * 2 + 1
            val b = index + 2
            when (index % 5) {
                0 -> {
                    questionText = if (isSinhala) {
                        "රන් ද්වාරය විවෘත කිරීමට මායා සංකේත එකතු කරන්න: $a + $b හි අගය කීයද?"
                    } else {
                        "To open the Golden Gate, add the magic symbols: What is $a + $b?"
                    }
                    options = listOf("${a + b - 1}", "${a + b}", "${a + b + 2}", "${a * b}")
                    correctAnswer = 1
                    hint = if (isSinhala) "සරල එකතු කිරීමේ මන්ත්‍රය: $a + $b" else "Simple addition: $a + $b"
                }
                1 -> {
                    val start = a + b + 5
                    val sub = b
                    questionText = if (isSinhala) {
                        "මහා මකරා සතුව ජීව බල ලකුණු $start ඇත. කඩු පහරකින් $sub බැගින් අඩු වේ නම් ඉතිරි බලය කොපමණද?"
                    } else {
                        "The High Dragon has $start health points. A sword strike reduces it by $sub. How many remain?"
                    }
                    options = listOf("${start - sub - 2}", "${start - sub + 1}", "${start - sub}", "${start - sub + 3}")
                    correctAnswer = 2
                    hint = if (isSinhala) "අඩු කිරීමේ මුදු මන්ත්‍රය: $start - $sub" else "Subtract $sub from $start."
                }
                2 -> {
                    questionText = if (isSinhala) {
                        "විජ්ජා මිටියේ ඇති ත්‍රිකෝණයක දාර (පැති) කීයක් පවතින්නේද?"
                    } else {
                        "How many sides (edges) does a magical triangle shield have?"
                    }
                    options = listOf("2", "3", "4", "5")
                    correctAnswer = 1
                    hint = if (isSinhala) "ත්‍රිකෝණයක පැති ගණන ගැන සිතන්න." else "Think about a triangle structure."
                }
                3 -> {
                    val count = index + 4
                    questionText = if (isSinhala) {
                        "රණශූරයෙක් රන් කාසි $count බැගින් කණ්ඩායම් 2 කට සමානව බෙදයි. මුළු කාසි ගණන කීයද?"
                    } else {
                        "A warrior distributes $count gold coins to 2 squires. How many total coins are given?"
                    }
                    options = listOf("${count * 2}", "${count + 2}", "${count * 2 - 1}", "${count * 2 + 4}")
                    correctAnswer = 0
                    hint = if (isSinhala) "$count දෙකෙන් ගුණ කරන්න." else "Multiply $count by 2."
                }
                else -> {
                    val total = 10 + index
                    val minus = 4
                    questionText = if (isSinhala) {
                        "මන්ත්‍ර බෝතල් $total කින් $minus ක් පාවිච්චි කල පසු ඉතිරිවන මන්ත්‍ර බෝතල් ගණන?"
                    } else {
                        "You had $total magic potions and drank $minus to heal. How many remain?"
                    }
                    options = listOf("${total - minus + 1}", "${total - minus - 1}", "${total - minus}", "${total - minus + 2}")
                    correctAnswer = 2
                    hint = if (isSinhala) "$total න් $minus ක් අඩු කරන්න." else "Perform basic subtraction: $total - $minus"
                }
            }
        } else if (grade in 4..6) {
            // Intermediate math: multiplication tables, simple fractions, rect borders, clocks
            val a = index * 5
            val b = index + 6
            when (index % 5) {
                0 -> {
                    questionText = if (isSinhala) {
                        "මායා පෙට්ටියක පරිමිතිය සෙවීම සඳහා: දිග සෙ.මී. $a ක් සහ පළල සෙ.මී. $b ක් වන සෘජුකෝණාස්‍රයක පරිමිතිය කොපමණද?"
                    } else {
                        "To solve a lock: What is the perimeter of a chest lid of length $a cm and width $b cm?"
                    }
                    options = listOf("${(a + b) * 2}", "${a * b}", "${a + b}", "${a * 2 + b}")
                    correctAnswer = 0
                    hint = if (isSinhala) "සූත්‍රය: 2 x (දිග + පළල)" else "Formula: 2 * (length + width)"
                }
                1 -> {
                    questionText = if (isSinhala) {
                        "විජ්ජාකාරයාගේ කොටස: 1/2 ට සමාන භාගයක් වන්නේ පහත කුමක්ද?"
                    } else {
                        "The Wizard's share: Which of the following fractions is equivalent to 1/2?"
                    }
                    options = listOf("2/3", "4/8", "3/5", "2/6")
                    correctAnswer = 1
                    hint = if (isSinhala) "භාගය ලඝු කර බලන්න." else "Simplify the options by dividing."
                }
                2 -> {
                    questionText = if (isSinhala) {
                        "බලකොටුවේ ඔරලෝසුව පෙන්වන්නේ පෙ.ව. 09:30 ය. තවත් මිනිත්තු 45 කට පසු වේලාව කුමක්ද?"
                    } else {
                        "The fort clock shows 09:30 AM. What time will it be in 45 minutes?"
                    }
                    options = listOf("10:00 AM", "10:15 AM", "10:30 AM", "09:45 AM")
                    correctAnswer = 1
                    hint = if (isSinhala) "මිනිත්තු 30 කින් 10:00 වන අතර තව 15 ක් එකතු කරන්න." else "30 mins makes it 10:00, then add remaining 15 mins."
                }
                3 -> {
                    val mult = 8
                    questionText = if (isSinhala) {
                        "සටන් භූමියට ගොබ්ලින් කණ්ඩායම් $index ක් පැමිණේ. සෑම කණ්ඩායමකම සෙබළු $mult ක් සිටී නම් මුළු සෙබළු ගණන?"
                    } else {
                        "A goblin brigade has $index squads. If each squad has $mult soldiers, what is the total force?"
                    }
                    options = listOf("${index * mult + 4}", "${index * mult - 2}", "${index * mult}", "${index + mult}")
                    correctAnswer = 2
                    hint = if (isSinhala) "$index වරක් $mult ගුණ කරන්න." else "Direct multiplication of $index by $mult."
                }
                else -> {
                    questionText = if (isSinhala) {
                        "සරල කරන්න: ($index x 10) + 12 හි අගය කුමක්ද?"
                    } else {
                        "Solve the runic equation: ($index * 10) + 12"
                    }
                    options = listOf("${index * 10 + 10}", "${index * 10 + 12}", "${index * 10 - 12}", "${index * 12}")
                    correctAnswer = 1
                    hint = if (isSinhala) "පළමුව වරහන් තුල ගුණ කර පසුව 12 ක් එකතු කරන්න." else "Execute parentheses multiplication first, then add 12."
                }
            }
        } else if (grade in 7..9) {
            // Advanced math: linear algebraic equations, ratios, percentage profit, statistics
            val multiplier = index + 1
            val constant = index * 3
            val total = (multiplier * 5) + constant
            when (index % 5) {
                0 -> {
                    questionText = if (isSinhala) {
                        "බීජ ගණිතය: ${multiplier}x + $constant = $total නම් x හි අගය සොයන්න."
                    } else {
                        "Runic Algebra: If ${multiplier}x + $constant = $total, what is the value of x?"
                    }
                    options = listOf("3", "4", "5", "6")
                    correctAnswer = 2 // x = 5
                    hint = if (isSinhala) "පළමුව $total න් $constant අඩුකර, ලැබෙන අගය $multiplier න් බෙදන්න." else "Subtract $constant from $total, then divide by $multiplier."
                }
                1 -> {
                    val p = index * 10
                    questionText = if (isSinhala) {
                        "මායා කඩුවක් රු. 500 කට මිලදී ගෙන රු. ${500 + p} කට විකිණීමෙන් ලබන ලාභ ප්‍රතිශතය කොපමණද?"
                    } else {
                        "A magical sword is bought for 500 gold coins and sold for ${500 + p} coins. What is the profit percentage?"
                    }
                    val pct = (p.toFloat() / 500f) * 100f
                    options = listOf("${pct - 5}%", "${pct}%", "${pct + 10}%", "${pct / 2}%")
                    correctAnswer = 1
                    hint = if (isSinhala) "ලාභය මුල් මිලෙන් බෙදා 100 න් ගුණ කරන්න." else "Profit divide by cost, then multiplied by 100."
                }
                2 -> {
                    questionText = if (isSinhala) {
                        "නිධන් පෙට්ටියක ඇති රන් හා රිදී කාසිවල අනුපාතය ${index}:2 කි. මුළු කාසි 24 ක් ඇති අතර රිදී කාසි අනුපාතය 2 නම් රන් කාසි ප්‍රමාණය කීයද? (index=$index)"
                    } else {
                        "A treasury ratio of gold to silver coins is ${index}:2. If there are ${12 * (index + 2)} total coins, how many gold coins exist?"
                    }
                    val totalParts = index + 2
                    val share = (12 * (index + 2)) / totalParts
                    val goldCoins = index * share
                    options = listOf("${goldCoins - 4}", "${goldCoins}", "${goldCoins + 6}", "${goldCoins / 2}")
                    correctAnswer = 1
                    hint = if (isSinhala) "අනුපාත කොටස් වල එකතුවෙන් මුළු අගය බෙදන්න." else "Find value of one part, then multiply by gold part."
                }
                3 -> {
                    questionText = if (isSinhala) {
                        "දිග සෙ.මී. 8 ක්, පළල සෙ.මී. 6 ක් වන සෘජුකෝණාස්‍රාකාර කාමරයක විකර්ණයේ (Diagonal) දිග කොපමණද?"
                    } else {
                        "What is the diagonal length of a master wizard study measuring 8 meters by 6 meters?"
                    }
                    options = listOf("9m", "10m", "12m", "14m")
                    correctAnswer = 1
                    hint = if (isSinhala) "පයිතගරස් ප්‍රමේයය භාවිත කරන්න: 8^2 + 6^2 හි වර්ගමූලය." else "Apply Pythagoras theorem: square root of (8^2 + 6^2)."
                }
                else -> {
                    val count = 20 * index
                    questionText = if (isSinhala) {
                        "අශ්වයන් $count දෙනෙකුට දින 10 ක් සෑහෙන තණකොළ, අශ්වයන් 10 දෙනෙකුට දින කීයකට සෑහේද?"
                    } else {
                        "A supply of hay feeds $count horses for 10 days. How many days will it last for 10 horses?"
                    }
                    val days = (count * 10) / 10
                    options = listOf("${days / 2}", "${days + 5}", "${days}", "${days - 10}")
                    correctAnswer = 2
                    hint = if (isSinhala) "අශ්වයන් ගණන සහ දින ගණන ප්‍රතිලෝමව සමානුපාතික වේ." else "Horses and days are inversely proportional."
                }
            }
        } else {
            // Grade 10: Complex exam level mathematics (trigonometry, quadratic equations, probability, matrices)
            val coeff = index + 2
            when (index % 5) {
                0 -> {
                    questionText = if (isSinhala) {
                        "සමීකරණ: x² - ${coeff + 3}x + ${coeff * 3} = 0 හි මූලයන් (Roots) මොනවාද?"
                    } else {
                        "Quadratic Equation: What are the roots of the equation x² - ${coeff + 3}x + ${coeff * 3} = 0?"
                    }
                    options = listOf("x = 2, 4", "x = 3, $coeff", "x = 1, 5", "x = -3, -$coeff")
                    correctAnswer = 1
                    hint = if (isSinhala) "සමීකරණය සාධක වලට වෙන් කරන්න: (x - 3)(x - $coeff) = 0" else "Factorize the quadratic expression: (x - 3)(x - $coeff) = 0"
                }
                1 -> {
                    questionText = if (isSinhala) {
                        "ත්‍රිකෝණමිතිය: සෘජුකෝණී ත්‍රිකෝණයක sin(θ) = 3/5 නම්, cos(θ) හි අගය කුමක්ද?"
                    } else {
                        "Trigonometry: In a right-angled triangle, if sin(θ) = 3/5, what is the value of cos(θ)?"
                    }
                    options = listOf("4/5", "3/4", "5/4", "1/2")
                    correctAnswer = 0
                    hint = if (isSinhala) "පයිතගරස් ත්‍රිත්වය (3, 4, 5) මඟින් පාද ලබාගෙන cos(θ) = ළඟ පාදය / විකර්ණය උපයෝගී කරගන්න." else "Use the 3-4-5 right triangle: cos(θ) = adjacent / hypotenuse."
                }
                2 -> {
                    val red = index + 3
                    val blue = index + 1
                    val totalGems = red + blue
                    questionText = if (isSinhala) {
                        "සම්භාවිතාවය: මල්ලක රතු මැණික් $red ක් සහ නිල් මැණික් $blue ක් ඇත. අහඹු ලෙස එකක් ඇදීමේදී එය නිල් මැණිකක් වීමේ සම්භාවිතාවය කීයද?"
                    } else {
                        "Probability Spell: A bag contains $red ruby gems and $blue sapphire gems. What is the probability of randomly drawing a sapphire?"
                    }
                    options = listOf("$blue/$totalGems", "$red/$totalGems", "1/2", "1/$totalGems")
                    correctAnswer = 0
                    hint = if (isSinhala) "නිල් මැණික් ගණන මුළු මැණික් ගණනින් බෙදන්න." else "Divide the number of sapphires by the total number of gems."
                }
                3 -> {
                    questionText = if (isSinhala) {
                        "ලඝුගණක: log₂($coeff) + log₂(4) හි අගය සමාන වන්නේ කුමකටද?"
                    } else {
                        "Logarithms: What is the simplified expression for log₂($coeff) + log₂(4)?"
                    }
                    options = listOf("log₂(${coeff + 4})", "log₂(${coeff * 4})", "log₂($coeff / 4)", "2 * log₂($coeff)")
                    correctAnswer = 1
                    hint = if (isSinhala) "ලඝුගණක නීතිය: log(A) + log(B) = log(A x B)" else "Laws of logs: log(A) + log(B) = log(A * B)"
                }
                else -> {
                    questionText = if (isSinhala) {
                        "ශ්‍රේණි: මුල් පදය $coeff ද, පොදු අන්තරය 3 ක් ද වන අංක ගණිත ශ්‍රේණියක 10 වන පදය කුමක්ද?"
                    } else {
                        "Progressions: In an arithmetic progression with first term a = $coeff and common difference d = 3, what is the 10th term?"
                    }
                    val term10 = coeff + (9 * 3)
                    options = listOf("${term10 - 3}", "${term10 + 3}", "$term10", "${term10 * 2}")
                    correctAnswer = 2
                    hint = if (isSinhala) "සූත්‍රය භාවිත කරන්න: T_n = a + (n-1)d" else "Apply the formula: T_n = a + (n-1)*d"
                }
            }
        }

        return Question(
            question = questionText,
            options = options,
            correctAnswer = correctAnswer,
            grade = grade,
            subject = "Math",
            difficulty = difficulty,
            hint = hint
        )
    }

    // 2. SCIENCE Offline Question Engine
    private fun generateScienceQuestion(grade: Int, index: Int, difficulty: String, isSinhala: Boolean): Question {
        val questionText: String
        val options: List<String>
        val correctAnswer: Int
        val hint: String

        if (grade in 1..3) {
            when (index % 5) {
                0 -> {
                    questionText = if (isSinhala) {
                        "පහත සඳහන් දැ වලින් මායා වනාන්තරයේ ඇති පණ නැති (අජීවී) දෙයක් වන්නේ කුමක්ද?"
                    } else {
                        "Which of these is a non-living thing in the magical forest?"
                    }
                    options = if (isSinhala) {
                        listOf("මකරා", "ගලක් (Rock)", "ගසක්", "හාවා")
                    } else {
                        listOf("A flying dragon", "A river rock", "An elven oak tree", "A wild rabbit")
                    }
                    correctAnswer = 1
                    hint = if (isSinhala) "පණ නැති දේට ආහාර හෝ හුස්ම ගැනීම අවශ්‍ය නැත." else "Non-living things do not eat, grow, or breathe."
                }
                1 -> {
                    questionText = if (isSinhala) {
                        "පලා වර්ග තමන්ට මන්ත්‍ර ආහාර නිපදවා ගැනීමට උරා ගන්නා ප්‍රධාන ආලෝක ශක්තිය කුමක්ද?"
                    } else {
                        "What natural source of light do green plants absorb to make their food?"
                    }
                    options = if (isSinhala) {
                        listOf("සූර්යාලෝකය (Sunlight)", "තරු එළිය", "ගිනි කූරු එළිය", "පුනීල විදුලි පහන")
                    } else {
                        listOf("Sunlight", "Starlight", "Firefly glow", "Candle flame")
                    }
                    correctAnswer = 0
                    hint = if (isSinhala) "ඉහළ අහසේ දිදුලන විශාල රන් පැහැති තරුවයි." else "The bright golden star in the daytime sky."
                }
                2 -> {
                    questionText = if (isSinhala) {
                        "වීරයෙකුට සතුරන් එන ශබ්දය ඇසීමට උදව් වන පංචේන්ද්‍රිය අවයවය කුමක්ද?"
                    } else {
                        "Which sensory organ tells a knight that a dragon is roaring nearby?"
                    }
                    options = if (isSinhala) {
                        listOf("ඇස", "දිව", "කන (Ear)", "නාසය")
                    } else {
                        listOf("Eye", "Tongue", "Ear", "Nose")
                    }
                    correctAnswer = 2
                    hint = if (isSinhala) "ශබ්ද තරංග ග්‍රහණය කර ගන්නා අපගේ ඉන්ද්‍රියකි." else "The organ responsible for capturing sound waves."
                }
                3 -> {
                    questionText = if (isSinhala) {
                        "ගසක පසේ තදින් සවි වී තිබීමට සහ ජලය උරා ගැනීමට උදව් වන කෙටස කුමක්ද?"
                    } else {
                        "Which part of a plant anchors it in the soil and absorbs water?"
                    }
                    options = if (isSinhala) {
                        listOf("කොළය", "මල", "මුල (Root)", "අත්ත")
                    } else {
                        listOf("Leaves", "Flowers", "Root", "Stem")
                    }
                    correctAnswer = 2
                    hint = if (isSinhala) "පස යට සැඟවී පවතින ප්‍රධාන කොටසයි." else "The section that grows underground."
                }
                else -> {
                    questionText = if (isSinhala) {
                        "අයිස් (Ice) රත් වූ විට එය කුමන තත්වයකට පත්වේද?"
                    } else {
                        "What does ice turn into when it is heated by the wizard's torch?"
                    }
                    options = if (isSinhala) {
                        listOf("වායුවක්", "දියරක් (Water)", "ඝනකමක්", "දුමාරයක්")
                    } else {
                        listOf("Gas", "Liquid water", "Solid rock", "Green light")
                    }
                    correctAnswer = 1
                    hint = if (isSinhala) "අයිස් දිය වී ද්‍රවයක් බවට පත් වේ." else "Solid ice melts into a liquid substance."
                }
            }
        } else if (grade in 4..6) {
            when (index % 5) {
                0 -> {
                    questionText = if (isSinhala) {
                        "ජලය රත් වී වාෂ්ප බවට පත්වීමේ භෞතික ක්‍රියාවලිය හඳුන්වන්නේ කුමක්ද?"
                    } else {
                        "What is the physical process of water turning into steam upon boiling?"
                    }
                    options = if (isSinhala) {
                        listOf("ඝනීභවනය", "වාෂ්පීකරණය (Evaporation)", "ද්‍රවීකරණය", "මිදීම")
                    } else {
                        listOf("Condensation", "Evaporation", "Melting", "Freezing")
                    }
                    correctAnswer = 1
                    hint = if (isSinhala) "දියර වායුවක් බවට පරිවර්තනය වීමයි." else "Water transitions from liquid to gas."
                }
                1 -> {
                    questionText = if (isSinhala) {
                        "විජ්ජා සෙබළෙකුගේ චුම්බක යෂ්ටියකට ඇදී යන්නේ පහත කුමන ලෝහයද?"
                    } else {
                        "Which metallic material is strongly attracted by a wizard's magnet?"
                    }
                    options = if (isSinhala) {
                        listOf("රන් (Gold)", "යකඩ (Iron)", "තඹ", "ප්ලාස්ටික්")
                    } else {
                        listOf("Gold", "Iron", "Copper", "Plastic")
                    }
                    correctAnswer = 1
                    hint = if (isSinhala) "කඩු සහ පලිස් සෑදීමට ගන්නා ප්‍රධාන ලෝහයයි." else "The primary magnetic metal used to forge swords."
                }
                2 -> {
                    questionText = if (isSinhala) {
                        "මිනිස් ජීවිතය පවත්වා ගැනීමට අප ආශ්වාස කරන අත්‍යවශ්‍ය වායුව කුමක්ද?"
                    } else {
                        "Which invisible gas in the air is essential for human respiration?"
                    }
                    options = if (isSinhala) {
                        listOf("කාබන් ඩයොක්සයිඩ්", "ඔක්සිජන් (Oxygen)", "නයිට්‍රජන්", "හීලියම්")
                    } else {
                        listOf("Carbon Dioxide", "Oxygen", "Nitrogen", "Argon")
                    }
                    correctAnswer = 1
                    hint = if (isSinhala) "පැලෑටි ප්‍රභාසංශ්ලේෂණයේදී මෙය පිට කරයි." else "Green leaves release this gas in daytime."
                }
                3 -> {
                    questionText = if (isSinhala) {
                        "වස්තුවක් මත යොදන තල්ලුව හෝ ඇදීම විද්‍යාත්මකව හඳුන්වන්නේ කුමන නමකින්ද?"
                    } else {
                        "A push or a pull exerted on an object is scientifically called what?"
                    }
                    options = if (isSinhala) {
                        listOf("ශක්තිය", "බලය (Force)", "කාර්යය", "වේගය")
                    } else {
                        listOf("Energy", "Force", "Work", "Speed")
                    }
                    correctAnswer = 1
                    hint = if (isSinhala) "නිශ්චල දේවල් චලනය කිරීමට මෙය යෙදිය යුතුය." else "It causes an object to accelerate."
                }
                else -> {
                    questionText = if (isSinhala) {
                        "දේදුන්න සැදීමේදී ආලෝකය වර්ණ 7 කට බෙදීමේ සංසිද්ධිය හඳුන්වන්නේ කුමක්ද?"
                    } else {
                        "What is the dispersion of white light into seven distinct colors called?"
                    }
                    options = if (isSinhala) {
                        listOf("ආලෝක ප්‍රකිරීම් (Dispersion)", "පරාවර්තනය", "වර්තනය", "ශෝෂණය")
                    } else {
                        listOf("Dispersion", "Reflection", "Refraction", "Absorption")
                    }
                    correctAnswer = 0
                    hint = if (isSinhala) "ආලෝකය ප්‍රිස්මයක් තුලින් යාමේදී ද මෙය සිදුවේ." else "Occurs when light passes through water drops or prisms."
                }
            }
        } else if (grade in 7..9) {
            when (index % 5) {
                0 -> {
                    questionText = if (isSinhala) {
                        "ජීවයේ ව්‍යුහමය හා ක්‍රියාකාරී ඒකකය වන සෛලයේ (Cell) ශක්තිගාරය හඳුන්වන්නේ කුමක්ද?"
                    } else {
                        "Which organelle is famously known as the powerhouse of the eukaryotic cell?"
                    }
                    options = if (isSinhala) {
                        listOf("න්‍යෂ්ටිය", "මයිටොකොන්ඩ්‍රියාව (Mitochondria)", "රයිබොසෝම", "ප්ලාස්ම පටලය")
                    } else {
                        listOf("Nucleus", "Mitochondria", "Ribosome", "Lysosome")
                    }
                    correctAnswer = 1
                    hint = if (isSinhala) "සෛලීය ශ්වසනය මඟින් ATP ශක්තිය නිපදවන තැන." else "Where cellular respiration produces ATP."
                }
                1 -> {
                    questionText = if (isSinhala) {
                        "දෙහි, දොඩම් වැනි ඇඹුල් පලතුරුවල බහුලව අඩංගු වන අම්ලය (Acid) කුමක්ද?"
                    } else {
                        "Which mild organic acid is abundant in citrus fruits like lemons and limes?"
                    }
                    options = if (isSinhala) {
                        listOf("සිට්‍රික් අම්ලය (Citric Acid)", "හයිඩ්‍රොක්ලෝරික් අම්ලය", "සල්ෆියුරික් අම්ලය", "ලැක්ටික් අම්ලය")
                    } else {
                        listOf("Citric Acid", "Hydrochloric Acid", "Sulfuric acid", "Acetic Acid")
                    }
                    correctAnswer = 0
                    hint = if (isSinhala) "පලතුරුවලට ඇඹුල් රසය දෙන ස්වභාවික දුර්වල අම්ලයකි." else "A weak organic acid with formula C6H8O7."
                }
                2 -> {
                    questionText = if (isSinhala) {
                        "ආවර්තිතා වගුවේ (Periodic Table) පළමුවෙන්ම පිහිටා ඇති සහ විශ්වයේ සැහැල්ලුම මූලද්‍රව්‍යය කුමක්ද?"
                    } else {
                        "What is the first element in the periodic table and the lightest gas in the universe?"
                    }
                    options = listOf("Helium", "Hydrogen", "Lithium", "Oxygen")
                    correctAnswer = 1
                    hint = if (isSinhala) "මෙහි පරමාණුක ක්‍රමාංකය (Atomic Number) 1 වේ." else "Atomic number is 1, symbol is H."
                }
                3 -> {
                    questionText = if (isSinhala) {
                        "මිනිස් සිරුරේ දිගම සහ ශක්තිමත්ම අස්ථිය (Bone) කුමක්ද?"
                    } else {
                        "What is the longest and strongest structural bone in the human body?"
                    }
                    options = if (isSinhala) {
                        listOf("කශේරුකාව", "කපාල අස්ථිය", "ඌරු ඇටය (Femur)", "කුඩා අස්ථිය")
                    } else {
                        listOf("Spine", "Skull bone", "Femur (Thigh bone)", "Rib bone")
                    }
                    correctAnswer = 2
                    hint = if (isSinhala) "මෙය කලවා ප්‍රදේශයේ පිහිටා ඇත." else "Located inside the thigh region."
                }
                else -> {
                    questionText = if (isSinhala) {
                        "විද්‍යුත් ධාරාවක් ගලායාම මැනීම සඳහා පරිපථයකට ශ්‍රේණිගතව සම්බන්ධ කරනු ලබන උපකරණය කුමක්ද?"
                    } else {
                        "Which instrument is connected in series to measure electric current?"
                    }
                    options = if (isSinhala) {
                        listOf("වෝල්ට්මීටරය", "ගැල්වනෝමීටරය", "ඇමීටරය (Ammeter)", "ඔ්ම්මීටරය")
                    } else {
                        listOf("Voltmeter", "Galvanometer", "Ammeter", "Ohmmeter")
                    }
                    correctAnswer = 2
                    hint = if (isSinhala) "විද්‍යුත් ධාරාව මනින්නේ ඇම්පියර් (A) වලිනි." else "Measures flow in Amperes (A)."
                }
            }
        } else {
            // Grade 10: Complex exam level science
            when (index % 5) {
                0 -> {
                    questionText = if (isSinhala) {
                        "භෞතික විද්‍යාව: නිව්ටන්ගේ දෙවන චලිත නියමයට අදාල ගණිතමය සූත්‍රය කුමක්ද?"
                    } else {
                        "Physics Formula: Which math equation represents Newton's Second Law of Motion?"
                    }
                    options = listOf("F = m * a", "W = m * g", "V = I * R", "E = m * c²")
                    correctAnswer = 0
                    hint = if (isSinhala) "බලය = ස්කන්ධය x ත්වරණය" else "Force equals Mass times Acceleration."
                }
                1 -> {
                    questionText = if (isSinhala) {
                        "රසායනික සූත්‍ර: සල්ෆියුරික් අම්ලයේ නිවැරදි රසායනික සූත්‍රය කුමක්ද?"
                    } else {
                        "Chemistry Scroll: What is the correct chemical formula of highly acidic Sulfuric Acid?"
                    }
                    options = listOf("HCl", "HNO3", "H2SO4", "CH3COOH")
                    correctAnswer = 2
                    hint = if (isSinhala) "මෙහි හයිඩ්‍රජන්, සල්ෆර් සහ ඔක්සිජන් පරමාණු ඇත." else "Contains Hydrogen, Sulfur, and Oxygen atoms."
                }
                2 -> {
                    questionText = if (isSinhala) {
                        "ප්‍රවේණි විද්‍යාව: මිනිසෙකුගේ පිරිමි ලිංගිකත්වය තීරණය කරන ක්‍රෝමසෝම යුගලය කුමක්ද?"
                    } else {
                        "Genetics Lab: Which chromosome pair determines male sex in human offspring?"
                    }
                    options = listOf("XX", "XY", "YY", "XO")
                    correctAnswer = 1
                    hint = if (isSinhala) "පිරිමි ලිංගයේදී ක්‍රෝමසෝම දෙක අසමාන යුගලක් ලෙස පවතී." else "A mismatched pair of sex chromosomes."
                }
                3 -> {
                    questionText = if (isSinhala) {
                        "තරංග: සංඛ්‍යාතය 50 Hz සහ තරංග ආයාමය 6 m වන තරංගයක ප්‍රවේගය (v = f * λ) කොපමණද?"
                    } else {
                        "Wave Dynamics: What is the velocity of a wave with frequency 50 Hz and wavelength 6 meters?"
                    }
                    options = listOf("300 m/s", "150 m/s", "3000 m/s", "8.3 m/s")
                    correctAnswer = 0
                    hint = if (isSinhala) "සංඛ්‍යාතය සහ තරංග ආයාමය ගුණ කරන්න: v = 50 x 6" else "Multiply frequency by wavelength: v = f * lambda."
                }
                else -> {
                    questionText = if (isSinhala) {
                        "ලෝහ සක්‍රීයතා ශ්‍රේණියේ වඩාත්ම සක්‍රීය (ක්‍රියාකාරී) ලෝහය කාණ්ඩයේ ඉහළින්ම ඇත. එය කුමක්ද?"
                    } else {
                        "Reactivity Series: Which metal sits near the very top of the reactivity series?"
                    }
                    options = if (isSinhala) {
                        listOf("යකඩ", "තඹ", "පොටෑසියම් (Potassium)", "රන්")
                    } else {
                        listOf("Iron", "Copper", "Potassium", "Gold")
                    }
                    correctAnswer = 2
                    hint = if (isSinhala) "ජලය සමඟ ඉතා ප්‍රචණ්ඩ ලෙස ප්‍රතික්‍රියා කරන මෘදු ලෝහයකි." else "Violently reacts with cold water; symbol is K."
                }
            }
        }

        return Question(
            question = questionText,
            options = options,
            correctAnswer = correctAnswer,
            grade = grade,
            subject = "Science",
            difficulty = difficulty,
            hint = hint
        )
    }

    // 3. ENGLISH Offline Question Engine
    private fun generateEnglishQuestion(grade: Int, index: Int, difficulty: String, isSinhala: Boolean): Question {
        val questionText: String
        val options: List<String>
        val correctAnswer: Int
        val hint: String

        if (grade in 1..3) {
            when (index % 5) {
                0 -> {
                    questionText = if (isSinhala) {
                        "ප්‍රතිවිරුද්ධ වචන: 'hot' (රස්නෙ) යන වචනයේ යන්නෙහි ප්‍රතිවිරුද්ධ ඉංග්‍රීසි වචනය කුමක්ද?"
                    } else {
                        "Opposite words: What is the antonym of the word 'hot'?"
                    }
                    options = listOf("Warm", "Cold", "Dry", "Fire")
                    correctAnswer = 1
                    hint = if (isSinhala) "අයිස් වැනි සිසිල් දේ හඳුන්වන වචනය." else "Think of freezing snow and ice."
                }
                1 -> {
                    questionText = if (isSinhala) {
                        "නාම පදයක් සොයන්න: 'The brave knight fought the dragon.' මෙහි ඇති නාම පදයක් වන්නේ කුමක්ද?"
                    } else {
                        "Identify the noun: 'The swift dragon flew over the trees.' Which word is the noun?"
                    }
                    options = listOf("swift", "flew", "dragon", "over")
                    correctAnswer = 2
                    hint = if (isSinhala) "සත්ත්වයා හෝ උපකරණය හඳුන්වන නාමයයි." else "It is the naming word of the flying creature."
                }
                2 -> {
                    questionText = if (isSinhala) {
                        "හිස්තැන පුරවන්න: S_I_E_D (ආරක්ෂිත පලිහ) වචනයේ නිවැරදි අක්ෂර වින්‍යාසය කුමක්ද?"
                    } else {
                        "Spelling scroll: Fill in the missing letters for a protective knight armor: S H I _ _ D"
                    }
                    options = listOf("E L", "O L", "E R", "U R")
                    correctAnswer = 0
                    hint = if (isSinhala) "කඩුපහරවලින් බේරීමට ගන්නා පලිහයි." else "A shield protects a knight from sword slashes."
                }
                3 -> {
                    questionText = if (isSinhala) {
                        "හිස්තැනට සුදුසු ක්‍රියාපදය තෝරන්න: 'A small bird can ___ high.'"
                    } else {
                        "Verb scroll: Choose the correct base verb: 'A small bird can ___ high in the sky.'"
                    }
                    options = listOf("fly", "flying", "flies", "flew")
                    correctAnswer = 0
                    hint = if (isSinhala) "'can' යන පදයෙන් පසුව ක්‍රියාපදයේ මූලික ස්වභාවය යෙදේ." else "After 'can', use the base form of the verb."
                }
                else -> {
                    questionText = if (isSinhala) {
                        "බහුවචන සොයන්න: 'Book' යන පදයේ බහුවචන (Plural) ස්වරූපය කුමක්ද?"
                    } else {
                        "Plurals: What is the plural form of the word 'book'?"
                    }
                    options = listOf("Bookes", "Books", "Bookies", "Booking")
                    correctAnswer = 1
                    hint = if (isSinhala) "වචනයේ අගට සරලව 's' අකුර එකතු කරන්න." else "Simply add an 's' to the end of the word."
                }
            }
        } else if (grade in 4..6) {
            when (index % 5) {
                0 -> {
                    questionText = if (isSinhala) {
                        "නාමවිශේෂණයක් (Adjective) සොයන්න: 'The mysterious wizard gave me a scroll.' මෙහි නාමවිශේෂණය කුමක්ද?"
                    } else {
                        "Find the Adjective: 'The mysterious wizard gave us a potion.' Which word is the adjective?"
                    }
                    options = listOf("wizard", "gave", "mysterious", "potion")
                    correctAnswer = 2
                    hint = if (isSinhala) "විජ්ජාකාරයාගේ ස්වභාවය විස්තර කරන වචනය." else "It describes the properties of the wizard."
                }
                1 -> {
                    questionText = if (isSinhala) {
                        "බහුවචන නාමපද: 'Wolf' (වෘකයා) යන වචනයේ නිවැරදි බහුවචන ස්වරූපය කුමක්ද?"
                    } else {
                        "Irregular Plurals: What is the plural form of the forest creature 'wolf'?"
                    }
                    options = listOf("Wolfs", "Wolves", "Wolvies", "Wolfen")
                    correctAnswer = 1
                    hint = if (isSinhala) "'f' වලින් අවසන් වන වචන බහුවචන කිරීමේදී 'ves' එකතු වේ." else "Nouns ending in 'f' usually change to 'ves' in plural."
                }
                2 -> {
                    questionText = if (isSinhala) {
                        "නිපාත පද (Prepositions): 'The treasure map is hidden ___ the ancient chest.'"
                    } else {
                        "Prepositions: 'The gold key is hidden ___ the ancient wooden chest.'"
                    }
                    options = listOf("on", "inside", "over", "between")
                    correctAnswer = 1
                    hint = if (isSinhala) "කාසි පෙට්ටිය තුල යන්න හඟවන පදයයි." else "It means the key is within the walls of the chest."
                }
                3 -> {
                    questionText = if (isSinhala) {
                        "ක්‍රියා කාල අවස්ථා: 'They ___ to the temple yesterday.' හිස්තැනට සුදුසු ක්‍රියා පදය කුමක්ද?"
                    } else {
                        "Tenses: 'We ___ to the majestic castle yesterday.' Which verb fits best?"
                    }
                    options = listOf("go", "gone", "went", "going")
                    correctAnswer = 2
                    hint = if (isSinhala) "'yesterday' යනු අතීත කාලයයි." else "'Yesterday' represents a past event."
                }
                else -> {
                    questionText = if (isSinhala) {
                        "සන්ධාන පද (Conjunctions): 'He was very tired, ___ he continued his quest.'"
                    } else {
                        "Conjunctions: 'He was very tired, ___ he continued his quest.'"
                    }
                    options = listOf("but", "or", "because", "so")
                    correctAnswer = 0
                    hint = if (isSinhala) "පරස්පර කරුණු දෙකක් සම්බන්ධ කිරීමට යොදන පදයකි." else "Connects two contrasting statements."
                }
            }
        } else if (grade in 7..9) {
            when (index % 5) {
                0 -> {
                    questionText = if (isSinhala) {
                        "න්‍යායාත්මක කර්තෘකාරක / කර්මකාරක: 'The wizard cast the spell.' යන වාක්‍යයේ කර්ම කාරකය (Passive form) කුමක්ද?"
                    } else {
                        "Passive Voice: What is the passive form of 'The wizard cast the spell'?"
                    }
                    options = if (isSinhala) {
                        listOf(
                            "The spell is cast by the wizard.",
                            "The spell was cast by the wizard.",
                            "The spell casted by the wizard.",
                            "Wizard did cast the spell."
                        )
                    } else {
                        listOf(
                            "The spell was cast by the wizard.",
                            "The spell is cast by the wizard.",
                            "The spell casted by the wizard.",
                            "The wizard was casting the spell."
                        )
                    }
                    correctAnswer = 0
                    hint = if (isSinhala) "'cast' යනු අතීත කාල ක්‍රියාපදයයි. එබැවින් 'was cast' භාවිත වේ." else "'cast' is past form, hence use 'was cast'."
                }
                1 -> {
                    questionText = if (isSinhala) {
                        "නිවැරදි ඉංග්‍රීසි අක්ෂර වින්‍යාසය (Spell check) තෝරන්න:"
                    } else {
                        "Runic Orthography: Which option represents the correct spelling?"
                    }
                    options = listOf("Medievall", "Mideval", "Medieval", "Medeval")
                    correctAnswer = 2
                    hint = if (isSinhala) "මධ්‍යතන යුගය හඳුන්වන ඉංග්‍රීසි වචනයයි." else "Relating to the Middle Ages."
                }
                2 -> {
                    questionText = if (isSinhala) {
                        "රූඩි පද (Idioms): 'A piece of cake' යන්නෙන් අදහස් වන සැබෑ අරුත කුමක්ද?"
                    } else {
                        "Idioms: What does the idiom 'A piece of cake' mean?"
                    }
                    options = if (isSinhala) {
                        listOf("බොහෝ රසවත් කෑමක්", "ඉතා පහසු කටයුත්තක් (Very easy)", "කටුක සටනක්", "නොමිලේ ලැබෙන තෑග්ගක්")
                    } else {
                        listOf("A delicious dessert", "A very easy task", "A challenging battle", "A free item")
                    }
                    correctAnswer = 1
                    hint = if (isSinhala) "විභාගය ඉතා ලෙහෙසි වූ බව පැවසීමට මෙය යොදා ගනී." else "Something simple to complete."
                }
                3 -> {
                    questionText = if (isSinhala) {
                        "නිවැරදි සම්බන්ධක සර්වනාමය (Relative Pronoun) තෝරන්න: 'The knight ___ won the battle was rewarded.'"
                    } else {
                        "Relative Pronouns: 'The elven knight ___ won the sword duel was rewarded.'"
                    }
                    options = listOf("who", "which", "whom", "whose")
                    correctAnswer = 0
                    hint = if (isSinhala) "පුද්ගලයන්ට ආදේශ කිරීමට 'who' යොදයි." else "Use 'who' for describing people/individuals."
                }
                else -> {
                    questionText = if (isSinhala) {
                        "වාක්‍ය සම්පූර්ණ කිරීම: 'If I had a key, I ______ open the room.'"
                    } else {
                        "Conditionals: Complete the clause: 'If I had a golden key, I ______ open the portal.'"
                    }
                    options = listOf("will", "would", "shall", "must")
                    correctAnswer = 1
                    hint = if (isSinhala) "අතීත කාල කොන්දේසියක් (Conditional type 2) සඳහා 'would' යොදයි." else "Type 2 conditional uses 'would' in the main clause."
                }
            }
        } else {
            // Grade 10: Complex exam level English
            when (index % 5) {
                0 -> {
                    questionText = if (isSinhala) {
                        "පහත දැක්වෙන වාක්‍යයන් අතරින් Type-3 කොන්දේසි සහිත වාක්‍යය (Type-3 Conditional) කුමක්ද?"
                    } else {
                        "Identify the Type-3 Conditional sentence from the options:"
                    }
                    options = if (isSinhala) {
                        listOf(
                            "If I study, I will pass the exam.",
                            "If I studied, I would pass the exam.",
                            "If I had studied, I would have passed the exam.",
                            "If I study, I pass."
                        )
                    } else {
                        listOf(
                            "If I had studied the scrolls, I would have passed.",
                            "If I study the scrolls, I will pass the trial.",
                            "If I studied the scrolls, I would pass the trial.",
                            "If I study, I pass."
                        )
                    }
                    correctAnswer = 0
                    hint = if (isSinhala) "Type-3 සූත්‍රය: If + past perfect, would have + past participle." else "Uses 'had + past participle' and 'would have + past participle'."
                }
                1 -> {
                    questionText = if (isSinhala) {
                        "වාක්‍ය ඛණ්ඩ විශ්ලේෂණය: 'The map, which was ancient, showed the treasure.' මෙහි ඇති 'which was ancient' කුමන ගණයේ වාක්‍ය ඛණ්ඩයක්ද?"
                    } else {
                        "Clause Analysis: In the sentence 'The scroll, which was ancient, burned.', what clause is 'which was ancient'?"
                    }
                    options = if (isSinhala) {
                        listOf("නාමවිශේෂණ වාක්‍ය ඛණ්ඩය (Adjective Clause)", "නාමපද වාක්‍ය ඛණ්ඩය", "ක්‍රියාවිශේෂණ වාක්‍ය ඛණ්ඩය", "ප්‍රධාන වාක්‍ය ඛණ්ඩය")
                    } else {
                        listOf("Relative / Adjective Clause", "Noun Clause", "Adverbial Clause", "Main Clause")
                    }
                    correctAnswer = 0
                    hint = if (isSinhala) "මෙමඟින් මඟපෙන්වන 'map' (නාමපදය) විස්තර කරයි." else "It describes the noun 'scroll'."
                }
                2 -> {
                    questionText = if (isSinhala) {
                        "සමාන අර්ථ ඇති පද: 'valiant' (නිර්භීත) යන වචනයේ සමාන පදය කුමක්ද?"
                    } else {
                        "Synonyms: What is the most appropriate synonym for the word 'valiant'?"
                    }
                    options = listOf("Cowardly", "Cunning", "Courageous", "Timid")
                    correctAnswer = 2
                    hint = if (isSinhala) "'courageous' යනු නිර්භීතභාවය හඟවන තවත් පදයකි." else "Having or showing courage."
                }
                3 -> {
                    questionText = if (isSinhala) {
                        "වක්‍ර කථනය (Indirect Speech): 'I am writing a spell,' said the wizard. මෙහි නිවැරදි වක්‍ර කථනය කුමක්ද?"
                    } else {
                        "Reported Speech: 'I am crafting a potion,' said the wizard. Choose the reported speech equivalent:"
                    }
                    options = if (isSinhala) {
                        listOf(
                            "The wizard said that he was writing a spell.",
                            "The wizard said that he is writing a spell.",
                            "The wizard said he writes a spell.",
                            "The wizard told that he was writing a spell."
                        )
                    } else {
                        listOf(
                            "The wizard said that he was crafting a potion.",
                            "The wizard said that he is crafting a potion.",
                            "The wizard said he has crafted a potion.",
                            "The wizard told that he has been crafting."
                        )
                    }
                    correctAnswer = 0
                    hint = if (isSinhala) "වක්‍ර කථනය කිරීමේදී Simple Present පදය Simple Past බවට පත් වේ." else "In reported speech, Present Continuous changes to Past Continuous."
                }
                else -> {
                    questionText = if (isSinhala) {
                        "ඉහළ මට්ටමේ වාග් මාලාව: 'He took an oath of allegiance to the king.' මෙහි 'allegiance' යන්නෙහි තේරුම කුමක්ද?"
                    } else {
                        "Advanced Vocabulary: What does 'allegiance' mean in 'They swore allegiance to the Crown'?"
                    }
                    options = if (isSinhala) {
                        listOf("වෛරය", "විශ්වාසවන්තභාවය (Loyalty/Commitment)", "කාලීන අවස්ථාව", "නොසැලකිලිමත්කම")
                    } else {
                        listOf("Rebellion", "Hostility", "Loyalty or commitment", "indifference")
                    }
                    correctAnswer = 2
                    hint = if (isSinhala) "රජු කෙරෙහි භක්තිය හෝ පක්ෂපාතීත්වයයි." else "Loyalty to a government, ruler, or cause."
                }
            }
        }

        return Question(
            question = questionText,
            options = options,
            correctAnswer = correctAnswer,
            grade = grade,
            subject = "English",
            difficulty = difficulty,
            hint = hint
        )
    }

    // 4. HISTORY Offline Question Engine
    private fun generateHistoryQuestion(grade: Int, index: Int, difficulty: String, isSinhala: Boolean): Question {
        val questionText: String
        val options: List<String>
        val correctAnswer: Int
        val hint: String

        if (grade in 1..3) {
            when (index % 5) {
                0 -> {
                    questionText = if (isSinhala) {
                        "පුරාවෘත්ත: ලක්දිව අතීත රජකුට අයත් වූ බව කියන 'දඬුමොනර' (flying machine) කුමක්ද?"
                    } else {
                        "Legends: Which mythical Sri Lankan king is associated with the 'Dandu Monara' flying machine?"
                    }
                    options = if (isSinhala) {
                        listOf("කාශ්‍යප රජු", "රාවණා රජු (King Ravana)", "මහාසේන රජු", "විජය රජු")
                    } else {
                        listOf("King Kasyapa", "King Ravana", "King Mahasen", "King Vijaya")
                    }
                    correctAnswer = 1
                    hint = if (isSinhala) "භාරත වංශ කතාවල ද සඳහන් ප්‍රබල රජෙකි." else "A powerful mythological king referenced in Ramayana legends."
                }
                1 -> {
                    questionText = if (isSinhala) {
                        "ලක්දිව පිහිටි ඓතිහාසික, පූජනීය 'ජය ශ්‍රී මහා බෝධින් වහන්සේ' රෝපණය කර තිබෙන්නේ කොහිද?"
                    } else {
                        "Holy sites: In which historic Sri Lankan city is the sacred Jaya Sri Maha Bodhi tree planted?"
                    }
                    options = if (isSinhala) {
                        listOf("මහනුවර", "අනුරාධපුරය (Anuradhapura)", "පොළොන්නරුව", "කොළඹ")
                    } else {
                        listOf("Kandy", "Anuradhapura", "Polonnaruwa", "Colombo")
                    }
                    correctAnswer = 1
                    hint = if (isSinhala) "ලක්දිව ප්‍රථම රාජධානිය පිහිටි පුරාණ නගරයයි." else "The ancient city where Sri Lanka's first kingdom was established."
                }
                2 -> {
                    questionText = if (isSinhala) {
                        "ඓතිහාසික වාර්තාවලට අනුව ලංකාවේ ප්‍රථම රජතුමා ලෙස සලකන්නේ කවුරුන්ද?"
                    } else {
                        "Founding Chronicles: Who is considered the first king of Sri Lanka according to Mahavamsa?"
                    }
                    options = if (isSinhala) {
                        listOf("පණ්ඩුකාභය රජු", "විජය රජු (King Vijaya)", "දුටුගැමුණු රජු", "කාශ්‍යප රජු")
                    } else {
                        listOf("King Pandukabhaya", "King Vijaya", "King Dutugemunu", "King Kasyapa")
                    }
                    correctAnswer = 1
                    hint = if (isSinhala) "භාරතයේ සිට පැමිණි විජය කුමාරයායි." else "The prince who arrived from India with 700 followers."
                }
                3 -> {
                    questionText = if (isSinhala) {
                        "පුරාණ ශ්‍රී ලංකාවේ මහජනතාව සඳහා ඉදි කෙරුණු 'වැව්' (Tanks) වල ප්‍රධාන ප්‍රයෝජනය කුමක්ද?"
                    } else {
                        "Ancient Reservoirs: What was the main purpose of building 'Wewa' (irrigation tanks) in old Sri Lanka?"
                    }
                    options = if (isSinhala) {
                        listOf("කුරුල්ලන් බැලීමට", "කෘෂිකර්මාන්තයට ජලය සැපයීමට", "නැව් සංචාර සඳහා", "දේශසීමා සටන්වලට")
                    } else {
                        listOf("Watching water birds", "Storing water for agriculture", "Sailing ships", "Defense battles")
                    }
                    correctAnswer = 1
                    hint = if (isSinhala) "ගොවිතැනට හා වගාවලට ජලය රැස් කර තබා ගැනීමටයි." else "Vital for growing rice and paddy fields."
                }
                else -> {
                    questionText = if (isSinhala) {
                        "පහත දැක්වෙන අපේ රටේ සංකේත අතුරින් රාජ්‍ය ලාංඡනයට ඇතුලත් සිව්පාවා කවුද?"
                    } else {
                        "State Symbol: Which majestic animal is featured prominently on the Sri Lankan national flag?"
                    }
                    options = if (isSinhala) {
                        listOf("අලියා", "සිංහයා (Lion)", "මුවා", "කොටියා")
                    } else {
                        listOf("Elephant", "Lion", "Deer", "Leopard")
                    }
                    correctAnswer = 1
                    hint = if (isSinhala) "යටත් නොවන, ශක්තිමත් සිංහ ස්වරූපයයි." else "Symbolizes bravery and strength."
                }
            }
        } else if (grade in 4..6) {
            when (index % 5) {
                0 -> {
                    questionText = if (isSinhala) {
                        "සීගිරිය බලකොටුව කාශ්‍යප රජතුමා විසින් ඉදිකරන ලද්දේ කුමන වන මෘගයෙකුගේ හැඩයටද?"
                    } else {
                        "Sigiriya, the sky fortress, was designed by King Kasyapa in the shape of which animal's paws?"
                    }
                    options = if (isSinhala) {
                        listOf("අලියා", "සිංහයා (Lion)", "කඳුළු මකරා", "මොනරා")
                    } else {
                        listOf("Elephant", "Lion", "Dragon", "Peacock")
                    }
                    correctAnswer = 1
                    hint = if (isSinhala) "මෙය සිංහ පර්වතය ලෙස ද හඳුන්වයි." else "Known globally as the Lion Rock."
                }
                1 -> {
                    questionText = if (isSinhala) {
                        "පොළොන්නරුව රාජධානියේ නිල මහා ජලාශය වන 'පරාක්‍රම සමුද්‍රය' කරවූ රජතුමා කවුද?"
                    } else {
                        "Which king constructed the massive Parakrama Samudra reservoir in Polonnaruwa?"
                    }
                    options = if (isSinhala) {
                        listOf("පරාක්‍රමබාහු රජු (King Parakramabahu)", "ධාතුසේන රජු", "පාණ්න්ඪ රජු", "විජයබාහු රජු")
                    } else {
                        listOf("King Parakramabahu", "King Dhatusena", "King Pandic", "King Vijayabahu")
                    }
                    correctAnswer = 0
                    hint = if (isSinhala) "'අහසින් වැටෙන කිසිදු දිය බිඳක් ප්‍රයෝජනයට නොගෙන මුහුදට ගලා යාමට නොදෙන්න' යන්න එතුමාගේ මතයයි." else "He declared that not a single drop of rain should flow to the sea unused."
                }
                2 -> {
                    questionText = if (isSinhala) {
                        "ධාතුසේන රජතුමා විසින් ඉදි කරවු අති විශාල වාරි තාක්ෂණයේ සංකේතයක් බඳු වැව කුමක්ද?"
                    } else {
                        "Which famous ancient reservoir was constructed by King Dhatusena in Anuradhapura?"
                    }
                    options = if (isSinhala) {
                        listOf("පරාක්‍රම සමුද්‍රය", "කලා වැව (Kala Wewa)", "මින්නේරිය වැව", "තිසා වැව")
                    } else {
                        listOf("Parakrama Samudra", "Kala Wewa", "Minneriya Wewa", "Tissa Wewa")
                    }
                    correctAnswer = 1
                    hint = if (isSinhala) "මෙය මහා අවුකන බුදු පිළිම වහන්සේ අසල ජලය සපයන වැවයි." else "Associated with feeding Yoda Ela canal."
                }
                3 -> {
                    questionText = if (isSinhala) {
                        "මිහිඳු මහ රහතන් වහන්සේ ලක්දිවට බුදු දහම දායාද කරන විට රට පාලනය කල රජු කවුද?"
                    } else {
                        "Who was the king of Sri Lanka when Buddhism was officially introduced by Arahat Mahinda?"
                    }
                    options = if (isSinhala) {
                        listOf("දුටුගැමුණු රජු", "දේවානම්පියතිස්ස රජු (King Devanampiyatissa)", "කාශ්‍යප රජු", "මහාසේන රජු")
                    } else {
                        listOf("King Dutugemunu", "King Devanampiyatissa", "King Kasyapa", "King Mahasen")
                    }
                    correctAnswer = 1
                    hint = if (isSinhala) "මිහින්තලා සෙල් පර්වතය අසලදී මුව දඩයමේ ගිය රජුය." else "He met Arahat Mahinda while hunting deer at Mihintale."
                }
                else -> {
                    questionText = if (isSinhala) {
                        "ලංකාවේ වැව් බැඳි රජතුමා ලෙස හැඳින්වෙන මින්නේරිය වැව කරවූ රජතුමා කවුද?"
                    } else {
                        "Which king, known as 'Minneri Deviyo' (the God of Minneriya), built the giant Minneriya Wewa?"
                    }
                    options = if (isSinhala) {
                        listOf("ධාතුසේන රජු", "මහාසේන රජු (King Mahasen)", "පළමුවන විජයබාහු", "පණ්ඩුකාභය")
                    } else {
                        listOf("King Dhatusena", "King Mahasen", "King Vijayabahu I", "King Pandukabhaya")
                    }
                    correctAnswer = 1
                    hint = if (isSinhala) "එතුමා වැව් 16 ක් සහ විශාල ඇළ මාර්ග කරවීය." else "He built 16 massive tanks and canals."
                }
            }
        } else if (grade in 7..9) {
            when (index % 5) {
                0 -> {
                    questionText = if (isSinhala) {
                        "දේශීය ඉතිහාසය: යුරෝපීය ජාතිකයන් අතරින් 1505 දී ප්‍රථමයෙන් ලංකාවට ආ මුහුදු සංචාරකයින් කවුද?"
                    } else {
                        "Foreign Arrivals: Which European power was the first to arrive in Sri Lanka in 1505?"
                    }
                    options = if (isSinhala) {
                        listOf("ලන්දේසීන්", "පෘතුගීසීන් (Portuguese)", "බ්‍රිතාන්‍යයන්", "ප්‍රංශ ජාතිකයන්")
                    } else {
                        listOf("Dutch", "Portuguese", "British", "French")
                    }
                    correctAnswer = 1
                    hint = if (isSinhala) "ලොරෙන්සෝ ද අල්මේදා (Lorenzo de Almeida) කොළඹ වරායට පැමිණියේය." else "Led by Lourenço de Almeida into Colombo harbor."
                }
                1 -> {
                    questionText = if (isSinhala) {
                        "ලක්දිව සොළී ආධිපත්‍යයෙන් මුදාගෙන අනුරාධපුරයෙන් පසු පොළොන්නරුව පළමු අගනුවර කරගත් රජු කවුද?"
                    } else {
                        "Liberation Wars: Which king defeated the Cholas and established Polonnaruwa as the capital?"
                    }
                    options = if (isSinhala) {
                        listOf("පළමුවන මහින්ද", "පළමුවන විජයබාහු රජු (King Vijayabahu I)", "පළමුවන පරාක්‍රමබාහු", "කීර්ති ශ්‍රී රාජසිංහ")
                    } else {
                        listOf("King Mahinda I", "King Vijayabahu I", "King Parakramabahu I", "King Rajasinghe")
                    }
                    correctAnswer = 1
                    hint = if (isSinhala) "එතුමා සොළී සේනාව පලවා හැරීමට වසර 17 ක් සටන් කළේය." else "He fought a 17-year campaign to restore sovereignty."
                }
                2 -> {
                    questionText = if (isSinhala) {
                        "පෘතුගීසීන්ට එරෙහිව දැඩි සටනක් මෙහෙයවා මුල්ලේරියා වෙල් සටන ජයගත් ලාංකීය රජතුමා කවුද?"
                    } else {
                        "Famous Battles: Which king won the battle of Mulleriyawa against the Portuguese army?"
                    }
                    options = if (isSinhala) {
                        listOf("කීර්ති ශ්‍රී රාජසිංහ", "සීතාවක රාජසිංහ රජු (Tikiri Bandara)", "ශ්‍රී වික්‍රම රාජසිංහ", "විමලධර්මසූරිය")
                    } else {
                        listOf("King Rajasinghe II", "King Rajasinghe I (Tikiri Bandara)", "King Sri Vikrama Rajasinghe", "King Wimaladharmasuriya I")
                    }
                    correctAnswer = 1
                    hint = if (isSinhala) "එතුමා ටිකිරි බණ්ඩාර කුමාරයා ලෙස ද හැඳින්විය." else "Also known as Prince Tikiri Bandara."
                }
                3 -> {
                    questionText = if (isSinhala) {
                        "ලන්දේසි (Dutch) පාලන සමයේ ලංකාවේ මුහුදුබඩ ප්‍රදේශවල අලෙවි කල ප්‍රධාන ආර්ථික බෝගය කුමක්ද?"
                    } else {
                        "Colonial Trade: Which valuable spice was monopoly-regulated by the Dutch VOC in coastal Ceylon?"
                    }
                    options = if (isSinhala) {
                        listOf("තේ", "කුරුඳු (Cinnamon)", "කෝපි", "ගම්මිරිස්")
                    } else {
                        listOf("Tea", "Cinnamon", "Coffee", "Black Pepper")
                    }
                    correctAnswer = 1
                    hint = if (isSinhala) "සුවඳවත් වටිනා පොත්තක් සහිත බෝගයකි." else "A highly fragrant bark spice."
                }
                else -> {
                    questionText = if (isSinhala) {
                        "ලක්දිව සතුරු ආක්‍රමණයකින් බේරාගෙන මහා වෙහෙරක් වන 'රුවන්වැලි සෑය' කරවූ නරපතියා කවුද?"
                    } else {
                        "Noble Architecture: Which heroic king defeated Elara and built the Ruwanweli Maha Seya stupa?"
                    }
                    options = if (isSinhala) {
                        listOf("සද්ධාතිස්ස රජු", "දුටුගැමුණු රජු (King Dutugemunu)", "වළගම්බා රජු", "පරාක්‍රමබාහු")
                    } else {
                        listOf("King Saddhatissa", "King Dutugemunu", "King Valagamba", "King Parakramabahu")
                    }
                    correctAnswer = 1
                    hint = if (isSinhala) "එතුමාගේ සන්තකයේ කන්ඩුල නම් මහා ඇතෙක් සිටියේය." else "His legendary royal elephant was Kadol."
                }
            }
        } else {
            // Grade 10: Complex exam level history (British Ceylon rule, independent Ceylon constitutional reforms)
            when (index % 5) {
                0 -> {
                    questionText = if (isSinhala) {
                        "නූතන ඉතිහාසය: බ්‍රිතාන්‍ය ලංකාවේ (British Ceylon) ව්‍යවස්ථා ප්‍රතිසංස්කරණ ආරම්භ කරවමින් 1833 දී ගෙන ආ කොමිසම කුමක්ද?"
                    } else {
                        "Constitutional Reforms: Which commission initiated major administrative reforms in Ceylon in 1833?"
                    }
                    options = if (isSinhala) {
                        listOf(
                            "සෝල්බරි කොමිසම (Soulbury)",
                            "ඩොනමෝර් කොමිසම (Donoughmore)",
                            "කෝල්බෲක්-කැමරන් කොමිසම (Colebrooke-Cameron)",
                            "මැනිං-ඩෙවොන්ෂයර් කොමිසම"
                        )
                    } else {
                        listOf(
                            "Soulbury Commission",
                            "Donoughmore Commission",
                            "Colebrooke-Cameron Commission",
                            "Manning-Devonshire Commission"
                        )
                    }
                    correctAnswer = 2
                    hint = if (isSinhala) "රාජකාරි ක්‍රමය අහෝසි කර ලංකාව පළාත් 5 කට බෙදුවේ මේ මඟිනි." else "Abolished the Rajakariya system and introduced a 5-province structure."
                }
                1 -> {
                    questionText = if (isSinhala) {
                        "බ්‍රිතාන්‍යයන්ට එරෙහිව ඌව වෙල්ලස්සේ ඇතිවූ 1818 මහා කැරැල්ල මෙහෙයවූ කැරලි නායකයා කවුද?"
                    } else {
                        "Great Rebellions: Who led the historic 1818 Great Rebellion in Uva against British occupation?"
                    }
                    options = if (isSinhala) {
                        listOf("ගොන්ගාලේගොඩ බණ්ඩා", "කැප්පෙටිපොළ දිසාවේ (Keppetipola)", "වීර පුරන් අප්පු", "ඇහැලේපොළ අදිකාරම්")
                    } else {
                        listOf("Gongalegoda Banda", "Monarawila Keppetipola Disawe", "Veera Puran Appu", "Ehelapola Nilame")
                    }
                    correctAnswer = 1
                    hint = if (isSinhala) "එතුමා බ්‍රිතාන්‍යයන්ගෙන් ලැබුණු නිල තනතුරු ප්‍රතික්ෂේප කර රට වෙනුවෙන් සටන් වැදුණි." else "He returned the British commission to fight alongside patriots."
                }
                2 -> {
                    questionText = if (isSinhala) {
                        "ලංකා ඉතිහාසයේ බ්‍රිතාන්‍යයන් සමඟ උඩරට ප්‍රධානීන් අත්සන් කල 'උඩරට ගිවිසුම' (Kandyan Convention) සිදු වූ වර්ෂය?"
                    } else {
                        "Kandyan Kingdom Collapse: In which year did the Kandyan chiefs sign the Kandyan Convention with the British?"
                    }
                    options = listOf("1796", "1802", "1815", "1818")
                    correctAnswer = 2
                    hint = if (isSinhala) "උඩරට රාජධානිය බ්‍රිතාන්‍ය කිරීටයට යටත් වූ අවුරුද්දයි." else "The year Sri Lanka's direct monarchy ended completely."
                }
                3 -> {
                    questionText = if (isSinhala) {
                        "වැවිලි කර්මාන්තය: 19 වන සියවසේදී කෝෆි වසංගතයෙන් පසු ලාංකීය ආර්ථිකයේ ප්‍රධාන අපනයන වැවිලිය බවට පත් වූයේ කුමක්ද?"
                    } else {
                        "Economic Shifts: Which cash crop replaced devastated coffee clearings to become Ceylon's primary export in the late 19th century?"
                    }
                    options = if (isSinhala) {
                        listOf("තේ (Tea)", "රබර්", "පොල්", "දුම්කොළ")
                    } else {
                        listOf("Tea", "Rubber", "Coconut", "Spices")
                    }
                    correctAnswer = 0
                    hint = if (isSinhala) "ජේම්ස් ටේලර් විසින් ලූලා කඳුර වත්තේ පළමුවෙන් වගා කල බෝගයයි." else "Introduced commercialized planting by James Taylor on Loolecondera estate."
                }
                else -> {
                    questionText = if (isSinhala) {
                        "ශ්‍රී ලංකාව බ්‍රිතාන්‍ය අධිරාජ්‍යයෙන් නිදහස ලබා සෝල්බරි ව්‍යවස්ථාව යටතේ ඩොමීනියන් තත්ත්වය ලැබුවේ කවදාද?"
                    } else {
                        "Independence: When did Sri Lanka officially gain independence from British colonial rule?"
                    }
                    options = listOf("February 4, 1948", "May 22, 1972", "February 4, 1956", "August 30, 1947")
                    correctAnswer = 0
                    hint = if (isSinhala) "ලංකාවේ ජාතික නිදහස් දිනය සමරන දිනයයි." else "The national day of celebration in Sri Lanka."
                }
            }
        }

        return Question(
            question = questionText,
            options = options,
            correctAnswer = correctAnswer,
            grade = grade,
            subject = "History",
            difficulty = difficulty,
            hint = hint
        )
    }

    // Generator function for bonus general knowledge / math/science/history questions (220 total)
    private fun generateBonusQuestion(grade: Int, subject: String, id: Int, isSinhala: Boolean): Question {
        val questionText: String
        val options: List<String>
        val correctAnswer: Int
        val hint: String

        when (id % 5) {
            0 -> {
                questionText = if (isSinhala) {
                    "බෝනස් ත්‍රිවියා: ලෝකයේ විශාලතම සාගරය (Ocean) වන්නේ පහත සඳහන් කුමන සාගරයද?"
                } else {
                    "Bonus Trivia: What is the largest and deepest ocean on our planet Earth?"
                }
                options = if (isSinhala) {
                    listOf("ඉන්දියානු සාගරය", "පැසිෆික් සාගරය (Pacific Ocean)", "අත්ලන්තික් සාගරය", "නක්කුල්ස් මුහුද")
                } else {
                    listOf("Indian Ocean", "Pacific Ocean", "Atlantic Ocean", "Arctic Ocean")
                }
                correctAnswer = 1
                hint = if (isSinhala) "ලොව විශාලතම ජල මස්කන්ධයයි." else "Contains the Mariana Trench, the deepest point on Earth."
            }
            1 -> {
                questionText = if (isSinhala) {
                    "බෝනස් ගණිතය: $id x 100 හි ඵලයෙහි අගය කුමක්ද?"
                } else {
                    "Bonus Math: What is the result of multiplying $id by 100?"
                }
                options = listOf("${id}0", "${id}00", "${id + 100}", "${id}000")
                correctAnswer = 1
                hint = if (isSinhala) "කිසියම් සංඛ්‍යාවක් 100 න් ගුණ කිරීම යනු අගට බිංදු 2 ක් එකතු කිරීමයි." else "Append two zeros to the number."
            }
            2 -> {
                questionText = if (isSinhala) {
                    "බෝනස් විද්‍යාව: සූර්ය ලෝකයේ ඇති ග්‍රහලෝක අතරින් තුන්වන ග්‍රහලෝකය කුමක්ද?"
                } else {
                    "Bonus Astronomy: Which planet resides as the third celestial world from the Sun in our Solar System?"
                }
                options = if (isSinhala) {
                    listOf("පෘථිවිය (Earth)", "අඟහරු", "බුධ", "සිකුරු")
                } else {
                    listOf("Earth", "Mars", "Mercury", "Venus")
                }
                correctAnswer = 0
                hint = if (isSinhala) "ජීවය පවතින එකම නිල් පැහැති ග්‍රහලෝකයයි." else "Our home planet where water covers 71%."
            }
            3 -> {
                questionText = if (isSinhala) {
                    "බෝනස් ඉංග්‍රීසි: 'An apple a day keeps the ______ away.' සම්පූර්ණ කරන්න."
                } else {
                    "Bonus English: Complete the classic proverb: 'An apple a day keeps the ______ away.'"
                }
                options = listOf("teacher", "doctor", "dragon", "goblin")
                correctAnswer = 1
                hint = if (isSinhala) "සෞඛ්‍ය සම්පන්න සිරුරක් ඇති විට මොහු හමුවීමට යාම අවශ්‍ය නොවේ." else "A health professional who heals knights and citizens."
            }
            else -> {
                questionText = if (isSinhala) {
                    "බෝනස් ඉතිහාසය: පුරාණ හෙළයේ 'වැව් දහසක්' බැඳි රජු ලෙස ජනප්‍රිය මහා පරාක්‍රමබාහු රජුගේ මුත්තණුවන් වන්නේ කවුද?"
                } else {
                    "Bonus History: Sri Lanka's historic flag has a lion holding what weapon?"
                }
                options = if (isSinhala) {
                    listOf("දුනු-ඊතල", "කඩුව (Sword)", "පලිහ", "මුගුර")
                } else {
                    listOf("Bow and arrow", "Sword", "Shield", "Spear")
                }
                correctAnswer = 1
                hint = if (isSinhala) "ලාංකීය ජාතික කොඩියේ සිංහයාගේ සුරතේ ඇත්තේ ද මෙයයි." else "A sharp bladed medieval weapon of honor."
            }
        }

        return Question(
            question = questionText,
            options = options,
            correctAnswer = correctAnswer,
            grade = grade,
            subject = subject,
            difficulty = "medium",
            hint = hint
        )
    }
}
