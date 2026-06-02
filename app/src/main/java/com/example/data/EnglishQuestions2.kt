package com.example.data

object EnglishQuestions2 {
    val list: List<Question> = generate()

    private fun generate(): List<Question> {
        val qList = mutableListOf<Question>()

        for (g in 6..10) {
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
                        6 -> {
                            // Grade 6 English
                            when (s) {
                                1 -> {
                                    // Adverbs
                                    questionEn = "Identify the adverb in: 'She walked slowly down the road.'"
                                    questionSi = "මෙහි ක්‍රියාවිශේෂණය හඳුනාගන්න: 'She walked slowly down the road.'"
                                    optionsEn = listOf("walked", "slowly", "road", "she")
                                    optionsSi = listOf("walked", "slowly", "road", "she")
                                    correctIndex = 1
                                    hintEn = "Adverbs often end with 'ly'."
                                    hintSi = "ඉංග්‍රීසි ක්‍රියාවිශේෂණ බහුලවම 'ly' අකුරු වලින් අවසන් වේ."
                                }
                                2 -> {
                                    // Proper pronouns
                                    questionEn = "Fill in the blank: Neither the teacher nor the students ______ happy write-up."
                                    questionSi = "හිස්තැන පුරවන්න: Neither the teacher nor the students ______ happy write-up."
                                    optionsEn = listOf("was", "were", "is", "has")
                                    optionsSi = listOf("was", "were", "is", "has")
                                    correctIndex = 1
                                    hintEn = "The verb agrees with the closer subject, which is plural."
                                    hintSi = "හිස්තැනට ළඟින්ම ඇති කර්තෘ බහුවචන පදයක් බැවින් ක්‍රියාපදයද බහුවචන වේ."
                                }
                                3 -> {
                                    // Simple homophones
                                    questionEn = "Choose the correct spelling: We can hear the waves of the ______."
                                    questionSi = "නිවැරදි ඉංග්‍රීසි වචනය තෝරන්න: We can hear the waves of the ______."
                                    optionsEn = listOf("sea", "see", "seen", "she")
                                    optionsSi = listOf("sea", "see", "seen", "she")
                                    correctIndex = 0
                                    hintEn = "It refers to the ocean."
                                    hintSi = "මහ මුහුද හැඳින්වීමට යොදන වචනයයි."
                                }
                            }
                        }
                        7 -> {
                            // Grade 7 English
                            when (s) {
                                1 -> {
                                    // Passive voice introduction
                                    questionEn = "What is the passive form of: 'A student wrote a letter.'?"
                                    questionSi = "මෙහි කර්මණී වාක්‍යය කුමක්ද: 'A student wrote a letter.'?"
                                    optionsEn = listOf("A letter is written by a student.", "A letter was written by a student.", "A letter had been written.", "A student is writing a letter.")
                                    optionsSi = listOf("A letter is written by a student.", "A letter was written by a student.", "A letter had been written.", "A student is writing a letter.")
                                    correctIndex = 1
                                    hintEn = "The original sentence is in the simple past tense."
                                    hintSi = "ආරම්භක වාක්‍යය අතීත කාලයෙන් පවතින බැවින් was සමඟ ක්‍රියා පදයේ තුන්වන අවස්ථාව යෙදේ."
                                }
                                2 -> {
                                    // Conjunctions
                                    questionEn = "Complete the sentence: You can take ______ a bus or a train to Colombo."
                                    questionSi = "සම්පූර්ණ කරන්න: You can take ______ a bus or a train to Colombo."
                                    optionsEn = listOf("either", "neither", "both", "not")
                                    optionsSi = listOf("either", "neither", "both", "not")
                                    correctIndex = 0
                                    hintEn = "It is paired with 'or'."
                                    hintSi = "or සමඟ හැමවිටම යෙදෙන සම්බන්ධකයයි."
                                }
                                3 -> {
                                    // Synonyms
                                    questionEn = "What is a synonym for the word 'ancient'?"
                                    questionSi = "පැරණි යන්නෙහි සමාන අර්ථ ඇති ඉංග්‍රීසි වචනය කුමක්ද?"
                                    optionsEn = listOf("modern", "new", "old", "fast")
                                    optionsSi = listOf("නවීන", "අලුත්", "පැරණි", "වේගවත්")
                                    correctIndex = 2
                                    hintEn = "Think of something very old."
                                    hintSi = "ඉතා පැරණි දෙයක් ගැන සිතන්න."
                                }
                            }
                        }
                        8 -> {
                            // Grade 8 English
                            when (s) {
                                1 -> {
                                    // Prepositions of movement
                                    questionEn = "He walked ______ the dark forest carefully."
                                    questionSi = "ඔහු අඳුරු වනාන්තරය මැදින් පරිස්සමින් ඇවිද ගියේය. හිස්තැනට සුදුසු පදය කුමක්ද?"
                                    optionsEn = listOf("through", "on", "at", "by")
                                    optionsSi = listOf("through", "on", "at", "by")
                                    correctIndex = 0
                                    hintEn = "It refers to going inside and out of active area."
                                    hintSi = "තුළින් හෝ මැදින් ගමන් කිරීම අදහස් කරයි."
                                }
                                2 -> {
                                    // Complex spelling
                                    questionEn = "Which of the following has the correct spelling?"
                                    questionSi = "පහත දැක්වෙන වචන වලින් නිවැරදි අක්ෂර වින්‍යාසය සහිත වචනය කුමක්ද?"
                                    optionsEn = listOf("accommodation", "acomodation", "accomodation", "acommodation")
                                    optionsSi = listOf("accommodation", "acomodation", "accomodation", "acommodation")
                                    correctIndex = 0
                                    hintEn = "It has double 'c' and double 'm'."
                                    hintSi = "එහි c අකුරු දෙකක් සහ m අකුරු දෙකක් අඩංගු වේ."
                                }
                                3 -> {
                                    // Prefixes
                                    questionEn = "Which prefix can be added to compile the opposite of 'honest'?"
                                    questionSi = "අවංක යන්නෙහි ප්‍රතිවිරුද්ධ ඉංග්‍රීසි වචනය සෑදීමට එකතු කළ යුතු උපසර්ගය කුමක්ද?"
                                    optionsEn = listOf("dis", "un", "im", "in")
                                    optionsSi = listOf("dis", "un", "im", "in")
                                    correctIndex = 0
                                    hintEn = "The result is 'dishonest'."
                                    hintSi = "එකතු කළ විට 'dishonest' යන වචනය සෑදේ."
                                }
                            }
                        }
                        9 -> {
                            // Grade 9 English
                            when (s) {
                                1 -> {
                                    // Relational pronouns
                                    questionEn = "The man ______ stole the bicycle has been arrested."
                                    questionSi = "බයිසිකලය සොරකම් කළ මිනිසා අත්අඩංගුවට ගෙන ඇත. හිස්තැනට සුදුසු පදය කුමක්ද?"
                                    optionsEn = listOf("who", "which", "whose", "whom")
                                    optionsSi = listOf("who", "which", "whose", "whom")
                                    correctIndex = 0
                                    hintEn = "It refers relative to a person performing action."
                                    hintSi = "පුද්ගලයෙකු සම්බන්ධ කිරීමට මෙම පද භාවිතා වේ."
                                }
                                2 -> {
                                    // Conditional structures
                                    questionEn = "If they had started earlier, they ______ have missed the flight."
                                    questionSi = "ඔවුන් කලින් පිටත් වුණා නම් ගුවන් ගමන මඟ හැරෙන්නේ නැත. හිස්තැනට එන ව්‍යාකරණ ලැබීම කුමක්ද?"
                                    optionsEn = listOf("would not", "could", "should", "will not")
                                    optionsSi = listOf("would not", "could", "should", "will not")
                                    correctIndex = 0
                                    hintEn = "This is a third conditional structure."
                                    hintSi = "මෙය අතීතයේ විය හැකිව තිබූ අවස්ථාවක් විස්තර කරයි."
                                }
                                3 -> {
                                    // Question tags
                                    questionEn = "You speak English fluently, ______ you?"
                                    questionSi = "ඔබ ඉංග්‍රීසි චතුර ලෙස කතා කරයි, එසේ නොවේද? හිස්තැනට එන ප්‍රශ්න පදය කුමක්ද?"
                                    optionsEn = listOf("don't", "aren't", "won't", "doesn't")
                                    optionsSi = listOf("don't", "aren't", "won't", "doesn't")
                                    correctIndex = 0
                                    hintEn = "The main verb is 'speak' in present active form."
                                    hintSi = "ප්‍රධාන ක්‍රියාපදය වර්තමාන කාලයෙන් ඇති බැවින් do ඇසුරෙන් ප්‍රශ්නය සකසයි."
                                }
                            }
                        }
                        10 -> {
                            // Grade 10 English
                            when (s) {
                                1 -> {
                                    // Direct and Indirect report
                                    questionEn = "Report this sentence directly: She said, 'I live here.' in indirect speech."
                                    questionSi = "මෙම වාක්‍යය වක්‍ර ප්‍රකාශනයක් බවට පරිවර්තනය කරන්න: She said, 'I live here.'"
                                    optionsEn = listOf("She said she lived there.", "She said she lives here.", "She said she was living there.", "She said she lived here.")
                                    optionsSi = listOf("She said she lived there.", "She said she lives here.", "She said she was living there.", "She said she lived here.")
                                    correctIndex = 0
                                    hintEn = "Tense changes from simple present to simple past, and 'here' becomes 'there'."
                                    hintSi = "වචනය අතීත කාලයට හැරෙන අතර 'here' යන්න 'there' බවට පරිවර්තනය වේ."
                                }
                                2 -> {
                                    // Phrasal verbs
                                    questionEn = "What does the phrasal verb 'call off' mean?"
                                    questionSi = "'call off' යන ඉංග්‍රීසි වාක්‍යාංශයේ අර්ථය කුමක්ද?"
                                    optionsEn = listOf("Cancel", "Postpone", "Continue", "Call someone loudly")
                                    optionsSi = listOf("අවලංගු කරනවා", "කල් දමනවා", "දිගටම කරගෙන යනවා", "ශබ්ද නඟා කතා කරනවා")
                                    correctIndex = 0
                                    hintEn = "It means to cancel an event."
                                    hintSi = "කිසියම් සංවිධානයක් හෝ රැස්වීමක් අවලංගු කිරීම මින් අදහස් කෙරේ."
                                }
                                3 -> {
                                    // Subject verb agreement irregular
                                    questionEn = "The news ______ not very encouraging today."
                                    questionSi = "අද පුවත් එතරම් සතුටුදායක නොවේ. හිස්තැනට එන ක්‍රියා පදය කුමක්ද?"
                                    optionsEn = listOf("is", "are", "were", "be")
                                    optionsSi = listOf("is", "are", "were", "be")
                                    correctIndex = 0
                                    hintEn = "The noun 'news' is single uncountable."
                                    hintSi = "'news' යන්න පෙනුමෙන් බහුවචන වුවද එය සැමවිටම ඒකවචන ක්‍රියා පදයක් ගනී."
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
