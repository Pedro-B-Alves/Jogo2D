package meujogo.modelo;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Inimigo {
	
	private Image imagem;
	//Para colocar imagem no codigo
	private int x, y;
	//Posicao do inimigo
	private int largura, altura;
	private boolean isVisivel;
	//Para o inimigo sumir quando for atingido pelo tiro
	
	private static int VELOCIDADE = 4;
	//Velocidade do inimigo
	
	
	
	
	
	public Inimigo (int x, int y, String imagem) {
		//Constructor
		
		
		ImageIcon referencia = new ImageIcon(imagem);
		this.imagem = referencia.getImage();
		
		this.x = x;
		this.y = y;
		isVisivel = true;
		
	}
	
	
	public void load() {
		//Metodo que define a imagem do inimigo
		
		this.largura = imagem.getWidth(null);
		this.altura = imagem.getHeight(null);
		//Para colocar a imagem
	}
	
	public void update() {
		this.x -= VELOCIDADE;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, largura, altura);
		//Para a colisao
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
