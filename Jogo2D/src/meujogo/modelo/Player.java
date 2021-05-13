package meujogo.modelo;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class Player {
	
	private int x, y;
	private int dx, dy;
	//Essas variaveis serao usadas para mover a nave
	private Image imagem;
	//Para colocar imagem no codigo
	private int altura, largura;
	//Serão usadas para a colisao
	private List <Tiro> tiros;
	private boolean isVisivel;
	
	public Player() {
		//Construtor
		this.x= 100;
		this.y = 700/2;
		//Coordenada em que o player vai nascer na tela
		isVisivel= true;
		
		tiros = new ArrayList<Tiro>();
		
	}
	
	public void load() {
		//Metodo que define a imagem da nave
		ImageIcon referencia = new ImageIcon("res\\nave-espacial.png");
		// ImageIcon deve ser importado como na linha 9
		imagem = referencia.getImage();
		altura = imagem.getHeight(null);
		largura = imagem.getWidth(null);
		//Para colocar a imagem
	}
	
	public void update() {
		x += dx;
		y += dy;
	}
	
	public void tiroSimples() {
		this.tiros.add(new Tiro(x + (largura/2), y + (altura/2)));
		//Para o tiro saia do meia da nave
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, largura, altura);
		//Para a colisao
	}
	
	
	public void keypressed(KeyEvent tecla) {
		int codigo  = tecla.getKeyCode();
		
		if(codigo== KeyEvent.VK_A) {
			tiroSimples();
		}//Quando apertar a tecla A, solta tiro
		
		if(codigo== KeyEvent.VK_UP) {
			dy = -3;
		}//Quando apertar a tecla para cima, o player vai para cima
		if(codigo== KeyEvent.VK_DOWN) {
			dy = 3;
		}//Quando apertar a tecla para baixo, o player vai para baixo	
		if(codigo== KeyEvent.VK_LEFT) {
			dx = -3;
		}//Quando apertar a tecla para esquerda, o player vai para esquerda	
		if(codigo== KeyEvent.VK_RIGHT) {
			dx = 3;
		}//Quando apertar a tecla para direita, o player vai para direita
		
	}
	
	public void keyRelease(KeyEvent tecla) {
		/* Esse metodo faz com que quando o usuario
		 * nao pressiona nenhuma tecla, que a nave 
		 * fique parado */
		
		int codigo  = tecla.getKeyCode();
		
		if(codigo== KeyEvent.VK_UP) {
			dy = 0;
		}
		if(codigo== KeyEvent.VK_DOWN) {
			dy = 0;
		}		
		if(codigo== KeyEvent.VK_LEFT) {
			dx = 0;
		}
		if(codigo== KeyEvent.VK_RIGHT) {
			dx = 0;
		}
		
	}
	
	

	public boolean isVisivel() {
		return isVisivel;
	}

	public void setVisivel(boolean isVisivel) {
		this.isVisivel = isVisivel;
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

	public List<Tiro> getTiros() {
		return tiros;
	}

	

}
