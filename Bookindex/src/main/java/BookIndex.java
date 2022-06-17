import java.sql.Array;
import java.util.*;

public class BookIndex {

    Map<String, Set<Integer>> bookindexmap = new TreeMap<>();


    public void addReference(String term, int pageNum) {
        if(pageNum > 0){
            if(bookindexmap.containsKey(term)){
                if (!bookindexmap.get(term).contains(pageNum)) {
                    bookindexmap.get(term).add(pageNum);
                }
            }
            else{
                Set<Integer> pages = new TreeSet<>();
                pages.add(pageNum);
                bookindexmap.put(term, pages);
            }
        }
    }

    public String getPages(String term) throws PagesNotFoundException{

        String s = "";
        if(bookindexmap.containsKey(term)){
            for (Integer integer : bookindexmap.get(term)){
                if(integer != bookindexmap.get(term).toArray()[bookindexmap.get(term).size()-1]){
                    s = s + integer.toString() + ", ";
                }
                else{
                    s = s + integer.toString() ;
                }
            }
        }
        else{
            throw new PagesNotFoundException("Seite nicht gefunden");
        }
        return s;
    }

    public void printIndex() {
        System.out.println(bookindexmap);
    }
}
