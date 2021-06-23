package com.adhitya.katalogfilm.utils

import com.adhitya.katalogfilm.data.source.local.entity.FilmEntity
import com.adhitya.katalogfilm.data.source.remote.response.*

object FilmsData {
    fun generateMoviesData(): ArrayList<FilmEntity> {
        val movies = ArrayList<FilmEntity>()

        movies.add(
            FilmEntity(
                "332562",
                "A Star Is Born",
                "Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally's career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.",
                "2018",
                "Drama, Romance, Music",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/wrFpXMNBRj2PBiN4Z5kix51XaIZ.jpg",
                "2h 16m",
                "https://www.themoviedb.org/movie/332562-a-star-is-born",
                "movies",
                false
            )
        )
        movies.add(
            FilmEntity(
                "399579",
                "Alita: Battle Angel",
                "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
                "2019",
                "Action, Science Fiction, Adventure",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/xRWht48C2V8XNfzvPehyClOvDni.jpg",
                "2h 2m",
                "https://www.themoviedb.org/movie/399579-alita-battle-angel",
                "movies",
                false
            )
        )
        movies.add(
            FilmEntity(
                "297802",
                "Aquaman",
                "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.",
                "2018",
                "Action, Adventure, Fantasy",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/xLPffWMhMj1l50ND3KchMjYoKmE.jpg",
                "2h 23m",
                "https://www.themoviedb.org/movie/297802-aquaman",
                "movies",
                false
            )
        )
        movies.add(
            FilmEntity(
                "424694",
                "Bohemian Rhapsody",
                "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock 'n' roll band Queen in 1970. Hit songs become instant classics. When Mercury's increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.",
                "2018",
                "Music, Drama, History",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/lHu1wtNaczFPGFDTrjCSzeLPTKN.jpg",
                "2h 15m",
                "https://www.themoviedb.org/movie/424694-bohemian-rhapsody",
                "movies",
                false
            )
        )
        movies.add(
            FilmEntity(
                "438650",
                "Cold Pursuit",
                "The quiet family life of Nels Coxman, a snowplow driver, is upended after his son's murder. Nels begins a vengeful hunt for Viking, the drug lord he holds responsible for the killing, eliminating Viking's associates one by one. As Nels draws closer to Viking, his actions bring even more unexpected and violent consequences, as he proves that revenge is all in the execution.",
                "2019",
                "Action, Crime, Thriller",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/hXgmWPd1SuujRZ4QnKLzrj79PAw.jpg",
                "1h 59m",
                "https://www.themoviedb.org/movie/438650-cold-pursuit",
                "movies",
                false
            )
        )
        movies.add(
            FilmEntity(
                "480530",
                "Creed II",
                "Between personal obligations and training for his next big fight against an opponent with ties to his family's past, Adonis Creed is up against the challenge of his life.",
                "2018",
                "Drama",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/v3QyboWRoA4O9RbcsqH8tJMe8EB.jpg",
                "2h 10m",
                "https://www.themoviedb.org/movie/480530-creed-ii",
                "movies",
                false
            )
        )
        movies.add(
            FilmEntity(
                "338952",
                "Fantastic Beasts: The Crimes of Grindelwald",
                "Gellert Grindelwald has escaped imprisonment and has begun gathering followers to his cause—elevating wizards above all non-magical beings. The only one capable of putting a stop to him is the wizard he once called his closest friend, Albus Dumbledore. However, Dumbledore will need to seek help from the wizard who had thwarted Grindelwald once before, his former student Newt Scamander, who agrees to help, unaware of the dangers that lie ahead. Lines are drawn as love and loyalty are tested, even among the truest friends and family, in an increasingly divided wizarding world.",
                "2018",
                "Adventure, Fantasy, Drama",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/fMMrl8fD9gRCFJvsx0SuFwkEOop.jpg",
                "2h 14m",
                "https://www.themoviedb.org/movie/338952-fantastic-beasts-the-crimes-of-grindelwald",
                "movies",
                false
            )
        )
        movies.add(
            FilmEntity(
                "450465",
                "Glass",
                "In a series of escalating encounters, former security guard David Dunn uses his supernatural abilities to track Kevin Wendell Crumb, a disturbed man who has twenty-four personalities. Meanwhile, the shadowy presence of Elijah Price emerges as an orchestrator who holds secrets critical to both men.",
                "2019",
                "Adventure, Fantasy, Drama",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/svIDTNUoajS8dLEo7EosxvyAsgJ.jpg",
                "2h 14m",
                "https://www.themoviedb.org/movie/450465-glass",
                "movies",
                false
            )
        )
        movies.add(
            FilmEntity(
                "166428",
                "How to Train Your Dragon: The Hidden World",
                "As Hiccup fulfills his dream of creating a peaceful dragon utopia, Toothless’ discovery of an untamed, elusive mate draws the Night Fury away. When danger mounts at home and Hiccup’s reign as village chief is tested, both dragon and rider must make impossible decisions to save their kind.",
                "2019",
                "Animation, Family, Adventure",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/xvx4Yhf0DVH8G4LzNISpMfFBDy2.jpg",
                "1h 44m",
                "https://www.themoviedb.org/movie/166428-how-to-train-your-dragon-3",
                "movies",
                false
            )
        )
        movies.add(
            FilmEntity(
                "299536",
                "Avengers: Infinity War",
                "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.",
                "2018",
                "Adventure, Action, Science Fiction",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/7WsyChQLEftFiDOVTGkv3hFpyyt.jpg",
                "2h 29m",
                "https://www.themoviedb.org/movie/299536-avengers-infinity-war",
                "movies",
                false
            )
        )
        movies.add(
            FilmEntity(
                "457136",
                "Mary Queen of Scots",
                "In 1561, Mary Stuart, widow of the King of France, returns to Scotland, reclaims her rightful throne and menaces the future of Queen Elizabeth I as ruler of England, because she has a legitimate claim to the English throne. Betrayals, rebellions, conspiracies and their own life choices imperil both Queens. They experience the bitter cost of power, until their tragic fate is finally fulfilled.",
                "2018",
                "Drama, History",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/b5RMzLAyq5QW6GtN9sIeAEMLlBI.jpg",
                "2h 4m",
                "https://www.themoviedb.org/movie/457136-mary-queen-of-scots",
                "movies",
                false
            )
        )
        movies.add(
            FilmEntity(
                "450001",
                "Master Z: Ip Man Legacy",
                "Following his defeat by Master Ip, Cheung Tin Chi tries to make a life with his young son in Hong Kong, waiting tables at a bar that caters to expats. But it's not long before the mix of foreigners, money, and triad leaders draw him once again to the fight.",
                "2018",
                "Action",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/6VxEvOF7QDovsG6ro9OVyjH07LF.jpg",
                "1h 47m",
                "https://www.themoviedb.org/movie/450001-cheung-tin-chi",
                "movies",
                false
            )
        )
        movies.add(
            FilmEntity(
                "428078",
                "Mortal Engines",
                "Many thousands of years in the future, Earth’s cities roam the globe on huge wheels, devouring each other in a struggle for ever diminishing resources. On one of these massive traction cities, the old London, Tom Natsworthy has an unexpected encounter with a mysterious young woman from the wastelands who will change the course of his life forever.",
                "2018",
                "Adventure, Science Fiction",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/gLhYg9NIvIPKVRTtvzCWnp1qJWG.jpg",
                "2h 9m",
                "https://www.themoviedb.org/movie/428078-mortal-engines",
                "movies",
                false
            )
        )
        movies.add(
            FilmEntity(
                "438799",
                "Overlord",
                "France, June 1944. On the eve of D-Day, some American paratroopers fall behind enemy lines after their aircraft crashes while on a mission to destroy a radio tower in a small village near the beaches of Normandy. After reaching their target, the surviving paratroopers realise that, in addition to fighting the Nazi troops that patrol the village, they also must fight against something else.",
                "2018",
                "Horror, War, Science Fiction",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/l76Rgp32z2UxjULApxGXAPpYdAP.jpg",
                "1h 50m",
                "https://www.themoviedb.org/movie/438799-overlord",
                "movies",
                false
            )
        )
        movies.add(
            FilmEntity(
                "404368",
                "Ralph Breaks the Internet",
                "Video game bad guy Ralph and fellow misfit Vanellope von Schweetz must risk it all by traveling to the World Wide Web in search of a replacement part to save Vanellope's video game, Sugar Rush. In way over their heads, Ralph and Vanellope rely on the citizens of the internet — the netizens — to help navigate their way, including an entrepreneur named Yesss, who is the head algorithm and the heart and soul of trend-making site BuzzzTube.",
                "2018",
                "Family, Animation, Comedy, Adventure",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/qEnH5meR381iMpmCumAIMswcQw2.jpg",
                "1h 52m",
                "https://www.themoviedb.org/movie/404368-ralph-breaks-the-internet",
                "movies",
                false
            )
        )
        movies.add(
            FilmEntity(
                "375588",
                "Robin Hood",
                "A war-hardened Crusader and his Moorish commander mount an audacious revolt against the corrupt English crown.",
                "2018",
                "Adventure, Action, Thriller",
                "https://www.themoviedb.org/movie/375588-robin-hood-origins",
                "1h 56m",
                "https://www.themoviedb.org/movie/375588-robin-hood-origins",
                "movies",
                false
            )
        )
        movies.add(
            FilmEntity(
                "452832",
                "Serenity",
                "The quiet life of Baker Dill, a fishing boat captain who lives on the isolated Plymouth Island, where he spends his days obsessed with capturing an elusive tuna while fighting his personal demons, is interrupted when someone from his past comes to him searching for help.",
                "2019",
                "Thriller, Mystery, Drama",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/hgWAcic93phg4DOuQ8NrsgQWiqu.jpg",
                "1h 42m",
                "https://www.themoviedb.org/movie/452832-serenity",
                "movies",
                false
            )
        )
        movies.add(
            FilmEntity(
                "324857",
                "Spider-Man: Into the Spider-Verse",
                "Miles Morales is juggling his life between being a high school student and being a spider-man. When Wilson \"Kingpin\" Fisk uses a super collider, others from across the Spider-Verse are transported to this dimension.",
                "2018",
                "Action, Adventure, Animation, Science Fiction, Comedy",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/iiZZdoQBEYBv6id8su7ImL0oCbD.jpg",
                "1h 57m",
                "https://www.themoviedb.org/movie/324857-spider-man-into-the-spider-verse",
                "movies",
                false
            )
        )
        movies.add(
            FilmEntity(
                "505954",
                "T-34",
                "In 1944, a courageous group of Russian soldiers managed to escape from German captivity in a half-destroyed legendary T-34 tank. Those were the times of unforgettable bravery, fierce fighting, unbreakable love, and legendary miracles.",
                "2019",
                "War, Action, Drama, History",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/jqBIHiSglRfNxjh1zodHLa9E7zW.jpg",
                "2h 19m",
                "https://www.themoviedb.org/movie/505954-34",
                "movies",
                false
            )
        )

        return movies
    }

    fun generateTVShowsData(): ArrayList<FilmEntity> {
        val tv_shows = ArrayList<FilmEntity>()

        tv_shows.add(
            FilmEntity(
                "1412",
                "Arrow",
                "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                "2012",
                "Crime, Drama, Mystery, Action & Adventure",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/gKG5QGz5Ngf8fgWpBsWtlg5L2SF.jpg",
                "42 menit",
                "https://www.themoviedb.org/tv/1412-arrow",
                "tv_shows",
                false
            )
        )
        tv_shows.add(
            FilmEntity(
                "79501",
                "Doom Patrol",
                "The Doom Patrol’s members each suffered horrible accidents that gave them superhuman abilities — but also left them scarred and disfigured. Traumatized and downtrodden, the team found purpose through The Chief, who brought them together to investigate the weirdest phenomena in existence — and to protect Earth from what they find.",
                "2019",
                "Sci-Fi & Fantasy, Comedy, Drama",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/drlfSCIlMKrEeMPhi8pqY4xGxj.jpg",
                "49 menit",
                "https://www.themoviedb.org/tv/79501-doom-patrol",
                "tv_shows",
                false
            )
        )
        tv_shows.add(
            FilmEntity(
                "12609",
                "Dragon Ball",
                "Long ago in the mountains, a fighting master known as Gohan discovered a strange boy whom he named Goku. Gohan raised him and trained Goku in martial arts until he died. The young and very strong boy was on his own, but easily managed. Then one day, Goku met a teenage girl named Bulma, whose search for the mystical Dragon Balls brought her to Goku's home. Together, they set off to find all seven and to grant her wish.",
                "1986",
                "Animation, Action & Adventure, Sci-Fi & Fantasy",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/tZ0jXOeYBWPZ0OWzUhTlYvMF7YR.jpg",
                "25 menit",
                "https://www.themoviedb.org/tv/12609-dragon-ball",
                "tv_shows",
                false
            )
        )
        tv_shows.add(
            FilmEntity(
                "46261",
                "Fairy Tail",
                "Lucy is a 17-year-old girl, who wants to be a full-fledged mage. One day when visiting Harujion Town, she meets Natsu, a young man who gets sick easily by any type of transportation. But Natsu isn't just any ordinary kid, he's a member of one of the world's most infamous mage guilds: Fairy Tail.",
                "2009",
                "Action & Adventure, Animation, Comedy, Sci-Fi & Fantasy, Mystery",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/jsYTctFnK8ewomnUgcwhmsTkOum.jpg",
                "25 menit",
                "https://www.themoviedb.org/tv/46261-fairy-tail",
                "tv_shows",
                false
            )
        )
        tv_shows.add(
            FilmEntity(
                "1434",
                "Family Guy",
                "Sick, twisted, politically incorrect and Freakin' Sweet animated series featuring the adventures of the dysfunctional Griffin family. Bumbling Peter and long-suffering Lois have three kids. Stewie (a brilliant but sadistic baby bent on killing his mother and taking over the world), Meg (the oldest, and is the most unpopular girl in town) and Chris (the middle kid, he's not very bright but has a passion for movies). The final member of the family is Brian - a talking dog and much more than a pet, he keeps Stewie in check whilst sipping Martinis and sorting through his own life issues.",
                "1999",
                "Animation, Comedy",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/eWWCRjBfLyePh2tfZdvNcIvKSJe.jpg",
                "22 menit",
                "https://www.themoviedb.org/tv/1434-family-guy",
                "tv_shows",
                false
            )
        )
        tv_shows.add(
            FilmEntity(
                "60735",
                "The Flash",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                "2014",
                "Drama, Sci-Fi & Fantasy",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
                "44 menit",
                "https://www.themoviedb.org/tv/60735-the-flash",
                "tv_shows",
                false
            )
        )
        tv_shows.add(
            FilmEntity(
                "1399",
                "Game of Thrones",
                "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.",
                "2011",
                "Sci-Fi & Fantasy, Drama, Action & Adventure",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/u3bZgnGQ9T01sWNhyveQz0wH0Hl.jpg",
                "1 jam",
                "https://www.themoviedb.org/tv/1399-game-of-thrones",
                "tv_shows",
                false
            )
        )
        tv_shows.add(
            FilmEntity(
                "60708",
                "Gotham",
                "Everyone knows the name Commissioner Gordon. He is one of the crime world's greatest foes, a man whose reputation is synonymous with law and order. But what is known of Gordon's story and his rise from rookie detective to Police Commissioner? What did it take to navigate the multiple layers of corruption that secretly ruled Gotham City, the spawning ground of the world's most iconic villains? And what circumstances created them – the larger-than-life personas who would become Catwoman, The Penguin, The Riddler, Two-Face and The Joker?",
                "2014",
                "Drama, Crime, Sci-Fi & Fantasy",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/4XddcRDtnNjYmLRMYpbrhFxsbuq.jpg",
                "43 menit",
                "https://www.themoviedb.org/tv/60708-gotham",
                "tv_shows",
                false
            )
        )
        tv_shows.add(
            FilmEntity(
                "1416",
                "Grey's Anatomy",
                "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.",
                "2005",
                "Drama",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/clnyhPqj1SNgpAdeSS6a6fwE6Bo.jpg",
                "43 menit",
                "https://www.themoviedb.org/tv/1416-grey-s-anatomy",
                "tv_shows",
                false
            )
        )
        tv_shows.add(
            FilmEntity(
                "54155",
                "Hanna",
                "This thriller and coming-of-age drama follows the journey of an extraordinary young girl as she evades the relentless pursuit of an off-book CIA agent and tries to unearth the truth behind who she is. Based on the 2011 Joe Wright film.",
                "2019",
                "Action & Adventure, Drama",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/iYUtjx1EN4SVTgxd2TB4cZTGSQb.jpg",
                "50 menit",
                "https://www.themoviedb.org/tv/54155-hanna",
                "tv_shows",
                false
            )
        )
        tv_shows.add(
            FilmEntity(
                "62127",
                "Marvel's Iron Fist",
                "Danny Rand resurfaces 15 years after being presumed dead. Now, with the power of the Iron Fist, he seeks to reclaim his past and fulfill his destiny.",
                "2017",
                "Action & Adventure, Drama, Sci-Fi & Fantasy",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/4l6KD9HhtD6nCDEfg10Lp6C6zah.jpg",
                "55 menit",
                "https://www.themoviedb.org/tv/62127-marvel-s-iron-fist",
                "tv_shows",
                false
            )
        )
        tv_shows.add(
            FilmEntity(
                "31910",
                "Naruto Shippūden",
                "Naruto Shippuuden is the continuation of the original animated TV series Naruto.The story revolves around an older and slightly more matured Uzumaki Naruto and his quest to save his friend Uchiha Sasuke from the grips of the snake-like Shinobi, Orochimaru. After 2 and a half years Naruto finally returns to his village of Konoha, and sets about putting his ambitions to work, though it will not be easy, as He has amassed a few (more dangerous) enemies, in the likes of the shinobi organization; Akatsuki.",
                "2007",
                "Animation, Action & Adventure, Sci-Fi & Fantasy",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/zAYRe2bJxpWTVrwwmBc00VFkAf4.jpg",
                "25 menit",
                "https://www.themoviedb.org/tv/31910-naruto-shipp-den",
                "tv_shows",
                false
            )
        )
        tv_shows.add(
            FilmEntity(
                "4614",
                "NCIS",
                "From murder and espionage to terrorism and stolen submarines, a team of special agents investigates any crime that has a shred of evidence connected to Navy and Marine Corps personnel, regardless of rank or position.",
                "2003",
                "Crime, Action & Adventure, Drama",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/fi8EvaWtL5CvoielOjjVvTr7ux3.jpg",
                "45 menit",
                "https://www.themoviedb.org/tv/4614-ncis",
                "tv_shows",
                false
            )
        )
        tv_shows.add(
            FilmEntity(
                "69050",
                "Riverdale",
                "Set in the present, the series offers a bold, subversive take on Archie, Betty, Veronica and their friends, exploring the surreality of small-town life, the darkness and weirdness bubbling beneath Riverdale’s wholesome facade.",
                "2017",
                "Mystery, Drama, Crime",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/wRbjVBdDo5qHAEOVYoMWpM58FSA.jpg",
                "45 menit",
                "https://www.themoviedb.org/tv/69050-riverdale",
                "tv_shows",
                false
            )
        )
        tv_shows.add(
            FilmEntity(
                "34307",
                "Shameless",
                "Chicagoan Frank Gallagher is the proud single dad of six smart, industrious, independent kids, who without him would be... perhaps better off. When Frank's not at the bar spending what little money they have, he's passed out on the floor. But the kids have found ways to grow up in spite of him. They may not be like any family you know, but they make no apologies for being exactly who they are.",
                "2011",
                "Drama, Comedy",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/9akij7PqZ1g6zl42DQQTtL9CTSb.jpg",
                "57 menit",
                "https://www.themoviedb.org/tv/34307-shameless",
                "tv_shows",
                false
            )
        )
        tv_shows.add(
            FilmEntity(
                "62688",
                "Supergirl",
                "Twenty-four-year-old Kara Zor-El, who was taken in by the Danvers family when she was 13 after being sent away from Krypton, must learn to embrace her powers after previously hiding them. The Danvers teach her to be careful with her powers, until she has to reveal them during an unexpected disaster, setting her on her journey of heroism.",
                "2015",
                "Drama, Sci-Fi & Fantasy, Action & Adventure",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/zsaiq8ZclPuneuN7loLEbsh1ANJ.jpg",
                "42 menit",
                "https://www.themoviedb.org/tv/62688-supergirl",
                "tv_shows",
                false
            )
        )
        tv_shows.add(
            FilmEntity(
                "1622",
                "Supernatural",
                "When they were boys, Sam and Dean Winchester lost their mother to a mysterious and demonic supernatural force. Subsequently, their father raised them to be soldiers. He taught them about the paranormal evil that lives in the dark corners and on the back roads of America ... and he taught them how to kill it. Now, the Winchester brothers crisscross the country in their '67 Chevy Impala, battling every kind of supernatural threat they encounter along the way.",
                "2005",
                "Drama, Mystery, Sci-Fi & Fantasy",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/KoYWXbnYuS3b0GyQPkbuexlVK9.jpg",
                "45 menit",
                "https://www.themoviedb.org/tv/1622-supernatural",
                "tv_shows",
                false
            )
        )
        tv_shows.add(
            FilmEntity(
                "456",
                "The Simpsons",
                "Set in Springfield, the average American town, the show focuses on the antics and everyday adventures of the Simpson family; Homer, Marge, Bart, Lisa and Maggie, as well as a virtual cast of thousands. Since the beginning, the series has been a pop culture icon, attracting hundreds of celebrities to guest star. The show has also made name for itself in its fearless satirical take on politics, media and American life in general.",
                "1989",
                "Family, Animation, Comedy",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/zLudbPueg8CxGhMS2tyDh3p0TdK.jpg",
                "22 menit",
                "https://www.themoviedb.org/tv/456-the-simpsons",
                "tv_shows",
                false
            )
        )
        tv_shows.add(
            FilmEntity(
                "75006",
                "The Umbrella Academy",
                "A dysfunctional family of superheroes comes together to solve the mystery of their father's death, the threat of the apocalypse and more.",
                "2019",
                "Action & Adventure, Sci-Fi & Fantasy, Drama",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/scZlQQYnDVlnpxFTxaIv2g0BWnL.jpg",
                "55 menit",
                "https://www.themoviedb.org/tv/75006-umbrella-academy",
                "tv_shows",
                false
            )
        )
        tv_shows.add(
            FilmEntity(
                "1402",
                "The Walking Dead",
                "Sheriff's deputy Rick Grimes awakens from a coma to find a post-apocalyptic world dominated by flesh-eating zombies. He sets out to find his family and encounters many other survivors along the way.",
                "2018",
                "Action & Adventure, Drama, Sci-Fi & Fantasy",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/rqeYMLryjcawh2JeRpCVUDXYM5b.jpg",
                "42 menit",
                "https://www.themoviedb.org/tv/1402-the-walking-dead",
                "tv_shows",
                false
            )
        )

        return tv_shows
    }

    fun generateRemoteMoviesDetailsData(): MoviesDetailsResponse {
        val listGenresItems = listOf(
            MoviesGenresItem("Normal", 1),
            MoviesGenresItem("Null", 2)
        )
        return MoviesDetailsResponse(
            "457136".toInt(),
            "123",
            "Mary Queen of Scots",
            listGenresItems,
            100,
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/b5RMzLAyq5QW6GtN9sIeAEMLlBI.jpg",
            "2018"
        )
    }

}