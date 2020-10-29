package classstructuremethods;

public class ClientMain {

    public static void main(String[] args) {

        Client client = new Client();

        client.setName("Luis");
        client.setYear((1980));
        client.setAddress("Győr");

        System.out.println(client.getName());
        System.out.println(client.getYear());
        System.out.println(client.getAddress());

        client.migrate("Sopron");

        System.out.println(client.getAddress());


    }


}
