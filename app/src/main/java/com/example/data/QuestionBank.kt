package com.example.data

object QuestionBank {

    fun getQuestions(grade: Int, subject: String, lang: String): List<Question> {
        val isSinhala = lang == "si"
        val normalizedSubject = when (subject.lowercase()) {
            "math", "mathematics", "ගණිතය" -> "Math"
            "science", "විද්‍යාව", "විද්යාව" -> "Science"
            "english", "ඉංග්‍රීසි", "ඉංග්රීසි" -> "English"
            "history", "ඉතිහාසය" -> "History"
            else -> "Math"
        }

        val list = mutableListOf<Question>()
        for (i in 0 until 10) {
            list.add(generateBuiltInQuestion(grade, normalizedSubject, i, isSinhala))
        }
        return list.shuffled() // Always shuffle built-in questions too!
    }

    private fun generateBuiltInQuestion(grade: Int, subject: String, idx: Int, isSinhala: Boolean): Question {
        val qid = "grade${grade}_${subject}_00${idx}"
        val difficulty = when {
            grade <= 3 -> "easy"
            grade <= 6 -> "medium"
            grade <= 9 -> "hard"
            else -> "expert"
        }

        return when (subject) {
            "Math" -> generateMathQuestion(grade, idx, qid, difficulty, isSinhala)
            "Science" -> generateScienceQuestion(grade, idx, qid, difficulty, isSinhala)
            "English" -> generateEnglishQuestion(grade, idx, qid, difficulty, isSinhala)
            else -> generateHistoryQuestion(grade, idx, qid, difficulty, isSinhala)
        }
    }

    private fun generateMathQuestion(grade: Int, idx: Int, qid: String, diff: String, isSinhala: Boolean): Question {
        // Deterministic but realistic math questions for Grade 1-10 (10 questions each)
        val qText: String
        val options: List<String>
        val correctIdx: Int
        val hint: String

        when (grade) {
            1 -> {
                // simple addition, subtraction, basic counting
                val val1 = idx + 2
                val val2 = idx + 1
                val sum = val1 + val2
                if (idx % 2 == 0) {
                    qText = if (isSinhala) "එකතු කරන්න: $val1 + $val2 කීයද?" else "Add: What is $val1 + $val2?"
                    correctIdx = 0
                    options = listOf("$sum", "${sum + 1}", "${sum - 1}", "${sum + 2}")
                    hint = if (isSinhala) "ඇඟිලි භාවිතයෙන් ගණන් කරන්න!" else "Use your fingers to count!"
                } else {
                    val subVal = val1 + val2
                    val rem = subVal - val1
                    qText = if (isSinhala) "අඩු කරන්න: $subVal - $val1 කීයද?" else "Subtract: What is $subVal - $val1?"
                    correctIdx = 1
                    options = listOf("${rem - 1}", "$rem", "${rem + 1}", "${rem + 2}")
                    hint = if (isSinhala) "මුළු එකතුවෙන් $val1 ක් ඉවත් කරන්න." else "Take $val1 away from $subVal."
                }
            }
            2 -> {
                // two-digit addition/subtraction, shapes
                if (idx % 2 == 0) {
                    val val1 = 10 + idx * 3
                    val val2 = 5 + idx * 2
                    val sum = val1 + val2
                    qText = if (isSinhala) "එකතු කරන්න: $val1 + $val2 කීයද?" else "Add: What is $val1 + $val2?"
                    correctIdx = 2
                    options = listOf("${sum - 2}", "${sum + 2}", "$sum", "${sum - 5}")
                    hint = if (isSinhala) "පළමුව එකස්ථාන එකතු කරන්න." else "Add the ones column first."
                } else {
                    val shapeNames = if (isSinhala) listOf("ත්‍රිකෝණය", "චතුරස්‍රය", "රවුම", "තාරකාව") else listOf("Triangle", "Square", "Circle", "Star")
                    val shapeSides = listOf(3, 4, 0, 5)
                    val chosenIdx = idx % 4
                    qText = if (isSinhala) "කාණ්ඩයේ සදහන් කරන ${shapeNames[chosenIdx]} හැඩයට පැති කීයක් තිබේද?" else "How many sides does a ${shapeNames[chosenIdx]} have?"
                    correctIdx = chosenIdx
                    options = shapeSides.map { it.toString() }
                    hint = if (isSinhala) "හැඩය මනසින් සිතා බලන්න." else "Visualise the boundaries of the shape."
                }
            }
            3 -> {
                // multiplication tables, fractions intro
                if (idx % 2 == 0) {
                    val base = (idx / 2) + 2
                    val mul = 5
                    val ans = base * mul
                    qText = if (isSinhala) "ගුණ කරන්න: $base x $mul කීයද?" else "Multiply: What is $base x $mul?"
                    correctIdx = 0
                    options = listOf("$ans", "${ans + 5}", "${ans - 5}", "${ans + 2}")
                    hint = if (isSinhala) "$mul වරක් වගුව මතකයට නඟන්න." else "Recall the multiplication table of $mul."
                } else {
                    val denom = (idx % 3) + 3
                    qText = if (isSinhala) "සමාන කොටස් $denom කට බෙදූ රූපයකින් 1ක් වර්ණ කළහොත් ලැබෙන භාගය කුමක්ද?" else "If a shape is cut into $denom equal parts and 1 is shaded, what is the fraction?"
                    correctIdx = 1
                    options = listOf("1/2", "1/$denom", "2/$denom", "$denom/1")
                    hint = if (isSinhala) "යටි අංකය මුළු කොටස් ගණනයි." else "The denominator represents the total equal parts."
                }
            }
            4 -> {
                // Division, decimals introduction, perimeters
                if (idx % 2 == 0) {
                    val div = 4
                    val ans = idx + 3
                    val dividend = ans * div
                    qText = if (isSinhala) "බෙදන්න: $dividend බෙදීම $div කීයද?" else "Divide: What is $dividend divided by $div?"
                    correctIdx = 2
                    options = listOf("${ans - 1}", "${ans + 1}", "$ans", "${ans + 2}")
                    hint = if (isSinhala) "$div වරක් කීයෙන්ද $dividend ලැබෙන්නේ?" else "How many times does $div go into $dividend?"
                } else {
                    val side = idx + 2
                    val peri = side * 4
                    qText = if (isSinhala) "පැත්තක දිග සෙන්ටිමීටර $side ක් වන සමචතුරස්‍රයක පරිමිතිය සොයන්න." else "Find the perimeter of a square with side length $side cm."
                    correctIdx = 0
                    options = listOf("$peri cm", "${peri + 2} cm", "${peri - 2} cm", "${peri * 2} cm")
                    hint = if (isSinhala) "සමචතුරස්‍රයට සමාන පැති 4ක් ඇත." else "A square has 4 equal sides."
                }
            }
            5 -> {
                // Fractions, percentages basics
                if (idx % 2 == 0) {
                    val percent = (idx + 1) * 10
                    val base = 200
                    val ans = (base * percent) / 100
                    qText = if (isSinhala) "$base න් $percent% ක් කීයද?" else "What is $percent% of $base?"
                    correctIdx = 3
                    options = listOf("${ans - 10}", "${ans + 10}", "${ans / 2}", "$ans")
                    hint = if (isSinhala) "$percent/100 ගුණ කිරීම $base ලෙස සලකන්න." else "Multiply $base by $percent and divide by 100."
                } else {
                    val den = 4
                    qText = if (isSinhala) "භාග එකතු කරන්න: 1/$den + 2/$den කීයද?" else "Add the fractions: 1/$den + 2/$den"
                    correctIdx = 1
                    options = listOf("1/$den", "3/$den", "4/$den", "2/$den")
                    hint = if (isSinhala) "හරයන් සමාන විට අංශ කොටස් එකතු කරන්න." else "When denominators match, add the numerators."
                }
            }
            6 -> {
                // Algebra introduction, geometry basics
                if (idx % 2 == 0) {
                    val add = idx + 5
                    val ans = idx + 10
                    val x = ans - add
                    qText = if (isSinhala) "x + $add = $ans නම්, x හි අගය සොයන්න." else "If x + $add = $ans, find the value of x."
                    correctIdx = 0
                    options = listOf("$x", "${x + 1}", "${x - 1}", "${x + 3}")
                    hint = if (isSinhala) "$add මුළු අගයෙන් අඩු කරන්න." else "Subtract $add from both sides."
                } else {
                    qText = if (isSinhala) "ත්‍රිකෝණයක අභ්‍යන්තර කෝණවල එකතුව අංශක කීයද?" else "What is the sum of interior angles in a triangle in degrees?"
                    correctIdx = 2
                    options = listOf("90", "120", "180", "360")
                    hint = if (isSinhala) "මෙය සරල රේඛාවක කෝණයට සමාන වේ." else "It equals the angle of a straight line."
                }
            }
            7 -> {
                // Linear equations, ratios
                if (idx % 2 == 0) {
                    val first = (idx + 1) * 5
                    val second = (idx + 1) * 10
                    qText = if (isSinhala) "සංසන්දනය $first:$second සරලම ආකාරයෙන් දක්වන්න." else "Simplify the ratio $first:$second to its simplest form."
                    correctIdx = 1
                    options = listOf("2:1", "1:2", "5:10", "3:4")
                    hint = if (isSinhala) "පැති දෙකම පොදු සාධකයෙන් බෙදන්න." else "Divide both parts by their greatest common factor."
                } else {
                    val mul = idx + 2
                    val ans = mul * 5
                    qText = if (isSinhala) "විසඳන්න: 5y = $ans නම්, y හි අගය කීයද?" else "Solve: If 5y = $ans, what is the value of y?"
                    correctIdx = 2
                    options = listOf("${mul - 1}", "${mul + 1}", "$mul", "${mul + 2}")
                    hint = if (isSinhala) "දෙපසම 5න් බෙදන්න." else "Divide both sides of the equation by 5."
                }
            }
            8 -> {
                // Quadratic basics, statistics
                if (idx % 2 == 0) {
                    val val1 = idx + 2
                    val sq = val1 * val1
                    qText = if (isSinhala) "$sq හි වර්ගමූලය (√$sq) කීයද?" else "What is the square root of $sq (√$sq)?"
                    correctIdx = 0
                    options = listOf("$val1", "${val1 + 1}", "${val1 - 1}", "${val1 * 2}")
                    hint = if (isSinhala) "කුමන සංඛ්‍යාව එම සංඛ්‍යාවෙන්ම ගුණ කළ විට $sq ලැබේද?" else "What number multiplied by itself yields $sq?"
                } else {
                    val a = 2
                    val b = idx + 3
                    val mean = (a + b) / 2.0
                    qText = if (isSinhala) "$a සහ $b හි සාමාන්‍යය මධ්‍යන්‍යය කොපමණද?" else "What is the average (mean) of $a and $b?"
                    correctIdx = 1
                    options = listOf("${mean - 0.5}", "$mean", "${mean + 0.5}", "${mean + 1.0}")
                    hint = if (isSinhala) "සංඛ්‍යා දෙක එකතු කර 2න් බෙදන්න." else "Add both numbers and divide by 2."
                }
            }
            9 -> {
                // Trigonometry basics, probability
                if (idx % 2 == 0) {
                    qText = if (isSinhala) "සෘජුකෝණී ත්‍රිකෝණයක sin(θ) සූත්‍රය කුමක්ද?" else "In a right-angled triangle, what is the formula for sin(θ)?"
                    correctIdx = 3
                    options = listOf(
                        "බද්ධ පැත්ත / කර්ණය (Adjacent / Hypotenuse)",
                        "සම්මුඛ පැත්ත / බද්ධ පැත්ත (Opposite / Adjacent)",
                        "කර්ණය / සම්මුඛ පැත්ත (Hypotenuse / Opposite)",
                        "සම්මුඛ පැත්ත / කර්ණය (Opposite / Hypotenuse)"
                    )
                    hint = if (isSinhala) "SOH-CAH-TOA නීතිය මතක තබා ගන්න!" else "Remember SOH-CAH-TOA!"
                } else {
                    val totalBalls = 10
                    val redBalls = (idx % 4) + 2
                    qText = if (isSinhala) "භාජනයක රතු බෝල $redBalls ක් සහ නිල් බෝල ${totalBalls - redBalls} ක් ඇත. රතු බෝලයක් ඇදීමේ සම්භාවිතාව කොපමණද?" else "A bag contains $redBalls red balls and ${totalBalls - redBalls} blue balls. What is the probability of drawing a red ball?"
                    correctIdx = 0
                    options = listOf("$redBalls/$totalBalls", "${totalBalls - redBalls}/$totalBalls", "1/2", "1/$redBalls")
                    hint = if (isSinhala) "රතු බෝල ගණන මුළු ප්‍රමාණයෙන් බෙදන්න." else "Divide the number of red balls by the total number of balls."
                }
            }
            else -> {
                // Grade 10: Advanced algebra, calculus intro
                if (idx % 2 == 0) {
                    // x^2 - 9 = 0
                    qText = if (isSinhala) "x² - 9 = 0 සමීකරණයේ ධන විසඳුම සොයන්න." else "Find the positive solution for the equation x² - 9 = 0."
                    correctIdx = 2
                    options = listOf("1", "2", "3", "9")
                    hint = if (isSinhala) "x² = 9 වේ, x හි අගය ගන්න." else "x² = 9, solve for positive x."
                } else {
                    qText = if (isSinhala) "y = x² ශ්‍රිතයේ x විෂයෙන් අවකලනය (dy/dx) කුමක්ද?" else "What is the derivative of y = x² with respect to x (dy/dx)?"
                    correctIdx = 1
                    options = listOf("x", "2x", "x²", "2")
                    hint = if (isSinhala) "අවකලන බල නීතිය d/dx(x^n) = n*x^(n-1) යොදන්න." else "Use the power rule of differentiation: d/dx(x^n) = n*x^(n-1)."
                }
            }
        }

        return Question(
            question = qText,
            options = options,
            correctAnswer = correctIdx,
            grade = grade,
            subject = "Mathematics",
            difficulty = diff,
            hint = hint
        )
    }

    private fun generateScienceQuestion(grade: Int, idx: Int, qid: String, diff: String, isSinhala: Boolean): Question {
        // Deterministic Science curricular questions for Grade 1-10
        val qText: String
        val options: List<String>
        val correctIdx: Int
        val hint: String

        when (grade) {
            1 -> {
                val animals = if (isSinhala) listOf("බල්ලා", "පත්තෑයා", "කුරුල්ලා", "මාළුවා") else listOf("Dog", "Centipede", "Bird", "Fish")
                val categories = if (isSinhala) listOf("ක්ෂීරපායී (ඇවිදින)", "කකුල් ගොඩක් ඇති", "පියාඹන", "පීනන") else listOf("walking mammal", "multilegged creeper", "flying animal", "swimming fish")
                val chosenIdx = idx % 4
                qText = if (isSinhala) "කාණ්ඩයෙන් ${categories[chosenIdx]} කවුද?" else "Which of these is a ${categories[chosenIdx]}?"
                correctIdx = chosenIdx
                options = animals
                hint = if (isSinhala) "සතා ජීවත්වන පරිසරය සිතන්න." else "Think about how each animal moves."
            }
            2 -> {
                val organs = if (isSinhala) listOf("ඇස්", "කන්", "නාසය", "දිව") else listOf("Eyes", "Ears", "Nose", "Tongue")
                val senses = if (isSinhala) listOf("දෘෂ්ටිය (දැකීම)", "ශ්‍රවණය (ඇසීම)", "ග්‍රහණය (සුවඳ)", "රස බැලීම") else listOf("vision (seeing)", "hearing (sounds)", "smelling (scents)", "tasting (flavors)")
                val chosenIdx = idx % 4
                qText = if (isSinhala) "මිනිස් සිරුරේ ${senses[chosenIdx]} සඳහා උදව් වන ඉන්ද්‍රිය කුමක්ද?" else "Which organ is responsible for ${senses[chosenIdx]}?"
                correctIdx = chosenIdx
                options = organs
                hint = if (isSinhala) "සංවේදන ක්‍රියාවලිය සිහිකරන්න." else "Think of what you use to do that action."
            }
            3 -> {
                if (idx % 2 == 0) {
                    qText = if (isSinhala) "පහත ප්‍රකාශ අතරින් බලයක් යෙදීමෙන් කළ හැක්කේ කුමක්ද?" else "Which of the following can be done by applying a force?"
                    correctIdx = 3
                    options = if (isSinhala) 
                        listOf("වස්තුවක හැඩය වෙනස් කිරීම පමණි", "වස්තුවක වේගය වෙනස් කිරීම පමණි", "වස්තුවක් නැවතීම පමණි", "ඉහත සියල්ලම")
                    else 
                        listOf("Change shape of an object only", "Change speed of an object only", "Stop a moving object only", "All of the above")
                    hint = if (isSinhala) "බලයක් මඟින් චලිතයට හා හැඩයට බොහෝ බලපෑම් කළ හැක." else "Force affects motion, direction, and shape."
                } else {
                    qText = if (isSinhala) "සරල යන්ත්‍රයක් භාවිතා කිරීමට ප්‍රධාන හේතුව කුමක්ද?" else "What is the primary benefit of using a simple machine?"
                    correctIdx = 0
                    options = if (isSinhala)
                        listOf("අඩු උත්සාහයකින් පහසුවෙන් වැඩ කිරීම", "වැඩේ සංකීර්ණ කිරීම", "බලය වැඩි කිරීම", "ශක්තිය මැනීම")
                    else
                        listOf("To make work easier with less effort", "To make work complex", "To destroy force", "To measure energy")
                    hint = if (isSinhala) "උදාහරණයක් ලෙස කප්පියක් හෝ කවටයක් සිතන්න." else "Think of how a pulley or inclined plane assists us."
                }
            }
            4 -> {
                if (idx % 2 == 0) {
                    qText = if (isSinhala) "විදුලිය සන්නයනය කරන ප්‍රධාන ලෝහයක් කුමක්ද?" else "Which material is an excellent conductor of electricity?"
                    correctIdx = 2
                    options = if (isSinhala) listOf("ප්ලාස්ටික්", "ලී", "තඹ ලෝහය", "වීදුරු") else listOf("Plastic", "Wood", "Copper", "Glass")
                    hint = if (isSinhala) "විදුලි රැහැන් සෑදීමට මෙය ඉතා බහුලව භාවිත වේ." else "This metal is widely used in electrical wiring."
                } else {
                    qText = if (isSinhala) "පරිසර පද්ධතියක ප්‍රාථමික ශක්ති ප්‍රභවය කුමක්ද?" else "What is the primary source of energy in almost all ecosystems?"
                    correctIdx = 0
                    options = if (isSinhala) listOf("සූර්යයා (ඉර)", "සුළඟ", "ජලය", "ගල් අඟුරු") else listOf("The Sun", "Wind", "Water", "Coal")
                    hint = if (isSinhala) "ශාකවලට ආහාර සෑදීමට මෙය අත්‍යවශ්‍ය වේ." else "Plants require it for photosynthesis."
                }
            }
            5 -> {
                if (idx % 2 == 0) {
                    qText = if (isSinhala) "ජලය වාෂ්ප වීම යන්නෙන් අදහස් කරන්නේ කුමන අවස්ථා පෙරලියක්ද?" else "What is the change of state when liquid water turns into water vapor?"
                    correctIdx = 1
                    options = if (isSinhala) listOf("ඝනීභවනය", "වාෂ්පීභවනය", "හිමායනය", "ද්‍රවීකරණය") else listOf("Condensation", "Evaporation", "Freezing", "Melting")
                    hint = if (isSinhala) "දියරයක් වායුවක් බවට පත්වන ක්‍රියාවලියයි." else "The process of a liquid turning into a gas."
                } else {
                    qText = if (isSinhala) "ප්‍රජා ආහාර දාමයක පළමු පුරුක කුමක්ද?" else "What is always the first organism in a typical food chain?"
                    correctIdx = 3
                    options = if (isSinhala) listOf("වංශභක්ෂකයෝ", "සිංහයා", "පෝෂකයෝ", "නිෂ්පාදකයෝ (කොළ ශාක)") else listOf("Herbivores", "Lions", "Decomposers", "Producers (Green Plants)")
                    hint = if (isSinhala) "තමාගේම ආහාර නිපදවා ගන්නා ජීවීන් ය." else "Organisms that make their own food."
                }
            }
            6 -> {
                if (idx % 2 == 0) {
                    qText = if (isSinhala) "ශාක සෛලයක පමණක් දැකිය හැකි සෛලීය අවයවිකය කුමක්ද?" else "Which organelle is found only in plant cells, not animal cells?"
                    correctIdx = 2
                    options = if (isSinhala) listOf("න්‍යෂ්ටිය", "සෛල ප්ලාස්මය", "හරිතප්‍රද / හරිතලවය", "මයිටොකොන්ඩ්‍රියාව") else listOf("Nucleus", "Cytoplasm", "Chloroplast", "Mitochondria")
                    hint = if (isSinhala) "ප්‍රභාසංස්ලේෂණයට අවශ්‍ය හරිත වර්ණය ලබා දෙයි." else "Responsible for photosynthesis and plant pigmentation."
                } else {
                    qText = if (isSinhala) "ප්‍රභාසංස්ලේෂණයේදී ශාක මඟින් පිට කරන වායුව කුමක්ද?" else "Which gas is released by plants as a byproduct of photosynthesis?"
                    correctIdx = 0
                    options = if (isSinhala) listOf("ඔක්සිජන්", "කාබන් ඩයොක්සයිඩ්", "නයිට්‍රජන්", "හයිඩ්‍රජන්") else listOf("Oxygen", "Carbon Dioxide", "Nitrogen", "Hydrogen")
                    hint = if (isSinhala) "අප හුස්ම ගැනීමට භාවිතා කරන්නේ මෙම වායුවයි." else "This gas is vital for standard human respiration."
                }
            }
            7 -> {
                if (idx % 2 == 0) {
                    qText = if (isSinhala) "පහත දැක්වෙන ද්‍රව්‍ය අතරින් මූලද්‍රව්‍යයක් නොවන්නේ කුමක්ද?" else "Which of the following substances is NOT a pure element?"
                    correctIdx = 3
                    options = if (isSinhala) listOf("රන්", "යකඩ", "ඔක්සිජන්", "ජලය") else listOf("Gold", "Iron", "Oxygen", "Water")
                    hint = if (isSinhala) "එහි හයිඩ්‍රජන් සහ ඔක්සිජන් සංයෝග වී ඇත." else "It is a compound comprised of hydrogen and oxygen molecules."
                } else {
                    qText = if (isSinhala) "බලයේ ජාත්‍යන්තර (SI) ඒකකය කුමක්ද?" else "What is the international (SI) unit of force?"
                    correctIdx = 1
                    options = if (isSinhala) listOf("ජූල් (J)", "නිවුටන් (N)", "වොට් (W)", "පැස්කල් (Pa)") else listOf("Joule (J)", "Newton (N)", "Watt (W)", "Pascal (Pa)")
                    hint = if (isSinhala) "ඇපල් ගෙඩියක් හිස මතට වැටුණු ශාස්ත්‍රඥයා සිහිකරන්න." else "Named after the scientist associated with gravity and apples."
                }
            }
            8 -> {
                if (idx % 2 == 0) {
                    qText = if (isSinhala) "ආවර්තිතා වගුවේ පළමු මූලද්‍රව්‍යය කුමක්ද?" else "What is the very first element in the periodic table?"
                    correctIdx = 0
                    options = if (isSinhala) listOf("හයිඩ්‍රජන් (H)", "හීලියම් (He)", "ලිතියම් (Li)", "කාබන් (C)") else listOf("Hydrogen (H)", "Helium (He)", "Lithium (Li)", "Carbon (C)")
                    hint = if (isSinhala) "එහි පරමාණුක ක්‍රමාංකය 1 කි." else "Its atomic number is 1."
                } else {
                    qText = if (isSinhala) "ශබ්දය ගමන් කරන්නේ කුමන තරංග ආකාරයෙන්ද?" else "Sound travels through a medium in which wave format?"
                    correctIdx = 1
                    options = if (isSinhala) listOf("තීර්යක් තරංග", "අනුදෛර්ය තරංග", "විද්‍යුත් චුම්බක තරංග", "ස්ථාවර තරංග") else listOf("Transverse waves", "Longitudinal waves", "Electromagnetic waves", "Stationary waves")
                    hint = if (isSinhala) "පීඩන වෙනස්වීම් සහ සම්පීඩන ඇති කරමින් ගමන් කරයි." else "Involves compressions and rarefactions of particles."
                }
            }
            9 -> {
                if (idx % 2 == 0) {
                    qText = if (isSinhala) "මිනිස් දේහ සෛලයක ඇති වර්ණදේහ සංඛ්‍යාව කොපමණද?" else "How many chromosomes are there in a standard human somatic cell?"
                    correctIdx = 2
                    options = if (isSinhala) listOf("23", "30", "46", "52") else listOf("23", "30", "46", "52")
                    hint = if (isSinhala) "යුගල 23ක් ඇත." else "It consists of 23 pairs."
                } else {
                    qText = if (isSinhala) "කාබන් කාබනික රසායනයේ සුවිශේෂී මූලද්‍රව්‍යයකි. එහි සංයුජතාව කීයද?" else "What is the valency of a Carbon atom in organic chemistry?"
                    correctIdx = 1
                    options = if (isSinhala) listOf("2", "4", "6", "8") else listOf("2", "4", "6", "8")
                    hint = if (isSinhala) "කාබන්ට බන්ධන හතරක් සෑදිය හැක." else "Carbon forms exactly four covalent bonds."
                }
            }
            else -> {
                // Grade 10: Advanced Chemistry, Newtonian Physics
                if (idx % 2 == 0) {
                    qText = if (isSinhala) "නිවුටන්ගේ දෙවන චලිත නියමයේ ගණිතමය ප්‍රකාශය කුමක්ද?" else "What is the mathematical equation representing Newton's second law?"
                    correctIdx = 0
                    options = listOf("F = ma", "F = m/a", "E = mc²", "v = u + at")
                    hint = if (isSinhala) "බලය සමාන වේ ස්කන්ධය ගුණ කිරීම ත්වරණය." else "Force equals mass multiplied by acceleration."
                } else {
                    qText = if (isSinhala) "ලෝහ කාණ්ඩයක අම්ල ප්‍රතික්‍රියාවකදී සාමාන්‍යයෙන් පිටවන වායුව කුමක්ද?" else "Which gas is typically produced when a metal reacts with dilute acid?"
                    correctIdx = 3
                    options = if (isSinhala) listOf("කාබන් ඩයොක්සයිඩ්", "ඔක්සිජන්", "නයිට්‍රජන්", "හයිඩ්‍රජන්") else listOf("Carbon Dioxide", "Oxygen", "Nitrogen", "Hydrogen")
                    hint = if (isSinhala) "මෙම වායුව පොප් හඬක් නංවමින් දැල්වේ." else "This gas ignites with a characteristic 'pop' sound."
                }
            }
        }

        return Question(
            question = qText,
            options = options,
            correctAnswer = correctIdx,
            grade = grade,
            subject = "Science",
            difficulty = diff,
            hint = hint
        )
    }

    private fun generateEnglishQuestion(grade: Int, idx: Int, qid: String, diff: String, isSinhala: Boolean): Question {
        // Deterministic English language curricular questions for Grade 1-10
        val qText: String
        val options: List<String>
        val correctIdx: Int
        val hint: String

        when (grade) {
            1 -> {
                val words = listOf("Apple", "Banana", "Cat", "Dog", "Elephant", "Frog", "Grapes", "House", "Ice", "Jar")
                val letters = listOf("A", "B", "C", "D", "E", "F", "G", "H", "I", "J")
                qText = if (isSinhala) "'${words[idx]}' යන වචනය ආරම්භ වන්නේ කුමන අකුරෙන්ද?" else "What letter does the word '${words[idx]}' start with?"
                correctIdx = 0
                options = listOf(letters[idx], letters[(idx + 1) % 10], letters[(idx + 2) % 10], letters[(idx + 3) % 10])
                hint = if (isSinhala) "වචනයේ මුල්ම ශබ්දය බලන්න." else "Look at the first character of the word."
            }
            2 -> {
                val verbs = listOf("go", "eat", "run", "sleep", "sing", "drink", "write", "see", "speak", "do")
                val pastTenses = listOf("went", "ate", "ran", "slept", "sang", "drank", "wrote", "saw", "spoke", "did")
                qText = if (isSinhala) "'${verbs[idx]}' යන්නෙහි නිවැරදි අතීත කාලය (Past Tense) කුමක්ද?" else "What is the past tense form of the verb '${verbs[idx]}'?"
                correctIdx = 1
                options = listOf(verbs[idx] + "ed", pastTenses[idx], verbs[idx] + "ing", verbs[idx] + "s")
                hint = if (isSinhala) "මෙය අක්‍රමවත් (Irregular) ක්‍රියා පදයකි." else "This is an irregular past tense verb."
            }
            3 -> {
                if (idx % 2 == 0) {
                    qText = if (isSinhala) "'Child' යන නාම පදයේ නිවැරදි බහුවචනය කුමක්ද?" else "Identify the plural form of 'child'?"
                    correctIdx = 2
                    options = listOf("childs", "childes", "children", "child")
                    hint = if (isSinhala) "මෙය අක්‍රමවත් බහුවචනයකි." else "An irregular plural form that adds -ren."
                } else {
                    qText = if (isSinhala) "'She ___ reading a book.' හිස්තැනට සුදුසු පදය කුමක්ද?" else "Complete: 'She ___ reading a book.'"
                    correctIdx = 0
                    options = listOf("is", "are", "am", "be")
                    hint = if (isSinhala) "ඒකවචන කර්තෘන් සඳහා 'is' භාවිතා වේ." else "Use the singular present form of 'to be'."
                }
            }
            4 -> {
                if (idx % 2 == 0) {
                    qText = if (isSinhala) "'The QUICK brown fox...' මෙහි කැපිටල් පදයේ ව්‍යාකරණ ගණය කුමක්ද?" else "Identify the part of speech of the capitalized word: 'The QUICK brown fox jumped.'"
                    correctIdx = 1
                    options = listOf("Noun", "Adjective", "Verb", "Adverb")
                    hint = if (isSinhala) "මෙය නාම පදයක් විස්තර කිරීමට යෙදේ." else "It describes the noun (fox)."
                } else {
                    qText = if (isSinhala) "'He walked slowly.' මෙහි ක්‍රියා විශේෂණය (Adverb) හඳුනාගන්න." else "Choose the adverb in the statement: 'He walked slowly.'"
                    correctIdx = 3
                    options = listOf("He", "walked", "walk", "slowly")
                    hint = if (isSinhala) "සාමාන්‍යයෙන් අවසානයට -ly එකතු වේ." else "Adverbs of manner often end with -ly."
                }
            }
            5 -> {
                if (idx % 2 == 0) {
                    qText = if (isSinhala) "'He is interested ___ science.' හිස්තැනට සුදුසු පූර්ව රුපය කුමක්ද?" else "Choose the correct preposition: 'He is interested ___ science.'"
                    correctIdx = 2
                    options = listOf("on", "at", "in", "by")
                    hint = if (isSinhala) "'Interested' සමඟ සැමවිටම යෙදේ." else "This preposition always follows 'interested'."
                } else {
                    qText = if (isSinhala) "'I have seen this movie.' මෙය කුමන කාලයට අයත්ද?" else "Identify the tense: 'I have seen this movie before.'"
                    correctIdx = 0
                    options = listOf("Present Perfect", "Simple Past", "Present Continuous", "Past Perfect")
                    hint = if (isSinhala) "'have' + අතීත ක්‍රියා ක්‍රියාකාරී පදය ඇත." else "Uses have/has + past participle."
                }
            }
            6 -> {
                if (idx % 2 == 0) {
                    qText = if (isSinhala) "'This is the boy ___ won the gold medal.' හිස්තැනට සුදුසු පදය කුමක්ද?" else "Fill in: 'This is the boy ___ won the gold medal.'"
                    correctIdx = 1
                    options = listOf("which", "who", "whom", "whose")
                    hint = if (isSinhala) "පුද්ගලයන්ට සම්බන්ධ කිරීමට මෙය යොදයි." else "Use the relative pronoun for persons."
                } else {
                    qText = if (isSinhala) "'He studied hard, ___ he failed the test.' හිස්තැනට ගැළපෙන සම්බන්ධක පදය කුමක්ද?" else "Select the proper conjunction: 'He studied hard, ___ he failed the test.'"
                    correctIdx = 0
                    options = listOf("but", "because", "so", "since")
                    hint = if (isSinhala) "විරුද්ධ අදහස් සම්බන්ධ කිරීමට යොදයි." else "Expresses contrast between the two clauses."
                }
            }
            7 -> {
                if (idx % 2 == 0) {
                    qText = if (isSinhala) "'BEAUTIFUL' පදයේ විරුද්ධ පදය කුමක්ද?" else "What is the antonym of the word 'BEAUTIFUL'?"
                    correctIdx = 3
                    options = listOf("Pretty", "Nice", "Lovely", "Ugly")
                    hint = if (isSinhala) "ලස්සන යන්නෙහි විරුද්ධ අදහස සිතන්න." else "Think of the opposite of highly pleasing."
                } else {
                    qText = if (isSinhala) "'He is taller ___ his brother.' හිස්තැනට ගැළපෙන පදය කුමක්ද?" else "Complete the sentence: 'He is taller ___ his brother.'"
                    correctIdx = 1
                    options = listOf("then", "than", "to", "as")
                    hint = if (isSinhala) "සංසන්දනය කිරීමට භාවිතා කරන පදයයි." else "Used in comparative structures."
                }
            }
            8 -> {
                if (idx % 2 == 0) {
                    qText = if (isSinhala) "'LARGE' පදයේ සමාන පදය කුමක්ද?" else "Choose the correct synonym for 'LARGE'?"
                    correctIdx = 0
                    options = listOf("Huge", "Small", "Tiny", "Thin")
                    hint = if (isSinhala) "විශාල යන්නෙහි සමාන අරුත සලකන්න." else "Meaning of massive scale."
                } else {
                    qText = if (isSinhala) "'Neither of the candidates ___ arrived yet.' නිවැරදි පදය තෝරන්න." else "Select the correct option: 'Neither of the candidates ___ arrived yet.'"
                    correctIdx = 1
                    options = listOf("have", "has", "are", "were")
                    hint = if (isSinhala) "Neither යනු ඒකවචන අදහසකි." else "Neither acts as a singular subject here."
                }
            }
            9 -> {
                if (idx % 2 == 0) {
                    qText = if (isSinhala) "'The chef prepared a meal.' මෙහි කර්මකාරක වාක්‍යය (Passive Voice) කුමක්ද?" else "Convert to Passive Voice: 'The chef prepared a delicious meal.'"
                    correctIdx = 2
                    options = listOf(
                        "The chef was preparing a delicious meal.",
                        "A delicious meal is prepared by the chef.",
                        "A delicious meal was prepared by the chef.",
                        "A delicious meal has been prepared by the chef."
                    )
                    hint = if (isSinhala) "අතීත කාලයේ කර්මකාරක රූපය සලකන්න." else "Use 'was' with the past participle."
                } else {
                    qText = if (isSinhala) "'If it rains, we ___ play football.' හිස්තැනට ගැළපෙන පදය කුමක්ද?" else "Complete the conditional: 'If it rains, we ___ play football.'"
                    correctIdx = 0
                    options = listOf("will not", "would not", "have not", "cannot be")
                    hint = if (isSinhala) "පළමු කොන්දේසි කාලය (First Conditional) සලකන්න." else "We use simple future negative in the main clause."
                }
            }
            else -> {
                // Grade 10: Advanced literature, essay writing
                if (idx % 2 == 0) {
                    qText = if (isSinhala) "'As brave as a lion.' යන්නෙහි අඩංගු අලංකාරෝක්තිය (Figure of Speech) කුමක්ද?" else "Identify the figure of speech: 'As brave as a lion.'"
                    correctIdx = 1
                    options = listOf("Metaphor", "Simile", "Personification", "Hyperbole")
                    hint = if (isSinhala) "'As' හෝ 'Like' භාවිතයෙන් කරන සැසඳීමකි." else "A comparison using the word 'as' or 'like'."
                } else {
                    qText = if (isSinhala) "'She was so sad that her tears created a sea.' මෙහි ඇති අතිශයෝක්තිය කුමක්ද?" else "Identify the figure of speech: 'She was so sad that her tears created an ocean.'"
                    correctIdx = 3
                    options = listOf("Metaphor", "Simile", "Alliteration", "Hyperbole")
                    hint = if (isSinhala) "වැඩි කර පෙන්වන අතිශයෝක්ති ප්‍රකාශයකි." else "An extreme exaggeration used to make a point."
                }
            }
        }

        return Question(
            question = qText,
            options = options,
            correctAnswer = correctIdx,
            grade = grade,
            subject = "English",
            difficulty = diff,
            hint = hint
        )
    }

    private fun generateHistoryQuestion(grade: Int, idx: Int, qid: String, diff: String, isSinhala: Boolean): Question {
        // Deterministic History curricular questions (focus on Sri Lankan & world history)
        val qText: String
        val options: List<String>
        val correctIdx: Int
        val hint: String

        when (grade) {
            1 -> {
                val flags = if (isSinhala) listOf("සිංහයා", "කොටියා", "අලියා", "මුවා") else listOf("Lion", "Tiger", "Elephant", "Deer")
                qText = if (isSinhala) "ශ්‍රී ලංකා ජාතික කොඩියේ නිරූපණය වන සත්වයා කවුද?" else "Which animal is depicted on the Sri Lankan national flag?"
                correctIdx = 0
                options = flags
                hint = if (isSinhala) "එම සත්වයා කඩුවක් දරා සිටී." else "This creature holds a golden ceremonial sword."
            }
            2 -> {
                if (idx % 2 == 0) {
                    qText = if (isSinhala) "ශ්‍රී ලංකාවේ ප්‍රථම ඓතිහාසික රජතුමා ලෙස සලකන්නේ කවුද?" else "Who is traditionally regarded as the first King of Sri Lanka?"
                    correctIdx = 1
                    options = if (isSinhala) listOf("පණ්ඩුකාභය", "විජය", "දේවානම්පියතිස්ස", "දුටුගැමුණු") else listOf("Pandukabhaya", "Vijaya", "Devanampiyatissa", "Dutugemunu")
                    hint = if (isSinhala) "භාරතයේ සිට ආණ්ඩුකාර කණ්ඩායමක් සමඟ මෙරටට පැමිණි තැනැත්තායි." else "He landed with 700 followers from India."
                } else {
                    qText = if (isSinhala) "ශ්‍රී ලංකාවේ අතීත අගනුවරක් වූයේ කුමක්ද?" else "Which of these was an ancient capital of Sri Lanka?"
                    correctIdx = 2
                    options = if (isSinhala) listOf("කොළඹ", "ගාල්ල", "අනුරාධපුරය", "යාපනය") else listOf("Colombo", "Galle", "Anuradhapura", "Jaffna")
                    hint = if (isSinhala) "වැව් සහ වෙහෙර විහාර රාශියක් ඇති ඓතිහාසික නගරයකි." else "Known for its massive stūpas and ruins."
                }
            }
            3 -> {
                if (idx % 2 == 0) {
                    qText = if (isSinhala) "ශ්‍රී ලංකාවේ මුහුදුබඩ ප්‍රදේශ ප්‍රථමයෙන් ආක්‍රමණය කළ විදේශීය ජාතිය කවුද?" else "Which European power was the first to invade coastal areas of Sri Lanka?"
                    correctIdx = 0
                    options = if (isSinhala) listOf("පෘතුගීසීන්", "ලන්දේසීන්", "ඉංග්‍රීසීන්", "ප්‍රංශ ජාතිකයන්") else listOf("Portuguese", "Dutch", "British", "French")
                    hint = if (isSinhala) "ඔහු 1505 වර්ෂයේදී මෙරටට පැමිණියේය." else "They arrived in 1505."
                } else {
                    qText = if (isSinhala) "පෘතුගීසීන්ගෙන් පසුව ශ්‍රී ලංකාවේ මුහුදුබඩ පාලනය අත්පත් කරගත්තේ කවුද?" else "Which European power succeeded the Portuguese in controlling coastal Sri Lanka?"
                    correctIdx = 1
                    options = if (isSinhala) listOf("ඉංග්‍රීසීන්", "ලන්දේසීන් (ලන්දේසි පෙරදිග ඉන්දියා සමාගම)", "ප්‍රංශ ජාතිකයන්", "ස්පාඤ්ඤ ජාතිකයන්") else listOf("British", "Dutch (VOC)", "French", "Spanish")
                    hint = if (isSinhala) "ඕලන්ද ජාතිකයන් ලෙසද හඳුන්වයි." else "Also referred to as the Hollanders."
                }
            }
            4 -> {
                if (idx % 2 == 0) {
                    qText = if (isSinhala) "සිංහල සහ දමිළ අලුත් අවුරුද්ද උදා වන්නේ සාමාන්‍යයෙන් කුමන මාසයේද?" else "In which month is the Sinhala and Tamil New Year celebrated?"
                    correctIdx = 2
                    options = if (isSinhala) listOf("ජනවාරි", "මාර්තු", "අප්‍රේල්", "මැයි") else listOf("January", "March", "April", "May")
                    hint = if (isSinhala) "එය බක් මාසය ලෙසද හඳුන්වයි." else "Known in the native calendar as Bak."
                } else {
                    qText = if (isSinhala) "ශ්‍රී ලංකාවට බුදුදහම උරුම කර දුන් ඓතිහාසික මිහිඳු මහරහතන් වහන්සේ වැඩම කළේ කුමන පොහෝ දිනකද?" else "On which Full Moon Poya Day did Arahat Mahinda introduce Buddhism to Sri Lanka?"
                    correctIdx = 0
                    options = if (isSinhala) listOf("පොසොන් පුර පසළොස්වක පොහොය", "වෙසක් පොහොය", "ඇසළ පොහොය", "දුරුතු පොහොය") else listOf("Poson Poya Day", "Vesak Poya Day", "Esala Poya Day", "Duruthu Poya Day")
                    hint = if (isSinhala) "දේවානම්පියතිස්ස රජතුමා දඩයමේ ගිය දිනයයි." else "Occurs in June, and is marked by Mihintale celebrations."
                }
            }
            5 -> {
                if (idx % 2 == 0) {
                    qText = if (isSinhala) "ඊජිප්තුවේ ගීසාහි පිරමිඩ ඉදි කරන ලද්දේ කුමන පැරණි ශිෂ්ටාචාරයේද?" else "Which ancient civilization built the Great Pyramids of Giza?"
                    correctIdx = 3
                    options = if (isSinhala) listOf("රෝම ශිෂ්ටාචාරය", "ග්‍රීක ශිෂ්ටාචාරය", "චීන ශිෂ්ටාචාරය", "ඊජිප්තු ශිෂ්ටාචාරය") else listOf("Roman", "Greek", "Chinese", "Egyptian")
                    hint = if (isSinhala) "නයිල් නදියේ නිම්නයේ බිහි වූ ශිෂ්ටාචාරයයි." else "Civilization centered along the Nile River."
                } else {
                    qText = if (isSinhala) "ලෝකයේ ප්‍රථම ලිඛිත නීති මාලාව ලෙස සැලකෙන හමුරාබි නීති සංග්‍රහය අයත් වන්නේ කුමන දේශයටද?" else "The Code of Hammurabi belongs to which ancient civilization?"
                    correctIdx = 1
                    options = if (isSinhala) listOf("පැරැණි චීනය", "මෙපොටේමියාව (Mesopotamia)", "ඉන්දු නිම්නය", "ග්‍රීසිය") else listOf("Ancient China", "Mesopotamia", "Indus Valley", "Greece")
                    hint = if (isSinhala) "ටයිග්‍රීස් සහ යුප්‍රටීස් ගංගා ආශ්‍රිතව බිහි විය." else "Centered between the Tigris and Euphrates rivers."
                }
            }
            6 -> {
                if (idx % 2 == 0) {
                    qText = if (isSinhala) "ලෝක පූජිත සීගිරිය බලකොටුව ඉදි කරන ලද්දේ කුමන රජතුමා විසින්ද?" else "Which Sri Lankan king constructed the world-renowned Sigiriya fortress?"
                    correctIdx = 2
                    options = if (isSinhala) listOf("ධාතුසේන රජු", "දේවානම්පියතිස්ස රජු", "කාශ්‍යප රජු (පළමු කාශ්‍යප)", "දුටුගැමුණු රජු") else listOf("King Dhatusena", "King Devanampiyatissa", "King Kashyapa", "King Dutugemunu")
                    hint = if (isSinhala) "සිංහගිරිය පර්වත මුදුනේ මාලිගාව තැනවූ කුමාරයායි." else "Built on top of a massive rock column."
                } else {
                    qText = if (isSinhala) "ලෝක ඉතිහාසයේ ප්‍රජාතන්ත්‍රවාදයේ උපන් බිම ලෙස සලකන්නේ කුමන නගර රාජ්‍යයද?" else "Which ancient city-state is considered the birthplace of democracy?"
                    correctIdx = 0
                    options = if (isSinhala) listOf("ඇතැන්ස් (Athens)", "ස්පාටා (Sparta)", "රෝමය (Rome)", "බැබිලෝනියාව (Babylon)") else listOf("Athens", "Sparta", "Rome", "Babylon")
                    hint = if (isSinhala) "මෙය පැරණි ග්‍රීසියේ අගනුවරයි." else "A historic Greek city-state."
                }
            }
            7 -> {
                if (idx % 2 == 0) {
                    qText = if (isSinhala) "අනුරාධපුරයේ පිහිටි උතුම් රුවන්වැලි මහා සෑය තැනවූ රජතුමා කවුද?" else "Which king built the iconic Ruwanwelisaya stupa in Anuradhapura?"
                    correctIdx = 1
                    options = if (isSinhala) listOf("වළගම්බා රජු", "දුටුගැමුණු රජු", "මහාසෙන් රජු", "පරාක්‍රමබාහු රජු") else listOf("King Walagamba", "King Dutugemunu", "King Mahasen", "King Parakramabahu")
                    hint = if (isSinhala) "එළාර රජු පරදවා ලංකාව එක්සේසත් කළ ශ්‍රේෂ්ඨතම රජතුමායි." else "Unified the island after defeating King Elara."
                } else {
                    qText = if (isSinhala) "ඓතිහාසික මහා පරාක්‍රමබාහු රජුගේ පාලන සමයේ අගනුවර වූයේ කුමක්ද?" else "What was the capital of Sri Lanka during the reign of King Parakramabahu I?"
                    correctIdx = 3
                    options = if (isSinhala) listOf("අනුරාධපුරය", "සීගිරිය", "දඹදෙණිය", "පොළොන්නරුව") else listOf("Anuradhapura", "Sigiriya", "Dambadeniya", "Polonnaruwa")
                    hint = if (isSinhala) "පරාක්‍රම සමුද්‍රය පිහිටා ඇත්තේ මෙහිය." else "Where the massive Parakrama Samudra is located."
                }
            }
            8 -> {
                if (idx % 2 == 0) {
                    qText = if (isSinhala) "මුළු ලංකාද්වීපයම ප්‍රථම වරට ඉංග්‍රීසි අධිරාජ්‍යයට යටත් වූයේ කුමන ගිවිසුම මඟින්ද?" else "By which historic treaty was the entire island of Sri Lanka ceded to the British Empire?"
                    correctIdx = 0
                    options = if (isSinhala) listOf("උඩරට ගිවිසුම (1815)", "අමියන්ස් ගිවිසුම", "ලන්ඩන් ගිවිසුම", "වෙරසාලිස් ගිවිසුම") else listOf("Kandyan Convention (1815)", "Treaty of Amiens", "Treaty of London", "Treaty of Versailles")
                    hint = if (isSinhala) "1815 මාර්තු 2 වැනිදා අත්සන් කරන ලදී." else "Signed on March 2, 1815 at the Kandyan Audience Hall."
                } else {
                    qText = if (isSinhala) "ශ්‍රී ලාංකික කීර්තිමත් විරුවෙකු වන වීර පුරන් අප්පු නායකත්වය දුන් කැරැල්ල කුමක්ද?" else "Which rebellion was led by the legendary patriot Veera Puran Appu?"
                    correctIdx = 2
                    options = if (isSinhala) listOf("ඌව වෙල්ලස්ස කැරැල්ල (1818)", "වැලිගම සටන", "මාතලේ කැරැල්ල (1848)", "ගොංfගාලේගොඩ බණ්ඩා කැරැල්ල") else listOf("Great Rebellion of 1818", "Weligama Battle", "Matale Rebellion (1848)", "Gongalegoda Rebellion")
                    hint = if (isSinhala) "ඉංග්‍රීසි බදු පාලනයට එරෙහිව 1848 දී ඇති වූවකි." else "Staged in 1848 against British tax policies."
                }
            }
            9 -> {
                if (idx % 2 == 0) {
                    qText = if (isSinhala) "ශ්‍රී ලංකාව (ලංකාද්වීපය) බ්‍රිතාන්‍ය පාලනයෙන් නිදහස ලබාගත්තේ කුමන වර්ෂයේද?" else "In which year did Sri Lanka (Ceylon) gain independence from British rule?"
                    correctIdx = 1
                    options = listOf("1947", "1948", "1956", "1972")
                    hint = if (isSinhala) "පෙබරවාරි මස 4 වන දින නිදහස් දිනය සමරනු ලැබේ." else "Celebrated on February 4th of this year."
                } else {
                    qText = if (isSinhala) "පළමු ලෝක යුද්ධය ආරම්භ වූයේ කුමන වර්ෂයේද?" else "In which year did the First World War break out?"
                    correctIdx = 0
                    options = listOf("1914", "1918", "1939", "1945")
                    hint = if (isSinhala) "එය 1918 දී අවසන් විය." else "It ended in the year 1918."
                }
            }
            else -> {
                // Grade 10: World wars, modern affairs
                if (idx % 2 == 0) {
                    qText = if (isSinhala) "නිදහස් ශ්‍රී ලංකාවේ ප්‍රථම අග්‍රාමාත්‍යවරයා කවුද?" else "Who was the very first Prime Minister of independent Sri Lanka?"
                    correctIdx = 3
                    options = if (isSinhala) listOf("සර් ජෝන් කොතලාවල", "එස්. ඩබ්ලිව්. ආර්. ඩී. බණ්ඩාරනායක", "ඩඩ්ලි සේනානායක", "ඩී. එස්. සේනානායක") else listOf("Sir John Kotelawala", "S.W.R.D. Bandaranaike", "Dudley Senanayake", "D.S. Senanayake")
                    hint = if (isSinhala) "දේශයේ පියා ලෙසද එතුමාව හඳුන්වයි." else "Known as the Father of the Nation."
                } else {
                    qText = if (isSinhala) "ශ්‍රී ලංකාව ජනරජයක් (Republic) බවට පත් වී නම 'ශ්‍රී ලංකා' ලෙස වෙනස් වූයේ කුමන වර්ෂයේද?" else "In which year did Sri Lanka become a republic, discarding its dominion status?"
                    correctIdx = 2
                    options = listOf("1948", "1956", "1972", "1978")
                    hint = if (isSinhala) "ප්‍රථම ජනරජ ව්‍යවස්ථාව හඳුන්වා දුන් වර්ෂයයි." else "This was when the First Republican Constitution was enacted."
                }
            }
        }

        return Question(
            question = qText,
            options = options,
            correctAnswer = correctIdx,
            grade = grade,
            subject = "History",
            difficulty = diff,
            hint = hint
        )
    }
}
