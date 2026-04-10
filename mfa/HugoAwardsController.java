package wf.garnier.spring.security.seven.authorization.server;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
class HugoAwardsController {

    private List<Book> books = List.of(
        new Book("Alfred Bester", "The Demolished Man", 1953),
        new Book("Mark Clifton", "They'd Rather Be Right (also known as The Forever Machine)", 1955),
        new Book("Robert A. Heinlein", "Double Star", 1956),
        new Book("Fritz Leiber", "The Big Time", 1958),
        new Book("James Blish", "A Case of Conscience", 1959),
        new Book("Robert A. Heinlein", "Starship Troopers", 1960),
        new Book("Walter M. Miller, Jr.", "A Canticle for Leibowitz", 1961),
        new Book("Robert A. Heinlein", "Stranger in a Strange Land", 1962),
        new Book("Philip K. Dick", "The Man in the High Castle", 1963),
        new Book("Clifford D. Simak", "Here Gather the Stars (also known as Way Station)", 1964),
        new Book("Fritz Leiber", "The Wanderer", 1965),
        new Book("Frank Herbert", "Dune", 1966),
        new Book("Robert A. Heinlein", "The Moon Is a Harsh Mistress", 1967),
        new Book("Roger Zelazny", "Lord of Light", 1968),
        new Book("John Brunner", "Stand on Zanzibar", 1969),
        new Book("Ursula K. Le Guin", "The Left Hand of Darkness", 1970),
        new Book("Larry Niven", "Ringworld", 1971),
        new Book("Philip José Farmer", "To Your Scattered Bodies Go", 1972),
        new Book("Isaac Asimov", "The Gods Themselves", 1973),
        new Book("Arthur C. Clarke", "Rendezvous with Rama", 1974),
        new Book("Ursula K. Le Guin", "The Dispossessed", 1975),
        new Book("Joe Haldeman", "The Forever War", 1976),
        new Book("Kate Wilhelm", "Where Late the Sweet Birds Sang", 1977),
        new Book("Frederik Pohl", "Gateway", 1978),
        new Book("Vonda N. McIntyre", "Dreamsnake", 1979),
        new Book("Arthur C. Clarke", "The Fountains of Paradise", 1980),
        new Book("Joan D. Vinge", "The Snow Queen", 1981),
        new Book("C. J. Cherryh", "Downbelow Station", 1982),
        new Book("Isaac Asimov", "Foundation's Edge", 1983),
        new Book("David Brin", "Startide Rising", 1984),
        new Book("William Gibson", "Neuromancer", 1985),
        new Book("Orson Scott Card", "Ender's Game", 1986),
        new Book("Orson Scott Card", "Speaker for the Dead", 1987),
        new Book("David Brin", "The Uplift War", 1988),
        new Book("C. J. Cherryh", "Cyteen", 1989),
        new Book("Dan Simmons", "Hyperion", 1990),
        new Book("Lois McMaster Bujold", "The Vor Game", 1991),
        new Book("Lois McMaster Bujold", "Barrayar", 1992),
        new Book("Vernor Vinge", "A Fire Upon the Deep", 1993),
        new Book("Kim Stanley Robinson", "Green Mars", 1994),
        new Book("Lois McMaster Bujold", "Mirror Dance", 1995),
        new Book("Neal Stephenson", "The Diamond Age", 1996),
        new Book("Kim Stanley Robinson", "Blue Mars", 1997),
        new Book("Joe Haldeman", "Forever Peace", 1998),
        new Book("Connie Willis", "To Say Nothing of the Dog", 1999),
        new Book("Vernor Vinge", "A Deepness in the Sky", 2000),
        new Book("J. K. Rowling", "Harry Potter and the Goblet of Fire", 2001),
        new Book("Neil Gaiman", "American Gods", 2002),
        new Book("Robert J. Sawyer", "Hominids", 2003),
        new Book("Lois McMaster Bujold", "Paladin of Souls", 2004),
        new Book("Susanna Clarke", "Jonathan Strange & Mr Norrell", 2005),
        new Book("Robert Charles Wilson", "Spin", 2006),
        new Book("Vernor Vinge", "Rainbows End", 2007),
        new Book("Michael Chabon", "The Yiddish Policemen's Union", 2008),
        new Book("Neil Gaiman", "The Graveyard Book", 2009),
        new Book("Paolo Bacigalupi", "The Windup Girl", 2010),
        new Book("Connie Willis", "Blackout/All Clear", 2011),
        new Book("Jo Walton", "Among Others", 2012),
        new Book("John Scalzi", "Redshirts", 2013),
        new Book("Ann Leckie", "Ancillary Justice", 2014),
        new Book("Cixin Liu (Chinese)", "The Three-Body Problem", 2015),
        new Book("N. K. Jemisin", "The Fifth Season", 2016),
        new Book("N. K. Jemisin", "The Obelisk Gate", 2017),
        new Book("N. K. Jemisin", "The Stone Sky", 2018),
        new Book("Mary Robinette Kowal", "The Calculating Stars", 2019),
        new Book("Arkady Martine", "A Memory Called Empire", 2020),
        new Book("Martha Wells", "Network Effect", 2021),
        new Book("Arkady Martine", "A Desolation Called Peace", 2022),
        new Book("Ursula Vernon (as T. Kingfisher)", "Nettle & Bone", 2023),
        new Book("Emily Tesh", "Some Desperate Glory", 2024),
        new Book("Robert Jackson Bennett", "The Tainted Cup", 2025)
    );

    record Book(String author, String title, int yearWon) {}

    @GetMapping("/hugo/winners")
    public List<Book> getWinners(
            @RequestParam(required = false) String from,
            @RequestParam(required = false) String to) {
        
        return books.stream()
                .filter(b -> from == null || b.yearWon() >= Integer.parseInt(from))
                .filter(b -> to == null || b.yearWon() <= Integer.parseInt(to))
                .collect(Collectors.toList());
    }
}
