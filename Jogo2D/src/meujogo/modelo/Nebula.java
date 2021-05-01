package meujogo.modelo;

import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;

public class Nebula {
	
	private Image imagem;
	//Para colocar imagem no codigo
	private int x, y;
	//Posição da nebula
	protected int largura, altura;
	private boolean isVisivel;
	private static int VELOCIDADE = 1;
	//Velocidade da nebula
	
	
	public Nebula (int x, int y) {
		//Construtor
		this.x = x;
		this.y = y;
		isVisivel = true;
	}
	
	public void load() {
		//Para colocar a imagem
		ImageIcon referencia = new ImageIcon("res\\Nebula.png");
		imagem = referencia.getImage();
		
		this.largura = imagem.getWidth(null);
		this.altura = imagem.getHeight(null);
	}
	
	public void update() {
		if(this.x < -(this.largura)) {
			this.x = largura;
			Random a = new Random();
			int m = a.nextInt(500);
			this.x = m + 1300;
			
			Random r = new Random();
			int n = r.nextInt(700);
			this.y = n;
		}
		else {
			this.x -= VELOCIDADE;
		}

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
