package com.example.data

object HintGenerator {

    fun generateHint(question: String, options: List<String>, correctAnswerIndex: Int, language: String): String {
        val qLower = question.lowercase()
        val isSinhala = language == "Sinhala"

        // Math-specific Hints
        if (qLower.contains("+") || qLower.contains("add") || qLower.contains("එකතු කරන්න") || qLower.contains("එකතුව")) {
            return if (isSinhala) {
                "සංඛ්‍යා දෙක එකතු කර ලැබෙන රීතිය ගැන සිතන්න."
            } else {
                "Add the two numbers step-by-step; notice the ones and tens places."
            }
        }
        if (qLower.contains("-") || qLower.contains("subtract") || qLower.contains("අඩු කරන්න")) {
            return if (isSinhala) {
                "විශාල සංඛ්‍යාවෙන් කුඩා සංඛ්‍යාව අඩු කර වෙනස සොයන්න."
            } else {
                "Subtract the smaller value from the larger one to find the difference."
            }
        }
        if (qLower.contains("x") || qLower.contains("times") || qLower.contains("වරක්") || qLower.contains("ගුණ කරන්න")) {
            return if (isSinhala) {
                "ගුණ කිරීම යනු එකම සංඛ්‍යාව නැවත නැවත එකතු කිරීමයි; අදාළ මග් පේළිය සිහි කරන්න."
            } else {
                "Multiplication is repeated addition of the same number group."
            }
        }
        if (qLower.contains("divide") || qLower.contains("බෙදන්න")) {
            return if (isSinhala) {
                "මුළු ප්‍රමාණය සමාන කාණ්ඩවලට බෙදා වෙන් කර බලන්න."
            } else {
                "Try sharing the total amount equally into the specified number of groups."
            }
        }
        if (qLower.contains("gradient") || qLower.contains("අනුක්‍රමණය")) {
            return if (isSinhala) {
                "අනුක්‍රමණය සෙවීම සඳහා (y2 - y1) / (x2 - x1) සූත්‍රය භාවිත කරන්න."
            } else {
                "Use the slope formula (y2 - y1) / (x2 - x1) with the given coordinates."
            }
        }
        if (qLower.contains("midpoint") || qLower.contains("මධ්‍ය ලක්ෂ්‍ය")) {
            return if (isSinhala) {
                "X ඛණ්ඩාංක දෙකේ එකතුවේ මධ්‍ය අගය සහ Y ඛණ්ඩාංක දෙකේ එකතුවේ මධ්‍ය අගය සොයන්න."
            } else {
                "Find the average of the two X coordinates and the two Y coordinates."
            }
        }
        if (qLower.contains("probability") || qLower.contains("සම්භාවිතාවය")) {
            return if (isSinhala) {
                "අදාළ අර්ධ සිදුවීම් ගණන, මුළු සිදුවීම් ගණනින් බෙදා භාගයක් ලෙස දක්වන්න."
            } else {
                "Divide the number of specific favorable outcomes by the total number of options."
            }
        }

        // Science-specific Hints
        if (qLower.contains("living") || qLower.contains("සජීවී") || qLower.contains("පණ ඇති")) {
            return if (isSinhala) {
                "හුස්ම ගැනීමට, වර්ධනය වීමට සහ ආහාර ගැනීමට හැකි සජීවී දේ ගැන සලකා බලන්න."
            } else {
                "Identify what can grow, feed, breathe, and reproduce naturally."
            }
        }
        if (qLower.contains("digestive") || qLower.contains("ආහාර ජීරණ")) {
            return if (isSinhala) {
                "ආහාර අප ශරීරයට උකහා ගැනීමට උපකාර වන පද්ධතිය සිහිපත් කරන්න."
            } else {
                "The system responsible for breaking down food into nutrients for survival."
            }
        }
        if (qLower.contains("gravity") || qLower.contains("ගුරුත්වාකර්ෂණය")) {
            return if (isSinhala) {
                "ඕනෑම වස්තුවක් පෘථිවිය දෙසට ඇද තබා ගන්නා අදෘශ්‍යමාන බලය කුමක්ද?"
            } else {
                "Think of the force that pulls falling leaves and rain down to the ground."
            }
        }
        if (qLower.contains("plant") || qLower.contains("ශාක") || qLower.contains("පැලෑටිය")) {
            return if (isSinhala) {
                "ප්‍රභාසංස්ලේෂණය මඟින් තමාටම ආහාර සාදාගන්නා හරිත ජීවීන් පිළිබඳව හිතන්න."
            } else {
                "Identify the organisms that grow in soil and prepare food using sunlight."
            }
        }

        // English Grammar Hints
        if (qLower.contains("synonym") || qLower.contains("සමාන තේරුම්") || qLower.contains("සමාන වචන")) {
            return if (isSinhala) {
                "ප්‍රශ්නයේ ඇති වචනයට සමාන අදහසක් ඇති වෙනත් වචනයක් විකල්ප අතරින් තෝරන්න."
            } else {
                "Look for an alternative word that expresses the same core meaning."
            }
        }
        if (qLower.contains("opposite") || qLower.contains("ප්‍රතිවිරුද්ධ")) {
            return if (isSinhala) {
                "ලබා දී ඇති වචනයේ අර්ථයට සම්පූර්ණයෙන්ම වෙනස් ප්‍රතිවිරුද්ධ අර්ථය ඇති පදය තෝරන්න."
            } else {
                "Pick the term that means the exact reverse or negation of the word."
            }
        }
        if (qLower.contains("past tense") || qLower.contains("අතීත කාල")) {
            return if (isSinhala) {
                "දැනටමත් සිදු කර අවසන් වූ ක්‍රියාවක් මෙයින් නිරූපණය වේ."
            } else {
                "Focus on the verb form used to state that an action has already finished."
            }
        }
        if (qLower.contains("preposition") || qLower.contains("නිපාත")) {
            return if (isSinhala) {
                "වස්තුවක් පවතින ස්ථානය හෝ දිශාව සම්බන්ධ කරන නිපාත පදය කුමක්ද?"
            } else {
                "Think about the spatial relationship or location (e.g. inside, on top, below)."
            }
        }

        // History-specific Hints
        if (qLower.contains("vijaya") || qLower.contains("විජය")) {
            return if (isSinhala) {
                "ශ්‍රී ලංකාවේ මුල්ම රජු ලෙස ඓතිහාසික සාහිත්‍යයේ දැක්වෙන චරිතය ගැන සිතන්න."
            } else {
                "Consider the legendary figure recognized as the leader of the first settlers."
            }
        }
        if (qLower.contains("dutugemunu") || qLower.contains("දුටුගැමුණු")) {
            return if (isSinhala) {
                "රජරට එක්සේසත් කර රුවන්වැලි මහා සෑය ඉදි කළ මහා රජු පිළිබඳව සිතන්න."
            } else {
                "Think of the Anuradhapura king who unified the island and built great stupas."
            }
        }
        if (qLower.contains("portuguese") || qLower.contains("පෘතුගීසි")) {
            return if (isSinhala) {
                "1505 වර්ෂයේදී ශ්‍රී ලංකාවට පැමිණි ප්‍රථම යුරෝපා ජාතිය කවුද?"
            } else {
                "Think of the first Western power that arrived on our coast in 1505."
            }
        }
        if (qLower.contains("dutch") || qLower.contains("ලන්දේසි")) {
            return if (isSinhala) {
                "බලකොටු ඉදි කළ සහ රෝම ලන්දේසි නීතිය හඳුන්වා දුන් විදේශිකයන් කවුද?"
            } else {
                "Consider the European traders who built costal forts and introduced new law concepts."
            }
        }
        if (qLower.contains("british") || qLower.contains("බ්‍රිතාන්‍ය") || qLower.contains("බ්රිතාන්ය")) {
            return if (isSinhala) {
                "1815 උඩරට ගිවිසුම මඟින් මුළු ලංකාවම යටත් කරගත් අවසන් අධිරාජ්‍යය කුමක්ද?"
            } else {
                "Think of the empire that signed the treaty in 1815 and ruled until 1948."
            }
        }

        // General Fallback Hints
        return if (isSinhala) {
            val correctOpt = if (correctAnswerIndex in options.indices) options[correctAnswerIndex] else ""
            if (correctOpt.isNotEmpty()) {
                "විකල්ප අතර ඇති සුදුසුම අර්ථය සහිත පිළිතුර මෙනෙහි කරන්න."
            } else {
                "ප්‍රශ්නය හොඳින් කියවා නිවැරදි විකල්පය තෝරාගන්න."
            }
        } else {
            "Look closely at the choices; find the one that fits best conceptually."
        }
    }
}
