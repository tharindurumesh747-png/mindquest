package com.example.data

object HistoryQuestions {

    fun getQuestions(grade: Int, stage: Int): List<Question> {
        val list = mutableListOf<Question>()
        for (i in 0..9) {
            list.add(getSingleQuestion(grade, stage, i))
        }
        return list
    }

    private fun getSingleQuestion(grade: Int, stage: Int, index: Int): Question {
        val qid = "g${grade}_History_s${stage}_q${index}"
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
            subject = "History",
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
        0 -> Pentuple("What is the national flower of Sri Lanka?", "ශ්‍රී ලංකාවේ ජාතික පුෂ්පය (මල) කුමක්ද?", listOf("Rose", "Blue Water Lily (Nil Mahanel)", "Lotus", "Jasmine"), listOf("රෝස", "නිල් මානෙල් (Nil Mahanel)", "නෙළුම්", "පිච්ච"), 1)
        1 -> Pentuple("What animal is featured holding a sword on the Sri Lankan National Flag?", "ශ්‍රී ලංකාවේ ජාතික කොඩියේ කඩුවක් අතින් ගෙන සිටින සත්වයා කවුද?", listOf("Tiger", "Elephant", "Lion", "Leopard"), listOf("කොටියා", "අලියා", "සිංහයා (Lion)", "දිවියා"), 2)
        2 -> Pentuple("What is the capital city of Sri Lanka?", "ශ්‍රී ලංකාවේ අගනුවර කුමක්ද?", listOf("Kandy", "Galle", "Sri Jayawardenepura Kotte", "Jaffna"), listOf("මහනුවර", "ගාල්ල", "ශ්‍රී ජයවර්ධනපුර කෝට්ටේ", "යාපනය"), 2)
        3 -> Pentuple("Which color is on the right background of the Sri Lankan National Flag?", "ජාතික කොඩියේ දකුණු පස පසුබිමේ ඇති ප්‍රධාන වර්ණය කුමක්ද?", listOf("Green", "Orange", "Maroon/Crimson", "Yellow"), listOf("කොළ", "තැඹිලි", "තද රතු / මරූන් (Maroon)", "කහ"), 2)
        4 -> Pentuple("How many leaves of Bo tree are shown in the corners of national flag maroon rectangle?", "ජාතික කොඩියේ තද රතු කොටසේ කොන් හතරේ ඇති බෝපත් ගණන කීයද?", listOf("2", "4", "6", "8"), listOf("2", "4 (බෝපත් හතර)", "6", "8"), 1)
        5 -> Pentuple("Which of these is Sri Lanka's national sport?", "ශ්‍රී ලංකාවේ ජාතික ක්‍රීඩාව වන්නේ කුමක්ද?", listOf("Cricket", "Football", "Volleyball", "Netball"), listOf("ක්‍රිකට්", "පාපන්දු", "වොලිබෝල් (Volleyball)", "නෙට්බෝල්"), 2)
        6 -> Pentuple("What shape is Sri Lanka island often compared to?", "ශ්‍රී ලංකා ද්වීපය පොදුවේ කුමන හැඩයකට සමාන කරනු ලබයිද?", listOf("Star", "Pearl or Teardrop", "Square", "Circle"), listOf("තරුවක", "මුතු ඇටයක් හෝ කඳුළු බින්දුවක් (Pearl)", "කොටුවක", "වෘත්තයක"), 1)
        7 -> Pentuple("What is the official national language alongside Tamil in Sri Lanka?", "දෙමළ භාෂාවට අමතරව ශ්‍රී ලංකාවේ රාජ්‍ය භාෂාව වන්නේ කුමක්ද?", listOf("English", "Sinhala", "Dutch", "German"), listOf("ඉංග්‍රීසි", "සිංහල (Sinhala)", "ලන්දේසි", "ජර්මන්"), 1)
        8 -> Pentuple("In the Sri Lankan National Flag, what does the orange stripe represent?", "ජාතික කොඩියේ තැඹිලි පාට තීරුවෙන් නිරූපණය වන්නේ කුමන ජන කොටසද?", listOf("Sinhalese", "Tamils", "Muslims", "Burghers"), listOf("සිංහල ජනතාව", "දෙමළ ජනතාව (Tamils)", "මුස්ලිම් ජනතාව", "බර්ගර්ස්"), 1)
        else -> Pentuple("In the Sri Lankan National Flag, what does the green stripe represent?", "ජාතික කොඩියේ කොළ පාට තීරුවෙන් නිරූපණය වන ජන කොටස කවුද?", listOf("Sinhalese", "Tamils", "Muslims / Moors", "Malays"), listOf("සිංහල ජනතාව", "දෙමළ ජනතාව", "මුස්ලිම් ජනතාව (Muslims)", "මැලේ"), 2)
    }

    private fun getGrade1Stage2(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("Which majestic animal has been historically revered as Sri Lanka's national heritage?", "ඉතිහාසයේ සිට ශ්‍රී ලංකාවේ පෙරහැරවල්වල ගෙන යන ගෞරවනීය සත්වයා කවුද?", listOf("Elephant", "Deer", "Tiger", "Fox"), listOf("අලියා (Elephant)", "මුවා", "කොටියා", "නරියා"), 0)
        1 -> Pentuple("What is the name of the ancient rock fortress built by King Kasyapa?", "කාශ්‍යප රජු විසින් සාදන ලද ලොව අටවන පුදුමය ලෙස හැඳින්වෙන පර්වත බලකොටුව?", listOf("Ruwanwelisaya", "Sigiriya", "Dalada Maligawa", "Yatala Vehera"), listOf("රුවන්වැලිසෑය", "සීගිරිය (Sigiriya)", "දළදා මාළිගාව", "යටාල වෙහෙර"), 1)
        2 -> Pentuple("In which historical province is the sacred Temple of the Tooth (Dalada Maligawa) located?", "පූජනීය ශ්‍රී දළදා මාළිගාව පිහිටා ඇත්තේ කුමන ඓතිහාසික නගරයේද?", listOf("Colombo", "Kandy (Senkadagala)", "Galle", "Anuradhapura"), listOf("කොළඹ", "මහනුවර - සෙංකඩගල (Kandy)", "ගාල්ල", "අනුරාධපුරය"), 1)
        3 -> Pentuple("What is the iconic giant dagoba built by King Dutugemunu in Anuradhapura?", "දුටුගැමුණු රජතුමා විසින් අනුරාධපුරයේ ඉදි කළ මහා ස්තූපය කුමක්ද?", listOf("Jetavanaramaya", "Ruwanwelisaya", "Abhayagiriya", "Thuparamaya"), listOf("ජේතවනාරාමය", "රුවන්වැලි මහා සෑය (Ruwanwelisaya)", "අභයගිරිය", "ථූපාරාමය"), 1)
        4 -> Pentuple("Who was the legendary king who built many massive water reservoirs (tanks) like Parakrama Samudra?", "පරාක්‍රම සමුද්‍රය ඇතුළු වැව් රාශියක් සාදා කෘෂිකර්මාන්තයට විශාල සේවයක් කළ රජතුමා කවුද?", listOf("King Dutugemunu", "King Parakramabahu I", "King Pandukabhaya", "King Kasyapa"), listOf("දුටුගැමුණු රජතුමා", "පළමුවන පරාක්‍රමබාහු රජතුමා (Parakramabahu)", "පණ්ඩුකාභය රජතුමා", "කාශ්‍යප රජතුමා"), 1)
        5 -> Pentuple("What ancient city is famous for its ruins and stone sculptures like Gal Vihara?", "ගල් විහාරයේ ඇති බුද්ධ ප්‍රතිමා ඇතුළු නටබුන් සඳහා ප්‍රසිද්ධ දෙවන අගනුවර?", listOf("Kotte", "Polonnaruwa", "Gampola", "Dambadeniya"), listOf("කෝට්ටේ", "පොළොන්නරුව (Polonnaruwa)", "ගම්පොළ", "දඹදෙණිය"), 1)
        6 -> Pentuple("Which sacred tree in Anuradhapura is known as the oldest historically planted tree in the world?", "ලොව පවතින ඉපැරණිතම ඓතිහාසික රෝපිත වෘක්ෂය ලෙස හඳුන්වන අනුරාධපුරයේ ඇති පූජනීය බෝධීන් වහන්සේ?", listOf("Sri Maha Bodhi", "Nuga Tree", "Banyan Tree", "Sal Tree"), listOf("ජය ශ්‍රී මහා බෝධින් වහන්සේ (Sri Maha Bodhi)", "නුග ගස", "මහනුවර බෝධිය", "සල් ගස"), 0)
        7 -> Pentuple("What is the traditional New Year celebrated in Sri Lanka by Sinhala and Tamil communities?", "සිංහල හා දෙමළ ජනතාව විසින් සමරන සාම්ප්‍රදායික අලුත් අවුරුද්ද කුමක්ද?", listOf("Christmas", "Ramadan", "Sinhala & Tamil Aluth Avurudu", "Wesak"), listOf("නත්තල්", "රාමසාන්", "සිංහල සහ දෙමළ අලුත් අවුරුද්ද (Avurudu)", "වෙසක්"), 2)
        8 -> Pentuple("Which local sweet made of rice flour and treacle is shaped like a round mesh?", "කැවුම් කිරිබත් අතර ඇති ජනප්‍රිය වළලු හැඩැති සාම්ප්‍රදායික ඇවුරුදු කැවිල්ල කුමක්ද?", listOf("Kavum", "Kokis", "Mung Kavum", "Asmi"), listOf("කැවුම්", "කොකිස්", "මුං කැවුම්", "අස්මී (Asmi)"), 3)
        else -> Pentuple("What milk rice dish is traditionally cooked as the first meal of Sri Lankan celebratory events?", "ඕනෑම සුභ කටයුත්තකදී සාදන සාම්ප්‍රදායික සහල් ආහාරය කුමක්ද?", listOf("Fried Rice", "Biryani", "Kiribath (Milk rice)", "Roti"), listOf("ෆ්‍රයිඩ් රයිස්", "බුරියානි", "කිරිබත් (Kiribath)", "රොටි"), 2)
    }

    private fun getGrade1Stage3(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("Which bird is Sri Lanka's national bird?", "ශ්‍රී ලංකාවේ ජාතික පක්ෂියා (කුරුල්ලා) කවුද?", listOf("Parrot", "Peacock", "Sri Lanka Junglefowl (Wali Kukula)", "Crow"), listOf("ගිරවා", "මොනරා", "ශ්‍රී ලංකා වලි කුකුළා (Junglefowl)", "කපුටා"), 2)
        1 -> Pentuple("What is the national tree of Sri Lanka?", "ශ්‍රී ලංකාවේ ජාතික වෘක්ෂය (ගස) කුමක්ද?", listOf("Na Tree (Ceylon Ironwood)", "Coconut Tree", "Jack Tree", "Banyan Tree"), listOf("නා ගස (Na Tree)", "පොල් ගස", "කොස් ගස", "නුග ගස"), 0)
        2 -> Pentuple("How must we behave when the National Anthem is being sung?", "ජාතික ගීය ගායනා කරන අවස්ථාවකදී අප සිටිය යුත්තේ කෙසේද?", listOf("Running and laughing", "Stand at attention and show respect", "Dancing", "Talking loudly"), listOf("දුවමින් සිනාසෙමින්", "සිලින්ව කෙලින් සිටගෙන ගෞරවය දැක්වීම (Attention)", "නටමින්", "කථා කරමින්"), 1)
        3 -> Pentuple("What is the blue sea area surrounding the island of Sri Lanka called?", "ශ්‍රී ලංකා ද්වීපය වටා ඇති නිල් පැහැති මුහුදු කලාපය අයත් වන සාගරය?", listOf("Pacific Ocean", "Indian Ocean", "Atlantic Ocean", "Arctic Ocean"), listOf("පැසිෆික් සාගරය", "ඉන්දියන් සාගරය (Indian Ocean)", "අත්ලන්තික් සාගරය", "ආක්ටික් සාගරය"), 1)
        4 -> Pentuple("What are historical ruins and ancient caves protected by?", "පුරාණ නටබුන් සහ ගල් ලෙන් ආරක්ෂා කරන්නේ කා මතද?", listOf("Monkeys", "The Department of Archaeology", "Foreign tourists", "Hunters"), listOf("වඳුරන්", "පුරාවිද්‍යා දෙපාර්තමේන්තුව (Archaeology)", "විදේශික සංචාරකයින්", "දඩයම්කරුවන්"), 1)
        5 -> Pentuple("Which local leaf is traditionally offered to elders to show respect and seek blessings?", "වැඩිහිටියන්ට ගෞරව කර ආශිර්වාද ලබාගැනීමට දෙන සාම්ප්‍රදායික කොළ වර්ගය කුමක්ද?", listOf("Mango leaf", "Betel leaf (Bulath)", "Lotus leaf", "Bo leaf"), listOf("අඹ කොළ", "බුලත් කොළ (Betel)", "නෙළුම් කොළ", "බෝ කොළ"), 1)
        6 -> Pentuple("What unique Sri Lankan performance art involves dancers wearing colorful wooden masks?", "වෙස් මුහුණු පැළඳ සාම්ප්‍රදායිකව කරන පහතරට නර්තන කලාව කුමක්ද?", listOf("Bharatanatyam", "Kathakali", "Traditional Mask/Low Country Dance (Ambalangoda kolam)", "Ballet"), listOf("භරතනාට්‍යම්", "කතකලි", "පහතරට වෙස් මුහුණු නර්තනය (Mask Dance)", "බැලේ"), 2)
        7 -> Pentuple("Which historical monument in Colombo is dedicated to the country's freedom from British rule in 1948?", "1948 නිදහස සැමරීම වෙනුවෙන් කොළඹ ඉදි කළ ස්මාරකය කුමක්ද?", listOf("Lotus Tower", "Independence Memorial Hall", "Galle Face Hotel", "Nelum Pokuna"), listOf("නෙළුම් කුළුණ", "නිදහස් චතුරස්‍රය / ස්මාරකය (Independence Hall)", "ගාලු මුවදොර හෝටලය", "නෙළුම් පොකුණ"), 1)
        8 -> Pentuple("Which city is known as the oldest capital of ancient Sri Lanka?", "පෞරාණික ලංකාවේ මුල්ම සහ දීර්ඝතම රාජධානිය (අගනුවර) ලෙස හැඳින්වෙන්නේ කුමක්ද?", listOf("Anuradhapura", "Polonnaruwa", "Kandy", "Kotte"), listOf("අනුරාධපුරය (Anuradhapura)", "පොළොන්නරුව", "මහනුවර", "කෝට්ටේ"), 0)
        else -> Pentuple("Who wrote Sri Lanka's national anthem 'Sri Lanka Matha'?", "ශ්‍රී ලංකා ජාතික ගීය වන 'ශ්‍රී ලංකා මාතා' ගීතය නිර්මාණය කළ කලාකරුවා කවුද?", listOf("Anagarika Dharmapala", "W. D. Amaradeva", "Ananda Samarakoon", "Arisen Ahubudu"), listOf("අනගාරික ධර්මපාල", "ඩබ්. ඩී. අමරදේව", "ආනන්ද සමරකෝන් (Ananda Samarakoon)", "අරිසෙන් අහුබුදු"), 2)
    }

    // GRADE 2
    private fun getGrade2Stage1(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("Who was the first historical king of Anuradhapura who built the city systematically?", "අනුරාධපුරය ක්‍රමානුකූලව සැලසුම් කර අගනුවර කළ පළමු රජතුමා කවුද?", listOf("King Devanampiyatissa", "King Pandukabhaya", "King Dutugemunu", "King Vijayabahu I"), listOf("දේවානම්පියතිස්ස රජතුමා", "පණ්ඩුකාභය රජතුමා (Pandukabhaya)", "දුටුගැමුණු රජතුමා", "පළමුවන විජයබාහු රජතුමා"), 1)
        1 -> Pentuple("Which city is famous for its massive multi-chambered ancient fortress on top of a rock?", "පර්වතයක් මුදුනේ ඉදිකළ දැවැන්ත මාළිගාවක් සහිත ඓතිහාසික බලකොටුව කුමක්ද?", listOf("Dambadeniya", "Sigiriya", "Yapahuwa", "Kurunegala"), listOf("දඹදෙනිය", "සීගිරිය (Sigiriya)", "යාපහුව", "කුරුණෑගල"), 1)
        2 -> Pentuple("Who established the famous city of Anuradhapura?", "අනුරාධපුරය මුලින්ම ගම්මානයක් ලෙස පිහිටුවූයේ කවුද?", listOf("Prince Vijaya", "Anuradha (Minister of Vijaya)", "King Mutasiva", "King Kasyapa"), listOf("විජය කුමාරයා", "අනුරාධ ඇමතිවරයා (Anuradha)", "මුටසීව රජතුමා", "කාශ්‍යප රජතුමා"), 1)
        3 -> Pentuple("What is the meaning of the word 'Anuradhapura'?", "'අනුරාධපුර' යන්නෙහි අරුත කුමක්ද?", listOf("City of Lions", "City of Monks", "City of Anuradha or Success Star", "Golden City"), listOf("සිංහයන්ගේ නගරය", "භික්ෂූන්ගේ නගරය", "අනුරාධ ඇමතිවරයාගේ හෝ සුභ නැකතින් පිහිටුවූ නගරය", "රන්වන් නගරය"), 2)
        4 -> Pentuple("Who was the mother of the legendary King Dutugemunu?", "දුටුගැමුණු රජතුමාගේ වීර මාතාව කවුද?", listOf("Queen Anula", "Queen Viharamahadevi", "Queen Soma", "Queen Lilavati"), listOf("අනුලා රැජින", "විහාරමහාදේවිය (Viharamahadevi)", "සෝමා රැජින", "ලීලාවතී රැජින"), 1)
        5 -> Pentuple("Which ancient king was famous for creating the magnificent Jetavanaramaya, the tallest brick dagoba?", "ලොව උසම ගඩොලින් තැනූ ජේතවනාරාම ස්තූපය ඉදි කළ මහා රජතුමා කවුද?", listOf("King Mahasen", "King Dutugemunu", "King Devanampiyatissa", "King Vasabha"), listOf("මහසෙන් රජතුමා (King Mahasen)", "දුටුගැමුණු රජතුමා", "දේවානම්පියතිස්ස රජතුමා", "වසභ රජතුමා"), 0)
        6 -> Pentuple("Which famous ancient construction is a long, highly gradient water canal carrying water from Kalawewa?", "කලාවැවේ සිට අනුරාධපුරයට ජලය රැගෙන යන තාක්ෂණික විස්මයක් බඳු ඇළ මාර්ගය කුමක්ද?", listOf("Yoda Ela (Jaya Ganga)", "Ketawala Ela", "Left Bank Canal", "Minipe Ela"), listOf("යෝධ ඇළ / ජය ගඟ (Yoda Ela)", "කැටවල ඇළ", "වම් ඉවුරු ඇළ", "මිණිපේ ඇළ"), 0)
        7 -> Pentuple("Who built the giant Kalawewa reservoir that feeds Yoda Ela?", "යෝධ ඇළ පෝෂණය කරන දැවැන්ත කලාවැව ඉදි කළ රජතුමා කවුද?", listOf("King Dhatusena", "King Kasyapa", "King Vasabha", "King Mahasen"), listOf("ධාතුසේන රජතුමා (King Dhatusena)", "කාශ්‍යප රජතුමා", "වසභ රජතුමා", "මහසෙන් රජතුමා"), 0)
        8 -> Pentuple("What ancient crop was cultivated in step terraces in mountain areas of Sri Lanka?", "කඳුකර ප්‍රදේශවල හෙල්මලු ක්‍රමයට සාම්ප්‍රදායිකව වගා කළ ප්‍රධාන භෝගය කුමක්ද?", listOf("Tea", "Paddy (Rice)", "Rubber", "Coffee"), listOf("තේ", "වී වගාව - බත් (Paddy)", "රබර්", "කෝපි"), 1)
        else -> Pentuple("Which system did ancient kings develop to secure food during dry seasons?", "නියඟ කාලවල වගා කටයුතු සඳහා පැරණි රජවරු නිර්මාණය කළේ කුමන වාරි පද්ධතියක්ද?", listOf("River diversions", "Rain reservoirs (Tanks/Wewa)", "Deep wells", "Glacier pipes"), listOf("ගංගා හැරවීම්", "වැව් පද්ධති (Tanks/Wewa)", "ළිං", "අයිස් නල"), 1)
    }

    private fun getGrade2Stage2(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("Where was Buddhism officially introduced to Sri Lanka?", "ශ්‍රී ලංකාවට බුදුදහම නිල වශයෙන් හඳුන්වා දුන් ස්ථානය කුමක්ද?", listOf("Kandy", "Anuradhapura", "Mihintale", "Sigiriya"), listOf("මහනුවර", "අනුරාධපුරය", "මිහින්තලය (Mihintale)", "සීගිරිය"), 2)
        1 -> Pentuple("Who brought Buddhism to Sri Lanka during King Devanampiyatissa's reign?", "දේවානම්පියතිස්ස රජු දවස බුදු දහම ලංකාවට රැගෙන ආවේ කවුද?", listOf("Buddha himself", "Arhat Mahinda Thero", "Arhat Sanghamitta", "Prince Vijaya"), listOf("බුදුරජුන්ම", "මිහිඳු මහරහතන් වහන්සේ (Mahinda Thero)", "සංඝමිත්තා මෙහෙණින් වහන්සේ", "විජය කුමාරයා"), 1)
        2 -> Pentuple("What was the name of the monk who brought the sacred Sri Maha Bodhi sapling?", "සුජාත ජය ශ්‍රී මහා බෝධින් වහන්සේගේ දක්ෂිණ ශාඛාව ලංකාවට වැඩම කරවූයේ කවුද?", listOf("Arhat Mahinda", "Theri Sanghamitta", "King Dharmasoka", "Samanera Sumana"), listOf("මිහිඳු රහතන් වහන්සේ", "සංඝමිත්තා මෙහෙණින් වහන්සේ (Sanghamitta)", "ධර්මාශෝක රජු", "සුමන සාමණේරයන්"), 1)
        3 -> Pentuple("What was the king's name when Buddhism was brought to Sri Lanka?", "ලංකාවට බුදු දහම වඩමවන විට ලංකාවේ රජ කළේ කවුද?", listOf("King Dutugemunu", "King Devanampiyatissa", "King Pandukabhaya", "King Vijayabahu I"), listOf("දුටුගැමුණු රජතුමා", "දේවානම්පියතිස්ස රජතුමා (Devanampiyatissa)", "පණ්ඩුකාභය රජතුමා", "විජයබාහු රජතුමා"), 1)
        4 -> Pentuple("Which local leaf is used to wrap traditional 'Halapa' sweet?", "සාම්ප්‍රදායික 'හැලප' කැවිල්ල එතීමට ගන්නේ කුමන කන්දරාවක කොළයක්ද?", listOf("Banana leaf", "Kenda leaf (Kenda Kolaya)", "Coconut leaf", "Lotus leaf"), listOf("කෙසෙල් කොළය", "කැන්ද කොළය (Kenda Kolaya)", "පොල් අත්ත", "නෙළුම් කොළය"), 1)
        5 -> Pentuple("What is the main ingredient of 'Kurakkan Thalapa'?", "'කුරක්කන් තලප' සාදන ප්‍රධාන ධාන්‍ය වර්ගය කුමක්ද?", listOf("Rice", "Kurakkan (Finger millet)", "Wheat flour", "Maize"), listOf("සහල්", "කුරක්කන් (Kurakkan)", "පාන් පිටි", "බඩඉරිඟු"), 1)
        6 -> Pentuple("Which ancient spice from Sri Lanka was highly valued globally by Arab and European traders?", "අරාබි සහ යුරෝපීය වෙළෙන්දන් ලංකාවෙන් සොයා ආ වටිනාම ඓතිහාසික කුළුබඩු වර්ගය කුමක්ද?", listOf("Chili", "Cinnamon", "Cardamom", "Pepper"), listOf("මිරිස්", "කුරුඳු (Cinnamon)", "කරදමුංගු", "ගම්මිරිස්"), 1)
        7 -> Pentuple("What ancient local food was prepared on stone grinders using parboiled rice flour and coconut milk?", "රතු කැකුළු සහල් පිටි සහ පොල්කිරිවලින් සාදන සාම්ප්‍රදායික උදෑසන ආහාරය කුමක්ද?", listOf("Noodles", "Kiribath / Pittu / Roti", "Bread", "Cake"), listOf("නූඩ්ල්ස්", "කිරිබත් / පිට්ටු / රොටි", "පාන්", "කේක්"), 1)
        8 -> Pentuple("Who brought agricultural technology and many community guilds alongside Theri Sanghamitta?", "සංඝමිත්තා මෙහෙණින් වහන්සේ සමඟ ශ්‍රී ලංකාවට පැමිණි තාක්ෂණික හා කලා කුසලතා පිරි පිරිස කවුද?", listOf("War scientists", "18 guilds of craftsmen and artisans", "Hunters", "Foreign soldiers"), listOf("යුද විද්‍යාඥයින්", "දහඅටක් ශිල්ප ශ්‍රේණිවල කලාකරුවන් (18 Guilds)", "දඩයක්කරුවන්", "විදේශීය සෙබළුන්"), 1)
        else -> Pentuple("What was the ancient name of Mihintale mountain?", "මිහින්තලා පර්වතයේ පැරණි නම කුමක්ද?", listOf("Sigiriya", "Missa Pabbata (Sgiri-Mihintale)", "Sripada", "Ritigala"), listOf("සීගිරිය", "මිස්සක පබ්බත (Missa Pabbata)", "ශ්‍රී පාදය", "රිටිගල"), 1)
    }

    private fun getGrade2Stage3(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("Who is the tribal king of Sri Lanka honored in legends for his advanced rule?", "ශ්‍රී ලංකාවේ මුල්ම ඉතිහාසයේ / ප්‍රාග් ඉතිහාසයේ සිටි බව පැවසෙන රාවණා රජු අයත් වන්නේ කුමන ගෝත්‍රයටද?", listOf("Yaksa", "Naga", "Deva", "Chola"), listOf("යක්ෂ (Yaksa)", "නාග", "දේව", "චෝල"), 0)
        1 -> Pentuple("Which historic system of canals connected the western coastal towns?", "ලන්දේසි යුගයේදී භාණ්ඩ ප්‍රවාහනය සඳහා බටහිර මුහුදුබඩ තැනූ දිගු ඇළ පද්ධතිය කුමක්ද?", listOf("Yoda Ela", "Hamilton Canal / Dutch Canal", "Minipe Canal", "Ganga diversion"), listOf("යෝධ ඇළ", "හැමිල්ටන් ඇළ / ලන්දේසි ඇළ (Canals)", "මිණිපේ ඇළ", "ගඟ හැරවීම"), 1)
        2 -> Pentuple("What was the primary currency of traditional trade before coins?", "කාසි භාවිතයට පෙර සාම්ප්‍රදායික වෙළඳාමේදී බඩු භාණ්ඩ හුවමාරු කරගත් ක්‍රමය?", listOf("Gold", "Barter system", "Paper bills", "Shells"), listOf("රන්", "භාණ්ඩ හුවමාරු ක්‍රමය (Barter)", "කඩදාසි සල්ලි", "කවඩි"), 1)
        3 -> Pentuple("What ancient craft involves using clay baked in fire to make utilities?", "මැටිවලින් බඳුන් සාදා අව්වේ වියලා, පසුව පුළුස්සා ගැනීම හඳුන්වන කර්මාන්තය කුමක්ද?", listOf("Blacksmithing", "Pottery (Liyana/Kumbalkarmanthaya)", "Weaving", "Carving"), listOf("යකඩ කර්මාන්තය", "කුඹල් කර්මාන්තය (Pottery)", "රෙදි විවීම", "ගල් කැටයම්"), 1)
        4 -> Pentuple("What is the names of the indigenous tribal forest-dwellers of Sri Lanka?", "ශ්‍රී ලංකාවේ වනාන්තර ආශ්‍රිතව ජීවත් වන ආදිවාසී ජන කොටස හඳුන්වන්නේ?", listOf("Siddhas", "Veddhas", "Yaksas", "Nagas"), listOf("සිද්ධයන්", "වැද්දන් (Veddhas)", "යක්ෂයින්", "නාගයින්"), 1)
        5 -> Pentuple("What fiber is used in handloom to weave traditional Sri Lankan mats (Paduru)?", "සාම්ප්‍රදායික පැදුරු විවීම සඳහා යොදාගන්නා කොළ විශේෂය කුමක්ද?", listOf("Coconut leaves", "Pang leaves / Hana (Hana-Pang)", "Mango leaves", "Banana fiber"), listOf("පොල් කොළ", "පන් කොළ / හණ (Pang)", "අඹ කොළ", "කෙසෙල් කන්"), 1)
        6 -> Pentuple("Which local tool was historically used to squeeze oil from coconuts manually?", "පැරණි කාලයේ තෙල් සහ කිරි මිරිකා හැරීම සඳහා ලීයෙන් තනා ගත් උපකරණය කුමක්ද?", listOf("Wangediya", "Sekkuwa / Ath-Sekkuwa", "Mirikuma", "Kulla"), listOf("වංගෙඩිය", "සේක්කුව (Sekkuwa)", "මිරිකුම", "කුල්ල"), 1)
        7 -> Pentuple("Who designed the world-famous water gardens of Sigiriya?", "සීගිරියේ ලොව පතළ දිය උද්‍යාන සහ තාක්ෂණය නිර්මාණය කරවන්නේ කවුද?", listOf("King Kasyapa", "King Dutugemunu", "King Dhatusena", "King Aggabodhi"), listOf("කාශ්‍යප රජතුමා (King Kasyapa)", "දුටුගැමුණු රජතුමා", "ධාතුසේන රජතුමා", "අග්ගබෝධි රජතුමා"), 0)
        8 -> Pentuple("What is the historical significance of Avukana Buddha statue?", "අවුකන බුද්ධ ප්‍රතිමා වහන්සේගේ ඇති විශේෂත්වය කුමක්ද?", listOf("It is made of wood", "It is carved out of a single living rock", "It is very small", "It is imported from China"), listOf("එය ලීයෙන් නිමවා ඇත", "එය තනි නොකැඩුණු උසැති පාෂාණ කඳකින් නෙළා ඇත (Rock carving)", "එය ඉතා කුඩාය", "එය චීනයෙන් ගෙන්වා ඇත"), 1)
        else -> Pentuple("Where is the historic gold-plated temple containing the Tooth Relic situated?", "දළදා වහන්සේ වැඩසිටින රත්තරන් වියනක් සහිත මාළිගාව පිහිටා ඇති නගරය?", listOf("Anuradhapura", "Kandy", "Polonnaruwa", "Kotte"), listOf("අනුරාධපුරය", "මහනුවර (Kandy)", "පොළොන්නරුව", "කෝට්ටේ"), 1)
    }

    // GRADE 3
    private fun getGrade3Stage1(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("Which kingdom was the very first capital of ancient Sri Lanka?", "පෞරාණික ලංකාවේ මුල්ම රාජධානිය කුමක්ද?", listOf("Polonnaruwa", "Anuradhapura", "Kandy", "Kotte"), listOf("පොළොන්නරුව", "අනුරාධපුරය (Anuradhapura)", "මහනුවර", "කෝට්ටේ"), 1)
        1 -> Pentuple("Which capital is famous for Alahana Pirivena and Quadrangle ruins?", "ඓතිහාසික අටදාගෙය, වටදාගෙය පිහිටි දෙවන අගනුවර (රාජධානිය) කුමක්ද?", listOf("Kurunegala", "Polonnaruwa", "Yapahuwa", "Gampola"), listOf("කුරුණෑගල", "පොළොන්නරුව (Polonnaruwa)", "යාපහුව", "ගම්පොළ"), 1)
        2 -> Pentuple("On which rock did King Kasyapa build his palace?", "කාශ්‍යප රජතුමා සිය මාලිගය ඉදි කළේ කුමන ඓතිහාසික පර්වතය මතද?", listOf("Mihintale", "Sigiriya Rock", "Yapahuwa Rock", "Pidurangala"), listOf("මිහින්තලය", "සීගිරි පර්වතය (Sigiriya)", "යාපහු පර්වතය", "පිදුරංගල"), 1)
        3 -> Pentuple("What flag was historically used by the Kings of Mahanuwara (Kandy)?", "ඓතිහාසික කන්‍ද උඩරට (මහනුවර) රාජධානියේ රජවරුන් භාවිතා කළ ධජය කුමක්ද?", listOf("Swastika flag", "Kanda Simha Flag (Lion with Sword)", "Jolly Roger", "Dutch Flag"), listOf("ස්වස්තික ධජය", "සිංහ ධජය (Simha Flag)", "ජොලි රොජර්", "ලන්දේසි කොඩිය"), 1)
        4 -> Pentuple("What is the traditional layout of ancient Buddhist monasteries called?", "පුරාණ විහාරාරාමවල බහුලව දක්නට ලැබෙන ඓතිහාසික ගොඩනැඟිලි රටාව කුමක්ද?", listOf("Fortress", "Panchayathana / Monasteries", "Pyramid", "Skyscraper"), listOf("බලකොටුව", "පංචායතන / පන්සල් ආකෘතිය (Monasteries)", "පිරමිඩ", "අහස්සූනි"), 1)
        5 -> Pentuple("Which structure represents Sri Lankan stone carving perfection containing animal carvings at entry step?", "විහාරස්ථානවලට ඇතුල් වන පියගැටපෙළ පාමුල පිහිටි අර්ධ කව හැඩැති අලංකාර කැටයම කුමක්ද?", listOf("Korawakgala", "Sandakada Pahana (Moonstone)", "Muragala", "Naga Gala"), listOf("කොරවක්ගල", "සඳකඩපහණ (Moonstone)", "මුරගල", "නාග ගල"), 1)
        6 -> Pentuple("Which stone guard stands beside the steps of ancient temples, depicting prosperity?", "සඳකඩපහණ දෙපස ඇති, සෞභාග්‍යය නිරූපණය කරන කැටයම් සහිත ගල් ලෑල්ල කුමක්ද?", listOf("Korawakgala", "Muragala (Guardstone depicting Cobra King)", "Sigiri Mirror Wall", "Gal Potha"), listOf("කොරවක්ගල", "මුරගල (Guardstone)", "කන්නාඩි පවුර", "ගල් පොත"), 1)
        7 -> Pentuple("What ancient structure is designed on the balustrades of temple stairs, shaped like mythical sea dragon?", "පඩිපෙළ දෙපස ඇති, මකර කුක්ෂිය නිරූපණය කරන කැටයම් කොටස කුමක්ද?", listOf("Sandakada Pahana", "Korawakgala", "Muragala", "Asana"), listOf("සඳකඩපහණ", "කොරවක්ගල (Korawakgala)", "මුරගල", "ආසන"), 1)
        8 -> Pentuple("Where is the world-famous mirror wall located?", "ලොව පතල කුරුටු ගී (Sigiri graffiti) ලියා ඇති කන්නාඩි පවුර පිහිටා ඇත්තේ කොහේද?", listOf("Abhayagiriya", "Sigiriya", "Polonnaruwa", "Jaffna Fort"), listOf("අභයගිරිය", "සීගිරිය (Sigiriya Mirror Wall)", "පොළොන්නරුව", "යාපනය බලකොටුව"), 1)
        else -> Pentuple("Who built the Yapahuwa Rock fortress with the famous Chinese-style lion staircase?", "චීන විලාසිතාවේ සිංහ පියගැටපෙළක් සහිත යාපහුව පර්වත බලකොටුව තැනවූයේ කවුද?", listOf("King Vijayabahu I", "King Bhuvanekabahu I", "King Mayadunne", "King Rajasinghe I"), listOf("පළමුවන විජයබාහු රජු", "පළමුවන බුවනෙකබාහු රජතුමා (Bhuvanekabahu)", "මායාදුන්නේ රජු", "පළමුවන රාජසිංහ රජු"), 1)
    }

    private fun getGrade3Stage2(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("Which King liberated Sri Lanka from Chola invaders and established the Polonnaruwa Kingdom?", "චෝල ආක්‍රමණයෙන් රට මුදාගෙන පොළොන්නරුව රාජධානිය ආරම්භ කළ රජතුමා කවුද?", listOf("King Dutugemunu", "King Vijayabahu I", "King Parakramabahu I", "King Nissankamalla"), listOf("දුටුගැමුණු රජතුමා", "පළමුවන විජයබාහු රජතුමා (Vijayabahu I)", "පළමුවන පරාක්‍රමබාහු රජතුමා", "නිශ්ශංකමල්ල රජතුමා"), 1)
        1 -> Pentuple("What is the giant water reservoir constructed by King Parakramabahu I in Polonnaruwa?", "පොළොන්නරුවේ පළමුවන පරාක්‍රමබාහු රජු කරවූ මහා වැව කුමක්ද?", listOf("Kalawewa", "Parakrama Samudra", "Minneriya Wewa", "Yoda Wewa"), listOf("කලාවැව", "පරාක්‍රම සමුද්‍රය (Parakrama Samudra)", "මින්නේරිය වැව", "යෝධ වැව"), 1)
        2 -> Pentuple("What unique round temple structure is found in Polonnaruwa containing four Buddha statues?", "පොළොන්නරුවේ ඇති බුදු පිළිම වහන්සේලා සතර නමකගෙන් සුසැදි වටකුරු වාස්තු නිර්මාණය කුමක්ද?", listOf("Hatadage", "Polonnaruwa Vatadage", "Lankatilaka", "Nissanka Latha Mandapaya"), listOf("හැටදාගෙය", "පොළොන්නරුව වටදාගෙය (Vatadage)", "ලංකාතිලකය", "නිශ්ශංක ලතා මණ්ඩපය"), 1)
        3 -> Pentuple("Which famous book-shaped giant stone inscription describes King Nissankamalla's work?", "නිශ්ශංකමල්ල රජුගේ තොරතුරු ඇතුළත්, පොතක හැඩයට නෙළා ඇති දැවැන්ත සෙල්ලිපිය කුමක්ද?", listOf("Gal Potha (Stone Book)", "Ruwanweli inscription", "Badulla Pillar", "Panakaduwa copper sheet"), listOf("ගල් පොත (Gal Potha)", "රුවන්වැලි සෙල්ලිපිය", "බදුලු ටැම් ලිපිය", "පනාකඩුව තඹ සන්නස"), 0)
        4 -> Pentuple("Who built the Minneriya tank, earning the status of 'Minneri Deviyo' among locals?", "මින්නේරිය වැව සාදා ප්‍රදේශවාසීන්ගේ ගෞරවයට හා දේවත්වයට පත් වූ රජු කවුද?", listOf("King Mahasen", "King Dhatusena", "King Vasabha", "King Kasyapa"), listOf("මහසෙන් රජතුමා (King Mahasen)", "ධාතුසේන රජතුමා", "වසභ රජතුමා", "කාශ්‍යප රජතුමා"), 0)
        5 -> Pentuple("What historic kingdom was famously situated on a high hill surrounded by three natural ramparts?", "ස්වභාවික පර්වත පවුරු තුනකින් වට වූ කන්දක් මතුපිට පිහිටුවූ මැදකාලීන රාජධානිය කුමක්ද?", listOf("Kotte", "Yapahuwa", "Gampola", "Dambadeniya"), listOf("කෝට්ටේ", "යාපහුව (Yapahuwa)", "ගම්පොළ", "දඹදෙණිය"), 1)
        6 -> Pentuple("Which kingdom was the last independent capital of Sri Lanka?", "ශ්‍රී ලංකාවේ අවසාන ස්වදේශීය රාජධානිය කුමක්ද?", listOf("Kotte", "Gampola", "Kandy / Mahanuwara", "Sitawaka"), listOf("කෝට්ටේ", "ගම්පොළ", "මහනුවර / උඩරට (Kandy)", "සීතාවක"), 2)
        7 -> Pentuple("Who designed the beautifully planned city of Kandy, serving as its first king?", "සෙන්කඩගල (මහනුවර) නගරය මුලින්ම රාජධානියක් කරගත් රජතුමා කවුද?", listOf("King Wimaladharmasuriya I", "King Senasammata Vikramabahu", "King Sri Vikrama Rajasinha", "King Rajasinghe II"), listOf("පළමුවන විමලධර්මසූරිය රජු", "සේනාසම්මත වික්‍රමබාහු රජතුමා (Vikramabahu)", "ශ්‍රී වික්‍රම රාජසිංහ", "දෙවන රාජසිංහ"), 1)
        8 -> Pentuple("Which river acts as a natural boundary surrounding the city of Kandy?", "මහනුවර නගරය වටකරමින් ගලා යන ලංකාවේ දිගම ගංගාව කුමක්ද?", listOf("Kelani River", "Mahaweli River", "Kalu Ganga", "Walawe Ganga"), listOf("කැලණි ගඟ", "මහවැලි ගඟ (Mahaweli)", "කළු ගඟ", "වළවේ ගඟ"), 1)
        else -> Pentuple("What is the traditional name of the royal audience hall in Kandy?", "මහනුවර රජ මාලිගය ආශ්‍රිතව ඇති පැරණි රාජ සභා ශාලාව හඳුන්වන්නේ කුමන නමකින්ද?", listOf("Siri Latha", "Magul Maduwa", "Ran Viyana", "Pattirippuwa"), listOf("සිරි ලතා", "මගුල් මඩුව (Magul Maduwa)", "රන් වියන", "පත්තිරිප්පුව"), 1)
    }

    private fun getGrade3Stage3(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("Who was the last king of the Kandyan Kingdom and Sri Lanka?", "උඩරට රාජධානියේ සහ මුළු ලංකාවේම අවසාන රජතුමා කවුද?", listOf("King Rajasinghe II", "King Sri Vikrama Rajasinha", "King Kirti Sri Rajasinghe", "King Sri Vijaya Rajasinghe"), listOf("දෙවන රාජසිංහ", "ශ්‍රී වික්‍රම රාජසිංහ (Sri Vikrama Rajasinha)", "කීර්ති ශ්‍රී රාජසිංහ", "ශ්‍රී විජය රාජසිංහ"), 1)
        1 -> Pentuple("In which historical year was the Kandyan Convention signed, handing power to British?", "උඩරට ගිවිසුම අත්සන් කරමින් ලංකාව බ්‍රිතාන්‍යයන්ට යටත් වූ වර්ෂය කුමක්ද?", listOf("1505", "1796", "1815", "1948"), listOf("1505", "1796", "1815 (Kandyan Convention)", "1948"), 2)
        2 -> Pentuple("What traditional drum is played during temple festivals and Hewisi performances?", "පන්සල්වල හේවිසි සහ උත්සව කාලවලදී වාදනය කරන සාම්ප්‍රදායික දිගු බෙර වර්ගය කුමක්ද?", listOf("Getabera", "Hewisi bera / Daula", "Thammattama", "Yak Bera"), listOf("ගැටබෙර", "දවුල (Daula)", "තම්මැට්ටම", "යක් බෙර"), 1)
        3 -> Pentuple("What is the unique octagonal structure in the temple of tooth relics constructed by Devendra Mulachari?", "දළදා මාළිගා පරිශ්‍රයේ ඇති, දේවෙන්ද්‍ර මූලාචාරී විසින් නිමවූ අටපට්ටම් හැඩැති ගොඩනැඟිල්ල කුමක්ද?", listOf("Ran Viyana", "Pattirippuwa (The Octagon)", "Magul Maduwa", "Hewisi Mandapaya"), listOf("රන් වියන", "පත්තිරිප්පුව (Pattirippuwa)", "මගුල් මඩුව", "හේවිසි මණ්ඩපය"), 1)
        4 -> Pentuple("Who was the prime minister who signed the Kandyan Convention representing the locals?", "සිංහල පාර්ශවය වෙනුවෙන් උඩරට ගිවිසුමට මුලින්ම අත්සන් කළ මහ අධිකාරම්වරයා කවුද?", listOf("Keppetipola", "Ehelepola", "Pilimatalavuva", "John D'Oyly"), listOf("කැප්පෙටිපොල", "ඇහැලේපොල (Ehelepola / Adikaram)", "පිළිමතලාව්ව", "ජෝන් ඩොයිලි"), 1)
        5 -> Pentuple("What local art form uses natural dyes on cotton cloths to draw traditional tales in temples?", "නොවෙනස් වන ස්වභාවික සායම් මඟින් පන්සල්වල සාදනු ලබන බිතුසිතුවම් කලාව කුමක්ද?", listOf("Oil paintings", "Temple Murals / Frescoes (Vihara Chithra)", "Watercolors", "Grafitti"), listOf("තෙල් සායම්", "බිතුසිතුවම් / විහාර චිත්‍ර (Murals)", "දිය සායම්", "කුරුටු ගී"), 1)
        6 -> Pentuple("Which local dance style originates specifically from the Kandyan hill country?", "උඩරට ප්‍රදේශයෙන් බිහිවූ, වෙස් ඇඳුමින් සැරසී කරන ප්‍රධාන නැටුම් ශෛලිය කුමක්ද?", listOf("Sabaragamuwa Dance", "Kandyan Dance (Udarata Natum)", "Low country Dance", "Kathak"), listOf("සබරගමුව නර්තනය", "උඩරට නැටුම් (Kandyan Dance)", "පහතරට නැටුම්", "කතක්"), 1)
        7 -> Pentuple("What ocean-boundary fortress in Southern Sri Lanka is famous for Portuguese, Dutch, and British changes?", "දකුණු ලංකාවේ පිහිටි ලන්දේසීන් විසින් විශාල ලෙස වැඩිදියුණු කළ පැරණි මුහුදුබඩ බලකොටුව කුමක්ද?", listOf("Colombo Fort", "Galle Fort", "Jaffna Fort", "Batticaloa Fort"), listOf("කොළඹ බලකොටුව", "ගාලු කොටුව (Galle Fort)", "යාපනය බලකොටුව", "මඩකලපුව කොටුව"), 1)
        8 -> Pentuple("The historical chronicle covering the lineages and histories of Sri Lankan kings is written in:", "ලක් රජවරුන්ගේ ඉතිහාසය විස්තරාත්මකව ඇතුළත් ඉපැරණි ඓතිහාසික ග්‍රන්ථය කුමක්ද?", listOf("Poojavaliya", "Mahavamsa (Great Chronicle)", "Saddharmarathnavaliya", "Thupavamsa"), listOf("පූජාවලිය", "මහාවංශය (Mahavamsa)", "සද්ධර්මරත්නාවලිය", "ථූපවංශය"), 1)
        else -> Pentuple("Which colonial power built the extensive star fort of Matara?", "මාතර පිහිටි තාරකා හැඩැති සුවිශේෂී බලකොටුව (Star Fort) නිර්මාණය කළ විදේශිකයින් කවුද?", listOf("Portuguese", "Dutch", "British", "French"), listOf("පෘතුගීසීන්", "ලන්දේසීන් (Dutch)", "බ්‍රිතාන්‍යයන්", "ප්‍රංශ"), 1)
    }

    // GRADE 4
    private fun getGrade4Stage1(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("According to legends, what was the powerful aviation machine used by King Ravana?", "ජනප්‍රවාදවලට අනුව රාවණා රජතුමා ගමන් බිමන් සඳහා යොදාගත් ගුවන් යානය කුමක්ද?", listOf("Angampora", "Dandu Monara (Flying machine)", "Pushpaka Vimana directly", "Yantra Woodcraft"), listOf("අංගම්පොර", "දඬු මොනර යන්ත්‍රය (Dandu Monara)", "පුෂ්පක විමානය කෙලින්ම", "ලී යන්ත්‍ර"), 1)
        1 -> Pentuple("Where is Ravana Ella, a waterfall connected with Ravana legends, located?", "රාවණා රජුගේ කථා සමඟ සම්බන්ධ වන රාවණා ඇල්ල පිහිටා ඇත්තේ කුමන ප්‍රදේශය ආසන්නයේද?", listOf("Anuradhapura", "Ella (Badulla District)", "Colombo", "Galle"), listOf("අනුරාධපුරය", "ඇල්ල - බදුල්ල (Ella)", "කොළඹ", "ගාල්ල"), 1)
        2 -> Pentuple("What is the legendary ancient system of physical martial art of Sri Lanka?", "ශ්‍රී ලංකාවේ පවතින ඉපැරණි සාම්ප්‍රදායික සටන් කලාව කුමක්ද?", listOf("Karate", "Angampora", "Kung Fu", "Kalaripayattu"), listOf("කරාතේ", "අංගම්පොර (Angampora)", "කුංෆු", "කලරිපයාට්ටු"), 1)
        3 -> Pentuple("Who was the Indian prince whose arrival is recorded as the start of Sinhalese civilization?", "ලංකාවට පැමිණ සිංහල ජනාවාස ආරම්භ කළ බව මහාවංශයේ දැක්වෙන ඉන්දීය කුමාරයා කවුද?", listOf("Prince Saliya", "Prince Vijaya", "Prince Dutugemunu", "Prince Siddhartha"), listOf("සාලිය කුමාරයා", "විජය කුමාරයා (Prince Vijaya)", "දුටුගැමුණු කුමාරයා", "සිද්ධාර්ථ කුමාරයා"), 1)
        4 -> Pentuple("According to chronicles, Prince Vijaya arrived in Sri Lanka with how many followers?", "ලංකාවට පැමිණි විජය කුමාරයා සමඟ පැමිණි මුළු සගයන් සංඛ්‍යාව කොපමණද?", listOf("100", "500", "700", "1000"), listOf("100", "500", "700 (හත්සියයක් සගයන්)", "1000"), 2)
        5 -> Pentuple("Where did Prince Vijaya and his followers land in Sri Lanka?", "විජය කුමාරයා සහ ඔහුගේ පිරිස ලංකාවට මුලින්ම ගොඩබැස්ස ස්ථානය කුමක්ද?", listOf("Galle", "Mannar (Tambapanni / Kudiramalai)", "Trincomalee", "Hambantota"), listOf("ගාල්ල", "මන්නාරම / තම්බපණ්ණි (Tambapanni)", "ත්‍රිකුණාමලය", "හම්බන්තොට"), 1)
        6 -> Pentuple("What was the name of the local princess (yaksa queen) who helped Prince Vijaya?", "විජය කුමාරයාට ලංකාවේ පාලනය ලබා ගැනීමට උදව් කළ මෙරට යක්ෂ ගෝත්‍රික කුමරිය කවුද?", listOf("Soma Devi", "Kuveni", "Anula Devi", "Viharamahadevi"), listOf("සෝමා දේවිය", "කුවේණි (Kuveni)", "අනුලා දේවිය", "විහාරමහාදේවිය"), 1)
        7 -> Pentuple("What did Vijaya rename the country due to the copper-colored soil of the landing beach?", "තඹ පැහැති වැලි සහිත වෙරළ නිසා විජය කුමාරයා ලංකාවට තැබූ නම කුමක්ද?", listOf("Lanka", "Tambapanni", "Ceilao", "Seylan"), listOf("ලංකා", "තම්බපණ්ණි (Tambapanni)", "සේලාන්", "සෙයිලාන්"), 1)
        8 -> Pentuple("Which plant was brought as a sacred icon by Theri Sanghamitta?", "සංඝමිත්තා මෙහෙණින් වහන්සේ විසින් ලංකාවට වැඩම කරවූ පූජනීය වෘක්ෂ කොටස කුමක්ද?", listOf("Bo Tree sapling (Jaya Sri Maha Bodhi branch)", "Sal tree seed", "Lotus flower", "Mango plant"), listOf("ශ්‍රී මහා බෝධින් වහන්සේගේ දක්ෂිණ ශාඛාව (Sri Maha Bodhi)", "සල් ඇටය", "නෙළුම් මල", "අඹ පැළය"), 0)
        else -> Pentuple("Where was Sri Maha Bodhi sapling planted in Anuradhapura?", "ශ්‍රී ලංකාවට වැඩම කළ ශ්‍රී මහා බෝධි ශාඛාව රෝපණය කළේ අනුරාධපුරයේ කුමන උද්‍යානයේද?", listOf("Nandana Uyana", "Mahamevnawa Park (Mahamegha Uyana)", "Ranmasu Uyana", "Victoria Park"), listOf("නන්දන උයන", "මහමෙව්නා උයන (Mahamevnawa)", "රන්මසු උයන", "වික්ටෝරියා උයන"), 1)
    }

    private fun getGrade4Stage2(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("Which giant stupa in Anuradhapura was built by King Dutugemunu but completed by his brother?", "දුටුගැමුණු රජු ආරම්භ කර ඔහුගේ සොහොයුරා නිම කළ අනුරාධපුරයේ විශාල ස්තූපය?", listOf("Ruwanwelisaya (Mahathupa)", "Abhayagiriya", "Thuparamaya", "Mirisawetiya"), listOf("රුවන්වැලි මහා සෑය (Ruwanwelisaya)", "අභයගිරිය", "ථූපාරාමය", "මිරිසවැටිය"), 0)
        1 -> Pentuple("Who completed the construction of Ruwanwelisaya after King Dutugemunu passed away?", "දුටුගැමුණු රජුගේ මරණයෙන් පසු රුවන්වැලිසෑයේ වැඩ නිම කළ ඔහුගේ සහෝදරයා කවුද?", listOf("King Saddhatissa", "King Walagamba", "King Lajjatissa", "King Khallata Naga"), listOf("සද්ධාතිස්ස රජතුමා (Saddhatissa)", "වළගම්බා රජතුමා", "ලජ්ජිතිස්ස රජතුමා", "ඛල්ලාටනාග රජතුමා"), 0)
        2 -> Pentuple("Which stupa is the very first stupa built in post-Buddhist Sri Lanka?", "ලංකාවට බුදු දහම ලැබීමෙන් පසු ඉදි කළ ලංකාවේ පළමු ස්තූපය කුමක්ද?", listOf("Ruwanwelisaya", "Thuparamaya", "Jetavanaramaya", "Kirivehera"), listOf("රුවන්වැලිසෑය", "ථූපාරාමය (Thuparamaya)", "ජේතවනාරාමය", "කිරිවෙහෙර"), 1)
        3 -> Pentuple("Who built the Thuparamaya Dagoba?", "ථූපාරාම ස්තූපය ඉදි කරවූ රජතුමා කවුද?", listOf("King Devanampiyatissa", "King Dutugemunu", "King Vasabha", "King Mahasen"), listOf("දේවානම්පියතිස්ස රජතුමා (Devanampiyatissa)", "දුටුගැමුණු රජතුමා", "වසභ රජතුමා", "මහසෙන් රජතුමා"), 0)
        4 -> Pentuple("The sacred tooth relic of Buddha was brought to Sri Lanka during the reign of which king?", "ශ්‍රී දළදා වහන්සේ ලංකාවට වැඩම කරවනු ලැබුවේ කුමන රජතුමාගේ රාජ්‍ය සමයේද?", listOf("King Dhatusena", "King Kirtsiri Meghavarna (Guhasiva era)", "King Mahasen", "King Vijayabahu I"), listOf("ධාතුසේන රජතුමා", "කිත්සිරිමෙවන් රජතුමා (Kirtsiri Meghavarna)", "මහසෙන් රජතුමා", "විජයබාහු රජතුමා"), 1)
        5 -> Pentuple("Who was the princess who brought the sacred tooth relic hidden in her hair?", "සිය කෙස් කළඹේ දළදා වහන්සේ සඟවාගෙන ලංකාවට වැඩම කළ ඉන්දීය කුමරිය කවුද?", listOf("Princess Sanghamitta", "Princess Hemamali", "Princess Anula", "Princess Lilavati"), listOf("සංඝමිත්තා කුමරිය", "හේමමාලා කුමරිය (Princess Hemamali)", "අනුලා කුමරිය", "ලීලාවතී කුමරිය"), 1)
        6 -> Pentuple("Who was the prince who accompanied Princess Hemamali to protect the tooth relic?", "දළදා වහන්සේ වැඩමවීමේදී හේමමාලා කුමරිය සමඟ පැමිණි කුමාරයා කවුද?", listOf("Prince Dantha", "Prince Vijaya", "Prince Saliya", "Prince Kassapa"), listOf("දන්ත කුමාරයා (Prince Dantha)", "විජය කුමාරය", "සාලිය කුමාරයා", "කාශ්‍යප කුමාරයා"), 0)
        7 -> Pentuple("Which King is famous for building the fortress of Sigiriya?", "සීගිරිය පර්වත බලකොටුව නිර්මාණය කළ කාශ්‍යප රජුගේ පියා කවුද?", listOf("King Dhatusena (father of Kasyapa)", "King Mahasen", "King Vasabha", "King Walagamba"), listOf("ධාතුසේන රජතුමා (Dhatusena)", "මහසෙන් රජතුමා", "වසභ රජතුමා", "වළගම්බා රජතුමා"), 0)
        8 -> Pentuple("What is the name of the beautiful poems written by visitors on Sigiriya's Mirror Wall?", "සීගිරිය නැරඹීමට පැමිණි පැරණි සිංහලයන් කන්නාඩි පවුර මත ලියා තැබූ කවි හඳුන්වන්නේ?", listOf("Graffiti (Sigiri Kurutu Gee)", "Kavyashekhara", "Selalihi Sandeshaya", "Gira Sandeshaya"), listOf("සීගිරි කුරුටු ගී (Kurutu Gee)", "කාව්‍යශේකරය", "සැළලිහිණි සන්දේශය", "ගිරා සන්දේශය"), 0)
        else -> Pentuple("Which king constructed the massive Abhayagiri Dagoba, which became a great center of studies?", "අභයගිරි මහා ස්තූපය සහ විහාරය ඉදි කරවූ රජතුමා කවුද?", listOf("King Walagamba (Vattagamani Abhaya)", "King Dutugemunu", "King Kasyapa", "King Dhatusena"), listOf("වළගම්බා රජතුමා (Walagamba)", "දුටුගැමුණු රජතුමා", "කාශ්‍යප රජතුමා", "ධාතුසේන රජතුමා"), 0)
    }

    private fun getGrade4Stage3(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("Who was the South Indian invader who ruled Anuradhapura for 44 years with extreme justice?", "අනුරාධපුරය වසර 44ක් සාධාරණව පාලනය කළ දෙමළ ආක්‍රමණික රජතුමා කවුද?", listOf("King Elara", "King Sena", "King Guttika", "King Magha"), listOf("එළාර රජතුමා (Elara)", "සේන රජතුමා", "ගුත්තික රජතුමා", "මාඝ රජතුමා"), 0)
        1 -> Pentuple("Where did the historic battle between King Dutugemunu and King Elara take place?", "දුටුගැමුණු රජු සහ එළාර රජු අතර අවසාන තීරණාත්මක ද්වන්ධ සටන පැවැත්වුණේ කොහේද?", listOf("Vijithapura", "Polonnaruwa", "Kandy", "Galle"), listOf("විජිතපුරය / කඩුල්ල පවුර (Battle near town)", "පොළොන්නරුව", "මහනුවර", "ගාල්ල"), 0)
        2 -> Pentuple("What was the name of King Dutugemunu's noble royal elephant?", "දුටුගැමුණු රජතුමාගේ මංගල හස්තිරාජයාගේ නම කුමක්ද?", listOf("Kadol Etha (Kadol Elephant)", "Mal Etha", "Giri Etha", "Senevi Etha"), listOf("කඩොල් ඇතා (Kadol Etha)", "මල් ඇතා", "ගිරි ඇතා", "සෙනෙවි ඇතා"), 0)
        3 -> Pentuple("What was the name of the horse ridden by Elara's soldiers or Dutugemunu's heroes?", "කුවේණිය හෝ දුටුගැමුණුගේ දස මහා යෝධයන් අතර සිටි වේළුසුමන යෝධයා පැදවූ අශ්වයා?", listOf("Saindhawa", "Mini", "Kalu", "Ran"), listOf("සෛන්ධව (Saindhawa)", "මිණි", "කළු", "රන්"), 0)
        4 -> Pentuple("Who were the ten legendary giant warriors of King Dutugemunu?", "දුටුගැමුණු රජුට සේනාව මෙහෙයවීමට සිටි ප්‍රධාන දක්ෂ සෙන්පතියන් හඳුන්වන්නේ?", listOf("Dasa Maha Yodayo (Ten Giants)", "Nava Mini", "Seneviyo", "Atara"), listOf("දස මහා යෝධයෝ (Dasa Maha Yodayo)", "නව මිණි", "සෙනෙවියෝ", "අටාර"), 0)
        5 -> Pentuple("Which of the following was one of the prime Ten Giants of Dutugemunu?", "දුටුගැමුණු රජුගේ දස මහා යෝධයන් අතර සිටි ප්‍රසිද්ධම යෝධයෙක් කවුද?", listOf("Nandhimithra / Suranimala / Theraputthabhya", "Minister Anuradha", "Devendra", "Mayadunne"), listOf("නන්දිමිත්‍ර / සුරනිමල / ථේරපුත්තාභය", "අනුරාධ ඇමති", "දේවෙන්ද්‍ර", "මායාදුන්නේ"), 0)
        6 -> Pentuple("Which giant warrior was famous for his outstanding physical strength, breaking city gates?", "කොටු පවුරු සහ යදම් බිඳ දැමීමට දක්ෂම වීර ශක්තිවන්තයා කවුද?", listOf("Nandhimithra", "Velusumana", "Suranimala", "Theraputthabhya"), listOf("නන්දිමිත්‍ර (Nandhimithra)", "වේළුසුමන", "සුරනිමල", "ථේරපුත්තාභය"), 0)
        7 -> Pentuple("What is the ancient pond built in the shape of a blooming lotus in Polonnaruwa called?", "පොළොන්නරුවේ ඇති නෙළුම් මලක හැඩයට තැනවූ පැරණි පොකුණ කුමක්ද?", listOf("Kuttam Pokuna", "Nelum Pokuna (Lotus Pond ruins)", "Kumara Pokuna", "Yantra Pokuna"), listOf("කුට්ටම් පොකුණ", "නෙළුම් පොකුණ (Nelum Pokuna)", "කුමාර පොකුණ", "යන්ත්‍ර පොකුණ"), 1)
        8 -> Pentuple("The twin ponds of Anuradhapura are historically known as:", "අනුරාධපුරයේ භික්ෂූන් වහන්සේලාගේ ජල අවශ්‍යතා සඳහා නිමවූ නිවුන් පොකුණු හඳුන්වන්නේ:", listOf("Kuttam Pokuna (Twin Ponds)", "Kumara Pokuna", "Elephant Pond", "Lotus Pond"), listOf("කුට්ටම් පොකුණ (Kuttam Pokuna)", "කුමාර පොකුණ", "ඇත් පොකුණ", "නෙළුම් පොකුණ"), 0)
        else -> Pentuple("What was the name of the special underground water system filter used in Kuttam Pokuna?", "කුට්ටම් පොකුණට පිරිසිදු ජලය ගලා ඒමට සකස් කර තිබූ පෙරහන් තාක්ෂණය කුමක්ද?", listOf("Mud traps and brick conduits", "Plastic pipes", "Chlorine basins", "Deep bores"), listOf("මඩ සහ රොන්මඩ රඳවන බඳුන් හා මැටි නල (Mud traps)", "ප්ලාස්ටික් නල", "ක්ලෝරීන් ටැංකි", "ගැඹුරු ළිං"), 0)
    }

    // GRADE 5
    private fun getGrade5Stage1(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("In which historical year did the Portuguese land in Sri Lanka accidentally?", "පෘතුගීසීන් අහම්බෙන් ලංකාවට පැමිණි ඓතිහාසික වර්ෂය කුමක්ද?", listOf("1505", "1602", "1658", "1796"), listOf("1505 (Portuguese arrival)", "1602", "1658", "1796"), 0)
        1 -> Pentuple("Who was the Portuguese captain who first arrived in Colombo harbour in 1505?", "1505 දී කොළඹ වරායට මුලින්ම පැමිණි පෘතුගීසි කපිතාන්වරයා කවුද?", listOf("Dom Lourenco de Almeida", "Vasco da Gama", "Constantino de Sa", "Gerard Hulft"), listOf("දොම් ලොරෙන්සෝ ද අල්මේදා (Almeida)", "වාස්කෝ ද ගාමා", "කොන්ස්තන්තිනෝ ද සා", "ජෙරාඩ් හල්ෆ්ට්"), 0)
        2 -> Pentuple("What did the Portuguese rename Sri Lanka in their language documents?", "පෘතුගීසීන් ලංකාව ඔවුන්ගේ ලේඛනවල හඳුන්වා දුන්නේ කුමන නමකින්ද?", listOf("Taprobane", "Ceilao", "Zeylan", "Ceylon"), listOf("තැප්‍රොබේන්", "සේලාඕ (Ceilao)", "සෙයිලාන්", "සිලෝන්"), 1)
        3 -> Pentuple("Which local king was ruling Kotte when the Portuguese arrived in 1505?", "පෘතුගීසීන් ලංකාවට පැමිණෙන විට කෝට්ටේ අගනුවර පාලනය කළ රජතුමා කවුද?", listOf("King Parakramabahu VIII", "King Dharma Parakramabahu IX", "King Buwanekabahu VII", "King Mayadunne"), listOf("අටවන පරාක්‍රමබාහු", "නවවන ධර්ම පරාක්‍රමබාහු (Dharma Parakramabahu)", "හත්වන බුවනෙකබාහු", "මායාදුන්නේ"), 1)
        4 -> Pentuple("What is the historic Sinhala saying derived from the Portuguese being taken through a long circular path to Kotte?", "පෘතුගීසීන් කෝට්ටේ දක්වා වටරවුම් මඟකින් ගෙන යාම නිසා ඇති වූ ක්‍රියා පදය කුමක්ද?", listOf("Going to Kotte is difficult", "Parangi Kottegiya Wage (Like the Portuguese went to Kotte)", "Galle Face style", "Parakrama system"), listOf("කෝට්ටේ යන්න ලේසි නෑ", "පරංගියා කෝට්ටේ ගියා වගේ (Parangi Kottegiya)", "ගාලු මුවදොර විලාසය", "පරාක්‍රම ක්‍රමය"), 1)
        5 -> Pentuple("Which local kingdom fought fiercely against Portuguese expansion, led by Mayadunne?", "පෘතුගීසි බලය ව්‍යාප්ත වීමට එරෙහිව මායාදුන්නේ රජු යටතේ දැඩිව සටන් කළ රාජධානිය කුමක්ද?", listOf("Sitawaka Kingdom", "Kandy Kingdom", "Jaffna Kingdom", "Gampola Kingdom"), listOf("සීතාවක රාජධානිය (Sitawaka)", "මහනුවර රාජධානිය", "යාපනය රාජධානිය", "ගම්පොළ රාජධානිය"), 0)
        6 -> Pentuple("Who was the legendary warrior king of Sitawaka who defeated Portuguese in the famous Battle of Mulleriyawa?", "මුල්ලේරියාව සටනින් පෘතුගීසීන් පරදා වීරත්වයට පත් වූ සීතාවක වීර රජතුමා කවුද?", listOf("King Rajasinghe I (Tikiri Bandara)", "King Mayadunne", "King Vijayabahu I", "King Wimaladharmasuriya I"), listOf("පළමුවන රාජසිංහ / ටිකිරි බණ්ඩාර (Rajasinghe I)", "මායාදුන්නේ රජු", "විජයබාහු රජු", "පළමුවන විමලධර්මසූරිය"), 0)
        7 -> Pentuple("What weapon technology did the Portuguese introduce that changed Sri Lankan warfare?", "යුද ශක්තිය වෙනස් කරමින් පෘතුගීසීන් මෙරටට හඳුන්වා දුන් ආයුධ මොනවාද?", listOf("Cannons and Firearm wall-pieces (Koduu)", "Iron swords", "Crossbows", "War elephants"), listOf("කාලතුවක්කු සහ තුවක්කු (Cannons / Firearms)", "යකඩ කඩු", "දුනු", "යුද අලි"), 0)
        8 -> Pentuple("In which historical year did the Dutch officially capture Colombo, ending Portuguese rule?", "ලන්දේසීන් කොළඹ නගරය අල්ලාගෙන පෘතුගීසි පාලනය අවසන් කළ වර්ෂය කුමක්ද?", listOf("1505", "1658", "1796", "1815"), listOf("1505", "1658 (Dutch capture of Colombo)", "1796", "1815"), 1)
        else -> Pentuple("The Dutch East India Company was famously known by which abbreviation?", "ලන්දේසි පෙරදිග ඉන්දියානු වෙළඳ සමාගම කෙටියෙන් හැඳින්වූයේ කුමන නමකින්ද?", listOf("VOC", "EIC", "SLPA", "LRC"), listOf("VOC (Vereenigde Oostindische Compagnie)", "EIC", "SLPA", "LRC"), 0)
    }

    private fun getGrade5Stage2(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("In which historical year did the British capture the coastal areas of Sri Lanka from the Dutch?", "ජාතික ඉතිහාසයේ බ්‍රිතාන්‍යයන් විසින් ලන්දේසීන්ගෙන් මුහුදුබඩ ප්‍රදේශ අල්ලාගත් වර්ෂය කුමක්ද?", listOf("1658", "1796", "1815", "1948"), listOf("1658", "1796 (British capture of coast)", "1815", "1948"), 1)
        1 -> Pentuple("Who was the first British governor of coastal areas of Ceylon?", "මුහුදුබඩ ප්‍රදේශවල පළමු බ්‍රිතාන්‍ය ආණ්ඩුකාරවරයා කවුද?", listOf("Sir Frederick North", "Sir Robert Brownrigg", "Thomas Maitland", "Edward Barnes"), listOf("ෆ්‍රෙඩ්රික් නෝත් (Sir Frederick North)", "රොබට් බ්‍රවුන්රිග්", "තෝමස් මේට්ලන්ඩ්", "එඩ්වඩ් බාන්ස්"), 0)
        2 -> Pentuple("In which historical year did the British capture the entire island by signing the Kandyan Convention?", "බ්‍රිතාන්‍යයන් මුළු දිවයිනම යටත් කර ගනිමින් උඩරට ගිවිසුම අත්සන් කළ වර්ෂය කුමක්ද?", listOf("1796", "1815", "1818", "1848"), listOf("1796", "1815 (Kandyan Convention Year)", "1818", "1848"), 1)
        3 -> Pentuple("Who was the British governor who signed the Kandyan Convention on behalf of the British crown?", "බ්‍රිතාන්‍ය කිරීටය වෙනුවෙන් උඩරට ගිවිසුමට අත්සන් තැබූ ආණ්ඩුකාරවරයා කවුද?", listOf("Sir Robert Brownrigg", "Sir Frederick North", "Thomas Maitland", "Edward Barnes"), listOf("රොබට් බ්‍රවුන්රිග් (Sir Robert Brownrigg)", "ෆ්‍රෙඩ්රික් නෝත්", "තෝමස් මේට්ලන්ඩ්", "එඩ්වඩ් බාන්ස්"), 0)
        4 -> Pentuple("Who was the heroic national leader of the 1818 Great Rebellion against British rule?", "1818 නිදහස් අරගලයට නායකත්වය දී වීරත්වය පතළ ප්‍රධාන දිසාවේ කවුද?", listOf("Keppetipola Disawe", "Veera Puran Appu", "Gongalegoda Banda", "Anagarika Dharmapala"), listOf("කැප්පෙටිපොල දිසාවේ (Keppetipola)", "වීර පුරන් අප්පු", "ගොංගාලේගොඩ බණ්ඩා", "අනගාරික ධර්මපාල"), 0)
        5 -> Pentuple("Where was Monarawila Keppetipola beheaded after being caught by British?", "බ්‍රිතාන්‍යයන්ට හසු වීමෙන් පසු වීර කැප්පෙටිපොල දිසාවේ හිස ගසා දැමූ ඓතිහාසික පරිශ්‍රය කුමක්ද?", listOf("Colombo Fort", "Katugastota", "Bogambara (Kandy)", "Trincomalee"), listOf("කොළඹ කොටුව", "කටුගස්තොට", "බෝගම්බර - මහනුවර (Bogambara)", "ත්‍රිකුණාමලය"), 2)
        6 -> Pentuple("Who was the prominent leader of the 1848 Matale Rebellion against British taxes?", "1848 මාතලේ කැරැල්ලට නායකත්වය දුන් වීර පුත්‍රයා කවුද?", listOf("Veera Puran Appu (and Gongalegoda Banda)", "Keppetipola", "Ehelepola", "Maduwanwela Disawe"), listOf("වීර පුරන් අප්පු (Veera Puran Appu)", "කැප්පෙටිපොල", "ඇහැලේපොල", "මඩුවන්වෙල දිසාවේ"), 0)
        7 -> Pentuple("Which governor built the Colombo-Kandy road system, laying foundations for central road network?", "කොළඹ - මහනුවර මහා මාර්ගය තැනවූයේ කුමන ක්‍රියාශීලී බ්‍රිතාන්‍ය ආණ්ඩුකාරවරයෙකු යටතේද?", listOf("Edward Barnes", "Robert Brownrigg", "Henry Ward", "Arthur Havelock"), listOf("එඩ්වඩ් බාන්ස් (Edward Barnes)", "රොබට් බ්‍රවුන්රිග්", "හෙන්රි වෝඩ්", "ආතර් හැව්ලොක්"), 0)
        8 -> Pentuple("Which crop did the British initially cultivate extensively, which was destroyed by coffee rust leaf disease?", "බ්‍රිතාන්‍යයන් ලංකාව තුළ මුලින්ම මහා පරිමාණයෙන් වගා කළ කෝපි මල බැඳීමේ රෝගයෙන් විනාශ වූ භෝගය කුමක්ද?", listOf("Tea", "Coffee", "Rubber", "Cinnamon"), listOf("තේ", "කෝපි (Coffee)", "රබර්", "කුරුඳු"), 1)
        else -> Pentuple("Which commercial crop was introduced by British to replace coffee, becoming Sri Lanka's leading export?", "කෝපි වගාව විනාශ වූ පසු ඒ වෙනුවට බ්‍රිතාන්‍යයන් මෙරට හඳුන්වා දුන් ප්‍රධාන භෝගය කුමක්ද?", listOf("Tea", "Rubber", "Coconut", "Cinchona"), listOf("තේ (Tea)", "රබර්", "පොල්", "සිංකෝනා"), 0)
    }

    private fun getGrade5Stage3(idx: Int): Pentuple = when (idx) {
        0 -> Pentuple("In which historical year did Sri Lanka (Ceylon) gain independence from British rule?", "ශ්‍රී ලංකාවට බ්‍රිතාන්‍ය පාලනයෙන් නිදහස ලැබුණු ඓතිහාසික වර්ෂය කුමක්ද?", listOf("1915", "1931", "1948", "1972"), listOf("1915", "1931", "1948 (Independence Year)", "1972"), 2)
        1 -> Pentuple("On which date was Sri Lanka's Independence gained?", "ශ්‍රී ලංකාවට නිදහස ලැබුණු පූජනීය දිනය කුමක්ද?", listOf("February 4th, 1948", "May 22nd", "January 8th", "December 18th"), listOf("පෙබරවාරි 4 වනදා (February 4th)", "මැයි 22 වනදා", "ජනවාරි 8 වනදා", "දෙසැම්බර් 18 වනදා"), 0)
        2 -> Pentuple("Who is honored as the 'Father of the Nation' and served as the first Prime Minister of independent Ceylon?", "නිදහස් ලංකාවේ ප්‍රථම අග්‍රාමාත්‍යවරයා සහ 'නිදහස් ජාතියේ පියා' ලෙස බුහුමන් ලබන නායකයා කවුද?", listOf("Don Stephen Senanayake (D. S. Senanayake)", "S. W. R. D. Bandaranaike", "Dudley Senanayake", "Sir John Kotelawala"), listOf("ඩී. එස්. සේනානායක (D. S. Senanayake)", "එස්. ඩබ්. ආර්. ඩී. බණ්ඩාරනායක", "ඩඩ්ලි සේනානායක", "සර් ජෝන් කොතලාවල"), 0)
        3 -> Pentuple("Who was the national leader who revived Buddhism and pioneered the Temperance (Amadyapa) movement in early 20th century?", "විසිවන සියවසේ මුල් භාගයේ බෞද්ධ සහ ජාතික පිබිදීමක් ඇති කිරීමට කැපවුණු වීරයා කවුද?", listOf("Anagarika Dharmapala (Don David)", "T. B. Jayah", "Ponnambalam Ramanathan", "F. R. Senanayake"), listOf("අනගාරික ධර්මපාල (Anagarika Dharmapala)", "ටී. බී. ජයා", "පොන්නම්බලම් රාමනාදන්", "එෆ්. ආර්. සේනානායක"), 0)
        4 -> Pentuple("Which Muslim national leader worked tirelessly for Ceylon's independence alongside D. S. Senanayake?", "නිදහස ලබා ගැනීමට ක්‍රියාශීලීව දායක වූ මුස්ලිම් ජාතික නායකයෙකු කවුද?", listOf("T. B. Jayah (and M. C. Siddi Lebbe)", "Badi-ud-din Mahmud", "A. C. S. Hameed", "M. H. Mohamed"), listOf("ටී. බී. ජයා (T. B. Jayah)", "බදියුදීන් මහමුද්", "ඒ. සී. එස්. හමීඩ්", "එම්. එච්. මොහොමඩ්"), 0)
        5 -> Pentuple("Which Tamil leader was a key architect of independence and was the first member of the Legislative Council?", "ලංකාවේ නිදහස් සටනට නායකත්වය දුන් ප්‍රමුඛ දෙමළ ජාතික නායකයා කවුද?", listOf("Sir Ponnambalam Ramanathan (and Ponnambalam Arunachalam)", "G. G. Ponnambalam", "S. J. V. Chelvanayakam", "A. Amirthalingam"), listOf("සර් පොන්නම්බලම් රාමනාදන් (Ramanathan)", "ජී. ජී. පොන්නම්බලම්", "එස්. ජේ. වී. චෙල්වනායගම්", "ඒ. අමිර්තලින්ගම්"), 0)
        6 -> Pentuple("What is the name of the first constitution of independent Ceylon in 1948?", "1948 දී නිදහස ලැබීමේදී ලංකාවට ලැබුණු මුල්ම ආණ්ඩුක්‍රම ව්‍යවස්ථාව කුමක්ද?", listOf("Soulbury Constitution", "Donoughmore Constitution", "Colebrooke Commission", "Republican Constitution"), listOf("සෝල්බරි ආණ්ඩුක්‍රම ව්‍යවස්ථාව (Soulbury)", "ඩොනමෝර් ආණ්ඩුක්‍රම ව්‍යවස්ථාව", "කෝල්බෲක් ප්‍රතිසංස්කරණ", "ජනරජ ව්‍යවස්ථාව"), 0)
        7 -> Pentuple("In which historical year did Ceylon change its name to 'Sri Lanka' and become a republic?", "ලංකාව 'ශ්‍රී ලංකා ජනරජය' බවට පත්වෙමින් බ්‍රිතාන්‍ය කිරීටයේ කටයුතුවලින් පූර්ණ නිදහස ලැබූ වර්ෂය?", listOf("1948", "1956", "1972", "1978"), listOf("1948", "1956", "1972 (Republican Year)", "1978"), 2)
        8 -> Pentuple("Who was the world's first female prime minister elected in Sri Lanka in 1960?", "1960 දී ශ්‍රී ලංකාවෙන් බිහිවූ ලොව ප්‍රථම අග්‍රාමාත්‍යවරිය (කාන්තා අගමැති) කවුද?", listOf("Sirimavo Bandaranaike", "Chandrika Kumaratunga", "Adeline Molamure", "Queen Lilavati"), listOf("සිරිමාවෝ බණ්ඩාරනායක (Sirimavo Bandaranaike)", "චන්ද්‍රිකා කුමාරතුංග", "ඇඩලින් මොලමුරේ", "ලීලාවතී රැජින"), 0)
        else -> Pentuple("What is the beautiful monument built in Kandy near Magul Maduwa to honor national heroes?", "දේශප්‍රේමීන් ස්මරණය කරමින් මහනුවර ඉදි කළ ස්මාරකය කුමක්ද?", listOf("Kandy War Memorial", "National Heroes Monument", "Lotus Tower", "Independent Pillar"), listOf("මහනුවර යුද ස්මාරකය", "ජාතික වීරයන්ගේ ස්මාරකය (Heroes Monument)", "නෙළුම් කුළුණ", "නිදහස් කණුව"), 1)
    }
}
