package gestionDeProjet.db;

public class TestConnection {

	public static void main(String[] args) {
		System.out.println((Connect.getInstance().getConnection()==null?true:false));
	}

}
