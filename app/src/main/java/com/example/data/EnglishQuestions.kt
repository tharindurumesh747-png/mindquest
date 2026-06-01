package com.example.data

object EnglishQuestions {

    fun getQuestions(grade: Int, stage: Int): List<Question> {
        val list = mutableListOf<Question>()
        for (i in 0..9) {
            list.add(getSingleQuestion(grade, stage, i))
        }
        return list
    }

    private fun getSingleQuestion(grade: Int, stage: Int, index: Int): Question {
        val qid = "g${grade}_English_s${stage}_q${index}"
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
            subject = "English",
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
        0 -> Pentuple("What is the first letter of the English alphabet?", "ඉංග්‍රීසි හෝඩියේ පළමු අකුර කුමක්ද?", listOf("A", "B", "C", "Z"), listOf("A", "B", "C", "Z"), 0)
        1 -> Pentuple("Which letter comes after 'C' in the alphabet?", "ඉංග්‍රීසි හෝඩියේ 'C' අකුරට පසුව එන අකුර කුමක්ද?", listOf("A", "B", "D", "E"), listOf("A", "B", "D", "E"), 2)
        2 -> Pentuple("What is the last letter of the English alphabet?", "ඉංග්‍රීසි හෝඩියේ අවසාන අකුර කුමක්ද?", listOf("X", "Y", "Z", "W"), listOf("X", "Y", "Z", "W"), 2)
        3 -> Pentuple("Which of these is a capital letter?", "පහත ඒවායින් මහ අකුරක් (Capital Letter) වන්නේ කුමක්ද?", listOf("a", "b", "G", "d"), listOf("a", "b", "G", "d"), 2)
        4 -> Pentuple("Which of these is a small letter?", "පහත ඒවායින් කුඩා අකුරක් (Small Letter) වන්නේ කුමක්ද?", listOf("H", "r", "M", "K"), listOf("H", "r", "M", "K"), 1)
        5 -> Pentuple("Choose the missing lowercase letter: A, B, __, D", "හිස්තැනට ආ යුතු කුඩා අකුර තෝරන්න: A, B, __, D", listOf("e", "c", "f", "x"), listOf("e", "c", "f", "x"), 1)
        6 -> Pentuple("What letter does the word 'Apple' start with?", "'Apple' (ඇපල්) වචනය ආරම්භ වන්නේ කුමන අකුරෙන්ද?", listOf("B", "P", "S", "A"), listOf("B", "P", "S", "A"), 3)
        7 -> Pentuple("What is the missing letter?: e, f, __, h", "හිස්තැනට සුදුසු අකුර කුමක්ද?: e, f, __, h", listOf("a", "g", "b", "z"), listOf("a", "g", "b", "z"), 1)
        8 -> Pentuple("Which letter comes before 'B'?", "'B' අකුරට කලින් එන අකුර කුමක්ද?", listOf("A", "C", "D", "E"), listOf("A", "C", "D", "E"), 0)
        else -> Pentuple("How many letters are in the English alphabet?", "ඉංග්‍රීසි හෝඩියේ මුළු අකුරු කීයක් තිබේද?", listOf("24", "26", "28", "30"), listOf("24", "26", "28", "30"), 1)
    }

    private fun getGrade1Stage2(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("What do you say when you see someone in the morning?", "උදෑසනකදී යමෙකු මුණගැසුණු විට පවසන්නේ කුමක්ද?", listOf("Good night", "Good morning", "Goodbye", "Thank you"), listOf("සුභ රාත්‍රියක්", "සුභ උදෑසනක් (Good morning)", "සමුගනිමු", "ස්තූතියි"), 1)
        1 -> Pentuple("What color is a red ripe tomato?", "ඉදුණු රතු තක්කාලි ගෙඩියක පැහැය කුමක්ද?", listOf("Blue", "Green", "Red", "Yellow"), listOf("නිල්", "කොළ", "රතු (Red)", "කහ"), 2)
        2 -> Pentuple("Which animal bark: 'Woof woof'?", "'Woof woof' හඬින් බුරන සත්වයා කවුද?", listOf("Cat", "Dog", "Cow", "Bird"), listOf("බළලා", "බල්ලා (Dog)", "එළදෙන", "කුරුල්ලා"), 1)
        3 -> Pentuple("Which of these is a color?", "මෙහි ඇති වර්ණයක් වන්නේ කුමක්ද?", listOf("Pen", "Run", "Blue", "Car"), listOf("පෑන", "දිවීම", "නිල් (Blue)", "කාර්"), 2)
        4 -> Pentuple("Who is your father's wife to you?", "ඔබගේ පියාගේ බිරිඳ ඔබට කවුරුන්ද?", listOf("Brother", "Mother", "Sister", "Uncle"), listOf("සහෝදරයා", "මව (Mother)", "සහෝදරිය", "මාමා"), 1)
        5 -> Pentuple("What do you say when you receive a nice gift?", "යමෙකුගෙන් ලස්සන තෑග්ගක් ලැබුණු විට පවසන්නේ කුමක්ද?", listOf("Sorry", "Goodbye", "Thank you", "Hello"), listOf("කණගාටුයි", "සමුගනිමු", "ස්තූතියි (Thank you)", "හෙලෝ"), 2)
        6 -> Pentuple("Which animal says: 'Meow'?", "'Meow' හඬ නගන සත්වයා කවුද?", listOf("Elephant", "Lion", "Cat", "Goat"), listOf("අලියා", "සිංහයා", "බළලා (Cat)", "එළුවා"), 2)
        7 -> Pentuple("What do you say when you go to sleep?", "නිදා ගැනීමට යන විට පවසන්නේ කුමක්ද?", listOf("Good morning", "Good night", "Good afternoon", "Hello"), listOf("සුභ උදෑසනක්", "සුභ රාත්‍රියක් (Good night)", "සුභ පස්වරුවක්", "හෙලෝ"), 1)
        8 -> Pentuple("Which word is a family member?", "පහත වචන වලින් පවුලේ සාමාජිකයෙක් හඳුන්වන්නේ කුමක්ද?", listOf("Table", "Sister", "Pencil", "Book"), listOf("මේසය", "සහෝදරිය (Sister)", "පැන්සල", "පොත"), 1)
        else -> Pentuple("Which of these is action word for jumping?", "පැනීම සඳහා භාවිතා කරන ඉංග්‍රීසි ක්‍රියා පදය කුමක්ද?", listOf("Sleep", "Eat", "Jump", "Read"), listOf("නිදාගැනීම", "කෑම", "Jump (පැනීම)", "කියවීම"), 2)
    }

    private fun getGrade1Stage3(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("Unscramble the letters to make a word: 'a-t-c'", "අකුරු ගළපා නිවැරදි වචනයක් සාදන්න: 'a-t-c'", listOf("Dog", "Cat", "Bus", "Toy"), listOf("බල්ලා", "බළලා (Cat)", "බස්", "සෙල්ලම්බඩුව"), 1)
        1 -> Pentuple("What is the spelling of numerical 3?", "3 ඉලක්කමේ නිවැරදි ඉංග්‍රීසි අක්ෂර වින්‍යාසය කුමක්ද?", listOf("Tree", "Three", "Thee", "Free"), listOf("ගස", "Three (තුන)", "ඔබ", "නිදහස්"), 1)
        2 -> Pentuple("Fill in the blank: An apple is a ______.", "හිස්තැන පුරවන්න: An apple is a ______.", listOf("animal", "fruit", "box", "toy"), listOf("සතෙක්", "පලතුරක් (fruit)", "පෙට්ටියක්", "සෙල්ලම්බඩුවක්"), 1)
        3 -> Pentuple("Identify the pet animal in English:", "සුරතල් සතෙකු හඳුන්වන ඉංග්‍රීසි වචනය තෝරන්න:", listOf("Tiger", "Crocodile", "Fish", "Kitten"), listOf("කොටියා", "කිඹුලා", "මාළු", "බළල් පැටියා (Kitten)"), 3)
        4 -> Pentuple("Which word matches the image of a 'sun'?", "'ඉර' සඳහා ගැළපෙන ඉංග්‍රීසි වචනය කුමක්ද?", listOf("Star", "Moon", "Sun", "Cloud"), listOf("තරුව", "හඳ", "Sun (ඉර)", "වලාකුළ"), 2)
        5 -> Pentuple("Choose the action word: 'She can ____.'", "ක්‍රියා පදය තෝරන්න: 'She can ____.'", listOf("tall", "run", "happy", "big"), listOf("උස", "දිවීම (run)", "සතුටු", "විශාල"), 1)
        6 -> Pentuple("Complete the word: 'b__y' (young human)", "වචනය සම්පූර්ණ කරන්න: 'b__y' (ළදරුවා)", listOf("u", "o", "a", "e"), listOf("u", "o", "a", "e"), 2)
        7 -> Pentuple("Which of these is a vegetable?", "පහත දැක්වෙන දේවලින් එළවළු වර්ගයක් වන්නේ කුමක්ද?", listOf("Mango", "Carrot", "Banana", "Apple"), listOf("අඹ", "කැරට් (Carrot)", "කෙසෙල්", "ඇපල්"), 1)
        8 -> Pentuple("What do we use to write on a paper?", "කඩදාසියක ලිවීම සඳහා අප භාවිතා කරන්නේ කුමක්ද?", listOf("Plate", "Pencil", "Shoes", "Spoon"), listOf("පිඟාන", "පැන්සල (Pencil)", "සපත්තු", "හැන්ද"), 1)
        else -> Pentuple("Complete the sequence: Mon, Tue, ___, Thu", "හිස්තැන පුරවන්න: Mon, Tue, ___, Thu", listOf("Wed", "Fri", "Sat", "Sun"), listOf("බදාදා (Wed)", "සිකුරාදා", "සෙනසුරාදා", "ඉරිදා"), 0)
    }

    // GRADE 2
    private fun getGrade2Stage1(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("How do you spell the fruit 'm-a-n-g-o'?", "'අඹ' සඳහා වන නිවැරදි වචනය කුමක්ද?", listOf("Mengo", "Mango", "Minko", "Mangoes"), listOf("මෙන්ගෝ", "Mango (අඹ)", "මින්කෝ", "මැන්ගෝස්"), 1)
        1 -> Pentuple("Which of these is a school item?", "පහත සඳහන් ඒවායින් පාසල් උපකරණයක් වන්නේ කුමක්ද?", listOf("Pillow", "Ruler", "Spoon", "Toothbrush"), listOf("කොට්ටය", "අඟල් රූල (Ruler)", "හැන්ද", "දත් බුරුසුව"), 1)
        2 -> Pentuple("Where does your mother cook food?", "ඔබේ මව ආහාර පිසින්නේ නිවසේ කුමන කොටසෙහිද?", listOf("Bedroom", "Bathroom", "Kitchen", "Garden"), listOf("නිදන කාමරය", "නාන කාමරය", "කුස්සිය (Kitchen)", "වත්ත"), 2)
        3 -> Pentuple("Which word describes the action of washing your body?", "මුළු සිරුරම පිරිසිදු කිරීම හඳුන්වන වචනය කුමක්ද?", listOf("Sweeping", "Bathing", "Playing", "Writing"), listOf("අතුගෑම", "නානවා (Bathing)", "සෙල්ලම් කිරීම", "ලිවීම"), 1)
        4 -> Pentuple("Where is the toy? It is ____ the box (inside).", "සෙල්ලම් බඩුව තිබෙන්නේ පෙට්ටිය ____ (තුළ).", listOf("on", "under", "in", "at"), listOf("මත", "යට", "තුළ (in)", "හිදී"), 2)
        5 -> Pentuple("Choose the fruit name:", "පලතුරක නම තෝරන්න:", listOf("Potato", "Cabbage", "Orange", "Leeks"), listOf("අර්තාපල්", "ගෝවා", "තැඹිලි පාට / දොඩම් (Orange)", "ලීක්ස්"), 2)
        6 -> Pentuple("What is used to erase pencil marks?", "පැන්සල් ලකුණු මකා දැමීමට භාවිතා කරන්නේ කුමක්ද?", listOf("Sharpener", "Eraser", "Pencil", "Notebook"), listOf("කටර් එක", "මකනය (Eraser)", "පැන්සල", "සටහන් පොත"), 1)
        7 -> Pentuple("Which room is used for sleeping?", "නිදා ගැනීම සඳහා භාවිතා කරන කාමරය කුමක්ද?", listOf("Living room", "Dining room", "Bedroom", "Kitchen"), listOf("සාලය", "කෑම කාමරය", "නිදන කාමරය (Bedroom)", "කුස්සිය"), 2)
        8 -> Pentuple("Complete: The cat is sitting ____ the table (below surface).", "හිස්තැන පුරවන්න: බළලා මේසය ____ වාඩි වී සිටී (පහළින්).", listOf("on", "above", "under", "in"), listOf("මත", "ඉහළින්", "යට (under)", "තුළ"), 2)
        else -> Pentuple("Which of these is a vegetable used to make sambol?", "පොල් සම්බෝල සෑදීමට ගන්නා එළවළු (අලංකාරක) ජාතියක්?", listOf("Onion", "Pineapple", "Apple", "Plum"), listOf("ලූණු (Onion)", "අන්නාසි", "ඇපල්", "ප්ලම්ස්"), 0)
    }

    private fun getGrade2Stage2(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("What do you do with a book?", "පොතක් සමඟ ඔබ කරන්නේ කුමක්ද?", listOf("Eat it", "Read it", "Wear it", "Kick it"), listOf("කනවා", "කියවනවා (Read it)", "අඳිනවා", "පයින් ගහනවා"), 1)
        1 -> Pentuple("Which word is the opposite of 'big'?", "'Big' (විශාල) වචනයේ ප්‍රතිවිරුද්ධ වචනය කුමක්ද?", listOf("Large", "Small", "Tall", "Fat"), listOf("විශාල", "කුඩා (Small)", "උස", "මහත"), 1)
        2 -> Pentuple("Fill in the blank: This ____ my pencil.", "හිස්තැන පුරවන්න: This ____ my pencil.", listOf("are", "am", "is", "were"), listOf("are", "am", "is", "were"), 2)
        3 -> Pentuple("What is the plural of 'book'?", "'Book' (පොත) වචනයේ බහුවචනය කුමක්ද?", listOf("Bookes", "Books", "Bookies", "Beek"), listOf("Bookes", "Books (පොත්)", "Bookies", "Beek"), 1)
        4 -> Pentuple("Which of these is a name of a weekday?", "සතියේ දවසක නමක් වන්නේ මෙයින් කුමක්ද?", listOf("January", "Monday", "March", "Sunday"), listOf("ජනවාරි", "සඳුදා (Monday)", "මාර්තු", "ඉරිදා"), 1)
        5 -> Pentuple("How do you greet a teacher at 2:00 PM?", "දවල් 2:00 ට ගුරුවරයකු මුණගැසුණු විට පවසන්නේ කුමක්ද?", listOf("Good morning", "Good afternoon", "Good night", "Goodbye"), listOf("සුභ උදෑසනක්", "සුභ පස්වරුවක් (Good afternoon)", "සුභ රාත්‍රියක්", "සමුගනිමු"), 1)
        6 -> Pentuple("What do we call a person who flies an airplane?", "ගුවන් යානයක් පදවන පුද්ගලයා හඳුන්වන්නේ කෙසේද?", listOf("Doctor", "Teacher", "Pilot", "Driver"), listOf("දොස්තර", "ගුරුතුමා", "නියමුවා (Pilot)", "රියදුරු"), 2)
        7 -> Pentuple("Which animal has a very long neck?", "ඉතා දිගු බෙල්ලක් ඇති සත්වයා කවුද?", listOf("Rabbit", "Dog", "Giraffe", "Elephant"), listOf("හාවා", "බල්ලා", "ජිරාෆ් (Giraffe)", "අලියා"), 2)
        8 -> Pentuple("Fill in: They ____ playing in the ground.", "හිස්තැන පුරවන්න: They ____ playing in the ground.", listOf("is", "am", "are", "was"), listOf("is", "am", "are", "was"), 2)
        else -> Pentuple("What is the opposite of 'happy'?", "'Happy' (සතුටු) වචනයේ ප්‍රතිවිරුද්ධ වචනය කුමක්ද?", listOf("Glad", "Smile", "Sad", "Bright"), listOf("සන්තෝෂ", "හිනාව", "දුක්බර (Sad)", "දීප්තිමත්"), 2)
    }

    private fun getGrade2Stage3(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("Choose the noun: 'The dog is barking.'", "නාම පදය තෝරන්න: 'The dog is barking.'", listOf("barking", "is", "dog", "loudly"), listOf("හඬනගන", "is", "බල්ලා (dog)", "ශබ්දයෙන්"), 2)
        1 -> Pentuple("What do we use to cut paper?", "කඩදාසි කැපීම සඳහා අප භාවිතා කරන්නේ කුමක්ද?", listOf("Ruler", "Scissors", "Pencil", "Eraser"), listOf("අඟල් රූල", "කැතුර (Scissors)", "පැන්සල", "මකනය"), 1)
        2 -> Pentuple("Which of these is a vehicle that travels on rails?", "රේල් පීලි මත ධාවනය වන වාහනය කුමක්ද?", listOf("Car", "Train", "Bus", "Bicycle"), listOf("කාර්", "කෝච්චිය (Train)", "බස්", "පාපැදිය"), 1)
        3 -> Pentuple("Fill in: She ____ a beautiful doll.", "හිස්තැන පුරවන්න: She ____ a beautiful doll.", listOf("have", "has", "are", "am"), listOf("have", "has (ඇත)", "are", "am"), 1)
        4 -> Pentuple("Which spelling is correct?", "නිවැරදි අක්ෂර වින්‍යාසය තෝරන්න?", listOf("Banana", "Banna", "Banaana", "Bananan"), listOf("Banana (කෙසෙල්)", "Banna", "Banaana", "Bananan"), 0)
        5 -> Pentuple("What is the spelling of numerical 8?", "8 ඉලක්කමේ ඉංග්‍රීසි අක්ෂර වින්‍යාසය කුමක්ද?", listOf("Eight", "Eigth", "Eigt", "Aight"), listOf("Eight (අට)", "Eigth", "Eigt", "Aight"), 0)
        6 -> Pentuple("Fill in: The birds are flying ____ the tree (above).", "කුරුල්ලන් ගසට ____ පියාඹයි (ඉහළින්).", listOf("under", "over", "in", "on"), listOf("යටින්", "ඉහළින් (over)", "තුළ", "මත"), 1)
        7 -> Pentuple("Which word is an adjective? 'It is a small cat.'", "විශේෂණ පදය කුමක්ද? 'It is a small cat.'", listOf("cat", "is", "small", "it"), listOf("බළලා", "is", "කුඩා (small)", "එය"), 2)
        8 -> Pentuple("What do we call a baby dog?", "බල්ලෙකුගේ පැටියා හඳුන්වන්නේ කුමන නමකින්ද?", listOf("Kitten", "Puppy", "Calf", "Lamb"), listOf("බළල් පැටියා", "බල්ලා පැටියා (Puppy)", "වස්සා", "බැටළු පැටියා"), 1)
        else -> Pentuple("Which of these means 'every day'?", "පහත ප්‍රකාශන වලින් 'සෑම දිනකම' යන්නෙහි තේරුම කුමක්ද?", listOf("Weekly", "Monthly", "Daily", "Yearly"), listOf("සතිපතා", "මාසිකව", "දිනපතා (Daily)", "වාර්ෂිකව"), 2)
    }

    // GRADE 3
    private fun getGrade3Stage1(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("What is the plural of 'child'?", "'Child' (ළමයා) වචනයේ බහුවචනය කුමක්ද?", listOf("Childs", "Children", "Childrens", "Childes"), listOf("Childs", "Children (ළමයි)", "Childrens", "Childes"), 1)
        1 -> Pentuple("Which pronoun replaces 'Anil and Kamal'?", "'Anil and Kamal' වෙනුවට යෙදිය හැකි සර්වනාමය කුමක්ද?", listOf("He", "They", "We", "She"), listOf("ඔහු", "ඔවුන් (They)", "අපි", "ඇය"), 1)
        2 -> Pentuple("What feeling is shown when we smile and laugh?", "අප සිනාසෙන විට ප්‍රකාශ වන හැඟීම කුමක්ද?", listOf("Angry", "Sad", "Happy", "Scared"), listOf("තරහින්", "දුකින්", "සතුටින් (Happy)", "බියෙන්"), 2)
        3 -> Pentuple("How is the weather when there are dark clouds and water falls?", "කළු වලාකුළු තිබී අහසින් ජලය වැටෙන විට කාලගුණය කෙසේද?", listOf("Sunny", "Windy", "Rainy", "Cold"), listOf("හිරු පායා ඇත", "සුළං සහිතයි", "වැසි සහිතයි (Rainy)", "සීතලයි"), 2)
        4 -> Pentuple("What is the plural of 'toy'?", "'Toy' (සෙල්ලම් බඩුව) වචනයේ බහුවචනය කුමක්ද?", listOf("Toyes", "Toys", "Toises", "Toies"), listOf("Toyes", "Toys (සෙල්ලම්බඩු)", "Toises", "Toies"), 1)
        5 -> Pentuple("Which pronoun replaces 'My mother'?", "'My mother' වෙනුවට යෙදෙන සර්වනාමය කුමක්ද?", listOf("He", "She", "It", "They"), listOf("ඔහු", "ඇය (She)", "එය", "ඔවුන්"), 1)
        6 -> Pentuple("What is the opposite of 'hot'?", "'Hot' (රස්නෙ) වචනයේ ප්‍රතිවිරුද්ධ වචනය කුමක්ද?", listOf("Warm", "Boiling", "Cold", "Dry"), listOf("උණුසුම්", "නටන", "සීතල (Cold)", "වියළි"), 2)
        7 -> Pentuple("Choose the plural of 'box':", "'Box' (පෙට්ටිය) වචනයේ බහුවචනය තෝරන්න:", listOf("Boxes", "Boxs", "Boxies", "Bex"), listOf("Boxes (පෙට්ටි)", "Boxs", "Boxies", "Bex"), 0)
        8 -> Pentuple("Identify the pronoun: 'Kamal went to town. ___ bought a book.'", "හිස්තැනට සුදුසු සර්වනාමය: 'Kamal went to town. ___ bought a book.'", listOf("She", "He", "They", "It"), listOf("ඇය", "ඔහු (He)", "ඔවුන්", "එය"), 1)
        else -> Pentuple("Which of these shows a scary feeling?", "බයවී ඇති බව පෙන්වන ඉංග්‍රීසි වචනය කුමක්ද?", listOf("Excited", "Sad", "Afraid", "Happy"), listOf("උද්යෝගිමත්", "දුක්බර", "බයගැන්වුණු (Afraid)", "සතුටුදායක"), 2)
    }

    private fun getGrade3Stage2(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("Which of these is the correct spelling of a number?", "නිවැරදි අංක අක්ෂර වින්‍යාසය කුමක්ද?", listOf("Twelv", "Twelve", "Twelf", "Tweleve"), listOf("Twelv", "Twelve (දොළහ)", "Twelf", "Tweleve"), 1)
        1 -> Pentuple("Choose the opposite of 'heavy':", "'Heavy' (බරැති) වචනයේ ප්‍රතිවිරුද්ධ වචනය කුමක්ද?", listOf("Hard", "Soft", "Light", "Weak"), listOf("තද", "මෘදු", "සැහැල්ලු (Light)", "දුර්වල"), 2)
        2 -> Pentuple("Fill in: There ___ ten pens in my bag.", "හිස්තැන පුරවන්න: There ___ ten pens in my bag.", listOf("is", "am", "are", "was"), listOf("is", "am", "are", "was"), 2)
        3 -> Pentuple("What time is shown when the short hand is on 3 and long hand is on 12?", "පැය කටුව 3 මතත් විනාඩි කටුව 12 මතත් ඇති විට වේලාව කීයද?", listOf("3:00 o'clock", "12:00 o'clock", "3:30", "12:15"), listOf("හරියටම 3:00 යි", "හරියටම 12:00 යි", "3:30", "12:15"), 0)
        4 -> Pentuple("What is the plural of 'glass'?", "'Glass' (වීදුරුව) වචනයේ බහුවචනය කුමක්ද?", listOf("Glasss", "Glasses", "Glassies", "Gleese"), listOf("Glasss", "Glasses (වීදුරු)", "Glassies", "Gleese"), 1)
        5 -> Pentuple("What is the past tense of 'play'?", "'Play' (සෙල්ලම් කරනවා) වචනයේ අතීත කාලය කුමක්ද?", listOf("Plays", "Playing", "Played", "Playen"), listOf("Plays", "Playing", "Played (සෙල්ලම් කළා)", "Playen"), 2)
        6 -> Pentuple("Which word is a synonym of 'look'?", "'Look' (බලනවා) වචනයට සමාන තේරුමක් දෙන වචනය කුමක්ද?", listOf("Listen", "See", "Hear", "Speak"), listOf("අසනවා", "දකිනවා / බලනවා (See)", "ඇසෙනවා", "කථා කරනවා"), 1)
        7 -> Pentuple("Choose the correct spelling:", "නිවැරදි අක්ෂර වින්‍යාසය තෝරන්න:", listOf("Beautiful", "Beautifull", "Butiful", "Beatiful"), listOf("Beautiful (ලස්සන)", "Beautifull", "Butiful", "Beatiful"), 0)
        8 -> Pentuple("Complete: A monkey is good at ______ trees.", "හිස්තැන පුරවන්න: 'A monkey is good at ______ trees.'", listOf("flying", "swimming", "climbing", "running"), listOf("පියාසර කිරීමට", "පිහිනීමට", "නැගීමට (climbing)", "දිවීමට"), 2)
        else -> Pentuple("What is the opposite of 'clean'?", "'Clean' (පිරිසිදු) වචනයේ ප්‍රතිවිරුද්ධ වචනය කුමක්ද?", listOf("Neat", "Dirty", "Fresh", "Pure"), listOf("පිළිවෙල", "කිලිටි (Dirty)", "නැවුම්", "පිරිසිදු"), 1)
    }

    private fun getGrade3Stage3(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("Identify the verb: 'The boys ran fast yesterday.'", "ක්‍රියා පදය තෝරන්න: 'The boys ran fast yesterday.'", listOf("boys", "ran", "fast", "yesterday"), listOf("ළමයි", "දිව්වා (ran)", "වේගයෙන්", "ඊයේ"), 1)
        1 -> Pentuple("Which letter is silent in the word 'knife'?", "'Knife' (පිහිය) වචනයේ නොඇසෙන අකුර (silent letter) කුමක්ද?", listOf("k", "n", "i", "f"), listOf("k", "n", "i", "f"), 0)
        2 -> Pentuple("Complete: He is ______ a letter now.", "හිස්තැන පුරවන්න: He is ______ a letter now.", listOf("write", "writes", "writing", "wrote"), listOf("write", "writes", "writing (ලියමින් සිටී)", "wrote"), 2)
        3 -> Pentuple("What is the opposite of 'dark'?", "'Dark' (අඳුරු) වචනයේ ප්‍රතිවිරුද්ධ වචනය කුමක්ද?", listOf("Black", "Light / Bright", "Dim", "Gray"), listOf("කළු", "දීප්තිමත් / එළිය (Light)", "අඳුරු", "අළු"), 1)
        4 -> Pentuple("Choose the noun: 'The flower is yellow.'", "නාම පදය තෝරන්න: 'The flower is yellow.'", listOf("flower", "is", "yellow", "beautiful"), listOf("මල (flower)", "is", "කහ", "ලස්සන"), 0)
        5 -> Pentuple("Complete: A fish can ______ in the river.", "හිස්තැන පුරවන්න: 'A fish can ______ in the river.'", listOf("fly", "walk", "swim", "crawl"), listOf("පියාඹන්න", "ඇවිදින්න", "පිහිනන්න (swim)", "බඩගාන්න"), 2)
        6 -> Pentuple("What is the suffix in 'joyful'?", "'Joyful' වචනයේ ප්‍රත්‍යය (suffix) කුමක්ද?", listOf("joy", "ful", "oy", "yful"), listOf("joy", "ful", "oy", "yful"), 1)
        7 -> Pentuple("Complete: Are ______ going to the school library?", "හිස්තැන පුරවන්න: 'Are ______ going to the school library?'", listOf("he", "she", "you", "it"), listOf("ඔහු", "ඇය", "ඔබ (you)", "එය"), 2)
        8 -> Pentuple("The plural of 'wolf' is:", "'Wolf' (වෘකයා) වචනයේ බහුවචනය කුමක්ද?", listOf("Wolfs", "Wolves", "Wolfes", "Welf"), listOf("Wolfs", "Wolves (වෘකයින්)", "Wolfes", "Welf"), 1)
        else -> Pentuple("Which spelling is correct?", "නිවැරදි ඉංග්‍රීසි අක්ෂර වින්‍යාසය තෝරන්න:", listOf("Turesday", "Thursday", "Thursda", "Therday"), listOf("Turesday", "Thursday (බ්‍රහස්පතින්දා)", "Thursda", "Therday"), 1)
    }

    // GRADE 4
    private fun getGrade4Stage1(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("What is a person who treats sick people called?", "ලෙඩ රෝග සුව කරන පුද්ගලයා හඳුන්වන්නේ කෙසේද?", listOf("Teacher", "Farmer", "Doctor", "Driver"), listOf("ගුරුවරයා", "ගොවියා", "වෛද්‍යවරයා (Doctor)", "රියදුරු"), 2)
        1 -> Pentuple("Which word is an adjective of quality in: 'He is a brave boy.'?", "විශේෂණ පදය කුමක්ද: 'He is a brave boy.'?", listOf("He", "boy", "brave", "is"), listOf("He", "boy", "ධෛර්යවන්ත (brave)", "is"), 2)
        2 -> Pentuple("What is the past tense of the verb 'go'?", "'Go' (යනවා) ක්‍රියා පදයේ අතීත කාලය කුමක්ද?", listOf("Goed", "Gone", "Went", "Going"), listOf("Goed", "Gone", "Went (ගියා)", "Going"), 2)
        3 -> Pentuple("Which pronoun represents the owners: 'The book belongs to us. It is ______.'?", "හිස්තැන පුරවන්න: 'The book belongs to us. It is ______.'", listOf("theirs", "ours", "yours", "his"), listOf("ඔවුන්ගේ", "අපගේ (ours)", "ඔබගේ", "ඔහුගේ"), 1)
        4 -> Pentuple("What is the opposite of the word 'fast'?", "'Fast' (වේගයෙන්) වචනයේ ප්‍රතිවිරුද්ධ වචනය කුමක්ද?", listOf("Quick", "Slow", "High", "Noisy"), listOf("වේගවත්", "සෙමින් (Slow)", "ඉහළ", "ඝෝෂාකාරී"), 1)
        5 -> Pentuple("A person who grows crops and paddy in fields is a:", "කුඹුරුවල ගොවිතැන් කරන පුද්ගලයා කවුද?", listOf("Carpenter", "Farmer", "Tailor", "Mason"), listOf("වඩුවා", "ගොවියා (Farmer)", "தையල්කරුවා", "මේසන්බාස්"), 1)
        6 -> Pentuple("Choose the past tense of 'eat':", "'Eat' (කනවා) වචනයේ අතීත කාලය කුමක්ද?", listOf("Eated", "Eating", "Ate", "Eaten"), listOf("Eated", "Eating", "Ate (කෑවා)", "Eaten"), 2)
        7 -> Pentuple("Which word is a synonym (meaning same) of 'shut'?", "'Shut' (වහනවා) වචනයට සමාන තේරුමක් ඇති වචනය කුමක්ද?", listOf("Open", "Close", "Push", "Keep"), listOf("අරිනවා", "වහනවා (Close)", "තල්ලු කරනවා", "තබාගන්නවා"), 1)
        8 -> Pentuple("What does a red traffic light sign mean?", "රථවාහන මාර්ග සංඥා ලාම්පුවේ රතු පැහැයෙන් අදහස් කරන්නේ කුමක්ද?", listOf("Go fast", "Stop", "Turn left", "Slow down"), listOf("වේගයෙන් යන්න", "නවතින්න (Stop)", "වමට හැරෙන්න", "වේගය බාල කරන්න"), 1)
        else -> Pentuple("Identify the plural of 'tooth':", "'Tooth' (දත) වචනයේ බහුවචනය කුමක්ද?", listOf("Tooths", "Teeth", "Toothes", "Teeths"), listOf("Tooths", "Teeth (දත්)", "Toothes", "Teeths"), 1)
    }

    private fun getGrade4Stage2(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("Complete the sentence: 'We ______ our school project yesterday.'", "හිස්තැන පුරවන්න: 'We ______ our school project yesterday.'", listOf("finish", "finishes", "finished", "finishing"), listOf("finish", "finishes", "finished (අවසන් කළා)", "finishing"), 2)
        1 -> Pentuple("Choose the correct adjective: 'Mt. Everest is the ______ mountain in the world.'", "නිවැරදි විශේෂණ පදය තෝරන්න: 'Mt. Everest is the ______ mountain in the world.'", listOf("tall", "taller", "tallest", "more tall"), listOf("tall", "taller", "tallest (උසම)", "more tall"), 2)
        2 -> Pentuple("Which conjunction joins: 'I like apples ___ I do not like oranges.'?", "සම්බන්ධක පදය කුමක්ද: 'I like apples ___ I do not like oranges.'?", listOf("and", "but", "or", "because"), listOf("සහ", "නමුත් (but)", "හෝ", "නිසා"), 1)
        3 -> Pentuple("What is the past tense of the verb 'run'?", "'Run' (දුවනවා) ක්‍රියා පදයේ අතීත කාලය කුමක්ද?", listOf("Runned", "Ran", "Running", "Runs"), listOf("Runned", "Ran (දිව්වා)", "Running", "Runs"), 1)
        4 -> Pentuple("Which pronoun describes a group including me: '_____ are Sri Lankans.'?", "අප සැම ඇතුළත් වන සර්වනාමය කුමක්ද: '_____ are Sri Lankans.'?", listOf("They", "We", "You", "He"), listOf("ඔවුන්", "අපි (We)", "ඔබ", "ඔහු"), 1)
        5 -> Pentuple("Choose the synonym of 'large':", "'Large' වචනයට සමාන පදය තෝරන්න:", listOf("Tiny", "Small", "Huge", "Thin"), listOf("ඉතා කුඩා", "කුඩා", "මහත් / විශාල (Huge)", "සිහින්"), 2)
        6 -> Pentuple("The opposite of 'rich' is:", "'Rich' (පොහොසත්) යන්නෙහි ප්‍රතිවිරුද්ධ පදය කුමක්ද?", listOf("Wealthy", "Poor", "Kind", "Strong"), listOf("ධනවත්", "දුප්පත් (Poor)", "කරුණාවන්ත", "ශක්තිමත්"), 1)
        7 -> Pentuple("What do we call a person who makes wooden furniture?", "ලී ගෘහභාණ්ඩ සාදන පුද්ගලයා කවුද?", listOf("Blacksmith", "Carpenter", "Tailor", "Teacher"), listOf("කම්මල්කරු", "වඩුවා (Carpenter)", "මහන්නා", "ගුරුවරයා"), 1)
        8 -> Pentuple("Which word is spelled correctly?", "නිවැරදිව ලියා ඇති වචනය තෝරන්න:", listOf("Scolarly", "Schoole", "School", "Shcol"), listOf("Scolarly", "Schoole", "School (පාසල)", "Shcol"), 2)
        else -> Pentuple("What is the past tense of 'buy'?", "'Buy' (මිලදී ගන්නවා) වචනයේ අතීත කාලය කුමක්ද?", listOf("Buyed", "Bought", "Brought", "Buing"), listOf("Buyed", "Bought (මිලදී ගත්තා)", "Brought", "Buing"), 1)
    }

    private fun getGrade4Stage3(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("Identify the collective noun: 'A ______ of birds was flying.'", "සමූහ නාම පදයක් තෝරන්න: 'A ______ of birds was flying.'", listOf("herd", "flock", "pack", "bunch"), listOf("රැළ (සිව්පා)", "Flock (කුරුලු රැළ)", "පැක්", "කන්දරාව"), 1)
        1 -> Pentuple("Which suffix turns 'quick' into an adverb?", "කුමන ප්‍රත්‍යය මඟින් 'quick' යන්න ක්‍රියා විශේෂණයක් කරයිද?", listOf("ness", "ly", "ful", "y"), listOf("ness", "ly (quickly)", "ful", "y"), 1)
        2 -> Pentuple("Complete: 'The sun shines ______ in the afternoon.'", "හිස්තැන පුරවන්න: 'The sun shines ______ in the afternoon.'", listOf("brightly", "softly", "darkly", "sadly"), listOf("දීප්තිමත්ව (brightly)", "මෘදුව", "අඳුරුව", "දුකින්"), 0)
        3 -> Pentuple("What punctuation mark is used to ask a query?", "ප්‍රශ්නයක් ඇසීමට භාවිතා කරන ලකුණ කුමක්ද?", listOf("Full stop", "Question mark", "Comma", "Exclamation mark"), listOf("තිත", "ප්‍රශ්නාර්ථ ලකුණ (?)", "කොමාව", "විස්මයාර්ථ ලකුණ"), 1)
        4 -> Pentuple("What is the past participle of 'write'?", "'Write' (ලියනවා) වචනයේ අතීත කෘදන්තය (Past Participle) කුමක්ද?", listOf("Writed", "Wrote", "Written", "Writing"), listOf("Writed", "Wrote", "Written (ලියා ඇත)", "Writing"), 2)
        5 -> Pentuple("Choose the correct sentence:", "නිවැරදි වාක්‍ය සැකැස්ම තෝරන්න:", listOf("They is happy.", "They are happy.", "They am happy.", "They was happy."), listOf("They is happy.", "They are happy. (ඔවුන් සතුටින්)", "They am happy.", "They was happy."), 1)
        6 -> Pentuple("Which word means 'to search for'?", "'සොයනවා' යන්නෙහි තේරුම දෙන ක්‍රියා ප්‍රකාශනය කුමක්ද?", listOf("Look after", "Look for", "Look down", "Look up"), listOf("බලාගන්නවා", "සොයනවා (Look for)", "පහත් කර බලනවා", "උඩ බලනවා"), 1)
        7 -> Pentuple("What contains the opposite of 'always'?", "'Always' (සෑමවිටම) යන්නෙහි ප්‍රතිවිරුද්ධ පද තේරුම කුමක්ද?", listOf("Often", "Never", "Usually", "Sometimes"), listOf("බොහෝවිට", "කිසිවිටක නැත (Never)", "සාමාන්‍යයෙන්", "සමහරවිට"), 1)
        8 -> Pentuple("Which underlying vowel is doubled to make plural of 'foot'?", "'Foot' වචනය බහුවචනය කිරීමේදී වෙනස් වන ස්වරය කුමක්ද?", listOf("Feets", "Foots", "Feet", "Footes"), listOf("Feets", "Foots", "Feet (අඩි)", "Footes"), 2)
        else -> Pentuple("Who repairs water pipes and taps at home?", "නිවසේ ජල නල සහ කරාම අලුත්වැඩියා කරන පුද්ගලයා කවුද?", listOf("Electrician", "Plumber", "Mason", "Driver"), listOf("විදුලි කාර්මිකයා", "නල කාර්මිකයා (Plumber)", "මේසන් බාස්", "රියදුරු"), 1)
    }

    // GRADE 5
    private fun getGrade5Stage1(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("Which conjunction is best: 'Sri Lanka is an island, ______ it is rich in biodiversity.'?", "හිස්තැනට සුදුසු සම්බන්ධක පදය: 'Sri Lanka is an island, ______ it is rich in biodiversity.'", listOf("but", "and", "or", "yet"), listOf("නමුදු", "සහ (and)", "හෝ", "එතෙකුදු"), 1)
        1 -> Pentuple("What is the superlative degree of the adjective 'good'?", "'Good' (හොඳ) යන්නෙහි විශිෂ්ටතම අවස්ථාව (Superlative) කුමක්ද?", listOf("Better", "Gooder", "Best", "Bestest"), listOf("වඩා හොඳ", "ගුඩර්", "හොඳම (Best)", "බෙස්ටස්"), 2)
        2 -> Pentuple("Complete: 'He was sleeping ______ it started to rain.'", "හිස්තැන පුරවන්න: 'He was sleeping ______ it started to rain.'", listOf("and", "when", "but", "or"), listOf("සහ", "එවිට (when)", "නමුත්", "හෝ"), 1)
        3 -> Pentuple("Which preposition is used for time: 'Our class starts ______ 8:00 AM.'?", "වේලාවන් හැඳින්වීමට ගන්නා නිවැරදි නිපාතය: 'Our class starts ______ 8:00 AM.'", listOf("on", "in", "at", "under"), listOf("on", "in", "at (මත)", "under"), 2)
        4 -> Pentuple("Which of these is the plural of 'life'?", "'Life' (ජීවිතය) වචනයේ බහුවචනය කුමක්ද?", listOf("Lifes", "Lifese", "Lives", "Lifess"), listOf("Lifes", "Lifese", "Lives (ජීවිත)", "Lifess"), 2)
        5 -> Pentuple("What is the past tense of 'sleep'?", "'Sleep' (නිදනවා) වචනයේ අතීත කාලය කුමක්ද?", listOf("Sleeped", "Slept", "Slepen", "Sleeping"), listOf("Sleeped", "Slept (නිදාගත්තා)", "Slepen", "Sleeping"), 1)
        6 -> Pentuple("Choose the comparative form of 'heavy':", "'Heavy' (බර) වචනයේ සංසන්දනාත්මක අවස්ථාව (Comparative) කුමක්ද?", listOf("Heavier", "Heaviest", "More heavy", "Heavyer"), listOf("බරින් වැඩි (Heavier)", "බරම", "මෝ හෙවි", "හෙවියර්"), 0)
        7 -> Pentuple("Complete: 'You ______ attend the extra lessons if you want.'", "හිස්තැන පුරවන්න: 'You ______ attend the extra lessons if you want.'", listOf("may", "was", "has", "am"), listOf("හැකිය (may)", "was", "has", "am"), 0)
        8 -> Pentuple("What is the synonym of 'clever'?", "'Clever' (දක්ෂ) වචනයට සමාන තේරුමක් දෙන වචනය කුමක්ද?", listOf("Smart", "Dull", "Lazy", "Weak"), listOf("දක්ෂ / ක්‍රියාශීලී (Smart)", "අදක්ෂ", "කම්මැලි", "දුර්වල"), 0)
        else -> Pentuple("The opposite of 'safe' is:", "'Safe' (ආරක්ෂිත) වචනයේ ප්‍රතිවිරුද්ධ වචනය කුමක්ද?", listOf("Secure", "Dangerous", "Healthy", "Careful"), listOf("සුරක්ෂිත", "අනතුරුදායක (Dangerous)", "සෞඛ්‍ය සම්පන්න", "ප්‍රවේශම් සහගතයි"), 1)
    }

    private fun getGrade5Stage2(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("Complete this sentence in past continuous tense: 'She ______ lunch when I arrived.'", "අතීත ප්‍රගතිශීලී කාලයෙන් වාක්‍යය සම්පූර්ණ කරන්න: 'She ______ lunch when I arrived.'", listOf("is eating", "was eating", "has eaten", "ate"), listOf("is eating", "was eating (කමින් සිටියාය)", "has eaten", "ate"), 1)
        1 -> Pentuple("Which adjective correctly compares two buildings: 'This tower is ______ than that one.'?", "ගොඩනැගිලි දෙකක් සංසන්දනය කිරීම: 'This tower is ______ than that one.'", listOf("high", "higher", "highest", "more high"), listOf("high", "higher (උසස්ය)", "highest", "more high"), 1)
        2 -> Pentuple("What prefix makes 'happy' negative?", "'Happy' යන වචනය විරුද්ධ පදයක් කිරීමට එක්කරන උපසර්ගය (prefix) කුමක්ද?", listOf("dis-", "un-", "im-", "in-"), listOf("dis-", "un- (unhappy)", "im-", "in-"), 1)
        3 -> Pentuple("Choose the correct preposition: 'The map is hanging ______ the wall.'", "නිවැරදි නිපාත පදය තෝරන්න: 'The map is hanging ______ the wall.'", listOf("on", "in", "at", "under"), listOf("මත (on)", "තුළ", "හිදී", "යටින්"), 0)
        4 -> Pentuple("What punctuation mark should end this: 'What a beautiful flower ______'", "මෙම වාක්‍යය අවසාන කිරීමට සුදුසු ලකුණ කුමක්ද: 'What a beautiful flower ______'", listOf("Question mark ?", "Exclamation mark !", "Comma ,", "Full stop ."), listOf("ප්‍රශ්නාර්ථය ?", "විස්මයාර්ථය ! (Exclamation)", "කොමාව ,", "තිත ."), 1)
        5 -> Pentuple("What is the plural of 'mouse' (rodent)?", "'Mouse' (මීයා) වචනයේ බහුවචනය කුමක්ද?", listOf("Mouses", "Mice", "Mices", "Meese"), listOf("Mouses", "Mice (මීයන්)", "Mices", "Meese"), 1)
        6 -> Pentuple("What is the past tense of 'teach'?", "'Teach' (උගන්වනවා) වචනයේ අතීත කාලය කුමක්ද?", listOf("Teached", "Taught", "Tought", "Teaching"), listOf("Teached", "Taught (ඉගැන්වූවා)", "Tought", "Teaching"), 1)
        7 -> Pentuple("Which word is an adverb of manner in: 'He completed the task easily.'?", "ක්‍රියා විශේෂණ පදය තෝරන්න: 'He completed the task easily.'", listOf("He", "completed", "task", "easily"), listOf("He", "completed", "task", "පහසුවෙන්ම (easily)"), 3)
        8 -> Pentuple("Identify the pronoun: 'Kamal and I are friends. ______ study together.'", "සර්වනාමය තෝරන්න: 'Kamal and I are friends. ______ study together.'", listOf("They", "We", "He", "She"), listOf("ඔවුන්", "අපි (We)", "ඔහු", "ඇය"), 1)
        else -> Pentuple("Complete: 'If it rains, we ______ stay at home.'", "හිස්තැන පුරවන්න: 'If it rains, we ______ stay at home.'", listOf("will", "would", "shall", "does"), listOf("will (නැවතී සිටිමු)", "would", "shall", "does"), 0)
    }

    private fun getGrade5Stage3(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("Identify sentence with correct punctuation:", "නිවැරදි විරාම ලකුණු සහිත වාක්‍යය තෝරන්න:", listOf("did you go to colombo.", "Did you go to Colombo?", "Did you go to colombo?", "did you go to Colombo?"), listOf("did you go to colombo.", "Did you go to Colombo? (ඔබ කොළඹ ගියාද?)", "Did you go to colombo?", "did you go to Colombo?"), 1)
        1 -> Pentuple("What is the comparative degree of 'bad'?", "'Bad' (නරක) වචනයේ සංසන්දනාත්මක (Comparative) අවස්ථාව කුමක්ද?", listOf("Badder", "Baddest", "Worse", "Worst"), listOf("බැඩර්", "බැඩස්ට්", "නරකම (Worse)", "වර්ස්ට්"), 2)
        2 -> Pentuple("What is the past participle of 'fly'?", "'Fly' (පියාඹනවා) වචනයේ අතීත කෘදන්තය (Past Participle) කුමක්ද?", listOf("Flewed", "Flew", "Flown", "Flying"), listOf("Flewed", "Flew", "Flown (පියාසර කර තිබේ)", "Flying"), 2)
        3 -> Pentuple("Combine: 'He is poor. He is honest.' using 'but':", "'He is poor' සහ 'He is honest' සම්බන්ධ කරන්න:", listOf("He is poor and honest.", "He is poor but honest.", "He is poor because honest.", "He is poor or honest."), listOf("He is poor and honest.", "He is poor but honest. (දුප්පත් නමුත් අවංකයි)", "He is poor because honest.", "He is poor or honest."), 1)
        4 -> Pentuple("What does suffix '-less' mean in 'careless'?", "'Careless' වචනයේ '-less' මඟින් අදහස් කරන්නේ කුමක්ද?", listOf("With", "Without", "Full", "More"), listOf("සහත", "තොරව (Without / care-less)", "පිරුණු", "වැඩිපුර"), 1)
        5 -> Pentuple("Choose the correct preposition: 'The plane flew ______ the mountains.'", "නිවැරදි නිපාත පදය: 'The plane flew ______ the mountains.'", listOf("under", "over", "at", "in"), listOf("යටින්", "ඉහළින් (over)", "හිදී", "තුළ"), 1)
        6 -> Pentuple("What is the plural of 'shelf'?", "'Shelf' (තට්ටුව / රාක්කය) වචනයේ බහුවචනය කුමක්ද?", listOf("Shelfs", "Shelves", "Shelfes", "Shilf"), listOf("Shelfs", "Shelves (රාක්ක)", "Shelfes", "Shilf"), 1)
        7 -> Pentuple("Complete: 'They ______ been writing the exam for two hours now.'", "හිස්තැන පුරවන්න: 'They ______ been writing the exam for two hours now.'", listOf("has", "have", "are", "was"), listOf("has", "have (ඇත)", "are", "was"), 1)
        8 -> Pentuple("What is the synonym of the word 'start'?", "'Start' (ආරම්භ කරනවා) වචනයට සමාන තේරුමක් දෙන වචනය කුමක්ද?", listOf("Finish", "Begin", "Stop", "Delay"), listOf("අවසන් කරනවා", "ආරම්භ කරනවා (Begin)", "නතර කරනවා", "ප්‍රමාද කරනවා"), 1)
        else -> Pentuple("What spelling is correct for representing a system of rules?", "රීති පද්ධතියක් සඳහා වන නිවැරදි ඉංග්‍රීසි ලක්ෂණය කුමක්ද?", listOf("Principle", "Principal", "Prancipal", "Prinsiple"), listOf("ධර්මය / රීතිය (Principle)", "විදුහල්පති (Principal)", "ප්‍රැන්සිපල්", "ප්‍රින්සිපල්"), 0)
    }
}
