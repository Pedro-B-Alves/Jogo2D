package meujogo.modelo;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Tiro {
	
	private Image imagem;
	//Para colocar imagem no codigo
	private int x, y;
	//Posicao do tiro
	private int largura, altura;
	private boolean isVisivel;
	//Para o tiro sumir quando bater no inimigo
	
	private static final int LARGURA = 1366;
	private static int VELOCIDADE = 2;
	//Velocidade do tiro
	
	public Tiro (int x, int y) {
		//Constructor
		this.x = x;
		this.y = y;
		isVisivel = true;
	}
	
	public void load() {
		//Metodo que define a imagem do tiro
		ImageIcon referencia = new ImageIcon("res\\tiro.png");
		imagem = referencia.getImage();
		
		this.largura = imagem.getWidth(null);
		this.altura = imagem.getHeight(null);
		//Para colocar a imagem
	}
	
	public void update() {
		this.x += VELOCIDADE;
		if (this.x > LARGURA) {
			isVisivel = false;
			//Quando o tiro chegar no limite da tela, ele some
		}
	}
	

	public Rectangle getBounds() {
		return new Rectangle(x, y, largura, altura);
		//Para a colisao do tiro nos inimigos
	}
	
	public boolean isVisivel() {
		return isVisivel;
	}

	public void setVisivel(boolean isVisivel) {
		this.isVisivel = isVisivel;
	}

	public static int getVELOCIDADE() {
		return VELOCIDADE;
	}

	public static void setVELOCIDADE(int vELOCIDADE) {
		VELOCIDADE = vELOCIDADE;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Image getImagem() {
		return imagem;
	}
	
	
	
}
