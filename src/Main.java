import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONObject;

public class Main {

	public static void main(String[] args) {
		
		System.out.println("Por favor, identifiquese.");
		System.out.print("Nombre: ");
		Scanner input = new Scanner(System.in);
		String nombre = input.nextLine();
		System.out.println("Bienvenido, " + nombre + ".\n\nMostrando el clima actual...\n");
		input.close();
		
		try {
			URL url = new URL("https://www.el-tiempo.net/api/json/v2/provincias/28");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.connect();
			
			int res = conn.getResponseCode();
			if (res != 200) { throw new RuntimeException("HttpResponse: " + res);} 
			else {
				StringBuilder sb = new StringBuilder();
				Scanner sc = new Scanner(url.openStream());
				while (sc.hasNext()) { sb.append(sc.nextLine() + "\n");}
				sc.close();

				JSONObject respuesta = new JSONObject(sb.toString()) ;
				System.out.println("\t\t"+respuesta.get("title")+"\n");
				JSONObject temperaturas = respuesta.getJSONArray("ciudades").getJSONObject(0).getJSONObject("temperatures");
				System.out.println(respuesta.getJSONObject("today").get("p"));
				System.out.println("\nTemperatura mínima: "+temperaturas.get("min"));
				System.out.println("Temperatura máxima: "+temperaturas.get("max"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
