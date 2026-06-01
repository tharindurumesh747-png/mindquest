package com.example.data

object QuestionBank {
    private val questions = mutableListOf<Question>()

    init {
        // ═══════════════════════════════════════
        // SCIENCE QUESTIONS
        // ═══════════════════════════════════════
        questions.add(Question(
            id = "sci_g3_s1_1",
            question = "Which of the following is a living thing?",
            questionSinhala = "පහත දැක්වෙන දේවලින් සජීවී දෙයක් වන්නේ කුමක්ද?",
            options = listOf("Stone", "Plant", "Toy Car", "Book"),
            optionsSinhala = listOf("ගලක්", "පැලෑටියක්", "සෙල්ලම් කාර් එකක්", "පොතක්"),
            correctAnswerIndex = 1,
            grade = 3, subject = "Science", stage = 1, difficulty = "easy",
            hint = "Living things need water, air, and can grow over time.",
            hintSinhala = "සජීවී දේවලට ජලය සහ වායුව අවශ්‍ය වන අතර කාලයත් සමඟ වර්ධනය වේ."
        ))

        questions.add(Question(
            id = "sci_g3_s1_2",
            question = "Which organ is responsible for pumping blood?",
            questionSinhala = "ලේ පොම්ප කිරීම සිදු කරන ප්‍රධාන අවයවය කුමක්ද?",
            options = listOf("Lungs", "Brain", "Heart", "Stomach"),
            optionsSinhala = listOf("පෙනහළු", "මොළය", "හෘදය", "ආමාශය"),
            correctAnswerIndex = 2,
            grade = 3, subject = "Science", stage = 1, difficulty = "easy",
            hint = "It beats continuously inside your chest.",
            hintSinhala = "එය ඔබේ පපුව ඇතුළත නොකඩවා ගැහෙමින් පවතී."
        ))

        questions.add(Question(
            id = "sci_g3_s1_3",
            question = "Which part of a plant makes food?",
            questionSinhala = "ශාකයක ආහාර නිෂ්පාදනය කරන කොටස කුමක්ද?",
            options = listOf("Root", "Leaf", "Flower", "Stem"),
            optionsSinhala = listOf("මුල", "පත්‍රය", "මල", "කඳ"),
            correctAnswerIndex = 1,
            grade = 3, subject = "Science", stage = 1, difficulty = "medium",
            hint = "This part absorbs sunlight and is usually green.",
            hintSinhala = "මෙම කොටස හිරු එළිය අවශෝෂණය කර ගන්නා අතර සාමාන්‍යයෙන් කොළ පාට වේ."
        ))

        // Grade 4 Science
        questions.add(Question(
            id = "sci_g4_s1_1",
            question = "Which gas do plants absorb during photosynthesis?",
            questionSinhala = "ප්‍රභාසංස්ලේෂණයේදී ශාක අවශෝෂණය කරන්නේ කුමන වායුවද?",
            options = listOf("Oxygen", "Nitrogen", "Carbon dioxide", "Hydrogen"),
            optionsSinhala = listOf("ඔක්සිජන්", "නයිට්‍රජන්", "කාබන් ඩයොක්සයිඩ්", "හයිඩ්‍රජන්"),
            correctAnswerIndex = 2,
            grade = 4, subject = "Science", stage = 1, difficulty = "medium",
            hint = "We exhale this gas, and plants take it in to build sugars.",
            hintSinhala = "අප පිටකරන මෙම වායුව ශාක විසින් ආහාර සෑදීමට ලබා ගනී."
        ))

        questions.add(Question(
            id = "sci_g4_s1_2",
            question = "What is the boiling point of pure water in Celsius?",
            questionSinhala = "පිරිසිදු ජලය නටන උෂ්ණත්වය සෙල්සියස් අංශක කීයද?",
            options = listOf("50", "100", "150", "200"),
            optionsSinhala = listOf("50", "100", "150", "200"),
            correctAnswerIndex = 1,
            grade = 4, subject = "Science", stage = 1, difficulty = "easy",
            hint = "Think of a triple-digit round number where boiling occurs at normal pressure.",
            hintSinhala = "සාමාන්‍ය පීඩනයේදී ජලය වාෂ්ප වීමට පටන් ගන්නා ඉලක්කම් තුනේ අගය සිතන්න."
        ))

        // Grade 5 Science
        questions.add(Question(
            id = "sci_g5_s1_1",
            question = "Which force pulls all objects down to Earth?",
            questionSinhala = "සියලුම වස්තූන් පෘථිවිය දෙසට ඇද තබා ගන්නා බලය කුමක්ද?",
            options = listOf("Magnetism", "Friction", "Gravity", "Electricity"),
            optionsSinhala = listOf("චුම්බකත්වය", "ඝර්ෂණය", "ගුරුත්වාකර්ෂණය", "විද්‍යුත් බලය"),
            correctAnswerIndex = 2,
            grade = 5, subject = "Science", stage = 1, difficulty = "easy",
            hint = "Sir Isaac Newton discovered this when an apple fell.",
            hintSinhala = "ඇපල් ගෙඩියක් බිමට වැටීම නිරීක්ෂණය කර අයිසැක් නිව්ටන් සොයාගත් බලයයි."
        ))

        // ═══════════════════════════════════════
        // MATH QUESTIONS
        // ═══════════════════════════════════════
        questions.add(Question(
            id = "math_g3_s1_1",
            question = "What is the sum of 15 and 27?",
            questionSinhala = "15 සහ 27 හි එකතුව කොපමණද?",
            options = listOf("32", "42", "38", "52"),
            optionsSinhala = listOf("32", "42", "38", "52"),
            correctAnswerIndex = 1,
            grade = 3, subject = "Math", stage = 1, difficulty = "easy",
            hint = "Add the ones place first (5 + 7), then the tens.",
            hintSinhala = "පළමුව එකස්ථානයේ ඉලක්කම් (5 + 7) එකතු කර ඉතිරිය දහස්ථානයට එකතු කරන්න."
        ))

        questions.add(Question(
            id = "math_g3_s1_2",
            question = "How many sides does a hexagon have?",
            questionSinhala = "ෂඩාස්‍රයකට පැති කීයක් තිබේද?",
            options = listOf("4", "5", "6", "8"),
            optionsSinhala = listOf("4", "5", "6", "8"),
            correctAnswerIndex = 2,
            grade = 3, subject = "Math", stage = 1, difficulty = "medium",
            hint = "'Hexa' means six in Greek numerical prefixes.",
            hintSinhala = "ග්‍රීක සහ ලතින් ක්‍රමයට අනුව හෙක්සා යනු හයයි."
        ))

        // Grade 4 Math
        questions.add(Question(
            id = "math_g4_s1_1",
            question = "What is 12 multiplied by 8?",
            questionSinhala = "12 ගුණ කිරීම 8 කීයද?",
            options = listOf("86", "96", "106", "116"),
            optionsSinhala = listOf("86", "96", "106", "116"),
            correctAnswerIndex = 1,
            grade = 4, subject = "Math", stage = 1, difficulty = "easy",
            hint = "Think of times tables: 10 times 8 plus 2 times 8.",
            hintSinhala = "ගුණිත වගුව සිහිපත් කරන්න: 10 වරක් 8 ට, 2 වරක් 8 ක් එකතු කරන්න."
        ))

        questions.add(Question(
            id = "math_g4_s1_2",
            question = "Which fraction is equivalent to one-half?",
            questionSinhala = "අර්ධයකට (දෙකෙන් එකකට) සමාන භාගය කුමක්ද?",
            options = listOf("2/3", "2/4", "3/5", "4/6"),
            optionsSinhala = listOf("2/3", "2/4", "3/5", "4/6"),
            correctAnswerIndex = 1,
            grade = 4, subject = "Math", stage = 1, difficulty = "medium",
            hint = "The top number (numerator) must be exactly half of the bottom number (denominator).",
            hintSinhala = "ඉහළ අගය (ලවය) පහළ අගයෙන් (හරය) හරියටම අඩක් විය යුතුය."
        ))

        // Grade 5 Math
        questions.add(Question(
            id = "math_g5_s1_1",
            question = "If x + 15 = 45, what is x?",
            questionSinhala = "x + 15 = 45 නම්, x හි අගය කුමක්ද?",
            options = listOf("20", "25", "30", "35"),
            optionsSinhala = listOf("20", "25", "30", "35"),
            correctAnswerIndex = 2,
            grade = 5, subject = "Math", stage = 1, difficulty = "easy",
            hint = "Subtract 15 from 45 to find the value of x.",
            hintSinhala = "x සෙවීම සඳහා 45 න් 15 ක් අඩු කරන්න."
        ))

        // ═══════════════════════════════════════
        // ENGLISH QUESTIONS
        // ═══════════════════════════════════════
        questions.add(Question(
            id = "eng_g3_s1_1",
            question = "What is the opposite of 'Dark'?",
            questionSinhala = "'Dark' (අඳුරු) යන්නෙහි ප්‍රතිවිරුද්ධ වචනය කුමක්ද?",
            options = listOf("Heavy", "Light", "Sad", "Cold"),
            optionsSinhala = listOf("බර", "ආලෝකය", "කනගාටුදායක", "සීතල"),
            correctAnswerIndex = 1,
            grade = 3, subject = "English", stage = 1, difficulty = "easy",
            hint = "It is what the sun shines on us during the day.",
            hintSinhala = "හිරු පායන විට අප වටා පැතිරෙන දීප්තියයි."
        ))

        questions.add(Question(
            id = "eng_g4_s1_1",
            question = "Choose the past tense of 'Go'.",
            questionSinhala = "'Go' යන්නෙහි අතීත කාල පදය තෝරන්න.",
            options = listOf("Goes", "Gone", "Went", "Going"),
            optionsSinhala = listOf("යයි", "ගොස් ඇත", "ගියා", "යමින් පවතී"),
            correctAnswerIndex = 2,
            grade = 4, subject = "English", stage = 1, difficulty = "easy",
            hint = "An irregular past tense verb starting with W.",
            hintSinhala = "ඩබ්ලිව් අකුරෙන් ආරම්භ වන අක්‍රමවත් අතීත කාල ක්‍රියා පදයකි."
        ))

        questions.add(Question(
            id = "eng_g5_s1_1",
            question = "Fill in the blank: She is interested ______ learning history.",
            questionSinhala = "හිස්තැන පුරවන්න: She is interested ______ learning history.",
            options = listOf("on", "at", "in", "with"),
            optionsSinhala = listOf("on", "at", "in", "with"),
            correctAnswerIndex = 2,
            grade = 5, subject = "English", stage = 1, difficulty = "medium",
            hint = "The adjective 'interested' is always paired with this preposition.",
            hintSinhala = "ඉංග්‍රීසි ව්‍යාකරණ අනුව interested සමඟ සැමවිටම යෙදෙන නිපාතයයි."
        ))

        // ═══════════════════════════════════════
        // HISTORY QUESTIONS (BUG 5 Compliant)
        // ═══════════════════════════════════════
        questions.add(Question(
            id = "hist_g3_s1_1",
            question = "Who is recognized as the first legendary king of Sri Lanka?",
            questionSinhala = "ශ්‍රී ලංකාවේ මුල්ම රජු ලෙස පිළිගැනෙන්නේ කවුද?",
            options = listOf("King Pandukabhaya", "King Dutugemunu", "King Vijaya", "King Devanampiyatissa"),
            optionsSinhala = listOf("පණ්ඩුකාභය රජතුමා", "දුටුගැමුණු රජතුමා", "විජය රජතුමා", "දේවානම්පියතිස්ස රජතුමා"),
            correctAnswerIndex = 2,
            grade = 3, subject = "History", stage = 1, difficulty = "easy",
            hint = "He arrived from India on Vesak day with 700 followers.",
            hintSinhala = "අනුගාමිකයන් හත්සියයක් සමඟ ඉන්දියාවේ සිට පැමිණි තැනැත්තායි."
        ))

        questions.add(Question(
            id = "hist_g4_s1_1",
            question = "Which king built the grand Sigiriya fortress?",
            questionSinhala = "සීගිරි පර්වතය මත මාලිගය සහ බලකොටුව ඉදි කළේ කුමන රජතුමාද?",
            options = listOf("King Kasyapa", "King Dutugemunu", "King Parakramabahu", "King Valagamba"),
            optionsSinhala = listOf("කාශ්‍යප රජතුමා", "දුටුගැමුණු රජතුමා", "පරාක්‍රමබාහු රජතුමා", "වළගම්බා රජතුමා"),
            correctAnswerIndex = 0,
            grade = 4, subject = "History", stage = 1, difficulty = "easy",
            hint = "He sought refuge on the lion rock to protect himself from his brother Moggalana.",
            hintSinhala = "මුගලන් කුමරුගෙන් ආරක්ෂා වීමට සිංහගිරිය මත මාලිගය තැනවූ රජුය."
        ))

        questions.add(Question(
            id = "hist_g5_s1_1",
            question = "In which year did the Portuguese first arrive in Sri Lanka?",
            questionSinhala = "පෘතුගීසීන් ශ්‍රී ලංකාවට ප්‍රථම වරට පැමිණියේ කුමන වර්ෂයේදීද?",
            options = listOf("1505", "1602", "1796", "1815"),
            optionsSinhala = listOf("1505", "1602", "1796", "1815"),
            correctAnswerIndex = 0,
            grade = 5, subject = "History", stage = 1, difficulty = "medium",
            hint = "It happened early in the 16th century, driven by storm to Galle reef.",
            hintSinhala = "දහසයවන සියවසේ මුල් කාලයේදී කුණාටුවකට හසුව ලංකාවට ගසාගෙන ආ වර්ෂයයි."
        ))

        // Duplicate or fallback questions for multiple stages (Stage 2 and 3)
        // Mathematcs Stage 2
        questions.add(Question(
            id = "math_g3_s2_1",
            question = "What is 45 minus 18?",
            questionSinhala = "45 න් 18 ක් අඩු කළ විට ලැබෙන අගය කීයද?",
            options = listOf("23", "27", "35", "37"),
            optionsSinhala = listOf("23", "27", "35", "37"),
            correctAnswerIndex = 1,
            grade = 3, subject = "Math", stage = 2, difficulty = "easy",
            hint = "Subtract 10 from 45, then subtract 8 from the result.",
            hintSinhala = "පළමුව 45 න් 10 ක් අඩු කර, එම අගයෙන් 8 ක් අඩු කරන්න."
        ))

        // History Stage 2
        questions.add(Question(
            id = "hist_g3_s2_1",
            question = "Who built the famous Ruwanwelisaya stupa in Anuradhapura?",
            questionSinhala = "අනුරාධපුරයේ පිහිටි රුවන්වැලි මහා සෑය ඉදි කළ මහා රජු කවුද?",
            options = listOf("King Dutugemunu", "King Tissa", "King Mahasen", "King Vijayabahu"),
            optionsSinhala = listOf("දුටුගැමුණු රජතුමා", "තිස්ස රජතුමා", "මහසෙන් රජතුමා", "විජයබාහු රජතුමා"),
            correctAnswerIndex = 0,
            grade = 3, subject = "History", stage = 2, difficulty = "easy",
            hint = "The epic hero king of ancient Rajarata who unified the island.",
            hintSinhala = "රජරට එක්සේසත් කළ මහා වීර ශ්‍රේෂ්ඨ රජතුමාය."
        ))

        // Science Stage 2
        questions.add(Question(
            id = "sci_g3_s2_1",
            question = "Which gas is vital for humans to breathe?",
            questionSinhala = "මිනිසාට හුස්ම ගැනීම සඳහා අත්‍යවශ්‍ය වන වායුව කුමක්ද?",
            options = listOf("Carbon Dioxide", "Oxygen", "Nitrogen", "Helium"),
            optionsSinhala = listOf("කාබන් ඩයොක්සයිඩ්", "ඔක්සිජන්", "නයිට්‍රජන්", "හෙලියම්"),
            correctAnswerIndex = 1,
            grade = 3, subject = "Science", stage = 2, difficulty = "easy",
            hint = "It is produced by green trees and is essential for cellular respiration.",
            hintSinhala = "හරිත ශාක මඟින් නිපදවන අතර ශ්වසනය සඳහා අත්‍යවශ්‍ය වේ."
        ))
    }

    fun getQuestionsFor(grade: Int, subject: String, stage: Int): List<Question> {
        val filtered = questions.filter { 
            it.grade == grade && 
            it.subject.equals(subject, ignoreCase = true) && 
            it.stage == stage 
        }
        if (filtered.isNotEmpty()) return filtered

        // Fallback: search by grade and subject (ignore stage)
        val fallback = questions.filter { 
            it.grade == grade && 
            it.subject.equals(subject, ignoreCase = true) 
        }
        if (fallback.isNotEmpty()) return fallback

        // Extreme fallback: return first 3 questions of that subject
        val extreme = questions.filter { it.subject.equals(subject, ignoreCase = true) }
        if (extreme.isNotEmpty()) return extreme

        // Absolute fallback: all questions
        return questions
    }
}
