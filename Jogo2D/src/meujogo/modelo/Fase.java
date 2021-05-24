package meujogo.modelo;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Fase extends JPanel implements ActionListener {
	// JPanel e uma classe interna do Java



	private Image fundo;
	// Para colocar imagem de fundo

	protected Image nebulosa;
	private Player player;
	// Coloca a nave na fase
	private Timer timer;
	private List<Inimigo> inimigo;
	// Coloca o inimigo na fase
	private List<Nebula> nebulas;
	private boolean emJogo;

	public Fase() {
		// Construtor
		TesteInicio();
	}

	public void TesteInicio() {
		setFocusable(true);
		setDoubleBuffered(true);
		// Para melhorar o desempenho

		ImageIcon referencia = new ImageIcon("res\\fundo.jpg");
		fundo = referencia.getImage();
		// Para colocar a imagem de fundo
		ImageIcon iiNebulosa = new ImageIcon("res\\Nebula.png");
		nebulosa = iiNebulosa.getImage();

		player = new Player();
		player.load();
		// Para colocar o player

		addKeyListener(new TecladoAdapter());

		timer = new Timer(5, this);
		timer.start();
		// Velocidade do jogo

		inicializaInimigos();
		inicializaNebulas();
		emJogo = true;

	}

	public void inicializaInimigos() {
		int coordenadas[] = new int[80];
		// Quantidade de inimigos
		inimigo = new ArrayList<Inimigo>();
		
		String[] vetor = new String[5];
		vetor[0] = "res\\industria.png"; 
		vetor[1] = "res\\agrotoxico.png";
		vetor[2] = "res\\fertilizante.png";
		vetor[3] = "res\\latinha.png";
		vetor[4] = "res\\maca.png";
		//Para deixar os inimigos aleatorios
		
		for (int i = 0; i < coordenadas.length; i++) {
			Random numero = new Random();
			int a = numero.nextInt(5);
			
			int x = (int) (Math.random() * 8000 + 1024);
			int y = (int) (Math.random() * 650 + 30);
			inimigo.add(new Inimigo(x, y, vetor[a]));
			// Para os inimigos aparecerem na tela aleatoriamente
		}

	}

	public void inicializaNebulas() {
		int coordenadas[] = new int[2];
		nebulas = new ArrayList<Nebula>();

		for (int i = 0; i < coordenadas.length; i++) {
			int x = (int) (Math.random() * 1050 + 1024);
			int y = (int) ((Math.random() * 768) - (Math.random() * 768));
			nebulas.add(new Nebula(x, y));
			// Para as nebulas aparecerem na tela aleatoriamente
		}
	}

	public void paint(Graphics g) {
		// Graphics serve para pintar a imagem na tela

		Graphics2D graficos = (Graphics2D) g;
		if (emJogo == true) {
			graficos.drawImage(fundo, 0, 0, null);
			// graficos.drawImage(nebulosa, 0, 0, null);
			/*
			 * 0, 0 sao as coordenadas para a imagem aparecer na tela null e para a imagem
			 * aparecer no meio da tela
			 */

			for (int j = 0; j < nebulas.size(); j++) {
				Nebula n = nebulas.get(j);
				n.load();
				graficos.drawImage(n.getImagem(), n.getX(), n.getY(), this);
			}

			List<Tiro> tiros = player.getTiros();
			for (int i = 0; i < tiros.size(); i++) {
				Tiro m = tiros.get(i);
				m.load();
				graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);
			}

			graficos.drawImage(player.getImagem(), player.getX(), player.getY(), this);

			
			
			for (int o = 0; o < inimigo.size(); o++) {
				Inimigo in = inimigo.get(o);
				
				in.load();
				graficos.drawImage(in.getImagem(), in.getX(), in.getY(), this);
			}
		} 

		g.dispose();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		player.update();
		// Atualiza a tela quando o player vai se movendo

			for (int p = 0; p < this.nebulas.size(); p++) {
				Nebula ne = nebulas.get(p);
				if (ne.isVisivel()) {
					ne.update();
				} 
				else {
					nebulas.remove(p);
				} 
			}
			
			
			List<Tiro> tiros = player.getTiros();
			// Para os tiros serem infinitos
			for (int i = 0; i < tiros.size(); i++) {
				Tiro m = tiros.get(i);
				if (m.isVisivel() && i%2==0) { // i%2==0 permite que o jogador tenha que recarregar a arma pra atirar
					m.update();
				} else {
					tiros.remove(i);
				}
			}

			for (int o = 0; o < inimigo.size(); o++) {
				Inimigo in = inimigo.get(o);
				if (in.isVisivel()) {
					in.update();
				} else {
					inimigo.remove(o);
				}
			}

			checarColisoes();

			repaint();
		

	}

	public void checarColisoes() {
		Rectangle formaNave = player.getBounds();
		Rectangle formaInimigo1;
		Rectangle formaTiro;

		for (int i = 0; i < inimigo.size(); i++) {
			Inimigo tempInimigo1 = inimigo.get(i);
			formaInimigo1 = tempInimigo1.getBounds();
			if (formaNave.intersects(formaInimigo1)) {
				// Colisao da nave com inimigo
				player.setVisivel(false);
				tempInimigo1.setVisivel(false);
				JOptionPane.showMessageDialog(null, "Voce perdeu :(");
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog(this, "Deseja jogar novamente?", "Fim de jogo!",
						dialogButton);
				if (dialogResult == 0) {
					JOptionPane.showMessageDialog(null, "Reiniciando...");
					inicializaInimigos();
					inicializaNebulas();
					player = new Player();
					player.load();
					emJogo = true;
					return;
				} else {
					JOptionPane.showMessageDialog(null, "Encerrando...");
					System.exit(0);
					emJogo = false;
				}
				// Game over
			}
		}

		List<Tiro> tiros = player.getTiros();

		for (int j = 0; j < tiros.size(); j++) {
			Tiro tempTiro = tiros.get(j);
			formaTiro = tempTiro.getBounds();
			for (int k = 0; k < inimigo.size(); k++) {
				Inimigo tempInimigo1 = inimigo.get(k);
				formaInimigo1 = tempInimigo1.getBounds();
				if (formaTiro.intersects(formaInimigo1)) {
					// Colisao do tiro com inimigo
					tempInimigo1.setVisivel(false);
					tempTiro.setVisivel(false);
				} 
			} 
		} 

	}

	private class TecladoAdapter extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			player.keypressed(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			player.keyRelease(e);
		}

	}

}
