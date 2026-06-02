package com.example.data

object EnglishQuestions {
    val list: List<Question> = generate()

    private fun generate(): List<Question> {
        val qList = mutableListOf<Question>()

        for (g in 1..5) {
            for (s in 1..3) {
                for (q in 0..9) {
                    val id = "eng_g${g}_s${s}_$q"
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
                            // Grade 1 English
                            when (s) {
                                1 -> {
                                    // Missing letter
                                    val wordPair = listOf(
                                        Triple("ba_y", "b", "b_by"),
                                        Triple("c_t", "a", "cat"),
                                        Triple("d_g", "o", "dog"),
                                        Triple("p_n", "e", "pen"),
                                        Triple("h_n", "e", "hen"),
                                        Triple("b_y", "o", "boy"),
                                        Triple("v_n", "a", "van"),
                                        Triple("r_t", "a", "rat"),
                                        Triple("p_g", "i", "pig"),
                                        Triple("f_x", "o", "fox")
                                    )[q]
                                    questionEn = "Fill in the single blank letter: '${wordPair.first}'"
                                    questionSi = "හිස්තැන සඳහා ගැලපෙන ඉංග්‍රීසි අකුර තෝරන්න: '${wordPair.first}'"
                                    optionsEn = listOf("a", "e", "i", "o")
                                    optionsSi = listOf("a", "e", "i", "o")
                                    correctIndex = optionsEn.indexOf(wordPair.second).coerceAtLeast(0)
                                    hintEn = "It spells a common word."
                                    hintSi = "එය සරල වචනයක් සාදයි."
                                }
                                2 -> {
                                    // Animals and nouns
                                    val animals = listOf("Dog", "Cat", "Lion", "Tiger", "Fox", "Cow", "Pig", "Hen", "Bee", "Ant")
                                    val animalSi = listOf("බල්ලා", "බළලා", "සිංහයා", "කොටියා", "නරියා", "එළදෙන", "ඌරා", "කිකිළිය", "මී මැස්සා", "කූඹියා")
                                    val idx = q
                                    questionEn = "What is the English word for: ${animalSi[idx]}?"
                                    questionSi = "මෙහි ඉංග්‍රීසි වචනය කුමක්ද: ${animalSi[idx]}?"
                                    optionsEn = listOf(animals[idx], "Apple", "Car", "Desk")
                                    optionsSi = listOf(animals[idx], "ඇපල්", "මෝටර් රථය", "මේසය")
                                    correctIndex = 0
                                    hintEn = "It starts with ${animals[idx].first()}."
                                    hintSi = "එය ආරම්භ වන්නේ ${animals[idx].first()} අකුරෙනි."
                                }
                                3 -> {
                                    // Basic objects
                                    val items = listOf("Book", "Pen", "Bag", "Desk", "Cup", "Hat", "Toy", "Key", "Map", "Fan")
                                    val itemsSi = listOf("පොත", "පෑන", "පසුම්බිය", "මේසය", "කෝප්පය", "තොප්පිය", "සෙල්ලම් බඩුව", "යතුර", "සිතියම", "පංකාව")
                                    val idx = q
                                    questionEn = "What is the correct English spelling for ${itemsSi[idx]}?"
                                    questionSi = "නිවැරදි ඉංග්‍රීසි අක්ෂර වින්‍යාසය තෝරන්න: ${itemsSi[idx]}"
                                    optionsEn = listOf(items[idx], items[idx] + "x", "y" + items[idx], "No")
                                    optionsSi = listOf(items[idx], items[idx] + "x", "y" + items[idx], "ප්‍රතික්ෂේප කරන්න")
                                    correctIndex = 0
                                    hintEn = "It is the standard spelling."
                                    hintSi = "එය සම්මත නිවැරදි වචනයයි."
                                }
                            }
                        }
                        2 -> {
                            // Grade 2 English
                            when (s) {
                                1 -> {
                                    // Colors
                                    val colors = listOf("Red", "Blue", "Green", "Yellow", "Pink", "Black", "White", "Brown", "Orange", "Gray")
                                    val colorsSi = listOf("රතු", "නිල්", "කොළ", "කහ", "රෝස", "කළු", "සුදු", "දුඹුරු", "තැඹිලි", "අළු")
                                    val idx = q
                                    questionEn = "What is the English word for the color ${colorsSi[idx]}?"
                                    questionSi = "කුමන වර්ණයද මින් අදහස් කරන්නේ: ${colorsSi[idx]}"
                                    optionsEn = listOf(colors[idx], "Purple", "Gold", "Silver")
                                    optionsSi = listOf(colors[idx], "දම්", "රන්", "රිදී")
                                    correctIndex = 0
                                    hintEn = "Starts with ${colors[idx].first()}."
                                    hintSi = "ආරම්භක අකුර ${colors[idx].first()} වේ."
                                }
                                2 -> {
                                    // Opposites
                                    val opposites = listOf(
                                        Triple("Hot", "Cold", "උණුසුම්"),
                                        Triple("Big", "Small", "විශාල"),
                                        Triple("Happy", "Sad", "සතුටු"),
                                        Triple("Tall", "Short", "උස"),
                                        Triple("Day", "Night", "දිවා"),
                                        Triple("Up", "Down", "ඉහළ"),
                                        Triple("Fast", "Slow", "වේගවත්"),
                                        Triple("Good", "Bad", "හොඳ"),
                                        Triple("New", "Old", "අලුත්"),
                                        Triple("Left", "Right", "වම")
                                    )[q]
                                    questionEn = "What is the opposite of '${opposites.first}'?"
                                    questionSi = "${opposites.third} යන්නෙහි ප්‍රතිවිරුද්ධ ඉංග්‍රීසි වචනය කුමක්ද?"
                                    optionsEn = listOf(opposites.second, "Heavens", "Heavy", "Soft")
                                    optionsSi = listOf(opposites.second, "ස්වර්ගය", "බර", "මෘදු")
                                    correctIndex = 0
                                    hintEn = "Starts with ${opposites.second.first()}."
                                    hintSi = "ආරම්භක අකුර ${opposites.second.first()} වේ."
                                }
                                3 -> {
                                    // Prepositions
                                    questionEn = "Fill in the blank: The ball is ______ the box."
                                    questionSi = "හිස්තැන පුරවන්න: පන්දුව පෙට්ටිය ______ ඇත."
                                    optionsEn = listOf("under", "at", "to", "by")
                                    optionsSi = listOf("යට", "වෙත", "දෙසට", "විසින්")
                                    correctIndex = 0
                                    hintEn = "It describes placing beneath."
                                    hintSi = "පෙට්ටියෙන් යට ප්‍රදේශය අදහස් කරයි."
                                }
                            }
                        }
                        3 -> {
                            // Grade 3 English
                            when (s) {
                                1 -> {
                                    // Verb forms
                                    questionEn = "Choose the singular form of 'dogs'."
                                    questionSi = "බල්ලන් යන්නෙහි ඒකවචනය තෝරන්න."
                                    optionsEn = listOf("dog", "doggy", "doggies", "dogs")
                                    optionsSi = listOf("බල්ලා", "බලු පැටියා", "බලු පැටවුන්", "බල්ලන්")
                                    correctIndex = 0
                                    hintEn = "Remove the trailing 's'."
                                    hintSi = "අගට ඇති අකුර ඉවත් කරන්න."
                                }
                                2 -> {
                                    // Simple spelling checks
                                    val listSp = listOf("School", "Friend", "Family", "Teacher", "Mother", "Father", "Sister", "Brother", "Doctor", "Farmer")
                                    val listSpSi = listOf("පාසල", "මිතුරා", "පවුල", "ගුරුතුමා", "මව", "පියතුමා", "සහෝදරිය", "සහෝදරයා", "වෛද්‍යවරයා", "ගොවියා")
                                    questionEn = "What is the correct English spelling of ${listSpSi[q]}?"
                                    questionSi = "${listSpSi[q]} යන්නෙහි නිවැරදි ඉංග්‍රීසි අක්ෂර වින්‍යාසය කුමක්ද?"
                                    optionsEn = listOf(listSp[q], listSp[q] + "i", "e" + listSp[q], "No")
                                    optionsSi = listOf(listSp[q], listSp[q] + "i", "e" + listSp[q], "ප්‍රතික්ෂේප කරන්න")
                                    correctIndex = 0
                                    hintEn = "Starts with ${listSp[q].first()}."
                                    hintSi = "ආරම්භක අකුර ${listSp[q].first()} වේ."
                                }
                                3 -> {
                                    // Action words
                                    val verbs = listOf("run", "swim", "fly", "eat", "read", "write", "jump", "sing", "dance", "sleep")
                                    val verbsSi = listOf("දුවනවා", "පීනනවා", "පියාඹනවා", "කනවා", "කියවනවා", "ලියනවා", "පනිනවා", "ගී ගයනවා", "නටනවා", "නිදාගන්නවා")
                                    questionEn = "Identify the action word for ${verbsSi[q]}."
                                    questionSi = "${verbsSi[q]} යන්නෙහි ඉංග්‍රීසි ක්‍රියා පදය කුමක්ද?"
                                    optionsEn = listOf(verbs[q], "Apple", "Table", "Cold")
                                    optionsSi = listOf(verbs[q], "ඇපල්", "මේසය", "සීතල")
                                    correctIndex = 0
                                    hintEn = "It describes active movement or physical state."
                                    hintSi = "එය ක්‍රියාවක් අදහස් කරයි."
                                }
                            }
                        }
                        4 -> {
                            // Grade 4 English
                            when (s) {
                                1 -> {
                                    // Pronouns
                                    questionEn = "Fill in the blank: ______ is a good student. (Use singular male)"
                                    questionSi = "හිස්තැන පුරවන්න: ______ දක්ෂ ශිෂ්‍යයෙකි."
                                    optionsEn = listOf("He", "They", "We", "She")
                                    optionsSi = listOf("ඔහු", "ඔවුන්", "අපි", "ඇය")
                                    correctIndex = 0
                                    hintEn = "It refers to a male person."
                                    hintSi = "පිරිමි ළමයෙකු හැඳින්වීමට යොදා ගනී."
                                }
                                2 -> {
                                    // Plurals irregular
                                    val plurals = listOf(
                                        Pair("child", "children"), Pair("man", "men"), Pair("woman", "women"),
                                        Pair("tooth", "teeth"), Pair("foot", "feet"), Pair("mouse", "mice"),
                                        Pair("leaf", "leaves"), Pair("knife", "knives"), Pair("life", "lives"),
                                        Pair("ox", "oxen")
                                    )[q]
                                    questionEn = "What is the plural form of the word '${plurals.first}'?"
                                    questionSi = "මෙම වචනයේ බහුවචන පදය කුමක්ද: '${plurals.first}'"
                                    optionsEn = listOf(plurals.second, plurals.first + "s", plurals.first + "es", "none")
                                    optionsSi = listOf(plurals.second, plurals.first + "s", plurals.first + "es", "කිසිවක් නැත")
                                    correctIndex = 0
                                    hintEn = "It is an irregular plural form."
                                    hintSi = "එය අක්‍රමවත් බහුවචන පදයකි."
                                }
                                3 -> {
                                    // Simple adjectives
                                    questionEn = "Which of the following is an adjective?"
                                    questionSi = "පහත දැක්වෙන වචන වලින් නාමවිශේෂණයක් වන්නේ කුමක්ද?"
                                    optionsEn = listOf("beautiful", "run", "slowly", "desk")
                                    optionsSi = listOf("ලස්සන", "දුවනවා", "සෙමින්", "මේසය")
                                    correctIndex = 0
                                    hintEn = "It describes the quality of a noun."
                                    hintSi = "එය නාමපදයක ගුණාංගයක් විස්තර කරයි."
                                }
                            }
                        }
                        5 -> {
                            // Grade 5 English
                            when (s) {
                                1 -> {
                                    // Tenses simple past
                                    val tenses = listOf(
                                        Triple("go", "went", "ගියා"), Triple("eat", "ate", "කෑවා"),
                                        Triple("write", "wrote", "ලිව්වා"), Triple("read", "read", "කියෙව්වා"),
                                        Triple("see", "saw", "දැක්කා"), Triple("run", "ran", "දිව්වා"),
                                        Triple("sing", "sang", "ගැයුවා"), Triple("buy", "bought", "මිලදී ගත්තා"),
                                        Triple("drink", "drank", "බීවා"), Triple("speak", "spoke", "කතා කළා")
                                    )[q]
                                    questionEn = "What is the past tense form of '${tenses.first}'?"
                                    questionSi = "මෙම වචනයේ අතීත කාල ඉංග්‍රීසි පදය කුමක්ද: '${tenses.first}'"
                                    optionsEn = listOf(tenses.second, tenses.first + "ed", tenses.first + "ing", "none")
                                    optionsSi = listOf(tenses.second, tenses.first + "ed", tenses.first + "ing", "කිසිවක් නැත")
                                    correctIndex = 0
                                    hintEn = "It is an irregular past tense form."
                                    hintSi = "එය අක්‍රමවත් අතීත කාල రూపයකි."
                                }
                                2 -> {
                                    // Preposition complex
                                    questionEn = "He is interested ______ reading historical stories."
                                    questionSi = "ඔහු ඉතිහාස කතා කියවීමට උනන්දුවක් දක්වයි. හිස්තැනට එන නිපාත පදය කුමක්ද?"
                                    optionsEn = listOf("in", "on", "at", "by")
                                    optionsSi = listOf("in", "on", "at", "by")
                                    correctIndex = 0
                                    hintEn = "The word 'interested' is paired with this preposition."
                                    hintSi = "ඉංග්‍රීසි ව්‍යාකරණ වලදී interested සමඟ සැමවිටම යෙදේ."
                                }
                                3 -> {
                                    // Conjunctions
                                    questionEn = "I wanted to play outside, ______ it was raining heavily."
                                    questionSi = "මට පිටත සෙල්ලම් කිරීමට අවශ්‍ය වුවද, තදින් වැසි පතිත විය. ගැලපෙන සම්බන්ධක පදය කුමක්ද?"
                                    optionsEn = listOf("but", "or", "so", "because")
                                    optionsSi = listOf("but", "or", "so", "because")
                                    correctIndex = 0
                                    hintEn = "It connects contrasting sentences."
                                    hintSi = "එය වෙනස් අදහස් දෙකක් එකතු කරයි."
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
                        subject = "English",
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
