package com.example.data

object MathQuestions {

    fun getQuestions(grade: Int, stage: Int): List<Question> {
        val list = mutableListOf<Question>()
        for (i in 0..9) {
            list.add(getSingleQuestion(grade, stage, i))
        }
        return list
    }

    private fun getSingleQuestion(grade: Int, stage: Int, index: Int): Question {
        val qid = "g${grade}_Math_s${stage}_q${index}"
        val difficulty = when (stage) {
            1 -> "easy"
            2 -> "medium"
            else -> "hard"
        }

        val p = when (grade) {
            1 -> when (stage) {
                1 -> getGrade1Stage1(index)
                2 -> getGrade1Stage2(index)
                else -> getGrade1Stage3(index)
            }
            2 -> when (stage) {
                1 -> getGrade2Stage1(index)
                2 -> getGrade2Stage2(index)
                else -> getGrade2Stage3(index)
            }
            3 -> when (stage) {
                1 -> getGrade3Stage1(index)
                2 -> getGrade3Stage2(index)
                else -> getGrade3Stage3(index)
            }
            4 -> when (stage) {
                1 -> getGrade4Stage1(index)
                2 -> getGrade4Stage2(index)
                else -> getGrade4Stage3(index)
            }
            else -> when (stage) {
                1 -> getGrade5Stage1(index)
                2 -> getGrade5Stage2(index)
                else -> getGrade5Stage3(index)
            }
        }

        val hintEng = HintGenerator.generateHint(p.question, p.options, p.correctAnswer, "English")
        val hintSin = HintGenerator.generateHint(p.questionSinhala, p.optionsSinhala, p.correctAnswer, "Sinhala")

        return Question(
            id = qid,
            question = p.question,
            questionSinhala = p.questionSinhala,
            options = p.options,
            optionsSinhala = p.optionsSinhala,
            correctAnswer = p.correctAnswer,
            grade = grade,
            subject = "Math",
            stage = stage,
            difficulty = difficulty,
            hint = hintEng,
            hintSinhala = hintSin
        )
    }

    private data class Pentuple(
        val question: String,
        val questionSinhala: String,
        val options: List<String>,
        val optionsSinhala: List<String>,
        val correctAnswer: Int
    )

    // GRADE 1
    private fun getGrade1Stage1(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("How many fingers are there on one hand?", "එක් අතක ඇඟිලි කීයක් තිබේද?", listOf("3", "4", "5", "6"), listOf("3", "4", "5", "6"), 2)
        1 -> Pentuple("Find the missing number: 1, 2, 3, __, 5.", "හිස්තැනට සුදුසු සංඛ්‍යාව කුමක්ද: 1, 2, 3, __, 5.", listOf("4", "6", "7", "8"), listOf("4", "6", "7", "8"), 0)
        2 -> Pentuple("Count the stars: ★ ★ ★", "තරු ගණන් කරන්න: ★ ★ ★", listOf("2 stars", "3 stars", "4 stars", "5 stars"), listOf("තරු 2", "තරු 3", "තරු 4", "තරු 5"), 1)
        3 -> Pentuple("Which number comes right after 9?", "9 ට පසුව එන සංඛ්‍යාව කුමක්ද?", listOf("8", "10", "11", "12"), listOf("8", "10", "11", "12"), 1)
        4 -> Pentuple("What is the name of this shape: ○ ?", "මෙම හැඩයේ නම කුමක්ද: ○ ?", listOf("Square", "Triangle", "Circle", "Rectangle"), listOf("සමචතුරස්‍රය", "ත්‍රිකෝණය", "වෘත්තය", "සෘජුකෝණාස්‍රය"), 2)
        5 -> Pentuple("Which is the largest number from these?", "පහත දැක්වෙන සංඛ්‍යාවලින් විශාලතම සංඛ්‍යාව කුමක්ද?", listOf("2", "5", "8", "4"), listOf("2", "5", "8", "4"), 2)
        6 -> Pentuple("Which number is the smallest?", "පහත දැක්වෙන සංඛ්‍යාවලින් කුඩාම සංඛ්‍යාව කුමක්ද?", listOf("10", "1", "7", "3"), listOf("10", "1", "7", "3"), 1)
        7 -> Pentuple("Count the circles: ○ ○ ○ ○", "වෘත්ත ගණන් කරන්න: ○ ○ ○ ○", listOf("3 circles", "4 circles", "5 circles", "6 circles"), listOf("වෘත්ත 3", "වෘත්ත 4", "වෘත්ත 5", "වෘත්ත 6"), 1)
        8 -> Pentuple("Pick the word for number 2:", "2 යන සංඛ්‍යාව වචනයෙන් ලියූවිට කුමක්ද?", listOf("One", "Two", "Three", "Four"), listOf("එක", "දෙක", "තුන", "හතර"), 1)
        else -> Pentuple("How many legs does a dog have?", "බල්ලෙකුට කකුල් කීයක් තිබේද?", listOf("2", "3", "4", "5"), listOf("2", "3", "4", "5"), 2)
    }

    private fun getGrade1Stage2(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("Add: 2 + 3 = ?", "එකතු කරන්න: 2 + 3 = ?", listOf("4", "5", "6", "7"), listOf("4", "5", "6", "7"), 1)
        1 -> Pentuple("What is 5 + 5?", "5 + 5 කීයද?", listOf("9", "10", "11", "12"), listOf("9", "10", "11", "12"), 1)
        2 -> Pentuple("If you have 1 apple and get 2 more, how many do you have?", "ඔබ ළඟ ඇපල් 1ක් ඇති අතර තවත් 2ක් ලැබුණහොත් මුළු ගණන කීයද?", listOf("2 apples", "3 apples", "4 apples", "5 apples"), listOf("ඇපල් 2", "ඇපල් 3", "ඇපල් 4", "ඇපල් 5"), 1)
        3 -> Pentuple("Add: 6 + 4 = ?", "එකතු කරන්න: 6 + 4 = ?", listOf("8", "9", "10", "11"), listOf("8", "9", "10", "11"), 2)
        4 -> Pentuple("What is 10 + 2?", "10 + 2 කීයද?", listOf("11", "12", "13", "14"), listOf("11", "12", "13", "14"), 1)
        5 -> Pentuple("Amal has 4 balls. Nimal gives him 3. Total balls?", "අමල් ළඟ බෝල 4ක් ඇත. නිමල් ඔහුට තවත් 3ක් දෙයි. මුළු බෝල ගණන?", listOf("6 balls", "7 balls", "8 balls", "9 balls"), listOf("බෝල 6", "බෝල 7", "බෝල 8", "බෝල 9"), 1)
        6 -> Pentuple("Add: 8 + 1 = ?", "එකතු කරන්න: 8 + 1 = ?", listOf("7", "8", "9", "10"), listOf("7", "8", "9", "10"), 2)
        7 -> Pentuple("What is 3 + 4?", "3 + 4 කීයද?", listOf("5", "6", "7", "8"), listOf("5", "6", "7", "8"), 2)
        8 -> Pentuple("If you have 5 sweets and buy 3 more, how many do you have?", "පැණිරස කෑම 5ක් ඇති විට තවත් 3ක් මිලදී ගත් මන ඇති මුළු ගණන?", listOf("7 sweets", "8 sweets", "9 sweets", "10 sweets"), listOf("කෑම 7", "කෑම 8", "කෑම 9", "කෑම 10"), 1)
        else -> Pentuple("Add: 7 + 5 = ?", "එකතු කරන්න: 7 + 5 = ?", listOf("11", "12", "13", "14"), listOf("11", "12", "13", "14"), 1)
    }

    private fun getGrade1Stage3(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("Subtract: 5 - 2 = ?", "අඩු කරන්න: 5 - 2 = ?", listOf("2", "3", "4", "5"), listOf("2", "3", "4", "5"), 1)
        1 -> Pentuple("What is 10 - 4?", "10 - 4 කීයද?", listOf("4", "5", "6", "7"), listOf("4", "5", "6", "7"), 2)
        2 -> Pentuple("If there are 6 birds and 2 fly away, how many are left?", "කුරුල්ලන් 6 දෙනෙකුගෙන් 2 දෙනෙක් පියඹා ගියහොත් ඉතිරි ගණන?", listOf("3 birds", "4 birds", "5 birds", "6 birds"), listOf("කුරුල්ලන් 3", "කුරුල්ලන් 4", "කුරුල්ලන් 5", "කුරුල්ලන් 6"), 1)
        3 -> Pentuple("What is 12 - 2?", "12 - 2 කීයද?", listOf("10", "11", "12", "8"), listOf("10", "11", "12", "8"), 0)
        4 -> Pentuple("Subtract: 8 - 5 = ?", "අඩු කරන්න: 8 - 5 = ?", listOf("2", "3", "4", "5"), listOf("2", "3", "4", "5"), 1)
        5 -> Pentuple("Sita had 7 pencils. She lost 3. How many pencils left?", "සීතා ළඟ පැන්සල් 7ක් තිබුණි. ඇයට ඉන් 3ක් නැතිවිය. ඉතිරි පැන්සල්?", listOf("3 pencils", "4 pencils", "5 pencils", "6 pencils"), listOf("පැන්සල් 3", "පැන්සල් 4", "පැන්සල් 5", "පැන්සල් 6"), 1)
        6 -> Pentuple("What is 15 - 5?", "15 - 5 කීයද?", listOf("5", "10", "15", "20"), listOf("5", "10", "15", "20"), 1)
        7 -> Pentuple("Subtract: 9 - 6 = ?", "අඩු කරන්න: 9 - 6 = ?", listOf("3", "4", "5", "6"), listOf("3", "4", "5", "6"), 0)
        8 -> Pentuple("Subtract: 14 - 4 = ?", "අඩු කරන්න: 14 - 4 = ?", listOf("8", "9", "10", "11"), listOf("8", "9", "10", "11"), 2)
        else -> Pentuple("What is 20 - 5?", "20 - 5 කීයද?", listOf("12", "13", "14", "15"), listOf("12", "13", "14", "15"), 3)
    }

    // GRADE 2
    private fun getGrade2Stage1(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("Calculate: 20 + 30 = ?", "ගණනය කරන්න: 20 + 30 = ?", listOf("40", "50", "60", "70"), listOf("40", "50", "60", "70"), 1)
        1 -> Pentuple("Subtract: 50 - 20 = ?", "අඩු කරන්න: 50 - 20 = ?", listOf("20", "30", "40", "50"), listOf("20", "30", "40", "50"), 1)
        2 -> Pentuple("Add: 45 + 12 = ?", "එකතු කරන්න: 45 + 12 = ?", listOf("55", "56", "57", "58"), listOf("55", "56", "57", "58"), 2)
        3 -> Pentuple("Subtract: 88 - 14 = ?", "අඩු කරන්න: 88 - 14 = ?", listOf("74", "76", "72", "70"), listOf("74", "76", "72", "70"), 0)
        4 -> Pentuple("Calculate: 60 + 25 = ?", "ගණනය කරන්න: 60 + 25 = ?", listOf("85", "80", "90", "75"), listOf("85", "80", "90", "75"), 0)
        5 -> Pentuple("Subtract: 95 - 40 = ?", "අඩු කරන්න: 95 - 40 = ?", listOf("50", "55", "60", "65"), listOf("50", "55", "60", "65"), 1)
        6 -> Pentuple("Calculate: 37 + 23 = ?", "ගණනය කරන්න: 37 + 23 = ?", listOf("50", "55", "60", "65"), listOf("50", "55", "60", "65"), 2)
        7 -> Pentuple("Subtract: 75 - 25 = ?", "අඩු කරන්න: 75 - 25 = ?", listOf("45", "50", "55", "60"), listOf("45", "50", "55", "60"), 1)
        8 -> Pentuple("Calculate: 18 + 19 = ?", "ගණනය කරන්න: 18 + 19 = ?", listOf("35", "36", "37", "38"), listOf("35", "36", "37", "38"), 2)
        else -> Pentuple("Subtract: 100 - 30 = ?", "අඩු කරන්න: 100 - 30 = ?", listOf("60", "70", "80", "90"), listOf("60", "70", "80", "90"), 1)
    }

    private fun getGrade2Stage2(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("What is 2 times 4 (2 x 4)?", "2 වරක් 4 (2 x 4) කොපමණද?", listOf("6", "8", "10", "12"), listOf("6", "8", "10", "12"), 1)
        1 -> Pentuple("What is 5 times 3 (5 x 3)?", "5 වරක් 3 (5 x 3) කොපමණද?", listOf("10", "15", "20", "25"), listOf("10", "15", "20", "25"), 1)
        2 -> Pentuple("What is 10 times 6 (10 x 6)?", "10 වරක් 6 (10 x 6) කොපමණද?", listOf("50", "60", "70", "80"), listOf("50", "60", "70", "80"), 1)
        3 -> Pentuple("Calculate: 2 x 7 = ?", "ගණනය කරන්න: 2 x 7 = ?", listOf("12", "14", "16", "18"), listOf("12", "14", "16", "18"), 1)
        4 -> Pentuple("Calculate: 5 x 5 = ?", "ගණනය කරන්න: 5 x 5 = ?", listOf("20", "25", "30", "35"), listOf("20", "25", "30", "35"), 1)
        5 -> Pentuple("What is 10 x 4?", "10 x 4 කොපමණද?", listOf("30", "40", "50", "60"), listOf("30", "40", "50", "60"), 1)
        6 -> Pentuple("Calculate: 2 x 8 = ?", "ගණනය කරන්න: 2 x 8 = ?", listOf("14", "16", "18", "20"), listOf("14", "16", "18", "20"), 1)
        7 -> Pentuple("Calculate: 5 x 8 = ?", "ගණනය කරන්න: 5 x 8 = ?", listOf("35", "40", "45", "50"), listOf("35", "40", "45", "50"), 1)
        8 -> Pentuple("What is 10 times 9?", "10 වරක් 9 කොපමණද?", listOf("80", "90", "100", "99"), listOf("80", "90", "100", "99"), 1)
        else -> Pentuple("If one pencil costs 5 LKR, what is the cost of 4 pencils?", "එක් පැන්සලක් රුපියල් 5ක් නම් එවැනි පැන්සල් 4ක මිල කීයද?", listOf("rupees 15", "rupees 20", "rupees 25", "rupees 30"), listOf("රුපියල් 15", "රුපියල් 20", "රුපියල් 25", "රුපියල් 30"), 1)
    }

    private fun getGrade2Stage3(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("If a chocolate is cut into 2 equal parts, what is one part called?", "චොකලට් එකක් සමාන කොටස් 2කට කැපූවිට එක් කොටසක් හඳුන්වන්නේ කුමක් ලෙසද?", listOf("Quarter", "Half", "Third", "Whole"), listOf("කාල", "අඩ (භාගය)", "තුනෙන් පංගුව", "සම්පූර්ණ"), 1)
        1 -> Pentuple("If a cake is cut into 4 equal pieces, what is one piece called?", "කේක් ගෙඩියක් සමාන කොටස් 4කට කැපූවිට එක් කොටසක් හඳුන්වන්නේ කුමක් ලෙසද?", listOf("Half", "Double", "Quarter", "Triple"), listOf("අඩ", "දෙගුණය", "කාල (හතරෙන් පංගුව)", "තෙගුණය"), 2)
        2 -> Pentuple("How many quarters make a whole sweet?", "මුළු කැවිල්ලක් සෑදීමට කාල කොටස් කීයක් තිබිය යුතුද?", listOf("2", "3", "4", "5"), listOf("2", "3", "4", "5"), 2)
        3 -> Pentuple("A standard wooden stick has what length measurement unit?", "සාමාන්‍ය රීලයක් මැනීමට ගන්නා ඒකකය කුමක්ද?", listOf("Hours", "Kilograms", "Meters", "Liters"), listOf("පැය", "කිලෝග්‍රෑම්", "මීටර", "ලීටර"), 2)
        4 -> Pentuple("How many halves make a whole circle?", "මුළු වෘත්තයක් සෑදීමට අර්ධ කොටස් කීයක් තිබිය යුතුද?", listOf("1", "2", "3", "4"), listOf("1", "2", "3", "4"), 1)
        5 -> Pentuple("If you have half a guava and someone gives you another half, you have:", "ඔබ ළඟ ඇති අර්ධ පේර ගෙඩියට තවත් අර්ධයක් ලැබුණහොත් ලැබෙන මුළු ගණන:", listOf("1 guava", "2 guavas", "Half guava", "4 guavas"), listOf("පේර ගෙඩි 1", "පේර ගෙඩි 2", "පේර භාගයක්", "පේර ගෙඩි 4"), 0)
        6 -> Pentuple("Which is longer: a 2 meter ribbon or a 5 meter ribbon?", "දිගින් වැඩි කුමක්ද: මීටර් 2 රිබන් පටියක්ද මීටර් 5ක්ද?", listOf("2m ribbon", "5m ribbon", "Both equal", "None"), listOf("මීටර් 2 රිබන් පටිය", "මීටර් 5 රිබන් පටිය", "දෙකම සමානයි", "කිසිවක් නොවේ"), 1)
        7 -> Pentuple("Which is the correct symbol for half?", "අඩ (භාගය) සඳහා නිවැරදි සංකේතය කුමක්ද?", listOf("1/4", "1/2", "3/4", "2/2"), listOf("1/4", "1/2", "3/4", "2/2"), 1)
        8 -> Pentuple("Which is the correct symbol for quarter?", "කාල (හතරෙන් එක) සඳහා නිවැරදි සංකේතය කුමක්ද?", listOf("1/2", "1/3", "1/4", "4/1"), listOf("1/2", "1/3", "1/4", "4/1"), 2)
        else -> Pentuple("A water bucket has capacity measured in which unit?", "බල්දියක ජල ධාරිතාවය මනින්නේ කුමන ඒකකයෙන්ද?", listOf("Grams", "Meters", "Liters", "Centimeters"), listOf("ග්‍රෑම්", "මීටර", "ලීටර", "සෙන්ටිමීටර"), 2)
    }

    // GRADE 3
    private fun getGrade3Stage1(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("Calculate: 3 x 6 = ?", "ගණනය කරන්න: 3 x 6 = ?", listOf("12", "15", "18", "21"), listOf("12", "15", "18", "21"), 2)
        1 -> Pentuple("What is 4 times 7?", "4 වරක් 7 කීයද?", listOf("24", "28", "32", "36"), listOf("24", "28", "32", "36"), 1)
        2 -> Pentuple("Calculate: 6 x 8 = ?", "ගණනය කරන්න: 6 x 8 = ?", listOf("42", "48", "54", "64"), listOf("42", "48", "54", "64"), 1)
        3 -> Pentuple("What is 7 times 7?", "7 වරක් 7 කීයද?", listOf("42", "49", "56", "63"), listOf("42", "49", "56", "63"), 1)
        4 -> Pentuple("Calculate: 9 x 4 = ?", "ගණනය කරන්න: 9 x 4 = ?", listOf("32", "36", "40", "45"), listOf("32", "36", "40", "45"), 1)
        5 -> Pentuple("What is 8 times 9?", "8 වරක් 9 කීයද?", listOf("64", "72", "80", "81"), listOf("64", "72", "80", "81"), 1)
        6 -> Pentuple("Calculate: 3 x 9 = ?", "ගණනය කරන්න: 3 x 9 = ?", listOf("24", "27", "30", "33"), listOf("24", "27", "30", "33"), 1)
        7 -> Pentuple("What is 6 times 6?", "6 වරක් 6 කීයද?", listOf("30", "36", "42", "48"), listOf("30", "36", "42", "48"), 1)
        8 -> Pentuple("Calculate: 8 x 7 = ?", "ගණනය කරන්න: 8 x 7 = ?", listOf("54", "56", "58", "60"), listOf("54", "56", "58", "60"), 1)
        else -> Pentuple("A box has 6 rows of eggs with 5 in each row. Total eggs?", "පෙට්ටියක එක් පේළියක බිත්තර 5 බැගින් පේළි 6ක් ඇත. මුළු බිත්තර ගණන?", listOf("25 eggs", "30 eggs", "35 eggs", "40 eggs"), listOf("බිත්තර 25", "බිත්තර 30", "බිත්තර 35", "බිත්තර 40"), 1)
    }

    private fun getGrade3Stage2(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("Divide 12 by 3 (12 / 3) = ?", "12, 3න් බෙදන්න (12 / 3) = ?", listOf("3", "4", "5", "6"), listOf("3", "4", "5", "6"), 1)
        1 -> Pentuple("Share 15 sweets equally among 5 kids. How many sweets does each get?", "පැණිරස කෑම 15 ක් ළමයින් 5 දෙනෙකු අතර සම සේ බෙදූ විට එක් අයෙකුට ලැබෙන ප්‍රමාණය?", listOf("2 sweets", "3 sweets", "4 sweets", "5 sweets"), listOf("කෑම 2", "කෑම 3", "කෑම 4", "කෑම 5"), 1)
        2 -> Pentuple("In the number 456, what is the place value of 5?", "456 සංඛ්‍යාවේ 5 හි ස්ථානීය අගය කුමක්ද?", listOf("Ones", "Tens", "Hundreds", "Thousands"), listOf("එකස්ථානය", "දසස්ථානය", "සියස්ථානය", "දහස්ථානය"), 1)
        3 -> Pentuple("In the number 892, what digit is in the hundreds place?", "892 සංඛ්‍යාවේ සියස්ථානයේ ඇති ඉලක්කම කුමක්ද?", listOf("2", "9", "8", "0"), listOf("2", "9", "8", "0"), 2)
        4 -> Pentuple("What is 35 divided by 5?", "35, 5න් බෙදූවිට ලැබෙන අගය?", listOf("6", "7", "8", "9"), listOf("6", "7", "8", "9"), 1)
        5 -> Pentuple("How is 'seven hundred and three' written in numbers?", "'හත්සිය තුන' සංඛ්‍යාත්මකව ලියන්නේ කෙසේද?", listOf("703", "730", "733", "7003"), listOf("703", "730", "733", "7003"), 0)
        6 -> Pentuple("Divide 18 by 2 = ?", "18, 2න් බෙදන්න = ?", listOf("7", "8", "9", "10"), listOf("7", "8", "9", "10"), 2)
        7 -> Pentuple("In the number 904, what does the '0' represent?", "904 සංඛ්‍යාවේ '0' මඟින් නියෝජනය කරන්නේ කුමන ස්ථානයද?", listOf("Zero Ones", "Zero Tens", "Zero Hundreds", "Zero Thousands"), listOf("එකස්ථාන 0ක්", "දසස්ථාන 0ක්", "සියස්ථාන 0ක්", "දහස්ථාන 0ක්"), 1)
        8 -> Pentuple("What is 40 / 4?", "40 / 4 කීයද?", listOf("8", "9", "10", "11"), listOf("8", "9", "10", "11"), 2)
        else -> Pentuple("If you divide 24 books into bags of 6, how many bags do you fill?", "පොත් 24ක් පැකට් එකකට 6 බැගින් ඇසුරූ විට පැකට් කීයක් සැකසිය හැකිද?", listOf("3 bags", "4 bags", "5 bags", "6 bags"), listOf("පැකට් 3", "පැකට් 4", "පැකට් 5", "පැකට් 6"), 1)
    }

    private fun getGrade3Stage3(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("If the clock's short hand is at 5 and long hand is at 12, what time is it?", "ඔරලෝසුවේ කුඩා කටුව 5 මත සහ විශාල කටුව 12 මත ඇතිවිට වේලාව කුමක්ද?", listOf("12:05", "5:00", "5:12", "12:50"), listOf("12:05", "පස්වරු 5:00", "5:12", "12:50"), 1)
        1 -> Pentuple("How many months are there in one year?", "වසරකට මාස කීයක් තිබේද?", listOf("10", "11", "12", "13"), listOf("10", "11", "12", "13"), 2)
        2 -> Pentuple("Which month has fewer than 30 days?", "දින 30 ට වඩා අඩු දින ගණනක් ඇති මාසය කුමක්ද?", listOf("January", "February", "March", "April"), listOf("ජනවාරි", "පෙබරවාරි", "මාර්තු", "අප්‍රේල්"), 1)
        3 -> Pentuple("How many minutes are in one hour?", "පැයකට මිනිත්තු කීයක් තිබේද?", listOf("30", "50", "60", "100"), listOf("30", "50", "60", "100"), 2)
        4 -> Pentuple("How many days are in a leap year?", "අධික අවුරුද්දකට දින කීයක් තිබේද?", listOf("364", "365", "366", "367"), listOf("364", "365", "366", "367"), 2)
        5 -> Pentuple("What time is 30 minutes after 6:00?", "පෙරවරු 6:00 ට මිනිත්තු 30 කට පසු වේලාව කුමක්ද?", listOf("6:30", "7:00", "5:30", "6:15"), listOf("6:30", "7:00", "5:30", "6:15"), 0)
        6 -> Pentuple("How many weeks are in a standard year?", "සාමාන්‍ය වසරකට සති කීයක් පමණ තිබේද?", listOf("48", "50", "52", "54"), listOf("48", "50", "52", "54"), 2)
        7 -> Pentuple("If today is Monday, what was yesterday?", "අද සඳුදා නම් ඊයේ දවස කුමක්ද?", listOf("Sunday", "Tuesday", "Wednesday", "Saturday"), listOf("ඉරිදා", "අඟහරුවාදා", "බදාදා", "සෙනසුරාදා"), 0)
        8 -> Pentuple("How many seconds are in 1 minute?", "මිනිත්තුවකට තත්පර කීයක් තිබේද?", listOf("45", "50", "60", "100"), listOf("45", "50", "60", "100"), 2)
        else -> Pentuple("If a movie starts at 2:00 PM and lasts 2 hours, when does it end?", "චිත්‍රපටයක් පස්වරු 2:00 ට ආරම්භ වී පැය 2ක් ධාවනය වූවහොත් අවසන් වන වේලාව?", listOf("3:00 PM", "4:00 PM", "5:00 PM", "6:00 PM"), listOf("පස්වරු 3:00", "පස්වරු 4:00", "පස්වරු 5:00", "පස්වරු 6:00"), 1)
    }

    // GRADE 4
    private fun getGrade4Stage1(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("Read the number: 4520. Which digit is in the thousands place?", "4520 සංඛ්‍යාවේ දහස් ස්ථානයේ ඇති ඉලක්කම කුමක්ද?", listOf("0", "2", "5", "4"), listOf("0", "2", "5", "4"), 3)
        1 -> Pentuple("Find the sum: 2500 + 1300 = ?", "එකතුව සොයන්න: 2500 + 1300 = ?", listOf("3700", "3800", "3900", "4000"), listOf("3700", "3800", "3900", "4000"), 1)
        2 -> Pentuple("What is the perimeter of a rectangle with length 5 cm and width 3 cm?", "දිග සෙ.මී. 5ක් සහ පළල සෙ.මී. 3ක් වන සෘජුකෝණාස්‍රයක පරිමිතිය කොපමණද?", listOf("8 cm", "15 cm", "16 cm", "20 cm"), listOf("සෙ.මී. 8", "සෙ.මී. 15", "සෙ.මී. 16", "සෙ.මී. 20"), 2)
        3 -> Pentuple("Calculate the perimeter of a square with a side length of 6 cm.", "පැත්තක දිග සෙ.මී. 6ක් වන සමචතුරස්‍රයක පරිමිතිය සොයන්න.", listOf("12 cm", "24 cm", "36 cm", "40 cm"), listOf("සෙ.මී. 12", "සෙ.මී. 24", "සෙ.මී. 36", "සෙ.මී. 40"), 1)
        4 -> Pentuple("Subtract: 7500 - 2300 = ?", "අඩු කරන්න: 7500 - 2300 = ?", listOf("5000", "5100", "5200", "5300"), listOf("5000", "5100", "5200", "5300"), 2)
        5 -> Pentuple("What is the value of 10000 - 1?", "10000 - 1 හි අගය කොපමණද?", listOf("999", "9990", "9999", "9000"), listOf("999", "9990", "9999", "9000"), 2)
        6 -> Pentuple("A triangle has sides of length 7 cm, 8 cm, and 9 cm. What is its perimeter?", "ත්‍රිකෝණයක පැතිවල දිග සෙ.මී. 7, 8, සහ 9 වේ. එහි පරිමිතිය කුමක්ද?", listOf("22 cm", "23 cm", "24 cm", "25 cm"), listOf("සෙ.මී. 22", "සෙ.මී. 23", "සෙ.මී. 24", "සෙ.මී. 25"), 2)
        7 -> Pentuple("Read 9085. What represents the value of the zero in this number?", "9085 සංඛ්‍යාවේ ඇති '0' හි වටිනාකම කුමක්ද?", listOf("Zero Tens", "Zero Hundreds", "Zero Ones", "Zero Thousands"), listOf("දසස්ථාන 0ක්", "සියස්ථාන 0ක්", "එකස්ථාන 0ක්", "දහස්ථාන 0ක්"), 1)
        8 -> Pentuple("Add: 6040 + 3020 = ?", "එකතු කරන්න: 6040 + 3020 = ?", listOf("9060", "9080", "9100", "9040"), listOf("9060", "9080", "9100", "9040"), 0)
        else -> Pentuple("What is the perimeter of an equilateral triangle with side length 5 cm?", "පැත්තක දිග සෙ.මී. 5ක් වන සමපාද ත්‍රිකෝණයක පරිමිතිය කොපමණද?", listOf("10 cm", "15 cm", "20 cm", "25 cm"), listOf("සෙ.මී. 10", "සෙ.මී. 15", "සෙ.මී. 20", "සෙ.මී. 25"), 1)
    }

    private fun getGrade4Stage2(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("Calculate: 15 x 6 = ?", "ගණනය කරන්න: 15 x 6 = ?", listOf("80", "90", "100", "110"), listOf("80", "90", "100", "110"), 1)
        1 -> Pentuple("Multiply: 24 x 5 = ?", "ගුණ කරන්න: 24 x 5 = ?", listOf("100", "110", "120", "130"), listOf("100", "110", "120", "130"), 2)
        2 -> Pentuple("What is 108 divided by 9?", "108, 9න් බෙදූ කල අගය කීයද?", listOf("11", "12", "13", "14"), listOf("11", "12", "13", "14"), 1)
        3 -> Pentuple("Calculate: 140 / 7 = ?", "ගණනය කරන්න: 140 / 7 = ?", listOf("10", "15", "20", "25"), listOf("10", "15", "20", "25"), 2)
        4 -> Pentuple("If you multiply 11 by 12, what is the product?", "11, 12න් ගුණ කළවිට ලැබෙන ගුණිතය කුමක්ද?", listOf("121", "132", "143", "154"), listOf("121", "132", "143", "154"), 1)
        5 -> Pentuple("Divide 250 by 10 = ?", "250, 10න් බෙදන්න = ?", listOf("20", "25", "30", "35"), listOf("20", "25", "30", "35"), 1)
        6 -> Pentuple("Multiply: 50 x 8 = ?", "ගුණ කරන්න: 50 x 8 = ?", listOf("350", "400", "450", "500"), listOf("350", "400", "450", "500"), 1)
        7 -> Pentuple("Divide: 420 / 6 = ?", "බෙදන්න: 420 / 6 = ?", listOf("60", "70", "80", "90"), listOf("60", "70", "80", "90"), 1)
        8 -> Pentuple("Calculate: 13 x 8 = ?", "ගණනය කරන්න: 13 x 8 = ?", listOf("94", "104", "114", "124"), listOf("94", "104", "114", "124"), 1)
        else -> Pentuple("If 5 identical books cost 75 LKR altogether, what is the cost of 1 book?", "පොත් 5ක මුළු මිල රුපියල් 75ක් නම් එක් පොතක මිල කොපමණද?", listOf("12 LKR", "15 LKR", "18 LKR", "20 LKR"), listOf("රුපියල් 12", "රුපියල් 15", "රුපියල් 18", "රුපියල් 20"), 1)
    }

    private fun getGrade4Stage3(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("Which of the following fractions is equivalent to 1/2?", "පහත ඒවායින් 1/2 ට සමාන වන භාගය කුමක්ද?", listOf("2/3", "2/4", "3/5", "4/6"), listOf("2/3", "2/4", "3/5", "4/6"), 1)
        1 -> Pentuple("Compare: which is larger, 3/4 or 1/4?", "සසඳන්න: විශාල වන්නේ කුමක්ද, 3/4 ද 1/4 ද?", listOf("1/4", "3/4", "Both equal", "Cannot compare"), listOf("1/4", "3/4", "දෙකම සමානයි", "සැසඳිය නොහැක"), 1)
        2 -> Pentuple("What is the area of a square of side length 4 cm?", "පැත්තක දිග සෙ.මී. 4ක් වන සමචතුරස්‍රයක වර්ගඵලය සොයන්න.", listOf("8 sq.cm", "16 sq.cm", "20 sq.cm", "24 sq.cm"), listOf("වර්ග සෙ.මී. 8", "වර්ග සෙ.මී. 16", "වර්ග සෙ.මී. 20", "වර්ග සෙ.මී. 24"), 1)
        3 -> Pentuple("Calculate the area of a rectangle with length 6 cm and width 4 cm.", "දිග සෙ.මී. 6ක් සහ පළල සෙ.මී. 4ක් වන සෘජුකෝණාස්‍රයක වර්ගඵලය සොයන්න.", listOf("10 sq.cm", "20 sq.cm", "24 sq.cm", "28 sq.cm"), listOf("වර්ග සෙ.මී. 10", "වර්ග සෙ.මී. 20", "වර්ග සෙ.මී. 24", "වර්ග සෙ.මී. 28"), 2)
        4 -> Pentuple("Find the missing fraction: 1/5 + __ = 4/5.", "හිස්තැනට ආ යුතු භාගය සොයන්න: 1/5 + __ = 4/5.", listOf("1/5", "2/5", "3/5", "4/5"), listOf("1/5", "2/5", "3/5", "4/5"), 2)
        5 -> Pentuple("What is the sum of 2/7 and 3/7?", "2/7 සහ 3/7 හි එකතුව කුමක්ද?", listOf("5/7", "5/14", "6/7", "1/7"), listOf("5/7", "5/14", "6/7", "1/7"), 0)
        6 -> Pentuple("What is the equivalent fraction of 3/9 in its simplest form?", "3/9 සරල කළවිට ලැබෙන අගය කුමක්ද?", listOf("1/2", "1/3", "2/3", "1/4"), listOf("1/2", "1/3", "2/3", "1/4"), 1)
        7 -> Pentuple("If a tile is 2 cm long and 2 cm wide, what is its area?", "දිග සෙ.මී. 2ක් සහ පළල සෙ.මී. 2ක් වන ටයිල් එකක වර්ගඵලය කුමක්ද?", listOf("4 sq.cm", "8 sq.cm", "6 sq.cm", "2 sq.cm"), listOf("වර්ග සෙ.මී. 4", "වර්ග සෙ.මී. 8", "වර්ග සෙ.මී. 6", "වර්ග සෙ.මී. 2"), 0)
        8 -> Pentuple("Subtract: 5/8 - 2/8 = ?", "අඩු කරන්න: 5/8 - 2/8 = ?", listOf("1/8", "2/8", "3/8", "4/8"), listOf("1/8", "2/8", "3/8", "4/8"), 2)
        else -> Pentuple("A field has area 30 sq.m. If its width is 5 m, find its length.", "වර්ගඵලය වර්ග මීටර් 30ක් වන කුඹුරක පළල මීටර් 5ක් නම් එහි දිග කොපමණද?", listOf("5 meters", "6 meters", "7 meters", "8 meters"), listOf("මීටර් 5", "මීටර් 6", "මීටර් 7", "මීටර් 8"), 1)
    }

    // GRADE 5
    private fun getGrade5Stage1(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("Which decimal number is equivalent to 1/10?", "1/10 ට සමාන වන දශම සංඛ්‍යාව කුමක්ද?", listOf("0.01", "0.1", "1.0", "0.001"), listOf("0.01", "0.1", "1.0", "0.001"), 1)
        1 -> Pentuple("Which is the value of the 8 in 85400?", "85400 හි 8 හි ස්ථානීය අගය කොපමණද?", listOf("Eighty", "Eight Hundred", "Eight Thousand", "Eighty Thousand"), listOf("අසූව", "අටසියය", "අටදහස", "අසූදහස"), 3)
        2 -> Pentuple("Calculate: 0.5 + 0.3 = ?", "ගණනය කරන්න: 0.5 + 0.3 = ?", listOf("0.08", "0.8", "8.0", "0.75"), listOf("0.08", "0.8", "8.0", "0.75"), 1)
        3 -> Pentuple("Subtract: 1.0 - 0.4 = ?", "අඩු කරන්න: 1.0 - 0.4 = ?", listOf("0.5", "0.6", "0.7", "0.8"), listOf("0.5", "0.6", "0.7", "0.8"), 1)
        4 -> Pentuple("In the number 12.34, which digit is in the tenths place?", "12.34 සංඛ්‍යාවේ දහයෙන් පංගුවේ ස්ථානයේ පවතින ඉලක්කම කුමක්ද?", listOf("1", "2", "3", "4"), listOf("1", "2", "3", "4"), 2)
        5 -> Pentuple("Convert to decimal: 5 and three-tenths.", "දශමයකට හරවන්න: පහයි දහයෙන් තුනක්.", listOf("5.3", "5.03", "5.003", "53.0"), listOf("5.3", "5.03", "5.003", "53.0"), 0)
        6 -> Pentuple("What is the sum of 12500 and 37500?", "12500 සහ 37500 හි එකතුව කොපමණද?", listOf("40000", "45000", "50000", "55000"), listOf("40000", "45000", "50000", "55000"), 2)
        7 -> Pentuple("What is 0.75 - 0.25?", "0.75 - 0.25 කීයද?", listOf("0.5", "0.55", "0.25", "0.7"), listOf("0.5", "0.55", "0.25", "0.7"), 0)
        8 -> Pentuple("Which number represents ninety-five thousand and six?", "අනූදහස් හය නියෝජනය කරන සංඛ්‍යාව කුමක්ද?", listOf("9506", "95006", "95060", "950006"), listOf("9506", "95006", "95060", "950006"), 1)
        else -> Pentuple("Find the value of 1.2 + 2.8 = ?", "1.2 + 2.8 හි අගය සොයන්න.", listOf("3.0", "3.8", "4.0", "4.2"), listOf("3.0", "3.8", "4.0", "4.2"), 2)
    }

    private fun getGrade5Stage2(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("Express 50% as a fraction in its simplest form.", "50% සරලම භාගයක් ලෙස දක්වන්න.", listOf("1/2", "1/4", "2/5", "3/4"), listOf("1/2", "1/4", "2/5", "3/4"), 0)
        1 -> Pentuple("A shirt bought for 1000 LKR was sold for 1200 LKR. What is the profit?", "රුපියල් 1000කට මිලදී ගත් කමිසයක් රුපියල් 1200කට විකුණන ලදී. ලැබුණු ලාභය?", listOf("100 LKR", "200 LKR", "300 LKR", "400 LKR"), listOf("රුපියල් 100", "රුපියල් 200", "රුපියල් 300", "රුපියල් 400"), 1)
        2 -> Pentuple("What is 10% of 200 LKR?", "රුපියල් 200න් 10%ක් කොපමණද?", listOf("10 LKR", "15 LKR", "20 LKR", "25 LKR"), listOf("රුපියල් 10", "රුපියල් 15", "රුපියල් 20", "රුපියල් 25"), 2)
        3 -> Pentuple("An item was bought for 500 LKR and sold at a 50 LKR loss. What is the selling price?", "රුපියල් 500කට මිලදී ගත් භාණ්ඩයක් රුපියල් 50ක අලාභයක් සහිතව විකුණන ලදී. විකුණුම් මිල?", listOf("450 LKR", "500 LKR", "550 LKR", "400 LKR"), listOf("රුපියල් 450", "රුපියල් 500", "රුපියල් 550", "රුපියල් 400"), 0)
        4 -> Pentuple("Convert to a percentage: 1/4.", "ප්‍රතිශතයකට හරවන්න: 1/4.", listOf("10%", "25%", "40%", "50%"), listOf("10%", "25%", "40%", "50%"), 1)
        5 -> Pentuple("Saman made a profit of 150 LKR selling a toy for 450 LKR. What was the buying cost?", "සමන් සෙල්ලම් බඩුවක් රුපියල් 450කට විකුණා රුපියල් 150ක ලාභයක් ලැබීය. එහි මුල් මිල?", listOf("200 LKR", "250 LKR", "300 LKR", "350 LKR"), listOf("රුපියල් 200", "රුපියල් 250", "රුපියල් 300", "රුපියල් 350"), 2)
        6 -> Pentuple("What is 100% of 75?", "75න් 100%ක් කීයද?", listOf("1", "7.5", "75", "100"), listOf("1", "7.5", "75", "100"), 2)
        7 -> Pentuple("Express 75% as a fraction.", "75% භාගයක් ලෙස දක්වන්න.", listOf("1/4", "1/2", "3/4", "4/5"), listOf("1/4", "1/2", "3/4", "4/5"), 2)
        8 -> Pentuple("If you sell a bag bought for 800 LKR for 700 LKR, what is your loss?", "රුපියල් 800ක බෑගයක් රුපියල් 700කට විකුණුවහොත් ලැබෙන පාඩුව?", listOf("50 LKR", "100 LKR", "150 LKR", "200 LKR"), listOf("රුපියල් 50", "රුපියල් 100", "රුපියල් 150", "රුපියල් 200"), 1)
        else -> Pentuple("What is 20% of 500?", "500න් 20%ක් කොපමණද?", listOf("50", "100", "150", "200"), listOf("50", "100", "150", "200"), 1)
    }

    private fun getGrade5Stage3(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("If 3 pens cost 45 LKR, what is the cost of 5 pens?", "පෑන් 3ක මිල රුපියල් 45ක් නම් පෑන් 5ක මිල කීයද?", listOf("60 LKR", "75 LKR", "90 LKR", "100 LKR"), listOf("රුපියල් 60", "රුපියල් 75", "රුපියල් 90", "රුපියල් 100"), 1)
        1 -> Pentuple("A tank is 1/4 full of water. If we add 10 liters, it becomes half full. Total capacity?", "ටැංකියකින් 1/4ක් ජලයෙන් පිරී ඇත. තවත් ලීටර් 10ක් දැමූවිට භාගයක් පිරේ. ටැංකියේ මුළු ධාරිතාවය?", listOf("20 liters", "30 liters", "40 liters", "50 liters"), listOf("ලීටර් 20", "ලීටර් 30", "ලීටර් 40", "ලීටර් 50"), 2)
        2 -> Pentuple("If 4 identical chocolates weigh 200 grams, how much do 10 chocolates weigh?", "චොකලට් 4ක බර ග්‍රෑම් 200ක් නම් එවැනි චොකලට් 10ක බර කොපමණද?", listOf("400g", "500g", "600g", "800g"), listOf("ග්‍රෑම් 400", "ග්‍රෑම් 500", "ග්‍රෑම් 600", "ග්‍රෑම් 800"), 1)
        3 -> Pentuple("A man walks 4 km per hour. How long will it take him to travel 12 km?", "මිනිසෙක් පැයකට කි.මී. 4ක වේගයෙන් ඇවිදී. කි.මී. 12ක දුරක් යාමට ගතවන කාලය?", listOf("2 hours", "3 hours", "4 hours", "5 hours"), listOf("පැය 2", "පැය 3", "පැය 4", "පැය 5"), 1)
        4 -> Pentuple("Five children are in a race. If Amal finished before Nimal, and Nimal before Kamal, who finished last?", "ළමයින් පස්දෙනෙක් දුවති. අමල් නිමල්ට වඩා කලින්ද නිමල් කමල්ට වඩා කලින්ද ආවේ නම් අවසන් වරට ආවේ කවුද?", listOf("Amal", "Nimal", "Kamal", "None"), listOf("අමල්", "නිමල්", "කමල්", "කිසිවෙක් නොවේ"), 2)
        5 -> Pentuple("If yesterday was Saturday, what day will it be 3 days from today?", "ඊයේ සෙනසුරාදා නම් අද සිට දින 3කට පසු ලැබෙන දිනය කුමක්ද?", listOf("Tuesday", "Wednesday", "Thursday", "Friday"), listOf("අඟහරුවාදා", "බදාදා", "බ්‍රහස්පතින්දා", "සිකුරාදා"), 1)
        6 -> Pentuple("An oil bottle is 3/5 full. What fraction of the bottle is empty?", "තෙල් බෝතලයකින් 3/5ක් පිරී ඇත. හිස්ව පවතින භාගය කුමක්ද?", listOf("1/5", "2/5", "3/5", "4/5"), listOf("1/5", "2/5", "3/5", "4/5"), 1)
        7 -> Pentuple("A wall is built by 4 builders in 5 days. How many days would 10 builders take?", "ගොඩනැගිල්ලක් මේසන්වරු 4 දෙනෙකු දින 5කදී හදයි. මේසන්වරු 10 දෙනෙක් එය හදන්න කොපමණ දිනක් ගනීද?", listOf("2 days", "3 days", "4 days", "10 days"), listOf("දින 2", "දින 3", "දින 4", "දින 10"), 0)
        8 -> Pentuple("A train departed at 08:30 AM and arrived at 11:45 AM. How long did the trip take?", "දුම්රියක් පෙරවරු 08:30 ට පිටත්ව පෙරවරු 11:45 ට ළඟාවිය. ගමනට ගතවූ කාලය?", listOf("2 hours 15 mins", "3 hours 15 mins", "3 hours 45 mins", "4 hours"), listOf("පැය 2 මිනිත්තු 15", "පැය 3 මිනිත්තු 15", "පැය 3 මිනිත්තු 45", "පැය 4"), 1)
        else -> Pentuple("If 2 pencils and 3 erasers cost 60 LKR, and 2 pencils and 1 eraser cost 40 LKR, what is the cost of 1 eraser?", "පැන්සල් 2ක් සහ මකන 3ක් රුපියල් 60ක් වන අතර පැන්සල් 2ක් සහ 1 මකනයක් රුපියල් 40ක් වේ. මකනයක මිල?", listOf("5 LKR", "10 LKR", "15 LKR", "20 LKR"), listOf("රුපියල් 5", "රුපියල් 10", "රුපියල් 15", "රුපියල් 20"), 1)
    }
}
