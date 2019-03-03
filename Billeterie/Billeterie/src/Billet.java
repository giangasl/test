import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class Billet {


	public Billet(String nom, String prenom, String mail, String concert, String type) {
		this.nom = nom;
		this.prenom = prenom;
		this.mail = mail;
		this.concert = concert;
		this.type = type;

		try {
			Class.forName("org.postgresql.Driver");
			System.out.println("Driver O.K.");

			String url = "jdbc:postgresql://localhost:1234/billet";
			String user = "postgres";
			String passwd = "Fares";

			Connection conn = DriverManager.getConnection(url, user, passwd);

			System.out.println("Connexion effective !");  



			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO billet VALUES (?, ?, ?, ?, ?)");
			pstmt.setString(1, this.nom);
			pstmt.setString(2, this.prenom);
			pstmt.setString(3, this.mail);
			pstmt.setString(4, this.concert);
			pstmt.setString(5, this.type);

			pstmt.executeUpdate();
		} catch (Exception e1) {
			e1.printStackTrace();
		} 


	}
	protected String nom;
	protected String prenom;
	protected String mail;
	protected String concert;
	protected String type;

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getConcert() {
		return concert;
	}

	public void setConcert(String concert) {
		this.concert = concert;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


}



