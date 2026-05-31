package com.example.data

data class Question(
    val text: String,
    val options: List<String>,
    val correctAnswerIndex: Int,
    val hint: String
)

object QuestionPool {
    fun getOfflineQuestions(grade: Int, subject: String, lang: String): List<Question> {
        val isSinhala = lang == "si"
        
        return when (subject.lowercase()) {
            "math" -> {
                if (isSinhala) {
                    listOf(
                        Question(
                            text = "මහා මන්ත්‍රීවරයා සතුව මායා දියර බෝතල් $grade ක් ඇත. ඔහු තව 5 ක් සොයා ගනී. දැන් ඔහු සතුව දියර බෝතල් කියක් තිබේද?",
                            options = listOf("${grade + 3}", "${grade + 5}", "${grade + 6}", "${grade * 5}"),
                            correctAnswerIndex = 1,
                            hint = "එකතු කිරීමේ මන්ත්‍රය පාවිච්චි කරන්න: $grade + 5"
                        ),
                        Question(
                            text = "කූඩු කළ මකරෙකුගේ බිත්තර 10 කින් 3 ක් පුපුරා ගියේය. නොකැඩුණු ඉතිරි මකර බිත්තර ගණන කොපමණද?",
                            options = listOf("5", "6", "7", "8"),
                            correctAnswerIndex = 2,
                            hint = "10 න් 3 ක් අඩු කරන්න."
                        ),
                        Question(
                            text = "රණශූරයෙක් රන් කාසි 15 ක් සමානව තිදෙනෙක් අතර බෙදා දෙයි. එක් අයෙකුට ලැබෙන රන් කාසි ප්‍රමාණය කොපමණද?",
                            options = listOf("3", "4", "5", "6"),
                            correctAnswerIndex = 2,
                            hint = "15 න් 3 බෙදන්න."
                        ),
                        Question(
                            text = "කඩු ශිල්පියෙකු දිනකට පැය 2 ක් පුහුණු වේ. දින 7 කදී ඔහු පුහුණු වන මුළු පැය ගණන කොපමණද?",
                            options = listOf("10", "12", "14", "16"),
                            correctAnswerIndex = 2,
                            hint = "7 වරක් 2 ගුණ කරන්න."
                        ),
                        Question(
                            text = "මහා ප්‍රඥාවන්තයෙකුගේ පොත් රාක්ක ගණන 4 කි. එක් රාක්කයක මන්ත්‍ර පොත් 5 බැගින් ඇත. මුළු මන්ත්‍ර පොත් ගණන කොපමණද?",
                            options = listOf("16", "20", "24", "28"),
                            correctAnswerIndex = 1,
                            hint = "4 ගුණ කිරීම 5."
                        )
                    )
                } else {
                    listOf(
                        Question(
                            text = "The High Wizard has $grade health potions. He finds 5 more in a chest. How many potions does he have now?",
                            options = listOf("${grade + 3}", "${grade + 5}", "${grade + 6}", "${grade * 5}"),
                            correctAnswerIndex = 1,
                            hint = "Cast the addition spell: $grade + 5"
                        ),
                        Question(
                            text = "A dragon nest had 10 eggs. 3 hatched into baby dragons. How many unhatched eggs remain?",
                            options = listOf("5", "6", "7", "8"),
                            correctAnswerIndex = 2,
                            hint = "Subtract 3 from 10."
                        ),
                        Question(
                            text = "An elven archer shoots 12 arrows in 3 equal rounds. How many arrows per round?",
                            options = listOf("2", "4", "6", "8"),
                            correctAnswerIndex = 1,
                            hint = "Divide 12 by 3."
                        ),
                        Question(
                            text = "A knight's iron shield weighs 8 kilograms, and the longsword weighs 6 kilograms. What is their total weight?",
                            options = listOf("12 kg", "13 kg", "14 kg", "15 kg"),
                            correctAnswerIndex = 2,
                            hint = "Add the shield and sword weights."
                        ),
                        Question(
                            text = "A goblin hoard contains 4 chests. Each chest holds 5 gold rings. How many rings in total?",
                            options = listOf("15", "18", "20", "22"),
                            correctAnswerIndex = 2,
                            hint = "Multiply 4 chests by 5 rings."
                        )
                    )
                }
            }
            "science" -> {
                if (isSinhala) {
                    listOf(
                        Question(
                            text = "අපේ පෘථිවි ග්‍රහලෝකයට ආලෝකය සහ තාපය ලබාදෙන ප්‍රධානතම මායා තාරකාව කුමක්ද?",
                            options = "සඳ (Moon), සූර්යයා (Sun), අඟහරු (Mars), බ්‍රහස්පති (Jupiter)".split(", "),
                            correctAnswerIndex = 1,
                            hint = "ප්‍රඥාවන්තයෝ දහවල් කාලයේ මෙයට ප්‍රණාමය පුද කරති."
                        ),
                        Question(
                            text = "ජලය රත් වී වාෂ්ප බවට පත්වීමේ භෞතික ක්‍රියාවලිය හඳුන්වන්නේ කුමක්ද?",
                            options = "ඝනීභවනය, වාෂ්පීකරණය, ද්‍රවීකරණය, මිදීම".split(", "),
                            correctAnswerIndex = 1,
                            hint = "ජල වාෂ්ප අහසට නැඟෙන ක්‍රියාවලියයි."
                        ),
                        Question(
                            text = "පැලෑටි තමන්ට අවශ්‍ය ආහාර සකස් කර ගන්නා මායා ක්‍රියාවලිය කුමක්ද?",
                            options = "ප්‍රභාසංශ්ලේෂණය (Photosynthesis), ශ්වසනය, උත්ස්වේදනය, පරිවෘත්තිය".split(", "),
                            correctAnswerIndex = 0,
                            hint = "සූර්යාලෝකය භාවිතා කර ආහාර නිපදවීමයි."
                        ),
                        Question(
                            text = "අප ආශ්වාස කරන, ජීවය පවත්වා ගැනීමට අත්‍යවශ්‍ය වන වායුව කුමක්ද?",
                            options = "කාබන් ඩයොක්සයිඩ්, නයිට්‍රජන්, ඔක්සිජන්, හීලියම්".split(", "),
                            correctAnswerIndex = 2,
                            hint = "මිනිසාගේ සහ සතුන්ගේ හුස්ම ගැනීම සඳහා අවශ්‍ය වේ."
                        ),
                        Question(
                            text = "ශක්ති ප්‍රභව අතරින් කිසිදා නොනිමෙන පුනර්ජනනීය බලශක්තියක් වන්නේ?",
                            options = "ගල් අඟුරු, සූර්ය බලශක්තිය, ඛනිජ තෙල්, ස්වභාවික වායුව".split(", "),
                            correctAnswerIndex = 1,
                            hint = "සූර්යයාගෙන් ලැබෙන අපරිමිත බලය."
                        )
                    )
                } else {
                    listOf(
                        Question(
                            text = "Which celestial body provides our kingdom with heat, light, and energy?",
                            options = listOf("Moon", "The Sun", "Mars", "Polar Star"),
                            correctAnswerIndex = 1,
                            hint = "It shines brightly in the daytime sky."
                        ),
                        Question(
                            text = "The air we breathe is full of gases. Which gas is essential for human life and respiration?",
                            options = listOf("Carbon Dioxide", "Nitrogen", "Oxygen", "Helium"),
                            correctAnswerIndex = 2,
                            hint = "Plants generate this gas during photosynthesis."
                        ),
                        Question(
                            text = "What state of matter is water when it is heated to a boil?",
                            options = listOf("Solid", "Liquid", "Gas (Steam)", "Plasma"),
                            correctAnswerIndex = 2,
                            hint = "Think about steam escaping the cauldron."
                        ),
                        Question(
                            text = "What is the force that pulls knights, dragons, and apples down toward the earth?",
                            options = listOf("Friction", "Gravity", "Magnetism", "Wind"),
                            correctAnswerIndex = 1,
                            hint = "Discovered by Sir Isaac Newton under an apple tree."
                        ),
                        Question(
                            text = "A plant's leaves look green because of a special substance. What is it called?",
                            options = listOf("Chlorophyll", "Sap", "Flower dust", "Oxygen"),
                            correctAnswerIndex = 0,
                            hint = "It absorbs starlight and sunlight to perform photosynthesis."
                        )
                    )
                }
            }
            "english" -> {
                if (isSinhala) {
                    listOf(
                        Question(
                            text = "පහත දැක්වෙන වචන අතුරින් නාම පදයක් (Noun) වන්නේ කුමක්ද?",
                            options = "Run, Knight, Brave, Quickly".split(", "),
                            correctAnswerIndex = 1,
                            hint = "පුද්ගලයෙකු, ස්ථානයක් හෝ වස්තුවක් හඳුන්වන වචනයකි."
                        ),
                        Question(
                            text = "ශබ්ද විද්‍යාත්මකව 'book' යන වචනයට සමාන ශබ්දයක් ඇති වචනය තෝරන්න.",
                            options = "Look, Boat, Bark, Bake".split(", "),
                            correctAnswerIndex = 0,
                            hint = "විශාල ශබ්දයකින් තොරව අවසානයේ 'ook' ශබ්දය එයි."
                        ),
                        Question(
                            text = "'The dragon is ______ than a horse.' හිස්තැනට සුදුසු වචනය කුමක්ද?",
                            options = "big, bigger, biggest, bigness".split(", "),
                            correctAnswerIndex = 1,
                            hint = "සංසන්දනාත්මක පද පාවිච්චි කරන්න."
                        ),
                        Question(
                            text = "'The king rules the country.' මෙහි ක්‍රියා පදය (Verb) කුමක්ද?",
                            options = "king, rules, country, the".split(", "),
                            correctAnswerIndex = 1,
                            hint = "රජු සිදු කරන ක්‍රියාවයි."
                        ),
                        Question(
                            text = "'Happy' යන වචනයේ ප්‍රතිවිරුද්ධ වචනය කුමක්ද?",
                            options = "Sad, Angry, Joyful, Excited".split(", "),
                            correctAnswerIndex = 0,
                            hint = "දුක හඟවන වචනයයි."
                        )
                    )
                } else {
                    listOf(
                        Question(
                            text = "Identify the noun in this sentence: 'The swift dragon flew across the cloudy sky.'",
                            options = listOf("flew", "swift", "dragon", "across"),
                            correctAnswerIndex = 2,
                            hint = "It is the name of the magical creature."
                        ),
                        Question(
                            text = "Complete the scroll: 'We must ______ quickly to escape the goblin dungeon.'",
                            options = listOf("running", "runs", "ran", "run"),
                            correctAnswerIndex = 3,
                            hint = "Use the base form of the verb."
                        ),
                        Question(
                            text = "What is the antonym of the medieval word 'brave'?",
                            options = listOf("Valiant", "Cowardly", "Strong", "Fierce"),
                            correctAnswerIndex = 1,
                            hint = "The opposite of having courage."
                        ),
                        Question(
                            text = "Which of the following is an adjective (describing word)?",
                            options = listOf("Castle", "Glows", "Magical", "Slowly"),
                            correctAnswerIndex = 2,
                            hint = "It describes properties like mystery or magic."
                        ),
                        Question(
                            text = "Choose the correct spelling of the protective metal garments:",
                            options = listOf("Armur", "Amor", "Armor", "Armourr"),
                            correctAnswerIndex = 2,
                            hint = "It deflects arrow impacts."
                        )
                    )
                }
            }
            else -> { // history / social studies
                if (isSinhala) {
                    listOf(
                        Question(
                            text = "ලංකාවේ ප්‍රථම අගනුවර සහ මහා විහාර රැසක් පිහිටි මායා නගරය කුමක්ද?",
                            options = "පොළොන්නරුව, අනුරාධපුරය, මහනුවර, සීගිරිය".split(", "),
                            correctAnswerIndex = 1,
                            hint = "ප්‍රථම රාජධානියයි."
                        ),
                        Question(
                            text = "සීගිරිය කාශ්‍යප රජතුමා විසින් ඉදිකරන ලද්දේ කුමන සත්වයාගේ හැඩයටද?",
                            options = "අලියා, සිංහයා, කොටි, මොනරා".split(", "),
                            correctAnswerIndex = 1,
                            hint = "සිංහ පර්වතය ලෙසද හැඳින්වේ."
                        ),
                        Question(
                            text = "ලෝකයේ පැරණිතම පාලම් හා වාරි තාක්‍ෂණය සඳහා ලක්දිව ප්‍රසිද්ධ වැව කුමක්ද?",
                            options = "පරාක්‍රම සමුද්‍රය, කලා වැව, මින්නේරිය වැව, තිසා වැව".split(", "),
                            correctAnswerIndex = 1,
                            hint = "ධාතුසේන රජතුමා විසින් ඉදිකරන ලදී."
                        ),
                        Question(
                            text = "ලංකාවේ අවසන් රජු ශ්‍රී වික්‍රම රාජසිංහ රජතුමා පරාජය කර අප රට යටත් කරගත් බාහිර බලවේගය කවුද?",
                            options = "ලන්දේසි, පෘතුගීසි, බ්‍රිතාන්‍ය, ප්‍රංශ".split(", "),
                            correctAnswerIndex = 2,
                            hint = "ඉංග්‍රීසි ජාතිකයන්ය."
                        ),
                        Question(
                            text = "ලක්දිවට බුදු දහම රැගෙන ආවේ කවුරුන් විසිනිද?",
                            options = "මිහිඳු මහ රහතන් වහන්සේ, සංඝමිත්තා මෙහෙණින් වහන්සේ, බුදුන් වහන්සේ, සර්වඥයන්".split(", "),
                            correctAnswerIndex = 0,
                            hint = "මුටසීව රජ දවසෙන් පසු දේවානම්පියතිස්ස රජ සමයේ සිදු විය."
                        )
                    )
                } else {
                    listOf(
                        Question(
                            text = "Which ruler built the legendary Sigiriya fortress on a massive lion paw-shaped rock?",
                            options = listOf("King Dutugemunu", "King Kasyapa", "King Parakramabahu", "King Devanampiyatissa"),
                            correctAnswerIndex = 1,
                            hint = "It is also called the Lion Rock."
                        ),
                        Question(
                            text = "Ancient civilisations often developed near which of these elements to ensure survival?",
                            options = listOf("Volcanoes", "Rivers", "Deserts", "Glaciers"),
                            correctAnswerIndex = 1,
                            hint = "Water is vital for crops, knights, and towns."
                        ),
                        Question(
                            text = "What ancient monument is considered one of the Seven Wonders of the Ancient World?",
                            options = listOf("The Great Pyramid of Giza", "Eiffel Tower", "The Colosseum", "Big Ben"),
                            correctAnswerIndex = 0,
                            hint = "A giant stone structure built for pharaohs in Egypt."
                        ),
                        Question(
                            text = "Who traveled from Italy to China along the Silk Road and wrote a famous journal about his journey?",
                            options = listOf("Columbus", "Marco Polo", "Alexander the Great", "Blackbeard"),
                            correctAnswerIndex = 1,
                            hint = "A famous merchant explorer."
                        ),
                        Question(
                            text = "Which island was named 'Taprobane' by the ancient Greeks and Romans?",
                            options = listOf("Sri Lanka", "Madagascar", "Iceland", "Cyprus"),
                            correctAnswerIndex = 0,
                            hint = "The pearl of the Indian Ocean."
                        )
                    )
                }
            }
        }
    }
}
