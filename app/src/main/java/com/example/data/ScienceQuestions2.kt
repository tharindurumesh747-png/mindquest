package com.example.data

object ScienceQuestions2 {

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
            subject = "Science",
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

    // GRADE 6
    private fun getGrade6Stage1(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("What is the basic structural and functional unit of all living organisms?", "සියලුම ජීවීන්ගේ මූලික ව්‍යුහමය හා ක්‍රියාකාරී ඒකකය කුමක්ද?", listOf("Tissue", "Cell", "Organ", "System"), listOf("පටකයක්", "සෛලය (Cell)", "අවයවයක්", "පද්ධතියක්"), 1)
        1 -> Pentuple("Which part is found only in plant cells and not in animal cells?", "ශාක සෛලවල පමණක් දක්නට ලැබෙන සත්ව සෛලවල නොමැති කොටස කුමක්ද?", listOf("Cell membrane", "Cytoplasm", "Cell Wall", "Nucleus"), listOf("සෛල ප්ලාස්ම පටලය", "සෛල ප්ලාස්මය", "සෛල බිත්තිය (Cell Wall)", "න්‍යෂ්ටිය"), 2)
        2 -> Pentuple("What is the green pigment in plant cells that traps light for photosynthesis?", "ප්‍රභාසංශ්ලේෂණය සඳහා ආලෝකය ලබා ගන්නා වර්ණකය කුමක්ද?", listOf("Melanin", "Hemoglobin", "Chlorophyll", "Carotene"), listOf("මෙලනින්", "හීමොග්ලොබින්", "ක්ලෝරොෆිල් (Chlorophyll)", "කැරොටින්"), 2)
        3 -> Pentuple("What are the microscopic organisms made of only one cell called?", "එක් සෛලයකින් පමණක් සැදුම් ලත් ඉතා කුඩා ජීවීන් හඳුන්වන්නේ කුමක් ලෙසද?", listOf("Multicellular", "Unicellular", "Viruses", "Parasites"), listOf("බහුසෛලිකයන්", "ඒකසෛලිකයන් (Unicellular)", "වෛරස්", "පරපෝෂිතයන්"), 1)
        4 -> Pentuple("Which organelle is known as the control center of the cell?", "සෛලයක පාලන මධ්‍යස්ථානය ලෙස හඳුන්වනු ලබන ව්‍යුහය කුමක්ද?", listOf("Mitochondria", "Vacuole", "Nucleus", "Ribosome"), listOf("මයිටොකොන්ඩ්‍රියාව", "රික්තකය", "න්‍යෂ්ටිය (Nucleus)", "රයිබොසෝමය"), 2)
        5 -> Pentuple("Which of these is a multicellular organism?", "පහත දැක්වෙන ජීවීන්ගෙන් බහුසෛලික ජීවියෙක් වන්නේ කුමක්ද?", listOf("Amoeba", "Paramecium", "Human", "Chlamydomonas"), listOf("ඇමීබා", "පැරමීසියම්", "මිනිසා (Human)", "ක්ලැමිඩොමොනාස්"), 2)
        6 -> Pentuple("What jelly-like substance fills the cell inside the membrane?", "සෛල ප්ලාස්ම පටලය ඇතුළත ඇති ජෙලි වැනි ද්‍රව්‍යය කුමක්ද?", listOf("Nucleus", "Cytoplasm", "Cell sap", "Chloroplast"), listOf("න්‍යෂ්ටිය", "සෛල ප්ලාස්මය (Cytoplasm)", "සෛල යුෂ", "හරිතලවය"), 1)
        7 -> Pentuple("Which organelle produces energy for cellular activities?", "සෛලීය ක්‍රියාකාරකම් සඳහා අවශ්‍ය ශක්තිය නිපදවන සෛලාංගය කුමක්ද?", listOf("Golgi body", "Vacuole", "Mitochondria", "Cell wall"), listOf("ගොල්ගි දේහය", "රික්තකය", "මයිටොකොන්ඩ්‍රියාව (Mitochondria)", "සෛල බිත්තිය"), 2)
        8 -> Pentuple("Which of the following is unicellular?", "පහත දැක්වෙන ජීවීන්ගෙන් ඒකසෛලික වන්නේ කවුද?", listOf("Paddy plant", "Cat", "Amoeba", "Butterfly"), listOf("වී පැළය", "බළලා", "ඇමීබා (Amoeba)", "සමනලයා"), 2)
        else -> Pentuple("In plant cells, what large central structure stores water and nutrients?", "ශාක සෛලවල ජලය සහ පෝෂ්‍ය පදාර්ථ ගබඩා කරන විශාල මැද පිහිටි ව්‍යුහය කුමක්ද?", listOf("Nucleus", "Large Vacuole", "Chloroplast", "Cell membrane"), listOf("න්‍යෂ්ටිය", "විශාල රික්තකය (Large Vacuole)", "හරිතලවය", "සෛල ප්ලාස්ම පටලය"), 1)
    }

    private fun getGrade6Stage2(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("A mixture where the components cannot be seen separately is:", "අඩංගු සංඝටක වෙන් වෙන්ව හඳුනාගත නොහැකි සමජාතීය මිශ්‍රණයක් වන්නේ:", listOf("Heterogeneous mix", "Homogeneous mix / Solution", "Suspension", "Emulsion"), listOf("විෂමජාතීය මිශ්‍රණය", "සමජාතීය මිශ්‍රණය (Homogeneous)", "අත්හිටුවීම", "තෙල් ද්‍රාවණය"), 1)
        1 -> Pentuple("Which method is best to separate insoluble sand from water?", "දිය නොවන වැලි, ජලයෙන් වෙන් කරගැනීමට වඩාත්ම සුදුසු ක්‍රමය කුමක්ද?", listOf("Evaporation", "Filtration", "Distillation", "Sublimation"), listOf("වාෂ්පීභවනය", "පෙරීම (Filtration)", "ස්වේදනය", "ඌර්ධවපාතනය"), 1)
        2 -> Pentuple("To obtain pure salt from sea water, which separation method is used?", "මුහුදු ජලයෙන් ලුණු ලබා ගැනීම සඳහා යොදාගන්නා ක්‍රමය කුමක්ද?", listOf("Filtration", "Evaporation", "Magnetic separation", "Sifting"), listOf("පෙරීම", "වාෂ්පීභවනය (Evaporation)", "චුම්භක වෙන්කිරීම", "පෙරීම"), 1)
        3 -> Pentuple("What is air classified as?", "වාතය අයත් වන්නේ කුමන කාණ්ඩයටද?", listOf("Element", "Compound", "Mixture of gases", "Pure substance"), listOf("මූලද්‍රව්‍යය", "සංයෝගය", "වායු මිශ්‍රණයක් (Mixture)", "ශුද්ධ ද්‍රව්‍යයක්"), 2)
        4 -> Pentuple("Which of these is a heterogeneous mixture?", "පහත දැක්වෙන දේවලින් විෂමජාතීය මිශ්‍රණයක් වන්නේ කුමක්ද?", listOf("Salt water", "Sugar syrup", "Sand and water", "Clear tea"), listOf("ලුණු වතුර", "සීනි පැණි", "වැලි මිශ්‍ර ජලය (Sand/water)", "පැහැදිලි තේ"), 2)
        5 -> Pentuple("How can iron filings be separated from sulfur powder?", "යකඩ කුඩු සහ සල්ෆර් කුඩු මිශ්‍රණයකින් යකඩ වෙන් කර ගැනීමට සුදුසු ක්‍රමය?", listOf("Evaporation", "Using a magnet", "Filtration", "Decantation"), listOf("වාෂ්පීභවනය", "චුම්භකය භාවිතයෙන් (Magnet)", "පෙරීම", "තැන්පත් කිරීම"), 1)
        6 -> Pentuple("What is the liquid that passes through the filter paper during filtration called?", "පෙරීමේ ක්‍රියාවලියේදී පෙරහන් කඩදාසිය හරහා පහළට එන ද්‍රවය හඳුන්වන්නේ?", listOf("Residue", "Filtrate", "Solute", "Suspension"), listOf("අවශේෂය", "පෙරහන (Filtrate)", "ද්‍රාව්‍යය", "අත්හිටුවීම"), 1)
        7 -> Pentuple("The solid remaining on the filter paper during filtration is called:", "පෙරීමේදී පෙරහන් කඩදාසිය මත ඉතිරිවන ඝන කොටස හඳුන්වන්නේ:", listOf("Filtrate", "Residue", "Solvent", "Precipitate"), listOf("පෙරහන", "අවශේෂය (Residue)", "ද්‍රාවකය", "අවක්ෂේපය"), 1)
        8 -> Pentuple("Which separation method is used in tea strainers to clean tea?", "තේ කොළ තේ කෝප්පයෙන් වෙන් කරගැනීමට අප නිවෙස්වල යොදාගන්නේ කුමන ක්‍රමයද?", listOf("Centrifugation", "Sifting/Filtration", "Crystallization", "Distillation"), listOf("කේන්ද්‍රපසාරණය", "පෙරීම (Filtration)", "ස්ඵටිකීකරණය", "ස්වේදනය"), 1)
        else -> Pentuple("Which of these is a pure substance and not a mixture?", "පහත ඒවායින් මිශ්‍රණයක් නොවන ශුද්ධ ද්‍රව්‍යය කුමක්ද?", listOf("Milk", "Seawater", "Distilled water", "Soil"), listOf("කිරි", "මුහුදු ජලය", "පිරිසිදු ආස්‍රැත ජලය (Distilled water)", "පස"), 2)
    }

    private fun getGrade6Stage3(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("What is the SI unit of electric current?", "විදුලි ධාරාව මැනීමේ SI ඒකකය කුමක්ද?", listOf("Volt", "Ampere", "Ohm", "Watt"), listOf("වෝල්ට්", "ඇම්පියරය (Ampere)", "ඕම්", "වොට්"), 1)
        1 -> Pentuple("Which instrument is used to measure potential difference?", "විභව වෙනස මැනීම සඳහා භාවිතා කරන උපකරණය කුමක්ද?", listOf("Ammeter", "Voltmeter", "Galvanometer", "Anemometer"), listOf("ඇමීටරය", "වෝල්ට්මීටරය (Voltmeter)", "ගැල්වනෝමීටරය", "සුළංවේගමානය"), 1)
        2 -> Pentuple("What device is used to open or close an electric circuit?", "විද්‍යුත් පරිපථයක් ක්‍රියාත්මක කිරීමට හෝ අක්‍රිය කිරීමට ගන්නා උපකරණය?", listOf("Battery", "Bulb", "Switch", "Fuse"), listOf("බැටරිය", "බල්බය", "ස්විචය (Switch)", "ෆියුස් එක"), 2)
        3 -> Pentuple("How is a voltmeter connected in a circuit relative to a component?", "පරිපථයක උපාංගයකට සාපේක්ෂව වෝල්ට්මීටරයක් සම්බන්ධ කළ යුත්තේ කෙසේද?", listOf("In series", "In parallel", "In reverse direction", "Anywhere"), listOf("ශ්‍රේණිගතව", "සමාන්තරගතව (In parallel)", "ප්‍රතිවිරුද්ධව", "ඕනෑම තැනක"), 1)
        4 -> Pentuple("What SI unit is used to measure resistance?", "ප්‍රතිරෝධය මැනීම සඳහා භාවිතා කරන SI ඒකකය කුමක්ද?", listOf("Ampere", "Volt", "Ohm", "Siemens"), listOf("ඇම්පියරය", "වෝල්ට්", "ඕම් (Ohm)", "සීමන්ස්"), 2)
        5 -> Pentuple("An ammeter should always be connected in which manner in a circuit?", "පරිපථයකට ඇමීටරයක් නිරන්තරයෙන්ම සම්බන්ධ කළ යුත්තේ කෙසේද?", listOf("In parallel", "In series", "In diagonal", "Independently"), listOf("සමාන්තරගතව", "ශ්‍රේණිගතව (In series)", "විකර්ණව", "ස්වාධීනව"), 1)
        6 -> Pentuple("If two bulbs are connected in series, and one bulb fuses (breaks), what happens to the other?", "බල්බ දෙකක් ශ්‍රේණිගතව සම්බන්ධ කර ඇති විට එකක් පිච්චී ගියහොත් අනෙකට කුමක් සිදුවේද?", listOf("Glows brighter", "Splits energy", "Goes out based on circuit break", "Stays unchanged"), listOf("වැඩිපුර ආලෝකවත් වේ", "ශක්තිය බෙදා ගනී", "පරිපථය බිඳී යන නිසා එයද නිවී යයි", "එලෙසම පවතී"), 2)
        7 -> Pentuple("What does a battery represent in a simple electrical circuit?", "සරල විද්‍යුත් පරිපථයක බැටරිය මඟින් ඉටුකරන ප්‍රධාන කාර්යය කුමක්ද?", listOf("A resistor", "A source of electrical energy", "A safety switch", "A conductor"), listOf("ප්‍රතිරෝධකයක්", "විද්‍යුත් ශක්ති ප්‍රභවයක් (Energy Source)", "ආරක්ෂිත ස්විචයක්", "සන්නායකයක්"), 1)
        8 -> Pentuple("What component prevents damage to a circuit by melting when current is too high?", "විදුලිය සැර වැඩිවූ විට දියවී ගොස් පරිපථය අනතුරුවලින් බේරාගන්නා උපාංගය?", listOf("Switch", "Wire", "Fuse", "Resistor"), listOf("ස්විචය", "රැහැන", "ෆියුස් එක (Fuse)", "ප්‍රතිරෝධකය"), 2)
        else -> Pentuple("What is the SI unit of length?", "දිග මැනීමේ SI ඒකකය කුමක්ද?", listOf("Centimeter", "Millimeter", "Meter", "Kilometer"), listOf("සෙන්ටිමීටර", "මිලිමීටර", "මීටර (Meter)", "කිලෝමීටර"), 2)
    }

    // GRADE 7
    private fun getGrade7Stage1(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("Which nutrient is primarily responsible for growth and repair of cells?", "සිරුරේ වර්ධනයට හා සෛල අලුත්වැඩියාව සඳහා අවශ්‍ය ප්‍රධාන පෝෂකය කුමක්ද?", listOf("Carbohydrates", "Fats", "Proteins", "Vitamins"), listOf("කාබෝහයිඩ්‍රේට්", "මේද", "ප්‍රෝටීන් (Proteins)", "විටමින්"), 2)
        1 -> Pentuple("Which disease is caused by deficiency of Vitamin C?", "විටමින් C ඌනතාවය නිසා ඇතිවන රෝගය කුමක්ද?", listOf("Night blindness", "Scurvy (bleeding gums)", "Rickets", "Beriberi"), listOf("රාත්‍රී අන්ධභාවය", "ස්කර්වි - විදුරුමස් ලේ ගැලීම (Scurvy)", "ක්ලෑවී යාම", "බෙරිබෙරි"), 1)
        2 -> Pentuple("What is the enzyme in saliva that starts starch digestion in mouth?", "කෙළවල අඩංගු, පිෂ්ඨය ජීර්ණය ආරම්භ කරන එන්සයිමය කුමක්ද?", listOf("Pepsin", "Amylase", "Lipase", "Trypsin"), listOf("පෙප්සින්", "ඇමයිලේස් (Amylase)", "ලයිපේස්", "ට්‍රිප්සින්"), 1)
        3 -> Pentuple("In which organ of the digestive system is water mainly absorbed from waste?", "ආහාර ජීරණ පද්ධතියේ ජලය වැඩිපුරම අවශෝෂණය කරගනු ලබන අවයවය කුමක්ද?", listOf("Stomach", "Small Intestine", "Large Intestine", "Esophagus"), listOf("ආමාශය", "කුඩා බඩවැල", "මහා බඩවැල (Large Intestine)", "ග්‍රසනිකාව"), 2)
        4 -> Pentuple("Where does digestion of proteins begin in the human body?", "මිනිස් සිරුර තුළ ප්‍රෝටීන් ජීර්ණය ආරම්භ වන්නේ කොතැනින්ද?", listOf("Mouth", "Stomach", "Small intestine", "Liver"), listOf("මුඛය", "ආමාශය (Stomach)", "කුඩා බඩවැල", "අක්මාව"), 1)
        5 -> Pentuple("What carbohydrate source is the main energy provider in Sri Lankan meals?", "ශ්‍රී ලංකාවේ ප්‍රධාන ශක්ති ප්‍රභවය ලෙස බහුලව ගන්නා පිෂ්ඨය අඩංගු ආහාරය?", listOf("Eggs", "Rice", "Meat", "Butter"), listOf("බිත්තර", "බත් (Rice)", "මස්", "බටර්"), 1)
        6 -> Pentuple("Deficiency of Iron in the blood diet leads to which disease?", "ආහාරයේ යකඩ ඌනතාවය නිසා ඇතිවන රෝග තත්ත්වය කුමක්ද?", listOf("Goiter", "Scurvy", "Anemia", "Rickets"), listOf("ගලගණ්ඩය", "ස්කර්වි", "නීරක්තතාවය (Anemia)", "ක්ලෑවී යාම"), 2)
        7 -> Pentuple("What acid is produced naturally in the human stomach to help digestion?", "ආහාර ජීර්ණයට උපකාරී වීම සඳහා ආමාශය තුළ නිපදවෙන අම්ලය කුමක්ද?", listOf("Sulphuric acid", "Nitric acid", "Hydrochloric acid", "Acetic acid"), listOf("සල්ෆියුරික් අම්ලය", "නයිට්‍රික් අම්ලය", "හයිඩ්‍රොක්ලෝරික් අම්ලය (HCl)", "ඇසිටික් අම්ලය"), 2)
        8 -> Pentuple("Which vitamin is produced in our skin when exposed to morning sunlight?", "සූර්යාලෝකයට සම නිරාවරණය වන විට ශරීරය තුළ නිපදවෙන විටමින් වර්ගය කුමක්ද?", listOf("Vitamin A", "Vitamin B", "Vitamin C", "Vitamin D"), listOf("විටමින් A", "විටමින් B", "විටමින් C", "විටමින් D"), 3)
        else -> Pentuple("Where is bile, which helps digest fats, produced?", "මේද ජීර්ණයට උදව් වන පිත (bile) නිපදවනු ලබන්නේ කොහේද?", listOf("Stomach", "Pancreas", "Liver", "Gallbladder directly"), listOf("ආමාශය", "අගන්‍යාශය", "අක්මාව (Liver)", "පිත්තාශය කෙලින්ම"), 2)
    }

    private fun getGrade7Stage2(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("What is the center of an atom containing protons and neutrons called?", "ප්‍රෝටෝන සහ නියුට්‍රෝන අඩංගු පරමාණුවක මධ්‍යය හඳුන්වන්නේ කුමක් ලෙසද?", listOf("Electron cloud", "Nucleus", "Orbit", "Molecule"), listOf("ඉලෙක්ට්‍රෝන වලාකුළ", "න්‍යෂ්ටිය (Nucleus)", "කක්ෂය", "අණුව"), 1)
        1 -> Pentuple("What negatively charged subatomic particles orbit around the nucleus?", "පරමාණුක න්‍යෂ්ටිය වටා කක්ෂවල ගමන් කරන සෘණ ආරෝපිත අංශු මොනවාද?", listOf("Protons", "Neutrons", "Electrons", "Positrons"), listOf("ප්‍රෝටෝන", "නියුට්‍රෝන", "ඉලෙක්ට්‍රෝන (Electrons)", "පොසිට්‍රෝන"), 2)
        2 -> Pentuple("Which subatomic particle has a positive charge?", "පරමාණුවේ ඇති ධන ආරෝපිත අංශුව කුමක්ද?", listOf("Electron", "Neutron", "Proton", "Quark"), listOf("ඉලෙක්ට්‍රෝනය", "නියුට්‍රෝනය", "ප්‍රෝටෝනය (Proton)", "ක්වාක්"), 2)
        3 -> Pentuple("Which particle inside an atom's nucleus has no electrical charge?", "පරමාණු න්‍යෂ්ටිය තුළ ඇති ආරෝපණයක් රහිත (උදාසීන) අංශුව කුමක්ද?", listOf("Proton", "Electron", "Neutron", "Ion"), listOf("ප්‍රෝටෝනය", "ඉලෙක්ට්‍රෝනය", "නියුට්‍රෝනය (Neutron)", "අයනය"), 2)
        4 -> Pentuple("Which of the following is a physical change?", "පහත දැක්වෙන දේවලින් භෞතික විපර්යාසයක් වන්නේ කුමක්ද?", listOf("Rusting of iron", "Burning of paper", "Melting of ice", "Digesting food"), listOf("යකඩ මලකෑම", "කඩදාසි පිළිස්සීම", "අයිස් දියවීම (Melting of ice)", "ආහාර ජීර්ණය"), 2)
        5 -> Pentuple("Which of the following represents a chemical change?", "නව ද්‍රව්‍යයක් සෑදෙන රසායනික විපර්යාසයක් නිරූපණය කරන්නේ කුමකින්ද?", listOf("Tearing paper", "Breaking glass", "Rusting of iron", "Boiling water"), listOf("කඩදාසි ඉරීම", "වීදුරු කැඩීම", "යකඩ මලකෑම (Rusting)", "ජලය නැටවීම"), 2)
        6 -> Pentuple("Which of these is a homogeneous mixture of an element?", "පහත ඒවායින් සංයෝගයක් නොවන මූලද්‍රව්‍යයක් වන්නේ කුමක්ද?", listOf("Water", "Carbon Dioxide", "Oxygen Gas (O2)", "Sodium Chloride"), listOf("ජලය", "කාබන් ඩයොක්සයිඩ්", "ඔක්සිජන් වායුව (Oxygen)", "සෝඩියම් ක්ලෝරයිඩ්"), 2)
        7 -> Pentuple("If substance X has pH 3, what is its nature?", "යම් ද්‍රව්‍යයක pH අගය 3ක් නම් එහි ස්වභාවය කුමක්ද?", listOf("Acidic", "Basic / Alkaline", "Neutral", "Insoluble"), listOf("අම්ලික (Acidic)", "භාස්මික / ක්ෂාරීය", "උදාසීන", "අද්‍රාව්‍ය"), 0)
        8 -> Pentuple("Which substance is a neutral solution with pH close to 7?", "pH අගය 7 ආසන්නයේ පවතින උදාසීන ද්‍රාවණයක් වන්නේ කුමක්ද?", listOf("Lime juice", "Soap water", "Pure water", "Vinegar"), listOf("දෙහි යුෂ", "සබන් වතුර", "පිරිසිදු ජලය (Pure water)", "විනාකිරි"), 2)
        else -> Pentuple("Which of these base solutions is commonly used in soap manufacturing?", "සබන් නිෂ්පාදනයේදී සාමාන්‍යයෙන් ගන්නා ප්‍රබල භෂ්මය කුමක්ද?", listOf("Hydrochloric acid", "Sodium Hydroxide (NaOH)", "Citric acid", "Distilled water"), listOf("හයිඩ්‍රොක්ලෝරික් අම්ලය", "සෝඩියම් හයිඩ්‍රොක්සයිඩ් (NaOH)", "සිට්‍රික් අම්ලය", "ආස්‍රැත ජලය"), 1)
    }

    private fun getGrade7Stage3(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("What is the SI unit of heat energy?", "තාප ශක්තිය මැනීමේ SI ඒකකය කුමක්ද?", listOf("Celsius", "Joule", "Kelvin", "Calorie"), listOf("සෙල්සියස්", "ජූල් (Joule)", "කෙල්වින්", "කැලරි"), 1)
        1 -> Pentuple("What is the SI unit of thermodynamic temperature?", "තාපගතික උෂ්ණත්වය මැනීමේ SI ඒකකය කුමක්ද?", listOf("Celsius", "Fahrenheit", "Kelvin", "Watt"), listOf("සෙල්සියස්", "ෆැරන්හයිට්", "කෙල්වින් (Kelvin)", "වොට්"), 2)
        2 -> Pentuple("What instrument is used to measure temperature accurately?", "උෂ්ණත්වය නිවැරදිව මැනීමට ගන්නා උපකරණය කුමක්ද?", listOf("Barometer", "Thermometer", "Spectrometer", "Odometer"), listOf("වායු පීඩනමානය", "උෂ්ණත්වමානය (Thermometer)", "වර්ණාවලිමානය", "ඕඩොමීටරය"), 1)
        3 -> Pentuple("In light reflection, the angle of incidence is always equal to:", "ආලෝක පරාවර්තනයේදී පතන කෝණය සෑමවිටම සමාන වන්නේ:", listOf("Angle of refraction", "Angle of reflection", "90 degrees", "Critical angle"), listOf("වර්තන කෝණයට", "පරාවර්තන කෝණයට (Reflection angle)", "අංශක 90 කෝණයට", "අවධි කෝණයට"), 1)
        4 -> Pentuple("A smooth highly polished surface that reflects light systematically is a:", "ආලෝකය ක්‍රමානුකූලව පරාවර්තනය කරන සිනිඳු ඔප දැමූ මතුපිටක් හඳුන්වන්නේ:", listOf("Lens", "Prism", "Mirror", "Glass slide"), listOf("කාචය", "ප්‍රිස්මය", "කන්නාඩිය / දර්පණය (Mirror)", "වීදුරු තහඩුව"), 2)
        5 -> Pentuple("What temperature represents the freezing point of water on Celsius scale?", "සෙල්සියස් පරිමාණයේ ජලය අයිස් බවට පත්වන උෂ්ණත්වය (හිමාංකය) කුමක්ද?", listOf("0°C", "32°C", "100°C", "273°C"), listOf("0°C", "32°C", "100°C", "273°C"), 0)
        6 -> Pentuple("What is the boiling point of pure water at standard atmospheric pressure?", "සාමාන්‍ය වායුගෝලීය පීඩනයේදී පිරිසිදු ජලයේ තාපාංකය කොපමණද?", listOf("0°C", "50°C", "100°C", "120°C"), listOf("0°C", "50°C", "100°C", "120°C"), 2)
        7 -> Pentuple("How does heat from the Sun reach the Earth across empty space?", "හිස් අවකාශය මැදින් සූර්යයාගේ සිට පෘථිවියට තාපය ගමන් කරන ප්‍රධාන ක්‍රමය?", listOf("Conduction", "Convection", "Radiation", "Evaporation"), listOf("සන්නයනය", "සංවහනය", "විකිරණය (Radiation)", "වාෂ්පීභවනය"), 2)
        8 -> Pentuple("Which method of heat transfer occurs mainly in solid metals?", "ලෝහ වැනි ඝන ද්‍රව්‍යවල තාපය ගමන් කරන ප්‍රධාන ක්‍රමය කුමක්ද?", listOf("Conduction", "Convection", "Radiation", "Sublimation"), listOf("සන්නයනය (Conduction)", "සංවහනය", "විකිරණය", "ඌර්ධවපාතනය"), 0)
        else -> Pentuple("Heat transfer through the movement of fluid molecules (liquid or gas) is:", "ද්‍රව හෝ වායු අංශුවල සැබෑ චලනයෙන් සිදුවන තාප සම්ප්‍රේෂණය කුමක්ද?", listOf("Conduction", "Convection", "Radiation", "Insulation"), listOf("සන්නයනය", "සංවහනය (Convection)", "විකිරණය", "පරිවාරණය"), 1)
    }

    // GRADE 8
    private fun getGrade8Stage1(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("Which blood vessels carry oxygenated blood away from the heart to the body?", "හදවතේ සිට ශරීරය පුරා ඔක්සිජන් සහිත රුධිරය ගෙනයන රුධිර වාහිනී මොනවාද?", listOf("Veins", "Arteries", "Capillaries", "Venules"), listOf("ශිරා", "ධමනි (Arteries)", "කේශනාලිකා", "ශිරාංශ"), 1)
        1 -> Pentuple("What are the microscopic air sacs in human lungs where gas exchange occurs?", "මිනිස් පෙනහළුවල වායු හුවමාරුව සිදුවන කුඩාම වායු කෝෂ හඳුන්වන්නේ?", listOf("Bronchi", "Trachea", "Alveoli", "Diaphragm"), listOf("ශ්වාසනාල", "ශ්වාසනාලය", "ගෝලාකාර වායු කෝෂ (Alveoli)", "මහා ප්‍රාචීරය"), 2)
        2 -> Pentuple("Which organ is the primary excretory organ filtering blood to make urine?", "රුධිරය පෙරා මුත්‍රා නිපදවන ප්‍රධාන බහිස්ස්‍රාවී ඉන්ද්‍රිය කුමක්ද?", listOf("Liver", "Kidney", "Skin", "Lungs"), listOf("අක්මාව", "වකුගඩුව (Kidney)", "සම", "පෙනහැලි"), 1)
        3 -> Pentuple("What is the main nitrogenous waste excreted in human urine?", "මිනිස් මුත්‍රා සමඟ පිටවන ප්‍රධාන නයිට්‍රජන්මය බහිස්ස්‍රාවී ද්‍රව්‍යය කුමක්ද?", listOf("Uric acid", "Urea", "Ammonia", "Glucose"), listOf("යූරික් අම්ලය", "යුරියා (Urea)", "ඇමෝනියා", "ග්ලූකෝස්"), 1)
        4 -> Pentuple("Which chamber of the heart pumps blood to the entire body?", "හෘදයේ කුමන කුටීරයෙන් මුළු ශරීරයටම රුධිරය පොම්ප කරනු ලබයිද?", listOf("Right Atrium", "Left Atrium", "Right Ventricle", "Left Ventricle"), listOf("දකුණු කර්ණිකාව", "වම් කර්ණිකාව", "දකුණු කෝෂිකාව", "වම් කෝෂිකාව (Left Ventricle)"), 3)
        5 -> Pentuple("Which blood cells are responsible for carrying oxygen?", "ඔක්සිජන් පරිවහනය කිරීම භාරව ඇති රුධිර සෛල වර්ගය කුමක්ද?", listOf("White Blood Cells", "Red Blood Cells", "Platelets", "Lymphocytes"), listOf("ශ්වේත රුධිර සෛල", "රතු රුධිර සෛල (Red Blood Cells)", "රුධිර පට්ටිකා", "ලින්ෆොසයිට"), 1)
        6 -> Pentuple("What component of blood is essential for blood clotting to stop bleeding?", "රුධිරය කැටි ගැසී ලේ ගැලීම නැවැත්වීමට අත්‍යවශ්‍ය වන රුධිර සෛල වර්ගය?", listOf("Red cells", "White cells", "Platelets", "Plasma"), listOf("රතු සෛල", "ශ්වේත සෛල", "රුධිර පට්ටිකා (Platelets)", "ප්ලාස්මය"), 2)
        7 -> Pentuple("Which blood vessel carries deoxygenated blood back to the heart?", "ඔක්සිජන් රහිත රුධිරය නැවත හදවත දෙසට රැගෙන එන රුධිර වාහිනී?", listOf("Arteries", "Veins", "Aorta", "Arterioles"), listOf("ධමනි", "ශිරා (Veins)", "මහා ධමනිය", "ධමනිකා"), 1)
        8 -> Pentuple("What is the tube that connects kidneys to the urinary bladder?", "වකුගඩුවේ සිට මුත්‍රාශය දක්වා මුත්‍රා රැගෙන යන නළය හඳුන්වන්නේ?", listOf("Urethra", "Ureter", "Nephron", "Arteriole"), listOf("මුත්‍ර මාර්ගය", "මුත්‍ර වාහිනිය (Ureter)", "නෙෆ්‍රෝනය", "ධමනිකාව"), 1)
        else -> Pentuple("What function do White Blood Cells perform in our body?", "ශ්වේත රුධිර සෛල මඟින් සිරුර තුළ ඉටුකරන ප්‍රධාන කාර්යය කුමක්ද?", listOf("Carry oxygen", "Clot blood", "Defend body against pathogens", "Store fats"), listOf("ඔක්සිජන් ගෙනයාම", "ලේ කැටගැසීම", "රෝග කාරකයන්ගෙන් සිරුර ආරක්ෂා කිරීම", "තෙල් ගබඩා කිරීම"), 2)
    }

    private fun getGrade8Stage2(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("What three things are required for combustion (fire tetrahedron basictrio)?", "දහනය (ගින්නක් ඇතිවීම) සඳහා අවශ්‍ය ප්‍රධාන සාධක තුන කුමක්ද?", listOf("Oxygen, Heat, Fuel", "Nitrogen, Fuel, Water", "Carbon dioxide, Heat, Oxygen", "Fuel, Wind, Carbon"), listOf("ඔක්සිජන්, තාපය, ඉන්ධන (Oxygen/Heat/Fuel)", "නයිට්‍රජන්, ඉන්ධන, ජලය", "කාබන් ඩයොක්සයිඩ්, තාපය, ඔක්සිජන්", "ඉන්ධන, සුළඟ, කාබන්"), 0)
        1 -> Pentuple("What gas is a supporter of combustion?", "දහනය සඳහා අනුබල දෙන (උදව් කරන) ප්‍රධාන වායුව කුමක්ද?", listOf("Carbon dioxide", "Oxygen", "Nitrogen", "Hydrogen"), listOf("කාබන් ඩයොක්සයිඩ්", "ඔක්සිජන් (Oxygen)", "නයිට්‍රජන්", "හයිඩ්‍රජන්"), 1)
        2 -> Pentuple("What gas is commonly used in fire extinguishers to put out fire?", "ගිනි නිවන උපකරණවල ගින්න නිවීම සඳහා බහුලව ගන්නා වායුව කුමක්ද?", listOf("Oxygen", "Hydrogen", "Carbon Dioxide", "Helium"), listOf("ඔක්සිජන්", "හයිඩ්‍රජන්", "කාබන් ඩයොක්සයිඩ් (CO2)", "හීලියම්"), 2)
        3 -> Pentuple("What is the SI unit of force?", "බලය මැනීමේ SI ඒකකය කුමක්ද?", listOf("Joule", "Watt", "Newton", "Pascal"), listOf("ජූල්", "වොට්", "නිව්ටන් (Newton)", "පැස්කල්"), 2)
        4 -> Pentuple("Pressure is mathematically defined as:", "පීඩනය (Pressure) සෙවීමේ සූත්‍රය කුමක්ද?", listOf("Force * Area", "Force / Area", "Mass * Acceleration", "Velocity * Time"), listOf("බලය * වර්ගඵලය", "බලය / වර්ගඵලය (Force / Area)", "ස්කන්ධය * ත්වරණය", "ප්‍රවේගය * කාලය"), 1)
        5 -> Pentuple("What is the SI unit of pressure?", "පීඩනය මැනීමේ SI ඒකකය කුමක්ද?", listOf("Newton", "Pascal", "Joule", "Ohm"), listOf("නිව්ටන්", "පැස්කල් (Pascal)", "ජූල්", "ඕම්"), 1)
        6 -> Pentuple("How does pressure change as the surface area decreases for same force?", "යොදන බලය සමාන වන විට වර්ගඵලය අඩු වන විට පීඩනයට කුමක් සිදුවේද?", listOf("Decreases", "Increases", "Stays same", "Becomes zero"), listOf("අඩු වේ", "වැඩි වේ (Increases)", "එලෙසම පවතී", "ශුන්‍ය වේ"), 1)
        7 -> Pentuple("Why do heavy trucks have wide tyres?", "බර වාහනවල ටයර් පළලින් වැඩි කර ඇත්තේ මන්ද?", listOf("To increase speed", "To decrease pressure on road by increasing area", "To reduce fuel usage", "For active styling"), listOf("වේගය වැඩි කිරීමට", "වර්ගඵලය වැඩි කිරීමෙන් පාර මත පීඩනය අඩු කිරීමට", "ඉන්ධන ඉතිරි කිරීමට", "අලංකාරත්වයට"), 1)
        8 -> Pentuple("What substance is released during complete combustion of hydrocarbon fuels?", "හයිඩ්‍රොකාබන් ඉන්ධන සම්පූර්ණ දහනයේදී ලැබෙන අතුරු ඵල මොනවාද?", listOf("Carbon monoxide and Carbon", "Carbon dioxide and Water vapor", "Hydrogen and Nitrogen", "Oxygen only"), listOf("කාබන් මොනොක්සයිඩ් සහ කාබන්", "කාබන් ඩයොක්සයිඩ් සහ ජල වාෂ්ප (CO2/Water)", "හයිඩ්‍රජන් සහ නයිට්‍රජන්", "ඔක්සිජන් පමණක්"), 1)
        else -> Pentuple("What temperature must a substance reach for it to catch fire?", "ද්‍රව්‍යයක් ගිනි ගැනීම සඳහා ලඟා විය යුතු අවම උෂ්ණත්වය කුමක්ද?", listOf("Boiling point", "Freezing point", "Ignition temperature", "Melting point"), listOf("තාපාංකය", "හිමාංකය", "ජ්වලන උෂ්ණත්වය (Ignition temp)", "ද්‍රවාංකය"), 2)
    }

    private fun getGrade8Stage3(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("What resistance force opposes motion when two surfaces slide over each other?", "පෘෂ්ඨ දෙකක් එකිනෙක ගැටෙමින් චලනය වීමට බාධා කරන බලය කුමක්ද?", listOf("Gravity", "Friction", "Tension", "Air resistance"), listOf("ගුරුත්වාකර්ෂණය", "ඝර්ෂණය (Friction)", "ප්‍රත්‍යස්ථතාව", "වායු ප්‍රතිරෝධය"), 1)
        1 -> Pentuple("Which of the following reduces friction?", "පහත දැක්වෙන දේවලින් ඝර්ෂණය අඩු කිරීමට උපකාරී වන්නේ කුමක්ද?", listOf("Making surface rough", "Applying lubricants like oil/grease", "Increasing weight", "Using rubber tracks"), listOf("පෘෂ්ඨය රළු කිරීම", "ලිහිසි තෙල් හෝ ග්‍රීස් තැවරීම (Lubricants)", "බර වැඩි කිරීම", "රබර් භාවිතය"), 1)
        2 -> Pentuple("How does sound travel from one place to another?", "ශබ්දය එක් ස්ථානයක සිට තවත් ස්ථානයකට ගමන් කරන්නේ කෙසේද?", listOf("Through vacuum only", "As longitudinal waves through medium", "As light particles", "Through space instantly"), listOf("ශුන්‍යය හරහා පමණක්", "මාධ්‍යයක් හරහා අන්වායාම තරංග ලෙස (Waves)", "ආලෝක අංශු ලෙස", "එසැනින් හිස් අවකාශය හරහා"), 1)
        3 -> Pentuple("Can sound travel through a vacuum (empty space with no air)?", "වාතය නොමැති ශුන්‍ය අවකාශයක් (Vacuum) හරහා ශබ්දයට ගමන් කළ හැකිද?", listOf("Yes", "No", "Only high frequency", "Only low frequency"), listOf("හැක", "නොහැක (No)", "ඉහළ සංඛ්‍යාත පමණක්", "අඩු සංඛ්‍යාත පමණක්"), 1)
        4 -> Pentuple("The pitch of a sound is determined by its:", "ශබ්දයක තාරතාව (Pitch) තීරණය වන්නේ එහි ඇති කුමන ලක්ෂණය මතද?", listOf("Amplitude", "Frequency", "Speed", "Loudness"), listOf("විස්තාරය", "සංඛ්‍යාතය (Frequency)", "වේගය", "තීව්‍රතාවය"), 1)
        5 -> Pentuple("What is the SI unit of frequency of sound?", "සංඛ්‍යාතය මැනීමේ SI ඒකකය කුමක්ද?", listOf("Decibel", "Hertz (Hz)", "Meter", "Pascal"), listOf("ඩෙසිබෙල්", "හර්ට්ස් - Hz (Hertz)", "මීටර", "පැස්කල්"), 1)
        6 -> Pentuple("Loudness of sound is mainly determined by its:", "ශබ්දයක සැර බව හෙවත් තීව්‍රතාවය තීරණය කරන ප්‍රධාන සාධකය?", listOf("Frequency", "Velocity", "Amplitude", "Wavelength"), listOf("සංඛ්‍යාතය", "ප්‍රවේගය", "විස්තාරය (Amplitude)", "තරංග ආයාමය"), 2)
        7 -> Pentuple("Which material can absorb sound well to prevent echoes?", "ප්‍රතිරාවය වැළැක්වීමට ශබ්දය හොඳින් අවශෝෂණය කරන ද්‍රව්‍යයක් කුමක්ද?", listOf("Concrete", "Glass", "Soft curtains/foams", "Iron sheet"), listOf("කොන්ක්‍රීට්", "වීදුරු", "මෘදු තිර රෙදි / ෆෝම් (Soft foam)", "යකඩ තහඩුව"), 2)
        8 -> Pentuple("What is the speed of sound in dry air at room temperature approximately?", "කාමර උෂ්ණත්වයේදී වියළි වාතය තුළ ශබ්දයේ වේගය තත්පරයට මීටර් කීයක් පමණද?", listOf("3 x 10^8 m/s", "340 m/s", "1500 m/s", "5000 m/s"), listOf("තත්පරයට මීටර් මිලියන 300 ක්", "තත්පරයට මීටර් 340 ක් පමණ (340 m/s)", "තත්පරයට මීටර් 1500 ක්", "තත්පරයට මීටර් 5000 ක්"), 1)
        else -> Pentuple("Which state of matter conducts sound fastest?", "ශබ්දය වේගයෙන්ම ගමන් කරන්නේ කුමන පදාර්ථ අවස්ථාව හරහාද?", listOf("Gas", "Liquid", "Solid", "Vacuum"), listOf("වායු", "ද්‍රව", "ඝන (Solid)", "හිස් අවකාශය"), 2)
    }

    // GRADE 9
    private fun getGrade9Stage1(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("What cell division process produces gametes (sperm and egg cells) with half the chromosome number?", "වර්ණදේහ සංඛ්‍යාව අඩක් කරමින් ලිංගික සෛල නිපදවන සෛල බෙදීමේ ආකාරය කුමක්ද?", listOf("Mitosis", "Meiosis", "Binary fission", "Budding"), listOf("අනුනන විභාජනය", "ඌනන විභාජනය (Meiosis)", "ද්විඛණ්ඩනය", "මුකුලනය"), 1)
        1 -> Pentuple("The scientific study of heredity and variation of inherited characteristics is:", "ප්‍රවේණිය සහ විචලනය පිළිබඳව කෙරෙන විද්‍යාත්මක අධ්‍යයනය කුමක්ද?", listOf("Ecology", "Genetics", "Microbiology", "Taxonomy"), listOf("පරිසර විද්‍යාව", "ප්‍රවේණි විද්‍යාව (Genetics)", "ක්ෂුද්‍ර ජීව විද්‍යාව", "වර්ගීකරණය"), 1)
        2 -> Pentuple("Who is considered the Father of Genetics for his pea plant experiments?", "මෑ පැළ අත්හදාබැලීම් නිසා ප්‍රවේණි විද්‍යාවේ පියා ලෙස සලකනු ලබන්නේ කවුද?", listOf("Charles Darwin", "Gregor Mendel", "Louis Pasteur", "Robert Hooke"), listOf("චාල්ස් ඩාවින්", "ග්‍රෙගෝර් මෙන්ඩල් (Gregor Mendel)", "ලුවී පාස්චර්", "රොබට් හුක්"), 1)
        3 -> Pentuple("Where is genetic information (DNA) stored inside a eukaryotic cell?", "යුකැරියෝටික සෛලයක ප්‍රවේණි තොරතුරු (DNA) ගබඩා කර ඇත්තේ කොහේද?", listOf("Cell wall", "Mitochondria", "Nucleus", "Ribosome"), listOf("සෛල බිත්තියේ", "මයිටොකොන්ඩ්‍රියාවේ", "න්‍යෂ්ටිය (Nucleus)", "රයිබොසෝමයේ"), 2)
        4 -> Pentuple("What cell division process is responsible for growth and tissue repair, keeping chromosomes same?", "වර්ණදේහ ගණන වෙනස් නොකරමින් ශරීර වර්ධනයට හා පටක අලුත්වැඩියාවට දායක වන සෛල බෙදීම?", listOf("Meiosis", "Mitosis", "Fragmentation", "Osmosis"), listOf("ඌනනය", "අනුනනය (Mitosis)", "ඛණ්ඩනය", "ආශ්‍රැතය"), 1)
        5 -> Pentuple("How many chromosomes are in a normal human somatic somatic body cell?", "සාමාන්‍ය නිරෝගී මිනිසෙකුගේ දේහ සෛලයක ඇති වර්ණදේහ ගණන කොපමණද?", listOf("23 pairs (46)", "46 pairs (92)", "12 pairs (24)", "4 pair"), listOf("යුගල 23ක් - 46ක් (46)", "යුගල 46ක්", "යුගල 12ක්", "යුගල 4ක්"), 0)
        6 -> Pentuple("Which molecule has double helix structure carrying all genetic codes?", "ද්විත්ව හෙලික්ස් හැඩයක් ඇති ප්‍රවේණික තොරතුරු රැගෙන යන අණුව කුමක්ද?", listOf("RNA", "DNA", "Protein", "Glucose"), listOf("RNA", "DNA", "ප්‍රෝටීන්", "ග්ලූකෝස්"), 1)
        7 -> Pentuple("What is alternative form of a gene located at specific locus on chromosomes?", "වර්ණදේහවල යම් නිශ්චිත ස්ථානයක එකම ලක්ෂණයක් පාලනය කරන විකල්ප ආකාරය?", listOf("Allele", "Zygote", "Nucleotide", "Phosphate"), listOf("ඇලීලය (Allele)", "යුක්තානුව", "නියුක්ලියෝටයිඩය", "පොස්පේට්"), 0)
        8 -> Pentuple("Gametes produced by meiosis are genetically identical or different?", "ඌනන විභාජනයෙන් බිහිවන ජන්ම ප්ලාස්ම සෛල ප්‍රවේණිකව සමානද ද්විලක්ෂණද?", listOf("100% Identical", "Genetically different due to crossing over", "Have double chromosomes", "Vary based on diet"), listOf("සම්පූර්ණ සමානයි", "ප්‍රවේණිකව වෙනස් වේ (different)", "ද්විත්ව වර්ණදේහ පවතී", "ආහාර මත වෙනස් වේ"), 1)
        else -> Pentuple("A segment of DNA on chromosomes carrying instructions for single protein is a:", "කිසියම් ප්‍රෝටීනයක් නිපදවීමට තොරතුරු සපයන DNA අණුවක කොටසක් හඳුන්වන්නේ:", listOf("Chromatid", "Centromere", "Gene", "Nucleosome"), listOf("ක්‍රොමැටිඩය", "සෙන්ට්‍රොමියරය", "ජානය (Gene)", "නියුක්ලියෝසෝමය"), 2)
    }

    private fun getGrade9Stage2(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("What are microscopic organisms like bacteria, viruses, and fungi called?", "බැක්ටීරියා, වෛරස්, දිලීර වැනි ඉතා කුඩා ඇසට නොපෙනෙන ජීවීන් හඳුන්වන්නේ?", listOf("Plants", "Animals", "Microorganisms", "Minerals"), listOf("ශාක", "සතුන්", "ක්ෂුද්‍ර ජීවීන් (Microorganisms)", "ඛනිජ"), 2)
        1 -> Pentuple("Which microorganism is used in bread making to ferment sugar and raise dough?", "පාන් සහ බනිස් පිපිරීම සඳහා පාවිච්චි කරන දිලීර වර්ගය කුමක්ද?", listOf("Amoeba", "Yeast", "Lactobacillus", "Penicillium"), listOf("ඇමීබා", "ඊස්ට් (Yeast)", "ලැක්ටොබැසිලස්", "පෙනිසිලියම්"), 1)
        2 -> Pentuple("What are disease-causing microorganisms called?", "ලෙඩ රෝග බෝ කරන හානිකර ක්ෂුද්‍ර ජීවීන් හඳුන්වන්නේ කුමක් ලෙසද?", listOf("Antibiotics", "Pathogens", "Decomposers", "Probiotics"), listOf("ප්‍රතිජීවක", "රෝග කාරකයන් (Pathogens)", "වියෝජකයන්", "ප්‍රෝබයෝටික"), 1)
        3 -> Pentuple("Which medicine is used to kill or inhibit bacterial growth?", "බැක්ටීරියා වර්ධනය නැවැත්වීමට හෝ විනාශ කිරීමට දෙන ඖෂධ වර්ග මොනවාද?", listOf("Vaccines", "Antibiotics", "Antivirals", "Vitamins"), listOf("එන්නත්", "ප්‍රතිජීවක (Antibiotics)", "ප්‍රතිවෛරස් ඖෂධ", "විටමින්"), 1)
        4 -> Pentuple("What non-cellular entity requires a host cell to replicate and cause flu?", "ස්වකීය වෛරස් සෛලයක් නොමැති සත්කාරක සෛලයක් තුළ පමණක් ප්‍රජනනය කරන ඒකකය?", listOf("Protozoa", "Bacteria", "Virus", "Algae"), listOf("ප්‍රොටසෝවා", "බැක්ටීරියා", "වෛරස් (Virus)", "ඇල්ගී"), 2)
        5 -> Pentuple("Which bacterium is responsible for turning milk into curd?", "කිරි මුදවමින් යෝගට් හා මුදවාපු කිරි සාදා ගැනීමට දායක වන බැක්ටීරියාව?", listOf("E. coli", "Lactobacillus", "Salmonella", "Yeast"), listOf("ඊ කොලායි", "ලැක්ටොබැසිලස් (Lactobacillus)", "සැල්මොනෙල්ලා", "ඊස්ට්"), 1)
        6 -> Pentuple("An ecosystem consists of which components interacting?", "පරිසර පද්ධතියක් සමන්විත වන්නේ කුමන සාධක එකිනෙක අන්තර්ක්‍රියා කිරීමෙන්ද?", listOf("Only living things", "Biotic (living) and Abiotic (non-living) factors", "Only non-living things", "Water and Soil only"), listOf("සජීවී සාධක පමණක්", "ජීවී හා අජීවී සාධක දෙකම (Biotic and Abiotic)", "අජීවී සාධක පමණක්", "ජලය සහ පස පමණක්"), 1)
        7 -> Pentuple("What is the primary source of carbon for plants in an ecosystem?", "පරිසර පද්ධතියක ශාකවලට කාබන් ලබා දෙන ප්‍රධාන වායුගෝලීය වායුව කුමක්ද?", listOf("Oxygen", "Hydrogen", "Carbon Dioxide", "Nitrogen"), listOf("ඔක්සිජන්", "හයිඩ්‍රජන්", "කාබන් ඩයොක්සයිඩ් (CO2)", "නයිට්‍රජන්"), 2)
        8 -> Pentuple("What chemical reaction style absorbs energy from surroundings?", "පරිසරයෙන් තාපය අවශෝෂණය කරමින් සිදුවන රසායනික ප්‍රතික්‍රියාව කුමක්ද?", listOf("Exothermic", "Endothermic", "Decomposition", "Combustion"), listOf("තාපදායක ප්‍රතික්‍රියාව", "තාප අවශෝෂක ප්‍රතික්‍රියාව (Endothermic)", "වියෝජනය", "දහනය"), 1)
        else -> Pentuple("A chemical reaction that releases energy in the form of heat is:", "පරිසරයට තාපය මුදා හරිමින් සිදුවන රසායනික ප්‍රතික්‍රියාව?", listOf("Endothermic", "Exothermic", "Neutralization", "Precipitation"), listOf("තාප අවශෝෂක", "තාපදායක ප්‍රතික්‍රියාව (Exothermic)", "උදාසීනකරණය", "අවක්ෂේපණය"), 1)
    }

    private fun getGrade9Stage3(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("What is the rate of change of displacement with time called?", "ස්ථාන මාරුවීම හෙවත් විස්ථාපනය වෙනස් වීමේ සීඝ්‍රතාවය කුමක්ද?", listOf("Speed", "Velocity", "Acceleration", "Distance"), listOf("වේගය", "ප්‍රවේගය (Velocity)", "ත්වරණය", "දුර"), 1)
        1 -> Pentuple("What is defined as the rate of change of velocity?", "ප්‍රවේගය වෙනස් වීමේ සීඝ්‍රතාවය හඳුන්වන්නේ කුමක් ලෙසද?", listOf("Speed", "Displacement", "Acceleration", "Momentum"), listOf("වේගය", "විස්ථාපනය", "ත්වරණය (Acceleration)", "ගම්‍යතාවය"), 2)
        2 -> Pentuple("State Newton's First Law of Motion concept:", "නිව්ටන්ගේ පළමු චලිත නියමයේ අඩංගු මූලික සංකල්පය කුමක්ද?", listOf("Inertia", "F = ma", "Action and Reaction", "Gravity"), listOf("අස්ථිතිකත්වය / ආවස්ථිතිය (Inertia)", "F = ma", "ක්‍රියාව සහ ප්‍රතික්‍රියාව", "ගුරුත්වාකර්ෂණය"), 0)
        3 -> Pentuple("What is the formula representing Newton's Second Law of Motion?", "නිව්ටන්ගේ දෙවන චලිත නියමය ගණිතමය ලෙස දක්වන්නේ කෙසේද?", listOf("F = m/a", "F = ma", "P = F/A", "v = u + at"), listOf("F = m/a", "F = ma", "P = F/A", "v = u + at"), 1)
        4 -> Pentuple("What does Newton's Third Law statement describe?", "නිව්ටන්ගේ තෙවන චලිත නියමයෙන් ප්‍රකාශ වන්නේ කුමක්ද?", listOf("Every object has inertia", "Force is mass times acceleration", "For every action, there is an equal and opposite reaction", "Gravity pulls down"), listOf("සෑම වස්තුවකම ආවස්ථිතියක් පවතී", "බලය යනු ස්කන්ධය සහ ත්වරණයේ ගුණිතයයි", "සෑම ක්‍රියාවකටම සමාන වූත් ප්‍රතිවිරුද්ධ වූත් ප්‍රතික්‍රියාවක් පවතී", "බලය පහළට අදියි"), 2)
        5 -> Pentuple("Distance is a scalar quantity. What is displacement?", "දුර යනු අදිශ රාශියකි. විස්ථාපනය යනු කුමන රාශියක්ද?", listOf("Scalar", "Vector", "Dimensionless", "Chemical"), listOf("අදිශ රාශියක්", "දෛශික රාශියක් (Vector)", "රාශි රහිත", "රසායනික"), 1)
        6 -> Pentuple("What is the SI unit of velocity?", "ප්‍රවේගය මැනීමේ SI ඒකකය කුමක්ද?", listOf("m/s", "m/s^2", "km/h", "N/kg"), listOf("තත්පරයට මීටර් (m/s)", "m/s^2", "කිලෝමීටර/පැය", "N/kg"), 0)
        7 -> Pentuple("What is the SI unit of acceleration?", "ත්වරණය මැනීමේ SI ඒකකය කුමක්ද?", listOf("m/s", "m/s^2", "N", "Joules"), listOf("m/s", "m/s^2 (තත්පර වර්ගයට මීටර්)", "නිව්ටන්", "ජූල්ස"), 1)
        8 -> Pentuple("If a car travels a distance of 100 meters in 5 seconds, what is its average speed?", "මෝටර් රථයක් තත්පර 5කදී මීටර් 100ක දුරක් ගමන් කරන්නේ නම් එහි සාමාන්‍ය වේගය කොපමණද?", listOf("10 m/s", "20 m/s", "50 m/s", "500 m/s"), listOf("තත්පරයට මීටර් 10", "තත්පරයට මීටර් 20 (20 m/s)", "තත්පරයට මීටර් 50", "තත්පරයට මීටර් 500"), 1)
        else -> Pentuple("What force pulls falling objects down to the ground?", "පතිත වන වස්තූන් පොළොව දෙසට ආකර්ෂණය කරගන්නා බලය කුමක්ද?", listOf("Magnetic", "Gravitational", "Friction", "Electrostatic"), listOf("චුම්භක බලය", "ගුරුත්වාකර්ෂණ බලය (Gravitational)", "ඝර්ෂණ බලය", "ස්ථිති විද්‍යුත් බලය"), 1)
    }

    // GRADE 10
    private fun getGrade10Stage1(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("What cross was Mendel analyzing specifically in genetics representing single traits?", "මෙන්ඩල් විසින් එක් ලක්ෂණයක් පමණක් සලකා කරන ලද ප්‍රවේණි සන්කරය කුමක්ද?", listOf("Monohybrid cross", "Dihybrid cross", "Trihybrid cross", "Test cross"), listOf("ඒකමුහුම් දෙමුහුම්කරණය (Monohybrid)", "ද්විමුහුම්කරණය", "ත්‍රිමුහුම්කරණය", "පරීක්‍ෂා මුහුම"), 0)
        1 -> Pentuple("Which nitrogenous base is found in RNA but NOT in DNA?", "DNA වල නොමැති RNA වල පමණක් පවතින නයිට්‍රජන්මය භෂ්මය කුමක්ද?", listOf("Adenine", "Thymine", "Uracil", "Cytosine"), listOf("ඇඩිනින්", "තයිමින්", "යුරැසිල් (Uracil)", "සයිටොසින්"), 2)
        2 -> Pentuple("What are the repeating structural units of DNA?", "DNA අණුවක නැවත නැවත පිහිටන මූලික රසායනික ව්‍යුහාත්මක ඒකකය කුමක්ද?", listOf("Amino Acids", "Nucleotides", "Fatty acids", "Monosaccharides"), listOf("ඇමයිනෝ අම්ල", "නියුක්ලියෝටයිඩ (Nucleotides)", "මේද අම්ල", "මොනොසැකරයිඩ"), 1)
        3 -> Pentuple("In genetics, what term describes having two identical alleles for particular gene?", "යම් ජානයකට අදාළව සමාන ඇලීල දෙකක් පිහිටීම (උදා: TT) හඳුන්වන්නේ කුමක් ලෙසද?", listOf("Heterozygous", "Homozygous", "Hemizygous", "Dominant only"), listOf("විෂමයුග්මක", "සමයුග්මක (Homozygous)", "අර්ධයුග්මක", "ප්‍රමුඛ පමණක්"), 1)
        4 -> Pentuple("If both alleles differ, like Tt, what is this state called?", "ඇලීල දෙක එකිනෙකට වෙනස් වන විට (උදා: Tt) එම තත්ත්වය හඳුන්වන්නේ:", listOf("Homozygous", "Heterozygous", "Phenotype", "Genotype"), listOf("සමයුග්මක", "විෂමයුග්මක (Heterozygous)", "දෘශ්‍යකාණ්ඩය", "ජානකාණ්ඩය"), 1)
        5 -> Pentuple("Which scientist proposed the Double Helix model of DNA alongside Francis Crick?", "ෆ්‍රැන්සිස් ක්‍රික් සමඟ එකතුවී DNA හි ද්විත්ව හෙලික්ස් ආකෘතිය ඉදිරිපත් කළේ කවුද?", listOf("Gregor Mendel", "James Watson", "Louis Pasteur", "Charles Darwin"), listOf("ග්‍රෙගෝර් මෙන්ඩල්", "ජේම්ස් වොට්සන් (James Watson)", "ලුවී පාස්චර්", "චාල්ස් ඩාවින්"), 1)
        6 -> Pentuple("What are the biological catalysts that speed up chemical reactions in cells?", "සෛල තුළ සිදුවන රසායනික ප්‍රතික්‍රියාවල වේගය වැඩි කරන ජෛව උත්ප්‍රේරක මොනවාද?", listOf("Hormones", "Enzymes", "Vitamins", "Carbohydrates"), listOf("හෝමෝන", "එන්සයිම (Enzymes)", "විටමින්", "කාබෝහයිඩ්‍රේට්"), 1)
        7 -> Pentuple("The observable physical properties of an organism (like tall/short) is its:", "ජීවියෙකුගේ පිටතට පෙනෙන භෞතික ගතිලක්ෂණ හඳුන්වන්නේ කුමක් ලෙසද?", listOf("Genotype", "Phenotype", "Dominance", "Recessiveness"), listOf("ජාන දර්ශකය", "දෘශ්‍ය දර්ශකය (Phenotype)", "ප්‍රමුඛතාව", "ප්‍රතිඡාදිතාව"), 1)
        8 -> Pentuple("How many hydrogen bonds form between Adenine and Thymine in DNA?", "DNA අණුවේ ඇඩිනින් (A) සහ තයිමින් (T) අතර සෑදෙන හයිඩ්‍රජන් බන්ධන ගණන?", listOf("1", "2", "3", "4"), listOf("1", "2", "3", "4"), 1)
        else -> Pentuple("Inherited disorders like color blindness are carried on which chromosomes?", "වර්ණ අන්ධභාවය වැනි ප්‍රවේණි ආබාධ රැගෙන යන්නේ කුමන වර්ණදේහ මතද?", listOf("Autosomes", "Sex chromosomes (X-linked)", "Y chromosome only", "Mitochondrial"), listOf("දේහ වර්ණදේහ", "ලිංගික වර්ණදේහ (X-linked)", "Y වර්ණදේහය පමණක්", "මයිටොකොන්ඩ්‍රීය"), 1)
    }

    private fun getGrade10Stage2(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("Which class of organic compounds contains only carbon and hydrogen elements?", "කාබන් සහ හයිඩ්‍රොජන් පමණක් අඩංගු කාබනික සංයෝග කාණ්ඩය කුමක්ද?", listOf("Alcohols", "Hydrocarbons", "Esters", "Carbohydrates"), listOf("ඇල්කොහොල", "හයිඩ්‍රොකාබන (Hydrocarbons)", "එස්ටර", "කාබෝහයිඩ්‍රේට"), 1)
        1 -> Pentuple("What is the general formula of Alkanes?", "ඇල්කේනවල (Alkanes) පොදු සූත්‍රය කුමක්ද?", listOf("CnH2n", "CnH2n+2", "CnH2n-2", "CnH2n+1OH"), listOf("CnH2n", "CnH2n+2", "CnH2n-2", "CnH2n+1OH"), 1)
        2 -> Pentuple("What unsaturated hydrocarbons have at least one carbon-carbon double bond?", "අඩුම තරමින් එක කාබන්-කාබන් ද්විත්ව බන්ධනයක් ඇති අසංතෘප්ත හයිඩ්‍රොකාබන?", listOf("Alkanes", "Alkenes", "Alkynes", "Alcohols"), listOf("ඇල්කේන", "ඇල්කීන (Alkenes)", "ඇල්කයින", "ඇල්කොහොල"), 1)
        3 -> Pentuple("Hydrocarbons containing triple bonds between carbon atoms are called:", "කාබන් පරමාණු අතර ත්‍රිත්ව බන්ධන සහිත හයිඩ්‍රොකාබන හඳුන්වන්නේ:", listOf("Alkanes", "Alkenes", "Alkynes", "Arenes"), listOf("ඇල්කේන", "ඇල්කීන", "ඇල්කයින (Alkynes)", "ඇරීන"), 2)
        4 -> Pentuple("What is the simplest alkane compound?", "සරලම ඇල්කේන සංයෝගය කුමක්ද?", listOf("Ethane", "Propane", "Methane", "Butane"), listOf("ඊතේන්", "ප්‍රොපේන්", "මෙතේන් (Methane)", "බියුටේන්"), 2)
        5 -> Pentuple("Which functional group is present in all organic alcohols?", "සියලුම කාබනික ඇල්කොහොලවල අන්තර්ගත වන ක්‍රියාකාරී සමූහය කුමක්ද?", listOf("Carboxyl (-COOH)", "Hydroxyl (-OH)", "Aldehyde (-CHO)", "Amino (-NH2)"), listOf("කාබොක්සිල් සමූහය", "හයිඩ්‍රොක්සිල් සමූහය (-OH)", "ඇල්ඩිහයිඩ සමූහය", "ඇමයිනෝ සමූහය"), 1)
        6 -> Pentuple("Which alcohol is found in common alcoholic beverages and hand sanitizers?", "මත්පැන් වල සහ සැනිටයිසර් වල බහුලව අඩංගු ඇල්කොහොල වර්ගය කුමක්ද?", listOf("Methanol", "Ethanol", "Propanol", "Butanol"), listOf("මෙතනෝල්", "එතනෝල් (Ethanol)", "ප්‍රොපනෝල්", "බියුටනෝල්"), 1)
        7 -> Pentuple("What functional group is characteristic of carboxylic acids?", "කාබොක්සිලික් අම්ලවල පවතින සුවිශේෂී ක්‍රියාකාරී සමූහය කුමක්ද?", listOf("-OH", "-COOH", "-CHO", "-COO-"), listOf("-OH", "-COOH (කාබොක්සිල්)", "-CHO", "-COO-"), 1)
        8 -> Pentuple("What is the main acid found in manufactured vinegar?", "විනාකිරිවල අඩංගු ප්‍රධාන කාබනික අම්ලය කුමක්ද?", listOf("Citric acid", "Formic acid", "Ethanoic acid (Acetic acid)", "Lactic acid"), listOf("සිට්‍රික් අම්ලය", "ෆෝමික් අම්ලය", "එතනොයික් අම්ලය / ඇසිටික් (Acetic)", "ලැක්ටික් අම්ලය"), 2)
        else -> Pentuple("Organic compounds formed by the reaction between alcohols and carboxylic acids are:", "ඇල්කොහොල සහ කාබොක්සිලික් අම්ල ප්‍රතික්‍රියා කර සාදන සුවඳවත් සංයෝග?", listOf("Alkanes", "Esters", "Ethers", "Ketones"), listOf("ඇල්කේන", "එස්ටර (Esters)", "ඊතර", "කීටෝන"), 1)
    }

    private fun getGrade10Stage3(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("What law states that the current through conductor is proportional to potential difference?", "සන්නායකයක් හරහා යන විදුලිය එහි විභව වෙනසට සමානුපාතික බව පවසන නියමය කුමක්ද?", listOf("Newton's Law", "Ohm's Law", "Faraday's Law", "Lenz's Law"), listOf("නිව්ටන් නියමය", "ඕම්ගේ නියමය (Ohm's Law)", "පැරඩේ නියමය", "ලෙන්ස් නියමය"), 1)
        1 -> Pentuple("What is the formula representing Ohm's Law?", "ඕම්ගේ නියමය ගණිතමය ලෙස දක්වන සූත්‍රය කුමක්ද?", listOf("P = VI", "V = IR", "Q = It", "R = V/I^2"), listOf("P = VI", "V = IR (විභවය = ධාරාව * ප්‍රතිරෝධය)", "Q = It", "R = V/I^2"), 1)
        2 -> Pentuple("The production of an electromotive force (EMF) in conductor by changing magnetic fields is:", "චලනය වන චුම්භක ක්ෂේත්‍රයක් නිසා සන්නායකයක විද්‍යුත්ගාමක බලයක් ඇතිවීම හඳුන්වන්නේ?", listOf("Electrostatic attraction", "Electromagnetic induction", "Refraction", "Thermionic emission"), listOf("ස්ථිති විද්‍යුත් ආකර්ෂණය", "විද්‍යුත් චුම්භක ප්‍රේරණය (Induction)", "වර්තනය", "තාප තාපජ විමෝචනය"), 1)
        3 -> Pentuple("Which device uses electromagnetic induction to convert mechanical energy into electricity?", "විද්‍යුත් චුම්භක ප්‍රේරණය මඟින් යාන්ත්‍රික ශක්තිය විද්‍යුත් ශක්තියට හරවන උපකරණය?", listOf("Electric Motor", "Electric Generator / Dynamo", "Transformer", "Battery"), listOf("විද්‍යුත් මෝටරය", "විද්‍යුත් ජනකය / ඩයිනමෝව (Generator)", "පරිණාමකය", "බැටරිය"), 1)
        4 -> Pentuple("Which component decreases or increases AC voltage using electromagnetic induction?", "ප්‍රත්‍යාවර්ත වෝල්ටීයතාව බාල හෝ ඉහළ දැමීමට භාවිතා කරන ප්‍රේරක උපාංගය?", listOf("Resistor", "Transformer", "Capacitor", "Transistor"), listOf("ප්‍රතිරෝධකය", "පරිණාමකය (Transformer)", "ධාරිත්‍රකය", "ට්‍රාන්සිස්ටරය"), 1)
        5 -> Pentuple("What is the unit of magnetic flux?", "චුම්භක ස්‍රාවය (Magnetic Flux) මැනීමේ ඒකකය කුමක්ද?", listOf("Tesla", "Weber (Wb)", "Henry", "Farad"), listOf("ටෙස්ලා", "වේබර් - Wb (Weber)", "හෙන්රි", "ෆැරඩ්"), 1)
        6 -> Pentuple("Lenz's Law in electromagnetism is consequence of law of conservation of:", "ලෙන්ස්ගේ නියමය මඟින් ප්‍රකාශ වන්නේ කුමන සංරක්ෂණ නියමයද?", listOf("Charge", "Momentum", "Energy", "Mass"), listOf("ආරෝපණ සංරක්ෂණය", "ගම්‍යතා සංරක්ෂණය", "ශක්ති සංරක්ෂණය (Energy)", "ස්කන්ධ සංරක්ෂණය"), 2)
        7 -> Pentuple("If resistance of circuit is doubled, what happens to current for same voltage?", "විභවය එක සමානව තබා පරිපථයක ප්‍රතිරෝධය දෙගුණ කළවිට ධාරාවට කුමක් සිදුවේද?", listOf("Doubled", "Halved", "Quadrupled", "Zero"), listOf("දෙගුණ වේ", "අඩක් වේ (Halved)", "හතර ගුණ වේ", "ශුන්‍ය වේ"), 1)
        8 -> Pentuple("Which law determines direction of induced electromotive force of a conductor?", "ප්‍රේරිත විද්‍යුත් ගාමක බලයේ දිශාව තීරණය කිරීමට උදව් වන නියමය කුමක්ද?", listOf("Ohm's Law", "Lenz's Law", "Coulomb's Law", "Gauss's Law"), listOf("ඕම්ගේ නියමය", "ලෙන්ස්ගේ නියමය (Lenz's Law)", "කූලෝම් නියමය", "ගවුස් නියමය"), 1)
        else -> Pentuple("What is the SI unit of magnetic field strength (magnetic B field)?", "චුම්භක ක්ෂේත්‍ර තීව්‍රතාවය (B) මැනීමේ SI ඒකකය කුමක්ද?", listOf("Weber", "Tesla (T)", "Gauss", "Ampere-turn"), listOf("වේබර්", "ටෙස්ලා - T (Tesla)", "ගවුස්", "ඇම්පියර්-වට"), 1)
    }
}
