package meujogo;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import meujogo.modelo.Fase;

public class Container extends JFrame {
//JFrame e uma classe interna do Java	
   /**
	 * 
	 */
	private static final long serialVersionUID = -5153604303054161944L;

	public Container (){
		//Construtor
		add(new Fase());
    	setTitle("Aquecimento global e as mudanças climáticas");
    	//Titulo da pagina
    	ImageIcon icone = new ImageIcon("res\\icone.png");
        this.setIconImage(icone.getImage());

		setSize(1300,700);
		//Dimensao(tamanho da pagina) sendo largura,altura
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Serve para fechar o jogo assim que clicar no x
		setLocationRelativeTo(null);
		// null faz com que o player apareca no meio
		this.setResizable(false);
		/*
		 * Serve para controlar se o usuario pode 
		 * maximizar ou deixar a tela menor
		 * false proibe o usuario de fazer isso
		 */
		setVisible(true);
		//Define que os comandos sets acima seja visivel
	}
             
	public static void main (String [] args){
			new Container();
	}
    

  
}
