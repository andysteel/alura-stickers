import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonParser {
    private static final Pattern REGEX_ITEMS = Pattern.compile(".*\\[(.+)\\].*");
    private static final Pattern REGEX_ITEM = Pattern.compile(".*\\{(.+)\\}.*");
    private static final Pattern REGEX_ATRIBUTOS_JSON = Pattern.compile("\"(.+?)\":\"(.*?)\"");
    
    public List<Map<String, String>> parse(final String json) {

        final Matcher matcher = REGEX_ITEMS.matcher(json);
        final Matcher itemMatcher = REGEX_ITEM.matcher(json);
        if (!matcher.find() && !itemMatcher.find()) {

            throw new IllegalArgumentException("Não encontrou items.");
        }

        String[] items = null;
        if(matcher.find()) {
            items = matcher.group(1).split("\\},\\{");
        } else {
            items = new String[]{itemMatcher.group()};
        }
        

        final List<Map<String, String>> dados = new ArrayList<>();

        for (final String item : items) {

            final Map<String, String> atributosItem = new HashMap<>();

            final Matcher matcherAtributosJson = REGEX_ATRIBUTOS_JSON.matcher(item);
            while (matcherAtributosJson.find()) {
                final String atributo = matcherAtributosJson.group(1);
                final String valor = matcherAtributosJson.group(2);
                atributosItem.put(atributo, valor);
            }

            dados.add(atributosItem);
        }

        return dados;

    }
}
