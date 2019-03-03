import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;



public class Fenetre extends JFrame implements ActionListener {

	//Initialize button, field and label
	private JTextField fieldNom = new JTextField(); //Zone de texte qui rempli le nom 
	private JTextField fieldPrenom = new JTextField(); //Zone de texte qui rempli le prénom
	private JTextField fieldMail = new JTextField();
	private JLabel labelWelcome = new JLabel(); //Creation du titre principal
	private JLabel labelNom = new JLabel(); //Creation du titre nom
	private JLabel labelPrenom = new JLabel();
	private JLabel labelMail = new JLabel();
	private JLabel labelConcert = new JLabel();
	private JLabel labelPlace = new JLabel();
	private JLabel labelPlaceDebout = new JLabel();
	private JLabel labelPlaceAssise = new JLabel();
	private JLabel erreur;
	private JButton buttonSend = new JButton(); //Création du bouton
	//Creation d'un tableau avec les valeurs à mettre dans la liste déroulante
	String[] concertTab = {"BigFlo & Oli : 12/05/2018", "BigFlo & Oli : 19/05/2018", "BigFlo & Oli : 27/05/2018", "BigFlo & Oli : 29/05/2018"};
	private JComboBox comboConcert = new JComboBox(concertTab); //creation de la liste deroulante qui recup�ere le tableau
	private JRadioButton buttonPlaceDebout = new JRadioButton(); //creation des boutons à cocher
	private JRadioButton buttonPlaceAssise = new JRadioButton();
	private ButtonGroup  groupTypePlace = new ButtonGroup(); //creation du groupe qui recuperera les deux boutons

	Boolean verif = false;//Initialize the verification to false. It will help us to know if the user did not fill a field

	Box windowPanel = Box.createVerticalBox();//Creation de la box qui accueillera les autres box

	Box panTitle = Box.createHorizontalBox(); //Pour le titre
	Box panNom = Box.createHorizontalBox(); //Pour le nom
	Box panPrenom = Box.createHorizontalBox(); //Pour le prenom
	Box panMail = Box.createHorizontalBox(); //Pour le mail
	Box panSend = Box.createHorizontalBox(); //Pour le bouton d'envoie
	Box panConcert = Box.createHorizontalBox(); //Pour la ligne concert
	Box panPlace = Box.createHorizontalBox(); // Pour la ligne place

	//Create one event listener when the user click on the button
	@Override
	public void actionPerformed(ActionEvent e) {
		//we take the value of the different textfield
		String NomValue = fieldNom.getText();
		String PrenomValue = fieldPrenom.getText();
		String MailValue = fieldMail.getText();
		String ConcertValue = comboConcert.getSelectedItem().toString();
		String TypeValue;
		//we see which buttonPlace is selected to attribute it the value
		if (buttonPlaceAssise.isSelected()) {
			TypeValue = "Place Assise";
		}else {
			TypeValue = "Place Debout";
		}

		//We see if one of the value was empty

		if (NomValue.isEmpty() || PrenomValue.isEmpty() || MailValue.isEmpty() || ConcertValue.isEmpty() || TypeValue.isEmpty() ) {
			//if one of them is empty we remove everything and we display it with a error message
			verif = true;
			windowPanel.removeAll();
			windowPanel.revalidate();
			Display(verif);
			verif = false;
			windowPanel.repaint();
		}else {
			//if its not we call the constructor of billet that will send to the database
			Billet b1 = new Billet(NomValue, PrenomValue, MailValue, ConcertValue, TypeValue);
			verif = false;
			Display(verif);
			
			windowPanel.repaint();
			JOptionPane.showMessageDialog(this,"Vous avez r�serv� votre billet !","Succ�s !",JOptionPane.INFORMATION_MESSAGE);
			
		}
	}

	public void Display(Boolean b) {//C'est la fonction afficher qui recupere un Boolean (OUI OU NON)

		windowPanel.removeAll();
		windowPanel.revalidate();
		windowPanel.add(panTitle);//Ajouter la ligne du titre
		windowPanel.add(Box.createRigidArea(new Dimension(0,13))); //Ajout d'une zone pour espacer les lignes
		windowPanel.add(panNom);
		windowPanel.add(Box.createRigidArea(new Dimension(0,8)));
		windowPanel.add(panPrenom);
		windowPanel.add(Box.createRigidArea(new Dimension(0,8)));
		windowPanel.add(panMail);
		windowPanel.add(Box.createRigidArea(new Dimension(0,8)));
		windowPanel.add(panConcert);
		windowPanel.add(Box.createRigidArea(new Dimension(0,8)));
		windowPanel.add(panPlace);
		windowPanel.add(Box.createRigidArea(new Dimension(0,13)));
		windowPanel.add(panSend);
		windowPanel.add(Box.createRigidArea(new Dimension(0,13)));
		if (b == true) {
			erreur = new JLabel("Veuillez remplir correctement les champs");
			Box panError = Box.createHorizontalBox();
			panError.add(erreur);
			windowPanel.add(panError);
			verif = false;
		}
		Box top = Box.createHorizontalBox(); //Je créer une box qui accueillera tout le monde
		top.add(Box.createHorizontalGlue());// Je mets une glue qui va pousser vers la droite
		top.add(windowPanel); // J'ajoute la box qui accueille toutes les lignes
		top.add(Box.createHorizontalGlue());//Je mets une glue qui va pousser vers la gauche (pour centrer le windowPanel)

		this.getContentPane().add(top);//J'ajoute ma box qui contient tout à ma fenetre
		this.setVisible(true);//et je dis que ma fenetre est visible
		verif = false;//je dis que verification est fausse
	}		

	public Fenetre(){

		//Window Parameters
		this.setTitle("Billeterie"); //titre de la fenetre
		this.setSize(500,500); //taille de la fenetre
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //fermeture de la fenetre
		this.setLocationRelativeTo(null); //la fenetre est centrée
		this.setResizable(false); //on ne peut pas agrandir la fenetre

		labelWelcome.setText("Reserver un billet");//Je mets un texte à mon label Welcome
		labelWelcome.setFont(new Font("Roboto", Font.BOLD, 54));//Je paramètre sa taille, il est gras	
		panTitle.add(labelWelcome);//j'ajoute mon label welcome à ma ligne (box) de titre
		panTitle.add(Box.createHorizontalGlue());//J'ajoute une glue pour mettre le label welcome à gauche

		//On ajoute tout au panel pour remplir le nom
		labelNom.setText("Votre nom : ");//Je dis qu'il sera écrit "votre nom :"
		fieldNom.setPreferredSize(new Dimension(100, 25));//je dis que le field nom a une taille
		fieldNom.setMaximumSize(new Dimension (100, 25));//je dis que le field nom a une taille
		panNom.add(labelNom);//j'ajoute le label nom dans le box nom
		panNom.add(fieldNom);//j'ajoute le field nom dans le box nom
		panNom.add(Box.createHorizontalGlue());//je centre à gauche

		//On ajoute tout au panel pour remplir le prenom
		labelPrenom.setText("Votre Prenom : ");
		fieldPrenom.setPreferredSize(new Dimension(100, 25));
		fieldPrenom.setMaximumSize(new Dimension (100, 25));
		panPrenom.add(labelPrenom);
		panPrenom.add(fieldPrenom);
		panPrenom.add(Box.createHorizontalGlue());

		//On ajoute tout au panel pour remplir le mail
		labelMail.setText("Votre Mail : ");
		fieldMail.setPreferredSize(new Dimension(220, 25));
		fieldMail.setMaximumSize(new Dimension (220, 25));
		panMail.add(labelMail);
		panMail.add(fieldMail);
		panMail.add(Box.createHorizontalGlue());

		//On ajoute tout au panel pour choisir le concert
		labelConcert.setText("Votre concert : ");
		panConcert.add(labelConcert);
		panConcert.add(comboConcert);
		comboConcert.setMaximumSize(comboConcert.getPreferredSize());
		panConcert.add(Box.createHorizontalGlue());

		//On ajoute tout au panel pour choisir la place
		labelPlace.setText("Type de place : ");
		labelPlaceAssise.setText("Assise");
		labelPlaceDebout.setText("Debout");

		buttonPlaceAssise.setSelected(true);

		groupTypePlace.add(buttonPlaceAssise);
		groupTypePlace.add(buttonPlaceDebout);
		panPlace.add(labelPlace);
		panPlace.add(labelPlaceAssise);
		panPlace.add(buttonPlaceAssise);
		panPlace.add(labelPlaceDebout);
		panPlace.add(buttonPlaceDebout);
		panPlace.add(Box.createHorizontalGlue());

		//On ajoute tout au panel pour appuyer sur le bouton envoyer
		buttonSend.setText("Reserver");
		panSend.add(buttonSend);
		buttonSend.addActionListener(this);

		verif = false;//je dis que ma verification est fausse pour qu'il n'affiche pas le message d'erreur
		Display(verif);
	}
}
