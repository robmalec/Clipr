package com.clipr.clipr;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by malec_000 on 11/26/2017.
 */

public class nameLibrary {
    /*
    This is a HashMap of names for which people might search where the key is the most formal possible version of the names which
    connect to an ArrayList of possible alternate names.  When stylist searches are run, the app will search first for the exact name
    entered, then search for all possible alternate names based on the formal root name under which the first name entered in the search
    falls, for example if a person searches for "Rob Malecki" it'll automatically search for "Robert Malecki", "Bob Malecki", "Robby Malecki",
    and so on.

    To do this, copy and paste this template for each root name with names starting with A through M between the curly braces {} after
    "private void initNamesAthruM()" and the same for "private void initNamesNthruZ()":

    //(Root name here)
    addToNameLibrary(new String[]{
    "Root name",
    "Name1",
    "Name2"
    });

    Names NEED to be between quotes otherwise the code won't work


    This NEEDS to include names of all ethnicities and races, but we can start with the 100 most common male and female names in the country
    http://www.studentsoftheworld.info/penpals/stats.php3?Pays=USA

    (By the way, if you type two forward slashes // you can type anything after it and the computer will ignore it, this is how we leave notes in code

    IMPORTANT: Regardless of alphabetical order, the first line under the text "tempNames =" NEEDS to contain the base name to which each
    shortened name is related.
     */
    private HashMap<String,ArrayList<String>> firstNames;
    private ArrayList<String> allRootNames;
    private ArrayList<String> commonLastNames;

    public nameLibrary()
    {
        firstNames = new HashMap<String,ArrayList<String>>();
        allRootNames = new ArrayList<String >();
        commonLastNames = new ArrayList<>(Arrays.asList("Smith","Jones","Malecki","Montes De Oca","Olsberg",
                "Sanders","Obama","Schlissel","Johnson","Martin","Sparks","Marr","Cannoy","Thompson"));

        initNamesFelipe();
        initNamesOshri();
    }
    private void initNamesFelipe()
    {
        //Andrew
        addToNameLibrary(new String[]{
                "Andrew",
                "Andy",
                "Drew"
        });
        //Andrea
        addToNameLibrary(new String[]{
                "Andrea",
                "Drea"
        });

    //Kaitlyn
    addToNameLibrary(new String[]{
        "Kaitlyn",
                "Kate",
                "Katie",
                "Katherin",
                "Katherine"
    });

    //Jenna
    addToNameLibrary(new String[]{
        "Jenna",
                "Jenny",
                "Jen",
                "Jennifer"
    });

    //Sierra
    addToNameLibrary(new String[]{
        "Sierra",
                "Siri"
    });

    //Hailey
    addToNameLibrary(new String[]{
        "Hailey",
                "Haley"
    });

    //Maria
    addToNameLibrary(new String[]{
        "Maria",
                "Mary"
    });

    //Andrea
    addToNameLibrary(new String[]{
        "Andrea",
                "Andy",
                "Drea"
    });

    //Destiny
    addToNameLibrary(new String[]{
        "Destiny",
                "Destinee"
    });

    //Kate
    addToNameLibrary(new String[]{
        "Kate",
                "Katie",
                "Kaitlyn",
                "Kat",
                "Katherine",
                "Katherin",
                "Catherine",
                "Catherin"
    });

    //Autumn
    addToNameLibrary(new String[]{
        "Autumn",
                "Fall"
    });

    //Allison
    addToNameLibrary(new String[]{
        "Allison",
                "Allyson",
                "Ally",
                "Allie",
                "Ale"
    });

    //Zoe
    addToNameLibrary(new String[]{
        "Zoe",
                "Zoey",
                "Zo"
    });

    //Amy
    addToNameLibrary(new String[]{
        "Amy",
                "Amee",
                "Amie"
    });

    //Lexi
    addToNameLibrary(new String[]{
        "Lexi",
                "Lex",
                "Alexis"
    });

    //Sophia
    addToNameLibrary(new String[]{
        "Sophia",
                "Sophie",
                "Sofia",
                "Sofie",
                "Sofi"
    });

    //Claire
    addToNameLibrary(new String[]{
        "Claire",
                "Clare",
                "Clara"
    });

    //Marissa
    addToNameLibrary(new String[]{
        "Marissa",
                "Mari",
                "Marisa",
                "Miss",
                "Missy",
                "Momo",
                "Rissa",
                "Missa"
    });

    //Abigail
    addToNameLibrary(new String[]{
        "Abigail",
                "Abby",
                "Abi",
                "Abbie",
                "Gail"
    });

    //Isabella
    addToNameLibrary(new String[]{
        "Isabella",
                "Isabel",
                "Isa",
                "Bella",
                "Bell"
    });

    //Molly
    addToNameLibrary(new String[]{
        "Molly",
                "Mollie",
                "Mary",
                "Margaret"
    });

    //Kelsey
    addToNameLibrary(new String[]{
        "Kelsey",
                "Kelsie",
                "Kel",
                "Kelly",
                "Kels",
                "Kelle"
    });

    //Kelly
    addToNameLibrary(new String[]{
        "Kelly",
                "Kellie",
                "Kel",
                "Kell",
                "Kelbo",
                "Kel-Kel"
    });

    //Kaylee
    addToNameLibrary(new String[]{
        "Kaylee",
                "Kaylie",
                "Kay",
                "Kaykay",
                "Kailee",
                "Kaileigh"
    });

    //Laua
    addToNameLibrary(new String[]{
        "Laura",
                "Lauren",
                "Lari",
                "Laurita",
                "Lollie",
                "Lolly",
                "Laureen",
                "Laurel",
                "Laurella"
    });

    //Alex
    addToNameLibrary(new String[]{
        "Alex",
                "Alexandra",
                "Lex",
                "Lexi",
                "Alejandra",
                "Ale"
    });

    //Mackenzie
    addToNameLibrary(new String[]{
        "Mackenzie",
        "Mac",
                "Kenzie",
                "Mack",
                "Kenzo"
    });

    //Melissa
    addToNameLibrary(new String[]{
        "Melissa",
                "Liss",
                "Lissa",
                "Lissie",
                "Mel",
                "Mellie",
                "Melly",
                "Missie",
                "Missy"
    });

    //Tiffany
    addToNameLibrary(new String[]{
        "Tiffany",
                "Tiff",
                "Tiffi",
                "Tiffie",
                "Tiffy"
    });

    //Christina
    addToNameLibrary(new String[]{
        "Christina",
                "Cristina",
                "Kristina",
                "Tina",
                "Crissy",
                "Cristy"
    });

    //Leah
    addToNameLibrary(new String[]{
        "Leah",
                "Lea",
                "Leia",
                "Lee",
                "Leigh",
                "Lia"
    });

    //Caroline
    addToNameLibrary(new String[]{
        "Caroline",
                "Carol",
                "Caro",
                "Carrie",
                "Cara"
    });

    //(Root name here)
    addToNameLibrary(new String[]{
        "Root name",
                "Name1",
                "Name2"
    });

    //Maggie
    addToNameLibrary(new String[]{
        "Maggie",
                "Mags",
                "Margaret",
                "Greta",
                "Gretal",
                "Gretel",
                "Grethel",
                "Gretta",
                "Grette"
    });

    //Alexa
    addToNameLibrary(new String[]{
        "Alexa",
                "Alex",
                "Alexandra",
                "Alejandra",
                "Ale"
    });

    //Bailey
    addToNameLibrary(new String[]{
        "Bailey",
                "Baylee",
                "Bailie",
                "Bayley",
                "Baylie"
    });

    //Heather
    addToNameLibrary(new String[]{
        "Heather"
    });

    //Caitlin
    addToNameLibrary(new String[]{
        "Caitlin",
                "Kaitlyn",
                "Katie",
                "Cat",
                "Catherine",
                "Catherin"
    });

    //Makayla
    addToNameLibrary(new String[]{
        "Makayla",
                "Kayla",
                "Mac",
                "Mack",
                "Mickey",
                "Micky"
    });

    //Cheyenne
    addToNameLibrary(new String[]{
        "Cheyenne",
                "Cheyene",
                "Shyanne",
                "Shianne",
                "Sheyenne",
                "Cheyann",
                "Cheyne",
                "Shyanne",
                "Cheyene"
    });

    //Miranda
    addToNameLibrary(new String[]{
        "Miranda",
                "Mandy",
                "Andy",
                "Mira",
                "Myra",
                "Randy",
                "Randa"
    });

    //Erica
    addToNameLibrary(new String[]{
        "Erica",
                "Eric",
                "Erika",
                "Rickee",
                "Ricki",
                "Rickie",
                "Ricky",
                "Rikki",
                "Rikke",
                "Rikky"
    });

    //Vanessa
    addToNameLibrary(new String[]{
        "Vanessa",
                "Vanesa",
                "Venus",
                "Nessi",
                "Vanny",
                "Vans",
                "V"
    });

    //Mia
    addToNameLibrary(new String[]{
        "Mia",
                "Maria",
                "Mea",
                "Meya",
                "Miya"
    });

    //Madeline
    addToNameLibrary(new String[]{
        "Madeline",
                "Maddy",
                "Maddie",
                "Madelin",
                "Madelyn",
                "Magdelena",
                "Magdalene"
    });

    //Faith
    addToNameLibrary(new String[]{
        "Faith",
                "Faithe",
                "Fayth",
                "Faythe"
    });

    //Audrey
    addToNameLibrary(new String[]{
        "Audrey",
                "Drew",
                "Audree",
                "Audrie"
    });

    //Gabby
    addToNameLibrary(new String[]{
        "Gabby",
                "Gaby",
                "Gabi",
                "Gabbie",
                "Gabbi",
                "Gabriella",
                "Gabrielle"
    });

    //Breanna
    addToNameLibrary(new String[]{
        "Breanna",
                "Briana",
                "Brianna",
                "Breanne",
                "Bree",
                "Bri",
                "Bria",
                "Brina"
    });

    //Jade
    addToNameLibrary(new String[]{
        "Jade",
                "Jadie",
                "Jady",
                "Jaydee",
                "Jayda",
                "Jay-Jay"
    });

    //Evan
    addToNameLibrary(new String[]{
        "Evan",
                "Euan",
                "Euen",
                "Evann",
                "Evans",
                "Ev",
                "Evin"
    });

    //Cody
    addToNameLibrary(new String[]{
        "Cody",
                "Codie",
                "Codey"
    });

    //Zachary
    addToNameLibrary(new String[]{
        "Zachary",
                "Zach",
                "Zac",
                "Zeke",
                "Zack",
                "Zacky"
    });

    //Chase
    addToNameLibrary(new String[]{
        "Chase",
                "Chayse",
                "Chace",
                "Chayce"
    });

    //Jose
    addToNameLibrary(new String[]{
        "Jose",
                "Joseph",
                "Pepe",
                "Joe"
    });

    //Steven
    addToNameLibrary(new String[]{
        "Steven",
                "Stephen",
                "Steve",
                "Steph",
                "Steve",
                "Stevie",
                "Stevey",
                "Esteban",
                "Estevan"
    });

    //Max
    addToNameLibrary(new String[]{
        "Max",
                "Maximo",
                "Maximilian",
                "Maximus",
                "Maxine",
                "Maximillian",
                "Maxwell"
    });

    //Ian
    addToNameLibrary(new String[]{
        "Ian",
                "Ean",
                "Eann"
    });

    //Connor
    addToNameLibrary(new String[]{
        "Connor",
                "Conor",
                "Connie",
                "Conner",
                "Coner"
    });

    //Mark
    addToNameLibrary(new String[]{
        "Mark",
                "Marc",
                "Marco",
                "Marcel",
                "Marques"
    });

    //Cole
    addToNameLibrary(new String[]{
        "Cole"
    });

    //Patrick
    addToNameLibrary(new String[]{
        "Patrick",
                "Rick",
                "Patric",
                "Patrik",
                "Patty",
                "Pat",
                "Pattie"
    });

    //Sean
    addToNameLibrary(new String[]{
        "Sean",
                "Shane",
                "Shaughn",
                "Shuan",
                "Shawn"
    });

    //Samuel
    addToNameLibrary(new String[]{
        "Samuel",
                "Sam",
                "Sammy",
                "Sammie",
                "Sami"
    });

    //Gabriel
    addToNameLibrary(new String[]{
        "Gabriel",
                "Gabe",
                "Gaby",
                "Gabi"
    });

    //Devin
    addToNameLibrary(new String[]{
        "Devin",
                "Dev",
                "Devon",
                "Devyn",
                "Devinn"
    });

    //Will
    addToNameLibrary(new String[]{
        "Will",
                "William",
                "Bill",
                "Santiago",
                "Santi"
    });

    //Jeremy
    addToNameLibrary(new String[]{
        "Jeremy",
                "Jer",
                "Jerry",
                "Jem",
                "Jez"
    });

    //Isaac
    addToNameLibrary(new String[]{
        "Isaac",
                "Ike",
                "Zack",
                "Zak",
                "Zeke",
                "Isaak",
                "Isac",
                "Isak"
    });

    //Jesse
    addToNameLibrary(new String[]{
        "Jesse",
                "Jessey",
                "Jesiah",
                "Jessie",
                "Jessy"
    });

    //Mason
    addToNameLibrary(new String[]{
        "Mason",
                "Mase",
                "Macen",
        "Maycen"
    });

    //Joey
    addToNameLibrary(new String[]{
        "Joey",
                "Joseph",
                "Jose",
                "Pepe",
                "Joe"
    });

    //Carlos
    addToNameLibrary(new String[]{
        "Carlos",
                "Charles",
                "Charlie",
                "Carlo",
                "Charley",
                "Carlitos"
    });

    //Bryan
    addToNameLibrary(new String[]{
        "Bryan",
                "Brian",
                "Brien"
    });

    //Jared
    addToNameLibrary(new String[]{
        "Jared",
                "Jarred",
                "Jarod",
                "Jarrod",
                "Jerry"
    });

    //Tanner
    addToNameLibrary(new String[]{
        "Tanner",
                "Tan",
                "Tannon",
                "Tanier",
                "Tannie",
                "Tanney"
    });

    //Peter
    addToNameLibrary(new String[]{
        "Peter",
                "Pete",
                "Pedro",
                "Petey",
                "Petr",
                "Petros",
                "Petrov"
    });

    //Tristan
    addToNameLibrary(new String[]{
        "Tristan",
                "Stan",
                "Tristen"
    });

    //Gavin
    addToNameLibrary(new String[]{
        "Gavin",
                "Gavinn",
                "Gav",
                "Gavyn",
                "Gaven",
                "Gavan",
                "Gavino",
    });

    //Shane
    addToNameLibrary(new String[]{
        "Shane",
                "Sean",
                "Shayne",
                "Shaine",
                "Shayn"
    });

    //Seth
    addToNameLibrary(new String[]{
        "Seth"
    });

    //Paul
    addToNameLibrary(new String[]{
        "Paul",
                "Paulie",
                "Paulo",
                "Pablo",
                "Pauly",
                "Paolo"
    });

    //Trevor
    addToNameLibrary(new String[]{
        "Trevor",
                "Trev"
    });

    //Elijah
    addToNameLibrary(new String[]{
        "Elijah",
                "Eli",
                "EJ",
                "Lij",
                "Elija"
    });

    //Brendan
    addToNameLibrary(new String[]{
        "Brendan",
                "Brandon",
                "Bennan",
                "Brendin",
                "Bren",
                "Brennon",
                "Brendon",
                "Breandon"
    });

    //Isaiah
    addToNameLibrary(new String[]{
        "Isaiah",
                "Ike",
                "Ice",
                "Zay",
                "Isaia"
    });

    //Marcus
    addToNameLibrary(new String[]{
        "Marcus",
                "Marcos",
                "Mark",
                "Marc",
                "Marco",
                "Markus",
                "Marques",
                "Marquez"
    });

    //Lucas
    addToNameLibrary(new String[]{
        "Lucas",
                "Luke",
                "Lukas",
                "Luca",
                "Luka"
    });

    //Garrett
    addToNameLibrary(new String[]{
        "Garrett",
                "Garret",
                "Garett",
                "Garry",
                "Gary"
    });

    //Aidan
    addToNameLibrary(new String[]{
        "Aidan",
                "Aiden",
                "Aidyn",
                "Ayden"
    });

    //Luis
    addToNameLibrary(new String[]{
        "Luis",
                "Louis",
                "Louise",
                "Lewis",
                "Lucho",
                "Luisito",
                "Luigi",
                "Louie"
    });

    //Charles
    addToNameLibrary(new String[]{
        "Charles",
                "Charlie",
                "Charley",
                "Carlos"
    });

    //Blake
    addToNameLibrary(new String[]{
        "Blake",
                "Blakey",
                "Blackie"
    });




    }
    private void initNamesOshri()
    {

        //Emily
        addToNameLibrary(new String[]{
                "Emily",
                "Emma",
                "Emy",
                "Emilee"
        });

        //Michael
        addToNameLibrary(new String[]{
                "Michael",
                "Mike",
                "Mikey",
                "Mychal",
                "Mickey"
        });

        //Hannah
        addToNameLibrary(new String[]{
                "Hannah",
                "Hanna",
        });

        //Jacob
        addToNameLibrary(new String[]{
                "Jacob",
                "Jakob",
                "Jake",
                "Jakey",
                "Jack",
                "Jacoby"
        });

        //Sarah
        addToNameLibrary(new String[]{
                "Sarah",
                "Sara",
                "Sarrah"
        });

        //Alexander
        addToNameLibrary(new String[]{
                "Alexander",
                "Alex",
                "Xander",
                "Sasha",
                "Sacha",
                "Al",
                "Alexi"
        });

        //Ashley
        addToNameLibrary(new String[]{
                "Ashley",
                "Ash",
                "Ashleigh",
                "Ashlee",
                "Ashlie"
        });

        //Tyler
        addToNameLibrary(new String[]{
                "Tyler",
                "Ty",
        });

        //Taylor
        addToNameLibrary(new String[]{
                "Taylor",
                "Tay",
                "Tailor"
        });

        //Andrew
        addToNameLibrary(new String[]{
                "Andrew",
                "Drew",
                "Andy"
        });

        //Jessica
        addToNameLibrary(new String[]{
                "Jessica",
                "Jessie",
                "Jesse"
        });

        //Daniel
        addToNameLibrary(new String[]{
                "Daniel",
                "Danny",
                "Dan"
        });

        //Katie
        addToNameLibrary(new String[]{
                "Katie",
                "Kathryn",
                "Caitlyn",
                "Katherine",
                "Catherine",
                "Kaitlyn",
                "Kat",
                "Kit",
                "Cat",
                "Cady",
                "Katy",
                "Kady"
        });

        //John
        addToNameLibrary(new String[]{
                "John",
                "Jon",
                "Johnathan",
                "Jonathan",
                "Johnny",
                "Jhonny",
                "Jonny",
        });

        //Emma
        addToNameLibrary(new String[]{
                "Emma",
                "Em"
        });

        //Matthew
        addToNameLibrary(new String[]{
                "Matthew",
                "Matt",
                "Matty",
                "Mat",
                "Mathew",
                "Mat",
                "Mathias",
                "Mathieu",
                "Mats",
                "Mathieu",
                "Matteo",
                "Mathis",
                "Matias",
                "Mattis"
        });

        //Lauren
        addToNameLibrary(new String[]{
                "Lauren",
                "Laura",
                "Lawren",
                "Loren",
                "Laurenn",
                "Lawrenn",
                "Louren"
        });

        //Ryan
        addToNameLibrary(new String[]{
                "Ryan",
                "Ry",
                "Ryen",
                "Rian",
                "Riann",
                "Ryann"
        });

        //Samantha
        addToNameLibrary(new String[]{
                "Samantha",
                "Sam",
                "Sammy",
                "Sammantha",
                "Sammee",
                "Sammi",
                "Sammey",
                "Sami"
        });

        //Austin
        addToNameLibrary(new String[]{
                "Austin",
                "Austen",
                "Austyn"
        });

        //Rachel
        addToNameLibrary(new String[]{
                "Rachel",
                "Rach",
                "Rae",
                "Rachael",
                "Rachie",
                "Shelly",
                "Rachell"
        });

        //David
        addToNameLibrary(new String[]{
                "David",
                "Dave",
                "Davey",
                "Davie"
        });

        //Olivia
        addToNameLibrary(new String[]{
                "Olivia",
                "Olive",
                "Liv",
                "Livvy",
                "Livia",
                "Oli",
                "Olly"
        });

        //Chris
        addToNameLibrary(new String[]{
                "Chris",
                "Christopher",
                "Kris",
                "Christian",
                "Toph"
        });

        //Kayla
        addToNameLibrary(new String[]{
                "Kayla",
                "Mykayla",
                "Michaela",
                "Mychaela"
        });

        //Nick
        addToNameLibrary(new String[]{
                "Nick",
                "Nicholas",
                "Nicky",
                "Nicki",
                "Nic",
                "Nik",
                "Nikki",
                "Nickolas",
                "Nico",
                "Niko",
        });

        //Anna
        addToNameLibrary(new String[]{
                "Anna",
                "Anne",
                "Ann",
                "Ana",
                "Annie",
                "Anya",
                "Anja"
        });

        //Brandon
        addToNameLibrary(new String[]{
                "Brandon",
                "Branden",
                "Brandin",
                "Brand",
                "Brandt",
                "Brant",
                "Brandan"
        });

        //Megan
        addToNameLibrary(new String[]{
                "Megan",
                "Meghan",
                "Meg",
                "Meggie",
                "Meggi",
                "Meggy",
                "Margaret",
                "Meagan",
                "Meaghan",
                "Peggy"
        });

        //Nathan
        addToNameLibrary(new String[]{
                "Nathan",
                "Nate",
                "Nat",
                "Nathaniel",
                "Natie",
                "Natty",
                "Nathanael",
                "Natan",
                "Nathon"
        });

        //Alyssa
        addToNameLibrary(new String[]{
                "Alyssa",
                "Alisa",
                "Allisa",
                "Allissa",
                "Alissa",
                "Alison",
                "Ali",
                "Ally",
                "Aly",
                "Lyss",
                "Lyssa",
                "Elisa",
                "Alysa"
        });

        //Anthony
        addToNameLibrary(new String[]{
                "Anthony",
                "Tony",
                "Toni",
                "Anton",
                "Anthoney",
                "Ant",
                "Nino"
        });

        //Alexis
        addToNameLibrary(new String[]{
                "Alexis",
                "Alex",
                "Alexi",
                "Alexandria",
                "Alexandra",
                "Alexa",
                "Alexia",
                "Lexi",
                "Lex",
                "Ally",
                "Allie",
                "Ali",
                "Alli",
                "Lexie",
                "Lexy",
                "Alexys"
        });

        //Ethan
        addToNameLibrary(new String[]{
                "Ethan",
                "Eitan",
                "Etan",
                "Aitan",
                "Ethen"
        });

        //Grace
        addToNameLibrary(new String[]{
                "Grace",
                "Gracie",
                "Gracie",
        });

        //Justin
        addToNameLibrary(new String[]{
                "Justin",
                "Justain",
                "Justen",
                "Juste",
                "Justan",
                "Just"
        });

        //Madison
        addToNameLibrary(new String[]{
                "Madison",
                "Maddy",
                "Maddie",
                "Maddi"
        });

        //Joshua
        addToNameLibrary(new String[]{
                "Joshua",
                "Josh",
                "Joshie",
                "Joshy",
        });

        //Elizabeth
        addToNameLibrary(new String[]{
                "ELizabeth",
                "Liza",
                "Lisa",
                "Liz",
                "Lissie",
                "Lizzy",
                "Betty",
                "Beth",
                "Bettie",
                "Eliza",
                "Aliza",
                "Elisabeth",
                "Ellie",
                "Ella",
                "Libby",
                "Betsy",
                "Betsey",
        });

        //Jordan
        addToNameLibrary(new String[]{
                "Jordan",
                "Jourdan",
                "Jordy",
                "Jory",
                "Jordain",
                "Jordyn"
        });

        //Nicole
        addToNameLibrary(new String[]{
                "Nicole",
                "Nikki",
                "Coco",
                "Nic",
                "Necole",
                "Nicholle",
                "Nickie"
        });

        //Amanda
        addToNameLibrary(new String[]{
                "Amanda",
                "Mandy",
                "Manda",
                "Mandee",
                "Mandi",
                "Mandie"
        });

        //Jack
        addToNameLibrary(new String[]{
                "Jack",
                "Jackson",
                "John",
                "Jackie",
                "Jacky",
                "Jay",
                "Jax",
        });

        //Abby
        addToNameLibrary(new String[]{
                "Abby",
                "Abigail",
                "Abi",
                "Abbie",
                "Gail",
                "Gayle",
                "Gigi",
                "Abbs"
        });

        //Dylan
        addToNameLibrary(new String[]{
                "Dylan",
                "Dylann",
                "Dilon",
                "Dill"
        });

        //Victoria
        addToNameLibrary(new String[]{
                "Victoria",
                "Vic",
                "Vick",
                "Vicky",
                "Tori",
                "Torey",
                "Toria",
                "Vika"
        });

        //James
        addToNameLibrary(new String[]{
                "James",
                "Jim",
                "Jimmie",
                "Jimmy",
                "Jem",
                "Gem",
                "Jimbo",
                "Jemmy",
                "Jamie",
                "Jameson",
                "Jamison",
                "Jimi"
        });

        //Brianna
        addToNameLibrary(new String[]{
                "Brianna",
                "Bree",
                "Breena",
                "Breenie",
                "Bri",
                "Bria",
                "Breeana",
                "Breanna",
                "Breanne"
        });

        //Kyle
        addToNameLibrary(new String[]{
                "Kyle",
                "Ky",
        });

        //Morgan
        addToNameLibrary(new String[]{
                "Morgan",
                "Annie",
        });

        //Kevin
        addToNameLibrary(new String[]{
                "Kevin",
                "Kev",
        });

        //Amber
        addToNameLibrary(new String[]{
                "Amber",
                "Ambar",
                "Ambur",
                "Ammie",
                "Amberly"
        });

        //Ben
        addToNameLibrary(new String[]{
                "Ben",
                "Benjamin",
                "Bennie",
                "Benji",
                "Beni",
                "Benny",
                "Benjy"
        });

        //Sydney
        addToNameLibrary(new String[]{
                "Sydney",
                "Syd",
                "Sid",
                "Sidney",
                "Sidnee",
                "Sydnee",
                "Syddi",
                "Syddie",
                "Cydney",
                "Cydnie",
        });

        //Noah
        addToNameLibrary(new String[]{
                "Noah",
                "Noa",
                "Noach",
                "Noam",
                "Noe",
                "Noey",
                "Noi"
        });

        //Brittany
        addToNameLibrary(new String[]{
                "Brittany",
                "Britt",
                "Brit",
                "Britney",
                "Britany"
        });

        //Eric
        addToNameLibrary(new String[]{
                "Eric",
                "Erick",
                "Erik"
        });

        //Haley
        addToNameLibrary(new String[]{
                "Haley",
                "Hayley",
                "Haleigh",
                "Hailey",
                "Hailee",
                "Haily",
                "Hailie",
                "Haylee",
                "Hayleigh",
                "Haylie"
        });

        //Natalie
        addToNameLibrary(new String[]{
                "Natalie",
                "Nat",
                "Natalee",
                "Natalia",
                "Nattie",
                "Natty",
                "Tali",
                "Talia",
                "Natka"
        });

        //Julia
        addToNameLibrary(new String[]{
                "Julia",
                "Julie",
                "Jules",
                "Juley",
                "Juli",
                "JuJu"
        });

        //Savannah
        addToNameLibrary(new String[]{
                "Savannah",
                "Savvy",
        });

        //Danielle
        addToNameLibrary(new String[]{
                "Danielle",
                "Dani",
                "Daniella",
                "Elle",
                "Danee",
                "Danie",
                "Ella"
        });

        //Joseph
        addToNameLibrary(new String[]{
                "Joseph",
                "Joe",
                "Joey",
                "Jojo",
                "Jo",
                "Josef",
                "Josep",
                "Guiseppe",
                "Jodi",
                "Jody",
                "Jodie"
        });

        //Courtney
        addToNameLibrary(new String[]{
                "Courtney",
                "Cortnee",
                "Cordie",
                "Cordy",
                "Corkie",
                "Corteney"
        });

        //Rebecca
        addToNameLibrary(new String[]{
                "Rebecca",
                "Rebekah",
                "Becky",
                "Becca",
                "Becs",
                "Becks",
                "Rebekkah",
                "Rivkah",
                "Becki",
                "Reba",
                "Riva",
                "Beckie"
        });

        //Paige
        addToNameLibrary(new String[]{
                "Paige",
                "Peggy",
        });

        //Adam
        addToNameLibrary(new String[]{
                "Adam",
                "Addam",
        });

        //Jasmine
        addToNameLibrary(new String[]{
                "Jasmine",
                "Yasmine",
                "Jaz",
                "Jazzi",
                "Jazzy",
                "Mina"
        });

        //Aaron
        addToNameLibrary(new String[]{
                "Aaron",
                "Aron",
                "Aaran",
                "Aaren",
                "Aarin",
                "Arin",
                "Aaronn",
                "Aarron"
        });

        //Jason
        addToNameLibrary(new String[]{
                "Jason",
                "Jayson",
                "Jay",
                "Jae",
                "JayJay",
                "Jace",
                "Jacen",
                "Jaison",
                "Jaisen",
                "Jase",
                "Jasen"
        });

        //Stephanie
        addToNameLibrary(new String[]{
                "Stephanie",
                "Steph",
                "Stef",
                "Steffi",
                "Stephie",
                "Stevie"
        });

        //Jennifer
        addToNameLibrary(new String[]{
                "Jennifer",
                "Jenifer",
                "Jenny",
                "Jeni",
                "Jenna",
                "Jen",
                "Jenn",
                "Jenn Jenn",
                "Jenni"
        });

        //Caleb
        addToNameLibrary(new String[]{
                "Caleb",
                "Kaleb",
                "Cal",
                "Cale",
                "Cayleb"
        });

        //Mary
        addToNameLibrary(new String[]{
                "Mary",
                "May",
                "Mamie",
                "Mimi",
                "Mae",
                "Mattie",
                "Molly"
        });

        //Brian
        addToNameLibrary(new String[]{
                "Brian",
                "Bryan",
                "Bryin",
                "Bri",
                "Brye",
                "Bryen"
        });

        //Chloe
        addToNameLibrary(new String[]{
                "Chloe",
                "Coco",
                "Lolo"
        });

        //William
        addToNameLibrary(new String[]{
                "William",
                "Will",
                "Bill",
                "Billy",
                "Willy",
                "Willie",
                "Liam",
                "Billie",
                "Wiley"
        });

        //Robert
        addToNameLibrary(new String[]{
                "Robert",
                "Rob",
                "Bob",
                "Robby",
                "Bobby",
                "Robbie",
                "Bobbie",
                "Bert",
                "Bertie",
                "Roby"
        });

        //Lily
        addToNameLibrary(new String[]{
                "Lily",
                "Lilly",
                "Lillie",
                "Lilia",
                "Lil"
        });

        //Michelle
        addToNameLibrary(new String[]{
                "Michelle",
                "Shelly",
                "Mickie",
                "Micky",
                "Shelley",
                "Mika",
                "Michy"
        });

        //Luke
        addToNameLibrary(new String[]{
                "Luke",
                "Lucas",
                "Lukas",
                "Luckas",
                "Luc",
                "Lucky",
                "Lou",
                "Luka"
        });

        //Brooke
        addToNameLibrary(new String[]{
                "Brooke",
                "Brook",
                "Brookie"
        });

        //Jordan
        addToNameLibrary(new String[]{
                "Jordan",
                "Jordana",
                "Jordyn",
                "Jordie",
                "Jojo"
        });

        //Cameron
        addToNameLibrary(new String[]{
                "Cameron",
                "Cam",
                "Ron",
                "Ronny",
                "Ronnie",
                "Camilo"
        });

        //Thomas
        addToNameLibrary(new String[]{
                "Thomas",
                "Tommy",
                "Tomas",
                "Tom",
                "Thom",
                "Tommie"
        });

        //Shelby
        addToNameLibrary(new String[]{
                "Shelby",
                "Shelly",
                "Shelley",
                "Shelbey",
                "Shelbi"
        });





    }
    private void addToNameLibrary(String[] tempNames)
    {
        allRootNames.add(tempNames[0]);
        ArrayList<String> namesToAdd = addNames(tempNames);
        firstNames.put(tempNames[0],namesToAdd);
    }
    private ArrayList<String> addNames(String[] names)
    {
        ArrayList<String> listNames = new ArrayList<>();
        int y = names.length;
        for (int x = 0; x < names.length; x++)
        {
            listNames.add(names[x]);
        }
        return listNames;
    }

    /*
    This function performs an exhaustive search for all names related to the one it's passed and returns them as an array of strings
     */
    public String[] getRelatedNames(String name)
    {
        int firstSpaceIndex = name.indexOf(" ");
        String firstName = name.substring(0,firstSpaceIndex);
        String lastName = name.substring((firstSpaceIndex + 1));
        String[] returnedArray = new String[]{};

        ArrayList<String> finalNameList = new ArrayList<>();
        ArrayList<String> rootNamesToSearch = new ArrayList<>();
        for (String rootName : allRootNames)
        {
            for (String relatedName : firstNames.get(rootName))
            {

                if (firstName.equals(relatedName) && !rootNamesToSearch.contains(rootName))
                {
                    rootNamesToSearch.add(rootName);
                }
            }
        }
        if (rootNamesToSearch.size() == 0)
        {
            returnedArray = new String[1];
            returnedArray[0] = (firstName + " " + lastName);
            return returnedArray;
        }

        for (String rootName : rootNamesToSearch)
        {
            for (String tempName : firstNames.get(rootName))
            {
                if (!finalNameList.contains(tempName))
                {
                    finalNameList.add(tempName);
                }
            }
        }
        returnedArray = new String[finalNameList.size()];
        int index = 0;

        for (String tempName : finalNameList)
        {
            returnedArray[index] = (tempName + " " + lastName);
            index++;
        }

        return returnedArray;
    }

    public String getTestAccountName()
    {
        ArrayList<String> firstNameList = firstNames.get(allRootNames.get((int) (Math.random() * allRootNames.size())));
        String chosenFirstName = firstNameList.get(publicFunctions.getRandomInt(firstNameList.size(),false));
        String chosenLastName = commonLastNames.get(publicFunctions.getRandomInt(commonLastNames.size(),false));
        return chosenFirstName + " " + chosenLastName;
    }

    public String getTestAccountUsername(String name)
    {
        Boolean useUnderscores = publicFunctions.getRandomBool();
        Boolean useNumAtEnd = publicFunctions.getRandomBool();
        String username = "";
        String temp;

        String[] nameFrags = name.split(" ");
        for (int y = 0; y < nameFrags.length; y++)
        {
            if (useUnderscores)
            {
                temp = nameFrags[y];
                temp.toLowerCase();

                if (y != (nameFrags.length - 1))
                {
                    username += temp += "_";
                }
                else
                {
                    username += temp;
                }
            }
            else
            {
                temp = nameFrags[y];
                username += temp;
            }



        }
        if (useNumAtEnd)
        {
            int numToAdd = publicFunctions.getRandomInt(9999,true);
            username += Integer.toString(numToAdd);
        }
        return username;
    }


}
