package com.example.data

object EnglishQuestions2 {

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
            subject = "English",
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
        0 -> Pentuple("What is the contraction for 'do not'?", "'do not' යන්න කෙටි කර ලියන්නේ කෙසේද?", listOf("don't", "dont'", "do'nt", "dnot"), listOf("don't", "dont'", "do'nt", "dnot"), 0)
        1 -> Pentuple("Which pronoun is plural?", "පහත සර්වනාම වලින් බහුවචන (Plural) සර්වනාමය කුමක්ද?", listOf("He", "She", "They", "It"), listOf("ඔහු", "ඇය", "ඔවුන් (They)", "එය"), 2)
        2 -> Pentuple("Identify the naming word (noun) in: 'Colombo is a big city.'", "නාම පදය තෝරන්න: 'Colombo is a big city.'", listOf("big", "city / Colombo", "is", "a"), listOf("විශාල", "නගරය / කොළඹ (city/Colombo)", "is", "a"), 1)
        3 -> Pentuple("Complete: 'The children ______ playing football now.'", "හිස්තැන පුරවන්න: 'The children ______ playing football now.'", listOf("is", "am", "are", "was"), listOf("is", "am", "are (පිරිසක්)", "was"), 2)
        4 -> Pentuple("What is the past tense of the irregular verb 'sing'?", "'Sing' (ගීත ගයනවා) ක්‍රියා පදයේ අතීත කාලය කුමක්ද?", listOf("Singed", "Sang", "Sung", "Singing"), listOf("Singed", "Sang (ගැයුවා)", "Sung", "Singing"), 1)
        5 -> Pentuple("Choose the word that needs a capital letter: 'we live in sri lanka.'", "මහ අකුරක් (Capital) අවශ්‍ය වන වචනය කුමක්ද: 'we live in sri lanka.'", listOf("we", "sri lanka", "live", "both 'we' and 'sri lanka'"), listOf("we", "sri lanka", "live", "we සහ sri lanka දෙකම"), 3)
        6 -> Pentuple("Add the missing contraction: 'I ______ go to play because I am busy.'", "හිස්තැනට කෙටි පදය තෝරන්න: 'I ______ go to play because I am busy.'", listOf("don't", "doesn't", "isn't", "aren't"), listOf("don't (බලාපොරොත්තු නොවේ)", "doesn't", "isn't", "aren't"), 0)
        7 -> Pentuple("What is the plural of 'class'?", "'Class' (පන්තිය) වචනයේ බහුවචනය කුමක්ද?", listOf("Classis", "Classes", "Classs", "Cleese"), listOf("Classis", "Classes (පන්ති)", "Classs", "Cleese"), 1)
        8 -> Pentuple("Choose the auxiliary verb: 'She ______ drawing a map.'", "සහායක ක්‍රියා පදය තෝරන්න: 'She ______ drawing a map.'", listOf("am", "is", "are", "have"), listOf("am", "is (ඇය එක අයෙක්)", "are", "have"), 1)
        else -> Pentuple("Identify the past tense of 'drink':", "'Drink' (බොනවා) ක්‍රියා පදයේ අතීත කාලය කුමක්ද?", listOf("Drank", "Drunk", "Drinked", "Drinking"), listOf("Drank (බීවා)", "Drunk", "Drinked", "Drinking"), 0)
    }

    private fun getGrade6Stage2(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("Complete with present continuous tense: 'The boys ______ the street.'", "වර්තමාන ප්‍රගතිශීලී කාලය යොදන්න: 'The boys ______ the street.'", listOf("runs", "are running", "was running", "is run"), listOf("runs", "are running (දුවමින් සිටිති)", "was running", "is run"), 1)
        1 -> Pentuple("What punctuation mark is missing: 'Where are you going tomorrow __'", "හිස් වූ විරාම ලකුණ කුමක්ද: 'Where are you going tomorrow __'", listOf("Full stop .", "Comma ,", "Question mark ?", "Exclamation !"), listOf("තිත .", "කොමාව ,", "ප්‍රශ්නාර්ථ ලකුණ (?)", "විස්මයාර්ථය !"), 2)
        2 -> Pentuple("Choose the regular past tense verb:", "සාමාන්‍ය නීතිගත අතීත ක්‍රියා පදය (regular past tense) තෝරන්න:", listOf("Went", "Ate", "Played", "Spoke"), listOf("Went (අක්‍රමවත්)", "Ate (අක්‍රමවත්)", "Played (played - ක්‍රමවත්)", "Spoke (අක්‍රමවත්)"), 2)
        3 -> Pentuple("Identify the possessive pronoun: 'This pen is ______.'", "අයිතිය පෙන්වන සර්වනාමය: 'This pen is ______.'", listOf("me", "my", "mine", "I"), listOf("මාව", "මගේ", "මා සතුය / මගේ (mine)", "මම"), 2)
        4 -> Pentuple("Which spelling is correct for representing a day?", "දවසක නිවැරදි ඉංග්‍රීසි අක්ෂර වින්‍යාසය කුමක්ද?", listOf("Wedensday", "Wednesday", "Wedsday", "Wednessday"), listOf("Wedensday", "Wednesday (බදාදා)", "Wedsday", "Wednessday"), 1)
        5 -> Pentuple("Complete: 'She doesn't ______ milk.'", "නිවැරදි ක්‍රියා පදය යොදන්න: 'She doesn't ______ milk.'", listOf("likes", "like", "liked", "liking"), listOf("likes", "like (singular negative verb)", "liked", "liking"), 1)
        6 -> Pentuple("What is the plural of 'baby'?", "'Baby' (බිළිඳා) වචනයේ බහුවචනය කුමක්ද?", listOf("Babys", "Babies", "Babyes", "Babeis"), listOf("Babys", "Babies (බිළිඳුන්)", "Babyes", "Babeis"), 1)
        7 -> Pentuple("Identify the common noun in: 'The teacher wrote on the whiteboard.'", "පොදු නාම පදය තෝරන්න: 'The teacher wrote on the whiteboard.'", listOf("wrote", "teacher / whiteboard", "on", "the"), listOf("wrote", "teacher / whiteboard (ගුරුතුමා/පුවරුව)", "on", "the"), 1)
        8 -> Pentuple("Which is the antonym of 'hardworking'?", "'Hardworking' (උත්සාහවන්ත) වචනයේ ප්‍රතිවිරුද්ධ වචනය කුමක්ද?", listOf("Diligent", "Active", "Lazy", "Smart"), listOf("දක්ෂ", "ක්‍රියාශීලී", "Lazy (කම්මැලි)", "ස්මාර්ට්"), 2)
        else -> Pentuple("Complete: 'Yesterday, we ______ a beautiful parrot in our garden.'", "හිස්තැන පුරවන්න: 'Yesterday, we ______ a beautiful parrot in our garden.'", listOf("see", "saw", "seen", "seeing"), listOf("see", "saw (දැක්කා)", "seen", "seeing"), 1)
    }

    private fun getGrade6Stage3(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("Choose the sentence with correct punctuation and capitalization:", "නිවැරදි විරාම ලකුණු හා අකුරු සහිත වාක්‍යය තෝරන්න:", listOf("we visited colombo zoo last Sunday.", "We visited Colombo Zoo last Sunday.", "We visited colombo zoo last sunday.", "We visited colombo Zoo last Sunday."), listOf("we visited colombo zoo last Sunday.", "We visited Colombo Zoo last Sunday. (නිවැරදිය)", "We visited colombo zoo last sunday.", "We visited colombo Zoo last Sunday."), 1)
        1 -> Pentuple("What is the irregular past tense of the verb 'know'?", "'Know' (දන්නවා) ක්‍රියා පදයේ අතීත කාලය කුමක්ද?", listOf("Knowed", "Knew", "Known", "Knowing"), listOf("Knowed", "Knew (දැන සිටියා)", "Known", "Knowing"), 1)
        2 -> Pentuple("Fill in: 'Neither Nimal ______ Kamal attended the meeting.'", "හිස්තැන පුරවන්න: 'Neither Nimal ______ Kamal attended the meeting.'", listOf("or", "nor", "and", "but"), listOf("හෝ", "nor ( Neither සමඟ යෙදේ)", "සහ", "නමුත්"), 1)
        3 -> Pentuple("What is the feminine counterpart of 'nephew'?", "'Nephew' (බෑණනුවන්) වචනයේ ස්ත්‍රී ලිංග පදය කුමක්ද?", listOf("Niece", "Aunt", "Sister", "Daughter"), listOf("Niece (ලේලිය)", "නැන්දා", "සහෝදරිය", "දියණිය"), 0)
        4 -> Pentuple("Choose the adverb of frequency in: 'He always wakes up at 5:00 AM.'", "ප්‍රවණතා ක්‍රියා විශේෂණය තෝරන්න: 'He always wakes up at 5:00 AM.'", listOf("wakes", "always", "at", "up"), listOf("අවදි වනවා", "always (සෑමවිටම)", "හිදී", "උඩ"), 1)
        5 -> Pentuple("The plural of 'leaf' is:", "'Leaf' (කොළය) වචනයේ බහුවචනය කුමක්ද?", listOf("Leafs", "Leaves", "Leafes", "Leef"), listOf("Leafs", "Leaves (කොළ)", "Leafes", "Leef"), 1)
        6 -> Pentuple("Which word is an adjective? 'He is a polite student.'", "විශේෂණ පදය කුමක්ද? 'He is a polite student.'", listOf("student", "polite", "he", "is"), listOf("ශිෂ්‍යයා", "polite (කාරුණික / විනීත)", "ඔහු", "is"), 1)
        7 -> Pentuple("What suffix is added to 'comfort' to mean 'able to be comfortable'?", "comfortable යන අර්ථය දීමට 'comfort' වලට එක් කරන ප්‍රත්‍යය කුමක්ද?", listOf("-ly", "-able", "-ness", "-ful"), listOf("-ly", "-able (comfortable)", "-ness", "-ful"), 1)
        8 -> Pentuple("What is the past tense of the verb 'bring'?", "'Bring' (රැගෙන එනවා) වචනයේ අතීත කාලය කුමක්ද?", listOf("Brought", "Bought", "Brang", "Bringing"), listOf("Brought (රැගෙන ආවා)", "Bought", "Brang", "Bringing"), 0)
        else -> Pentuple("Complete: 'They ______ finished their work an hour ago.'", "හිස්තැන පුරවන්න: 'They ______ finished their work an hour ago.'", listOf("have", "had", "has", "was"), listOf("have", "had (අතීත කාල finished සමඟ)", "has", "was"), 1)
    }

    // GRADE 7
    private fun getGrade7Stage1(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("What modal verb shows strong necessity or obligation?", "අනිවාර්යයෙන්ම කළ යුතු දෙයක් පෙන්වීමට ගන්නා සහායක ක්‍රියාව කුමක්ද?", listOf("can", "must", "might", "may"), listOf("හැක", "must (අනිවාර්යයෙන්ම)", "සමහරවිට හැකිය", "සහයෝගයෙන් හැකිය"), 1)
        1 -> Pentuple("What is the comparative form of the adjective 'bad'?", "'Bad' (නරක) වචනයේ සංසන්දනාත්මක අවස්ථාව (Comparative) කුමක්ද?", listOf("badder", "worse", "worst", "more bad"), listOf("බැඩර්", "worse (වඩා නරක)", "Worst (නරකම)", "මෝ බැඩ්"), 1)
        2 -> Pentuple("Which relative pronoun is used for people?", "පුද්ගලයන් හැඳින්වීමට ගන්නා සර්වනාම පදය කුමක්ද?", listOf("which", "who", "where", "that"), listOf("කුමන", "who (කවුරුන්ද / යම්කිසි කෙනෙක්)", "කොහේද", "ඒ"), 1)
        3 -> Pentuple("Complete the passive voice sentence: 'The letter ______ written by Anil yesterday.'", "කර්ම කාරක වාක්‍යය සම්පූර්ණ කරන්න: 'The letter ______ written by Anil yesterday.'", listOf("is", "was", "were", "are"), listOf("is", "was (ලිපිය ලියා තිබුණි)", "were", "are"), 1)
        4 -> Pentuple("Choose the comparative form of 'clever':", "'Clever' (දක්ෂ) වචනයේ සංසන්දන අවස්ථාව තෝරන්න:", listOf("cleverer", "cleverest", "more cleverest", "clevererest"), listOf("cleverer (වඩා දක්ෂ)", "cleverest", "මෝ ක්ලෙවරස්ට්", "ක්ලෙවරරස්ට්"), 0)
        5 -> Pentuple("Which modal expresses a polite request?", "විනීත ඉල්ලීමක් ප්‍රකාශ කිරීමට ගන්නා වචනය කුමක්ද?", listOf("must", "could", "should", "will"), listOf("අනිවාර්යය", "could (කරන්න පුළුවන්ද)", "යුතුය", "කරනු ඇත"), 1)
        6 -> Pentuple("Fill in with relative pronoun: 'This is the book ______ I bought yesterday.'", "හිස්තැන පුරවන්න: 'This is the book ______ I bought yesterday.'", listOf("who", "which / that", "whose", "whom"), listOf("කවුද", "which / that (පොත් සඳහා)", "කාගේද", "කාටද"), 1)
        7 -> Pentuple("What is the passive form of: 'She eats an apple.'?", "'She eats an apple' යන්නෙහි කර්ම කාරක (passive) ආකාරය කුමක්ද?", listOf("An apple was eaten by her.", "An apple is eaten by her.", "An apple has been eaten by her.", "An apple is eating her."), listOf("An apple was eaten by her.", "An apple is eaten by her. (නිවැරදිය)", "An apple has been eaten by her.", "An apple is eating her."), 1)
        8 -> Pentuple("What modal is best for giving advice?: 'You ______ study hard for the term exam.'", "උපදෙස් දීමට ගන්නා ක්‍රියා පදය: 'You ______ study hard for the term exam.'", listOf("must", "should", "might", "could"), listOf("අනිවාර්යය", "should (යුතුය)", "සමහරවිට", "පුළුවන්"), 1)
        else -> Pentuple("Choose the correct spelling of the vegetable:", "නිවැරදි ඉංග්‍රීසි අක්ෂර වින්‍යාසය තෝරන්න:", listOf("Pumpkin", "Pumkin", "Pumpken", "Pompkin"), listOf("Pumpkin (වට්ටක්කා)", "Pumkin", "Pumpken", "Pompkin"), 0)
    }

    private fun getGrade7Stage2(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("What is the comparative form of the adjective 'far'?", "'Far' (දුර) වචනයේ සංසන්දනාත්මක අවස්ථාව කුමක්ද?", listOf("farrer", "farther / further", "farthest", "more far"), listOf("ෆැරර්", "farther / further (වඩා දුරින්)", "Farthest", "මෝ ෆා"), 1)
        1 -> Pentuple("Complete: 'The teacher made us ______ the whole exercise.'", "හිස්තැන පුරවන්න: 'The teacher made us ______ the whole exercise.'", listOf("to copy", "copy", "copied", "copying"), listOf("to copy", "copy (infinitive without to)", "copied", "copying"), 1)
        2 -> Pentuple("Which of these is a compound word?", "පහත ඒවායින් සංයුක්ත වචනයක් (Compound Word) කුමක්ද?", listOf("Beautiful", "Whiteboard", "Teacher", "Quickly"), listOf("ලස්සන", "Whiteboard (සුදු ලෑල්ල)", "ගුරුතුමා", "වේගයෙන්"), 1)
        3 -> Pentuple("What is the passive form of: 'The carpenter fixed the chair.'?", "'The carpenter fixed the chair' යන්නෙහි කර්ම කාරක ස්වරූපය?", listOf("The chair is fixed by the carpenter.", "The chair was fixed by the carpenter.", "The chair had been fixed by her.", "The carpenter was fixed by the chair."), listOf("The chair is fixed by... ", "The chair was fixed by... (Fixed - past tense)", "The chair had been...", "The carpenter was fixed..."), 1)
        4 -> Pentuple("Complete: 'The lady ______ dog was lost called the police.'", "හිස්තැන පුරවන්න: 'The lady ______ dog was lost called the police.'", listOf("who", "whose", "whom", "which"), listOf("කවුද", "whose (කාගේද / ඇයගේ)", "කාටද", "කුමන"), 1)
        5 -> Pentuple("Which modal represents a past ability?", "අතීතයේ තිබූ හැකියාවක් පෙන්වන වචනය කුමක්ද?", listOf("can", "will", "could", "should"), listOf("හැක", "කරනු ඇත", "could (හැකිව තිබුණි)", "යුතුය"), 2)
        6 -> Pentuple("Choose the superlative of 'dangerous':", "'Dangerous' (අනතුරුදායක) වචනයේ උපරිමතම (Superlative) අවස්ථාව?", listOf("dangeroustop", "most dangerous", "more dangerous", "dangerousest"), listOf("ඩේන්ජරස්ටොප්", "most dangerous (අනතුරුදායකම)", "more dangerous", "ඩේන්ජරස්ට්"), 1)
        7 -> Pentuple("What is the opposite of 'generous' (giving)?", "'Generous' (ත්‍යාගශීලී) වචනයේ ප්‍රතිවිරුද්ධ වචනය කුමක්ද?", listOf("Kind", "Mean / Stingy", "Polite", "Helpful"), listOf("කරුණාවන්ත", "Mean / Stingy (මසුරු / කුණූ)", "විනීත", "උදව් සහගත"), 1)
        8 -> Pentuple("Complete: 'She ______ speak French when she was five years old.'", "හිස්තැන පුරවන්න: 'She ______ speak French when she was five years old.'", listOf("can", "could", "might", "should"), listOf("හැකිය", "could (අතීත හැසිරීම)", "සමහරවිට", "යුතුය"), 1)
        else -> Pentuple("What spelling represents a holiday spent at sea?", "මුහුදේ සංචාරය කරමින් ගත කරන නිවාඩුව කුමක්ද?", listOf("Cruse", "Cruise", "Crewse", "Cruize"), listOf("Cruse", "Cruise (මුහුදු සංචාරය)", "Crewse", "Cruize"), 1)
    }

    private fun getGrade7Stage3(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("Combine using 'who': 'The boy is poor. He works hard.'", "'The boy is poor' සහ 'He works hard' එකතු කරන්න:", listOf("The boy is poor who works hard.", "The boy who works hard is poor.", "The boy works hard who is poor.", "The boy is poor and works hard."), listOf("The boy is poor who works hard.", "The boy who works hard is poor. (නිවැරදිය)", "The boy works hard who is poor.", "The boy is poor and works hard."), 1)
        1 -> Pentuple("What is direct speech translated to when we report it?", "යමෙකු පැවසූ දේ අප නැවත වාර්තා කිරීමේදී හඳුන්වන්නේ?", listOf("Active speech", "Indirect / Reported speech", "Passive voice", "Direct voice"), listOf("ක්‍රියාකාරක", "Indirect / Reported speech (වාර්තාකරණය)", "කර්ම කාරක", "ප්‍රකාශනය"), 1)
        2 -> Pentuple("Translate to indirect speech: She said, 'I am happy.'", "Indirect speech වලට හරවන්න: She said, 'I am happy.'", listOf("She said she is happy.", "She said that she was happy.", "She said that I am happy.", "She said she was happy yesterday."), listOf("She said she is happy.", "She said that she was happy. (නිවැරදිය)", "She said that I am happy.", "She said she was happy yesterday."), 1)
        3 -> Pentuple("What is superlative of 'little'?", "'Little' (කුඩා / සුළු) වචනයේ උපරිමතම (Superlative) අවස්ථාව?", listOf("least", "less", "littlest", "fewest"), listOf("least (අඩුම)", "less", "littlest", "fewest"), 0)
        4 -> Pentuple("Which spelling is correct for writing a written note sent to another?", "නිල වශයෙන් යවන කෙටි ලිපියක නිවැරදි ඉංග්‍රීසි ලක්ෂණය?", listOf("Mandum", "Memorandum", "Memurandum", "Memorandem"), listOf("Mandum", "Memorandum (කෙටි නිල සටහන)", "Memurandum", "Memorandem"), 1)
        5 -> Pentuple("Complete: 'The teacher asked the class ______ make any noise.'", "හිස්තැන පුරවන්න: 'The teacher asked the class ______ make any noise.'", listOf("to not", "not to", "don't", "no to"), listOf("to not", "not to (ක්‍රියා පදයට පෙර)", "don't", "no to"), 1)
        6 -> Pentuple("Choose the passive equivalent: 'A dog bit the postman.'", "කර්ම කාරකය තෝරන්න: 'A dog bit the postman.'", listOf("The postman is bitten by a dog.", "The postman was bitten by a dog.", "The postman had bitten a dog.", "A dog was bitten by the postman."), listOf("The postman is bitten by...", "The postman was bitten by... (Bit - past)", "The postman had bitten...", "A dog was bitten by..."), 1)
        7 -> Pentuple("Identify the indirect speech: He said, 'I am reading.'", "Reported form එක කුමක්ද: He said, 'I am reading.'", listOf("He said that he is reading.", "He said that he was reading.", "He said he read.", "He says he is reading."), listOf("He said that he is reading.", "He said that he was reading. (was reading - past cont)", "He said he read.", "He says he is reading."), 1)
        8 -> Pentuple("What suffix can turn the verb 'agree' into a noun?", "'Agree' ක්‍රියාව නාම පදයක් කිරීමට එකතු කරන ප්‍රත්‍යය කුමක්ද?", listOf("-ly", "-ment", "-able", "-tion"), listOf("-ly", "-ment (agreement)", "-able", "-tion"), 1)
        else -> Pentuple("Choose the antonym of 'domestic' (animals):", "'Domestic' (ගෘහස්ථ) වචනයේ ප්‍රතිවිරුද්ධ වචනය කුමක්ද?", listOf("Indoor", "Wild", "Tame", "Pet"), listOf("ගෘහස්ථ", "Wild (වනචාරී / වල්)", "සුරතල්", "පෙට්"), 1)
    }

    // GRADE 8
    private fun getGrade8Stage1(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("Identify the conditional sentence Type 1:", "පළමු ගණයේ කොන්දේසි සහිත වාක්‍යය (Type 1 Conditional) කුමක්ද?", listOf("If I sleep, I dream.", "If it rains, we will stay at home.", "If I were you, I would go.", "If I had run, I would have won."), listOf("If I sleep, I dream.", "If it rains, we will stay at home. (will + verb)", "If I were you, I would go.", "If I had run, I would have won."), 1)
        1 -> Pentuple("What is the past perfect tense of 'write'?", "'Write' (ලියනවා) වචනයේ අතීත පූර්ණ කාලය (Past Perfect) කුමක්ද?", listOf("wrote", "had written", "has written", "was writing"), listOf("wrote", "had written (had + pp)", "has written", "was writing"), 1)
        2 -> Pentuple("Which adverb of frequency shows doing something 100% of the time?", "100% ක්ම යම් දෙයක් නිරන්තරයෙන්ම කරන බව හැඟවීමට ගන්නා පදය?", listOf("Never", "Sometimes", "Often", "Always"), listOf("කිසිවිටක නැත", "සමහරවිට", "බොහෝවිට", "Always (නිරන්තරයෙන්ම)"), 3)
        3 -> Pentuple("Complete the question tag: 'You are a student, ______?'", "ප්‍රශ්න ටැගය (Question Tag) සම්පූර්ණ කරන්න: 'You are a student, ______?'", listOf("are you", "aren't you", "don't you", "isn't it"), listOf("are you", "aren't you (ධන වාක්‍යකට සෘණ ටැගය)", "don't you", "isn't it"), 1)
        4 -> Pentuple("Choose the past perfect tense: 'By the time I arrived, they ______.'", "අතීත පූර්ණ කාලය යොදන්න: 'By the time I arrived, they ______.'", listOf("left", "had left", "have left", "were leaving"), listOf("left", "had left (Had + PP)", "have left", "were leaving"), 1)
        5 -> Pentuple("Complete the conditional sentence: 'If you study hard, you ______ pass.'", "කොන්දේසිය සම්පූර්ණ කරන්න: 'If you study hard, you ______ pass.'", listOf("will", "would", "having", "was"), listOf("will (will + verb)", "would", "having", "was"), 0)
        6 -> Pentuple("What is the adverb of frequency meaning 0%?", "0% ක් එනම් කිසිසේත්ම සිදු නොවන බව පවසන පදය කුමක්ද?", listOf("Rarely", "Never", "Always", "Usually"), listOf("කලාතුරකින්", "Never (කිසිවිටක නැත)", "සෑමවිටම", "සාමාන්‍යයෙන්"), 1)
        7 -> Pentuple("Choose the correct question tag: 'Kamal went to Kandy, ______?'", "නිවැරදි ප්‍රශ්න ටැගය: 'Kamal went to Kandy, ______?'", listOf("didn't he", "did he", "isn't he", "doesn't he"), listOf("didn't he (අතීත ධන වාක්‍යයට)", "did he", "isn't he", "doesn't he"), 0)
        8 -> Pentuple("Identify the past perfect form: 'She ______ the keys before leaving.'", "හිස්තැන පුරවන්න: 'She ______ the keys before leaving.'", listOf("loses", "lost", "had lost", "has lost"), listOf("loses", "lost", "had lost (Had + PP)", "has lost"), 2)
        else -> Pentuple("Which preposition indicates direction towards a destination?", "කිසියම් ඉලක්කයක් හෝ දිශාවක් පෙන්වීමට ගන්නා නිපාතය?", listOf("at", "on", "towards", "by"), listOf("හිදී", "මත", "towards (දෙසට)", "විසින්"), 2)
    }

    private fun getGrade8Stage2(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("Complete conditional type 1: 'If she ______ late, she will miss the train.'", "Type 1 කොන්දේසිය සම්පූර්ණ කරන්න: 'If she ______ late, she will miss the train.'", listOf("arrived", "arrives", "will arrive", "has arrived"), listOf("arrived", "arrives (simple present in if-clause)", "will arrive", "has arrived"), 1)
        1 -> Pentuple("What is the question tag for: 'They had not eaten lunch, ______?'", "නිවැරදි ප්‍රශ්න ටැගය: 'They had not eaten lunch, ______?'", listOf("had they", "hadn't they", "did they", "didn't they"), listOf("had they (had not - සෘණ වාක්‍යයට ධන ටැගය)", "hadn't they", "did they", "didn't they"), 0)
        2 -> Pentuple("Complete with past perfect: 'I ______ never eaten a dragon fruit before.'", "Past perfect සම්පූර්ණ කරන්න: 'I ______ never eaten a dragon fruit before.'", listOf("have", "has", "had", "was"), listOf("have", "has", "had (Past perfect - had)", "was"), 2)
        3 -> Pentuple("Choose the preposition of direction: 'He jumped ______ the swimming pool.'", "දිශාව දක්වන නිපාතය: 'He jumped ______ the swimming pool.'", listOf("into", "in", "on", "under"), listOf("තුළට (into - චලනය ඇතිව)", "තුළ", "මත", "යට"), 0)
        4 -> Pentuple("If he is late, he will miss the class. What type conditional is this?", "මෙම කොන්දේසි සහිත වාක්‍යය කුමන වර්ගයට අයත්ද?", listOf("Type 0", "Type 1", "Type 2", "Type 3"), listOf("Type 0", "Type 1 (present + will verb)", "Type 2", "Type 3"), 1)
        5 -> Pentuple("What is the tag for: 'She is very beautiful, ______?'", "නිවැරදි ප්‍රශ්න ටැගය කුමක්ද: 'She is very beautiful, ______?'", listOf("is she", "isn't she", "doesn't she", "isn't it"), listOf("is she", "isn't she (is ධන වාක්‍යයට)", "doesn't she", "isn't it"), 1)
        6 -> Pentuple("Complete: 'By noon, the rain ______ stopped.'", "හිස්තැන පුරවන්න: 'By noon, the rain ______ stopped.'", listOf("has", "have", "had", "was"), listOf("has", "have", "had (past perfect)", "was"), 2)
        7 -> Pentuple("Which of these means 'rarely' or 'almost never'?", "'very rarely' එනම් 'කලාතුරකින්' යන්නෙහි තේරුම කුමක්ද?", listOf("Always", "Seldom", "Often", "Usually"), listOf("සැමවිටම", "Seldom (කලාතුරකින්)", "බොහෝවිට", "සාමාන්‍යයෙන්"), 1)
        8 -> Pentuple("Preposition of direction meaning 'moving to higher level': 'The cat climbed ______ the roof.'", "ගස මුදුනට හෝ උඩට නැගීමේදී ගන්නා නිපාතය:", listOf("onto", "into", "through", "past"), listOf("onto (මතුපිට උඩට)", "into", "හරහා", "පසුකර"), 0)
        else -> Pentuple("What spelling is correct for writing a formal introduction sent with CV?", "CV එකක් සමඟ යවන නිල ලිපියේ නිවැරදි ඉංග්‍රීසි අක්ෂර වින්‍යාසය?", listOf("Covering letter", "Coverring letter", "Cowering letter", "Covver letter"), listOf("Covering letter (ආවරණ ලිපිය)", "Coverring letter", "Cowering letter", "Covver letter"), 0)
    }

    private fun getGrade8Stage3(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("Complete conditional type 2: 'If I ______ a king, I would help the poor.'", "Type 2 කොන්දේසිය සම්පූර්ණ කරන්න: 'If I ______ a king, I would help the poor.'", listOf("am", "was", "were", "been"), listOf("am", "was", "were (අභූත / මන:කල්පිත)", "been"), 2)
        1 -> Pentuple("Question tag for: 'Let's go to the beach, ______?'", "නිවැරදි ප්‍රශ්න ටැගය: 'Let's go to the beach, ______?'", listOf("shall we", "will we", "don't we", "aren't we"), listOf("shall we (Let's සමඟ shall we යෙදේ)", "will we", "don't we", "aren't we"), 0)
        2 -> Pentuple("Complete the past perfect continuous: 'They ______ been playing for hours.'", "Past perfect continuous සම්පූර්ණ කරන්න: 'They ______ been playing for hours.'", listOf("has", "have", "had", "was"), listOf("has", "have", "had (had + been)", "was"), 2)
        3 -> Pentuple("Which preposition shows direction 'going through inside a tunnel'?", "උමගක් හෝ වනාන්තරයක් හරහා යාම පෙන්වීමට ගන්නා නිපාතය?", listOf("across", "through", "over", "along"), listOf("හරහා (තලයක)", "through (ඇතුළතින් හරහා)", "ඉහළින්", "දිගේ"), 1)
        4 -> Pentuple("What is the passive form of: 'Someone has stolen my bag.'?", "'Someone has stolen my bag' යන්නෙහි කර්ම කාරකය?", listOf("My bag is stolen by someone.", "My bag has been stolen.", "My bag was stolen by someone.", "Someone was stolen my bag."), listOf("My bag is stolen by someone.", "My bag has been stolen. (නිවැරදිය)", "My bag was stolen by someone.", "Someone was stolen my bag."), 1)
        5 -> Pentuple("Identify the sentence containing an adverb of frequency:", "ප්‍රවණතා ක්‍රියා විශේෂණයක් අඩංගු වාක්‍යය තෝරන්න:", listOf("He ran to the school.", "He seldom speaks in class.", "She is writing a letter.", "They went home yesterday."), listOf("He ran to the school.", "He seldom speaks in class. (seldom - කලාතුරකින්)", "She is writing a letter.", "They went home yesterday."), 1)
        6 -> Pentuple("What is the tag for: 'Don't make any noise, ______?'", "නිවැරදි ප්‍රශ්න ටැගය: 'Don't make any noise, ______?'", listOf("will you", "do you", "don't you", "won't you"), listOf("will you ( Don't / ඉල්ලීම් සමඟ)", "do you", "don't you", "won't you"), 0)
        7 -> Pentuple("Complete type 2: 'If I ______ more money, I would buy a car.'", "Type 2 කොන්දේසිය සම්පූර්ණ කරන්න: 'If I ______ more money, I would buy a car.'", listOf("have", "had", "would have", "had had"), listOf("have", "had (Simple past in if-clause)", "would have", "had had"), 1)
        8 -> Pentuple("Which connector represents two possibilities of which neither is chosen?", "ලබා දී ඇති විකල්ප දෙකම නොවන බව හැඟවීමට ගන්නා සම්බන්ධකය?", listOf("either...or", "neither...nor", "not only...but also", "both...and"), listOf("either...or", "neither...nor (දෙකම නොවන)", "not only...but also", "both...and"), 1)
        else -> Pentuple("Choose the meaning of the idiom 'once in a blue moon':", "'once in a blue moon' යන උපහැරණයේ තේරුම කුමක්ද?", listOf("Very frequently", "Very rarely", "Every full moon night", "Never"), listOf("නිතර නිතර", "ඉතා කලාතුරකින් (Very rarely)", "සෑම පසළොස්වක පොහොයකම", "කිසිවිටක නැත"), 1)
    }

    // GRADE 9
    private fun getGrade9Stage1(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("Identify the passive voice: 'The old temple was built by the ancient kings.'", "කර්ම කාරක (passive voice) වාක්‍යය තෝරන්න:", listOf("The ancient kings built the old temple.", "The old temple was built by the ancient kings.", "The kings build temples.", "The temple is very old."), listOf("The ancient kings built...", "The old temple was built... (was + built)", "The kings build...", "The temple is very old."), 1)
        1 -> Pentuple("What is the reported speech equivalent of: 'Where do you live?' she asked.", "වාර්තාකරණ ක්‍රමය තෝරන්න: 'Where do you live?' she asked.", listOf("She asked where I live.", "She asked where I lived.", "She asked where do I live.", "She asked me where did I lived."), listOf("She asked where I live.", "She asked where I lived. (lived - past change)", "She asked where do I live.", "She asked me where did I lived."), 1)
        2 -> Pentuple("Complete: 'You should do your homework, ______?'", "ප්‍රශ්න ටැගය සම්පූර්ණ කරන්න: 'You should do your homework, ______?'", listOf("should you", "shouldn't you", "must you", "don't you"), listOf("should you", "shouldn't you (should ධන වාක්‍යට)", "must you", "don't you"), 1)
        3 -> Pentuple("Fill in: 'He is ______ intelligent ______ hardworking.' (not only... but also style)", "හිස්තැන පුරවන්න: 'He is ______ intelligent ______ hardworking.'", listOf("neither... nor", "not only... but also", "either... or", "both... or"), listOf("neither... nor", "not only... but also (intelligent මෙන්ම)", "either... or", "both... or"), 1)
        4 -> Pentuple("What is the past perfect of the verb 'seek'?", "'Seek' (සොයනවා) වචනයේ අතීත පූර්ණ කාලය (Past Perfect) කුමක්ද?", listOf("had seeked", "had sought", "had soughten", "sought"), listOf("had seeked", "had sought (had + sought)", "had soughten", "sought"), 1)
        5 -> Pentuple("Choose the correct passive voice: 'We are cleanings the rooms now.'", "නිවැරදි කර්ම කාරක වාක්‍යය තෝරන්න: 'We are cleaning the rooms now.'", listOf("The rooms are cleaned by us.", "The rooms are being cleaned by us.", "The rooms were being cleaned by us.", "The rooms is being cleaned by us."), listOf("The rooms are cleaned...", "The rooms are being cleaned... (continuous)", "The rooms were being...", "The rooms is being..."), 1)
        6 -> Pentuple("Which relative clause is non-defining (adds extra information with commas)?", "අතිරේක තොරතුරු සපයන කොමා සහිත relative clause වර්ගය කුමක්ද?", listOf("Defining relative clause", "Non-defining relative clause", "Noun clause", "Adverb clause"), listOf("Defining relative clause", "Non-defining relative clause (අතිරේක තොරතුරු)", "Noun clause", "Adverb clause"), 1)
        7 -> Pentuple("Reported speech of: She said, 'I have completed my work.'", "She said, 'I have completed my work' යන්නෙහි indirect speech?", listOf("She said that she completed her work.", "She said that she had completed her work.", "She said she has completed her work.", "She said she had completed my work."), listOf("She said that she completed...", "She said that she had completed her work. (had+pp)", "She said she has completed...", "She said she had completed my work."), 1)
        8 -> Pentuple("Complete: 'The driver, ______ car crashed, was arrested.'", "හිස්තැන පුරවන්න: 'The driver, ______ car crashed, was arrested.'", listOf("who", "whose", "whom", "which"), listOf("කවුද", "whose (කාගේද / ඔහුට අයත්)", "කාටද", "කුමන"), 1)
        else -> Pentuple("What is the antonym of the word 'voluntary'?", "'Voluntary' (ස්වෙච්ඡා) වචනයේ ප්‍රතිවිරුද්ධ වචනය කුමක්ද?", listOf("Compulsory / Mandatory", "Optional", "Willing", "Free"), listOf("Compulsory / Mandatory (අනිවාර්යය)", "ස්වකීය", "කැමැත්තෙන්", "නිදහස්"), 0)
    }

    private fun getGrade9Stage2(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("What is the passive form of: 'The chef cooked a delicious dinner.'?", "'The chef cooked a delicious dinner' යන්නෙහි passive voice?", listOf("A delicious dinner is cooked by the chef.", "A delicious dinner was cooked by the chef.", "A delicious dinner had cooked by the chef.", "A delicious dinner was cooking by the chef."), listOf("Dinner is cooked by...", "A delicious dinner was cooked by the chef. (was cooked)", "Dinner had cooked by...", "Dinner was cooking by..."), 1)
        1 -> Pentuple("Reported speech of: He said, 'Don't make a noise.'", "Indirect speech: He said, 'Don't make a noise.'", listOf("He said me not to make a noise.", "He told us not to make a noise.", "He told us don't make a noise.", "He said to not make a noise."), listOf("He said me not to make...", "He told us not to make a noise. (told us not to)", "He told us don't make...", "He said to not make..."), 1)
        2 -> Pentuple("Complete: 'We can go ______ to the park ______ to the library.' (either...or style)", "හිස්තැන පුරවන්න: 'We can go ______ to the park ______ to the library.'", listOf("neither...nor", "either...or", "not only...but also", "both...and"), listOf("neither...nor", "either...or (එක්කෝ උද්‍යානයට නැතහොත්)", "not only...but also", "both...and"), 1)
        3 -> Pentuple("Identify the passive voice: 'The project will be completed tomorrow by them.'", "Passive voice එකක් වන වාක්‍යය තෝරන්න:", listOf("They will complete the project.", "The project will be completed tomorrow by them.", "The project is completed.", "They complete the project."), listOf("They will complete...", "The project will be completed tomorrow... (will be + PP)", "The project is completed.", "They complete the project."), 1)
        4 -> Pentuple("What relative pronoun is used to refer to a place?", "ස්ථානයක් හැඳින්වීම සඳහා ගන්නා relative pronoun එක කුමක්ද?", listOf("who", "where", "which", "when"), listOf("කවුද", "where (කොහේද / යම් ස්ථානයක)", "කුමන", "කවදාද"), 1)
        5 -> Pentuple("Reported speech of: She asked, 'Are you happy?'", "She asked, 'Are you happy?' යන්නෙහි reported voice?", listOf("She asked if I am happy.", "She asked if I was happy.", "She asked that I was happy.", "She asked whether I am happy."), listOf("She asked if I am happy.", "She asked if I was happy. (asked if + was)", "She asked that I was happy.", "She asked whether I am happy."), 1)
        6 -> Pentuple("Complete: 'I have known him ______ ten years.'", "හිස්තැන පුරවන්න: 'I have known him ______ ten years.'", listOf("since", "for", "during", "from"), listOf("සිට", "for (කාලයක් දර්ශනය කරන විට)", "තුළ", "සිට"), 1)
        7 -> Pentuple("Identify past perfect continuous: 'They ______ been waiting for two hours.'", "Past perfect continuous ආකාරය සම්පූර්ණ කරන්න:", listOf("have", "had", "has", "was"), listOf("have", "had (had been + verb-ing)", "has", "was"), 1)
        8 -> Pentuple("What is the word representing the sound made by bees?", "මී මැස්සන් නගන හඬ (onomatopoeia) හඳුන්වන ඉංග්‍රීසි වචනය?", listOf("Click", "Buzz", "Hiss", "Roar"), listOf("ක්ලික්", "Buzz (බස් ගාන හඬ)", "හිස්", "ගෙජ්ජ"), 1)
        else -> Pentuple("Which of these is the correct spelling of a professional title?", "නිවැරදි ඉංග්‍රීසි අක්ෂර වින්‍යාසය තෝරන්න:", listOf("Secretary", "Secretaty", "Sectetary", "Secretari"), listOf("Secretary (ලේකම්)", "Secretaty", "Sectetary", "Secretari"), 0)
    }

    private fun getGrade9Stage3(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("Identify the conditional sentence Type 3:", "තුන්වන ගණයේ කොන්දේසි සහිත වාක්‍යය (Type 3 Conditional) කුමක්ද?", listOf("If you run, you will win.", "If you ran, you would win.", "If you had run, you would have won.", "If you run, you win."), listOf("If you run, you will win.", "If you ran, you would win.", "If you had run, you would have won. (had + would have PP)", "If you run, you win."), 2)
        1 -> Pentuple("What is the passive voice: 'The government is building a new highway.'?", "'The government is building a new highway' යන්නෙහි passive voice?", listOf("A new highway is built by the government.", "A new highway is being built by the government.", "A new highway was being built.", "A new highway has been built."), listOf("Highway is built by...", "A new highway is being built... (is being + built)", "Highway was being built.", "Highway has been built."), 1)
        2 -> Pentuple("Complete: 'He is the scientist ______ won the Nobel Prize.'", "හිස්තැන පුරවන්න: 'He is the scientist ______ won the Nobel Prize.'", listOf("who", "which", "whose", "whom"), listOf("who (පුද්ගලයා සඳහා)", "කුමන", "කාගේද", "කාටද"), 0)
        3 -> Pentuple("Reported speech of: She said, 'I may come tomorrow.'", "She said, 'I may come tomorrow' යන්නෙහි indirect speech?", listOf("She said she may come tomorrow.", "She said that she might come the next day.", "She said she was coming tomorrow.", "She said that she could come tomorrow."), listOf("She said she may come tomorrow.", "She said that she might come the next day. (might + next day)", "She said she was coming tomorrow.", "She said that she could come tomorrow."), 1)
        4 -> Pentuple("What is the plural of 'axis'?", "'Axis' (අක්ෂය) වචනයේ බහුවචනය කුමක්ද?", listOf("Axises", "Axes", "Axies", "Axe"), listOf("Axises", "Axes (අක්ෂ)", "Axies", "Axe"), 1)
        5 -> Pentuple("Which idiom means 'to disclose a secret'?", "රහසක් හෙළි කිරීම හඳුන්වන ඉංග්‍රීසි උපහැරණය කුමක්ද?", listOf("Spill the beans", "Bite the bullet", "Hit the sack", "Break a leg"), listOf("Spill the beans (රහස හෙළි කරනවා)", "Bite the bullet", "Hit the sack", "Break a leg"), 0)
        6 -> Pentuple("Choose the correct sentence in subjunctive mood:", "Subjunctive mood එකකින් පිහිටි නිවැරදි වාක්‍ය සැකැස්ම?", listOf("I wish I was a bird.", "I wish I were a bird.", "I wish I am a bird.", "I wish I been a bird."), listOf("I wish I was a bird.", "I wish I were a bird. (were - මන:කල්පිත)", "I wish I am a bird.", "I wish I been a bird."), 1)
        7 -> Pentuple("What part of speech is 'cheerfully' in: 'She sang cheerfully.'?", "'She sang cheerfully' වාක්‍යයේ 'cheerfully' යනු කුමන පදයක්ද?", listOf("Adjective", "Adverb", "Noun", "Verb"), listOf("विशेषण", "ක්‍රියා විශේෂණය (Adverb)", "නාම පදය", "ක්‍රියා පදය"), 1)
        8 -> Pentuple("Complete: 'They had already left before the meeting ______.'", "හිස්තැන පුරවන්න: 'They had already left before the meeting ______.'", listOf("starts", "started", "had started", "starting"), listOf("starts", "started (Simple past after past perfect clause)", "had started", "starting"), 1)
        else -> Pentuple("Which prefix makes 'understand' mean 'to interpret incorrectly'?", "'Understand' යන්නෙහි වැරදියට වටහාගැනීම යන අර්ථය දීමට එක්කරන උපසර්ගය?", listOf("un-", "mis-", "dis-", "in-"), listOf("un-", "mis- (misunderstand)", "dis-", "in-"), 1)
    }

    // GRADE 10
    private fun getGrade10Stage1(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("Complete conditional type 3: 'If you had arrived on time, you ______ missed the bus.'", "Type 3 කොන්දේසිය සම්පූර්ණ කරන්න: 'If you had arrived on time, you ______ missed the bus.'", listOf("would not have", "will not have", "would not", "had not"), listOf("would not have (would not have + PP)", "will not have", "would not", "had not"), 0)
        1 -> Pentuple("What are modal perfects used to describe usually?", "Modal Perfects (should have, must have) සාමාන්‍යයෙන් යොදාගන්නේ කුමටද?", listOf("Present continuous habits", "Past possibilities or regrets", "Future predictions", "General timeless truths"), listOf("වර්තමාන පුරුදු දැක්වීමට", "අතීතයේ වියදම් වූ දේ හෝ පසුතැවීම් (Possibility/Regrets)", "අනාගත අනාවැකි", "සදාකාලික සත්‍යයන්"), 1)
        2 -> Pentuple("Choose the correct modal perfect: 'He scored zero. He ______ studied at all.'", "නිවැරදි Modal Perfect එක තෝරන්න: 'He scored zero. He ______ studied at all.'", listOf("must have", "should have", "cannot have (can't have)", "might have"), listOf("must have", "should have", "cannot have (කිසිසේත්ම පාඩම් කර නැත)", "might have"), 2)
        3 -> Pentuple("What noun clause is representing the subject in: 'What you said is true.'?", "මෙම වාක්‍යයේ ඇති නාමපද වාක්‍ය ඛණ්ඩය (Noun Clause) කුමක්ද?", listOf("is true", "What you said", "said is true", "true"), listOf("is true", "What you said (ඔබ පැවසූ දේ)", "said is true", "true"), 1)
        4 -> Pentuple("Complete: 'She suggested that he ______ the doctor immediately.'", "හිස්තැන පුරවන්න: 'She suggested that he ______ the doctor immediately.'", listOf("visit", "visits", "visited", "should visited"), listOf("visit (subjunctive bare infinitive)", "visits", "visited", "should visited"), 0)
        5 -> Pentuple("What is the passive voice: 'They say that garlic is good for health.'?", "'They say that garlic is good for health' යන්නෙහි passive voice?", listOf("Garlic is said to be good for health.", "It was said garlic is good.", "Garlic was said to be good.", "Garlic is say to be good."), listOf("Garlic is said to be...", "It was said garlic is good.", "Garlic was said to be good.", "Garlic is say to be good."), 0)
        6 -> Pentuple("Reported speech of: She asked, 'Are you going to town tomorrow?'", "Reported speech: She asked, 'Are you going to town tomorrow?'", listOf("She asked me if I was going to town the next day.", "She asked that I am going to town tomorrow.", "She asked if I am going to town tomorrow.", "She asked me were I going to town."), listOf("She asked me if I was... (was going + next day)", "She asked that I am going...", "She asked if I am going...", "She asked me were I going to town."), 0)
        7 -> Pentuple("Complete: 'I wish I ______ my exams last year.'", "හිස්තැන පුරවන්න: 'I wish I ______ my exams last year.'", listOf("pass", "passed", "had passed", "have passed"), listOf("pass", "passed", "had passed (Past perfect regret of past)", "have passed"), 2)
        8 -> Pentuple("What relative pronoun is used to show ownership?", "අයිතිවාසිකමක් හෝ හිමිකමක් හැඳින්වීමට ගන්නා සර්වනාමය කුමක්ද?", listOf("who", "whose", "whose's", "whom"), listOf("කවුද", "whose (කාගේද / කාට අයත්)", "whose's", "කාටද"), 1)
        else -> Pentuple("Identify the correct collective noun for lions:", "සිංහයන් රැළක් හැඳින්වීම සඳහා ගන්නා නිවැරදි නාම පදය කුමක්ද?", listOf("Herd", "Pride", "Pack", "Flock"), listOf("රැළ (සිව්පා)", "Pride (සිංහ රැළ)", "පැක්", "කුරුලු රැළ"), 1)
    }

    private fun getGrade10Stage2(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("Which idiom means 'very expensive'?", "'ඉතා මිල අධික' යන්න හැඟවීමට යොදාගන්නා ඉංග්‍රීසි උපහැරණය?", listOf("Cost an arm and a leg", "A piece of cake", "Over the moon", "Under the weather"), listOf("කකුලක් සහ අතක් වටිනා (Cost an arm and leg)", "කේක් කැබැල්ලක්", "හඳෙන් එහා", "කාලගුණය යටතේ"), 0)
        1 -> Pentuple("Complete modal perfect: 'You ______ driven so fast. The road was wet.'", "හිස්තැන පුරවන්න: 'You ______ driven so fast. The road was wet.'", listOf("should have", "should not have", "must have", "might have"), listOf("යුතුය", "should not have (විශාල වැරදීමක් / පසුතැවීමක්)", "විය යුතුය", "සමහරවිට"), 1)
        2 -> Pentuple("What is the noun clause in: 'I believe that he will pass.'?", "මෙම වාක්‍යයේ Noun Clause එක කුමක්ද: 'I believe that he will pass.'", listOf("I believe", "that he will pass", "believe that", "will pass"), listOf("I believe", "that he will pass (ඔහු සමත් වන බව)", "believe that", "will pass"), 1)
        3 -> Pentuple("Complete: 'Hardly had I arrived at the station ______ the train left.'", "හිස්තැන පුරවන්න: 'Hardly had I arrived at the station ______ the train left.'", listOf("than", "when / before", "then", "since"), listOf("වඩා", "when / before (Hardly had සමඟ)", "එවිට", "සිට"), 1)
        4 -> Pentuple("Which spelling is correct for writing an official request for supplies?", "නිල වශයෙන් සැපයුම් ඉල්ලන ලියවිල්ලක නිවැරදි ඉංග්‍රීසි අක්ෂර වින්‍යාසය?", listOf("Requisision", "Requisition", "Requisetion", "Requasition"), listOf("Requisision", "Requisition (නිල සැපයුම් ඉල්ලුම් පත)", "Requisetion", "Requasition"), 1)
        5 -> Pentuple("What is the passive form of: 'They are playing cricket'?", "'They are playing cricket' යන්නෙහි passive voice?", listOf("Cricket is played by them.", "Cricket is being played by them.", "Cricket was being played by them.", "Cricket has been played by them."), listOf("Cricket is played...", "Cricket is being played... (is being + played)", "Cricket was being...", "Cricket has been..."), 1)
        6 -> Pentuple("Complete type 3: 'If I had studied, I ______ passed.'", "Type 3 කොන්දේසිය සම්පූර්ණ කරන්න: 'If I had studied, I ______ passed.'", listOf("will have", "would have", "would", "had had"), listOf("will have", "would have (would have + pp)", "would", "had had"), 1)
        7 -> Pentuple("Choose the antonym of 'meticulous' (being extremely careful):", "'Meticulous' (ඉතා සැලකිලිමත්) වචනයේ ප්‍රතිවිරුද්ධ වචනය කුමක්ද?", listOf("Careful", "Careless / Negligent", "Accurate", "Diligent"), listOf("සැලකිලිමත්", "Careless/Negligent (නොසැලකිලිමත්)", "නිවැරදි", "උත්සාහවන්ත"), 1)
        8 -> Pentuple("What does the idiom 'cry over spilled milk' mean?", "'cry over spilled milk' යන උපහැරණයේ තේරුම කුමක්ද?", listOf("To be happy about future", "To cry when hurt", "To complain about a past mistake that cannot be changed", "To clean milk"), listOf("අනාගතය ගැන සතුටු වීම", "රිදුණු විට ඇඬීම", "වෙනස් කළ නොහැකි අතීත වැරදි ගැන පසුතැවීම", "කිරි පිරිසිදු කිරීම"), 2)
        else -> Pentuple("Identify the adverb clause of time in: 'We will leave when the rain stops.'", "කාලය දක්වන ක්‍රියා විශේෂණ වාක්‍ය ඛණ්ඩය කුමක්ද?", listOf("We will leave", "when the rain stops", "will leave when", "rain stops"), listOf("We will leave", "when the rain stops (වැස්ස නැවතුණු විට)", "will leave when", "rain stops"), 1)
    }

    private fun getGrade10Stage3(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("Complete conditional with inversion: 'Had I known you were ill, I ______ visited you.'", "Had I known සමඟ කොන්දේසිය සම්පූර්ණ කරන්න:", listOf("will have", "would have", "would had", "should"), listOf("will have", "would have (Had inverted Type 3)", "would had", "should"), 1)
        1 -> Pentuple("What is the meaning of the idiom 'burn the midnight oil'?", "'burn the midnight oil' යන උපහැරණයේ තේරුම කුමක්ද?", listOf("To waste oil", "To study or work late into the night", "To light candles", "To sleep early"), listOf("තෙල් නාස්ති කිරීම", "රෑ බෝවන තෙක් වැඩ හෝ පාඩම් කිරීම (Work late)", "ඉටිපන්දම් දැල්වීම", "වේලාසනින් නිදාගැනීම"), 1)
        2 -> Pentuple("Complete the subjunctive: 'It is vital that he ______ present.'", "Subjunctive අවස්ථාව සම්පූර්ණ කරන්න: 'It is vital that he ______ present.'", listOf("is", "be", "was", "were"), listOf("is", "be (vital that + bare subjunctive)", "was", "were"), 1)
        3 -> Pentuple("What is the passive form of: 'He recommended changing the policy.'?", "'He recommended changing the policy' යන්නෙහි passive voice?", listOf("He recommended that the policy should be changed.", "He recommended the policy is changed.", "The policy recommended changed.", "He recommended to write policy."), listOf("He recommended that the policy should be changed. (should be + pp)", "He recommended the policy is changed.", "The policy recommended changed.", "He recommended to write policy."), 0)
        4 -> Pentuple("Identify the noun clause acting as direct object: 'I do not know where she lives.'", "I do not know where she lives වාක්‍යයේ Noun Clause එක කුමක්ද?", listOf("I do not know", "where she lives", "know where", "lives"), listOf("I do not know", "where she lives (ඇය කොහේ ජීවත් වන්නේදැයි)", "know where", "lives"), 1)
        5 -> Pentuple("Complete: 'No sooner had they started their journey ______ it started pouring.'", "හිස්තැන පුරවන්න: 'No sooner had they started their journey ______ it started pouring.'", listOf("when", "than", "then", "before"), listOf("when", "than (No sooner had සමඟ than යෙදේ)", "then", "before"), 1)
        6 -> Pentuple("What spelling represents a complete list of items in an exhibition?", "ප්‍රදර්ශනයක ඇති භාණ්ඩවල සම්පූර්ණ නාම ලේඛනයේ නිවැරදි අක්ෂර වින්‍යාසය?", listOf("Catalogue", "Cataloge", "Cetalogue", "Cataloug"), listOf("Catalogue (නාමාවලිය)", "Cataloge", "Cetalogue", "Cataloug"), 0)
        7 -> Pentuple("Reported speech of: He said, 'If I were you, I would study.'", "Reported speech: He said, 'If I were you, I would study.'", listOf("He said that if he was me, he would study.", "He advised me to study.", "He said he would study.", "He told me if he is me, he would study."), listOf("He said that...", "He advised me to study. (උපදෙස් දීමක් ලෙස - advised)", "He said he would study.", "He told me if he is me..."), 1)
        8 -> Pentuple("What is the comparative form of the adjective 'pleasant'?", "'Pleasant' (ප්‍රසන්න) වචනයේ සංසන්දනාත්මක අවස්ථාව කුමක්ද?", listOf("pleasantier", "more pleasant", "most pleasant", "pleasantest"), listOf("pleasantier", "more pleasant (වඩා ප්‍රසන්න)", "most pleasant", "pleasantest"), 1)
        else -> Pentuple("Choose the meaning of the idiom 'piece of cake':", "'piece of cake' යන්නෙන් අදහස් කරන්නේ කුමක්ද?", listOf("Very sweet", "Very easy task", "Heavy work", "Unpleasant food"), listOf("ඉතා පැණිරස", "ඉතා පහසු කාර්යයක් (Very easy task)", "බර වැඩ", "අප්‍රසන්න කෑම"), 1)
    }
}
