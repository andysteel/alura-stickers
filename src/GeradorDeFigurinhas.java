import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class GeradorDeFigurinhas {
    
    /**
     * @param inputStream
     * @param nomeDoArquivo
     * @throws IOException
     */
    public void cria(InputStream inputStream, String nomeDoArquivo, String textoFigurinha) throws IOException {
        BufferedImage imagemOriginal = ImageIO.read(inputStream);

        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200;

        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        Graphics2D graphics = novaImagem.createGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);

        var fonte = new Font(Font.SANS_SERIF, Font.BOLD, 76);
        graphics.setColor(Color.YELLOW);
        graphics.setFont(fonte);
        graphics.drawString(textoFigurinha, posicaoLateralTexto(textoFigurinha, largura), novaAltura - 100);

        var saidaArquivo = new File("saida"+File.separator+nomeDoArquivo);
        if(saidaArquivo.mkdirs()) {
            ImageIO.write(novaImagem, "png", saidaArquivo);
        }
        
    }

    private int posicaoLateralTexto(String texto, int larguraImagem) {
        int tamanhoDaLetraEmPixel = 60;
        int metadeImagem = larguraImagem / 2;
        int metadeTexto = (tamanhoDaLetraEmPixel * texto.length()) / 2;

        return metadeImagem - metadeTexto;
    }
}
