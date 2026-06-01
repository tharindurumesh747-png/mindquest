package com.example.data

object MathQuestions2 {

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
            6 -> when (stage) {
                1 -> getGrade6Stage1(index)
                2 -> getGrade6Stage2(index)
                else -> getGrade6Stage3(index)
            }
            7 -> when (stage) {
                1 -> getGrade7Stage1(index)
                2 -> getGrade7Stage2(index)
                else -> getGrade7Stage3(index)
            }
            8 -> when (stage) {
                1 -> getGrade8Stage1(index)
                2 -> getGrade8Stage2(index)
                else -> getGrade8Stage3(index)
            }
            9 -> when (stage) {
                1 -> getGrade9Stage1(index)
                2 -> getGrade9Stage2(index)
                else -> getGrade9Stage3(index)
            }
            else -> when (stage) {
                1 -> getGrade10Stage1(index)
                2 -> getGrade10Stage2(index)
                else -> getGrade10Stage3(index)
            }
        }

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
            difficulty = difficulty
        )
    }

    private data class Pentuple(
        val question: String,
        val questionSinhala: String,
        val options: List<String>,
        val optionsSinhala: List<String>,
        val correctAnswer: Int
    )

    // GRADE 6
    private fun getGrade6Stage1(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("What is the Lowest Common Multiple (LCM) of 4 and 6?", "4 සහ 6 හි කුඩාම පොදු ගුණාකාරය (කු.පො.ගු.) කුමක්ද?", listOf("12", "18", "24", "36"), listOf("12", "18", "24", "36"), 0)
        1 -> Pentuple("What is the Highest Common Factor (HCF) of 12 and 18?", "12 සහ 18 හි මහඟු පොදු සාධකය (ම.පො.ස.) කුමක්ද?", listOf("2", "3", "6", "9"), listOf("2", "3", "6", "9"), 2)
        2 -> Pentuple("Which of these is a multiple of 7?", "පහත ඒවායින් 7 හි ගුණාකාරයක් වන්නේ කුමක්ද?", listOf("17", "21", "27", "31"), listOf("17", "21", "27", "31"), 1)
        3 -> Pentuple("Which of the following is a prime number?", "පහත දැක්වෙන සංඛ්‍යාවලින් ප්‍රථමක සංඛ්‍යාවක් වන්නේ කුමක්ද?", listOf("8", "9", "11", "15"), listOf("8", "9", "11", "15"), 2)
        4 -> Pentuple("Find the factors of 15.", "15 හි සාධකවලින් සමන්විත ලැයිස්තුව කුමක්ද?", listOf("1, 3, 5, 15", "1, 5, 15", "3, 5", "1, 15"), listOf("1, 3, 5, 15", "1, 5, 15", "3, 5", "1, 15"), 0)
        5 -> Pentuple("Calculate the sum of the first three multiples of 5.", "5 හි පළමු ගුණාකාර තුනේ එකතුව සොයන්න.", listOf("15", "30", "45", "60"), listOf("15", "30", "45", "60"), 1)
        6 -> Pentuple("Which is the only even prime number?", "ප්‍රථමක සංඛ්‍යාවක් වන එකම ඉරට්ටේ සංඛ්‍යාව කුමක්ද?", listOf("0", "2", "4", "6"), listOf("0", "2", "4", "6"), 1)
        7 -> Pentuple("What is the LCM of 3, 5, and 10?", "3, 5 සහ 10 හි කු.පො.ගු. කුමක්ද?", listOf("15", "30", "45", "60"), listOf("15", "30", "45", "60"), 1)
        8 -> Pentuple("What is the HCF of 15 and 25?", "15 සහ 25 හි ම.පො.ස. කුමක්ද?", listOf("3", "5", "10", "15"), listOf("3", "5", "10", "15"), 1)
        else -> Pentuple("Which number is a factor of every integer?", "සෑම පූර්ණ සංඛ්‍යාවකම සාධකයක් වන සංඛ්‍යාව කුමක්ද?", listOf("0", "1", "2", "10"), listOf("0", "1", "2", "10"), 1)
    }

    private fun getGrade6Stage2(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("If the ratio of boys to girls is 2:3 and there are 12 boys, how many girls are there?", "පිරිමි ළමයින් සහ ගැහැණු ළමයින් අතර අනුපාතය 2:3 ක් වන අතර එහි පිරිමි ළමයින් 12ක් සිටී නම් ගැහැණු ළමයින් කීයක් සිටීද?", listOf("15", "18", "20", "24"), listOf("15", "18", "20", "24"), 1)
        1 -> Pentuple("Solve: (-5) + (+8) = ?", "සුළු කරන්න: (-5) + (+8) = ?", listOf("-13", "-3", "3", "13"), listOf("-13", "-3", "3", "13"), 2)
        2 -> Pentuple("Simplify the ratio 15:25 to its lowest terms.", "15:25 අනුපාතය සරලම ආකාරයෙන් දක්වන්න.", listOf("3:5", "5:3", "3:10", "1:5"), listOf("3:5", "5:3", "3:10", "1:5"), 0)
        3 -> Pentuple("What is the opposite number of -12?", "-12 හි ප්‍රතිවිරුද්ධ සංඛ්‍යාව කුමක්ද?", listOf("0", "-12", "+12", "-1/12"), listOf("0", "-12", "+12", "-1/12"), 2)
        4 -> Pentuple("Calculate: (-3) + (-4) = ?", "ගණනය කරන්න: (-3) + (-4) = ?", listOf("-7", "-1", "1", "7"), listOf("-7", "-1", "1", "7"), 0)
        5 -> Pentuple("Divide 100 LKR in the ratio of 1:4. What is the smaller share?", "රුපියල් 100ක මුදලක් 1:4 අනුපාතයට බෙදූවිට ලැබෙන කුඩාම කොටස?", listOf("10 LKR", "20 LKR", "25 LKR", "40 LKR"), listOf("රුපියල් 10", "රුපියල් 20", "රුපියල් 25", "රුපියල් 40"), 1)
        6 -> Pentuple("Which integer represents 50 meters below sea level?", "මුහුදු මට්ටමෙන් මීටර් 50ක් ගැඹුර නිරූපණය කරන පූර්ණ සංඛ්‍යාව කුමක්ද?", listOf("+50", "-50", "0", "-0.5"), listOf("+50", "-50", "0", "-0.5"), 1)
        7 -> Pentuple("Evaluate: (+10) - (+3) = ?", "සුළු කරන්න: (+10) - (+3) = ?", listOf("-7", "+7", "+13", "-13"), listOf("-7", "+7", "+13", "-13"), 1)
        8 -> Pentuple("Express 4:10 as a fraction.", "4:10 අනුපාතය භාගයක් ලෙස දක්වන්න.", listOf("2/5", "1/2", "3/5", "4/5"), listOf("2/5", "1/2", "3/5", "4/5"), 0)
        else -> Pentuple("Solve: (-7) - (-2) = ?", "සුළු කරන්න: (-7) - (-2) = ?", listOf("-9", "-5", "5", "9"), listOf("-9", "-5", "5", "9"), 1)
    }

    private fun getGrade6Stage3(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("What type of angle is exactly 90 degrees?", "정ශයෙන් අංශක 90ක් වන කෝණය හඳුන්වන්නේ කුමක් ලෙසද?", listOf("Acute", "Right Angle", "Obtuse", "Straight"), listOf("සුළු කෝණය", "සෘජු කෝණය", "මහා කෝණය", "සරල රේඛීය කෝණය"), 1)
        1 -> Pentuple("Evaluate the algebraic expression 3x - 5 when x = 4.", "x = 4 වන විට 3x - 5 වීජීය ප්‍රකාශනයේ අගය සොයන්න.", listOf("5", "7", "12", "17"), listOf("5", "7", "12", "17"), 1)
        2 -> Pentuple("What is an angle larger than 90 degrees but less than 180 degrees called?", "අංශක 90ට වඩා වැඩි සහ 180ට වඩා අඩු කෝණ මොනවාද?", listOf("Acute", "Right Angle", "Obtuse", "Reflex"), listOf("සුළු කෝණය", "සෘජු කෝණය", "මහා කෝණය", "පරාවර්තී කෝණය"), 2)
        3 -> Pentuple("Simplify: 2a + 3b + 5a - b = ?", "සුළු කරන්න: 2a + 3b + 5a - b = ?", listOf("7a + 2b", "7a + 4b", "10ab", "7a - 2b"), listOf("7a + 2b", "7a + 4b", "10ab", "7a - 2b"), 0)
        4 -> Pentuple("An angle measuring 45 degrees is classified as:", "අංශක 45ක කෝණයක් වර්ග කෙරෙන්නේ:", listOf("Acute", "Obtuse", "Reflex", "Straight"), listOf("සුළු කෝණය", "මහා කෝණය", "පරාවර්තී කෝණය", "සරල රේඛීය කෝණය"), 0)
        5 -> Pentuple("If y = -2, what is the value of 5 - y?", "y = -2 නම් 5 - y හි අගය කුමක්ද?", listOf("3", "5", "7", "-7"), listOf("3", "5", "7", "-7"), 2)
        6 -> Pentuple("What is the sum of angles on a straight line?", "සරල රේඛාවක පිහිටි කෝණවල එකතුව කොපමණද?", listOf("90°", "180°", "270°", "360°"), listOf("90°", "180°", "270°", "360°"), 1)
        7 -> Pentuple("Simplify: x + x + y - x = ?", "සුළු කරන්න: x + x + y - x = ?", listOf("x + y", "2x + y", "2xy", "x - y"), listOf("x + y", "2x + y", "2xy", "x - y"), 0)
        8 -> Pentuple("What is an angle larger than 180 degrees but less than 360 degrees called?", "අංශක 180ට වඩා වැඩි සහ अංශක 360ට වඩා අඩු කෝණය?", listOf("Obtuse", "Straight", "Reflex", "Acute"), listOf("මහා කෝණය", "සරල රේඛීය කෝණය", "පරාවර්තී කෝණය", "සුළු කෝණය"), 2)
        else -> Pentuple("If 2x = 10, what is the value of x + 4?", "2x = 10 නම් x + 4 හි අගය කුමක්ද?", listOf("5", "9", "14", "24"), listOf("5", "9", "14", "24"), 1)
    }

    // GRADE 7
    private fun getGrade7Stage1(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("Solve: 2x - 3 = 7. Find x.", "විසඳන්න: 2x - 3 = 7. x හි අගය සොයන්න.", listOf("2", "4", "5", "10"), listOf("2", "4", "5", "10"), 2)
        1 -> Pentuple("If set A = {2, 4, 6, 8}, what is the number of elements of A, n(A)?", "A = {2, 4, 6, 8} නම් A කුලකයේ මූලද්‍රව්‍ය ගණන වන n(A) සොයන්න.", listOf("2", "4", "6", "8"), listOf("2", "4", "6", "8"), 1)
        2 -> Pentuple("Solve: 5y + 2 = 17. Find y.", "විසඳන්න: 5y + 2 = 17. y හි අගය සොයන්න.", listOf("2", "3", "4", "5"), listOf("2", "3", "4", "5"), 1)
        3 -> Pentuple("Which set has no elements?", "කිසිදු මූලද්‍රව්‍යයක් නොමැති කුලකය හඳුන්වන්නේ කුමක් ලෙසද?", listOf("Universal set", "Finite set", "Infinite set", "Null / Empty set"), listOf("විශ්ව කුලකය", "සන්තක කුලකය", "අනන්ත කුලකය", "ශුන්‍ය / හිස් කුලකය"), 3)
        4 -> Pentuple("Solve: x/2 = 6. Find x.", "විසඳන්න: x/2 = 6. x හි අගය සොයන්න.", listOf("3", "4", "8", "12"), listOf("3", "4", "8", "12"), 3)
        5 -> Pentuple("If B = {x | x is a letter in 'CAT'}, what is n(B)?", "B = {x | x යනු 'CAT' වචනයේ අකුරකි} නම් n(B) සෙයන්න.", listOf("1", "2", "3", "4"), listOf("1", "2", "3", "4"), 2)
        6 -> Pentuple("Solve: 3(x + 2) = 15. Find x.", "විසඳන්න: 3(x + 2) = 15. x හි අගය සොයන්න.", listOf("3", "4", "5", "7"), listOf("3", "4", "5", "7"), 0)
        7 -> Pentuple("Which of the following describes finite set?", "පහත ඒවායින් පරිමිත කුලකයක් දැක්වෙන්නේ කුමකින්ද?", listOf("{1, 2, 3...}", "Set of stars in sky", "{1, 3, 5, 7}", "{even numbers}"), listOf("{1, 2, 3...}", "අහසේ තරු කුලකය", "{1, 3, 5, 7}", "{ඉරට්ටේ සංඛ්‍යා}"), 2)
        8 -> Pentuple("Solve: 12 - x = 8. Find x.", "විසඳන්න: 12 - x = 8. x හි අගය සොයන්න.", listOf("2", "3", "4", "5"), listOf("2", "3", "4", "5"), 2)
        else -> Pentuple("If U = {1, 2, 3, 4, 5} and A = {2, 4}, what is A' (complement)?", "U = {1, 2, 3, 4, 5} සහ A = {2, 4} නම් A' (අනුපූරක කුලකය) සොයන්න.", listOf("{1, 3, 5}", "{1, 2, 3}", "{2, 4}", "{5}"), listOf("{1, 3, 5}", "{1, 2, 3}", "{2, 4}", "{5}"), 0)
    }

    private fun getGrade7Stage2(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("The sum of angles in any triangle is exactly:", "ඕනෑම ත්‍රිකෝණයක අභ්‍යන්තර කෝණවල එකතුව කොපමණද?", listOf("90°", "180°", "270°", "360°"), listOf("90°", "180°", "270°", "360°"), 1)
        1 -> Pentuple("Two angles in a triangle are 50° and 60°. What is the third angle?", "ත්‍රිකෝණයක කෝණ දෙකක් 50° සහ 60° වේ. තෙවන කෝණය කොපමණද?", listOf("50°", "60°", "70°", "80°"), listOf("50°", "60°", "70°", "80°"), 2)
        2 -> Pentuple("If two parallel lines are cut by transversal, what type of equal angles makes a 'Z' shape?", "සමාන්තර රේඛා දෙකක් හරස් කපන රේඛාවකින් කැපීමේදී Z අකුරේ හැඩයට සෑදෙන සමාන කෝණ වර්ගය කුමක්ද?", listOf("Adjacent", "Vertically opposite", "Alternate angles", "Corresponding"), listOf("බද්ධ කෝණ", "ප්‍රතිමුඛ කෝණ", "ඒකාන්තර කෝණ", "අනුරූප කෝණ"), 2)
        3 -> Pentuple("What is the complement of a 40° angle?", "අංශක 40ක කෝණයක අනුපූරක කෝණය (කෝණ දෙකක එකතුව 90° වන) කොපමණද?", listOf("50°", "90°", "140°", "180°"), listOf("50°", "90°", "140°", "180°"), 0)
        4 -> Pentuple("What is the supplement of a 110° angle?", "අංශක 110ක කෝණයක පරිපූරක කෝණය (කෝණ දෙකක එකතුව 180° වන) කොපමණද?", listOf("40°", "70°", "90°", "180°"), listOf("40°", "70°", "90°", "180°"), 1)
        5 -> Pentuple("In a right-angled triangle, one angle is 90° and another is 35°. Find the third angle.", "සෘජුකෝණී ත්‍රිකෝණයක එක් කෝණයක් 90° ක් සහ තවත් කෝණයක් 35° ක් වේ. තෙවන කෝණය සොයන්න.", listOf("45°", "55°", "65°", "90°"), listOf("45°", "55°", "65°", "90°"), 1)
        6 -> Pentuple("Vertically opposite angles are always:", "ප්‍රතිමුඛ කෝණ සෑමවිටම:", listOf("Complementary", "Supplementary", "Equal", "Unequal"), listOf("අනුපූරක වේ", "පරිපූරක වේ", "සමාන වේ", "අසමාන වේ"), 2)
        7 -> Pentuple("The sum of angles at a point is:", "ලක්ෂ්‍යයක් වටා ඇති කෝණවල එකතුව කොපමණද?", listOf("90°", "180°", "360°", "540°"), listOf("90°", "180°", "360°", "540°"), 2)
        8 -> Pentuple("If an isosceles triangle has one vertex angle of 40°, what is the value of each equal base angle?", "සමද්වීපාද ත්‍රිකෝණයක ශීර්ෂ කෝණය 40° ක් නම් එක් පාද කෝණයක අගය කොපමණද?", listOf("40°", "70°", "80°", "140°"), listOf("40°", "70°", "80°", "140°"), 1)
        else -> Pentuple("Parallel line corresponding angles make which letter shape layout?", "සමාන්තර රේඛාවල පිහිටන අනුරූප කෝණ සාමාන්‍යයෙන් කුමන අකුරේ හැඩය ගනීද?", listOf("Z", "X", "F", "C"), listOf("Z", "X", "F", "C"), 2)
    }

    private fun getGrade7Stage3(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("Find the mean of the data set: 2, 4, 6, 8, 10.", "2, 4, 6, 8, 10 යන දත්ත සමූහයේ මධ්‍යන්‍යය (Mean) සොයන්න.", listOf("4", "5", "6", "8"), listOf("4", "5", "6", "8"), 2)
        1 -> Pentuple("What is the median of the data set: 1, 3, 5, 7, 9, 11?", "1, 3, 5, 7, 9, 11 දත්ත සමූහයේ මධ්‍යස්ථය (Median) සොයන්න.", listOf("5", "6", "7", "8"), listOf("5", "6", "7", "8"), 1)
        2 -> Pentuple("Determine the mode of the data set: 3, 4, 4, 5, 5, 5, 6, 7.", "3, 4, 4, 5, 5, 5, 6, 7 දත්ත සමූහයේ බහුතමය (Mode) සොයන්න.", listOf("4", "5", "6", "7"), listOf("4", "5", "6", "7"), 1)
        3 -> Pentuple("Find the mean of: 10, 20, 30.", "10, 20, 30 යන දත්තවල මධ්‍යන්‍යය සොයන්න.", listOf("10", "20", "30", "60"), listOf("10", "20", "30", "60"), 1)
        4 -> Pentuple("What is the median of: 15, 5, 20, 10, 8? (Sort first)", "15, 5, 20, 10, 8 යන දත්තවල මධ්‍යස්ථය සොයන්න. (පෙළගස්වන්න)", listOf("8", "10", "15", "20"), listOf("8", "10", "15", "20"), 1)
        5 -> Pentuple("A student received marks: 40, 50, 40, 60. What is the mode of his marks?", "සිසුවෙකු ලබාගත් ලකුණු: 40, 50, 40, 60. බහුතමය කුමක්ද?", listOf("40", "45", "50", "60"), listOf("40", "45", "50", "60"), 0)
        6 -> Pentuple("What is the range of the data set: 15, 25, 45, 12, 50?", "15, 25, 45, 12, 50 යන දත්ත මාලාවේ පරාසය (Range) සොයන්න.", listOf("35", "38", "45", "50"), listOf("35", "38", "45", "50"), 1)
        7 -> Pentuple("If five numbers have a mean of 8, what is their total sum?", "සංඛ්‍යා 5ක මධ්‍යන්‍යය 8ක් නම්, ඒවායේ මුළු එකතුව කොපමණද?", listOf("13", "35", "40", "45"), listOf("13", "35", "40", "45"), 2)
        8 -> Pentuple("Find the mean of: 1.5, 2.5, 3.5, 4.5.", "1.5, 2.5, 3.5, 4.5 දත්තවල මධ්‍යන්‍යය සොයන්න.", listOf("2.5", "3.0", "3.5", "4.0"), listOf("2.5", "3.0", "3.5", "4.0"), 1)
        else -> Pentuple("Determine the median of the values: 12, 10, 14, 18, 16.", "12, 10, 14, 18, 16 යන අගයන්ගේ මධ්‍යස්ථය සොයන්න.", listOf("12", "14", "16", "18"), listOf("12", "14", "16", "18"), 1)
    }

    // GRADE 8
    private fun getGrade8Stage1(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("Solve: x + y = 10, x - y = 4. What is x?", "විසඳන්න: x + y = 10, x - y = 4. x හි අගය කීයද?", listOf("5", "6", "7", "8"), listOf("5", "6", "7", "8"), 2)
        1 -> Pentuple("If 2x + y = 11 and x + y = 6, find the value of x.", "2x + y = 11 සහ x + y = 6 නම් x හි අගය සොයන්න.", listOf("3", "4", "5", "6"), listOf("3", "4", "5", "6"), 2)
        2 -> Pentuple("Expand the algebraic expression: 3(2x - 5) = ?", "වීජීය ප්‍රකාශනය ප්‍රසාරණය කරන්න: 3(2x - 5) = ?", listOf("6x - 5", "6x - 15", "5x - 15", "6x + 15"), listOf("6x - 5", "6x - 15", "5x - 15", "6x + 15"), 1)
        3 -> Pentuple("Solve corresponding values: a + b = 15, a = 2b. What is b?", "අගයන් විසඳන්න: a + b = 15, a = 2b. b හි අගය සොයන්න.", listOf("3", "4", "5", "6"), listOf("3", "4", "5", "6"), 2)
        4 -> Pentuple("Expand: (x + 3)(x + 2) = ?", "ප්‍රසාරණය කරන්න: (x + 3)(x + 2) = ?", listOf("x^2 + 5x + 5", "x^2 + 5x + 6", "x^2 + 6x + 6", "x^2 + 6"), listOf("x^2 + 5x + 5", "x^2 + 5x + 6", "x^2 + 6x + 6", "x^2 + 6"), 1)
        5 -> Pentuple("Solve: 3x - y = 5, y = 1. What is x?", "විසඳන්න: 3x - y = 5, y = 1. x හි අගය කීයද?", listOf("1", "2", "3", "4"), listOf("1", "2", "3", "4"), 1)
        6 -> Pentuple("Expand: (a - 4)(a + 4) = ?", "ප්‍රසාරණය කරන්න: (a - 4)(a + 4) = ?", listOf("a^2 - 16", "a^2 - 8", "a^2 + 16", "a^2 - 8a - 16"), listOf("a^2 - 16", "a^2 - 8", "a^2 + 16", "a^2 - 8a - 16"), 0)
        7 -> Pentuple("Find the sum: (2x + 3) + (x - 7) = ?", "එකතුව සොයන්න: (2x + 3) + (x - 7) = ?", listOf("3x + 10", "3x - 4", "3x + 4", "3x - 10"), listOf("3x + 10", "3x - 4", "3x + 4", "3x - 10"), 1)
        8 -> Pentuple("Solve the simultaneous pair: x - y = 1, x + y = 9. Find value of y.", "යුගල සමීකරණ විසඳන්න: x - y = 1, x + y = 9. y හි අගය සොයන්න.", listOf("3", "4", "5", "6"), listOf("3", "4", "5", "6"), 1)
        else -> Pentuple("Expand: 2y(y + 4) = ?", "ප්‍රසාරණය කරන්න: 2y(y + 4) = ?", listOf("2y^2 + 8", "2y^2 + 8y", "2y + 8", "2y^2 + 4y"), listOf("2y^2 + 8", "2y^2 + 8y", "2y + 8", "2y^2 + 4y"), 1)
    }

    private fun getGrade8Stage2(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("Factorize: x^2 - 9 = ?", "සාධකවලට වෙන් කරන්න: x^2 - 9 = ?", listOf("(x - 3)(x - 3)", "(x - 3)(x + 3)", "(x + 3)(x + 3)", "x(x - 9)"), listOf("(x - 3)(x - 3)", "(x - 3)(x + 3)", "(x + 3)(x + 3)", "x(x - 9)"), 1)
        1 -> Pentuple("Factorize the trinomial: x^2 + 5x + 6 = ?", "තිපද ප්‍රකාශනය සාධකවලට වෙන් කරන්න: x^2 + 5x + 6 = ?", listOf("(x + 1)(x + 6)", "(x + 2)(x + 3)", "(x - 2)(x - 3)", "(x + 5)(x + 1)"), listOf("(x + 1)(x + 6)", "(x + 2)(x + 3)", "(x - 2)(x - 3)", "(x + 5)(x + 1)"), 1)
        2 -> Pentuple("Evaluate: 2^3 * 2^2 = ?", "සුළු කරන්න: 2^3 * 2^2 = ?", listOf("12", "16", "32", "64"), listOf("12", "16", "32", "64"), 2)
        3 -> Pentuple("What is the index value of 3^-2?", "3^-2 හි දර්ශක අගය කුමක්ද?", listOf("1/6", "1/9", "9", "-9"), listOf("1/6", "1/9", "9", "-9"), 1)
        4 -> Pentuple("Evaluate: (5^2)^3 = ?", "සුළු කරන්න: (5^2)^3 = ?", listOf("5^5", "5^6", "5^8", "25^3"), listOf("5^5", "5^6", "5^8", "25^3"), 1)
        5 -> Pentuple("Factorize: x^2 + x - 12 = ?", "සාධකවලට වෙන් කරන්න: x^2 + x - 12 = ?", listOf("(x - 3)(x - 4)", "(x + 4)(x - 3)", "(x - 4)(x + 3)", "(x - 12)(x + 1)"), listOf("(x - 3)(x - 4)", "(x + 4)(x - 3)", "(x - 4)(x + 3)", "(x - 12)(x + 1)"), 1)
        6 -> Pentuple("What is the value of 100^0?", "100^0 හි අගය කොපමණද?", listOf("0", "1", "100", "1000"), listOf("0", "1", "100", "1000"), 1)
        7 -> Pentuple("Evaluate: 10^5 / 10^3 = ?", "සුළු කරන්න: 10^5 / 10^3 = ?", listOf("10", "100", "1000", "10^8"), listOf("10", "100", "1000", "10^8"), 1)
        8 -> Pentuple("Factorize the expression: 4x + 12 = ?", "ප්‍රකාශනයේ පොදු සාධක වෙන් කරන්න: 4x + 12 = ?", listOf("4(x + 3)", "4(x + 12)", "2(2x + 10)", "4x(1 + 3)"), listOf("4(x + 3)", "4(x + 12)", "2(2x + 10)", "4x(1 + 3)"), 0)
        else -> Pentuple("Solve for index power: 2^n = 16. What is n?", "දර්ශකය විසඳන්න: 2^n = 16 නම් n හි අගය සොයන්න.", listOf("3", "4", "5", "8"), listOf("3", "4", "5", "8"), 1)
    }

    private fun getGrade8Stage3(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("What is the probability of tossing a fair coin and getting a Head?", "සමබර කාසියක් උඩ දැමූවිට හිස ලැබීමේ සම්භාවිතාවය කුමක්ද?", listOf("0", "1/4", "1/2", "1"), listOf("0", "1/4", "1/2", "1"), 2)
        1 -> Pentuple("A bag contains 3 red balls and 7 blue balls. What is the probability of picking a red ball?", "මල්ලක රතු බෝල 3ක් සහ නිල් බෝල 7ක් ඇත. සසම්භාවීව ගන්නා බෝලයක් රතු වීමේ සම්භාවිතාවය?", listOf("3/10", "7/10", "1/2", "3/7"), listOf("3/10", "7/10", "1/2", "3/7"), 0)
        2 -> Pentuple("What is the circumference of a circle with a radius of 7 cm? (Take π = 22/7)", "අරය සෙ.මී. 7ක් වන වෘත්තයක පරිධිය සොයන්න. (π = 22/7)", listOf("22 cm", "44 cm", "88 cm", "154 cm"), listOf("සෙ.මී. 22", "සෙ.මී. 44", "සෙ.මී. 88", "සෙ.මී. 154"), 1)
        3 -> Pentuple("Calculate the area of a circle with a radius of 7 cm. (Take π = 22/7)", "අරය සෙ.මී. 7ක් වන වෘත්තයක වර්ගඵලය සොයන්න. (π = 22/7)", listOf("44 sq.cm", "88 sq.cm", "154 sq.cm", "308 sq.cm"), listOf("වර්ග සෙ.මී. 44", "වර්ග සෙ.මී. 88", "වර්ග සෙ.මී. 154", "වර්ග සෙ.මී. 308"), 2)
        4 -> Pentuple("If the probability of rain tomorrow is 0.3, what is the probability of no rain?", "හෙට වැස්ස ලැබීමේ සම්භාවිතාවය 0.3ක් නම් වැස්ස නොලැබීමේ සම්භාවිතාවය කුමක්ද?", listOf("0.3", "0.5", "0.7", "1.0"), listOf("0.3", "0.5", "0.7", "1.0"), 2)
        5 -> Pentuple("What is the relation between diameter (d) and radius (r) of a circle?", "වෘත්තයක විෂ්කම්භය (d) සහ අරය (r) අතර සම්බන්ධය කුමක්ද?", listOf("d = r", "d = 2r", "r = 2d", "d = r^2"), listOf("d = r", "d = 2r", "r = 2d", "d = r^2"), 1)
        6 -> Pentuple("Two fair 6-sided dice are rolled. How many outcomes are there in sample space?", "සමබර දාදු කැට දෙකක් දැමූවිට නියැදි අවකාශයේ පවතින මුළු සිදුවීම් ගණන?", listOf("12", "18", "36", "64"), listOf("12", "18", "36", "64"), 2)
        7 -> Pentuple("If a circle's diameter is 20 cm, what is its radius?", "වෘත්තයක විෂ්කම්භය සෙ.මී. 20ක් නම් එහි අරය කොපමණද?", listOf("5 cm", "10 cm", "15 cm", "40 cm"), listOf("සෙ.මී. 5", "සෙ.මී. 10", "සෙ.මී. 15", "සෙ.මී. 40"), 1)
        8 -> Pentuple("What is the probability of getting a number 7 when rolling a standard six-sided die?", "සාමාන්‍ය දාදු කැටයක් දැමූවිට 7 අංකය ලැබීමේ සම්භාවිතාවය කුමක්ද?", listOf("0", "1/6", "1/2", "1"), listOf("0", "1/6", "1/2", "1"), 0)
        else -> Pentuple("Find the boundary perimeter of a semi-circular plate with radius 7 cm. (Circumference arc + diameter, π=22/7)", "අරය සෙ.මී. 7ක් වන අර්ධ වෘත්තාකාර තහඩුවක මුළු මායිම් පරිමිතිය සොයන්න. (චාප දිග + විෂ්කම්භය, π=22/7)", listOf("22 cm", "36 cm", "44 cm", "58 cm"), listOf("සෙ.මී. 22", "සෙ.මී. 36", "සෙ.මී. 44", "සෙ.මී. 58"), 1)
    }

    // GRADE 9
    private fun getGrade9Stage1(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("In a right triangle, which trigonometric ratio is defined as (Opposite / Hypotenuse)?", "සෘජුකෝණී ත්‍රිකෝණයක (සම්මුඛ පාදය / විකර්ණය) මඟින් නියෝජනය වන ත්‍රිකෝණමිතික අනුපාතය?", listOf("sin", "cos", "tan", "sec"), listOf("sin", "cos", "tan", "sec"), 0)
        1 -> Pentuple("In a right triangle, which ratio is defined as (Adjacent / Hypotenuse)?", "සෘජුකෝණී ත්‍රිකෝණයක (බද්ධ පාදය / විකර්ණය) මඟින් නියෝජනය වන ත්‍රිකෝණමිතික අනුපාතය?", listOf("sin", "cos", "tan", "cosec"), listOf("sin", "cos", "tan", "cosec"), 1)
        2 -> Pentuple("What is the ratio defined as (Opposite / Adjacent)?", "(සම්මුඛ පාදය / බද්ධ පාදය) මඟින් නියෝජනය වන ත්‍රිකෝණමිතික අනුපාතය කුමක්ද?", listOf("sin", "cos", "tan", "cot"), listOf("sin", "cos", "tan", "cot"), 2)
        3 -> Pentuple("What is the value of sin(30°)?", "sin(30°) හි අගය කොපමණද?", listOf("0", "1/2", "sqrt(3)/2", "1"), listOf("0", "1/2", "sqrt(3)/2", "1"), 1)
        4 -> Pentuple("What is the value of cos(60°)?", "cos(60°) හි අගය කොපමණද?", listOf("0", "1/2", "sqrt(3)/2", "1"), listOf("0", "1/2", "sqrt(3)/2", "1"), 1)
        5 -> Pentuple("If θ is a sharp angle and sin(θ) = 1, what is the value of θ?", "sin(θ) = 1 නම් θ හි කෝණික අගය කුමක්ද?", listOf("0°", "30°", "45°", "90°"), listOf("0°", "30°", "45°", "90°"), 3)
        6 -> Pentuple("What is the trig value of tan(45°)?", "tan(45°) හි අගය කොපමණද?", listOf("0", "1/2", "1", "undefined"), listOf("0", "1/2", "1", "undefined"), 2)
        7 -> Pentuple("In a right-angled triangle with hypotenuse 10 cm and opposite side 5 cm, find sin(θ).", "විකර්ණය සෙ.මී. 10ක් සහ සම්මුඛ පාදය සෙ.මී. 5ක් වන සෘජුකෝණී ත්‍රිකෝණයක sin(θ) සොයන්න.", listOf("0.5", "0.8", "1.0", "2.0"), listOf("0.5", "0.8", "1.0", "2.0"), 0)
        8 -> Pentuple("If tan(θ) = 3/4, then find the ratio of (Opposite / Adjacent).", "tan(θ) = 3/4 නම් (සම්මුඛ පාදය / බද්ධ පාදය) අනුපාතය කුමක්ද?", listOf("3/4", "4/3", "3/5", "4/5"), listOf("3/4", "4/3", "3/5", "4/5"), 0)
        else -> Pentuple("What is the value of cos(90°)?", "cos(90°) හි අගය කොපමණද?", listOf("0", "1/2", "1", "undefined"), listOf("0", "1/2", "1", "undefined"), 0)
    }

    private fun getGrade9Stage2(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("Find the gradient (m) of the line passing through coordinates (1, 2) and (3, 6).", "(1, 2) සහ (3, 6) ලක්ෂ්‍ය හරහා යන සරල රේඛාවේ අනුක්‍රමණය (m) සොයන්න.", listOf("1", "2", "3", "4"), listOf("1", "2", "3", "4"), 1)
        1 -> Pentuple("In the line equation y = mx + c, what does 'c' represent?", "y = mx + c සරල රේඛා සමීකරණයේ 'c' මඟින් නියෝජනය කරන්නේ කුමක්ද?", listOf("Gradient", "X-intercept", "Y-intercept", "Coordinates origin"), listOf("අනුක්‍රමණය", "X-අන්තඃඛණ්ඩය", "Y-අන්තඃඛණ්ඩය", "මූලලක්ෂ්‍ය ඛණ්ඩාංක"), 2)
        2 -> Pentuple("Determine the gradient of the line y = 3x - 5.", "y = 3x - 5 සරල රේඛාවේ අනුක්‍රමණය සොයන්න.", listOf("-5", "3", "5", "8"), listOf("-5", "3", "5", "8"), 1)
        3 -> Pentuple("Find the midpoint of the line segment joining (2, 8) and (4, 12).", "(2, 8) සහ (4, 12) ලක්ෂ්‍ය සම්බන්ධ කරන රේඛාවේ මධ්‍ය ලක්ෂ්‍යය සොයන්න.", listOf("(3, 10)", "(2, 10)", "(3, 8)", "(4, 10)"), listOf("(3, 10)", "(2, 10)", "(3, 8)", "(4, 10)"), 0)
        4 -> Pentuple("What is the equation of a line passing through origin with gradient 2?", "මූල ලක්ෂ්‍යය (0,0) හරහා යන 2ක අනුක්‍රමණයක් ඇති රේඛාවේ සමීකරණය කුමක්ද?", listOf("y = 2", "x = 2", "y = 2x", "y = x + 2"), listOf("y = 2", "x = 2", "y = 2x", "y = x + 2"), 2)
        5 -> Pentuple("What is the gradient of a horizontal line parallel to X axis?", "X-අක්ෂයට සමාන්තර තිරස් රේඛාවක අනුක්‍රමණය කොපමණද?", listOf("0", "1", "-1", "Infinite"), listOf("0", "1", "-1", "අනන්තය"), 0)
        6 -> Pentuple("Find the y-intercept of the line 2y = 4x + 6.", "2y = 4x + 6 රේඛාවේ Y-අන්තඃඛණ්ඩය සොයන්න.", listOf("3", "6", "2", "4"), listOf("3", "6", "2", "4"), 0)
        7 -> Pentuple("If a line has gradient 4 and passing through (0, 5), find its equation.", "අනුක්‍රමණය 4ක් වන සහ Y-අක්ෂය (0,5) හිදී කපන සරල රේඛාවේ සමීකරණය කුමක්ද?", listOf("y = 4x - 5", "y = 5x + 4", "y = 4x + 5", "y = x/4 + 5"), listOf("y = 4x - 5", "y = 5x + 4", "y = 4x + 5", "y = x/4 + 5"), 2)
        8 -> Pentuple("Which of the following lines is parallel to y = -2x + 8?", "පහත දැක්වෙන රේඛාවලින් y = -2x + 8 රේඛාවට සමාන්තර වන්නේ කුමක්ද?", listOf("y = 2x + 1", "y = -2x - 3", "y = x/2 + 8", "y = -x/2 + 5"), listOf("y = 2x + 1", "y = -2x - 3", "y = x/2 + 8", "y = -x/2 + 5"), 1)
        else -> Pentuple("Determine the gradient of line passing through (0, 0) and (2, 8).", "මූල ලක්ෂ්‍යය (0, 0) සහ (2, 8) හරහා යන සරල රේඛාවේ අනුක්‍රමණය සොයන්න.", listOf("1", "2", "4", "8"), listOf("1", "2", "4", "8"), 2)
    }

    private fun getGrade9Stage3(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("If a matrix A has dimensions 2x3 and matrix B has dimensions 3x4, can we multiply A x B?", "A නියුහයේ ප්‍රමාණය 2x3 ක් සහ B නියුහයේ ප්‍රමාණය 3x4 ක් නම් A x B ගුණ කිරීම කළ හැකිද?", listOf("No", "Yes", "Only if square", "None"), listOf("නොහැක", "හැක", "සමචතුරස්‍ර නම් පමණි", "කිසිවක් නොවේ"), 1)
        1 -> Pentuple("A matrix has 4 elements. Which of the following cannot be its dimensions?", "නියුහයක මූලද්‍රව්‍ය 4ක් ඇත. පහත ඒවායින් එම නියුහයේ ප්‍රමාණය (dimension) විය නොහැක්කේ කුමක්ද?", listOf("1x4", "2x2", "3x2", "4x1"), listOf("1x4", "2x2", "3x2", "4x1"), 2)
        2 -> Pentuple("In a Venn diagram, if A and B are mutually exclusive, then n(A ∩ B) = ?", "වෙන් රූප සටහනක A සහ B පරස්පර වියුක්ත කුලක දෙකක් නම් n(A ∩ B) = ?", listOf("0", "1", "n(A) + n(B)", "Infinite"), listOf("0", "1", "n(A) + n(B)", "අනන්තය"), 0)
        3 -> Pentuple("Identify the identity matrix of 2x2 size.", "ප්‍රමාණය 2x2 වන ඒකක නියුහය (Identity Matrix) හඳුන්වන්න.", listOf("[[0, 1], [1, 0]]", "[[1, 0], [0, 1]]", "[[1, 1], [1, 1]]", "[[0, 0], [0, 0]]"), listOf("[[0, 1], [1, 0]]", "[[1, 0], [0, 1]]", "[[1, 1], [1, 1]]", "[[0, 0], [0, 0]]"), 1)
        4 -> Pentuple("In a class of 30 pupils, 18 study Music and 15 Art. If 5 study both, how many study neither?", "සිසුන් 30ක පන්තියක 18ක් සංගීතයද 15ක් චිත්‍ර කලාවද හදාරති. 5දෙනෙක් විෂයන් දෙකම හදාරන්නේ නම් කිසිවක් හදාරන්නේ නැති ගණන?", listOf("1", "2", "3", "5"), listOf("1", "2", "3", "5"), 1)
        5 -> Pentuple("If A is a 2x2 matrix: [[5, 2], [1, 3]]. What is 2A?", "A නියුහය [[5, 2], [1, 3]] නම් 2A නියුහය කුමක්ද?", listOf("[[10, 2], [1, 6]]", "[[10, 4], [2, 6]]", "[[5, 4], [2, 3]]", "[[10, 4], [1, 3]]"), listOf("[[10, 2], [1, 6]]", "[[10, 4], [2, 6]]", "[[5, 4], [2, 3]]", "[[10, 4], [1, 3]]"), 1)
        6 -> Pentuple("The transpose of a row matrix is which type of matrix?", "පේළි නියුහයක ට්‍රාන්ස්පෝස් (Transpose) නියුහය කුමන ආකාරයේ එකක්ද?", listOf("Row", "Column", "Diagonal", "Symmetric"), listOf("පේළි නියුහයක්", "තීරු නියුහයක්", "විකර්ණ නියුහයක්", "සමමිතික නියුහයක්"), 1)
        7 -> Pentuple("Calculate final value: n(A ∪ B) = n(A) + n(B) - ?", "සූත්‍රය සම්පූර්ණ කරන්න: n(A ∪ B) = n(A) + n(B) - ?", listOf("n(A ∩ B)", "n(A')", "n(B')", "n(U)"), listOf("n(A ∩ B)", "n(A')", "n(B')", "n(U)"), 0)
        8 -> Pentuple("What is the transpose of [[1, 2], [3, 4]]?", "[[1, 2], [3, 4]] නියුහයේ ට්‍රාන්ස්පෝස් (Transpose) අගය කුමක්ද?", listOf("[[3, 4], [1, 2]]", "[[1, 3], [2, 4]]", "[[4, 2], [3, 1]]", "[[1, 2], [3, 4]]"), listOf("[[3, 4], [1, 2]]", "[[1, 3], [2, 4]]", "[[4, 2], [3, 1]]", "[[1, 2], [3, 4]]"), 1)
        else -> Pentuple("In Venn diagrams, the rectangle represent which set?", "වෙන් රූප සටහන්වල ඇති සෘජුකෝණාස්‍රයෙන් නිරූපණය වන කුලකය කුමක්ද?", listOf("Subset", "Null set", "Universal set", "Intersection"), listOf("උපකුලකය", "හිස් කුලකය", "විශ්ව කුලකය", "ඡේදනය"), 2)
    }

    // GRADE 10
    private fun getGrade10Stage1(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("What are the roots of the quadratic equation x^2 - 5x + 6 = 0?", "x^2 - 5x + 6 = 0 වර්ගජ සමීකරණයේ මූලයන් සොයන්න.", listOf("x = 1, 6", "x = 2, 3", "x = -2, -3", "x = 0, 5"), listOf("x = 1, 6", "x = 2, 3", "x = -2, -3", "x = 0, 5"), 1)
        1 -> Pentuple("What formula calculates the discriminant (Delta) of ax^2 + bx + c = 0?", "ax^2 + bx + c = 0 වර්ගජ සමීකරණයේ විවේචකය (Delta) ගණනය කරන සූත්‍රය කුමක්ද?", listOf("b - 4ac", "b^2 - 4ac", "sqrt(b^2 - 4ac)", "4ac - b^2"), listOf("b - 4ac", "b^2 - 4ac", "sqrt(b^2 - 4ac)", "4ac - b^2"), 1)
        2 -> Pentuple("If discriminant (b^2 - 4ac) of quadratic equation is negative, the roots are:", "වර්ගජ සමීකරණයක විවේචකය සෘණ අගයක් ගනී නම් එහි මූලයන්:", listOf("Real & Equal", "Real & Distinct", "Complex/No Real Roots", "Infinite"), listOf("තාත්වික සහ සමාන වේ", "තාත්වික සහ අසමාන වේ", "තාත්වික නොවේ (Complex)", "අනන්ත වේ"), 2)
        3 -> Pentuple("Find the sum of roots of the quadratic equation x^2 - 7x + 10 = 0.", "x^2 - 7x + 10 = 0 වර්ගජ සමීකරණයේ මූලයන්ගේ එකතුව සොයන්න.", listOf("7", "-7", "10", "17"), listOf("7", "-7", "10", "17"), 0)
        4 -> Pentuple("Find the product of roots of quadratic equation x^2 - 4x - 12 = 0.", "x^2 - 4x - 12 = 0 වර්ගජ සමීකරණයේ මූලයන්ගේ ගුණිතය සොයන්න.", listOf("4", "-4", "12", "-12"), listOf("4", "-4", "12", "-12"), 3)
        5 -> Pentuple("Determine the roots of x^2 - 16 = 0.", "x^2 - 16 = 0 හි මූලයන් සොයන්න.", listOf("x = 4", "x = -4", "x = 4 or -4", "x = 16"), listOf("x = 4", "x = -4", "x = 4 or -4", "x = 16"), 2)
        6 -> Pentuple("If x^2 - 2x + 1 = 0, find its roots.", "x^2 - 2x + 1 = 0 හි මූලයන් සොයන්න.", listOf("x = 1 (equal roots)", "x = -1, 1", "x = -1 (equal roots)", "x = 2"), listOf("x = 1 (සමාන මූල)", "x = -1, 1", "x = -1 (සමාන මූල)", "x = 2"), 0)
        7 -> Pentuple("Evaluate: If x(x - 5) = 0, then what are the values of x?", "x(x - 5) = 0 නම් x හි හැකි අගයන් මොනවාද?", listOf("x = 5", "x = 0 or 5", "x = 0 or -5", "x = -5"), listOf("x = 5", "x = 0 or 5", "x = 0 or -5", "x = -5"), 1)
        8 -> Pentuple("What is the discriminant of x^2 + 4x + 4 = 0?", "x^2 + 4x + 4 = 0 වර්ගජ සමීකරණයේ විවේචකය කොපමණද?", listOf("0", "8", "16", "32"), listOf("0", "8", "16", "32"), 0)
        else -> Pentuple("By completing square, rewrite x^2 + 6x as a perfect square format.", "වර්ග පූර්ණයෙන් x^2 + 6x නිරූපණය කරන්න.", listOf("(x + 3)^2 - 3", "(x + 3)^2 - 9", "(x + 6)^2 - 36", "(x + 3)^2"), listOf("(x + 3)^2 - 3", "(x + 3)^2 - 9", "(x + 6)^2 - 36", "(x + 3)^2"), 1)
    }

    private fun getGrade10Stage2(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("State which of the following is core identity: sin^2(θ) + cos^2(θ) = ?", "මූලික ත්‍රිකෝණමිතික සර්වසාම්‍යය සම්පූර්ණ කරන්න: sin^2(θ) + cos^2(θ) = ?", listOf("0", "1", "2", "sin(θ)"), listOf("0", "1", "2", "sin(θ)"), 1)
        1 -> Pentuple("In an Arithmetic Progression (AP), the formula for nth term (Tn) is:", "සමාන්තර ශ්‍රේඪියක (AP) n වන පදය (Tn) සෙවීමේ සූත්‍රය කුමක්ද?", listOf("a + nd", "a + (n-1)d", "a + (n+1)d", "n/2 (a + l)"), listOf("a + nd", "a + (n-1)d", "a + (n+1)d", "n/2 (a + l)"), 1)
        2 -> Pentuple("In Arithmetic Progression: 3, 7, 11, 15... what is the common difference (d)?", "3, 7, 11, 15... යන සමාන්තර ශ්‍රේඪියේ පොදු අන්තරය (d) සොයන්න.", listOf("3", "4", "7", "11"), listOf("3", "4", "7", "11"), 1)
        3 -> Pentuple("In a Geometric Progression (GP) with first term 'a' and ratio 'r', what is the formula for Tn?", "ගුණෝත්තර ශ්‍රේඪියක (GP) n වන පදය සෙවීමේ සූත්‍රය කුමක්ද?", listOf("a * r^n", "a * r^(n-1)", "a * r^(n+1)", "a * (1 - r^n)"), listOf("a * r^n", "a * r^(n-1)", "a * r^(n+1)", "a * (1 - r^n)"), 1)
        4 -> Pentuple("For AP: 2, 5, 8, 11... Find the 10th term.", "2, 5, 8, 11... සමාන්තර ශ්‍රේඪියේ 10 වන පදය සොයන්න.", listOf("26", "29", "32", "35"), listOf("26", "29", "32", "35"), 1)
        5 -> Pentuple("In GP: 2, 6, 18, 54... what is the common ratio (r)?", "2, 6, 18, 54... ගුණෝත්තර ශ්‍රේඪියේ පොදු අනුපාතය (r) සොයන්න.", listOf("2", "3", "4", "6"), listOf("2", "3", "4", "6"), 1)
        6 -> Pentuple("What is the sum of terms (Sn) formula in AP?", "සමාන්තර ශ්‍රේඪියක පද n එකතුවේ (Sn) සූත්‍රය කුමක්ද?", listOf("n(a + l)", "n/2 [2a + (n-1)d]", "n/2 [a + (n-1)d]", "a * (r^n - 1)"), listOf("n(a + l)", "n/2 [2a + (n-1)d]", "n/2 [a + (n-1)d]", "a * (r^n - 1)"), 1)
        7 -> Pentuple("Find the 5th term of the geometric sequence: 3, 6, 12, 24...", "3, 6, 12, 24... ගුණෝත්තර ශ්‍රේඪියේ 5 වන පදය සොයන්න.", listOf("36", "48", "96", "120"), listOf("36", "48", "96", "120"), 1)
        8 -> Pentuple("Simplify the trigonometric identity expression: 1 - sin^2(θ) = ?", "සුළු කරන්න: 1 - sin^2(θ) = ?", listOf("cos(θ)", "cos^2(θ)", "tan(θ)", "sec^2(θ)"), listOf("cos(θ)", "cos^2(θ)", "tan(θ)", "sec^2(θ)"), 1)
        else -> Pentuple("In GP: 5, 10, 20... find the sum of the first 4 terms.", "5, 10, 20... ගුණෝත්තර ශ්‍රේඪියේ පළමු පද 4 හි එකතුව සොයන්න.", listOf("45", "65", "75", "85"), listOf("45", "65", "75", "85"), 2)
    }

    private fun getGrade10Stage3(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("Find the magnitude of the 2D vector v = 3i + 4j.", "v = 3i + 4j ද්විමාන දෛශිකයේ විශාලත්වය (Magnitude) සොයන්න.", listOf("5", "7", "12", "25"), listOf("5", "7", "12", "25"), 0)
        1 -> Pentuple("If vector a = 2i + j and b = i - 3j, find vector a + b.", "දෛශික a = 2i + j සහ b = i - 3j නම් a + b සොයන්න.", listOf("3i - 2j", "i + 4j", "3i + 4j", "3i - 3j"), listOf("3i - 2j", "i + 4j", "3i + 4j", "3i - 3j"), 0)
        2 -> Pentuple("What is the scalar product (dot product) of vectors v1 = 2i + 3j and v2 = 4i - j?", "v1 = 2i + 3j සහ v2 = 4i - j දෛශිකවල අදිශ ගුණිතය (Dot product) සොයන්න.", listOf("5", "6", "8", "11"), listOf("5", "6", "8", "11"), 0)
        3 -> Pentuple("If vector u = 6i - 8j, what unit vector represents u in the same direction?", "u = 6i - 8j නම් u හි දිශාවේ ඒකක දෛශිකය සොයන්න.", listOf("(3/5)i - (4/5)j", "(6/10)i + (8/10)j", "i - j", "(6)i - (8)j"), listOf("(3/5)i - (4/5)j", "(6/10)i + (8/10)j", "i - j", "(6)i - (8)j"), 0)
        4 -> Pentuple("When calculating the mean of grouped data, what does 'mid-interval value' represent?", "සමූහිත දත්තවල මධ්‍යන්‍යය සෙවීමේදී 'පන්ති මධ්‍ය අගය' කුමක්ද?", listOf("Frequency", "Average of upper and lower limits", "Cumulative frequency", "Range"), listOf("සංඛ්‍යාතය", "පන්ති ප්‍රාන්තරයේ පහළ සහ ඉහළ සීමා දෙකේ සාමාන්‍යය", "සංචිත සංඛ්‍යාතය", "පරාසය"), 1)
        5 -> Pentuple("The scalar product of two perpendicular vectors is always equal to:", "පරස්පර ලම්භක දෛශික දෙකක අදිශ ගුණිතය සෑමවිටම සමාන වන්නේ:", listOf("-1", "0", "1", "Infinite"), listOf("-1", "0", "1", "අනන්තයට"), 1)
        6 -> Pentuple("How are colinear vectors defined?", "ඒක රේඛීය දෛශික (colinear vectors) යනු කුමන ආකාරයේ දෛශිකද?", listOf("Perpendicular", "Parallel or lying on same line", "Equal in magnitude only", "Orthogonal"), listOf("ලම්භක දෛශික", "සමාන්තර හෝ එකම රේඛාවක පිහිටන දෛශික", "විශාලත්වයෙන් පමණක් සමාන", "orthogonal දෛශික"), 1)
        7 -> Pentuple("Convert coordinates polar representation (r, θ) to Cartesian (x, y). What is x?", "ධ්‍රැවීය ඛණ්ඩාංක (r, θ), කාටිසීය ඛණ්ඩාංක (x, y) බවට හැරවීමේදී x සෙවීමේ සූත්‍රය?", listOf("r * sin(θ)", "r * cos(θ)", "r * tan(θ)", "sqrt(r)"), listOf("r * sin(θ)", "r * cos(θ)", "r * tan(θ)", "sqrt(r)"), 1)
        8 -> Pentuple("Under vector algebra, which is structural property of vector dot product?", "දෛශික වීජ ගණිතයට අනුව අදිශ ගුණිතයේ පවතින සත්‍ය ගුණාංගය කුමක්ද?", listOf("a.b = -b.a", "a.b = b.a (commutative)", "a.b = 0 always", "a.b is vector"), listOf("a.b = -b.a", "a.b = b.a (පරිවර්තනීය)", "සෑම විටම a.b = 0 වේ", "a.b යනු දෛශිකයකි"), 1)
        else -> Pentuple("Find magnitude of vector u = 5i - 12j.", "u = 5i - 12j దෛශිකයේ විශාලත්වය (Magnitude) සොයන්න.", listOf("12", "13", "17", "169"), listOf("12", "13", "17", "169"), 1)
    }
}
