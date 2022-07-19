public class TextFormatter {
    
    public static String format(final String title, final String image, final String rating) {

        final String divider = "-";
        final String star = "*";
        final String pipe = "|";
        final String sublinhado = "\u001b[4m";
        final String reset = "\u001b[m";
        final String textoMagenta = "\u001b[35m";
        final String textoAzul = "\u001b[34m";
        final String emoji = formatEmoji(rating);

        return """
                *%s%s%s%s%s%s%s*
                | %s %s %s %s %s %s %S |    
                | %s %s %s %s %s %s %s | 
                *%s%s%s%s%s%s%s*
                """.formatted( 
                    divider.repeat(title.length()+2), 
                    star, 
                    divider.repeat(image.length()+2),
                    star,
                    divider.repeat(rating.length()+2),
                    star,
                    divider.repeat(emoji.length()+2),
                    textoMagenta+title+reset, pipe, 
                    sublinhado+textoAzul+image+reset, pipe, 
                    formatRating(rating)+reset, pipe,
                    emoji,
                    " ".repeat(title.length()), pipe, 
                    " ".repeat(image.length()), pipe,
                    " ".repeat(rating.length()), pipe,
                    " ".repeat(emoji.length()),
                    divider.repeat(title.length()+2), star,
                    divider.repeat(image.length()+2), star,
                    divider.repeat(rating.length()+2), star,
                    divider.repeat(emoji.length()+2));
    }

    private static String formatRating(final String rating) {
        final int rate = parseToInt(rating);
        final String textoVermelho = "\u001b[31m";
        final String textoVerde = "\u001b[32m";
        final String textoAmarelo = "\u001b[33m";

        if(rate >= 8) {
            return textoVerde+rating;
        }  else if(rate >= 6 && rate < 8) {
            return textoAmarelo+rating;
        }

        return textoVermelho+rating;
    }

    private static String formatEmoji(final String rating) {
        final int rate = parseToInt(rating);

        if(rate >= 8) {
            return "\uD83D\uDC4F";
        } else if(rate >= 6 && rate < 8) {
            return "\uD83D\uDE4D";
        }

        return "\uD83D\uDECC";
    }

    private static int parseToInt(final String rating) {
        return Double.valueOf(rating).intValue();
    }

}
