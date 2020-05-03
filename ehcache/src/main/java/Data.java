import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

public class Data {
    private Cache cache;

    public Data(Cache cache) {
        this.cache = cache;
    }
    public void addEntinty()
    {
        cache.put(new Element("111111", "Toyota Avensis"));
        cache.put(new Element("222222", "Toyota Supra"));
        cache.put(new Element("333333", "Toyota Supra"));
        cache.put(new Element("444444", "Fiat 125P"));
        cache.put(new Element("555555", "Toyota 1266p"));
        cache.put(new Element("111111", "Opel Astra"));
    }

}
