package com.example.data

object ScienceQuestions {

    fun getQuestions(grade: Int, stage: Int): List<Question> {
        val list = mutableListOf<Question>()
        for (i in 0..9) {
            list.add(getSingleQuestion(grade, stage, i))
        }
        return list
    }

    private fun getSingleQuestion(grade: Int, stage: Int, index: Int): Question {
        val qid = "g${grade}_Science_s${stage}_q${index}"
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

        return Question(
            id = qid,
            question = p.question,
            questionSinhala = p.questionSinhala,
            options = p.options,
            optionsSinhala = p.optionsSinhala,
            correctAnswer = p.correctAnswer,
            grade = grade,
            subject = "Science",
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

    // GRADE 1
    private fun getGrade1Stage1(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("Which of the following is a living thing?", "පහත දැක්වෙන දේවලින් පණ ඇති (සජීවී) දෙයක් වන්නේ කුමක්ද?", listOf("Stone", "Plant", "Toy Car", "Book"), listOf("ගලක්", "පැලෑටියක්", "සෙල්ලම් කාර් එකක්", "පොතක්"), 1)
        1 -> Pentuple("Which of these cannot grow?", "පහත දැක්වෙන දේවලින් වර්ධනය විය නොහැක්කේ කුමකින්ද?", listOf("Kitten", "Baby", "Plastic Doll", "Tree"), listOf("පූස් පැටියා", "බිළිඳා", "ප්ලාස්ටික් බෝනික්කා", "ගස"), 2)
        2 -> Pentuple("What is a dog?", "බල්ලෙකු අයත් වන්නේ කුමන කාණ්ඩයටද?", listOf("An animal", "A plant", "A stone", "A vehicle"), listOf("සතෙක්", "ශාකයක්", "ගලක්", "වාහනයක්"), 0)
        3 -> Pentuple("Pick a non-living thing from here:", "මෙහි ඇති අජීවී (පණ නැති) දෙය තෝරන්න:", listOf("Cat", "Fish", "Pencil", "Banyan tree"), listOf("බළලා", "මසෙක්", "පැන්සල", "නුග ගස"), 2)
        4 -> Pentuple("Which part of our body is a sense organ?", "අපගේ ශරීරයේ ඇති ඉන්ද්‍රියක් වන්නේ කුමක්ද?", listOf("Eye", "Hair", "Nail", "Bone"), listOf("ඇස", "කෙස්", "නිය", "කටුව"), 0)
        5 -> Pentuple("Which structure is found on most plants?", "බොහෝ ශාකවල දැකිය හැකි කොටසක් කුමක්ද?", listOf("Feather", "Green leaf", "Tail", "Beak"), listOf("පිහාටුව", "කොළ පැහැති පත්‍රය", "වලිගය", "හොටය"), 1)
        6 -> Pentuple("Which animal has wings and can fly?", "පියාපත් ඇති, පියාසර කළ හැකි සත්වයා කවුද?", listOf("Dog", "Frog", "Parrot", "Fish"), listOf("බල්ලා", "ගෙම්බා", "ගිරවා", "මසෙක්"), 2)
        7 -> Pentuple("Water we drink is a:", "අප පානය කරන ජලය යනු කුමක්ද?", listOf("Living thing", "Non-living thing", "Plant", "Animal"), listOf("සජීවී දෙයක්", "අජීවී දෙයක්", "ශාකයක්", "සතෙක්"), 1)
        8 -> Pentuple("Which of these has no life?", "පහත ඒවායින් ජීවයක් නොමැති දෙය කුමක්ද?", listOf("Bird", "Earthworm", "Bicycle", "Rose bush"), listOf("කුරුල්ලා", "ගැඩවිලා", "පාපැදිය", "රෝස පඳුර"), 2)
        else -> Pentuple("What runs on roads and is non-living?", "පාරේ ධාවනය වන, ජීවයක් නොමැති දෙයක් කුමක්ද?", listOf("Horse", "Bus", "Snail", "Frog"), listOf("අශ්වයා", "බස් රථය", "ගොළුබෙල්ලා", "ගෙම්බා"), 1)
    }

    private fun getGrade1Stage2(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("Which sense organ helps us to hear music?", "සංගීතය ඇසීමට උපකාරී වන්නේ කුමන ඉන්ද්‍රියද?", listOf("Eye", "Ear", "Nose", "Tongue"), listOf("ඇස", "කන", "නාසය", "දිව"), 1)
        1 -> Pentuple("Which organ do we use to taste ice cream?", "අයිස්ක්‍රීම් රස බැලීමට අප භාවිතා කරන්නේ කුමන ඉන්ද්‍රියද?", listOf("Skin", "Nose", "Tongue", "Ear"), listOf("සම", "නාසය", "දිව", "කන"), 2)
        2 -> Pentuple("What is the main function of our eyes?", "අපගේ ඇස්වල ප්‍රධාන කාර්යය කුමක්ද?", listOf("To hear", "To see", "To smell", "To run"), listOf("ඇසීම", "පෙනීම", "ඉව කිරීම", "දිවීම"), 1)
        3 -> Pentuple("Which organ helps us to smell flower perfume?", "මල් සුවඳ දැන ගැනීමට අපට උපකාර වන ඉන්ද්‍රිය කුමක්ද?", listOf("Eye", "Nose", "Ear", "Skin"), listOf("ඇස", "නාසය", "කන", "සම"), 1)
        4 -> Pentuple("Which organ covers our whole body and feels touch?", "අපගේ මුළු ශරීරයම ආවරණය කරමින් ස්පර්ශය දැනීමට සලස්වන ඉන්ද්‍රිය?", listOf("Skin", "Nose", "Ear", "Tongue"), listOf("සම", "නාසය", "කන", "දිව"), 0)
        5 -> Pentuple("We should brush our teeth how many times a day?", "අප දිනකට කී වතාවක් දත් මැදිය යුතුද?", listOf("Never", "Once a week", "At least twice", "Every hour"), listOf("කිසිවිටක නෙවේ", "සතියට වරක්", "අවම දෙවරක්", "සෑම පැයකදීම"), 2)
        6 -> Pentuple("Which structure do birds use to eat food?", "කුරුල්ලන් ආහාර ගැනීමට භාවිතා කරන ශරීර කොටස කුමක්ද?", listOf("Beak", "Teeth", "Hands", "Tail"), listOf("හොටය", "දත්", "අත්", "වලිගය"), 0)
        7 -> Pentuple("Which animal lives in water and swims with fins?", "ජලයේ ජීවත් වන, වරල් මඟින් පිහිනන සත්වයා කවුද?", listOf("Cat", "Fish", "Squirrel", "Ant"), listOf("බළලා", "මසෙක්", "ලේනා", "කූඹියා"), 1)
        8 -> Pentuple("What is the color of the clear sky during a sunny day?", "හිරු පායා ඇති පැහැදිලි දවසක අහසේ පැහැය කුමක්ද?", listOf("Red", "Blue", "Black", "Green"), listOf("රතු", "නිල්", "කළු", "කොළ"), 1)
        else -> Pentuple("Which animal makes a 'meow' sound?", "'මියව්' හඬ නගන සත්වයා කවුද?", listOf("Dog", "Cat", "Cow", "Crow"), listOf("බල්ලා", "බළලා", "එළදෙන", "කපුටා"), 1)
    }

    private fun getGrade1Stage3(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("What must we use to protect ourselves when it rains?", "වැසි වසින විට ආරක්ෂා වීමට අප භාවිතා කළ යුත්තේ කුමක්ද?", listOf("Hat", "Sunglasses", "Umbrella", "Socks"), listOf("තොප්පියක්", "අව් කණ්ණාඩි", "කුඩයක්", "මේස් යුගලක්"), 2)
        1 -> Pentuple("What gives us light and heat during the daytime?", "දිවා කාලයේ අපට ආලෝකය සහ තාපය ලබා දෙන්නේ කුමකින්ද?", listOf("Moon", "Stars", "Sun", "Candle"), listOf("හඳ", "තරු", "හිරු", "ඉටිපන්දම"), 2)
        2 -> Pentuple("Where do wild animals like leopards and elephants live?", "දිවියන් සහ අලි වැනි කැලෑ සතුන් ස්වභාවිකව ජීවත් වන්නේ කොහේද?", listOf("In houses", "In the forest", "In the sea", "In schools"), listOf("නිවාසවල", "වනාන්තරයේ", "මුහුදේ", "පාසල්වල"), 1)
        3 -> Pentuple("Which of these is a seasonal fruit popular in Sri Lanka?", "ශ්‍රී ලංකාවේ ජනප්‍රිය වාර අනුව හැදෙන පලතුරක් වන්නේ කුමකින්ද?", listOf("Apple", "Rambutan", "Grape", "Plum"), listOf("ඇපල්", "රඹුටන්", "මිදි", "ප්ලම්ස්"), 1)
        4 -> Pentuple("What happens to water if we freeze it in a refrigerator?", "ශීතකරණයක දමා අයිස් සෑදූවිට ජලයට කුමක් සිදුවේද?", listOf("Becomes gas", "Becomes solid ice", "Disappears", "Heats up"), listOf("වායුවක් වේ", "ඝන අයිස් බවට පත් වේ", "නැතිවී යයි", "රත් වේ"), 1)
        5 -> Pentuple("Which animal has a trunk and large ears?", "හොඬවැලක් සහ විශාල කන් ඇති සත්වයා කවුද?", listOf("Lion", "Giraffe", "Elephant", "Deer"), listOf("සිංහයා", "ජිරාෆ්", "අලියා", "මුවා"), 2)
        6 -> Pentuple("Which plant leaf is commonly used to make sambol in Sri Lanka?", "ශ්‍රී ලංකාවේ පොල් සම්බෝල සමඟ සෑදීමට ගන්නා ඖෂධීය පත්‍රය කුමක්ද?", listOf("Rose leaf", "Gotukola", "Mango leaf", "Coconut leaf"), listOf("රෝස පත්‍ර", "ගොටුකොළ", "අඹ පත්‍ර", "පොල් අතු"), 1)
        7 -> Pentuple("Which of these animals crawls on the ground slowly?", "පොළොව මත ඉතා සෙමින් බඩගා යන සත්වයා කවුද?", listOf("Cheetah", "Snail", "Bird", "Monkey"), listOf("දිවියා", "ගොළුබෙල්ලා", "කුරුල්ලා", "වඳුරා"), 1)
        8 -> Pentuple("What should we do before eating food?", "ආහාර ගැනීමට පෙර අප අනිවාර්යයෙන්ම කළ යුත්තේ කුමක්ද?", listOf("Sleep", "Wash hands", "Play with dust", "Run"), listOf("නිදාගැනීම", "අත් සේදීම", "පස් සමඟ සෙල්ලම් කිරීම", "දිවීම"), 1)
        else -> Pentuple("Which part of the plant grows below the soil?", "ශාකයක පස ඇතුළත පහළට වර්ධනය වන කොටස කුමක්ද?", listOf("Stem", "Leaf", "Flower", "Root"), listOf("කඳ", "කොළය", "මල", "මුල"), 3)
    }

    // GRADE 2
    private fun getGrade2Stage1(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("What hard bone structures do we use to chew food?", "ආහාර හැපීම සඳහා අප භාවිතා කරන ශරීරයේ ඇති තද අස්ථි කොටස් මොනවාද?", listOf("Nails", "Teeth", "Fingers", "Tongue"), listOf("නියපොතු", "දත්", "ඇඟිලි", "දිව"), 1)
        1 -> Pentuple("Which body part connects our head to our shoulders?", "අපගේ හිස උරහිසට සම්බන්ධ කරන ශරීර කොටස කුමක්ද?", listOf("Leg", "Neck", "Elbow", "Knee"), listOf("කකුල", "බෙල්ල", "වැලමිට", "දණහිස"), 1)
        2 -> Pentuple("How many lungs does a healthy human body have?", "නිරෝගී මිනිස් සිරුරක පෙනහැලි කීයක් තිබේද?", listOf("1", "2", "3", "4"), listOf("1", "2", "3", "4"), 1)
        3 -> Pentuple("What liquid inside our body is red and pumped by the heart?", "අපගේ ශරීරය පුරා ඇති හදවත මඟින් පොම්ප කරන රතු පැහැති ද්‍රවය කුමක්ද?", listOf("Water", "Saliva", "Blood", "Bile"), listOf("ජලය", "කෙළ", "ලේ (රුධිරය)", "පිත"), 2)
        4 -> Pentuple("Which organ in our chest beats and pumps blood?", "ළරෙහි පිහිටා ඇති, රුධිරය පොම්ප කරන ඉන්ද්‍රිය කුමක්ද?", listOf("Lungs", "Brain", "Heart", "Stomach"), listOf("පෙනහැලි", "මොළය", "හෘදය (හදවත)", "ආමාශය"), 2)
        5 -> Pentuple("We use our knees to bend which part of our body?", "අපගේ දණහිස නැමීමට භාවිතා කරන්නේ ශරීරයේ කුමන කොටසද?", listOf("Arms", "Legs", "Neck", "Fingers"), listOf("අත්", "කකුල්", "බෙල්ල", "ඇඟිලි"), 1)
        6 -> Pentuple("What protects the brain inside our head?", "අපගේ හිස ඇතුළත ඇති මොළය ආරක්ෂා කරන්නේ කුමකින්ද?", listOf("Ribs", "Skull (head bone)", "Skin", "Hair"), listOf("ඉළඇට", "කபாலය (හිස්කබල)", "සම", "කෙස්"), 1)
        7 -> Pentuple("Which body part is used to hold a pencil to write?", "ලියා තැබීමට පැන්සලක් අල්ලා ගැනීමට ගන්නා ශරීර කොටස?", listOf("Foot", "Hand/Fingers", "Toe", "Elbow"), listOf("යටි පතුල", "අත / ඇඟිලි", "පා ඇඟිල්ල", "වැලමිට"), 1)
        8 -> Pentuple("What are the soft parts covering our bones called?", "අපගේ ඇටකටු වසා ඇති මෘදු කොටස් හඳුන්වන්නේ කුමක් ලෙසද?", listOf("Skin", "Muscles", "Nails", "Hair"), listOf("සම", "පේශි (මස්පිඬු)", "නිය", "කෙස්"), 1)
        else -> Pentuple("What is the main organ of thinking located in our head?", "හිස තුළ පිහිටා ඇති, සිතීමට උපකාර වන ප්‍රධාන ඉන්ද්‍රිය කුමක්ද?", listOf("Heart", "Stomach", "Lungs", "Brain"), listOf("හෘදය", "ආමාශය", "පෙනහැලි", "මොළය"), 3)
    }

    private fun getGrade2Stage2(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("Which of the following is healthy food?", "පහත දැක්වෙන දේවලින් සෞඛ්‍යයට හිතකර කෑම කුමක්ද?", listOf("Fizzy drinks", "Sweets", "Fresh vegetables", "French fries"), listOf("පැණිබීම", "පැණිරස රසකැවිලි", "නැවුම් එළවළු", "අල පෙති"), 2)
        1 -> Pentuple("Why does our body need water?", "අපගේ ශරීරයට ජලය අවශ්‍ය වන්නේ ඇයි?", listOf("To make us blue", "To stay hydrated and alive", "To sleep better", "None"), listOf("නිල් පැහැ වීමට", "නිරෝගීව හා ජීවත්ව සිටීමට", "සුව නින්දකට", "කිසිවක් නොවේ"), 1)
        2 -> Pentuple("Which gas in the air do we breathe in to survive?", "ජීවත්වීම සඳහා අප වාතයෙන් ආශ්වාස කරන වායුව කුමක්ද?", listOf("Carbon dioxide", "Oxygen", "Nitrogen", "Methane"), listOf("කාබන් ඩයොක්සයිඩ්", "ඔක්සිජන්", "නයිට්‍රජන්", "මෙතේන්"), 1)
        3 -> Pentuple("What is the main source of natural light on Earth?", "පෘථිවියට ලැබෙන ස්වභාවික ආලෝකයේ ප්‍රධාන ප්‍රභවය කුමක්ද?", listOf("Bulb", "Flashlight", "Sun", "Fire"), listOf("බල්බය", "විදුලි පන්දම", "හිරු", "ගින්දර"), 2)
        4 -> Pentuple("Which food gives us vitamins and minerals?", "අපට විටමින් සහ ඛනිජ ලවණ ලබා දෙන ආහාර කාණ්ඩය කුමක්ද?", listOf("Sugar", "Rice", "Fruits and Vegetables", "Butter"), listOf("සීනි", "බත්", "පලතුරු සහ එළවළු", "බටර්"), 2)
        5 -> Pentuple("Where does rain water come from?", "වැසි ජලය ලැබෙන්නේ කොහෙන්ද?", listOf("Underground", "Seas directly", "Clouds", "Rivers"), listOf("පොළොව යටින්", "මුහුදෙන් කෙලින්ම", "වලාකුළු වලින්", "ගංගා වලින්"), 2)
        6 -> Pentuple("What is moving air called?", "චලනය වන වාතය හඳුන්වන්නේ කුමක් ලෙසද?", listOf("Water", "Wind", "Soil", "Heat"), listOf("ජලය", "සුළඟ", "පස", "තාපය"), 1)
        7 -> Pentuple("Which of these is a liquid we drink daily?", "අප දිනපතා පානය කරන ද්‍රවයක් වන්නේ මෙයින් කුමක්ද?", listOf("Oil", "Water", "Petrol", "Paint"), listOf("තෙල්", "ජලය", "පෙට්‍රල්", "තීන්ත"), 1)
        8 -> Pentuple("Why should we wash fruits before eating?", "ආහාරයට ගැනීමට පෙර පලතුරු සේදිය යුත්තේ ඇයි?", listOf("To make them sweeter", "To remove germs and dirt", "To change their color", "To dry them"), listOf("පැණිරස කිරීමට", "විෂබීජ සහ කුණු ඉවත් කිරීමට", "පැහැය වෙනස් කිරීමට", "වියලීමට"), 1)
        else -> Pentuple("Which food is rich in protein and obtained from chickens?", "කුකුළන්ගෙන් ලබාගන්නා ප්‍රෝටීන් බහුල ආහාරය කුමක්ද?", listOf("Bread", "Eggs", "Apple", "Rice"), listOf("පාන්", "බිත්තර", "ඇපල්", "බත්"), 1)
    }

    private fun getGrade2Stage3(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("Which material is used to make a rain jacket because it is waterproof?", "දිය නොදකින (waterproof) නිසා වැහි කබා සෑදීමට යොදාගන්නා ද්‍රව්‍යය කුමක්ද?", listOf("Paper", "Cotton", "Plastic/Rubber", "Wood"), listOf("කඩදාසි", "කපු", "ප්ලාස්ටික් / රබර්", "ලී"), 2)
        1 -> Pentuple("Why is iron used to build bridges?", "පාලම් තැනීම සඳහා යකඩ භාවිතා කරන්නේ ඇයි?", listOf("It is soft", "It is light", "It is very strong and hard", "It floats"), listOf("එය මෘදුය", "එය සැහැල්ලුය", "එය ඉතා ශක්තිමත් හා තද බැවින්", "එය පාවෙන බැවින්"), 2)
        2 -> Pentuple("Which object is made of glass because it is transparent?", "ආලෝකය විනිවිද පෙනෙන (transparent) බැවින් වීදුරු වලින් හදන උපකරණය කුමක්ද?", listOf("Key", "Window pane", "Spoon", "Blanket"), listOf("යටතුර", "ජනේල වීදුරුව", "හැන්ද", "වැරදීම"), 1)
        3 -> Pentuple("What material can a magnet attract?", "චුම්භකයකට ඇද ගත හැක්කේ කුමන ද්‍රව්‍යයද?", listOf("Plastic", "Paper", "Iron", "Wood"), listOf("ප්ලාස්ටික්", "කඩදාසි", "යකඩ", "ලී"), 2)
        4 -> Pentuple("Which material is soft and used to make clothing?", "ඇඳුම් සෑදීමට යොදා ගන්නා මෘදු ද්‍රව්‍යය කුමක්ද?", listOf("Stone", "Glass", "Fabric/Cotton", "Metal"), listOf("ගල්", "වීදුරු", "රෙදි / කපු", "ලෝහ"), 2)
        5 -> Pentuple("What makes rubber suitable for making tyres?", "ටයර් සෑදීම සඳහා රබර් යෝග්‍ය වීමට හේතුව එහි ඇති කුමන ලක්ෂණයද?", listOf("Brittleness", "Elasticity and flexibility", "Transparency", "Solubility"), listOf("භංගුර බව", "ප්‍රත්‍යාස්ථතාව සහ නම්‍යශීලී බව", "විනිවිද පෙනෙන බව", "ද්‍රාව්‍යතාව"), 1)
        6 -> Pentuple("Which material stretches easily and springs back?", "පහසුවෙන් ඇදීමට සහ නැවත තිබූ තත්ත්වයට පත්වීමට හැකි ද්‍රව්‍යය කුමක්ද?", listOf("Clay", "Wood", "Rubber band", "Glass"), listOf("මැටි", "ලී", "රබර් පටිය", "වීදුරු"), 2)
        7 -> Pentuple("A clay pot is made of clay because it can be:", "මැටි බඳුනක් මැටිවලින් සාදන්නේ මැටිවල ඇති කුමන ලක්ෂණය නිසාද?", listOf("Stretched", "Shaped when wet and baked", "Transparent", "Dissolved in water"), listOf("ඇදිය හැකි බැවින්", "තෙත් කර හැඩගස්වා පුලුස්සා ගත හැකි බැවින්", "විනිවිද පෙනෙන බැවින්", "දෙහි දියවන බැවින්"), 1)
        8 -> Pentuple("Which property of wood makes it good for making tables?", "ලී මේසයක් සෑදීමට ලී සුදුසු වීමට හේතුව එහි ඇති කුමන ගුණයද?", listOf("Softness", "Rigidity and strength", "Water solubility", "Transparency"), listOf("මෘදු බව", "දෘඪතාව සහ ශක්තිය", "ජල ද්‍රාව්‍යතාව", "විනිවිද පෙනෙන බව"), 1)
        else -> Pentuple("Paper is made from which natural source?", "කඩදාසි නිපදවනු ලබන්නේ කුමන ස්වභාවික ප්‍රභවයෙන්ද?", listOf("Rocks", "Trees (wood pulp)", "Crude oil", "Animals"), listOf("පාෂාණ", "ගස් (ලී පල්ප්)", "ඛනිජ තෙල්", "සතුන්"), 1)
    }

    // GRADE 3
    private fun getGrade3Stage1(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("What green pigment in leaves helps plants trap sunlight?", "සූර්යාලෝකය උරා ගැනීමට ශාක පත්‍රවල ඇති කොළ පැහැති වර්ණකය කුමක්ද?", listOf("Water", "Chlorophyll", "Oxygen", "Sap"), listOf("ජලය", "ක්ලෝරොෆිල්", "ඔක්සිජන්", "ශාක යුෂ"), 1)
        1 -> Pentuple("What process do plants use to make their own food?", "ශාක තමන්ට අවශ්‍ය ආහාර නිෂ්පාදනය කරන ක්‍රියාවලිය කුමක්ද?", listOf("Respiration", "Photosynthesis", "Transpiration", "Absorption"), listOf("ශ්වසනය", "ප්‍රභාසංශ්ලේෂණය", "උත්ස්වේදනය", "අවශෝෂණය"), 1)
        2 -> Pentuple("Which of the following is required for photosynthesis?", "ප්‍රභාසංශ්ලේෂණය සඳහා ශාකයට අත්‍යවශ්‍ය වන්නේ කුමක්ද?", listOf("Salt", "Sunlight", "Sugar", "Soil only"), listOf("ලුණු", "සූර්යාලෝකය", "සීනි", "පස පමණක්"), 1)
        3 -> Pentuple("What gas do plants release into the air during food making?", "ආහාර නිපදවීමේදී ශාක මඟින් වාතයට මුදා හරින වායුව කුමක්ද?", listOf("Nitrogen", "Carbon dioxide", "Oxygen", "Helium"), listOf("නයිට්‍රජන්", "කාබන් ඩයොක්සයිඩ්", "ඔක්සිජන්", "හීලියම්"), 2)
        4 -> Pentuple("Which part of the plant absorbs water from the soil?", "පසෙන් ජලය අවශෝෂණය කරගනු ලබන්නේ ශාකයේ කුමන කොටසද?", listOf("Leaves", "Flower", "Root", "Stem"), listOf("පත්‍ර", "මල", "මුල", "කඳ"), 2)
        5 -> Pentuple("Which of these is a non-green plant that cannot make its own food?", "තමන්ටම ආහාර සාදාගත නොහැකි කොළ පැහැති නොවන ශාකයක් (දිලීරයක්) කුමක්ද?", listOf("Rose", "Coconut", "Mushroom", "Grass"), listOf("රෝස", "පොල්", "හතු (Mushroom)", "තණකොළ"), 2)
        6 -> Pentuple("What is the main sugar manufactured by plants during photosynthesis?", "ප්‍රභාසංශ්ලේෂණයේදී ශාකය නිපදවන ප්‍රධාන සීනි වර්ගය (ආහාරය) කුමක්ද?", listOf("Glucose", "Starch", "Salt", "Sucrose"), listOf("ග්ලූකෝස්", "පිෂ්ඨය", "ලුණු", "සුක්‍රෝස්"), 0)
        7 -> Pentuple("In which part of most plants does photosynthesis mainly occur?", "සාමාන්‍යයෙන් ශාකයක ප්‍රභාසංශ්ලේෂණය සිදුවන ප්‍රධාන කොටස කුමක්ද?", listOf("Roots", "Stems", "Leaves", "Flowers"), listOf("මුල්", "කඳන්", "පත්‍ර (කොළ)", "මල්"), 2)
        8 -> Pentuple("What gas do plants absorb from the air to perform photosynthesis?", "ප්‍රභාසංශ්ලේෂණය සඳහා ශාක වාතයෙන් ලබා ගන්නා වායුව කුමක්ද?", listOf("Oxygen", "Nitrogen", "Carbon Dioxide", "Hydrogen"), listOf("ඔක්සිජන්", "නයිට්‍රජන්", "කාබන් ඩයොක්සයිඩ්", "හයිඩ්‍රජන්"), 2)
        else -> Pentuple("Where is excess food stored in a carrot plant?", "කැරට් ශාකයේ අතිරික්ත ආහාර ගබඩා කරන්නේ කොහේද?", listOf("Leaves", "Seeds", "Taproot", "Flowers"), listOf("පත්‍රවල", "බීජවල", "මුලෙහි (අලයේ)", "මල්වල"), 2)
    }

    private fun getGrade3Stage2(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("Where does a monkey mainly live?", "වඳුරන්ගේ ප්‍රධාන ජීවන පරිසරය (වාසස්ථානය) කුමක්ද?", listOf("Sheds", "Underground", "In trees (Arboreal)", "In oceans"), listOf("ගාල් වල", "පොළොව යට", "ගස් උඩ (වෘක්ෂීය)", "සාගරවල"), 2)
        1 -> Pentuple("Which animal lives in a nest they build themselves?", "තමන්ම සාදාගත් කූඩු තුළ ජීවත් වන සතුන් කවුද?", listOf("Fish", "Birds", "Earthworms", "Dogs"), listOf("මාළු", "කුරුල්ලන්", "ගැඩවිලුන්", "බල්ලන්"), 1)
        2 -> Pentuple("An earthworm's preferred habitat is:", "ගැඩවිලෙකු ජීවත් වීමට ප්‍රිය කරන පරිසරය කුමක්ද?", listOf("Dry sand", "Moist soil", "High branches", "Clean water"), listOf("වියළි වැලි", "තෙත් පස", "ඉහළ අතු", "පිරිසිදු ජලය"), 1)
        3 -> Pentuple("Which animal habitat is the ocean?", "සාගරය ස්වභාවික වාසස්ථානය කරගත් සත්වයා කවුද?", listOf("Frog", "Rabbit", "Whale", "Butterfly"), listOf("ගෙම්බා", "හාවා", "තල්මසා", "සමනලයා"), 2)
        4 -> Pentuple("Which animal lives in a kennel?", "බල්ලන් ඇති කිරීමට මිනිසා සාදන වාසස්ථානය හඳුන්වන්නේ කුමක්ද?", listOf("Coop", "Stable", "Kennel", "Hive"), listOf("කුඩුව", "ගාල", "Kennel (බල්ලාගේ කූඩුව)", "මී වදය"), 2)
        5 -> Pentuple("An animal that can live both on land and in water is called:", "ගොඩබිම සහ ජලය යන දෙකෙහිම ජීවත් විය හැකි සතුන් හඳුන්වන්නේ:", listOf("Reptile", "Mammal", "Amphibian", "Fish"), listOf("සර්පයන්", "ක්ෂීරපායින්", "උභයජීවීන්", "මාළු"), 2)
        6 -> Pentuple("Where do honeybees live and store honey?", "මී මැස්සන් ජීවත් වෙමින් මී පැණි ගබඩා කරන්නේ කොහේද?", listOf("Under stones", "In beehives", "In water", "Underground burrows"), listOf("ගල් යට", "මී වද වල", "ජලයේ", "පොළොව යට බෙන"), 1)
        7 -> Pentuple("Which animal lives in underground burrows and has long ears?", "පොළොව යට බෙන හාරා ජීවත් වන, දිගු කන් ඇති සත්වයා කවුද?", listOf("Cat", "Rabbit", "Deer", "Squirrel"), listOf("බළලා", "හාවා", "මුවා", "ලේනා"), 1)
        8 -> Pentuple("A frog's eggs are laid where?", "ගෙම්බා බිත්තර දමන්නේ කොහේද?", listOf("On tree leaves", "In water", "Dry soil", "Inside nests"), listOf("ගස්වල කොළ මත", "ජලයේ", "වියළි පසේ", "කූඩු ඇතුළේ"), 1)
        else -> Pentuple("What is a natural habitat of a camel?", "ඔටුවෙකුගේ ස්වභාවික වාසස්ථානය කුමක්ද?", listOf("Rainforest", "Desert", "Arctic ice", "River stream"), listOf("වැසි වනාන්තරය", "කාන්තාරය", "ආක්ටික් අයිස්", "ගංගාව"), 1)
    }

    private fun getGrade3Stage3(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("What force do we use when we pull a drawer out?", "ලාච්චුවක් පිටතට ඇදීමේදී අප යොදන්නා බලය කුමක්ද?", listOf("Push", "Pull", "Friction", "Gravity"), listOf("තල්ලුව", "ඇදීම", "ඝර්ෂණය", "ගුරුත්වාකර්ෂණය"), 1)
        1 -> Pentuple("When you kick a football, you apply which force?", "පාපන්දුවකට පයින් පහර දීමේදී යෙදෙන බලය කුමක්ද?", listOf("Pull", "Push", "Gravity", "Magnetic"), listOf("ඇදීම", "තල්ලුව (Push)", "ගුරුත්වාකර්ෂණය", "චුම්භක"), 1)
        2 -> Pentuple("What forms when an object blocks the path of light?", "ආලෝකය ගමන් කරන මාවතේ ඇති බාධකයක් නිසා සෑදෙන්නේ කුමක්ද?", listOf("Rainbow", "Shadow", "Reflection", "Mirror"), listOf("දේදුන්න", "සෙවනැල්ල (Shadow)", "පරාවර්තනය", "කන්නාඩිය"), 1)
        3 -> Pentuple("What is the primary source of light that creates shadows outdoors?", "එළිමහනේ සෙවනැලි ඇති වීමට බලපාන ප්‍රධාන ස්වභාවික ආලෝක ප්‍රභවය කුමක්ද?", listOf("Moon", "Sun", "Street lamp", "Candle"), listOf("හඳ", "හිරු (Sun)", "වීදි ලාම්පුව", "ඉටිපන්දම"), 1)
        4 -> Pentuple("Light travels in which path way?", "ආලෝකය ගමන් කරන්නේ කුමන ආකාරයේ මාර්ගයකද?", listOf("Curved lines", "Zigzag lines", "Straight lines", "Circles"), listOf("වක්‍ර රේඛා ඔස්සේ", "ඇද කුද රේඛා ඔස්සේ", "සරල රේඛා ඔස්සේ", "වෘත්තාකාරව"), 2)
        5 -> Pentuple("To close a door from the outside, you must:", "දොරක් පිටතින් වැසීමට ඔබ කළ යුත්තේ:", listOf("Pull it", "Push it", "Drop it", "Lift it"), listOf("එය ඇදීම", "එය තල්ලු කිරීම (Push)", "එය අතහැරීම", "එය එසවීම"), 1)
        6 -> Pentuple("Which object allows light to pass through completely?", "ආලෝකය සම්පූර්ණයෙන්ම තමා තුළින් යාමට ඉඩ හරින ද්‍රව්‍යය කුමක්ද?", listOf("Cardboard", "Clear glass", "Wooden door", "Metal sheet"), listOf("කාඩ්බෝඩ්", "පැහැදිලි වීදුරු (Clear glass)", "ලී දොර", "ලෝහ තහඩුව"), 1)
        7 -> Pentuple("Which object does not allow any light to pass, casting a dark shadow?", "ආලෝකය තමා තුළින් යාමට ඉඩ නොහරින, තද සෙවනැල්ලක් සාදන ද්‍රව්‍ය?", listOf("Clear water", "Cellophane sheet", "Thick wood board", "Air"), listOf("පිරිසිදු ජලය", "සෙලෝපේන් කොළය", "ඝන ලී ලෑල්ල", "වාතය"), 2)
        8 -> Pentuple("Where is a shadow cast relative to the light source?", "ආලෝක ප්‍රභවයට සාපේක්ෂව සෙවනැල්ලක් ඇති වන්නේ කුමන දිශාවේද?", listOf("Towards the source", "On the opposite side", "Above the source", "Nowhere"), listOf("ප්‍රභවය දෙසට", "ප්‍රභවයට විරුද්ධ පැත්තේ (opposite side)", "ප්‍රභවයට ඉහළින්", "කොහේවත් නොවේ"), 1)
        else -> Pentuple("What happens to the size of a shadow when you move the object closer to the light source?", "වස්තුවක් ආලෝක ප්‍රභවයට ආසන්න කරන විට සෙවනැල්ලේ ප්‍රමාණයට කුමක් සිදුවේද?", listOf("Becomes smaller", "Becomes larger", "Stays same", "Disappears"), listOf("කුඩා වේ", "විශාල වේ (Becomes larger)", "එලෙසම පවතී", "නැතිවී යයි"), 1)
    }

    // GRADE 4
    private fun getGrade4Stage1(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("Which soil type has the smallest particles and holds water the best?", "කුඩාම අංශු ඇති, ජලය හොඳින්ම රඳවා ගන්නා පස් වර්ගය කුමක්ද?", listOf("Humus", "Sandy soil", "Clay soil", "Loam"), listOf("කොම්පෝස්ට් පස", "වැලි පස", "මැටි පස (Clay soil)", "නිසරු පස"), 2)
        1 -> Pentuple("Which soil contains much sand and has very large spacing particles?", "විශාල වැලි අංශු ඇති, ජලය රඳවා නොගන්නා පස් වර්ගය කුමක්ද?", listOf("Clay soil", "Sandy soil", "Loam", "Peat"), listOf("මැටි පස", "වැලි පස (Sandy soil)", "ලෝම පස", "පීට් පස"), 1)
        2 -> Pentuple("Which soil is dark, rich in nutrients, and best for growing vegetables?", "එළවළු වැවීමට වඩාත්ම සුදුසු, පෝෂක බහුල වගා පස කුමක්ද?", listOf("Clay soil", "Sandy soil", "Loamy soil/Humus", "Silt"), listOf("මැටි පස", "වැලි පස", "ලෝම පස / කොම්පෝස්ට්", "පොඩි පස්"), 2)
        3 -> Pentuple("What is the organic matter formed from decayed plants and animals in soil called?", "පසෙහි ඇති දිරාගිය ශාක හා සත්ව කොටස් වලින් සෑදෙන කාබනික ද්‍රව්‍යය කුමක්ද?", listOf("Sand", "Clay", "Pebbles", "Humus"), listOf("වැලි", "මැටි", "ගල්කැට", "හියුමස් (Humus)"), 3)
        4 -> Pentuple("Which soil is used to make clay pots and bricks?", "මැටි බඳුන් සහ ගඩොල් සෑදීමට ගන්නා පස් වර්ගය කුමක්ද?", listOf("Sandy soil", "Silty soil", "Clay soil", "Gravel"), listOf("වැලි පස", "මඩ පස", "මැටි පස (Clay soil)", "බොරළු පස"), 2)
        5 -> Pentuple("Why is sandy soil not good for growing paddy?", "වී වගාව සඳහා වැලි පස සුදුසු නොවන්නේ ඇයි?", listOf("It is too heavy", "It does not hold water", "It is dark", "It smells bad"), listOf("එය බර වැඩි බැවින්", "එහි ජලය රඳා නොපවතින බැවින්", "එය කළු බැවින්", "එහි ගඳ ගහන බැවින්"), 1)
        6 -> Pentuple("Soil is formed by the slow breakdown of what?", "පස සෑදෙන්නේ කුමක් සෙමින් හායනය වීමෙන්ද?", listOf("Water", "Rocks", "Plastic", "Glass"), listOf("ජලය", "පාෂාණ (Rocks)", "ප්ලාස්ටික්", "වීදුරු"), 1)
        7 -> Pentuple("Which animal lives in the soil and helps aerate it?", "පස ඇතුළත ජීවත් වෙමින් පස බුරුල් කිරීමට උදව් වන සත්වයා කවුද?", listOf("Bird", "Earthworm", "Frog", "Snail"), listOf("කුරුල්ලා", "ගැඩවිලා (Earthworm)", "ගෙම්බා", "ගොළුබෙල්ලා"), 1)
        8 -> Pentuple("What is the top layer of the Earth's surface where plants grow?", "ශාක වර්ධනය වන පෘථිවියේ මතුපිටම පිහිටි ස්ථරය කුමක්ද?", listOf("Core", "Mantle", "Soil/Crust", "Oceans"), listOf("මධ්‍යය", "ප්‍රාවරණය", "පස / කබොල", "සාගර"), 2)
        else -> Pentuple("Which of these can damage soil quality and cause pollution?", "පසේ ගුණාත්මය විනාශ කරමින් පස් දූෂණයට හේතු වන්නේ කුමක්ද?", listOf("Adding compost", "Planting trees", "Dumping plastic wastes", "Watering"), listOf("කොම්පෝස්ට් එක් කිරීම", "ගස් වැවීම", "ප්ලාස්ටික් අපද්‍රව්‍ය දැමීම", "ජලය දැමීම"), 2)
    }

    private fun getGrade4Stage2(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("What is the process where liquid water turns into water vapor?", "ද්‍රව ජලය වාෂ්ප බවට පත්වීමේ ක්‍රියාවලිය කුමක්ද?", listOf("Condensation", "Evaporation", "Precipitation", "Freezing"), listOf("ඝනීභවනය", "වාෂ්පීභවනය (Evaporation)", "අවක්ෂේපණය", "කැටි ගැසීම"), 1)
        1 -> Pentuple("What is it called when water vapor cools down and turns back into liquid water droplets in clouds?", "ජල වාෂ්ප සිසිල් වී නැවත ද්‍රව ජල බිඳිති බවට පත්වීමේ ක්‍රියාවලිය?", listOf("Evaporation", "Condensation", "Melting", "Boiling"), listOf("වාෂ්පීභවනය", "ඝනීභවනය (Condensation)", "දියවීම", "නැටීම"), 1)
        2 -> Pentuple("Water falling from clouds as rain, snow, or sleet is known as:", "වලාකුළුවල සිට වැසි, හිම ලෙස ජලය පොළොවට පතිත වීම හඳුන්වන්නේ:", listOf("Evaporation", "Transpiration", "Precipitation", "Collection"), listOf("වාෂ්පීභවනය", "උත්ස්වේදනය", "අවක්ෂේපණය (Precipitation)", "එකතු කිරීම"), 2)
        3 -> Pentuple("The continuous movement of water from Earth to air and back is called the:", "පෘථිවිය සහ වායුගෝලය අතර සිදුවන නිරන්තර ජල සංසරණය කුමක්ද?", listOf("Rock cycle", "Water cycle", "Nitrogen cycle", "Food cycle"), listOf("පාෂාණ චක්‍රය", "ජල චක්‍රය (Water cycle)", "නයිට්‍රජන් චක්‍රය", "ආහාර චක්‍රය"), 1)
        4 -> Pentuple("What provides the energy/heat to drive the water cycle?", "ජල චක්‍රය ක්‍රියාත්මක වීමට අවශ්‍ය ශක්තිය/තාපය සපයන්නේ කුමකින්ද?", listOf("The Wind", "The Sun", "The Moon", "Volcanoes"), listOf("සුළඟ", "හිරු (Sun)", "හඳ", "ගිනි කඳු"), 1)
        5 -> Pentuple("Plants release water vapor into the air through leaves. What is this called?", "ශාක තම පත්‍ර හරහා වාතයට ජල වාෂ්ප මුදා හැරීම හඳුන්වන්නේ:", listOf("Absorption", "Respiration", "Transpiration", "Condensation"), listOf("අවශෝෂණය", "ශ්වසනය", "උත්ස්වේදනය (Transpiration)", "ඝනීභවනය"), 2)
        6 -> Pentuple("Where is most of the Earth's liquid water stored?", "පෘථිවියේ ඇති දියර ජලයෙන් වැඩිම ප්‍රමාණයක් ගබඩා වී ඇත්තේ කොහේද?", listOf("In rivers", "In swimming pools", "In oceans and seas", "In clouds"), listOf("ගංගාවල", "පිහිනුම් තටාකවල", "සාගර සහ මුහුදුවල", "වලාකුළුවල"), 2)
        7 -> Pentuple("What is a simple connection of living things eating other living things called?", "එක් ජීවියෙකු තවත් ජීවියෙකු ආහාරයට ගනිමින් ඇති කරගන්නා සරල සම්බන්ධතාවය?", listOf("Water cycle", "Food chain", "Photosynthesis", "Breathing"), listOf("ජල චක්‍රය", "ආහාර දාමය (Food chain)", "ප්‍රභාසංශ්ලේෂණය", "ශ්වසනය"), 1)
        8 -> Pentuple("In a green forest food chain, what role do green plants play?", "ආහාර දාමයක හරිත ශාක හඳුන්වන්නේ කුමන නමකින්ද?", listOf("Decomposers", "Consumers", "Producers", "Predators"), listOf("වියෝජකයන්", "පාරිභෝගිකයන්", "නිෂ්පාදකයන් (Producers)", "විලෝපිකයන්"), 2)
        else -> Pentuple("An animal that eats only other animals is called a:", "වෙනත් සතුන් පමණක් ආහාරයට ගන්නා සත්වයා හඳුන්වන්නේ:", listOf("Herbivore", "Carnivore", "Omnivore", "Producer"), listOf("ශාකභක්ෂකයා", "මාංශභක්ෂකයා (Carnivore)", "සර්වභක්ෂකයා", "නිෂ්පාදකයා"), 1)
    }

    private fun getGrade4Stage3(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("What is a material that allows electricity to flow through it easily?", "තමා තුළින් පහසුවෙන් විදුලිය ගලා යාමට ඉඩ සලසන ද්‍රව්‍ය වර්ග මොනවාද?", listOf("Insulator", "Conductor", "Reflector", "Absorber"), listOf("පරිවාරකය", "සන්නායකය (Conductor)", "පරාවර්තකය", "අවශෝෂකය"), 1)
        1 -> Pentuple("Which of the following is a good conductor of electricity?", "පහත දැක්වෙන දේවලින් විදුලි සන්නායකයක් වන්නේ කුමක්ද?", listOf("Plastic cup", "Copper wire", "Dry wood", "Rubber glove"), listOf("ප්ලාස්ටික් කෝප්පය", "තඹ කම්බිය (Copper wire)", "වියළි ලී", "රබර් අත්වැසුම"), 1)
        2 -> Pentuple("What is a material that blocks the flow of electricity called?", "විදුලිය ගලා යාම වළක්වන/පාලනය කරන ද්‍රව්‍යය කුමක්ද?", listOf("Conductor", "Insulator", "Battery", "Switch"), listOf("සන්නායකය", "පරිවාරකය (Insulator)", "බැටරිය", "ස්විචය"), 1)
        3 -> Pentuple("Which material is a strong insulator used to coat electric wires?", "විදුලි රැහැන් ආවරණය කිරීම සඳහා යොදාගන්නා ශක්තිමත් පරිවාරක ද්‍රව්‍යය කුමක්ද?", listOf("Silver", "Copper", "Plastic/Rubber", "Iron"), listOf("රිදී", "තඹ", "ප්ලාස්ටික් / රබර්", "යකඩ"), 2)
        4 -> Pentuple("A magnet has how many magnetic poles?", "චුම්භකයකට චුම්භක ධ්‍රැව කීයක් පවතීද?", listOf("1 pole", "2 poles", "3 poles", "4 poles"), listOf("ධ්‍රැව 1ක්", "ධ්‍රැව 2ක්", "ධ්‍රැව 3ක්", "ධ්‍රැව 4ක්"), 1)
        5 -> Pentuple("What are the names of the two poles of a magnet?", "චුම්භකයක ඇති ධ්‍රැව දෙකෙහි නම් මොනවාද?", listOf("East and West", "North and South", "Up and Down", "Left and Right"), listOf("නැගෙනහිර සහ බටහිර", "උත්තර සහ දක්ෂිණ ධ්‍රැව", "ඉහළ සහ පහළ", "වම් සහ දකුණු"), 1)
        6 -> Pentuple("What happens when two North poles of two magnets are brought close?", "චුම්භක දෙකක උත්තර ධ්‍රැව (N) දෙකක් එකිනෙකට ළං කළවිට කුමක් සිදුවේද?", listOf("They attract", "They repel (push away)", "Nothing", "They catch fire"), listOf("ආකර්ෂණය වේ", "විකර්ෂණය වේ (Repel)", "කිසිවක් නොවේ", "ගිනි ගනී"), 1)
        7 -> Pentuple("What happens when a North pole and a South pole are brought close?", "උත්තර ධ්‍රැවයක් සහ දක්ෂිණ ධ්‍රැවයක් එකිනෙකට ළං කළවිට කුමක් සිදුවේද?", listOf("They repel", "They attract (pull together)", "They dissolve", "They demagnetize"), listOf("විකර්ෂණය වේ", "ආකර්ෂණය වේ (Attract)", "දිය වේ", "නිශ්චුම්භක වේ"), 1)
        8 -> Pentuple("Which device uses a magnetic needle to show directions?", "දිශාවන් සෙවීම සඳහා චුම්භක කටුවක් භාවිතා කරන උපකරණය කුමක්ද?", listOf("Thermometer", "Compass", "Barometer", "Speedometer"), listOf("උෂ්ණත්වමානය", "මාලිමාව (Compass)", "වායු පීඩනමානය", "වේගමානය"), 1)
        else -> Pentuple("What element provides power in a simple torch light circuit?", "සරල විදුලි පන්දම් පරිපථයකට විදුලි බලය සපයන ප්‍රභවය කුමක්ද?", listOf("Switch", "Wire", "Dry cell (battery)", "Bulb"), listOf("ස්විචය", "කම්බිය", "වියළි කෝෂය (බැටරිය)", "බල්බය"), 2)
    }

    // GRADE 5
    private fun getGrade5Stage1(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("What are the three main states of matter?", "පදාර්ථයේ පවතින ප්‍රධාන භෞතික අවස්ථා තුන කුමක්ද?", listOf("Heat, Light, Water", "Solid, Liquid, Gas", "Ato, Molecule, Compound", "Ice, Soil, Vapor"), listOf("තාපය, ආලෝකය, ජලය", "ඝන, ද්‍රව, වායු", "පරමාණුව, අණුව, සංයෝගය", "අයිස්, පස, වාෂ්ප"), 1)
        1 -> Pentuple("Which state of matter has a definite shape and a definite volume?", "නියත හැඩයක් සහ නියත පරිමාවක් ඇති පදාර්ථ අවස්ථාව කුමක්ද?", listOf("Liquid", "Gas", "Solid", "Plasma"), listOf("ද්‍රව", "වායු", "ඝන (Solid)", "ප්ලාස්මා"), 2)
        2 -> Pentuple("Which state of matter has a definite volume but takes the shape of its container?", "නියත පරිමාවක් තිබුණද බහාලුමේ හැඩය ගන්නා පදාර්ථ වාස්ථාව කුමක්ද?", listOf("Solid", "Liquid", "Gas", "Vapor"), listOf("ඝන", "ද්‍රව (Liquid)", "වායු", "වාෂ්ප"), 1)
        3 -> Pentuple("Which of the following is an example of gas?", "පහත දැක්වෙන දේවලින් වායුවක් සඳහා උදාහරණයක් වන්නේ කුමක්ද?", listOf("Ice", "Honey", "Oxygen/Air", "Iron block"), listOf("අයිස්", "මීපැණි", "ඔක්සිජන් / වාතය", "යකඩ කුට්ටිය"), 2)
        4 -> Pentuple("What is the process of a solid turning into a liquid called?", "ඝන ද්‍රව්‍යයක් රත් වී ද්‍රවයක් බවට පත්වීමේ ක්‍රියාවලිය?", listOf("Freezing", "Condensation", "Melting", "Evaporation"), listOf("කැටිගැසීම", "ඝනීභවනය", "දියවීම (Melting)", "වාෂ්පීභවනය"), 2)
        5 -> Pentuple("When water is boiled, it turns into water vapor. This is:", "ජලය නැටවීමේදී එය ජල වාෂ්ප බවට පත්වීම කුමක්ද?", listOf("Freezing", "Solidifying", "Evaporation", "Condensation"), listOf("කැටිගැසීම", "ඝන වීම", "වාෂ්පීභවනය (Evaporation)", "ඝනීභවනය"), 2)
        6 -> Pentuple("What happens to water when it is cooled down to 0 degrees Celsius?", "සෙල්සියස් අංශක 0 දක්වා ජලය සිසිල් කළවිට සිදුවන්නේ කුමක්ද?", listOf("It boils", "It freezes into ice", "It evaporates", "It vanishes"), listOf("එය නටයි", "එය අයිස් බවට පත් වේ (Freezes)", "එය වාෂ්ප වේ", "එය වැනසෙයි"), 1)
        7 -> Pentuple("Which of these substances can dissolve easily in water?", "පහත ද්‍රව්‍යවලින් ජලයේ පහසුවෙන් දියවන (ද්‍රාව්‍ය) ද්‍රව්‍යය කුමක්ද?", listOf("Sand", "Stone", "Sugar", "Plastic"), listOf("වැලි", "ගල්", "සීනි (Sugar)", "ප්ලාස්ටික්"), 2)
        8 -> Pentuple("Which state of matter spreads to fill any available space completely?", "ඇති ඕනෑම අවකාශයක් සම්පූර්ණයෙන්ම පිරවීමට ව්‍යාප්ත වන පදාර්ථ අවස්ථාව?", listOf("Solid", "Liquid", "Gas", "Ice"), listOf("ඝන", "ද්‍රව", "වායු (Gas)", "අයිස්"), 2)
        else -> Pentuple("A stone has a fixed volume and shape. It is classified as:", "ගලකට ස්ථාවර පරිමාවක් සහ හැඩයක් ඇත. එය අයත් වන්නේ කුමන අවස්ථාවටද?", listOf("Liquid", "Gas", "Solid", "Vapor"), listOf("ද්‍රව", "වායු", "ඝන (Solid)", "වාෂ්ප"), 2)
    }

    private fun getGrade5Stage2(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("What process is used to treat milk so it lasts longer without spoiling?", "කිරි වැඩි කාලයක් නරක් නොවී තබා ගැනීමට කරන ක්‍රියාවලිය කුමක්ද?", listOf("Boiling", "Freezing", "Pasteurization", "Drying"), listOf("නැටවීම", "ශීත කිරීම", "පැස්ටරීකරණය", "වියලීම"), 2)
        1 -> Pentuple("Adding excess salt to fish to preserve it is called:", "කල් තබා ගැනීම සඳහා මාලුවලට වැඩිපුර ලුණු දැමීමේ ක්‍රියාවලිය?", listOf("Dehydration", "Salting / Curing", "Canning", "Fermentation"), listOf("නිර්ජලීකරණය", "ලුණු දැමීම (Salting)", "ටින් කිරීම", "කිලීකරණය"), 1)
        2 -> Pentuple("Why does food spoil?", "ආහාර නරක් වීමට ප්‍රධාන හේතුව කුමක්ද?", listOf("Due to sunlight only", "Due to actions of microorganisms", "Due to salt", "Due to cold"), listOf("හිරු එළිය නිසා පමණක්", "ක්ෂුද්‍ර ජීවීන්ගේ ක්‍රියාකාරිත්වය නිසා", "ලුණු නිසා", "සීතල නිසා"), 1)
        3 -> Pentuple("Drying grains in the sun preserves them by removing what?", "ධාන්‍ය අව්වේ වේලීමෙන් ඒවා කල් තබාගත හැක්කේ කුමක් ඉවත් කිරීමෙන්ද?", listOf("Proteins", "Moisture / Water", "Carbohydrates", "Color"), listOf("ප්‍රෝටීන්", "තෙතමනය / ජලය (Moisture)", "කාබෝහයිඩ්‍රේට්", "පැහැය"), 1)
        4 -> Pentuple("Which method keeps ready-made food inside sealed metal containers?", "සූදානම් කළ ආහාර මුද්‍රා තැබූ ලෝහ ඇසුරුම්වල කල් තබා ගන්නේ කුමන ක්‍රමයෙන්ද?", listOf("Salting", "Smoked curing", "Canning", "Fermentation"), listOf("ලුණු දැමීම", "දුම් ගැසීම", "ටින් කිරීම (Canning)", "කිලීකරණය"), 2)
        5 -> Pentuple("Keeping food inside a refrigerator preserves it because the low temperature:", "ශීතකරණයක ආහාර තැබීමෙන් කල් තබාගත හැක්කේ එහි ඇති අඩු උෂ්ණත්වය නිසා:", listOf("Kills all bacteria instantly", "Slows down the growth of microorganisms", "Removes water", "Adds vitamins"), listOf("බැක්ටීරියා වහාම මරන නිසා", "ක්ෂුද්‍ර ජීවී වර්ධනය බාල කරන නිසා (Slows growth)", "ජලය ඉවත් කරන නිසා", "විටමින් එක් කරන නිසා"), 1)
        6 -> Pentuple("Which natural preservative is commonly used in making lime pickles?", "ලුණු දෙහි සෑදීමේදී බහුලව ගන්නා ස්වභාවික කල්තබා ගැනීමේ ද්‍රව්‍යය කුමක්ද?", listOf("Sugar", "Vinegar and Salt", "Water", "Chili"), listOf("සීනි", "විනාකිරි සහ ලුණු (Vinegar and Salt)", "ජලය", "මිරිස්"), 1)
        7 -> Pentuple("Which of these is a traditional Sri Lankan method of preserving woodapple or mango?", "ඇඹුල් දොඩම් හෝ අඹ කල් තබා ගැනීමට ශ්‍රී ලංකාවේ සාම්ප්‍රදායික ක්‍රමයක් කුමක්ද?", listOf("Freezing", "Making chutney / jam with sugar", "Irradiation", "Canning"), listOf("ශීත කිරීම", "සීනි දමා චට්නි / ජෑම් සෑදීම", "විකිරණය", "ටින් කිරීම"), 1)
        8 -> Pentuple("Preservation of food is important to prevent what?", "ආහාර කල් තබා ගැනීම වැදගත් වන්නේ කුමක් වැළැක්වීමටද?", listOf("Vitamins", "Food waste and food poisoning", "Digestion", "Cooking"), listOf("විටමින්", "ආහාර අපතේ යාම සහ විෂ වීම (Waste and Poisoning)", "ජීර්ණය", "පිසීම"), 1)
        else -> Pentuple("What is the gas inside puffed potato chips bags to prevent spoilage?", "නරක් වීම වැළැක්වීමට අර්තාපල් පෙති පැකට් තුළ පුරවා ඇති වායුව කුමක්ද?", listOf("Oxygen", "Nitrogen", "Carbon dioxide", "Hydrogen"), listOf("ඔක්සිජන්", "නයිට්‍රජන් (Nitrogen)", "කාබන් ඩයොක්සයිඩ්", "හයිඩ්‍රජන්"), 1)
    }

    private fun getGrade5Stage3(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("What is the contamination of air, water, or soil called?", "වාතය, ජලය හෝ පස අපිරිසිදු වී හානිකර තත්ත්වයට පත්වීම හඳුන්වන්නේ?", listOf("Conservation", "Pollution", "Purification", "Erosion"), listOf("සංරක්ෂණය", "දූෂණය (Pollution)", "පවිත්‍රකරණය", "ඛාදනය"), 1)
        1 -> Pentuple("Which of the following causes air pollution?", "පහත දැක්වෙන දේවලින් වාතය දූෂණය වීමට හේතු වන්නේ කුමක්ද?", listOf("Planting trees", "Smoke from factories and vehicles", "Filtering water", "Recycling plastic"), listOf("ගස් වැවීම", "කර්මාන්තශාලා සහ වාහන දුම (Smoke)", "ජලය පෙරීම", "ප්ලාස්ටික් ප්‍රතිචක්‍රීකරණය"), 1)
        2 -> Pentuple("Dumping plastic waste into water bodies causes:", "ජලාශවලට ප්ලාස්ටික් අපද්‍රව්‍ය දැමීමෙන් සිදුවන්නේ කුමක්ද?", listOf("Air pollution", "Water pollution", "Noise pollution", "Soil enrichment"), listOf("වායු දූෂණය", "ජල දූෂණය (Water pollution)", "ශබ්ද දූෂණය", "පස් සාරවත් වීම"), 1)
        3 -> Pentuple("Which of the following is a non-biodegradable pollutant?", "පහත දැක්වෙන දේවලින් ස්වභාවිකව දිරාපත් නොවන අපද්‍රව්‍යයක් වන්නේ කුමක්ද?", listOf("Banana peel", "Paper bag", "Polythene bag", "Dry leaves"), listOf("කෙසෙල් ලෙල්ල", "කඩදාසි බෑගය", "පොලිතින් බෑගය (Polythene)", "වියළි කොළ"), 2)
        4 -> Pentuple("What can we do to reduce plastic waste?", "ප්ලාස්ටික් අපද්‍රව්‍ය අඩු කිරීමට අපට කළ හැක්කේ කුමක්ද?", listOf("Burn it outdoors", "Throw in rivers", "Recycle and reuse", "Bury in forest"), listOf("එළිමහනේ පුළුස්සා දැමීම", "ගංගාවලට දැමීම", "ප්‍රතිචක්‍රීකරණය සහ නැවත භාවිතය", "කැලෑවේ වැළලීම"), 2)
        5 -> Pentuple("Excessive use of chemical fertilizers on farm lands leads to:", "වගා බිම්වල රසායනික පොහොර පමණට වඩා භාවිතයෙන් සිදුවන්නේ:", listOf("Air purification", "Soil and groundwater pollution", "Organic food growth", "Rainfall"), listOf("වාතය පිරිසිදු වීම", "පස සහ භූගත ජලය දූෂණය වීම (Soil pollution)", "කාබනික ආහාර වර්ධනය", "වැසි ලැබීම"), 1)
        6 -> Pentuple("What is the loud disturbing sound that can damage our hearing called?", "අපගේ ශ්‍රවණයට හානි කරන, බාධාකාරී අධික ශබ්ද හඳුන්වන්නේ කුමක් ලෙසද?", listOf("Air pollution", "Water pollution", "Noise pollution", "Soil erosion"), listOf("වායු දූෂණය", "ජල දූෂණය", "ශබ්ද දූෂණය (Noise pollution)", "පස සෝදායාම"), 2)
        7 -> Pentuple("Which practice helps to protect the environment?", "පරිසරය ආරක්ෂා කිරීමට උදව් වන ක්‍රියාවක් වන්නේ පහත ඒවායින් කුමක්ද?", listOf("Deforestation", "Conserving water and recycling", "Dumping chemical wastes", "Wasting electricity"), listOf("වනාන්තර විනාශය", "ජලය සුරැකීම සහ ප්‍රතිචක්‍රීකරණය (Recycling)", "රසායනික අපද්‍රව්‍ය බැහැර කිරීම", "විදුලිය නාස්ති කිරීම"), 1)
        8 -> Pentuple("Acid rain can be caused by severe:", "අම්ල වැසි ඇති වීමට ප්‍රධාන වශයෙන් බලපාන්නේ දරුණු ලෙස සිදුවන:", listOf("Water pollution", "Air pollution", "Noise pollution", "Soil conservation"), listOf("ජල දූෂණය", "වායු දූෂණය (Air pollution)", "ශබ්ද දූෂණය", "පස සංරක්ෂණය"), 1)
        else -> Pentuple("What is the best way to handle kitchen food waste?", "කුස්සියේ ඉතිරිවන ආහාර අපද්‍රව්‍ය කළමනාකරණයට හොඳම ක්‍රමය කුමක්ද?", listOf("Throw in plastic bags", "Convert into organic compost", "Burn it", "Throw in lake"), listOf("ප්ලාස්ටික් බෑග්වල දමා විසි කිරීම", "කාබනික කොම්පෝස්ට් සෑදීම (Compost)", "පුළුස්සා දැමීම", "වැවට දැමීම"), 1)
    }
}
