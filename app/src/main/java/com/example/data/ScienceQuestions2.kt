package com.example.data

object ScienceQuestions2 {
    val list: List<Question> = generate()

    private fun generate(): List<Question> {
        val qList = mutableListOf<Question>()

        for (g in 6..10) {
            for (s in 1..3) {
                for (q in 0..9) {
                    val id = "sci_g${g}_s${s}_$q"
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
                            // Grade 6 Science
                            when (s) {
                                1 -> {
                                    // Heat and temperature
                                    questionEn = "What instrument is used to measure temperature?"
                                    questionSi = "උෂ්ණත්වය මැනීම සඳහා භාවිතා කරන උපකරණය කුමක්ද?"
                                    optionsEn = listOf("Barometer", "Thermometer", "Anemometer", "Lactometer")
                                    optionsSi = listOf("වායුපීඩනමානය", "උෂ්ණත්වමානය", "සුළංවේගමානය", "ක්ෂීරමානය")
                                    correctIndex = 1
                                    hintEn = "It contains liquid mercury or colored alcohol."
                                    hintSi = "එහි ද්‍රව රසදිය හෝ වර්ණ ගැන්වූ ඇල්කොහොල් අඩංගු වේ."
                                }
                                2 -> {
                                    // States of Water
                                    questionEn = "What is the boiling point of pure water under standard atmospheric pressure?"
                                    questionSi = "සාමාන්‍ය වායුගෝලීය පීඩනය යටතේ පිරිසිදු ජලය නටන උෂ්ණත්වය කොපමණද?"
                                    optionsEn = listOf("0 degrees Celsius", "50 degrees Celsius", "100 degrees Celsius", "120 degrees Celsius")
                                    optionsSi = listOf("සෙල්සියස් අංශක 0", "සෙල්සියස් අංශක 50", "සෙල්සියස් අංශක 100", "සෙල්සියස් අංශක 120")
                                    correctIndex = 2
                                    hintEn = "It is exactly double of fifty."
                                    hintSi = "එය හරියටම පනහේ අගය මෙන් දෙගුණයකි."
                                }
                                3 -> {
                                    // Components of air
                                    questionEn = "Which gas is highest in abundance in Earth's atmosphere?"
                                    questionSi = "පෘථිවි වායුගෝලයේ බහුලවම අඩංගු වන වායුව කුමක්ද?"
                                    optionsEn = listOf("Oxygen", "Nitrogen", "Carbon dioxide", "Argon")
                                    optionsSi = listOf("ඔක්සිජන්", "නයිට්‍රජන්", "කාබන් ඩයොක්සයිඩ්", "ආගන්")
                                    correctIndex = 1
                                    hintEn = "It makes up about 78 percent of our air."
                                    hintSi = "එහි අනුපාතය වායුගෝලයෙන් සියයට 78 ක් පමණ වේ."
                                }
                            }
                        }
                        7 -> {
                            // Grade 7 Science
                            when (s) {
                                1 -> {
                                    // Acids and Bases
                                    questionEn = "What color does blue litmus paper turn in acidic solutions?"
                                    questionSi = "අම්ල ද්‍රාවණයකදී නිල් ලිට්මස් කඩදාසි කුමන වර්ණයකට හැරේවිද?"
                                    optionsEn = listOf("Red", "Green", "Yellow", "Stay blue")
                                    optionsSi = listOf("රතු", "කොළ", "කහ", "නිල් පැහැයෙන්ම පවතී")
                                    correctIndex = 0
                                    hintEn = "It turns into the color of a ripe tomato."
                                    hintSi = "එය ඉදුණු තක්කාලි ගෙඩියක වර්ණය බවට පත් වේ."
                                }
                                2 -> {
                                    // Senses & organs
                                    questionEn = "Which organ is responsible for maintaining balance in the human body?"
                                    questionSi = "මිනිස් සිරුරේ සමතුලිතතාවය පවත්වා ගැනීමට දායක වන ප්‍රධාන අවයවය කුමක්ද?"
                                    optionsEn = listOf("Eyes", "Brain", "Inner Ear", "Spine")
                                    optionsSi = listOf("ඇස්", "මොළය", "අභ්‍යන්තර කන", "කොන්දේ අස්ථි")
                                    correctIndex = 2
                                    hintEn = "This sensory organ is located beside your face."
                                    hintSi = "මෙම සංවේදන අවයවය ඔබේ මුහුණ දෙපස පිහිටා ඇත."
                                }
                                3 -> {
                                    // Elements
                                    questionEn = "What is the chemical symbol of Oxygen?"
                                    questionSi = "ඔක්සිජන් වායුවේ රසායනික සංකේතය කුමක්ද?"
                                    optionsEn = listOf("Ox", "O", "Og", "On")
                                    optionsSi = listOf("Ox", "O", "Og", "On")
                                    correctIndex = 1
                                    hintEn = "It is a single letter matching the starting character."
                                    hintSi = "එහි නමේ මුල් අකුර වන තනි ඉංග්‍රීසි අකුරකි."
                                }
                            }
                        }
                        8 -> {
                            // Grade 8 Science
                            when (s) {
                                1 -> {
                                    // Photosynthesis
                                    questionEn = "Which gas is released by green plants during active photosynthesis?"
                                    questionSi = "ප්‍රභාසංස්ලේෂණයේදී හරිත ශාක මඟින් පිටකරන වායුව කුමක්ද?"
                                    optionsEn = listOf("Carbon Dioxide", "Oxygen", "Nitrogen", "Methane")
                                    optionsSi = listOf("කාබන් ඩයොක්සයිඩ්", "ඔක්සිජන්", "නයිට්‍රජන්", "මෙතේන්")
                                    correctIndex = 1
                                    hintEn = "It is the gas required for humans to breathe."
                                    hintSi = "එය මිනිසාගේ හුස්ම ගැනීමට අත්‍යවශ්‍ය වායුවයි."
                                }
                                2 -> {
                                    // Pressure
                                    questionEn = "What is the unit of pressure in the SI system?"
                                    questionSi = "පීඩනය මැනීමේ ජාත්‍යන්තර සම්මත ඒකකය කුමක්ද?"
                                    optionsEn = listOf("Newton", "Pascal", "Joule", "Watt")
                                    optionsSi = listOf("නිව්ටන්", "පැස්කල්", "ජූල්", "වොට්")
                                    correctIndex = 1
                                    hintEn = "It is named after Blaise, a French scientist."
                                    hintSi = "ප්‍රංශ විද්‍යාඥ බ්ලේස්ගේ නමින් මෙය හඳුන්වයි."
                                }
                                3 -> {
                                    // Human Organs
                                    questionEn = "Where in the human digestive system is water mainly absorbed?"
                                    questionSi = "මිනිස් ජීරණ පද්ධතියේ ජලය වැඩිපුරම අවශෝෂණය කරගනු ලබන්නේ කොහේද?"
                                    optionsEn = listOf("Stomach", "Small Intestine", "Large Intestine", "Esophagus")
                                    optionsSi = listOf("ආමාශය", "කුඩා අන්ත්‍රය", "මහා අන්ත්‍රය", "ග්‍රසනිකාව")
                                    correctIndex = 2
                                    hintEn = "It is the wider section of the intestines before waste excretion."
                                    hintSi = "අපද්‍රව්‍ය බැහැර කිරීමට පෙර පවතින බඩවැල්වල ඉතිරි කොටසයි."
                                }
                            }
                        }
                        9 -> {
                            // Grade 9 Science
                            when (s) {
                                1 -> {
                                    // Atom particles
                                    questionEn = "Which atomic particle holds a negative electrical charge?"
                                    questionSi = "පරමාණුවක සෘණ ආරෝපණයක් දරන අංශුව කුමක්ද?"
                                    optionsEn = listOf("Proton", "Neutron", "Electron", "Nucleus")
                                    optionsSi = listOf("ප්‍රෝටෝනය", "නියුට්‍රෝනය", "ඉලෙක්ට්‍රෝනය", "න්‍යෂ්ටිය")
                                    correctIndex = 2
                                    hintEn = "It orbits around the central nucleus."
                                    hintSi = "එය මධ්‍යයේ ඇති න්‍යෂ්ටිය වටා කක්ෂවල ගමන් කරයි."
                                }
                                2 -> {
                                    // Laws of motion
                                    questionEn = "Who formulated the three laws of motion?"
                                    questionSi = "චලිතය පිළිබඳ නියම තුන ඉදිරිපත් කළේ කවුද?"
                                    optionsEn = listOf("Albert Einstein", "Galileo Galilei", "Isaac Newton", "Marie Curie")
                                    optionsSi = listOf("ඇල්බට් අයින්ස්ටයින්", "ගැලීලියෝ ගැලිලි", "අයිසැක් නිව්ටන්", "මාරි කියුරි")
                                    correctIndex = 2
                                    hintEn = "An apple fell on his head, leading to gravity studies."
                                    hintSi = "ඔහුගේ හිස මතට ඇපල් ගෙඩියක් වැටීමෙන් ගුරුත්වාකර්ෂණය ගැන සිතූ විද්‍යාඥයායි."
                                }
                                3 -> {
                                    // Genetics
                                    questionEn = "Where is DNA primarily located in eukaryotic animal cells?"
                                    questionSi = "සත්ත්ව සෛලයක ඩීඑන්ඒ බහුලවම පිහිටා තිබෙන්නේ කොහෙද?"
                                    optionsEn = listOf("Cytoplasm", "Mitochondria", "Nucleus", "Ribosome")
                                    optionsSi = listOf("සෛල ප්ලාස්මය", "මයිටොකොන්ඩ්‍රියාව", "න්‍යෂ්ටිය", "රයිබොසෝමය")
                                    correctIndex = 2
                                    hintEn = "It is the control room of the cell."
                                    hintSi = "එය සෛලයේ පාලන මධ්‍යස්ථානය ලෙස සැලකේ."
                                }
                            }
                        }
                        10 -> {
                            // Grade 10 Science
                            when (s) {
                                1 -> {
                                    // Periodic table
                                    questionEn = "Which of the following elements is a noble gas?"
                                    questionSi = "පහත දැක්වෙන මූලද්‍රව්‍යයන්ගෙන් නිෂ්ක්‍රීය වායුවක් වන්නේ කුමක්ද?"
                                    optionsEn = listOf("Hydrogen", "Helium", "Oxygen", "Sodium")
                                    optionsSi = listOf("හයිඩ්‍රජන්", "හෙලියම්", "ඔක්සිජන්", "සෝඩියම්")
                                    correctIndex = 1
                                    hintEn = "It is used in party balloons because it is lighter than air."
                                    hintSi = "වාතයට වඩා සැහැල්ලු බැවින් සෙල්ලම් බැලූන් පිරවීමට මෙය ගනී."
                                }
                                2 -> {
                                    // Mechanics: Ohm's law
                                    questionEn = "What is the relationship between voltage, current, and resistance?"
                                    questionSi = "ධාරාව, වෝල්ටීයතාවය සහ ප්‍රතිරෝධය අතර සම්බන්ධය කුමක්ද?"
                                    optionsEn = listOf("V = I + R", "V = I / R", "V = I multiplied by R", "V = R / I")
                                    optionsSi = listOf("V = I + R", "V = I / R", "V = I ගුණ කිරීම R", "V = R / I")
                                    correctIndex = 2
                                    hintEn = "Voltage equals current multiplied by resistance."
                                    hintSi = "වෝල්ටීයතාවය යනු ධාරාවේ සහ ප්‍රතිරෝධයේ ගුණිතයට සමාන වේ."
                                }
                                3 -> {
                                    // Reflection and speed
                                    questionEn = "What is the approximate speed of light in a vacuum?"
                                    questionSi = "රික්තයක් තුළ ආලෝකයේ වේගය තත්පරයකට කිලෝමීටර කොපමණද?"
                                    optionsEn = listOf("30,000", "300,000", "1,500,000", "3,000,000")
                                    optionsSi = listOf("තිස්දහසක්", "ලක්ෂ තුනක්", "ලක්ෂ පහළොවක්", "තිස්ලක්ෂයක්")
                                    correctIndex = 1
                                    hintEn = "Three followed by five zeros kilometers per second."
                                    hintSi = "තත්පරයකට කිලෝමීටර ලක්ෂ තුනක් පමණ වේ."
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
                        subject = "Science",
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
