package com.example.data

object HistoryQuestions2 {
    val list: List<Question> = generate()

    private fun generate(): List<Question> {
        val qList = mutableListOf<Question>()

        for (g in 6..10) {
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
                        6 -> {
                            // Grade 6 History
                            when (s) {
                                1 -> {
                                    // Prehistoric man
                                    questionEn = "What is the name given to the prehistoric human remains found in Sri Lanka?"
                                    questionSi = "ලංකාවෙන් හමුවූ ප්‍රාග් ඓතිහාසික මානවයා හඳුන්වන නම කුමක්ද?"
                                    optionsEn = listOf("Balangoda Man", "Neanderthal", "Java Man", "Peking Man")
                                    optionsSi = listOf("බලංගොඩ මානවයා", "නියැන්ඩර්තාල්", "ජාවා මානවයා", "පීකිං මානවයා")
                                    correctIndex = 0
                                    hintEn = "Named after the area Balangoda where bones were located."
                                    hintSi = "සාධක හමුවූ බලංගොඩ ප්‍රදේශය ඇසුරෙන් මෙම නම තබා ඇත."
                                }
                                2 -> {
                                    // Anuradhapura foundations
                                    questionEn = "Which king built the famous Abhayagiri stupa in Anuradhapura?"
                                    questionSi = "අනුරාධපුර අභයගිරි ස්තූපය ඉදිකළ රජතුමා කවුද?"
                                    optionsEn = listOf("Walagamba", "Dutugemunu", "Mahasen", "Dhatusena")
                                    optionsSi = listOf("වළගම්බා", "දුටුගැමුණු", "මහසෙන්", "ධාතුසේන")
                                    correctIndex = 0
                                    hintEn = "Also known as King Vattagamani Abhaya."
                                    hintSi = "වට්ටගාමිණී අභය යන නමින්ද මොහු ප්‍රසිද්ධය."
                                }
                                3 -> {
                                    // Ancient trade
                                    questionEn = "Which ancient Sri Lankan port was a major hub on the Silk Road?"
                                    questionSi = "සිල්ක් මාවතේ ප්‍රධාන නැවතුම්පොළක් වූ පුරාණ ලංකාවේ සුප්‍රසිද්ධ වරාය කුමක්ද?"
                                    optionsEn = listOf("Mantai", "Galle", "Colombo", "Trincomalee")
                                    optionsSi = listOf("මාන්තොට", "ගාල්ල", "කොළඹ", "ත්‍රිකුණාමලය")
                                    correctIndex = 0
                                    hintEn = "Located close to Mannar in the northwestern coast."
                                    hintSi = "වයඹදිග වෙරළ තීරයේ මන්නාරමට නුදුරින් පිහිටා තිබුණි."
                                }
                            }
                        }
                        7 -> {
                            // Grade 7 History
                            when (s) {
                                1 -> {
                                    // Polonnaruwa details
                                    questionEn = "Who was the first king to establish Polonnaruwa as the official capital?"
                                    questionSi = "පොළොන්නරුව සිය නිල අගනුවර ලෙස ස්ථාපිත කළ ප්‍රථම රජතුමා කවුද?"
                                    optionsEn = listOf("Vijayabahu", "Parakramabahu", "Nissankamalla", "Lilavati")
                                    optionsSi = listOf("පළමු විජයබාහු", "මහා පරාක්‍රමබාහු", "නිශ්ශංකමල්ල", "ලීලාවතී රැජින")
                                    correctIndex = 0
                                    hintEn = "He defeated Chola invaders and restored Sinhalese rule."
                                    hintSi = "චෝල ආක්‍රමණිකයන් පරදා සිංහල රජ පරපුර නැවත පිහිටවූයේ මොහුය."
                                }
                                2 -> {
                                    // Ancient architecture
                                    questionEn = "Which architectural wonder in Sigiriya features highly polished plaster?"
                                    questionSi = "සීගිරියේ ඇති අතිශය ඔප දැමූ බිත්තිය හඳුන්වන නම කුමක්ද?"
                                    optionsEn = listOf("Mirror Wall", "Graffiti Wall", "Lion Platform", "Fresco Bank")
                                    optionsSi = listOf("කැඩපත් පවුර", "කුරුටු ගී පවුර", "සිංහ වේදිකාව", "දිය අගල")
                                    correctIndex = 0
                                    hintEn = "Visitors wrote poems on it expressing their admiration."
                                    hintSi = "පැමිණි සංචාරකයන් එහි සිය අදහස් කුරුටු ගී ලෙස සටහන් කර ඇත."
                                }
                                3 -> {
                                    // Epigraphy
                                    questionEn = "What is the term used for ancient inscriptions carved on rock faces or stone slabs?"
                                    questionSi = "ගල් මතුපිට හෝ සෙල්ලිපි වල කොටවා ඇති අතීත ලේඛන හඳුන්වන්නේ කුමන නමකින්ද?"
                                    optionsEn = listOf("Inscriptions", "Manuscripts", "Ola books", "Scrolls")
                                    optionsSi = listOf("සෙල්ලිපි", "අත්පිටපත්", "පුස්කොළ පොත්", "තල්පත්")
                                    correctIndex = 0
                                    hintEn = "They are direct sources of Sri Lankan history."
                                    hintSi = "ඒවා ලක් ඉතිහාසය හැදෑරීමට ඇති සෘජු ලිඛිත මූලාශ්‍ර වේ."
                                }
                            }
                        }
                        8 -> {
                            // Grade 8 History
                            when (s) {
                                1 -> {
                                    // Dambadeniya transition
                                    questionEn = "Which king defended the country against Magha's invasion and ruled from Dambadeniya?"
                                    questionSi = "කාලිංග මාඝ ආක්‍රමණයට එරෙහිව සටන් වැද දඹදෙණිය අගනුවර කරගත් රජතුමා කවුද?"
                                    optionsEn = listOf("Vijayabahu III", "Parakramabahu II", "Bosath Vijayabahu", "Bhuvanekabahu I")
                                    optionsSi = listOf("තුන්වන විජයබාහු", "දෙවන පරාක්‍රමබාහු", "බෝසත් විජයබාහු", "පළමු බුවනෙකබාහු")
                                    correctIndex = 0
                                    hintEn = "The founder of the Dambadeniya dynasty."
                                    hintSi = "දඹදෙණි රාජවංශයේ නිර්මාතෘවරයා මොහුය."
                                }
                                2 -> {
                                    // Ancient reservoirs structures
                                    questionEn = "What is the special ancient engineering structure used to control water outflow in reservoirs?"
                                    questionSi = "වැව්වලින් පිටවන ජල පීඩනය පාලනය කිරීම සඳහා භාවිත කළ පුරාණ වාරි තාක්ෂණික ක්‍රමය කුමක්ද?"
                                    optionsEn = listOf("Bisokotuwa", "Sorowwa", "Sluice Gate", "Spillway")
                                    optionsSi = listOf("බිසෝකොටුව", "සොරොව්ව", "පිටවාන", "රැලිපන්නය")
                                    correctIndex = 0
                                    hintEn = "It is considered a magnificent inventions of ancient irrigation."
                                    hintSi = "එය ලොව මෙතෙක් බිහිවූ විශිෂ්ටතම වාරි තාක්ෂණික නිපැයුමකි."
                                }
                                3 -> {
                                    // Kotte Kingdom
                                    questionEn = "Under which king did the Kotte Kingdom reach its golden era of literature?"
                                    questionSi = "කෝට්ටේ යුගයේ සාහිත්‍යයේ ස්වර්ණමය යුගය බිහිවූයේ කුමන රජ දවසද?"
                                    optionsEn = listOf("Parakramabahu VI", "Parakramabahu VIII", "Sapumal Kumaraya", "Dharmapala")
                                    optionsSi = listOf("හයවන පරාක්‍රමබාහු", "අටවන පරාක්‍රමබාහු", "සපුමල් කුමාරයා", "ධර්මපාල රජතුමා")
                                    correctIndex = 0
                                    hintEn = "He united Sri Lanka under one single rule for the last time."
                                    hintSi = "මුළු ලක්දිව අවසන් වරට එක්සේසත් කළ රජතුමා මොහුය."
                                }
                            }
                        }
                        9 -> {
                            // Grade 9 History
                            when (s) {
                                1 -> {
                                    // Kandyan Kingdom
                                    questionEn = "Who was the founder of the Kandyan Kingdom?"
                                    questionSi = "මහනුවර සෙංකඩගල රාජධානියේ ප්‍රථම පාලකයා සහ නිර්මාතෘ කවුද?"
                                    optionsEn = listOf("Senasammata Vikramabahu", "Wimaladharmasuriya I", "Rajasinghe II", "Sri Vikrama Rajasinghe")
                                    optionsSi = listOf("සේනාසම්මත වික්‍රමබාහු", "පළමු විමලධර්මසූරිය", "දෙවන රාජසිංහ", "ශ්‍රී වික්‍රම රාජසිංහ")
                                    correctIndex = 0
                                    hintEn = "He established the capital in the late fifteenth century."
                                    hintSi = "15 වන සියවසේ අගභාගයේදී කන්ද උඩරට රාජධානිය ආරම්භ කළේ මොහුය."
                                }
                                2 -> {
                                    // Resistance heroes
                                    questionEn = "Who was the heroic leader of the Uva Wellassa Rebellion in 1818?"
                                    questionSi = "1818 ඌව වෙල්ලස්ස මහා නිදහස් අරගලයේ වීරෝදාර නායකයා කවුද?"
                                    optionsEn = listOf("Keppetipola", "Gongalegoda Banda", "Veera Puran Appu", "Madanma Kumaraya")
                                    optionsSi = listOf("කැප්පෙටිපොල දිසාවේ", "ගොංගාලේගොඩ බණ්ඩා", "වීර පුරන් අප්පු", "මඩන්ම කුමාරයා")
                                    correctIndex = 0
                                    hintEn = "He was the Disawa of Uva during British rule."
                                    hintSi = "ඉංග්‍රීසි පාලන සමයේ ඌව පළාතේ දිසාවේ තනතුර දරන ලද වීරයායි."
                                }
                                3 -> {
                                    // Treaties
                                    questionEn = "In which year was the Kandyan Convention signed, ceding control to the British?"
                                    questionSi = "උඩරට ගිවිසුම අත්සන් කරමින් ලංකාව සම්පූර්ණයෙන්ම ඉංග්‍රීසීන්ට යටත් වූයේ කුමන වර්ෂයේද?"
                                    optionsEn = listOf("1815", "1796", "1803", "1833")
                                    optionsSi = listOf("එක්දහස් අටසිය පහළොව", "එක්දහස් හත්සිය අනූහය", "එක්දහස් අටසිය තුන", "එක්දහස් අටසිය තිස්තුන")
                                    correctIndex = 0
                                    hintEn = "Signed on March 2nd."
                                    hintSi = "එම වසරේ මාර්තු මස දෙවන දින මෙම ගිවිසුම අත්සන් කරන ලදී."
                                }
                            }
                        }
                        10 -> {
                            // Grade 10 History
                            when (s) {
                                1 -> {
                                    // Colebrooke reforms
                                    questionEn = "Which commission introduced administrative reforms to Sri Lanka in 1833?"
                                    questionSi = "ක්‍රිස්තු වර්ෂ 1833 දී ලක්දිවට පරිපාලන ප්‍රතිසංස්කරණ හඳුන්වා දුන් කොමිසම කුමක්ද?"
                                    optionsEn = listOf("Colebrooke Cameron", "Donoughmore", "Soulbury", "Manning")
                                    optionsSi = listOf("කෝල්බෲක් කැමරන්", "ඩොනමෝර්", "සෝල්බරි", "මැනිං")
                                    correctIndex = 0
                                    hintEn = "It recommended a single legislative council and unified administration."
                                    hintSi = "ව්‍යවස්ථාදායක සභාවක් පත්කිරීම සහ ඒකාබද්ධ පාලනයක් නිර්දේශ කළේය."
                                }
                                2 -> {
                                    // Independence movement
                                    questionEn = "Who is revered as the 'Father of the Nation' in Sri Lanka, becoming prime minister in 1947?"
                                    questionSi = "ශ්‍රී ලංකාවේ 'නිදහසේ පියා' ලෙස බහුමානයට පාත්‍ර වූ සහ ප්‍රථම අග්‍රාමාත්‍යවරයා වූයේ කවුද?"
                                    optionsEn = listOf("D. S. Senanayake", "F. R. Senanayake", "S. W. R. D. Bandaranaike", "James Peiris")
                                    optionsSi = listOf("ඩී. ඇස්. සේනානායක", "ඇෆ්. ආර්. සේනානායක", "ඇස්. ඩබ්ලිව්. ආර්. ඩී. බණ්ඩාරනායක", "ජේම්ස් පීරිස්")
                                    correctIndex = 0
                                    hintEn = "He actively led the negotiations for independence from British rule."
                                    hintSi = "ඉංග්‍රීසි පාලනයෙන් නිදහස ලබාගැනීමේ සාකච්ඡා වලට මූලිකත්වය දුන් නායකයායි."
                                }
                                3 -> {
                                    // Constitutional history
                                    questionEn = "In which year did Sri Lanka officially declare itself a sovereign Republic, changing its name from Ceylon?"
                                    questionSi = "ලංකාව 'ශ්‍රී ලංකා ජනරජයක්' බවට නිල වශයෙන් ප්‍රකාශයට පත් කළේ කුමන වර්ෂයේද?"
                                    optionsEn = listOf("1972", "1948", "1956", "1978")
                                    optionsSi = listOf("එක්දහස් නවසිය හැත්තෑදෙක", "එක්දහස් නවසිය හතළිස්අට", "එක්දහස් නවසිය පනස්හය", "එක්දහස් නවසිය හැත්තෑඅට")
                                    correctIndex = 0
                                    hintEn = "Under the first Republican Constitution introduced by Sirimavo Bandaranaike's government."
                                    hintSi = "සිරිමාවෝ බණ්ඩාරනායක මැතිණියගේ රජය යටතේ ප්‍රථම ජනරජ ව්‍යවස්ථාව හඳුන්වා දෙන ලදී."
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
