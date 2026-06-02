package com.example.data

object ScienceQuestions {
    val list: List<Question> = generate()

    private fun generate(): List<Question> {
        val qList = mutableListOf<Question>()

        for (g in 1..5) {
            for (s in 1..3) {
                for (q in 0..9) {
                    val id = "sci_g${g}_s${s}_$q"
                    val difficulty = when (s) {
                        1 -> "easy"
                        2 -> "medium"
                        else -> "hard"
                    }

                    var questionEn = ""
                    var questionSi = ""
                    var optionsEn = listOf<String>()
                    var optionsSi = listOf<String>()
                    var correctIndex = 0
                    var hintEn = ""
                    var hintSi = ""

                    when (g) {
                        1 -> {
                            // Grade 1 Science - Extreme Simple
                            val animalList = listOf(
                                Pair("Dog", "බල්ලා"), Pair("Cat", "බළලා"), Pair("Bird", "කුරුල්ලා"),
                                Pair("Cow", "එළදෙන"), Pair("Fish", "මුවා"), Pair("Parrot", "ගිරවා"),
                                Pair("Rabbit", "හාවා"), Pair("Elephant", "අලියා"), Pair("Squirrel", "ලේනා"),
                                Pair("Butterfly", "සමනලයා")
                            )
                            val animal = animalList[q]
                            when (s) {
                                1 -> {
                                    // Living vs Non-living
                                    questionEn = "Is a ${animal.first} a living thing?"
                                    questionSi = "${animal.second} සජීවී දෙයක්ද?"
                                    optionsEn = listOf("Yes", "No", "Only when sleeping", "Only when running")
                                    optionsSi = listOf("ඔව්", "නැත", "නිදාගන්නා විට පමණි", "දුවන විට පමණි")
                                    correctIndex = 0
                                    hintEn = "Living things grow, move, and breathe."
                                    hintSi = "සජීවී දේවල් වර්ධනය වේ, චලනය වේ, සහ හුස්ම ගනී."
                                }
                                2 -> {
                                    // Body Parts & Senses
                                    val senses = listOf(
                                        Triple("see", "දැකීමට", "Eyes"),
                                        Triple("hear", "ඇසීමට", "Ears"),
                                        Triple("smell", "ඉව කිරීමට", "Nose"),
                                        Triple("taste", "රස බැලීමට", "Tongue"),
                                        Triple("touch", "දැනීමට", "Skin")
                                    )
                                    val sense = senses[q % senses.size]
                                    questionEn = "Which organ do we use to ${sense.first}?"
                                    questionSi = "අප ${sense.second} භාවිතා කරන ඉන්ද්‍රිය කුමක්ද?"
                                    optionsEn = listOf("Eyes", "Ears", "Nose", "Tongue")
                                    optionsSi = listOf("ඇස්", "කන්", "නාසය", "දිව")
                                    correctIndex = when (sense.third) {
                                        "Eyes" -> 0
                                        "Ears" -> 1
                                        "Nose" -> 2
                                        else -> 3
                                    }
                                    hintEn = "Think about the parts of your face."
                                    hintSi = "ඔබේ මුහුණේ ඇති කොටස් ගැන සිතන්න."
                                }
                                3 -> {
                                    // Sun, Moon, Sky
                                    if (q % 2 == 0) {
                                        questionEn = "What do we see in the sky during the day?"
                                        questionSi = "දිවා කාලයේ අපට අහසේ දැකිය හැක්කේ කුමක්ද?"
                                        optionsEn = listOf("Sun", "Moon", "Stars", "Clouds only")
                                        optionsSi = listOf("හිරු", "සඳ", "තරු", "වලාකුළු පමණි")
                                        correctIndex = 0
                                        hintEn = "It gives us bright light and heat."
                                        hintSi = "එය අපට ආලෝකය සහ උණුසුම ලබා දෙයි."
                                    } else {
                                        questionEn = "What do we usually see in the sky at night?"
                                        questionSi = "රාත්‍රී කාලයේ අපට අහසේ දැකිය හැක්කේ කුමක්ද?"
                                        optionsEn = listOf("Sun", "Rainbow", "Moon", "Green clouds")
                                        optionsSi = listOf("හිරු", "දේදුන්න", "සඳ", "කොළ පැහැති වලාකුළු")
                                        correctIndex = 2
                                        hintEn = "It shines gently when it is dark."
                                        hintSi = "අඳුරු කාලයේදී එය මෘදුව බබළයි."
                                    }
                                }
                            }
                        }
                        2 -> {
                            // Grade 2 Science
                            when (s) {
                                1 -> {
                                    // Animal sounds and homes
                                    val insects = listOf("Bee", "Ant", "Spider", "Worm")
                                    val insectSi = listOf("මී මැස්සා", "කූඹියා", "බෙල්ලා", "පණුවා")
                                    val idx = q % insects.size
                                    questionEn = "Which of these is a tiny crawling animal?"
                                    questionSi = "මෙහි දැක්වෙන කුඩා බඩගාන සත්ත්වයා කවුද?"
                                    optionsEn = listOf(insects[idx], "Elephant", "Leopard", "Deer")
                                    optionsSi = listOf(insectSi[idx], "අලියා", "කොටියා", "මුවා")
                                    correctIndex = 0
                                    hintEn = "It is very small and crawls on branches or ground."
                                    hintSi = "එය ඉතා කුඩා වන අතර අතු මත හෝ බිම බඩගා යයි."
                                }
                                2 -> {
                                    // Simple elements/Weather
                                    if (q % 2 == 0) {
                                        questionEn = "What falls from the clouds during rainy weather?"
                                        questionSi = "වැසි සහිත කාලගුණයේදී වලාකුළු වලින් බිමට වැටෙන්නේ කුමක්ද?"
                                        optionsEn = listOf("Rock", "Water", "Sand", "Paper")
                                        optionsSi = listOf("ගල්", "ජලය", "වැලි", "කඩදාසි")
                                        correctIndex = 1
                                        hintEn = "It is a liquid that fills streams."
                                        hintSi = "එය දිය පහරවල් පුරවන ද්‍රවයකි."
                                    } else {
                                        questionEn = "What do we feel when there is strong wind?"
                                        questionSi = "තද සුළඟක් ඇති විට අපට දැනෙන්නේ කුමක්ද?"
                                        optionsEn = listOf("Moving water", "Moving air", "Falling rocks", "Heat")
                                        optionsSi = listOf("චලනය වන ජලය", "චලනය වන වායුව", "වැටෙන ගල්", "රස්නය")
                                        correctIndex = 1
                                        hintEn = "Wind is simply the movement of air."
                                        hintSi = "සුළඟ යනු වායුව වේගයෙන් ගමන් කිරීමයි."
                                    }
                                }
                                3 -> {
                                    // Basic Plants
                                    questionEn = "Which part of a tree is found underground?"
                                    questionSi = "ගසක පස යටින් පවතින කොටස කුමක්ද?"
                                    optionsEn = listOf("Leaves", "Flower", "Roots", "Fruit")
                                    optionsSi = listOf("කොළ", "මල", "මුල්", "ගෙඩි")
                                    correctIndex = 2
                                    hintEn = "This part holds the tree firmly in the ground."
                                    hintSi = "මෙම කොටස මඟින් ගස පසට සවි කර තබා ගනී."
                                }
                            }
                        }
                        3 -> {
                            // Grade 3 Science
                            when (s) {
                                1 -> {
                                    // Plant parts & functions
                                    questionEn = "What is the primary function of plant roots?"
                                    questionSi = "ශාක මුල්වල ප්‍රධාන කාර්යය කුමක්ද?"
                                    optionsEn = listOf("To absorb water", "To make seeds", "To produce flowers", "To color leaves")
                                    optionsSi = listOf("ජලය අවශෝෂණය කිරීම", "බීජ සෑදීම", "මල් හට ගැනීම", "කොළ පාට කිරීම")
                                    correctIndex = 0
                                    hintEn = "Roots take in water and nutrients from the soil."
                                    hintSi = "මුල් මඟින් පසෙහි ඇති ජලය සහ පෝෂක කොටස් ලබා ගනී."
                                }
                                2 -> {
                                    // Materials and properties
                                    val materials = listOf("Wood", "Iron", "Rubber", "Clay")
                                    val materialsSi = listOf("ලී", "යකඩ", "රබර්", "මැටි")
                                    val idx = q % materials.size
                                    questionEn = "Which material is used to make a classroom desk?"
                                    questionSi = "පන්ති කාමරයේ ඇති මේසය සෑදීමට බහුලව ගන්නා ද්‍රව්‍යය කුමක්ද?"
                                    optionsEn = listOf("Wood", "Rubber", "Clay", "Glass")
                                    optionsSi = listOf("ලී", "රබර්", "මැටි", "වීදුරු")
                                    correctIndex = 0
                                    hintEn = "It comes from the trunk of big trees."
                                    hintSi = "එය විශාල ගස්වල කඳන් මඟින් ලබා ගනී."
                                }
                                3 -> {
                                    // Living resources
                                    questionEn = "Which of the following is essential for all living things to survive?"
                                    questionSi = "සියලුම සජීවී දේවල් ජීවත් වීමට අත්‍යවශ්‍ය වන්නේ කුමක්ද?"
                                    optionsEn = listOf("Plastic", "Water", "Iron", "Gold")
                                    optionsSi = listOf("ප්ලාස්ටික්", "ජලය", "යකඩ", "රන්")
                                    correctIndex = 1
                                    hintEn = "We drink it when we are thirsty."
                                    hintSi = "පිපාසය ඇති වූ විට අප එය පානය කරමු."
                                }
                            }
                        }
                        4 -> {
                            // Grade 4 Science
                            when (s) {
                                1 -> {
                                    // Earth and Space
                                    questionEn = "What is the closest star to the Earth?"
                                    questionSi = "පෘථිවියට ආසන්නයෙන්ම පිහිටි තරුව කුමක්ද?"
                                    optionsEn = listOf("Sirius", "Sun", "Polaris", "Alpha Centauri")
                                    optionsSi = listOf("සීරියස්", "සූර්යයා", "ධ්‍රැව තරුව", "ඇල්ෆා සෙන්චූරි")
                                    correctIndex = 1
                                    hintEn = "It provides light and heat during the daytime."
                                    hintSi = "දිවා කාලයේදී එය පෘථිවියට ආලෝකය සහ රස්නය සපයයි."
                                }
                                2 -> {
                                    // Human structure basics
                                    questionEn = "Which set of bones protects our brain?"
                                    questionSi = "මොළය ආරක්ෂා කරන්නේ කුමන අස්ථි පද්ධතියකින්ද?"
                                    optionsEn = listOf("Ribcage", "Spine", "Skull", "Pelvis")
                                    optionsSi = listOf("පර්ශු කූඩුව", "කොන්දේ අස්ථි", "හිස්කබල", "ශ්‍රෝණි අස්ථි")
                                    correctIndex = 2
                                    hintEn = "It is the hard bone structure forming the head."
                                    hintSi = "එය හිස ප්‍රදේශය වටා ඇති දෘඩ අස්ථි කූඩුවයි."
                                }
                                3 -> {
                                    // States of matter
                                    questionEn = "What state of matter is water vapor?"
                                    questionSi = "ජල වාෂ්ප යනු කුමන පදාර්ථ අවස්ථාවක්ද?"
                                    optionsEn = listOf("Solid", "Liquid", "Gas", "Metal")
                                    optionsSi = listOf("ඝන", "ද්‍රව", "වායු", "ලෝහ")
                                    correctIndex = 2
                                    hintEn = "It behaves like air and floats freely."
                                    hintSi = "එය වාතය මෙන් හැසිරෙමින් නිදහසේ පාවී යයි."
                                }
                            }
                        }
                        5 -> {
                            // Grade 5 Science
                            when (s) {
                                1 -> {
                                    // Force & Energy
                                    questionEn = "What is the force that pulls things down towards the ground?"
                                    questionSi = "වස්තූන් පොළොව දෙසට ඇද තබා ගන්නා බලය කුමක්ද?"
                                    optionsEn = listOf("Friction", "Gravity", "Magnetic force", "Static force")
                                    optionsSi = listOf("ඝර්ෂණය", "ගුරුත්වාකර්ෂණය", "චුම්බක බලය", "ස්ථිති විද්‍යුත් බලය")
                                    correctIndex = 1
                                    hintEn = "It keeps the Earth orbiting the Sun and apples falling down."
                                    hintSi = "එය නිසා ඇපල් ගෙඩියක් ගසෙන් බිමට වැටේ."
                                }
                                2 -> {
                                    // Adaptations
                                    questionEn = "How do fish breathe underwater?"
                                    questionSi = "මත්ස්‍යයන් ජලය යටදී ශ්වසනය කරන්නේ කුමක් මඟින්ද?"
                                    optionsEn = listOf("Lungs", "Skin", "Gills", "Fins")
                                    optionsSi = listOf("පෙනහළු", "සම", "කරමල්", "වරල්")
                                    correctIndex = 2
                                    hintEn = "These special respiratory organs filter dissolved oxygen from water."
                                    hintSi = "ජලයේ දියවී ඇති ඔක්සිජන් පෙරා ගැනීමට මසුන්ට ඇති අවයවයයි."
                                }
                                3 -> {
                                    // Light and Shadows
                                    questionEn = "What is formed when light is blocked by an opaque object?"
                                    questionSi = "ආලෝකය අපාරදෘශ්‍ය වස්තුවකින් අවහිර වූ විට සෑදෙන්නේ කුමක්ද?"
                                    optionsEn = listOf("Rainbow", "Reflection", "Shadow", "Sparkle")
                                    optionsSi = listOf("දේදුන්න", "ප්‍රතිබිම්බය", "සෙවනැල්ල", "දීප්තිය")
                                    correctIndex = 2
                                    hintEn = "It is a dark shape cast on a surface behind the object."
                                    hintSi = "එය වස්තුව පිටුපස ඇති පොළොව හෝ ලෑල්ල මත සෑදෙන අඳුරු හැඩයයි."
                                }
                            }
                        }
                    }

                    qList.add(Question(
                        id = id,
                        question = questionEn,
                        questionSinhala = questionSi,
                        options = optionsEn,
                        optionsSinhala = optionsSi,
                        correctAnswerIndex = correctIndex,
                        grade = g,
                        subject = "Science",
                        stage = s,
                        difficulty = difficulty,
                        hint = hintEn,
                        hintSinhala = hintSi
                    ))
                }
            }
        }
        return qList
    }
}
