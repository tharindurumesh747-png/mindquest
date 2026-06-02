package com.example.data

object HistoryQuestions {
    val list: List<Question> = generate()

    private fun generate(): List<Question> {
        val qList = mutableListOf<Question>()

        for (g in 1..5) {
            for (s in 1..3) {
                for (q in 0..9) {
                    val id = "hist_g${g}_s${s}_$q"
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
                            // Grade 1 History - extremely simple
                            when (s) {
                                1 -> {
                                    // Me and My Family
                                    questionEn = "Who is the mother of your father?"
                                    questionSi = "ඔබේ පියාගේ මව ඔබට කවුරුන් වේද?"
                                    optionsEn = listOf("Grandmother", "Aunt", "Sister", "Mother")
                                    optionsSi = listOf("ආච්චි", "නැන්දා", "සහෝදරිය", "මව")
                                    correctIndex = 0
                                    hintEn = "She is an elder female member of your home."
                                    hintSi = "ඇය පවුලේ සිටින වයස්ගතම කාන්තාවකි."
                                }
                                2 -> {
                                    // National Symbols simple
                                    questionEn = "What animal is depicted in the Sri Lankan national flag?"
                                    questionSi = "ශ්‍රී ලංකා ජාතික ධජයේ නිරූපණය කර ඇති සත්ත්වයා කවුද?"
                                    optionsEn = listOf("Lion", "Tiger", "Elephant", "Leopard")
                                    optionsSi = listOf("සිංහයා", "කොටියා", "අලියා", "කොටියා")
                                    correctIndex = 0
                                    hintEn = "It holds a sword in its right paw."
                                    hintSi = "ඌ දකුණු අතින් කඩුවක් දරා සිටී."
                                }
                                3 -> {
                                    // Ancient stories basic
                                    questionEn = "Which giant castle is built on top of a single huge rock in Sri Lanka?"
                                    questionSi = "ශ්‍රී ලංකාවේ තනි විශාල ගලක් මත ඉදිකර ඇති බලකොටුව කුමක්ද?"
                                    optionsEn = listOf("Sigiriya", "Galle Fort", "Yapahuwa", "Trincomalee Fort")
                                    optionsSi = listOf("සීගිරිය", "ගාලු කොටුව", "යාපහුව", "ත්‍රිකුණාමල කොටුව")
                                    correctIndex = 0
                                    hintEn = "It has lion paws at the entrance."
                                    hintSi = "එහි ඇතුල්වීමේ දොරටුවේ සිංහ පාද දෙකක් ඇත."
                                }
                            }
                        }
                        2 -> {
                            // Grade 2 History
                            when (s) {
                                1 -> {
                                    // Ancient toys and lifestyle
                                    questionEn = "What did children in ancient Sri Lanka use to write on before paper?"
                                    questionSi = "අතීතයේ කඩදාසි සොයා ගැනීමට පෙර ලේඛන කටයුතු සඳහා භාවිත කළේ කුමක්ද?"
                                    optionsEn = listOf("Ola leaf", "Plastic card", "Iron plate", "Glass sheet")
                                    optionsSi = listOf("පුස්කොළ", "ප්ලාස්ටික් කාඩ්පත", "යකඩ තහඩුව", "වීදුරු තහඩුව")
                                    correctIndex = 0
                                    hintEn = "It is prepared from palm tree leaves."
                                    hintSi = "එය තල කොළ පිළියෙල කිරීමෙන් සාදා ගනී."
                                }
                                2 -> {
                                    // National heroes simple
                                    questionEn = "Who was the great king who built the Ruwanwelisaya stupa in Anuradhapura?"
                                    questionSi = "අනුරාධපුරයේ රුවන්වැලිසෑය මහා ස්තූපය ඉදිකළ උදාර රජතුමා කවුද?"
                                    optionsEn = listOf("Dutugemunu", "Parakramabahu", "Devanampiyatissa", "Kashyapa")
                                    optionsSi = listOf("දුටුගැමුණු", "පරාක්‍රමබාහු", "දේවානම්පියතිස්ස", "කාශ්‍යප")
                                    correctIndex = 0
                                    hintEn = "This king's name starts with 'Dutugemunu' in Sinhala."
                                    hintSi = "ඔහුගේ නම දුටුගැමුණු ලෙස උච්චාරණය කරනු ලැබේ."
                                }
                                3 -> {
                                    // Historical places
                                    questionEn = "Where is the ancient temple Sri Dalada Maligawa located?"
                                    questionSi = "ශ්‍රී දළදා මාළිගාව පිහිටා තිබෙන්නේ කුමන ඓතිහාසික නගරයේද?"
                                    optionsEn = listOf("Kandy", "Anuradhapura", "Polonnaruwa", "Galle")
                                    optionsSi = listOf("මහනුවර", "අනුරාධපුරය", "පොළොන්නරුව", "ගාල්ල")
                                    correctIndex = 0
                                    hintEn = "It is the last royal capital of Sri Lanka, surrounded by hills."
                                    hintSi = "එය කඳුකරයෙන් වටවූ ලංකාවේ අවසාන රාජධානියයි."
                                }
                            }
                        }
                        3 -> {
                            // Grade 3 History
                            when (s) {
                                1 -> {
                                    // Ancient agriculture
                                    questionEn = "What was the main system used by ancient Sri Lankans to save water for farming?"
                                    questionSi = "අතීතයේ වගා කටයුතු වලට ජලය රැස් කර තබා ගැනීමට භාවිත කළ ප්‍රධාන උපක්‍රමය කුමක්ද?"
                                    optionsEn = listOf("Reservoirs", "Taps", "Plastic bottles", "Concrete wells")
                                    optionsSi = listOf("වැව්", "නළ ළිං", "ප්ලාස්ටික් බෝතල්", "කොන්ක්‍රීට් ළිං")
                                    correctIndex = 0
                                    hintEn = "They are large man-made lakes built by kings."
                                    hintSi = "නරපතියන් විසින් ගොඩනඟන ලද විශාල මානව වැව් විශේෂයකි."
                                }
                                2 -> {
                                    // Ancient capitals
                                    questionEn = "Which city was the very first capital kingdom of ancient Sri Lanka?"
                                    questionSi = "පුරාණ ලංකාවේ ප්‍රථම අගනුවර සහ රාජධානිය වූයේ කුමන නගරයද?"
                                    optionsEn = listOf("Anuradhapura", "Polonnaruwa", "Sigiriya", "Kandy")
                                    optionsSi = listOf("Anuradhapura", "Polonnaruwa", "Sigiriya", "Kandy")
                                    // Sinhala options should be pure Sinhala script
                                    optionsSi = listOf("අනුරාධපුරය", "පොළොන්නරුව", "සීගිරිය", "මහනුවර")
                                    correctIndex = 0
                                    hintEn = "A sacred city founded by King Pandukabhaya."
                                    hintSi = "පණ්ඩුකාභය රජතුමා විසින් සැලසුම් කරන ලද පූජනීය නගරයයි."
                                }
                                3 -> {
                                    // Traditional art
                                    questionEn = "What unique Sri Lankan dance originated in the central hill country?"
                                    questionSi = "මධ්‍යම කඳුකරයෙන් පැවත එන ශ්‍රී ලංකාවේ සාම්ප්‍රදායික නර්තන කලාව කුමක්ද?"
                                    optionsEn = listOf("Kandyan Dance", "Low Country Dance", "Sabaragamuwa Dance", "Kathakali")
                                    optionsSi = listOf("උඩරට නැටුම්", "පහතරට නැටුම්", "සබරගමු නැටුම්", "කතකලි")
                                    correctIndex = 0
                                    hintEn = "It features standard Ves attire and drums."
                                    hintSi = "වෙස් ඇඳුමෙන් සැරසී ගැටබෙර හඬට නටන නර්තන විශේෂයකි."
                                }
                            }
                        }
                        4 -> {
                            // Grade 4 History
                            when (s) {
                                1 -> {
                                    // Buddhism intro
                                    questionEn = "Who was the ruler of Sri Lanka when Buddhism was officially introduced?"
                                    questionSi = "ලක්දිවට බුදුදහම නිල වශයෙන් හඳුන්වා දුන් සමයේ මෙරට පාලකයා කවුද?"
                                    optionsEn = listOf("Devanampiyatissa", "Dutugemunu", "Pandukabhaya", "Vijayabahu")
                                    optionsSi = listOf("දේවානම්පියතිස්ස", "දුටුගැමුණු", "පණ්ඩුකාභය", "විජයබාහු")
                                    correctIndex = 0
                                    hintEn = "He met Arahant Mahinda Thero at Mihintale rock."
                                    hintSi = "මිහින්තලා ගල මුදුනේදී මිහිඳු මහ රහතන් වහන්සේ මුණගැසුණු පාලකයාය."
                                }
                                2 -> {
                                    // National heroes 2
                                    questionEn = "Who was the brave king who unified Sri Lanka under one umbrella for the first time?"
                                    questionSi = "සම්පූර්ණ ලක්දිව එක්සේසත් කළ ප්‍රථම වීරෝදාර රජතුමා කවුද?"
                                    optionsEn = listOf("Dutugemunu", "Parakramabahu", "Vijayabahu", "Walagamba")
                                    optionsSi = listOf("දුටුගැමුණු", "පරාක්‍රමබාහු", "විජයබාහු", "වළගම්බා")
                                    correctIndex = 0
                                    hintEn = "He defeated King Elara in Anuradhapura."
                                    hintSi = "අනුරාධපුරයේදී එළාර රජු පරදවා රට එක්සේසත් කළේ මොහුය."
                                }
                                3 -> {
                                    // Sacred trees
                                    questionEn = "What is the name of the oldest historically documented tree in Sri Lanka?"
                                    questionSi = "ලෝකයේ දීර්ඝතම ලිඛිත ඉතිහාසයක් ඇති ශ්‍රී ලංකාවේ වැඩසිටින පූජනීය බෝධීන් වහන්සේ කුමක්ද?"
                                    optionsEn = listOf("Jaya Sri Maha Bodhi", "Ananda Bodhi", "Kiri Palu Tree", "Nuga Tree")
                                    optionsSi = listOf("ජය ශ්‍රී මහා බෝධින් වහන්සේ", "ආනන්ද බෝධිය", "කිරිපළු රුක", "නුග ගස")
                                    correctIndex = 0
                                    hintEn = "Brought by Sanghamitta Theri to Anuradhapura."
                                    hintSi = "සංඝමිත්තා මෙහෙණින් වහන්සේ විසින් අනුරාධපුරයට වැඩම කරවන ලදී."
                                }
                            }
                        }
                        5 -> {
                            // Grade 5 History
                            when (s) {
                                1 -> {
                                    // Polonnaruwa
                                    questionEn = "Who was the great king who constructed the Parakrama Samudra reservoir?"
                                    questionSi = "පරාක්‍රම සමුද්‍රය නම් මහා වැව ඉදිකළ උත්තම රජතුමා කවුද?"
                                    optionsEn = listOf("Parakramabahu", "Vijayabahu", "Dutugemunu", "Mahasen")
                                    optionsSi = listOf("පරාක්‍රමබාහු", "විජයබාහු", "දුටුගැමුණු", "මහසෙන්")
                                    correctIndex = 0
                                    hintEn = "He said not a single drop of rain should flow to the sea without being useful."
                                    hintSi = "අහසින් වැටෙන එකදු දිය බිඳක්වත් ප්‍රයෝජනයට නොගෙන මුහුදට නොයැවිය යුතු යැයි ප්‍රකාශ කළ තැනැත්තායි."
                                }
                                2 -> {
                                    // Foreign invasions
                                    questionEn = "Which European nation was the first to arrive in Sri Lanka in 1505?"
                                    questionSi = "ක්‍රිස්තු වර්ෂ 1505 දී ප්‍රථම වරට ලංකාවට පැමිණි යුරෝපීය ජාතිය කුමක්ද?"
                                    optionsEn = listOf("Portuguese", "Dutch", "British", "French")
                                    optionsSi = listOf("පෘතුගීසීන්", "ලන්දේසීන්", "ඉංග්‍රීසීන්", "ප්‍රංශ ජාතිකයන්")
                                    correctIndex = 0
                                    hintEn = "Led by Lourenco de Almeida."
                                    hintSi = "ලොරෙන්සෝ ද අල්මේදා විසින් මෙම නෞකා මෙරටට මෙහෙයවන ලදී."
                                }
                                3 -> {
                                    // Irrigation systems
                                    questionEn = "Which king built the famous Yoda Ela canal that has active slow gradient?"
                                    questionSi = "ඉතා සියුම් බැස්මක් ඇති ඓතිහාසික ජය ගඟ හෙවත් යෝධ ඇළ කරවූ රජතුමා කවුද?"
                                    optionsEn = listOf("Dhatusena", "Mahasen", "Parakramabahu", "Kashyapa")
                                    optionsSi = listOf("ධාතුසේන", "මහසෙන්", "පරාක්‍රමබාහු", "කාශ්‍යප")
                                    correctIndex = 0
                                    hintEn = "He also built the Kala Wewa reservoir."
                                    hintSi = "ප්‍රසිද්ධ කලා වැව ඉදිකළ රජතුමා මොහුය."
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
                        subject = "History",
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
