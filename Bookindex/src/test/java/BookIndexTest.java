import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BookIndexTest {

    BookIndex idx;

    @Before
    public void setUp() throws Exception {
        idx = new BookIndex();

        idx.addReference("Affe",11);
        idx.addReference("Affe",11);
        idx.addReference("Affe",11);
        idx.addReference("Affe",22);
        idx.addReference("Affe",22);
        idx.addReference("Affe",41);

        for(int i=1; i<100; i++) {
            idx.addReference("GuildWarsRulez",i);
        }

        idx.addReference("Batman",666);
        idx.addReference("Robin",666);

        idx.addReference("Joker", -1);  // -1 is a very bad page number!

        idx.printIndex();
    }

    @Test
    public void monkeyPages() throws PagesNotFoundException {
        assertEquals("11, 22, 41", idx.getPages("Affe"));
    }

    @Test
    public void GuildWarsLength() throws PagesNotFoundException {
        assertEquals(385, idx.getPages("GuildWarsRulez").length()); // length of "1, 2, 3, 4, ... 98, 99"
    }

    @Test
    public void batmanAndRobinAlwaysOnSamePages() throws PagesNotFoundException {
        assertEquals("666", idx.getPages("Batman"));
        assertEquals("666", idx.getPages("Robin"));
    }

    @Test(expected = PagesNotFoundException.class)
    public void jokerPagesNotFound() throws PagesNotFoundException {
        idx.getPages("Joker");
    }

    @Test(expected = PagesNotFoundException.class)
    public void twoFacePagesNotFound() throws PagesNotFoundException {
        idx.getPages("TwoFace");
    }
}